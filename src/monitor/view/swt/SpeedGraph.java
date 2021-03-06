package monitor.view.swt;

import java.awt.BasicStroke;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Map;

import javax.swing.BorderFactory;

import org.eclipse.swt.widgets.Composite;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.swt.ChartComposite;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.RectangleInsets;

import monitor.view.View;


public class SpeedGraph extends ChartComposite implements View {

	private TimeSeries in_rate;
	private TimeSeries out_rate;
	private int maxAge;
	private JFreeChart chart;
	private static final Millisecond ms = new Millisecond();
	
	public SpeedGraph(Composite parent, int style) {
		super(parent, style);
		// TODO Auto-generated constructor stub
		init();
	}
	
	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}
	
	public int getMaxAge() {
		
		return maxAge;
	}
    
	private void createDataSeries() {
		
		this.in_rate = new TimeSeries("Current inbound speed 0 bit/s");
		this.out_rate = new TimeSeries("Current outbound speed 0 bit/s");
		this.in_rate.setMaximumItemAge(maxAge);
		this.out_rate.setMaximumItemAge(maxAge);
		
	}
	
	private TimeSeriesCollection createDataSet() {
		
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(this.in_rate);
		dataset.addSeries(this.out_rate);
		
		return dataset;
		
	}
	
	private DateAxis createDateAxis() {
		
		DateAxis domain = new DateAxis("Current Time, seconds");
		domain.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));
		domain.setLabelFont(new Font("SansSerif", Font.PLAIN, 14));
		domain.setAutoRange(true);
		domain.setLowerMargin(0.0);
		domain.setUpperMargin(0.0);
		domain.setTickLabelsVisible(true);
		
	  return domain;
	}
	
	private NumberAxis createNumberAxis() {
		
		NumberAxis range = new NumberAxis("Speed, kbits/s");
		range.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));
		range.setLabelFont(new Font("SansSerif", Font.PLAIN, 14));
		range.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		
	   return range;
	}
	
	private XYItemRenderer createXYItemRenderer() {
		
		XYItemRenderer renderer = new XYLineAndShapeRenderer(true, false);
		renderer.setSeriesPaint(0, Color.BLUE);
		renderer.setSeriesPaint(1, Color.GREEN);
		renderer.setBaseStroke(new BasicStroke(3f, BasicStroke.CAP_BUTT,
		BasicStroke.JOIN_BEVEL));
		
	  return renderer;
	}
	
	private XYPlot createXYPlot() {
		
		XYPlot plot = new XYPlot(createDataSet(), createDateAxis(), createNumberAxis(), createXYItemRenderer());
		plot.setBackgroundPaint(Color.lightGray);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);
		plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
		
	  return plot;
		
	}
	
	private void init() {

		createDataSeries();
		chart = new JFreeChart("Current speed rate",
		new Font("SansSerif", Font.BOLD, 24), createXYPlot(), true);
		chart.setBackgroundPaint(Color.white);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(900, 300));
		chartPanel.setBorder(BorderFactory.createCompoundBorder(
		BorderFactory.createEmptyBorder(4, 4, 4, 4),
		BorderFactory.createLineBorder(Color.black)));
		setChart(chart);
		/*
		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
		data.horizontalSpan = 2;
		setLayoutData(data);
		*/
	}
    
	@Override
	public void addObservation(double ... param) {
		    
		    
		   this.in_rate.add(ms, param[0]);
		   this.in_rate.setKey(String.format("Current inbound speed - %.2f kbits/s", param[0]));
		   this.out_rate.add(ms, param[1]);
		   this.out_rate.setKey(String.format("Current outbound speed - %.2f kbits/s", param[1]));
	}


	@Override
	public void addObservation(Map<Object, Object> param) {
		
         throw new UnsupportedOperationException();
		
	}

}
