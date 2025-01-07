package com.android.systemui.deviceentry.data.repository;

import com.android.internal.widget.LockPatternUtils;
import com.android.systemui.statusbar.phone.KeyguardBypassController;
import com.android.systemui.user.data.repository.UserRepositoryImpl;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DeviceEntryRepositoryImpl {
    public final StateFlowImpl _isLockscreenEnabled;
    public final CoroutineDispatcher backgroundDispatcher;
    public final ReadonlyStateFlow isBypassEnabled;
    public final ReadonlyStateFlow isLockscreenEnabled;
    public final KeyguardBypassController keyguardBypassController;
    public final LockPatternUtils lockPatternUtils;
    public final UserRepositoryImpl userRepository;

    public DeviceEntryRepositoryImpl(CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, UserRepositoryImpl userRepositoryImpl, LockPatternUtils lockPatternUtils, KeyguardBypassController keyguardBypassController) {
        this.backgroundDispatcher = coroutineDispatcher;
        this.userRepository = userRepositoryImpl;
        this.lockPatternUtils = lockPatternUtils;
        this.keyguardBypassController = keyguardBypassController;
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(Boolean.TRUE);
        this._isLockscreenEnabled = MutableStateFlow;
        this.isLockscreenEnabled = new ReadonlyStateFlow(MutableStateFlow);
        this.isBypassEnabled = FlowKt.stateIn(FlowConflatedKt.conflatedCallbackFlow(new DeviceEntryRepositoryImpl$isBypassEnabled$1(this, null)), coroutineScope, SharingStarted.Companion.Eagerly, Boolean.valueOf(keyguardBypassController.getBypassEnabled()));
    }

    public final Object isLockscreenEnabled(ContinuationImpl continuationImpl) {
        return BuildersKt.withContext(this.backgroundDispatcher, new DeviceEntryRepositoryImpl$isLockscreenEnabled$2(this, null), continuationImpl);
    }
}
