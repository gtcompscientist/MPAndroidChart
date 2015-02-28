
package com.github.mikephil.charting.data;

import java.util.ArrayList;

/**
 * Baseclass for all Line, Bar and ScatterData.
 * 
 * @author Philipp Jahoda
 */
public abstract class BarLineScatterCandleData<T extends BarLineScatterCandleDataSet<? extends Entry>>
        extends ChartData<T> {
    
    public BarLineScatterCandleData() {
        super();
    }
    
    public BarLineScatterCandleData(ArrayList<String> xVals, ArrayList<Float> xPoints) {
        super(xVals, xPoints);
    }
    
    public BarLineScatterCandleData(String[] xVals, float[] xPoints) {
        super(xVals, xPoints);
    }

    public BarLineScatterCandleData(ArrayList<String> xVals, ArrayList<Float> xPoints, ArrayList<T> sets) {
        super(xVals, xPoints, sets);
    }

    public BarLineScatterCandleData(String[] xVals, float[] xPoints, ArrayList<T> sets) {
        super(xVals, xPoints, sets);
    }
}
