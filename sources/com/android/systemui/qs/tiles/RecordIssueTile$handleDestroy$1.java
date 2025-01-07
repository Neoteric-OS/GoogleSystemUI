package com.android.systemui.qs.tiles;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import com.android.systemui.qs.pipeline.domain.interactor.PanelInteractorImpl;
import com.android.systemui.recordissue.IssueRecordingService;
import com.android.systemui.recordissue.TraceurMessageSender;
import com.android.systemui.screenrecord.RecordingController;
import com.android.systemui.screenrecord.RecordingController.AnonymousClass3;
import com.android.systemui.settings.UserContextProvider;
import com.android.systemui.settings.UserTrackerImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RecordIssueTile$handleDestroy$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ RecordIssueTile this$0;

    public /* synthetic */ RecordIssueTile$handleDestroy$1(RecordIssueTile recordIssueTile, int i) {
        this.$r8$classId = i;
        this.this$0 = recordIssueTile;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                RecordIssueTile recordIssueTile = this.this$0;
                TraceurMessageSender traceurMessageSender = recordIssueTile.traceurMessageSender;
                Context context = recordIssueTile.mContext;
                if (traceurMessageSender.isBound) {
                    context.unbindService(traceurMessageSender.traceurConnection);
                    break;
                }
                break;
            case 1:
                this.this$0.refreshState(null);
                break;
            default:
                RecordIssueTile recordIssueTile2 = this.this$0;
                recordIssueTile2.getClass();
                int i = IssueRecordingService.$r8$clinit;
                UserContextProvider userContextProvider = recordIssueTile2.userContextProvider;
                UserTrackerImpl userTrackerImpl = (UserTrackerImpl) userContextProvider;
                PendingIntent service = PendingIntent.getService(((UserTrackerImpl) userContextProvider).getUserContext(), 2, new Intent(userTrackerImpl.getUserContext(), (Class<?>) IssueRecordingService.class).setAction("com.android.systemui.screenrecord.START"), 201326592);
                PendingIntent service2 = PendingIntent.getService(((UserTrackerImpl) userContextProvider).getUserContext(), 2, IssueRecordingService.Companion.getStopIntent(userTrackerImpl.getUserContext()), 201326592);
                RecordingController recordingController = recordIssueTile2.recordingController;
                recordingController.mIsStarting = true;
                recordingController.mStopIntent = service2;
                RecordingController.AnonymousClass3 anonymousClass3 = recordingController.new AnonymousClass3(0L, service);
                recordingController.mCountDownTimer = anonymousClass3;
                anonymousClass3.start();
                this.this$0.dialogTransitionAnimator.disableAllCurrentDialogsExitAnimations();
                ((PanelInteractorImpl) this.this$0.panelInteractor).shadeController.postAnimateCollapseShade();
                break;
        }
    }
}
