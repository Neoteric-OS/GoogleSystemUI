package com.android.systemui.qs.tiles.base.actions;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.UserHandle;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.animation.Expandable;
import com.android.systemui.plugins.ActivityStarter;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QSTileIntentUserInputHandlerImpl {
    public final ActivityStarter activityStarter;
    public final PackageManager packageManager;
    public final UserHandle userHandle;

    public QSTileIntentUserInputHandlerImpl(ActivityStarter activityStarter, PackageManager packageManager, UserHandle userHandle) {
        this.activityStarter = activityStarter;
        this.packageManager = packageManager;
        this.userHandle = userHandle;
    }

    public static void handle$default(QSTileIntentUserInputHandlerImpl qSTileIntentUserInputHandlerImpl, Expandable expandable, Intent intent) {
        ActivityTransitionAnimator.Controller controller;
        if (expandable != null) {
            qSTileIntentUserInputHandlerImpl.getClass();
            controller = expandable.activityTransitionController(32);
        } else {
            controller = null;
        }
        qSTileIntentUserInputHandlerImpl.activityStarter.postStartActivityDismissingKeyguard(intent, 0, controller);
    }
}
