package pro1041.team_3.form;

import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import pro1041.team_3.domainModel.ChiTietDienThoai;
import pro1041.team_3.domainModel.DienThoai;
import pro1041.team_3.domainModel.DienThoaiKhuyenMai;
import pro1041.team_3.domainModel.Hang;
import pro1041.team_3.domainModel.KhuyenMai;
import pro1041.team_3.domainModel.MauSac;
import pro1041.team_3.dto.ChiTietDienThoaiResponse;
import pro1041.team_3.dto.DienThoaiKhuyenMaiDto;
import pro1041.team_3.service.impl.ChiTietDienThoaiImpl;
import pro1041.team_3.service.impl.DienThoaiKhuyenMaiServiceImpl;
import pro1041.team_3.service.impl.DienThoaiServiceImpl;
import pro1041.team_3.service.impl.HangServiceImpl;
import pro1041.team_3.service.impl.KhuyenMaiServiceImpl;
import pro1041.team_3.service.impl.MauSacServiceImpl;

/**
 *
 * @author trangdttph27721
 */
public class ChiTietSanPhamForm extends javax.swing.JPanel {

    private DienThoaiServiceImpl dienThoaiImpl;
    private HangServiceImpl hangImpl;
    private MauSacServiceImpl mauSacImpl;
    private ChiTietDienThoaiImpl chiTietDTImpl;
    private DienThoaiKhuyenMaiServiceImpl dienThoaiKhuyenMaiServiceImpl;
    private KhuyenMaiServiceImpl khuyenMaiServiceImpl;
    private List<ChiTietDienThoaiResponse> list = new ArrayList<>();
    private String _hinhAnh = null;

    public ChiTietSanPhamForm() {
        initComponents();
        this.txtMa.setEditable(false);
        this.dienThoaiImpl = new DienThoaiServiceImpl();
        this.hangImpl = new HangServiceImpl();
        this.mauSacImpl = new MauSacServiceImpl();
        this.chiTietDTImpl = new ChiTietDienThoaiImpl();
        this.dienThoaiKhuyenMaiServiceImpl = new DienThoaiKhuyenMaiServiceImpl();
        this.khuyenMaiServiceImpl = new KhuyenMaiServiceImpl();
        this.list = this.chiTietDTImpl.getAllResponse();
        this.loadCbbDienThoai();
        this.loadCbbHang();
        this.loadCbbMauSac();
        this.loadTable(list);
    }

    public void loadCbbDienThoai() {
        List<DienThoai> lst = this.dienThoaiImpl.getAll();
        for (DienThoai x : lst) {
            cbbDienThoai.addItem(x);
        }
    }

    public void loadCbbHang() {
        List<Hang> lst = this.hangImpl.getAll();
        for (Hang x : lst) {
            cbbHang.addItem(x);
        }
    }

    public void loadCbbMauSac() {
        List<MauSac> lst = this.mauSacImpl.getAll();
        for (MauSac x : lst) {
            cbbMauSac.addItem(x);
        }
    }

    public void loadTable(List<ChiTietDienThoaiResponse> lst) {
        DefaultTableModel model = (DefaultTableModel) this.tblChiTietDienThoai.getModel();
        model.setRowCount(0);
        for (ChiTietDienThoaiResponse x : lst) {
            model.addRow(x.toDataRow());
        }
    }

    public void clearForm() {
        this.txtRom.setText("");
        this.txtGiaBan.setText("");
        this.txtImei.setText("");
        this.txtMa.setText("");
        this.txtMoTa.setText("");
        this.txtBaoHanh.setText("");
        this.txtRam.setText("");
        this.cbbDienThoai.setSelectedIndex(0);
        this.cbbHang.setSelectedIndex(0);
        this.cbbMauSac.setSelectedIndex(0);
        this.rdoCon.setSelected(true);
        this.rdoMoi.setSelected(true);
        _hinhAnh = null;
    }

