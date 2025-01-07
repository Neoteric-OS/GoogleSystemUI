package com.android.systemui.shared.navigationbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.CanvasProperty;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RecordingCanvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Trace;
import android.view.RenderNodeAnimator;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import com.android.systemui.navigationbar.views.NavigationBar$$ExternalSyntheticLambda4;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.HashSet;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class KeyButtonRipple extends Drawable {
    public static final Interpolator ALPHA_OUT_INTERPOLATOR = new PathInterpolator(0.0f, 0.0f, 0.8f, 1.0f);
    public CanvasProperty mBottomProp;
    public boolean mDark;
    public boolean mDrawingHardwareGlow;
    public boolean mLastDark;
    public CanvasProperty mLeftProp;
    public int mMaxWidth;
    public NavigationBar$$ExternalSyntheticLambda4 mOnInvisibleRunnable;
    public CanvasProperty mPaintProp;
    public boolean mPressed;
    public CanvasProperty mRightProp;
    public Paint mRipplePaint;
    public CanvasProperty mRxProp;
    public CanvasProperty mRyProp;
    public boolean mSpeedUpNextFade;
    public boolean mSupportHardware;
    public final View mTargetView;
    public CanvasProperty mTopProp;
    public boolean mVisible;
    public float mGlowAlpha = 0.0f;
    public float mGlowScale = 1.0f;
    public final LogInterpolator mInterpolator = new LogInterpolator();
    public final Handler mHandler = new Handler();
    public final HashSet mRunningAnimations = new HashSet();
    public final ArrayList mTmpArray = new ArrayList();
    public final AnonymousClass1 mExitHwTraceAnimator = new AnonymousClass1("exitHardware");
    public final AnonymousClass1 mEnterHwTraceAnimator = new AnonymousClass1("enterHardware");
    public Type mType = Type.ROUNDED_RECT;
    public final AnonymousClass1 mAnimatorListener = new AnonymousClass1(this);
    public final int mMaxWidthResource = R.dimen.key_button_ripple_max_width;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.shared.navigationbar.KeyButtonRipple$1, reason: invalid class name */
    public final class AnonymousClass1 extends AnimatorListenerAdapter {
        public final /* synthetic */ int $r8$classId = 0;
        public final Object this$0;

        public AnonymousClass1(KeyButtonRipple keyButtonRipple) {
            this.this$0 = keyButtonRipple;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            switch (this.$r8$classId) {
                case 1:
                    if (Trace.isEnabled()) {
                        Trace.instant(4096L, "KeyButtonRipple.cancel." + ((String) this.this$0));
                        break;
                    }
                    break;
                default:
                    super.onAnimationCancel(animator);
                    break;
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            switch (this.$r8$classId) {
                case 0:
                    ((KeyButtonRipple) this.this$0).mRunningAnimations.remove(animator);
                    if (((KeyButtonRipple) this.this$0).mRunningAnimations.isEmpty()) {
                        KeyButtonRipple keyButtonRipple = (KeyButtonRipple) this.this$0;
                        if (!keyButtonRipple.mPressed) {
                            keyButtonRipple.mVisible = false;
                            keyButtonRipple.mDrawingHardwareGlow = false;
                            keyButtonRipple.invalidateSelf();
                            break;
                        }
                    }
                    break;
                default:
                    if (Trace.isEnabled()) {
                        Trace.instant(4096L, "KeyButtonRipple.end." + ((String) this.this$0));
                        break;
                    }
                    break;
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            switch (this.$r8$classId) {
                case 1:
                    if (Trace.isEnabled()) {
                        Trace.instant(4096L, "KeyButtonRipple.start." + ((String) this.this$0));
                        break;
                    }
                    break;
                default:
                    super.onAnimationStart(animator);
                    break;
            }
        }

        public AnonymousClass1(String str) {
            this.this$0 = str;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class LogInterpolator implements Interpolator {
        @Override // android.animation.TimeInterpolator
        public final float getInterpolation(float f) {
            return 1.0f - ((float) Math.pow(400.0d, (-f) * 1.4d));
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Type {
        public static final /* synthetic */ Type[] $VALUES;
        public static final Type OVAL;
        public static final Type ROUNDED_RECT;

        static {
            Type type = new Type("OVAL", 0);
            OVAL = type;
            Type type2 = new Type("ROUNDED_RECT", 1);
            ROUNDED_RECT = type2;
            $VALUES = new Type[]{type, type2};
        }

        public static Type valueOf(String str) {
            return (Type) Enum.valueOf(Type.class, str);
        }

        public static Type[] values() {
            return (Type[]) $VALUES.clone();
        }
    }

    public KeyButtonRipple(Context context, View view) {
        this.mMaxWidth = context.getResources().getDimensionPixelSize(R.dimen.key_button_ripple_max_width);
        this.mTargetView = view;
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        boolean isHardwareAccelerated = canvas.isHardwareAccelerated();
        this.mSupportHardware = isHardwareAccelerated;
        Type type = Type.ROUNDED_RECT;
        if (isHardwareAccelerated) {
            RecordingCanvas recordingCanvas = (RecordingCanvas) canvas;
            if (this.mDrawingHardwareGlow) {
                if (this.mType == type) {
                    recordingCanvas.drawRoundRect(this.mLeftProp, this.mTopProp, this.mRightProp, this.mBottomProp, this.mRxProp, this.mRyProp, this.mPaintProp);
                } else {
                    recordingCanvas.drawCircle(CanvasProperty.createFloat(getBounds().width() / 2), CanvasProperty.createFloat(getBounds().height() / 2), CanvasProperty.createFloat((Math.min(getBounds().width(), getBounds().height()) * 1.0f) / 2.0f), this.mPaintProp);
                }
            }
        } else if (this.mGlowAlpha > 0.0f) {
            Paint ripplePaint = getRipplePaint();
            ripplePaint.setAlpha((int) (this.mGlowAlpha * 255.0f));
            float width = getBounds().width();
            float height = getBounds().height();
            boolean z = width > height;
            float rippleSize = getRippleSize() * this.mGlowScale * 0.5f;
            float f = width * 0.5f;
            float f2 = height * 0.5f;
            float f3 = z ? rippleSize : f;
            if (z) {
                rippleSize = f2;
            }
            float f4 = z ? f2 : f;
            if (this.mType == type) {
                canvas.drawRoundRect(f - f3, f2 - rippleSize, f3 + f, f2 + rippleSize, f4, f4, ripplePaint);
            } else {
                canvas.save();
                canvas.translate(f, f2);
                float min = Math.min(f3, rippleSize);
                float f5 = -min;
                canvas.drawOval(f5, f5, min, min, ripplePaint);
                canvas.restore();
            }
        }
        if (this.mPressed || this.mVisible || this.mOnInvisibleRunnable == null) {
            return;
        }
        new Handler(Looper.getMainLooper()).post(this.mOnInvisibleRunnable);
        this.mOnInvisibleRunnable = null;
    }

    public final void endAnimations(String str, boolean z) {
        if (Trace.isEnabled()) {
            Trace.instant(4096L, "KeyButtonRipple.endAnim: reason=" + str + " cancel=" + z);
        }
        this.mVisible = false;
        this.mTmpArray.addAll(this.mRunningAnimations);
        int size = this.mTmpArray.size();
        for (int i = 0; i < size; i++) {
            Animator animator = (Animator) this.mTmpArray.get(i);
            if (z) {
                animator.cancel();
            } else {
                animator.end();
            }
        }
        this.mTmpArray.clear();
        this.mRunningAnimations.clear();
        this.mHandler.removeCallbacksAndMessages(null);
    }

    public final int getExtendSize() {
        boolean isHorizontal = isHorizontal();
        Rect bounds = getBounds();
        return isHorizontal ? bounds.width() : bounds.height();
    }

    public float getGlowAlpha() {
        return this.mGlowAlpha;
    }

    public float getGlowScale() {
        return this.mGlowScale;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -3;
    }

    public final Paint getRipplePaint() {
        if (this.mRipplePaint == null) {
            Paint paint = new Paint();
            this.mRipplePaint = paint;
            paint.setAntiAlias(true);
            this.mRipplePaint.setColor(this.mLastDark ? DarkIconDispatcher.DEFAULT_INVERSE_ICON_TINT : -1);
        }
        return this.mRipplePaint;
    }

    public final int getRippleSize() {
        return Math.min(isHorizontal() ? getBounds().width() : getBounds().height(), this.mMaxWidth);
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean hasFocusStateSpecified() {
        return true;
    }

    public final boolean isHorizontal() {
        return getBounds().width() > getBounds().height();
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean isStateful() {
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public final void jumpToCurrentState() {
        endAnimations("jumpToCurrentState", false);
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean onStateChange(int[] iArr) {
        boolean z;
        int i;
        int i2 = 0;
        while (true) {
            if (i2 >= iArr.length) {
                z = false;
                break;
            }
            if (iArr[i2] == 16842919) {
                z = true;
                break;
            }
            i2++;
        }
        if (z == this.mPressed) {
            return false;
        }
        boolean z2 = this.mDark;
        if (z2 != this.mLastDark && z) {
            this.mRipplePaint = null;
            this.mLastDark = z2;
        }
        if (this.mSupportHardware) {
            if (z) {
                endAnimations("enterHardware", true);
                this.mVisible = true;
                this.mDrawingHardwareGlow = true;
                CanvasProperty createFloat = CanvasProperty.createFloat(getExtendSize() / 2);
                if (isHorizontal()) {
                    this.mLeftProp = createFloat;
                } else {
                    this.mTopProp = createFloat;
                }
                RenderNodeAnimator renderNodeAnimator = new RenderNodeAnimator(isHorizontal() ? this.mLeftProp : this.mTopProp, (getExtendSize() / 2) - ((getRippleSize() * 1.35f) / 2.0f));
                renderNodeAnimator.setDuration(350L);
                renderNodeAnimator.setInterpolator(this.mInterpolator);
                renderNodeAnimator.addListener(this.mAnimatorListener);
                renderNodeAnimator.setTarget(this.mTargetView);
                CanvasProperty createFloat2 = CanvasProperty.createFloat(getExtendSize() / 2);
                if (isHorizontal()) {
                    this.mRightProp = createFloat2;
                } else {
                    this.mBottomProp = createFloat2;
                }
                RenderNodeAnimator renderNodeAnimator2 = new RenderNodeAnimator(isHorizontal() ? this.mRightProp : this.mBottomProp, ((getRippleSize() * 1.35f) / 2.0f) + (getExtendSize() / 2));
                renderNodeAnimator2.setDuration(350L);
                renderNodeAnimator2.setInterpolator(this.mInterpolator);
                renderNodeAnimator2.addListener(this.mAnimatorListener);
                renderNodeAnimator2.addListener(this.mEnterHwTraceAnimator);
                renderNodeAnimator2.setTarget(this.mTargetView);
                if (isHorizontal()) {
                    this.mTopProp = CanvasProperty.createFloat(0.0f);
                    this.mBottomProp = CanvasProperty.createFloat(getBounds().height());
                    this.mRxProp = CanvasProperty.createFloat(getBounds().height() / 2);
                    this.mRyProp = CanvasProperty.createFloat(getBounds().height() / 2);
                } else {
                    this.mLeftProp = CanvasProperty.createFloat(0.0f);
                    this.mRightProp = CanvasProperty.createFloat(getBounds().width());
                    this.mRxProp = CanvasProperty.createFloat(getBounds().width() / 2);
                    this.mRyProp = CanvasProperty.createFloat(getBounds().width() / 2);
                }
                this.mGlowScale = 1.35f;
                this.mGlowAlpha = this.mLastDark ? 0.1f : 0.2f;
                Paint ripplePaint = getRipplePaint();
                this.mRipplePaint = ripplePaint;
                ripplePaint.setAlpha((int) (this.mGlowAlpha * 255.0f));
                this.mPaintProp = CanvasProperty.createPaint(this.mRipplePaint);
                renderNodeAnimator.start();
                renderNodeAnimator2.start();
                this.mRunningAnimations.add(renderNodeAnimator);
                this.mRunningAnimations.add(renderNodeAnimator2);
                invalidateSelf();
            } else {
                this.mPaintProp = CanvasProperty.createPaint(getRipplePaint());
                RenderNodeAnimator renderNodeAnimator3 = new RenderNodeAnimator(this.mPaintProp, 1, 0.0f);
                i = this.mSpeedUpNextFade ? 80 : 450;
                this.mSpeedUpNextFade = false;
                renderNodeAnimator3.setDuration(i);
                renderNodeAnimator3.setInterpolator(ALPHA_OUT_INTERPOLATOR);
                renderNodeAnimator3.addListener(this.mAnimatorListener);
                renderNodeAnimator3.addListener(this.mExitHwTraceAnimator);
                renderNodeAnimator3.setTarget(this.mTargetView);
                renderNodeAnimator3.start();
                this.mRunningAnimations.add(renderNodeAnimator3);
                invalidateSelf();
            }
        } else if (z) {
            endAnimations("enterSoftware", true);
            this.mVisible = true;
            this.mGlowAlpha = this.mLastDark ? 0.1f : 0.2f;
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "glowScale", 0.0f, 1.35f);
            ofFloat.setInterpolator(this.mInterpolator);
            ofFloat.setDuration(350L);
            ofFloat.addListener(this.mAnimatorListener);
            ofFloat.start();
            this.mRunningAnimations.add(ofFloat);
        } else {
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, "glowAlpha", this.mGlowAlpha, 0.0f);
            ofFloat2.setInterpolator(ALPHA_OUT_INTERPOLATOR);
            i = this.mSpeedUpNextFade ? 80 : 450;
            this.mSpeedUpNextFade = false;
            ofFloat2.setDuration(i);
            ofFloat2.addListener(this.mAnimatorListener);
            ofFloat2.start();
            this.mRunningAnimations.add(ofFloat2);
        }
        this.mPressed = z;
        return true;
    }

    public void setGlowAlpha(float f) {
        this.mGlowAlpha = f;
        invalidateSelf();
    }

    public void setGlowScale(float f) {
        this.mGlowScale = f;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        if (visible) {
            jumpToCurrentState();
        }
        return visible;
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
    }
}
