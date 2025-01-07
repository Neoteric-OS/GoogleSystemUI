package com.android.systemui.keyguard.domain.interactor;

import com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl;
import dagger.Lazy;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DozeInteractor {
    public final KeyguardRepositoryImpl keyguardRepository;

    public DozeInteractor(KeyguardRepositoryImpl keyguardRepositoryImpl, Lazy lazy) {
        this.keyguardRepository = keyguardRepositoryImpl;
    }
}
