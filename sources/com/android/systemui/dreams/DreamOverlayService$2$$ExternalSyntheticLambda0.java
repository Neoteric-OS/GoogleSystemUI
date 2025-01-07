package com.android.systemui.dreams;

import com.android.systemui.dreams.DreamOverlayService;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class DreamOverlayService$2$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId = 0;
    public final /* synthetic */ Object f$0;
    public final /* synthetic */ Boolean f$1;

    public /* synthetic */ DreamOverlayService$2$$ExternalSyntheticLambda0(DreamOverlayService.AnonymousClass2 anonymousClass2, Boolean bool) {
        this.f$0 = anonymousClass2;
        this.f$1 = bool;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                DreamOverlayService.AnonymousClass2 anonymousClass2 = (DreamOverlayService.AnonymousClass2) this.f$0;
                Boolean bool = this.f$1;
                if (anonymousClass2.this$0.mCommunalVisible != bool.booleanValue()) {
                    anonymousClass2.this$0.mCommunalVisible = bool.booleanValue();
                    anonymousClass2.this$0.updateLifecycleStateLocked();
                    break;
                }
                break;
            default:
                DreamOverlayService.AnonymousClass2 anonymousClass22 = (DreamOverlayService.AnonymousClass2) this.f$0;
                Boolean bool2 = this.f$1;
                if (anonymousClass22.this$0.mBouncerShowing != bool2.booleanValue()) {
                    anonymousClass22.this$0.mBouncerShowing = bool2.booleanValue();
                    anonymousClass22.this$0.updateLifecycleStateLocked();
                    anonymousClass22.this$0.updateGestureBlockingLocked();
                    break;
                }
                break;
        }
    }

    public /* synthetic */ DreamOverlayService$2$$ExternalSyntheticLambda0(DreamOverlayService.AnonymousClass2 anonymousClass2, Boolean bool, byte b) {
        this.f$0 = anonymousClass2;
        this.f$1 = bool;
    }
}
