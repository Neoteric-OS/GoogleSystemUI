package com.android.systemui.keyguard.domain.interactor;

import com.android.keyguard.logging.KeyguardLogger;
import com.android.systemui.keyguard.ui.viewmodel.AodBurnInViewModel;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardTransitionAuditLogger {
    public final AodBurnInViewModel aodBurnInViewModel;
    public final KeyguardInteractor keyguardInteractor;
    public final KeyguardOcclusionInteractor keyguardOcclusionInteractor;
    public final KeyguardLogger logger;
    public final PowerInteractor powerInteractor;
    public final CoroutineScope scope;
    public final ShadeInteractor shadeInteractor;
    public final SharedNotificationContainerViewModel sharedNotificationContainerViewModel;

    public KeyguardTransitionAuditLogger(CoroutineScope coroutineScope, KeyguardInteractor keyguardInteractor, KeyguardLogger keyguardLogger, PowerInteractor powerInteractor, SharedNotificationContainerViewModel sharedNotificationContainerViewModel, AodBurnInViewModel aodBurnInViewModel, ShadeInteractor shadeInteractor, KeyguardOcclusionInteractor keyguardOcclusionInteractor) {
        this.scope = coroutineScope;
        this.keyguardInteractor = keyguardInteractor;
        this.logger = keyguardLogger;
        this.powerInteractor = powerInteractor;
        this.sharedNotificationContainerViewModel = sharedNotificationContainerViewModel;
        this.aodBurnInViewModel = aodBurnInViewModel;
        this.shadeInteractor = shadeInteractor;
        this.keyguardOcclusionInteractor = keyguardOcclusionInteractor;
    }
}
