package com.google.android.systemui.assist;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class OpaLayout$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ OpaLayout f$0;

    public /* synthetic */ OpaLayout$$ExternalSyntheticLambda0(OpaLayout opaLayout, int i) {
        this.$r8$classId = i;
        this.f$0 = opaLayout;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        OpaLayout opaLayout = this.f$0;
        switch (i) {
            case 0:
                if (opaLayout.mCurrentAnimators.isEmpty()) {
                    opaLayout.startDiamondAnimation();
                    break;
                }
                break;
            default:
                opaLayout.getOpaEnabled();
                break;
        }
    }
}
