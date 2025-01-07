package com.android.systemui.recordissue;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Messenger;
import android.os.UserHandle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Switch;
import com.android.systemui.flags.FeatureFlagsClassicRelease;
import com.android.systemui.flags.Flags;
import com.android.systemui.mediaprojection.SessionCreationSource;
import com.android.systemui.mediaprojection.devicepolicy.ScreenCaptureDevicePolicyResolver;
import com.android.systemui.mediaprojection.devicepolicy.ScreenCaptureDisabledDialogDelegate;
import com.android.systemui.recordissue.TraceurMessageSender;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.phone.SystemUIDialog;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RecordIssueDialogDelegate$beforeCreate$2 implements Runnable {
    public final /* synthetic */ Object $dialog;
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.recordissue.RecordIssueDialogDelegate$beforeCreate$2$1, reason: invalid class name */
    public final class AnonymousClass1 implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ Object this$0;

        public /* synthetic */ AnonymousClass1(int i, Object obj) {
            this.$r8$classId = i;
            this.this$0 = obj;
        }

        @Override // java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    RecordIssueDialogDelegate recordIssueDialogDelegate = (RecordIssueDialogDelegate) this.this$0;
                    TraceurMessageSender traceurMessageSender = recordIssueDialogDelegate.traceurMessageSender;
                    traceurMessageSender.getClass();
                    TraceurMessageSender.notifyTraceur$default(traceurMessageSender, 3, null, new Messenger(new TraceurMessageSender.TagsHandler(traceurMessageSender.backgroundLooper, recordIssueDialogDelegate.state)), 2);
                    break;
                case 1:
                    RecordIssueDialogDelegate recordIssueDialogDelegate2 = (RecordIssueDialogDelegate) this.this$0;
                    recordIssueDialogDelegate2.getClass();
                    boolean isEnabled = ((FeatureFlagsClassicRelease) recordIssueDialogDelegate2.flags).isEnabled(Flags.WM_ENABLE_PARTIAL_SCREEN_SHARING_ENTERPRISE_POLICIES);
                    UserTracker userTracker = recordIssueDialogDelegate2.userTracker;
                    if (!isEnabled || !((ScreenCaptureDevicePolicyResolver) recordIssueDialogDelegate2.devicePolicyResolver.get()).isScreenCaptureCompletelyDisabled(UserHandle.of(((UserTrackerImpl) userTracker).getUserId()))) {
                        recordIssueDialogDelegate2.mediaProjectionMetricsLogger.notifyProjectionInitiated(((UserTrackerImpl) userTracker).getUserId(), SessionCreationSource.SYSTEM_UI_SCREEN_RECORDER);
                        if (!recordIssueDialogDelegate2.state.prefs.getBoolean("HasApprovedScreenRecord", false)) {
                            recordIssueDialogDelegate2.mainExecutor.execute(new AnonymousClass1(3, recordIssueDialogDelegate2));
                            break;
                        }
                    } else {
                        recordIssueDialogDelegate2.mainExecutor.execute(new AnonymousClass1(2, recordIssueDialogDelegate2));
                        break;
                    }
                    break;
                case 2:
                    ScreenCaptureDisabledDialogDelegate screenCaptureDisabledDialogDelegate = ((RecordIssueDialogDelegate) this.this$0).screenCaptureDisabledDialogDelegate;
                    SystemUIDialog systemUIDialog = new SystemUIDialog(screenCaptureDisabledDialogDelegate.context);
                    screenCaptureDisabledDialogDelegate.initDialog(systemUIDialog);
                    systemUIDialog.show();
                    Switch r4 = ((RecordIssueDialogDelegate) this.this$0).screenRecordSwitch;
                    if (r4 == null) {
                        r4 = null;
                    }
                    r4.setChecked(false);
                    break;
                case 3:
                    RecordIssueDialogDelegate recordIssueDialogDelegate3 = (RecordIssueDialogDelegate) this.this$0;
                    SystemUIDialog createDialog = new ScreenCapturePermissionDialogDelegate(recordIssueDialogDelegate3.factory, recordIssueDialogDelegate3.state).createDialog();
                    final RecordIssueDialogDelegate recordIssueDialogDelegate4 = (RecordIssueDialogDelegate) this.this$0;
                    createDialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.android.systemui.recordissue.RecordIssueDialogDelegate$onScreenRecordSwitchClicked$2$1$1
                        @Override // android.content.DialogInterface.OnCancelListener
                        public final void onCancel(DialogInterface dialogInterface) {
                            Switch r0 = RecordIssueDialogDelegate.this.screenRecordSwitch;
                            if (r0 == null) {
                                r0 = null;
                            }
                            r0.setChecked(false);
                        }
                    });
                    createDialog.show();
                    break;
                default:
                    ((Button) this.this$0).setEnabled(true);
                    break;
            }
        }
    }

    public /* synthetic */ RecordIssueDialogDelegate$beforeCreate$2(int i, Object obj, Object obj2) {
        this.$r8$classId = i;
        this.this$0 = obj;
        this.$dialog = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                RecordIssueDialogDelegate recordIssueDialogDelegate = (RecordIssueDialogDelegate) this.this$0;
                recordIssueDialogDelegate.traceurMessageSender.onBoundToTraceur.add(new AnonymousClass1(0, recordIssueDialogDelegate));
                TraceurMessageSender traceurMessageSender = ((RecordIssueDialogDelegate) this.this$0).traceurMessageSender;
                Context context = ((SystemUIDialog) this.$dialog).getContext();
                if (!traceurMessageSender.isBound) {
                    try {
                        context.bindService(new Intent().setClassName(context.getPackageManager().getPackageInfo("com.android.traceur", 1048576).packageName, "com.android.traceur.BindableTraceService"), traceurMessageSender.traceurConnection, 33554465);
                        break;
                    } catch (Exception e) {
                        Log.e("TraceurMessageSender", "failed to bind to Traceur's service", e);
                        return;
                    }
                }
                break;
            default:
                ((RecordIssueDialogDelegate$onIssueTypeClicked$onMenuItemClickListener$1) this.this$0).onMenuItemClick((MenuItem) this.$dialog);
                break;
        }
    }
}
