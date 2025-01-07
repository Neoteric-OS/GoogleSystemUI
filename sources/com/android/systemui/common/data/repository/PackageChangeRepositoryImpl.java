package com.android.systemui.common.data.repository;

import android.content.Context;
import android.os.Handler;
import android.os.UserHandle;
import com.android.systemui.util.time.SystemClock;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$11;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PackageChangeRepositoryImpl implements PackageChangeRepository {
    public final Lazy monitor$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.common.data.repository.PackageChangeRepositoryImpl$monitor$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$11 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$11 = PackageChangeRepositoryImpl.this.monitorFactory;
            UserHandle userHandle = UserHandle.ALL;
            daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$11.getClass();
            DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$11.this$0;
            CoroutineDispatcher coroutineDispatcher = (CoroutineDispatcher) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl).bgDispatcherProvider.get();
            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl;
            Handler handler = (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBgHandlerProvider.get();
            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = switchingProvider.sysUIGoogleGlobalRootComponentImpl;
            return new PackageUpdateMonitor(userHandle, coroutineDispatcher, handler, (Context) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideApplicationContextProvider.get(), (CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get(), (PackageUpdateLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.packageUpdateLoggerProvider.get(), (SystemClock) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemClockProvider.get());
        }
    });
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$11 monitorFactory;
    public final ReadonlyStateFlow packageInstallSessionsForPrimaryUser;

    public PackageChangeRepositoryImpl(PackageInstallerMonitor packageInstallerMonitor, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$11 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$11) {
        this.monitorFactory = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$11;
        this.packageInstallSessionsForPrimaryUser = packageInstallerMonitor.installSessionsForPrimaryUser;
    }

    public final PackageChangeRepositoryImpl$packageChanged$$inlined$filter$1 packageChanged(UserHandle userHandle) {
        PackageUpdateMonitor packageUpdateMonitor = (PackageUpdateMonitor) this.monitor$delegate.getValue();
        return new PackageChangeRepositoryImpl$packageChanged$$inlined$filter$1(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(packageUpdateMonitor._packageChanged, new PackageUpdateMonitor$packageChanged$1(2, packageUpdateMonitor.logger, PackageUpdateLogger.class, "logChange", "logChange(Lcom/android/systemui/common/shared/model/PackageChangeModel;)V", 4), 0), userHandle);
    }
}
