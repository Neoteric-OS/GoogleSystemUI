package com.android.systemui.statusbar.phone;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.view.View;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.plugins.ActivityStarter;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface ActivityStarterInternal {
    void dismissKeyguardThenExecute(ActivityStarter.OnDismissAction onDismissAction, Runnable runnable, boolean z, String str);

    void executeRunnableDismissingKeyguard(Runnable runnable, Runnable runnable2, boolean z, boolean z2, boolean z3, boolean z4, String str);

    boolean shouldAnimateLaunch(boolean z);

    void startActivity(Intent intent, boolean z, ActivityTransitionAnimator.Controller controller, boolean z2, UserHandle userHandle);

    void startActivityDismissingKeyguard(Intent intent, boolean z, boolean z2, ActivityStarter.Callback callback, int i, ActivityTransitionAnimator.Controller controller, String str, boolean z3, UserHandle userHandle);

    void startPendingIntentDismissingKeyguard(PendingIntent pendingIntent, boolean z, Runnable runnable, View view, ActivityTransitionAnimator.Controller controller, boolean z2, boolean z3, Intent intent, Bundle bundle, String str);
}
