package com.android.wm.shell.shared.bubbles;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.FrameLayout;
import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import androidx.compose.foundation.text.ValidatingOffsetMappingKt$$ExternalSyntheticOutline0;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.viewpager.widget.ViewPager$$ExternalSyntheticOutline0;
import com.android.wm.shell.R;
import com.android.wm.shell.shared.animation.PhysicsAnimator;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Reflection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DismissView extends FrameLayout {
    public static final String TAG = Reflection.getOrCreateKotlinClass(DismissView.class).getSimpleName();
    public final long DISMISS_SCRIM_FADE_MS;
    public final DismissView$GRADIENT_ALPHA$1 GRADIENT_ALPHA;
    public final PhysicsAnimator animator;
    public final DismissCircleView circle;
    public Config config;
    public GradientDrawable gradientDrawable;
    public boolean isShowing;
    public final PhysicsAnimator.SpringConfig spring;
    public final WindowManager wm;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Config {
        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Config)) {
                return false;
            }
            ((Config) obj).getClass();
            return true;
        }

        public final int hashCode() {
            return Integer.hashCode(R.drawable.pip_ic_close_white) + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(R.drawable.dismiss_circle_background, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(android.R.color.system_neutral1_900, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(R.dimen.floating_dismiss_gradient_height, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(R.dimen.floating_dismiss_bottom_margin, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(R.dimen.dismiss_target_x_size, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(R.dimen.dismiss_circle_size, Integer.hashCode(R.id.dismiss_view) * 31, 31), 31), 31), 31), 31), 31);
        }

        public final String toString() {
            StringBuilder m = ValidatingOffsetMappingKt$$ExternalSyntheticOutline0.m(R.id.dismiss_view, R.dimen.dismiss_circle_size, "Config(dismissViewResId=", ", targetSizeResId=", ", iconSizeResId=");
            ViewPager$$ExternalSyntheticOutline0.m(m, R.dimen.dismiss_target_x_size, ", bottomMarginResId=", R.dimen.floating_dismiss_bottom_margin, ", floatingGradientHeightResId=");
            ViewPager$$ExternalSyntheticOutline0.m(m, R.dimen.floating_dismiss_gradient_height, ", floatingGradientColorResId=17170472, backgroundResId=", R.drawable.dismiss_circle_background, ", iconResId=");
            return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(m, R.drawable.pip_ic_close_white, ")");
        }
    }

    public DismissView(Context context) {
        super(context);
        DismissCircleView dismissCircleView = new DismissCircleView(context);
        this.circle = dismissCircleView;
        Function2 function2 = PhysicsAnimator.onAnimatorCreated;
        this.animator = PhysicsAnimator.Companion.getInstance(dismissCircleView);
        this.spring = new PhysicsAnimator.SpringConfig(200.0f, 0.75f);
        this.DISMISS_SCRIM_FADE_MS = 200L;
        this.wm = (WindowManager) context.getSystemService("window");
        this.GRADIENT_ALPHA = new DismissView$GRADIENT_ALPHA$1("alpha");
        setClipToPadding(false);
        setClipChildren(false);
        setVisibility(4);
        addView(dismissCircleView);
    }

    public final void hide() {
        if (this.isShowing) {
            GradientDrawable gradientDrawable = this.gradientDrawable;
            if (gradientDrawable == null) {
                Log.e(TAG, "The view isn't ready. Should be called after `setup`");
            }
            if (gradientDrawable == null) {
                return;
            }
            this.isShowing = false;
            ObjectAnimator ofInt = ObjectAnimator.ofInt(gradientDrawable, this.GRADIENT_ALPHA, gradientDrawable.getAlpha(), 0);
            ofInt.setDuration(this.DISMISS_SCRIM_FADE_MS);
            ofInt.start();
            PhysicsAnimator physicsAnimator = this.animator;
            physicsAnimator.spring(DynamicAnimation.TRANSLATION_Y, getHeight(), 0.0f, this.spring);
            physicsAnimator.withEndActions(new Function0() { // from class: com.android.wm.shell.shared.bubbles.DismissView$hide$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    DismissView.this.setVisibility(4);
                    DismissView.this.circle.setScaleX(1.0f);
                    DismissView.this.circle.setScaleY(1.0f);
                    return Unit.INSTANCE;
                }
            });
            physicsAnimator.start();
        }
    }

    public final void show() {
        if (this.isShowing) {
            return;
        }
        GradientDrawable gradientDrawable = this.gradientDrawable;
        if (gradientDrawable == null) {
            Log.e(TAG, "The view isn't ready. Should be called after `setup`");
        }
        if (gradientDrawable == null) {
            return;
        }
        this.isShowing = true;
        setVisibility(0);
        ObjectAnimator ofInt = ObjectAnimator.ofInt(gradientDrawable, this.GRADIENT_ALPHA, gradientDrawable.getAlpha(), 255);
        ofInt.setDuration(this.DISMISS_SCRIM_FADE_MS);
        ofInt.start();
        this.animator.cancel();
        PhysicsAnimator physicsAnimator = this.animator;
        physicsAnimator.spring(DynamicAnimation.TRANSLATION_Y, 0.0f, 0.0f, this.spring);
        physicsAnimator.start();
    }

    public final void updatePadding() {
        Config config = this.config;
        if (config == null) {
            Log.e(TAG, "The view isn't ready. Should be called after `setup`");
        }
        if (config == null) {
            return;
        }
        setPadding(0, 0, 0, getResources().getDimensionPixelSize(R.dimen.floating_dismiss_bottom_margin) + this.wm.getCurrentWindowMetrics().getWindowInsets().getInsetsIgnoringVisibility(WindowInsets.Type.navigationBars()).bottom);
    }

    public final void updateResources() {
        Config config = this.config;
        if (config == null) {
            Log.e(TAG, "The view isn't ready. Should be called after `setup`");
        }
        if (config == null) {
            return;
        }
        updatePadding();
        getLayoutParams().height = getResources().getDimensionPixelSize(R.dimen.floating_dismiss_gradient_height);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.dismiss_circle_size);
        this.circle.getLayoutParams().width = dimensionPixelSize;
        this.circle.getLayoutParams().height = dimensionPixelSize;
        this.circle.requestLayout();
    }
}
