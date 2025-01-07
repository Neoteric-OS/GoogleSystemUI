package com.android.systemui.navigationbar.gestural;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.MathUtils;
import android.view.View;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import com.android.internal.util.LatencyTracker;
import com.android.systemui.navigationbar.gestural.EdgePanelParams;
import java.util.Set;
import kotlin.collections.SetsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BackPanel extends View {
    public final Set allAnimatedFloat;
    public final AnimatedFloat arrowAlpha;
    public final Paint arrowBackgroundPaint;
    public final RectF arrowBackgroundRect;
    public final AnimatedFloat arrowHeight;
    public final AnimatedFloat arrowLength;
    public final Paint arrowPaint;
    public final Path arrowPath;
    public boolean arrowsPointLeft;
    public final AnimatedFloat backgroundAlpha;
    public final AnimatedFloat backgroundEdgeCornerRadius;
    public final AnimatedFloat backgroundFarCornerRadius;
    public final AnimatedFloat backgroundHeight;
    public final AnimatedFloat backgroundWidth;
    public final AnimatedFloat horizontalTranslation;
    public boolean isLeftPanel;
    public final LatencyTracker latencyTracker;
    public final AnimatedFloat scale;
    public final AnimatedFloat scalePivotX;
    public boolean trackingBackArrowLatency;
    public final AnimatedFloat verticalTranslation;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class AnimatedFloat {
        public final SpringAnimation animation;
        public float pos;
        public float restingPosition;

        public /* synthetic */ AnimatedFloat(BackPanel backPanel, Float f, Float f2, int i) {
            this((i & 2) != 0 ? null : f, (i & 4) != 0 ? null : f2, (Float) null);
        }

        public static void stretchTo$default(AnimatedFloat animatedFloat, float f, Float f2, int i) {
            if ((i & 2) != 0) {
                f2 = null;
            }
            SpringAnimation springAnimation = animatedFloat.animation;
            if (f2 != null) {
                float floatValue = f2.floatValue();
                springAnimation.cancel();
                springAnimation.mVelocity = floatValue;
            }
            springAnimation.animateToFinalPosition(animatedFloat.restingPosition + f);
        }

        public final void setSpring(SpringForce springForce) {
            SpringAnimation springAnimation = this.animation;
            springAnimation.cancel();
            springAnimation.mSpring = springForce;
        }

        public final void snapTo(float f) {
            SpringAnimation springAnimation = this.animation;
            springAnimation.cancel();
            this.restingPosition = f;
            springAnimation.mSpring.mFinalPosition = f;
            if (this.pos == f) {
                return;
            }
            this.pos = f;
            BackPanel.this.invalidate();
        }

        public final void snapToRestingPosition() {
            snapTo(this.restingPosition);
        }

        public final void stretchBy(Float f, float f2) {
            float floatValue = f != null ? f.floatValue() : 0.0f;
            float f3 = this.restingPosition;
            this.animation.animateToFinalPosition(f3 + ((floatValue - f3) * f2));
        }

        public final void updateRestingPosition(Float f, boolean z) {
            if (f == null) {
                return;
            }
            float floatValue = f.floatValue();
            this.restingPosition = floatValue;
            if (z) {
                this.animation.animateToFinalPosition(floatValue);
            } else {
                snapTo(floatValue);
            }
        }

        public AnimatedFloat(Float f, Float f2, Float f3) {
            SpringAnimation springAnimation = new SpringAnimation(this, new BackPanel$AnimatedFloat$floatProp$1());
            springAnimation.mSpring = new SpringForce();
            if (f2 != null) {
                springAnimation.mMinValue = f2.floatValue();
            }
            if (f3 != null) {
                springAnimation.mMaxValue = f3.floatValue();
            }
            if (f != null) {
                springAnimation.setMinimumVisibleChange(f.floatValue());
            }
            this.animation = springAnimation;
        }
    }

    public BackPanel(Context context, LatencyTracker latencyTracker) {
        super(context);
        this.latencyTracker = latencyTracker;
        this.arrowPath = new Path();
        Paint paint = new Paint();
        this.arrowPaint = paint;
        this.arrowBackgroundRect = new RectF();
        Paint paint2 = new Paint();
        this.arrowBackgroundPaint = paint2;
        Float valueOf = Float.valueOf(1.0f);
        Float f = null;
        int i = 12;
        AnimatedFloat animatedFloat = new AnimatedFloat(this, valueOf, f, i);
        this.arrowLength = animatedFloat;
        AnimatedFloat animatedFloat2 = new AnimatedFloat(this, Float.valueOf(0.1f), f, i);
        this.arrowHeight = animatedFloat2;
        Float valueOf2 = Float.valueOf(0.0f);
        int i2 = 8;
        AnimatedFloat animatedFloat3 = new AnimatedFloat(this, valueOf, valueOf2, i2);
        this.backgroundWidth = animatedFloat3;
        this.backgroundHeight = new AnimatedFloat(this, valueOf, valueOf2, i2);
        int i3 = 14;
        AnimatedFloat animatedFloat4 = new AnimatedFloat(this, f, f, i3);
        this.backgroundEdgeCornerRadius = animatedFloat4;
        AnimatedFloat animatedFloat5 = new AnimatedFloat(this, f, f, i3);
        this.backgroundFarCornerRadius = animatedFloat5;
        AnimatedFloat animatedFloat6 = new AnimatedFloat(this, Float.valueOf(0.002f), valueOf2, i2);
        this.scale = animatedFloat6;
        AnimatedFloat animatedFloat7 = new AnimatedFloat(this, valueOf, Float.valueOf(animatedFloat3.pos / 2), i2);
        this.scalePivotX = animatedFloat7;
        Float f2 = null;
        AnimatedFloat animatedFloat8 = new AnimatedFloat(this, f2, f2, i3);
        this.horizontalTranslation = animatedFloat8;
        AnimatedFloat animatedFloat9 = new AnimatedFloat(Float.valueOf(0.00390625f), valueOf2, valueOf);
        this.arrowAlpha = animatedFloat9;
        AnimatedFloat animatedFloat10 = new AnimatedFloat(Float.valueOf(0.00390625f), valueOf2, valueOf);
        this.backgroundAlpha = animatedFloat10;
        this.allAnimatedFloat = SetsKt.setOf(animatedFloat, animatedFloat2, animatedFloat3, animatedFloat4, animatedFloat5, animatedFloat7, animatedFloat6, animatedFloat8, animatedFloat9, animatedFloat10);
        Float f3 = null;
        this.verticalTranslation = new AnimatedFloat(this, f3, f3, 14);
        setVisibility(8);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.SQUARE);
        paint2.setStyle(Paint.Style.FILL);
        paint2.setStrokeJoin(Paint.Join.ROUND);
        paint2.setStrokeCap(Paint.Cap.ROUND);
    }

    public static void setSpring$default(BackPanel backPanel, SpringForce springForce, SpringForce springForce2, SpringForce springForce3, SpringForce springForce4, SpringForce springForce5, SpringForce springForce6, SpringForce springForce7, SpringForce springForce8, SpringForce springForce9, SpringForce springForce10, int i) {
        if ((i & 1) != 0) {
            springForce = null;
        }
        if ((i & 2) != 0) {
            springForce2 = null;
        }
        if ((i & 4) != 0) {
            springForce3 = null;
        }
        if ((i & 8) != 0) {
            springForce4 = null;
        }
        if ((i & 16) != 0) {
            springForce5 = null;
        }
        if ((i & 64) != 0) {
            springForce6 = null;
        }
        if ((i & 128) != 0) {
            springForce7 = null;
        }
        if ((i & 256) != 0) {
            springForce8 = null;
        }
        if ((i & 512) != 0) {
            springForce9 = null;
        }
        if ((i & 1024) != 0) {
            springForce10 = null;
        }
        if (springForce4 != null) {
            backPanel.arrowLength.setSpring(springForce4);
        }
        if (springForce5 != null) {
            backPanel.arrowHeight.setSpring(springForce5);
        }
        if (springForce6 != null) {
            backPanel.backgroundAlpha.setSpring(springForce6);
        }
        if (springForce7 != null) {
            backPanel.backgroundFarCornerRadius.setSpring(springForce7);
        }
        if (springForce8 != null) {
            backPanel.backgroundEdgeCornerRadius.setSpring(springForce8);
        }
        if (springForce3 != null) {
            backPanel.scale.setSpring(springForce3);
        }
        if (springForce9 != null) {
            backPanel.backgroundWidth.setSpring(springForce9);
        }
        if (springForce10 != null) {
            backPanel.backgroundHeight.setSpring(springForce10);
        }
        if (springForce != null) {
            backPanel.horizontalTranslation.setSpring(springForce);
        }
        if (springForce2 != null) {
            backPanel.verticalTranslation.setSpring(springForce2);
        } else {
            backPanel.getClass();
        }
    }

    @Override // android.view.View
    public final boolean hasOverlappingRendering() {
        return false;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        float f = this.backgroundEdgeCornerRadius.pos;
        float f2 = this.backgroundFarCornerRadius.pos;
        float f3 = 2;
        float f4 = this.backgroundHeight.pos / f3;
        int width = getWidth();
        float f5 = this.backgroundWidth.pos;
        float f6 = this.scalePivotX.pos;
        canvas.save();
        if (!this.isLeftPanel) {
            canvas.scale(-1.0f, 1.0f, width / 2.0f, 0.0f);
        }
        canvas.translate(this.horizontalTranslation.pos, (getHeight() * 0.5f) + this.verticalTranslation.pos);
        float f7 = this.scale.pos;
        canvas.scale(f7, f7, f6, 0.0f);
        RectF rectF = this.arrowBackgroundRect;
        rectF.left = 0.0f;
        rectF.top = -f4;
        rectF.right = f5;
        rectF.bottom = f4;
        Path path = new Path();
        path.addRoundRect(rectF, new float[]{f, f, f2, f2, f2, f2, f, f}, Path.Direction.CW);
        Paint paint = this.arrowBackgroundPaint;
        float f8 = 255;
        paint.setAlpha((int) (this.backgroundAlpha.pos * f8));
        canvas.drawPath(path, paint);
        float f9 = this.arrowLength.pos;
        float f10 = this.arrowHeight.pos;
        canvas.translate((f5 - f9) / f3, 0.0f);
        if (!(this.arrowsPointLeft ^ this.isLeftPanel)) {
            canvas.scale(-1.0f, 1.0f, 0.0f, 0.0f);
            canvas.translate(-f9, 0.0f);
        }
        this.arrowPath.reset();
        float f11 = -f10;
        this.arrowPath.moveTo(f9, f11);
        this.arrowPath.lineTo(0.0f, 0.0f);
        this.arrowPath.lineTo(f9, f10);
        this.arrowPath.moveTo(f9, f11);
        Path path2 = this.arrowPath;
        Paint paint2 = this.arrowPaint;
        paint2.setAlpha((int) (MathUtils.min(this.arrowAlpha.pos, this.backgroundAlpha.pos) * f8));
        canvas.drawPath(path2, paint2);
        canvas.restore();
        if (this.trackingBackArrowLatency) {
            this.latencyTracker.onActionEnd(15);
            this.trackingBackArrowLatency = false;
        }
    }

    public final void popOffEdge(float f) {
        AnimatedFloat.stretchTo$default(this.scale, 0.0f, Float.valueOf((-0.8f) * f), 4);
        AnimatedFloat.stretchTo$default(this.horizontalTranslation, 0.0f, Float.valueOf(f * 200.0f), 4);
    }

    public final void setStretch(float f, float f2, float f3, float f4, float f5, float f6, float f7, EdgePanelParams.BackIndicatorDimens backIndicatorDimens) {
        this.horizontalTranslation.stretchBy(backIndicatorDimens.horizontalTranslation, f);
        AnimatedFloat animatedFloat = this.arrowLength;
        EdgePanelParams.ArrowDimens arrowDimens = backIndicatorDimens.arrowDimens;
        animatedFloat.stretchBy(arrowDimens.length, f2);
        this.arrowHeight.stretchBy(arrowDimens.height, f2);
        this.arrowAlpha.stretchBy(Float.valueOf(arrowDimens.alpha), f3);
        AnimatedFloat animatedFloat2 = this.backgroundAlpha;
        EdgePanelParams.BackgroundDimens backgroundDimens = backIndicatorDimens.backgroundDimens;
        animatedFloat2.stretchBy(Float.valueOf(backgroundDimens.alpha), 1.0f);
        this.backgroundWidth.stretchBy(backgroundDimens.width, f4);
        this.backgroundHeight.stretchBy(Float.valueOf(backgroundDimens.height), f5);
        this.backgroundEdgeCornerRadius.stretchBy(Float.valueOf(backgroundDimens.edgeCornerRadius), f6);
        this.backgroundFarCornerRadius.stretchBy(Float.valueOf(backgroundDimens.farCornerRadius), f7);
    }
}
