package pro1041.team_3.chart.chartColumn.blankChartColumn;

import java.awt.Graphics2D;

public abstract class BlankPlotChatRenderChartColumn {

    public abstract String getLabelText(int index);

    public abstract void renderSeries(BlankPlotChartColumn chart, Graphics2D g2, SeriesSizeChartColumn size, int index);
}
