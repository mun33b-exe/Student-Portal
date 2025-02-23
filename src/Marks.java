
import java.sql.SQLIntegrityConstraintViolationException;
import Database.MyConnection;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author This PC
 */
public class Marks extends javax.swing.JFrame {

    Connection con = MyConnection.getConnection();
    PreparedStatement ps;

    public void setCoursesToTextFields(String Registration_No, JTextField jTextField3, JTextField jTextField7, JTextField jTextField4, JTextField jTextField17, JTextField jTextField5, JTextField jTextField9) {
        String sql = "SELECT course1, course2, course3, course4, course5,course6 FROM courses WHERE Registration_No = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, Registration_No);

            try (var rs = ps.executeQuery()) {
                if (rs.next()) {
                    jTextField3.setText(rs.getString("course1"));
                    jTextField7.setText(rs.getString("course2"));
                    jTextField4.setText(rs.getString("course3"));
                    jTextField17.setText(rs.getString("course4"));
                    jTextField5.setText(rs.getString("course5"));
                    jTextField9.setText(rs.getString("course6"));
                } else {
                    JOptionPane.showMessageDialog(null, "The Registration No didn't exists, Enter the Record first");
                    jTextField3.setText("");
                    jTextField7.setText("");
                    jTextField4.setText("");
                    jTextField17.setText("");
                    jTextField5.setText("");
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(Courses.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void insertMarks(String Registration_No, String course1, String quiz1, String assignment1, String mids1, String terminals1,
            String course2, String quiz2, String assignment2, String mids2, String terminals2,
            String course3, String quiz3, String assignment3, String mids3, String terminals3,
            String course4, String quiz4, String assignment4, String mids4, String terminals4,
            String course5, String quiz5, String assignment5, String mids5, String terminals5,
            String course6, String quiz6, String assignment6, String mids6, String terminals6) {

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = MyConnection.getConnection();

            // Check if the Registration_No already exists
            if (isRegistrationNoExists(conn, Registration_No)) {
                JOptionPane.showMessageDialog(null, "Registration number already exists.");
                this.dispose();
                new TeacherDashboard(registra).setVisible(true);

                return;
            }

            // Insert marks
            String query = "INSERT INTO studentmarks (Registration_No, Course1, Quiz1, Assignment1, Mids1, Terminals1, "
                    + "Course2, Quiz2, Assignment2, Mids2, Terminals2, "
                    + "Course3, Quiz3, Assignment3, Mids3, Terminals3, "
                    + "Course4, Quiz4, Assignment4, Mids4, Terminals4, "
                    + "Course5, Quiz5, Assignment5, Mids5, Terminals5, "
                    + "Course6, Quiz6, Assignment6, Mids6, Terminals6) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, Registration_No);
            pstmt.setString(2, course1);
            pstmt.setString(3, quiz1);
            pstmt.setString(4, assignment1);
            pstmt.setString(5, mids1);
            pstmt.setString(6, terminals1);
            pstmt.setString(7, course2);
            pstmt.setString(8, quiz2);
            pstmt.setString(9, assignment2);
            pstmt.setString(10, mids2);
            pstmt.setString(11, terminals2);
            pstmt.setString(12, course3);
            pstmt.setString(13, quiz3);
            pstmt.setString(14, assignment3);
            pstmt.setString(15, mids3);
            pstmt.setString(16, terminals3);
            pstmt.setString(17, course4);
            pstmt.setString(18, quiz4);
            pstmt.setString(19, assignment4);
            pstmt.setString(20, mids4);
            pstmt.setString(21, terminals4);
            pstmt.setString(22, course5);
            pstmt.setString(23, quiz5);
            pstmt.setString(24, assignment5);
            pstmt.setString(25, mids5);
            pstmt.setString(26, terminals5);
            pstmt.setString(27, course6);
            pstmt.setString(28, quiz6);
            pstmt.setString(29, assignment6);
            pstmt.setString(30, mids6);
            pstmt.setString(31, terminals6);

            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Marks inserted successfully.");

        } catch (SQLException e) {
            Logger.getLogger(Courses.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            // Close resources in finally block to ensure they are always closed
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    Logger.getLogger(Courses.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(Courses.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    // Method to check if the registration number already exists
    private boolean isRegistrationNoExists(Connection conn, String Registration_No) throws SQLException {
        String query = "SELECT COUNT(*) FROM studentmarks WHERE Registration_No = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, Registration_No);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0;
                }
            }
        }
        return false;
    }

    public void getmarksValue(JTable table, String searchValue) {
        String sql = "select * from Scores where concat(Registration_No, Score1, Score2, Score3,Score4,Score5,Score6) like ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + searchValue + "%");
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()) {
                row = new Object[6];
                row[0] = rs.getString(1);
                row[1] = rs.getDouble(2);
                row[2] = rs.getDouble(3);
                row[3] = rs.getDouble(4);
                row[4] = rs.getDouble(5);
                row[5] = rs.getDouble(6);
                row[5] = rs.getDouble(7);

                model.addRow(row);
            }
        } catch (SQLException e) {
            Logger.getLogger(Marks.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    /**
     * Creates new form Marks
     */
    static String registra;

    public Marks(String Registration) {
        initComponents();
        registra = Registration;
    }

    public void insert_scores(String Registration_No, double score1, double score2, double score3, double score4, double score5, double score6, double cgpa) {
        String sql = "INSERT INTO scores (Registration_No, Score1, Score2, Score3, Score4, Score5, Score6, CGPA) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, Registration_No);
            ps.setDouble(2, score1);
            ps.setDouble(3, score2);
            ps.setDouble(4, score3);
            ps.setDouble(5, score4);
            ps.setDouble(6, score5);
            ps.setDouble(7, score6);
            ps.setDouble(8, cgpa);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Scores Data Added Successfully");
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            // Handle duplicate entry error
            JOptionPane.showMessageDialog(null, "Duplicate entry for Registration No: " + Registration_No);
        } catch (SQLException e) {
            // Log any other SQL exceptions
            Logger.getLogger(Marks.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "An error occurred while adding scores. Please try again.");
        }
    }

    public void update_scores(String Registration_No, double score1, double score2, double score3, double score4, double score5, double score6, double cgpa) throws SQLException {

        String sql = "UPDATE scores SET Score1 = ?, Score2 = ?, Score3 = ?, Score4 = ?, Score5 = ?, Score6 = ?, CGPA = ? WHERE Registration_No = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, score1);
            ps.setDouble(2, score2);
            ps.setDouble(3, score3);
            ps.setDouble(4, score4);
            ps.setDouble(5, score5);
            ps.setDouble(6, score6);
            ps.setDouble(7, cgpa);
            ps.setString(8, Registration_No); // Registration No for WHERE clause

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Scores Data Also Updated Successfully");
            } else {
                // Handle no rows affected (e.g., Registration No not found)
                JOptionPane.showMessageDialog(null, "No records found for Registration No: " + Registration_No);
            }
        } catch (SQLException e) {
            // Log any other SQL exceptions
            Logger.getLogger(Marks.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "An error occurred while updating scores. Please try again.");
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
        rSButtonHover9 = new rojeru_san.complementos.RSButtonHover();
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
        jLayeredPane22 = new javax.swing.JLayeredPane();
        lcourse1 = new javax.swing.JLabel();
        lcourse4 = new javax.swing.JLabel();
        lcourse3 = new javax.swing.JLabel();
        lcourse2 = new javax.swing.JLabel();
        scgpa2 = new app.bolivia.swing.JCTextField();
        scgpa3 = new app.bolivia.swing.JCTextField();
        scgpa4 = new app.bolivia.swing.JCTextField();
        lcourse5 = new javax.swing.JLabel();
        scgpa5 = new app.bolivia.swing.JCTextField();
        lcourse6 = new javax.swing.JLabel();
        scgpa6 = new app.bolivia.swing.JCTextField();
        scgpa1 = new app.bolivia.swing.JCTextField();
        jLayeredPane23 = new javax.swing.JLayeredPane();
        jLabel3 = new javax.swing.JLabel();
        cgpa = new app.bolivia.swing.JCTextField();
        jLayeredPane24 = new javax.swing.JLayeredPane();
        rSButtonHover8 = new rojeru_san.complementos.RSButtonHover();
        rSButtonHover10 = new rojeru_san.complementos.RSButtonHover();
        rSButtonHover11 = new rojeru_san.complementos.RSButtonHover();

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
        jLabel5.setText("Marks");

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

        rSButtonHover9.setText("Seach");
        rSButtonHover9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        rSButtonHover9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover9ActionPerformed(evt);
            }
        });

        jLayeredPane4.setLayer(rSButtonHover7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(Registrationfind, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(rSButtonHover9, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane4Layout = new javax.swing.GroupLayout(jLayeredPane4);
        jLayeredPane4.setLayout(jLayeredPane4Layout);
        jLayeredPane4Layout.setHorizontalGroup(
            jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Registrationfind, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rSButtonHover9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                    .addComponent(rSButtonHover7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonHover9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        Quiz3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                Quiz3FocusLost(evt);
            }
        });

        Assignment3.setText("0.0");
        Assignment3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                Assignment3FocusLost(evt);
            }
        });

        Mids3.setText("0.0");
        Mids3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                Mids3FocusLost(evt);
            }
        });

        Terminals3.setText("0.0");
        Terminals3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                Terminals3FocusLost(evt);
            }
        });

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
        Quiz5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                Quiz5FocusLost(evt);
            }
        });

        Assignment5.setText("0.0");
        Assignment5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                Assignment5FocusLost(evt);
            }
        });

        Mids5.setText("0.0");
        Mids5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                Mids5FocusLost(evt);
            }
        });

        Terminals5.setText("0.0");
        Terminals5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                Terminals5FocusLost(evt);
            }
        });

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
        Quiz1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                Quiz1FocusLost(evt);
            }
        });

        Assignment1.setText("0.0");
        Assignment1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                Assignment1FocusLost(evt);
            }
        });

        Mids1.setText("0.0");
        Mids1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                Mids1FocusLost(evt);
            }
        });

        Terminals1.setText("0.0");
        Terminals1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                Terminals1FocusLost(evt);
            }
        });

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
        Quiz6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                Quiz6FocusLost(evt);
            }
        });

        Assignment6.setText("0.0");
        Assignment6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                Assignment6FocusLost(evt);
            }
        });

        Mids6.setText("0.0");
        Mids6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                Mids6FocusLost(evt);
            }
        });

        Terminals6.setText("0.0");
        Terminals6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                Terminals6FocusLost(evt);
            }
        });

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
        Quiz4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                Quiz4FocusLost(evt);
            }
        });

        Assignment4.setText("0.0");
        Assignment4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                Assignment4FocusLost(evt);
            }
        });

        Mids4.setText("0.0");
        Mids4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                Mids4FocusLost(evt);
            }
        });

        Terminals4.setText("0.0");
        Terminals4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                Terminals4FocusLost(evt);
            }
        });

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
        Quiz2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                Quiz2FocusLost(evt);
            }
        });
        Quiz2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Quiz2ActionPerformed(evt);
            }
        });

        Assignment2.setText("0.0");
        Assignment2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                Assignment2FocusLost(evt);
            }
        });

        Mids2.setText("0.0");
        Mids2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                Mids2FocusLost(evt);
            }
        });

        Terminals2.setText("0.0");
        Terminals2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                Terminals2FocusLost(evt);
            }
        });

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

        scgpa1.setText("0.0");

        jLayeredPane22.setLayer(lcourse1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane22.setLayer(lcourse4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane22.setLayer(lcourse3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane22.setLayer(lcourse2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane22.setLayer(scgpa2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane22.setLayer(scgpa3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane22.setLayer(scgpa4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane22.setLayer(lcourse5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane22.setLayer(scgpa5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane22.setLayer(lcourse6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane22.setLayer(scgpa6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane22.setLayer(scgpa1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane22Layout = new javax.swing.GroupLayout(jLayeredPane22);
        jLayeredPane22.setLayout(jLayeredPane22Layout);
        jLayeredPane22Layout.setHorizontalGroup(
            jLayeredPane22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane22Layout.createSequentialGroup()
                        .addGroup(jLayeredPane22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lcourse1, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                            .addComponent(lcourse2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lcourse3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lcourse4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lcourse5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jLayeredPane22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jLayeredPane22Layout.createSequentialGroup()
                                    .addGap(26, 26, 26)
                                    .addComponent(scgpa2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(scgpa3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane22Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jLayeredPane22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(scgpa4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(scgpa5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(scgpa1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jLayeredPane22Layout.createSequentialGroup()
                        .addComponent(lcourse6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scgpa6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jLayeredPane23.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("CGPA");

        cgpa.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cgpa.setText("0.0");
        cgpa.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N

        jLayeredPane23.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane23.setLayer(cgpa, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane23Layout = new javax.swing.GroupLayout(jLayeredPane23);
        jLayeredPane23.setLayout(jLayeredPane23Layout);
        jLayeredPane23Layout.setHorizontalGroup(
            jLayeredPane23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cgpa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        jLayeredPane23Layout.setVerticalGroup(
            jLayeredPane23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jLayeredPane23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cgpa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLayeredPane24.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        rSButtonHover8.setText("Save");
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
                .addContainerGap(44, Short.MAX_VALUE))
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
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLayeredPane23)
                    .addComponent(jLayeredPane22)
                    .addComponent(jLayeredPane24))
                .addGap(0, 60, Short.MAX_VALUE))
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLayeredPane23)
                    .addComponent(jLayeredPane4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLayeredPane22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLayeredPane24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(1304, 1304, 1304)
                .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rSButtonHover7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover7ActionPerformed
        // TODO add your handling code here:
        String reg = Registrationfind.getText();
        setCoursesToTextFields(reg, Course1, Course2, Course3, Course4, Course5, Course6);
    }//GEN-LAST:event_rSButtonHover7ActionPerformed

    private void rSButtonHover8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover8ActionPerformed
        // TODO add your handling code here:
        // Handling code
        if (Registrationfind.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Enter the Registration No and fetch data first");
        } else {
            String reg = Registrationfind.getText();
            // Check if any of the text fields are empty or null
            if (reg.isEmpty() || Course1.getText().isEmpty() || Quiz1.getText().isEmpty() || Assignment1.getText().isEmpty() || Mids1.getText().isEmpty() || Terminals1.getText().isEmpty()
                    || Course2.getText().isEmpty() || Quiz2.getText().isEmpty() || Assignment2.getText().isEmpty() || Mids2.getText().isEmpty() || Terminals2.getText().isEmpty()
                    || Course3.getText().isEmpty() || Quiz3.getText().isEmpty() || Assignment3.getText().isEmpty() || Mids3.getText().isEmpty() || Terminals3.getText().isEmpty()
                    || Course4.getText().isEmpty() || Quiz4.getText().isEmpty() || Assignment4.getText().isEmpty() || Mids4.getText().isEmpty() || Terminals4.getText().isEmpty()
                    || Course5.getText().isEmpty() || Quiz5.getText().isEmpty() || Assignment5.getText().isEmpty() || Mids5.getText().isEmpty() || Terminals5.getText().isEmpty()
                    || Course6.getText().isEmpty() || Quiz6.getText().isEmpty() || Assignment6.getText().isEmpty() || Mids6.getText().isEmpty() || Terminals6.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "All fields must be filled");
                return; // Stop execution if any field is empty
            } else {

                // Get values from text fields
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
                    // Insert marks into the database
                    insertMarks(reg, course1, quiz1, assignment1, mids1, terminal1, course2, quiz2, assignment2, mids2, terminal2, course3, quiz3, assignment3, mids3, terminal3, course4, quiz4, assignment4, mids4, terminal4, course5, quiz5, assignment5, mids5, terminal5, course6, quiz6, assignment6, mids6, terminal6);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // Calculate scores
                double Score1 = ((Double.parseDouble(quiz1) + Double.parseDouble(assignment1) + Double.parseDouble(mids1) + Double.parseDouble(terminal1)) / 25);
                double Score2 = ((Double.parseDouble(quiz2) + Double.parseDouble(assignment2) + Double.parseDouble(mids2) + Double.parseDouble(terminal2)) / 25);
                double Score3 = ((Double.parseDouble(quiz3) + Double.parseDouble(assignment3) + Double.parseDouble(mids3) + Double.parseDouble(terminal3)) / 25);
                double Score4 = ((Double.parseDouble(quiz4) + Double.parseDouble(assignment4) + Double.parseDouble(mids4) + Double.parseDouble(terminal4)) / 25);
                double Score5 = ((Double.parseDouble(quiz5) + Double.parseDouble(assignment5) + Double.parseDouble(mids5) + Double.parseDouble(terminal5)) / 25);
                double Score6 = ((Double.parseDouble(quiz6) + Double.parseDouble(assignment6) + Double.parseDouble(mids6) + Double.parseDouble(terminal6)) / 25);
                double CGPA = (Score1 + Score2 + Score3 + Score4 + Score5 + Score6) / 6;
                String formattedCGPA = String.format("%.2f", CGPA);

                // Display scores
                scgpa1.setText(Double.toString(Score1));
                scgpa2.setText(Double.toString(Score2));
                scgpa3.setText(Double.toString(Score3));
                scgpa4.setText(Double.toString(Score4));
                scgpa5.setText(Double.toString(Score5));
                scgpa6.setText(Double.toString(Score6));
                cgpa.setText(formattedCGPA);
                // Insert scores into the database
                Marks marks = new Marks(registra);
                insert_scores(reg, Score1, Score2, Score3, Score4, Score5, Score6, CGPA);

            }
        }
    }//GEN-LAST:event_rSButtonHover8ActionPerformed

    private void scgpa3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scgpa3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_scgpa3ActionPerformed

    private void rSButtonHover9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSButtonHover9ActionPerformed

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

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        this.dispose();
        TeacherDashboard td = new TeacherDashboard(registra);
        td.setVisible(true);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void Quiz1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Quiz1FocusLost
        // TODO add your handling code here:
        isInquizRange(Quiz1);
    }//GEN-LAST:event_Quiz1FocusLost

    private void Quiz2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Quiz2FocusLost
        // TODO add your handling code here:
        isInquizRange(Quiz2);
    }//GEN-LAST:event_Quiz2FocusLost

    private void Quiz3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Quiz3FocusLost
        // TODO add your handling code here:
        isInquizRange(Quiz3);
    }//GEN-LAST:event_Quiz3FocusLost

    private void Quiz4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Quiz4FocusLost
        // TODO add your handling code here:
        isInquizRange(Quiz4);
    }//GEN-LAST:event_Quiz4FocusLost

    private void Quiz5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Quiz5FocusLost
        // TODO add your handling code here:
        isInquizRange(Quiz5);
    }//GEN-LAST:event_Quiz5FocusLost

    private void Quiz6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Quiz6FocusLost
        // TODO add your handling code here:
        isInquizRange(Quiz6);
    }//GEN-LAST:event_Quiz6FocusLost

    private void Assignment1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Assignment1FocusLost
        // TODO add your handling code here:
        isInassignmentRange(Assignment1);
    }//GEN-LAST:event_Assignment1FocusLost

    private void Assignment2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Assignment2FocusLost
        // TODO add your handling code here:
        isInassignmentRange(Assignment2);
    }//GEN-LAST:event_Assignment2FocusLost

    private void Assignment3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Assignment3FocusLost
        // TODO add your handling code here:
        isInassignmentRange(Assignment3);
    }//GEN-LAST:event_Assignment3FocusLost

    private void Assignment4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Assignment4FocusLost
        // TODO add your handling code here:
        isInassignmentRange(Assignment4);
    }//GEN-LAST:event_Assignment4FocusLost

    private void Assignment5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Assignment5FocusLost
        // TODO add your handling code here:
        isInassignmentRange(Assignment5);
    }//GEN-LAST:event_Assignment5FocusLost

    private void Assignment6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Assignment6FocusLost
        // TODO add your handling code here:
        isInassignmentRange(Assignment6);
    }//GEN-LAST:event_Assignment6FocusLost

    private void Mids1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Mids1FocusLost
        // TODO add your handling code here:
        isInmidsRange(Mids1);

    }//GEN-LAST:event_Mids1FocusLost

    private void Mids2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Mids2FocusLost
        // TODO add your handling code here:
        isInmidsRange(Mids2);

    }//GEN-LAST:event_Mids2FocusLost

    private void Mids3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Mids3FocusLost
        // TODO add your handling code here:
        isInmidsRange(Mids3);

    }//GEN-LAST:event_Mids3FocusLost

    private void Mids4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Mids4FocusLost
        // TODO add your handling code here:
        isInmidsRange(Mids4);

    }//GEN-LAST:event_Mids4FocusLost

    private void Mids5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Mids5FocusLost
        // TODO add your handling code here:
        isInmidsRange(Mids5);

    }//GEN-LAST:event_Mids5FocusLost

    private void Mids6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Mids6FocusLost
        // TODO add your handling code here:
        isInmidsRange(Mids6);

    }//GEN-LAST:event_Mids6FocusLost

    private void Terminals1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Terminals1FocusLost
        // TODO add your handling code here:
        isInterminalRange(Terminals1);

    }//GEN-LAST:event_Terminals1FocusLost

    private void Terminals2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Terminals2FocusLost
        // TODO add your handling code here:
        isInterminalRange(Terminals2);

    }//GEN-LAST:event_Terminals2FocusLost

    private void Terminals3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Terminals3FocusLost
        // TODO add your handling code here:
        isInterminalRange(Terminals3);

    }//GEN-LAST:event_Terminals3FocusLost

    private void Terminals4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Terminals4FocusLost
        // TODO add your handling code here:
        isInterminalRange(Terminals4);

    }//GEN-LAST:event_Terminals4FocusLost

    private void Terminals5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Terminals5FocusLost
        // TODO add your handling code here:
        isInterminalRange(Terminals5);

    }//GEN-LAST:event_Terminals5FocusLost

    private void Terminals6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Terminals6FocusLost
        // TODO add your handling code here:
        isInterminalRange(Terminals6);

    }//GEN-LAST:event_Terminals6FocusLost

    /**
     * @param args the command line arguments
     */

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
    private app.bolivia.swing.JCTextField cgpa;
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
    private rojeru_san.complementos.RSButtonHover rSButtonHover9;
    private app.bolivia.swing.JCTextField scgpa1;
    private app.bolivia.swing.JCTextField scgpa2;
    private app.bolivia.swing.JCTextField scgpa3;
    private app.bolivia.swing.JCTextField scgpa4;
    private app.bolivia.swing.JCTextField scgpa5;
    private app.bolivia.swing.JCTextField scgpa6;
    // End of variables declaration//GEN-END:variables
}
