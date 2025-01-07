package com.android.systemui.bouncer.ui.binder;

import android.view.ViewGroup;
import com.android.keyguard.KeyguardSecurityContainerController;
import com.android.systemui.bouncer.ui.viewmodel.KeyguardBouncerViewModel;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl;
import dagger.Lazy;
import kotlin.coroutines.EmptyCoroutineContext;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BouncerViewBinder {
    public final Lazy legacyBouncerDependencies;

    public BouncerViewBinder(Lazy lazy, Lazy lazy2) {
        this.legacyBouncerDependencies = lazy;
    }

    public final void bind(ViewGroup viewGroup) {
        LegacyBouncerDependencies legacyBouncerDependencies = (LegacyBouncerDependencies) this.legacyBouncerDependencies.get();
        KeyguardBouncerViewModel keyguardBouncerViewModel = legacyBouncerDependencies.viewModel;
        DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory = legacyBouncerDependencies.componentFactory;
        KeyguardSecurityContainerController keyguardSecurityContainerController = (KeyguardSecurityContainerController) new DaggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl(daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory.sysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory.sysUIGoogleSysUIComponentImpl, viewGroup).keyguardSecurityContainerControllerProvider.get();
        keyguardSecurityContainerController.init$9();
        SelectedUserInteractor selectedUserInteractor = legacyBouncerDependencies.selectedUserInteractor;
        RepeatWhenAttachedKt.repeatWhenAttached(viewGroup, EmptyCoroutineContext.INSTANCE, new KeyguardBouncerViewBinder$bind$1(keyguardBouncerViewModel, new KeyguardBouncerViewBinder$bind$delegate$1(keyguardSecurityContainerController, selectedUserInteractor), viewGroup, keyguardSecurityContainerController, legacyBouncerDependencies.bouncerLogger, legacyBouncerDependencies.bouncerMessageInteractor, legacyBouncerDependencies.messageAreaControllerFactory, legacyBouncerDependencies.primaryBouncerToGoneTransitionViewModel, selectedUserInteractor, null));
    }
}
