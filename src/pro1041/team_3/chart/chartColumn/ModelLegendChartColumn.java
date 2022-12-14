package pro1041.team_3.chart.chartColumn;

import java.awt.Color;

public class ModelLegendChartColumn {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ModelLegendChartColumn(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public ModelLegendChartColumn() {
    }

    private String name;
    private Color color;
}
