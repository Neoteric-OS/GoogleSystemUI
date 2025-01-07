package com.android.keyguard;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.util.MathUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.android.app.animation.Interpolators;
import com.android.keyguard.KeyguardInputView;
import com.android.settingslib.animation.DisappearAnimationUtils;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class KeyguardPINView extends KeyguardPinBasedInputView {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final ValueAnimator mAppearAnimator;
    public View mBouncerMessageArea;
    public ConstraintLayout mContainerConstraintLayout;
    public final DisappearAnimationUtils mDisappearAnimationUtils;
    public final DisappearAnimationUtils mDisappearAnimationUtilsLocked;
    public final int mDisappearYTranslation;
    public int mLastDevicePosture;
    public View[][] mViews;
    public final int mYTrans;
    public final int mYTransOffset;

    public KeyguardPINView(Context context) {
        this(context, null);
    }

    @Override // com.android.keyguard.KeyguardAbsKeyInputView
    public final int getPasswordTextViewId() {
        return R.id.pinEntry;
    }

    @Override // com.android.keyguard.KeyguardAbsKeyInputView
    public final int getWrongPasswordStringId() {
        return R.string.kg_wrong_pin;
    }

    @Override // android.view.View
    public final boolean hasOverlappingRendering() {
        return false;
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        updateMargins$1();
    }

    public final void onDevicePostureChanged(int i) {
        if (this.mLastDevicePosture == i) {
            return;
        }
        this.mLastDevicePosture = i;
        updateMargins$1();
    }

    @Override // com.android.keyguard.KeyguardPinBasedInputView, com.android.keyguard.KeyguardAbsKeyInputView, com.android.keyguard.KeyguardInputView, android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.mBouncerMessageArea = findViewById(R.id.bouncer_message_area);
        this.mViews = new View[][]{new View[]{findViewById(R.id.row0), null, null}, new View[]{findViewById(R.id.key1), findViewById(R.id.key2), findViewById(R.id.key3)}, new View[]{findViewById(R.id.key4), findViewById(R.id.key5), findViewById(R.id.key6)}, new View[]{findViewById(R.id.key7), findViewById(R.id.key8), findViewById(R.id.key9)}, new View[]{findViewById(R.id.delete_button), findViewById(R.id.key0), findViewById(R.id.key_enter)}, new View[]{null, this.mEcaView, null}};
    }

    @Override // com.android.keyguard.KeyguardInputView
    public final void startAppearAnimation() {
        setAlpha(1.0f);
        setTranslationY(0.0f);
        if (this.mAppearAnimator.isRunning()) {
            this.mAppearAnimator.cancel();
        }
        this.mAppearAnimator.setDuration(650L);
        this.mAppearAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.keyguard.KeyguardPINView$$ExternalSyntheticLambda1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                KeyguardPINView keyguardPINView = KeyguardPINView.this;
                int i = KeyguardPINView.$r8$clinit;
                keyguardPINView.getClass();
                float animatedFraction = valueAnimator.getAnimatedFraction();
                Interpolator interpolator = Interpolators.STANDARD_DECELERATE;
                Interpolator interpolator2 = Interpolators.LEGACY_DECELERATE;
                float interpolation = ((PathInterpolator) interpolator).getInterpolation(animatedFraction);
                View view = keyguardPINView.mBouncerMessageArea;
                float f = keyguardPINView.mYTrans;
                view.setTranslationY(f - (f * interpolation));
                keyguardPINView.mBouncerMessageArea.setAlpha(interpolation);
                int i2 = 0;
                while (true) {
                    View[][] viewArr = keyguardPINView.mViews;
                    if (i2 >= viewArr.length) {
                        return;
                    }
                    for (GLSurfaceView gLSurfaceView : viewArr[i2]) {
                        if (gLSurfaceView != 0) {
                            float interpolation2 = ((PathInterpolator) interpolator2).getInterpolation(MathUtils.constrain((animatedFraction - (i2 * 0.075f)) / (1.0f - (keyguardPINView.mViews.length * 0.075f)), 0.0f, 1.0f));
                            gLSurfaceView.setAlpha(interpolation2);
                            float f2 = (keyguardPINView.mYTransOffset * i2) + keyguardPINView.mYTrans;
                            gLSurfaceView.setTranslationY(f2 - (f2 * interpolation));
                            if (gLSurfaceView instanceof NumPadAnimationListener) {
                                ((NumPadAnimationListener) gLSurfaceView).setProgress(interpolation2);
                            }
                        }
                    }
                    i2++;
                }
            }
        });
        this.mAppearAnimator.addListener(new KeyguardInputView.AnonymousClass1(19));
        this.mAppearAnimator.start();
    }

    public final void updateMargins$1() {
        int dimensionPixelSize = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(R.dimen.num_pad_entry_row_margin_bottom);
        int dimensionPixelSize2 = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(R.dimen.num_pad_key_margin_end);
        String string = ((LinearLayout) this).mContext.getResources().getString(R.string.num_pad_key_ratio);
        for (int i = 1; i < 5; i++) {
            for (int i2 = 0; i2 < 3; i2++) {
                View view = this.mViews[i][i2];
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) view.getLayoutParams();
                layoutParams.dimensionRatio = string;
                if (i != 4) {
                    ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin = dimensionPixelSize;
                }
                if (i2 != 2) {
                    ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin = dimensionPixelSize2;
                }
                view.setLayoutParams(layoutParams);
            }
        }
        float f = ((LinearLayout) this).mContext.getResources().getFloat(R.dimen.half_opened_bouncer_height_ratio);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this.mContainerConstraintLayout);
        if (this.mLastDevicePosture != 2) {
            f = 0.0f;
        }
        constraintSet.setGuidelinePercent(R.id.pin_pad_top_guideline, f);
        constraintSet.applyTo(this.mContainerConstraintLayout);
    }

    public KeyguardPINView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mAppearAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.mLastDevicePosture = 0;
        this.mDisappearAnimationUtils = new DisappearAnimationUtils(context, 125L, 0.6f, 0.45f, AnimationUtils.loadInterpolator(((LinearLayout) this).mContext, android.R.interpolator.fast_out_linear_in));
        this.mDisappearAnimationUtilsLocked = new DisappearAnimationUtils(context, 187L, 0.6f, 0.45f, AnimationUtils.loadInterpolator(((LinearLayout) this).mContext, android.R.interpolator.fast_out_linear_in));
        this.mDisappearYTranslation = getResources().getDimensionPixelSize(R.dimen.disappear_y_translation);
        this.mYTrans = getResources().getDimensionPixelSize(R.dimen.pin_view_trans_y_entry);
        this.mYTransOffset = getResources().getDimensionPixelSize(R.dimen.pin_view_trans_y_entry_offset);
    }
}
