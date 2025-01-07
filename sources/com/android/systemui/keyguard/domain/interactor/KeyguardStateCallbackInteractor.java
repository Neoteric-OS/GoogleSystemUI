package com.android.systemui.keyguard.domain.interactor;

import android.app.trust.TrustManager;
import com.android.systemui.CoreStartable;
import com.android.systemui.bouncer.domain.interactor.SimBouncerInteractor;
import com.android.systemui.keyguard.DismissCallbackRegistry;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import java.util.ArrayList;
import java.util.List;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardStateCallbackInteractor implements CoreStartable {
    public final CoroutineDispatcher backgroundDispatcher;
    public final List callbacks;
    public final DismissCallbackRegistry dismissCallbackRegistry;
    public final KeyguardTransitionInteractor keyguardTransitionInteractor;
    public final SelectedUserInteractor selectedUserInteractor;
    public final SimBouncerInteractor simBouncerInteractor;
    public final TrustInteractor trustInteractor;
    public final TrustManager trustManager;
    public final WindowManagerLockscreenVisibilityInteractor wmLockscreenVisibilityInteractor;

    public KeyguardStateCallbackInteractor(CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, SelectedUserInteractor selectedUserInteractor, KeyguardTransitionInteractor keyguardTransitionInteractor, TrustInteractor trustInteractor, SimBouncerInteractor simBouncerInteractor, DismissCallbackRegistry dismissCallbackRegistry, WindowManagerLockscreenVisibilityInteractor windowManagerLockscreenVisibilityInteractor, TrustManager trustManager) {
        new ArrayList();
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
    }
}
