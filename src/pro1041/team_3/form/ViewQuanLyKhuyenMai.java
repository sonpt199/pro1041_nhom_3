package pro1041.team_3.form;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pro1041.team_3.domainModel.ChiTietDienThoai;
import pro1041.team_3.domainModel.DienThoai;
import pro1041.team_3.domainModel.DienThoaiKhuyenMai;
import pro1041.team_3.domainModel.Hang;
import pro1041.team_3.domainModel.KhuyenMai;
import pro1041.team_3.domainModel.MauSac;
import pro1041.team_3.dto.ChiTietDienThoaiResponse;
import pro1041.team_3.dto.DienThoaiKhuyenMaiDto;
import pro1041.team_3.dto.KhuyenMaiDto;
import pro1041.team_3.dto.KhuyenMaiReQuestDto;
import pro1041.team_3.service.impl.ChiTietDienThoaiImpl;
import pro1041.team_3.service.impl.DienThoaiKhuyenMaiServiceImpl;
import pro1041.team_3.service.impl.DienThoaiServiceImpl;
import pro1041.team_3.service.impl.HangServiceImpl;
import pro1041.team_3.service.impl.KhuyenMaiServiceImpl;
import pro1041.team_3.service.impl.MauSacServiceImpl;

/**
 *
 * @author hanhlt
 */
public class ViewQuanLyKhuyenMai extends javax.swing.JPanel {

    private ChiTietDienThoaiImpl chiTietDienThoaiImpl;
    private KhuyenMaiServiceImpl khuyenMaiImpl;
    private DienThoaiKhuyenMaiServiceImpl dienThoaiKhuyenMaiServiceImpl;
    private DienThoaiServiceImpl dienThoaiImpl;
    private HangServiceImpl hangImpl;
    private MauSacServiceImpl mauSacImpl;
    private List<DienThoaiKhuyenMaiDto> list = new ArrayList<>();
    private List<KhuyenMaiReQuestDto> listDTKM = new ArrayList<>();
    private List<KhuyenMaiDto> list1 = new ArrayList<>();
    private List<ChiTietDienThoaiResponse> listSPKM;

    public ViewQuanLyKhuyenMai() {
        initComponents();
        khuyenMaiImpl = new KhuyenMaiServiceImpl();
        dienThoaiKhuyenMaiServiceImpl = new DienThoaiKhuyenMaiServiceImpl();
        chiTietDienThoaiImpl = new ChiTietDienThoaiImpl();
        this.dienThoaiImpl = new DienThoaiServiceImpl();
        this.hangImpl = new HangServiceImpl();
        this.mauSacImpl = new MauSacServiceImpl();
        list1 = this.khuyenMaiImpl.getAllResponse();
        listSPKM = this.chiTietDienThoaiImpl.getAllTrangThai(0);
        loadTableKM(list1);
        txtMa.setEditable(false);
        //Set Icon
        ImageIcon iconDialog = new ImageIcon(getClass().getResource("/pro1041/team_3/icon/logoCircle.png"));
        dlThemDienThoaiVaoKm.setTitle("Thêm điện thoại vào đợt khuyến mại");
        dlThemDienThoaiVaoKm.setIconImage(iconDialog.getImage());
        dlChonSanPhamKm.setTitle("Chọn điện thoại được áp dụng khuyến mại");
        dlChonSanPhamKm.setIconImage(iconDialog.getImage());
        //Set Scroll Table
        tblAllSpChiTiet.fixTable(jspTblAllSpChiTiet);
        tblDienThoaiChon.fixTable(jspTblDienThoaiChon);
        tblDienThoaiThem.fixTable(jspTblDienThoaiThem);
        tblKhuyenMai.fixTable(jspTblKhuyenMai);
        
        //Mặc định thời gian
        tpThoiGianBatDau.now();
        tpThoiGianKetThuc.now();
//        txtNgayBD.setText("");
//        txtNgayKT.setText("");
    }

    private void clear() {
        txtMa.setText("");
        txtTen.setText("");
        txtNgayBD.setText("");
        txtNgayKT.setText("");
        txtMucKM.setText("");
        txtTimDTKM.setText("");
        txtTimKhuyenMai.setText("");
    }

    public void loadCbbDienThoai() {
        List<DienThoai> lst = this.dienThoaiImpl.getAll();
        for (DienThoai x : lst) {
            cbbLocDienThoai.addItem(x);
        }
    }

    public void loadCbbHang() {
        List<Hang> lst = this.hangImpl.getAll();
        for (Hang x : lst) {
            cbbLocHang.addItem(x);
        }
    }

    public void loadCbbMauSac() {
        List<MauSac> lst = this.mauSacImpl.getAll();
        for (MauSac x : lst) {
            cbbLocMauSac.addItem(x);
        }
    }

    public void loadCbbDienThoai1() {
        List<DienThoai> lst = this.dienThoaiImpl.getAll();
        for (DienThoai x : lst) {
            cbbLocDienThoai1.addItem(x);
        }
    }

    public void loadCbbHang1() {
        List<Hang> lst = this.hangImpl.getAll();
        for (Hang x : lst) {
            cbbLocHang1.addItem(x);
        }
    }

    public void loadCbbMauSac1() {
        List<MauSac> lst = this.mauSacImpl.getAll();
        for (MauSac x : lst) {
            cbbLocMauSac1.addItem(x);
        }
    }

    public void loadTableSP(List<DienThoaiKhuyenMaiDto> lst) {
        DefaultTableModel model = (DefaultTableModel) tblAllSpChiTiet.getModel();
        model.setRowCount(0);
        while (model.getRowCount() != 0) {
            model.removeRow(0);
        }
        int index = 1;
        for (DienThoaiKhuyenMaiDto x : lst) {
            model.addRow(x.toDataRow(index));
            index++;
        }
    }

    public void loadTableKM(List<KhuyenMaiDto> lst) {
        DefaultTableModel model = (DefaultTableModel) tblKhuyenMai.getModel();
        model.setRowCount(0);
        int index = 1;
        for (KhuyenMaiDto x : lst) {
            model.addRow(x.toDataRow(index));
            index++;
        }
    }

    public void loadTableSPKM(List<ChiTietDienThoaiResponse> lst) {
        DefaultTableModel model = (DefaultTableModel) tblDienThoaiChon.getModel();
        model.setRowCount(0);
        int index = 1;
        for (ChiTietDienThoaiResponse x : lst) {
            model.addRow(x.toDataRowKM(index));
            index++;
        }
    }

    public void loadTableDienThoai(List<ChiTietDienThoaiResponse> lst) {
        DefaultTableModel model = (DefaultTableModel) tblDienThoaiThem.getModel();
        model.setRowCount(0);
        int index = 1;
        for (ChiTietDienThoaiResponse x : lst) {
            model.addRow(x.toDataRowKM(index));
            index++;
        }
    }

