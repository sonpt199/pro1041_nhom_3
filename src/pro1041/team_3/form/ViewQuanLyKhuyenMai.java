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
import pro1041.team_3.domainModel.DienThoaiKhuyenMai;
import pro1041.team_3.domainModel.KhuyenMai;
import pro1041.team_3.dto.ChiTietDienThoaiResponse;
import pro1041.team_3.dto.DienThoaiKhuyenMaiDto;
import pro1041.team_3.dto.KhuyenMaiDto;
import pro1041.team_3.dto.KhuyenMaiReQuestDto;
import pro1041.team_3.service.impl.ChiTietDienThoaiImpl;
import pro1041.team_3.service.impl.DienThoaiKhuyenMaiServiceImpl;
import pro1041.team_3.service.impl.DienThoaiServiceImpl;
import pro1041.team_3.service.impl.KhuyenMaiServiceImpl;

/**
 *
 * @author trangdttph27721
 */
public class ViewQuanLyKhuyenMai extends javax.swing.JPanel {

    private ChiTietDienThoaiImpl chiTietDienThoaiImpl;
    private KhuyenMaiServiceImpl khuyenMaiImpl;
    private DienThoaiKhuyenMaiServiceImpl dienThoaiKhuyenMaiServiceImpl;
    private List<DienThoaiKhuyenMaiDto> list = new ArrayList<>();
    private List<KhuyenMaiReQuestDto> listDTKM = new ArrayList<>();
    private List<KhuyenMaiDto> list1 = new ArrayList<>();
    private List<ChiTietDienThoaiResponse> listSPKM;

