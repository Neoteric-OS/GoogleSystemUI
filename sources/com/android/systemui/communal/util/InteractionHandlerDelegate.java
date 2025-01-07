package com.android.systemui.communal.util;

import android.app.ActivityOptions;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Pair;
import android.view.View;
import android.widget.RemoteViews;
import androidx.constraintlayout.motion.widget.MotionLayout$$ExternalSyntheticOutline0;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.animation.GhostedViewTransitionAnimatorController;
import com.android.systemui.communal.domain.interactor.CommunalSceneInteractor;
import com.android.systemui.communal.widgets.CommunalTransitionAnimatorController;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.log.core.Logger;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class InteractionHandlerDelegate implements RemoteViews.InteractionHandler {
    public final CommunalSceneInteractor communalSceneInteractor;
    public final Lambda findViewToAnimate;
    public final IntentStarter intentStarter;
    public final Logger logger;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface IntentStarter {
        void startActivity(PendingIntent pendingIntent, Intent intent, ActivityOptions activityOptions, CommunalTransitionAnimatorController communalTransitionAnimatorController);

        default boolean startPendingIntent(View view, PendingIntent pendingIntent, Intent intent, ActivityOptions activityOptions) {
            return RemoteViews.startPendingIntent(view, pendingIntent, new Pair(intent, activityOptions));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public InteractionHandlerDelegate(CommunalSceneInteractor communalSceneInteractor, Function1 function1, IntentStarter intentStarter, Logger logger) {
        this.communalSceneInteractor = communalSceneInteractor;
        this.findViewToAnimate = (Lambda) function1;
        this.intentStarter = intentStarter;
        this.logger = logger;
    }

    /* JADX WARN: Type inference failed for: r1v6, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    public final boolean onInteraction(View view, PendingIntent pendingIntent, RemoteViews.RemoteResponse remoteResponse) {
        View view2;
        GhostedViewTransitionAnimatorController fromView$default;
        Logger logger = this.logger;
        CommunalTransitionAnimatorController communalTransitionAnimatorController = null;
        LogMessage obtain = logger.getBuffer().obtain(logger.getTag(), LogLevel.INFO, new Function1() { // from class: com.android.systemui.communal.util.InteractionHandlerDelegate$onInteraction$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return MotionLayout$$ExternalSyntheticOutline0.m("Starting ", logMessage.getStr1(), " (", logMessage.getStr2(), ")");
            }
        }, null);
        obtain.setStr1(pendingIntent.isActivity() ? "activity" : pendingIntent.isBroadcast() ? "broadcast" : pendingIntent.isForegroundService() ? "fgService" : pendingIntent.isService() ? "service" : "unknown");
        obtain.setStr2(pendingIntent.getCreatorPackage());
        logger.getBuffer().commit(obtain);
        Pair launchOptions = remoteResponse.getLaunchOptions(view);
        Intrinsics.checkNotNull(launchOptions);
        Intent intent = (Intent) launchOptions.first;
        ActivityOptions activityOptions = (ActivityOptions) launchOptions.second;
        Object obj = view;
        if (!pendingIntent.isActivity()) {
            IntentStarter intentStarter = this.intentStarter;
            Intrinsics.checkNotNull(intent);
            Intrinsics.checkNotNull(activityOptions);
            return intentStarter.startPendingIntent(view, pendingIntent, intent, activityOptions);
        }
        while (true) {
            if (!(obj instanceof View)) {
                view2 = null;
                break;
            }
            if (((Boolean) this.findViewToAnimate.invoke(obj)).booleanValue()) {
                view2 = (View) obj;
                break;
            }
            obj = ((View) obj).getParent();
        }
        if (view2 != null && (fromView$default = ActivityTransitionAnimator.Controller.Companion.fromView$default(view2, null, 30)) != null) {
            this.communalSceneInteractor.setIsLaunchingWidget(true);
            communalTransitionAnimatorController = new CommunalTransitionAnimatorController(fromView$default, this.communalSceneInteractor);
        }
        IntentStarter intentStarter2 = this.intentStarter;
        Intrinsics.checkNotNull(intent);
        Intrinsics.checkNotNull(activityOptions);
        intentStarter2.startActivity(pendingIntent, intent, activityOptions, communalTransitionAnimatorController);
        return true;
    }
}
