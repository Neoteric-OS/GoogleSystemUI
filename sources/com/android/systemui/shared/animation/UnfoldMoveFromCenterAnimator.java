package com.android.systemui.shared.animation;

import android.graphics.Point;
import android.view.View;
import android.view.WindowManager;
import com.android.systemui.statusbar.phone.PhoneStatusBarViewController;
import com.android.systemui.statusbar.phone.StatusBarMoveFromCenterAnimationController;
import com.android.systemui.unfold.UnfoldTransitionProgressProvider;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UnfoldMoveFromCenterAnimator implements UnfoldTransitionProgressProvider.TransitionProgressListener {
    public final StatusBarMoveFromCenterAnimationController.StatusBarIconsAlphaProvider alphaProvider;
    public final List animatedViews;
    public final Point screenSize;
    public final AnonymousClass1 translationApplier = new AnonymousClass1();
    public final PhoneStatusBarViewController.StatusBarViewsCenterProvider viewCenterProvider;
    public final WindowManager windowManager;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.shared.animation.UnfoldMoveFromCenterAnimator$1, reason: invalid class name */
    public final class AnonymousClass1 {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class AnimatedView {
        public final WeakReference view;
    }

    public UnfoldMoveFromCenterAnimator(WindowManager windowManager, PhoneStatusBarViewController.StatusBarViewsCenterProvider statusBarViewsCenterProvider, StatusBarMoveFromCenterAnimationController.StatusBarIconsAlphaProvider statusBarIconsAlphaProvider) {
        this.alphaProvider = statusBarIconsAlphaProvider;
        new Point();
        this.animatedViews = new ArrayList();
    }

    @Override // com.android.systemui.unfold.UnfoldTransitionProgressProvider.TransitionProgressListener
    public final void onTransitionProgress(float f) {
        for (AnimatedView animatedView : this.animatedViews) {
            View view = (View) animatedView.view.get();
            if (view != null) {
                float f2 = 1 - f;
                view.setTranslationX(0.0f * f2);
                view.setTranslationY(0.0f * f2);
            }
            StatusBarMoveFromCenterAnimationController.StatusBarIconsAlphaProvider statusBarIconsAlphaProvider = this.alphaProvider;
            View view2 = (View) animatedView.view.get();
            if (view2 != null) {
                view2.setAlpha(Intrinsics.areEqual(StatusBarMoveFromCenterAnimationController.this.isOnHomeActivity, Boolean.TRUE) ? 1.0f : Math.max(0.0f, (f - 0.75f) / 0.25f));
            }
        }
    }
}
