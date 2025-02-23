
import Database.MyConnection;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Random;
import java.util.regex.Pattern;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author This PC
 */
public class SignupasStudent extends javax.swing.JFrame {

    Connection con = MyConnection.getConnection();
    PreparedStatement ps;

    public void insertatsignup(String name, String registrationNo, String fatherName, String dob, String email,
            String gender, String department, String address, String phoneNo,
            String city, String section, String Student_Password, String Confirm_Password) {

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = MyConnection.getConnection();
            String query = "INSERT INTO students (Name, Registration_No, Father_Name,  DateOfBirth,Email, Gender, Department, Address, Phone, City, Section,Student_Password,Confirm_Password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, registrationNo);
            pstmt.setString(3, fatherName);
            pstmt.setString(4, dob);
            pstmt.setString(5, email);
            pstmt.setString(6, gender);
            pstmt.setString(7, department);
            pstmt.setString(8, address);
            pstmt.setString(9, phoneNo);
            pstmt.setString(10, city);
            pstmt.setString(11, section);
            pstmt.setString(12, Student_Password);
            pstmt.setString(13, Confirm_Password);

            JOptionPane.showMessageDialog(this, "Signup Successful");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static boolean isValidPakistaniPhone(String phone_number) {
        String pattern = "^(0[1-9]|[1-9][0-9])([ -]?\\d{3}){3}$";
        return phone_number.matches(pattern);
    }

    public static boolean isValidEmail(String email) {
        String pattern = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,3}$";
        return Pattern.matches(pattern, email);
    }

    private boolean IsEmptyStudent() {
        if (namefieild.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Student Name is missing Please Enter the Name");
            return false;
        }
        if (reg_no.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Student Registration No is missing Please Enter the Registration No");
            return false;
        }
        if (department.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Student Department is missing Please Enter the Department");
            return false;
        }
        if (section.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Student Section is not selected");
            return false;
        }
        if (email.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Student Email is missing Please Enter the Email");
            return false;
        }
        if (email.getText().matches("!#$%^&*.+..")) {
            JOptionPane.showMessageDialog(this, "Enter Vald Email");
            return false;
        }
        if (phone_no.getText().matches("!#$%^&*")) {
            JOptionPane.showMessageDialog(this, "Student Phone Number is missing Please Enter the Phone Number");
            return false;
        }
        if (phone_no.getText().length() > 15) {
            JOptionPane.showMessageDialog(this, "Student Phone Number is too long Please Enter the Phone Number again");
            return false;
        }

        if (jgender.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Student gender is not selected");
            return false;
        }
        return true;
    }

    private void ClearStudents() {
        namefieild.setText(null);
        reg_no.setText(null);
        father_name.setText(null);
        email.setText(null);
        dob.setDate(null);
        jgender.setSelectedItem(0);
        department.setText(null);
        section.setSelectedItem(0);
        address.setText(null);
        phone_no.setText(null);
        city.setText(null);
        st_password.setText(null);
        t_confirm_password.setText(null);
        captchafield.setText(null);
    }

