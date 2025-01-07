package com.android.systemui.volume;

import android.R;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.media.AudioManager;
import android.util.Log;
import android.view.KeyEvent;
import androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0;
import com.android.settingslib.bluetooth.LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.plugins.PluginManager;
import com.android.systemui.plugins.VolumeDialog;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.google.common.collect.ImmutableList;
import java.util.Optional;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CsdWarningDialog extends SystemUIDialog implements DialogInterface.OnDismissListener, DialogInterface.OnClickListener {
    public static final String TAG = Util.logTag(CsdWarningDialog.class);
    public final Optional mActionIntents;
    public final AudioManager mAudioManager;
    public final BroadcastDispatcher mBroadcastDispatcher;
    public int mCachedMediaStreamVolume;
    public ExecutorImpl.ExecutionToken mCancelScheduledNoUserActionRunnable;
    public final Context mContext;
    public final int mCsdWarning;
    public final DelayableExecutor mDelayableExecutor;
    public final CsdWarningDialog$$ExternalSyntheticLambda0 mNoUserActionRunnable;
    public final NotificationManager mNotificationManager;
    public final VolumeDialogImpl$$ExternalSyntheticLambda2 mOnCleanup;
    public final AnonymousClass1 mReceiver;
    public final BroadcastReceiver mReceiverDismissNotification;
    public final BroadcastReceiver mReceiverUndo;
    public long mShowTime;
    public final Object mTimerLock;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [android.content.BroadcastReceiver, com.android.systemui.volume.CsdWarningDialog$1] */
    /* JADX WARN: Type inference failed for: r6v1, types: [com.android.systemui.volume.CsdWarningDialog$$ExternalSyntheticLambda0] */
    public CsdWarningDialog(int i, Context context, AudioManager audioManager, NotificationManager notificationManager, DelayableExecutor delayableExecutor, VolumeDialogImpl$$ExternalSyntheticLambda2 volumeDialogImpl$$ExternalSyntheticLambda2, Optional optional, BroadcastDispatcher broadcastDispatcher) {
        super(context);
        this.mTimerLock = new Object();
        this.mCancelScheduledNoUserActionRunnable = null;
        final int i2 = 0;
        ?? r1 = new BroadcastReceiver(this) { // from class: com.android.systemui.volume.CsdWarningDialog.1
            public final /* synthetic */ CsdWarningDialog this$0;

            {
                this.this$0 = this;
            }

            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                switch (i2) {
                    case 0:
                        if ("android.intent.action.CLOSE_SYSTEM_DIALOGS".equals(intent.getAction())) {
                            if (D.BUG) {
                                Log.d(CsdWarningDialog.TAG, "Received ACTION_CLOSE_SYSTEM_DIALOGS");
                            }
                            this.this$0.cancel();
                            VolumeDialogImpl$$ExternalSyntheticLambda2 volumeDialogImpl$$ExternalSyntheticLambda22 = this.this$0.mOnCleanup;
                            if (volumeDialogImpl$$ExternalSyntheticLambda22 != null) {
                                volumeDialogImpl$$ExternalSyntheticLambda22.run();
                                break;
                            }
                        }
                        break;
                    case 1:
                        if (VolumeDialog.ACTION_VOLUME_UNDO.equals(intent.getAction())) {
                            if (D.BUG) {
                                Log.d(CsdWarningDialog.TAG, "Received ACTION_VOLUME_UNDO");
                            }
                            CsdWarningDialog csdWarningDialog = this.this$0;
                            csdWarningDialog.mAudioManager.setStreamVolume(3, csdWarningDialog.mCachedMediaStreamVolume, 1);
                            this.this$0.mNotificationManager.cancel(1007);
                            this.this$0.destroy();
                            break;
                        }
                        break;
                    default:
                        if ("com.android.systemui.volume.DISMISS_CSD_NOTIFICATION".equals(intent.getAction())) {
                            if (D.BUG) {
                                Log.d(CsdWarningDialog.TAG, "Received DISMISS_CSD_NOTIFICATION");
                            }
                            this.this$0.destroy();
                            break;
                        }
                        break;
                }
            }
        };
        this.mReceiver = r1;
        final int i3 = 1;
        this.mReceiverUndo = new BroadcastReceiver(this) { // from class: com.android.systemui.volume.CsdWarningDialog.1
            public final /* synthetic */ CsdWarningDialog this$0;

            {
                this.this$0 = this;
            }

            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                switch (i3) {
                    case 0:
                        if ("android.intent.action.CLOSE_SYSTEM_DIALOGS".equals(intent.getAction())) {
                            if (D.BUG) {
                                Log.d(CsdWarningDialog.TAG, "Received ACTION_CLOSE_SYSTEM_DIALOGS");
                            }
                            this.this$0.cancel();
                            VolumeDialogImpl$$ExternalSyntheticLambda2 volumeDialogImpl$$ExternalSyntheticLambda22 = this.this$0.mOnCleanup;
                            if (volumeDialogImpl$$ExternalSyntheticLambda22 != null) {
                                volumeDialogImpl$$ExternalSyntheticLambda22.run();
                                break;
                            }
                        }
                        break;
                    case 1:
                        if (VolumeDialog.ACTION_VOLUME_UNDO.equals(intent.getAction())) {
                            if (D.BUG) {
                                Log.d(CsdWarningDialog.TAG, "Received ACTION_VOLUME_UNDO");
                            }
                            CsdWarningDialog csdWarningDialog = this.this$0;
                            csdWarningDialog.mAudioManager.setStreamVolume(3, csdWarningDialog.mCachedMediaStreamVolume, 1);
                            this.this$0.mNotificationManager.cancel(1007);
                            this.this$0.destroy();
                            break;
                        }
                        break;
                    default:
                        if ("com.android.systemui.volume.DISMISS_CSD_NOTIFICATION".equals(intent.getAction())) {
                            if (D.BUG) {
                                Log.d(CsdWarningDialog.TAG, "Received DISMISS_CSD_NOTIFICATION");
                            }
                            this.this$0.destroy();
                            break;
                        }
                        break;
                }
            }
        };
        final int i4 = 2;
        this.mReceiverDismissNotification = new BroadcastReceiver(this) { // from class: com.android.systemui.volume.CsdWarningDialog.1
            public final /* synthetic */ CsdWarningDialog this$0;

            {
                this.this$0 = this;
            }

            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                switch (i4) {
                    case 0:
                        if ("android.intent.action.CLOSE_SYSTEM_DIALOGS".equals(intent.getAction())) {
                            if (D.BUG) {
                                Log.d(CsdWarningDialog.TAG, "Received ACTION_CLOSE_SYSTEM_DIALOGS");
                            }
                            this.this$0.cancel();
                            VolumeDialogImpl$$ExternalSyntheticLambda2 volumeDialogImpl$$ExternalSyntheticLambda22 = this.this$0.mOnCleanup;
                            if (volumeDialogImpl$$ExternalSyntheticLambda22 != null) {
                                volumeDialogImpl$$ExternalSyntheticLambda22.run();
                                break;
                            }
                        }
                        break;
                    case 1:
                        if (VolumeDialog.ACTION_VOLUME_UNDO.equals(intent.getAction())) {
                            if (D.BUG) {
                                Log.d(CsdWarningDialog.TAG, "Received ACTION_VOLUME_UNDO");
                            }
                            CsdWarningDialog csdWarningDialog = this.this$0;
                            csdWarningDialog.mAudioManager.setStreamVolume(3, csdWarningDialog.mCachedMediaStreamVolume, 1);
                            this.this$0.mNotificationManager.cancel(1007);
                            this.this$0.destroy();
                            break;
                        }
                        break;
                    default:
                        if ("com.android.systemui.volume.DISMISS_CSD_NOTIFICATION".equals(intent.getAction())) {
                            if (D.BUG) {
                                Log.d(CsdWarningDialog.TAG, "Received DISMISS_CSD_NOTIFICATION");
                            }
                            this.this$0.destroy();
                            break;
                        }
                        break;
                }
            }
        };
        this.mCsdWarning = i;
        this.mContext = context;
        this.mAudioManager = audioManager;
        this.mNotificationManager = notificationManager;
        this.mOnCleanup = volumeDialogImpl$$ExternalSyntheticLambda2;
        this.mDelayableExecutor = delayableExecutor;
        this.mActionIntents = optional;
        this.mBroadcastDispatcher = broadcastDispatcher;
        getWindow().setType(2010);
        SystemUIDialog.setShowForAllUsers(this);
        int i5 = R.string.data_usage_rapid_body;
        if (i != 1) {
            if (i != 3) {
                Log.e(TAG, AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "Invalid CSD warning event "), new Exception());
            } else {
                i5 = R.string.data_usage_rapid_title;
            }
        }
        setMessage(context.getString(i5));
        setButton(-1, context.getString(com.android.wm.shell.R.string.csd_button_keep_listening), this);
        setButton(-2, context.getString(com.android.wm.shell.R.string.csd_button_lower_volume), this);
        setOnDismissListener(this);
        context.registerReceiver(r1, new IntentFilter("android.intent.action.CLOSE_SYSTEM_DIALOGS"), 2);
        if (i == 1) {
            this.mNoUserActionRunnable = new Runnable() { // from class: com.android.systemui.volume.CsdWarningDialog$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    CsdWarningDialog csdWarningDialog = CsdWarningDialog.this;
                    if (csdWarningDialog.mCsdWarning == 1) {
                        csdWarningDialog.mCachedMediaStreamVolume = csdWarningDialog.mAudioManager.getStreamVolume(3);
                        csdWarningDialog.mAudioManager.lowerVolumeToRs1();
                        csdWarningDialog.sendNotification(false);
                    }
                }
            };
        } else {
            this.mNoUserActionRunnable = null;
        }
    }

    public void destroy() {
        if (!this.mActionIntents.isPresent() || ((ImmutableList) this.mActionIntents.get()).isEmpty()) {
            return;
        }
        this.mBroadcastDispatcher.unregisterReceiver(this.mReceiverUndo);
        this.mBroadcastDispatcher.unregisterReceiver(this.mReceiverDismissNotification);
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        if (i == -2) {
            LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0.m(new StringBuilder("Lower volume pressed for CSD warning "), this.mCsdWarning, TAG);
            this.mAudioManager.lowerVolumeToRs1();
            dismiss();
        }
        if (D.BUG) {
            ExifInterface$$ExternalSyntheticOutline0.m("on click ", TAG, i);
        }
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        try {
            this.mContext.unregisterReceiver(this.mReceiver);
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "Error unregistering broadcast receiver", e);
        }
        VolumeDialogImpl$$ExternalSyntheticLambda2 volumeDialogImpl$$ExternalSyntheticLambda2 = this.mOnCleanup;
        if (volumeDialogImpl$$ExternalSyntheticLambda2 != null) {
            volumeDialogImpl$$ExternalSyntheticLambda2.run();
        }
    }

    @Override // android.app.AlertDialog, android.app.Dialog, android.view.KeyEvent.Callback
    public final boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i == 24) {
            return true;
        }
        if (i == 25 && System.currentTimeMillis() - this.mShowTime > 1000) {
            Log.i(TAG, "Confirmed CSD exposure warning via VOLUME_DOWN");
            dismiss();
        }
        return super.onKeyUp(i, keyEvent);
    }

    public final void sendNotification(boolean z) {
        Intent intent;
        PendingIntent activity = PendingIntent.getActivity(this.mContext, 0, new Intent("android.settings.SOUND_SETTINGS"), 67108864);
        String string = z ? this.mContext.getString(com.android.wm.shell.R.string.csd_500_system_lowered_text) : this.mContext.getString(com.android.wm.shell.R.string.csd_system_lowered_text);
        Notification.Builder category = new Notification.Builder(this.mContext, PluginManager.NOTIFICATION_CHANNEL_ID).setSmallIcon(com.android.wm.shell.R.drawable.hearing).setContentTitle(this.mContext.getString(com.android.wm.shell.R.string.csd_lowered_title)).setContentText(string).setContentIntent(activity).setStyle(new Notification.BigTextStyle().bigText(string)).setVisibility(1).setLocalOnly(true).setAutoCancel(true).setCategory("sys");
        if (this.mActionIntents.isPresent() && !((ImmutableList) this.mActionIntents.get()).isEmpty()) {
            ImmutableList.Itr listIterator = ((ImmutableList) this.mActionIntents.get()).listIterator(0);
            while (listIterator.hasNext()) {
                CsdWarningAction csdWarningAction = (CsdWarningAction) listIterator.next();
                if (csdWarningAction.label == null || (intent = csdWarningAction.intent) == null) {
                    Log.w(TAG, "Null action intent received. Skipping addition to notification");
                } else {
                    Context context = this.mContext;
                    PendingIntent activity2 = csdWarningAction.isActivity ? PendingIntent.getActivity(context, 0, intent, 201326592) : PendingIntent.getBroadcast(context, 0, intent, 67108864);
                    if (activity2 == null) {
                        Log.w(TAG, "Null pending intent received. Skipping addition to notification");
                    } else {
                        String str = csdWarningAction.label;
                        category.addAction(0, str, activity2);
                        if (str.equals(this.mContext.getString(com.android.wm.shell.R.string.volume_undo_action))) {
                            this.mBroadcastDispatcher.registerReceiver(this.mReceiverUndo, new IntentFilter(VolumeDialog.ACTION_VOLUME_UNDO), null, null, 4, null);
                            PendingIntent broadcast = PendingIntent.getBroadcast(this.mContext, 0, new Intent("com.android.systemui.volume.DISMISS_CSD_NOTIFICATION").setPackage(this.mContext.getPackageName()), 67108864);
                            this.mBroadcastDispatcher.registerReceiver(this.mReceiverDismissNotification, new IntentFilter("com.android.systemui.volume.DISMISS_CSD_NOTIFICATION"), null, null, 4, null);
                            category.setDeleteIntent(broadcast);
                        }
                    }
                }
            }
        }
        this.mNotificationManager.notify(1007, category.build());
    }

    @Override // android.app.Dialog
    public final void show() {
        int i = this.mCsdWarning;
        if (i != 2) {
            super.show();
            return;
        }
        if (i != 2) {
            Log.w(TAG, "Notification dose repeat 5x is not shown for " + this.mCsdWarning);
        } else {
            this.mCachedMediaStreamVolume = this.mAudioManager.getStreamVolume(3);
            this.mAudioManager.lowerVolumeToRs1();
            sendNotification(true);
        }
        try {
            this.mContext.unregisterReceiver(this.mReceiver);
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "Error unregistering broadcast receiver", e);
        }
        VolumeDialogImpl$$ExternalSyntheticLambda2 volumeDialogImpl$$ExternalSyntheticLambda2 = this.mOnCleanup;
        if (volumeDialogImpl$$ExternalSyntheticLambda2 != null) {
            volumeDialogImpl$$ExternalSyntheticLambda2.run();
        }
    }

    @Override // com.android.systemui.statusbar.phone.SystemUIDialog
    public final void start() {
        this.mShowTime = System.currentTimeMillis();
        synchronized (this.mTimerLock) {
            try {
                CsdWarningDialog$$ExternalSyntheticLambda0 csdWarningDialog$$ExternalSyntheticLambda0 = this.mNoUserActionRunnable;
                if (csdWarningDialog$$ExternalSyntheticLambda0 != null) {
                    this.mCancelScheduledNoUserActionRunnable = this.mDelayableExecutor.executeDelayed(csdWarningDialog$$ExternalSyntheticLambda0, 5000L);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.android.systemui.statusbar.phone.SystemUIDialog
    public final void stop() {
        synchronized (this.mTimerLock) {
            try {
                ExecutorImpl.ExecutionToken executionToken = this.mCancelScheduledNoUserActionRunnable;
                if (executionToken != null) {
                    executionToken.run();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
