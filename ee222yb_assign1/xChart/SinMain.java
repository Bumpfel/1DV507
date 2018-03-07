package ee222yb_assign1.xChart;

import java.util.ArrayList;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

public class SinMain {

	public static void main(String[] args) {
		final int POINTS = 1000;
		final double X_MAX = 2 * Math.PI;
		
		XYChart chart = new XYChartBuilder().width(800).height(600).build();
		chart.getStyler().setXAxisMin(0.0);
		chart.getStyler().setXAxisMax(X_MAX);
		chart.setXAxisTitle("x");
		chart.setYAxisTitle("y");
	    chart.getStyler().setMarkerSize(0);
		
		ArrayList<Double> xData = new ArrayList<>();
		ArrayList<Double> yData = new ArrayList<>();
		for(int i = 0; i < POINTS; i ++) { 
			xData.add((double) i / POINTS * X_MAX);
			yData.add((1 + xData.get(i) / Math.PI) * Math.cos(xData.get(i)) * Math.cos(40 * xData.get(i)));
		}
		
		chart.addSeries("Sine Curve", xData, yData);
		new SwingWrapper<XYChart>(chart).displayChart();
	}

}
