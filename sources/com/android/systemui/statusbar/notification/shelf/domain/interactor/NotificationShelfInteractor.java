package com.android.systemui.statusbar.notification.shelf.domain.interactor;

import com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryImpl;
import com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.statusbar.LockscreenShadeTransitionController;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationShelfInteractor {
    public final DeviceEntryFaceAuthRepositoryImpl deviceEntryFaceAuthRepository;
    public final KeyguardRepositoryImpl keyguardRepository;
    public final LockscreenShadeTransitionController keyguardTransitionController;
    public final PowerInteractor powerInteractor;

    public NotificationShelfInteractor(KeyguardRepositoryImpl keyguardRepositoryImpl, DeviceEntryFaceAuthRepositoryImpl deviceEntryFaceAuthRepositoryImpl, PowerInteractor powerInteractor, LockscreenShadeTransitionController lockscreenShadeTransitionController) {
        this.keyguardRepository = keyguardRepositoryImpl;
        this.deviceEntryFaceAuthRepository = deviceEntryFaceAuthRepositoryImpl;
        this.powerInteractor = powerInteractor;
        this.keyguardTransitionController = lockscreenShadeTransitionController;
    }

    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 isShelfStatic() {
        return new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(this.keyguardRepository.isKeyguardShowing, this.deviceEntryFaceAuthRepository.isBypassEnabled, new NotificationShelfInteractor$isShelfStatic$1(3, null));
    }
}
