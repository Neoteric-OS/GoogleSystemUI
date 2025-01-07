package com.android.keyguard;

import com.android.keyguard.KeyguardSecurityViewFlipperController;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class KeyguardSecurityContainerController$$ExternalSyntheticLambda3 implements KeyguardSecurityViewFlipperController.OnViewInflatedCallback {
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ KeyguardSecurityContainerController$$ExternalSyntheticLambda3(int i) {
        this.$r8$classId = i;
    }

    @Override // com.android.keyguard.KeyguardSecurityViewFlipperController.OnViewInflatedCallback
    public final void onViewInflated(KeyguardInputViewController keyguardInputViewController) {
        switch (this.$r8$classId) {
            case 0:
                keyguardInputViewController.onPause();
                break;
            case 1:
                keyguardInputViewController.onPause();
                break;
            case 2:
                keyguardInputViewController.onResume(1);
                break;
            case 3:
                ((KeyguardInputView) keyguardInputViewController.mView).startAppearAnimation();
                break;
            default:
                keyguardInputViewController.onStartingToHide();
                break;
        }
    }
}
