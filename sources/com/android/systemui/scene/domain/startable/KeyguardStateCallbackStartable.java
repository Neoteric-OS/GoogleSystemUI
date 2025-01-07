package com.android.systemui.scene.domain.startable;

import com.android.systemui.CoreStartable;
import com.android.systemui.bouncer.domain.interactor.SimBouncerInteractor;
import com.android.systemui.deviceentry.domain.interactor.DeviceEntryInteractor;
import com.android.systemui.keyguard.domain.interactor.TrustInteractor;
import com.android.systemui.scene.domain.interactor.SceneInteractor;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import java.util.ArrayList;
import java.util.List;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class KeyguardStateCallbackStartable implements CoreStartable {
    public final CoroutineDispatcher backgroundDispatcher;
    public final List callbacks;
    public final DeviceEntryInteractor deviceEntryInteractor;
    public final SceneInteractor sceneInteractor;
    public final SelectedUserInteractor selectedUserInteractor;
    public final SimBouncerInteractor simBouncerInteractor;
    public final TrustInteractor trustInteractor;

    public KeyguardStateCallbackStartable(CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, SceneInteractor sceneInteractor, SelectedUserInteractor selectedUserInteractor, DeviceEntryInteractor deviceEntryInteractor, SimBouncerInteractor simBouncerInteractor, TrustInteractor trustInteractor) {
        new ArrayList();
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
    }
}
