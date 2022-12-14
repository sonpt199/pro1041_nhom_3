package pro1041.team_3.form;

import pro1041.team_3.chart.ModelChartLine;
import pro1041.team_3.chart.ModelChartPie;
import pro1041.team_3.model.ModelStaff;
import java.awt.Color;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pro1041.team_3.chart.charLineCustom.chart.ModelChart;
import pro1041.team_3.chart.chartColumn.ChartColumn;
import pro1041.team_3.chart.chartColumn.ModelChartColumn;
import pro1041.team_3.dto.DoanhThuCaNhanDto;
import pro1041.team_3.dto.DoanhThuTheoNgayDto;
import pro1041.team_3.service.ThongKeService;
import pro1041.team_3.service.impl.ThongKeServiceImpl;
import pro1041.team_3.domainModel.DienThoai;
import pro1041.team_3.domainModel.Hang;
import pro1041.team_3.dto.ThongKeTheoHang;
import pro1041.team_3.service.DienThoaiService;
import pro1041.team_3.service.HangService;
import pro1041.team_3.service.impl.DienThoaiServiceImpl;
import pro1041.team_3.service.impl.HangServiceImpl;

public class ViewThongKe extends javax.swing.JPanel {

    private ThongKeService thongKeService;
    private DienThoaiService dienThoaiService;
    private HangService hangService;

    public ViewThongKe() {
        initComponents();
        thongKeService = new ThongKeServiceImpl();
        dienThoaiService = new DienThoaiServiceImpl();
        hangService = new HangServiceImpl();
        chart.addLegend("Doanh thu", Color.decode("#7b4397"), Color.decode("#dc2430"));
        LocalDateTime local = LocalDateTime.now();
        thongKeTheoNgay(local.getDayOfMonth(), local.getMonthValue(), local.getYear());
        getDoanhThuCaNhan(local.getDayOfMonth(), local.getMonthValue(), local.getYear());
        doanhThuTrongTuan();

        loadCbbTheoDienThoai();
        Calendar now = Calendar.getInstance();
        Date end = now.getTime();
        now.add(Calendar.DATE, -29);
        Date start = now.getTime();
        now.add(Calendar.DATE, 1);
        chooserTheoHangTu.setSelectedDate(start);
        thongKeTheoHang(cbbThongKeTheoHang.getItemAt(0), start, end);

    }

    private void loadCbbTheoDienThoai() {
        cbbThongKeTheoHang.removeAllItems();
        List<Hang> lstHang = hangService.getAll();
        for (Hang x : lstHang) {
            cbbThongKeTheoHang.addItem(x);
        }
    }

    private void thongKeTheoNgay(int day, int month, int year) {
        DoanhThuTheoNgayDto doanhThu = thongKeService.theoNgay(day, month, year);
        if (doanhThu != null) {
            DecimalFormat moneyFormat = new DecimalFormat("#,###");
            txtDoanhThuTheoNgay.setText(moneyFormat.format(doanhThu.getDoanhThu()) + "VNĐ");
            txtSoHoaDonTheoNgay.setText(doanhThu.getSoHoaDon().toString() + " hóa đơn");
            txtTienMatTheoNgay.setText(doanhThu.getTienMat() == null ? "-" : moneyFormat.format(doanhThu.getTienMat()) + "VNĐ");
            txtNganHangTheoNgay.setText(doanhThu.getNganHang() == null ? "-" : moneyFormat.format(doanhThu.getNganHang()) + "VNĐ");
        } else {
            txtDoanhThuTheoNgay.setText("Chưa có");
            txtSoHoaDonTheoNgay.setText("Chưa có");
            txtTienMatTheoNgay.setText("-");
            txtNganHangTheoNgay.setText("-");
        }
    }

