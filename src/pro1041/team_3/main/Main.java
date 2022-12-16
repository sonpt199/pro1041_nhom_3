package pro1041.team_3.main;

import java.awt.Image;
import pro1041.team_3.component.MenuLayout;
import pro1041.team_3.swing.eventInterface.EventMenuSelected;
import pro1041.team_3.form.ViewThongKe;
import pro1041.team_3.form.ViewBanHang;
import pro1041.team_3.form.MainForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import pro1041.team_3.component.Menu;
import pro1041.team_3.component.Profile;
import pro1041.team_3.domainModel.NhanVien;
import pro1041.team_3.dto.HoaDonChiTietDto;
import pro1041.team_3.dto.HoaDonDto;
import pro1041.team_3.form.ViewNhanVienXemKhuyenMai;
import pro1041.team_3.form.ViewNhanVienXemSanPham;
import pro1041.team_3.form.ViewQuanLyHoaDon;
import pro1041.team_3.form.ViewQuanLyKhachHang;
import pro1041.team_3.form.ViewQuanLyKhuyenMai;
import pro1041.team_3.form.ViewQuanLyNhanVien;
import pro1041.team_3.form.ViewQuanLySanPham;
import pro1041.team_3.service.HoaDonChiTietService;
import pro1041.team_3.service.HoaDonService;
import pro1041.team_3.service.NhanVienService;
import pro1041.team_3.service.impl.HoaDonChiTietServiceImpl;
import pro1041.team_3.service.impl.HoaDonServiceImpl;
import pro1041.team_3.service.impl.NhanVienServiceImpl;
import pro1041.team_3.swing.Notification;
import pro1041.team_3.util.EmailUtil;
//import pro1041.team_3.util.DailyCheckKhuyenMai;

public class Main extends javax.swing.JFrame {

    private final MigLayout layout;
    private final MainForm main;
    private final MenuLayout menu;
    private final Animator animator;
//    private final DailyCheckKhuyenMai daily;
    private HoaDonService hoaDonService;
    private HoaDonChiTietService hoaDonChiTietService;
    private NhanVien userHienTai;
    private static int maXacNhan = 0;
    private NhanVienService nhanVienService;

