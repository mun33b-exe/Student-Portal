
import Database.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author This PC
 */
public class UpdateMarks extends javax.swing.JFrame {

    Connection con = MyConnection.getConnection();
    PreparedStatement ps;

    public void updateMarks(String Registration_No, String course1,
            String quiz1, String assignment1, String mids1, String terminals1, String course2,
            String quiz2, String assignment2, String mids2, String terminals2, String course3,
            String quiz3, String assignment3, String mids3, String terminals3, String course4,
            String quiz4, String assignment4, String mids4, String terminals4, String course5,
            String quiz5, String assignment5, String mids5, String terminals5, String course6,
            String quiz6, String assignment6, String mids6, String terminals6
    ) throws SQLException {

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = MyConnection.getConnection();
            String query = "UPDATE studentmarks SET "
                    + "Course1=?, Quiz1=?, Assignment1=?, Mids1=?, Terminals1=?, "
                    + "Course2=?, Quiz2=?, Assignment2=?, Mids2=?, Terminals2=?, "
                    + "Course3=?, Quiz3=?, Assignment3=?, Mids3=?, Terminals3=?, "
                    + "Course4=?, Quiz4=?, Assignment4=?, Mids4=?, Terminals4=?, "
                    + "Course5=?, Quiz5=?, Assignment5=?, Mids5=?, Terminals5=?, "
                    + "Course6=?, Quiz6=?, Assignment6=?, Mids6=?, Terminals6=? "
                    + "WHERE Registration_No=?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, course1);
            pstmt.setString(2, quiz1);
            pstmt.setString(3, assignment1);
            pstmt.setString(4, mids1);
            pstmt.setString(5, terminals1);
            pstmt.setString(6, course2);
            pstmt.setString(7, quiz2);
            pstmt.setString(8, assignment2);
            pstmt.setString(9, mids2);
            pstmt.setString(10, terminals2);
            pstmt.setString(11, course3);
            pstmt.setString(12, quiz3);
            pstmt.setString(13, assignment3);
            pstmt.setString(14, mids3);
            pstmt.setString(15, terminals3);
            pstmt.setString(16, course4);
            pstmt.setString(17, quiz4);
            pstmt.setString(18, assignment4);
            pstmt.setString(19, mids4);
            pstmt.setString(20, terminals4);
            pstmt.setString(21, course5);
            pstmt.setString(22, quiz5);
            pstmt.setString(23, assignment5);
            pstmt.setString(24, mids5);
            pstmt.setString(25, terminals5);
            pstmt.setString(26, course6);
            pstmt.setString(27, quiz6);
            pstmt.setString(28, assignment6);
            pstmt.setString(29, mids6);
            pstmt.setString(30, terminals6);
            pstmt.setString(31, Registration_No);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            // Log the error or show a user-friendly message
            e.printStackTrace();
            throw e; // Rethrow the exception to handle it at a higher level
        } finally {
            // Close resources in finally block to ensure they are always closed
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                // Log the error or show a user-friendly message
                ex.printStackTrace();
            }
        }
    }
