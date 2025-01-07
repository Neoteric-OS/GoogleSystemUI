package com.android.systemui.unfold;

import android.R;
import android.view.Display;
import com.android.systemui.unfold.UnfoldTransitionWallpaperController.TransitionListener;
import com.android.systemui.unfold.updates.DeviceFoldStateProvider;
import com.android.systemui.unfold.updates.RotationChangeProvider;
import com.android.systemui.unfold.updates.RotationChangeProvider$addCallback$1;
import com.android.systemui.unfold.util.NaturalRotationUnfoldProgressProvider;
import com.android.systemui.unfold.util.NaturalRotationUnfoldProgressProvider$rotationListener$1;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl;
import java.util.Iterator;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UnfoldInitializationStartable$start$1 implements Consumer {
    public static final UnfoldInitializationStartable$start$1 INSTANCE = new UnfoldInitializationStartable$start$1(0);
    public static final UnfoldInitializationStartable$start$1 INSTANCE$1 = new UnfoldInitializationStartable$start$1(1);
    public static final UnfoldInitializationStartable$start$1 INSTANCE$2 = new UnfoldInitializationStartable$start$1(2);
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ UnfoldInitializationStartable$start$1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl) obj;
                Iterator it = daggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl.getFullScreenLightRevealAnimations().iterator();
                while (it.hasNext()) {
                    ((FullscreenLightRevealAnimation) it.next()).init();
                }
                UnfoldTransitionWallpaperController unfoldTransitionWallpaperController = (UnfoldTransitionWallpaperController) daggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl.unfoldTransitionWallpaperControllerProvider.get();
                unfoldTransitionWallpaperController.getClass();
                unfoldTransitionWallpaperController.unfoldTransitionProgressProvider.addCallback(unfoldTransitionWallpaperController.new TransitionListener());
                NaturalRotationUnfoldProgressProvider naturalRotationUnfoldProgressProvider = daggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl.p2;
                RotationChangeProvider rotationChangeProvider = naturalRotationUnfoldProgressProvider.rotationChangeProvider;
                rotationChangeProvider.getClass();
                NaturalRotationUnfoldProgressProvider$rotationListener$1 naturalRotationUnfoldProgressProvider$rotationListener$1 = naturalRotationUnfoldProgressProvider.rotationListener;
                rotationChangeProvider.bgHandler.post(new RotationChangeProvider$addCallback$1(rotationChangeProvider, naturalRotationUnfoldProgressProvider$rotationListener$1));
                Display display = naturalRotationUnfoldProgressProvider.context.getDisplay();
                if (display != null) {
                    naturalRotationUnfoldProgressProvider$rotationListener$1.onRotationChanged(display.getRotation());
                }
                UnfoldLatencyTracker unfoldLatencyTracker = (UnfoldLatencyTracker) daggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl.unfoldLatencyTrackerProvider.get();
                if (unfoldLatencyTracker.context.getResources().getIntArray(R.array.config_fontManagerServiceCerts).length != 0) {
                    unfoldLatencyTracker.deviceStateManager.registerCallback(unfoldLatencyTracker.uiBgExecutor, unfoldLatencyTracker.foldStateListener);
                    unfoldLatencyTracker.screenLifecycle.mObservers.add(unfoldLatencyTracker);
                    if (unfoldLatencyTracker.transitionProgressProvider.isPresent()) {
                        ((UnfoldTransitionProgressProvider) unfoldLatencyTracker.transitionProgressProvider.get()).addCallback(unfoldLatencyTracker);
                        break;
                    }
                }
                break;
            case 1:
                FoldStateLoggingProviderImpl foldStateLoggingProviderImpl = (FoldStateLoggingProviderImpl) obj;
                DeviceFoldStateProvider deviceFoldStateProvider = foldStateLoggingProviderImpl.foldStateProvider;
                deviceFoldStateProvider.addCallback(foldStateLoggingProviderImpl);
                deviceFoldStateProvider.start();
                break;
            default:
                FoldStateLogger foldStateLogger = (FoldStateLogger) obj;
                foldStateLogger.foldStateLoggingProvider.addCallback(foldStateLogger);
                break;
        }
    }
}