    private void doanhThuTrongTuan() {
        Calendar now = Calendar.getInstance();
        Date end = now.getTime();
        now.add(Calendar.DATE, -6);
        Date start = now.getTime();
        now.add(Calendar.DATE, 1);
        chooserTu.setSelectedDate(start);
        List<DoanhThuTheoNgayDto> lst = thongKeService.theoKhoangThoiGian(start, end);
        chart.setTitle("Doanh thu trên ngày");
        chart.clear();
        for (DoanhThuTheoNgayDto x : lst) {
            chart.addData(new ModelChart(x.getNgay() + "/" + x.getThang(), new double[]{x.getDoanhThu().doubleValue()}));
        }
        chart.start();
    }

    private void thongKeTheoHang(Hang hang, Date start, Date end) {
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        endCalendar.add(Calendar.DATE, 1);
        List<ThongKeTheoHang> lst = thongKeService.thongKeTheohang(hang.getId(), start, endCalendar.getTime());
        if (lst == null) {
            JOptionPane.showMessageDialog(this, "Không có dữ liệu thống kê theo hãng");
            return;
        }
        if (lst.size() == 0) {
            chartTheoHang.setVisible(false);
            lbKhongCoDuLieuTheoHang.setVisible(true);
            return;
        } else {
            chartTheoHang.setVisible(true);
            lbKhongCoDuLieuTheoHang.setVisible(false);
        }
        chartTheoHang.clearAll();
        double[] data = new double[lst.size()];
        for (int i = 0; i < lst.size(); i++) {
            Random randomGenerator = new Random();
            int red = randomGenerator.nextInt(256);
            int green = randomGenerator.nextInt(256);
            int blue = randomGenerator.nextInt(256);
            Color randomColor = new Color(red, green, blue);
            chartTheoHang.addLegend(lst.get(i).getTenDienThoai(), randomColor);
            data[i] = lst.get(i).getSoLuong();
        }
        chartTheoHang.addData(new ModelChartColumn(hang.getTen(), data));
    }

