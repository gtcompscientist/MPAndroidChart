
package com.github.mikephil.charting.components;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.renderer.LineChartRenderer;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;

/**
 * Class representing the legend of the chart. The legend will contain one entry
 * per color and DataSet. Multiple colors in one DataSet are grouped together.
 * The legend object is NOT available before setting data to the chart.
 * 
 * @author Philipp Jahoda
 */
public class Legend {

    public enum LegendPosition {
        RIGHT_OF_CHART, RIGHT_OF_CHART_CENTER, RIGHT_OF_CHART_INSIDE, BELOW_CHART_LEFT, BELOW_CHART_RIGHT, BELOW_CHART_CENTER, PIECHART_CENTER
    }

    public enum LegendForm {
        SQUARE, CIRCLE, LINE
    }
    
    /** flag indicating if the legend should be drawn or not */
    private boolean mEnabled = true;

    /** the legend colors */
    private int[] mColors;

    /** the legend labels */
    private String[] mLabels;

    /** the position relative to the chart the legend is drawn on */
    private LegendPosition mPosition = LegendPosition.BELOW_CHART_LEFT;

    /** the shape/form the legend colors are drawn in */
    private LegendForm[] mShape = new LegendForm[] { LegendForm.SQUARE };

    /** the count of the array, to speed processing */
    private int mShapeCount = 1;

    /** the optional lineWidths for the legend */
    private Float[] mLineWidths;

    /** the count of the array, to speed processing */
    private int mLineWidthCount = 0;

    /** the optional dash effects to match the chart */
    private DashPathEffect[] mLineEffects;

    /** the optional dash effects to match the chart */
    private int mLineEffectCount = 0;

    /** the typeface used for the legend labels */
    private Typeface mTypeface = null;

    /** the text size of the legend labels */
    private float mTextSize = 10f;

    /** the text color to use */
    private int mTextColor = Color.BLACK;

    /** the size of the legend forms/shapes */
    private float mFormSize = 8f;

    /**
     * the space between the legend entries on a horizontal axis, default 6f
     */
    private float mXEntrySpace = 6f;

    /**
     * the space between the legend entries on a vertical axis, default 5f
     */
    private float mYEntrySpace = 5f;

    /**
     * the space between the legend entries on a vertical axis, default 2f
     * private float mYEntrySpace = 2f; /** the space between the form and the
     * actual label/text
     */
    private float mFormToTextSpace = 5f;

    /** the space that should be left between stacked forms */
    private float mStackSpace = 3f;    
    
    /** the offset in pixels this axis labels have on the x-axis */
    protected float mXOffset = 5f;
    
    /** the offset in pixels this axis labels have on the Y-axis */
    protected float mYOffset = 6f;

    /** default constructor */
    public Legend() {

        mFormSize = Utils.convertDpToPixel(8f);
        mXEntrySpace = Utils.convertDpToPixel(6f);
        mYEntrySpace = Utils.convertDpToPixel(5f);
        mFormToTextSpace = Utils.convertDpToPixel(5f);
        mTextSize = Utils.convertDpToPixel(10f);
        mStackSpace = Utils.convertDpToPixel(3f);
        this.mXOffset = Utils.convertDpToPixel(5f);
        this.mYOffset = Utils.convertDpToPixel(6f);
    }

    /**
     * Constructor. Provide colors and labels for the legend.
     * 
     * @param colors
     * @param labels
     */
    public Legend(int[] colors, String[] labels) {
        this();

        if (colors == null || labels == null) {
            throw new IllegalArgumentException("colors array or labels array is NULL");
        }

        if (colors.length != labels.length) {
            throw new IllegalArgumentException(
                    "colors array and labels array need to be of same size");
        }

        this.mColors = colors;
        this.mLabels = labels;
    }

