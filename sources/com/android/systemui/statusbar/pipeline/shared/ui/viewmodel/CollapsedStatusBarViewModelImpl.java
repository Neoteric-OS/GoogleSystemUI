package com.android.systemui.statusbar.pipeline.shared.ui.viewmodel;

import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.keyguard.shared.model.Edge;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.scene.domain.interactor.SceneContainerOcclusionInteractor;
import com.android.systemui.scene.domain.interactor.SceneInteractor;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.shade.domain.interactor.ShadeInteractorImpl;
import com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel;
import com.android.systemui.statusbar.notification.domain.interactor.ActiveNotificationsInteractor;
import com.android.systemui.statusbar.phone.domain.interactor.LightsOutInteractor;
import com.android.systemui.statusbar.pipeline.shared.domain.interactor.CollapsedStatusBarInteractor;
import com.android.systemui.statusbar.pipeline.shared.domain.interactor.CollapsedStatusBarInteractor$special$$inlined$map$1;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StartedWhileSubscribed;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CollapsedStatusBarViewModelImpl implements CollapsedStatusBarViewModel {
    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 isClockVisible;
    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 isHomeScreenStatusBarAllowed;
    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 isHomeScreenStatusBarAllowedLegacy;
    public final ReadonlyStateFlow isHomeStatusBarAllowedByScene;
    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 isNotificationIconContainerVisible;
    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 isSystemInfoVisible;
    public final ReadonlyStateFlow isTransitioningFromLockscreenToOccluded;
    public final ReadonlyStateFlow ongoingActivityChips;
    public final ReadonlyStateFlow primaryOngoingActivityChip;
    public final CollapsedStatusBarViewModelImpl$special$$inlined$map$1 transitionFromLockscreenToDreamStartedEvent;

    public CollapsedStatusBarViewModelImpl(CollapsedStatusBarInteractor collapsedStatusBarInteractor, LightsOutInteractor lightsOutInteractor, ActiveNotificationsInteractor activeNotificationsInteractor, KeyguardTransitionInteractor keyguardTransitionInteractor, SceneInteractor sceneInteractor, SceneContainerOcclusionInteractor sceneContainerOcclusionInteractor, ShadeInteractor shadeInteractor, OngoingActivityChipsViewModel ongoingActivityChipsViewModel, CoroutineScope coroutineScope) {
        Edge.Companion companion = Edge.Companion;
        KeyguardState keyguardState = KeyguardState.LOCKSCREEN;
        Edge.StateToState stateToState = new Edge.StateToState(keyguardState, KeyguardState.OCCLUDED);
        String str = KeyguardTransitionInteractor.TAG;
        Flow isInTransition = keyguardTransitionInteractor.isInTransition(stateToState, null);
        StartedWhileSubscribed WhileSubscribed$default = SharingStarted.Companion.WhileSubscribed$default(3);
        Boolean bool = Boolean.FALSE;
        this.isTransitioningFromLockscreenToOccluded = FlowKt.stateIn(isInTransition, coroutineScope, WhileSubscribed$default, bool);
        this.transitionFromLockscreenToDreamStartedEvent = new CollapsedStatusBarViewModelImpl$special$$inlined$map$1(new CollapsedStatusBarViewModelImpl$special$$inlined$map$1(keyguardTransitionInteractor.transition(new Edge.StateToState(keyguardState, KeyguardState.DREAMING)), 1), 0);
        this.primaryOngoingActivityChip = ongoingActivityChipsViewModel.primaryChip;
        ReadonlyStateFlow readonlyStateFlow = ongoingActivityChipsViewModel.chips;
        FlowKt.stateIn(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(sceneInteractor.currentScene, sceneContainerOcclusionInteractor.invisibleDueToOcclusion, new CollapsedStatusBarViewModelImpl$isHomeStatusBarAllowedByScene$1(3, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), bool);
        FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 flowKt__ZipKt$combine$$inlined$unsafeFlow$1 = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(keyguardTransitionInteractor.currentKeyguardState, ((ShadeInteractorImpl) shadeInteractor).isAnyFullyExpanded, new CollapsedStatusBarViewModelImpl$isHomeScreenStatusBarAllowedLegacy$1(3, null));
        CollapsedStatusBarInteractor$special$$inlined$map$1 collapsedStatusBarInteractor$special$$inlined$map$1 = collapsedStatusBarInteractor.visibilityViaDisableFlags;
        new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(flowKt__ZipKt$combine$$inlined$unsafeFlow$1, collapsedStatusBarInteractor$special$$inlined$map$1, new CollapsedStatusBarViewModelImpl$isClockVisible$1(this, null));
        new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(flowKt__ZipKt$combine$$inlined$unsafeFlow$1, collapsedStatusBarInteractor$special$$inlined$map$1, new CollapsedStatusBarViewModelImpl$isNotificationIconContainerVisible$1(this, null));
        new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(flowKt__ZipKt$combine$$inlined$unsafeFlow$1, collapsedStatusBarInteractor$special$$inlined$map$1, new CollapsedStatusBarViewModelImpl$isSystemInfoVisible$1(this, null));
    }
}