    private void getDoanhThuCaNhan(int day, int month, int year) {
        List<DoanhThuCaNhanDto> lst = thongKeService.getDoanhThuCaNhanTheoNgay(day, month, year);
        List<ModelChartPie> lstModel = new ArrayList<>();
        for (DoanhThuCaNhanDto x : lst) {
            Random randomGenerator = new Random();
            int red = randomGenerator.nextInt(256);
            int green = randomGenerator.nextInt(256);
            int blue = randomGenerator.nextInt(256);
            Color randomColour = new Color(red, green, blue);
            lstModel.add(new ModelChartPie(x.getMaNhanVien(), x.getDoanhThu().doubleValue(), randomColour));
        }
        chartPieDoanhThuCaNhan.clearAll();
        chartPieDoanhThuCaNhan.setModel(lstModel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chooserTu = new pro1041.team_3.swing.DateChooser();
        chooserDen = new pro1041.team_3.swing.DateChooser();
        chooserNgayDoanhThu = new pro1041.team_3.swing.DateChooser();
        chooserTheoHangTu = new pro1041.team_3.swing.DateChooser();
        chooserTheoHangDen = new pro1041.team_3.swing.DateChooser();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtDoanhThuTheoNgay = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txtSoHoaDonTheoNgay = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        chartPieDoanhThuCaNhan = new pro1041.team_3.chart.ChartPie();
        jPanel5 = new javax.swing.JPanel();
        txtTienMatTheoNgay = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        txtNganHangTheoNgay = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnThongKeTheoNgay = new pro1041.team_3.swing.ButtonCustom();
        jpnTongDoanhThu = new javax.swing.JPanel();
        jpnTongDoanhThuTheoNgay = new javax.swing.JPanel();
        txtTongDoanhThuTheoNgay = new pro1041.team_3.swing.TextField();
        jpnTongDoanhThuTheoThang = new javax.swing.JPanel();
        cbbNamTongDoanhThu = new pro1041.team_3.swing.Combobox<>();
        cbbThangTongDoanhThu = new pro1041.team_3.swing.Combobox<>();
        cbbLoaiTongDoanhThu = new pro1041.team_3.swing.Combobox();
        materialTabbed1 = new pro1041.team_3.swing.MaterialTabbed();
        jPanel3 = new javax.swing.JPanel();
        panelShadow1 = new pro1041.team_3.chart.charLineCustom.panel.PanelShadow();
        chart = new pro1041.team_3.chart.charLineCustom.chart.CurveLineChart();
        txtDen = new pro1041.team_3.swing.TextField();
        txtTu = new pro1041.team_3.swing.TextField();
        btnDoanhThuTheoKhoang = new pro1041.team_3.swing.ButtonCustom();
        jPanel7 = new javax.swing.JPanel();
        jpnChartTheoHang = new pro1041.team_3.chart.chartColumn.PanelRound();
        chartTheoHang = new pro1041.team_3.chart.chartColumn.ChartColumn();
        lbKhongCoDuLieuTheoHang = new javax.swing.JLabel();
        cbbThongKeTheoHang = new pro1041.team_3.swing.Combobox<>();
        txtTheoHangTu = new pro1041.team_3.swing.TextField();
        txtTheoHangDen = new pro1041.team_3.swing.TextField();
        btnThongKeTheoHang = new pro1041.team_3.swing.ButtonCustom();
        jLabel2 = new javax.swing.JLabel();

        chooserTu.setTextRefernce(txtTu);

        chooserDen.setTextRefernce(txtDen);

        chooserNgayDoanhThu.setTextRefernce(txtTongDoanhThuTheoNgay);

        chooserTheoHangTu.setTextRefernce(txtTheoHangTu);

        chooserTheoHangDen.setTextRefernce(txtTheoHangDen);

        setBackground(new java.awt.Color(250, 250, 250));
        setMinimumSize(new java.awt.Dimension(1150, 750));
        setPreferredSize(new java.awt.Dimension(1150, 750));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Chi tiết doanh thu theo ngày", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Nunito Light", 1, 14))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(1, 181, 204));
        jPanel2.setPreferredSize(new java.awt.Dimension(140, 140));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Nunito Light", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Doanh thu");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 13, 130, 32));

        txtDoanhThuTheoNgay.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        txtDoanhThuTheoNgay.setForeground(new java.awt.Color(255, 255, 255));
        txtDoanhThuTheoNgay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtDoanhThuTheoNgay.setText("2000000");
        jPanel2.add(txtDoanhThuTheoNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 63, 130, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 119, 130, 110));

        jPanel4.setBackground(new java.awt.Color(1, 181, 204));
        jPanel4.setPreferredSize(new java.awt.Dimension(140, 140));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtSoHoaDonTheoNgay.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        txtSoHoaDonTheoNgay.setForeground(new java.awt.Color(255, 255, 255));
        txtSoHoaDonTheoNgay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtSoHoaDonTheoNgay.setText("2000000");
        jPanel4.add(txtSoHoaDonTheoNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 60, 130, 31));

        jLabel3.setFont(new java.awt.Font("Nunito Light", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Số hóa đơn");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 130, 32));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 130, 110));

        chartPieDoanhThuCaNhan.setFont(new java.awt.Font("Nunito Light", 1, 12)); // NOI18N
        jPanel1.add(chartPieDoanhThuCaNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 30, 530, -1));

        jPanel5.setBackground(new java.awt.Color(1, 181, 204));
        jPanel5.setPreferredSize(new java.awt.Dimension(140, 140));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTienMatTheoNgay.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        txtTienMatTheoNgay.setForeground(new java.awt.Color(255, 255, 255));
        txtTienMatTheoNgay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTienMatTheoNgay.setText("2000000");
        jPanel5.add(txtTienMatTheoNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 60, 130, 31));

        jLabel4.setFont(new java.awt.Font("Nunito Light", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Tiền mặt");
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 130, 32));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 120, 130, 110));

        jPanel6.setBackground(new java.awt.Color(1, 181, 204));
        jPanel6.setPreferredSize(new java.awt.Dimension(140, 140));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNganHangTheoNgay.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        txtNganHangTheoNgay.setForeground(new java.awt.Color(255, 255, 255));
        txtNganHangTheoNgay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtNganHangTheoNgay.setText("2000000");
        jPanel6.add(txtNganHangTheoNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 60, 130, 31));

        jLabel5.setFont(new java.awt.Font("Nunito Light", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Ngân hàng");
        jPanel6.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 130, 32));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 120, 130, 110));

        btnThongKeTheoNgay.setBackground(new java.awt.Color(1, 181, 204));
        btnThongKeTheoNgay.setForeground(new java.awt.Color(255, 255, 255));
        btnThongKeTheoNgay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/stats.png"))); // NOI18N
        btnThongKeTheoNgay.setText("Kiểm tra");
        btnThongKeTheoNgay.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        btnThongKeTheoNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeTheoNgayActionPerformed(evt);
            }
        });
        jPanel1.add(btnThongKeTheoNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 40, 138, -1));

        jpnTongDoanhThu.setBackground(new java.awt.Color(255, 255, 255));
        jpnTongDoanhThu.setLayout(new java.awt.CardLayout());

        jpnTongDoanhThuTheoNgay.setBackground(new java.awt.Color(255, 255, 255));
        jpnTongDoanhThuTheoNgay.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTongDoanhThuTheoNgay.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        txtTongDoanhThuTheoNgay.setLabelColor(new java.awt.Color(3, 124, 227));
        txtTongDoanhThuTheoNgay.setLabelText("Ngày");
        jpnTongDoanhThuTheoNgay.add(txtTongDoanhThuTheoNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 190, -1));

        jpnTongDoanhThu.add(jpnTongDoanhThuTheoNgay, "card3");

        jpnTongDoanhThuTheoThang.setBackground(new java.awt.Color(255, 255, 255));
        jpnTongDoanhThuTheoThang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbbNamTongDoanhThu.setModel(new javax.swing.DefaultComboBoxModel<>(new Integer[] { 2021, 2022 }));
        cbbNamTongDoanhThu.setSelectedIndex(-1);
        cbbNamTongDoanhThu.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        cbbNamTongDoanhThu.setLabeText("Năm");
        cbbNamTongDoanhThu.setLabelColor(new java.awt.Color(3, 124, 227));
        jpnTongDoanhThuTheoThang.add(cbbNamTongDoanhThu, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 90, -1));

        cbbThangTongDoanhThu.setModel(new javax.swing.DefaultComboBoxModel(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12}));
        cbbThangTongDoanhThu.setSelectedIndex(-1);
        cbbThangTongDoanhThu.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        cbbThangTongDoanhThu.setLabeText("Tháng");
        cbbThangTongDoanhThu.setLabelColor(new java.awt.Color(3, 124, 227));
        jpnTongDoanhThuTheoThang.add(cbbThangTongDoanhThu, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 90, -1));

        jpnTongDoanhThu.add(jpnTongDoanhThuTheoThang, "card2");

        jPanel1.add(jpnTongDoanhThu, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 240, 70));

        cbbLoaiTongDoanhThu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Trong ngày", "Tháng" }));
        cbbLoaiTongDoanhThu.setSelectedIndex(-1);
        cbbLoaiTongDoanhThu.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        cbbLoaiTongDoanhThu.setLabeText("Loại");
        cbbLoaiTongDoanhThu.setLabelColor(new java.awt.Color(3, 124, 227));
        cbbLoaiTongDoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLoaiTongDoanhThuActionPerformed(evt);
            }
        });
        jPanel1.add(cbbLoaiTongDoanhThu, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 150, 50));

        materialTabbed1.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Thống kê theo khoảng thời gian", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Nunito Light", 1, 14))); // NOI18N

        panelShadow1.setBackground(new java.awt.Color(34, 59, 69));
        panelShadow1.setForeground(new java.awt.Color(255, 255, 255));
        panelShadow1.setColorGradient(new java.awt.Color(17, 38, 47));

        chart.setForeground(new java.awt.Color(255, 255, 255));
        chart.setFont(new java.awt.Font("Nunito", 0, 12)); // NOI18N
        chart.setTitleFont(new java.awt.Font("Nunito Light", 1, 12)); // NOI18N

        javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
        panelShadow1.setLayout(panelShadow1Layout);
        panelShadow1Layout.setHorizontalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, 1087, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelShadow1Layout.setVerticalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                .addContainerGap())
        );

        txtDen.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        txtDen.setLabelColor(new java.awt.Color(3, 124, 227));
        txtDen.setLabelText("Đến");

        txtTu.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        txtTu.setLabelColor(new java.awt.Color(3, 124, 227));
        txtTu.setLabelText("Từ");

        btnDoanhThuTheoKhoang.setBackground(new java.awt.Color(1, 181, 204));
        btnDoanhThuTheoKhoang.setForeground(new java.awt.Color(255, 255, 255));
        btnDoanhThuTheoKhoang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/stats.png"))); // NOI18N
        btnDoanhThuTheoKhoang.setText("Thống kê");
        btnDoanhThuTheoKhoang.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        btnDoanhThuTheoKhoang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoanhThuTheoKhoangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTu, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtDen, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDoanhThuTheoKhoang, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnDoanhThuTheoKhoang, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        materialTabbed1.addTab("Doanh thu", jPanel3);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpnChartTheoHang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jpnChartTheoHang.add(chartTheoHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1090, 300));

        lbKhongCoDuLieuTheoHang.setBackground(new java.awt.Color(255, 255, 255));
        lbKhongCoDuLieuTheoHang.setFont(new java.awt.Font("Nunito Light", 1, 24)); // NOI18N
        lbKhongCoDuLieuTheoHang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbKhongCoDuLieuTheoHang.setText("Không có điện thoại nào được bán trong thời gian này");
        jpnChartTheoHang.add(lbKhongCoDuLieuTheoHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, 740, 40));

        jPanel7.add(jpnChartTheoHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 1110, 320));

        cbbThongKeTheoHang.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        cbbThongKeTheoHang.setLabeText("Hãng");
        cbbThongKeTheoHang.setLabelColor(new java.awt.Color(3, 124, 227));
        jPanel7.add(cbbThongKeTheoHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 185, 50));

        txtTheoHangTu.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        txtTheoHangTu.setLabelColor(new java.awt.Color(3, 124, 227));
        txtTheoHangTu.setLabelText("Từ");
        jPanel7.add(txtTheoHangTu, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 0, 190, -1));

        txtTheoHangDen.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        txtTheoHangDen.setLabelColor(new java.awt.Color(3, 124, 227));
        txtTheoHangDen.setLabelText("Đến");
        jPanel7.add(txtTheoHangDen, new org.netbeans.lib.awtextra.AbsoluteConstraints(423, 0, 190, -1));

        btnThongKeTheoHang.setBackground(new java.awt.Color(1, 181, 204));
        btnThongKeTheoHang.setForeground(new java.awt.Color(255, 255, 255));
        btnThongKeTheoHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/stats.png"))); // NOI18N
        btnThongKeTheoHang.setText("Thống kê");
        btnThongKeTheoHang.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        btnThongKeTheoHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeTheoHangActionPerformed(evt);
            }
        });
        jPanel7.add(btnThongKeTheoHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 10, 138, -1));

        jLabel2.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        jLabel2.setText("Thống kê số lượng điện thoại bán theo hãng");
        jPanel7.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 310, -1));

        materialTabbed1.addTab("Điện thoại", jPanel7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(materialTabbed1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(materialTabbed1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDoanhThuTheoKhoangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoanhThuTheoKhoangActionPerformed
        //BTN Thống kê khoảng tgian
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date start;
        Date end;
        try {
            start = sdf.parse(txtTu.getText());
            end = sdf.parse(txtDen.getText());
        } catch (ParseException ex) {
            return;
        }
        if (start.compareTo(end) == 1 || start.compareTo(end) == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn lại ngày. Từ ... đến...");
            return;
        } else if (start.compareTo(new Date()) == 1 || end.compareTo(new Date()) == 1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày trong quá khứ");
            return;
        } else if ((end.getTime() - start.getTime()) > 2678400000L) {
            JOptionPane.showMessageDialog(this, "Thống kê tối đa trong vòng 31 ngày");
            return;
        }
        List<DoanhThuTheoNgayDto> lst = thongKeService.theoKhoangThoiGian(start, end);
        chart.setTitle("Doanh thu trong ngày (VNĐ)");
        chart.clear();
        for (DoanhThuTheoNgayDto x : lst) {
            chart.addData(new ModelChart(x.getNgay() + "/" + x.getThang(), new double[]{x.getDoanhThu().doubleValue()}));
        }
        chart.start();
    }//GEN-LAST:event_btnDoanhThuTheoKhoangActionPerformed

    private void btnThongKeTheoHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeTheoHangActionPerformed
        // BTN thống ke theo hãng
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date start;
        Date end;
        try {
            start = sdf.parse(txtTheoHangTu.getText());
            end = sdf.parse(txtTheoHangDen.getText());
        } catch (ParseException ex) {
            ex.printStackTrace();
            return;
        }
        if (start.compareTo(end) == 1 || start.compareTo(end) == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn lại ngày. Từ ... đến...");
            return;
        } else if (start.compareTo(new Date()) == 1 || end.compareTo(new Date()) == 1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày trong quá khứ");
            return;
        } else if ((end.getTime() - start.getTime()) > 2678400000L) {
            JOptionPane.showMessageDialog(this, "Thống kê tối đa trong vòng 31 ngày");
            return;
        }
        Hang hang = (Hang) cbbThongKeTheoHang.getSelectedItem();
        thongKeTheoHang(hang, start, end);
    }//GEN-LAST:event_btnThongKeTheoHangActionPerformed

    private void btnThongKeTheoNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeTheoNgayActionPerformed
        // BTN Thống kê theo ngày
        int loai = cbbLoaiTongDoanhThu.getSelectedIndex();
        if (loai == 0) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date selectedDate = null;
            try {
                selectedDate = sdf.parse(txtTongDoanhThuTheoNgay.getText());
            } catch (ParseException ex) {
                ex.printStackTrace();
                return;
            }
            LocalDateTime local = LocalDateTime.ofInstant(selectedDate.toInstant(), ZoneId.of("Asia/Ho_Chi_Minh"));
            thongKeTheoNgay(local.getDayOfMonth(), local.getMonthValue(), local.getYear());
            getDoanhThuCaNhan(local.getDayOfMonth(), local.getMonthValue(), local.getYear());
        } else if (loai == 1) {
            if (cbbThangTongDoanhThu.getSelectedIndex() == -1 || cbbNamTongDoanhThu.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(this, "Mời chọn tháng, năm cần tính tổng doanh thu");
                return;
            }
            int month = (int) cbbThangTongDoanhThu.getSelectedItem();
            int year = (int) cbbNamTongDoanhThu.getSelectedItem();
            DoanhThuTheoNgayDto doanhThu = thongKeService.theoThang(month, year);
            if (doanhThu != null) {
                DecimalFormat moneyFormat = new DecimalFormat("#,###");
                txtDoanhThuTheoNgay.setText(moneyFormat.format(doanhThu.getDoanhThu()) + "VNĐ");
                txtSoHoaDonTheoNgay.setText(doanhThu.getSoHoaDon().toString() + " hóa đơn");
                txtTienMatTheoNgay.setText(doanhThu.getTienMat() == null ? "-" : moneyFormat.format(doanhThu.getTienMat()) + "VNĐ");
                txtNganHangTheoNgay.setText(doanhThu.getNganHang() == null ? "-" : moneyFormat.format(doanhThu.getNganHang()) + "VNĐ");
            } else {
                txtDoanhThuTheoNgay.setText("Chưa có");
                txtSoHoaDonTheoNgay.setText("Chưa có");
                txtTienMatTheoNgay.setText("-");
                txtNganHangTheoNgay.setText("-");
            }
            List<DoanhThuCaNhanDto> lst = thongKeService.getDoanhThuCaNhanTheoThang(month, year);
            List<ModelChartPie> lstModel = new ArrayList<>();
            for (DoanhThuCaNhanDto x : lst) {
                Random randomGenerator = new Random();
                int red = randomGenerator.nextInt(256);
                int green = randomGenerator.nextInt(256);
                int blue = randomGenerator.nextInt(256);
                Color randomColour = new Color(red, green, blue);
                lstModel.add(new ModelChartPie(x.getMaNhanVien(), x.getDoanhThu().doubleValue(), randomColour));
            }
            chartPieDoanhThuCaNhan.clearAll();
            chartPieDoanhThuCaNhan.setModel(lstModel);
        }

    }//GEN-LAST:event_btnThongKeTheoNgayActionPerformed

    private void cbbLoaiTongDoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLoaiTongDoanhThuActionPerformed
        // CBB Loại tổng doanh thu
        int selected = cbbLoaiTongDoanhThu.getSelectedIndex();
        if (selected == 0) {
            jpnTongDoanhThuTheoNgay.setVisible(true);
            jpnTongDoanhThuTheoThang.setVisible(false);
        } else if (selected == 1) {
            jpnTongDoanhThuTheoNgay.setVisible(false);
            jpnTongDoanhThuTheoThang.setVisible(true);
        }
    }//GEN-LAST:event_cbbLoaiTongDoanhThuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private pro1041.team_3.swing.ButtonCustom btnDoanhThuTheoKhoang;
    private pro1041.team_3.swing.ButtonCustom btnThongKeTheoHang;
    private pro1041.team_3.swing.ButtonCustom btnThongKeTheoNgay;
    private pro1041.team_3.swing.Combobox cbbLoaiTongDoanhThu;
    private pro1041.team_3.swing.Combobox<Integer> cbbNamTongDoanhThu;
    private pro1041.team_3.swing.Combobox<Integer> cbbThangTongDoanhThu;
    private pro1041.team_3.swing.Combobox<Hang> cbbThongKeTheoHang;
    private pro1041.team_3.chart.charLineCustom.chart.CurveLineChart chart;
    private pro1041.team_3.chart.ChartPie chartPieDoanhThuCaNhan;
    private pro1041.team_3.chart.chartColumn.ChartColumn chartTheoHang;
    private pro1041.team_3.swing.DateChooser chooserDen;
    private pro1041.team_3.swing.DateChooser chooserNgayDoanhThu;
    private pro1041.team_3.swing.DateChooser chooserTheoHangDen;
    private pro1041.team_3.swing.DateChooser chooserTheoHangTu;
    private pro1041.team_3.swing.DateChooser chooserTu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private pro1041.team_3.chart.chartColumn.PanelRound jpnChartTheoHang;
    private javax.swing.JPanel jpnTongDoanhThu;
    private javax.swing.JPanel jpnTongDoanhThuTheoNgay;
    private javax.swing.JPanel jpnTongDoanhThuTheoThang;
    private javax.swing.JLabel lbKhongCoDuLieuTheoHang;
    private pro1041.team_3.swing.MaterialTabbed materialTabbed1;
    private pro1041.team_3.chart.charLineCustom.panel.PanelShadow panelShadow1;
    private pro1041.team_3.swing.TextField txtDen;
    private javax.swing.JLabel txtDoanhThuTheoNgay;
    private javax.swing.JLabel txtNganHangTheoNgay;
    private javax.swing.JLabel txtSoHoaDonTheoNgay;
    private pro1041.team_3.swing.TextField txtTheoHangDen;
    private pro1041.team_3.swing.TextField txtTheoHangTu;
    private javax.swing.JLabel txtTienMatTheoNgay;
    private pro1041.team_3.swing.TextField txtTongDoanhThuTheoNgay;
    private pro1041.team_3.swing.TextField txtTu;
    // End of variables declaration//GEN-END:variables
}
