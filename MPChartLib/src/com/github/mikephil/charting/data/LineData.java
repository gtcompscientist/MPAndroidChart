
package com.github.mikephil.charting.data;

import java.util.ArrayList;

public class LineData extends BarLineScatterCandleData<LineDataSet> {

    public LineData() {
        super();
    }

    public LineData(ArrayList<String> xVals, ArrayList<Float> xPoints) {
        super(xVals, xPoints);
    }

    public LineData(String[] xVals, float[] xPoints) {
        super(xVals, xPoints);
    }

    public LineData(ArrayList<String> xVals, ArrayList<Float> xPoints, ArrayList<LineDataSet> dataSets) {
        super(xVals, xPoints, dataSets);
    }

    public LineData(String[] xVals, float[] xPoints, ArrayList<LineDataSet> dataSets) {
        super(xVals, xPoints, dataSets);
    }

    public LineData(ArrayList<String> xVals, ArrayList<Float> xPoints, LineDataSet dataSet) {
        super(xVals, xPoints, toArrayList(dataSet));
    }

    public LineData(String[] xVals, float[] xPoints, LineDataSet dataSet) {
        super(xVals, xPoints, toArrayList(dataSet));
    }

    private static ArrayList<LineDataSet> toArrayList(LineDataSet dataSet) {
        ArrayList<LineDataSet> sets = new ArrayList<LineDataSet>();
        sets.add(dataSet);
        return sets;
    }
}
