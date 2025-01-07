package com.android.systemui.shade;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class ShadeControllerImpl$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ShadeControllerImpl f$0;

    public /* synthetic */ ShadeControllerImpl$$ExternalSyntheticLambda0(ShadeControllerImpl shadeControllerImpl, int i) {
        this.$r8$classId = i;
        this.f$0 = shadeControllerImpl;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        ShadeControllerImpl shadeControllerImpl = this.f$0;
        switch (i) {
            case 0:
                shadeControllerImpl.animateCollapseShade(0, true, false, 1.0f);
                break;
            case 1:
                shadeControllerImpl.animateCollapseShade(0);
                break;
            case 2:
                shadeControllerImpl.runPostCollapseActions();
                break;
            case 3:
                shadeControllerImpl.collapseShadeInternal();
                break;
            default:
                shadeControllerImpl.animateExpandQs();
                break;
        }
    }
}
