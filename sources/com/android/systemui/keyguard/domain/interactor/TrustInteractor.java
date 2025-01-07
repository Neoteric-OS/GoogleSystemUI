package com.android.systemui.keyguard.domain.interactor;

import com.android.systemui.keyguard.data.repository.TrustRepositoryImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TrustInteractor {
    public final CoroutineScope applicationScope;
    public final ReadonlyStateFlow isEnrolledAndEnabled;
    public final ReadonlyStateFlow isTrustAgentCurrentlyAllowed;
    public final ReadonlyStateFlow isTrusted;
    public final TrustRepositoryImpl repository;

    public TrustInteractor(CoroutineScope coroutineScope, TrustRepositoryImpl trustRepositoryImpl) {
        this.isEnrolledAndEnabled = trustRepositoryImpl.isCurrentUserTrustUsuallyManaged;
        this.isTrustAgentCurrentlyAllowed = trustRepositoryImpl.isCurrentUserTrustManaged();
        this.isTrusted = trustRepositoryImpl.isCurrentUserTrusted();
    }
}
