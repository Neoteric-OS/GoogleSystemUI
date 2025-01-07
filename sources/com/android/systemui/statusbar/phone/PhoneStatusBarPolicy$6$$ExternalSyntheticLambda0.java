package com.android.systemui.statusbar.phone;

import com.android.systemui.statusbar.phone.PhoneStatusBarPolicy;
import com.android.systemui.statusbar.phone.ui.StatusBarIconControllerImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class PhoneStatusBarPolicy$6$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ PhoneStatusBarPolicy.AnonymousClass6 f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ PhoneStatusBarPolicy$6$$ExternalSyntheticLambda0(PhoneStatusBarPolicy.AnonymousClass6 anonymousClass6, boolean z) {
        this.f$0 = anonymousClass6;
        this.f$1 = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        PhoneStatusBarPolicy.AnonymousClass6 anonymousClass6 = this.f$0;
        boolean z = this.f$1;
        PhoneStatusBarPolicy phoneStatusBarPolicy = PhoneStatusBarPolicy.this;
        ((StatusBarIconControllerImpl) phoneStatusBarPolicy.mIconController).setIconVisibility(phoneStatusBarPolicy.mSlotSensorsOff, z);
    }
}
