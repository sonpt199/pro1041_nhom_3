package pro1041.team_3.form;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
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
 * @author trangdttph27721
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
        DefaultTableModel model = (DefaultTableModel) tblAllSpKM.getModel();
        model.setRowCount(0);
        int index = 1;
        for (ChiTietDienThoaiResponse x : lst) {
            model.addRow(x.toDataRowKM(index));
            index++;
        }
    }

    public void loadTableDienThoai(List<ChiTietDienThoaiResponse> lst) {
        DefaultTableModel model = (DefaultTableModel) tblDienThoai.getModel();
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
        String ngayBD = txtNgayBD.getText().trim();
        String ngayKT = txtNgayKT.getText().trim();
        String mucKM = txtMucKM.getText().trim();
        float gia = 0;
        Float phanTram = null;
        Float tienMat = null;
        Date ngay1 = null;
        Date ngay2 = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            ngay1 = sdf.parse(ngayBD);
            ngay2 = sdf.parse(ngayKT);
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
            JOptionPane.showMessageDialog(this, "Mức giá phai la so nguyen duong");
            return null;
        }

        KhuyenMai khuyenMai = new KhuyenMai();
        LocalDateTime time = LocalDateTime.now();
        String maKM = "KM" + time.getSecond() + time.getMinute() + time.getHour();
        khuyenMai.setMa(maKM);
        khuyenMai.setNgayBatDau(ngay1);
        khuyenMai.setNgayKetThuc(ngay2);
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

        danhSachSanPham = new javax.swing.JDialog();
        btnThoai = new javax.swing.JButton();
        btnThemSPKM = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        cbbLocDienThoai1 = new pro1041.team_3.swing.ComboBoxSuggestion();
        jPanel9 = new javax.swing.JPanel();
        cbbLocHang1 = new pro1041.team_3.swing.ComboBoxSuggestion();
        jPanel10 = new javax.swing.JPanel();
        cbbLocMauSac1 = new pro1041.team_3.swing.ComboBoxSuggestion();
        jPanel11 = new javax.swing.JPanel();
        cbbLocTinhTrang1 = new pro1041.team_3.swing.ComboBoxSuggestion();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAllSpKM = new pro1041.team_3.swing.config.Table();
        DSDienThoai = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        btnThemDienThoaiKM = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        cbbLocDienThoai = new pro1041.team_3.swing.ComboBoxSuggestion();
        jPanel3 = new javax.swing.JPanel();
        cbbLocHang = new pro1041.team_3.swing.ComboBoxSuggestion();
        jPanel4 = new javax.swing.JPanel();
        cbbLocMauSac = new pro1041.team_3.swing.ComboBoxSuggestion();
        jPanel5 = new javax.swing.JPanel();
        cbbLocTinhTrang = new pro1041.team_3.swing.ComboBoxSuggestion();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDienThoai = new pro1041.team_3.swing.config.Table();
        comboSuggestionUI1 = new pro1041.team_3.swing.ComboSuggestionUI();
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
        jLabel8 = new javax.swing.JLabel();
        cbbLocKM = new pro1041.team_3.swing.Combobox();
        btnThem = new pro1041.team_3.swing.ButtonCustom();
        btnXoa = new pro1041.team_3.swing.ButtonCustom();
        btnSua = new pro1041.team_3.swing.ButtonCustom();
        btnThemSpKM = new pro1041.team_3.swing.ButtonCustom();
        btnClear = new pro1041.team_3.swing.ButtonCustom();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblAllSpChiTiet = new pro1041.team_3.swing.config.Table();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblKhuyenMai = new pro1041.team_3.swing.config.Table();
        txtTimKhuyenMai = new pro1041.team_3.swing.TextField();
        txtTimDTKM = new pro1041.team_3.swing.TextField();
        jPanel12 = new javax.swing.JPanel();

        danhSachSanPham.setMinimumSize(new java.awt.Dimension(730, 570));
        danhSachSanPham.setPreferredSize(new java.awt.Dimension(730, 570));
        danhSachSanPham.setSize(new java.awt.Dimension(730, 570));
        danhSachSanPham.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnThoai.setBackground(new java.awt.Color(0, 255, 255));
        btnThoai.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnThoai.setText("Thoát");
        btnThoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoaiActionPerformed(evt);
            }
        });
        danhSachSanPham.getContentPane().add(btnThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 480, -1, -1));

        btnThemSPKM.setBackground(new java.awt.Color(0, 255, 255));
        btnThemSPKM.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnThemSPKM.setText("Thêm");
        btnThemSPKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSPKMActionPerformed(evt);
            }
        });
        danhSachSanPham.getContentPane().add(btnThemSPKM, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 480, -1, -1));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách điện thoại", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Điện thoại", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 13))); // NOI18N
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbbLocDienThoai1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All" }));
        cbbLocDienThoai1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLocDienThoai1ActionPerformed(evt);
            }
        });
        jPanel7.add(cbbLocDienThoai1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jPanel6.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 160, 70));

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hãng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 13))); // NOI18N
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbbLocHang1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All" }));
        cbbLocHang1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLocHang1ActionPerformed(evt);
            }
        });
        jPanel9.add(cbbLocHang1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 20, -1, -1));

        jPanel6.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, 160, 70));

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Màu sắc", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 13))); // NOI18N
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbbLocMauSac1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All" }));
        cbbLocMauSac1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLocMauSac1ActionPerformed(evt);
            }
        });
        jPanel10.add(cbbLocMauSac1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 20, -1, -1));

        jPanel6.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 30, 160, 70));

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tình trạng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 13))); // NOI18N
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbbLocTinhTrang1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "100" }));
        cbbLocTinhTrang1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLocTinhTrang1ActionPerformed(evt);
            }
        });
        jPanel11.add(cbbLocTinhTrang1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 20, -1, -1));

        jPanel6.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 30, 160, 70));

        tblAllSpKM.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblAllSpKM);
        if (tblAllSpKM.getColumnModel().getColumnCount() > 0) {
            tblAllSpKM.getColumnModel().getColumn(0).setPreferredWidth(10);
            tblAllSpKM.getColumnModel().getColumn(1).setPreferredWidth(25);
            tblAllSpKM.getColumnModel().getColumn(3).setPreferredWidth(25);
            tblAllSpKM.getColumnModel().getColumn(4).setPreferredWidth(25);
            tblAllSpKM.getColumnModel().getColumn(5).setPreferredWidth(25);
            tblAllSpKM.getColumnModel().getColumn(6).setPreferredWidth(25);
        }

        jPanel6.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 710, 360));

        danhSachSanPham.getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 520));

        DSDienThoai.setMinimumSize(new java.awt.Dimension(730, 570));
        DSDienThoai.setPreferredSize(new java.awt.Dimension(730, 570));
        DSDienThoai.setSize(new java.awt.Dimension(730, 570));
        DSDienThoai.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách điện thoại", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnThemDienThoaiKM.setBackground(new java.awt.Color(0, 255, 255));
        btnThemDienThoaiKM.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnThemDienThoaiKM.setText("Thêm");
        btnThemDienThoaiKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemDienThoaiKMActionPerformed(evt);
            }
        });
        jPanel1.add(btnThemDienThoaiKM, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 480, -1, -1));

        btnThoat.setBackground(new java.awt.Color(0, 255, 255));
        btnThoat.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnThoat.setText("Thoát");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });
        jPanel1.add(btnThoat, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 480, -1, -1));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Điện thoại", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 13))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbbLocDienThoai.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All" }));
        cbbLocDienThoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLocDienThoaiActionPerformed(evt);
            }
        });
        jPanel2.add(cbbLocDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 20, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 150, 60));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hãng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 13))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbbLocHang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All" }));
        cbbLocHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLocHangActionPerformed(evt);
            }
        });
        jPanel3.add(cbbLocHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 20, -1, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, 150, 60));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Màu sắc", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 13))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbbLocMauSac.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All" }));
        cbbLocMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLocMauSacActionPerformed(evt);
            }
        });
        jPanel4.add(cbbLocMauSac, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 20, -1, -1));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 30, 150, 60));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tình trạng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 13))); // NOI18N
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbbLocTinhTrang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "100" }));
        cbbLocTinhTrang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLocTinhTrangActionPerformed(evt);
            }
        });
        jPanel5.add(cbbLocTinhTrang, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 20, -1, -1));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 30, 150, 60));

        tblDienThoai.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblDienThoai);
        if (tblDienThoai.getColumnModel().getColumnCount() > 0) {
            tblDienThoai.getColumnModel().getColumn(0).setPreferredWidth(3);
            tblDienThoai.getColumnModel().getColumn(1).setPreferredWidth(25);
            tblDienThoai.getColumnModel().getColumn(3).setPreferredWidth(20);
            tblDienThoai.getColumnModel().getColumn(4).setPreferredWidth(20);
            tblDienThoai.getColumnModel().getColumn(5).setPreferredWidth(20);
            tblDienThoai.getColumnModel().getColumn(6).setPreferredWidth(15);
        }

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 710, 360));

        DSDienThoai.getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 520));

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
        txtMa.setFont(new java.awt.Font("Nunito", 0, 13)); // NOI18N
        txtMa.setLabelText("Mã khuyến mại");
        jPanel8.add(txtMa, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 290, -1));

        txtTen.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtTen.setLabelText("Tên khuyến mại");
        jPanel8.add(txtTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 290, -1));

        txtNgayBD.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtNgayBD.setLabelText("Ngày bắt đầu");
        jPanel8.add(txtNgayBD, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 290, -1));

        txtNgayKT.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtNgayKT.setLabelText("Ngày kết thúc");
        jPanel8.add(txtNgayKT, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 290, -1));

        cbbLoaiKM.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Theo %", "Theo tiền mặt" }));
        cbbLoaiKM.setLabeText("");
        cbbLoaiKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLoaiKMActionPerformed(evt);
            }
        });
        jPanel8.add(cbbLoaiKM, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, 180, 40));

        txtMucKM.setFont(new java.awt.Font("Nunito", 0, 13)); // NOI18N
        txtMucKM.setLabelText("Mức khuyến mại");
        jPanel8.add(txtMucKM, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 240, -1));

        jLabel2.setFont(new java.awt.Font("Nunito", 0, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(3, 155, 216));
        jLabel2.setText("Loại khuyến mại");
        jPanel8.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));

        add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 20, 340, 340));

        jLabel8.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
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
        jScrollPane3.setViewportView(tblAllSpChiTiet);
        if (tblAllSpChiTiet.getColumnModel().getColumnCount() > 0) {
            tblAllSpChiTiet.getColumnModel().getColumn(0).setPreferredWidth(5);
            tblAllSpChiTiet.getColumnModel().getColumn(1).setPreferredWidth(55);
            tblAllSpChiTiet.getColumnModel().getColumn(3).setPreferredWidth(40);
            tblAllSpChiTiet.getColumnModel().getColumn(4).setPreferredWidth(40);
            tblAllSpChiTiet.getColumnModel().getColumn(6).setPreferredWidth(50);
            tblAllSpChiTiet.getColumnModel().getColumn(7).setPreferredWidth(50);
        }

        add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 1130, 340));

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
        jScrollPane6.setViewportView(tblKhuyenMai);
        if (tblKhuyenMai.getColumnModel().getColumnCount() > 0) {
            tblKhuyenMai.getColumnModel().getColumn(0).setPreferredWidth(8);
            tblKhuyenMai.getColumnModel().getColumn(1).setPreferredWidth(30);
            tblKhuyenMai.getColumnModel().getColumn(2).setPreferredWidth(60);
            tblKhuyenMai.getColumnModel().getColumn(5).setPreferredWidth(50);
        }

        add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 760, 270));

        txtTimKhuyenMai.setFont(new java.awt.Font("Nunito", 0, 14)); // NOI18N
        txtTimKhuyenMai.setLabelText("Tìm kiếm khuyến mại theo mã và tên");
        txtTimKhuyenMai.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKhuyenMaiCaretUpdate(evt);
            }
        });
        add(txtTimKhuyenMai, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 310, 40));

        txtTimDTKM.setFont(new java.awt.Font("Nunito", 0, 14)); // NOI18N
        txtTimDTKM.setLabelText("Tìm kiếm sản phẩm khuyến mại theo mã và tên");
        txtTimDTKM.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimDTKMCaretUpdate(evt);
            }
        });
        add(txtTimDTKM, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 380, 310, -1));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách khuyến mại", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Nunito", 1, 14))); // NOI18N
        add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 780, 360));
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemSPKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSPKMActionPerformed
        // TODO add your handling code here:
        List<Integer> lst = new ArrayList<>();
        List<KhuyenMaiReQuestDto> lstKMRequest = new ArrayList<>();
        int row = tblAllSpKM.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Mời bạn chọn sản phẩm");
            return;
        }
        int[] arrRow = tblDienThoai.getSelectedRows();
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
                    List<DienThoaiKhuyenMaiDto> lstDTKMDienRavaTgLai = dienThoaiKhuyenMaiServiceImpl.findDTKhuyenMaiDienRavaTgLai(listSPKM.get(i).getId());
                    for (DienThoaiKhuyenMaiDto a : lstDTKMDienRavaTgLai) {
                        //(StartA < EndB) && (EndA > StartB)
                        KhuyenMai KM = khuyenMaiImpl.findById(a.getIdKhuyenMai());
                        if (khuyenMai.getNgayBatDau().getTime() < KM.getNgayKetThuc().getTime()
                                && khuyenMai.getNgayKetThuc().getTime() > KM.getNgayBatDau().getTime()) {
                            JOptionPane.showMessageDialog(this, "Điện thoại " + a.getMaChiTietDienThoai() + " đã có khuyến mại " + KM.getTen());
                            return;
                        }
                    }
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
            danhSachSanPham.setVisible(false);

        }
        JOptionPane.showMessageDialog(this, ketQua);

    }//GEN-LAST:event_btnThemSPKMActionPerformed

    private void btnThoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoaiActionPerformed
        // TODO add your handling code here:
        danhSachSanPham.setVisible(false);
    }//GEN-LAST:event_btnThoaiActionPerformed

    private void btnThemDienThoaiKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemDienThoaiKMActionPerformed
        // TODO add your handling code here:
        int row = this.tblKhuyenMai.getSelectedRow();
        UUID id = list1.get(row).getIdKhuyenMai();
        KhuyenMai khuyenMai = this.getFormData();
        if (khuyenMai == null) {
            return;
        }
        khuyenMai.setId(id);
        int row1 = tblDienThoai.getSelectedRow();
        if (row1 == -1) {
            JOptionPane.showMessageDialog(this, "Mời bạn chọn sản phẩm");
            return;
        }
        List<Integer> lst = new ArrayList<>();
        int[] arrRow = tblDienThoai.getSelectedRows();
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

        List<ChiTietDienThoaiResponse> listCTDT = chiTietDienThoaiImpl.getAllDienThoaiNotInKM(id);
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
            DSDienThoai.setVisible(false);
        }
        JOptionPane.showMessageDialog(this, ketQua);
    }//GEN-LAST:event_btnThemDienThoaiKMActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        // TODO add your handling code here:
        DSDienThoai.setVisible(false);
    }//GEN-LAST:event_btnThoatActionPerformed

    private void cbbLocDienThoai1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocDienThoai1ActionPerformed
        // TODO add your handling code here:
        String text = this.cbbLocDienThoai1.getSelectedItem().toString();
        if (cbbLocDienThoai1.getSelectedIndex() == 0) {
            listSPKM = this.chiTietDienThoaiImpl.getAllTrangThai(0);
            this.loadTableSPKM(listSPKM);
        } else {
            listSPKM = this.chiTietDienThoaiImpl.getAllCTDienThoaiByDienThoai(text);
            this.loadTableSPKM(listSPKM);
        }

    }//GEN-LAST:event_cbbLocDienThoai1ActionPerformed

    private void cbbLocHang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocHang1ActionPerformed
        // TODO add your handling code here:
        String text = this.cbbLocHang1.getSelectedItem().toString();
        if (cbbLocHang1.getSelectedIndex() == 0) {
            listSPKM = this.chiTietDienThoaiImpl.getAllTrangThai(0);
            this.loadTableSPKM(listSPKM);
        } else {
            listSPKM = this.chiTietDienThoaiImpl.getAllCTDienThoaiByHang(text);
            this.loadTableSPKM(listSPKM);
        }
    }//GEN-LAST:event_cbbLocHang1ActionPerformed

    private void cbbLocMauSac1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocMauSac1ActionPerformed
        // TODO add your handling code here:
        String text = this.cbbLocMauSac1.getSelectedItem().toString();
        if (cbbLocMauSac1.getSelectedIndex() == 0) {
            listSPKM = this.chiTietDienThoaiImpl.getAllTrangThai(0);
            this.loadTableSPKM(listSPKM);
        } else {
            listSPKM = this.chiTietDienThoaiImpl.getAllCTDienThoaiByMauSac(text);
            this.loadTableSPKM(listSPKM);
        }
    }//GEN-LAST:event_cbbLocMauSac1ActionPerformed

    private void cbbLocDienThoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocDienThoaiActionPerformed
        // TODO add your handling code here:
        int row = this.tblKhuyenMai.getSelectedRow();
        UUID id = list1.get(row).getIdKhuyenMai();
        String text = this.cbbLocDienThoai.getSelectedItem().toString();
        if (cbbLocDienThoai.getSelectedIndex() == 0) {
            listSPKM = this.chiTietDienThoaiImpl.getAllDienThoaiNotInKM(id);
            this.loadTableDienThoai(listSPKM);
        } else {
            listSPKM = this.chiTietDienThoaiImpl.getAllCTDTNotInKMByDienThoai(id, text);
            this.loadTableDienThoai(listSPKM);
        }
    }//GEN-LAST:event_cbbLocDienThoaiActionPerformed

    private void cbbLocHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocHangActionPerformed
        // TODO add your handling code here:
        int row = this.tblKhuyenMai.getSelectedRow();
        UUID id = list1.get(row).getIdKhuyenMai();
        String text = this.cbbLocHang.getSelectedItem().toString();
        if (cbbLocHang.getSelectedIndex() == 0) {
            listSPKM = this.chiTietDienThoaiImpl.getAllDienThoaiNotInKM(id);
            this.loadTableDienThoai(listSPKM);
        } else {
            listSPKM = this.chiTietDienThoaiImpl.getAllCTDTNotInKMByHang(id, text);
            this.loadTableDienThoai(listSPKM);
        }
    }//GEN-LAST:event_cbbLocHangActionPerformed

    private void cbbLocMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocMauSacActionPerformed
        // TODO add your handling code here:
        int row = this.tblKhuyenMai.getSelectedRow();
        UUID id = list1.get(row).getIdKhuyenMai();
        String text = this.cbbLocMauSac.getSelectedItem().toString();
        if (cbbLocMauSac.getSelectedIndex() == 0) {
            listSPKM = this.chiTietDienThoaiImpl.getAllDienThoaiNotInKM(id);
            this.loadTableDienThoai(listSPKM);
        } else {
            listSPKM = this.chiTietDienThoaiImpl.getAllCTDTNotInKMByMauSac(id, text);
            this.loadTableDienThoai(listSPKM);
        }
    }//GEN-LAST:event_cbbLocMauSacActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        this.loadCbbDienThoai1();
        this.loadCbbHang1();
        this.loadCbbMauSac1();
        this.loadTableSPKM(chiTietDienThoaiImpl.getAllTrangThai(0));
        danhSachSanPham.setVisible(true);
        danhSachSanPham.setLocationRelativeTo(null);
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
            JOptionPane.showMessageDialog(this, "Bạn cần chọn một điện thoại khuyến mại");
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
        this.loadCbbDienThoai();
        this.loadCbbHang();
        this.loadCbbMauSac();
        this.loadTableDienThoai(chiTietDienThoaiImpl.getAllDienThoaiNotInKM(id));
        DSDienThoai.setVisible(true);
        DSDienThoai.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnThemSpKMActionPerformed

    private void tblKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhuyenMaiMouseClicked
        // TODO add your handling code here:
        int row = this.tblKhuyenMai.getSelectedRow();
        if (row == -1) {
            return;
        }
        KhuyenMaiDto x = list1.get(row);
        this.txtMa.setText(x.getMaKhuyenMai());
        this.txtTen.setText(x.getTenKhuyenMai());
        this.txtNgayBD.setText(x.getNgayBatDau().toString());
        this.txtNgayKT.setText(x.getNgayKetThuc().toString());

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

    private void cbbLocTinhTrangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocTinhTrangActionPerformed
        // TODO add your handling code here:
        int row = this.tblKhuyenMai.getSelectedRow();
        UUID id = list1.get(row).getIdKhuyenMai();
        String text = this.cbbLocTinhTrang.getSelectedItem().toString();
        
        if (cbbLocTinhTrang.getSelectedIndex() == 0) {
            listSPKM = this.chiTietDienThoaiImpl.getAllDienThoaiNotInKM(id);
            this.loadTableDienThoai(listSPKM);
        } else {
            Integer tinhTrang = Integer.parseInt(text);
            listSPKM = this.chiTietDienThoaiImpl.getAllCTDTNotInKMByTinhTrang(id, tinhTrang);
            this.loadTableDienThoai(listSPKM);
        }
        
    }//GEN-LAST:event_cbbLocTinhTrangActionPerformed

    private void cbbLocTinhTrang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocTinhTrang1ActionPerformed
        // TODO add your handling code here:
        String text = this.cbbLocTinhTrang1.getSelectedItem().toString();
        
        if (cbbLocTinhTrang1.getSelectedIndex() == 0) {
            listSPKM = this.chiTietDienThoaiImpl.getAllTrangThai(0);
            this.loadTableSPKM(listSPKM);
        } else {
            Integer tinhTrang = Integer.parseInt(text);
            listSPKM = this.chiTietDienThoaiImpl.getAllCTDienThoaiByTinhTrang(tinhTrang);
            this.loadTableSPKM(listSPKM);
        }
    }//GEN-LAST:event_cbbLocTinhTrang1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog DSDienThoai;
    private pro1041.team_3.swing.ButtonCustom btnClear;
    private pro1041.team_3.swing.ButtonCustom btnSua;
    private pro1041.team_3.swing.ButtonCustom btnThem;
    private javax.swing.JButton btnThemDienThoaiKM;
    private javax.swing.JButton btnThemSPKM;
    private pro1041.team_3.swing.ButtonCustom btnThemSpKM;
    private javax.swing.JButton btnThoai;
    private javax.swing.JButton btnThoat;
    private pro1041.team_3.swing.ButtonCustom btnXoa;
    private pro1041.team_3.swing.Combobox cbbLoaiKM;
    private pro1041.team_3.swing.ComboBoxSuggestion cbbLocDienThoai;
    private pro1041.team_3.swing.ComboBoxSuggestion cbbLocDienThoai1;
    private pro1041.team_3.swing.ComboBoxSuggestion cbbLocHang;
    private pro1041.team_3.swing.ComboBoxSuggestion cbbLocHang1;
    private pro1041.team_3.swing.Combobox cbbLocKM;
    private pro1041.team_3.swing.ComboBoxSuggestion cbbLocMauSac;
    private pro1041.team_3.swing.ComboBoxSuggestion cbbLocMauSac1;
    private pro1041.team_3.swing.ComboBoxSuggestion cbbLocTinhTrang;
    private pro1041.team_3.swing.ComboBoxSuggestion cbbLocTinhTrang1;
    private pro1041.team_3.swing.ComboSuggestionUI comboSuggestionUI1;
    private javax.swing.JDialog danhSachSanPham;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lblLoai;
    private pro1041.team_3.swing.config.Table tblAllSpChiTiet;
    private pro1041.team_3.swing.config.Table tblAllSpKM;
    private pro1041.team_3.swing.config.Table tblDienThoai;
    private pro1041.team_3.swing.config.Table tblKhuyenMai;
    private pro1041.team_3.swing.TextField txtMa;
    private pro1041.team_3.swing.TextField txtMucKM;
    private pro1041.team_3.swing.TextField txtNgayBD;
    private pro1041.team_3.swing.TextField txtNgayKT;
    private pro1041.team_3.swing.TextField txtTen;
    private pro1041.team_3.swing.TextField txtTimDTKM;
    private pro1041.team_3.swing.TextField txtTimKhuyenMai;
    // End of variables declaration//GEN-END:variables
}
