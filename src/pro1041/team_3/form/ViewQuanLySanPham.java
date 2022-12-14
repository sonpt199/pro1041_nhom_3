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
import pro1041.team_3.dto.DienThoaiDto;
import pro1041.team_3.dto.DienThoaiKhuyenMaiDto;
import pro1041.team_3.dto.HangDto;
import pro1041.team_3.dto.MauSacDto;
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
    private List<DienThoaiDto> listDT = new ArrayList<>();
    private List<HangDto> listHang = new ArrayList<>();
    private List<MauSacDto> listMS = new ArrayList<>();
    private String _hinhAnh = null;

    public ViewQuanLySanPham() {
        initComponents();
        //Edit scroll table
        tblChiTietDienThoai.fixTable(jspTblChiTietDienThoai);
        tblDienThoai.fixTable(jspTblDanhSachDT);
        tblHang.fixTable(jspTblHang);
        tblMS.fixTable(jspTblMS);
        this.txtMa.setEditable(false);
        this.dienThoaiImpl = new DienThoaiServiceImpl();
        this.hangImpl = new HangServiceImpl();
        this.mauSacImpl = new MauSacServiceImpl();
        this.chiTietDTImpl = new ChiTietDienThoaiImpl();
        this.dienThoaiKhuyenMaiServiceImpl = new DienThoaiKhuyenMaiServiceImpl();
        this.khuyenMaiServiceImpl = new KhuyenMaiServiceImpl();
        this.list = this.chiTietDTImpl.getAllResponse();
        this.listDT = this.dienThoaiImpl.getAllResponse();
        this.listHang = this.hangImpl.getAllResponse();
        this.listMS = this.mauSacImpl.getAllResponse();
        this.tinhTrangCuPanel.setVisible(false);
        this.loadCbbDienThoai();
        this.loadCbbHang();
        this.loadCbbMauSac();
        this.loadTable(list);
        this.loadTableDT(listDT);
        this.loadTableHang(listHang);
        this.loadTableMS(listMS);
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

    public void loadTableDT(List<DienThoaiDto> lstDT) {
        DefaultTableModel modelDT = (DefaultTableModel) this.tblDienThoai.getModel();
        modelDT.setRowCount(0);
        int index = 1;
        for (DienThoaiDto dt : lstDT) {
            modelDT.addRow(dt.toDataRow(index));
            index++;
        }
    }

    public void loadTableHang(List<HangDto> lstHang) {
        DefaultTableModel modelHang = (DefaultTableModel) this.tblHang.getModel();
        modelHang.setRowCount(0);
        int index = 1;
        for (HangDto hang : lstHang) {
            modelHang.addRow(hang.toDataRow(index));
            index++;
        }
    }

    public void loadTableMS(List<MauSacDto> lst) {
        DefaultTableModel modelMS = (DefaultTableModel) this.tblMS.getModel();
        modelMS.setRowCount(0);
        int index = 1;
        for (MauSacDto ms : lst) {
            modelMS.addRow(ms.toDataRow(index));
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

    public void clearFormDT() {
        this.txtMaDT.setText("");
        this.txtTenDT.setText("");
        txtTimKiemDT.setText("");
    }

    public void clearFormHang() {
        this.txtMaHangDT.setText("");
        this.txtTenHangDT.setText("");
        txtTimKiemHang.setText("");
    }

    public void clearFormMS() {
        this.txtMaMS.setText("");
        this.txtTenMS.setText("");
        txtTimKiemMS.setText("");
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

    public DienThoai getFormDataDT() {
        String maDT = this.txtMaDT.getText().trim();
        String tenDT = this.txtTenDT.getText().trim();
        DienThoai dt = new DienThoai();
        dt.setMa(maDT);
        dt.setTen(tenDT);
        return dt;
    }

    public Hang getFormDataHang() {
        String maHang = this.txtMaHangDT.getText().trim();
        String tenHang = this.txtTenHangDT.getText().trim();
        Hang hang = new Hang();
        hang.setMa(maHang);
        hang.setTen(tenHang);
        return hang;
    }

    public MauSac getFormDataMS() {
        String maMS = this.txtMaMS.getText().trim();
        String tenMS = this.txtTenMS.getText().trim();
        MauSac ms = new MauSac();
        ms.setMa(maMS);
        ms.setTen(tenMS);
        return ms;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        dlEditDienThoai = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jspTblDanhSachDT = new javax.swing.JScrollPane();
        tblDienThoai = new pro1041.team_3.swing.config.Table();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnTimKiemDT = new javax.swing.JLabel();
        txtMaDT = new pro1041.team_3.swing.TextField();
        txtTenDT = new pro1041.team_3.swing.TextField();
        txtTimKiemDT = new pro1041.team_3.swing.TextField();
        btnQuayLai = new pro1041.team_3.swing.ButtonCustom();
        btnClear = new pro1041.team_3.swing.ButtonCustom();
        btnSuaDT = new pro1041.team_3.swing.ButtonCustom();
        btnXoaDT = new pro1041.team_3.swing.ButtonCustom();
        btnThemDT = new pro1041.team_3.swing.ButtonCustom();
        dlEditHangDT = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        jspTblHang = new javax.swing.JScrollPane();
        tblHang = new pro1041.team_3.swing.config.Table();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        btnTimKiemHang = new javax.swing.JLabel();
        txtMaHangDT = new pro1041.team_3.swing.TextField();
        txtTenHangDT = new pro1041.team_3.swing.TextField();
        txtTimKiemHang = new pro1041.team_3.swing.TextField();
        btnQuayLaiHang = new pro1041.team_3.swing.ButtonCustom();
        btnClearHang = new pro1041.team_3.swing.ButtonCustom();
        btnSuaHangDT = new pro1041.team_3.swing.ButtonCustom();
        btnXoaHangDT = new pro1041.team_3.swing.ButtonCustom();
        btnThemHangDT = new pro1041.team_3.swing.ButtonCustom();
        dlEditMauSac = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        jspTblMS = new javax.swing.JScrollPane();
        tblMS = new pro1041.team_3.swing.config.Table();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        btnTimKiemHang1 = new javax.swing.JLabel();
        txtMaMS = new pro1041.team_3.swing.TextField();
        txtTenMS = new pro1041.team_3.swing.TextField();
        txtTimKiemMS = new pro1041.team_3.swing.TextField();
        btnQuayLaiMS = new pro1041.team_3.swing.ButtonCustom();
        btnClearMS = new pro1041.team_3.swing.ButtonCustom();
        btnSuaMS = new pro1041.team_3.swing.ButtonCustom();
        btnXoaMS = new pro1041.team_3.swing.ButtonCustom();
        btnThemMS = new pro1041.team_3.swing.ButtonCustom();
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
        btnEditDienThoai = new pro1041.team_3.swing.ButtonCustom();
        btnEditHangDT = new pro1041.team_3.swing.ButtonCustom();
        buttonCustom6 = new pro1041.team_3.swing.ButtonCustom();
        cbbTrangThai = new pro1041.team_3.swing.Combobox();
        cbbDienThoai = new pro1041.team_3.swing.ComboBoxSuggestion<DienThoai>();
        cbbHang = new pro1041.team_3.swing.ComboBoxSuggestion<Hang>();
        cbbMauSac = new pro1041.team_3.swing.ComboBoxSuggestion<MauSac>();
        jspTblChiTietDienThoai = new javax.swing.JScrollPane();
        tblChiTietDienThoai = new pro1041.team_3.swing.config.Table();
        jPanel3 = new javax.swing.JPanel();
        btnThem1 = new pro1041.team_3.swing.ButtonCustom();
        btnThem = new pro1041.team_3.swing.ButtonCustom();
        btnSua = new pro1041.team_3.swing.ButtonCustom();
        btnExport = new pro1041.team_3.swing.ButtonCustom();
        btnImport = new pro1041.team_3.swing.ButtonCustom();
        btnTaoQr = new pro1041.team_3.swing.ButtonCustom();

        dlEditDienThoai.setSize(new java.awt.Dimension(700, 630));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(700, 630));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblDienThoai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Mã", "Tên"
            }
        ));
        tblDienThoai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDienThoaiMouseClicked(evt);
            }
        });
        jspTblDanhSachDT.setViewportView(tblDienThoai);

        jPanel2.add(jspTblDanhSachDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 265, 650, 244));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Danh sách điện thoại");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 228, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(1, 181, 204));
        jLabel6.setText("Tìm kiếm");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(181, 32, -1, -1));

        btnTimKiemDT.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnTimKiemDT.setForeground(new java.awt.Color(1, 181, 204));
        btnTimKiemDT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/customSearch.png"))); // NOI18N
        btnTimKiemDT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTimKiemDTMouseClicked(evt);
            }
        });
        jPanel2.add(btnTimKiemDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 31, -1, -1));

        txtMaDT.setEditable(false);
        txtMaDT.setForeground(new java.awt.Color(102, 102, 102));
        txtMaDT.setLabelText("Mã điện thoại");
        txtMaDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaDTActionPerformed(evt);
            }
        });
        jPanel2.add(txtMaDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 82, 240, -1));

        txtTenDT.setForeground(new java.awt.Color(102, 102, 102));
        txtTenDT.setLabelText("Tên điện thoại");
        jPanel2.add(txtTenDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 146, 240, -1));

        txtTimKiemDT.setForeground(new java.awt.Color(102, 102, 102));
        txtTimKiemDT.setLabelText("");
        jPanel2.add(txtTimKiemDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(262, 9, 200, -1));

        btnQuayLai.setBackground(new java.awt.Color(153, 153, 153));
        btnQuayLai.setForeground(new java.awt.Color(255, 255, 255));
        btnQuayLai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/back.png"))); // NOI18N
        btnQuayLai.setText("Quay lai");
        btnQuayLai.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnQuayLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuayLaiActionPerformed(evt);
            }
        });
        jPanel2.add(btnQuayLai, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 530, 150, -1));

        btnClear.setBackground(new java.awt.Color(1, 181, 204));
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        btnClear.setText("Clear");
        btnClear.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        jPanel2.add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 530, 150, -1));

        btnSuaDT.setBackground(new java.awt.Color(1, 181, 204));
        btnSuaDT.setForeground(new java.awt.Color(255, 255, 255));
        btnSuaDT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/edit.png"))); // NOI18N
        btnSuaDT.setText("Sửa");
        btnSuaDT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSuaDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaDTActionPerformed(evt);
            }
        });
        jPanel2.add(btnSuaDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 140, 150, -1));

        btnXoaDT.setBackground(new java.awt.Color(255, 0, 0));
        btnXoaDT.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaDT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/delete.png"))); // NOI18N
        btnXoaDT.setText("Xóa");
        btnXoaDT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoaDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaDTActionPerformed(evt);
            }
        });
        jPanel2.add(btnXoaDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 200, 150, -1));

        btnThemDT.setBackground(new java.awt.Color(1, 181, 204));
        btnThemDT.setForeground(new java.awt.Color(255, 255, 255));
        btnThemDT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/add.png"))); // NOI18N
        btnThemDT.setText("Thêm");
        btnThemDT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThemDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemDTActionPerformed(evt);
            }
        });
        jPanel2.add(btnThemDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 80, 150, -1));

        javax.swing.GroupLayout dlEditDienThoaiLayout = new javax.swing.GroupLayout(dlEditDienThoai.getContentPane());
        dlEditDienThoai.getContentPane().setLayout(dlEditDienThoaiLayout);
        dlEditDienThoaiLayout.setHorizontalGroup(
            dlEditDienThoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        dlEditDienThoaiLayout.setVerticalGroup(
            dlEditDienThoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        dlEditHangDT.setSize(new java.awt.Dimension(700, 630));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(700, 630));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Mã", "Tên"
            }
        ));
        tblHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHangMouseClicked(evt);
            }
        });
        jspTblHang.setViewportView(tblHang);

        jPanel5.add(jspTblHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 265, 650, 244));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel22.setText("Danh sách hãng điện thoại");
        jPanel5.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 228, -1, -1));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(1, 181, 204));
        jLabel23.setText("Tìm kiếm");
        jPanel5.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(181, 32, -1, -1));

        btnTimKiemHang.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnTimKiemHang.setForeground(new java.awt.Color(1, 181, 204));
        btnTimKiemHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/customSearch.png"))); // NOI18N
        btnTimKiemHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTimKiemHangMouseClicked(evt);
            }
        });
        jPanel5.add(btnTimKiemHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 31, -1, -1));

        txtMaHangDT.setEditable(false);
        txtMaHangDT.setForeground(new java.awt.Color(102, 102, 102));
        txtMaHangDT.setLabelText("Mã hãng điện thoại");
        txtMaHangDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaHangDTActionPerformed(evt);
            }
        });
        jPanel5.add(txtMaHangDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 82, 240, -1));

        txtTenHangDT.setForeground(new java.awt.Color(102, 102, 102));
        txtTenHangDT.setLabelText("Tên hãng điện thoại");
        jPanel5.add(txtTenHangDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 146, 240, -1));

        txtTimKiemHang.setForeground(new java.awt.Color(102, 102, 102));
        txtTimKiemHang.setLabelText("");
        jPanel5.add(txtTimKiemHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(262, 5, 200, 50));

        btnQuayLaiHang.setBackground(new java.awt.Color(153, 153, 153));
        btnQuayLaiHang.setForeground(new java.awt.Color(255, 255, 255));
        btnQuayLaiHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/back.png"))); // NOI18N
        btnQuayLaiHang.setText("Quay lai");
        btnQuayLaiHang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnQuayLaiHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuayLaiHangActionPerformed(evt);
            }
        });
        jPanel5.add(btnQuayLaiHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 530, 150, -1));

        btnClearHang.setBackground(new java.awt.Color(1, 181, 204));
        btnClearHang.setForeground(new java.awt.Color(255, 255, 255));
        btnClearHang.setText("Clear");
        btnClearHang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnClearHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearHangActionPerformed(evt);
            }
        });
        jPanel5.add(btnClearHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 530, 150, -1));

        btnSuaHangDT.setBackground(new java.awt.Color(1, 181, 204));
        btnSuaHangDT.setForeground(new java.awt.Color(255, 255, 255));
        btnSuaHangDT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/edit.png"))); // NOI18N
        btnSuaHangDT.setText("Sửa");
        btnSuaHangDT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSuaHangDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaHangDTActionPerformed(evt);
            }
        });
        jPanel5.add(btnSuaHangDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 140, 150, -1));

        btnXoaHangDT.setBackground(new java.awt.Color(255, 0, 0));
        btnXoaHangDT.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaHangDT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/delete.png"))); // NOI18N
        btnXoaHangDT.setText("Xóa");
        btnXoaHangDT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoaHangDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaHangDTActionPerformed(evt);
            }
        });
        jPanel5.add(btnXoaHangDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 200, 150, -1));

        btnThemHangDT.setBackground(new java.awt.Color(1, 181, 204));
        btnThemHangDT.setForeground(new java.awt.Color(255, 255, 255));
        btnThemHangDT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/add.png"))); // NOI18N
        btnThemHangDT.setText("Thêm");
        btnThemHangDT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThemHangDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemHangDTActionPerformed(evt);
            }
        });
        jPanel5.add(btnThemHangDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 80, 150, -1));

        javax.swing.GroupLayout dlEditHangDTLayout = new javax.swing.GroupLayout(dlEditHangDT.getContentPane());
        dlEditHangDT.getContentPane().setLayout(dlEditHangDTLayout);
        dlEditHangDTLayout.setHorizontalGroup(
            dlEditHangDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        dlEditHangDTLayout.setVerticalGroup(
            dlEditHangDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        dlEditMauSac.setSize(new java.awt.Dimension(700, 630));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setPreferredSize(new java.awt.Dimension(700, 630));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblMS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Mã", "Tên"
            }
        ));
        tblMS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMSMouseClicked(evt);
            }
        });
        jspTblMS.setViewportView(tblMS);

        jPanel6.add(jspTblMS, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 265, 650, 244));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel24.setText("Danh sách màu");
        jPanel6.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 228, -1, -1));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(1, 181, 204));
        jLabel25.setText("Tìm kiếm");
        jPanel6.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(181, 32, -1, -1));

        btnTimKiemHang1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnTimKiemHang1.setForeground(new java.awt.Color(1, 181, 204));
        btnTimKiemHang1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/customSearch.png"))); // NOI18N
        btnTimKiemHang1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTimKiemHang1MouseClicked(evt);
            }
        });
        jPanel6.add(btnTimKiemHang1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 31, -1, -1));

        txtMaMS.setEditable(false);
        txtMaMS.setForeground(new java.awt.Color(102, 102, 102));
        txtMaMS.setLabelText("Mã màu sắc");
        txtMaMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaMSActionPerformed(evt);
            }
        });
        jPanel6.add(txtMaMS, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 82, 240, -1));

        txtTenMS.setForeground(new java.awt.Color(102, 102, 102));
        txtTenMS.setLabelText("Tên màu sắc");
        jPanel6.add(txtTenMS, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 146, 240, -1));

        txtTimKiemMS.setForeground(new java.awt.Color(102, 102, 102));
        txtTimKiemMS.setLabelText("");
        jPanel6.add(txtTimKiemMS, new org.netbeans.lib.awtextra.AbsoluteConstraints(262, 5, 200, 50));

        btnQuayLaiMS.setBackground(new java.awt.Color(153, 153, 153));
        btnQuayLaiMS.setForeground(new java.awt.Color(255, 255, 255));
        btnQuayLaiMS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/back.png"))); // NOI18N
        btnQuayLaiMS.setText("Quay lai");
        btnQuayLaiMS.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnQuayLaiMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuayLaiMSActionPerformed(evt);
            }
        });
        jPanel6.add(btnQuayLaiMS, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 530, 150, -1));

        btnClearMS.setBackground(new java.awt.Color(1, 181, 204));
        btnClearMS.setForeground(new java.awt.Color(255, 255, 255));
        btnClearMS.setText("Clear");
        btnClearMS.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnClearMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearMSActionPerformed(evt);
            }
        });
        jPanel6.add(btnClearMS, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 530, 150, -1));

        btnSuaMS.setBackground(new java.awt.Color(1, 181, 204));
        btnSuaMS.setForeground(new java.awt.Color(255, 255, 255));
        btnSuaMS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/edit.png"))); // NOI18N
        btnSuaMS.setText("Sửa");
        btnSuaMS.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSuaMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaMSActionPerformed(evt);
            }
        });
        jPanel6.add(btnSuaMS, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 140, 150, -1));

        btnXoaMS.setBackground(new java.awt.Color(255, 0, 0));
        btnXoaMS.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaMS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/delete.png"))); // NOI18N
        btnXoaMS.setText("Xóa");
        btnXoaMS.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoaMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaMSActionPerformed(evt);
            }
        });
        jPanel6.add(btnXoaMS, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 200, 150, -1));

        btnThemMS.setBackground(new java.awt.Color(1, 181, 204));
        btnThemMS.setForeground(new java.awt.Color(255, 255, 255));
        btnThemMS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/add.png"))); // NOI18N
        btnThemMS.setText("Thêm");
        btnThemMS.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThemMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemMSActionPerformed(evt);
            }
        });
        jPanel6.add(btnThemMS, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 80, 150, -1));

        javax.swing.GroupLayout dlEditMauSacLayout = new javax.swing.GroupLayout(dlEditMauSac.getContentPane());
        dlEditMauSac.getContentPane().setLayout(dlEditMauSacLayout);
        dlEditMauSacLayout.setHorizontalGroup(
            dlEditMauSacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        dlEditMauSacLayout.setVerticalGroup(
            dlEditMauSacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

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
        txtTim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTimMouseClicked(evt);
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
        jLabel9.setText("Mã điện thoại");
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

        btnEditDienThoai.setBackground(new java.awt.Color(1, 181, 204));
        btnEditDienThoai.setForeground(new java.awt.Color(255, 255, 255));
        btnEditDienThoai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/add.png"))); // NOI18N
        btnEditDienThoai.setText("Edit");
        btnEditDienThoai.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        btnEditDienThoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditDienThoaiActionPerformed(evt);
            }
        });
        jPanel1.add(btnEditDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 110, 80, -1));

        btnEditHangDT.setBackground(new java.awt.Color(1, 181, 204));
        btnEditHangDT.setForeground(new java.awt.Color(255, 255, 255));
        btnEditHangDT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/add.png"))); // NOI18N
        btnEditHangDT.setText("Edit");
        btnEditHangDT.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        btnEditHangDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditHangDTActionPerformed(evt);
            }
        });
        jPanel1.add(btnEditHangDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 170, 80, 50));

        buttonCustom6.setBackground(new java.awt.Color(1, 181, 204));
        buttonCustom6.setForeground(new java.awt.Color(255, 255, 255));
        buttonCustom6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/add.png"))); // NOI18N
        buttonCustom6.setText("Edit");
        buttonCustom6.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        buttonCustom6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCustom6ActionPerformed(evt);
            }
        });
        jPanel1.add(buttonCustom6, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 230, 80, -1));

        cbbTrangThai.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất cả", " Đang bán", " Đã bán", " Sản phẩm lỗi" }));
        cbbTrangThai.setLabeText("");
        cbbTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTrangThaiActionPerformed(evt);
            }
        });
        jPanel1.add(cbbTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 20, 220, 40));
        jPanel1.add(cbbDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 110, 190, 40));
        jPanel1.add(cbbHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 170, 190, 40));
        jPanel1.add(cbbMauSac, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 228, 190, 40));

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
        this.txtMa.setText(ctdtr.getMaDienThoai());
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
        ChiTietDienThoaiResponse chiTietDienThoaiResponse = chiTietDTImpl.checkImei(txtImei.getText().trim());
        if (chiTietDienThoaiResponse != null) {
            JOptionPane.showMessageDialog(this, "Trùng Imei");
            return;
        }
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
//            ctDT.setMa(this.txtMa.getText().trim());

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
        // CBB lọc danh sách sản phẩm theo trạng thái
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

    private void btnEditDienThoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditDienThoaiActionPerformed
        // BTN hiển thị form edit điện thoại
        dlEditDienThoai.setVisible(true);
        dlEditDienThoai.setLocationRelativeTo(null);

    }//GEN-LAST:event_btnEditDienThoaiActionPerformed

    private void txtMaDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaDTActionPerformed

    private void btnEditHangDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditHangDTActionPerformed
        // BTN hiển thị form edit Hãng
        dlEditHangDT.setVisible(true);
        dlEditHangDT.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnEditHangDTActionPerformed

    private void btnQuayLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuayLaiActionPerformed
        dlEditDienThoai.setVisible(false);
    }//GEN-LAST:event_btnQuayLaiActionPerformed

    private void txtMaHangDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaHangDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaHangDTActionPerformed

    private void btnQuayLaiHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuayLaiHangActionPerformed
        dlEditHangDT.setVisible(false);
    }//GEN-LAST:event_btnQuayLaiHangActionPerformed

    private void tblDienThoaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDienThoaiMouseClicked
        int row = this.tblDienThoai.getSelectedRow();
        DienThoaiDto dtdto = this.listDT.get(row);
        this.txtMaDT.setText(dtdto.getMa() + "");
        this.txtTenDT.setText(dtdto.getTen() + "");
    }//GEN-LAST:event_tblDienThoaiMouseClicked

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        this.clearFormDT();
        loadTableDT(dienThoaiImpl.getAllResponse());
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnThemDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemDTActionPerformed
        DienThoai dt = this.getFormDataDT();
        if (dt == null) {
            return;
        }
        String ketQua = dienThoaiImpl.insert(dt);
        if (ketQua.equals("Thêm thành công")) {
            listDT = this.dienThoaiImpl.getAllResponse();
            loadTableDT(listDT);
            loadCbbDienThoai();
            this.clearFormDT();
        }
        JOptionPane.showMessageDialog(this, ketQua);
    }//GEN-LAST:event_btnThemDTActionPerformed

    private void btnSuaDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaDTActionPerformed
        int index = tblDienThoai.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Bạn cần chọn một điện thoại trên table");
            return;
        }
        String ma = (String) tblDienThoai.getValueAt(index, 1);
        DienThoai dt = this.getFormDataDT();
        if (dt == null) {
            return;
        }
        dt.setMa(ma);
        String ketQua = dienThoaiImpl.update(dt);
        if (ketQua.equals("Sửa thành công")) {
            listDT = this.dienThoaiImpl.getAllResponse();
            loadTableDT(listDT);
            loadCbbDienThoai();
            this.clearFormDT();
        }
        JOptionPane.showMessageDialog(this, ketQua);


    }//GEN-LAST:event_btnSuaDTActionPerformed

    private void btnTimKiemDTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimKiemDTMouseClicked
        String keyWord = txtTimKiemDT.getText().trim();
        if (keyWord.isBlank()) {
            JOptionPane.showMessageDialog(this, "Mời nhập tên dien thoai để tìm kiếm");
            return;
        }
        List<DienThoaiDto> lstFind = dienThoaiImpl.findByName(keyWord);
        if (lstFind == null) {
            JOptionPane.showMessageDialog(this, "Lỗi hệ thống");
            return;
        }
        if (lstFind.size() == 0) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy");
            return;
        }
        JOptionPane.showMessageDialog(this, "Tìm thấy " + lstFind.size() + " kết quả");
        loadTableDT(lstFind);
    }//GEN-LAST:event_btnTimKiemDTMouseClicked

    private void tblHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHangMouseClicked
        int row = this.tblHang.getSelectedRow();
        HangDto hangdto = this.listHang.get(row);
        this.txtMaHangDT.setText(hangdto.getMa() + "");
        this.txtTenHangDT.setText(hangdto.getTen() + "");
    }//GEN-LAST:event_tblHangMouseClicked

    private void btnClearHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearHangActionPerformed
        this.clearFormHang();
        loadTableHang(hangImpl.getAllResponse());
    }//GEN-LAST:event_btnClearHangActionPerformed

    private void btnThemHangDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemHangDTActionPerformed
        Hang hang = this.getFormDataHang();
        if (hang == null) {
            return;
        }
        String ketQua = hangImpl.insert(hang);
        if (ketQua.equals("Thêm thành công")) {
            listHang = this.hangImpl.getAllResponse();
            loadTableHang(listHang);
            loadCbbHang();
            this.clearFormHang();
        }
        JOptionPane.showMessageDialog(this, ketQua);
    }//GEN-LAST:event_btnThemHangDTActionPerformed

    private void btnSuaHangDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaHangDTActionPerformed

        int index = tblHang.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Bạn cần chọn một hãng trên table");
            return;
        }
        String ma = (String) tblHang.getValueAt(index, 1);
        Hang hang = getFormDataHang();
        if (hang == null) {
            return;
        }
        hang.setMa(ma);
        String ketQua = hangImpl.update(hang);
        if (ketQua.equals("Sửa thành công")) {
            listHang = this.hangImpl.getAllResponse();
            loadTableHang(listHang);
            loadCbbHang();
            this.clearFormHang();
        }
        JOptionPane.showMessageDialog(this, ketQua);

    }//GEN-LAST:event_btnSuaHangDTActionPerformed

    private void btnTimKiemHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimKiemHangMouseClicked
        String ten = txtTimKiemHang.getText().trim();
        if (ten.isBlank()) {
            JOptionPane.showMessageDialog(this, "Mời nhập tên hãng để tìm kiếm");
            return;
        }
        List<HangDto> lstFind = hangImpl.findHang(ten);
        if (lstFind == null) {
            JOptionPane.showMessageDialog(this, "Lỗi hệ thống");
            return;
        }
        if (lstFind.size() == 0) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy");
            return;
        }
        JOptionPane.showMessageDialog(this, "Tìm thấy " + lstFind.size() + " kết quả");
        loadTableHang(lstFind);
    }//GEN-LAST:event_btnTimKiemHangMouseClicked

    private void btnXoaDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaDTActionPerformed
        int row = this.tblDienThoai.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Bạn cần chọn một điện thoại trên table");
            return;
        }
        int c = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa bản ghi này không");
        if (c != JOptionPane.YES_OPTION) {
            return;
        }
        String ma = (String) tblDienThoai.getValueAt(row, 1);
        String ketqua = dienThoaiImpl.delete(ma);
        if (ketqua.equals("Xóa thành công")) {
            listDT = dienThoaiImpl.getAllResponse();
            loadTableDT(listDT);
            loadCbbDienThoai();
            clearFormDT();
        }
        JOptionPane.showMessageDialog(this, ketqua);
    }//GEN-LAST:event_btnXoaDTActionPerformed

    private void btnXoaHangDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaHangDTActionPerformed
        int row = this.tblHang.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Bạn cần chọn một hãng trên table");
            return;
        }
        int c = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa bản ghi này không");
        if (c != JOptionPane.YES_OPTION) {
            return;
        }
        String ma = (String) tblHang.getValueAt(row, 1);
        String ketqua = hangImpl.delete(ma);
        if (ketqua.equals("Xóa thành công")) {
            listHang = hangImpl.getAllResponse();
            loadTableHang(listHang);
            loadCbbHang();
            clearFormHang();
        }
        JOptionPane.showMessageDialog(this, ketqua);
    }//GEN-LAST:event_btnXoaHangDTActionPerformed

    private void tblMSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMSMouseClicked
        int row = this.tblMS.getSelectedRow();
        MauSacDto msdto = this.listMS.get(row);
        this.txtMaMS.setText(msdto.getMa() + "");
        this.txtTenMS.setText(msdto.getTen() + "");
    }//GEN-LAST:event_tblMSMouseClicked

    private void btnTimKiemHang1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimKiemHang1MouseClicked
        String keyWord = txtTimKiemMS.getText().trim();
        if (keyWord.isBlank()) {
            JOptionPane.showMessageDialog(this, "Mời nhập tên màu sắc để tìm kiếm");
            return;
        }
        List<MauSacDto> lstFind = mauSacImpl.findByName(keyWord);
        if (lstFind == null) {
            JOptionPane.showMessageDialog(this, "Lỗi hệ thống");
            return;
        }
        if (lstFind.size() == 0) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy");
            return;
        }
        JOptionPane.showMessageDialog(this, "Tìm thấy " + lstFind.size() + " kết quả");
        loadTableMS(lstFind);
    }//GEN-LAST:event_btnTimKiemHang1MouseClicked

    private void txtMaMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaMSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaMSActionPerformed

    private void btnQuayLaiMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuayLaiMSActionPerformed
        dlEditMauSac.setVisible(false);
    }//GEN-LAST:event_btnQuayLaiMSActionPerformed

    private void btnClearMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearMSActionPerformed
        this.clearFormMS();
        loadTableMS(mauSacImpl.getAllResponse());
    }//GEN-LAST:event_btnClearMSActionPerformed

    private void btnSuaMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaMSActionPerformed

        int index = tblMS.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Bạn cần chọn một màu sắc trên table");
            return;
        }
        String ma = (String) tblMS.getValueAt(index, 1);
        MauSac mauSac = getFormDataMS();
        if (mauSac == null) {
            return;
        }
        mauSac.setMa(ma);
        String ketQua = mauSacImpl.update(mauSac);
        if (ketQua.equals("Sửa thành công")) {
            listMS = this.mauSacImpl.getAllResponse();
            loadTableMS(listMS);
            loadCbbMauSac();
            this.clearFormMS();
        }
        JOptionPane.showMessageDialog(this, ketQua);

    }//GEN-LAST:event_btnSuaMSActionPerformed

    private void btnXoaMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaMSActionPerformed
        int row = this.tblMS.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Bạn cần chọn một màu sắc trên table");
            return;
        }
        int c = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa bản ghi này không");
        if (c != JOptionPane.YES_OPTION) {
            return;
        }
        String ma = (String) tblMS.getValueAt(row, 1);
        String ketqua = mauSacImpl.delete(ma);
        if (ketqua.equals("Xóa thành công")) {
            listMS = mauSacImpl.getAllResponse();
            loadTableMS(listMS);
            loadCbbMauSac();
            clearFormMS();
        }
        JOptionPane.showMessageDialog(this, ketqua);
    }//GEN-LAST:event_btnXoaMSActionPerformed

    private void btnThemMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMSActionPerformed
        MauSac ms = this.getFormDataMS();
        if (ms == null) {
            return;
        }
        String ketQua = mauSacImpl.insert(ms);
        if (ketQua.equals("Thêm thành công")) {
            listMS = this.mauSacImpl.getAllResponse();
            loadTableMS(listMS);
            loadCbbMauSac();
            this.clearFormMS();
        }
        JOptionPane.showMessageDialog(this, ketQua);
    }//GEN-LAST:event_btnThemMSActionPerformed

    private void buttonCustom6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCustom6ActionPerformed
        dlEditMauSac.setVisible(true);
        dlEditMauSac.setLocationRelativeTo(null);
    }//GEN-LAST:event_buttonCustom6ActionPerformed

    private void txtTimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private pro1041.team_3.swing.ButtonCustom btnClear;
    private pro1041.team_3.swing.ButtonCustom btnClearHang;
    private pro1041.team_3.swing.ButtonCustom btnClearMS;
    private pro1041.team_3.swing.ButtonCustom btnEditDienThoai;
    private pro1041.team_3.swing.ButtonCustom btnEditHangDT;
    private pro1041.team_3.swing.ButtonCustom btnExport;
    private pro1041.team_3.swing.ButtonCustom btnImport;
    private pro1041.team_3.swing.ButtonCustom btnQuayLai;
    private pro1041.team_3.swing.ButtonCustom btnQuayLaiHang;
    private pro1041.team_3.swing.ButtonCustom btnQuayLaiMS;
    private pro1041.team_3.swing.ButtonCustom btnSua;
    private pro1041.team_3.swing.ButtonCustom btnSuaDT;
    private pro1041.team_3.swing.ButtonCustom btnSuaHangDT;
    private pro1041.team_3.swing.ButtonCustom btnSuaMS;
    private pro1041.team_3.swing.ButtonCustom btnTaoQr;
    private pro1041.team_3.swing.ButtonCustom btnThem;
    private pro1041.team_3.swing.ButtonCustom btnThem1;
    private pro1041.team_3.swing.ButtonCustom btnThemDT;
    private pro1041.team_3.swing.ButtonCustom btnThemHangDT;
    private pro1041.team_3.swing.ButtonCustom btnThemMS;
    private javax.swing.JLabel btnTimKiemDT;
    private javax.swing.JLabel btnTimKiemHang;
    private javax.swing.JLabel btnTimKiemHang1;
    private pro1041.team_3.swing.ButtonCustom btnXoaDT;
    private pro1041.team_3.swing.ButtonCustom btnXoaHangDT;
    private pro1041.team_3.swing.ButtonCustom btnXoaMS;
    private pro1041.team_3.swing.ButtonCustom buttonCustom6;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private pro1041.team_3.swing.ComboBoxSuggestion<DienThoai> cbbDienThoai;
    private pro1041.team_3.swing.ComboBoxSuggestion<Hang> cbbHang;
    private pro1041.team_3.swing.ComboBoxSuggestion<MauSac> cbbMauSac;
    private pro1041.team_3.swing.ComboBoxSuggestion cbbTinhTrangCu;
    private pro1041.team_3.swing.Combobox cbbTrangThai;
    private javax.swing.JDialog dlEditDienThoai;
    private javax.swing.JDialog dlEditHangDT;
    private javax.swing.JDialog dlEditMauSac;
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
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jspTblChiTietDienThoai;
    private javax.swing.JScrollPane jspTblDanhSachDT;
    private javax.swing.JScrollPane jspTblHang;
    private javax.swing.JScrollPane jspTblMS;
    private javax.swing.JRadioButton rdoCon;
    private javax.swing.JRadioButton rdoCu;
    private javax.swing.JRadioButton rdoHet;
    private javax.swing.JRadioButton rdoLoi;
    private javax.swing.JRadioButton rdoMoi;
    private pro1041.team_3.swing.config.Table tblChiTietDienThoai;
    private pro1041.team_3.swing.config.Table tblDienThoai;
    private pro1041.team_3.swing.config.Table tblHang;
    private pro1041.team_3.swing.config.Table tblMS;
    private javax.swing.JPanel tinhTrangCuPanel;
    private javax.swing.JTextField txtBaoHanh;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtImei;
    private javax.swing.JTextField txtMa;
    private pro1041.team_3.swing.TextField txtMaDT;
    private pro1041.team_3.swing.TextField txtMaHangDT;
    private pro1041.team_3.swing.TextField txtMaMS;
    private javax.swing.JTextField txtMoTa;
    private javax.swing.JTextField txtRam;
    private javax.swing.JTextField txtRom;
    private pro1041.team_3.swing.TextField txtTenDT;
    private pro1041.team_3.swing.TextField txtTenHangDT;
    private pro1041.team_3.swing.TextField txtTenMS;
    private javax.swing.JTextField txtTim;
    private pro1041.team_3.swing.TextField txtTimKiemDT;
    private pro1041.team_3.swing.TextField txtTimKiemHang;
    private pro1041.team_3.swing.TextField txtTimKiemMS;
    // End of variables declaration//GEN-END:variables
}
