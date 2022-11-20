/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package fpoly.pro1041.nhom_3.views;

import fpoly.pro1041.nhom_3.domainModel.DanhMucBean;
import fpoly.pro1041.nhom_3.controller.ChuyenManHinhController;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author van15
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form MainTestFinal
     */
    public Main() {
        initComponents();
        
        ChuyenManHinhController controller = new ChuyenManHinhController(jpnView);
        controller.setView(jpnTrangChu, jlbTrangChu);
        
        List<DanhMucBean> listItem = new ArrayList<>();
        listItem.add(new DanhMucBean("TrangChu", jpnTrangChu, jlbTrangChu));
        listItem.add(new DanhMucBean("SanPham", jpnSanPham, jlbSanPham));
        listItem.add(new DanhMucBean("BanHang", jpnBanHang, jlbBanHang));
        listItem.add(new DanhMucBean("HoaDon", jpnHoaDon, jlbHoaDon));
        listItem.add(new DanhMucBean("KhuyenMai", jpnKhuyenMai, jlbKhuyenMai));
        listItem.add(new DanhMucBean("TaiKhoan", jpnTaiKhoan, jlbTaiKhoan));
        listItem.add(new DanhMucBean("KhachHang", jpnKhachHang, jlbKhachHang));
        listItem.add(new DanhMucBean("ThongKe", jpnThongKe, jlbThongKe));
        
        controller.setEvent(listItem);
    }