    /**
     * Constructor. Provide colors and labels for the legend.
     * 
     * @param colors
     * @param labels
     */
    public Legend(ArrayList<Integer> colors, ArrayList<String> labels) {
        this(colors, labels, null);
    }
    /**
     * Constructor. Provide colors, labels, and shapes for the legend.
     *
     * @param colors
     * @param labels
     * @param shapes
     */
    public Legend(ArrayList<Integer> colors, ArrayList<String> labels, ArrayList<LegendForm> shapes) {
        this();

        if (colors == null || labels == null) {
            throw new IllegalArgumentException("colors array or labels array is NULL");
        }

        if (colors.size() != labels.size()) {
            throw new IllegalArgumentException(
                    "colors array and labels array need to be of same size");
        }

        if (shapes != null) {
            this.mShapeCount = shapes.size();
            this.mShape = shapes.toArray(new LegendForm[mShapeCount]);
        }

        this.mColors = Utils.convertIntegers(colors);
        this.mLabels = Utils.convertStrings(labels);
    }

    /**
     * returns the maximum length in pixels across all legend labels + formsize
     * + formtotextspace
     * 
     * @param p the paint object used for rendering the text
     * @return
     */
    public float getMaximumEntryWidth(Paint p) {

        float max = 0f;

        for (int i = 0; i < mLabels.length; i++) {

            if (mLabels[i] != null) {

                float length = (float) Utils.calcTextWidth(p, mLabels[i]);

                if (length > max)
                    max = length;
            }
        }

        return max + mFormSize + mFormToTextSpace;
    }

    /**
     * returns the maximum height in pixels across all legend labels
     * 
     * @param p the paint object used for rendering the text
     * @return
     */
    public float getMaximumEntryHeight(Paint p) {

        float max = 0f;

        for (int i = 0; i < mLabels.length; i++) {

            if (mLabels[i] != null) {

                float length = (float) Utils.calcTextHeight(p, mLabels[i]);

                if (length > max)
                    max = length;
            }
        }

        return max;
    }

    /**
     * returns all the colors the legend uses
     * 
     * @return
     */
    public int[] getColors() {
        return mColors;
    }

    /**
     * returns all the labels the legend uses
     * 
     * @return
     */
    public String[] getLegendLabels() {
        return mLabels;
    }

    /**
     * Sets a custom array of labels for the legend. Make sure the labels array
     * has the same length as the colors array.
     * 
     * @param labels
     */
    public void setLegendLabels(String[] labels) {

        if (mColors.length != labels.length) {
            throw new IllegalArgumentException(
                    "colors array and labels array need to be of same size");
        }

        this.mLabels = labels;
    }

    /**
     * Returns the legend-label at the given index.
     * 
     * @param index
     * @return
     */
    public String getLabel(int index) {
        return mLabels[index];
    }

    /**
     * returns the position of the legend relative to the chart
     * 
     * @return
     */
    public LegendPosition getPosition() {
        return mPosition;
    }

    /**
     * sets the position of the legend relative to the whole chart
     * 
     * @param pos
     */
    public void setPosition(LegendPosition pos) {
        mPosition = pos;
    }

    /**
     * returns the current form/shape that is set for the legend
     * 
     * @return
     */
    public LegendForm[] getForms() {
        return mShape;
    }


    /**
     * returns the current form/shape that is set for the legend
     *
     * @param index
     * @return
     */
    public LegendForm getForm(int index) {
        return mShape[index % mShapeCount];
    }

    /**
     * sets the form/shape of the legend forms
     * 
     * @param shape
     */
    public void setForms(ArrayList<LegendForm> shape) {
        mShapeCount = shape.size();
        mShape = shape.toArray(new LegendForm[mShapeCount]);
    }

    /**
     * sets the form/shape of the legend forms
     *
     * @param shape
     */
    public void setForms(LegendForm[] shape) {
        mShape = shape;
        mShapeCount = mShape.length;
    }

    /**
     * sets the form/shape of the legend forms
     *
     * @param index
     * @param shape
     */
    public void setForm(int index, LegendForm shape) {
        if (index < mShapeCount)
            mShape[index] = shape;
        LegendForm[] newShapes = new LegendForm[mShapeCount + 1];
        System.arraycopy(mShape, 0, newShapes, 0, mShapeCount);
        mShape = newShapes;
        mShape[mShapeCount] = shape;
        mShapeCount++;
    }

