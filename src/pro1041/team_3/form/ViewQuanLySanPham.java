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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
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
import pro1041.team_3.swing.Notification;
import pro1041.team_3.swing.jnafilechooser.api.JnaFileChooser;

/**
 *
 * @author trangdttph27721
 */
public class ViewQuanLySanPham extends javax.swing.JPanel {

    private DienThoaiServiceImpl dienThoaiImpl;
    private HangServiceImpl hangImpl;
    private MauSacServiceImpl mauSacImpl;
    private ChiTietDienThoaiImpl chiTietDTImpl;
    private DienThoaiKhuyenMaiServiceImpl dienThoaiKhuyenMaiServiceImpl;
    private KhuyenMaiServiceImpl khuyenMaiServiceImpl;
    private List<ChiTietDienThoaiResponse> list = new ArrayList<>();
    private String _hinhAnh = null;

    public ViewQuanLySanPham() {
        initComponents();
        //Edit scroll table
        tblChiTietDienThoai.fixTable(jspTblChiTietDienThoai);
        this.txtMa.setEditable(false);
        this.dienThoaiImpl = new DienThoaiServiceImpl();
        this.hangImpl = new HangServiceImpl();
        this.mauSacImpl = new MauSacServiceImpl();
        this.chiTietDTImpl = new ChiTietDienThoaiImpl();
        this.dienThoaiKhuyenMaiServiceImpl = new DienThoaiKhuyenMaiServiceImpl();
        this.khuyenMaiServiceImpl = new KhuyenMaiServiceImpl();
        this.list = this.chiTietDTImpl.getAllResponse();
        this.tinhTrangCuPanel.setVisible(false);
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
        int index = 1;
        for (ChiTietDienThoaiResponse x : lst) {
            model.addRow(x.toDataRow(index));
            index++;
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
        this.cbbTinhTrangCu.setSelectedIndex(0);
        this.rdoCon.setSelected(true);
        this.rdoMoi.setSelected(true);
        this.tinhTrangCuPanel.setVisible(false);
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
        BigDecimal giaBan = null;
        Integer boNho = -1;
        Integer ram = -1;
        Integer sl = -1;
        Integer baoHanh = -1;
        Integer tinhTrangPhamTram = -1;
        try {
            giaBan = new BigDecimal(giaBanStr);
            if (giaBan.compareTo(BigDecimal.ZERO) == -1) {
                JOptionPane.showMessageDialog(this, "Giá bán không được nhỏ hơn 0");
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Giá bán phải là số");
            e.printStackTrace();
            return null;
        }

        try {
            boNho = Integer.parseInt(boNhoStr);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Bộ nhớ trong - ROM phải là số");
            e.printStackTrace();
            return null;
        }

        try {
            ram = Integer.parseInt(ramStr);
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

        if (tinhTrang == 1) {
            tinhTrangPhamTram = 100;
        } else {
            String giaTriTinhTrangCu = this.cbbTinhTrangCu.getSelectedItem().toString();
            tinhTrangPhamTram = Integer.parseInt(giaTriTinhTrangCu);
        }

        ChiTietDienThoai chiTietDienThoai = new ChiTietDienThoai();
        chiTietDienThoai.setBoNho(boNho);
        chiTietDienThoai.setDienThoai(dienThoai);
        chiTietDienThoai.setDonGia(giaBan);
        chiTietDienThoai.setHang(hang);
        chiTietDienThoai.setImei(imei);
        chiTietDienThoai.setMauSac(mauSac);
        chiTietDienThoai.setMoTa(mota == null ? null : mota);
        chiTietDienThoai.setRam(ram);
        chiTietDienThoai.setTinhTrang(tinhTrangPhamTram);
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
        jLabel21 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        rdoMoi = new javax.swing.JRadioButton();
        rdoCu = new javax.swing.JRadioButton();
        rdoLoi = new javax.swing.JRadioButton();
        jLabel19 = new javax.swing.JLabel();
        rdoCon = new javax.swing.JRadioButton();
        rdoHet = new javax.swing.JRadioButton();
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
        tinhTrangCuPanel = new javax.swing.JPanel();
        cbbTinhTrangCu = new pro1041.team_3.swing.ComboBoxSuggestion();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        buttonCustom4 = new pro1041.team_3.swing.ButtonCustom();
        buttonCustom5 = new pro1041.team_3.swing.ButtonCustom();
        buttonCustom6 = new pro1041.team_3.swing.ButtonCustom();
        cbbTrangThai = new pro1041.team_3.swing.Combobox();
        cbbDienThoai = new pro1041.team_3.swing.Combobox<DienThoai>();
        cbbHang = new pro1041.team_3.swing.Combobox<Hang>();
        cbbMauSac = new pro1041.team_3.swing.Combobox<MauSac>();
        jspTblChiTietDienThoai = new javax.swing.JScrollPane();
        tblChiTietDienThoai = new pro1041.team_3.swing.config.Table();
        jPanel3 = new javax.swing.JPanel();
        btnThem1 = new pro1041.team_3.swing.ButtonCustom();
        btnThem = new pro1041.team_3.swing.ButtonCustom();
        btnSua = new pro1041.team_3.swing.ButtonCustom();
        btnExport = new pro1041.team_3.swing.ButtonCustom();
        btnImport = new pro1041.team_3.swing.ButtonCustom();
        btnTaoQr = new pro1041.team_3.swing.ButtonCustom();

        setBackground(new java.awt.Color(250, 255, 255));
        setPreferredSize(new java.awt.Dimension(1294, 709));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(252, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiết điện thoại", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(1295, 709));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        jLabel4.setText("Lọc trạng thái:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 40, -1, -1));

        txtTim.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtTim.setToolTipText("");
        txtTim.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(90, 183, 210)));
        txtTim.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimCaretUpdate(evt);
            }
        });
        jPanel1.add(txtTim, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 300, 30));

        jLabel21.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        jLabel21.setText("Màu sắc");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 250, -1, -1));

        jLabel17.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        jLabel17.setText("Trạng thái");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 340, -1, -1));

        buttonGroup1.add(rdoMoi);
        rdoMoi.setFont(new java.awt.Font("Nunito", 1, 13)); // NOI18N
        rdoMoi.setText("Mới");
        rdoMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoMoiActionPerformed(evt);
            }
        });
        jPanel1.add(rdoMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 300, -1, -1));

        buttonGroup1.add(rdoCu);
        rdoCu.setFont(new java.awt.Font("Nunito", 1, 13)); // NOI18N
        rdoCu.setText("Cũ");
        rdoCu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoCuActionPerformed(evt);
            }
        });
        jPanel1.add(rdoCu, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 300, -1, -1));

        buttonGroup2.add(rdoLoi);
        rdoLoi.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        rdoLoi.setText("Sản phẩm lỗi");
        jPanel1.add(rdoLoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 340, -1, -1));

        jLabel19.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        jLabel19.setText("Tình trạng");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 300, -1, -1));

        buttonGroup2.add(rdoCon);
        rdoCon.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        rdoCon.setText("Đang bán");
        rdoCon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoConActionPerformed(evt);
            }
        });
        jPanel1.add(rdoCon, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 340, -1, -1));

        buttonGroup2.add(rdoHet);
        rdoHet.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        rdoHet.setText("Đã bán");
        rdoHet.setEnabled(false);
        jPanel1.add(rdoHet, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 340, -1, -1));

        jLabel13.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        jLabel13.setText("Hãng điện thoại");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 190, -1, 20));

        jLabel18.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        jLabel18.setText("Điện thoại");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 140, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel8.setText("VND");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 80, -1, -1));

        jLabel9.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        jLabel9.setText("Mã CT điện thoại");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, -1));

        jLabel10.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        jLabel10.setText("Giá bán");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 80, -1, -1));

        jLabel15.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        jLabel15.setText("RAM");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, -1, -1));

        txtGiaBan.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        txtGiaBan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(90, 183, 210)));
        jPanel1.add(txtGiaBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 70, 190, 30));

        txtMa.setBackground(new java.awt.Color(243, 243, 243));
        txtMa.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        txtMa.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(56, 183, 210)));
        jPanel1.add(txtMa, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 220, 30));

        txtImei.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        txtImei.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(90, 183, 210)));
        jPanel1.add(txtImei, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 220, 30));

        txtRom.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        txtRom.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(90, 183, 210)));
        jPanel1.add(txtRom, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, 220, 30));

        jLabel11.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        jLabel11.setText("Imei");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, -1));

        jLabel12.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        jLabel12.setText("ROM");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, -1));

        txtBaoHanh.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        txtBaoHanh.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(90, 183, 210)));
        jPanel1.add(txtBaoHanh, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 330, 130, 30));

        jLabel14.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        jLabel14.setText("Bảo hành");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, -1, -1));

        txtRam.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        txtRam.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(90, 183, 210)));
        jPanel1.add(txtRam, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 230, 220, 30));

        jLabel16.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        jLabel16.setText("Mô tả");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, -1, -1));

        txtMoTa.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        txtMoTa.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(90, 183, 210)));
        jPanel1.add(txtMoTa, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 280, 220, 30));

        jLabel5.setFont(new java.awt.Font("Nunito", 1, 13)); // NOI18N
        jLabel5.setText("Tìm kiếm");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, -1, 20));

        tinhTrangCuPanel.setBackground(new java.awt.Color(252, 255, 255));

        cbbTinhTrangCu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99" }));
        tinhTrangCuPanel.add(cbbTinhTrangCu);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("%");
        tinhTrangCuPanel.add(jLabel1);

        jPanel1.add(tinhTrangCuPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 290, 170, 40));

        jLabel2.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        jLabel2.setText("Tháng");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 340, -1, -1));

        buttonCustom4.setBackground(new java.awt.Color(1, 181, 204));
        buttonCustom4.setForeground(new java.awt.Color(255, 255, 255));
        buttonCustom4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/add.png"))); // NOI18N
        buttonCustom4.setText("Edit");
        buttonCustom4.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        jPanel1.add(buttonCustom4, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 110, 80, -1));

        buttonCustom5.setBackground(new java.awt.Color(1, 181, 204));
        buttonCustom5.setForeground(new java.awt.Color(255, 255, 255));
        buttonCustom5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/add.png"))); // NOI18N
        buttonCustom5.setText("Edit");
        buttonCustom5.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        jPanel1.add(buttonCustom5, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 170, 80, 50));

        buttonCustom6.setBackground(new java.awt.Color(1, 181, 204));
        buttonCustom6.setForeground(new java.awt.Color(255, 255, 255));
        buttonCustom6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/add.png"))); // NOI18N
        buttonCustom6.setText("Edit");
        buttonCustom6.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        jPanel1.add(buttonCustom6, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 230, 80, -1));

        cbbTrangThai.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất cả", " Đang bán", " Đã bán", " Sản phẩm lỗi" }));
        cbbTrangThai.setLabeText("");
        cbbTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTrangThaiActionPerformed(evt);
            }
        });
        jPanel1.add(cbbTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 20, 220, 40));

        cbbDienThoai.setLabeText("");
        jPanel1.add(cbbDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 110, 190, 40));

        cbbHang.setLabeText("");
        jPanel1.add(cbbHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 170, 190, -1));

        cbbMauSac.setLabeText("");
        jPanel1.add(cbbMauSac, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 230, 190, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 930, 380));

        tblChiTietDienThoai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT ", "Mã", "Điện thoại", "Hãng", "Giá bán", "Màu sắc", "Imei", "Ram", "Rom", "Tình trạng", "Trạng thái", "Mô tả", "Bảo hành"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblChiTietDienThoai.setFocusTraversalPolicyProvider(true);
        tblChiTietDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tblChiTietDienThoai.setInheritsPopupMenu(true);
        tblChiTietDienThoai.getTableHeader().setReorderingAllowed(false);
        tblChiTietDienThoai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChiTietDienThoaiMouseClicked(evt);
            }
        });
        jspTblChiTietDienThoai.setViewportView(tblChiTietDienThoai);
        if (tblChiTietDienThoai.getColumnModel().getColumnCount() > 0) {
            tblChiTietDienThoai.getColumnModel().getColumn(0).setPreferredWidth(5);
            tblChiTietDienThoai.getColumnModel().getColumn(1).setPreferredWidth(10);
            tblChiTietDienThoai.getColumnModel().getColumn(2).setPreferredWidth(65);
            tblChiTietDienThoai.getColumnModel().getColumn(3).setPreferredWidth(45);
            tblChiTietDienThoai.getColumnModel().getColumn(4).setPreferredWidth(55);
            tblChiTietDienThoai.getColumnModel().getColumn(5).setPreferredWidth(50);
            tblChiTietDienThoai.getColumnModel().getColumn(6).setPreferredWidth(70);
            tblChiTietDienThoai.getColumnModel().getColumn(7).setPreferredWidth(10);
            tblChiTietDienThoai.getColumnModel().getColumn(8).setPreferredWidth(10);
            tblChiTietDienThoai.getColumnModel().getColumn(9).setPreferredWidth(35);
            tblChiTietDienThoai.getColumnModel().getColumn(10).setPreferredWidth(35);
            tblChiTietDienThoai.getColumnModel().getColumn(12).setPreferredWidth(30);
        }

        add(jspTblChiTietDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 1130, 360));

        jPanel3.setBackground(new java.awt.Color(252, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 153, 153)));
        jPanel3.setForeground(new java.awt.Color(153, 153, 153));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnThem1.setBackground(new java.awt.Color(1, 181, 204));
        btnThem1.setForeground(new java.awt.Color(255, 255, 255));
        btnThem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/add.png"))); // NOI18N
        btnThem1.setText("Thêm");
        btnThem1.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        btnThem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem1ActionPerformed(evt);
            }
        });
        jPanel3.add(btnThem1, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 380, 110, 50));

        btnThem.setBackground(new java.awt.Color(1, 181, 204));
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/add.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel3.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 120, -1));

        btnSua.setBackground(new java.awt.Color(1, 181, 204));
        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/edit.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        jPanel3.add(btnSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 120, -1));

        btnExport.setBackground(new java.awt.Color(1, 181, 204));
        btnExport.setForeground(new java.awt.Color(255, 255, 255));
        btnExport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/export.png"))); // NOI18N
        btnExport.setText("Export");
        btnExport.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportActionPerformed(evt);
            }
        });
        jPanel3.add(btnExport, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 120, -1));

        btnImport.setBackground(new java.awt.Color(1, 181, 204));
        btnImport.setForeground(new java.awt.Color(255, 255, 255));
        btnImport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/import.png"))); // NOI18N
        btnImport.setText("Import");
        btnImport.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        btnImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportActionPerformed(evt);
            }
        });
        jPanel3.add(btnImport, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 120, -1));

        btnTaoQr.setBackground(new java.awt.Color(1, 181, 204));
        btnTaoQr.setForeground(new java.awt.Color(255, 255, 255));
        btnTaoQr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/scanQr.png"))); // NOI18N
        btnTaoQr.setText("Tải QR");
        btnTaoQr.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        btnTaoQr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoQrActionPerformed(evt);
            }
        });
        jPanel3.add(btnTaoQr, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 120, -1));

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 20, 160, 350));
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimCaretUpdate
        // TODO add your handling code here:
        try {
            String text = this.txtTim.getText().trim();
            if (text.length() == 0) {
                list = this.chiTietDTImpl.getAllResponse();
                this.loadTable(list);
            } else {
                list = this.chiTietDTImpl.findBy(text);
                this.loadTable(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }//GEN-LAST:event_txtTimCaretUpdate

    private void rdoConActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoConActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoConActionPerformed

    private void tblChiTietDienThoaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietDienThoaiMouseClicked
        // TODO add your handling code here:
        int row = this.tblChiTietDienThoai.getSelectedRow();

//        list = this.chiTietDTImpl.getAllResponse();
        ChiTietDienThoaiResponse ctdtr = this.list.get(row);

//        String ma = this.tblChiTietDienThoai.getValueAt(row, 0).toString();
//        String dienThoai = this.tblChiTietDienThoai.getValueAt(row, 1).toString();
//        String hang = this.tblChiTietDienThoai.getValueAt(row, 2).toString();
//        String giaBan = this.tblChiTietDienThoai.getValueAt(row, 3).toString();
//        String mauSac = this.tblChiTietDienThoai.getValueAt(row, 4).toString();
//        String imei = this.tblChiTietDienThoai.getValueAt(row, 5).toString();
//        String ram = this.tblChiTietDienThoai.getValueAt(row, 6).toString();
//        String rom = this.tblChiTietDienThoai.getValueAt(row, 7).toString();
//        String tinhTrang = this.tblChiTietDienThoai.getValueAt(row, 8).toString();
//        String trangThai = this.tblChiTietDienThoai.getValueAt(row, 9).toString();
//        String moTa = this.tblChiTietDienThoai.getValueAt(row, 10).toString();
//        String baoHanh = this.tblChiTietDienThoai.getValueAt(row, 11).toString();
        this.txtRom.setText(ctdtr.getBoNho() + "");
        this.txtGiaBan.setText(ctdtr.getDonGia() + "");
        this.txtImei.setText(ctdtr.getImei());
        this.txtMa.setText(ctdtr.getMa());
        this.txtMoTa.setText(ctdtr.getMoTa() == null ? "_" : ctdtr.getMoTa());
        this.txtRam.setText(ctdtr.getRam() + "");
        this.txtBaoHanh.setText(ctdtr.getThoiGianBaoHanh() + "");

        int slDT = this.cbbDienThoai.getItemCount();
        for (int i = 0; i < slDT; i++) {
            DienThoai dt = cbbDienThoai.getItemAt(i);

            if (dt.getTen().equalsIgnoreCase(ctdtr.getDienThoai())) {
                cbbDienThoai.setSelectedIndex(i);
            }
        }
        int slHang = this.cbbHang.getItemCount();
        for (int i = 0; i < slHang; i++) {
            Hang hg = cbbHang.getItemAt(i);

            if (hg.getTen().equalsIgnoreCase(ctdtr.getHang())) {
                cbbHang.setSelectedIndex(i);
            }
        }
        int slms = this.cbbMauSac.getItemCount();
        for (int i = 0; i < slms; i++) {
            MauSac ms = cbbMauSac.getItemAt(i);

            if (ms.getTen().equalsIgnoreCase(ctdtr.getMauSac())) {
                cbbMauSac.setSelectedIndex(i);
            }
        }

        if (ctdtr.getTrangThai() == 0) {
            this.rdoCon.setSelected(true);
        } else if (ctdtr.getTrangThai() == 1) {
            this.rdoHet.setSelected(true);
        } else {
            this.rdoLoi.setSelected(true);
        }

        if (ctdtr.getTinhTrang() == 100) {
            this.rdoMoi.setSelected(true);
            this.tinhTrangCuPanel.setVisible(false);
        } else {
            this.rdoCu.setSelected(true);
            this.tinhTrangCuPanel.setVisible(true);
            this.cbbTinhTrangCu.setSelectedItem(ctdtr.getTinhTrang() + "");
        }

    }//GEN-LAST:event_tblChiTietDienThoaiMouseClicked

    private void rdoCuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoCuActionPerformed
        // TODO add your handling code here:
        if (this.rdoCu.isSelected() == true) {
            this.tinhTrangCuPanel.setVisible(true);
        } else if (this.rdoMoi.isSelected() == true) {
            this.tinhTrangCuPanel.setVisible(false);
        }
    }//GEN-LAST:event_rdoCuActionPerformed

    private void rdoMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoMoiActionPerformed
        // TODO add your handling code here:
        if (this.rdoCu.isSelected() == true) {
            this.tinhTrangCuPanel.setVisible(true);
        } else if (this.rdoMoi.isSelected() == true) {
            this.tinhTrangCuPanel.setVisible(false);
        }
    }//GEN-LAST:event_rdoMoiActionPerformed

    private void btnThem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnThem1ActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        ChiTietDienThoai chiTietDienThoai = this.getFormData();
        if (chiTietDienThoai == null) {
            return;
        }

        this.chiTietDTImpl.insert(chiTietDienThoai);
        list = this.chiTietDTImpl.getAllResponse();
        loadTable(list);
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
//            String giaCuStr = this.list.get(index).getDonGia();
            BigDecimal giaCu = this.list.get(index).getDonGia();
            ChiTietDienThoai ctDT = this.getFormData();
            ctDT.setId(list.get(index).getId());
            ctDT.setMa(this.txtMa.getText().trim());
            if (ctDT == null) {
                return;
            }

            if (giaCu.compareTo(ctDT.getDonGia()) != 0) {
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
                        BigDecimal giaBan = ctDT.getDonGia();
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
                        dtkm.setDonGia(ctDT.getDonGia());
                        dtkm.setGiaConLai(giaconLai);

                        this.dienThoaiKhuyenMaiServiceImpl.update(dtkm);

                    }
                    list = this.chiTietDTImpl.getAllResponse();
                    loadTable(list);
                    clearForm();
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                    return;
                }
            }

            this.chiTietDTImpl.update(ctDT);
            list = this.chiTietDTImpl.getAllResponse();
            loadTable(list);
            clearForm();

            JOptionPane.showMessageDialog(this, "Cập nhật thành công");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Cập nhật thất bại");
            return;
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportActionPerformed
        // TODO add your handling code here:
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
    }//GEN-LAST:event_btnExportActionPerformed

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

    private void btnImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportActionPerformed
        //BTN Import Excel
    }//GEN-LAST:event_btnImportActionPerformed

    private void btnTaoQrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoQrActionPerformed
        //BTN Tạo QR
        int row = tblChiTietDienThoai.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Mời chọn một điện thoại để tải mã QR");
            return;
        }
        JnaFileChooser jfc = new JnaFileChooser();
        jfc.setMode(JnaFileChooser.Mode.Directories);
        if (!jfc.showOpenDialog((JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, this))) {
            return;
        }
        ChiTietDienThoaiResponse ctdt = list.get(row);
        String mess = chiTietDTImpl.exportQr(jfc.getSelectedFile().getAbsolutePath(), ctdt.getId());
        if (mess.equals("Tải thành công")) {
            Notification panel = new Notification((JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, this), 
                    Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Tải thành công");
            panel.showNotification();
        } else {
            JOptionPane.showMessageDialog(this, mess);
        }
    }//GEN-LAST:event_btnTaoQrActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private pro1041.team_3.swing.ButtonCustom btnExport;
    private pro1041.team_3.swing.ButtonCustom btnImport;
    private pro1041.team_3.swing.ButtonCustom btnSua;
    private pro1041.team_3.swing.ButtonCustom btnTaoQr;
    private pro1041.team_3.swing.ButtonCustom btnThem;
    private pro1041.team_3.swing.ButtonCustom btnThem1;
    private pro1041.team_3.swing.ButtonCustom buttonCustom4;
    private pro1041.team_3.swing.ButtonCustom buttonCustom5;
    private pro1041.team_3.swing.ButtonCustom buttonCustom6;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private pro1041.team_3.swing.Combobox<DienThoai> cbbDienThoai;
    private pro1041.team_3.swing.Combobox<Hang> cbbHang;
    private pro1041.team_3.swing.Combobox<MauSac> cbbMauSac;
    private pro1041.team_3.swing.ComboBoxSuggestion cbbTinhTrangCu;
    private pro1041.team_3.swing.Combobox cbbTrangThai;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jspTblChiTietDienThoai;
    private javax.swing.JRadioButton rdoCon;
    private javax.swing.JRadioButton rdoCu;
    private javax.swing.JRadioButton rdoHet;
    private javax.swing.JRadioButton rdoLoi;
    private javax.swing.JRadioButton rdoMoi;
    private pro1041.team_3.swing.config.Table tblChiTietDienThoai;
    private javax.swing.JPanel tinhTrangCuPanel;
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
