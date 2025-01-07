package com.android.systemui.bouncer.ui.viewmodel;

import com.android.systemui.bouncer.domain.interactor.PrimaryBouncerInteractor;
import com.android.systemui.bouncer.domain.interactor.PrimaryBouncerInteractor$special$$inlined$filter$1;
import com.android.systemui.bouncer.domain.interactor.PrimaryBouncerInteractor$special$$inlined$map$1;
import com.android.systemui.bouncer.ui.BouncerViewImpl;
import kotlinx.coroutines.flow.FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardBouncerViewModel {
    public final StateFlow bouncerExpansionAmount;
    public final FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1 bouncerShowMessage;
    public final PrimaryBouncerInteractor interactor;
    public final PrimaryBouncerInteractor$special$$inlined$map$1 isInteractable;
    public final ReadonlyStateFlow isShowing;
    public final FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1 keyguardAuthenticated;
    public final FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1 keyguardPosition;
    public final FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1 startDisappearAnimation;
    public final PrimaryBouncerInteractor$special$$inlined$map$1 startingToHide;
    public final PrimaryBouncerInteractor$special$$inlined$filter$1 updateResources;
    public final BouncerViewImpl view;

    public KeyguardBouncerViewModel(BouncerViewImpl bouncerViewImpl, PrimaryBouncerInteractor primaryBouncerInteractor) {
        this.view = bouncerViewImpl;
        this.interactor = primaryBouncerInteractor;
        this.bouncerExpansionAmount = primaryBouncerInteractor.panelExpansionAmount;
        this.isInteractable = primaryBouncerInteractor.isInteractable;
        this.isShowing = primaryBouncerInteractor.isShowing;
        this.startingToHide = primaryBouncerInteractor.startingToHide;
        this.startDisappearAnimation = primaryBouncerInteractor.startingDisappearAnimation;
        this.keyguardPosition = primaryBouncerInteractor.keyguardPosition;
        this.updateResources = primaryBouncerInteractor.resourceUpdateRequests;
        this.bouncerShowMessage = primaryBouncerInteractor.showMessage;
        this.keyguardAuthenticated = primaryBouncerInteractor.keyguardAuthenticatedBiometrics;
    }
}
