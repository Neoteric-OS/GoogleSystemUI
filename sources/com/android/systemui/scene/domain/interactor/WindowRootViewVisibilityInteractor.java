package com.android.systemui.scene.domain.interactor;

import com.android.systemui.CoreStartable;
import com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.scene.data.repository.WindowRootViewVisibilityRepository;
import com.android.systemui.statusbar.notification.domain.interactor.ActiveNotificationsInteractor;
import com.android.systemui.statusbar.notification.init.NotificationsController;
import com.android.systemui.statusbar.phone.StatusBarNotificationPresenter;
import com.android.systemui.statusbar.policy.HeadsUpManager;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class WindowRootViewVisibilityInteractor implements CoreStartable {
    public final HeadsUpManager headsUpManager;
    public final ReadonlyStateFlow isLockscreenOrShadeVisible;
    public final ReadonlyStateFlow isLockscreenOrShadeVisibleAndInteractive;
    public final KeyguardRepositoryImpl keyguardRepository;
    public StatusBarNotificationPresenter notificationPresenter;
    public NotificationsController notificationsController;
    public final CoroutineScope scope;
    public final WindowRootViewVisibilityRepository windowRootViewVisibilityRepository;

    public WindowRootViewVisibilityInteractor(CoroutineScope coroutineScope, WindowRootViewVisibilityRepository windowRootViewVisibilityRepository, KeyguardRepositoryImpl keyguardRepositoryImpl, HeadsUpManager headsUpManager, PowerInteractor powerInteractor, ActiveNotificationsInteractor activeNotificationsInteractor) {
        this.scope = coroutineScope;
        this.windowRootViewVisibilityRepository = windowRootViewVisibilityRepository;
        this.keyguardRepository = keyguardRepositoryImpl;
        this.headsUpManager = headsUpManager;
        ReadonlyStateFlow readonlyStateFlow = windowRootViewVisibilityRepository.isLockscreenOrShadeVisible;
        this.isLockscreenOrShadeVisible = readonlyStateFlow;
        this.isLockscreenOrShadeVisibleAndInteractive = FlowKt.stateIn(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(readonlyStateFlow, powerInteractor.isAwake, new WindowRootViewVisibilityInteractor$isLockscreenOrShadeVisibleAndInteractive$1(3, null)), coroutineScope, SharingStarted.Companion.Eagerly, Boolean.FALSE);
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        BuildersKt.launch$default(this.scope, null, null, new WindowRootViewVisibilityInteractor$start$1(this, null), 3);
    }
}
