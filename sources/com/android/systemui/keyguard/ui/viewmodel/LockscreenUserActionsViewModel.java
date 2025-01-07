package com.android.systemui.keyguard.ui.viewmodel;

import com.android.systemui.communal.domain.interactor.CommunalInteractor;
import com.android.systemui.deviceentry.domain.interactor.DeviceEntryInteractor;
import com.android.systemui.scene.ui.viewmodel.UserActionsViewModel;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LockscreenUserActionsViewModel extends UserActionsViewModel {
    public final CommunalInteractor communalInteractor;
    public final DeviceEntryInteractor deviceEntryInteractor;
    public final ShadeInteractor shadeInteractor;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Factory {
    }

    public LockscreenUserActionsViewModel(DeviceEntryInteractor deviceEntryInteractor, CommunalInteractor communalInteractor, ShadeInteractor shadeInteractor) {
    }
}