public boolean isInquizRange(JTextField textField) {
        try {
            double value = 0; // Initialize the value
            if (textField.getText() != null && !textField.getText().isEmpty()) {
                value = Double.parseDouble(textField.getText());
            }

            if (value >= 0 && value <= 15) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Enter quiz marks in the range of 0 to 15");
                textField.setText(null); // Clear the field if the value is out of range
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Enter valid numeric quiz marks");
            textField.setText(null); // Clear the field if invalid numeric input
            return false;
        }
    }

    public boolean isInassignmentRange(JTextField textField) {
        try {
            double value = 0;
            if (textField.getText() != null && !textField.getText().isEmpty()) {
                value = Double.parseDouble(textField.getText());
            }

            if (value >= 0 && value <= 10) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Enter assignment marks in the range of 0 to 10");
                textField.setText(null); // Clear the field if the value is out of range
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Enter valid numeric assignment marks");
            textField.setText(null); // Clear the field if invalid numeric input
            return false;
        }
    }

    public boolean isInmidsRange(JTextField textField) {
        try {
            double value = 0;
            if (textField.getText() != null && !textField.getText().isEmpty()) {
                value = Double.parseDouble(textField.getText());
            }

            if (value >= 0 && value <= 25) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Enter mids marks in the range of 0 to 25");
                textField.setText(null);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Enter valid numeric quiz marks");
            textField.setText(null);
            return false;
        }
    }

    public boolean isInterminalRange(JTextField textField) {
        try {
            double value = 0;
            if (textField.getText() != null && !textField.getText().isEmpty()) {
                value = Double.parseDouble(textField.getText());
            }

            if (value >= 0 && value <= 50) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Enter terminal marks in the range of 0 to 50");
                textField.setText(null);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Enter valid numeric Terminal marks");
            textField.setText(null);
            return false;
        }
    }

    public void setMarksToTextFields(String Registration_No, JTextField Course1, JTextField Quiz1, JTextField Assignment1, JTextField Mids1, JTextField Terminal1,
            JTextField Course2, JTextField Quiz2, JTextField Assignment2, JTextField Mids2, JTextField Terminal2,
            JTextField Course3, JTextField Quiz3, JTextField Assignment3, JTextField Mids3, JTextField Terminal3,
            JTextField Course4, JTextField Quiz4, JTextField Assignment4, JTextField Mids4, JTextField Terminal4,
            JTextField Course5, JTextField Quiz5, JTextField Assignment5, JTextField Mids5, JTextField Terminal5,
            JTextField Course6, JTextField Quiz6, JTextField Assignment6, JTextField Mids6, JTextField Terminal6) {
        String sql = "SELECT * FROM studentmarks WHERE Registration_No = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, Registration_No);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Course1.setText(rs.getString("course1"));
                    Quiz1.setText(rs.getString("quiz1"));
                    Assignment1.setText(rs.getString("assignment1"));
                    Mids1.setText(rs.getString("mids1"));
                    Terminal1.setText(rs.getString("terminals1"));

                    Course2.setText(rs.getString("course2"));
                    Quiz2.setText(rs.getString("quiz2"));
                    Assignment2.setText(rs.getString("assignment2"));
                    Mids2.setText(rs.getString("mids2"));
                    Terminal2.setText(rs.getString("terminals2"));

                    Course3.setText(rs.getString("course3"));
                    Quiz3.setText(rs.getString("quiz3"));
                    Assignment3.setText(rs.getString("assignment3"));
                    Mids3.setText(rs.getString("mids3"));
                    Terminal3.setText(rs.getString("terminals3"));

                    Course4.setText(rs.getString("course4"));
                    Quiz4.setText(rs.getString("quiz4"));
                    Assignment4.setText(rs.getString("assignment4"));
                    Mids4.setText(rs.getString("mids4"));
                    Terminal4.setText(rs.getString("terminals4"));

                    Course5.setText(rs.getString("course5"));
                    Quiz5.setText(rs.getString("quiz5"));
                    Assignment5.setText(rs.getString("assignment5"));
                    Mids5.setText(rs.getString("mids5"));
                    Terminal5.setText(rs.getString("terminals5"));

                    Course6.setText(rs.getString("course6"));
                    Quiz6.setText(rs.getString("quiz6"));
                    Assignment6.setText(rs.getString("assignment6"));
                    Mids6.setText(rs.getString("mids6"));
                    Terminal6.setText(rs.getString("terminals6"));
                } else {
                    JOptionPane.showMessageDialog(null, "The Registration No didn't exist. Enter the record first.");
                    Course1.setText("");
                    Quiz1.setText("");
                    Assignment1.setText("");
                    Mids1.setText("");
                    Terminals1.setText("");

                    Course2.setText("");
                    Quiz2.setText("");
                    Assignment2.setText("");
                    Mids2.setText("");
                    Terminals2.setText("");

                    Course3.setText("");
                    Quiz3.setText("");
                    Assignment3.setText("");
                    Mids3.setText("");
                    Terminals3.setText("");

                    Course4.setText("");
                    Quiz4.setText("");
                    Assignment4.setText("");
                    Mids4.setText("");
                    Terminal4.setText("");

                    Course5.setText("");
                    Quiz5.setText("");
                    Assignment5.setText("");
                    Mids5.setText("");
                    Terminal5.setText("");

                    Course6.setText("");
                    Quiz6.setText("");
                    Assignment6.setText("");
                    Mids6.setText("");
                    Terminal6.setText("");
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(Marks.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    static String Registra;

    /**
     * Creates new form Marks
     */
    public UpdateMarks(String Reg_no) {
        initComponents();
        Registra = Reg_no;

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
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        rSButtonHover2 = new rojeru_san.complementos.RSButtonHover();
        rSButtonHover3 = new rojeru_san.complementos.RSButtonHover();
        jLayeredPane4 = new javax.swing.JLayeredPane();
        rSButtonHover7 = new rojeru_san.complementos.RSButtonHover();
        Registrationfind = new app.bolivia.swing.JCTextField();
        jLabel2 = new javax.swing.JLabel();
        jLayeredPane5 = new javax.swing.JLayeredPane();
        jLayeredPane19 = new javax.swing.JLayeredPane();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        Quiz3 = new app.bolivia.swing.JCTextField();
        Assignment3 = new app.bolivia.swing.JCTextField();
        Mids3 = new app.bolivia.swing.JCTextField();
        Terminals3 = new app.bolivia.swing.JCTextField();
        jLayeredPane11 = new javax.swing.JLayeredPane();
        Course5 = new app.bolivia.swing.JCTextField();
        jLayeredPane10 = new javax.swing.JLayeredPane();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        Quiz5 = new app.bolivia.swing.JCTextField();
        Assignment5 = new app.bolivia.swing.JCTextField();
        Mids5 = new app.bolivia.swing.JCTextField();
        Terminals5 = new app.bolivia.swing.JCTextField();
        jLayeredPane9 = new javax.swing.JLayeredPane();
        Course2 = new app.bolivia.swing.JCTextField();
        jLayeredPane20 = new javax.swing.JLayeredPane();
        Course6 = new app.bolivia.swing.JCTextField();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Quiz1 = new app.bolivia.swing.JCTextField();
        Assignment1 = new app.bolivia.swing.JCTextField();
        Mids1 = new app.bolivia.swing.JCTextField();
        Terminals1 = new app.bolivia.swing.JCTextField();
        jLayeredPane21 = new javax.swing.JLayeredPane();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        Quiz6 = new app.bolivia.swing.JCTextField();
        Assignment6 = new app.bolivia.swing.JCTextField();
        Mids6 = new app.bolivia.swing.JCTextField();
        Terminals6 = new app.bolivia.swing.JCTextField();
        jLayeredPane18 = new javax.swing.JLayeredPane();
        Course3 = new app.bolivia.swing.JCTextField();
        jLayeredPane17 = new javax.swing.JLayeredPane();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        Quiz4 = new app.bolivia.swing.JCTextField();
        Assignment4 = new app.bolivia.swing.JCTextField();
        Mids4 = new app.bolivia.swing.JCTextField();
        Terminals4 = new app.bolivia.swing.JCTextField();
        jLayeredPane8 = new javax.swing.JLayeredPane();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        Quiz2 = new app.bolivia.swing.JCTextField();
        Assignment2 = new app.bolivia.swing.JCTextField();
        Mids2 = new app.bolivia.swing.JCTextField();
        Terminals2 = new app.bolivia.swing.JCTextField();
        jLayeredPane16 = new javax.swing.JLayeredPane();
        Course4 = new app.bolivia.swing.JCTextField();
        jLayeredPane6 = new javax.swing.JLayeredPane();
        Course1 = new app.bolivia.swing.JCTextField();
        jLayeredPane23 = new javax.swing.JLayeredPane();
        jLabel3 = new javax.swing.JLabel();
        jLayeredPane24 = new javax.swing.JLayeredPane();
        rSButtonHover8 = new rojeru_san.complementos.RSButtonHover();
        rSButtonHover10 = new rojeru_san.complementos.RSButtonHover();
        rSButtonHover11 = new rojeru_san.complementos.RSButtonHover();
        jLayeredPane22 = new javax.swing.JLayeredPane();
        lcourse1 = new javax.swing.JLabel();
        lcourse4 = new javax.swing.JLabel();
        lcourse3 = new javax.swing.JLabel();
        lcourse2 = new javax.swing.JLabel();
        scgpa1 = new app.bolivia.swing.JCTextField();
        scgpa2 = new app.bolivia.swing.JCTextField();
        scgpa3 = new app.bolivia.swing.JCTextField();
        scgpa4 = new app.bolivia.swing.JCTextField();
        lcourse5 = new javax.swing.JLabel();
        scgpa5 = new app.bolivia.swing.JCTextField();
        lcourse6 = new javax.swing.JLabel();
        scgpa6 = new app.bolivia.swing.JCTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(30, 42, 70));
        jPanel1.setPreferredSize(new java.awt.Dimension(1080, 720));

        jLabel4.setBackground(new java.awt.Color(30, 42, 72));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/1481 (1).gif"))); // NOI18N
        jLabel4.setToolTipText("");
        jLabel4.setAutoscrolls(true);
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Update Marks");

        jLayeredPane2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 5, true));

        rSButtonHover2.setText("Register");
        rSButtonHover2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N

        rSButtonHover3.setText("Clear");
        rSButtonHover3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N

        jLayeredPane2.setLayer(rSButtonHover2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(rSButtonHover3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(rSButtonHover2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(rSButtonHover3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rSButtonHover2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonHover3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLayeredPane4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));

        rSButtonHover7.setText("Fetch");
        rSButtonHover7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        rSButtonHover7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover7ActionPerformed(evt);
            }
        });

        Registrationfind.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Registration No : ");

        jLayeredPane4.setLayer(rSButtonHover7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(Registrationfind, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane4Layout = new javax.swing.GroupLayout(jLayeredPane4);
        jLayeredPane4.setLayout(jLayeredPane4Layout);
        jLayeredPane4Layout.setHorizontalGroup(
            jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Registrationfind, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(rSButtonHover7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jLayeredPane4Layout.setVerticalGroup(
            jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(Registrationfind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonHover7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        jLayeredPane19.setBackground(new java.awt.Color(30, 46, 101));
        jLayeredPane19.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Quizzes");

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Terminals");

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("Mids");

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("Assignments");

        Quiz3.setText("0.0");

        Assignment3.setText("0.0");

        Mids3.setText("0.0");
        Mids3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Mids3ActionPerformed(evt);
            }
        });

        Terminals3.setText("0.0");

        jLayeredPane19.setLayer(jLabel34, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane19.setLayer(jLabel35, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane19.setLayer(jLabel36, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane19.setLayer(jLabel37, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane19.setLayer(Quiz3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane19.setLayer(Assignment3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane19.setLayer(Mids3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane19.setLayer(Terminals3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane19Layout = new javax.swing.GroupLayout(jLayeredPane19);
        jLayeredPane19.setLayout(jLayeredPane19Layout);
        jLayeredPane19Layout.setHorizontalGroup(
            jLayeredPane19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane19Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jLayeredPane19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel37)
                    .addComponent(jLabel34)
                    .addComponent(jLabel36)
                    .addComponent(jLabel35))
                .addGap(15, 15, 15)
                .addGroup(jLayeredPane19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(Quiz3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Assignment3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Mids3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Terminals3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane19Layout.setVerticalGroup(
            jLayeredPane19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Quiz3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jLayeredPane19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Assignment3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jLayeredPane19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel36)
                    .addComponent(Mids3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jLayeredPane19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Terminals3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLayeredPane11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        Course5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        Course5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Course5.setPlaceholder("Fetch Courses First");

        jLayeredPane11.setLayer(Course5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane11Layout = new javax.swing.GroupLayout(jLayeredPane11);
        jLayeredPane11.setLayout(jLayeredPane11Layout);
        jLayeredPane11Layout.setHorizontalGroup(
            jLayeredPane11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Course5, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                .addContainerGap())
        );
        jLayeredPane11Layout.setVerticalGroup(
            jLayeredPane11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Course5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLayeredPane10.setBackground(new java.awt.Color(30, 46, 101));
        jLayeredPane10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Quizzes");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Terminals");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Mids");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Assignments");

        Quiz5.setText("0.0");

        Assignment5.setText("0.0");

        Mids5.setText("0.0");
        Mids5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Mids5ActionPerformed(evt);
            }
        });

        Terminals5.setText("0.0");

        jLayeredPane10.setLayer(jLabel18, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane10.setLayer(jLabel19, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane10.setLayer(jLabel20, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane10.setLayer(jLabel21, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane10.setLayer(Quiz5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane10.setLayer(Assignment5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane10.setLayer(Mids5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane10.setLayer(Terminals5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane10Layout = new javax.swing.GroupLayout(jLayeredPane10);
        jLayeredPane10.setLayout(jLayeredPane10Layout);
        jLayeredPane10Layout.setHorizontalGroup(
            jLayeredPane10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane10Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jLayeredPane10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel21)
                    .addComponent(jLabel18)
                    .addComponent(jLabel20)
                    .addComponent(jLabel19))
                .addGap(15, 15, 15)
                .addGroup(jLayeredPane10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(Quiz5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Assignment5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Mids5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Terminals5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane10Layout.setVerticalGroup(
            jLayeredPane10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Quiz5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jLayeredPane10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Assignment5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jLayeredPane10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel20)
                    .addComponent(Mids5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jLayeredPane10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Terminals5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLayeredPane9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        Course2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        Course2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Course2.setPlaceholder("Fetch Courses First");

        jLayeredPane9.setLayer(Course2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane9Layout = new javax.swing.GroupLayout(jLayeredPane9);
        jLayeredPane9.setLayout(jLayeredPane9Layout);
        jLayeredPane9Layout.setHorizontalGroup(
            jLayeredPane9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane9Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(Course2, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                .addContainerGap())
        );
        jLayeredPane9Layout.setVerticalGroup(
            jLayeredPane9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Course2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLayeredPane20.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        Course6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        Course6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Course6.setPlaceholder("Fetch Courses First");

        jLayeredPane20.setLayer(Course6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane20Layout = new javax.swing.GroupLayout(jLayeredPane20);
        jLayeredPane20.setLayout(jLayeredPane20Layout);
        jLayeredPane20Layout.setHorizontalGroup(
            jLayeredPane20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Course6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jLayeredPane20Layout.setVerticalGroup(
            jLayeredPane20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Course6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLayeredPane1.setBackground(new java.awt.Color(30, 46, 101));
        jLayeredPane1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Quizzes");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Terminals");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Mids");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Assignments");

        Quiz1.setText("0.0");

        Assignment1.setText("0.0");

        Mids1.setText("0.0");
        Mids1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Mids1ActionPerformed(evt);
            }
        });

        Terminals1.setText("0.0");

        jLayeredPane1.setLayer(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(Quiz1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(Assignment1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(Mids1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(Terminals1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel8)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10))
                .addGap(15, 15, 15)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(Quiz1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Assignment1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Mids1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Terminals1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLayeredPane1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel10, jLabel6, jLabel7, jLabel8});

        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Quiz1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Assignment1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel7)
                    .addComponent(Mids1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Terminals1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLayeredPane1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {Quiz1, jLabel10, jLabel6, jLabel7});

        jLayeredPane21.setBackground(new java.awt.Color(30, 46, 101));
        jLayeredPane21.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("Quizzes");

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Terminals");

        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("Mids");

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("Assignments");

        Quiz6.setText("0.0");

        Assignment6.setText("0.0");

        Mids6.setText("0.0");
        Mids6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Mids6ActionPerformed(evt);
            }
        });

        Terminals6.setText("0.0");

        jLayeredPane21.setLayer(jLabel38, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane21.setLayer(jLabel39, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane21.setLayer(jLabel40, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane21.setLayer(jLabel41, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane21.setLayer(Quiz6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane21.setLayer(Assignment6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane21.setLayer(Mids6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane21.setLayer(Terminals6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane21Layout = new javax.swing.GroupLayout(jLayeredPane21);
        jLayeredPane21.setLayout(jLayeredPane21Layout);
        jLayeredPane21Layout.setHorizontalGroup(
            jLayeredPane21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane21Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jLayeredPane21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel41)
                    .addComponent(jLabel38)
                    .addComponent(jLabel40)
                    .addComponent(jLabel39))
                .addGap(15, 15, 15)
                .addGroup(jLayeredPane21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(Quiz6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Assignment6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Mids6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Terminals6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane21Layout.setVerticalGroup(
            jLayeredPane21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Quiz6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jLayeredPane21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Assignment6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jLayeredPane21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel40)
                    .addComponent(Mids6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jLayeredPane21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Terminals6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLayeredPane18.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        Course3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        Course3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Course3.setPlaceholder("Fetch Courses First");

        jLayeredPane18.setLayer(Course3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane18Layout = new javax.swing.GroupLayout(jLayeredPane18);
        jLayeredPane18.setLayout(jLayeredPane18Layout);
        jLayeredPane18Layout.setHorizontalGroup(
            jLayeredPane18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Course3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jLayeredPane18Layout.setVerticalGroup(
            jLayeredPane18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Course3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLayeredPane17.setBackground(new java.awt.Color(30, 46, 101));
        jLayeredPane17.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Quizzes");

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Terminals");

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Mids");

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Assignments");

        Quiz4.setText("0.0");

        Assignment4.setText("0.0");

        Mids4.setText("0.0");
        Mids4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Mids4ActionPerformed(evt);
            }
        });

        Terminals4.setText("0.0");

        jLayeredPane17.setLayer(jLabel30, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane17.setLayer(jLabel31, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane17.setLayer(jLabel32, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane17.setLayer(jLabel33, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane17.setLayer(Quiz4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane17.setLayer(Assignment4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane17.setLayer(Mids4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane17.setLayer(Terminals4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane17Layout = new javax.swing.GroupLayout(jLayeredPane17);
        jLayeredPane17.setLayout(jLayeredPane17Layout);
        jLayeredPane17Layout.setHorizontalGroup(
            jLayeredPane17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane17Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jLayeredPane17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel33)
                    .addComponent(jLabel30)
                    .addComponent(jLabel32)
                    .addComponent(jLabel31))
                .addGap(15, 15, 15)
                .addGroup(jLayeredPane17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(Quiz4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Assignment4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Mids4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Terminals4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane17Layout.setVerticalGroup(
            jLayeredPane17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Quiz4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jLayeredPane17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Assignment4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jLayeredPane17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel32)
                    .addComponent(Mids4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jLayeredPane17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Terminals4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLayeredPane8.setBackground(new java.awt.Color(30, 46, 101));
        jLayeredPane8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Quizzes");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Terminals");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Mids");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Assignments");

        Quiz2.setText("0.0");
        Quiz2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Quiz2ActionPerformed(evt);
            }
        });

        Assignment2.setText("0.0");

        Mids2.setText("0.0");
        Mids2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Mids2ActionPerformed(evt);
            }
        });

        Terminals2.setText("0.0");

        jLayeredPane8.setLayer(jLabel14, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane8.setLayer(jLabel15, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane8.setLayer(jLabel16, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane8.setLayer(jLabel17, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane8.setLayer(Quiz2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane8.setLayer(Assignment2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane8.setLayer(Mids2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane8.setLayer(Terminals2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane8Layout = new javax.swing.GroupLayout(jLayeredPane8);
        jLayeredPane8.setLayout(jLayeredPane8Layout);
        jLayeredPane8Layout.setHorizontalGroup(
            jLayeredPane8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane8Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jLayeredPane8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel17)
                    .addComponent(jLabel14)
                    .addComponent(jLabel16)
                    .addComponent(jLabel15))
                .addGap(15, 15, 15)
                .addGroup(jLayeredPane8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(Quiz2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Assignment2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Mids2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Terminals2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane8Layout.setVerticalGroup(
            jLayeredPane8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Quiz2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jLayeredPane8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Assignment2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jLayeredPane8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel16)
                    .addComponent(Mids2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jLayeredPane8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Terminals2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLayeredPane16.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        Course4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        Course4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Course4.setPlaceholder("Fetch Courses First");

        jLayeredPane16.setLayer(Course4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane16Layout = new javax.swing.GroupLayout(jLayeredPane16);
        jLayeredPane16.setLayout(jLayeredPane16Layout);
        jLayeredPane16Layout.setHorizontalGroup(
            jLayeredPane16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Course4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jLayeredPane16Layout.setVerticalGroup(
            jLayeredPane16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Course4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLayeredPane6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        Course1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        Course1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Course1.setPlaceholder("Fetch Courses First");

        jLayeredPane6.setLayer(Course1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane6Layout = new javax.swing.GroupLayout(jLayeredPane6);
        jLayeredPane6.setLayout(jLayeredPane6Layout);
        jLayeredPane6Layout.setHorizontalGroup(
            jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Course1, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                .addContainerGap())
        );
        jLayeredPane6Layout.setVerticalGroup(
            jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Course1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLayeredPane5.setLayer(jLayeredPane19, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(jLayeredPane11, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(jLayeredPane10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(jLayeredPane9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(jLayeredPane20, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(jLayeredPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(jLayeredPane21, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(jLayeredPane18, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(jLayeredPane17, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(jLayeredPane8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(jLayeredPane16, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(jLayeredPane6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane5Layout = new javax.swing.GroupLayout(jLayeredPane5);
        jLayeredPane5.setLayout(jLayeredPane5Layout);
        jLayeredPane5Layout.setHorizontalGroup(
            jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jLayeredPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLayeredPane16)
                        .addComponent(jLayeredPane17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jLayeredPane9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLayeredPane8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLayeredPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLayeredPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLayeredPane18)
                    .addComponent(jLayeredPane19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLayeredPane20)
                    .addComponent(jLayeredPane21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jLayeredPane5Layout.setVerticalGroup(
            jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane5Layout.createSequentialGroup()
                        .addComponent(jLayeredPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jLayeredPane5Layout.createSequentialGroup()
                        .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLayeredPane18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLayeredPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLayeredPane19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLayeredPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(10, 10, 10)
                .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPane20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLayeredPane16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLayeredPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPane21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLayeredPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLayeredPane17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(184, 184, 184))
        );

        jLayeredPane23.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("SGPA");

        jLayeredPane23.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane23Layout = new javax.swing.GroupLayout(jLayeredPane23);
        jLayeredPane23.setLayout(jLayeredPane23Layout);
        jLayeredPane23Layout.setHorizontalGroup(
            jLayeredPane23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane23Layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(jLabel3)
                .addContainerGap(107, Short.MAX_VALUE))
        );
        jLayeredPane23Layout.setVerticalGroup(
            jLayeredPane23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLayeredPane24.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        rSButtonHover8.setText("Update");
        rSButtonHover8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        rSButtonHover8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover8ActionPerformed(evt);
            }
        });

        rSButtonHover10.setText("Print");
        rSButtonHover10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        rSButtonHover10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover10ActionPerformed(evt);
            }
        });

        rSButtonHover11.setText("Logout");
        rSButtonHover11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        rSButtonHover11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover11ActionPerformed(evt);
            }
        });

        jLayeredPane24.setLayer(rSButtonHover8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane24.setLayer(rSButtonHover10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane24.setLayer(rSButtonHover11, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane24Layout = new javax.swing.GroupLayout(jLayeredPane24);
        jLayeredPane24.setLayout(jLayeredPane24Layout);
        jLayeredPane24Layout.setHorizontalGroup(
            jLayeredPane24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane24Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jLayeredPane24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rSButtonHover8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonHover10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonHover11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jLayeredPane24Layout.setVerticalGroup(
            jLayeredPane24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane24Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(rSButtonHover8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rSButtonHover10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rSButtonHover11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jLayeredPane22.setBackground(new java.awt.Color(30, 46, 101));
        jLayeredPane22.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        lcourse1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lcourse1.setForeground(new java.awt.Color(255, 255, 255));
        lcourse1.setText("Course 1");

        lcourse4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lcourse4.setForeground(new java.awt.Color(255, 255, 255));
        lcourse4.setText("Course 4");

        lcourse3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lcourse3.setForeground(new java.awt.Color(255, 255, 255));
        lcourse3.setText("Course 3");

        lcourse2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lcourse2.setForeground(new java.awt.Color(255, 255, 255));
        lcourse2.setText("Course 2");

        scgpa1.setText("0.0");

        scgpa2.setText("0.0");

        scgpa3.setText("0.0");
        scgpa3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scgpa3ActionPerformed(evt);
            }
        });

        scgpa4.setText("0.0");

        lcourse5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lcourse5.setForeground(new java.awt.Color(255, 255, 255));
        lcourse5.setText("Course 5");

        scgpa5.setText("0.0");

        lcourse6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lcourse6.setForeground(new java.awt.Color(255, 255, 255));
        lcourse6.setText("Course 6");

        scgpa6.setText("0.0");

        jLayeredPane22.setLayer(lcourse1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane22.setLayer(lcourse4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane22.setLayer(lcourse3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane22.setLayer(lcourse2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane22.setLayer(scgpa1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane22.setLayer(scgpa2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane22.setLayer(scgpa3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane22.setLayer(scgpa4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane22.setLayer(lcourse5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane22.setLayer(scgpa5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane22.setLayer(lcourse6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane22.setLayer(scgpa6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane22Layout = new javax.swing.GroupLayout(jLayeredPane22);
        jLayeredPane22.setLayout(jLayeredPane22Layout);
        jLayeredPane22Layout.setHorizontalGroup(
            jLayeredPane22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lcourse6, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                    .addComponent(lcourse4, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                    .addComponent(lcourse3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lcourse2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lcourse1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lcourse5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
                .addGroup(jLayeredPane22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jLayeredPane22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jLayeredPane22Layout.createSequentialGroup()
                                    .addGap(26, 26, 26)
                                    .addComponent(scgpa1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(scgpa2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(scgpa3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(scgpa4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(scgpa5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(scgpa6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jLayeredPane22Layout.setVerticalGroup(
            jLayeredPane22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lcourse1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scgpa1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jLayeredPane22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lcourse2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scgpa2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jLayeredPane22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lcourse3)
                    .addComponent(scgpa3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jLayeredPane22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lcourse4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scgpa4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jLayeredPane22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lcourse5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scgpa5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jLayeredPane22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lcourse6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scgpa6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(300, 300, 300))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(457, 457, 457))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLayeredPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLayeredPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPane23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLayeredPane22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLayeredPane24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel5)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLayeredPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLayeredPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1304, 1304, 1304)
                        .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLayeredPane22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLayeredPane24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rSButtonHover7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover7ActionPerformed

        String reg = Registrationfind.getText();
        setMarksToTextFields(Registrationfind.getText(), Course1, Quiz1, Assignment1, Mids1, Terminals1, Course2, Quiz2, Assignment2, Mids2, Terminals2, Course3, Quiz3, Assignment3, Mids3, Terminals3, Course4, Quiz4, Assignment4, Mids4, Terminals4, Course5, Quiz5, Assignment5, Mids5, Terminals5, Course6, Quiz6, Assignment6, Mids6, Terminals6);
    }//GEN-LAST:event_rSButtonHover7ActionPerformed

    private void rSButtonHover8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover8ActionPerformed
        // TODO add your handling code here:
        if (Registrationfind.getText() == null) {
            JOptionPane.showMessageDialog(null, "Enter the Registration No and fetch data first");
        } else {
            String reg = Registrationfind.getText();
            String course1 = Course1.getText();
            String quiz1 = Quiz1.getText();
            String assignment1 = Assignment1.getText();
            String mids1 = Mids1.getText();
            String terminal1 = Terminals1.getText();

            String course2 = Course2.getText();
            String quiz2 = Quiz2.getText();
            String assignment2 = Assignment2.getText();
            String mids2 = Mids2.getText();
            String terminal2 = Terminals2.getText();

            String course3 = Course3.getText();
            String quiz3 = Quiz3.getText();
            String assignment3 = Assignment3.getText();
            String mids3 = Mids3.getText();
            String terminal3 = Terminals3.getText();

            String course4 = Course4.getText();
            String quiz4 = Quiz4.getText();
            String assignment4 = Assignment4.getText();
            String mids4 = Mids4.getText();
            String terminal4 = Terminals4.getText();

            String course5 = Course5.getText();
            String quiz5 = Quiz5.getText();
            String assignment5 = Assignment5.getText();
            String mids5 = Mids5.getText();
            String terminal5 = Terminals5.getText();

            String course6 = Course6.getText();
            String quiz6 = Quiz6.getText();
            String assignment6 = Assignment6.getText();
            String mids6 = Mids6.getText();
            String terminal6 = Terminals6.getText();
            try {
                updateMarks(reg, course1, quiz1, assignment1, mids1, terminal1, course2, quiz2, assignment2, mids2, terminal2, course3, quiz3, assignment3, mids3, terminal3, course4, quiz4, assignment4, mids4, terminal4, course5, quiz5, assignment5, mids5, terminal5, course6, quiz6, assignment6, mids6, terminal6);

            } catch (SQLException ex) {
                Logger.getLogger(Marks.class.getName()).log(Level.SEVERE, null, ex);
            }
            double Score1 = 0, Score2 = 0, Score3 = 0, Score4 = 0, Score5 = 0, Score6 = 0;
            Score1 = ((Double.parseDouble(quiz1) + Double.parseDouble(assignment1) + Double.parseDouble(mids1) + Double.parseDouble(terminal1)) / (25));
            Score2 = ((Double.parseDouble(quiz2) + Double.parseDouble(assignment2) + Double.parseDouble(mids2) + Double.parseDouble(terminal2)) / (25));
            Score3 = ((Double.parseDouble(quiz3) + Double.parseDouble(assignment3) + Double.parseDouble(mids3) + Double.parseDouble(terminal3)) / (25));
            Score4 = ((Double.parseDouble(quiz4) + Double.parseDouble(assignment4) + Double.parseDouble(mids4) + Double.parseDouble(terminal4)) / (25));
            Score5 = ((Double.parseDouble(quiz5) + Double.parseDouble(assignment5) + Double.parseDouble(mids5) + Double.parseDouble(terminal5)) / (25));
            Score6 = ((Double.parseDouble(quiz5) + Double.parseDouble(assignment5) + Double.parseDouble(mids5) + Double.parseDouble(terminal5)) / (25));
            double CGPA = (Score1 + Score2 + Score3 + Score4 + Score5 + Score6) / 6;
            String formattedCGPA = String.format("%.2f", CGPA);
            Marks marks = new Marks(Registra);
            scgpa1.setText(Double.toString(Score1));
            scgpa2.setText(Double.toString(Score2));
            scgpa3.setText(Double.toString(Score3));
            scgpa4.setText(Double.toString(Score4));
            scgpa5.setText(Double.toString(Score5));
            scgpa6.setText(Double.toString(Score6));
            try {
                marks.update_scores(reg, Score1, Score2, Score3, Score4, Score5, Score6, CGPA);
            } catch (SQLException ex) {
                Logger.getLogger(UpdateMarks.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_rSButtonHover8ActionPerformed

    private void Mids1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mids1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Mids1ActionPerformed

    private void Mids2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mids2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Mids2ActionPerformed

    private void Mids5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mids5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Mids5ActionPerformed

    private void Mids4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mids4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Mids4ActionPerformed

    private void Mids3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mids3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Mids3ActionPerformed

    private void Mids6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mids6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Mids6ActionPerformed

    private void rSButtonHover10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSButtonHover10ActionPerformed

    private void rSButtonHover11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover11ActionPerformed
        // TODO add your handling code here:
        int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to Logout?", "Logout", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            // Dispose the current frame (window)
            this.dispose();
            // Open the login window
            new LoginasStudent().setVisible(true);
        }
    }//GEN-LAST:event_rSButtonHover11ActionPerformed

    private void Quiz2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Quiz2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Quiz2ActionPerformed

    private void scgpa3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scgpa3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_scgpa3ActionPerformed
private void Quiz1FocusLost(java.awt.event.FocusEvent evt) {                                
        // TODO add your handling code here:
        isInquizRange(Quiz1);
    }                               

    private void Quiz2FocusLost(java.awt.event.FocusEvent evt) {                                
        // TODO add your handling code here:
        isInquizRange(Quiz2);
    }                               

    private void Quiz3FocusLost(java.awt.event.FocusEvent evt) {                                
        // TODO add your handling code here:
        isInquizRange(Quiz3);
    }                               

    private void Quiz4FocusLost(java.awt.event.FocusEvent evt) {                                
        // TODO add your handling code here:
        isInquizRange(Quiz4);
    }                               

    private void Quiz5FocusLost(java.awt.event.FocusEvent evt) {                                
        // TODO add your handling code here:
        isInquizRange(Quiz5);
    }                               

    private void Quiz6FocusLost(java.awt.event.FocusEvent evt) {                                
        // TODO add your handling code here:
        isInquizRange(Quiz6);
    }                               

    private void Assignment1FocusLost(java.awt.event.FocusEvent evt) {                                      
        // TODO add your handling code here:
        isInassignmentRange(Assignment1);
    }                                     

    private void Assignment2FocusLost(java.awt.event.FocusEvent evt) {                                      
        // TODO add your handling code here:
        isInassignmentRange(Assignment2);
    }                                     

    private void Assignment3FocusLost(java.awt.event.FocusEvent evt) {                                      
        // TODO add your handling code here:
        isInassignmentRange(Assignment3);
    }                                     

    private void Assignment4FocusLost(java.awt.event.FocusEvent evt) {                                      
        // TODO add your handling code here:
        isInassignmentRange(Assignment4);
    }                                     

    private void Assignment5FocusLost(java.awt.event.FocusEvent evt) {                                      
        // TODO add your handling code here:
        isInassignmentRange(Assignment5);
    }                                     

    private void Assignment6FocusLost(java.awt.event.FocusEvent evt) {                                      
        // TODO add your handling code here:
        isInassignmentRange(Assignment6);
    }                                     

    private void Mids1FocusLost(java.awt.event.FocusEvent evt) {                                
        // TODO add your handling code here:
        isInmidsRange(Mids1);

    }                               

    private void Mids2FocusLost(java.awt.event.FocusEvent evt) {                                
        // TODO add your handling code here:
        isInmidsRange(Mids2);

    }                               

    private void Mids3FocusLost(java.awt.event.FocusEvent evt) {                                
        // TODO add your handling code here:
        isInmidsRange(Mids3);

    }                               

    private void Mids4FocusLost(java.awt.event.FocusEvent evt) {                                
        // TODO add your handling code here:
        isInmidsRange(Mids4);

    }                               

    private void Mids5FocusLost(java.awt.event.FocusEvent evt) {                                
        // TODO add your handling code here:
        isInmidsRange(Mids5);

    }                               

    private void Mids6FocusLost(java.awt.event.FocusEvent evt) {                                
        // TODO add your handling code here:
        isInmidsRange(Mids6);

    }                               

    private void Terminals1FocusLost(java.awt.event.FocusEvent evt) {                                     
        // TODO add your handling code here:
        isInterminalRange(Terminals1);

    }                                    

    private void Terminals2FocusLost(java.awt.event.FocusEvent evt) {                                     
        // TODO add your handling code here:
        isInterminalRange(Terminals2);

    }                                    

    private void Terminals3FocusLost(java.awt.event.FocusEvent evt) {                                     
        // TODO add your handling code here:
        isInterminalRange(Terminals3);

    }                                    

    private void Terminals4FocusLost(java.awt.event.FocusEvent evt) {                                     
        // TODO add your handling code here:
        isInterminalRange(Terminals4);

    }                                    

    private void Terminals5FocusLost(java.awt.event.FocusEvent evt) {                                     
        // TODO add your handling code here:
        isInterminalRange(Terminals5);

    }                                    

    private void Terminals6FocusLost(java.awt.event.FocusEvent evt) {                                     
        // TODO add your handling code here:
        isInterminalRange(Terminals6);

    }                                    

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        this.dispose();
        TeacherDashboard td = new TeacherDashboard(Registra);
        td.setVisible(true);
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
            java.util.logging.Logger.getLogger(Marks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Marks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Marks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Marks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Marks(Registra).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private app.bolivia.swing.JCTextField Assignment1;
    private app.bolivia.swing.JCTextField Assignment2;
    private app.bolivia.swing.JCTextField Assignment3;
    private app.bolivia.swing.JCTextField Assignment4;
    private app.bolivia.swing.JCTextField Assignment5;
    private app.bolivia.swing.JCTextField Assignment6;
    private app.bolivia.swing.JCTextField Course1;
    private app.bolivia.swing.JCTextField Course2;
    private app.bolivia.swing.JCTextField Course3;
    private app.bolivia.swing.JCTextField Course4;
    private app.bolivia.swing.JCTextField Course5;
    private app.bolivia.swing.JCTextField Course6;
    private app.bolivia.swing.JCTextField Mids1;
    private app.bolivia.swing.JCTextField Mids2;
    private app.bolivia.swing.JCTextField Mids3;
    private app.bolivia.swing.JCTextField Mids4;
    private app.bolivia.swing.JCTextField Mids5;
    private app.bolivia.swing.JCTextField Mids6;
    private app.bolivia.swing.JCTextField Quiz1;
    private app.bolivia.swing.JCTextField Quiz2;
    private app.bolivia.swing.JCTextField Quiz3;
    private app.bolivia.swing.JCTextField Quiz4;
    private app.bolivia.swing.JCTextField Quiz5;
    private app.bolivia.swing.JCTextField Quiz6;
    private app.bolivia.swing.JCTextField Registrationfind;
    private app.bolivia.swing.JCTextField Terminals1;
    private app.bolivia.swing.JCTextField Terminals2;
    private app.bolivia.swing.JCTextField Terminals3;
    private app.bolivia.swing.JCTextField Terminals4;
    private app.bolivia.swing.JCTextField Terminals5;
    private app.bolivia.swing.JCTextField Terminals6;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane10;
    private javax.swing.JLayeredPane jLayeredPane11;
    private javax.swing.JLayeredPane jLayeredPane16;
    private javax.swing.JLayeredPane jLayeredPane17;
    private javax.swing.JLayeredPane jLayeredPane18;
    private javax.swing.JLayeredPane jLayeredPane19;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane20;
    private javax.swing.JLayeredPane jLayeredPane21;
    private javax.swing.JLayeredPane jLayeredPane22;
    private javax.swing.JLayeredPane jLayeredPane23;
    private javax.swing.JLayeredPane jLayeredPane24;
    private javax.swing.JLayeredPane jLayeredPane4;
    private javax.swing.JLayeredPane jLayeredPane5;
    private javax.swing.JLayeredPane jLayeredPane6;
    private javax.swing.JLayeredPane jLayeredPane8;
    private javax.swing.JLayeredPane jLayeredPane9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lcourse1;
    private javax.swing.JLabel lcourse2;
    private javax.swing.JLabel lcourse3;
    private javax.swing.JLabel lcourse4;
    private javax.swing.JLabel lcourse5;
    private javax.swing.JLabel lcourse6;
    private rojeru_san.complementos.RSButtonHover rSButtonHover10;
    private rojeru_san.complementos.RSButtonHover rSButtonHover11;
    private rojeru_san.complementos.RSButtonHover rSButtonHover2;
    private rojeru_san.complementos.RSButtonHover rSButtonHover3;
    private rojeru_san.complementos.RSButtonHover rSButtonHover7;
    private rojeru_san.complementos.RSButtonHover rSButtonHover8;
    private app.bolivia.swing.JCTextField scgpa1;
    private app.bolivia.swing.JCTextField scgpa2;
    private app.bolivia.swing.JCTextField scgpa3;
    private app.bolivia.swing.JCTextField scgpa4;
    private app.bolivia.swing.JCTextField scgpa5;
    private app.bolivia.swing.JCTextField scgpa6;
    // End of variables declaration//GEN-END:variables
}
