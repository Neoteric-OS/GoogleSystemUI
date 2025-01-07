package com.android.systemui.statusbar.phone.data.repository;

import com.android.systemui.statusbar.phone.DarkIconDispatcherImpl;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DarkIconRepositoryImpl {
    public final ReadonlyStateFlow darkState;

    public DarkIconRepositoryImpl(DarkIconDispatcherImpl darkIconDispatcherImpl) {
        this.darkState = new ReadonlyStateFlow(darkIconDispatcherImpl.mDarkChangeFlow);
    }
}
