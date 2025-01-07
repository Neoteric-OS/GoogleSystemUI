package com.android.wm.shell.bubbles.animation;

import android.view.View;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class PhysicsAnimationLayout$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ PhysicsAnimationLayout f$0;
    public final /* synthetic */ View f$1;

    public /* synthetic */ PhysicsAnimationLayout$$ExternalSyntheticLambda0(PhysicsAnimationLayout physicsAnimationLayout, View view) {
        this.f$0 = physicsAnimationLayout;
        this.f$1 = view;
    }

    @Override // java.lang.Runnable
    public final void run() {
        PhysicsAnimationLayout physicsAnimationLayout = this.f$0;
        View view = this.f$1;
        physicsAnimationLayout.cancelAnimationsOnView(view);
        physicsAnimationLayout.removeTransientView(view);
    }
}
