package pro1041.team_3.chart;

import java.awt.Color;
import java.awt.Graphics;
import java.text.DecimalFormat;

public class ItemChartPie extends javax.swing.JPanel {

    private final ModelChartPie data;

    public ItemChartPie(ModelChartPie data) {
        this.data = data;
        initComponents();
        setOpaque(false);
        DecimalFormat df = new DecimalFormat("VNĐ #,##0.##");
        lbName.setText(data.getName());
        lbName.setBackground(data.getColor());
        lbValues.setText(df.format(data.getValue()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbValues = new javax.swing.JLabel();
        lbName = new javax.swing.JTextField();

        lbValues.setForeground(new java.awt.Color(69, 69, 69));
        lbValues.setText("Values");

        lbName.setEditable(false);
        lbName.setForeground(new java.awt.Color(255, 255, 255));
        lbName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lbName.setBorder(null);
        lbName.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lbName, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(lbValues)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lbValues, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics g) {
        //  Create line
        g.setColor(new Color(240, 240, 240));
        g.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
        int margin = 8;
        int size = getHeight() - margin * 2;
        g.setColor(data.getColor());
//        g.fillRect(margin, margin, size, size);
        super.paintComponent(g);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField lbName;
    private javax.swing.JLabel lbValues;
    // End of variables declaration//GEN-END:variables
}
