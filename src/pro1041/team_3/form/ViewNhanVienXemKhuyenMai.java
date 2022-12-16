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
public class ViewNhanVienXemKhuyenMai extends javax.swing.JPanel {

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

    public ViewNhanVienXemKhuyenMai() {
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
        //Set Scroll Table
        tblAllSpChiTiet.fixTable(jspTblAllSpChiTiet);
        tblKhuyenMai.fixTable(jspTblKhuyenMai);
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        lblLoai = new javax.swing.JLabel();
        txtMa = new pro1041.team_3.swing.TextField();
        txtTen = new pro1041.team_3.swing.TextField();
        txtNgayBD = new pro1041.team_3.swing.TextField();
        txtNgayKT = new pro1041.team_3.swing.TextField();
        txtLoaiKhuyenMai = new pro1041.team_3.swing.TextField();
        txtMucKM = new pro1041.team_3.swing.TextField();
        jLabel8 = new javax.swing.JLabel();
        cbbLocKM = new pro1041.team_3.swing.Combobox();
        jspTblAllSpChiTiet = new javax.swing.JScrollPane();
        tblAllSpChiTiet = new pro1041.team_3.swing.config.Table();
        jspTblKhuyenMai = new javax.swing.JScrollPane();
        tblKhuyenMai = new pro1041.team_3.swing.config.Table();
        txtTimDTKM = new pro1041.team_3.swing.TextField();
        jPanel12 = new javax.swing.JPanel();
        txtTimKhuyenMai = new pro1041.team_3.swing.TextField();

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

        txtTen.setEditable(false);
        txtTen.setFont(new java.awt.Font("Nunito Light", 1, 13)); // NOI18N
        txtTen.setLabelColor(new java.awt.Color(1, 132, 203));
        txtTen.setLabelText("Tên khuyến mại");
        jPanel8.add(txtTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 290, -1));

        txtNgayBD.setFont(new java.awt.Font("Nunito Light", 1, 13)); // NOI18N
        txtNgayBD.setLabelColor(new java.awt.Color(1, 132, 203));
        txtNgayBD.setLabelText("Ngày bắt đầu");
        jPanel8.add(txtNgayBD, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 290, -1));

        txtNgayKT.setFont(new java.awt.Font("Nunito Light", 1, 13)); // NOI18N
        txtNgayKT.setLabelColor(new java.awt.Color(1, 132, 203));
        txtNgayKT.setLabelText("Ngày kết thúc");
        jPanel8.add(txtNgayKT, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 290, -1));

        txtLoaiKhuyenMai.setEditable(false);
        txtLoaiKhuyenMai.setFont(new java.awt.Font("Nunito Light", 1, 13)); // NOI18N
        txtLoaiKhuyenMai.setLabelColor(new java.awt.Color(1, 132, 203));
        txtLoaiKhuyenMai.setLabelText("Loại khuyến mại");
        jPanel8.add(txtLoaiKhuyenMai, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 290, -1));

        txtMucKM.setEditable(false);
        txtMucKM.setFont(new java.awt.Font("Nunito Light", 1, 13)); // NOI18N
        txtMucKM.setLabelColor(new java.awt.Color(1, 132, 203));
        txtMucKM.setLabelText("Mức khuyến mại");
        jPanel8.add(txtMucKM, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 240, -1));

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

    private void tblKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhuyenMaiMouseClicked
        // TODO add your handling code here:
        int row = this.tblKhuyenMai.getSelectedRow();
        if (row == -1) {
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("kk:mm dd/MM/yyyy");
        KhuyenMaiDto x = list1.get(row);
        this.txtMa.setText(x.getMaKhuyenMai());
        this.txtTen.setText(x.getTenKhuyenMai());
        this.txtNgayBD.setText(sdf.format(x.getNgayBatDau()));
        this.txtNgayKT.setText(sdf.format(x.getNgayKetThuc()));
        if (x.getGiaTriPhanTram() == null) {
            txtMucKM.setText(x.getGiaTriTienMat().toString());
            txtLoaiKhuyenMai.setText("Theo tiền mặt");
            lblLoai.setText("VNĐ");
        } else {
            txtMucKM.setText(x.getGiaTriPhanTram().toString());
            txtLoaiKhuyenMai.setText("Theo %");
            lblLoai.setText("%");
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private pro1041.team_3.swing.Combobox cbbLocKM;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jspTblAllSpChiTiet;
    private javax.swing.JScrollPane jspTblKhuyenMai;
    private javax.swing.JLabel lblLoai;
    private pro1041.team_3.swing.config.Table tblAllSpChiTiet;
    private pro1041.team_3.swing.config.Table tblKhuyenMai;
    private pro1041.team_3.swing.TextField txtLoaiKhuyenMai;
    private pro1041.team_3.swing.TextField txtMa;
    private pro1041.team_3.swing.TextField txtMucKM;
    private pro1041.team_3.swing.TextField txtNgayBD;
    private pro1041.team_3.swing.TextField txtNgayKT;
    private pro1041.team_3.swing.TextField txtTen;
    private pro1041.team_3.swing.TextField txtTimDTKM;
    private pro1041.team_3.swing.TextField txtTimKhuyenMai;
    // End of variables declaration//GEN-END:variables
}
