package com.android.systemui.statusbar.phone;

import android.app.PendingIntent;
import android.content.Intent;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ActivityStarterImpl$postStartActivityDismissingKeyguard$1 implements Runnable {
    public final /* synthetic */ Object $intent;
    public final /* synthetic */ int $r8$classId = 0;
    public final /* synthetic */ ActivityStarterImpl this$0;

    public ActivityStarterImpl$postStartActivityDismissingKeyguard$1(ActivityStarterImpl activityStarterImpl, PendingIntent pendingIntent) {
        this.this$0 = activityStarterImpl;
        this.$intent = pendingIntent;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                ActivityStarterImpl activityStarterImpl = this.this$0;
                activityStarterImpl.activityStarterInternal.startPendingIntentDismissingKeyguard((PendingIntent) this.$intent, true, (r23 & 4) != 0 ? null : null, (r23 & 8) != 0 ? null : null, (r23 & 16) != 0 ? null : null, (r23 & 32) == 0, (r23 & 64) == 0, (r23 & 128) != 0 ? null : null, (r23 & 256) != 0 ? null : null, (r23 & 512) != 0 ? null : null);
                break;
            default:
                ActivityStarterImpl activityStarterImpl2 = this.this$0;
                activityStarterImpl2.activityStarterInternal.startActivityDismissingKeyguard((Intent) this.$intent, true, (r23 & 4) != 0 ? false : true, (r23 & 8) != 0 ? null : null, (r23 & 16) != 0 ? 0 : 0, (r23 & 32) != 0 ? null : null, (r23 & 64) != 0 ? null : null, (r23 & 128) != 0 ? false : false, (r23 & 256) != 0 ? null : null);
                break;
        }
    }

    public ActivityStarterImpl$postStartActivityDismissingKeyguard$1(ActivityStarterImpl activityStarterImpl, Intent intent) {
        this.this$0 = activityStarterImpl;
        this.$intent = intent;
    }
}