    private KhuyenMai getFormData() {
        UUID id = UUID.randomUUID();
        String ma = txtMa.getText().trim();
        String ten = txtTen.getText().trim();
        String ngayBDStr = txtNgayBD.getText().trim();
        String ngayKTStr = txtNgayKT.getText().trim();
        String mucKM = txtMucKM.getText().trim();
        String thoiGianBatDauStr = txtThoiGianBD.getText();
        String thoiGianKetThucStr = txtThoiGianKT.getText();
        float gia = 0;
        Float phanTram = null;
        Float tienMat = null;
        Date ngayBatDau = null;
        Date ngayKetThuc = null;
        SimpleDateFormat sdfTime = new SimpleDateFormat("hh:mm aa", Locale.US);
        SimpleDateFormat sdfCongChuoi = new SimpleDateFormat("kk:mm");
        SimpleDateFormat sdfInput = new SimpleDateFormat("dd-MM-yyyy kk:mm");       
        try {
            Date thoiGianBatDau = sdfTime.parse(thoiGianBatDauStr);
            Date thoiGianKetThuc = sdfTime.parse(thoiGianKetThucStr);
            String ngayBatDauInput = ngayBDStr + " " + sdfCongChuoi.format(thoiGianBatDau);
            String ngayKetThucInput = ngayKTStr + " " + sdfCongChuoi.format(thoiGianKetThuc);
            ngayBatDau = sdfInput.parse(ngayBatDauInput);
            ngayKetThuc = sdfInput.parse(ngayKetThucInput);
            System.out.println(ngayBatDau.toLocaleString());
            System.out.println(ngayKetThuc.toLocaleString());
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Không đúng định dạng ngày");
            ex.printStackTrace();
        }
        try {
            gia = Float.parseFloat(mucKM);
            if (gia < 0) {
                JOptionPane.showMessageDialog(this, "Mức giá phải > 0");
                return null;
            }
            if (gia >= 0 && gia <= 100) {
                phanTram = gia;
            } else if (gia > 100) {
                tienMat = gia;
                phanTram = null;
            }
        } catch (NumberFormatException numberFormatException) {
            numberFormatException.printStackTrace();
            JOptionPane.showMessageDialog(this, "Mức giá phải lớn hơn hoặc bằng 0");
            return null;
        }

        KhuyenMai khuyenMai = new KhuyenMai();
        LocalDateTime time = LocalDateTime.now();
        String maKM = "KM" + time.getSecond() + time.getMinute() + time.getHour();
        khuyenMai.setMa(maKM);
        khuyenMai.setNgayBatDau(ngayBatDau);
        khuyenMai.setNgayKetThuc(ngayKetThuc);
        khuyenMai.setTen(ten);
        if (tienMat == null) {
            khuyenMai.setGiaTriPhanTram(phanTram);
            khuyenMai.setGiaTriTienMat(null);
        } else if (phanTram == null) {
            khuyenMai.setGiaTriPhanTram(null);
            khuyenMai.setGiaTriTienMat(new BigDecimal(tienMat));
        }
        return khuyenMai;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dlChonSanPhamKm = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        jspTblDienThoaiChon = new javax.swing.JScrollPane();
        tblDienThoaiChon = new pro1041.team_3.swing.config.Table();
        cbbLocDienThoai1 = new pro1041.team_3.swing.Combobox();
        cbbLocHang1 = new pro1041.team_3.swing.Combobox();
        cbbLocMauSac1 = new pro1041.team_3.swing.Combobox();
        cbbLocTinhTrang1 = new pro1041.team_3.swing.Combobox();
        btnThemSPKM = new pro1041.team_3.swing.ButtonCustom();
        buttonCustom2 = new pro1041.team_3.swing.ButtonCustom();
        dlThemDienThoaiVaoKm = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jspTblDienThoaiThem = new javax.swing.JScrollPane();
        tblDienThoaiThem = new pro1041.team_3.swing.config.Table();
        btnThoat = new pro1041.team_3.swing.ButtonCustom();
        cbbLocDienThoai = new pro1041.team_3.swing.Combobox();
        btnThemDienThoaiKM = new pro1041.team_3.swing.ButtonCustom();
        cbbLocHang = new pro1041.team_3.swing.Combobox();
        cbbLocMauSac = new pro1041.team_3.swing.Combobox();
        cbbLocTinhTrang = new pro1041.team_3.swing.Combobox();
        dlChonNgayBatDau = new pro1041.team_3.swing.DateChooser();
        dlChonNgayKetThuc = new pro1041.team_3.swing.DateChooser();
        tpThoiGianBatDau = new pro1041.team_3.swing.TimePicker();
        tpThoiGianKetThuc = new pro1041.team_3.swing.TimePicker();
        jLabel1 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        lblLoai = new javax.swing.JLabel();
        txtMa = new pro1041.team_3.swing.TextField();
        txtTen = new pro1041.team_3.swing.TextField();
        txtNgayBD = new pro1041.team_3.swing.TextField();
        txtNgayKT = new pro1041.team_3.swing.TextField();
        cbbLoaiKM = new pro1041.team_3.swing.Combobox();
        txtMucKM = new pro1041.team_3.swing.TextField();
        jLabel2 = new javax.swing.JLabel();
        txtThoiGianKT = new pro1041.team_3.swing.TextField();
        txtThoiGianBD = new pro1041.team_3.swing.TextField();
        jLabel8 = new javax.swing.JLabel();
        cbbLocKM = new pro1041.team_3.swing.Combobox();
        btnThem = new pro1041.team_3.swing.ButtonCustom();
        btnXoa = new pro1041.team_3.swing.ButtonCustom();
        btnSua = new pro1041.team_3.swing.ButtonCustom();
        btnThemSpKM = new pro1041.team_3.swing.ButtonCustom();
        btnClear = new pro1041.team_3.swing.ButtonCustom();
        jspTblAllSpChiTiet = new javax.swing.JScrollPane();
        tblAllSpChiTiet = new pro1041.team_3.swing.config.Table();
        jspTblKhuyenMai = new javax.swing.JScrollPane();
        tblKhuyenMai = new pro1041.team_3.swing.config.Table();
        txtTimDTKM = new pro1041.team_3.swing.TextField();
        jPanel12 = new javax.swing.JPanel();
        txtTimKhuyenMai = new pro1041.team_3.swing.TextField();

        dlChonSanPhamKm.setMinimumSize(new java.awt.Dimension(730, 570));
        dlChonSanPhamKm.setSize(new java.awt.Dimension(750, 600));
        dlChonSanPhamKm.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách điện thoại", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblDienThoaiChon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã CTDT", "Điện thoại", "Hãng", "Màu sắc", "Tình trạng", "Rom", "Imei"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jspTblDienThoaiChon.setViewportView(tblDienThoaiChon);
        if (tblDienThoaiChon.getColumnModel().getColumnCount() > 0) {
            tblDienThoaiChon.getColumnModel().getColumn(0).setPreferredWidth(10);
            tblDienThoaiChon.getColumnModel().getColumn(1).setPreferredWidth(25);
            tblDienThoaiChon.getColumnModel().getColumn(3).setPreferredWidth(25);
            tblDienThoaiChon.getColumnModel().getColumn(4).setPreferredWidth(25);
            tblDienThoaiChon.getColumnModel().getColumn(5).setPreferredWidth(25);
            tblDienThoaiChon.getColumnModel().getColumn(6).setPreferredWidth(25);
        }

        jPanel6.add(jspTblDienThoaiChon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 710, 360));

