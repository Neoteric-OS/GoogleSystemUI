package com.android.systemui.statusbar.phone;

import com.android.systemui.statusbar.StatusBarStateControllerImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ActivityStarterImpl$postQSRunnableDismissingKeyguard$1 implements Runnable {
    public final /* synthetic */ int $r8$classId = 0;
    public final /* synthetic */ Runnable $runnable;
    public final /* synthetic */ ActivityStarterImpl this$0;

    public ActivityStarterImpl$postQSRunnableDismissingKeyguard$1(ActivityStarterImpl activityStarterImpl, Runnable runnable) {
        this.this$0 = activityStarterImpl;
        this.$runnable = runnable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                ActivityStarterImpl activityStarterImpl = this.this$0;
                ((StatusBarStateControllerImpl) activityStarterImpl.statusBarStateController).mLeaveOpenOnKeyguardHide = true;
                activityStarterImpl.activityStarterInternal.executeRunnableDismissingKeyguard(new ActivityStarterImpl$postQSRunnableDismissingKeyguard$1(this.$runnable, activityStarterImpl), (r18 & 2) != 0 ? null : null, (r18 & 4) != 0 ? false : false, (r18 & 8) != 0 ? false : false, (r18 & 16) != 0 ? false : false, (r18 & 32) != 0 ? false : false, (r18 & 64) != 0 ? null : null);
                break;
            default:
                Runnable runnable = this.$runnable;
                if (runnable != null) {
                    this.this$0.mainExecutor.executeDelayed(runnable, 0);
                    break;
                }
                break;
        }
    }

    public ActivityStarterImpl$postQSRunnableDismissingKeyguard$1(Runnable runnable, ActivityStarterImpl activityStarterImpl) {
        this.$runnable = runnable;
        this.this$0 = activityStarterImpl;
    }
}
