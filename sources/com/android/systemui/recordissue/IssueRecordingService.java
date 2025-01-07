package com.android.systemui.recordissue;

import android.app.IActivityManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.qs.pipeline.domain.interactor.PanelInteractor;
import com.android.systemui.screenrecord.RecordingController;
import com.android.systemui.screenrecord.RecordingService;
import com.android.systemui.screenrecord.RecordingServiceStrings;
import com.android.systemui.settings.UserContextProvider;
import com.android.systemui.statusbar.phone.KeyguardDismissUtil;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class IssueRecordingService extends RecordingService {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final IssueRecordingServiceCommandHandler commandHandler;
    public final IssueRecordingState issueRecordingState;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Companion {
        public static Intent getStopIntent(Context context) {
            return new Intent(context, (Class<?>) IssueRecordingService.class).setAction("com.android.systemui.screenrecord.STOP").putExtra("android.intent.extra.user_handle", context.getUserId());
        }
    }

    public IssueRecordingService(RecordingController recordingController, Executor executor, Handler handler, UiEventLogger uiEventLogger, NotificationManager notificationManager, UserContextProvider userContextProvider, KeyguardDismissUtil keyguardDismissUtil, DialogTransitionAnimator dialogTransitionAnimator, PanelInteractor panelInteractor, TraceurMessageSender traceurMessageSender, IssueRecordingState issueRecordingState, IActivityManager iActivityManager) {
        super(recordingController, executor, handler, uiEventLogger, notificationManager, userContextProvider, keyguardDismissUtil);
        this.issueRecordingState = issueRecordingState;
        this.commandHandler = new IssueRecordingServiceCommandHandler(executor, dialogTransitionAnimator, panelInteractor, traceurMessageSender, issueRecordingState, iActivityManager, notificationManager, userContextProvider);
    }

    @Override // com.android.systemui.screenrecord.RecordingService
    public final String getChannelId() {
        return "issue_record";
    }

    @Override // com.android.systemui.screenrecord.RecordingService
    public final String getTag() {
        return "IssueRecordingService";
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0029, code lost:
    
        if (r0.equals("com.android.systemui.screenrecord.STOP_FROM_NOTIF") == false) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0037, code lost:
    
        r0 = r5.commandHandler;
        r1 = getContentResolver();
        r0.bgExecutor.execute(new com.android.systemui.recordissue.IssueRecordingServiceCommandHandler$handleStopCommand$1(r0, r1));
        r0 = r0.issueRecordingState;
        r0.isRecording = false;
        r0 = r0.listeners.iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0055, code lost:
    
        if (r0.hasNext() == false) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0057, code lost:
    
        ((java.lang.Runnable) r0.next()).run();
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0033, code lost:
    
        if (r0.equals("com.android.systemui.screenrecord.STOP") == false) goto L39;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.android.systemui.screenrecord.RecordingService, android.app.Service
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int onStartCommand(android.content.Intent r6, int r7, int r8) {
        /*
            r5 = this;
            r0 = 0
            if (r6 == 0) goto L8
            java.lang.String r1 = r6.getAction()
            goto L9
        L8:
            r1 = r0
        L9:
            java.lang.String r2 = "handling action: "
            java.lang.String r3 = "IssueRecordingService"
            androidx.fragment.app.FragmentManagerViewModel$$ExternalSyntheticOutline0.m(r2, r1, r3)
            if (r6 == 0) goto L16
            java.lang.String r0 = r6.getAction()
        L16:
            if (r0 == 0) goto Lde
            int r1 = r0.hashCode()
            r2 = 0
            r3 = 1
            switch(r1) {
                case -1688140755: goto La8;
                case -1687783248: goto L61;
                case -470086188: goto L2d;
                case -288359034: goto L23;
                default: goto L21;
            }
        L21:
            goto Lde
        L23:
            java.lang.String r1 = "com.android.systemui.screenrecord.STOP_FROM_NOTIF"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L37
            goto Lde
        L2d:
            java.lang.String r1 = "com.android.systemui.screenrecord.STOP"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L37
            goto Lde
        L37:
            com.android.systemui.recordissue.IssueRecordingServiceCommandHandler r0 = r5.commandHandler
            android.content.ContentResolver r1 = r5.getContentResolver()
            java.util.concurrent.Executor r3 = r0.bgExecutor
            com.android.systemui.recordissue.IssueRecordingServiceCommandHandler$handleStopCommand$1 r4 = new com.android.systemui.recordissue.IssueRecordingServiceCommandHandler$handleStopCommand$1
            r4.<init>()
            r3.execute(r4)
            com.android.systemui.recordissue.IssueRecordingState r0 = r0.issueRecordingState
            r0.isRecording = r2
            java.util.concurrent.CopyOnWriteArrayList r0 = r0.listeners
            java.util.Iterator r0 = r0.iterator()
        L51:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto Lde
            java.lang.Object r1 = r0.next()
            java.lang.Runnable r1 = (java.lang.Runnable) r1
            r1.run()
            goto L51
        L61:
            java.lang.String r1 = "com.android.systemui.screenrecord.START"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L6a
            goto Lde
        L6a:
            com.android.systemui.recordissue.IssueRecordingServiceCommandHandler r0 = r5.commandHandler
            java.util.concurrent.Executor r1 = r0.bgExecutor
            com.android.systemui.recordissue.IssueRecordingServiceCommandHandler$handleStartCommand$1 r4 = new com.android.systemui.recordissue.IssueRecordingServiceCommandHandler$handleStartCommand$1
            r4.<init>()
            r1.execute(r4)
            com.android.systemui.recordissue.IssueRecordingState r0 = r0.issueRecordingState
            r0.isRecording = r3
            java.util.concurrent.CopyOnWriteArrayList r0 = r0.listeners
            java.util.Iterator r0 = r0.iterator()
        L80:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L90
            java.lang.Object r1 = r0.next()
            java.lang.Runnable r1 = (java.lang.Runnable) r1
            r1.run()
            goto L80
        L90:
            com.android.systemui.recordissue.IssueRecordingState r0 = r5.issueRecordingState
            android.content.SharedPreferences r0 = r0.prefs
            java.lang.String r1 = "key_recordScreen"
            boolean r0 = r0.getBoolean(r1, r2)
            if (r0 != 0) goto Lde
            android.content.Intent r6 = new android.content.Intent
            java.lang.String r0 = "com.android.systemui.screenrecord.START_NOTIF"
            r6.<init>(r0)
            int r5 = super.onStartCommand(r6, r7, r8)
            return r5
        La8:
            java.lang.String r1 = "com.android.systemui.screenrecord.SHARE"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto Lb1
            goto Lde
        Lb1:
            com.android.systemui.recordissue.IssueRecordingServiceCommandHandler r7 = r5.commandHandler
            java.lang.String r8 = "notification_id"
            int r0 = r5.mNotificationId
            int r8 = r6.getIntExtra(r8, r0)
            java.lang.String r0 = "extra_path"
            java.lang.Class<android.net.Uri> r1 = android.net.Uri.class
            java.lang.Object r6 = r6.getParcelableExtra(r0, r1)
            android.net.Uri r6 = (android.net.Uri) r6
            java.util.concurrent.Executor r0 = r7.bgExecutor
            com.android.systemui.recordissue.IssueRecordingServiceCommandHandler$handleShareCommand$1 r1 = new com.android.systemui.recordissue.IssueRecordingServiceCommandHandler$handleShareCommand$1
            r1.<init>()
            r0.execute(r1)
            com.android.systemui.animation.DialogTransitionAnimator r5 = r7.dialogTransitionAnimator
            r5.disableAllCurrentDialogsExitAnimations()
            com.android.systemui.qs.pipeline.domain.interactor.PanelInteractor r5 = r7.panelInteractor
            com.android.systemui.qs.pipeline.domain.interactor.PanelInteractorImpl r5 = (com.android.systemui.qs.pipeline.domain.interactor.PanelInteractorImpl) r5
            com.android.systemui.shade.ShadeController r5 = r5.shadeController
            r5.postAnimateCollapseShade()
            return r3
        Lde:
            int r5 = super.onStartCommand(r6, r7, r8)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.recordissue.IssueRecordingService.onStartCommand(android.content.Intent, int, int):int");
    }

    @Override // com.android.systemui.screenrecord.RecordingService
    public final RecordingServiceStrings provideRecordingServiceStrings() {
        return new IrsStrings(getResources());
    }
}
