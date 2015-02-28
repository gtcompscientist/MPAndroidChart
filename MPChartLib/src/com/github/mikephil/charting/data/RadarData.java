
package com.github.mikephil.charting.data;

import java.util.ArrayList;

/**
 * Data container for the RadarChart.
 * 
 * @author Philipp Jahoda
 */
public class RadarData extends ChartData<RadarDataSet> {

    public RadarData() {
        super();
    }
    
    public RadarData(ArrayList<String> xVals, ArrayList<Float> xPoints) {
        super(xVals, xPoints);
    }
    
    public RadarData(String[] xVals, float[] xPoints) {
        super(xVals, xPoints);
    }
    
    public RadarData(ArrayList<String> xVals, ArrayList<Float> xPoints, ArrayList<RadarDataSet> dataSets) {
        super(xVals, xPoints, dataSets);
    }

    public RadarData(String[] xVals, float[] xPoints, ArrayList<RadarDataSet> dataSets) {
        super(xVals, xPoints, dataSets);
    }

    public RadarData(ArrayList<String> xVals, ArrayList<Float> xPoints, RadarDataSet dataSet) {
        super(xVals, xPoints, toArrayList(dataSet));
    }

    public RadarData(String[] xVals, float[] xPoints, RadarDataSet dataSet) {
        super(xVals, xPoints, toArrayList(dataSet));
    }
    
    private static ArrayList<RadarDataSet> toArrayList(RadarDataSet dataSet) {
        ArrayList<RadarDataSet> sets = new ArrayList<RadarDataSet>();
        sets.add(dataSet);
        return sets;
    }
}
