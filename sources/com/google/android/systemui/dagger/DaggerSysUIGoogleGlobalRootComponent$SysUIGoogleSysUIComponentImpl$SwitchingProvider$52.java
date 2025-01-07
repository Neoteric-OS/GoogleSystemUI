package com.google.android.systemui.dagger;

import android.app.ActivityManager;
import android.app.AppGlobals;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IDeviceIdleController;
import android.os.UserHandle;
import android.service.quicksettings.IQSService;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.qs.external.PackageManagerAdapter;
import com.android.systemui.qs.external.TileLifecycleManager;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.time.SystemClock;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$52 {
    public final /* synthetic */ DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider this$0;

    public DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$52(DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider) {
        this.this$0 = switchingProvider;
    }

    public final TileLifecycleManager create(Intent intent, UserHandle userHandle) {
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider = this.this$0;
        Handler handler = (Handler) switchingProvider.sysUIGoogleGlobalRootComponentImpl.provideMainHandlerProvider.get();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = switchingProvider.sysUIGoogleGlobalRootComponentImpl;
        Context context = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl;
        IQSService iQSService = (IQSService) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.tileServicesProvider.get();
        Context context2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context;
        PackageManagerAdapter packageManagerAdapter = new PackageManagerAdapter();
        packageManagerAdapter.mPackageManager = context2.getPackageManager();
        packageManagerAdapter.mIPackageManager = AppGlobals.getPackageManager();
        return new TileLifecycleManager(handler, context, iQSService, packageManagerAdapter, (BroadcastDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.broadcastDispatcherProvider.get(), intent, userHandle, (ActivityManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideActivityManagerProvider.get(), (IDeviceIdleController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideDeviceIdleControllerProvider.get(), (DelayableExecutor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBackgroundDelayableExecutorProvider.get(), (SystemClock) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemClockProvider.get());
    }
}