    /**
     * returns the typeface used for the legend labels, returns null if none is
     * set
     * 
     * @return
     */
    public Typeface getTypeface() {
        return mTypeface;
    }

    /**
     * sets a specific typeface for the legend labels
     * 
     * @param tf
     */
    public void setTypeface(Typeface tf) {
        mTypeface = tf;
    }

    /**
     * sets the size in pixels of the legend forms, this is internally converted
     * in dp, default 8f
     * 
     * @param size
     */
    public void setFormSize(float size) {
        mFormSize = Utils.convertDpToPixel(size);
    }

    /**
     * returns the size in dp of the legend forms
     * 
     * @return
     */
    public float getFormSize() {
        return mFormSize;
    }

    /**
     * returns the space between the legend entries on a horizontal axis in
     * pixels
     * 
     * @return
     */
    public float getXEntrySpace() {
        return mXEntrySpace;
    }

    /**
     * sets the space between the legend entries on a horizontal axis in pixels,
     * converts to dp internally
     * 
     * @param space
     */
    public void setXEntrySpace(float space) {
        mXEntrySpace = Utils.convertDpToPixel(space);
    }

    /**
     * returns the space between the legend entries on a vertical axis in pixels
     * 
     * @return
     */
    public float getYEntrySpace() {
        return mYEntrySpace;
    }

    /**
     * sets the space between the legend entries on a vertical axis in pixels,
     * converts to dp internally
     * 
     * @param space
     */
    public void setYEntrySpace(float space) {
        mYEntrySpace = Utils.convertDpToPixel(space);
    }

    /**
     * returns the space between the form and the actual label/text
     * 
     * @return
     */
    public float getFormToTextSpace() {
        return mFormToTextSpace;
    }

    /**
     * sets the space between the form and the actual label/text, converts to dp
     * internally
     * 
     * @param space
     */
    public void setFormToTextSpace(float space) {
        this.mFormToTextSpace = Utils.convertDpToPixel(space);
    }

    /**
     * draws the form at the given position with the color at the given index
     * 
     * @param c canvas to draw with
     * @param x
     * @param y
     * @param p paint to use for drawing
     * @param index the index of the color to use (in the colors array)
     */
    public void drawForm(Canvas c, float x, float y, Paint p, int index) {

        if (mColors[index] == -2)
            return;

        p.setStyle(Paint.Style.FILL);
        p.setColor(mColors[index]);
        p.setStrokeWidth(0f);
        p.setPathEffect(null);

        float half = mFormSize / 2f;

        switch (getForm(index)) {
            case CIRCLE:
                c.drawCircle(x + half, y + half, half, p);
                break;
            case SQUARE:
                c.drawRect(x, y, x + mFormSize, y + mFormSize, p);
                break;
            case LINE:
                p.setStyle(Paint.Style.STROKE);
                p.setStrokeWidth(getLineWidth(index));
                p.setPathEffect(getDashPathEffect(index));
                Path line = new Path();
                line.moveTo(x, y+ half);
                line.lineTo(x + mFormSize, y + half);
                c.drawPath(line, p);
                break;
        }
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(0f);
        p.setPathEffect(null);
    }

    /**
     * draws the label at the given index in the labels array at the given
     * position
     * 
     * @param c canvas to draw with
     * @param x
     * @param y
     * @param p paint to use for drawing
     * @param index index in the labels-array
     */
    public void drawLabel(Canvas c, float x, float y, Paint p, int index) {

        c.drawText(mLabels[index], x, y, p);
    }

