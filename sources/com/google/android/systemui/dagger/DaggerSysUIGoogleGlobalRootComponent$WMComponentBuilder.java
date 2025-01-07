package com.google.android.systemui.dagger;

import android.widget.FrameLayout;
import com.android.systemui.statusbar.policy.KeyguardUserSwitcherView;
import dagger.internal.DoubleCheck;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DaggerSysUIGoogleGlobalRootComponent$WMComponentBuilder {
    public Object setShellMainThread;
    public final Object sysUIGoogleGlobalRootComponentImpl;

    public DaggerSysUIGoogleGlobalRootComponent$WMComponentBuilder(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl) {
        this.sysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
    }

    public DaggerSysUIGoogleGlobalRootComponent$WMComponentBuilder(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, FrameLayout frameLayout) {
        this.sysUIGoogleGlobalRootComponentImpl = frameLayout;
        this.setShellMainThread = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusViewComponentImpl$SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 2));
    }

    public DaggerSysUIGoogleGlobalRootComponent$WMComponentBuilder(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, KeyguardUserSwitcherView keyguardUserSwitcherView) {
        this.sysUIGoogleGlobalRootComponentImpl = keyguardUserSwitcherView;
        this.setShellMainThread = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusViewComponentImpl$SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 3));
    }
}
