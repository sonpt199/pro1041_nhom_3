package pro1041.team_3.chart.chartColumn;

import pro1041.team_3.chart.chartColumn.blankChartColumn.BlankPlotChartColumn;
import pro1041.team_3.chart.chartColumn.blankChartColumn.BlankPlotChatRenderChartColumn;
import pro1041.team_3.chart.chartColumn.blankChartColumn.SeriesSizeChartColumn;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class ChartColumn extends javax.swing.JPanel {

    private List<ModelLegendChartColumn> legends = new ArrayList<>();
    private List<ModelChartColumn> model = new ArrayList<>();
    private final int seriesSize = 12;
    private final int seriesSpace = 6;

    public ChartColumn() {
        initComponents();
        blankPlotChart.setBlankPlotChatRender(new BlankPlotChatRenderChartColumn() {
            @Override
            public String getLabelText(int index) {
                return model.get(index).getLabel();
            }

            @Override
            public void renderSeries(BlankPlotChartColumn chart, Graphics2D g2, SeriesSizeChartColumn size, int index) {
                double totalSeriesWidth = (seriesSize * legends.size()) + (seriesSpace * (legends.size() - 1));
                double x = (size.getWidth() - totalSeriesWidth) / 2;
                for (int i = 0; i < legends.size(); i++) {
                    ModelLegendChartColumn legend = legends.get(i);
                    GradientPaint gra = new GradientPaint(0, 0, new Color(86, 195, 250), 0, (int) (size.getY() + size.getHeight()), legend.getColor());
                    g2.setPaint(gra);
                    double seriesValues = chart.getSeriesValuesOf(model.get(index).getValues()[i], size.getHeight());
                    g2.fillRect((int) (size.getX() + x), (int) (size.getY() + size.getHeight() - seriesValues), seriesSize, (int) seriesValues);
                    x += seriesSpace + seriesSize;
                }
            }
        });
    }

    public void addLegend(String name, Color color) {
        ModelLegendChartColumn data = new ModelLegendChartColumn(name, color);
        legends.add(data);
        panelLegend.add(new LegendItemChartColumn(data));
        panelLegend.repaint();
    }

    public void addData(ModelChartColumn data) {
        model.add(data);
        blankPlotChart.setLabelCount(model.size());
        double max = data.getMaxValues();
        if (max > blankPlotChart.getMaxValues()) {
            blankPlotChart.setMaxValues(max);
        }
        blankPlotChart.repaint();
    }
                       
    public void clearAll() {
        legends.clear();
        model.clear();
        panelLegend.removeAll();
        panelLegend.repaint();   
        blankPlotChart.removeAll();
        blankPlotChart.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        blankPlotChart = new pro1041.team_3.chart.chartColumn.blankChartColumn.BlankPlotChartColumn();
        panelLegend = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));

        panelLegend.setOpaque(false);
        panelLegend.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelLegend, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
                    .addComponent(blankPlotChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(blankPlotChart, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(panelLegend, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private pro1041.team_3.chart.chartColumn.blankChartColumn.BlankPlotChartColumn blankPlotChart;
    private javax.swing.JPanel panelLegend;
    // End of variables declaration//GEN-END:variables
}
