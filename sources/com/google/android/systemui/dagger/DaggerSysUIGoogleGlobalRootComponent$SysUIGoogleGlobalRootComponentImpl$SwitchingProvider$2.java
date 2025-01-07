package com.google.android.systemui.dagger;

import com.android.systemui.unfold.UnfoldTransitionModule;
import com.android.systemui.unfold.util.ATraceLoggerTransitionProgressListener;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$2 {
    public final /* synthetic */ DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider this$0;

    public DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$2(DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider switchingProvider) {
        this.this$0 = switchingProvider;
    }

    public final ATraceLoggerTransitionProgressListener create(String str) {
        UnfoldTransitionModule unfoldTransitionModule = ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl) this.this$0.keyguardStatusBarViewComponentImpl).unfoldTransitionModule;
        return new ATraceLoggerTransitionProgressListener(str);
    }
}
