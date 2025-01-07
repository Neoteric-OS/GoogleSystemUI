package com.android.systemui.qs.panels.data.repository;

import com.android.systemui.settings.UserFileManager;
import com.android.systemui.user.data.repository.UserRepositoryImpl;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QSPreferencesRepository {
    public final DefaultLargeTilesRepositoryImpl defaultLargeTilesRepository;
    public final Flow largeTilesSpecs;
    public final Flow showLabels;
    public final UserFileManager userFileManager;
    public final UserRepositoryImpl userRepository;

    public QSPreferencesRepository(UserFileManager userFileManager, UserRepositoryImpl userRepositoryImpl, DefaultLargeTilesRepositoryImpl defaultLargeTilesRepositoryImpl, CoroutineDispatcher coroutineDispatcher) {
        this.userFileManager = userFileManager;
        this.userRepository = userRepositoryImpl;
        this.defaultLargeTilesRepository = defaultLargeTilesRepositoryImpl;
        this.showLabels = FlowKt.flowOn(FlowKt.transformLatest(userRepositoryImpl.selectedUserInfo, new QSPreferencesRepository$special$$inlined$flatMapLatest$1(null, this)), coroutineDispatcher);
        this.largeTilesSpecs = FlowKt.flowOn(FlowKt.transformLatest(userRepositoryImpl.selectedUserInfo, new QSPreferencesRepository$special$$inlined$flatMapLatest$2(null, this)), coroutineDispatcher);
    }
}
