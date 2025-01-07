package com.android.keyguard;

import android.view.WindowInsets;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class KeyguardPasswordView$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ KeyguardPasswordView f$0;

    public /* synthetic */ KeyguardPasswordView$$ExternalSyntheticLambda0(KeyguardPasswordView keyguardPasswordView, int i) {
        this.$r8$classId = i;
        this.f$0 = keyguardPasswordView;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        KeyguardPasswordView keyguardPasswordView = this.f$0;
        switch (i) {
            case 0:
                if (keyguardPasswordView.mPasswordEntry.isAttachedToWindow() && !keyguardPasswordView.mPasswordEntry.getRootWindowInsets().isVisible(WindowInsets.Type.ime())) {
                    keyguardPasswordView.mPasswordEntry.requestFocus();
                    keyguardPasswordView.mPasswordEntry.getWindowInsetsController().show(WindowInsets.Type.ime());
                    break;
                }
                break;
            default:
                if (keyguardPasswordView.mPasswordEntry.isAttachedToWindow() && keyguardPasswordView.mPasswordEntry.getRootWindowInsets().isVisible(WindowInsets.Type.ime())) {
                    keyguardPasswordView.mPasswordEntry.clearFocus();
                    keyguardPasswordView.mPasswordEntry.getWindowInsetsController().hide(WindowInsets.Type.ime());
                    break;
                }
                break;
        }
    }
}
