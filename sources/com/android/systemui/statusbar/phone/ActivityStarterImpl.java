package com.android.systemui.statusbar.phone;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.view.View;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.util.concurrency.DelayableExecutor;
import dagger.Lazy;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ActivityStarterImpl implements ActivityStarter {
    public final ActivityStarterInternal activityStarterInternal;
    public final DelayableExecutor mainExecutor;
    public final SysuiStatusBarStateController statusBarStateController;

    public ActivityStarterImpl(SysuiStatusBarStateController sysuiStatusBarStateController, DelayableExecutor delayableExecutor, Lazy lazy) {
        this.statusBarStateController = sysuiStatusBarStateController;
        this.mainExecutor = delayableExecutor;
        Object obj = lazy.get();
        Intrinsics.checkNotNull(obj);
        this.activityStarterInternal = (ActivityStarterInternal) obj;
    }

    @Override // com.android.systemui.plugins.ActivityStarter
    public final void dismissKeyguardThenExecute(ActivityStarter.OnDismissAction onDismissAction, Runnable runnable, boolean z) {
        this.activityStarterInternal.dismissKeyguardThenExecute(onDismissAction, runnable, z, null);
    }

    @Override // com.android.systemui.plugins.ActivityStarter
    public final void executeRunnableDismissingKeyguard(Runnable runnable, Runnable runnable2, boolean z, boolean z2, boolean z3) {
        this.activityStarterInternal.executeRunnableDismissingKeyguard(runnable, (r18 & 2) != 0 ? null : runnable2, (r18 & 4) != 0 ? false : z, (r18 & 8) != 0 ? false : z2, (r18 & 16) != 0 ? false : z3, (r18 & 32) != 0 ? false : false, (r18 & 64) != 0 ? null : null);
    }

    @Override // com.android.systemui.plugins.ActivityStarter
    public final void postQSRunnableDismissingKeyguard(Runnable runnable) {
        this.mainExecutor.executeDelayed(new ActivityStarterImpl$postQSRunnableDismissingKeyguard$1(this, runnable), 0);
    }

    @Override // com.android.systemui.plugins.ActivityStarter
    public final void postStartActivityDismissingKeyguard(PendingIntent pendingIntent) {
        this.mainExecutor.executeDelayed(new ActivityStarterImpl$postStartActivityDismissingKeyguard$1(this, pendingIntent), 0);
    }

    @Override // com.android.systemui.plugins.ActivityStarter
    public final boolean shouldAnimateLaunch(boolean z) {
        return this.activityStarterInternal.shouldAnimateLaunch(z);
    }

    @Override // com.android.systemui.plugins.ActivityStarter
    public final void startActivity(Intent intent, boolean z, ActivityTransitionAnimator.Controller controller, boolean z2) {
        this.activityStarterInternal.startActivity(intent, z, controller, z2, null);
    }

    @Override // com.android.systemui.plugins.ActivityStarter
    public final void startActivityDismissingKeyguard(Intent intent, boolean z, boolean z2, String str) {
        this.activityStarterInternal.startActivityDismissingKeyguard(intent, z2, (r23 & 4) != 0 ? false : z, (r23 & 8) != 0 ? null : null, (r23 & 16) != 0 ? 0 : 0, (r23 & 32) != 0 ? null : null, (r23 & 64) != 0 ? null : str, (r23 & 128) != 0 ? false : false, (r23 & 256) != 0 ? null : null);
    }

    @Override // com.android.systemui.plugins.ActivityStarter
    public final void startPendingIntentDismissingKeyguard(PendingIntent pendingIntent) {
        this.activityStarterInternal.startPendingIntentDismissingKeyguard(pendingIntent, true, (r23 & 4) != 0 ? null : null, (r23 & 8) != 0 ? null : null, (r23 & 16) != 0 ? null : null, (r23 & 32) == 0, (r23 & 64) == 0, (r23 & 128) != 0 ? null : null, (r23 & 256) != 0 ? null : null, (r23 & 512) != 0 ? null : null);
    }

    @Override // com.android.systemui.plugins.ActivityStarter
    public final void startPendingIntentMaybeDismissingKeyguard(PendingIntent pendingIntent, Runnable runnable, ActivityTransitionAnimator.Controller controller) {
        this.activityStarterInternal.startPendingIntentDismissingKeyguard(pendingIntent, true, (r23 & 4) != 0 ? null : runnable, (r23 & 8) != 0 ? null : null, (r23 & 16) != 0 ? null : controller, (r23 & 32) == 0, (r23 & 64) == 0, (r23 & 128) != 0 ? null : null, (r23 & 256) != 0 ? null : null, (r23 & 512) != 0 ? null : null);
    }

    @Override // com.android.systemui.plugins.ActivityStarter
    public final void startPendingIntentWithoutDismissing(PendingIntent pendingIntent, boolean z, Runnable runnable, ActivityTransitionAnimator.Controller controller, Intent intent, Bundle bundle) {
        this.activityStarterInternal.startPendingIntentDismissingKeyguard(pendingIntent, z, (r23 & 4) != 0 ? null : runnable, (r23 & 8) != 0 ? null : null, (r23 & 16) != 0 ? null : controller, (r23 & 32) == 0, (r23 & 64) == 0, (r23 & 128) != 0 ? null : intent, (r23 & 256) != 0 ? null : bundle, (r23 & 512) != 0 ? null : null);
    }

    @Override // com.android.systemui.plugins.ActivityStarter
    public final void dismissKeyguardThenExecute(ActivityStarter.OnDismissAction onDismissAction, Runnable runnable, boolean z, String str) {
        this.activityStarterInternal.dismissKeyguardThenExecute(onDismissAction, runnable, z, str);
    }

    @Override // com.android.systemui.plugins.ActivityStarter
    public final void startActivity(Intent intent, boolean z) {
        this.activityStarterInternal.startActivityDismissingKeyguard(intent, z, (r23 & 4) != 0 ? false : false, (r23 & 8) != 0 ? null : null, (r23 & 16) != 0 ? 0 : 0, (r23 & 32) != 0 ? null : null, (r23 & 64) != 0 ? null : null, (r23 & 128) != 0 ? false : false, (r23 & 256) != 0 ? null : null);
    }

    @Override // com.android.systemui.plugins.ActivityStarter
    public final void startActivityDismissingKeyguard(Intent intent, boolean z, boolean z2, boolean z3, ActivityStarter.Callback callback, int i, ActivityTransitionAnimator.Controller controller, UserHandle userHandle) {
        this.activityStarterInternal.startActivityDismissingKeyguard(intent, z2, (r23 & 4) != 0 ? false : z, (r23 & 8) != 0 ? null : callback, (r23 & 16) != 0 ? 0 : i, (r23 & 32) != 0 ? null : controller, (r23 & 64) != 0 ? null : null, (r23 & 128) != 0 ? false : z3, (r23 & 256) != 0 ? null : userHandle);
    }

    @Override // com.android.systemui.plugins.ActivityStarter
    public final void startPendingIntentDismissingKeyguard(PendingIntent pendingIntent, Runnable runnable) {
        this.activityStarterInternal.startPendingIntentDismissingKeyguard(pendingIntent, true, (r23 & 4) != 0 ? null : runnable, (r23 & 8) != 0 ? null : null, (r23 & 16) != 0 ? null : null, (r23 & 32) == 0, (r23 & 64) == 0, (r23 & 128) != 0 ? null : null, (r23 & 256) != 0 ? null : null, (r23 & 512) != 0 ? null : null);
    }

    @Override // com.android.systemui.plugins.ActivityStarter
    public final void startPendingIntentMaybeDismissingKeyguard(PendingIntent pendingIntent, boolean z, Runnable runnable, ActivityTransitionAnimator.Controller controller, Intent intent, Bundle bundle, String str) {
        this.activityStarterInternal.startPendingIntentDismissingKeyguard(pendingIntent, z, (r23 & 4) != 0 ? null : runnable, (r23 & 8) != 0 ? null : null, (r23 & 16) != 0 ? null : controller, (r23 & 32) == 0, (r23 & 64) == 0, (r23 & 128) != 0 ? null : intent, (r23 & 256) != 0 ? null : bundle, (r23 & 512) != 0 ? null : str);
    }

    @Override // com.android.systemui.plugins.ActivityStarter
    public final void postStartActivityDismissingKeyguard(PendingIntent pendingIntent, ActivityTransitionAnimator.Controller controller) {
        this.mainExecutor.executeDelayed(new ActivityStarterImpl$postStartActivityDismissingKeyguard$2(this, pendingIntent, controller), 0);
    }

    @Override // com.android.systemui.plugins.ActivityStarter
    public final void startActivity(Intent intent, boolean z, boolean z2) {
        this.activityStarterInternal.startActivityDismissingKeyguard(intent, z2, (r23 & 4) != 0 ? false : z, (r23 & 8) != 0 ? null : null, (r23 & 16) != 0 ? 0 : 0, (r23 & 32) != 0 ? null : null, (r23 & 64) != 0 ? null : null, (r23 & 128) != 0 ? false : false, (r23 & 256) != 0 ? null : null);
    }

    @Override // com.android.systemui.plugins.ActivityStarter
    public final void startPendingIntentDismissingKeyguard(PendingIntent pendingIntent, Runnable runnable, View view) {
        this.activityStarterInternal.startPendingIntentDismissingKeyguard(pendingIntent, true, (r23 & 4) != 0 ? null : runnable, (r23 & 8) != 0 ? null : view, (r23 & 16) != 0 ? null : null, (r23 & 32) == 0, (r23 & 64) == 0, (r23 & 128) != 0 ? null : null, (r23 & 256) != 0 ? null : null, (r23 & 512) != 0 ? null : null);
    }

    @Override // com.android.systemui.plugins.ActivityStarter
    public final void startActivity(Intent intent, boolean z, ActivityStarter.Callback callback) {
        this.activityStarterInternal.startActivityDismissingKeyguard(intent, z, (r23 & 4) != 0 ? false : false, (r23 & 8) != 0 ? null : callback, (r23 & 16) != 0 ? 0 : 0, (r23 & 32) != 0 ? null : null, (r23 & 64) != 0 ? null : null, (r23 & 128) != 0 ? false : false, (r23 & 256) != 0 ? null : null);
    }

    @Override // com.android.systemui.plugins.ActivityStarter
    public final void startPendingIntentDismissingKeyguard(PendingIntent pendingIntent, Runnable runnable, ActivityTransitionAnimator.Controller controller) {
        this.activityStarterInternal.startPendingIntentDismissingKeyguard(pendingIntent, true, (r23 & 4) != 0 ? null : runnable, (r23 & 8) != 0 ? null : null, (r23 & 16) != 0 ? null : controller, (r23 & 32) == 0, (r23 & 64) == 0, (r23 & 128) != 0 ? null : null, (r23 & 256) != 0 ? null : null, (r23 & 512) != 0 ? null : null);
    }

    @Override // com.android.systemui.plugins.ActivityStarter
    public final void postStartActivityDismissingKeyguard(Intent intent, int i) {
        this.mainExecutor.executeDelayed(new ActivityStarterImpl$postStartActivityDismissingKeyguard$1(this, intent), i);
    }

    @Override // com.android.systemui.plugins.ActivityStarter
    public final void startActivity(Intent intent, boolean z, boolean z2, int i) {
        this.activityStarterInternal.startActivityDismissingKeyguard(intent, z2, (r23 & 4) != 0 ? false : z, (r23 & 8) != 0 ? null : null, (r23 & 16) != 0 ? 0 : i, (r23 & 32) != 0 ? null : null, (r23 & 64) != 0 ? null : null, (r23 & 128) != 0 ? false : false, (r23 & 256) != 0 ? null : null);
    }

    @Override // com.android.systemui.plugins.ActivityStarter
    public final void startActivity(Intent intent, boolean z, ActivityTransitionAnimator.Controller controller, boolean z2, UserHandle userHandle) {
        this.activityStarterInternal.startActivity(intent, z, controller, z2, userHandle);
    }

    @Override // com.android.systemui.plugins.ActivityStarter
    public final void postStartActivityDismissingKeyguard(Intent intent, int i, ActivityTransitionAnimator.Controller controller) {
        this.mainExecutor.executeDelayed(new ActivityStarterImpl$postStartActivityDismissingKeyguard$2(this, intent, controller), i);
    }

    @Override // com.android.systemui.plugins.ActivityStarter
    public final void postStartActivityDismissingKeyguard(final Intent intent, int i, final ActivityTransitionAnimator.Controller controller, final String str) {
        this.mainExecutor.executeDelayed(new Runnable() { // from class: com.android.systemui.statusbar.phone.ActivityStarterImpl$postStartActivityDismissingKeyguard$5
            @Override // java.lang.Runnable
            public final void run() {
                ActivityStarterImpl activityStarterImpl = ActivityStarterImpl.this;
                activityStarterImpl.activityStarterInternal.startActivityDismissingKeyguard(intent, true, (r23 & 4) != 0 ? false : true, (r23 & 8) != 0 ? null : null, (r23 & 16) != 0 ? 0 : 0, (r23 & 32) != 0 ? null : controller, (r23 & 64) != 0 ? null : str, (r23 & 128) != 0 ? false : false, (r23 & 256) != 0 ? null : null);
            }
        }, i);
    }
}
