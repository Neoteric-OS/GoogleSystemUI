package com.android.systemui.unfold;

import android.content.ContentResolver;
import android.os.Handler;
import com.android.systemui.unfold.config.ResourceUnfoldTransitionConfig;
import com.android.systemui.unfold.progress.PhysicsBasedUnfoldTransitionProgressProvider;
import com.android.systemui.unfold.updates.DeviceFoldStateProvider;
import com.android.systemui.unfold.util.ATraceLoggerTransitionProgressListener;
import com.android.systemui.unfold.util.ScaleAwareTransitionProgressProvider;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$1;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$3;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$4;
import java.util.Optional;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UnfoldSharedInternalModule {
    public static Optional createOptionalUnfoldTransitionProgressProvider(ResourceUnfoldTransitionConfig resourceUnfoldTransitionConfig, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$1 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$1, ATraceLoggerTransitionProgressListener aTraceLoggerTransitionProgressListener, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$3 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$3, DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider switchingProvider, DeviceFoldStateProvider deviceFoldStateProvider, Handler handler) {
        Object obj;
        if (!((Boolean) resourceUnfoldTransitionConfig.isEnabled$delegate.getValue()).booleanValue()) {
            return Optional.empty();
        }
        if (((Boolean) resourceUnfoldTransitionConfig.isHingeAngleEnabled$delegate.getValue()).booleanValue()) {
            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$3.this$0.keyguardStatusBarViewComponentImpl;
            obj = new PhysicsBasedUnfoldTransitionProgressProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$4) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.factoryProvider3.get(), deviceFoldStateProvider, handler);
        } else {
            obj = switchingProvider.get();
        }
        Intrinsics.checkNotNull(obj);
        ScaleAwareTransitionProgressProvider scaleAwareTransitionProgressProvider = new ScaleAwareTransitionProgressProvider((UnfoldTransitionProgressProvider) obj, (ContentResolver) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$1.this$0.keyguardStatusBarViewComponentImpl).provideContentResolverProvider.get());
        scaleAwareTransitionProgressProvider.scopedUnfoldTransitionProgressProvider.listeners.add(aTraceLoggerTransitionProgressListener);
        return Optional.of(scaleAwareTransitionProgressProvider);
    }
}
