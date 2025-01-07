package com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel;

import com.android.systemui.common.shared.model.ContentDescription;
import com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconInteractor;
import com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconInteractorImpl;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CarrierBasedSatelliteViewModelImpl implements MobileIconViewModelCommon {
    public final FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2 activityContainerVisible;
    public final FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2 activityInVisible;
    public final FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2 activityOutVisible;
    public final StateFlow icon;
    public final StateFlowImpl networkTypeBackground;
    public final FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2 networkTypeIcon;
    public final FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2 roaming;
    public final int subscriptionId;
    public final StateFlowImpl isVisible = StateFlowKt.MutableStateFlow(Boolean.TRUE);
    public final StateFlowImpl contentDescription = StateFlowKt.MutableStateFlow(new ContentDescription.Loaded(""));

    public CarrierBasedSatelliteViewModelImpl(int i, MobileIconInteractor mobileIconInteractor) {
        this.subscriptionId = i;
        this.icon = ((MobileIconInteractorImpl) mobileIconInteractor).signalLevelIcon;
        Boolean bool = Boolean.FALSE;
        this.roaming = new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(bool);
        this.networkTypeIcon = new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(null);
        this.networkTypeBackground = StateFlowKt.MutableStateFlow(null);
        this.activityInVisible = new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(bool);
        this.activityOutVisible = new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(bool);
        this.activityContainerVisible = new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(bool);
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