        cbbLocDienThoai1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All" }));
        cbbLocDienThoai1.setSelectedIndex(-1);
        cbbLocDienThoai1.setFocusLostColor(new java.awt.Color(3, 155, 216));
        cbbLocDienThoai1.setFont(new java.awt.Font("Nunito Light", 1, 12)); // NOI18N
        cbbLocDienThoai1.setLabeText("Điện thoại");
        cbbLocDienThoai1.setLabelColor(new java.awt.Color(1, 132, 203));
        cbbLocDienThoai1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLocDienThoai1ActionPerformed(evt);
            }
        });
        jPanel6.add(cbbLocDienThoai1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 160, -1));

        cbbLocHang1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All" }));
        cbbLocHang1.setSelectedIndex(-1);
        cbbLocHang1.setFocusLostColor(new java.awt.Color(3, 155, 216));
        cbbLocHang1.setFont(new java.awt.Font("Nunito Light", 1, 12)); // NOI18N
        cbbLocHang1.setLabeText("Hãng");
        cbbLocHang1.setLabelColor(new java.awt.Color(1, 132, 203));
        cbbLocHang1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLocHang1ActionPerformed(evt);
            }
        });
        jPanel6.add(cbbLocHang1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, 160, -1));

        cbbLocMauSac1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All" }));
        cbbLocMauSac1.setSelectedIndex(-1);
        cbbLocMauSac1.setFocusLostColor(new java.awt.Color(3, 155, 216));
        cbbLocMauSac1.setFont(new java.awt.Font("Nunito Light", 1, 12)); // NOI18N
        cbbLocMauSac1.setLabeText("Màu sắc");
        cbbLocMauSac1.setLabelColor(new java.awt.Color(1, 132, 203));
        cbbLocMauSac1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLocMauSac1ActionPerformed(evt);
            }
        });
        jPanel6.add(cbbLocMauSac1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, 160, -1));

        cbbLocTinhTrang1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All" }));
        cbbLocTinhTrang1.setSelectedIndex(-1);
        cbbLocTinhTrang1.setFocusLostColor(new java.awt.Color(3, 155, 216));
        cbbLocTinhTrang1.setFont(new java.awt.Font("Nunito Light", 1, 12)); // NOI18N
        cbbLocTinhTrang1.setLabeText("Tình trạng");
        cbbLocTinhTrang1.setLabelColor(new java.awt.Color(1, 132, 203));
        cbbLocTinhTrang1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLocTinhTrang1ActionPerformed(evt);
            }
        });
        jPanel6.add(cbbLocTinhTrang1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 40, 160, -1));

        btnThemSPKM.setBackground(new java.awt.Color(1, 181, 204));
        btnThemSPKM.setForeground(new java.awt.Color(255, 255, 255));
        btnThemSPKM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/add.png"))); // NOI18N
        btnThemSPKM.setText("Thêm");
        btnThemSPKM.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        btnThemSPKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSPKMActionPerformed(evt);
            }
        });
        jPanel6.add(btnThemSPKM, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 480, 120, -1));

        buttonCustom2.setBackground(new java.awt.Color(153, 153, 153));
        buttonCustom2.setForeground(new java.awt.Color(255, 255, 255));
        buttonCustom2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/back.png"))); // NOI18N
        buttonCustom2.setText("Quay lại");
        buttonCustom2.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        buttonCustom2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCustom2ActionPerformed(evt);
            }
        });
        jPanel6.add(buttonCustom2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 480, -1, -1));

        dlChonSanPhamKm.getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 540));

        dlThemDienThoaiVaoKm.setMinimumSize(new java.awt.Dimension(730, 570));
        dlThemDienThoaiVaoKm.setSize(new java.awt.Dimension(750, 580));
        dlThemDienThoaiVaoKm.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách điện thoại", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblDienThoaiThem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã CTDT", "Điện thoại", "Hãng", "Màu sắc", "Tình trạng", "Rom", "Imei"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jspTblDienThoaiThem.setViewportView(tblDienThoaiThem);
        if (tblDienThoaiThem.getColumnModel().getColumnCount() > 0) {
            tblDienThoaiThem.getColumnModel().getColumn(0).setPreferredWidth(3);
            tblDienThoaiThem.getColumnModel().getColumn(1).setPreferredWidth(25);
            tblDienThoaiThem.getColumnModel().getColumn(3).setPreferredWidth(20);
            tblDienThoaiThem.getColumnModel().getColumn(4).setPreferredWidth(20);
            tblDienThoaiThem.getColumnModel().getColumn(5).setPreferredWidth(20);
            tblDienThoaiThem.getColumnModel().getColumn(6).setPreferredWidth(15);
        }

        jPanel1.add(jspTblDienThoaiThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 710, 360));

        btnThoat.setBackground(new java.awt.Color(153, 153, 153));
        btnThoat.setForeground(new java.awt.Color(255, 255, 255));
        btnThoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/back.png"))); // NOI18N
        btnThoat.setText("Quay lại");
        btnThoat.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });
        jPanel1.add(btnThoat, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 470, 120, -1));

        cbbLocDienThoai.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All" }));
        cbbLocDienThoai.setSelectedIndex(-1);
        cbbLocDienThoai.setFocusLostColor(new java.awt.Color(3, 155, 216));
        cbbLocDienThoai.setLabeText("Điện thoại");
        cbbLocDienThoai.setLabelColor(new java.awt.Color(1, 132, 203));
        cbbLocDienThoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLocDienThoaiActionPerformed(evt);
            }
        });
        jPanel1.add(cbbLocDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 160, -1));

        btnThemDienThoaiKM.setBackground(new java.awt.Color(1, 181, 204));
        btnThemDienThoaiKM.setForeground(new java.awt.Color(255, 255, 255));
        btnThemDienThoaiKM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/add.png"))); // NOI18N
        btnThemDienThoaiKM.setText("Thêm");
        btnThemDienThoaiKM.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        btnThemDienThoaiKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemDienThoaiKMActionPerformed(evt);
            }
        });
        jPanel1.add(btnThemDienThoaiKM, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 470, 120, -1));

        cbbLocHang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All" }));
        cbbLocHang.setSelectedIndex(-1);
        cbbLocHang.setFocusLostColor(new java.awt.Color(3, 155, 216));
        cbbLocHang.setLabeText("Hãng");
        cbbLocHang.setLabelColor(new java.awt.Color(1, 132, 203));
        cbbLocHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLocHangActionPerformed(evt);
            }
        });
        jPanel1.add(cbbLocHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, 160, -1));

        cbbLocMauSac.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All" }));
        cbbLocMauSac.setSelectedIndex(-1);
        cbbLocMauSac.setFocusLostColor(new java.awt.Color(3, 155, 216));
        cbbLocMauSac.setLabeText("Màu sắc");
        cbbLocMauSac.setLabelColor(new java.awt.Color(1, 132, 203));
        cbbLocMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLocMauSacActionPerformed(evt);
            }
        });
        jPanel1.add(cbbLocMauSac, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 40, 160, -1));

        cbbLocTinhTrang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All" }));
        cbbLocTinhTrang.setSelectedIndex(-1);
        cbbLocTinhTrang.setFocusLostColor(new java.awt.Color(3, 155, 216));
        cbbLocTinhTrang.setLabeText("Tình trạng");
        cbbLocTinhTrang.setLabelColor(new java.awt.Color(1, 132, 203));
        cbbLocTinhTrang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLocTinhTrangActionPerformed(evt);
            }
        });
        jPanel1.add(cbbLocTinhTrang, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 40, 160, -1));

        dlThemDienThoaiVaoKm.getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 530));

        dlChonNgayBatDau.setTextRefernce(txtNgayBD);

        dlChonNgayKetThuc.setTextRefernce(txtNgayKT);

        tpThoiGianBatDau.setDisplayText(txtThoiGianBD);

        tpThoiGianKetThuc.setDisplayText(txtThoiGianKT);

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1272, 748));
        setMinimumSize(new java.awt.Dimension(1272, 748));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        jLabel1.setText("Danh sách sản phẩm");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, -1, -1));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblLoai.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblLoai.setForeground(new java.awt.Color(1, 181, 204));
        lblLoai.setText("%");
        jPanel8.add(lblLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 310, 50, -1));

        txtMa.setEditable(false);
        txtMa.setBackground(new java.awt.Color(246, 246, 246));
        txtMa.setFont(new java.awt.Font("Nunito Light", 1, 13)); // NOI18N
        txtMa.setLabelColor(new java.awt.Color(1, 132, 203));
        txtMa.setLabelText("Mã khuyến mại");
        jPanel8.add(txtMa, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 290, -1));

        txtTen.setFont(new java.awt.Font("Nunito Light", 1, 13)); // NOI18N
        txtTen.setLabelColor(new java.awt.Color(1, 132, 203));
        txtTen.setLabelText("Tên khuyến mại");
        jPanel8.add(txtTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 290, -1));

        txtNgayBD.setFont(new java.awt.Font("Nunito Light", 1, 13)); // NOI18N
        txtNgayBD.setLabelColor(new java.awt.Color(1, 132, 203));
        txtNgayBD.setLabelText("Ngày bắt đầu");
        jPanel8.add(txtNgayBD, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 140, -1));

        txtNgayKT.setFont(new java.awt.Font("Nunito Light", 1, 13)); // NOI18N
        txtNgayKT.setLabelColor(new java.awt.Color(1, 132, 203));
        txtNgayKT.setLabelText("Ngày kết thúc");
        jPanel8.add(txtNgayKT, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 140, -1));

        cbbLoaiKM.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Theo %", "Theo tiền mặt" }));
        cbbLoaiKM.setLabeText("");
        cbbLoaiKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLoaiKMActionPerformed(evt);
            }
        });
        jPanel8.add(cbbLoaiKM, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, 180, 40));

        txtMucKM.setFont(new java.awt.Font("Nunito Light", 1, 13)); // NOI18N
        txtMucKM.setLabelColor(new java.awt.Color(1, 132, 203));
        txtMucKM.setLabelText("Mức khuyến mại");
        jPanel8.add(txtMucKM, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 240, -1));

        jLabel2.setFont(new java.awt.Font("Nunito Light", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(3, 155, 216));
        jLabel2.setText("Loại khuyến mại");
        jPanel8.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));

        txtThoiGianKT.setEditable(false);
        txtThoiGianKT.setFont(new java.awt.Font("Nunito Light", 1, 13)); // NOI18N
        txtThoiGianKT.setLabelColor(new java.awt.Color(1, 132, 203));
        txtThoiGianKT.setLabelText("");
        txtThoiGianKT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtThoiGianKTMouseClicked(evt);
            }
        });
        jPanel8.add(txtThoiGianKT, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, 140, -1));

        txtThoiGianBD.setEditable(false);
        txtThoiGianBD.setFont(new java.awt.Font("Nunito Light", 1, 13)); // NOI18N
        txtThoiGianBD.setLabelColor(new java.awt.Color(1, 132, 203));
        txtThoiGianBD.setLabelText("");
        txtThoiGianBD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtThoiGianBDMouseClicked(evt);
            }
        });
        jPanel8.add(txtThoiGianBD, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 140, -1));

        add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 20, 340, 340));

        jLabel8.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(1, 132, 203));
        jLabel8.setText("Lọc trạng thái:");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(496, 40, 100, 40));

        cbbLocKM.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất cả", "Đang diễn ra", "Sắp diễn ra", "Kết thúc" }));
        cbbLocKM.setLabeText("");
        cbbLocKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLocKMActionPerformed(evt);
            }
        });
        add(cbbLocKM, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 40, 160, 40));

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
        add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 380, 110, 50));

        btnXoa.setBackground(new java.awt.Color(255, 0, 0));
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/delete.png"))); // NOI18N
        btnXoa.setText("Xóa SP");
        btnXoa.setToolTipText("Xóa điện thoại khỏi khuyến mại");
        btnXoa.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 380, 120, 50));

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
        add(btnSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 380, 110, 50));

        btnThemSpKM.setBackground(new java.awt.Color(1, 181, 204));
        btnThemSpKM.setForeground(new java.awt.Color(255, 255, 255));
        btnThemSpKM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/add.png"))); // NOI18N
        btnThemSpKM.setText("Thêm SP");
        btnThemSpKM.setToolTipText("Thêm điện thoại vào khuyến mại");
        btnThemSpKM.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        btnThemSpKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSpKMActionPerformed(evt);
            }
        });
        add(btnThemSpKM, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 380, 120, 50));

        btnClear.setBackground(new java.awt.Color(1, 181, 204));
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        btnClear.setText("Làm mới");
        btnClear.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 380, 110, 50));

        tblAllSpChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã điện thoại", "Tên điện thoại", "Hãng", "Màu sắc", "Imei", "Giá bán (VNĐ)", "Giá còn lại (VNĐ)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jspTblAllSpChiTiet.setViewportView(tblAllSpChiTiet);
        if (tblAllSpChiTiet.getColumnModel().getColumnCount() > 0) {
            tblAllSpChiTiet.getColumnModel().getColumn(0).setPreferredWidth(5);
            tblAllSpChiTiet.getColumnModel().getColumn(1).setPreferredWidth(55);
            tblAllSpChiTiet.getColumnModel().getColumn(3).setPreferredWidth(40);
            tblAllSpChiTiet.getColumnModel().getColumn(4).setPreferredWidth(40);
            tblAllSpChiTiet.getColumnModel().getColumn(6).setPreferredWidth(50);
            tblAllSpChiTiet.getColumnModel().getColumn(7).setPreferredWidth(50);
        }

        add(jspTblAllSpChiTiet, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 1130, 300));

        tblKhuyenMai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã KM", "Tên khuyến mại", "Ngày bắt đầu", "Ngày kết thúc", "Mức khuyến mại", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhuyenMaiMouseClicked(evt);
            }
        });
        jspTblKhuyenMai.setViewportView(tblKhuyenMai);
        if (tblKhuyenMai.getColumnModel().getColumnCount() > 0) {
            tblKhuyenMai.getColumnModel().getColumn(0).setPreferredWidth(8);
            tblKhuyenMai.getColumnModel().getColumn(1).setPreferredWidth(30);
            tblKhuyenMai.getColumnModel().getColumn(2).setPreferredWidth(60);
            tblKhuyenMai.getColumnModel().getColumn(5).setPreferredWidth(50);
        }

        add(jspTblKhuyenMai, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 760, 270));

        txtTimDTKM.setFont(new java.awt.Font("Nunito", 0, 14)); // NOI18N
        txtTimDTKM.setLabelColor(new java.awt.Color(1, 132, 203));
        txtTimDTKM.setLabelText("Tìm kiếm sản phẩm khuyến mại theo mã và tên");
        txtTimDTKM.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimDTKMCaretUpdate(evt);
            }
        });
        add(txtTimDTKM, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 380, 310, -1));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách khuyến mại", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Nunito", 1, 14))); // NOI18N
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTimKhuyenMai.setFont(new java.awt.Font("Nunito", 0, 14)); // NOI18N
        txtTimKhuyenMai.setLabelColor(new java.awt.Color(1, 132, 203));
        txtTimKhuyenMai.setLabelText("Tìm kiếm khuyến mại theo mã và tên");
        txtTimKhuyenMai.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKhuyenMaiCaretUpdate(evt);
            }
        });
        jPanel12.add(txtTimKhuyenMai, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 310, 50));

        add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 780, 360));
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        this.loadCbbDienThoai1();
        this.loadCbbHang1();
        this.loadCbbMauSac1();
        this.loadTableSPKM(chiTietDienThoaiImpl.getAllTrangThai(0));
        dlChonSanPhamKm.setVisible(true);
        dlChonSanPhamKm.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        int row = this.tblKhuyenMai.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Bạn cần chọn một khuyến mại");
            return;
        }
        UUID id = list1.get(row).getIdKhuyenMai();
        KhuyenMai khuyenMai = this.getFormData();
        if (khuyenMai == null) {
            return;
        }
        khuyenMai.setId(id);
        String ketQua = khuyenMaiImpl.update(khuyenMai);
        if (ketQua.equals("Sửa thành công")) {
            list1 = khuyenMaiImpl.getAllResponse();
            loadTableKM(list1);
            clear();
        }
        JOptionPane.showMessageDialog(this, ketQua);
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        clear();
        loadTableKM(khuyenMaiImpl.getAllResponse());
        list.clear();
        loadTableSP(list);
    }//GEN-LAST:event_btnClearActionPerformed

    private void cbbLoaiKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLoaiKMActionPerformed
        // TODO add your handling code here:
        if (cbbLoaiKM.getSelectedIndex() == 0) {
            this.lblLoai.setText("%");
        } else {
            this.lblLoai.setText("VND");
        }
    }//GEN-LAST:event_cbbLoaiKMActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // Xóa một sản phẩm khỏi khuyến mại
        int row = this.tblKhuyenMai.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Bạn cần chọn một khuyến mại");
            return;
        }
        int row2 = this.tblAllSpChiTiet.getSelectedRow();
        if (row2 == -1) {
            JOptionPane.showMessageDialog(this, "Bạn cần chọn một điện thoại trong đợt khuyến mại để xóa");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa bản ghi này không?");
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        UUID id = list1.get(row).getIdKhuyenMai();
        KhuyenMai khuyenMai = this.getFormData();
        if (khuyenMai == null) {
            return;
        }
        khuyenMai.setId(id);

        list = this.dienThoaiKhuyenMaiServiceImpl.findDienThoaiKhuyenMaiByIdKM(id);
        ChiTietDienThoai chiTietDienThoai = new ChiTietDienThoai();
        chiTietDienThoai.setId(list.get(row2).getIdChiTietDienThoai());

        DienThoaiKhuyenMai dienThoaiKhuyenMai = new DienThoaiKhuyenMai();
        dienThoaiKhuyenMai.setChiTietDienThoai(chiTietDienThoai);
        dienThoaiKhuyenMai.setKhuyenMai(khuyenMai);

        String ketQua = dienThoaiKhuyenMaiServiceImpl.deleteDTKM(dienThoaiKhuyenMai);
        if (ketQua.equals("Xóa thành công")) {
            list = this.dienThoaiKhuyenMaiServiceImpl.findDienThoaiKhuyenMaiByIdKM(id);
            loadTableSP(list);
        }
        JOptionPane.showMessageDialog(this, ketQua);
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnThemSpKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSpKMActionPerformed
        // Thêm sản phẩm vào khuyến mại đã có 
        int row = this.tblKhuyenMai.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Bạn cần chọn một khuyến mại");
            return;
        }
        UUID id = list1.get(row).getIdKhuyenMai();
        Date dateNow = new Date();
        KhuyenMaiDto x = list1.get(row);
        if (x.getNgayKetThuc().getTime() < dateNow.getTime()) {
            JOptionPane.showMessageDialog(this, "Khuyến mại này đã dừng hoạt động");
            return;
        }
        KhuyenMai khuyenMai = this.getFormData();
        if (khuyenMai == null) {
            return;
        }
        this.loadCbbDienThoai();
        this.loadCbbHang();
        this.loadCbbMauSac();
        listSPKM = this.chiTietDienThoaiImpl.getAllCTDTNotInKMTrung(khuyenMai.getNgayBatDau(), khuyenMai.getNgayKetThuc());
        this.loadTableDienThoai(listSPKM);
        dlThemDienThoaiVaoKm.setVisible(true);
        dlThemDienThoaiVaoKm.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnThemSpKMActionPerformed

    private void tblKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhuyenMaiMouseClicked
        // TODO add your handling code here:
        int row = this.tblKhuyenMai.getSelectedRow();
        if (row == -1) {
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat sdfThoiGian = new SimpleDateFormat("hh:mm aa", Locale.US);
        KhuyenMaiDto x = list1.get(row);
        this.txtMa.setText(x.getMaKhuyenMai());
        this.txtTen.setText(x.getTenKhuyenMai());
        this.txtNgayBD.setText(sdf.format(x.getNgayBatDau()));
        this.txtNgayKT.setText(sdf.format(x.getNgayKetThuc()));
        this.txtThoiGianBD.setText(sdfThoiGian.format(x.getNgayBatDau()));
        this.txtThoiGianKT.setText(sdfThoiGian.format(x.getNgayKetThuc()));
        if (x.getGiaTriPhanTram() == null) {
            txtMucKM.setText(x.getGiaTriTienMat().toString());
            cbbLoaiKM.setSelectedIndex(1);
        } else {
            txtMucKM.setText(x.getGiaTriPhanTram().toString());
            cbbLoaiKM.setSelectedIndex(0);
        }

//        UUID id = list1.get(row).getIdKhuyenMai();
        list = this.dienThoaiKhuyenMaiServiceImpl.findDienThoaiKhuyenMaiByIdKM(x.getIdKhuyenMai());
        this.loadTableSP(list);
    }//GEN-LAST:event_tblKhuyenMaiMouseClicked

    private void cbbLocKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocKMActionPerformed
        // TODO add your handling code here:
        int chon = cbbLocKM.getSelectedIndex();
        if (chon == 0) {
            loadTableKM(khuyenMaiImpl.getAllResponse());
        } else if (chon == 1) {
            loadTableKM(khuyenMaiImpl.findKMDangDienRa());
        } else if (chon == 2) {
            loadTableKM(khuyenMaiImpl.findNgayTuongLai());
        } else if (chon == 3) {
            loadTableKM(khuyenMaiImpl.findKMKetThuc());
        }
    }//GEN-LAST:event_cbbLocKMActionPerformed

    private void txtTimKhuyenMaiCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKhuyenMaiCaretUpdate
        // TODO add your handling code here:
        loadTableKM(khuyenMaiImpl.findKhuyenMai(txtTimKhuyenMai.getText().trim()));
    }//GEN-LAST:event_txtTimKhuyenMaiCaretUpdate

    private void txtTimDTKMCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimDTKMCaretUpdate
        // TODO add your handling code here:
        int row = this.tblKhuyenMai.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Mời bạn chọn 1 khuyến mại");
            return;
        } else {
            UUID id = list1.get(row).getIdKhuyenMai();
            loadTableSP(dienThoaiKhuyenMaiServiceImpl.findDienThoaiKhuyenMai(id, txtTimDTKM.getText().trim()));
        }
    }//GEN-LAST:event_txtTimDTKMCaretUpdate

    private void cbbLocDienThoai1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocDienThoai1ActionPerformed
        // CBB lọc điện thoại khi chọn Điện thoại vào KM
        KhuyenMai khuyenMai = getFormData();
        
        String text = this.cbbLocDienThoai1.getSelectedItem().toString();
        if (cbbLocDienThoai1.getSelectedIndex() == 0) {
            listSPKM = this.chiTietDienThoaiImpl.getAllCTDTNotInKMTrung(khuyenMai.getNgayBatDau(), khuyenMai.getNgayKetThuc());
            this.loadTableSPKM(listSPKM);
        } else {
            listSPKM = this.chiTietDienThoaiImpl.getAllCTDienThoaiByDienThoai(text, khuyenMai.getNgayBatDau(), khuyenMai.getNgayKetThuc());
            this.loadTableSPKM(listSPKM);
        }
    }//GEN-LAST:event_cbbLocDienThoai1ActionPerformed

    private void cbbLocHang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocHang1ActionPerformed
        // CBB lọc hãng khi chọn Điện thoại vào KM
        KhuyenMai khuyenMai = getFormData();
        String text = this.cbbLocHang1.getSelectedItem().toString();
        if (cbbLocHang1.getSelectedIndex() == 0) {
            listSPKM = this.chiTietDienThoaiImpl.getAllCTDTNotInKMTrung(khuyenMai.getNgayBatDau(), khuyenMai.getNgayKetThuc());
            this.loadTableSPKM(listSPKM);
        } else {
            listSPKM = this.chiTietDienThoaiImpl.getAllCTDienThoaiByHang(text, khuyenMai.getNgayBatDau(), khuyenMai.getNgayKetThuc());
            this.loadTableSPKM(listSPKM);
        }
    }//GEN-LAST:event_cbbLocHang1ActionPerformed

    private void cbbLocMauSac1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocMauSac1ActionPerformed
        // CBB lọc màu sắc khi chọn Điện thoại vào KM
        KhuyenMai khuyenMai = getFormData();
        String text = this.cbbLocMauSac1.getSelectedItem().toString();
        if (cbbLocMauSac1.getSelectedIndex() == 0) {
            listSPKM = this.chiTietDienThoaiImpl.getAllCTDTNotInKMTrung(khuyenMai.getNgayBatDau(), khuyenMai.getNgayKetThuc());
            this.loadTableSPKM(listSPKM);
        } else {
            listSPKM = this.chiTietDienThoaiImpl.getAllCTDienThoaiByMauSac(text, khuyenMai.getNgayBatDau(), khuyenMai.getNgayKetThuc());
            this.loadTableSPKM(listSPKM);
        }
    }//GEN-LAST:event_cbbLocMauSac1ActionPerformed

    private void cbbLocTinhTrang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocTinhTrang1ActionPerformed
        // CBB lọc tình trạng khi chọn Điện thoại vào KM
        KhuyenMai khuyenMai = getFormData();
        String text = this.cbbLocTinhTrang1.getSelectedItem().toString();

        if (cbbLocTinhTrang1.getSelectedIndex() == 0) {
            listSPKM = this.chiTietDienThoaiImpl.getAllCTDTNotInKMTrung(khuyenMai.getNgayBatDau(), khuyenMai.getNgayKetThuc());
            this.loadTableSPKM(listSPKM);
        } else {
            Integer tinhTrang = Integer.parseInt(text);
            listSPKM = this.chiTietDienThoaiImpl.getAllCTDienThoaiByTinhTrang(tinhTrang, khuyenMai.getNgayBatDau(), khuyenMai.getNgayKetThuc());
            this.loadTableSPKM(listSPKM);
        }
    }//GEN-LAST:event_cbbLocTinhTrang1ActionPerformed

    private void btnThemSPKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSPKMActionPerformed
        // BTN Thêm khuyến mại sau khi chọn SP được khuyến mại
        List<Integer> lst = new ArrayList<>();
        List<KhuyenMaiReQuestDto> lstKMRequest = new ArrayList<>();
        int row = tblDienThoaiChon.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Mời bạn chọn sản phẩm");
            return;
        }
        int[] arrRow = tblDienThoaiChon.getSelectedRows();
        for (int i : arrRow) {
            lst.add(i);
        }

