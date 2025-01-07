package com.android.systemui.scene.domain.startable;

import android.content.Context;
import android.os.Binder;
import android.os.IBinder;
import com.android.internal.statusbar.IStatusBarService;
import com.android.systemui.CoreStartable;
import com.android.systemui.authentication.domain.interactor.AuthenticationInteractor;
import com.android.systemui.deviceconfig.domain.interactor.DeviceConfigInteractor;
import com.android.systemui.deviceentry.domain.interactor.DeviceEntryFaceAuthInteractor;
import com.android.systemui.deviceentry.domain.interactor.DeviceEntryInteractor;
import com.android.systemui.navigation.domain.interactor.NavigationInteractor;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.scene.domain.interactor.SceneContainerOcclusionInteractor;
import com.android.systemui.scene.domain.interactor.SceneInteractor;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StatusBarStartable implements CoreStartable {
    public final Context applicationContext;
    public final CoroutineScope applicationScope;
    public final AuthenticationInteractor authenticationInteractor;
    public final CoroutineDispatcher backgroundDispatcher;
    public final DeviceConfigInteractor deviceConfigInteractor;
    public final DeviceEntryFaceAuthInteractor deviceEntryFaceAuthInteractor;
    public final DeviceEntryInteractor deviceEntryInteractor;
    public final IBinder disableToken = new Binder();
    public final NavigationInteractor navigationInteractor;
    public final PowerInteractor powerInteractor;
    public final SceneContainerOcclusionInteractor sceneContainerOcclusionInteractor;
    public final SceneInteractor sceneInteractor;
    public final SelectedUserInteractor selectedUserInteractor;
    public final IStatusBarService statusBarService;

    public StatusBarStartable(CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, Context context, SelectedUserInteractor selectedUserInteractor, SceneInteractor sceneInteractor, DeviceEntryInteractor deviceEntryInteractor, SceneContainerOcclusionInteractor sceneContainerOcclusionInteractor, DeviceConfigInteractor deviceConfigInteractor, NavigationInteractor navigationInteractor, AuthenticationInteractor authenticationInteractor, PowerInteractor powerInteractor, DeviceEntryFaceAuthInteractor deviceEntryFaceAuthInteractor, IStatusBarService iStatusBarService) {
        this.applicationScope = coroutineScope;
        this.backgroundDispatcher = coroutineDispatcher;
        this.applicationContext = context;
        this.selectedUserInteractor = selectedUserInteractor;
        this.statusBarService = iStatusBarService;
    }

    @Override // com.android.systemui.CoreStartable
    public final void onBootCompleted() {
        BuildersKt.launch$default(this.applicationScope, this.backgroundDispatcher, null, new StatusBarStartable$onBootCompleted$1(this, null), 2);
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
    }
}
