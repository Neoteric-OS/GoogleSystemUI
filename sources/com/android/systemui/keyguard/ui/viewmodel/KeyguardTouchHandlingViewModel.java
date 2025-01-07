package com.android.systemui.keyguard.ui.viewmodel;

import com.android.systemui.keyguard.domain.interactor.KeyguardTouchHandlingInteractor;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardTouchHandlingViewModel {
    public final KeyguardTouchHandlingInteractor interactor;
    public final ReadonlyStateFlow isLongPressHandlingEnabled;

    public KeyguardTouchHandlingViewModel(KeyguardTouchHandlingInteractor keyguardTouchHandlingInteractor) {
        this.interactor = keyguardTouchHandlingInteractor;
        this.isLongPressHandlingEnabled = keyguardTouchHandlingInteractor.isLongPressHandlingEnabled;
    }
}
