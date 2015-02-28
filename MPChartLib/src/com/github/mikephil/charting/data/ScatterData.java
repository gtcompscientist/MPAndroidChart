
package com.github.mikephil.charting.data;

import java.util.ArrayList;

public class ScatterData extends BarLineScatterCandleData<ScatterDataSet> {

    public ScatterData() {
        super();
    }
    
    public ScatterData(ArrayList<String> xVals, ArrayList<Float> xPoints) {
        super(xVals, xPoints);
    }

    public ScatterData(String[] xVals, float[] xPoints) {
        super(xVals, xPoints);
    }

    public ScatterData(ArrayList<String> xVals, ArrayList<Float> xPoints, ArrayList<ScatterDataSet> dataSets) {
        super(xVals, xPoints, dataSets);
    }

    public ScatterData(String[] xVals, float[] xPoints, ArrayList<ScatterDataSet> dataSets) {
        super(xVals, xPoints, dataSets);
    }

    public ScatterData(ArrayList<String> xVals, ArrayList<Float> xPoints, ScatterDataSet dataSet) {
        super(xVals, xPoints, toArrayList(dataSet));
    }

    public ScatterData(String[] xVals, float[] xPoints, ScatterDataSet dataSet) {
        super(xVals, xPoints, toArrayList(dataSet));
    }

    private static ArrayList<ScatterDataSet> toArrayList(ScatterDataSet dataSet) {
        ArrayList<ScatterDataSet> sets = new ArrayList<ScatterDataSet>();
        sets.add(dataSet);
        return sets;
    }

    /**
     * Returns the maximum shape-size across all DataSets.
     * 
     * @return
     */
    public float getGreatestShapeSize() {

        float max = 0f;

        for (ScatterDataSet set : mDataSets) {
            float size = set.getScatterShapeSize();

            if (size > max)
                max = size;
        }

        return max;
    }
}
