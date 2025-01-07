package com.android.keyguard;

import com.android.keyguard.KeyguardSecurityContainerController;
import com.android.keyguard.KeyguardSecurityModel;
import com.android.keyguard.KeyguardSecurityViewFlipperController;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.shade.NotificationShadeWindowControllerImpl;
import com.android.systemui.shade.NotificationShadeWindowState;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class KeyguardSecurityContainerController$$ExternalSyntheticLambda5 implements KeyguardSecurityViewFlipperController.OnViewInflatedCallback {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ KeyguardSecurityContainerController$$ExternalSyntheticLambda5(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // com.android.keyguard.KeyguardSecurityViewFlipperController.OnViewInflatedCallback
    public void onViewInflated(KeyguardInputViewController keyguardInputViewController) {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 1:
                KeyguardSecurityContainerController keyguardSecurityContainerController = (KeyguardSecurityContainerController) obj;
                keyguardInputViewController.onResume(2);
                KeyguardSecurityViewFlipperController keyguardSecurityViewFlipperController = keyguardSecurityContainerController.mSecurityViewFlipperController;
                int indexOfChild = ((KeyguardSecurityViewFlipper) keyguardSecurityViewFlipperController.mView).indexOfChild(keyguardInputViewController.mView);
                if (indexOfChild != -1) {
                    ((KeyguardSecurityViewFlipper) keyguardSecurityViewFlipperController.mView).setDisplayedChild(indexOfChild);
                }
                keyguardSecurityContainerController.configureMode();
                KeyguardSecurityContainerController.AnonymousClass3 anonymousClass3 = keyguardSecurityContainerController.mKeyguardSecurityCallback;
                boolean needsInput = keyguardInputViewController.needsInput();
                NotificationShadeWindowControllerImpl notificationShadeWindowControllerImpl = (NotificationShadeWindowControllerImpl) ((StatusBarKeyguardViewManager) KeyguardViewMediator.this.mKeyguardViewControllerLazy.get()).mNotificationShadeWindowController;
                NotificationShadeWindowState notificationShadeWindowState = notificationShadeWindowControllerImpl.mCurrentState;
                notificationShadeWindowState.keyguardNeedsInput = needsInput;
                notificationShadeWindowControllerImpl.apply(notificationShadeWindowState);
                break;
            default:
                Runnable runnable = (Runnable) obj;
                if (!keyguardInputViewController.startDisappearAnimation(runnable) && runnable != null) {
                    runnable.run();
                    break;
                }
                break;
        }
    }

    public /* synthetic */ KeyguardSecurityContainerController$$ExternalSyntheticLambda5(KeyguardSecurityContainerController keyguardSecurityContainerController, KeyguardSecurityModel.SecurityMode securityMode) {
        this.$r8$classId = 1;
        this.f$0 = keyguardSecurityContainerController;
    }
}
