package com.google.android.systemui.dagger;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import com.android.systemui.settings.DisplayTracker;
import com.android.systemui.unfold.FullscreenLightRevealAnimationController;
import com.android.systemui.unfold.updates.RotationChangeProvider;
import com.android.systemui.util.concurrency.ThreadFactoryImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl;
import java.util.concurrent.Executor;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl$SwitchingProvider$1 {
    public final /* synthetic */ DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider this$0;

    public DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl$SwitchingProvider$1(DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider switchingProvider) {
        this.this$0 = switchingProvider;
    }

    public final FullscreenLightRevealAnimationController create(Function1 function1, Function1 function12, String str) {
        DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider switchingProvider = this.this$0;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = switchingProvider.sysUIGoogleGlobalRootComponentImpl;
        Context context = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context;
        DisplayManager displayManager = (DisplayManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideDisplayManagerProvider.get();
        ThreadFactoryImpl threadFactoryImpl = new ThreadFactoryImpl();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2 = switchingProvider.sysUIGoogleGlobalRootComponentImpl;
        Handler handler = (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2.unfoldBgProgressHandlerProvider.get();
        RotationChangeProvider rotationChangeProvider = (RotationChangeProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2.provideBgRotationChangeProvider.get();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = switchingProvider.sysUIGoogleSysUIComponentImpl;
        return new FullscreenLightRevealAnimationController(context, displayManager, threadFactoryImpl, handler, rotationChangeProvider, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.setDisplayAreaHelper, (DisplayTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideDisplayTrackerProvider.get(), (CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgApplicationScopeProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2.provideMainExecutorProvider.get(), function1, function12, str);
    }
}
