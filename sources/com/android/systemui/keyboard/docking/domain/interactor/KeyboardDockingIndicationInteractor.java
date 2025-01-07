package com.android.systemui.keyboard.docking.domain.interactor;

import com.android.systemui.keyboard.data.repository.KeyboardRepositoryImpl;
import kotlinx.coroutines.flow.Flow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyboardDockingIndicationInteractor {
    public final Flow onKeyboardConnected;

    public KeyboardDockingIndicationInteractor(KeyboardRepositoryImpl keyboardRepositoryImpl) {
        this.onKeyboardConnected = keyboardRepositoryImpl.isAnyKeyboardConnected;
    }
}
