package com.github.mikephil.charting.data;

import java.util.ArrayList;

public class CandleData extends BarLineScatterCandleData<CandleDataSet> {

    public CandleData() {
        super();
    }
    
    public CandleData(ArrayList<String> xVals, ArrayList<Float> xPoints) {
        super(xVals, xPoints);
    }
    
    public CandleData(String[] xVals, float[] xPoints) {
        super(xVals, xPoints);
    }
    
    public CandleData(ArrayList<String> xVals, ArrayList<Float> xPoints, ArrayList<CandleDataSet> dataSets) {
        super(xVals, xPoints, dataSets);
    }

    public CandleData(String[] xVals, float[] xPoints, ArrayList<CandleDataSet> dataSets) {
        super(xVals, xPoints, dataSets);
    }
    
    public CandleData(ArrayList<String> xVals, ArrayList<Float> xPoints, CandleDataSet dataSet) {
        super(xVals, xPoints, toArrayList(dataSet));
    }
    
    public CandleData(String[] xVals, float[] xPoints, CandleDataSet dataSet) {
        super(xVals, xPoints, toArrayList(dataSet));
    }
    
    private static ArrayList<CandleDataSet> toArrayList(CandleDataSet dataSet) {
        ArrayList<CandleDataSet> sets = new ArrayList<CandleDataSet>();
        sets.add(dataSet);
        return sets;
    }
}
