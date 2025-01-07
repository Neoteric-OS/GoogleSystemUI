package com.android.systemui.communal.smartspace;

import android.app.ActivityOptions;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.View;
import android.widget.RemoteViews;
import com.android.systemui.communal.domain.interactor.CommunalSceneInteractor;
import com.android.systemui.communal.util.InteractionHandlerDelegate;
import com.android.systemui.communal.widgets.CommunalTransitionAnimatorController;
import com.android.systemui.communal.widgets.SmartspaceAppWidgetHostView;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.Logger;
import com.android.systemui.plugins.ActivityStarter;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SmartspaceInteractionHandler implements RemoteViews.InteractionHandler {
    public final ActivityStarter activityStarter;
    public final InteractionHandlerDelegate delegate;

    public SmartspaceInteractionHandler(ActivityStarter activityStarter, CommunalSceneInteractor communalSceneInteractor, LogBuffer logBuffer) {
        this.activityStarter = activityStarter;
        this.delegate = new InteractionHandlerDelegate(communalSceneInteractor, new Function1() { // from class: com.android.systemui.communal.smartspace.SmartspaceInteractionHandler$delegate$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(((View) obj) instanceof SmartspaceAppWidgetHostView);
            }
        }, new InteractionHandlerDelegate.IntentStarter() { // from class: com.android.systemui.communal.smartspace.SmartspaceInteractionHandler$delegate$2
            @Override // com.android.systemui.communal.util.InteractionHandlerDelegate.IntentStarter
            public final void startActivity(PendingIntent pendingIntent, Intent intent, ActivityOptions activityOptions, CommunalTransitionAnimatorController communalTransitionAnimatorController) {
                SmartspaceInteractionHandler.this.activityStarter.startPendingIntentWithoutDismissing(pendingIntent, false, null, communalTransitionAnimatorController, intent, activityOptions.toBundle());
            }
        }, new Logger(logBuffer, "SmartspaceInteractionHandler"));
    }

    public final boolean onInteraction(View view, PendingIntent pendingIntent, RemoteViews.RemoteResponse remoteResponse) {
        return this.delegate.onInteraction(view, pendingIntent, remoteResponse);
    }
}
