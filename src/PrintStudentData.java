
import Database.MyConnection;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author This PC
 */
public class PrintStudentData extends javax.swing.JFrame {

    Connection con = MyConnection.getConnection();
    PreparedStatement ps;

    /**
     * Creates new form TeacherDashboard
     */
     public void retrieveAndPrintStudentData(Connection con,String registrationNo) {
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    StringBuilder studentInfo = new StringBuilder();

    try {
        String sql = "SELECT * FROM students WHERE Registration_No = ?";
        statement = con.prepareStatement(sql);
        statement.setString(1, registrationNo);

        resultSet = statement.executeQuery();
        if (resultSet.next()) {
            String indent = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
            
            // Add non-breaking spaces for the first two lines
            studentInfo.append(indent).append("<br>");
            studentInfo.append(indent).append("<br>");

            // Start printing from the third line
            studentInfo.append(indent).append("Name: ").append(resultSet.getString("name")).append("<br>");
            studentInfo.append(indent).append("Registration Number: ").append(resultSet.getString("Registration_No")).append("<br>");
            studentInfo.append(indent).append("Father's Name: ").append(resultSet.getString("father_name")).append("<br>");
            studentInfo.append(indent).append("Email: ").append(resultSet.getString("Email")).append("<br>");
            studentInfo.append(indent).append("DateOfBirth: ").append(resultSet.getDate("DateOfBirth")).append("<br>");
            studentInfo.append(indent).append("Gender: ").append(resultSet.getString("Gender")).append("<br>");
            studentInfo.append(indent).append("Department: ").append(resultSet.getString("Department")).append("<br>");
            studentInfo.append(indent).append("Address: ").append(resultSet.getString("Address")).append("<br>");
            studentInfo.append(indent).append("Phone: ").append(resultSet.getString("Phone")).append("<br>");
            studentInfo.append(indent).append("City: ").append(resultSet.getString("City")).append("<br>");
            studentInfo.append(indent).append("Section: ").append(resultSet.getString("Section")).append("<br>");
            studentInfo.append(indent).append("Student_Password: ").append(resultSet.getString("Student_Password")).append("<br>");
            studentInfo.append(indent).append("Confirm_Password: ").append(resultSet.getString("Confirm_Password")).append("<br>");

            printText(studentInfo.toString());
        } else {
            System.out.println("Student with Registration No " + registrationNo + " not found.");
        }
    } catch (SQLException | PrinterException e) {
        e.printStackTrace();
    } finally {
        // Close resources
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
public void retrieveAndPrintStudentMarks(Connection con, String registrationNo) {
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    StringBuilder marksInfo = new StringBuilder();

    try {
        String sql = "SELECT * FROM studentmarks WHERE Registration_No = ?";
        statement = con.prepareStatement(sql);
        statement.setString(1, registrationNo);

        resultSet = statement.executeQuery();
        if (resultSet.next()) {
            String indent = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
            
            // Add non-breaking spaces for the first two lines
            marksInfo.append(indent).append("<br>");
            marksInfo.append(indent).append("<br>");

            // Start printing from the third line
            marksInfo.append(indent).append("Course 1: ").append(resultSet.getString("Course1")).append("<br>");
            marksInfo.append(indent).append("Quiz 1: ").append(resultSet.getString("Quiz1")).append("<br>");
            marksInfo.append(indent).append("Assignment 1: ").append(resultSet.getString("Assignment1")).append("<br>");
            marksInfo.append(indent).append("Mids 1: ").append(resultSet.getString("Mids1")).append("<br>");
            marksInfo.append(indent).append("Terminals 1: ").append(resultSet.getString("Terminals1")).append("<br>");
            marksInfo.append(indent).append("<br>");
            marksInfo.append(indent).append("Course 2: ").append(resultSet.getString("Course2")).append("<br>");
            marksInfo.append(indent).append("Quiz 2: ").append(resultSet.getString("Quiz2")).append("<br>");
            marksInfo.append(indent).append("Assignment 2: ").append(resultSet.getString("Assignment2")).append("<br>");
            marksInfo.append(indent).append("Mids 2: ").append(resultSet.getString("Mids2")).append("<br>");
            marksInfo.append(indent).append("Terminals 2: ").append(resultSet.getString("Terminals2")).append("<br>");
            marksInfo.append(indent).append("<br>");
            marksInfo.append(indent).append("Course 3: ").append(resultSet.getString("Course3")).append("<br>");
            marksInfo.append(indent).append("Quiz 3: ").append(resultSet.getString("Quiz3")).append("<br>");
            marksInfo.append(indent).append("Assignment 3: ").append(resultSet.getString("Assignment3")).append("<br>");
            marksInfo.append(indent).append("Mids 3: ").append(resultSet.getString("Mids3")).append("<br>");
            marksInfo.append(indent).append("Terminals 3: ").append(resultSet.getString("Terminals3")).append("<br>");
            marksInfo.append(indent).append("<br>");
            marksInfo.append(indent).append("Course 4: ").append(resultSet.getString("Course4")).append("<br>");
            marksInfo.append(indent).append("Quiz4 ").append(resultSet.getString("Quiz4")).append("<br>");
            marksInfo.append(indent).append("Assignment 4: ").append(resultSet.getString("Assignment4")).append("<br>");
            marksInfo.append(indent).append("Mids 4: ").append(resultSet.getString("Mids4")).append("<br>");
            marksInfo.append(indent).append("Terminals 4: ").append(resultSet.getString("Terminals4")).append("<br>");
            marksInfo.append(indent).append("<br>");
            marksInfo.append(indent).append("Course 5: ").append(resultSet.getString("Course5")).append("<br>");
            marksInfo.append(indent).append("Quiz 5: ").append(resultSet.getString("Quiz5")).append("<br>");
            marksInfo.append(indent).append("Assignment 5: ").append(resultSet.getString("Assignment5")).append("<br>");
            marksInfo.append(indent).append("Mids 5: ").append(resultSet.getString("Mids5")).append("<br>");
            marksInfo.append(indent).append("Terminals 5: ").append(resultSet.getString("Terminals5")).append("<br>");
            marksInfo.append(indent).append("<br>");
            marksInfo.append(indent).append("Course 6: ").append(resultSet.getString("Course6")).append("<br>");
            marksInfo.append(indent).append("Quiz 6: ").append(resultSet.getString("Quiz6")).append("<br>");
            marksInfo.append(indent).append("Assignment 6: ").append(resultSet.getString("Assignment6")).append("<br>");
            marksInfo.append(indent).append("Mids 6: ").append(resultSet.getString("Mids6")).append("<br>");
            marksInfo.append(indent).append("Terminals 6: ").append(resultSet.getString("Terminals6")).append("<br>");
            // Repeat for other courses and assessments

            printText(marksInfo.toString());
        } else {
            System.out.println("No marks found for student with Registration No " + registrationNo);
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Close resources
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
    private void printText(String text) throws PrinterException {
        JEditorPane editorPane = new JEditorPane("text/html", text);
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable((graphics, pageFormat, pageIndex) -> {
            if (pageIndex > 0) {
                return Printable.NO_SUCH_PAGE;
            }
            editorPane.setSize((int) pageFormat.getImageableWidth(), (int) pageFormat.getImageableHeight());
            editorPane.print(graphics);
            return Printable.PAGE_EXISTS;
        });

        if (job.printDialog()) {
            job.print();
        }
    }
    static String registra;

    public PrintStudentData(String Reg) {
        registra = Reg;
        initComponents();

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
        jPanel2 = new javax.swing.JPanel();
        DashStudentName = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        rSButtonHover12 = new rojeru_san.complementos.RSButtonHover();
        rSButtonHover9 = new rojeru_san.complementos.RSButtonHover();
        rSButtonHover13 = new rojeru_san.complementos.RSButtonHover();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(30, 42, 71));

        DashStudentName.setForeground(new java.awt.Color(255, 255, 255));

        jLabel4.setBackground(new java.awt.Color(30, 42, 72));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/1481 (1).gif"))); // NOI18N
        jLabel4.setToolTipText("");
        jLabel4.setAutoscrolls(true);
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Student");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addComponent(jLabel4)
                .addContainerGap(103, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(DashStudentName, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(89, 89, 89))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(163, 163, 163)
                .addComponent(jLabel4)
                .addGap(2, 2, 2)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(DashStudentName, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(445, Short.MAX_VALUE))
        );

        jLayeredPane1.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(28, 62, 128), 3, true));
        jLayeredPane1.setOpaque(true);

        rSButtonHover12.setBackground(new java.awt.Color(28, 62, 128));
        rSButtonHover12.setText("Print Marks");
        rSButtonHover12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        rSButtonHover12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover12ActionPerformed(evt);
            }
        });

