package com.android.wm.shell.back;

import com.android.wm.shell.back.BackAnimationController;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BackAnimationController$BackAnimationImpl$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ BackAnimationController.BackAnimationImpl f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ BackAnimationController$BackAnimationImpl$$ExternalSyntheticLambda3(BackAnimationController.BackAnimationImpl backAnimationImpl, boolean z) {
        this.f$0 = backAnimationImpl;
        this.f$1 = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        BackAnimationController.BackAnimationImpl backAnimationImpl = this.f$0;
        BackAnimationController.this.setTriggerBack(this.f$1);
    }
}
