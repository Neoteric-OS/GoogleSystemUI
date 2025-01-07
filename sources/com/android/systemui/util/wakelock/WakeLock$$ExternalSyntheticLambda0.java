package com.android.systemui.util.wakelock;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class WakeLock$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ Runnable f$0;
    public final /* synthetic */ WakeLock f$1;

    public /* synthetic */ WakeLock$$ExternalSyntheticLambda0(Runnable runnable, WakeLock wakeLock) {
        this.f$0 = runnable;
        this.f$1 = wakeLock;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Runnable runnable = this.f$0;
        WakeLock wakeLock = this.f$1;
        try {
            runnable.run();
        } finally {
            wakeLock.release("wrap");
        }
    }
}
