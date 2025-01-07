package com.android.systemui.statusbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.os.Trace;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import com.android.keyguard.logging.ScrimLogger;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.systemui.shade.TouchLogger;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl$$ExternalSyntheticLambda2;
import com.android.systemui.util.ColorUtilKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class LightRevealScrim extends View {
    public final Paint gradientPaint;
    public float interpolatedRevealAmount;
    public boolean isScrimOpaque;
    public CentralSurfacesImpl$$ExternalSyntheticLambda2 isScrimOpaqueChangedListener;
    public final String logString;
    public float revealAmount;
    public LightRevealEffect revealEffect;
    public final PointF revealGradientCenter;
    public final int revealGradientEndColor;
    public float revealGradientEndColorAlpha;
    public float revealGradientHeight;
    public float revealGradientWidth;
    public ScrimLogger scrimLogger;
    public final Matrix shaderGradientMatrix;
    public float startColorAlpha;
    public int viewHeight;
    public int viewWidth;

    public LightRevealScrim(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, null, null, 12, null);
    }

    @Override // android.view.View
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean dispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
        TouchLogger.Companion.logDispatchTouch("LightRevealScrim", motionEvent, dispatchTouchEvent);
        return dispatchTouchEvent;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        if (this.revealGradientWidth <= 0.0f || this.revealGradientHeight <= 0.0f || this.revealAmount == 0.0f) {
            if (this.revealAmount < 1.0f) {
                canvas.drawColor(this.revealGradientEndColor);
                return;
            }
            return;
        }
        float f = this.startColorAlpha;
        if (f > 0.0f) {
            canvas.drawColor(ColorUtilKt.getColorWithAlpha(this.revealGradientEndColor, f));
        }
        Matrix matrix = this.shaderGradientMatrix;
        matrix.setScale(this.revealGradientWidth, this.revealGradientHeight, 0.0f, 0.0f);
        PointF pointF = this.revealGradientCenter;
        matrix.postTranslate(pointF.x, pointF.y);
        this.gradientPaint.getShader().setLocalMatrix(matrix);
        canvas.drawRect(0.0f, 0.0f, getWidth(), getHeight(), this.gradientPaint);
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.viewWidth = getMeasuredWidth();
        this.viewHeight = getMeasuredHeight();
    }

    @Override // android.view.View
    public final void setAlpha(float f) {
        super.setAlpha(f);
        ScrimLogger scrimLogger = this.scrimLogger;
        if (scrimLogger != null) {
            scrimLogger.d("LightRevealScrim", "alpha", f + " on " + this.logString);
        }
        updateScrimOpaque();
    }

    public final void setRevealAmount(float f) {
        ScrimLogger scrimLogger;
        if (this.revealAmount == f) {
            return;
        }
        this.revealAmount = f;
        if ((f <= 0.0f || f >= 1.0f) && (scrimLogger = this.scrimLogger) != null) {
            scrimLogger.d("LightRevealScrim", "revealAmount", f + " on " + this.logString);
        }
        this.revealEffect.setRevealAmountOnScrim(f, this);
        updateScrimOpaque();
        Trace.traceCounter(4096L, AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("light_reveal_amount ", this.logString), (int) (this.revealAmount * 100));
        invalidate();
    }

    public final void setRevealEffect(LightRevealEffect lightRevealEffect) {
        if (Intrinsics.areEqual(this.revealEffect, lightRevealEffect)) {
            return;
        }
        this.revealEffect = lightRevealEffect;
        lightRevealEffect.setRevealAmountOnScrim(this.revealAmount, this);
        ScrimLogger scrimLogger = this.scrimLogger;
        if (scrimLogger != null) {
            scrimLogger.d("LightRevealScrim", "revealEffect", lightRevealEffect + " on " + this.logString);
        }
        invalidate();
    }

    public final void setRevealGradientBounds(float f, float f2, float f3, float f4) {
        float f5 = f3 - f;
        this.revealGradientWidth = f5;
        float f6 = f4 - f2;
        this.revealGradientHeight = f6;
        PointF pointF = this.revealGradientCenter;
        pointF.x = (f5 / 2.0f) + f;
        pointF.y = (f6 / 2.0f) + f2;
    }

    public final void setRevealGradientEndColorAlpha(float f) {
        if (this.revealGradientEndColorAlpha == f) {
            return;
        }
        this.revealGradientEndColorAlpha = f;
        this.gradientPaint.setColorFilter(new PorterDuffColorFilter(ColorUtilKt.getColorWithAlpha(this.revealGradientEndColor, this.revealGradientEndColorAlpha), PorterDuff.Mode.MULTIPLY));
    }

    @Override // android.view.View
    public final void setVisibility(int i) {
        super.setVisibility(i);
        ScrimLogger scrimLogger = this.scrimLogger;
        if (scrimLogger != null) {
            scrimLogger.d("LightRevealScrim", "visibility", i + " on " + this.logString);
        }
        updateScrimOpaque();
    }

    public final void updateScrimOpaque() {
        boolean z = this.revealAmount == 0.0f && getAlpha() == 1.0f && getVisibility() == 0;
        if (this.isScrimOpaque != z) {
            this.isScrimOpaque = z;
            CentralSurfacesImpl$$ExternalSyntheticLambda2 centralSurfacesImpl$$ExternalSyntheticLambda2 = this.isScrimOpaqueChangedListener;
            if (centralSurfacesImpl$$ExternalSyntheticLambda2 != null) {
                centralSurfacesImpl$$ExternalSyntheticLambda2.accept(Boolean.valueOf(z));
            }
            ScrimLogger scrimLogger = this.scrimLogger;
            if (scrimLogger != null) {
                scrimLogger.d("LightRevealScrim", "isScrimOpaque", z + " on " + this.logString);
            }
        }
    }

    public LightRevealScrim(Context context, AttributeSet attributeSet, Integer num) {
        this(context, attributeSet, num, null, 8, null);
    }

    public /* synthetic */ LightRevealScrim(Context context, AttributeSet attributeSet, Integer num, Integer num2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, attributeSet, (i & 4) != 0 ? null : num, (i & 8) != 0 ? null : num2);
    }

    public LightRevealScrim(Context context, AttributeSet attributeSet, Integer num, Integer num2) {
        super(context, attributeSet);
        String simpleName = Reflection.getOrCreateKotlinClass(LightRevealScrim.class).getSimpleName();
        Intrinsics.checkNotNull(simpleName);
        this.logString = simpleName + "@" + hashCode();
        this.revealAmount = 1.0f;
        this.revealEffect = LiftReveal.INSTANCE;
        this.revealGradientCenter = new PointF();
        this.viewWidth = num != null ? num.intValue() : 0;
        this.viewHeight = num2 != null ? num2.intValue() : 0;
        this.revealGradientEndColor = DarkIconDispatcher.DEFAULT_INVERSE_ICON_TINT;
        this.interpolatedRevealAmount = 1.0f;
        Paint paint = new Paint();
        paint.setShader(new RadialGradient(0.0f, 0.0f, 1.0f, new int[]{0, -1}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP));
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        this.gradientPaint = paint;
        this.shaderGradientMatrix = new Matrix();
        this.revealEffect.setRevealAmountOnScrim(this.revealAmount, this);
        paint.setColorFilter(new PorterDuffColorFilter(ColorUtilKt.getColorWithAlpha(this.revealGradientEndColor, this.revealGradientEndColorAlpha), PorterDuff.Mode.MULTIPLY));
        invalidate();
    }
}
