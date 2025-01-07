package com.android.systemui.dreams;

import androidx.lifecycle.Lifecycle;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class DreamOverlayService$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ DreamOverlayService f$0;

    public /* synthetic */ DreamOverlayService$$ExternalSyntheticLambda1(DreamOverlayService dreamOverlayService, int i) {
        this.$r8$classId = i;
        this.f$0 = dreamOverlayService;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        DreamOverlayService dreamOverlayService = this.f$0;
        switch (i) {
            case 0:
                boolean z = DreamOverlayService.DEBUG;
                dreamOverlayService.mLifecycleRegistry.setCurrentState(Lifecycle.State.CREATED);
                break;
            default:
                boolean z2 = DreamOverlayService.DEBUG;
                dreamOverlayService.mLifecycleRegistry.setCurrentState(Lifecycle.State.DESTROYED);
                dreamOverlayService.mDestroyed = true;
                dreamOverlayService.mResetHandler.reset(new DreamOverlayService$$ExternalSyntheticLambda2(), "destroying");
                break;
        }
    }
}
