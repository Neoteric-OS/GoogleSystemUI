package com.android.systemui.deviceentry.ui.viewmodel;

import com.android.systemui.accessibility.domain.interactor.AccessibilityInteractor;
import com.android.systemui.biometrics.domain.interactor.UdfpsOverlayInteractor;
import com.android.systemui.keyguard.ui.viewmodel.DeviceEntryForegroundViewModel;
import com.android.systemui.keyguard.ui.viewmodel.DeviceEntryIconViewModel;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DeviceEntryUdfpsAccessibilityOverlayViewModel extends UdfpsAccessibilityOverlayViewModel {
    public final DeviceEntryForegroundViewModel deviceEntryFgIconViewModel;
    public final DeviceEntryIconViewModel deviceEntryIconViewModel;

    public DeviceEntryUdfpsAccessibilityOverlayViewModel(UdfpsOverlayInteractor udfpsOverlayInteractor, AccessibilityInteractor accessibilityInteractor, DeviceEntryIconViewModel deviceEntryIconViewModel, DeviceEntryForegroundViewModel deviceEntryForegroundViewModel) {
        super(udfpsOverlayInteractor, accessibilityInteractor);
        this.deviceEntryIconViewModel = deviceEntryIconViewModel;
        this.deviceEntryFgIconViewModel = deviceEntryForegroundViewModel;
    }

    @Override // com.android.systemui.deviceentry.ui.viewmodel.UdfpsAccessibilityOverlayViewModel
    public final Flow isVisibleWhenTouchExplorationEnabled() {
        return new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(this.deviceEntryFgIconViewModel.viewModel, this.deviceEntryIconViewModel.deviceEntryViewAlpha, new DeviceEntryUdfpsAccessibilityOverlayViewModel$isVisibleWhenTouchExplorationEnabled$1(3, null));
    }
}