    public Main(NhanVien user) {
        initComponents();
//        daily = new DailyCheckKhuyenMai();
//        daily.start();
        userHienTai = user;
        hoaDonService = new HoaDonServiceImpl();
        hoaDonChiTietService = new HoaDonChiTietServiceImpl();
        nhanVienService = new NhanVienServiceImpl();
        userHienTai = user;
        Notification panel = new Notification(this, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Đăng nhập thành công");
        panel.showNotification();
        this.setTitle("Waikiki");
        ImageIcon icon = new ImageIcon(getClass().getResource("/pro1041/team_3/icon/logoCircle.png"));
        dlDoiMatKhau.setIconImage(icon.getImage());
        dlDoiMatKhau.setTitle("Đổi mật khẩu");
        dlDoiMatKhau.setLocationRelativeTo(null);
        this.setIconImage(icon.getImage());
        dlTimHoaDon.setIconImage(icon.getImage());
        dlTimHoaDon.setLocationRelativeTo(null);
        dlTimHoaDon.setTitle("Tìm kiếm hóa đơn");
        layout = new MigLayout("fill", "0[fill]0", "0[fill]0");
        main = new MainForm(user);
        menu = new MenuLayout(user);
        menu.getMenu().initMoving(Main.this);
        main.initMoving(Main.this);
        mainPanel.setLayer(menu, JLayeredPane.POPUP_LAYER);
        mainPanel.setLayout(layout);
        mainPanel.add(main);
        mainPanel.add(menu, "pos -215 0 100% 100%", 0);
        Profile.user = true;
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                float x = (fraction * 215);
                float alpha;
                if (menu.isShow()) {
                    x = -x;
                    alpha = 0.5f - (fraction / 2);
                } else {
                    x -= 215;
                    alpha = fraction / 2;
                }
                layout.setComponentConstraints(menu, "pos " + (int) x + " 0 100% 100%");
                if (alpha < 0) {
                    alpha = 0;
                }
                menu.setAlpha(alpha);
                mainPanel.revalidate();
            }

            @Override
            public void end() {
                menu.setShow(!menu.isShow());
                if (!menu.isShow()) {
                    menu.setVisible(false);
                }
            }

        };
        animator = new Animator(200, target);
        menu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    if (!animator.isRunning()) {
                        if (menu.isShow()) {
                            animator.start();
                        }
                    }
                }
            }
        });
        main.addEventMenu(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!animator.isRunning()) {
                    if (!menu.isShow()) {
                        menu.setVisible(true);
                        animator.start();
                    }
                }
            }
        });
        menu.getMenu().addEventMenuSelected(new EventMenuSelected() {
            @Override
            public void selected(int index) {
                if (user.getVaiTro() == 2) {
                    if (index == 0) {
                        main.show(new ViewThongKe());
                    } else if (index == 1) {
                        main.show(new ViewBanHang(user));
                    } else if (index == 2) {
                        main.show(new ViewQuanLySanPham());
                    } else if (index == 3) {
                        main.show(new ViewQuanLyKhuyenMai());
                    } else if (index == 4) {
                        main.show(new ViewQuanLyKhachHang());
                    } else if (index == 5) {
                        main.show(new ViewQuanLyNhanVien());
                    } else if (index == 6) {
                        main.show(new ViewQuanLyHoaDon());
                    } else if (index == 7) {
                        dlDoiMatKhau.setVisible(true);
                    } else if (index == 8) {
                        ViewDangNhap dangNhap = new ViewDangNhap();
                        dangNhap.setVisible(true);
                        close();
                    } else if (index == 9) {
                        System.exit(0);
                    }
                } else {
                    if (index == 0) {
                        main.show(new ViewBanHang(user));
                    } else if (index == 1) {
                        main.show(new ViewNhanVienXemSanPham());
                    } else if (index == 2) {
                        main.show(new ViewNhanVienXemKhuyenMai());
                    } else if (index == 3) {
                        main.show(new ViewQuanLyKhachHang());
                    } else if (index == 4) {
                        dlTimHoaDon.setVisible(true);
                    } else if (index == 5) {
                        dlDoiMatKhau.setVisible(true);
                        jpnConfirm.setVisible(true);
                        jpnChange.setVisible(false);
                        txtEmailError.setVisible(false);
                        txtMaXacNhanError.setVisible(false);
                    } else if (index == 6) {
                        ViewDangNhap dangNhap = new ViewDangNhap();
                        dangNhap.setVisible(true);
                        close();
                    } else if (index == 7) {
                        System.exit(0);
                    }
                }
            }
        });
    }

    private void close() {
        this.dispose();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dlTimHoaDon = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        txtTongTien = new pro1041.team_3.swing.TextField();
        txtMaHoaDon = new pro1041.team_3.swing.TextField();
        txtMaKhachHang = new pro1041.team_3.swing.TextField();
        txtTenKhachHang = new pro1041.team_3.swing.TextField();
        txtSdtKhachHang = new pro1041.team_3.swing.TextField();
        txtMaNhanVien = new pro1041.team_3.swing.TextField();
        txtNgayThanhToan = new pro1041.team_3.swing.TextField();
        txtHinhThucThanhToan = new pro1041.team_3.swing.TextField();
        txtNganHang = new pro1041.team_3.swing.TextField();
        txtMaGiaoDich = new pro1041.team_3.swing.TextField();
        txtTenNhanVien = new pro1041.team_3.swing.TextField();
        txtTienMat = new pro1041.team_3.swing.TextField();
        txtTimKiemHoaDon = new pro1041.team_3.swing.TextField();
        jspTbHoaDonChiTiet = new javax.swing.JScrollPane();
        tbHoaDonChiTiet = new pro1041.team_3.swing.config.Table();
        dlDoiMatKhau = new javax.swing.JDialog();
        jpnConfirm = new javax.swing.JPanel();
        txtMaXacNhan = new pro1041.team_3.swing.TextField();
        txtEmail = new pro1041.team_3.swing.TextField();
        btnGuiMa = new pro1041.team_3.swing.ButtonCustom();
        btnConfirm = new pro1041.team_3.swing.ButtonCustom();
        txtMaXacNhanError = new javax.swing.JLabel();
        txtEmailError = new javax.swing.JLabel();
        jpnChange = new javax.swing.JPanel();
        btnChange = new pro1041.team_3.swing.ButtonCustom();
        txtConfirmPassError = new javax.swing.JLabel();
        txtNewPassError = new javax.swing.JLabel();
        txtConfirmPass = new pro1041.team_3.swing.TextField();
        txtNewPass = new pro1041.team_3.swing.TextField();
        mainPanel = new javax.swing.JLayeredPane();

        dlTimHoaDon.setPreferredSize(new java.awt.Dimension(1095, 590));
        dlTimHoaDon.setSize(new java.awt.Dimension(1095, 590));
        dlTimHoaDon.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.MatteBorder(null), "Chi tiết hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Nunito Light", 1, 14))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTongTien.setEditable(false);
        txtTongTien.setFont(new java.awt.Font("Nunito SemiBold", 0, 12)); // NOI18N
        txtTongTien.setLabelColor(new java.awt.Color(1, 132, 203));
        txtTongTien.setLabelText("Tổng tiền");
        jPanel1.add(txtTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 150, 180, -1));

        txtMaHoaDon.setEditable(false);
        txtMaHoaDon.setFont(new java.awt.Font("Nunito SemiBold", 0, 12)); // NOI18N
        txtMaHoaDon.setLabelColor(new java.awt.Color(1, 132, 203));
        txtMaHoaDon.setLabelText("Mã hóa đơn");
        jPanel1.add(txtMaHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 210, -1));

        txtMaKhachHang.setEditable(false);
        txtMaKhachHang.setFont(new java.awt.Font("Nunito SemiBold", 0, 12)); // NOI18N
        txtMaKhachHang.setLabelColor(new java.awt.Color(1, 132, 203));
        txtMaKhachHang.setLabelText("Mã khách hàng");
        jPanel1.add(txtMaKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 90, 180, -1));

        txtTenKhachHang.setEditable(false);
        txtTenKhachHang.setFont(new java.awt.Font("Nunito SemiBold", 0, 12)); // NOI18N
        txtTenKhachHang.setLabelColor(new java.awt.Color(1, 132, 203));
        txtTenKhachHang.setLabelText("Tên khách hàng");
        jPanel1.add(txtTenKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 90, 180, -1));

        txtSdtKhachHang.setEditable(false);
        txtSdtKhachHang.setFont(new java.awt.Font("Nunito SemiBold", 0, 12)); // NOI18N
        txtSdtKhachHang.setLabelColor(new java.awt.Color(1, 132, 203));
        txtSdtKhachHang.setLabelText("SĐT khách hàng");
        jPanel1.add(txtSdtKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 90, 180, -1));

        txtMaNhanVien.setEditable(false);
        txtMaNhanVien.setFont(new java.awt.Font("Nunito SemiBold", 0, 12)); // NOI18N
        txtMaNhanVien.setLabelColor(new java.awt.Color(1, 132, 203));
        txtMaNhanVien.setLabelText("Mã nhân viên");
        jPanel1.add(txtMaNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, 180, -1));

        txtNgayThanhToan.setEditable(false);
        txtNgayThanhToan.setFont(new java.awt.Font("Nunito SemiBold", 0, 12)); // NOI18N
        txtNgayThanhToan.setLabelColor(new java.awt.Color(1, 132, 203));
        txtNgayThanhToan.setLabelText("Ngày thanh toán");
        jPanel1.add(txtNgayThanhToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 90, 180, -1));

        txtHinhThucThanhToan.setEditable(false);
        txtHinhThucThanhToan.setFont(new java.awt.Font("Nunito SemiBold", 0, 12)); // NOI18N
        txtHinhThucThanhToan.setLabelColor(new java.awt.Color(1, 132, 203));
        txtHinhThucThanhToan.setLabelText("Hình thức thanh toán");
        jPanel1.add(txtHinhThucThanhToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 210, -1));

        txtNganHang.setEditable(false);
        txtNganHang.setFont(new java.awt.Font("Nunito SemiBold", 0, 12)); // NOI18N
        txtNganHang.setLabelColor(new java.awt.Color(1, 132, 203));
        txtNganHang.setLabelText("Ngân hàng");
        jPanel1.add(txtNganHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 150, 180, -1));

        txtMaGiaoDich.setEditable(false);
        txtMaGiaoDich.setFont(new java.awt.Font("Nunito SemiBold", 0, 12)); // NOI18N
        txtMaGiaoDich.setLabelColor(new java.awt.Color(1, 132, 203));
        txtMaGiaoDich.setLabelText("Mã giao dịch");
        jPanel1.add(txtMaGiaoDich, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 150, 180, -1));

        txtTenNhanVien.setEditable(false);
        txtTenNhanVien.setFont(new java.awt.Font("Nunito SemiBold", 0, 12)); // NOI18N
        txtTenNhanVien.setLabelColor(new java.awt.Color(1, 132, 203));
        txtTenNhanVien.setLabelText("Tên nhân viên");
        jPanel1.add(txtTenNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 30, 180, -1));

        txtTienMat.setEditable(false);
        txtTienMat.setFont(new java.awt.Font("Nunito SemiBold", 0, 12)); // NOI18N
        txtTienMat.setLabelColor(new java.awt.Color(1, 132, 203));
        txtTienMat.setLabelText("Tiền mặt");
        jPanel1.add(txtTienMat, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 150, 180, -1));

        txtTimKiemHoaDon.setFont(new java.awt.Font("Nunito SemiBold", 0, 12)); // NOI18N
        txtTimKiemHoaDon.setLabelColor(new java.awt.Color(1, 132, 203));
        txtTimKiemHoaDon.setLabelText("Tìm kiếm");
        txtTimKiemHoaDon.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemHoaDonCaretUpdate(evt);
            }
        });
        txtTimKiemHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemHoaDonActionPerformed(evt);
            }
        });
        jPanel1.add(txtTimKiemHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 210, -1));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 1010, 210));

        tbHoaDonChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "IMEI", "Hãng", "Màu sắc", "Bộ nhớ", "Tình trạng", "Đơn giá", "Giá bán"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbHoaDonChiTiet.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        tbHoaDonChiTiet.getTableHeader().setReorderingAllowed(false);
        jspTbHoaDonChiTiet.setViewportView(tbHoaDonChiTiet);

        jPanel2.add(jspTbHoaDonChiTiet, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 1060, 310));

        dlTimHoaDon.getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 550));

        dlDoiMatKhau.setPreferredSize(new java.awt.Dimension(270, 285));
        dlDoiMatKhau.setSize(new java.awt.Dimension(270, 285));
        dlDoiMatKhau.getContentPane().setLayout(new java.awt.CardLayout());

        jpnConfirm.setBackground(new java.awt.Color(255, 255, 255));
        jpnConfirm.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtMaXacNhan.setFont(new java.awt.Font("Nunito", 0, 14)); // NOI18N
        txtMaXacNhan.setLabelColor(new java.awt.Color(1, 132, 203));
        txtMaXacNhan.setLabelText("Mã xác nhận");
        jpnConfirm.add(txtMaXacNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 220, -1));

        txtEmail.setFont(new java.awt.Font("Nunito", 0, 14)); // NOI18N
        txtEmail.setLabelColor(new java.awt.Color(1, 132, 203));
        txtEmail.setLabelText("Email");
        jpnConfirm.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 220, -1));

        btnGuiMa.setBackground(new java.awt.Color(1, 181, 204));
        btnGuiMa.setForeground(new java.awt.Color(255, 255, 255));
        btnGuiMa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/reload.png"))); // NOI18N
        btnGuiMa.setText("Gửi mã");
        btnGuiMa.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        btnGuiMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuiMaActionPerformed(evt);
            }
        });
        jpnConfirm.add(btnGuiMa, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));

        btnConfirm.setBackground(new java.awt.Color(1, 181, 204));
        btnConfirm.setForeground(new java.awt.Color(255, 255, 255));
        btnConfirm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/check.png"))); // NOI18N
        btnConfirm.setText("Xác nhận");
        btnConfirm.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });
        jpnConfirm.add(btnConfirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, -1, -1));

        txtMaXacNhanError.setFont(new java.awt.Font("Nunito", 2, 12)); // NOI18N
        txtMaXacNhanError.setForeground(new java.awt.Color(255, 51, 51));
        txtMaXacNhanError.setText("Warning");
        jpnConfirm.add(txtMaXacNhanError, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 220, -1));

        txtEmailError.setFont(new java.awt.Font("Nunito", 2, 12)); // NOI18N
        txtEmailError.setForeground(new java.awt.Color(255, 51, 51));
        txtEmailError.setText("Warning");
        jpnConfirm.add(txtEmailError, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 220, -1));

        dlDoiMatKhau.getContentPane().add(jpnConfirm, "card2");

        jpnChange.setBackground(new java.awt.Color(255, 255, 255));
        jpnChange.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnChange.setBackground(new java.awt.Color(1, 181, 204));
        btnChange.setForeground(new java.awt.Color(255, 255, 255));
        btnChange.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/changePass.png"))); // NOI18N
        btnChange.setText("Đổi mật khẩu");
        btnChange.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        btnChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeActionPerformed(evt);
            }
        });
        jpnChange.add(btnChange, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, -1, -1));

        txtConfirmPassError.setFont(new java.awt.Font("Nunito", 2, 12)); // NOI18N
        txtConfirmPassError.setForeground(new java.awt.Color(255, 51, 0));
        txtConfirmPassError.setText("Warning");
        jpnChange.add(txtConfirmPassError, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 220, -1));

        txtNewPassError.setFont(new java.awt.Font("Nunito", 2, 12)); // NOI18N
        txtNewPassError.setForeground(new java.awt.Color(255, 51, 0));
        txtNewPassError.setText("Warning");
        jpnChange.add(txtNewPassError, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 220, -1));

        txtConfirmPass.setFont(new java.awt.Font("Nunito", 0, 14)); // NOI18N
        txtConfirmPass.setLabelColor(new java.awt.Color(1, 132, 203));
        txtConfirmPass.setLabelText("Nhập lại mật khẩu mới");
        jpnChange.add(txtConfirmPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 220, -1));

        txtNewPass.setFont(new java.awt.Font("Nunito", 0, 14)); // NOI18N
        txtNewPass.setLabelColor(new java.awt.Color(1, 132, 203));
        txtNewPass.setLabelText("Mật khẩu mới");
        jpnChange.add(txtNewPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 220, -1));

        dlDoiMatKhau.getContentPane().add(jpnChange, "card3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setBackground(new java.awt.Color(250, 250, 250));
        mainPanel.setOpaque(true);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1150, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKiemHoaDonCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemHoaDonCaretUpdate

    }//GEN-LAST:event_txtTimKiemHoaDonCaretUpdate

    private void txtTimKiemHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemHoaDonActionPerformed
        // TXT Tìm kiếm hóa đơn submit
        String keyword = txtTimKiemHoaDon.getText().trim();
        if (keyword.isEmpty()) {
            return;
        }
        HoaDonDto hoaDon = hoaDonService.findHoaDonForNhanVien(keyword);
        if (hoaDon == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn");
            return;
        }
        List<HoaDonChiTietDto> lstHdct = hoaDonChiTietService.getAllByIdHoaDon(hoaDon.getId());
        if (lstHdct.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn");
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("kk:mm dd/MM/yy");
        DecimalFormat money = new DecimalFormat("#,###");
        int count = 1;
        DefaultTableModel model = (DefaultTableModel) tbHoaDonChiTiet.getModel();
        for (HoaDonChiTietDto x : lstHdct) {
            model.addRow(new Object[]{
                count,
                x.getMa(),
                x.getTen(),
                x.getImei(),
                x.getHang(),
                x.getMauSac(),
                x.getBoNho(),
                x.getTinhTrang(),
                money.format(x.getDonGia()) + "VNĐ",
                money.format(x.getGiaBan()) + "VNĐ"
            });
            count += 1;
        }
        txtMaHoaDon.setText(hoaDon.getMaHoaDon());
        txtMaKhachHang.setText(hoaDon.getMaKhachHang() == null ? "-" : hoaDon.getMaKhachHang());
        txtTenKhachHang.setText(hoaDon.getTenKhachHang() == null ? "-" : hoaDon.getTenKhachHang());
        txtSdtKhachHang.setText(hoaDon.getSdtKhachHang() == null ? "-" : hoaDon.getSdtKhachHang());
        txtMaNhanVien.setText(hoaDon.getMaNhanVien() == null ? "" : hoaDon.getMaNhanVien());
        txtTenNhanVien.setText(hoaDon.getTenNhanVien() == null ? "" : hoaDon.getTenNhanVien());
        txtNgayThanhToan.setText(sdf.format(hoaDon.getNgayThanhToan()));
        Integer httt = hoaDon.getHinhThucThanhToan();
        txtHinhThucThanhToan.setText(httt == 0 ? "Tiền mặt" : httt == 1 ? "Ngân hàng" : httt == 2 ? "Kết hợp" : "");
        txtTienMat.setText(hoaDon.getTienMat() == null ? "-" : money.format(hoaDon.getTienMat()) + "VNĐ");
        txtNganHang.setText(hoaDon.getNganHang() == null ? "-" : money.format(hoaDon.getNganHang()) + "VNĐ");
        txtMaGiaoDich.setText(hoaDon.getMaGiaoDich() == null ? "-" : hoaDon.getMaGiaoDich());
        txtTongTien.setText(money.format(hoaDon.getTongTien()) + "VNĐ");
    }//GEN-LAST:event_txtTimKiemHoaDonActionPerformed

    private void btnGuiMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuiMaActionPerformed
        // BTN Gửi mã
        String inputEmail = txtEmail.getText().trim();
        if (inputEmail.isEmpty()) {
            txtEmailError.setText("Vui lòng nhập email");
            txtEmailError.setVisible(true);
            return;
        } else if (!inputEmail.matches("^\\S+@\\S+$")) {
            txtEmailError.setText("Email không đúng định dạng");
            txtEmailError.setVisible(true);
            return;
        } else if (!inputEmail.equals(userHienTai.getEmail())) {
            txtEmailError.setText("Email không đúng");
            txtEmailError.setVisible(true);
            return;
        } else {
            txtEmailError.setVisible(false);
        }
        maXacNhan = (int) (Math.random() * 100000000 + 1);
        EmailUtil.send(userHienTai.getEmail(), "[WAIKIKI] Mã xác nhận đổi mật khẩu",
                "Mã xác nhận đổi mật khẩu của bạn là: " + maXacNhan);
    }//GEN-LAST:event_btnGuiMaActionPerformed

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        // BTN Confirm
        String inputEmail = txtEmail.getText().trim();
        if (inputEmail.isEmpty()) {
            txtEmailError.setText("Vui lòng nhập email");
            txtEmailError.setVisible(true);
            return;
        } else if (!inputEmail.matches("^\\S+@\\S+$")) {
            txtEmailError.setText("Email không đúng định dạng");
            txtEmailError.setVisible(true);
            return;
        } else {
            txtEmailError.setVisible(false);
        }
        String inputMa = txtMaXacNhan.getText().trim();
        if (inputMa.isEmpty()) {
            txtMaXacNhanError.setText("Vui lòng nhập mã xác nhận gồm 8 số");
            txtMaXacNhanError.setVisible(true);
            return;
        }
        if (!inputMa.matches("\\d+")) {
            txtMaXacNhanError.setText("Mã xác nhận là số gồm 8 chữ số");
            txtMaXacNhanError.setVisible(true);
            return;
        } else {
            txtMaXacNhanError.setVisible(false);
        }
        if (Integer.parseInt(inputMa) != maXacNhan) {
            txtMaXacNhanError.setText("Mã xác nhận không đúng");
            txtMaXacNhanError.setVisible(true);
            return;
        }
        jpnChange.setVisible(true);
        jpnConfirm.setVisible(false);
        txtConfirmPassError.setVisible(false);
        txtNewPassError.setVisible(false);
    }//GEN-LAST:event_btnConfirmActionPerformed

    private void btnChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeActionPerformed
        // BTN Change
        String newPass = txtNewPass.getText().trim();
        String confirm = txtConfirmPass.getText().trim();
        if (newPass.isEmpty()) {
            txtNewPassError.setText("Vui lòng nhập mật khẩu mới");
            txtNewPassError.setVisible(true);
            return;
        } else {
            txtNewPassError.setVisible(false);
        }
        if (confirm.isEmpty()) {
            txtConfirmPassError.setVisible(true);
            txtConfirmPassError.setText("Vui lòng nhập lại mật khẩu mới");
            return;
        } else if (!confirm.equals(newPass)) {
            txtConfirmPassError.setVisible(true);
            txtConfirmPassError.setText("Mật khẩu không khớp");
            return;
        } else {
            txtConfirmPassError.setVisible(false);
        }
        if (nhanVienService.updateMatKhau(userHienTai.getId(), newPass)) {
            JOptionPane.showMessageDialog(this, "Đổi mật khẩu thành công. \nVui lòng đăng nhập lại!");
            ViewDangNhap dangNhap = new ViewDangNhap();
            dangNhap.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnChangeActionPerformed

    public static void main(String args[]) {
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

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new Main(null).setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private pro1041.team_3.swing.ButtonCustom btnChange;
    private pro1041.team_3.swing.ButtonCustom btnConfirm;
    private pro1041.team_3.swing.ButtonCustom btnGuiMa;
    private javax.swing.JDialog dlDoiMatKhau;
    private javax.swing.JDialog dlTimHoaDon;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jpnChange;
    private javax.swing.JPanel jpnConfirm;
    private javax.swing.JScrollPane jspTbHoaDonChiTiet;
    private javax.swing.JLayeredPane mainPanel;
    private pro1041.team_3.swing.config.Table tbHoaDonChiTiet;
    private pro1041.team_3.swing.TextField txtConfirmPass;
    private javax.swing.JLabel txtConfirmPassError;
    private pro1041.team_3.swing.TextField txtEmail;
    private javax.swing.JLabel txtEmailError;
    private pro1041.team_3.swing.TextField txtHinhThucThanhToan;
    private pro1041.team_3.swing.TextField txtMaGiaoDich;
    private pro1041.team_3.swing.TextField txtMaHoaDon;
    private pro1041.team_3.swing.TextField txtMaKhachHang;
    private pro1041.team_3.swing.TextField txtMaNhanVien;
    private pro1041.team_3.swing.TextField txtMaXacNhan;
    private javax.swing.JLabel txtMaXacNhanError;
    private pro1041.team_3.swing.TextField txtNewPass;
    private javax.swing.JLabel txtNewPassError;
    private pro1041.team_3.swing.TextField txtNganHang;
    private pro1041.team_3.swing.TextField txtNgayThanhToan;
    private pro1041.team_3.swing.TextField txtSdtKhachHang;
    private pro1041.team_3.swing.TextField txtTenKhachHang;
    private pro1041.team_3.swing.TextField txtTenNhanVien;
    private pro1041.team_3.swing.TextField txtTienMat;
    private pro1041.team_3.swing.TextField txtTimKiemHoaDon;
    private pro1041.team_3.swing.TextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
