package com.liuguangqiang.progressbar.sample;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 弧形进度条
 * 
 * @author Eric
 * 
 */
public class ArcProgressbar11 extends View {

    public ArcProgressbar11(Context context) {
        super(context);
    }

    public ArcProgressbar11(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        init(canvas);
    }

    private void init(Canvas canvas) {
        // 画弧形的矩阵区域。
        rectBg = new RectF(15, 15, diameter, diameter);

        // 计算弧形的圆心和半径。
        int cx1 = (diameter + 15) / 2;
        int cy1 = (diameter + 15) / 2;
        int arcRadius = (diameter - 15) / 2;
        // ProgressBar结尾和开始画2个圆，实现ProgressBar的圆角。
        mPaintCircle = new Paint();
        mPaintCircle.setAntiAlias(true);
        mPaintCircle.setColor(bgColor);

        canvas.drawCircle((float) (cx1 + arcRadius * Math.cos(startAngle * 3.14 / 180)),
                (float) (cy1 + arcRadius * Math.sin(startAngle * 3.14 / 180)), bgStrokeWidth / 2,
                mPaintCircle);// 小圆

        canvas.drawCircle((float) (cx1 + arcRadius * Math.cos((180 - startAngle) * 3.14 / 180)),
                (float) (cy1 + arcRadius * Math.sin((180 - startAngle) * 3.14 / 180)),
                bgStrokeWidth / 2, mPaintCircle);// 小圆

        // 弧形背景。
        mPaintBg = new Paint();
        mPaintBg.setAntiAlias(true);
        mPaintBg.setStyle(Style.STROKE);
        mPaintBg.setStrokeWidth(bgStrokeWidth);
        mPaintBg.setColor(bgColor);
        canvas.drawArc(rectBg, startAngle, endAngle, false, mPaintBg);

        // 弧形小背景。
        if (showSmallBg) {
            mPaintSmallBg = new Paint();
            mPaintSmallBg.setAntiAlias(true);
            mPaintSmallBg.setStyle(Style.STROKE);
            mPaintSmallBg.setStrokeWidth(barStrokeWidth);
            mPaintSmallBg.setColor(smallBgColor);
            canvas.drawArc(rectBg, startAngle, endAngle, false, mPaintSmallBg);
        }

        // 弧形ProgressBar。
        mPaintBar = new Paint();
        mPaintBar.setAntiAlias(true);
        mPaintBar.setStyle(Style.STROKE);
        mPaintBar.setStrokeWidth(barStrokeWidth);
        mPaintBar.setColor(barColor);
        canvas.drawArc(rectBg, startAngle, progress, false, mPaintBar);

        // 随ProgressBar移动的圆。
        if (showMoveCircle) {
            mPaintCircle.setColor(barColor);
            canvas.drawCircle((float) (cx1 + arcRadius * Math.cos(angleOfMoveCircle * 3.14 / 180)),
                    (float) (cy1 + arcRadius * Math.sin(angleOfMoveCircle * 3.14 / 180)),
                    bgStrokeWidth / 2, mPaintCircle);// 小圆
        }

        invalidate();
    }

    /**
     * 
     * @param progress
     */
    public void addProgress(int _progress) {
        progress += _progress;
        angleOfMoveCircle += _progress;
        System.out.println(progress);
        if (progress > endAngle) {
            progress = 0;
            angleOfMoveCircle = startAngle;
        }
        invalidate();
    }

    /**
     * 设置弧形背景的画笔宽度。
     */
    public void setBgStrokeWidth(int bgStrokeWidth) {
        this.bgStrokeWidth = bgStrokeWidth;
    }

    /**
     * 设置弧形ProgressBar的画笔宽度。
     */
    public void setBarStrokeWidth(int barStrokeWidth) {
        this.barStrokeWidth = barStrokeWidth;
    }

    /**
     * 设置弧形背景的颜色。
     */
    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    /**
     * 设置弧形ProgressBar的颜色。
     */
    public void setBarColor(int barColor) {
        this.barColor = barColor;
    }

    /**
     * 设置弧形小背景的颜色。
     */
    public void setSmallBgColor(int smallBgColor) {
        this.smallBgColor = smallBgColor;
    }

    /**
     * 设置弧形的直径。
     */
    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    /**
     * 是否显示小背景。
     */
    public void setShowSmallBg(boolean showSmallBg) {
        this.showSmallBg = showSmallBg;
    }

    /**
     * 是否显示移动的小圆。
     */
    public void setShowMoveCircle(boolean showMoveCircle) {
        this.showMoveCircle = showMoveCircle;
    }

    private int bgStrokeWidth = 44;
    private int barStrokeWidth = 15;
    private int bgColor = Color.GRAY;
    private int barColor = Color.RED;
    private int smallBgColor = Color.WHITE;
    private int progress = 0;
    private int angleOfMoveCircle = 140;// 移动小园的起始角度。
    private int startAngle = 140;
    private int endAngle = 260;
    private Paint mPaintBar = null;
    private Paint mPaintSmallBg = null;
    private Paint mPaintBg = null;
    private Paint mPaintCircle = null;
    private RectF rectBg = null;
    /**
     * 直徑。
     */
    private int diameter = 450;

    private boolean showSmallBg = true;// 是否显示小背景。
    private boolean showMoveCircle = true;// 是否显示移动的小园。

}
