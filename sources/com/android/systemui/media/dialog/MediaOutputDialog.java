package com.android.systemui.media.dialog;

import android.content.Context;
import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.os.Bundle;
import android.util.Log;
import androidx.core.graphics.drawable.IconCompat;
import com.android.internal.logging.UiEventLogger;
import com.android.settingslib.media.InfoMediaManager;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.broadcast.BroadcastSender;
import com.android.systemui.shared.system.SysUiStatsLog;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaOutputDialog extends MediaOutputBaseDialog {
    public final DialogTransitionAnimator mDialogTransitionAnimator;
    public final UiEventLogger mUiEventLogger;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MediaOutputEvent implements UiEventLogger.UiEventEnum {
        public static final /* synthetic */ MediaOutputEvent[] $VALUES;
        public static final MediaOutputEvent MEDIA_OUTPUT_DIALOG_SHOW;
        private final int mId = 655;

        static {
            MediaOutputEvent mediaOutputEvent = new MediaOutputEvent();
            MEDIA_OUTPUT_DIALOG_SHOW = mediaOutputEvent;
            $VALUES = new MediaOutputEvent[]{mediaOutputEvent};
        }

        public static MediaOutputEvent valueOf(String str) {
            return (MediaOutputEvent) Enum.valueOf(MediaOutputEvent.class, str);
        }

        public static MediaOutputEvent[] values() {
            return (MediaOutputEvent[]) $VALUES.clone();
        }

        public final int getId() {
            return this.mId;
        }
    }

    public MediaOutputDialog(Context context, boolean z, BroadcastSender broadcastSender, MediaSwitchingController mediaSwitchingController, DialogTransitionAnimator dialogTransitionAnimator, UiEventLogger uiEventLogger, boolean z2) {
        super(context, broadcastSender, mediaSwitchingController, z2);
        this.mDialogTransitionAnimator = dialogTransitionAnimator;
        this.mUiEventLogger = uiEventLogger;
        this.mAdapter = new MediaOutputAdapter(this.mMediaSwitchingController);
        if (z) {
            return;
        }
        getWindow().setType(2038);
    }

    @Override // com.android.systemui.media.dialog.MediaOutputBaseDialog
    public final IconCompat getAppSourceIcon() {
        return this.mMediaSwitchingController.getNotificationSmallIcon();
    }

    @Override // com.android.systemui.media.dialog.MediaOutputBaseDialog
    public final IconCompat getHeaderIcon() {
        return this.mMediaSwitchingController.getHeaderIcon();
    }

    @Override // com.android.systemui.media.dialog.MediaOutputBaseDialog
    public final int getHeaderIconSize() {
        return ((MediaOutputBaseDialog) this).mContext.getResources().getDimensionPixelSize(R.dimen.media_output_dialog_header_album_icon_size);
    }

    @Override // com.android.systemui.media.dialog.MediaOutputBaseDialog
    public final CharSequence getHeaderSubtitle() {
        MediaMetadata metadata;
        MediaController mediaController = this.mMediaSwitchingController.mMediaController;
        if (mediaController == null || (metadata = mediaController.getMetadata()) == null) {
            return null;
        }
        return metadata.getDescription().getSubtitle();
    }

    @Override // com.android.systemui.media.dialog.MediaOutputBaseDialog
    public final CharSequence getHeaderText() {
        MediaMetadata metadata;
        MediaSwitchingController mediaSwitchingController = this.mMediaSwitchingController;
        MediaController mediaController = mediaSwitchingController.mMediaController;
        return (mediaController == null || (metadata = mediaController.getMetadata()) == null) ? mediaSwitchingController.mContext.getText(R.string.controls_media_title) : metadata.getDescription().getTitle();
    }

    @Override // com.android.systemui.media.dialog.MediaOutputBaseDialog
    public final CharSequence getStopButtonText() {
        return ((MediaOutputBaseDialog) this).mContext.getText(R.string.media_output_dialog_button_stop_casting);
    }

    @Override // com.android.systemui.media.dialog.MediaOutputBaseDialog
    public final int getStopButtonVisibility() {
        return !(this.mMediaSwitchingController.mLocalMediaManager.getCurrentConnectedDevice() != null ? MediaSwitchingController.isActiveRemoteDevice(this.mMediaSwitchingController.mLocalMediaManager.getCurrentConnectedDevice()) : false) ? 8 : 0;
    }

    @Override // com.android.systemui.media.dialog.MediaOutputBaseDialog
    public final void onBroadcastIconClick() {
        startLeBroadcastDialog();
    }

    @Override // com.android.systemui.media.dialog.MediaOutputBaseDialog, com.android.systemui.statusbar.phone.SystemUIDialog, android.app.AlertDialog, android.app.Dialog
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mUiEventLogger.log(MediaOutputEvent.MEDIA_OUTPUT_DIALOG_SHOW);
    }

    @Override // com.android.systemui.media.dialog.MediaOutputBaseDialog
    public final void onStopButtonClick() {
        MediaSwitchingController mediaSwitchingController = this.mMediaSwitchingController;
        MediaOutputMetricLogger mediaOutputMetricLogger = mediaSwitchingController.mMetricLogger;
        if (MediaOutputMetricLogger.DEBUG) {
            mediaOutputMetricLogger.getClass();
            Log.d("MediaOutputMetricLogger", "logInteraction - Stop casting");
        }
        SysUiStatsLog.write(2, 0, mediaOutputMetricLogger.getLoggingPackageName(), false);
        InfoMediaManager infoMediaManager = mediaSwitchingController.mLocalMediaManager.mInfoMediaManager;
        infoMediaManager.releaseSession(infoMediaManager.getActiveRoutingSession());
        this.mDialogTransitionAnimator.disableAllCurrentDialogsExitAnimations();
        dismiss();
    }
}
