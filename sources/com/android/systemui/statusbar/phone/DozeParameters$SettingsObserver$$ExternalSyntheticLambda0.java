package com.android.systemui.statusbar.phone;

import com.android.systemui.statusbar.phone.DozeParameters;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class DozeParameters$SettingsObserver$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ DozeParameters.SettingsObserver f$0;

    public /* synthetic */ DozeParameters$SettingsObserver$$ExternalSyntheticLambda0(DozeParameters.SettingsObserver settingsObserver, int i) {
        this.$r8$classId = i;
        this.f$0 = settingsObserver;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        DozeParameters.SettingsObserver settingsObserver = this.f$0;
        switch (i) {
            case 0:
                settingsObserver.mHandler.post(new DozeParameters$SettingsObserver$$ExternalSyntheticLambda0(settingsObserver, 1));
                break;
            default:
                settingsObserver.update(null);
                break;
        }
    }
}