//        for (int i = 0; i < tblAllSpKM.getRowCount(); i++) {
//            Boolean check = (boolean) tblAllSpKM.getValueAt(i, 4);
//            if (check) {
//                lst.add(i);
//            }
//        }
        for (int i = 0; i < listSPKM.size(); i++) {
            for (Integer x : lst) {
                if (i == x) {
                    KhuyenMai khuyenMai = this.getFormData();
                    if (khuyenMai == null) {
                        return;
                    }
//                    List<DienThoaiKhuyenMaiDto> lstDTKMDienRavaTgLai = dienThoaiKhuyenMaiServiceImpl.findDTKhuyenMaiDienRavaTgLai(listSPKM.get(i).getId());
//                    for (DienThoaiKhuyenMaiDto a : lstDTKMDienRavaTgLai) {
//                        //(StartA < EndB) && (EndA > StartB)
//                        KhuyenMai KM = khuyenMaiImpl.findById(a.getIdKhuyenMai());
//                        if (khuyenMai.getNgayBatDau().getTime() < KM.getNgayKetThuc().getTime()
//                                && khuyenMai.getNgayKetThuc().getTime() > KM.getNgayBatDau().getTime()) {
//                            JOptionPane.showMessageDialog(this, "Điện thoại " + a.getMaChiTietDienThoai() + " đã có khuyến mại " + KM.getTen());
//                            lstKMRequest.clear();
//                            return;
//                        }
//                    }
                    ChiTietDienThoai chiTietDienThoai = new ChiTietDienThoai();
                    chiTietDienThoai.setId(listSPKM.get(i).getId());
                    chiTietDienThoai.setDonGia(listSPKM.get(i).getDonGia());

                    BigDecimal giaBan = chiTietDienThoai.getDonGia();
                    Float giaphanTram = khuyenMai.getGiaTriPhanTram();
                    BigDecimal giaTienMat = khuyenMai.getGiaTriTienMat();
                    BigDecimal giaconLai = null;
                    if (giaTienMat == null) {
//                        giaconLai = giaBan - (giaBan * (giaphanTram / 100));
                        giaconLai = giaBan.subtract(giaBan.multiply(BigDecimal.valueOf(giaphanTram / 100)));
                    } else if (giaphanTram == null) {
                        giaconLai = giaBan.subtract(giaTienMat);
                    }
                    System.out.println(i);
                    System.out.println(giaphanTram);
                    System.out.println(giaTienMat);
                    System.out.println(giaconLai);

                    KhuyenMaiReQuestDto khuyenMaiReQuestDto = new KhuyenMaiReQuestDto();
                    khuyenMaiReQuestDto.setMaKhuyenMai(khuyenMai.getMa());
                    khuyenMaiReQuestDto.setTenKhuyenMai(khuyenMai.getTen());
                    khuyenMaiReQuestDto.setNgayBatDau(khuyenMai.getNgayBatDau());
                    khuyenMaiReQuestDto.setNgayKetThuc(khuyenMai.getNgayKetThuc());
                    khuyenMaiReQuestDto.setGiaTriPhanTram(khuyenMai.getGiaTriPhanTram());
                    khuyenMaiReQuestDto.setGiaTriTienMat(khuyenMai.getGiaTriTienMat());
                    khuyenMaiReQuestDto.setChiTietDienThoai(chiTietDienThoai);
                    khuyenMaiReQuestDto.setGiaBan(giaBan);
                    khuyenMaiReQuestDto.setGiaConLai(giaconLai);

                    lstKMRequest.add(khuyenMaiReQuestDto);

                }
            }
        }
        String ketQua = dienThoaiKhuyenMaiServiceImpl.insertKMDT(lstKMRequest);
        if (ketQua.equals("Thêm thành công")) {
            list1 = khuyenMaiImpl.getAllResponse();
            clear();
            loadTableKM(list1);
            list.clear();
            loadTableSP(list);
            dlChonSanPhamKm.setVisible(false);

        }
        JOptionPane.showMessageDialog(this, ketQua);

    }//GEN-LAST:event_btnThemSPKMActionPerformed

    private void buttonCustom2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCustom2ActionPerformed
        // BTN Close
        dlChonSanPhamKm.setVisible(false);
    }//GEN-LAST:event_buttonCustom2ActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        // TODO add your handling code here:
        dlThemDienThoaiVaoKm.setVisible(false);
    }//GEN-LAST:event_btnThoatActionPerformed

    private void btnThemDienThoaiKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemDienThoaiKMActionPerformed
        // BTN Thêm điện thoại vào KM sau khi chọn điện thoại
        int row = this.tblKhuyenMai.getSelectedRow();
        UUID id = list1.get(row).getIdKhuyenMai();
        KhuyenMai khuyenMai = this.getFormData();
        if (khuyenMai == null) {
            return;
        }
        khuyenMai.setId(id);
        int row1 = tblDienThoaiThem.getSelectedRow();
        if (row1 == -1) {
            JOptionPane.showMessageDialog(this, "Mời bạn chọn sản phẩm");
            return;
        }
        List<Integer> lst = new ArrayList<>();
        int[] arrRow = tblDienThoaiThem.getSelectedRows();
        for (int i : arrRow) {
            lst.add(i);
        }
        List<KhuyenMaiReQuestDto> lstKMRequest = new ArrayList<>();
