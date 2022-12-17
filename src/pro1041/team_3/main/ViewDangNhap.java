package pro1041.team_3.main;

import pro1041.team_3.service.LoginService;
import pro1041.team_3.service.impl.LoginServiceImpl;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import pro1041.team_3.domainModel.NhanVien;
import pro1041.team_3.swing.Notification;

/**
 *
 * @author van15
 */
public class ViewDangNhap extends javax.swing.JFrame {

    private LoginService loginService;

    public ViewDangNhap() {
        initComponents();
        this.setTitle("Login");
        ImageIcon icon = new ImageIcon(getClass().getResource("/pro1041/team_3/icon/logoCircle.png"));
        this.setIconImage(icon.getImage());
        loginService = new LoginServiceImpl();
    }
    
    private void login() {        
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        if (username.isEmpty() || password.isEmpty()) {
//            txtError.setText("Vui lòng nhập đầy đủ Username và Password");
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.TOP_CENTER, "Vui lòng nhập đầy đủ Username và Password");
            panel.showNotification();
            return;
        }
        if (username.contains(" ")) {
//            txtError.setText("Username không được chưa khoảng trắng");
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.TOP_CENTER, "Username không được chưa khoảng trắng");
            panel.showNotification();
            return;
        }
        if (username.length() < 6) {
//            txtError.setText("Username không được chưa khoảng trắng");
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.TOP_CENTER, "Username tối thiểu 6 ký tự");
            panel.showNotification();
            return;
        }
        if (username.length() > 30) {
//            txtError.setText("Username không được chưa khoảng trắng");
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.TOP_CENTER, "Username tối đa 30 ký tự");
            panel.showNotification();
            return;
        }
        if (!username.matches("\\w+")) {
//            txtError.setText("Username không được chưa khoảng trắng");
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.TOP_CENTER, "Username chỉ gồm ký tự chữ và số");
            panel.showNotification();
            return;
        }
        if (password.contains(" ")) {
//            txtError.setText("Password không được chưa khoảng trắng");
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.TOP_CENTER, "Password không được chưa khoảng trắng");
            panel.showNotification();
            return;
        }
        if (password.length() < 6) {
//            txtError.setText("Password không được chưa khoảng trắng");
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.TOP_CENTER, "Password tối thiểu 6 ký tự");
            panel.showNotification();
            return;
        }
        if (password.length() > 50) {
//            txtError.setText("Password không được chưa khoảng trắng");
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.TOP_CENTER, "Password tối đa 50 ký tự");
            panel.showNotification();
            return;
        }
        if (!password.matches("\\w+")) {
//            txtError.setText("Password không được chưa khoảng trắng");
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.TOP_CENTER, "Password chỉ gồm ký tự chữ và số");
            panel.showNotification();
            return;
        }
        
        NhanVien user = loginService.login(username, password);
        if (user == null) {
//            txtError.setText("Username hoặc Password không đúng");
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.TOP_CENTER, "Username hoặc Password không đúng");
            panel.showNotification();
            return;
        }
        if (user.getTrangThaiLamViec() == 1) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.TOP_CENTER, "Tài khoản đã bị vô hiệu hóa");
            panel.showNotification();
            return;
        }
        Main mainView = new Main(user);
        mainView.setVisible(true);
        this.dispose();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        Exit = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtError = new javax.swing.JLabel();
        txtPassword = new pro1041.team_3.swing.PasswordField();
        txtUsername = new pro1041.team_3.swing.TextField();
        buttonCustom1 = new pro1041.team_3.swing.ButtonCustom();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/logoWhiteBg.jpg"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(63, 41, 366, 336));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 440));

        jPanel2.setBackground(new java.awt.Color(1, 181, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Exit.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        Exit.setForeground(new java.awt.Color(255, 255, 255));
        Exit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Exit.setText("x");
        Exit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ExitMouseClicked(evt);
            }
        });
        jPanel2.add(Exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(374, 0, 40, -1));

        jLabel2.setFont(new java.awt.Font("Nunito", 0, 32)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Sign in");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 414, -1));

        jLabel3.setFont(new java.awt.Font("Nunito", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Xin chào!");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 121, 414, -1));

        txtError.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtError.setForeground(new java.awt.Color(255, 51, 51));
        jPanel2.add(txtError, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 308, 360, 26));

        txtPassword.setBackground(new java.awt.Color(1, 181, 204));
        txtPassword.setForeground(new java.awt.Color(255, 255, 255));
        txtPassword.setFocusLostColor(new java.awt.Color(255, 255, 255));
        txtPassword.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtPassword.setLabelColor(new java.awt.Color(255, 255, 255));
        txtPassword.setLabelText("Password");
        txtPassword.setLineColor(new java.awt.Color(255, 255, 255));
        txtPassword.setSelectionColor(new java.awt.Color(153, 153, 153));
        txtPassword.setShowAndHide(true);
        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });
        jPanel2.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 320, -1));

        txtUsername.setBackground(new java.awt.Color(1, 181, 204));
        txtUsername.setForeground(new java.awt.Color(255, 255, 255));
        txtUsername.setCaretColor(new java.awt.Color(255, 255, 255));
        txtUsername.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtUsername.setFocusLostColor(new java.awt.Color(255, 255, 255));
        txtUsername.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtUsername.setLabelColor(new java.awt.Color(255, 255, 255));
        txtUsername.setLabelText("Username");
        txtUsername.setLineColor(new java.awt.Color(255, 255, 255));
        txtUsername.setSelectionColor(new java.awt.Color(153, 153, 153));
        jPanel2.add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 320, -1));

        buttonCustom1.setText("Sign In");
        buttonCustom1.setFont(new java.awt.Font("Nunito", 1, 18)); // NOI18N
        buttonCustom1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCustom1ActionPerformed(evt);
            }
        });
        jPanel2.add(buttonCustom1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 340, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 0, 420, 440));

        setSize(new java.awt.Dimension(916, 438));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitMouseClicked
        System.exit(0);
    }//GEN-LAST:event_ExitMouseClicked

    private void buttonCustom1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCustom1ActionPerformed
        // BTN SignIn
        login();
    }//GEN-LAST:event_buttonCustom1ActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TXT Password enter
        login();
    }//GEN-LAST:event_txtPasswordActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewDangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewDangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewDangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewDangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewDangNhap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Exit;
    private pro1041.team_3.swing.ButtonCustom buttonCustom1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel txtError;
    private pro1041.team_3.swing.PasswordField txtPassword;
    private pro1041.team_3.swing.TextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
