package com.android.systemui.statusbar.phone;

import android.os.Trace;
import android.util.ArraySet;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.plugins.OverlayPlugin;
import com.android.systemui.shade.NotificationShadeWindowControllerImpl;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class CentralSurfacesImpl$3$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ CentralSurfacesImpl$3$$ExternalSyntheticLambda0(int i, Object obj, Object obj2) {
        this.$r8$classId = i;
        this.f$0 = obj;
        this.f$1 = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                CentralSurfacesImpl.AnonymousClass3 anonymousClass3 = (CentralSurfacesImpl.AnonymousClass3) this.f$0;
                OverlayPlugin overlayPlugin = (OverlayPlugin) this.f$1;
                CentralSurfacesImpl centralSurfacesImpl = (CentralSurfacesImpl) anonymousClass3.this$0;
                overlayPlugin.setup(((NotificationShadeWindowControllerImpl) centralSurfacesImpl.mNotificationShadeWindowController).mWindowRootView, centralSurfacesImpl.getNavigationBarView(), new CentralSurfacesImpl.AnonymousClass3(anonymousClass3, overlayPlugin), centralSurfacesImpl.mDozeParameters);
                break;
            case 1:
                CentralSurfacesImpl.AnonymousClass3 anonymousClass32 = (CentralSurfacesImpl.AnonymousClass3) this.f$0;
                ((ArraySet) anonymousClass32.mOverlays).remove((OverlayPlugin) this.f$1);
                ((NotificationShadeWindowControllerImpl) ((CentralSurfacesImpl) anonymousClass32.this$0).mNotificationShadeWindowController).setForcePluginOpen(anonymousClass32, ((ArraySet) anonymousClass32.mOverlays).size() != 0);
                break;
            default:
                CentralSurfacesImpl.AnonymousClass4 anonymousClass4 = (CentralSurfacesImpl.AnonymousClass4) this.f$0;
                ActivityTransitionAnimator.Runner runner = (ActivityTransitionAnimator.Runner) this.f$1;
                KeyguardViewMediator keyguardViewMediator = CentralSurfacesImpl.this.mKeyguardViewMediator;
                if (keyguardViewMediator.mKeyguardDonePending) {
                    keyguardViewMediator.mKeyguardExitAnimationRunner = runner;
                    KeyguardViewMediator.AnonymousClass4 anonymousClass42 = keyguardViewMediator.mViewMediatorCallback;
                    anonymousClass42.getClass();
                    Trace.beginSection("KeyguardViewMediator.mViewMediatorCallback#readyForKeyguardDone");
                    KeyguardViewMediator keyguardViewMediator2 = KeyguardViewMediator.this;
                    if (keyguardViewMediator2.mKeyguardDonePending) {
                        keyguardViewMediator2.mKeyguardDonePending = false;
                        keyguardViewMediator2.tryKeyguardDone();
                    }
                    Trace.endSection();
                    break;
                }
                break;
        }
    }
}
