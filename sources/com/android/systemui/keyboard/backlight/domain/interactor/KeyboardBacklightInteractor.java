package com.android.systemui.keyboard.backlight.domain.interactor;

import com.android.systemui.keyboard.data.repository.KeyboardRepositoryImpl;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyboardBacklightInteractor {
    public final ChannelFlowTransformLatest backlight;
    public final KeyboardRepositoryImpl keyboardRepository;

    public KeyboardBacklightInteractor(KeyboardRepositoryImpl keyboardRepositoryImpl) {
        this.keyboardRepository = keyboardRepositoryImpl;
        this.backlight = FlowKt.transformLatest(keyboardRepositoryImpl.isAnyKeyboardConnected, new KeyboardBacklightInteractor$special$$inlined$flatMapLatest$1(null, this));
    }
}
