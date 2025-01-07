package com.android.systemui.user.domain.interactor;

import com.android.systemui.user.data.repository.UserRepositoryImpl;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.StandaloneCoroutine;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RefreshUsersScheduler {
    public final CoroutineScope applicationScope;
    public boolean isPaused;
    public final CoroutineDispatcher mainDispatcher;
    public final UserRepositoryImpl repository;
    public StandaloneCoroutine scheduledUnpauseJob;

    public RefreshUsersScheduler(CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, UserRepositoryImpl userRepositoryImpl) {
        this.applicationScope = coroutineScope;
        this.mainDispatcher = coroutineDispatcher;
        this.repository = userRepositoryImpl;
    }

    public final void refreshIfNotPaused() {
        BuildersKt.launch$default(this.applicationScope, this.mainDispatcher, null, new RefreshUsersScheduler$refreshIfNotPaused$1(this, null), 2);
    }
}
