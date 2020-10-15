package com.example.wss_2000.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


import com.example.wss_2000.R;

import java.math.BigDecimal;

public class CSeekBarPressure extends View {
    private static final String TAG = "CSeekBarPressure";
    private static final int CLICK_ON_LOW = 1;
    private static final int CLICK_ON_HIGH = 2;
    private static final int CLICK_IN_LOW_AREA = 3;
    private static final int CLICK_IN_HIGH_AREA = 4;
    private static final int CLICK_OUT_AREA = 5;
    private static final int CLICK_INVAILD = 0;
    private static final int[] STATE_NORMAL = new int[0];
    private static final int[] STATE_PRESSED = new int[]{16842919, 16842909};
    private Drawable hasScrollBarBg;
    private Drawable notScrollBarBg;
    private Drawable mThumbLow;
    private Drawable mThumbHigh;
    private int mScollBarWidth;
    private int mScollBarHeight;
    private int mThumbWidth;
    private int mThumbHeight;
    private double mOffsetLow;
    private double mOffsetHigh;
    private int mDistance;
    private int mThumbMarginTop;
    private int mFlag;
    private CSeekBarPressure.OnSeekBarChangeListener mBarChangeListener;
    private double defaultScreenLow;
    private double defaultScreenHigh;
    private boolean showValue;

    public boolean isShowValue() {
        return this.showValue;
    }

    public void setShowValue(boolean showValue) {
        this.showValue = showValue;
        if (showValue) {
            this.mThumbMarginTop = 30;
        } else {
            this.mThumbMarginTop = 13;
        }

    }

    public CSeekBarPressure(Context context) {
        this(context, (AttributeSet)null);
    }

    public CSeekBarPressure(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CSeekBarPressure(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mOffsetLow = 0.0D;
        this.mOffsetHigh = 0.0D;
        this.mDistance = 0;
        this.mThumbMarginTop = 13;
        this.mFlag = 0;
        this.defaultScreenLow = 0.0D;
        this.defaultScreenHigh = 100.0D;
        this.showValue = false;
        Resources resources = this.getResources();
        this.notScrollBarBg = resources.getDrawable(R.drawable.seekbarpressure_bg_progress2);
        this.hasScrollBarBg = resources.getDrawable(R.drawable.seekbarpressure_bg_normal2);
        this.mThumbLow = resources.getDrawable(R.drawable.scrollbar2);
        this.mThumbHigh = resources.getDrawable(R.drawable.scrollbar2);
        this.mThumbLow.setState(STATE_NORMAL);
        this.mThumbHigh.setState(STATE_NORMAL);
        this.mScollBarWidth = this.notScrollBarBg.getIntrinsicWidth();
        this.mScollBarHeight = this.notScrollBarBg.getIntrinsicHeight();
        this.mThumbWidth = this.mThumbLow.getIntrinsicWidth();
        this.mThumbHeight = this.mThumbLow.getIntrinsicHeight();
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = this.measureWidth(widthMeasureSpec);
        this.mScollBarWidth = width;
        this.mOffsetHigh = (double)(width - this.mThumbWidth / 2);
        this.mOffsetLow = (double)(this.mThumbWidth / 2);
        this.mDistance = width - this.mThumbWidth;
        this.mOffsetLow = formatDouble(this.defaultScreenLow / 100.0D * (double)this.mDistance) + (double)(this.mThumbWidth / 2);
        this.mOffsetHigh = formatDouble(this.defaultScreenHigh / 100.0D * (double)this.mDistance) + (double)(this.mThumbWidth / 2);
        this.setMeasuredDimension(width, this.mThumbHeight + this.mThumbMarginTop + 2);
    }

    private int measureWidth(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode != -2147483648 && specMode == 1073741824) {
        }

        return specSize;
    }

