package pro1041.team_3.form;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pro1041.team_3.domainModel.ChiTietDienThoai;
import pro1041.team_3.domainModel.DienThoai;
import pro1041.team_3.domainModel.Hang;
import pro1041.team_3.domainModel.MauSac;
import pro1041.team_3.dto.ChiTietDienThoaiResponse;
import pro1041.team_3.service.impl.ChiTietDienThoaiImpl;
import pro1041.team_3.service.impl.DienThoaiServiceImpl;
import pro1041.team_3.service.impl.HangServiceImpl;
import pro1041.team_3.service.impl.MauSacServiceImpl;

/**
 *
 * @author trangdttph27721
 */
public class SanPhamForm extends javax.swing.JPanel {

    private DienThoaiServiceImpl dienThoaiImpl;
    private HangServiceImpl hangImpl;
    private MauSacServiceImpl mauSacImpl;
    private ChiTietDienThoaiImpl chiTietDTImpl;
    private List<ChiTietDienThoaiResponse> list = new ArrayList<>();
    private String _hinhAnh = null;
    public SanPhamForm() {
        initComponents();
        this.dienThoaiImpl = new DienThoaiServiceImpl();
        this.hangImpl = new HangServiceImpl();
        this.mauSacImpl = new MauSacServiceImpl();
        this.chiTietDTImpl = new ChiTietDienThoaiImpl();
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
        DefaultTableModel model = (DefaultTableModel) tblChiTietDienThoai.getModel();
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
        this.txtRam.setText("");
        this.cbbDienThoai.setSelectedIndex(0);
        this.cbbHang.setSelectedIndex(0);
        this.cbbMauSac.setSelectedIndex(0);
        this.rdoCon.setSelected(true);
        this.rdoMoi.setSelected(true);
        this.lblHinhAnh.setText("Image");
        this.lblHinhAnh.setIcon(null);
        _hinhAnh = null;
    }
    
