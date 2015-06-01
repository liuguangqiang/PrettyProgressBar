/*
 *
 *  * Copyright 2014 Eric Liu
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

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
public abstract class AbsProgressBar extends View {

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

    private static final int DEFAULT_SIZE = 80;
    private static final int DEFAULT_BACKGROUND_COLOR = Color.parseColor("#999999");
    private static final int DEFAULT_PROGRESS_COLOR = Color.parseColor("#54bfad");
    private static final int DEFAULT_PROGRESS_END_COLOR = 0;
    private static final StartLocation DEFAULT_START_LOCATION = StartLocation.TOP;
    private static final int DEFAULT_MAX = 100;
    private float DEFAULT_STROKE_WIDTH = 6;
    private static final boolean DEFAULT_TRANSITION_ENABLE = false;
    private static final PaintStyle DEFAULT_PAINT_STYLE = PaintStyle.STROKE;

    public int mProgressBackgroundColor;
    public int mProgressColor;
    public int mProgressEndColor;
    public StartLocation mStartLocation;
    private int mMax;
    private int mProgress;
    private PaintStyle mPaintStyle;

    private static final int END_ANGLE = 360;

    public Paint mProgressPaint;
    public Paint mProgressBgPaint;

    public RectF mProgressRectF = new RectF(0, 0, 0, 0);
    public RectF mProgressBgRectF = new RectF(0, 0, 0, 0);

    public float mStrokeWith = 0;

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
        invalidate();
    }

    public int getPrgoress() {
        return mProgress;
    }

    public AbsProgressBar(Context context) {
        this(context, null);
    }

    public AbsProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AbsProgressBar(Context context, AttributeSet attrs, int defStyle) {
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

        DEFAULT_STROKE_WIDTH = DisplayUtils.dp2px(getContext(), DEFAULT_STROKE_WIDTH);
        mStrokeWith = attributes.getDimension(R.styleable.MyProgressBar_stroke_width, DEFAULT_STROKE_WIDTH);

        int paintStyle = attributes.getInt(R.styleable.MyProgressBar_paint_style, DEFAULT_PAINT_STYLE.ordinal());
        mPaintStyle = PaintStyle.values()[paintStyle];
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measure(widthMeasureSpec), measure(heightMeasureSpec));
    }

    private int measure(int measureSpec) {
        int result;
        int mode = View.MeasureSpec.getMode(measureSpec);
        int size = View.MeasureSpec.getSize(measureSpec);
        if (mode == View.MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = (int) DisplayUtils.dp2px(getContext(), DEFAULT_SIZE);
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

    public abstract void drawProgress(Canvas canvas);

    public abstract void drawProgressBg(Canvas canvas);

    public void setProgress(int progress) {
        mProgress = progress;
        invalidate();
    }

}
