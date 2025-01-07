package com.android.systemui.screenshot.ui.binder;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.systemui.screenshot.ui.TransitioningIconDrawable;
import com.android.systemui.screenshot.ui.viewmodel.ActionButtonAppearance;
import com.android.systemui.screenshot.ui.viewmodel.ActionButtonViewModel;
import com.android.wm.shell.R;
import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ActionButtonViewBinder {
    public static void bind(View view, final ActionButtonViewModel actionButtonViewModel) {
        ImageView imageView = (ImageView) view.requireViewById(R.id.overlay_action_chip_icon);
        TextView textView = (TextView) view.requireViewById(R.id.overlay_action_chip_text);
        if (imageView.getDrawable() == null) {
            final TransitioningIconDrawable transitioningIconDrawable = new TransitioningIconDrawable();
            transitioningIconDrawable.alpha = 255;
            ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            Intrinsics.checkNotNull(ofFloat);
            ofFloat.addListener(new Animator.AnimatorListener() { // from class: com.android.systemui.screenshot.ui.TransitioningIconDrawable$transitionAnimator$lambda$1$$inlined$doOnEnd$1
                @Override // android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animator) {
                    TransitioningIconDrawable transitioningIconDrawable2 = TransitioningIconDrawable.this;
                    transitioningIconDrawable2.drawable = transitioningIconDrawable2.enteringDrawable;
                    transitioningIconDrawable2.enteringDrawable = null;
                    transitioningIconDrawable2.invalidateSelf();
                }

                @Override // android.animation.Animator.AnimatorListener
                public final void onAnimationCancel(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public final void onAnimationRepeat(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public final void onAnimationStart(Animator animator) {
                }
            });
            transitioningIconDrawable.transitionAnimator = ofFloat;
            imageView.setImageDrawable(transitioningIconDrawable);
        }
        Drawable drawable = imageView.getDrawable();
        TransitioningIconDrawable transitioningIconDrawable2 = drawable instanceof TransitioningIconDrawable ? (TransitioningIconDrawable) drawable : null;
        ActionButtonAppearance actionButtonAppearance = actionButtonViewModel.appearance;
        if (transitioningIconDrawable2 != null) {
            Drawable drawable2 = actionButtonAppearance.icon;
            if (!Objects.equals(transitioningIconDrawable2.drawable, drawable2) || transitioningIconDrawable2.transitionAnimator.isRunning()) {
                if (drawable2 != null) {
                    drawable2.setColorFilter(transitioningIconDrawable2.colorFilter);
                }
                if (drawable2 != null) {
                    drawable2.setTintList(transitioningIconDrawable2.tint);
                }
                if (transitioningIconDrawable2.drawable == null) {
                    transitioningIconDrawable2.drawable = drawable2;
                    transitioningIconDrawable2.invalidateSelf();
                } else if (transitioningIconDrawable2.enteringDrawable != null) {
                    transitioningIconDrawable2.enteringDrawable = drawable2;
                } else {
                    transitioningIconDrawable2.enteringDrawable = drawable2;
                    transitioningIconDrawable2.transitionAnimator.setCurrentFraction(0.0f);
                    transitioningIconDrawable2.transitionAnimator.start();
                    transitioningIconDrawable2.invalidateSelf();
                }
            }
        }
        imageView.setImageDrawable(actionButtonAppearance.icon);
        if (!actionButtonAppearance.tint) {
            imageView.setImageTintList(null);
        }
        textView.setText(actionButtonAppearance.label);
        Drawable drawable3 = actionButtonAppearance.customBackground;
        if (drawable3 != null) {
            if (drawable3.canApplyTheme()) {
                drawable3.applyTheme(view.getRootView().getContext().getTheme());
            }
            view.setBackground(drawable3);
        }
        CharSequence charSequence = actionButtonAppearance.label;
        boolean z = charSequence != null && charSequence.length() > 0;
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) textView.getLayoutParams();
        if (z) {
            layoutParams.setMarginStart(imageView.getResources().getDimensionPixelSize(R.dimen.overlay_action_chip_padding_start));
            layoutParams.setMarginEnd(imageView.getResources().getDimensionPixelSize(R.dimen.overlay_action_chip_spacing));
            layoutParams2.setMarginStart(0);
            layoutParams2.setMarginEnd(textView.getResources().getDimensionPixelSize(R.dimen.overlay_action_chip_padding_end));
        } else {
            int dimensionPixelSize = imageView.getResources().getDimensionPixelSize(R.dimen.overlay_action_chip_icon_only_padding_horizontal);
            layoutParams.setMarginStart(dimensionPixelSize);
            layoutParams.setMarginEnd(dimensionPixelSize);
        }
        imageView.setLayoutParams(layoutParams);
        textView.setLayoutParams(layoutParams2);
        view.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.screenshot.ui.binder.ActionButtonViewBinder$bind$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ActionButtonViewModel.this.onClicked.invoke();
            }
        });
        view.setTag(Integer.valueOf(actionButtonViewModel.id));
        view.setContentDescription(actionButtonAppearance.description);
        view.setVisibility(0);
        view.setAlpha(1.0f);
    }
}
