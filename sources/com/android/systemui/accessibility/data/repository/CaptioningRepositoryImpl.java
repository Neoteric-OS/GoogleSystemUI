package com.android.systemui.accessibility.data.repository;

import com.android.systemui.user.data.repository.UserRepositoryImpl;
import com.android.systemui.user.utils.UserScopedServiceImpl;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CaptioningRepositoryImpl {
    public final CoroutineContext backgroundCoroutineContext;
    public final ReadonlyStateFlow captioningManager;
    public final ReadonlyStateFlow captioningModel;
    public final UserScopedServiceImpl userScopedCaptioningManagerProvider;

    public CaptioningRepositoryImpl(UserScopedServiceImpl userScopedServiceImpl, UserRepositoryImpl userRepositoryImpl, CoroutineContext coroutineContext, CoroutineScope coroutineScope) {
        this.userScopedCaptioningManagerProvider = userScopedServiceImpl;
        this.backgroundCoroutineContext = coroutineContext;
        ReadonlyStateFlow stateIn = FlowKt.stateIn(new CaptioningRepositoryImpl$special$$inlined$map$1(userRepositoryImpl.selectedUser, this, 0), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), null);
        this.captioningManager = stateIn;
        this.captioningModel = FlowKt.stateIn(FlowKt.transformLatest(new FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1(stateIn), new CaptioningRepositoryImpl$special$$inlined$flatMapLatest$1(null, this)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), null);
    }

    public final Object setIsSystemAudioCaptioningEnabled(boolean z, Continuation continuation) {
        Object withContext = BuildersKt.withContext(this.backgroundCoroutineContext, new CaptioningRepositoryImpl$setIsSystemAudioCaptioningEnabled$2(this, z, null), continuation);
        return withContext == CoroutineSingletons.COROUTINE_SUSPENDED ? withContext : Unit.INSTANCE;
    }
}
