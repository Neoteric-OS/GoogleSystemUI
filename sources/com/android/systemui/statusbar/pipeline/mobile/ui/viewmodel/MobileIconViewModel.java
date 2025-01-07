package com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel;

import com.android.systemui.flags.FeatureFlagsClassic;
import com.android.systemui.statusbar.pipeline.airplane.domain.interactor.AirplaneModeInteractor;
import com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconInteractor;
import com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconInteractorImpl;
import com.android.systemui.statusbar.pipeline.shared.ConnectivityConstants;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MobileIconViewModel implements MobileIconViewModelCommon {
    public final ChannelFlowTransformLatest activityContainerVisible;
    public final ChannelFlowTransformLatest activityInVisible;
    public final ChannelFlowTransformLatest activityOutVisible;
    public final Lazy cellProvider$delegate;
    public final ChannelFlowTransformLatest contentDescription;
    public final ChannelFlowTransformLatest icon;
    public final ReadonlyStateFlow isVisible;
    public final ReadonlyStateFlow networkTypeBackground;
    public final ChannelFlowTransformLatest networkTypeIcon;
    public final ChannelFlowTransformLatest roaming;
    public final Lazy satelliteProvider$delegate;
    public final int subscriptionId;
    public final ReadonlyStateFlow vmProvider;

    public MobileIconViewModel(int i, final MobileIconInteractor mobileIconInteractor, final AirplaneModeInteractor airplaneModeInteractor, final ConnectivityConstants connectivityConstants, final FeatureFlagsClassic featureFlagsClassic, final ContextScope contextScope) {
        this.subscriptionId = i;
        Lazy lazy = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconViewModel$cellProvider$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return new CellularIconViewModel(MobileIconViewModel.this.subscriptionId, mobileIconInteractor, airplaneModeInteractor, connectivityConstants, featureFlagsClassic, contextScope);
            }
        });
        this.cellProvider$delegate = lazy;
        this.satelliteProvider$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconViewModel$satelliteProvider$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return new CarrierBasedSatelliteViewModelImpl(MobileIconViewModel.this.subscriptionId, mobileIconInteractor);
            }
        });
        ReadonlyStateFlow stateIn = FlowKt.stateIn(FlowKt.mapLatest(new MobileIconViewModel$vmProvider$1(this, null), ((MobileIconInteractorImpl) mobileIconInteractor).isNonTerrestrial), contextScope, SharingStarted.Companion.WhileSubscribed$default(3), (CellularIconViewModel) lazy.getValue());
        this.isVisible = FlowKt.stateIn(FlowKt.transformLatest(stateIn, new MobileIconViewModel$special$$inlined$flatMapLatest$1(3, null)), contextScope, SharingStarted.Companion.WhileSubscribed$default(3), Boolean.FALSE);
        this.icon = FlowKt.transformLatest(stateIn, new MobileIconViewModel$special$$inlined$flatMapLatest$2(3, null));
        this.contentDescription = FlowKt.transformLatest(stateIn, new MobileIconViewModel$special$$inlined$flatMapLatest$3(3, null));
        this.roaming = FlowKt.transformLatest(stateIn, new MobileIconViewModel$special$$inlined$flatMapLatest$4(3, null));
        this.networkTypeIcon = FlowKt.transformLatest(stateIn, new MobileIconViewModel$special$$inlined$flatMapLatest$5(3, null));
        this.networkTypeBackground = FlowKt.stateIn(FlowKt.transformLatest(stateIn, new MobileIconViewModel$special$$inlined$flatMapLatest$6(3, null)), contextScope, SharingStarted.Companion.WhileSubscribed$default(3), null);
        this.activityInVisible = FlowKt.transformLatest(stateIn, new MobileIconViewModel$special$$inlined$flatMapLatest$7(3, null));
        this.activityOutVisible = FlowKt.transformLatest(stateIn, new MobileIconViewModel$special$$inlined$flatMapLatest$8(3, null));
        this.activityContainerVisible = FlowKt.transformLatest(stateIn, new MobileIconViewModel$special$$inlined$flatMapLatest$9(3, null));
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconViewModelCommon
    public final Flow getActivityContainerVisible() {
        return this.activityContainerVisible;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconViewModelCommon
    public final Flow getActivityInVisible() {
        return this.activityInVisible;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconViewModelCommon
    public final Flow getActivityOutVisible() {
        return this.activityOutVisible;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconViewModelCommon
    public final Flow getContentDescription() {
        return this.contentDescription;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconViewModelCommon
    public final Flow getIcon() {
        return this.icon;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconViewModelCommon
    public final StateFlow getNetworkTypeBackground() {
        return this.networkTypeBackground;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconViewModelCommon
    public final Flow getNetworkTypeIcon() {
        return this.networkTypeIcon;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconViewModelCommon
    public final Flow getRoaming() {
        return this.roaming;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconViewModelCommon
    public final int getSubscriptionId() {
        return this.subscriptionId;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconViewModelCommon
    public final StateFlow isVisible() {
        return this.isVisible;
    }
}
