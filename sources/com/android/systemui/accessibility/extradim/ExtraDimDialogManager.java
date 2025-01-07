package com.android.systemui.accessibility.extradim;

import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.plugins.ActivityStarter;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ExtraDimDialogManager {
    public final DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider extraDimDialogDelegateProvider;
    public final ActivityStarter mActivityStarter;

    public ExtraDimDialogManager(DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider, ActivityStarter activityStarter, DialogTransitionAnimator dialogTransitionAnimator) {
        this.mActivityStarter = activityStarter;
    }
}