    public ViewQuanLyKhuyenMai() {
        initComponents();
        khuyenMaiImpl = new KhuyenMaiServiceImpl();
        dienThoaiKhuyenMaiServiceImpl = new DienThoaiKhuyenMaiServiceImpl();
        chiTietDienThoaiImpl = new ChiTietDienThoaiImpl();
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

    public void loadTableSP(List<DienThoaiKhuyenMaiDto> lst) {
        DefaultTableModel model = (DefaultTableModel) tblAllSpChiTiet.getModel();
        model.setRowCount(0);
        while (model.getRowCount() != 0) {
            model.removeRow(0);
        }
        for (DienThoaiKhuyenMaiDto x : lst) {
            model.addRow(x.toDataRow());
        }
    }

    public void loadTableKM(List<KhuyenMaiDto> lst) {
        DefaultTableModel model = (DefaultTableModel) tblKhuyenMai.getModel();
        model.setRowCount(0);
        for (KhuyenMaiDto x : lst) {
            model.addRow(x.toDataRow());
        }
    }

    public void loadTableSPKM(List<ChiTietDienThoaiResponse> lst) {
        DefaultTableModel model = (DefaultTableModel) tblAllSpKM.getModel();
        model.setRowCount(0);
        for (ChiTietDienThoaiResponse x : lst) {
            model.addRow(x.toDataRowKM());
        }
    }

    public void loadTableDienThoai(List<ChiTietDienThoaiResponse> lst) {
        DefaultTableModel model = (DefaultTableModel) tblDienThoai.getModel();
        model.setRowCount(0);
        for (ChiTietDienThoaiResponse x : lst) {
            model.addRow(x.toDataRowKM());
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
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblAllSpKM = new javax.swing.JTable();
        btnThoai = new javax.swing.JButton();
        btnThemSPKM = new javax.swing.JButton();
        DSDienThoai = new javax.swing.JDialog();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblDienThoai = new javax.swing.JTable();
        btnThemDienThoaiKM = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAllSpChiTiet = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtTimKhuyenMai = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNgayKT = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        txtNgayBD = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtMucKM = new javax.swing.JTextField();
        txtTen = new javax.swing.JTextField();
        lblLoai = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbbLoaiKM = new javax.swing.JComboBox<>();
        btnClear = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKhuyenMai = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        cbbLocKM = new javax.swing.JComboBox<>();
        txtTimDTKM = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btnXoa = new javax.swing.JButton();
        btnThemSpKM = new javax.swing.JButton();

        danhSachSanPham.setSize(new java.awt.Dimension(655, 570));
        danhSachSanPham.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Danh sách điện thoại");
        danhSachSanPham.getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 211, -1));

        tblAllSpKM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã CT điện thoại", "Tên điện thoại", "Tình trạng", "Imei", "Chọn"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblAllSpKM);

        danhSachSanPham.getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 598, -1));

        btnThoai.setBackground(new java.awt.Color(0, 255, 255));
        btnThoai.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnThoai.setText("Thoát");
        btnThoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoaiActionPerformed(evt);
            }
        });
        danhSachSanPham.getContentPane().add(btnThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 490, -1, -1));

        btnThemSPKM.setBackground(new java.awt.Color(0, 255, 255));
        btnThemSPKM.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnThemSPKM.setText("Thêm");
        btnThemSPKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSPKMActionPerformed(evt);
            }
        });
        danhSachSanPham.getContentPane().add(btnThemSPKM, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 490, -1, -1));

        DSDienThoai.setSize(new java.awt.Dimension(655, 570));
        DSDienThoai.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Danh sách điện thoại");
        DSDienThoai.getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 211, -1));

        tblDienThoai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã CT điện thoại", "Tên điện thoại", "Tình trạng", "Imei", "Chọn"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tblDienThoai);

        DSDienThoai.getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 598, -1));

        btnThemDienThoaiKM.setBackground(new java.awt.Color(0, 255, 255));
        btnThemDienThoaiKM.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnThemDienThoaiKM.setText("Thêm");
        btnThemDienThoaiKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemDienThoaiKMActionPerformed(evt);
            }
        });
        DSDienThoai.getContentPane().add(btnThemDienThoaiKM, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 490, -1, -1));

        btnThoat.setBackground(new java.awt.Color(0, 255, 255));
        btnThoat.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnThoat.setText("Thoát");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });
        DSDienThoai.getContentPane().add(btnThoat, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 490, -1, -1));

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1272, 748));
        setMinimumSize(new java.awt.Dimension(1272, 748));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblAllSpChiTiet.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        tblAllSpChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã điện thoại", "Tên điện thoại", "Hãng", "Màu sắc", "imei", "Giá bán (VNĐ)", "Giá còn lại (VNĐ)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblAllSpChiTiet);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 412, 1248, 323));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Danh sách sản phẩm");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 383, -1, -1));

        txtTimKhuyenMai.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        txtTimKhuyenMai.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKhuyenMaiCaretUpdate(evt);
            }
        });
        add(txtTimKhuyenMai, new org.netbeans.lib.awtextra.AbsoluteConstraints(186, 23, 231, -1));

        jPanel8.setBackground(new java.awt.Color(234, 242, 235));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel4.setText("Loại khuyến mại");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel6.setText("Mã khuyến mại");

        txtNgayKT.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtNgayKT.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel7.setText("Tên khuyến mại");

        txtMa.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtMa.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));

        txtNgayBD.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtNgayBD.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel10.setText("Ngày bắt đầu:");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel13.setText("Mức khuyến mại");

        txtMucKM.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtMucKM.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));

        txtTen.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtTen.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));

        lblLoai.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblLoai.setText("%");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel5.setText("Ngày kết thúc:");

        cbbLoaiKM.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        cbbLoaiKM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Theo %", "Theo tiền mặt" }));
        cbbLoaiKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLoaiKMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addGap(22, 22, 22)
                            .addComponent(txtMa))
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel10)
                                .addComponent(jLabel5))
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addGap(31, 31, 31)
                                    .addComponent(txtNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addGap(27, 27, 27)
                                    .addComponent(txtNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(5, 5, 5))
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addGap(18, 18, 18)
                            .addComponent(txtTen)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(txtMucKM, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblLoai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(cbbLoaiKM, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 18, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(26, 26, 26)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbbLoaiKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtMucKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLoai))
                .addContainerGap())
        );

        add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(896, 19, -1, 322));

        btnClear.setBackground(new java.awt.Color(0, 255, 255));
        btnClear.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnClear.setText("Làm mới");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(1169, 359, -1, 35));

        btnThem.setBackground(new java.awt.Color(0, 255, 255));
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(937, 359, 73, 35));

        btnSua.setBackground(new java.awt.Color(0, 255, 255));
        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        add(btnSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(1057, 359, 64, 35));

        tblKhuyenMai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã khuyến mại", "Tên khuyến mại", "Ngày bắt đầu", "Ngày kết thúc", "Mức khuyến mại", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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
        jScrollPane2.setViewportView(tblKhuyenMai);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 865, 302));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Danh sách khuyến mại");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 24, -1, -1));

        cbbLocKM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sắp diễn ra", "Đang diễn ra", "Dừng" }));
        cbbLocKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLocKMActionPerformed(evt);
            }
        });
        add(cbbLocKM, new org.netbeans.lib.awtextra.AbsoluteConstraints(478, 24, 151, -1));

        txtTimDTKM.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        txtTimDTKM.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimDTKMCaretUpdate(evt);
            }
        });
        add(txtTimDTKM, new org.netbeans.lib.awtextra.AbsoluteConstraints(187, 382, 231, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel8.setText("Lọc:");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(435, 26, -1, -1));

        btnXoa.setBackground(new java.awt.Color(0, 255, 255));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnXoa.setText("Xóa 1 sản phẩm");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(704, 377, -1, 35));

        btnThemSpKM.setBackground(new java.awt.Color(0, 255, 255));
        btnThemSpKM.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnThemSpKM.setText("Thêm sp vào KM");
        btnThemSpKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSpKMActionPerformed(evt);
            }
        });
        add(btnThemSpKM, new org.netbeans.lib.awtextra.AbsoluteConstraints(551, 377, -1, 35));
    }// </editor-fold>//GEN-END:initComponents

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

    private void cbbLoaiKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLoaiKMActionPerformed
        // TODO add your handling code here:
        if (cbbLoaiKM.getSelectedIndex() == 0) {
            this.lblLoai.setText("%");
        } else {
            this.lblLoai.setText("VND");
        }
    }//GEN-LAST:event_cbbLoaiKMActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:

        this.loadTableSPKM(chiTietDienThoaiImpl.getAllTrangThai(0));
        danhSachSanPham.setVisible(true);
        danhSachSanPham.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnThemSPKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSPKMActionPerformed
        // TODO add your handling code here:
        List<Integer> lst = new ArrayList<>();
        List<KhuyenMaiReQuestDto> lstKMRequest = new ArrayList<>();
        int row = tblAllSpKM.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Mời bạn chọn sản phẩm");
            return;
        }
        for (int i = 0; i < tblAllSpKM.getRowCount(); i++) {
            Boolean check = (boolean) tblAllSpKM.getValueAt(i, 4);
            if (check) {
                lst.add(i);
            }
        }

        for (int i = 0; i < listSPKM.size(); i++) {
            for (Integer x : lst) {
                if (i == x) {
                    KhuyenMai khuyenMai = this.getFormData();
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

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        clear();
        loadTableKM(khuyenMaiImpl.getAllResponse());
        list.clear();
        loadTableSP(list);
    }//GEN-LAST:event_btnClearActionPerformed

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

    private void cbbLocKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocKMActionPerformed
        // TODO add your handling code here:
        int chon = cbbLoaiKM.getSelectedIndex();
        if (chon == 0) {
            loadTableKM(khuyenMaiImpl.findNgayTuongLai());
        }
    }//GEN-LAST:event_cbbLocKMActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        int row = this.tblKhuyenMai.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Bạn cần chọn một khuyến mại");
            return;
        }
        UUID id = list1.get(row).getIdKhuyenMai();
        KhuyenMai khuyenMai = this.getFormData();
        khuyenMai.setId(id);
        String ketQua = khuyenMaiImpl.update(khuyenMai);
        if (ketQua.equals("Sửa thành công")) {
            loadTableKM(khuyenMaiImpl.getAllResponse());
        }
        JOptionPane.showMessageDialog(this, ketQua);
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThemSpKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSpKMActionPerformed
        // TODO add your handling code here:
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
        this.loadTableDienThoai(chiTietDienThoaiImpl.getAllDienThoaiNotInKM(id));
        DSDienThoai.setVisible(true);
        DSDienThoai.setLocationRelativeTo(null);

    }//GEN-LAST:event_btnThemSpKMActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
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

    private void btnThoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoaiActionPerformed
        // TODO add your handling code here:
        danhSachSanPham.setVisible(false);
    }//GEN-LAST:event_btnThoaiActionPerformed

    private void btnThemDienThoaiKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemDienThoaiKMActionPerformed
        // TODO add your handling code here:
        int row = this.tblKhuyenMai.getSelectedRow();
