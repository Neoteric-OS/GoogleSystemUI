package com.android.wm.shell.shared.bubbles;

import androidx.core.animation.Animator;
import androidx.core.animation.AnimatorListenerAdapter;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BaseBubblePinController$addEndAction$1 extends AnimatorListenerAdapter {
    public final /* synthetic */ Runnable $runnable;

    public BaseBubblePinController$addEndAction$1(Runnable runnable) {
        this.$runnable = runnable;
    }

    @Override // androidx.core.animation.AnimatorListenerAdapter, androidx.core.animation.Animator.AnimatorListener
    public final void onAnimationEnd$1(Animator animator) {
        this.$runnable.run();
    }
}
