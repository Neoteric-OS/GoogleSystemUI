package com.android.systemui.qs.external;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class TileLifecycleManager$$ExternalSyntheticLambda11 implements Runnable {
    public final /* synthetic */ TileLifecycleManager f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ TileLifecycleManager$$ExternalSyntheticLambda11(TileLifecycleManager tileLifecycleManager, boolean z) {
        this.f$0 = tileLifecycleManager;
        this.f$1 = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.setBindService(this.f$1);
    }
}
