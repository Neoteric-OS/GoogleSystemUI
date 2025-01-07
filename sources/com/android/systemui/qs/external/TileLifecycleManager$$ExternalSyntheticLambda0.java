package com.android.systemui.qs.external;

import android.app.ActivityManager;
import android.util.Log;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.time.SystemClockImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class TileLifecycleManager$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ TileLifecycleManager f$0;

    public /* synthetic */ TileLifecycleManager$$ExternalSyntheticLambda0(TileLifecycleManager tileLifecycleManager, int i) {
        this.$r8$classId = i;
        this.f$0 = tileLifecycleManager;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        TileLifecycleManager tileLifecycleManager = this.f$0;
        switch (i) {
            case 0:
                if (tileLifecycleManager.mBound.get()) {
                    if (tileLifecycleManager.mDebug) {
                        Log.d("TileLifecycleManager", "Trying to rebind " + tileLifecycleManager.mIntent.getComponent());
                    }
                    tileLifecycleManager.setBindService(true);
                    break;
                }
                break;
            case 1:
                if (tileLifecycleManager.mBound.get()) {
                    tileLifecycleManager.setBindService(true);
                }
                tileLifecycleManager.isDeathRebindScheduled.set(false);
                break;
            case 2:
                if (tileLifecycleManager.mIsBound.get()) {
                    if (tileLifecycleManager.mDebug) {
                        Log.d("TileLifecycleManager", "handleDeath " + tileLifecycleManager.mIntent.getComponent());
                    }
                    tileLifecycleManager.unbindService();
                    if (tileLifecycleManager.mBound.get() && tileLifecycleManager.checkComponentState() && tileLifecycleManager.isDeathRebindScheduled.compareAndSet(false, true)) {
                        DelayableExecutor delayableExecutor = tileLifecycleManager.mExecutor;
                        TileLifecycleManager$$ExternalSyntheticLambda0 tileLifecycleManager$$ExternalSyntheticLambda0 = new TileLifecycleManager$$ExternalSyntheticLambda0(tileLifecycleManager, 1);
                        ((SystemClockImpl) tileLifecycleManager.mSystemClock).getClass();
                        System.currentTimeMillis();
                        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
                        tileLifecycleManager.mActivityManager.getMemoryInfo(memoryInfo);
                        long j = memoryInfo.lowMemory ? 20000L : 5000L;
                        if (tileLifecycleManager.mDebug) {
                            Log.i("TileLifecycleManager", "Rebinding with a delay=" + j + " - " + tileLifecycleManager.mIntent.getComponent());
                        }
                        delayableExecutor.executeDelayed(tileLifecycleManager$$ExternalSyntheticLambda0, j);
                        break;
                    }
                }
                break;
            case 3:
                tileLifecycleManager.mUnbindImmediate.set(true);
                tileLifecycleManager.setBindService(true);
                break;
            default:
                if (tileLifecycleManager.mUnbindImmediate.get()) {
                    tileLifecycleManager.mUnbindImmediate.set(false);
                    tileLifecycleManager.setBindService(false);
                    break;
                }
                break;
        }
    }
}
