package com.android.systemui.animation;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class DialogTransitionAnimator$createActivityTransitionController$1$enableDialogDismiss$1 implements Runnable {
    public final /* synthetic */ AnimatedDialog $tmp0;

    public DialogTransitionAnimator$createActivityTransitionController$1$enableDialogDismiss$1(AnimatedDialog animatedDialog) {
        this.$tmp0 = animatedDialog;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.$tmp0.onDialogDismissed();
    }
}