    private int measureHeight(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        int defaultHeight = 100;
        if (specMode != -2147483648 && specMode == 1073741824) {
            defaultHeight = specSize;
        }

        return defaultHeight;
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint text_Paint = new Paint();
        text_Paint.setTextAlign(Paint.Align.CENTER);
        text_Paint.setColor(-1);
        text_Paint.setTextSize(20.0F);
        int aaa = this.mThumbMarginTop + this.mThumbHeight / 2 - this.mScollBarHeight / 2;
        int bbb = aaa + this.mScollBarHeight;
        this.notScrollBarBg.setBounds(this.mThumbWidth / 2 - 17, aaa, this.mScollBarWidth - this.mThumbWidth / 2 + 17, bbb);
        this.notScrollBarBg.draw(canvas);
        this.hasScrollBarBg.setBounds((int)this.mOffsetLow, aaa, (int)this.mOffsetHigh, bbb + 2);
        this.hasScrollBarBg.draw(canvas);
        this.mThumbLow.setBounds((int)(this.mOffsetLow - (double)(this.mThumbWidth / 2)) + 5, this.mThumbMarginTop + 5, (int)(this.mOffsetLow + (double)(this.mThumbWidth / 2)) - 5, this.mThumbHeight + this.mThumbMarginTop - 5);
        this.mThumbLow.draw(canvas);
        this.mThumbHigh.setBounds((int)(this.mOffsetHigh - (double)(this.mThumbWidth / 2)) + 5, this.mThumbMarginTop + 5, (int)(this.mOffsetHigh + (double)(this.mThumbWidth / 2)) - 5, this.mThumbHeight + this.mThumbMarginTop - 5);
        this.mThumbHigh.draw(canvas);
        double progressLow = formatDouble((this.mOffsetLow - (double)(this.mThumbWidth / 2)) * 100.0D / (double)this.mDistance);
        double progressHigh = formatDouble((this.mOffsetHigh - (double)(this.mThumbWidth / 2)) * 100.0D / (double)this.mDistance);
        if (this.showValue) {
            canvas.drawText((int)progressLow + "", (float)((int)this.mOffsetLow - 2 - 2), 15.0F, text_Paint);
            canvas.drawText((int)progressHigh + "", (float)((int)this.mOffsetHigh - 2), 15.0F, text_Paint);
        }

        if (this.mBarChangeListener != null) {
            this.mBarChangeListener.onProgressChanged(this, progressLow, progressHigh);
        }

    }

    public boolean onTouchEvent(MotionEvent e) {
        if (e.getAction() == 0) {
            if (this.mBarChangeListener != null) {
                this.mBarChangeListener.onProgressBefore();
            }

            this.mFlag = this.getAreaFlag(e);
            if (this.mFlag == 1) {
                this.mThumbLow.setState(STATE_PRESSED);
            } else if (this.mFlag == 2) {
                this.mThumbHigh.setState(STATE_PRESSED);
            } else if (this.mFlag == 3) {
                this.mThumbLow.setState(STATE_PRESSED);
                if (e.getX() >= 0.0F && e.getX() > (float)(this.mThumbWidth / 2)) {
                    if (e.getX() > (float)(this.mScollBarWidth - this.mThumbWidth / 2)) {
                        this.mOffsetLow = (double)(this.mThumbWidth / 2 + this.mDistance);
                    } else {
                        this.mOffsetLow = formatDouble((double)e.getX());
                    }
                } else {
                    this.mOffsetLow = (double)(this.mThumbWidth / 2);
                }
            } else if (this.mFlag == 4) {
                this.mThumbHigh.setState(STATE_PRESSED);
                if (e.getX() >= (float)(this.mScollBarWidth - this.mThumbWidth / 2)) {
                    this.mOffsetHigh = (double)(this.mDistance + this.mThumbWidth / 2);
                } else {
                    this.mOffsetHigh = formatDouble((double)e.getX());
                }
            }

            this.refresh();
        } else if (e.getAction() == 2) {
            if (this.mFlag == 1) {
                if (e.getX() >= 0.0F && e.getX() > (float)(this.mThumbWidth / 2)) {
                    if (e.getX() >= (float)(this.mScollBarWidth - this.mThumbWidth / 2)) {
                        this.mOffsetLow = (double)(this.mThumbWidth / 2 + this.mDistance);
                        this.mOffsetHigh = this.mOffsetLow;
                    } else {
                        this.mOffsetLow = formatDouble((double)e.getX());
                        if (this.mOffsetHigh - this.mOffsetLow <= 0.0D) {
                            this.mOffsetHigh = this.mOffsetLow <= (double)(this.mDistance + this.mThumbWidth / 2) ? this.mOffsetLow : (double)(this.mDistance + this.mThumbWidth / 2);
                        }
                    }
                } else {
                    this.mOffsetLow = (double)(this.mThumbWidth / 2);
                }
            } else if (this.mFlag == 2) {
                if (e.getX() < (float)(this.mThumbWidth / 2)) {
                    this.mOffsetHigh = (double)(this.mThumbWidth / 2);
                    this.mOffsetLow = (double)(this.mThumbWidth / 2);
                } else if (e.getX() > (float)(this.mScollBarWidth - this.mThumbWidth / 2)) {
                    this.mOffsetHigh = (double)(this.mThumbWidth / 2 + this.mDistance);
                } else {
                    this.mOffsetHigh = formatDouble((double)e.getX());
                    if (this.mOffsetHigh - this.mOffsetLow <= 0.0D) {
                        this.mOffsetLow = this.mOffsetHigh >= (double)(this.mThumbWidth / 2) ? this.mOffsetHigh : (double)(this.mThumbWidth / 2);
                    }
                }
            }

            this.refresh();
        } else if (e.getAction() == 1) {
            this.mThumbLow.setState(STATE_NORMAL);
            this.mThumbHigh.setState(STATE_NORMAL);
            if (this.mBarChangeListener != null) {
                this.mBarChangeListener.onProgressAfter();
            }
        }

        this.setProgressLow(formatDouble((this.mOffsetLow - (double)(this.mThumbWidth / 2)) * 100.0D / (double)this.mDistance));
        this.setProgressHigh(formatDouble((this.mOffsetHigh - (double)(this.mThumbWidth / 2)) * 100.0D / (double)this.mDistance));
        return true;
    }

