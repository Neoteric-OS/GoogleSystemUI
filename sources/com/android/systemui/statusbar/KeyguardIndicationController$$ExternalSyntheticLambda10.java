package com.android.systemui.statusbar;

import android.content.res.ColorStateList;
import android.text.TextUtils;
import com.android.systemui.keyguard.KeyguardIndication;
import com.android.systemui.keyguard.KeyguardIndicationRotateTextViewController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class KeyguardIndicationController$$ExternalSyntheticLambda10 implements Runnable {
    public final /* synthetic */ int $r8$classId = 0;
    public final /* synthetic */ KeyguardIndicationController f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ KeyguardIndicationController$$ExternalSyntheticLambda10(KeyguardIndicationController keyguardIndicationController, CharSequence charSequence) {
        this.f$0 = keyguardIndicationController;
        this.f$1 = charSequence;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                KeyguardIndicationController keyguardIndicationController = this.f$0;
                CharSequence charSequence = (CharSequence) this.f$1;
                if (((KeyguardStateControllerImpl) keyguardIndicationController.mKeyguardStateController).mShowing) {
                    KeyguardIndicationRotateTextViewController keyguardIndicationRotateTextViewController = keyguardIndicationController.mRotateTextViewController;
                    ColorStateList colorStateList = keyguardIndicationController.mInitialTextColorState;
                    if (TextUtils.isEmpty(charSequence)) {
                        throw new IllegalStateException("message or icon must be set");
                    }
                    if (colorStateList == null) {
                        throw new IllegalStateException("text color must be set");
                    }
                    keyguardIndicationRotateTextViewController.updateIndication(1, new KeyguardIndication(charSequence, colorStateList, null, null, null, null, Boolean.FALSE), false);
                    return;
                }
                return;
            default:
                KeyguardIndicationController keyguardIndicationController2 = this.f$0;
                String str = (String) this.f$1;
                if (TextUtils.isEmpty(str) || !((KeyguardStateControllerImpl) keyguardIndicationController2.mKeyguardStateController).mShowing) {
                    keyguardIndicationController2.mRotateTextViewController.hideIndication(0);
                    return;
                }
                KeyguardIndicationRotateTextViewController keyguardIndicationRotateTextViewController2 = keyguardIndicationController2.mRotateTextViewController;
                ColorStateList colorStateList2 = keyguardIndicationController2.mInitialTextColorState;
                if (TextUtils.isEmpty(str)) {
                    throw new IllegalStateException("message or icon must be set");
                }
                if (colorStateList2 == null) {
                    throw new IllegalStateException("text color must be set");
                }
                keyguardIndicationRotateTextViewController2.updateIndication(0, new KeyguardIndication(str, colorStateList2, null, null, null, null, Boolean.FALSE), false);
                return;
        }
    }

    public /* synthetic */ KeyguardIndicationController$$ExternalSyntheticLambda10(KeyguardIndicationController keyguardIndicationController, String str) {
        this.f$0 = keyguardIndicationController;
        this.f$1 = str;
    }
}