    public boolean RegistrationNoExist(String reg_no) {
        try {
            ps = con.prepareStatement("Select * from students where Registration_No = ?");
            ps.setString(1, reg_no);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            Logger.getLogger(SignupasStudent.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    public boolean EmailExists(String reg_no) {
        try {
            ps = con.prepareStatement("Select * from students where Email = ?");
            ps.setString(1, reg_no);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            Logger.getLogger(SignupasStudent.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }



    public boolean PhoneExists(String phone_number) {
        try {
            ps = con.prepareStatement("Select * from students where Email = ?");
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
    /**
     * Creates new form Home
     */
    public int no;

    public SignupasStudent() {
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
        city = new app.bolivia.swing.JCTextField();
        section = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jgender = new javax.swing.JComboBox<>();
        email = new app.bolivia.swing.JCTextField();
        father_name = new app.bolivia.swing.JCTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        reg_no = new app.bolivia.swing.JCTextField();
        phone_no = new app.bolivia.swing.JCTextField();
        jLabel13 = new javax.swing.JLabel();
        address = new app.bolivia.swing.JCTextField();
        namefieild = new app.bolivia.swing.JCTextField();
        department = new app.bolivia.swing.JCTextField();
        dob = new com.toedter.calendar.JDateChooser();
        jLabel14 = new javax.swing.JLabel();
        st_password = new app.bolivia.swing.JCTextField();
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
        jLabel2.setText("Signup as a Student");

        jLayeredPane1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Section");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Department");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Email");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Gender");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Name");

        city.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        section.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "A", "B", "C" }));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Address");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Phone No");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Father Name");

        jgender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Male", "Female" }));

        email.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        email.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                emailFocusLost(evt);
            }
        });

        father_name.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("City");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Date of Birth");

        reg_no.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        reg_no.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                reg_noFocusLost(evt);
            }
        });

        phone_no.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        phone_no.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                phone_noFocusLost(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Registration No");

        address.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        namefieild.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        department.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        dob.setDateFormatString("yyyy-MM-dd");
        dob.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                dobFocusLost(evt);
            }
        });
        dob.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dobKeyPressed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Password");

        st_password.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

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
        jLayeredPane1.setLayer(city, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(section, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jgender, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(email, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(father_name, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel11, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(reg_no, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(phone_no, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel13, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(address, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(namefieild, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(department, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(dob, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel14, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(st_password, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel15, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(t_confirm_password, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(captcha, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(captchafield, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(st_password, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(t_confirm_password, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(captcha, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addComponent(captchafield, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                .addComponent(jLabel6)
                                .addComponent(jLabel8)
                                .addComponent(jLabel10)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(jLabel1)))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dob, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                                    .addComponent(phone_no, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(city, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(29, 29, 29)
                                        .addComponent(section, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jLayeredPane1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                                .addComponent(jgender, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(department, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE))
                                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))))))
                            .addComponent(address, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                        .addComponent(namefieild, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE))
                                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                        .addComponent(father_name, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel9)
                                        .addGap(75, 75, 75)))
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(reg_no, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(20, 20, 20))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel1)
                            .addComponent(namefieild, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(reg_no, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(father_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)))
                .addGap(30, 30, 30)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jgender, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(dob, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(department, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(30, 30, 30)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(address, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(city, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(phone_no, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(section, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(t_confirm_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(st_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(captchafield, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(captcha, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
        );

        jLayeredPane2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));

        rSButtonHover3.setBackground(new java.awt.Color(255, 51, 51));
        rSButtonHover3.setText("Login as Student");
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
        rSButtonHover2.setText("Signup as Teacher");
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
                .addComponent(rSButtonHover3, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rSButtonHover1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rSButtonHover2, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rSButtonHover1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonHover3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonHover2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(488, 488, 488)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(561, 561, 561)
                        .addComponent(jLabel3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(20, 20, 20)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(194, Short.MAX_VALUE))
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

    private void rSButtonHover2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonHover2MouseClicked
        // TODO add your handling code here:
        this.dispose();
        new SignupasTeacher().setVisible(true);
    }//GEN-LAST:event_rSButtonHover2MouseClicked

    private void rSButtonHover3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonHover3MouseClicked
        // TODO add your handling code here:
        this.dispose();
        new LoginasStudent().setVisible(true);
    }//GEN-LAST:event_rSButtonHover3MouseClicked

    private void rSButtonHover1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover1ActionPerformed
        // TODO add your handling code here:
        if (IsEmptyStudent()) {
            SignupasStudent signupasStudent = new SignupasStudent();
            if (!signupasStudent.RegistrationNoExist(reg_no.getText())) {
                if (!signupasStudent.PhoneExists(phone_no.getText())) {
                    if (!signupasStudent.EmailExists(email.getText())) {
                        String Name = namefieild.getText();
                        String Registration_No = reg_no.getText();
                        String Father_Name = this.father_name.getText();
                        String Email = email.getText(); // Change variable name here
                        Date dobDate = dob.getDate();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String DateOfBirth = dateFormat.format(dobDate);
                        String gender = jgender.getSelectedItem().toString();
                        String Department = department.getText();
                        String section = this.section.getSelectedItem().toString();
                        String Address = address.getText();
                        String Phone = phone_no.getText();
                        String City = city.getText();
                        String Password = st_password.getText();
                        String Confirm_Password = t_confirm_password.getText();
                        if (Password.equals(Confirm_Password)) {
                            String captchaFieldText = captchafield.getText();
                            if (String.valueOf(no).equalsIgnoreCase(captchaFieldText)) {
                                signupasStudent.insertatsignup(Name, Registration_No, Father_Name, DateOfBirth, Email, gender, Department, Address, Phone, City, section, Password, Confirm_Password); // Corrected variable names
                                ClearStudents();
                                Random rand = new Random();
                                int number = rand.nextInt(45645672) + 123456;
                                no = number;
                                captcha.setText(String.valueOf(number));
                                ClearStudents(); // Assuming a method to clear teacher fields
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
        }

    }//GEN-LAST:event_rSButtonHover1ActionPerformed

    private void phone_noFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_phone_noFocusLost
        // TODO add your handling code here:
        String phone = phone_no.getText();
        if (!PhoneExists(phone)) {
            if (isValidPakistaniPhone(phone)) {

            } else {
                JOptionPane.showMessageDialog(this, "Not a valid Phone No");
                email.setText(null);

            }
        } else {
            JOptionPane.showMessageDialog(this, "Phone Number No already exists!!!");
            email.setText(null);
        }
    }//GEN-LAST:event_phone_noFocusLost

    private void emailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailFocusLost
        // TODO add your handling code here:

        String Email = email.getText();
        if (!EmailExists(Email)) {
            if (isValidEmail(Email)) {

            } else {
                JOptionPane.showMessageDialog(this, "Not a valid Email");
                email.setText(null);

            }
        } else {
            JOptionPane.showMessageDialog(this, "Email No already exists!!!");
            email.setText(null);
        }

    }//GEN-LAST:event_emailFocusLost

    private void dobFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dobFocusLost

    }//GEN-LAST:event_dobFocusLost

    private void dobKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dobKeyPressed

    }//GEN-LAST:event_dobKeyPressed

    private void reg_noFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_reg_noFocusLost
        // TODO add your handling code here:
        String reg = reg_no.getText();
        if (!RegistrationNoExist(reg)) {

        } else {
            JOptionPane.showMessageDialog(this, "Registration No already exists!!!");
            reg_no.setText(null);
        }

    }//GEN-LAST:event_reg_noFocusLost

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

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private app.bolivia.swing.JCTextField address;
    public javax.swing.JLabel captcha;
    private app.bolivia.swing.JCTextField captchafield;
    private app.bolivia.swing.JCTextField city;
    private app.bolivia.swing.JCTextField department;
    private com.toedter.calendar.JDateChooser dob;
    private app.bolivia.swing.JCTextField email;
    private app.bolivia.swing.JCTextField father_name;
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
    private javax.swing.JComboBox<String> jgender;
    private app.bolivia.swing.JCTextField namefieild;
    private app.bolivia.swing.JCTextField phone_no;
    private rojeru_san.complementos.RSButtonHover rSButtonHover1;
    private rojeru_san.complementos.RSButtonHover rSButtonHover2;
    private rojeru_san.complementos.RSButtonHover rSButtonHover3;
    private app.bolivia.swing.JCTextField reg_no;
    private javax.swing.JComboBox<String> section;
    private app.bolivia.swing.JCTextField st_password;
    private app.bolivia.swing.JCTextField t_confirm_password;
    // End of variables declaration//GEN-END:variables
}
