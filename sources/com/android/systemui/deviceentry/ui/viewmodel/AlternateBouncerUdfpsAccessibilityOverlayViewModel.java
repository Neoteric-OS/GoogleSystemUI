package com.android.systemui.deviceentry.ui.viewmodel;

import com.android.systemui.accessibility.domain.interactor.AccessibilityInteractor;
import com.android.systemui.biometrics.domain.interactor.UdfpsOverlayInteractor;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AlternateBouncerUdfpsAccessibilityOverlayViewModel extends UdfpsAccessibilityOverlayViewModel {
    public AlternateBouncerUdfpsAccessibilityOverlayViewModel(UdfpsOverlayInteractor udfpsOverlayInteractor, AccessibilityInteractor accessibilityInteractor) {
        super(udfpsOverlayInteractor, accessibilityInteractor);
    }

    @Override // com.android.systemui.deviceentry.ui.viewmodel.UdfpsAccessibilityOverlayViewModel
    public final Flow isVisibleWhenTouchExplorationEnabled() {
        return new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(Boolean.TRUE);
    }
}
