package com.android.systemui.qs.tiles.impl.irecording;

import android.app.BroadcastOptions;
import android.app.PendingIntent;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.qs.pipeline.domain.interactor.PanelInteractor;
import com.android.systemui.qs.tiles.base.interactor.QSTileInput;
import com.android.systemui.qs.tiles.base.interactor.QSTileUserActionInteractor;
import com.android.systemui.qs.tiles.viewmodel.QSTileUserAction;
import com.android.systemui.recordissue.IssueRecordingService;
import com.android.systemui.screenrecord.RecordingController;
import com.android.systemui.settings.UserContextProvider;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.phone.KeyguardDismissUtil;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$50;
import java.util.Objects;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlinx.coroutines.BuildersKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class IssueRecordingUserActionInteractor implements QSTileUserActionInteractor {
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$50 delegateFactory;
    public final DialogTransitionAnimator dialogTransitionAnimator;
    public final KeyguardDismissUtil keyguardDismissUtil;
    public final KeyguardStateController keyguardStateController;
    public final CoroutineContext mainCoroutineContext;
    public final PanelInteractor panelInteractor;
    public final RecordingController recordingController;
    public final UserContextProvider userContextProvider;

    public IssueRecordingUserActionInteractor(CoroutineContext coroutineContext, KeyguardDismissUtil keyguardDismissUtil, KeyguardStateController keyguardStateController, DialogTransitionAnimator dialogTransitionAnimator, PanelInteractor panelInteractor, UserContextProvider userContextProvider, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$50 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$50, RecordingController recordingController) {
        this.mainCoroutineContext = coroutineContext;
        this.keyguardDismissUtil = keyguardDismissUtil;
        this.keyguardStateController = keyguardStateController;
        this.dialogTransitionAnimator = dialogTransitionAnimator;
        this.panelInteractor = panelInteractor;
        this.userContextProvider = userContextProvider;
        this.delegateFactory = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$50;
        this.recordingController = recordingController;
    }

    @Override // com.android.systemui.qs.tiles.base.interactor.QSTileUserActionInteractor
    public final Object handleInput(QSTileInput qSTileInput, Continuation continuation) {
        QSTileUserAction qSTileUserAction = qSTileInput.action;
        boolean z = qSTileUserAction instanceof QSTileUserAction.Click;
        Unit unit = Unit.INSTANCE;
        if (!z) {
            Objects.toString(qSTileUserAction);
        } else {
            if (!((IssueRecordingModel) qSTileInput.data).isRecording) {
                Object withContext = BuildersKt.withContext(this.mainCoroutineContext, new IssueRecordingUserActionInteractor$handleInput$2(this, qSTileInput, null), continuation);
                return withContext == CoroutineSingletons.COROUTINE_SUSPENDED ? withContext : unit;
            }
            int i = IssueRecordingService.$r8$clinit;
            UserContextProvider userContextProvider = this.userContextProvider;
            PendingIntent service = PendingIntent.getService(((UserTrackerImpl) userContextProvider).getUserContext(), 2, IssueRecordingService.Companion.getStopIntent(((UserTrackerImpl) userContextProvider).getUserContext()), 201326592);
            BroadcastOptions makeBasic = BroadcastOptions.makeBasic();
            makeBasic.setInteractive(true);
            service.send(makeBasic.toBundle());
        }
        return unit;
    }
}
