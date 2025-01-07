package com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel;

import com.android.systemui.statusbar.phone.StatusBarLocation;
import com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconInteractor;
import com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconInteractorImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ShadeCarrierGroupMobileIconViewModel extends LocationBasedMobileViewModel {
    public final ReadonlyStateFlow carrierName;
    public final ReadonlyStateFlow isSingleCarrier;
    public final ReadonlyStateFlow isVisible;

    public ShadeCarrierGroupMobileIconViewModel(MobileIconViewModelCommon mobileIconViewModelCommon, MobileIconInteractor mobileIconInteractor, CoroutineScope coroutineScope) {
        super(mobileIconViewModelCommon, StatusBarLocation.SHADE_CARRIER_GROUP, null);
        MobileIconInteractorImpl mobileIconInteractorImpl = (MobileIconInteractorImpl) mobileIconInteractor;
        ReadonlyStateFlow readonlyStateFlow = mobileIconInteractorImpl.isSingleCarrier;
        this.carrierName = mobileIconInteractorImpl.carrierName;
        this.isVisible = FlowKt.stateIn(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(mobileIconViewModelCommon.isVisible(), readonlyStateFlow, new ShadeCarrierGroupMobileIconViewModel$isVisible$1(3, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), mobileIconViewModelCommon.isVisible().getValue());
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.LocationBasedMobileViewModel, com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconViewModelCommon
    public final StateFlow isVisible() {
        return this.isVisible;
    }
}
