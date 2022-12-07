package pro1041.team_3.form;

import pro1041.team_3.chart.ModelChartLine;
import pro1041.team_3.chart.ModelChartPie;
import pro1041.team_3.model.ModelStaff;
import java.awt.Color;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pro1041.team_3.chart.charLineCustom.chart.ModelChart;
import pro1041.team_3.dto.DoanhThuTheoThangDto;
import pro1041.team_3.service.ThongKeService;
import pro1041.team_3.service.impl.ThongKeServiceImpl;

public class ViewThongKe extends javax.swing.JPanel {

    private ThongKeService thongKeService;

    public ViewThongKe() {
        initComponents();
        thongKeService = new ThongKeServiceImpl();
//        initChart();
        thongKeTheoNgay(LocalDateTime.now().getDayOfMonth(), LocalDateTime.now().getMonthValue(), LocalDateTime.now().getYear());
    }

    private void thongKeTheoNgay(int day, int month, int year) {
        DoanhThuTheoThangDto doanhThu = thongKeService.theoNgay(day, month, year);
        if (doanhThu != null) {
            DecimalFormat moneyFormat = new DecimalFormat("#,###");
            txtDoanhThuTheoNgay.setText(moneyFormat.format(doanhThu.getDoanhThu()) + "VNĐ");
            txtSoHoaDonTheoNgay.setText(doanhThu.getSoHoaDon().toString() + " hóa đơn");
        } else {
            txtDoanhThuTheoNgay.setText("Chưa có");
            txtSoHoaDonTheoNgay.setText("");
        }
    }

    private void initChart() {
        chart.setTitle("Doanh thu 7 ngày gần nhất");
        chart.addLegend("Amount", Color.decode("#7b4397"), Color.decode("#dc2430"));
        chart.clear();
        chart.addData(new ModelChart("1", new double[]{5000000d}));
        chart.addData(new ModelChart("2", new double[]{10000000d}));
        chart.addData(new ModelChart("2", new double[]{6000000d}));
        chart.addData(new ModelChart("2", new double[]{20000000d}));
        chart.addData(new ModelChart("2", new double[]{10000000d}));
        chart.addData(new ModelChart("2", new double[]{30000000d}));
        chart.addData(new ModelChart("2", new double[]{10000000d}));
        chart.addData(new ModelChart("2", new double[]{50000000d}));
        chart.addData(new ModelChart("2", new double[]{80000000d}));
        chart.addData(new ModelChart("2", new double[]{10000000d}));
        chart.addData(new ModelChart("2", new double[]{60000000d}));
        chart.addData(new ModelChart("2", new double[]{80000000d}));
        chart.addData(new ModelChart("2", new double[]{90000000d}));
        chart.addData(new ModelChart("2", new double[]{100000000d}));
        chart.addData(new ModelChart("2", new double[]{50000000d}));
        chart.addData(new ModelChart("2", new double[]{60000000d}));
        chart.addData(new ModelChart("2", new double[]{20000000d}));
        chart.addData(new ModelChart("2", new double[]{20000000d}));
        chart.addData(new ModelChart("2", new double[]{20000000d}));
        chart.addData(new ModelChart("2", new double[]{20000000d}));
        chart.addData(new ModelChart("2", new double[]{20000000d}));
        chart.addData(new ModelChart("2", new double[]{20000000d}));
        chart.addData(new ModelChart("2", new double[]{20000000d}));
        chart.addData(new ModelChart("2", new double[]{20000000d}));
        chart.addData(new ModelChart("2", new double[]{20000000d}));
        chart.addData(new ModelChart("2", new double[]{20000000d}));
        chart.addData(new ModelChart("2", new double[]{20000000d}));
        chart.addData(new ModelChart("2", new double[]{20000000d}));
        chart.addData(new ModelChart("2", new double[]{20000000d}));
        chart.addData(new ModelChart("2", new double[]{20000000d}));
        chart.start();
    }

//    private void initData() {
//        //  Test Data table
//        DefaultTableModel model = (DefaultTableModel) table1.getModel();
//        Random r = new Random();
//        for (int i = 0; i < 20; i++) {
//            String status;
//            int ran = r.nextInt(3);
//            if (ran == 0) {
//                status = "Pending";
//            } else if (ran == 1) {
//                status = "Approved";
//            } else {
//                status = "Cancel";
//            }
//            model.addRow(new Object[]{
//                "1", "Mr Raven", "Male", "raven_programming@gmail.com"
//            });
//        
//        
//        }
//        table1.fixTable(jScrollPane1);
//        List<ModelChartPie> list1 = new ArrayList<>();
//        list1.add(new ModelChartPie("Monday", 10, new Color(4, 174, 243)));
//        list1.add(new ModelChartPie("Tuesday", 150, new Color(215, 39, 250)));
//        list1.add(new ModelChartPie("Wednesday", 80, new Color(44, 88, 236)));
//        list1.add(new ModelChartPie("Thursday", 100, new Color(21, 202, 87)));
//        list1.add(new ModelChartPie("Friday", 125, new Color(127, 63, 255)));
//        list1.add(new ModelChartPie("Saturday", 1000, new Color(238, 167, 35)));
//        list1.add(new ModelChartPie("Sunday", 200, new Color(245, 79, 99)));
//        chartPie.setModel(list1);
    //  Test data chart line
//        List<ModelChartLine> list = new ArrayList<>();
//        list.add(new ModelChartLine("Monday", 10));
//        list.add(new ModelChartLine("Tuesday", 150));
//        list.add(new ModelChartLine("Wednesday", 80));
//        list.add(new ModelChartLine("Thursday", 100));
//        list.add(new ModelChartLine("Friday", 125));
//        list.add(new ModelChartLine("Saturday", 1000));
//        list.add(new ModelChartLine("Sunday", 200));
//        chartLine1.setModel(list);
//    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        panelShadow1 = new pro1041.team_3.chart.charLineCustom.panel.PanelShadow();
        chart = new pro1041.team_3.chart.charLineCustom.chart.CurveLineChart();
        buttonCustom1 = new pro1041.team_3.swing.ButtonCustom();
        cbbThang = new pro1041.team_3.swing.Combobox();
        cbbNam = new pro1041.team_3.swing.Combobox();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtDoanhThuTheoNgay = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtSoHoaDonTheoNgay = new javax.swing.JLabel();
        chartPie1 = new pro1041.team_3.chart.ChartPie();

