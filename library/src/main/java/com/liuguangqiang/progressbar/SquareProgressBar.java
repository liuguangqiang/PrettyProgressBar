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
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

/**
 * Created by Eric on 14/12/27.
 */
public class SquareProgressBar extends AbsProgressBar {

    public SquareProgressBar(Context context) {
        this(context, null);
    }

    public SquareProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SquareProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initPaint();
    }

    private void initPaint() {
        mProgressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mProgressPaint.setColor(mProgressColor);
        mProgressPaint.setStrokeWidth(mStrokeWith);

        mProgressBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mProgressBgPaint.setStrokeWidth(mStrokeWith);
        mProgressBgPaint.setStyle(Paint.Style.STROKE);
        mProgressBgPaint.setColor(mProgressBackgroundColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        initUnitProgress();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawProgressBg(canvas);
        drawProgress(canvas);
    }

    @Override
    public void drawProgress(Canvas canvas) {

        if (getPrgoress() >= getQuarterProgress() * 0) {
            drawTop(canvas);
        }

        if (getPrgoress() >= getQuarterProgress() * 1) {
            drawRight(canvas);
        }

        if (getPrgoress() >= getQuarterProgress() * 2) {
            drawBottom(canvas);
        }

        if (getPrgoress() >= getQuarterProgress() * 3) {
            drawLeft(canvas);
        }

    }

    int quarter = 0;
    int unit = 0;

    private void initUnitProgress() {
        quarter = getMax() / 4;
        unit = getWidth() / quarter;
    }

    private int getQuarterProgress() {
        return getMax() / 4;
    }

    private void drawTop(Canvas canvas) {

        canvas.drawLine(getPaddingLeft(), 0, unit * getPrgoress(), 0, mProgressPaint);
    }

    private void drawRight(Canvas canvas) {
        canvas.drawLine(getWidth() - getPaddingLeft() - getPaddingRight(), 0,
                getWidth() - getPaddingLeft() - getPaddingRight(), unit * (getPrgoress() - getQuarterProgress()),
                mProgressPaint);
    }

    private void drawBottom(Canvas canvas) {
        int endX = getWidth() - getPaddingLeft() - getPaddingRight();
        int startX = getWidth() - unit * (getPrgoress() - getQuarterProgress() * 2);
        canvas.drawLine(startX, getHeight(), endX, getHeight(),
                mProgressPaint);
    }

    private void drawLeft(Canvas canvas) {
        int startX = getPaddingLeft();
        int endX = getPaddingLeft();
        int startY = getHeight() - unit * (getPrgoress() - getQuarterProgress() * 3);
        int endY = getHeight();
        canvas.drawLine(startX, startY, endX, endY,
                mProgressPaint);
    }

    @Override
    public void drawProgressBg(Canvas canvas) {
        mProgressBgRectF.left = getPaddingLeft();
        mProgressBgRectF.top = getPaddingTop();
        mProgressBgRectF.right = getWidth() - getPaddingLeft() - getPaddingRight();
        mProgressBgRectF.bottom = getHeight();
        canvas.drawRect(mProgressBgRectF, mProgressBgPaint);
    }


}