    /**
     * applies the state from the legend in the parameter to this legend (except
     * colors, labels and offsets)
     * 
     * @param l
     */
    public void apply(Legend l) {

        mPosition = l.mPosition;
        mShape = l.mShape;
        mShapeCount = mShape.length;
        mTypeface = l.mTypeface;
        mFormSize = l.mFormSize;
        mXEntrySpace = l.mXEntrySpace;
        mYEntrySpace = l.mYEntrySpace;
        mFormToTextSpace = l.mFormToTextSpace;
        mTextSize = l.mTextSize;
        mStackSpace = l.mStackSpace;
        mTextColor = l.mTextColor;
        mEnabled = l.mEnabled;
        mXOffset = l.mXOffset;
        mYOffset = l.mYOffset;
        mLineWidths = l.mLineWidths;
        mLineEffects = l.mLineEffects;
    }

    /**
     * sets the text size of the legend labels, default 9f
     * 
     * @param size
     */
    public void setTextSize(float size) {
        mTextSize = Utils.convertDpToPixel(size);
    }

    /**
     * returns the text size of the legend labels
     * 
     * @return
     */
    public float getTextSize() {
        return mTextSize;
    }

    /**
     * returns the space that is left out between stacked forms (with no label)
     * 
     * @return
     */
    public float getStackSpace() {
        return mStackSpace;
    }

    /**
     * sets the space that is left out between stacked forms (with no label)
     * 
     * @param space
     */
    public void setStackSpace(float space) {
        mStackSpace = space;
    }

    /**
     * calculates the full width the fully drawn legend will use in pixels
     *
     * @param labelPaint
     * @return
     */
    public float getFullWidth(Paint labelPaint) {

        float width = 0f;

        for (int i = 0; i < mLabels.length; i++) {

            // grouped forms have null labels
            if (mLabels[i] != null) {

                // make a step to the left
                if (mColors[i] != -2)
                    width += mFormSize + mFormToTextSpace;

                width += Utils.calcTextWidth(labelPaint, mLabels[i])
                        + mXEntrySpace;
            } else {
                width += mFormSize + mStackSpace;
            }
        }

        return width;
    }

    /**
     * Calculates the full height of the drawn legend.
     * 
     * @param labelPaint
     * @return
     */
    public float getFullHeight(Paint labelPaint) {

        float height = 0f;

        for (int i = 0; i < mLabels.length; i++) {

            // grouped forms have null labels
            if (mLabels[i] != null) {

                height += Utils.calcTextHeight(labelPaint, mLabels[i])
                        + mYEntrySpace;
            }
        }

        return height;
    }

    /** the total width of the legend (needed width space) */
    public float mNeededWidth = 0f;

    /** the total height of the legend (needed height space) */
    public float mNeededHeight = 0f;
    
    public float mTextHeightMax = 0f;
    
    public float mTextWidthMax = 0f;

    /**
     * Calculates the dimensions of the Legend. This includes the maximum width
     * and height of a single entry, as well as the total width and height of
     * the Legend.
     * 
     * @param labelPaint
     */
    public void calculateDimensions(Paint labelPaint) {

        if (mPosition == LegendPosition.RIGHT_OF_CHART
                || mPosition == LegendPosition.RIGHT_OF_CHART_CENTER
                || mPosition == LegendPosition.PIECHART_CENTER) {
            mNeededWidth = getMaximumEntryWidth(labelPaint);
            mNeededHeight = getFullHeight(labelPaint);
            mTextWidthMax = mNeededWidth;
            mTextHeightMax = getMaximumEntryHeight(labelPaint);

        } else {

            mNeededWidth = getFullWidth(labelPaint);
            mNeededHeight = getMaximumEntryHeight(labelPaint);
            mTextWidthMax = getMaximumEntryWidth(labelPaint);
            mTextHeightMax = mNeededHeight;
        }
    }

    /**
     * Sets the text color to use for the legend labels. Make sure to use
     * getResources().getColor(...) when using a color from the resources.
     * 
     * @param color
     */
    public void setTextColor(int color) {
        mTextColor = color;
    }

    /**
     * Returns the text color that is set for the legend labels.
     * 
     * @return
     */
    public int getTextColor() {
        return mTextColor;
    }

    /**
     * Set this to true if the legend should be enabled (should be drawn), false
     * if not. Default: true
     * 
     * @param enabled
     */
    public void setEnabled(boolean enabled) {
        mEnabled = enabled;
    }