        setBackground(new java.awt.Color(250, 250, 250));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Thống kê theo tháng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Nunito Light", 1, 14))); // NOI18N

        panelShadow1.setBackground(new java.awt.Color(34, 59, 69));
        panelShadow1.setForeground(new java.awt.Color(255, 255, 255));
        panelShadow1.setColorGradient(new java.awt.Color(17, 38, 47));

        chart.setForeground(new java.awt.Color(255, 255, 255));
        chart.setFont(new java.awt.Font("Nunito", 0, 12)); // NOI18N
        chart.setTitleFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N

        javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
        panelShadow1.setLayout(panelShadow1Layout);
        panelShadow1Layout.setHorizontalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelShadow1Layout.setVerticalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                .addContainerGap())
        );

        buttonCustom1.setBackground(new java.awt.Color(1, 181, 204));
        buttonCustom1.setForeground(new java.awt.Color(255, 255, 255));
        buttonCustom1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/stats.png"))); // NOI18N
        buttonCustom1.setText("Thống kê");
        buttonCustom1.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        buttonCustom1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCustom1ActionPerformed(evt);
            }
        });

        cbbThang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        cbbThang.setSelectedIndex(-1);
        cbbThang.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        cbbThang.setLabeText("Tháng");
        cbbThang.setLabelColor(new java.awt.Color(1, 132, 203));

        cbbNam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2021", "2022", "2023" }));
        cbbNam.setSelectedIndex(-1);
        cbbNam.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        cbbNam.setLabeText("Năm");
        cbbNam.setLabelColor(new java.awt.Color(1, 132, 203));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbbThang, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(cbbNam, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonCustom1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(722, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonCustom1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbbThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Doanh thu trong ngày", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Nunito Light", 1, 14))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(1, 181, 204));
        jPanel2.setPreferredSize(new java.awt.Dimension(140, 140));

        jLabel1.setFont(new java.awt.Font("Nunito Light", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Doanh thu");

        txtDoanhThuTheoNgay.setFont(new java.awt.Font("Nunito Light", 1, 18)); // NOI18N
        txtDoanhThuTheoNgay.setForeground(new java.awt.Color(255, 255, 255));
        txtDoanhThuTheoNgay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtDoanhThuTheoNgay.setText("2000000");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtDoanhThuTheoNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtDoanhThuTheoNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(1, 181, 204));
        jPanel4.setPreferredSize(new java.awt.Dimension(140, 140));

        jLabel2.setFont(new java.awt.Font("Nunito Light", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Số hóa đơn");

        txtSoHoaDonTheoNgay.setFont(new java.awt.Font("Nunito Light", 1, 18)); // NOI18N
        txtSoHoaDonTheoNgay.setForeground(new java.awt.Color(255, 255, 255));
        txtSoHoaDonTheoNgay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtSoHoaDonTheoNgay.setText("2000000");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtSoHoaDonTheoNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtSoHoaDonTheoNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chartPie1, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chartPie1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCustom1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCustom1ActionPerformed
        //BTN Thống kê khoảng tgian
        if (cbbThang.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn tháng cần thống kê");
            return;
        }
        if (cbbNam.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn năm cần thống kê");
            return;
        }
        String thang = cbbThang.getSelectedItem().toString();
        String nam = cbbNam.getSelectedItem().toString();
        List<DoanhThuTheoThangDto> lst = thongKeService.theoThang(Integer.parseInt(thang), Integer.parseInt(nam));
        chart.setTitle("Doanh thu trong tháng " + thang);
        chart.addLegend(nam, Color.decode("#7b4397"), Color.decode("#dc2430"));
        chart.clear();
        for (DoanhThuTheoThangDto x : lst) {
            chart.addData(new ModelChart(x.getNgay().toString(), new double[]{x.getDoanhThu().doubleValue()}));
        }
        chart.start();
    }//GEN-LAST:event_buttonCustom1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private pro1041.team_3.swing.ButtonCustom buttonCustom1;
    private pro1041.team_3.swing.Combobox cbbNam;
    private pro1041.team_3.swing.Combobox cbbThang;
    private pro1041.team_3.chart.charLineCustom.chart.CurveLineChart chart;
    private pro1041.team_3.chart.ChartPie chartPie1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private pro1041.team_3.chart.charLineCustom.panel.PanelShadow panelShadow1;
    private javax.swing.JLabel txtDoanhThuTheoNgay;
    private javax.swing.JLabel txtSoHoaDonTheoNgay;
    // End of variables declaration//GEN-END:variables
}
