package com.android.systemui.statusbar.policy;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class LocationControllerImpl$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ LocationControllerImpl f$0;

    public /* synthetic */ LocationControllerImpl$$ExternalSyntheticLambda1(LocationControllerImpl locationControllerImpl, int i) {
        this.$r8$classId = i;
        this.f$0 = locationControllerImpl;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        LocationControllerImpl locationControllerImpl = this.f$0;
        switch (i) {
            case 0:
                locationControllerImpl.updateActiveLocationRequests();
                break;
            default:
                locationControllerImpl.areActiveLocationRequests();
                break;
        }
    }
}