    /**
     * Returns true if the legend is enabled (should be drawn), false if not.
     * 
     * @return
     */
    public boolean isEnabled() {
        return mEnabled;
    }
    
    /**
     * Returns the used offset on the x-axis for drawing the axis labels. This
     * offset is applied before and after the label.
     * 
     * @return
     */
    public float getXOffset() {
        return mXOffset;
    }

    /**
     * Sets the used x-axis offset for the labels on this axis.
     * 
     * @param xOffset
     */
    public void setXOffset(float xOffset) {
        mXOffset = Utils.convertDpToPixel(xOffset);
    }
    
    /**
     * Returns the used offset on the x-axis for drawing the axis labels. This
     * offset is applied before and after the label.
     * 
     * @return
     */
    public float getYOffset() {
        return mYOffset;
    }

    /**
     * Sets the used x-axis offset for the labels on this axis.
     * 
     * @param yOffset
     */
    public void setYOffset(float yOffset) {
        mYOffset = Utils.convertDpToPixel(yOffset);
    }

    /**
     * Returns the array of lineWidths for the legend
     *
     * @return
     */
    public Float[] getLineWidths() {
        return mLineWidths;
    }

    /**
     * Returns the indexed lineWidth for the legend
     *
     * @param index
     * @return
     */
    public float getLineWidth(int index) {
        if (mLineWidths == null || mLineWidthCount == 0)
            return 1f;
        return mLineWidths[index % mLineWidthCount];
    }

    /**
     * sets the lineWidths of the legend forms
     *
     * @param widths
     */
    public void setLineWidths(ArrayList<Float> widths) {
        mLineWidthCount = widths.size();
        mLineWidths = widths.toArray(new Float[mLineWidthCount]);
    }

    /**
     * sets the lineWidths of the legend forms
     *
     * @param widths
     */
    public void setLineWidths(Float[] widths) {
        mLineWidths = widths;
        mLineWidthCount = mLineWidths.length;
    }

    /**
     * sets the lineWidths of the legend forms
     *
     * @param index
     * @param width
     */
    public void setLineWidth(int index, float width) {
        if (index < mLineWidthCount)
            mLineWidths[index] = width;

        Float[] newWidths = new Float[mLineWidthCount + 1];
        System.arraycopy(mLineWidths, 0, newWidths, 0, mLineWidthCount);
        mLineWidths = newWidths;
        mLineWidths[mLineWidthCount] = width;
        mLineWidthCount++;
    }

    /**
     * Returns the array of lineEffects for the legend
     *
     * @return
     */
    public DashPathEffect[] getDashPathEffects() {
        return mLineEffects;
    }

    /**
     * Returns the indexed lineEffect for the legend
     *
     * @param index
     * @return
     */
    public DashPathEffect getDashPathEffect(int index) {
        if (mLineEffects == null || mLineEffectCount == 0)
            return null;
        return mLineEffects[index % mLineEffectCount];
    }

    /**
     * sets the lineEffects of the legend forms
     *
     * @param effects
     */
    public void setDashPathEffects(ArrayList<DashPathEffect> effects) {
        mLineEffectCount = effects.size();
        mLineEffects = effects.toArray(new DashPathEffect[mLineEffectCount]);
    }

    /**
     * sets the lineWidths of the legend forms
     *
     * @param effects
     */
    public void setDashPathEffects(DashPathEffect[] effects) {
        mLineEffects = effects;
        mLineEffectCount = mLineEffects.length;
    }

    /**
     * sets the lineWidths of the legend forms
     *
     * @param index
     * @param effect
     */
    public void setDashPathEffect(int index, DashPathEffect effect) {
        if (index < mLineEffectCount)
            mLineEffects[index] = effect;

        DashPathEffect[] newEffects = new DashPathEffect[mLineEffectCount + 1];
        System.arraycopy(mLineEffects, 0, newEffects, 0, mLineEffectCount);
        mLineEffects = newEffects;
        mLineEffects[mLineEffectCount] = effect;
        mLineEffectCount++;
    }
}
