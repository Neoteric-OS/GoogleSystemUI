package com.android.keyguard;

import com.android.keyguard.ClockEventController;
import com.android.systemui.statusbar.policy.ZenModeControllerImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ClockEventController$registerListeners$2 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object this$0;

    public /* synthetic */ ClockEventController$registerListeners$2(int i, Object obj) {
        this.$r8$classId = i;
        this.this$0 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                ClockEventController clockEventController = (ClockEventController) this.this$0;
                clockEventController.zenModeCallback.onZenChanged(((ZenModeControllerImpl) clockEventController.zenModeController).mZenMode);
                ((ClockEventController) this.this$0).zenModeCallback.onNextAlarmChanged();
                break;
            default:
                ClockEventController.TimeListener timeListener = (ClockEventController.TimeListener) this.this$0;
                if (timeListener.isRunning) {
                    timeListener.executor.executeDelayed(this, 990L);
                    ((ClockEventController.TimeListener) this.this$0).clockFace.getEvents().onTimeTick();
                    break;
                }
                break;
        }
    }
}
