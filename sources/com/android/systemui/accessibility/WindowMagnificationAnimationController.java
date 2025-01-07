package com.android.systemui.accessibility;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import android.view.accessibility.IRemoteMagnificationAnimationCallback;
import androidx.compose.animation.AndroidFlingSpline$$ExternalSyntheticOutline0;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class WindowMagnificationAnimationController implements ValueAnimator.AnimatorUpdateListener, Animator.AnimatorListener {
    public static final boolean DEBUG = Log.isLoggable("WindowMagnificationAnimationController", 3);
    static final int STATE_DISABLED = 0;
    static final int STATE_ENABLED = 1;
    public IRemoteMagnificationAnimationCallback mAnimationCallback;
    public final Context mContext;
    public WindowMagnificationController mController;
    public WindowMagnificationController$$ExternalSyntheticLambda0 mOnAnimationEndRunnable;
    public final ValueAnimator mValueAnimator;
    public final AnimationSpec mStartSpec = new AnimationSpec();
    public final AnimationSpec mEndSpec = new AnimationSpec();
    public float mMagnificationFrameOffsetRatioX = 0.0f;
    public float mMagnificationFrameOffsetRatioY = 0.0f;
    public boolean mEndAnimationCanceled = false;
    public int mState = 0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class AnimationSpec {
        public float mScale = Float.NaN;
        public float mCenterX = Float.NaN;
        public float mCenterY = Float.NaN;

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || AnimationSpec.class != obj.getClass()) {
                return false;
            }
            AnimationSpec animationSpec = (AnimationSpec) obj;
            return this.mScale == animationSpec.mScale && this.mCenterX == animationSpec.mCenterX && this.mCenterY == animationSpec.mCenterY;
        }

        public final int hashCode() {
            float f = this.mScale;
            int floatToIntBits = (f != 0.0f ? Float.floatToIntBits(f) : 0) * 31;
            float f2 = this.mCenterX;
            int floatToIntBits2 = (floatToIntBits + (f2 != 0.0f ? Float.floatToIntBits(f2) : 0)) * 31;
            float f3 = this.mCenterY;
            return floatToIntBits2 + (f3 != 0.0f ? Float.floatToIntBits(f3) : 0);
        }

        public final void set(float f, float f2, float f3) {
            this.mScale = f;
            this.mCenterX = f2;
            this.mCenterY = f3;
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("AnimationSpec{mScale=");
            sb.append(this.mScale);
            sb.append(", mCenterX=");
            sb.append(this.mCenterX);
            sb.append(", mCenterY=");
            return AndroidFlingSpline$$ExternalSyntheticOutline0.m(sb, this.mCenterY, '}');
        }
    }

    public WindowMagnificationAnimationController(Context context, ValueAnimator valueAnimator) {
        this.mContext = context;
        this.mValueAnimator = valueAnimator;
        valueAnimator.addUpdateListener(this);
        valueAnimator.addListener(this);
    }

    public final void enableWindowMagnification(float f, float f2, float f3, float f4, float f5, IRemoteMagnificationAnimationCallback iRemoteMagnificationAnimationCallback) {
        if (this.mController == null) {
            return;
        }
        sendAnimationCallback(false);
        this.mMagnificationFrameOffsetRatioX = f4;
        this.mMagnificationFrameOffsetRatioY = f5;
        if (iRemoteMagnificationAnimationCallback == null) {
            int i = this.mState;
            if (i == 3 || i == 2) {
                this.mValueAnimator.cancel();
            }
            this.mController.updateWindowMagnificationInternal(f, f2, f3, this.mMagnificationFrameOffsetRatioX, this.mMagnificationFrameOffsetRatioY);
            updateState();
            return;
        }
        this.mAnimationCallback = iRemoteMagnificationAnimationCallback;
        setupEnableAnimationSpecs(f, f2, f3);
        if (!this.mEndSpec.equals(this.mStartSpec)) {
            int i2 = this.mState;
            if (i2 == 2) {
                this.mValueAnimator.reverse();
            } else {
                if (i2 == 3) {
                    this.mValueAnimator.cancel();
                }
                this.mValueAnimator.start();
            }
            setState(3);
            return;
        }
        int i3 = this.mState;
        if (i3 == 0) {
            this.mController.updateWindowMagnificationInternal(f, f2, f3, this.mMagnificationFrameOffsetRatioX, this.mMagnificationFrameOffsetRatioY);
        } else if (i3 == 3 || i3 == 2) {
            this.mValueAnimator.cancel();
        }
        sendAnimationCallback(true);
        updateState();
    }

    public int getState() {
        return this.mState;
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        this.mEndAnimationCanceled = true;
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        this.mEndAnimationCanceled = false;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        if (this.mController == null) {
            return;
        }
        float animatedFraction = valueAnimator.getAnimatedFraction();
        AnimationSpec animationSpec = this.mStartSpec;
        float f = animationSpec.mScale;
        AnimationSpec animationSpec2 = this.mEndSpec;
        float m = AndroidFlingSpline$$ExternalSyntheticOutline0.m(animationSpec2.mScale, f, animatedFraction, f);
        float f2 = animationSpec.mCenterX;
        float m2 = AndroidFlingSpline$$ExternalSyntheticOutline0.m(animationSpec2.mCenterX, f2, animatedFraction, f2);
        float f3 = animationSpec.mCenterY;
        this.mController.updateWindowMagnificationInternal(m, m2, AndroidFlingSpline$$ExternalSyntheticOutline0.m(animationSpec2.mCenterY, f3, animatedFraction, f3), this.mMagnificationFrameOffsetRatioX, this.mMagnificationFrameOffsetRatioY);
    }

    public final void sendAnimationCallback(boolean z) {
        IRemoteMagnificationAnimationCallback iRemoteMagnificationAnimationCallback = this.mAnimationCallback;
        if (iRemoteMagnificationAnimationCallback != null) {
            try {
                iRemoteMagnificationAnimationCallback.onResult(z);
                if (DEBUG) {
                    Log.d("WindowMagnificationAnimationController", "sendAnimationCallback success = " + z);
                }
            } catch (RemoteException e) {
                Log.w("WindowMagnificationAnimationController", "sendAnimationCallback failed : " + e);
            }
            this.mAnimationCallback = null;
        }
    }

    public final void setState(int i) {
        if (DEBUG) {
            Log.d("WindowMagnificationAnimationController", "setState from " + this.mState + " to " + i);
        }
        this.mState = i;
    }

    public final void setupEnableAnimationSpecs(float f, float f2, float f3) {
        WindowMagnificationController windowMagnificationController = this.mController;
        if (windowMagnificationController == null) {
            return;
        }
        float f4 = windowMagnificationController.isActivated() ? windowMagnificationController.mScale : Float.NaN;
        WindowMagnificationController windowMagnificationController2 = this.mController;
        float exactCenterX = windowMagnificationController2.isActivated() ? windowMagnificationController2.mMagnificationFrame.exactCenterX() : Float.NaN;
        WindowMagnificationController windowMagnificationController3 = this.mController;
        float exactCenterY = windowMagnificationController3.isActivated() ? windowMagnificationController3.mMagnificationFrame.exactCenterY() : Float.NaN;
        if (this.mState == 0) {
            this.mStartSpec.set(1.0f, f2, f3);
            AnimationSpec animationSpec = this.mEndSpec;
            if (Float.isNaN(f)) {
                f = this.mContext.getResources().getInteger(R.integer.magnification_default_scale);
            }
            animationSpec.set(f, f2, f3);
        } else {
            this.mStartSpec.set(f4, exactCenterX, exactCenterY);
            int i = this.mState;
            if (i == 3) {
                f4 = this.mEndSpec.mScale;
            }
            if (i == 3) {
                exactCenterX = this.mEndSpec.mCenterX;
            }
            if (i == 3) {
                exactCenterY = this.mEndSpec.mCenterY;
            }
            AnimationSpec animationSpec2 = this.mEndSpec;
            if (Float.isNaN(f)) {
                f = f4;
            }
            if (Float.isNaN(f2)) {
                f2 = exactCenterX;
            }
            if (Float.isNaN(f3)) {
                f3 = exactCenterY;
            }
            animationSpec2.set(f, f2, f3);
        }
        if (DEBUG) {
            Log.d("WindowMagnificationAnimationController", "SetupEnableAnimationSpecs : mStartSpec = " + this.mStartSpec + ", endSpec = " + this.mEndSpec);
        }
    }

    public final void updateState() {
        WindowMagnificationController windowMagnificationController = this.mController;
        if (Float.isNaN(windowMagnificationController.isActivated() ? windowMagnificationController.mScale : Float.NaN)) {
            setState(0);
        } else {
            setState(1);
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator, boolean z) {
        if (this.mEndAnimationCanceled || this.mController == null) {
            return;
        }
        this.mOnAnimationEndRunnable.run();
        if (this.mState == 2) {
            this.mController.deleteWindowMagnification$1();
        }
        updateState();
        sendAnimationCallback(true);
        this.mValueAnimator.setDuration(this.mContext.getResources().getInteger(android.R.integer.config_longAnimTime));
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationRepeat(Animator animator) {
    }
}
