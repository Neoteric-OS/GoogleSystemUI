package com.android.systemui.battery;

import android.R;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;
import android.util.PathParser;
import com.android.settingslib.graph.ThemedBatteryDrawable;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AccessorizedBatteryDrawable extends DrawableWrapper {
    public final Context context;
    public float density;
    public boolean displayShield;
    public final boolean dualTone;
    public final Function0 invalidateRunnable;
    public final Matrix scaleMatrix;
    public final Path scaledShield;
    public float shieldLeftOffsetScaled;
    public final Paint shieldPaint;
    public final Path shieldPath;
    public float shieldTopOffsetScaled;
    public final Paint shieldTransparentOutlinePaint;

    public AccessorizedBatteryDrawable(int i, Context context) {
        super(new ThemedBatteryDrawable(i, context));
        this.context = context;
        Path path = new Path();
        this.shieldPath = path;
        this.scaledShield = new Path();
        this.scaleMatrix = new Matrix();
        this.shieldLeftOffsetScaled = 8.0f;
        this.shieldTopOffsetScaled = 10.0f;
        this.density = context.getResources().getDisplayMetrics().density;
        this.dualTone = context.getResources().getBoolean(R.bool.config_batterymeterDualTone);
        Paint paint = new Paint(1);
        paint.setColor(0);
        paint.setStrokeWidth(6.0f);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.shieldTransparentOutlinePaint = paint;
        Paint paint2 = new Paint(1);
        paint2.setColor(-65281);
        paint2.setStyle(Paint.Style.FILL);
        paint2.setDither(true);
        this.shieldPaint = paint2;
        path.set(PathParser.createPathFromPathData(context.getResources().getString(com.android.wm.shell.R.string.config_batterymeterShieldPath)));
        this.invalidateRunnable = new Function0() { // from class: com.android.systemui.battery.AccessorizedBatteryDrawable$invalidateRunnable$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                AccessorizedBatteryDrawable.this.invalidateSelf();
                return Unit.INSTANCE;
            }
        };
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        canvas.saveLayer(null, null);
        super.draw(canvas);
        if (this.displayShield) {
            canvas.translate(this.shieldLeftOffsetScaled, this.shieldTopOffsetScaled);
            canvas.drawPath(this.scaledShield, this.shieldTransparentOutlinePaint);
            canvas.drawPath(this.scaledShield, this.shieldPaint);
        }
        canvas.restore();
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return (int) ((this.displayShield ? 23.0f : 20.0f) * this.density);
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        return (int) ((this.displayShield ? 18.0f : 12.0f) * this.density);
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -1;
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public final void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        Rect bounds = getBounds();
        if (bounds.isEmpty()) {
            return;
        }
        float width = bounds.width();
        if (this.displayShield) {
            width *= 0.6666667f;
        }
        float height = bounds.height();
        if (this.displayShield) {
            height *= 0.8695652f;
        }
        Drawable drawable = getDrawable();
        if (drawable != null) {
            int i = bounds.left;
            int i2 = bounds.top;
            drawable.setBounds(i, i2, ((int) width) + i, ((int) height) + i2);
        }
        if (this.displayShield) {
            float f = bounds.right / 18.0f;
            float f2 = bounds.bottom / 23.0f;
            this.scaleMatrix.setScale(f, f2);
            this.shieldPath.transform(this.scaleMatrix, this.scaledShield);
            this.shieldLeftOffsetScaled = 8.0f * f;
            this.shieldTopOffsetScaled = f2 * 10.0f;
            this.shieldTransparentOutlinePaint.setStrokeWidth(RangesKt.coerceAtLeast(f * 4.0f, 6.0f));
        }
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        super.setColorFilter(getColorFilter());
        this.shieldPaint.setColorFilter(getColorFilter());
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
    }
}
