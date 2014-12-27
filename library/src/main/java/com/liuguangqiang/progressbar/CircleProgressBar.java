package com.liuguangqiang.progressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.liuguangqiang.arcprogressbar.R;

/**
 * Created by Eric on 14/12/27.
 */
public class CircleProgressBar extends View {

    public enum StartLocation {
        TOP,
        RIGHT,
        BOTTOM,
        LEFT
    }

    public enum PaintStyle {
        STROKE,
        FILL
    }

    private static final String TAG = "ArcProgressBar";

    private static final float MAX_SWEEP_ANGLE = 360;

    private static final int DEFAULT_SIZE = 80;
    private static final int DEFAULT_BACKGROUND_COLOR = Color.parseColor("#999999");
    private static final int DEFAULT_PROGRESS_COLOR = Color.parseColor("#54bfad");
    private static final int DEFAULT_PROGRESS_END_COLOR = 0;
    private static final StartLocation DEFAULT_START_LOCATION = StartLocation.TOP;
    private static final int DEFAULT_MAX = 100;
    private float DEFAULT_STROKE_WIDTH = 6;
    private static final boolean DEFAULT_TRANSITION_ENABLE = false;
    private static final PaintStyle DEFAULT_PAINT_STYLE = PaintStyle.STROKE;

    private int mProgressBackgroundColor;
    private int mProgressColor;
    private int mProgressEndColor;
    private StartLocation mStartLocation;
    private int mMax;
    private int mProgress;
    private PaintStyle mPaintStyle;

    private int startAngle = 0;

    private int endAngle = 360;

    private int progressSweepAngle = 0;

    private Paint mProgressPaint;
    private Paint mProgressBgPaint;

    private RectF mProgressRectF = new RectF(0, 0, 0, 0);
    private RectF mProgressBgRectF = new RectF(0, 0, 0, 0);

    private float mStrokeWith = 0;

    private float unitAngle = 0.0f;

    private float getStrokeOffset() {
        if (mPaintStyle == PaintStyle.FILL) return 0;
        return mStrokeWith / 2;
    }

    public int getMax() {
        return mMax;
    }

    public void setMax(int max) {
        mMax = max;
        setUnitProgress();
        invalidate();
    }

    public int getPrgoress() {
        return mProgress;
    }

    public CircleProgressBar(Context context) {
        this(context, null);
    }

    public CircleProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        loadStyledAttr(context, attrs, defStyle);
        initPaint();
    }

    private void loadStyledAttr(Context context, AttributeSet attrs, int defStyle) {
        final TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyProgressBar,
                defStyle, 0);
        mProgressBackgroundColor = attributes.getColor(R.styleable.MyProgressBar_background_color, DEFAULT_BACKGROUND_COLOR);
        mProgressColor = attributes.getColor(R.styleable.MyProgressBar_progress_color, DEFAULT_PROGRESS_COLOR);
        mProgressEndColor = attributes.getColor(R.styleable.MyProgressBar_progress_end_color, DEFAULT_PROGRESS_END_COLOR);

        int startLocation = attributes.getInt(R.styleable.MyProgressBar_start_location, DEFAULT_START_LOCATION.ordinal());
        mStartLocation = StartLocation.values()[startLocation];
        mMax = attributes.getInt(R.styleable.MyProgressBar_max, DEFAULT_MAX);
        mProgress = attributes.getInt(R.styleable.MyProgressBar_progress, 0);

        DEFAULT_STROKE_WIDTH = dp2px(DEFAULT_STROKE_WIDTH);
        mStrokeWith = attributes.getDimension(R.styleable.MyProgressBar_stroke_width, DEFAULT_STROKE_WIDTH);

        int paintStyle = attributes.getInt(R.styleable.MyProgressBar_paint_style, DEFAULT_PAINT_STYLE.ordinal());
        mPaintStyle = PaintStyle.values()[paintStyle];
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measure(widthMeasureSpec), measure(heightMeasureSpec));
        setUnitProgress();
    }

    private int measure(int measureSpec) {
        int result;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = (int) dp2px(DEFAULT_SIZE);
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawProgressBg(canvas);
        drawProgress(canvas);
    }

    private void initPaint() {
        //Progress
        mProgressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mProgressPaint.setColor(mProgressColor);

        //Progress Background
        mProgressBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mProgressBgPaint.setColor(mProgressBackgroundColor);


        if (mPaintStyle == PaintStyle.STROKE) {
            mProgressPaint.setStyle(Paint.Style.STROKE);
            mProgressPaint.setStrokeWidth(mStrokeWith);

            mProgressBgPaint.setStyle(Paint.Style.STROKE);
            mProgressBgPaint.setStrokeWidth(mStrokeWith);
        }
    }

    private void drawProgress(Canvas canvas) {
        mProgressRectF.left = getPaddingLeft() + getStrokeOffset();
        mProgressRectF.top = getPaddingTop() + getStrokeOffset();
        mProgressRectF.right = getWidth() - getPaddingLeft() - getPaddingRight() - getStrokeOffset();
        mProgressRectF.bottom = getHeight() - getStrokeOffset();
        canvas.drawArc(mProgressRectF, getStartAngle(), getSweepAngel(), getUseCenter(), mProgressPaint);
    }

    private void drawProgressBg(Canvas canvas) {
        mProgressBgRectF.left = getPaddingLeft() + getStrokeOffset();
        mProgressBgRectF.top = getPaddingTop() + getStrokeOffset();
        mProgressBgRectF.right = getWidth() - getPaddingLeft() - getPaddingRight() - getStrokeOffset();
        mProgressBgRectF.bottom = getHeight() - getStrokeOffset();
        canvas.drawArc(mProgressBgRectF, startAngle, endAngle, getUseCenter(), mProgressBgPaint);
    }

    private boolean getUseCenter() {
        return mPaintStyle == PaintStyle.FILL;
    }

    private void setUnitProgress() {
        unitAngle = MAX_SWEEP_ANGLE / mMax;
    }

    public void setProgress(int progress) {
        mProgress = progress;
        if (mProgressEndColor != 0)
            onProgressChanged();
        invalidate();
    }

    private int getSweepAngel() {
        return (int) (unitAngle * mProgress);
    }

    private int getStartAngle() {
        switch (mStartLocation) {
            case RIGHT:
                return 0;
            case BOTTOM:
                return 90;
            case LEFT:
                return 180;
            case TOP:
                return 270;
        }
        return 0;
    }

    private float dp2px(float dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }

    private void onProgressChanged() {
        float fraction = (float) mProgress / (float) mMax;
        int c = (Integer) ColorUtils.evaluate(fraction, mProgressColor, mProgressEndColor);
        mProgressPaint.setColor(c);
    }

}