//        for (int i = 0; i < tblDienThoai.getRowCount(); i++) {
//            Boolean check = (boolean) tblDienThoai.getValueAt(i, 4);
//            if (check) {
//                lst.add(i);
//            }
//        }

        List<ChiTietDienThoaiResponse> listCTDT = chiTietDienThoaiImpl.getAllCTDTNotInKMTrung(khuyenMai.getNgayBatDau(), khuyenMai.getNgayKetThuc());
        for (int i = 0; i < listCTDT.size(); i++) {
            for (Integer x : lst) {
                if (i == x) {
                    List<DienThoaiKhuyenMaiDto> lstDTKMDienRavaTgLai = dienThoaiKhuyenMaiServiceImpl.findDTKhuyenMaiDienRavaTgLai(listSPKM.get(i).getId());
                    for (DienThoaiKhuyenMaiDto a : lstDTKMDienRavaTgLai) {
                        //(StartA < EndB) && (EndA > StartB)
                        KhuyenMai KM = khuyenMaiImpl.findById(a.getIdKhuyenMai());
                        if (khuyenMai.getNgayBatDau().getTime() < KM.getNgayKetThuc().getTime()
                                && khuyenMai.getNgayKetThuc().getTime() > KM.getNgayBatDau().getTime()) {
                            JOptionPane.showMessageDialog(this, "Điện thoại " + a.getMaChiTietDienThoai() + " đã có khuyến mại " + KM.getTen());
                            lstKMRequest.clear();
                            return;
                        }
                    }
                    ChiTietDienThoai chiTietDienThoai = new ChiTietDienThoai();
                    chiTietDienThoai.setId(listCTDT.get(i).getId());
                    chiTietDienThoai.setDonGia(listCTDT.get(i).getDonGia());

                    BigDecimal giaBan = chiTietDienThoai.getDonGia();
                    Float giaphanTram = khuyenMai.getGiaTriPhanTram();
                    BigDecimal giaTienMat = khuyenMai.getGiaTriTienMat();
                    BigDecimal giaconLai = null;
                    if (giaTienMat == null) {
//                        giaconLai = giaBan - (giaBan * (giaphanTram / 100));
                        giaconLai = giaBan.subtract(giaBan.multiply(BigDecimal.valueOf(giaphanTram / 100)));
                    } else if (giaphanTram == null) {
                        giaconLai = giaBan.subtract(giaTienMat);
                    }

                    KhuyenMaiReQuestDto khuyenMaiReQuestDto = new KhuyenMaiReQuestDto();
                    khuyenMaiReQuestDto.setMaKhuyenMai(khuyenMai.getMa());
                    khuyenMaiReQuestDto.setTenKhuyenMai(khuyenMai.getTen());
                    khuyenMaiReQuestDto.setNgayBatDau(khuyenMai.getNgayBatDau());
                    khuyenMaiReQuestDto.setNgayKetThuc(khuyenMai.getNgayKetThuc());
                    khuyenMaiReQuestDto.setGiaTriPhanTram(khuyenMai.getGiaTriPhanTram());
                    khuyenMaiReQuestDto.setGiaTriTienMat(khuyenMai.getGiaTriTienMat());
                    khuyenMaiReQuestDto.setChiTietDienThoai(chiTietDienThoai);
                    khuyenMaiReQuestDto.setGiaBan(giaBan);
                    khuyenMaiReQuestDto.setGiaConLai(giaconLai);

                    lstKMRequest.add(khuyenMaiReQuestDto);
                }
            }
        }

        String ketQua = dienThoaiKhuyenMaiServiceImpl.insertSanPhamKM(khuyenMai, lstKMRequest);
        if (ketQua.equals("Thêm thành công")) {

            list1 = khuyenMaiImpl.getAllResponse();
            clear();
            loadTableKM(list1);
            list.clear();
            loadTableSP(list);
            dlThemDienThoaiVaoKm.setVisible(false);
        }
        JOptionPane.showMessageDialog(this, ketQua);
    }//GEN-LAST:event_btnThemDienThoaiKMActionPerformed

    private void cbbLocDienThoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocDienThoaiActionPerformed
        // CBB lọc điện thoại khi thêm Điện thoại vào KM
        int row = this.tblKhuyenMai.getSelectedRow();
        UUID id = list1.get(row).getIdKhuyenMai();
        KhuyenMai khuyenMai = getFormData();
        String text = this.cbbLocDienThoai.getSelectedItem().toString();
        if (cbbLocDienThoai.getSelectedIndex() == 0) {
            listSPKM = this.chiTietDienThoaiImpl.getAllCTDTNotInKMTrung(khuyenMai.getNgayBatDau(), khuyenMai.getNgayKetThuc());
            this.loadTableDienThoai(listSPKM);
        } else {
            listSPKM = this.chiTietDienThoaiImpl.getAllCTDienThoaiByDienThoai(text, khuyenMai.getNgayBatDau(), khuyenMai.getNgayKetThuc());
            this.loadTableDienThoai(listSPKM);
        }
    }//GEN-LAST:event_cbbLocDienThoaiActionPerformed

    private void cbbLocHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocHangActionPerformed
        // CBB lọc hãng khi thêm Điện thoại vào KM
        int row = this.tblKhuyenMai.getSelectedRow();
        UUID id = list1.get(row).getIdKhuyenMai();
        String text = this.cbbLocHang.getSelectedItem().toString();
        KhuyenMai khuyenMai = getFormData();
        if (cbbLocHang.getSelectedIndex() == 0) {
            listSPKM = this.chiTietDienThoaiImpl.getAllCTDTNotInKMTrung(khuyenMai.getNgayBatDau(), khuyenMai.getNgayKetThuc());
            this.loadTableDienThoai(listSPKM);
        } else {
            listSPKM = this.chiTietDienThoaiImpl.getAllCTDienThoaiByHang(text, khuyenMai.getNgayBatDau(), khuyenMai.getNgayKetThuc());
            this.loadTableDienThoai(listSPKM);
        }
    }//GEN-LAST:event_cbbLocHangActionPerformed

    private void cbbLocMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocMauSacActionPerformed
        // CBB lọc màu sắc khi thêm Điện thoại vào KM
        int row = this.tblKhuyenMai.getSelectedRow();
        UUID id = list1.get(row).getIdKhuyenMai();
        String text = this.cbbLocMauSac.getSelectedItem().toString();
        KhuyenMai khuyenMai = getFormData();
        if (cbbLocMauSac.getSelectedIndex() == 0) {
            listSPKM = this.chiTietDienThoaiImpl.getAllCTDTNotInKMTrung(khuyenMai.getNgayBatDau(), khuyenMai.getNgayKetThuc());
            this.loadTableDienThoai(listSPKM);
        } else {
            listSPKM = this.chiTietDienThoaiImpl.getAllCTDienThoaiByMauSac(text, khuyenMai.getNgayBatDau(), khuyenMai.getNgayKetThuc());
            this.loadTableDienThoai(listSPKM);
        }
    }//GEN-LAST:event_cbbLocMauSacActionPerformed

    private void cbbLocTinhTrangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocTinhTrangActionPerformed
        // CBB lọc Màu sắc khi thêm Điện thoại vào KM
        int row = this.tblKhuyenMai.getSelectedRow();
        UUID id = list1.get(row).getIdKhuyenMai();
        String text = this.cbbLocTinhTrang.getSelectedItem().toString();
        KhuyenMai khuyenMai = getFormData();
        if (cbbLocTinhTrang.getSelectedIndex() == 0) {
            listSPKM = this.chiTietDienThoaiImpl.getAllCTDTNotInKMTrung(khuyenMai.getNgayBatDau(), khuyenMai.getNgayKetThuc());
            this.loadTableDienThoai(listSPKM);
        } else {
            Integer tinhTrang = Integer.parseInt(text);
            listSPKM = this.chiTietDienThoaiImpl.getAllCTDienThoaiByTinhTrang(tinhTrang, khuyenMai.getNgayBatDau(), khuyenMai.getNgayKetThuc());
            this.loadTableDienThoai(listSPKM);
        }
    }//GEN-LAST:event_cbbLocTinhTrangActionPerformed

    private void txtThoiGianBDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtThoiGianBDMouseClicked
        // TXT Thời gian bắt đầu đầu click
        tpThoiGianBatDau.showPopup(this, (getWidth() - tpThoiGianBatDau.getPreferredSize().width) / 2, (getHeight() - tpThoiGianBatDau.getPreferredSize().height) / 2);
    }//GEN-LAST:event_txtThoiGianBDMouseClicked

    private void txtThoiGianKTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtThoiGianKTMouseClicked
        // TXT Thời gian kết thúc đầu click
        tpThoiGianKetThuc.showPopup(this, (getWidth() - tpThoiGianKetThuc.getPreferredSize().width) / 2, (getHeight() - tpThoiGianKetThuc.getPreferredSize().height) / 2);
    }//GEN-LAST:event_txtThoiGianKTMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private pro1041.team_3.swing.ButtonCustom btnClear;
    private pro1041.team_3.swing.ButtonCustom btnSua;
    private pro1041.team_3.swing.ButtonCustom btnThem;
    private pro1041.team_3.swing.ButtonCustom btnThemDienThoaiKM;
    private pro1041.team_3.swing.ButtonCustom btnThemSPKM;
    private pro1041.team_3.swing.ButtonCustom btnThemSpKM;
    private pro1041.team_3.swing.ButtonCustom btnThoat;
    private pro1041.team_3.swing.ButtonCustom btnXoa;
    private pro1041.team_3.swing.ButtonCustom buttonCustom2;
    private pro1041.team_3.swing.Combobox cbbLoaiKM;
    private pro1041.team_3.swing.Combobox cbbLocDienThoai;
    private pro1041.team_3.swing.Combobox cbbLocDienThoai1;
    private pro1041.team_3.swing.Combobox cbbLocHang;
    private pro1041.team_3.swing.Combobox cbbLocHang1;
    private pro1041.team_3.swing.Combobox cbbLocKM;
    private pro1041.team_3.swing.Combobox cbbLocMauSac;
    private pro1041.team_3.swing.Combobox cbbLocMauSac1;
    private pro1041.team_3.swing.Combobox cbbLocTinhTrang;
    private pro1041.team_3.swing.Combobox cbbLocTinhTrang1;
    private pro1041.team_3.swing.DateChooser dlChonNgayBatDau;
    private pro1041.team_3.swing.DateChooser dlChonNgayKetThuc;
    private javax.swing.JDialog dlChonSanPhamKm;
    private javax.swing.JDialog dlThemDienThoaiVaoKm;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jspTblAllSpChiTiet;
    private javax.swing.JScrollPane jspTblDienThoaiChon;
    private javax.swing.JScrollPane jspTblDienThoaiThem;
    private javax.swing.JScrollPane jspTblKhuyenMai;
    private javax.swing.JLabel lblLoai;
    private pro1041.team_3.swing.config.Table tblAllSpChiTiet;
    private pro1041.team_3.swing.config.Table tblDienThoaiChon;
    private pro1041.team_3.swing.config.Table tblDienThoaiThem;
    private pro1041.team_3.swing.config.Table tblKhuyenMai;
    private pro1041.team_3.swing.TimePicker tpThoiGianBatDau;
    private pro1041.team_3.swing.TimePicker tpThoiGianKetThuc;
    private pro1041.team_3.swing.TextField txtMa;
    private pro1041.team_3.swing.TextField txtMucKM;
    private pro1041.team_3.swing.TextField txtNgayBD;
    private pro1041.team_3.swing.TextField txtNgayKT;
    private pro1041.team_3.swing.TextField txtTen;
    private pro1041.team_3.swing.TextField txtThoiGianBD;
    private pro1041.team_3.swing.TextField txtThoiGianKT;
    private pro1041.team_3.swing.TextField txtTimDTKM;
    private pro1041.team_3.swing.TextField txtTimKhuyenMai;
    // End of variables declaration//GEN-END:variables
}
