package com.android.systemui.doze;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class DozeScreenState$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ DozeScreenState f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ DozeScreenState$$ExternalSyntheticLambda1(DozeScreenState dozeScreenState, int i) {
        this.f$0 = dozeScreenState;
        this.f$1 = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.applyScreenState(this.f$1);
    }
}
