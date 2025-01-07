package com.android.systemui.accessibility.floatingmenu;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.ArrayMap;
import android.util.Log;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Space;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import androidx.compose.foundation.text.ValidatingOffsetMappingKt$$ExternalSyntheticOutline0;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.viewpager.widget.ViewPager$$ExternalSyntheticOutline0;
import com.android.wm.shell.R;
import com.android.wm.shell.shared.animation.PhysicsAnimator;
import com.android.wm.shell.shared.bubbles.DismissCircleView;
import java.util.Iterator;
import java.util.Map;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Reflection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DragToInteractView extends FrameLayout {
    public static final String TAG = Reflection.getOrCreateKotlinClass(DragToInteractView.class).getSimpleName();
    public final DragToInteractView$GRADIENT_ALPHA$1 GRADIENT_ALPHA;
    public final long INTERACT_SCRIM_FADE_MS;
    public final Config config;
    public final GradientDrawable gradientDrawable;
    public final ArrayMap interactMap;
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
            return Integer.hashCode(R.drawable.pip_ic_close_white) + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(R.drawable.dismiss_circle_background, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(android.R.color.system_neutral1_900, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(R.dimen.floating_dismiss_gradient_height, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(R.dimen.floating_dismiss_bottom_margin, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(R.dimen.dismiss_target_x_size, Integer.hashCode(R.dimen.dismiss_circle_size) * 31, 31), 31), 31), 31), 31);
        }

        public final String toString() {
            StringBuilder m = ValidatingOffsetMappingKt$$ExternalSyntheticOutline0.m(R.dimen.dismiss_circle_size, R.dimen.dismiss_target_x_size, "Config(targetSizeResId=", ", iconSizeResId=", ", bottomMarginResId=");
            ViewPager$$ExternalSyntheticOutline0.m(m, R.dimen.floating_dismiss_bottom_margin, ", floatingGradientHeightResId=", R.dimen.floating_dismiss_gradient_height, ", floatingGradientColorResId=17170472, backgroundResId=");
            m.append(R.drawable.dismiss_circle_background);
            m.append(", iconResId=");
            m.append(R.drawable.pip_ic_close_white);
            m.append(")");
            return m.toString();
        }
    }

    public DragToInteractView(Context context) {
        super(context);
        this.interactMap = new ArrayMap();
        this.spring = new PhysicsAnimator.SpringConfig(200.0f, 0.75f);
        this.INTERACT_SCRIM_FADE_MS = 200L;
        this.wm = (WindowManager) context.getSystemService("window");
        this.GRADIENT_ALPHA = new DragToInteractView$GRADIENT_ALPHA$1("alpha");
        setClipToPadding(false);
        setClipChildren(false);
        setVisibility(4);
        Config config = new Config();
        this.config = config;
        setLayoutParams(new FrameLayout.LayoutParams(-1, getResources().getDimensionPixelSize(R.dimen.floating_dismiss_gradient_height), 80));
        updatePadding();
        int color = getContext().getColor(android.R.color.system_neutral1_900);
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, new int[]{Color.argb((int) 178.5f, Color.red(color), Color.green(color), Color.blue(color)), 0});
        gradientDrawable.setDither(true);
        gradientDrawable.setAlpha(0);
        this.gradientDrawable = gradientDrawable;
        setBackground(gradientDrawable);
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        linearLayout.setWeightSum(0.0f);
        addView(linearLayout);
        addSpace(linearLayout);
        addCircle(config, R.id.action_remove_menu, R.drawable.pip_ic_close_white, linearLayout);
        addCircle(config, R.id.action_edit, R.drawable.ic_screenshot_edit, linearLayout);
        setClickable(false);
        setFocusable(false);
        setImportantForAccessibility(2);
    }

    public final void addCircle(Config config, int i, int i2, LinearLayout linearLayout) {
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.dismiss_circle_size);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dimensionPixelSize, dimensionPixelSize, 0.0f);
        layoutParams.gravity = 81;
        DismissCircleView dismissCircleView = new DismissCircleView(getContext());
        dismissCircleView.setId(i);
        dismissCircleView.mBackgroundResId = R.drawable.dismiss_circle_background;
        dismissCircleView.mIconSizeResId = R.dimen.dismiss_target_x_size;
        dismissCircleView.setBackground(dismissCircleView.getContext().getDrawable(R.drawable.dismiss_circle_background));
        dismissCircleView.mIconView.setImageDrawable(dismissCircleView.getContext().getDrawable(i2));
        int dimensionPixelSize2 = dismissCircleView.getResources().getDimensionPixelSize(dismissCircleView.mIconSizeResId);
        dismissCircleView.mIconView.setLayoutParams(new FrameLayout.LayoutParams(dimensionPixelSize2, dimensionPixelSize2, 17));
        dismissCircleView.setLayoutParams(layoutParams);
        dismissCircleView.setTranslationY(getResources().getDimensionPixelSize(R.dimen.floating_dismiss_gradient_height));
        ArrayMap arrayMap = this.interactMap;
        Integer valueOf = Integer.valueOf(dismissCircleView.getId());
        Function2 function2 = PhysicsAnimator.onAnimatorCreated;
        arrayMap.put(valueOf, new Pair(dismissCircleView, PhysicsAnimator.Companion.getInstance(dismissCircleView)));
        linearLayout.addView(dismissCircleView);
        addSpace(linearLayout);
    }

    public final void addSpace(LinearLayout linearLayout) {
        Space space = new Space(getContext());
        space.setLayoutParams(new LinearLayout.LayoutParams(-2, -2, 1.0f));
        linearLayout.addView(space);
        linearLayout.setWeightSum(linearLayout.getWeightSum() + 1.0f);
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
            ofInt.setDuration(this.INTERACT_SCRIM_FADE_MS);
            ofInt.start();
            Iterator it = this.interactMap.entrySet().iterator();
            while (it.hasNext()) {
                PhysicsAnimator physicsAnimator = (PhysicsAnimator) ((Pair) ((Map.Entry) it.next()).getValue()).getSecond();
                physicsAnimator.spring(DynamicAnimation.TRANSLATION_Y, getHeight(), 0.0f, this.spring);
                physicsAnimator.withEndActions(new Function0() { // from class: com.android.systemui.accessibility.floatingmenu.DragToInteractView$hide$1$1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        DragToInteractView.this.setVisibility(4);
                        return Unit.INSTANCE;
                    }
                });
                physicsAnimator.start();
            }
        }
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
}
