package com.android.systemui.util.condition;

import android.os.PowerManager;
import android.os.SystemClock;
import android.util.ArraySet;
import android.util.Log;
import com.android.systemui.dreams.DreamMonitor;
import com.android.systemui.flags.RestartDozeListener;
import com.android.systemui.shared.condition.Monitor;
import com.android.systemui.shared.condition.Monitor$$ExternalSyntheticLambda2;
import com.android.systemui.util.settings.SecureSettings;
import com.android.systemui.util.time.SystemClockImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class ConditionalCoreStartable$$ExternalSyntheticLambda0 implements Monitor.Callback {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ DreamMonitor f$0;

    public /* synthetic */ ConditionalCoreStartable$$ExternalSyntheticLambda0(DreamMonitor dreamMonitor, int i) {
        this.$r8$classId = i;
        this.f$0 = dreamMonitor;
    }

    @Override // com.android.systemui.shared.condition.Monitor.Callback
    public final void onConditionsChanged(boolean z) {
        switch (this.$r8$classId) {
            case 0:
                DreamMonitor dreamMonitor = this.f$0;
                if (!z) {
                    dreamMonitor.getClass();
                    break;
                } else {
                    Monitor monitor = dreamMonitor.mMonitor;
                    monitor.mExecutor.execute(new Monitor$$ExternalSyntheticLambda2(0, monitor, dreamMonitor.mBootCompletedToken));
                    dreamMonitor.mBootCompletedToken = null;
                    break;
                }
            default:
                DreamMonitor dreamMonitor2 = this.f$0;
                if (!z) {
                    dreamMonitor2.getClass();
                    break;
                } else {
                    Monitor monitor2 = dreamMonitor2.mMonitor;
                    monitor2.mExecutor.execute(new Monitor$$ExternalSyntheticLambda2(0, monitor2, dreamMonitor2.mStartToken));
                    dreamMonitor2.mStartToken = null;
                    if (Log.isLoggable("DreamMonitor", 3)) {
                        Log.d("DreamMonitor", "started");
                    }
                    ArraySet arraySet = new ArraySet();
                    arraySet.add(dreamMonitor2.mDreamCondition);
                    Monitor.Subscription subscription = new Monitor.Subscription(arraySet, dreamMonitor2.mCallback, null);
                    Monitor monitor3 = dreamMonitor2.mConditionMonitor;
                    monitor3.addSubscription(subscription, monitor3.mPreconditions);
                    final RestartDozeListener restartDozeListener = dreamMonitor2.mRestartDozeListener;
                    if (!restartDozeListener.inited) {
                        restartDozeListener.inited = true;
                        restartDozeListener.statusBarStateController.addCallback(restartDozeListener.listener);
                    }
                    restartDozeListener.bgExecutor.executeDelayed(new Runnable() { // from class: com.android.systemui.flags.RestartDozeListener$maybeRestartSleep$1
                        @Override // java.lang.Runnable
                        public final void run() {
                            SecureSettings secureSettings = RestartDozeListener.this.settings;
                            if (secureSettings.getBoolForUser(secureSettings.getUserId(), "restart_nap_after_start", false)) {
                                Log.d("RestartDozeListener", "Restarting sleep state");
                                RestartDozeListener restartDozeListener2 = RestartDozeListener.this;
                                PowerManager powerManager = restartDozeListener2.powerManager;
                                ((SystemClockImpl) restartDozeListener2.systemClock).getClass();
                                powerManager.wakeUp(SystemClock.uptimeMillis(), 2, "RestartDozeListener");
                                RestartDozeListener restartDozeListener3 = RestartDozeListener.this;
                                PowerManager powerManager2 = restartDozeListener3.powerManager;
                                ((SystemClockImpl) restartDozeListener3.systemClock).getClass();
                                powerManager2.goToSleep(SystemClock.uptimeMillis());
                            }
                        }
                    }, 1000L);
                    break;
                }
        }
    }
}
