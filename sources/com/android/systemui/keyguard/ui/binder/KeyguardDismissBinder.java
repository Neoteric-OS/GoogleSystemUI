package com.android.systemui.keyguard.ui.binder;

import com.android.keyguard.logging.KeyguardLogger;
import com.android.systemui.CoreStartable;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardDismissBinder implements CoreStartable {
    public final KeyguardDismissInteractor interactor;
    public final KeyguardLogger keyguardLogger;
    public final SelectedUserInteractor selectedUserInteractor;
    public final KeyguardViewMediator.AnonymousClass4 viewMediatorCallback;

    public KeyguardDismissBinder(KeyguardDismissInteractor keyguardDismissInteractor, SelectedUserInteractor selectedUserInteractor, KeyguardViewMediator.AnonymousClass4 anonymousClass4, CoroutineScope coroutineScope, KeyguardLogger keyguardLogger) {
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
    }
}
