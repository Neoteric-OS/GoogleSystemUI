package com.android.systemui.screenrecord;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import android.os.UserHandle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Switch;
import android.widget.TextView;
import com.android.systemui.mediaprojection.MediaProjectionCaptureTarget;
import com.android.systemui.mediaprojection.MediaProjectionMetricsLogger;
import com.android.systemui.mediaprojection.appselector.MediaProjectionAppSelectorActivity;
import com.android.systemui.mediaprojection.permission.BaseMediaProjectionPermissionDialogDelegate;
import com.android.systemui.mediaprojection.permission.BaseMediaProjectionPermissionDialogDelegate$setStartButtonOnClickListener$1;
import com.android.systemui.mediaprojection.permission.ScreenShareOption;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.screenrecord.ScreenRecordPermissionDialogDelegate.CaptureTargetResultReceiver;
import com.android.systemui.settings.UserContextProvider;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.wm.shell.R;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ScreenRecordPermissionDialogDelegate extends BaseMediaProjectionPermissionDialogDelegate implements SystemUIDialog.Delegate {
    public static final List MODES = CollectionsKt__CollectionsKt.listOf(ScreenRecordingAudioSource.INTERNAL, ScreenRecordingAudioSource.MIC, ScreenRecordingAudioSource.MIC_AND_INTERNAL);
    public final ActivityStarter activityStarter;
    public Switch audioSwitch;
    public final Context context;
    public final RecordingController controller;
    public final int hostUid;
    public final UserHandle hostUserHandle;
    public final Runnable onStartRecordingClicked;
    public Spinner options;
    public final SystemUIDialog.Factory systemUIDialogFactory;
    public Switch tapsSwitch;
    public View tapsView;
    public final int theme;
    public final UserContextProvider userContextProvider;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CaptureTargetResultReceiver extends ResultReceiver {
        public CaptureTargetResultReceiver() {
            super(new Handler(Looper.getMainLooper()));
        }

        @Override // android.os.ResultReceiver
        public final void onReceiveResult(int i, Bundle bundle) {
            if (i == -1) {
                ScreenRecordPermissionDialogDelegate.access$requestScreenCapture(ScreenRecordPermissionDialogDelegate.this, (MediaProjectionCaptureTarget) bundle.getParcelable("capture_region", MediaProjectionCaptureTarget.class));
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScreenRecordPermissionDialogDelegate(UserHandle userHandle, int i, RecordingController recordingController, ActivityStarter activityStarter, UserContextProvider userContextProvider, Runnable runnable, MediaProjectionMetricsLogger mediaProjectionMetricsLogger, SystemUIDialog.Factory factory, Context context) {
        super(CollectionsKt__CollectionsKt.listOf(new ScreenShareOption(0, R.string.screenrecord_permission_dialog_option_text_single_app, R.string.screenrecord_permission_dialog_warning_single_app, R.string.media_projection_entry_generic_permission_dialog_continue_single_app, null), new ScreenShareOption(1, R.string.screenrecord_permission_dialog_option_text_entire_screen, R.string.screenrecord_permission_dialog_warning_entire_screen, R.string.screenrecord_permission_dialog_continue_entire_screen, null)), null, i, mediaProjectionMetricsLogger, Integer.valueOf(R.drawable.ic_screenrecord), Integer.valueOf(R.color.screenrecord_icon_color), 0);
        int i2 = SystemUIDialog.$r8$clinit;
        this.hostUserHandle = userHandle;
        this.hostUid = i;
        this.controller = recordingController;
        this.activityStarter = activityStarter;
        this.userContextProvider = userContextProvider;
        this.onStartRecordingClicked = runnable;
        this.systemUIDialogFactory = factory;
        this.theme = R.style.Theme_SystemUI_Dialog;
        this.context = context;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0022  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0029  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0036  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void access$requestScreenCapture(com.android.systemui.screenrecord.ScreenRecordPermissionDialogDelegate r7, com.android.systemui.mediaprojection.MediaProjectionCaptureTarget r8) {
        /*
            com.android.systemui.settings.UserContextProvider r0 = r7.userContextProvider
            com.android.systemui.settings.UserTrackerImpl r0 = (com.android.systemui.settings.UserTrackerImpl) r0
            android.content.Context r0 = r0.getUserContext()
            com.android.systemui.mediaprojection.permission.ScreenShareOption r1 = r7.selectedScreenShareOption
            int r1 = r1.mode
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L1d
            android.widget.Switch r1 = r7.tapsSwitch
            if (r1 != 0) goto L15
            r1 = r3
        L15:
            boolean r1 = r1.isChecked()
            if (r1 == 0) goto L1d
            r1 = r2
            goto L1e
        L1d:
            r1 = 0
        L1e:
            android.widget.Switch r4 = r7.audioSwitch
            if (r4 != 0) goto L23
            r4 = r3
        L23:
            boolean r4 = r4.isChecked()
            if (r4 == 0) goto L36
            android.widget.Spinner r4 = r7.options
            if (r4 != 0) goto L2e
            goto L2f
        L2e:
            r3 = r4
        L2f:
            java.lang.Object r3 = r3.getSelectedItem()
            com.android.systemui.screenrecord.ScreenRecordingAudioSource r3 = (com.android.systemui.screenrecord.ScreenRecordingAudioSource) r3
            goto L38
        L36:
            com.android.systemui.screenrecord.ScreenRecordingAudioSource r3 = com.android.systemui.screenrecord.ScreenRecordingAudioSource.NONE
        L38:
            int r3 = r3.ordinal()
            java.lang.String r4 = com.android.systemui.screenrecord.RecordingService.GROUP_KEY_SAVED
            android.content.Intent r4 = new android.content.Intent
            java.lang.Class<com.android.systemui.screenrecord.RecordingService> r5 = com.android.systemui.screenrecord.RecordingService.class
            r4.<init>(r0, r5)
            java.lang.String r5 = "com.android.systemui.screenrecord.START"
            android.content.Intent r4 = r4.setAction(r5)
            java.lang.String r5 = "extra_resultCode"
            r6 = -1
            android.content.Intent r4 = r4.putExtra(r5, r6)
            java.lang.String r5 = "extra_useAudio"
            android.content.Intent r3 = r4.putExtra(r5, r3)
            java.lang.String r4 = "extra_showTaps"
            android.content.Intent r1 = r3.putExtra(r4, r1)
            java.lang.String r3 = "extra_captureTarget"
            android.content.Intent r8 = r1.putExtra(r3, r8)
            r1 = 2
            r3 = 201326592(0xc000000, float:9.8607613E-32)
            android.app.PendingIntent r8 = android.app.PendingIntent.getForegroundService(r0, r1, r8, r3)
            android.content.Intent r4 = new android.content.Intent
            java.lang.Class<com.android.systemui.screenrecord.RecordingService> r5 = com.android.systemui.screenrecord.RecordingService.class
            r4.<init>(r0, r5)
            java.lang.String r5 = "com.android.systemui.screenrecord.STOP"
            android.content.Intent r4 = r4.setAction(r5)
            int r5 = r0.getUserId()
            java.lang.String r6 = "android.intent.extra.user_handle"
            android.content.Intent r4 = r4.putExtra(r6, r5)
            android.app.PendingIntent r0 = android.app.PendingIntent.getService(r0, r1, r4, r3)
            com.android.systemui.screenrecord.RecordingController r7 = r7.controller
            r7.mIsStarting = r2
            r7.mStopIntent = r0
            com.android.systemui.screenrecord.RecordingController$3 r0 = new com.android.systemui.screenrecord.RecordingController$3
            r1 = 3000(0xbb8, double:1.482E-320)
            r0.<init>(r1, r8)
            r7.mCountDownTimer = r0
            r0.start()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.screenrecord.ScreenRecordPermissionDialogDelegate.access$requestScreenCapture(com.android.systemui.screenrecord.ScreenRecordPermissionDialogDelegate, com.android.systemui.mediaprojection.MediaProjectionCaptureTarget):void");
    }

    @Override // com.android.systemui.statusbar.phone.SystemUIDialog.Delegate
    public final SystemUIDialog createDialog() {
        return this.systemUIDialogFactory.create(this, this.context, this.theme);
    }

    @Override // com.android.systemui.mediaprojection.permission.BaseMediaProjectionPermissionDialogDelegate
    public final Integer getOptionsViewLayoutId() {
        return Integer.valueOf(R.layout.screen_record_options);
    }

    @Override // com.android.systemui.statusbar.phone.DialogDelegate
    public final void onCreate(Dialog dialog, Bundle bundle) {
        final SystemUIDialog systemUIDialog = (SystemUIDialog) dialog;
        onCreate(systemUIDialog);
        setDialogTitle(R.string.screenrecord_permission_dialog_title);
        systemUIDialog.setTitle(R.string.screenrecord_title);
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.android.systemui.screenrecord.ScreenRecordPermissionDialogDelegate$onCreate$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Runnable runnable = ScreenRecordPermissionDialogDelegate.this.onStartRecordingClicked;
                if (runnable != null) {
                    runnable.run();
                }
                ScreenRecordPermissionDialogDelegate screenRecordPermissionDialogDelegate = ScreenRecordPermissionDialogDelegate.this;
                if (screenRecordPermissionDialogDelegate.selectedScreenShareOption.mode == 1) {
                    ScreenRecordPermissionDialogDelegate.access$requestScreenCapture(screenRecordPermissionDialogDelegate, null);
                }
                if (ScreenRecordPermissionDialogDelegate.this.selectedScreenShareOption.mode == 0) {
                    Intent intent = new Intent(systemUIDialog.getContext(), (Class<?>) MediaProjectionAppSelectorActivity.class);
                    intent.addFlags(268435456);
                    intent.putExtra("capture_region_result_receiver", ScreenRecordPermissionDialogDelegate.this.new CaptureTargetResultReceiver());
                    intent.putExtra("launched_from_user_handle", ScreenRecordPermissionDialogDelegate.this.hostUserHandle);
                    intent.putExtra("launched_from_host_uid", ScreenRecordPermissionDialogDelegate.this.hostUid);
                    intent.putExtra("screen_share_type", "ScreenRecord");
                    ScreenRecordPermissionDialogDelegate.this.activityStarter.startActivity(intent, true);
                }
                systemUIDialog.dismiss();
            }
        };
        TextView textView = this.startButton;
        if (textView == null) {
            textView = null;
        }
        textView.setOnClickListener(new BaseMediaProjectionPermissionDialogDelegate$setStartButtonOnClickListener$1(this, onClickListener));
        View.OnClickListener onClickListener2 = new View.OnClickListener() { // from class: com.android.systemui.screenrecord.ScreenRecordPermissionDialogDelegate$onCreate$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SystemUIDialog.this.dismiss();
            }
        };
        TextView textView2 = this.cancelButton;
        if (textView2 == null) {
            textView2 = null;
        }
        textView2.setOnClickListener(onClickListener2);
        AlertDialog alertDialog = this.dialog;
        if (alertDialog == null) {
            alertDialog = null;
        }
        this.audioSwitch = (Switch) alertDialog.requireViewById(R.id.screenrecord_audio_switch);
        AlertDialog alertDialog2 = this.dialog;
        if (alertDialog2 == null) {
            alertDialog2 = null;
        }
        this.tapsSwitch = (Switch) alertDialog2.requireViewById(R.id.screenrecord_taps_switch);
        Switch r5 = this.audioSwitch;
        if (r5 == null) {
            r5 = null;
        }
        r5.setOnTouchListener(ScreenRecordPermissionDialogDelegate$initRecordOptionsView$1.INSTANCE);
        Switch r52 = this.tapsSwitch;
        if (r52 == null) {
            r52 = null;
        }
        r52.setOnTouchListener(ScreenRecordPermissionDialogDelegate$initRecordOptionsView$1.INSTANCE$1);
        AlertDialog alertDialog3 = this.dialog;
        if (alertDialog3 == null) {
            alertDialog3 = null;
        }
        View requireViewById = alertDialog3.requireViewById(R.id.show_taps);
        this.tapsView = requireViewById;
        if (requireViewById == null) {
            requireViewById = null;
        }
        requireViewById.setVisibility(this.selectedScreenShareOption.mode == 0 ? 8 : 0);
        AlertDialog alertDialog4 = this.dialog;
        if (alertDialog4 == null) {
            alertDialog4 = null;
        }
        this.options = (Spinner) alertDialog4.requireViewById(R.id.screen_recording_options);
        AlertDialog alertDialog5 = this.dialog;
        if (alertDialog5 == null) {
            alertDialog5 = null;
        }
        ScreenRecordingAdapter screenRecordingAdapter = new ScreenRecordingAdapter(alertDialog5.getContext(), android.R.layout.simple_spinner_dropdown_item, MODES);
        screenRecordingAdapter.mSelectedInternal = screenRecordingAdapter.getSelected(R.string.screenrecord_device_audio_label);
        screenRecordingAdapter.mSelectedMic = screenRecordingAdapter.getSelected(R.string.screenrecord_mic_label);
        screenRecordingAdapter.mSelectedMicAndInternal = screenRecordingAdapter.getSelected(R.string.screenrecord_device_audio_and_mic_label);
        screenRecordingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = this.options;
        if (spinner == null) {
            spinner = null;
        }
        spinner.setAdapter((SpinnerAdapter) screenRecordingAdapter);
        Spinner spinner2 = this.options;
        if (spinner2 == null) {
            spinner2 = null;
        }
        spinner2.setOnItemClickListenerInt(new AdapterView.OnItemClickListener() { // from class: com.android.systemui.screenrecord.ScreenRecordPermissionDialogDelegate$initRecordOptionsView$3
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                Switch r0 = ScreenRecordPermissionDialogDelegate.this.audioSwitch;
                if (r0 == null) {
                    r0 = null;
                }
                r0.setChecked(true);
            }
        });
        Spinner spinner3 = this.options;
        if (spinner3 == null) {
            spinner3 = null;
        }
        spinner3.setAccessibilityDelegate(new ScreenRecordPermissionDialogDelegate$initRecordOptionsView$4());
        Spinner spinner4 = this.options;
        (spinner4 != null ? spinner4 : null).setLongClickable(false);
    }

    @Override // com.android.systemui.mediaprojection.permission.BaseMediaProjectionPermissionDialogDelegate, android.widget.AdapterView.OnItemSelectedListener
    public final void onItemSelected(AdapterView adapterView, View view, int i, long j) {
        super.onItemSelected(adapterView, view, i, j);
        View view2 = this.tapsView;
        if (view2 == null) {
            view2 = null;
        }
        view2.setVisibility(this.selectedScreenShareOption.mode == 0 ? 8 : 0);
    }
}