    public ChiTietDienThoai getFormData() {
        String ma = this.txtMa.getText().trim();
        String giaBanStr = this.txtGiaBan.getText().trim();
        String imei = this.txtImei.getText().trim();
        String ramStr = this.txtRam.getText().trim();
        String boNhoStr = this.txtRom.getText().trim();
        String mota = this.txtMoTa.getText().trim();
        int trangThai = this.rdoCon.isSelected() == true ? 0 : this.rdoHet.isSelected() == true ? 1 : 2;
        int tinhTrang = this.rdoMoi.isSelected() == true ? 1 : 0;
        DienThoai dienThoai = (DienThoai) this.cbbDienThoai.getSelectedItem();
        Hang hang = (Hang) this.cbbHang.getSelectedItem();
        MauSac mauSac = (MauSac) this.cbbMauSac.getSelectedItem();
        
        if (ma.length() == 0 || giaBanStr.length() == 0 || imei.length() == 0 || ramStr.length() == 0 || boNhoStr.length() == 0) {
            JOptionPane.showMessageDialog(this, "Không được để trống");
            return null;
        }
        double giaBan = -1;
        float boNho = -1;
        float ram = -1;
        int sl = -1;
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
        ChiTietDienThoai chiTietDienThoai = new ChiTietDienThoai();
        chiTietDienThoai.setMa(ma);
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
        chiTietDienThoai.setHinhAnh(_hinhAnh);
        return chiTietDienThoai;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblChiTietDienThoai = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtTim = new javax.swing.JTextField();
        cbbHang = new javax.swing.JComboBox<Hang>();
        btnMauSac = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        cbbDienThoai = new javax.swing.JComboBox<DienThoai>();
        jLabel21 = new javax.swing.JLabel();
        cbbMauSac = new javax.swing.JComboBox<MauSac>();
        btnDienThoai = new javax.swing.JButton();
        btnHang = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        rdoMoi = new javax.swing.JRadioButton();
        rdoCu = new javax.swing.JRadioButton();
        jLabel19 = new javax.swing.JLabel();
        rdoCon = new javax.swing.JRadioButton();
        rdoHet = new javax.swing.JRadioButton();
        cbbLoaiSapXep = new javax.swing.JComboBox<>();
        btnSapXep = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblHinhAnh = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        txtGiaBan = new javax.swing.JTextField();
        txtMa = new javax.swing.JTextField();
        txtImei = new javax.swing.JTextField();
        txtRom = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtRam = new javax.swing.JTextField();
        rdoLoi = new javax.swing.JRadioButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();

        tblChiTietDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblChiTietDienThoai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Điện thoại", "Hãng", "Giá bán", "Màu sắc", "Imei", "RAM", "ROM", "Tinh trang", "Trang thai", "Mô tả", "Hình ảnh"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblChiTietDienThoai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChiTietDienThoaiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblChiTietDienThoai);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel4.setText("Tìm kiếm");

        txtTim.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtTim.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        txtTim.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimCaretUpdate(evt);
            }
        });

        cbbHang.setFont(new java.awt.Font("Segoe UI", 0, 13));
        cbbHang.setModel(new javax.swing.DefaultComboBoxModel<Hang>());

        btnMauSac.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnMauSac.setText("Edit");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel13.setText("Hãng điện thoại");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel18.setText("Điện thoại");

        cbbDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 13));
        cbbDienThoai.setModel(new javax.swing.DefaultComboBoxModel<DienThoai>());
        cbbDienThoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbDienThoaiActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel21.setText("Màu sắc");

        cbbMauSac.setFont(new java.awt.Font("Segoe UI", 0, 13));
        cbbMauSac.setModel(new javax.swing.DefaultComboBoxModel<MauSac>());

        btnDienThoai.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnDienThoai.setText("Edit");

        btnHang.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnHang.setText("Edit");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel17.setText("Trạng thái");

        buttonGroup1.add(rdoMoi);
        rdoMoi.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        rdoMoi.setText("Mới");

        buttonGroup1.add(rdoCu);
        rdoCu.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        rdoCu.setText("Cũ");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel19.setText("Tình trạng");

        buttonGroup2.add(rdoCon);
        rdoCon.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        rdoCon.setText("Đang bán");
        rdoCon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoConActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdoHet);
        rdoHet.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        rdoHet.setText("Đã bán");

        cbbLoaiSapXep.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Giá bán", "Số lượng" }));

        btnSapXep.setText("Sắp xếp");
        btnSapXep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSapXepActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblHinhAnh.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        lblHinhAnh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHinhAnh.setText("Image");
        lblHinhAnh.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 102, 255)));
        lblHinhAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhAnhMouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel8.setText("VND");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Mã điện thoại");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Giá bán");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("Mô tả");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setText("RAM");

        txtMoTa.setColumns(20);
        txtMoTa.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        txtMoTa.setRows(5);
        jScrollPane2.setViewportView(txtMoTa);

        txtGiaBan.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        txtGiaBan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));

        txtMa.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        txtMa.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));

        txtImei.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        txtImei.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));

        txtRom.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        txtRom.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Imei");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("ROM");

        txtRam.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        txtRam.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel15)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11)
                                            .addComponent(jLabel12)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel10))))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtRam, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtRom, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtImei, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel8))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lblHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtImei, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtRom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtRam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86))
        );

        buttonGroup2.add(rdoLoi);
        rdoLoi.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        rdoLoi.setText("Sản phẩm lỗi");

        btnThem.setBackground(new java.awt.Color(0, 255, 255));
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThem.setText("Lưu");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(0, 255, 255));
        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(0, 255, 255));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel18)
                                .addGap(71, 71, 71)
                                .addComponent(jLabel13)
                                .addGap(67, 67, 67)
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel19))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbbDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(btnDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbbHang, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(btnHang, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(btnMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(39, 39, 39)
                                .addComponent(jLabel17)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoCon)
                            .addComponent(rdoMoi))
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoCu)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rdoHet)
                                .addGap(18, 18, 18)
                                .addComponent(rdoLoi))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(225, 225, 225)
                        .addComponent(cbbLoaiSapXep, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(btnSapXep))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 894, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThem)
                        .addGap(67, 67, 67)
                        .addComponent(btnSua)
                        .addGap(46, 46, 46)
                        .addComponent(btnXoa)
                        .addGap(35, 35, 35))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbbLoaiSapXep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnSapXep)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel13)
                            .addComponent(jLabel21)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(rdoMoi)
                                .addComponent(jLabel19)
                                .addComponent(rdoCu)))
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbbDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(btnDienThoai))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbbHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(btnHang))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(btnMauSac))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(rdoCon)
                                        .addComponent(jLabel17))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(rdoHet)
                                        .addComponent(rdoLoi))))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 598, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnXoa)
                            .addComponent(btnSua)
                            .addComponent(btnThem))))
                .addContainerGap(35, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

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
        String hinhAnh = this.tblChiTietDienThoai.getValueAt(row, 11).toString();
        _hinhAnh = hinhAnh;
        this.txtRom.setText(rom);
        this.txtGiaBan.setText(giaBan);
        this.txtImei.setText(imei);
        this.txtMa.setText(ma);
        this.txtMoTa.setText(moTa == null ? "" : moTa);
        this.txtRam.setText(ram);

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
        this.lblHinhAnh.setText("Image");
        if (hinhAnh.equalsIgnoreCase("Image")) {
            this.lblHinhAnh.setText("Image");
            this.lblHinhAnh.setIcon(null);
        } else {
            this.lblHinhAnh.setText("");
            try {
                Image avartarImg = ImageIO.read(new File(hinhAnh));
                ImageIcon icon = new ImageIcon(avartarImg.getScaledInstance(this.lblHinhAnh.getWidth(), this.lblHinhAnh.getHeight(), Image.SCALE_SMOOTH));
                this.lblHinhAnh.setIcon(icon);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_tblChiTietDienThoaiMouseClicked

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

    private void btnSapXepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSapXepActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnSapXepActionPerformed

    private void lblHinhAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhAnhMouseClicked
        // TODO add your handling code here:
        try {
            JFileChooser jfc = new JFileChooser("D:\\Du_an_1_Nhom3\\src\\pro1041\\team_3\\icon");
            jfc.showOpenDialog(null);
            File file = jfc.getSelectedFile();
            Image img = ImageIO.read(file);
            _hinhAnh = file.getPath();
            this.lblHinhAnh.setText("");
            int wight = this.lblHinhAnh.getWidth();
            int height = this.lblHinhAnh.getHeight();
            this.lblHinhAnh.setIcon(new ImageIcon(img.getScaledInstance(wight, height, 0)));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_lblHinhAnhMouseClicked

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        try {
            int index = tblChiTietDienThoai.getSelectedRow();
            if (index == -1) {
                JOptionPane.showMessageDialog(this, "Bạn cần chọn một sản phẩm trên danh sách");
                return;
            }
            ChiTietDienThoai ctDT = this.getFormData();
            ctDT.setId(list.get(index).getId());
            if (ctDT == null) {
                return;
            }
            String ma = this.tblChiTietDienThoai.getValueAt(index, 0).toString();
            
            
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
        // TODO add your handling code here:
        try {
            int index = tblChiTietDienThoai.getSelectedRow();
            if (index == -1) {
                JOptionPane.showMessageDialog(this, "Bạn cần chọn một sản phẩm trên danh sách");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa dòng này không");
            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }
            String ma = this.tblChiTietDienThoai.getValueAt(index, 0).toString();
            String message = this.chiTietDTImpl.delete(ma);
            this.loadTable(this.chiTietDTImpl.getAllResponse());
            this.clearForm();
            JOptionPane.showMessageDialog(this, message);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Xóa thất bại");
            return;
        }
    }//GEN-LAST:event_btnXoaActionPerformed

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDienThoai;
    private javax.swing.JButton btnHang;
    private javax.swing.JButton btnMauSac;
    private javax.swing.JButton btnSapXep;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox<DienThoai> cbbDienThoai;
    private javax.swing.JComboBox<Hang> cbbHang;
    private javax.swing.JComboBox<String> cbbLoaiSapXep;
    private javax.swing.JComboBox<MauSac> cbbMauSac;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblHinhAnh;
    private javax.swing.JRadioButton rdoCon;
    private javax.swing.JRadioButton rdoCu;
    private javax.swing.JRadioButton rdoHet;
    private javax.swing.JRadioButton rdoLoi;
    private javax.swing.JRadioButton rdoMoi;
    private javax.swing.JTable tblChiTietDienThoai;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtImei;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtRam;
    private javax.swing.JTextField txtRom;
    private javax.swing.JTextField txtTim;
    // End of variables declaration//GEN-END:variables
}
