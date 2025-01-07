package com.android.systemui.security.data.repository;

import com.android.systemui.statusbar.policy.SecurityController;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.flow.Flow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SecurityRepositoryImpl {
    public final CoroutineDispatcher bgDispatcher;
    public final Flow security = FlowConflatedKt.conflatedCallbackFlow(new SecurityRepositoryImpl$security$1(this, null));
    public final SecurityController securityController;

    public SecurityRepositoryImpl(SecurityController securityController, CoroutineDispatcher coroutineDispatcher) {
        this.securityController = securityController;
        this.bgDispatcher = coroutineDispatcher;
    }
}
