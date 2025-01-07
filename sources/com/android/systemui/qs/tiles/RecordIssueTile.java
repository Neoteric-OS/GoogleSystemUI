package com.android.systemui.qs.tiles;

import android.app.BroadcastOptions;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.Switch;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.animation.Expandable;
import com.android.systemui.animation.TransitionAnimator;
import com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogViewModel$showDialog$1$$ExternalSyntheticOutline0;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QsEventLoggerImpl;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.pipeline.domain.interactor.PanelInteractor;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.recordissue.IssueRecordingService;
import com.android.systemui.recordissue.IssueRecordingState;
import com.android.systemui.recordissue.TraceurMessageSender;
import com.android.systemui.screenrecord.RecordingController;
import com.android.systemui.settings.UserContextProvider;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.phone.KeyguardDismissUtil;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.wm.shell.R;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$50;
import java.util.concurrent.Executor;
import kotlin.Unit;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RecordIssueTile extends QSTileImpl {
    public final Executor bgExecutor;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$50 delegateFactory;
    public final DialogTransitionAnimator dialogTransitionAnimator;
    public final IssueRecordingState issueRecordingState;
    public final KeyguardDismissUtil keyguardDismissUtil;
    public final KeyguardStateController keyguardStateController;
    public final RecordIssueTile$handleDestroy$1 onRecordingChangeListener;
    public final PanelInteractor panelInteractor;
    public final RecordingController recordingController;
    public final TraceurMessageSender traceurMessageSender;
    public final UserContextProvider userContextProvider;

    public RecordIssueTile(QSHost qSHost, QsEventLoggerImpl qsEventLoggerImpl, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, KeyguardDismissUtil keyguardDismissUtil, KeyguardStateController keyguardStateController, DialogTransitionAnimator dialogTransitionAnimator, PanelInteractor panelInteractor, UserContextProvider userContextProvider, TraceurMessageSender traceurMessageSender, Executor executor, IssueRecordingState issueRecordingState, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$50 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$50, RecordingController recordingController) {
        super(qSHost, qsEventLoggerImpl, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger);
        this.keyguardDismissUtil = keyguardDismissUtil;
        this.keyguardStateController = keyguardStateController;
        this.dialogTransitionAnimator = dialogTransitionAnimator;
        this.panelInteractor = panelInteractor;
        this.userContextProvider = userContextProvider;
        this.traceurMessageSender = traceurMessageSender;
        this.bgExecutor = executor;
        this.issueRecordingState = issueRecordingState;
        this.delegateFactory = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$50;
        this.recordingController = recordingController;
        this.onRecordingChangeListener = new RecordIssueTile$handleDestroy$1(this, 1);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final Intent getLongClickIntent() {
        return null;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final CharSequence getTileLabel() {
        return this.mContext.getString(R.string.qs_record_issue_label);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public void handleClick(final Expandable expandable) {
        if (!this.issueRecordingState.isRecording) {
            this.mUiHandler.post(new Runnable() { // from class: com.android.systemui.qs.tiles.RecordIssueTile$handleClick$1
                @Override // java.lang.Runnable
                public final void run() {
                    final RecordIssueTile recordIssueTile = RecordIssueTile.this;
                    final Expandable expandable2 = expandable;
                    recordIssueTile.getClass();
                    final SystemUIDialog createDialog = recordIssueTile.delegateFactory.create(new RecordIssueTile$handleDestroy$1(recordIssueTile, 2)).createDialog();
                    recordIssueTile.keyguardDismissUtil.executeWhenUnlocked(new ActivityStarter.OnDismissAction() { // from class: com.android.systemui.qs.tiles.RecordIssueTile$showPrompt$dismissAction$1
                        @Override // com.android.systemui.plugins.ActivityStarter.OnDismissAction
                        public final boolean onDismiss() {
                            Unit unit;
                            SystemUIDialog systemUIDialog = createDialog;
                            Expandable expandable3 = Expandable.this;
                            if (expandable3 != null) {
                                RecordIssueTile recordIssueTile2 = recordIssueTile;
                                if (!((KeyguardStateControllerImpl) recordIssueTile2.keyguardStateController).mShowing) {
                                    DialogTransitionAnimator.Controller m = BluetoothTileDialogViewModel$showDialog$1$$ExternalSyntheticOutline0.m(58, "record_issue", expandable3);
                                    if (m != null) {
                                        TransitionAnimator.Timings timings = DialogTransitionAnimator.TIMINGS;
                                        recordIssueTile2.dialogTransitionAnimator.show(systemUIDialog, m, false);
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
                }
            });
            return;
        }
        int i = IssueRecordingService.$r8$clinit;
        UserContextProvider userContextProvider = this.userContextProvider;
        PendingIntent service = PendingIntent.getService(((UserTrackerImpl) userContextProvider).getUserContext(), 2, IssueRecordingService.Companion.getStopIntent(((UserTrackerImpl) userContextProvider).getUserContext()), 201326592);
        BroadcastOptions makeBasic = BroadcastOptions.makeBasic();
        makeBasic.setInteractive(true);
        service.send(makeBasic.toBundle());
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleDestroy() {
        super.handleDestroy();
        this.bgExecutor.execute(new RecordIssueTile$handleDestroy$1(this, 0));
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleSetListening(boolean z) {
        super.handleSetListening(z);
        RecordIssueTile$handleDestroy$1 recordIssueTile$handleDestroy$1 = this.onRecordingChangeListener;
        IssueRecordingState issueRecordingState = this.issueRecordingState;
        if (z) {
            issueRecordingState.listeners.add(recordIssueTile$handleDestroy$1);
        } else {
            issueRecordingState.listeners.remove(recordIssueTile$handleDestroy$1);
        }
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final boolean isAvailable() {
        boolean z = Build.IS_DEBUGGABLE;
        return false;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final QSTile.State newTileState() {
        QSTile.BooleanState booleanState = new QSTile.BooleanState();
        booleanState.label = getTileLabel();
        booleanState.handlesLongClick = false;
        return booleanState;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        CharSequence charSequence;
        if (this.issueRecordingState.isRecording) {
            booleanState.value = true;
            booleanState.state = 2;
            booleanState.forceExpandIcon = false;
            booleanState.secondaryLabel = this.mContext.getString(R.string.qs_record_issue_stop);
            booleanState.icon = QSTileImpl.ResourceIcon.get(R.drawable.qs_record_issue_icon_on);
        } else {
            booleanState.value = false;
            booleanState.state = 1;
            booleanState.forceExpandIcon = true;
            booleanState.secondaryLabel = this.mContext.getString(R.string.qs_record_issue_start);
            booleanState.icon = QSTileImpl.ResourceIcon.get(R.drawable.qs_record_issue_icon_off);
        }
        booleanState.label = getTileLabel();
        if (TextUtils.isEmpty(booleanState.secondaryLabel)) {
            charSequence = booleanState.label;
        } else {
            charSequence = ((Object) booleanState.label) + ", " + ((Object) booleanState.secondaryLabel);
        }
        booleanState.contentDescription = charSequence;
        booleanState.expandedAccessibilityClassName = Switch.class.getName();
    }
}