        rSButtonHover9.setBackground(new java.awt.Color(28, 62, 128));
        rSButtonHover9.setForeground(new java.awt.Color(0, 0, 0));
        rSButtonHover9.setText("Print Data");
        rSButtonHover9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        rSButtonHover9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover9ActionPerformed(evt);
            }
        });

        rSButtonHover13.setBackground(new java.awt.Color(28, 62, 128));
        rSButtonHover13.setText("Logout");
        rSButtonHover13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        rSButtonHover13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover13ActionPerformed(evt);
            }
        });

        jLayeredPane1.setLayer(rSButtonHover12, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(rSButtonHover9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(rSButtonHover13, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(rSButtonHover9, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(rSButtonHover12, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(rSButtonHover13, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        jLayeredPane1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {rSButtonHover12, rSButtonHover9});

        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rSButtonHover13, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonHover12, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonHover9, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        jLayeredPane1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {rSButtonHover12, rSButtonHover9});

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(28, 62, 128));
        jLabel2.setText("University Portal");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 176, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2)
                    .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(85, 85, 85))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addComponent(jLabel2)
                .addGap(168, 168, 168)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rSButtonHover9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover9ActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            retrieveAndPrintStudentData(con, registra);
        } catch (Exception ex) {
            Logger.getLogger(PrintStudentData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_rSButtonHover9ActionPerformed

    private void rSButtonHover12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover12ActionPerformed
        // TODO add your handling code here:
try {
            // TODO add your handling code here:
            retrieveAndPrintStudentMarks(con, registra);
        } catch (Exception ex) {
            Logger.getLogger(PrintStudentData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_rSButtonHover12ActionPerformed

    private void rSButtonHover13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover13ActionPerformed
        // TODO add your handling code here:
        int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to Logout?", "Logout", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            // Dispose the current frame (window)
            this.dispose();
            // Open the login window
            new LoginasStudent().setVisible(true);
        }
    }//GEN-LAST:event_rSButtonHover13ActionPerformed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        this.dispose();
        StudentDashboard sd = new StudentDashboard(registra);
        sd.setVisible(true);
    
    }//GEN-LAST:event_jLabel4MouseClicked

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
            java.util.logging.Logger.getLogger(TeacherDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TeacherDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TeacherDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TeacherDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PrintStudentData(registra).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DashStudentName;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private rojeru_san.complementos.RSButtonHover rSButtonHover12;
    private rojeru_san.complementos.RSButtonHover rSButtonHover13;
    private rojeru_san.complementos.RSButtonHover rSButtonHover9;
    // End of variables declaration//GEN-END:variables
}
