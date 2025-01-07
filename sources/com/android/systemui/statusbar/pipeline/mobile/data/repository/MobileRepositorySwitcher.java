package com.android.systemui.statusbar.pipeline.mobile.data.repository;

import com.android.systemui.demomode.DemoModeController;
import com.android.systemui.statusbar.pipeline.mobile.data.repository.demo.DemoMobileConnectionsRepository;
import com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionsRepositoryImpl;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MobileRepositorySwitcher implements MobileConnectionsRepository {
    public final ReadonlyStateFlow activeMobileDataRepository;
    public final ReadonlyStateFlow activeMobileDataSubscriptionId;
    public final ReadonlyStateFlow activeRepo;
    public final ChannelFlowTransformLatest activeSubChangedInGroupEvent;
    public final ReadonlyStateFlow defaultConnectionIsValidated;
    public final ReadonlyStateFlow defaultDataSubId;
    public final ReadonlyStateFlow defaultDataSubRatConfig;
    public final ChannelFlowTransformLatest defaultMobileIconGroup;
    public final ChannelFlowTransformLatest defaultMobileIconMapping;
    public final DemoMobileConnectionsRepository demoMobileConnectionsRepository;
    public final ReadonlyStateFlow hasCarrierMergedConnection;
    public final ChannelFlowTransformLatest isAnySimSecure;
    public final ReadonlyStateFlow isDemoMode;
    public final ReadonlyStateFlow isDeviceEmergencyCallCapable;
    public final ReadonlyStateFlow mobileIsDefault;
    public final MobileConnectionsRepositoryImpl realRepository;
    public final ReadonlyStateFlow subscriptions;

    public MobileRepositorySwitcher(CoroutineScope coroutineScope, MobileConnectionsRepositoryImpl mobileConnectionsRepositoryImpl, DemoMobileConnectionsRepository demoMobileConnectionsRepository, DemoModeController demoModeController) {
        this.realRepository = mobileConnectionsRepositoryImpl;
        this.demoMobileConnectionsRepository = demoMobileConnectionsRepository;
        ReadonlyStateFlow stateIn = FlowKt.stateIn(FlowConflatedKt.conflatedCallbackFlow(new MobileRepositorySwitcher$isDemoMode$1(demoModeController, this, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), Boolean.valueOf(demoModeController.isInDemoMode));
        this.isDemoMode = stateIn;
        ReadonlyStateFlow stateIn2 = FlowKt.stateIn(FlowKt.mapLatest(new MobileRepositorySwitcher$activeRepo$1(this, null), stateIn), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), mobileConnectionsRepositoryImpl);
        this.activeRepo = stateIn2;
        this.subscriptions = FlowKt.stateIn(FlowKt.transformLatest(stateIn2, new MobileRepositorySwitcher$special$$inlined$flatMapLatest$1(3, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), ((StateFlowImpl) mobileConnectionsRepositoryImpl.subscriptions.$$delegate_0).getValue());
        this.activeMobileDataSubscriptionId = FlowKt.stateIn(FlowKt.transformLatest(stateIn2, new MobileRepositorySwitcher$special$$inlined$flatMapLatest$2(3, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), ((StateFlowImpl) mobileConnectionsRepositoryImpl.activeMobileDataSubscriptionId.$$delegate_0).getValue());
        this.activeMobileDataRepository = FlowKt.stateIn(FlowKt.transformLatest(stateIn2, new MobileRepositorySwitcher$special$$inlined$flatMapLatest$3(3, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), ((StateFlowImpl) mobileConnectionsRepositoryImpl.activeMobileDataRepository.$$delegate_0).getValue());
        this.activeSubChangedInGroupEvent = FlowKt.transformLatest(stateIn2, new MobileRepositorySwitcher$special$$inlined$flatMapLatest$4(3, null));
        this.defaultDataSubRatConfig = FlowKt.stateIn(FlowKt.transformLatest(stateIn2, new MobileRepositorySwitcher$special$$inlined$flatMapLatest$5(3, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), ((StateFlowImpl) mobileConnectionsRepositoryImpl.defaultDataSubRatConfig.$$delegate_0).getValue());
        this.defaultMobileIconMapping = FlowKt.transformLatest(stateIn2, new MobileRepositorySwitcher$special$$inlined$flatMapLatest$6(3, null));
        this.defaultMobileIconGroup = FlowKt.transformLatest(stateIn2, new MobileRepositorySwitcher$special$$inlined$flatMapLatest$7(3, null));
        this.isDeviceEmergencyCallCapable = FlowKt.stateIn(FlowKt.transformLatest(stateIn2, new MobileRepositorySwitcher$special$$inlined$flatMapLatest$8(3, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), ((StateFlowImpl) mobileConnectionsRepositoryImpl.isDeviceEmergencyCallCapable.$$delegate_0).getValue());
        this.isAnySimSecure = FlowKt.transformLatest(stateIn2, new MobileRepositorySwitcher$special$$inlined$flatMapLatest$9(3, null));
        this.defaultDataSubId = FlowKt.stateIn(FlowKt.transformLatest(stateIn2, new MobileRepositorySwitcher$special$$inlined$flatMapLatest$10(3, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), ((StateFlowImpl) mobileConnectionsRepositoryImpl.defaultDataSubId.$$delegate_0).getValue());
        this.mobileIsDefault = FlowKt.stateIn(FlowKt.transformLatest(stateIn2, new MobileRepositorySwitcher$special$$inlined$flatMapLatest$11(3, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), ((StateFlowImpl) mobileConnectionsRepositoryImpl.mobileIsDefault.$$delegate_0).getValue());
        this.hasCarrierMergedConnection = FlowKt.stateIn(FlowKt.transformLatest(stateIn2, new MobileRepositorySwitcher$special$$inlined$flatMapLatest$12(3, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), ((StateFlowImpl) mobileConnectionsRepositoryImpl.hasCarrierMergedConnection.$$delegate_0).getValue());
        this.defaultConnectionIsValidated = FlowKt.stateIn(FlowKt.transformLatest(stateIn2, new MobileRepositorySwitcher$special$$inlined$flatMapLatest$13(3, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), ((StateFlowImpl) mobileConnectionsRepositoryImpl.defaultConnectionIsValidated.$$delegate_0).getValue());
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionsRepository
    public final StateFlow getActiveMobileDataRepository() {
        return this.activeMobileDataRepository;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionsRepository
    public final StateFlow getActiveMobileDataSubscriptionId() {
        return this.activeMobileDataSubscriptionId;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionsRepository
    public final Flow getActiveSubChangedInGroupEvent() {
        return this.activeSubChangedInGroupEvent;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionsRepository
    public final StateFlow getDefaultConnectionIsValidated() {
        return this.defaultConnectionIsValidated;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionsRepository
    public final StateFlow getDefaultDataSubId() {
        return this.defaultDataSubId;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionsRepository
    public final StateFlow getDefaultDataSubRatConfig() {
        return this.defaultDataSubRatConfig;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionsRepository
    public final Flow getDefaultMobileIconGroup() {
        return this.defaultMobileIconGroup;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionsRepository
    public final Flow getDefaultMobileIconMapping() {
        return this.defaultMobileIconMapping;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionsRepository
    public final Flow getHasCarrierMergedConnection() {
        return this.hasCarrierMergedConnection;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionsRepository
    public final boolean getIsAnySimSecure() {
        return ((MobileConnectionsRepository) ((StateFlowImpl) this.activeRepo.$$delegate_0).getValue()).getIsAnySimSecure();
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionsRepository
    public final StateFlow getMobileIsDefault() {
        return this.mobileIsDefault;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionsRepository
    public final MobileConnectionRepository getRepoForSubId(int i) {
        return ((Boolean) ((StateFlowImpl) this.isDemoMode.$$delegate_0).getValue()).booleanValue() ? this.demoMobileConnectionsRepository.getRepoForSubId(i) : this.realRepository.getOrCreateRepoForSubId(i);
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionsRepository
    public final StateFlow getSubscriptions() {
        return this.subscriptions;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionsRepository
    public final Flow isAnySimSecure() {
        return this.isAnySimSecure;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionsRepository
    public final StateFlow isDeviceEmergencyCallCapable() {
        return this.isDeviceEmergencyCallCapable;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionsRepository
    public final Object isInEcmMode(Continuation continuation) {
        return ((Boolean) ((StateFlowImpl) this.isDemoMode.$$delegate_0).getValue()).booleanValue() ? Boolean.FALSE : this.realRepository.isInEcmMode(continuation);
    }

    public static /* synthetic */ void getActiveRepo$frameworks__base__packages__SystemUI__android_common__SystemUI_core$annotations() {
    }
}
