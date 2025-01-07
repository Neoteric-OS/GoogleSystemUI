package com.android.wm.shell.pip.phone;

import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class PipController$IPipImpl$$ExternalSyntheticLambda6 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ boolean f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ PipController$IPipImpl$$ExternalSyntheticLambda6(int i, int i2, boolean z) {
        this.$r8$classId = i2;
        this.f$0 = z;
        this.f$1 = i;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                boolean z = this.f$0;
                int i = this.f$1;
                int i2 = PipController.$r8$clinit;
                ((PipController) obj).setLauncherKeepClearAreaHeight$1(i, z);
                break;
            default:
                boolean z2 = this.f$0;
                int i3 = this.f$1;
                int i4 = PipController.$r8$clinit;
                ((PipController) obj).setLauncherKeepClearAreaHeight$1(i3, z2);
                break;
        }
    }
}
