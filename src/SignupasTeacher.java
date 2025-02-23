
import Database.MyConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author This PC
 */
public class SignupasTeacher extends javax.swing.JFrame {

    java.sql.Connection con = MyConnection.getConnection();
    PreparedStatement ps;

    public void insertTeacher(String Teacher_name, String TeacherID,
            String fatherName, String dob, String email,
            String gender, String field, String address, String phoneNo,
            String city, String salary, String Teacher_Password, String Confirm_Password) throws SQLException {

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = MyConnection.getConnection();
            String query = "INSERT INTO teachers (Teacher_name, TeacherID, Father_Name, DateOfBirth, Email, Gender, Field, Address, Phone, City, Salary, Teacher_Password,Confirm_Password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, Teacher_name);
            pstmt.setString(2, TeacherID);
            pstmt.setString(3, fatherName);
            pstmt.setString(4, dob);
            pstmt.setString(5, email);
            pstmt.setString(6, gender);
            pstmt.setString(7, field);
            pstmt.setString(8, address);
            pstmt.setString(9, phoneNo);
            pstmt.setString(10, city);
            pstmt.setString(11, salary);
            pstmt.setString(12, Teacher_Password);
            pstmt.setString(13, Confirm_Password);
            JOptionPane.showMessageDialog(this, "Signup Successful");
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

    private boolean IsEmptyTeacher() {
        if (t_Namefield.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Teacher Name is missing Please Enter the Name");
            return false;
        }
        if (Teacherid.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Teacher Registration No is missing Please Enter the Registration No");
            return false;
        }
        if (t_Field.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Teacher Department is missing Please Enter the Department");
            return false;
        }
        if (t_Salary.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Teacher Salary is not selected");
            return false;
        }
        if (t_email.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Teacher Email is missing Please Enter the Email");
            return false;
        }
        if (t_email.getText().matches("!#$%^&*.+..")) {
            JOptionPane.showMessageDialog(this, "Enter Vald Email");
            return false;
        }
        if (t_Phone.getText().matches("!#$%^&*")) {
            JOptionPane.showMessageDialog(this, "Teacher Phone Number is missing Please Enter the Phone Number");
            return false;
        }
        if (t_Phone.getText().length() > 15) {
            JOptionPane.showMessageDialog(this, "Teacher Phone Number is too long Please Enter the Phone Number again");
            return false;
        }

        if (t_gender.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Teacher gender is not selected");
            return false;
        }
        return true;
    }

    private void ClearTeachers() {
        t_Namefield.setText(null);
        Teacherid.setText(null);
        t_fathername.setText(null);
        t_email.setText(null);
        t_dob.setDate(null);
        t_gender.setSelectedItem(0);
        t_Field.setText(null);
        t_Salary.setSelectedItem(0);
        t_Address.setText(null);
        t_Phone.setText(null);
        t_City.setText(null);
        t_Password.setText(null);
        t_confirm_password.setText(null);
        captchafield.setText(null);
    }

    public boolean TeacherIDNoExist(String TeacherID) {
        try {
            ps = con.prepareStatement("Select * from teachers where TeacherID = ?");
            ps.setString(1, TeacherID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            Logger.getLogger(SignupasStudent.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    public boolean T_EmailExists(String TeacherID) {
        try {
            ps = con.prepareStatement("Select * from teachers where TeacherID = ?");
            ps.setString(1, TeacherID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            Logger.getLogger(SignupasStudent.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    public boolean T_PhoneExists(String phone_number) {
        try {
            ps = con.prepareStatement("Select * from teachers where Email = ?");
            ps.setString(1, phone_number);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            Logger.getLogger(SignupasStudent.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }
public static boolean isValidPakistaniPhone(String phone_number) {
        String pattern = "^(0[1-9]|[1-9][0-9])([ -]?\\d{3}){3}$";
        return phone_number.matches(pattern);
    }
public static boolean isValidEmail(String email) {
        String pattern = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,3}$";
        return Pattern.matches(pattern, email);
    }
    /**
     * Creates new form Home
     */
    public SignupasTeacher() {
        initComponents();
        Random rand = new Random();

        int number = rand.nextInt(45645672) + 123456;
        no = number;
        captcha.setText(String.valueOf(number));
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
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel12 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        t_City = new app.bolivia.swing.JCTextField();
        t_Salary = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        t_gender = new javax.swing.JComboBox<>();
        t_email = new app.bolivia.swing.JCTextField();
        t_fathername = new app.bolivia.swing.JCTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Teacherid = new app.bolivia.swing.JCTextField();
        t_Phone = new app.bolivia.swing.JCTextField();
        jLabel13 = new javax.swing.JLabel();
        t_Address = new app.bolivia.swing.JCTextField();
        t_Namefield = new app.bolivia.swing.JCTextField();
        t_Field = new app.bolivia.swing.JCTextField();
        t_dob = new com.toedter.calendar.JDateChooser();
        jLabel14 = new javax.swing.JLabel();
        t_Password = new app.bolivia.swing.JCTextField();
        jLabel15 = new javax.swing.JLabel();
        t_confirm_password = new app.bolivia.swing.JCTextField();
        captcha = new javax.swing.JLabel();
        captchafield = new app.bolivia.swing.JCTextField();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        rSButtonHover3 = new rojeru_san.complementos.RSButtonHover();
        rSButtonHover1 = new rojeru_san.complementos.RSButtonHover();
        rSButtonHover2 = new rojeru_san.complementos.RSButtonHover();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(30, 42, 72));

        jLabel3.setBackground(new java.awt.Color(30, 42, 72));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/1481 (1).gif"))); // NOI18N
        jLabel3.setToolTipText("");
        jLabel3.setAutoscrolls(true);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Signup as a Teacher");

        jLayeredPane1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Salary");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Field");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Email");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Gender");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Name");

        t_City.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        t_City.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_CityActionPerformed(evt);
            }
        });

        t_Salary.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "25,000+", "50,000+", "80,000+", "125,000+" }));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Address");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Phone No");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Father Name");

        t_gender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Male", "Female" }));

        t_email.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        t_email.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                t_emailFocusLost(evt);
            }
        });

        t_fathername.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("City");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Date of Birth");

        Teacherid.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Teacherid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                TeacheridFocusLost(evt);
            }
        });

        t_Phone.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        t_Phone.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                t_PhoneFocusLost(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Teacher Id");

        t_Address.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        t_Namefield.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        t_Field.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Password");

        t_Password.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Confirm Password");

        t_confirm_password.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        captcha.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        captcha.setForeground(new java.awt.Color(255, 255, 255));
        captcha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        captchafield.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        captchafield.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                captchafieldFocusLost(evt);
            }
        });

        jLayeredPane1.setLayer(jLabel12, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(t_City, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(t_Salary, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(t_gender, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(t_email, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(t_fathername, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel11, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(Teacherid, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(t_Phone, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel13, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(t_Address, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(t_Namefield, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(t_Field, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(t_dob, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel14, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(t_Password, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel15, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(t_confirm_password, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(captcha, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(captchafield, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                .addComponent(jLabel6)
                                .addComponent(jLabel8)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10))
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(jLabel1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                                        .addComponent(t_Namefield, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(569, 569, 569))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jLayeredPane1Layout.createSequentialGroup()
                                                .addComponent(t_fathername, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(t_email, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(Teacherid, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(96, 96, 96))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                        .addComponent(t_Phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(t_City, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(t_Salary, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(t_Address, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                                                .addComponent(t_dob, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(346, 346, 346))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel9)
                                                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                                        .addComponent(t_gender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(30, 30, 30)
                                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(t_Field, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(96, 96, 96))))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(t_Password, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel15)
                        .addGap(30, 30, 30)
                        .addComponent(t_confirm_password, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(captcha, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(captchafield, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(96, 96, 96)))
                .addContainerGap())
        );

        jLayeredPane1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {t_dob, t_fathername});

        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel1)
                            .addComponent(t_Namefield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(Teacherid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(t_fathername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(t_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)))
                .addGap(30, 30, 30)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(t_dob, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(t_gender, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(t_Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(30, 30, 30)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(t_Address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(t_City, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(t_Salary, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t_Phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(30, 30, 30)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel14)
                    .addComponent(t_Password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t_confirm_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(captchafield, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(captcha, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
        );

        jLayeredPane2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));

        rSButtonHover3.setBackground(new java.awt.Color(255, 51, 51));
        rSButtonHover3.setText("Login as Teacher");
        rSButtonHover3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        rSButtonHover3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonHover3MouseClicked(evt);
            }
        });
        rSButtonHover3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover3ActionPerformed(evt);
            }
        });

        rSButtonHover1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sign-up.png"))); // NOI18N
        rSButtonHover1.setText("Signup");
        rSButtonHover1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        rSButtonHover1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover1ActionPerformed(evt);
            }
        });

        rSButtonHover2.setBackground(new java.awt.Color(255, 51, 51));
        rSButtonHover2.setText("Signup as Student");
        rSButtonHover2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        rSButtonHover2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonHover2MouseClicked(evt);
            }
        });
        rSButtonHover2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover2ActionPerformed(evt);
            }
        });

        jLayeredPane2.setLayer(rSButtonHover3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(rSButtonHover1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(rSButtonHover2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rSButtonHover3, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(rSButtonHover1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(rSButtonHover2, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(rSButtonHover2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonHover3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonHover1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(232, 232, 232)
                        .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void rSButtonHover2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSButtonHover2ActionPerformed

    private void rSButtonHover3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSButtonHover3ActionPerformed

    private void t_CityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_CityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_CityActionPerformed

    private void rSButtonHover2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonHover2MouseClicked
        // TODO add your handling code here:
        this.dispose();
        new SignupasStudent().setVisible(true);
    }//GEN-LAST:event_rSButtonHover2MouseClicked

    private void rSButtonHover3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonHover3MouseClicked
        // TODO add your handling code here:
        this.dispose();
        new LoginasTeacher().setVisible(true);
    }//GEN-LAST:event_rSButtonHover3MouseClicked
    public int no;
    private void rSButtonHover1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover1ActionPerformed
        // TODO add your handling code here:
        // TODO Add your handling code here:
        if (IsEmptyTeacher()) {
            SignupasTeacher signupasTeacher = new SignupasTeacher();
            if (!signupasTeacher.TeacherIDNoExist(Teacherid.getText())) {
                if (!signupasTeacher.T_PhoneExists(t_Phone.getText())) {
                    if (!signupasTeacher.T_EmailExists(t_email.getText())) {
                        String Teacher_name = t_Namefield.getText();
                        String TeacherID_No = Teacherid.getText();
                        String fatherName = t_fathername.getText();
                        String email = t_email.getText();  // Change variable name here
                        Date dobDate = t_dob.getDate();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String dob = dateFormat.format(dobDate);
                        String gender = t_gender.getSelectedItem().toString();
                        String field = t_Field.getText(); // Assuming department field is used for Teacher's field
                        String address = t_Address.getText();
                        String phoneNo = t_Phone.getText();
                        String city = t_City.getText();
                        String salary = t_Salary.getSelectedItem().toString(); // Assuming there's a salaryField
                        String Password = t_Password.getText();
                        String ConfirmPassword = t_Password.getText();

                        if (Password.equals(ConfirmPassword)) {
                            String captchaFieldText = captchafield.getText();
                            if (String.valueOf(no).equalsIgnoreCase(captchaFieldText)) {
                                try {
                                    signupasTeacher.insertTeacher(Teacher_name, TeacherID_No, fatherName, dob, email, gender, field, address, phoneNo, city, salary, Password, ConfirmPassword);
                                    
                                } catch (SQLException ex) {
                                    Logger.getLogger(SignupasTeacher.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                ClearTeachers();
                                Random rand = new Random();
                                int number = rand.nextInt(45645672) + 123456;
                                no = number;
                                captcha.setText(String.valueOf(number));
                                ClearTeachers();
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Email Number already exists");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Phone Number already exists");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Registration No already exists");
                }
            }

        }
    }//GEN-LAST:event_rSButtonHover1ActionPerformed

    private void captchafieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_captchafieldFocusLost
        // TODO add your handling code here:

        String captchaFieldText = captchafield.getText();

        if (String.valueOf(no).equalsIgnoreCase(captchaFieldText)) {

        } else {
            Random rand = new Random();
            int number = rand.nextInt(45645672) + 123456;
            JOptionPane.showMessageDialog(this, "Captcha doesn't matched Please try again");
            no = number;
            captcha.setText(String.valueOf(number));
        }
    }//GEN-LAST:event_captchafieldFocusLost

    private void t_PhoneFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_PhoneFocusLost
 String phone = t_Phone.getText();
        if(!T_PhoneExists(phone)){
            if (isValidPakistaniPhone(phone)) {

        } else {
            JOptionPane.showMessageDialog(this, "Not a valid Phone No");
            t_Phone.setText(null);

        }
        }
        else{
            JOptionPane.showMessageDialog(this, "Phone Number No already exists!!!");
            t_Phone.setText(null);
        }
    }//GEN-LAST:event_t_PhoneFocusLost

    private void t_emailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_emailFocusLost
        // TODO add your handling code here:
         String Email = t_email.getText();
        if(!T_EmailExists(Email)){
            if (isValidEmail(Email)) {

        } else {
            JOptionPane.showMessageDialog(this, "Not a valid Email");
            t_email.setText(null);

        }
        }
        else{
            JOptionPane.showMessageDialog(this, "Email No already exists!!!");
            t_email.setText(null);
        }
    }//GEN-LAST:event_t_emailFocusLost

    private void TeacheridFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TeacheridFocusLost
        // TODO add your handling code here:
        String reg=Teacherid.getText();
        if(!TeacherIDNoExist(reg)){
            
        }
        else{
            JOptionPane.showMessageDialog(this, "Teacher Id No already exists!!!");
            Teacherid.setText(null);
        }
    }//GEN-LAST:event_TeacheridFocusLost

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private app.bolivia.swing.JCTextField Teacherid;
    public javax.swing.JLabel captcha;
    public app.bolivia.swing.JCTextField captchafield;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JPanel jPanel1;
    private rojeru_san.complementos.RSButtonHover rSButtonHover1;
    private rojeru_san.complementos.RSButtonHover rSButtonHover2;
    private rojeru_san.complementos.RSButtonHover rSButtonHover3;
    private app.bolivia.swing.JCTextField t_Address;
    private app.bolivia.swing.JCTextField t_City;
    private app.bolivia.swing.JCTextField t_Field;
    private app.bolivia.swing.JCTextField t_Namefield;
    private app.bolivia.swing.JCTextField t_Password;
    private app.bolivia.swing.JCTextField t_Phone;
    private javax.swing.JComboBox<String> t_Salary;
    private app.bolivia.swing.JCTextField t_confirm_password;
    private com.toedter.calendar.JDateChooser t_dob;
    private app.bolivia.swing.JCTextField t_email;
    private app.bolivia.swing.JCTextField t_fathername;
    private javax.swing.JComboBox<String> t_gender;
    // End of variables declaration//GEN-END:variables
}
