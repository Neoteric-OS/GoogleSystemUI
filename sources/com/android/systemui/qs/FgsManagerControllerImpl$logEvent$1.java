package com.android.systemui.qs;

import android.util.StatsEvent;
import android.util.StatsLog;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FgsManagerControllerImpl$logEvent$1 implements Runnable {
    public final /* synthetic */ int $event;
    public final /* synthetic */ String $packageName;
    public final /* synthetic */ long $timeLogged;
    public final /* synthetic */ long $timeStarted;
    public final /* synthetic */ int $userId;
    public final /* synthetic */ FgsManagerControllerImpl this$0;

    public FgsManagerControllerImpl$logEvent$1(FgsManagerControllerImpl fgsManagerControllerImpl, String str, int i, int i2, long j, long j2) {
        this.this$0 = fgsManagerControllerImpl;
        this.$packageName = str;
        this.$userId = i;
        this.$event = i2;
        this.$timeLogged = j;
        this.$timeStarted = j2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int packageUidAsUser = this.this$0.packageManager.getPackageUidAsUser(this.$packageName, this.$userId);
        int i = this.$event;
        long j = this.$timeLogged - this.$timeStarted;
        StatsEvent.Builder newBuilder = StatsEvent.newBuilder();
        newBuilder.setAtomId(450);
        newBuilder.writeInt(packageUidAsUser);
        newBuilder.addBooleanAnnotation((byte) 1, true);
        newBuilder.writeInt(i);
        newBuilder.writeLong(j);
        newBuilder.usePooledBuffer();
        StatsLog.write(newBuilder.build());
    }
}