//        if (row == -1) {
//            JOptionPane.showMessageDialog(this, "Bạn cần chọn một khuyến mại");
//            return;
//        }
        UUID id = list1.get(row).getIdKhuyenMai();
        KhuyenMai khuyenMai = this.getFormData();
        khuyenMai.setId(id);
        int row1 = tblDienThoai.getSelectedRow();
        if (row1 == -1) {
            JOptionPane.showMessageDialog(this, "Mời bạn chọn sản phẩm");
            return;
        }
        List<Integer> lst = new ArrayList<>();
        List<KhuyenMaiReQuestDto> lstKMRequest = new ArrayList<>();
        for (int i = 0; i < tblDienThoai.getRowCount(); i++) {
            Boolean check = (boolean) tblDienThoai.getValueAt(i, 4);
            if (check) {
                lst.add(i);
            }
        }

        for (int i = 0; i < listSPKM.size(); i++) {
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog DSDienThoai;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemDienThoaiKM;
    private javax.swing.JButton btnThemSPKM;
    private javax.swing.JButton btnThemSpKM;
    private javax.swing.JButton btnThoai;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbbLoaiKM;
    private javax.swing.JComboBox<String> cbbLocKM;
    private javax.swing.JDialog danhSachSanPham;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblLoai;
    private javax.swing.JTable tblAllSpChiTiet;
    private javax.swing.JTable tblAllSpKM;
    private javax.swing.JTable tblDienThoai;
    private javax.swing.JTable tblKhuyenMai;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtMucKM;
    private javax.swing.JTextField txtNgayBD;
    private javax.swing.JTextField txtNgayKT;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTimDTKM;
    private javax.swing.JTextField txtTimKhuyenMai;
    // End of variables declaration//GEN-END:variables
}
