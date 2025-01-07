package com.android.systemui.controls.settings;

import com.android.systemui.user.data.repository.UserRepositoryImpl;
import com.android.systemui.util.settings.SecureSettings;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ControlsSettingsRepositoryImpl implements ControlsSettingsRepository {
    public final CoroutineDispatcher backgroundDispatcher;
    public final CoroutineScope scope;
    public final SecureSettings secureSettings;
    public final UserRepositoryImpl userRepository;
    public final ReadonlyStateFlow canShowControlsInLockscreen = makeFlowForSetting("lockscreen_show_controls");
    public final ReadonlyStateFlow allowActionOnTrivialControlsInLockscreen = makeFlowForSetting("lockscreen_allow_trivial_controls");

    public ControlsSettingsRepositoryImpl(CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, UserRepositoryImpl userRepositoryImpl, SecureSettings secureSettings) {
        this.scope = coroutineScope;
        this.backgroundDispatcher = coroutineDispatcher;
        this.userRepository = userRepositoryImpl;
        this.secureSettings = secureSettings;
    }

    public final ReadonlyStateFlow makeFlowForSetting(String str) {
        return FlowKt.stateIn(FlowKt.transformLatest(FlowKt.distinctUntilChanged(this.userRepository.selectedUserInfo), new ControlsSettingsRepositoryImpl$makeFlowForSetting$$inlined$flatMapLatest$1(null, this, str)), this.scope, SharingStarted.Companion.Eagerly, Boolean.FALSE);
    }
}
