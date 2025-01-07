package com.android.systemui.keyguard.ui.viewmodel;

import com.android.systemui.deviceentry.domain.interactor.OccludingAppDeviceEntryInteractor;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class OccludingAppDeviceEntryMessageViewModel {
    public final ChannelFlowTransformLatest message;

    public OccludingAppDeviceEntryMessageViewModel(OccludingAppDeviceEntryInteractor occludingAppDeviceEntryInteractor) {
        this.message = occludingAppDeviceEntryInteractor.message;
    }
}
