package com.android.systemui.recordissue;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.PopupMenu;
import android.widget.Switch;
import com.android.systemui.flags.FeatureFlagsClassic;
import com.android.systemui.mediaprojection.MediaProjectionMetricsLogger;
import com.android.systemui.mediaprojection.devicepolicy.ScreenCaptureDisabledDialogDelegate;
import com.android.systemui.recordissue.RecordIssueDialogDelegate$beforeCreate$2;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.wm.shell.R;
import dagger.Lazy;
import java.util.Set;
import java.util.concurrent.Executor;
import kotlin.collections.EmptySet;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RecordIssueDialogDelegate implements SystemUIDialog.Delegate {
    public final Executor bgExecutor;
    public final Lazy devicePolicyResolver;
    public final SystemUIDialog.Factory factory;
    public final FeatureFlagsClassic flags;
    public Button issueTypeButton;
    public final Executor mainExecutor;
    public final MediaProjectionMetricsLogger mediaProjectionMetricsLogger;
    public final Runnable onStarted;
    public final ScreenCaptureDisabledDialogDelegate screenCaptureDisabledDialogDelegate;
    public Switch screenRecordSwitch;
    public final IssueRecordingState state;
    public final TraceurMessageSender traceurMessageSender;
    public final UserTracker userTracker;

    public RecordIssueDialogDelegate(SystemUIDialog.Factory factory, UserTracker userTracker, FeatureFlagsClassic featureFlagsClassic, Executor executor, Executor executor2, Lazy lazy, MediaProjectionMetricsLogger mediaProjectionMetricsLogger, ScreenCaptureDisabledDialogDelegate screenCaptureDisabledDialogDelegate, IssueRecordingState issueRecordingState, TraceurMessageSender traceurMessageSender, Runnable runnable) {
        this.factory = factory;
        this.userTracker = userTracker;
        this.flags = featureFlagsClassic;
        this.bgExecutor = executor;
        this.mainExecutor = executor2;
        this.devicePolicyResolver = lazy;
        this.mediaProjectionMetricsLogger = mediaProjectionMetricsLogger;
        this.screenCaptureDisabledDialogDelegate = screenCaptureDisabledDialogDelegate;
        this.state = issueRecordingState;
        this.traceurMessageSender = traceurMessageSender;
        this.onStarted = runnable;
    }

    @Override // com.android.systemui.statusbar.phone.DialogDelegate
    public final void beforeCreate(Dialog dialog) {
        SystemUIDialog systemUIDialog = (SystemUIDialog) dialog;
        systemUIDialog.setView(LayoutInflater.from(systemUIDialog.getContext()).inflate(R.layout.record_issue_dialog, (ViewGroup) null));
        systemUIDialog.setTitle(systemUIDialog.getContext().getString(R.string.qs_record_issue_label));
        systemUIDialog.setIcon(R.drawable.qs_record_issue_icon_off);
        systemUIDialog.setNegativeButton(R.string.cancel, RecordIssueDialogDelegate$beforeCreate$1$1.INSTANCE);
        systemUIDialog.setPositiveButton(R.string.qs_record_issue_start, new DialogInterface.OnClickListener() { // from class: com.android.systemui.recordissue.RecordIssueDialogDelegate$beforeCreate$1$2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                RecordIssueDialogDelegate.this.onStarted.run();
            }
        });
        this.bgExecutor.execute(new RecordIssueDialogDelegate$beforeCreate$2(0, this, systemUIDialog));
    }

    @Override // com.android.systemui.statusbar.phone.SystemUIDialog.Delegate
    public final SystemUIDialog createDialog() {
        SystemUIDialog.Factory factory = this.factory;
        return factory.create(this, factory.mContext);
    }

    @Override // com.android.systemui.statusbar.phone.DialogDelegate
    public final void onCreate(Dialog dialog, Bundle bundle) {
        final int i = 0;
        SystemUIDialog systemUIDialog = (SystemUIDialog) dialog;
        Window window = systemUIDialog.getWindow();
        if (window != null) {
            window.addPrivateFlags(16);
            window.setGravity(17);
        }
        Switch r0 = (Switch) systemUIDialog.requireViewById(R.id.screenrecord_switch);
        IssueRecordingState issueRecordingState = this.state;
        r0.setChecked(issueRecordingState.prefs.getBoolean("key_recordScreen", false));
        r0.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.android.systemui.recordissue.RecordIssueDialogDelegate$onCreate$1$2$1
            public final /* synthetic */ RecordIssueDialogDelegate this$0;

            {
                this.this$0 = this;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                switch (i) {
                    case 0:
                        this.this$0.state.prefs.edit().putBoolean("key_recordScreen", z).apply();
                        if (z) {
                            RecordIssueDialogDelegate recordIssueDialogDelegate = this.this$0;
                            recordIssueDialogDelegate.bgExecutor.execute(new RecordIssueDialogDelegate$beforeCreate$2.AnonymousClass1(1, recordIssueDialogDelegate));
                            break;
                        }
                        break;
                    default:
                        this.this$0.state.prefs.edit().putBoolean("key_takeBugReport", z).apply();
                        break;
                }
            }
        });
        this.screenRecordSwitch = r0;
        Switch r02 = (Switch) systemUIDialog.requireViewById(R.id.bugreport_switch);
        r02.setChecked(issueRecordingState.prefs.getBoolean("key_takeBugReport", false));
        final int i2 = 1;
        r02.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.android.systemui.recordissue.RecordIssueDialogDelegate$onCreate$1$2$1
            public final /* synthetic */ RecordIssueDialogDelegate this$0;

            {
                this.this$0 = this;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                switch (i2) {
                    case 0:
                        this.this$0.state.prefs.edit().putBoolean("key_recordScreen", z).apply();
                        if (z) {
                            RecordIssueDialogDelegate recordIssueDialogDelegate = this.this$0;
                            recordIssueDialogDelegate.bgExecutor.execute(new RecordIssueDialogDelegate$beforeCreate$2.AnonymousClass1(1, recordIssueDialogDelegate));
                            break;
                        }
                        break;
                    default:
                        this.this$0.state.prefs.edit().putBoolean("key_takeBugReport", z).apply();
                        break;
                }
            }
        });
        final Button button = (Button) systemUIDialog.requireViewById(R.id.issue_type_button);
        final Button button2 = systemUIDialog.getButton(-1);
        if (issueRecordingState.getIssueTypeRes() != -1) {
            button.setText(issueRecordingState.getIssueTypeRes());
        } else {
            button2.setEnabled(false);
        }
        button.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.recordissue.RecordIssueDialogDelegate$onCreate$1$4$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                final RecordIssueDialogDelegate recordIssueDialogDelegate = RecordIssueDialogDelegate.this;
                Context context = button.getContext();
                RecordIssueDialogDelegate$beforeCreate$2.AnonymousClass1 anonymousClass1 = new RecordIssueDialogDelegate$beforeCreate$2.AnonymousClass1(4, button2);
                recordIssueDialogDelegate.getClass();
                Button button3 = recordIssueDialogDelegate.issueTypeButton;
                if (button3 == null) {
                    button3 = null;
                }
                PopupMenu popupMenu = new PopupMenu(context, button3);
                final RecordIssueDialogDelegate$onIssueTypeClicked$onMenuItemClickListener$1 recordIssueDialogDelegate$onIssueTypeClicked$onMenuItemClickListener$1 = new RecordIssueDialogDelegate$onIssueTypeClicked$onMenuItemClickListener$1(recordIssueDialogDelegate, anonymousClass1);
                for (Integer num : IssueRecordingState.ALL_ISSUE_TYPES.keySet()) {
                    Menu menu = popupMenu.getMenu();
                    Intrinsics.checkNotNull(num);
                    MenuItem add = menu.add(num.intValue());
                    add.setIcon(R.drawable.arrow_pointing_down);
                    if (num.intValue() != recordIssueDialogDelegate.state.getIssueTypeRes()) {
                        add.setIconTintList(ColorStateList.valueOf(0));
                    }
                    add.setIntent(new Intent().putExtra("extra_issueTypeRes", num.intValue()));
                    if (num.intValue() == R.string.custom) {
                        add.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: com.android.systemui.recordissue.RecordIssueDialogDelegate$onIssueTypeClicked$1$1$1
                            @Override // android.view.MenuItem.OnMenuItemClickListener
                            public final boolean onMenuItemClick(MenuItem menuItem) {
                                RecordIssueDialogDelegate recordIssueDialogDelegate2 = RecordIssueDialogDelegate.this;
                                SystemUIDialog.Factory factory = recordIssueDialogDelegate2.factory;
                                IssueRecordingState issueRecordingState2 = recordIssueDialogDelegate2.state;
                                CustomTraceState customTraceState = issueRecordingState2.customTraceState;
                                SharedPreferences sharedPreferences = issueRecordingState2.prefs;
                                Set<String> set = EmptySet.INSTANCE;
                                Set<String> stringSet = sharedPreferences.getStringSet("key_tagTitles", set);
                                if (stringSet != null) {
                                    set = stringSet;
                                }
                                new CustomTraceSettingsDialogDelegate(factory, customTraceState, set, new RecordIssueDialogDelegate$beforeCreate$2(1, recordIssueDialogDelegate$onIssueTypeClicked$onMenuItemClickListener$1, menuItem)).createDialog().show();
                                return true;
                            }
                        });
                    }
                }
                popupMenu.setOnMenuItemClickListener(recordIssueDialogDelegate$onIssueTypeClicked$onMenuItemClickListener$1);
                popupMenu.setForceShowIcon(true);
                popupMenu.show();
            }
        });
        this.issueTypeButton = button;
    }
}
