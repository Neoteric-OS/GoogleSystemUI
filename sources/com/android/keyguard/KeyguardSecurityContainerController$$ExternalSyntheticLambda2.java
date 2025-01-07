package com.android.keyguard;

import android.content.res.ColorStateList;
import com.android.keyguard.KeyguardSecurityViewFlipperController;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class KeyguardSecurityContainerController$$ExternalSyntheticLambda2 implements KeyguardSecurityViewFlipperController.OnViewInflatedCallback {
    public final /* synthetic */ CharSequence f$0;
    public final /* synthetic */ ColorStateList f$1;
    public final /* synthetic */ boolean f$2;

    public /* synthetic */ KeyguardSecurityContainerController$$ExternalSyntheticLambda2(CharSequence charSequence, ColorStateList colorStateList, boolean z) {
        this.f$0 = charSequence;
        this.f$1 = colorStateList;
        this.f$2 = z;
    }

    @Override // com.android.keyguard.KeyguardSecurityViewFlipperController.OnViewInflatedCallback
    public final void onViewInflated(KeyguardInputViewController keyguardInputViewController) {
        keyguardInputViewController.showMessage(this.f$0, this.f$1, this.f$2);
    }
}
