package com.aim.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.IntervalMarker;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.TextAnchor;

public class CreateGraph {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("deprecation")
	public static JFreeChart createChart(CategoryDataset dataset) {
		// create the chart...
		JFreeChart chart = ChartFactory.createBarChart3D("", // chart
				"", // domain axis label
				"", // range axis label
				dataset, // data
				PlotOrientation.VERTICAL, // orientation
				false, // include legend
				true, // tooltips?
				false // URLs?
		);

		// set the background color for the chart...
		chart.setBackgroundPaint(Color.white);
		

		// get a reference to the plot for further customisation...
		CategoryPlot plot = chart.getCategoryPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);

		Font font = new Font("FreeMono", Font.BOLD, 14);
		
		
		
		IntervalMarker target = new IntervalMarker(4.5, 7.5);
		target.setLabelFont(new Font("SansSerif", Font.BOLD, 12));
		target.setLabelAnchor(RectangleAnchor.LEFT);
		target.setLabelTextAnchor(TextAnchor.CENTER_LEFT);
		target.setPaint(new Color(222, 222, 255, 128));
		// plot.addRangeMarker(target, Layer.BACKGROUND);

		// set the range axis to display integers only...
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

		// disable bar outlines...
		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setDrawBarOutline(false);
		renderer.setItemMargin(0);

		// set up gradient paints for series...
		GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, Color.BLUE, 0.0f, 0.0f, Color.lightGray);
		GradientPaint gp1 = new GradientPaint(0.0f, 0.0f, Color.green, 0.0f, 0.0f, Color.lightGray);
		GradientPaint gp2 = new GradientPaint(0.0f, 0.0f, Color.red, 0.0f, 0.0f, Color.lightGray);
		renderer.setSeriesPaint(0, gp0);
		renderer.setSeriesPaint(1, gp1);
		renderer.setSeriesPaint(2, gp2);

		renderer.setItemLabelGenerator(new MyJfreeChart.LabelGenerator());
		renderer.setItemLabelsVisible(true);
		ItemLabelPosition p = new ItemLabelPosition(ItemLabelAnchor.INSIDE12, TextAnchor.CENTER_RIGHT,
				TextAnchor.CENTER_RIGHT, -Math.PI / 2.0);
		renderer.setPositiveItemLabelPosition(p);

		ItemLabelPosition p2 = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.CENTER_LEFT,
				TextAnchor.CENTER_LEFT, -Math.PI / 2.0);
		
		renderer.setPositiveItemLabelPositionFallback(p2);
		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
		domainAxis.setLabelFont(font);
		renderer.setItemLabelFont(font);
		
		
		return chart;

	}
	
	
	public static JFreeChart drawPIEChart(DefaultPieDataset dataset)
	{
		
		JFreeChart chart=ChartFactory.createPieChart("", dataset,false,true,false);                
                   
        PiePlot ColorConfigurator = (PiePlot)chart.getPlot();
      
    
        ColorConfigurator.setBackgroundPaint(Color.WHITE);
        ColorConfigurator.setOutlinePaint(Color.white);
        Font font = new Font("FreeMono", Font.BOLD, 14);
        ColorConfigurator.setLabelFont(font);
		
		return chart;
		
		
	}
	
	
	
	
	
}
