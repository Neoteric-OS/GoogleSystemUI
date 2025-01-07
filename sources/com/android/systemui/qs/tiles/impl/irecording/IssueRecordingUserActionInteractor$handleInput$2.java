package com.android.systemui.qs.tiles.impl.irecording;

import android.app.PendingIntent;
import android.content.Intent;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.animation.Expandable;
import com.android.systemui.animation.TransitionAnimator;
import com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogViewModel$showDialog$1$$ExternalSyntheticOutline0;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.qs.pipeline.domain.interactor.PanelInteractorImpl;
import com.android.systemui.qs.tiles.base.interactor.QSTileInput;
import com.android.systemui.recordissue.IssueRecordingService;
import com.android.systemui.screenrecord.RecordingController;
import com.android.systemui.screenrecord.RecordingController.AnonymousClass3;
import com.android.systemui.settings.UserContextProvider;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class IssueRecordingUserActionInteractor$handleInput$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ QSTileInput $input;
    int label;
    final /* synthetic */ IssueRecordingUserActionInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IssueRecordingUserActionInteractor$handleInput$2(IssueRecordingUserActionInteractor issueRecordingUserActionInteractor, QSTileInput qSTileInput, Continuation continuation) {
        super(2, continuation);
        this.this$0 = issueRecordingUserActionInteractor;
        this.$input = qSTileInput;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new IssueRecordingUserActionInteractor$handleInput$2(this.this$0, this.$input, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        IssueRecordingUserActionInteractor$handleInput$2 issueRecordingUserActionInteractor$handleInput$2 = (IssueRecordingUserActionInteractor$handleInput$2) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        issueRecordingUserActionInteractor$handleInput$2.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        final IssueRecordingUserActionInteractor issueRecordingUserActionInteractor = this.this$0;
        final Expandable expandable = this.$input.action.getExpandable();
        issueRecordingUserActionInteractor.getClass();
        final SystemUIDialog createDialog = issueRecordingUserActionInteractor.delegateFactory.create(new Runnable() { // from class: com.android.systemui.qs.tiles.impl.irecording.IssueRecordingUserActionInteractor$showPrompt$dialog$1
            @Override // java.lang.Runnable
            public final void run() {
                IssueRecordingUserActionInteractor issueRecordingUserActionInteractor2 = IssueRecordingUserActionInteractor.this;
                issueRecordingUserActionInteractor2.getClass();
                int i = IssueRecordingService.$r8$clinit;
                UserContextProvider userContextProvider = issueRecordingUserActionInteractor2.userContextProvider;
                UserTrackerImpl userTrackerImpl = (UserTrackerImpl) userContextProvider;
                PendingIntent service = PendingIntent.getService(((UserTrackerImpl) userContextProvider).getUserContext(), 2, new Intent(userTrackerImpl.getUserContext(), (Class<?>) IssueRecordingService.class).setAction("com.android.systemui.screenrecord.START"), 201326592);
                PendingIntent service2 = PendingIntent.getService(((UserTrackerImpl) userContextProvider).getUserContext(), 2, IssueRecordingService.Companion.getStopIntent(userTrackerImpl.getUserContext()), 201326592);
                RecordingController recordingController = issueRecordingUserActionInteractor2.recordingController;
                recordingController.mIsStarting = true;
                recordingController.mStopIntent = service2;
                RecordingController.AnonymousClass3 anonymousClass3 = recordingController.new AnonymousClass3(0L, service);
                recordingController.mCountDownTimer = anonymousClass3;
                anonymousClass3.start();
                IssueRecordingUserActionInteractor.this.dialogTransitionAnimator.disableAllCurrentDialogsExitAnimations();
                ((PanelInteractorImpl) IssueRecordingUserActionInteractor.this.panelInteractor).shadeController.postAnimateCollapseShade();
            }
        }).createDialog();
        issueRecordingUserActionInteractor.keyguardDismissUtil.executeWhenUnlocked(new ActivityStarter.OnDismissAction() { // from class: com.android.systemui.qs.tiles.impl.irecording.IssueRecordingUserActionInteractor$showPrompt$dismissAction$1
            @Override // com.android.systemui.plugins.ActivityStarter.OnDismissAction
            public final boolean onDismiss() {
                Unit unit;
                SystemUIDialog systemUIDialog = createDialog;
                Expandable expandable2 = Expandable.this;
                if (expandable2 != null) {
                    IssueRecordingUserActionInteractor issueRecordingUserActionInteractor2 = issueRecordingUserActionInteractor;
                    if (!((KeyguardStateControllerImpl) issueRecordingUserActionInteractor2.keyguardStateController).mShowing) {
                        DialogTransitionAnimator.Controller m = BluetoothTileDialogViewModel$showDialog$1$$ExternalSyntheticOutline0.m(58, "record_issue", expandable2);
                        if (m != null) {
                            TransitionAnimator.Timings timings = DialogTransitionAnimator.TIMINGS;
                            issueRecordingUserActionInteractor2.dialogTransitionAnimator.show(systemUIDialog, m, false);
                            unit = Unit.INSTANCE;
                        } else {
                            unit = null;
                        }
                        if (unit == null) {
                            systemUIDialog.show();
                        }
                        return false;
                    }
                }
                systemUIDialog.show();
                return false;
            }
        }, false, true);
        return Unit.INSTANCE;
    }
}