    public ChiTietDienThoai getFormData() {
        LocalDateTime time = LocalDateTime.now();
        String ma = "CTDT" + time.getSecond() + time.getMinute() + time.getHour();

        String giaBanStr = this.txtGiaBan.getText().trim();
        String imei = this.txtImei.getText().trim();
        String ramStr = this.txtRam.getText().trim();
        String boNhoStr = this.txtRom.getText().trim();
        String mota = this.txtMoTa.getText().trim();
        String baoHanhStr = this.txtBaoHanh.getText().trim();
        int trangThai = this.rdoCon.isSelected() == true ? 0 : this.rdoHet.isSelected() == true ? 1 : 2;
        int tinhTrang = this.rdoMoi.isSelected() == true ? 1 : 0;
        DienThoai dienThoai = (DienThoai) this.cbbDienThoai.getSelectedItem();
        Hang hang = (Hang) this.cbbHang.getSelectedItem();
        MauSac mauSac = (MauSac) this.cbbMauSac.getSelectedItem();

        if (giaBanStr.length() == 0 || imei.length() == 0 || ramStr.length() == 0 || boNhoStr.length() == 0) {
            JOptionPane.showMessageDialog(this, "Không được để trống");
            return null;
        }
        double giaBan = -1;
        float boNho = -1;
        float ram = -1;
        int sl = -1;
        int baoHanh = -1;
        try {
            giaBan = Double.parseDouble(giaBanStr);
            if (giaBan <= 0) {
                JOptionPane.showMessageDialog(this, "Giá bán không được nhỏ hơn 0");
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Giá bán phải là số");
            e.printStackTrace();
            return null;
        }

        try {
            boNho = Float.parseFloat(boNhoStr);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Bộ nhớ trong - ROM phải là số");
            e.printStackTrace();
            return null;
        }

        try {
            ram = Float.parseFloat(ramStr);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Bộ nhớ ngoài - RAM phải là số");
            e.printStackTrace();
            return null;
        }

        try {
            baoHanh = Integer.parseInt(baoHanhStr);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Thời gian bảo hành phải là số");
            e.printStackTrace();
            return null;
        }

        ChiTietDienThoai chiTietDienThoai = new ChiTietDienThoai();
        chiTietDienThoai.setBoNho(boNho);
        chiTietDienThoai.setDienThoai(dienThoai);
        chiTietDienThoai.setGiaBan(BigDecimal.valueOf(giaBan));
        chiTietDienThoai.setHang(hang);
        chiTietDienThoai.setImei(imei);
        chiTietDienThoai.setMauSac(mauSac);
        chiTietDienThoai.setMoTa(mota == null ? " " : mota);
        chiTietDienThoai.setRam(ram);
        chiTietDienThoai.setTinhTrang(tinhTrang);
        chiTietDienThoai.setTrangThai(trangThai);
        chiTietDienThoai.setThoiGianBaoHanh(baoHanh);
        return chiTietDienThoai;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtTim = new javax.swing.JTextField();
        cbbDienThoai = new javax.swing.JComboBox<DienThoai>();
        jLabel21 = new javax.swing.JLabel();
        cbbMauSac = new javax.swing.JComboBox<MauSac>();
        btnDienThoai = new javax.swing.JButton();
        btnHang = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        rdoMoi = new javax.swing.JRadioButton();
        rdoCu = new javax.swing.JRadioButton();
        rdoLoi = new javax.swing.JRadioButton();
        jLabel19 = new javax.swing.JLabel();
        rdoCon = new javax.swing.JRadioButton();
        rdoHet = new javax.swing.JRadioButton();
        cbbHang = new javax.swing.JComboBox<Hang>();
        btnMauSac = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtGiaBan = new javax.swing.JTextField();
        txtMa = new javax.swing.JTextField();
        txtImei = new javax.swing.JTextField();
        txtRom = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtBaoHanh = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtRam = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtMoTa = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cbbTrangThai = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblChiTietDienThoai = new pro1041.team_3.swing.Table();
        jPanel3 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();

        setBackground(new java.awt.Color(250, 255, 255));
        setPreferredSize(new java.awt.Dimension(1294, 709));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(252, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiết điện thoại", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(1295, 709));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel4.setText("Lọc trạng thái:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 30, -1, -1));

        txtTim.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtTim.setToolTipText("");
        txtTim.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(90, 183, 210)));
        txtTim.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimCaretUpdate(evt);
            }
        });
        jPanel1.add(txtTim, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 300, 30));

        cbbDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 13));
        cbbDienThoai.setModel(new javax.swing.DefaultComboBoxModel<DienThoai>());
        cbbDienThoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbDienThoaiActionPerformed(evt);
            }
        });
        jPanel1.add(cbbDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 120, 190, 30));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel21.setText("Màu sắc");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 230, -1, -1));

        cbbMauSac.setFont(new java.awt.Font("Segoe UI", 0, 13));
        cbbMauSac.setModel(new javax.swing.DefaultComboBoxModel<MauSac>());
        jPanel1.add(cbbMauSac, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 220, 190, 30));

        btnDienThoai.setBackground(new java.awt.Color(0, 255, 255));
        btnDienThoai.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnDienThoai.setText("Edit");
        jPanel1.add(btnDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 170, 70, -1));

        btnHang.setBackground(new java.awt.Color(0, 255, 255));
        btnHang.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnHang.setText("Edit");
        jPanel1.add(btnHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 120, 70, 30));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel17.setText("Trạng thái");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 330, -1, -1));

        buttonGroup1.add(rdoMoi);
        rdoMoi.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        rdoMoi.setText("Mới");
        jPanel1.add(rdoMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 280, -1, -1));

        buttonGroup1.add(rdoCu);
        rdoCu.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        rdoCu.setText("Cũ");
        jPanel1.add(rdoCu, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 280, -1, -1));

        buttonGroup2.add(rdoLoi);
        rdoLoi.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        rdoLoi.setText("Sản phẩm lỗi");
        jPanel1.add(rdoLoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 320, -1, -1));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel19.setText("Tình trạng");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 280, -1, -1));

        buttonGroup2.add(rdoCon);
        rdoCon.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        rdoCon.setText("Đang bán");
        rdoCon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoConActionPerformed(evt);
            }
        });
        jPanel1.add(rdoCon, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 320, -1, -1));

        buttonGroup2.add(rdoHet);
        rdoHet.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        rdoHet.setText("Đã bán");
        jPanel1.add(rdoHet, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 320, -1, -1));

        cbbHang.setFont(new java.awt.Font("Segoe UI", 0, 13));
        cbbHang.setModel(new javax.swing.DefaultComboBoxModel<Hang>());
        cbbHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbHangActionPerformed(evt);
            }
        });
        jPanel1.add(cbbHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 170, 190, 30));

        btnMauSac.setBackground(new java.awt.Color(0, 255, 255));
        btnMauSac.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnMauSac.setText("Edit");
        jPanel1.add(btnMauSac, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 220, 70, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel13.setText("Hãng điện thoại");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 180, -1, 20));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel18.setText("Điện thoại");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 130, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel8.setText("VND");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 80, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Mã CT điện thoại");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Giá bán");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 70, -1, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setText("RAM");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, -1, -1));

        txtGiaBan.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        txtGiaBan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(90, 183, 210)));
        jPanel1.add(txtGiaBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 70, 190, 30));

        txtMa.setBackground(new java.awt.Color(243, 243, 243));
        txtMa.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        txtMa.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(56, 183, 210)));
        jPanel1.add(txtMa, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 230, 30));

        txtImei.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        txtImei.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(90, 183, 210)));
        jPanel1.add(txtImei, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 230, 30));

        txtRom.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        txtRom.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(90, 183, 210)));
        jPanel1.add(txtRom, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, 230, 30));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Imei");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("ROM");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, -1));

        txtBaoHanh.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        txtBaoHanh.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(90, 183, 210)));
        jPanel1.add(txtBaoHanh, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 320, 230, 30));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("Bảo hành");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, -1, -1));

        txtRam.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        txtRam.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(90, 183, 210)));
        jPanel1.add(txtRam, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, 230, 30));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setText("Mô tả");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, -1, -1));

        txtMoTa.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        txtMoTa.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(90, 183, 210)));
        jPanel1.add(txtMoTa, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 270, 230, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel5.setText("Tìm kiếm");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, -1, 20));

        cbbTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Đang bán", "Đã bán", "Sản phẩm lỗi" }));
        cbbTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTrangThaiActionPerformed(evt);
            }
        });
        jPanel1.add(cbbTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 22, 170, 30));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 1000, 360));

        tblChiTietDienThoai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã ", "Điện thoại", "Hãng", "Giá bán", "Màu sắc", "Imei", "Ram", "Rom", "Tình trạng", "Trạng thái", "Mô tả", "Bảo hành"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblChiTietDienThoai.setFocusTraversalPolicyProvider(true);
        tblChiTietDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tblChiTietDienThoai.setInheritsPopupMenu(true);
        tblChiTietDienThoai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChiTietDienThoaiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblChiTietDienThoai);
        if (tblChiTietDienThoai.getColumnModel().getColumnCount() > 0) {
            tblChiTietDienThoai.getColumnModel().getColumn(0).setPreferredWidth(45);
            tblChiTietDienThoai.getColumnModel().getColumn(1).setPreferredWidth(65);
            tblChiTietDienThoai.getColumnModel().getColumn(2).setPreferredWidth(50);
            tblChiTietDienThoai.getColumnModel().getColumn(3).setPreferredWidth(55);
            tblChiTietDienThoai.getColumnModel().getColumn(4).setPreferredWidth(55);
            tblChiTietDienThoai.getColumnModel().getColumn(6).setPreferredWidth(45);
            tblChiTietDienThoai.getColumnModel().getColumn(7).setPreferredWidth(45);
            tblChiTietDienThoai.getColumnModel().getColumn(8).setPreferredWidth(45);
            tblChiTietDienThoai.getColumnModel().getColumn(9).setPreferredWidth(50);
            tblChiTietDienThoai.getColumnModel().getColumn(11).setPreferredWidth(35);
        }

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 1240, 310));

        jPanel3.setBackground(new java.awt.Color(252, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 153, 153)));
        jPanel3.setForeground(new java.awt.Color(153, 153, 153));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnThem.setBackground(new java.awt.Color(0, 255, 255));
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThem.setText("Lưu");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel3.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 100, 40));

        btnSua.setBackground(new java.awt.Color(0, 255, 255));
        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        jPanel3.add(btnSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 100, 40));

        btnXoa.setBackground(new java.awt.Color(0, 255, 255));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoa.setText("Export");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel3.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 100, 40));

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 20, 170, 300));
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimCaretUpdate
        // TODO add your handling code here:
        try {
            String text = this.txtTim.getText().trim();
            list = this.chiTietDTImpl.findBy(text);

            this.loadTable(list);
            this.txtTim.setText("");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }//GEN-LAST:event_txtTimCaretUpdate

    private void cbbDienThoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbDienThoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbDienThoaiActionPerformed

    private void rdoConActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoConActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoConActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        ChiTietDienThoai chiTietDienThoai = this.getFormData();
        if (chiTietDienThoai == null) {
            return;
        }

        this.chiTietDTImpl.insert(chiTietDienThoai);
        this.loadTable(this.chiTietDTImpl.getAllResponse());
        this.clearForm();
        JOptionPane.showMessageDialog(this, "Thêm thành công");
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        try {
            int index = tblChiTietDienThoai.getSelectedRow();
            if (index == -1) {
                JOptionPane.showMessageDialog(this, "Bạn cần chọn một sản phẩm trên danh sách");
                return;
            }
            String giaCuStr = this.tblChiTietDienThoai.getValueAt(index, 3).toString();;
            double giaCu = Double.parseDouble(giaCuStr);
            ChiTietDienThoai ctDT = this.getFormData();
            ctDT.setId(list.get(index).getId());
            ctDT.setMa(this.txtMa.getText().trim());
            if (ctDT == null) {
                return;
            }

            if (giaCu != ctDT.getGiaBan().doubleValue()) {
                List<DienThoaiKhuyenMaiDto> lstDTKMKetThuc = this.dienThoaiKhuyenMaiServiceImpl.findDTKhuyenMaiKetThuc();
                for (DienThoaiKhuyenMaiDto x : lstDTKMKetThuc) {
                    if (list.get(index).getId().equals(x.getIdChiTietDienThoai())) {
                        JOptionPane.showMessageDialog(this, "Điện thoại có trong danh sách khuyến mại kết thúc. Không thể sửa giá");
                        return;
                    }
                }
                List<DienThoaiKhuyenMaiDto> lstDTKMDangVaTgLai = this.dienThoaiKhuyenMaiServiceImpl.findDTKhuyenMaiDienRavaTgLai(list.get(index).getId());
                if (lstDTKMDangVaTgLai.size() != 0) {
                    // Cập nhật giá bán
                    this.chiTietDTImpl.update(ctDT);
                    // duyệt list 
                    for (DienThoaiKhuyenMaiDto x : lstDTKMDangVaTgLai) {
                        // Tìm điện thoại theo id
                        KhuyenMai khuyenMai = this.khuyenMaiServiceImpl.findById(x.getIdKhuyenMai());

                        BigDecimal giaconLai = null;
                        BigDecimal giaBan = ctDT.getGiaBan();
                        Float giaphanTram = khuyenMai.getGiaTriPhanTram();
                        BigDecimal giaTienMat = khuyenMai.getGiaTriTienMat();
                        if (giaTienMat == null) {
                            giaconLai = giaBan.subtract(giaBan.multiply(BigDecimal.valueOf(giaphanTram / 100)));
                        } else if (giaphanTram == null) {
                            giaconLai = giaBan.subtract(giaTienMat);
                        }

                        DienThoaiKhuyenMai dtkm = new DienThoaiKhuyenMai();
                        dtkm.setChiTietDienThoai(ctDT);
                        dtkm.setKhuyenMai(khuyenMai);
                        dtkm.setDonGia(ctDT.getGiaBan());
                        dtkm.setGiaConLai(giaconLai);

                        this.dienThoaiKhuyenMaiServiceImpl.update(dtkm);

                    }
                    loadTable(this.chiTietDTImpl.getAllResponse());
                    clearForm();
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                    return;
                }
            }

            this.chiTietDTImpl.update(ctDT);
            loadTable(this.chiTietDTImpl.getAllResponse());
            clearForm();

            JOptionPane.showMessageDialog(this, "Cập nhật thành công");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Cập nhật thất bại");
            return;
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        JFileChooser fileChooser = new JFileChooser("D:\\");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("File Exel (.xlsx)", "xlsx");
        fileChooser.setFileFilter(filter);
        int x = fileChooser.showSaveDialog(this);
        FileOutputStream fos = null;
        File file = fileChooser.getSelectedFile();
        if (!(x == JFileChooser.APPROVE_OPTION)) {
            return;
        }
        if (chiTietDTImpl.export(file)) {
            JOptionPane.showMessageDialog(this, "Xuất danh sách thành công", "Export", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Thất bại", "Export", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void tblChiTietDienThoaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietDienThoaiMouseClicked
        // TODO add your handling code here:
        int row = this.tblChiTietDienThoai.getSelectedRow();
        String ma = this.tblChiTietDienThoai.getValueAt(row, 0).toString();
        String dienThoai = this.tblChiTietDienThoai.getValueAt(row, 1).toString();
        String hang = this.tblChiTietDienThoai.getValueAt(row, 2).toString();
        String giaBan = this.tblChiTietDienThoai.getValueAt(row, 3).toString();
        String mauSac = this.tblChiTietDienThoai.getValueAt(row, 4).toString();
        String imei = this.tblChiTietDienThoai.getValueAt(row, 5).toString();
        String ram = this.tblChiTietDienThoai.getValueAt(row, 6).toString();
        String rom = this.tblChiTietDienThoai.getValueAt(row, 7).toString();
        String tinhTrang = this.tblChiTietDienThoai.getValueAt(row, 8).toString();
        String trangThai = this.tblChiTietDienThoai.getValueAt(row, 9).toString();
        String moTa = this.tblChiTietDienThoai.getValueAt(row, 10).toString();
        String baoHanh = this.tblChiTietDienThoai.getValueAt(row, 11).toString();
        this.txtRom.setText(rom);
        this.txtGiaBan.setText(giaBan);
        this.txtImei.setText(imei);
        this.txtMa.setText(ma);
        this.txtMoTa.setText(moTa == null ? "" : moTa);
        this.txtRam.setText(ram);
        this.txtBaoHanh.setText(baoHanh);

        int slDT = this.cbbDienThoai.getItemCount();
        for (int i = 0; i < slDT; i++) {
            DienThoai dt = cbbDienThoai.getItemAt(i);

            if (dt.getTen().equalsIgnoreCase(dienThoai)) {
                cbbDienThoai.setSelectedIndex(i);
            }
        }
        int slHang = this.cbbHang.getItemCount();
        for (int i = 0; i < slHang; i++) {
            Hang hg = cbbHang.getItemAt(i);

            if (hg.getTen().equalsIgnoreCase(hang)) {
                cbbHang.setSelectedIndex(i);
            }
        }
        int slms = this.cbbMauSac.getItemCount();
        for (int i = 0; i < slms; i++) {
            MauSac ms = cbbMauSac.getItemAt(i);

            if (ms.getTen().equalsIgnoreCase(mauSac)) {
                cbbMauSac.setSelectedIndex(i);
            }
        }

        if (trangThai.equals("Đang bán")) {
            this.rdoCon.setSelected(true);
        } else if (trangThai.equals("Đã bán")) {
            this.rdoHet.setSelected(true);
        } else {
            this.rdoLoi.setSelected(true);
        }

        if (tinhTrang.equals("Mới")) {
            this.rdoMoi.setSelected(true);
        } else {
            this.rdoCu.setSelected(true);
        }

    }//GEN-LAST:event_tblChiTietDienThoaiMouseClicked

    private void cbbHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbHangActionPerformed

    private void cbbTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTrangThaiActionPerformed
        // TODO add your handling code here:
        if (this.cbbTrangThai.getSelectedIndex() == 0) {
            this.list = this.chiTietDTImpl.getAllResponse();
            loadTable(list);
        } else if (this.cbbTrangThai.getSelectedIndex() == 1) {
            this.list = this.chiTietDTImpl.getAllTrangThai(0);
            loadTable(list);
        } else if (this.cbbTrangThai.getSelectedIndex() == 2) {
            this.list = this.chiTietDTImpl.getAllTrangThai(1);
            loadTable(list);
        } else if (this.cbbTrangThai.getSelectedIndex() == 3) {
            this.list = this.chiTietDTImpl.getAllTrangThai(2);
            loadTable(list);
        }
    }//GEN-LAST:event_cbbTrangThaiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDienThoai;
    private javax.swing.JButton btnHang;
    private javax.swing.JButton btnMauSac;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<DienThoai> cbbDienThoai;
    private javax.swing.JComboBox<Hang> cbbHang;
    private javax.swing.JComboBox<MauSac> cbbMauSac;
    private javax.swing.JComboBox<String> cbbTrangThai;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdoCon;
    private javax.swing.JRadioButton rdoCu;
    private javax.swing.JRadioButton rdoHet;
    private javax.swing.JRadioButton rdoLoi;
    private javax.swing.JRadioButton rdoMoi;
    private pro1041.team_3.swing.Table tblChiTietDienThoai;
    private javax.swing.JTextField txtBaoHanh;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtImei;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtMoTa;
    private javax.swing.JTextField txtRam;
    private javax.swing.JTextField txtRom;
    private javax.swing.JTextField txtTim;
    // End of variables declaration//GEN-END:variables
}
