package com.android.systemui.statusbar.domain.interactor;

import com.android.systemui.statusbar.data.repository.KeyguardStatusBarRepositoryImpl;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class KeyguardStatusBarInteractor {
    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 isKeyguardUserSwitcherEnabled;

    public KeyguardStatusBarInteractor(KeyguardStatusBarRepositoryImpl keyguardStatusBarRepositoryImpl) {
        this.isKeyguardUserSwitcherEnabled = keyguardStatusBarRepositoryImpl.isKeyguardUserSwitcherEnabled;
    }
}
