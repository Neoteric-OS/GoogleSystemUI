package com.android.systemui.bluetooth;

import com.android.systemui.animation.DialogTransitionAnimator;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$13;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BroadcastDialogController {
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$13 mBroadcastDialogFactory;
    public final DialogTransitionAnimator mDialogTransitionAnimator;

    public BroadcastDialogController(DialogTransitionAnimator dialogTransitionAnimator, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$13 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$13) {
        this.mDialogTransitionAnimator = dialogTransitionAnimator;
        this.mBroadcastDialogFactory = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$13;
    }
}
