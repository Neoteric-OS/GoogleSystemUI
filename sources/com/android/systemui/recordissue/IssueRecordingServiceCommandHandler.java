package com.android.systemui.recordissue;

import android.app.IActivityManager;
import android.app.NotificationManager;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.qs.pipeline.domain.interactor.PanelInteractor;
import com.android.systemui.settings.UserContextProvider;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class IssueRecordingServiceCommandHandler {
    public final Executor bgExecutor;
    public final DialogTransitionAnimator dialogTransitionAnimator;
    public final IActivityManager iActivityManager;
    public final IssueRecordingState issueRecordingState;
    public final NotificationManager notificationManager;
    public final PanelInteractor panelInteractor;
    public final TraceurMessageSender traceurMessageSender;
    public final UserContextProvider userContextProvider;

    public IssueRecordingServiceCommandHandler(Executor executor, DialogTransitionAnimator dialogTransitionAnimator, PanelInteractor panelInteractor, TraceurMessageSender traceurMessageSender, IssueRecordingState issueRecordingState, IActivityManager iActivityManager, NotificationManager notificationManager, UserContextProvider userContextProvider) {
        this.bgExecutor = executor;
        this.dialogTransitionAnimator = dialogTransitionAnimator;
        this.panelInteractor = panelInteractor;
        this.traceurMessageSender = traceurMessageSender;
        this.issueRecordingState = issueRecordingState;
        this.iActivityManager = iActivityManager;
        this.notificationManager = notificationManager;
        this.userContextProvider = userContextProvider;
    }
}
