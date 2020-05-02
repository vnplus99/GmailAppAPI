/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gmailApi;

import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Draft;
import com.google.api.services.gmail.model.MessagePart;
import com.google.api.services.gmail.model.MessagePartHeader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class SendMailProcess {

    /**
     * doc cho ham sendMail
     *
     * @param service: mail session
     * @param toMail : String[] danh sach mail muon gui toi
     * @param cc : String[]danh sach cc
     * @param bcc: String[] danh sach bcc
     * @param subject String tieu de cua mail
     * @param body String noi dung cua mail
     * @param fileName: String[] danh sach nhung file can attach
     */
    Gmail service = GlobalVariable.getService();
    String userId = GlobalVariable.userId;
    String[] toMail;
    String[] cc;
    String[] bcc;
    String subject;
    String body;
    List<String> fileName;
    MimeMessage msg;

    public SendMailProcess(String[] toMail, String[] cc, String[] bcc, String subject, String body) {
	this.toMail = toMail;
	this.cc = cc;
	this.bcc = bcc;
	this.subject = subject;
	this.body = body;
    }

    public SendMailProcess(String[] toMail, String[] cc, String[] bcc, String subject, String body, List<String> fileName) {
	this.toMail = toMail;
	this.cc = cc;
	this.bcc = bcc;
	this.subject = subject;
	this.body = body;
	this.fileName = fileName;
    }

    /**
     * Create a message from an email.
     *
     * @param emailContent Email to be set to raw of message
     * @return a message containing a base64url encoded email
     * @throws IOException
     * @throws MessagingException
     */
    private static com.google.api.services.gmail.model.Message createMessageWithEmail(MimeMessage emailContent)
	    throws MessagingException, IOException {
	ByteArrayOutputStream buffer = new ByteArrayOutputStream();
	emailContent.writeTo(buffer);
	byte[] bytes = buffer.toByteArray();
	String encodedEmail = Base64.encodeBase64URLSafeString(bytes);
	com.google.api.services.gmail.model.Message message = new com.google.api.services.gmail.model.Message();
	message.setRaw(encodedEmail);
	return message;
    }

    // chuẩn bị phần mail là dạng text
    private void prepareTextMail() {
	Properties props = new Properties();
	Session session = Session.getDefaultInstance(props, null);

	this.msg = new MimeMessage(session);
	try {
	    // set mail header
	    msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
	    msg.addHeader("format", "flowed");
	    msg.addHeader("Content-Transfer-Encoding", "8bit");
	    // Set mail from who
	    //msg.setFrom(new InternetAddress(GetMailGmail.fromMail));
	    // set reply to
	    //msg.setReplyTo(InternetAddress.parse(GetMailGmail.fromMail,false));

	    // set cc
	    if (cc != null) {
		InternetAddress[] listcc = new InternetAddress[cc.length];
		for (int i = 0; i < cc.length; i++) {
		    listcc[i] = new InternetAddress(cc[i]);
		}
		msg.setRecipients(Message.RecipientType.CC, listcc);
	    }
	    // set bcc
	    if (bcc != null) {

		InternetAddress[] listbcc = new InternetAddress[bcc.length];
		for (int i = 0; i < bcc.length; i++) {
		    listbcc[i] = new InternetAddress(bcc[i]);
		}
		msg.setRecipients(Message.RecipientType.BCC, listbcc);
	    }
	    // set to mail
	    if (toMail != null) {
		InternetAddress[] listto = new InternetAddress[toMail.length];
		for (int i = 0; i < toMail.length; i++) {
		    listto[i] = new InternetAddress(toMail[i]);
		}
		msg.setRecipients(Message.RecipientType.TO, listto);
	    }

	    // set subject of mail
	    msg.setSubject(subject);

	    // body
	    msg.setText(body, "UTF-8");
	} catch (MessagingException e) {
	    System.out.println(e.toString());
	    System.out.println("There's something wrong with prepare text mail!");
	    msg = null;
	}
    }

    // thêm file attachment
    private void addAttachment(Multipart multiPart, String file) {
	try {
	    BodyPart attachBodyPart = new MimeBodyPart();
	    DataSource source = new FileDataSource(file);
	    DataHandler dh = new DataHandler(source);
	    attachBodyPart.setDataHandler(dh);
	    attachBodyPart.setFileName(file);
	    multiPart.addBodyPart(attachBodyPart);
	} catch (MessagingException e) {
	    System.out.println(e.toString());
	    System.out.println("Failed to add file: " + file);
	}
    }

    // thêm vào message phần attachment
    private void prepareMailAttachment() {
	// create the multi message for attachment
	Multipart multiPart = new MimeMultipart();
	try {

	    // create the body part 
	    BodyPart msgBodyPart = new MimeBodyPart();

	    // add the body message
	    msgBodyPart.setText(body);

	    // add the first multi part (text)  
	    multiPart.addBodyPart(msgBodyPart);

	    // add the second part (attachment)
	    for (int i = 0; i < fileName.size(); i++) {
		addAttachment(multiPart, fileName.get(i));
	    }

	    // set msg full part(text + attachment) 
	    msg.setContent(multiPart);
	} catch (MessagingException e) {
	    System.out.println(e.toString());
	    System.out.println("There're something wrong with prepare mail with attachment !");
	    multiPart = null;
	}
    }

    /**
     * Send an email from the user's mailbox to its recipient.
     *
     * @param service Authorized Gmail API instance.
     * @param userId User's email address. The special value "me" can be used to
     * indicate the authenticated user.
     * @param emailContent Email to be sent.
     * @throws MessagingException
     * @throws IOException
     */
    public static void sendMessage(Gmail service,
	    String userId,
	    MimeMessage emailContent)
	    throws MessagingException, IOException {
	com.google.api.services.gmail.model.Message message = createMessageWithEmail(emailContent);
	service.users().messages().send(userId, message).execute();
//	chỉ dành cho khi test chức năng
//	System.out.println("Message id: " + message.getId());
//	System.out.println(message.toPrettyString());
//	System.out.println("Send Succesful !");
//        return message;
    }

    /**
     * gửi mail
     *
     * @throws MessagingException
     * @throws IOException
     */
    public void setUpAndSend() throws MessagingException, IOException {
	if (fileName == null) {
	    // just Text mail
	    prepareTextMail();
	} else {
	    prepareTextMail();
	    prepareMailAttachment();
	}
	sendMessage(service, userId, msg);
    }

    /**
     * taọ mail như là 1 thư nháp, được tìm thấy trong hộp thư nháp
     *
     * @param service
     * @param userId
     * @param msg
     */
    public void createDraft(Gmail service,
	    String userId) {
	if (fileName == null) {
	    // just Text mail
	    prepareTextMail();
	} else {
	    prepareTextMail();
	    prepareMailAttachment();
	}
	try {
	    com.google.api.services.gmail.model.Message message = createMessageWithEmail(this.msg);
	    Draft draft = new Draft();
	    draft.setMessage(message);
	    service.users().drafts().create(userId, draft).execute();
	} catch (IOException | MessagingException ex) {
	    Logger.getLogger(SendMailProcess.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
    
        /**
     * reply a message
     *
     * @param messageId
     * @param replyMessage
     * @throws IOException
     */
    public static void reply(MessageObject msgOb, String replyMessage) throws IOException {
	// must get from old mail
	String from = null;
	String subject;
	String newSubject = "";
	String oldReferences = null;

	String messageID = null;

	Gmail service = GlobalVariable.getService();
	String userId = GlobalVariable.userId;
	// lấy thông tin của mail cũ
//	com.google.api.services.gmail.model.Message message = service.users().messages().get(userId, messageId).setFormat("full").execute();
//	String threadId = message.getThreadId();
//	
//	MessagePart payload = message.getPayload();
//	List<MessagePartHeader> headers = payload.getHeaders();
//	for (MessagePartHeader messHeadPart : headers) {
//	    if (messHeadPart.getName().equals("From")) {
//		from = messHeadPart.getValue();
//	    }
//	    if (messHeadPart.getName().equals("Subject")) {
//		subject = messHeadPart.getValue();
//		newSubject += subject;
//	    }
//	    if (messHeadPart.getName().equals("Message-ID")) {
//		messageID = messHeadPart.getValue();
//	    }
//	    if (messHeadPart.getName().equals("References")) {
//		oldReferences = messHeadPart.getValue();
//	    }
//	}
	from = msgOb.from;
	subject = msgOb.subject;
	oldReferences = msgOb.references;
	messageID = msgOb.messageID;
	// tạo mail mới
	Properties props = new Properties();
	Session session = Session.getDefaultInstance(props, null);

	MimeMessage mimeMessage = new MimeMessage(session);
	InternetAddress[] listto = new InternetAddress[1];
	InternetAddress[] listfrom = new InternetAddress[1];
	try {
	    listto[0] = new InternetAddress(from);
	    mimeMessage.setText(replyMessage);
	    mimeMessage.setRecipients(javax.mail.Message.RecipientType.TO, listto);
	    mimeMessage.setFrom(new InternetAddress(userId));
	    mimeMessage.setSubject(newSubject, "utf-8");
	    mimeMessage.setHeader("References", oldReferences + " " + messageID);
	    mimeMessage.setHeader("In-Reply-To", messageID);

	    SendMailProcess.sendMessage(service, userId, mimeMessage);
	} catch (AddressException ex) {
	    Logger.getLogger(MessageProcess.class.getName()).log(Level.SEVERE, null, ex);
	} catch (MessagingException ex) {
	    Logger.getLogger(MessageProcess.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
}
