package com.nocompany.magnetsensor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CompassView extends View {

    private Paint paint;

    // height, width
    private int h,w;

    // radii
    private int r;

    private int centerX, centerY;

    // magnetic angle - from x to y
    private double phi  = 0.0;

    public CompassView(Context context) {
        // super(context);
        // init();
        this(context, null);
    }


    public CompassView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    // Just initialize paint
    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(0xdd0000aa);
        paint.setStrokeWidth(5);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.w = w;
        this.h = h;

        this.r = Math.min(w,h)/2;

        this.centerX = w/2;
        this.centerY = h/2;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawLine(
                centerX, centerY, centerX + (float) (r*Math.cos(phi)), centerY - (float) (r*Math.sin(phi)), paint
        );

    }

    // Called from sensor listener
    public void magChanged(float[] values) {
        float magX = values[0];
        float magY = values[1];

        phi = Math.atan2(magY, magX);

        invalidate();

    }
}
