package com.android.systemui.statusbar.lockscreen;

import android.app.ActivityOptions;
import android.app.PendingIntent;
import android.app.smartspace.SmartspaceSession;
import android.app.smartspace.SmartspaceTargetEvent;
import android.content.Intent;
import android.view.View;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class LockscreenSmartspaceController$buildView$2 implements BcSmartspaceDataPlugin.IntentStarter, BcSmartspaceDataPlugin.SmartspaceEventNotifier {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ LockscreenSmartspaceController this$0;

    public /* synthetic */ LockscreenSmartspaceController$buildView$2(LockscreenSmartspaceController lockscreenSmartspaceController, int i) {
        this.$r8$classId = i;
        this.this$0 = lockscreenSmartspaceController;
    }

    @Override // com.android.systemui.plugins.BcSmartspaceDataPlugin.SmartspaceEventNotifier
    public void notifySmartspaceEvent(SmartspaceTargetEvent smartspaceTargetEvent) {
        switch (this.$r8$classId) {
            case 1:
                SmartspaceSession smartspaceSession = this.this$0.session;
                if (smartspaceSession != null) {
                    smartspaceSession.notifySmartspaceEvent(smartspaceTargetEvent);
                    break;
                }
                break;
            case 2:
                SmartspaceSession smartspaceSession2 = this.this$0.session;
                if (smartspaceSession2 != null) {
                    smartspaceSession2.notifySmartspaceEvent(smartspaceTargetEvent);
                    break;
                }
                break;
            default:
                SmartspaceSession smartspaceSession3 = this.this$0.session;
                if (smartspaceSession3 != null) {
                    smartspaceSession3.notifySmartspaceEvent(smartspaceTargetEvent);
                    break;
                }
                break;
        }
    }

    @Override // com.android.systemui.plugins.BcSmartspaceDataPlugin.IntentStarter
    public void startIntent(View view, Intent intent, boolean z) {
        LockscreenSmartspaceController lockscreenSmartspaceController = this.this$0;
        if (z) {
            lockscreenSmartspaceController.activityStarter.startActivity(intent, true, (ActivityTransitionAnimator.Controller) null, true);
        } else {
            lockscreenSmartspaceController.activityStarter.postStartActivityDismissingKeyguard(intent, 0);
        }
    }

    @Override // com.android.systemui.plugins.BcSmartspaceDataPlugin.IntentStarter
    public void startPendingIntent(View view, PendingIntent pendingIntent, boolean z) {
        if (z) {
            pendingIntent.send(ActivityOptions.makeBasic().setPendingIntentBackgroundActivityStartMode(1).toBundle());
        } else {
            this.this$0.activityStarter.postStartActivityDismissingKeyguard(pendingIntent);
        }
    }
}
