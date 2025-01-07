package com.android.systemui.keyevent.domain.interactor;

import com.android.systemui.keyevent.data.repository.KeyEventRepositoryImpl;
import kotlinx.coroutines.flow.Flow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyEventInteractor {
    public final Flow isPowerButtonDown;

    public KeyEventInteractor(KeyEventRepositoryImpl keyEventRepositoryImpl) {
        this.isPowerButtonDown = keyEventRepositoryImpl.isPowerButtonDown;
    }
}