    public int getAreaFlag(MotionEvent e) {
        int top = this.mThumbMarginTop;
        int bottom = this.mThumbHeight + this.mThumbMarginTop;
        if (e.getY() >= (float)top && e.getY() <= (float)bottom && (double)e.getX() >= this.mOffsetLow - (double)(this.mThumbWidth / 2) && (double)e.getX() <= this.mOffsetLow + (double)(this.mThumbWidth / 2)) {
            return 1;
        } else if (e.getY() >= (float)top && e.getY() <= (float)bottom && (double)e.getX() >= this.mOffsetHigh - (double)(this.mThumbWidth / 2) && (double)e.getX() <= this.mOffsetHigh + (double)(this.mThumbWidth / 2)) {
            return 2;
        } else if (e.getY() < (float)top || e.getY() > (float)bottom || (e.getX() < 0.0F || (double)e.getX() >= this.mOffsetLow - (double)(this.mThumbWidth / 2)) && ((double)e.getX() <= this.mOffsetLow + (double)(this.mThumbWidth / 2) || (double)e.getX() > (this.mOffsetHigh + this.mOffsetLow) / 2.0D)) {
            if (e.getY() >= (float)top && e.getY() <= (float)bottom && ((double)e.getX() > (this.mOffsetHigh + this.mOffsetLow) / 2.0D && (double)e.getX() < this.mOffsetHigh - (double)(this.mThumbWidth / 2) || (double)e.getX() > this.mOffsetHigh + (double)(this.mThumbWidth / 2) && e.getX() <= (float)this.mScollBarWidth)) {
                return 4;
            } else {
                return e.getX() >= 0.0F && e.getX() <= (float)this.mScollBarWidth && e.getY() >= (float)top && e.getY() <= (float)bottom ? 0 : 5;
            }
        } else {
            return 3;
        }
    }

    private void refresh() {
        this.invalidate();
    }

    public void setProgressLow(double progressLow) {
        this.defaultScreenLow = progressLow;
        this.mOffsetLow = formatDouble(progressLow / 100.0D * (double)this.mDistance) + (double)(this.mThumbWidth / 2);
        this.refresh();
    }

    public void setProgressHigh(double progressHigh) {
        this.defaultScreenHigh = progressHigh;
        this.mOffsetHigh = formatDouble(progressHigh / 100.0D * (double)this.mDistance) + (double)(this.mThumbWidth / 2);
        this.refresh();
    }

    public void setOnSeekBarChangeListener(CSeekBarPressure.OnSeekBarChangeListener mListener) {
        this.mBarChangeListener = mListener;
    }

    public static double formatDouble(double pDouble) {
        BigDecimal bd = new BigDecimal(pDouble);
        BigDecimal bd1 = bd.setScale(2, 4);
        pDouble = bd1.doubleValue();
        return pDouble;
    }

    public interface OnSeekBarChangeListener {
        void onProgressBefore();

        void onProgressChanged(CSeekBarPressure var1, double var2, double var4);

        void onProgressAfter();
    }
}
