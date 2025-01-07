package com.android.systemui.inputdevice.tutorial.domain.interactor;

import com.android.systemui.keyboard.data.repository.KeyboardRepositoryImpl;
import com.android.systemui.touchpad.data.repository.TouchpadRepositoryImpl;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyboardTouchpadConnectionInteractor {
    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 connectionState;

    public KeyboardTouchpadConnectionInteractor(KeyboardRepositoryImpl keyboardRepositoryImpl, TouchpadRepositoryImpl touchpadRepositoryImpl) {
        this.connectionState = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(keyboardRepositoryImpl.isAnyKeyboardConnected, touchpadRepositoryImpl.isAnyTouchpadConnected, new KeyboardTouchpadConnectionInteractor$connectionState$1(3, null));
    }
}
