package com.android.systemui.statusbar.core;

import com.android.systemui.statusbar.phone.CentralSurfacesImpl$$ExternalSyntheticLambda0;
import com.android.systemui.statusbar.window.StatusBarWindowControllerImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StatusBarInitializerImpl {
    public final DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider collapsedStatusBarFragmentProvider;
    public final Set creationListeners;
    public CentralSurfacesImpl$$ExternalSyntheticLambda0 statusBarViewUpdatedListener;
    public final StatusBarWindowControllerImpl windowController;

    public StatusBarInitializerImpl(StatusBarWindowControllerImpl statusBarWindowControllerImpl, DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider, Set set) {
        this.windowController = statusBarWindowControllerImpl;
        this.collapsedStatusBarFragmentProvider = switchingProvider;
        this.creationListeners = set;
    }
}
