package com.android.systemui.screenshot;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.ActivityOptions;
import android.app.BroadcastOptions;
import android.app.ExitTransitionCoordinator;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.UserHandle;
import android.util.Log;
import android.util.Pair;
import android.view.Window;
import com.android.app.tracing.coroutines.CoroutineTracingKt;
import com.android.systemui.screenshot.ui.ScreenshotAnimationController;
import com.android.systemui.screenshot.ui.ScreenshotAnimationController$getActionsAnimator$1;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ActionExecutor {
    public final CoroutineScope applicationScope;
    public final Function0 finishDismiss;
    public final ActionIntentExecutor intentExecutor;
    public boolean isPendingSharedTransition;
    public final ScreenshotShelfViewProxy viewProxy;
    public final Window window;

    public ActionExecutor(ActionIntentExecutor actionIntentExecutor, CoroutineScope coroutineScope, Window window, ScreenshotShelfViewProxy screenshotShelfViewProxy, Function0 function0) {
        this.intentExecutor = actionIntentExecutor;
        this.applicationScope = coroutineScope;
        this.window = window;
        this.viewProxy = screenshotShelfViewProxy;
        this.finishDismiss = function0;
    }

    public final void sendPendingIntent(PendingIntent pendingIntent) {
        try {
            BroadcastOptions makeBasic = BroadcastOptions.makeBasic();
            makeBasic.setInteractive(true);
            makeBasic.setPendingIntentBackgroundActivityStartMode(1);
            pendingIntent.send(makeBasic.toBundle());
            this.viewProxy.requestDismissal(null, null);
        } catch (PendingIntent.CanceledException e) {
            Log.e("ActionExecutor", "Intent cancelled", e);
        }
    }

    public final void startSharedTransition(Intent intent, UserHandle userHandle, boolean z) {
        this.isPendingSharedTransition = true;
        ScreenshotShelfViewProxy screenshotShelfViewProxy = this.viewProxy;
        ScreenshotAnimationController screenshotAnimationController = screenshotShelfViewProxy.animationController;
        Animator animator = screenshotAnimationController.animator;
        if (animator != null) {
            animator.cancel();
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(1.0f, 0.0f);
        ofFloat.addUpdateListener(new ScreenshotAnimationController$getActionsAnimator$1(screenshotAnimationController, 2));
        screenshotAnimationController.animator = ofFloat;
        ofFloat.start();
        CoroutineTracingKt.launch$default(this.applicationScope, null, new ActionExecutor$startSharedTransition$1(this, intent, userHandle, z, ActivityOptions.startSharedElementAnimation(this.window, new ExitTransitionCoordinator.ExitTransitionCallbacks() { // from class: com.android.systemui.screenshot.ActionExecutor$createWindowTransition$callbacks$1
            public final void hideSharedElements() {
                ActionExecutor actionExecutor = ActionExecutor.this;
                actionExecutor.isPendingSharedTransition = false;
                actionExecutor.finishDismiss.invoke();
            }

            public final boolean isReturnTransitionAllowed() {
                return false;
            }

            public final void onFinish() {
            }
        }, null, new Pair[]{Pair.create(screenshotShelfViewProxy.screenshotPreview, "screenshot_preview_image")}), null), 6);
    }
}
