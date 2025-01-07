package com.android.systemui.statusbar.phone;

import com.android.systemui.statusbar.phone.PhoneStatusBarPolicy;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class PhoneStatusBarPolicy$2$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ PhoneStatusBarPolicy.AnonymousClass2 f$0;

    public /* synthetic */ PhoneStatusBarPolicy$2$$ExternalSyntheticLambda0(PhoneStatusBarPolicy.AnonymousClass2 anonymousClass2, int i) {
        this.$r8$classId = i;
        this.f$0 = anonymousClass2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        PhoneStatusBarPolicy.AnonymousClass2 anonymousClass2 = this.f$0;
        switch (i) {
            case 0:
                anonymousClass2.getClass();
                boolean z = PhoneStatusBarPolicy.DEBUG;
                PhoneStatusBarPolicy phoneStatusBarPolicy = anonymousClass2.this$0;
                phoneStatusBarPolicy.updateAlarm();
                phoneStatusBarPolicy.updateProfileIcon();
                phoneStatusBarPolicy.onUserSetupChanged();
                break;
            default:
                anonymousClass2.this$0.mUserInfoController.reloadUserInfo();
                break;
        }
    }
}
