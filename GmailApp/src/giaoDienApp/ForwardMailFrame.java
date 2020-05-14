/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giaoDienApp;

import gmailApi.MessageObject;
import gmailApi.SendMailProcess;
import gmailApi.XuLyFile;
import java.awt.Frame;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class ForwardMailFrame extends javax.swing.JFrame {

    /**
     * Creates new form waitingFrame
     */
    MessageObject msgOb;
    String mainText;
    // if click cancel; returnValue = false
    public ForwardMailFrame(newMainPage parent, MessageObject msgOb) {
	this.msgOb = msgOb;
	initComponents();
	replyText_Tarea.setEditable(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        openAddFile_Bt = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        cancel_Bt = new javax.swing.JButton();
        attachFile_Jcb = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        replyText_Tarea = new javax.swing.JTextArea();
        sentForward_Bt = new javax.swing.JButton();
        forwardTo_Tf = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(600, 400));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(161, 233, 237));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(161, 233, 237));
        jLabel1.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        jLabel1.setText("Trả lời đến:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 130, 30));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 640, 10));

        openAddFile_Bt.setBackground(new java.awt.Color(161, 233, 237));
        openAddFile_Bt.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        openAddFile_Bt.setText("OPEN");
        openAddFile_Bt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        openAddFile_Bt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        openAddFile_Bt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openAddFile_BtActionPerformed(evt);
            }
        });
        jPanel1.add(openAddFile_Bt, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 340, 100, 40));

        jLabel3.setBackground(new java.awt.Color(161, 233, 237));
        jLabel3.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        jLabel3.setText("Nội dung:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        cancel_Bt.setBackground(new java.awt.Color(161, 233, 237));
        cancel_Bt.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        cancel_Bt.setText("CANCEL");
        cancel_Bt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancel_Bt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cancel_Bt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancel_BtActionPerformed(evt);
            }
        });
        jPanel1.add(cancel_Bt, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 340, 100, 40));

        jPanel1.add(attachFile_Jcb, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 340, 230, 40));

        replyText_Tarea.setColumns(20);
        replyText_Tarea.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        replyText_Tarea.setRows(5);
        jScrollPane1.setViewportView(replyText_Tarea);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 640, 260));

        sentForward_Bt.setBackground(new java.awt.Color(161, 233, 237));
        sentForward_Bt.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        sentForward_Bt.setText("SENT");
        sentForward_Bt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sentForward_Bt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sentForward_Bt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sentForward_BtActionPerformed(evt);
            }
        });
        jPanel1.add(sentForward_Bt, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 340, 100, 40));

        forwardTo_Tf.setBackground(new java.awt.Color(161, 233, 237));
        forwardTo_Tf.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        forwardTo_Tf.setToolTipText("các mail cách nhau bởi dấu \";\"");
        forwardTo_Tf.setBorder(null);
        jPanel1.add(forwardTo_Tf, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 12, 640, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 390));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void openAddFile_BtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openAddFile_BtActionPerformed
	// TODO add your handling code here:
	String filePath = XuLyFile.showOpenFileDialog();
	this.attachFile_Jcb.addItem(filePath);
    }//GEN-LAST:event_openAddFile_BtActionPerformed

    private void cancel_BtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancel_BtActionPerformed
	// TODO add your handling code here:
	this.dispose();
    }//GEN-LAST:event_cancel_BtActionPerformed

    private void sentForward_BtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sentForward_BtActionPerformed
        // TODO add your handling code here:
	// lấy to address
	String[] listTo;
	listTo = this.forwardTo_Tf.getText().trim().split(";");
	// lấy list file
	List<String> listFileAttach = new ArrayList<>();
	for(int i=0;i<this.attachFile_Jcb.getItemCount();i++){
	    listFileAttach.add(attachFile_Jcb.getItemAt(i));
	}
	// lấy main text ( nội dung chính) 
	this.mainText = this.replyText_Tarea.getText();
	try {
	    SendMailProcess.forwardMail(msgOb,listTo, mainText,  listFileAttach);
	    JOptionPane.showMessageDialog(this, "Bạn đã chuyển tiếp thành công !");
	    this.dispose();
	} catch (IOException ex) {
	    JOptionPane.showMessageDialog(this, "Bạn đã chuyển tiếp thất bại!");
	    Logger.getLogger(ForwardMailFrame.class.getName()).log(Level.SEVERE, null, ex);
	}
	
    }//GEN-LAST:event_sentForward_BtActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
	/* Set the Nimbus look and feel */
	//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
	/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
	 */
	try {
	    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
		if ("Nimbus".equals(info.getName())) {
		    javax.swing.UIManager.setLookAndFeel(info.getClassName());
		    break;
		}
	    }
	} catch (ClassNotFoundException ex) {
	    java.util.logging.Logger.getLogger(ForwardMailFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (InstantiationException ex) {
	    java.util.logging.Logger.getLogger(ForwardMailFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (IllegalAccessException ex) {
	    java.util.logging.Logger.getLogger(ForwardMailFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (javax.swing.UnsupportedLookAndFeelException ex) {
	    java.util.logging.Logger.getLogger(ForwardMailFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	}
	//</editor-fold>
	//</editor-fold>

	/* Create and display the form */
//	java.awt.EventQueue.invokeLater(new Runnable() {
//	    public void run() {
//		new ReplyMailFrame().setVisible(true);
//	    }
//	});
	//</editor-fold>
	//</editor-fold>

	/* Create and display the form */
//	java.awt.EventQueue.invokeLater(new Runnable() {
//	    public void run() {
//		new ReplyMailFrame().setVisible(true);
//	    }
//	});
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> attachFile_Jcb;
    private javax.swing.JButton cancel_Bt;
    private javax.swing.JTextField forwardTo_Tf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton openAddFile_Bt;
    private javax.swing.JTextArea replyText_Tarea;
    private javax.swing.JButton sentForward_Bt;
    // End of variables declaration//GEN-END:variables
}