//    void setColor(JPanel panel){
//        panel.setBackground(new Color(1,163,231));
//    }
//    void resetColor(JPanel panel){
//        panel.setBackground(new Color(1,187,203));
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnRoot = new javax.swing.JPanel();
        jpnMenu = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jpnTrangChu = new javax.swing.JPanel();
        jlbTrangChu = new javax.swing.JLabel();
        jpnSanPham = new javax.swing.JPanel();
        jlbSanPham = new javax.swing.JLabel();
        jpnBanHang = new javax.swing.JPanel();
        jlbBanHang = new javax.swing.JLabel();
        jpnHoaDon = new javax.swing.JPanel();
        jlbHoaDon = new javax.swing.JLabel();
        jpnKhuyenMai = new javax.swing.JPanel();
        jlbKhuyenMai = new javax.swing.JLabel();
        jpnTaiKhoan = new javax.swing.JPanel();
        jlbTaiKhoan = new javax.swing.JLabel();
        jpnKhachHang = new javax.swing.JPanel();
        jlbKhachHang = new javax.swing.JLabel();
        jpnThongKe = new javax.swing.JPanel();
        jlbThongKe = new javax.swing.JLabel();
        jpnDangXuat = new javax.swing.JPanel();
        jlbDangXuat = new javax.swing.JLabel();
        jpnThoat = new javax.swing.JPanel();
        jlbThoat = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jpnView = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpnRoot.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpnMenu.setBackground(new java.awt.Color(1, 187, 203));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("LOGO");
        jLabel1.setBorder(new javax.swing.border.MatteBorder(null));

        jpnTrangChu.setBackground(new java.awt.Color(1, 187, 203));
        jpnTrangChu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpnTrangChuMouseClicked(evt);
            }
        });

        jlbTrangChu.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jlbTrangChu.setForeground(new java.awt.Color(255, 255, 255));
        jlbTrangChu.setText("Trang chu");

        javax.swing.GroupLayout jpnTrangChuLayout = new javax.swing.GroupLayout(jpnTrangChu);
        jpnTrangChu.setLayout(jpnTrangChuLayout);
        jpnTrangChuLayout.setHorizontalGroup(
            jpnTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnTrangChuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbTrangChu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnTrangChuLayout.setVerticalGroup(
            jpnTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnTrangChuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbTrangChu, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpnSanPham.setBackground(new java.awt.Color(1, 187, 203));
        jpnSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpnSanPhamMouseClicked(evt);
            }
        });

        jlbSanPham.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jlbSanPham.setForeground(new java.awt.Color(255, 255, 255));
        jlbSanPham.setText("San pham");

        javax.swing.GroupLayout jpnSanPhamLayout = new javax.swing.GroupLayout(jpnSanPham);
        jpnSanPham.setLayout(jpnSanPhamLayout);
        jpnSanPhamLayout.setHorizontalGroup(
            jpnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnSanPhamLayout.setVerticalGroup(
            jpnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpnBanHang.setBackground(new java.awt.Color(1, 187, 203));
        jpnBanHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpnBanHangMouseClicked(evt);
            }
        });

        jlbBanHang.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jlbBanHang.setForeground(new java.awt.Color(255, 255, 255));
        jlbBanHang.setText("Ban hang");

        javax.swing.GroupLayout jpnBanHangLayout = new javax.swing.GroupLayout(jpnBanHang);
        jpnBanHang.setLayout(jpnBanHangLayout);
        jpnBanHangLayout.setHorizontalGroup(
            jpnBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnBanHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbBanHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnBanHangLayout.setVerticalGroup(
            jpnBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnBanHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbBanHang, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpnHoaDon.setBackground(new java.awt.Color(1, 187, 203));
        jpnHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpnHoaDonMouseClicked(evt);
            }
        });

        jlbHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jlbHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        jlbHoaDon.setText("Hoa don");

        javax.swing.GroupLayout jpnHoaDonLayout = new javax.swing.GroupLayout(jpnHoaDon);
        jpnHoaDon.setLayout(jpnHoaDonLayout);
        jpnHoaDonLayout.setHorizontalGroup(
            jpnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnHoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnHoaDonLayout.setVerticalGroup(
            jpnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnHoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpnKhuyenMai.setBackground(new java.awt.Color(1, 187, 203));
        jpnKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpnKhuyenMaiMouseClicked(evt);
            }
        });

        jlbKhuyenMai.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jlbKhuyenMai.setForeground(new java.awt.Color(255, 255, 255));
        jlbKhuyenMai.setText("Khuyen mai");

        javax.swing.GroupLayout jpnKhuyenMaiLayout = new javax.swing.GroupLayout(jpnKhuyenMai);
        jpnKhuyenMai.setLayout(jpnKhuyenMaiLayout);
        jpnKhuyenMaiLayout.setHorizontalGroup(
            jpnKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnKhuyenMaiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbKhuyenMai, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnKhuyenMaiLayout.setVerticalGroup(
            jpnKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnKhuyenMaiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbKhuyenMai, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpnTaiKhoan.setBackground(new java.awt.Color(1, 187, 203));
        jpnTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpnTaiKhoanMouseClicked(evt);
            }
        });

        jlbTaiKhoan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jlbTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        jlbTaiKhoan.setText("Tai khoan");

        javax.swing.GroupLayout jpnTaiKhoanLayout = new javax.swing.GroupLayout(jpnTaiKhoan);
        jpnTaiKhoan.setLayout(jpnTaiKhoanLayout);
        jpnTaiKhoanLayout.setHorizontalGroup(
            jpnTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnTaiKhoanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnTaiKhoanLayout.setVerticalGroup(
            jpnTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnTaiKhoanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpnKhachHang.setBackground(new java.awt.Color(1, 187, 203));
        jpnKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpnKhachHangMouseClicked(evt);
            }
        });

        jlbKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jlbKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        jlbKhachHang.setText("Khach hang");

        javax.swing.GroupLayout jpnKhachHangLayout = new javax.swing.GroupLayout(jpnKhachHang);
        jpnKhachHang.setLayout(jpnKhachHangLayout);
        jpnKhachHangLayout.setHorizontalGroup(
            jpnKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnKhachHangLayout.setVerticalGroup(
            jpnKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpnThongKe.setBackground(new java.awt.Color(1, 187, 203));
        jpnThongKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpnThongKeMouseClicked(evt);
            }
        });

        jlbThongKe.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jlbThongKe.setForeground(new java.awt.Color(255, 255, 255));
        jlbThongKe.setText("Thong ke");

        javax.swing.GroupLayout jpnThongKeLayout = new javax.swing.GroupLayout(jpnThongKe);
        jpnThongKe.setLayout(jpnThongKeLayout);
        jpnThongKeLayout.setHorizontalGroup(
            jpnThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnThongKeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnThongKeLayout.setVerticalGroup(
            jpnThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnThongKeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpnDangXuat.setBackground(new java.awt.Color(1, 187, 203));
        jpnDangXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpnDangXuatMouseClicked(evt);
            }
        });

        jlbDangXuat.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jlbDangXuat.setForeground(new java.awt.Color(255, 255, 255));
        jlbDangXuat.setText("Dang xuat");

        javax.swing.GroupLayout jpnDangXuatLayout = new javax.swing.GroupLayout(jpnDangXuat);
        jpnDangXuat.setLayout(jpnDangXuatLayout);
        jpnDangXuatLayout.setHorizontalGroup(
            jpnDangXuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnDangXuatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbDangXuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnDangXuatLayout.setVerticalGroup(
            jpnDangXuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnDangXuatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbDangXuat, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpnThoat.setBackground(new java.awt.Color(1, 187, 203));
        jpnThoat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpnThoatMouseClicked(evt);
            }
        });

        jlbThoat.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jlbThoat.setForeground(new java.awt.Color(255, 255, 255));
        jlbThoat.setText("Thoat");

        javax.swing.GroupLayout jpnThoatLayout = new javax.swing.GroupLayout(jpnThoat);
        jpnThoat.setLayout(jpnThoatLayout);
        jpnThoatLayout.setHorizontalGroup(
            jpnThoatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnThoatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbThoat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnThoatLayout.setVerticalGroup(
            jpnThoatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnThoatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbThoat, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jpnMenuLayout = new javax.swing.GroupLayout(jpnMenu);
        jpnMenu.setLayout(jpnMenuLayout);
        jpnMenuLayout.setHorizontalGroup(
            jpnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnTrangChu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnBanHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnKhuyenMai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnDangXuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnThoat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpnMenuLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnMenuLayout.setVerticalGroup(
            jpnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnMenuLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jpnTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(137, Short.MAX_VALUE))
        );

        jpnRoot.add(jpnMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 230, 800));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel2.setText("Hello, Nguyen Van A");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Avatar");
        jLabel3.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Chu chay");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 735, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 317, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(jLabel5))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jpnRoot.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, 1290, 60));

        javax.swing.GroupLayout jpnViewLayout = new javax.swing.GroupLayout(jpnView);
        jpnView.setLayout(jpnViewLayout);
        jpnViewLayout.setHorizontalGroup(
            jpnViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1290, Short.MAX_VALUE)
        );
        jpnViewLayout.setVerticalGroup(
            jpnViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 740, Short.MAX_VALUE)
        );

        jpnRoot.add(jpnView, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, 1290, 740));

        getContentPane().add(jpnRoot, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1520, 800));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jpnTrangChuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnTrangChuMouseClicked
//        setColor(jpnTrangChu);
//        resetColor(jpnSanPham);
//        resetColor(jpnBanHang);
//        resetColor(jpnHoaDon);
//        resetColor(jpnKhuyenMai);
//        resetColor(jpnTaiKhoan);
//        resetColor(jpnKhachHang);
//        resetColor(jpnThongKe);
//        resetColor(jpnDangXuat);
//        resetColor(jpnThoat);
    }//GEN-LAST:event_jpnTrangChuMouseClicked

    private void jpnSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnSanPhamMouseClicked
//        setColor(jpnSanPham);
//        resetColor(jpnBanHang);
//        resetColor(jpnHoaDon);
//        resetColor(jpnKhuyenMai);
//        resetColor(jpnTaiKhoan);
//        resetColor(jpnKhachHang);
//        resetColor(jpnThongKe);
//        resetColor(jpnDangXuat);
//        resetColor(jpnThoat);
//        resetColor(jpnTrangChu);
    }//GEN-LAST:event_jpnSanPhamMouseClicked

    private void jpnBanHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnBanHangMouseClicked
//        setColor(jpnBanHang);
//        resetColor(jpnHoaDon);
//        resetColor(jpnKhuyenMai);
//        resetColor(jpnTaiKhoan);
//        resetColor(jpnKhachHang);
//        resetColor(jpnThongKe);
//        resetColor(jpnDangXuat);
//        resetColor(jpnThoat);
//        resetColor(jpnTrangChu);
//        resetColor(jpnSanPham);
    }//GEN-LAST:event_jpnBanHangMouseClicked

    private void jpnHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnHoaDonMouseClicked
//        setColor(jpnHoaDon);
//        resetColor(jpnKhuyenMai);
//        resetColor(jpnTaiKhoan);
//        resetColor(jpnKhachHang);
//        resetColor(jpnThongKe);
//        resetColor(jpnDangXuat);
//        resetColor(jpnThoat);
//        resetColor(jpnTrangChu);
//        resetColor(jpnSanPham);
//        resetColor(jpnBanHang);
    }//GEN-LAST:event_jpnHoaDonMouseClicked

    private void jpnKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnKhuyenMaiMouseClicked
//        setColor(jpnKhuyenMai);
//        resetColor(jpnTaiKhoan);
//        resetColor(jpnKhachHang);
//        resetColor(jpnThongKe);
//        resetColor(jpnDangXuat);
//        resetColor(jpnThoat);
//        resetColor(jpnTrangChu);
//        resetColor(jpnSanPham);
//        resetColor(jpnBanHang);
//        resetColor(jpnHoaDon);
    }//GEN-LAST:event_jpnKhuyenMaiMouseClicked

    private void jpnTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnTaiKhoanMouseClicked
//        setColor(jpnTaiKhoan);
//        resetColor(jpnKhachHang);
//        resetColor(jpnThongKe);
//        resetColor(jpnDangXuat);
//        resetColor(jpnThoat);
//        resetColor(jpnTrangChu);
//        resetColor(jpnSanPham);
//        resetColor(jpnBanHang);
//        resetColor(jpnHoaDon);
//        resetColor(jpnKhuyenMai);
    }//GEN-LAST:event_jpnTaiKhoanMouseClicked

    private void jpnKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnKhachHangMouseClicked
//        setColor(jpnKhachHang);
//        resetColor(jpnThongKe);
//        resetColor(jpnDangXuat);
//        resetColor(jpnThoat);
//        resetColor(jpnTrangChu);
//        resetColor(jpnSanPham);
//        resetColor(jpnBanHang);
//        resetColor(jpnHoaDon);
//        resetColor(jpnKhuyenMai);
//        resetColor(jpnTaiKhoan);
    }//GEN-LAST:event_jpnKhachHangMouseClicked

    private void jpnThongKeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnThongKeMouseClicked
//        setColor(jpnThongKe);
//        resetColor(jpnDangXuat);
//        resetColor(jpnThoat);
//        resetColor(jpnTrangChu);
//        resetColor(jpnSanPham);
//        resetColor(jpnBanHang);
//        resetColor(jpnHoaDon);
//        resetColor(jpnKhuyenMai);
//        resetColor(jpnTaiKhoan);
//        resetColor(jpnKhachHang);
    }//GEN-LAST:event_jpnThongKeMouseClicked

    private void jpnDangXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnDangXuatMouseClicked
//        setColor(jpnDangXuat);
//        resetColor(jpnThoat);
//        resetColor(jpnTrangChu);
//        resetColor(jpnSanPham);
//        resetColor(jpnBanHang);
//        resetColor(jpnHoaDon);
//        resetColor(jpnKhuyenMai);
//        resetColor(jpnTaiKhoan);
//        resetColor(jpnKhachHang);
//        resetColor(jpnThongKe);
    }//GEN-LAST:event_jpnDangXuatMouseClicked

    private void jpnThoatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnThoatMouseClicked
//        setColor(jpnThoat);
//        resetColor(jpnTrangChu);
//        resetColor(jpnSanPham);
//        resetColor(jpnBanHang);
//        resetColor(jpnHoaDon);
//        resetColor(jpnKhuyenMai);
//        resetColor(jpnTaiKhoan);
//        resetColor(jpnKhachHang);
//        resetColor(jpnThongKe);
//        resetColor(jpnDangXuat);
        System.exit(0);
    }//GEN-LAST:event_jpnThoatMouseClicked

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel jlbBanHang;
    private javax.swing.JLabel jlbDangXuat;
    private javax.swing.JLabel jlbHoaDon;
    private javax.swing.JLabel jlbKhachHang;
    private javax.swing.JLabel jlbKhuyenMai;
    private javax.swing.JLabel jlbSanPham;
    private javax.swing.JLabel jlbTaiKhoan;
    private javax.swing.JLabel jlbThoat;
    private javax.swing.JLabel jlbThongKe;
    private javax.swing.JLabel jlbTrangChu;
    private javax.swing.JPanel jpnBanHang;
    private javax.swing.JPanel jpnDangXuat;
    private javax.swing.JPanel jpnHoaDon;
    private javax.swing.JPanel jpnKhachHang;
    private javax.swing.JPanel jpnKhuyenMai;
    private javax.swing.JPanel jpnMenu;
    private javax.swing.JPanel jpnRoot;
    private javax.swing.JPanel jpnSanPham;
    private javax.swing.JPanel jpnTaiKhoan;
    private javax.swing.JPanel jpnThoat;
    private javax.swing.JPanel jpnThongKe;
    private javax.swing.JPanel jpnTrangChu;
    private javax.swing.JPanel jpnView;
    // End of variables declaration//GEN-END:variables
}