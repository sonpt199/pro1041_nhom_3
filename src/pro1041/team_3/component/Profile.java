package pro1041.team_3.component;

import javax.swing.ImageIcon;
import pro1041.team_3.domainModel.NhanVien;

public class Profile extends javax.swing.JPanel {
    
    public static Boolean user;

    public Profile(NhanVien userHienTai) {
        initComponents();
        txtHello.setText("Xin chào, " + userHienTai.getHoTen());
        ImageIcon icon = new ImageIcon(getClass().getResource("/pro1041/team_3/icon/logoCircle.png"));
        setOpaque(false);
        user = false;
    }
    
    public static Boolean get() {
        return user;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pic = new pro1041.team_3.swing.ImageAvatar();
        txtHello = new javax.swing.JLabel();

        pic.setForeground(new java.awt.Color(245, 245, 245));
        pic.setBorderSize(2);
        pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/logoCircleWhite.png"))); // NOI18N

        txtHello.setBackground(new java.awt.Color(255, 255, 255));
        txtHello.setFont(new java.awt.Font("Nunito", 3, 14)); // NOI18N
        txtHello.setForeground(new java.awt.Color(255, 255, 255));
        txtHello.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtHello.setText("Xin chao");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addComponent(pic, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtHello, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pic, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtHello)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private pro1041.team_3.swing.ImageAvatar pic;
    private javax.swing.JLabel txtHello;
    // End of variables declaration//GEN-END:variables
}
