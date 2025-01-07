package com.android.systemui.user.ui.dialog;

import android.app.Dialog;
import com.android.systemui.CoreStartable;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import dagger.Lazy;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UserSwitcherDialogCoordinator implements CoreStartable {
    public final Lazy activityStarter;
    public final Lazy applicationScope;
    public final Lazy context;
    public Dialog currentDialog;
    public final Lazy dialogTransitionAnimator;
    public final Lazy eventLogger;
    public final Lazy falsingCollector;
    public final Lazy falsingManager;
    public final Lazy interactor;
    public final DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider userDetailAdapterProvider;
    public final Lazy userSwitcherViewModel;

    public UserSwitcherDialogCoordinator(Lazy lazy, Lazy lazy2, Lazy lazy3, Lazy lazy4, Lazy lazy5, Lazy lazy6, DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider, Lazy lazy7, Lazy lazy8, Lazy lazy9, Lazy lazy10) {
        this.context = lazy;
        this.applicationScope = lazy2;
        this.falsingManager = lazy3;
        this.dialogTransitionAnimator = lazy5;
        this.interactor = lazy6;
        this.userDetailAdapterProvider = switchingProvider;
        this.eventLogger = lazy7;
        this.activityStarter = lazy8;
        this.falsingCollector = lazy9;
        this.userSwitcherViewModel = lazy10;
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        Lazy lazy = this.applicationScope;
        BuildersKt.launch$default((CoroutineScope) lazy.get(), null, null, new UserSwitcherDialogCoordinator$startHandlingDialogShowRequests$1(this, null), 3);
        BuildersKt.launch$default((CoroutineScope) lazy.get(), null, null, new UserSwitcherDialogCoordinator$startHandlingDialogDismissRequests$1(this, null), 3);
    }
}
