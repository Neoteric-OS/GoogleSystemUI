package com.android.systemui.dreams.homecontrols.domain.interactor;

import android.app.DreamManager;
import android.content.ComponentName;
import android.os.PowerManager;
import com.android.systemui.common.domain.interactor.PackageChangeInteractor;
import com.android.systemui.controls.dagger.ControlsComponent;
import com.android.systemui.controls.management.ControlsListingController;
import com.android.systemui.controls.panels.AuthorizedPanelsRepositoryImpl;
import com.android.systemui.controls.panels.SelectedComponentRepositoryImpl;
import com.android.systemui.user.data.repository.UserRepositoryImpl;
import com.android.systemui.user.data.repository.UserRepositoryImpl$special$$inlined$map$2;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.EmptyFlow;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharedFlowImpl;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class HomeControlsComponentInteractor {
    public static final /* synthetic */ int $r8$clinit = 0;
    public static final long MAX_UPDATE_CORRELATION_DELAY;
    public final ChannelFlowTransformLatest allAuthorizedPanels;
    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 allAvailableAndAuthorizedPanels;
    public final ControlsListingController controlsListingController;
    public final DreamManager dreamManager;
    public final PackageChangeInteractor packageChangeInteractor;
    public final ReadonlyStateFlow panelComponent;
    public final PowerManager powerManager;
    public final SelectedComponentRepositoryImpl selectedComponentRepository;
    public final HomeControlsComponentInteractor$special$$inlined$map$1 selectedPanel;
    public final SystemClock systemClock;
    public final SharedFlowImpl taskFragmentFinished;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PanelComponent {
        public final ComponentName componentName;
        public final ComponentName panelActivity;

        public PanelComponent(ComponentName componentName, ComponentName componentName2) {
            this.componentName = componentName;
            this.panelActivity = componentName2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof PanelComponent)) {
                return false;
            }
            PanelComponent panelComponent = (PanelComponent) obj;
            return Intrinsics.areEqual(this.componentName, panelComponent.componentName) && Intrinsics.areEqual(this.panelActivity, panelComponent.panelActivity);
        }

        public final int hashCode() {
            return this.panelActivity.hashCode() + (this.componentName.hashCode() * 31);
        }

        public final String toString() {
            return "PanelComponent(componentName=" + this.componentName + ", panelActivity=" + this.panelActivity + ")";
        }
    }

    static {
        int i = Duration.$r8$clinit;
        MAX_UPDATE_CORRELATION_DELAY = DurationKt.toDuration(500, DurationUnit.MILLISECONDS);
    }

    public HomeControlsComponentInteractor(SelectedComponentRepositoryImpl selectedComponentRepositoryImpl, ControlsComponent controlsComponent, AuthorizedPanelsRepositoryImpl authorizedPanelsRepositoryImpl, UserRepositoryImpl userRepositoryImpl, PackageChangeInteractor packageChangeInteractor, SystemClock systemClock, PowerManager powerManager, DreamManager dreamManager, CoroutineScope coroutineScope) {
        Flow flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
        this.selectedComponentRepository = selectedComponentRepositoryImpl;
        this.packageChangeInteractor = packageChangeInteractor;
        this.systemClock = systemClock;
        this.powerManager = powerManager;
        this.dreamManager = dreamManager;
        ControlsListingController controlsListingController = (ControlsListingController) controlsComponent.controlsListingController.orElse(null);
        this.controlsListingController = controlsListingController;
        HomeControlsComponentInteractor$special$$inlined$flatMapLatest$1 homeControlsComponentInteractor$special$$inlined$flatMapLatest$1 = new HomeControlsComponentInteractor$special$$inlined$flatMapLatest$1(this, null);
        UserRepositoryImpl$special$$inlined$map$2 userRepositoryImpl$special$$inlined$map$2 = userRepositoryImpl.selectedUserInfo;
        HomeControlsComponentInteractor$special$$inlined$map$1 homeControlsComponentInteractor$special$$inlined$map$1 = new HomeControlsComponentInteractor$special$$inlined$map$1(FlowKt.transformLatest(userRepositoryImpl$special$$inlined$map$2, homeControlsComponentInteractor$special$$inlined$flatMapLatest$1), 0);
        ChannelFlowTransformLatest transformLatest = FlowKt.transformLatest(userRepositoryImpl$special$$inlined$map$2, new HomeControlsComponentInteractor$special$$inlined$flatMapLatest$2(null, authorizedPanelsRepositoryImpl));
        if (controlsListingController == null) {
            flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 = EmptyFlow.INSTANCE;
        } else {
            flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 = new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new HomeControlsComponentInteractor$allAvailableServices$2(this, null), FlowConflatedKt.conflatedCallbackFlow(new HomeControlsComponentInteractor$allAvailableServices$1(this, null)));
        }
        this.panelComponent = FlowKt.stateIn(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1, transformLatest, new HomeControlsComponentInteractor$allAvailableAndAuthorizedPanels$1(3, null)), homeControlsComponentInteractor$special$$inlined$map$1, new HomeControlsComponentInteractor$panelComponent$1(3, null)), coroutineScope, SharingStarted.Companion.Eagerly, null);
        this.taskFragmentFinished = SharedFlowKt.MutableSharedFlow$default(1, 0, BufferOverflow.DROP_OLDEST, 2);
    }

    public final Object monitorUpdatesAndRestart(Continuation continuation) {
        SharedFlowImpl sharedFlowImpl = this.taskFragmentFinished;
        sharedFlowImpl.resetReplayCache();
        Object collect = com.android.systemui.util.kotlin.FlowKt.sample(new FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1(com.android.systemui.util.kotlin.FlowKt.pairwiseBy(new HomeControlsComponentInteractor$special$$inlined$map$1(FlowKt.transformLatest(this.panelComponent, new HomeControlsComponentInteractor$monitorUpdatesAndRestart$$inlined$flatMapLatest$1(this, null)), 1), HomeControlsComponentInteractor$monitorUpdatesAndRestart$4.INSTANCE)), sharedFlowImpl, HomeControlsComponentInteractor$monitorUpdatesAndRestart$6.INSTANCE).collect(new HomeControlsComponentInteractor$monitorUpdatesAndRestart$$inlined$filter$2$2(new FlowCollector() { // from class: com.android.systemui.dreams.homecontrols.domain.interactor.HomeControlsComponentInteractor$monitorUpdatesAndRestart$8
            @Override // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(Object obj, Continuation continuation2) {
                HomeControlsComponentInteractor.this.dreamManager.startDream();
                return Unit.INSTANCE;
            }
        }), continuation);
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        Unit unit = Unit.INSTANCE;
        if (collect != coroutineSingletons) {
            collect = unit;
        }
        return collect == coroutineSingletons ? collect : unit;
    }
}
