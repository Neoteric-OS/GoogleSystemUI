package com.android.systemui.smartspace.ui.viewmodel;

import com.android.systemui.power.domain.interactor.PowerInteractor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SmartspaceViewModel {
    public final SmartspaceViewModel$special$$inlined$filter$1 isAwake;
    public final String surfaceName;

    public SmartspaceViewModel(PowerInteractor powerInteractor, String str) {
        this.surfaceName = str;
        this.isAwake = new SmartspaceViewModel$special$$inlined$filter$1(powerInteractor.isAwake, this);
    }
}
