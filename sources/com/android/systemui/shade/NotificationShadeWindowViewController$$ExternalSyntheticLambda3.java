package com.android.systemui.shade;

import com.android.keyguard.KeyguardUnfoldTransition;
import com.android.systemui.shared.animation.UnfoldConstantTranslateAnimator;
import com.android.wm.shell.R;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class NotificationShadeWindowViewController$$ExternalSyntheticLambda3 implements Consumer {
    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        KeyguardUnfoldTransition keyguardUnfoldTransition = (KeyguardUnfoldTransition) obj;
        float dimensionPixelSize = keyguardUnfoldTransition.context.getResources().getDimensionPixelSize(R.dimen.keyguard_unfold_translation_x);
        ((UnfoldConstantTranslateAnimator) keyguardUnfoldTransition.translateAnimator$delegate.getValue()).init(keyguardUnfoldTransition.shadeWindowView, dimensionPixelSize);
        ((UnfoldConstantTranslateAnimator) keyguardUnfoldTransition.shortcutButtonsAnimator$delegate.getValue()).init(keyguardUnfoldTransition.keyguardRootView, dimensionPixelSize);
    }
}
