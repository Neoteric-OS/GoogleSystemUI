package com.android.wm.shell.bubbles;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.widget.FrameLayout;
import com.android.wm.shell.R;
import com.android.wm.shell.shared.bubbles.DismissCircleView;
import com.android.wm.shell.shared.bubbles.DismissView;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class DismissViewUtils {
    public static final void setup(DismissView dismissView) {
        dismissView.config = new DismissView.Config();
        dismissView.setLayoutParams(new FrameLayout.LayoutParams(-1, dismissView.getResources().getDimensionPixelSize(R.dimen.floating_dismiss_gradient_height), 80));
        dismissView.updatePadding();
        int color = dismissView.getContext().getColor(android.R.color.system_neutral1_900);
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, new int[]{Color.argb((int) 178.5f, Color.red(color), Color.green(color), Color.blue(color)), 0});
        gradientDrawable.setDither(true);
        gradientDrawable.setAlpha(0);
        dismissView.gradientDrawable = gradientDrawable;
        dismissView.setBackgroundDrawable(gradientDrawable);
        dismissView.circle.setId(R.id.dismiss_view);
        DismissCircleView dismissCircleView = dismissView.circle;
        dismissCircleView.mBackgroundResId = R.drawable.dismiss_circle_background;
        dismissCircleView.mIconSizeResId = R.dimen.dismiss_target_x_size;
        dismissCircleView.setBackground(dismissCircleView.getContext().getDrawable(R.drawable.dismiss_circle_background));
        dismissCircleView.mIconView.setImageDrawable(dismissCircleView.getContext().getDrawable(R.drawable.pip_ic_close_white));
        int dimensionPixelSize = dismissCircleView.getResources().getDimensionPixelSize(dismissCircleView.mIconSizeResId);
        dismissCircleView.mIconView.setLayoutParams(new FrameLayout.LayoutParams(dimensionPixelSize, dimensionPixelSize, 17));
        int dimensionPixelSize2 = dismissView.getResources().getDimensionPixelSize(R.dimen.dismiss_circle_size);
        dismissView.circle.setLayoutParams(new FrameLayout.LayoutParams(dimensionPixelSize2, dimensionPixelSize2, 81));
        dismissView.circle.setTranslationY(dismissView.getResources().getDimensionPixelSize(R.dimen.floating_dismiss_gradient_height));
    }
}
