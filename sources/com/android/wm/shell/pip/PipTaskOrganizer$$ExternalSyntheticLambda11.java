package com.android.wm.shell.pip;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class PipTaskOrganizer$$ExternalSyntheticLambda11 implements Runnable {
    public final /* synthetic */ PipTaskOrganizer f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ boolean f$2;

    public /* synthetic */ PipTaskOrganizer$$ExternalSyntheticLambda11(PipTaskOrganizer pipTaskOrganizer, int i, boolean z) {
        this.f$0 = pipTaskOrganizer;
        this.f$1 = i;
        this.f$2 = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        PipTaskOrganizer pipTaskOrganizer = this.f$0;
        int i = this.f$1;
        boolean z = this.f$2;
        pipTaskOrganizer.mPipTransitionState.setTransitionState(4);
        pipTaskOrganizer.exitPip(i, z);
    }
}
