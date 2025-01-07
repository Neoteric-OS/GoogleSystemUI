package com.android.systemui.statusbar.phone;

import com.android.systemui.statusbar.phone.CentralSurfacesImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class CentralSurfacesImpl$9$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ CentralSurfacesImpl.AnonymousClass9 f$0;

    public /* synthetic */ CentralSurfacesImpl$9$$ExternalSyntheticLambda0(CentralSurfacesImpl.AnonymousClass9 anonymousClass9, int i) {
        this.$r8$classId = i;
        this.f$0 = anonymousClass9;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0036, code lost:
    
        if (((com.android.systemui.flags.FeatureFlagsClassicRelease) r0.mFeatureFlags).isEnabled(com.android.systemui.flags.Flags.ZJ_285570694_LOCKSCREEN_TRANSITION_FROM_AOD) != false) goto L14;
     */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void run() {
        /*
            r5 = this;
            int r0 = r5.$r8$classId
            com.android.systemui.statusbar.phone.CentralSurfacesImpl$9 r5 = r5.f$0
            switch(r0) {
                case 0: goto L4a;
                case 1: goto L42;
                case 2: goto Lb;
                default: goto L7;
            }
        L7:
            r5.startLockscreenTransitionFromAod()
            return
        Lb:
            com.android.systemui.statusbar.phone.CentralSurfacesImpl r0 = com.android.systemui.statusbar.phone.CentralSurfacesImpl.this
            r1 = 1
            r0.mDeviceInteractive = r1
            com.android.systemui.shade.ShadeSurface r2 = r0.mShadeSurface
            r2.setWillPlayDelayedDozeAmountAnimation()
            com.android.systemui.statusbar.notification.NotificationWakeUpCoordinator r2 = r0.mWakeUpCoordinator
            r2.setWakingUp(r1)
            r2 = 0
            r0.updateIsKeyguard(r2)
            com.android.systemui.statusbar.phone.DozeParameters r3 = r0.mDozeParameters
            boolean r4 = r3.getAlwaysOn()
            if (r4 == 0) goto L39
            boolean r3 = r3.getDisplayNeedsBlanking()
            if (r3 != 0) goto L39
            com.android.systemui.flags.ReleasedFlag r3 = com.android.systemui.flags.Flags.ZJ_285570694_LOCKSCREEN_TRANSITION_FROM_AOD
            com.android.systemui.flags.FeatureFlagsClassic r4 = r0.mFeatureFlags
            com.android.systemui.flags.FeatureFlagsClassicRelease r4 = (com.android.systemui.flags.FeatureFlagsClassicRelease) r4
            boolean r3 = r4.isEnabled(r3)
            if (r3 == 0) goto L39
            goto L3a
        L39:
            r1 = r2
        L3a:
            r0.mShouldDelayLockscreenTransitionFromAod = r1
            if (r1 != 0) goto L41
            r5.startLockscreenTransitionFromAod()
        L41:
            return
        L42:
            com.android.systemui.statusbar.phone.CentralSurfacesImpl r5 = com.android.systemui.statusbar.phone.CentralSurfacesImpl.this
            com.android.systemui.statusbar.phone.CentralSurfacesCommandQueueCallbacks r5 = r5.mCommandQueueCallbacks
            r5.onEmergencyActionLaunchGestureDetected()
            return
        L4a:
            com.android.systemui.statusbar.phone.CentralSurfacesImpl r5 = com.android.systemui.statusbar.phone.CentralSurfacesImpl.this
            com.android.systemui.statusbar.phone.CentralSurfacesCommandQueueCallbacks r0 = r5.mCommandQueueCallbacks
            int r5 = r5.mLastCameraLaunchSource
            r0.onCameraLaunchGestureDetected(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.phone.CentralSurfacesImpl$9$$ExternalSyntheticLambda0.run():void");
    }
}
