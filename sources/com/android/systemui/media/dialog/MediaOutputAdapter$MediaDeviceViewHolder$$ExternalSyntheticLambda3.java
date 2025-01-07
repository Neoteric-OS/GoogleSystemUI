package com.android.systemui.media.dialog;

import android.app.KeyguardManager;
import android.content.Intent;
import android.media.AudioManager;
import android.util.Log;
import android.view.View;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.animation.DialogTransitionAnimator$createActivityTransitionController$1;
import com.android.systemui.media.dialog.MediaOutputAdapter;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class MediaOutputAdapter$MediaDeviceViewHolder$$ExternalSyntheticLambda3 implements View.OnClickListener {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ MediaOutputAdapter$MediaDeviceViewHolder$$ExternalSyntheticLambda3(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        KeyguardManager keyguardManager;
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                MediaOutputAdapter.MediaDeviceViewHolder mediaDeviceViewHolder = (MediaOutputAdapter.MediaDeviceViewHolder) obj;
                MediaSwitchingController mediaSwitchingController = MediaOutputAdapter.this.mController;
                if (mediaSwitchingController.mAudioManager.getMutingExpectedDevice() != null) {
                    try {
                        synchronized (mediaSwitchingController.mMediaDevicesLock) {
                            ((CopyOnWriteArrayList) mediaSwitchingController.mOutputMediaItemList).removeIf(new MediaSwitchingController$$ExternalSyntheticLambda4());
                        }
                        AudioManager audioManager = mediaSwitchingController.mAudioManager;
                        audioManager.cancelMuteAwaitConnection(audioManager.getMutingExpectedDevice());
                    } catch (Exception unused) {
                        Log.d("MediaSwitchingController", "Unable to cancel mute await connection");
                    }
                }
                MediaOutputAdapter.this.notifyDataSetChanged();
                return;
            case 1:
                ((MediaOutputAdapter.MediaDeviceViewHolder) obj).mCheckBox.performClick();
                return;
            case 2:
                ((MediaOutputAdapter.MediaDeviceViewHolder) obj).mEndClickIcon.performClick();
                return;
            default:
                MediaSwitchingController mediaSwitchingController2 = (MediaSwitchingController) obj;
                DialogTransitionAnimator dialogTransitionAnimator = mediaSwitchingController2.mDialogTransitionAnimator;
                dialogTransitionAnimator.getClass();
                DialogTransitionAnimator$createActivityTransitionController$1 createActivityTransitionController$default = DialogTransitionAnimator.createActivityTransitionController$default(dialogTransitionAnimator, view);
                if (createActivityTransitionController$default == null || ((keyguardManager = mediaSwitchingController2.mKeyGuardManager) != null && keyguardManager.isKeyguardLocked())) {
                    ((MediaOutputBaseDialog) mediaSwitchingController2.mCallback).mBroadcastSender.closeSystemDialogs();
                }
                Intent addFlags = new Intent("android.settings.BLUETOOTH_SETTINGS").addFlags(335544320);
                Intent intent = new Intent("android.settings.SETTINGS_EMBED_DEEP_LINK_ACTIVITY");
                if (intent.resolveActivity(mediaSwitchingController2.mContext.getPackageManager()) == null) {
                    mediaSwitchingController2.startActivity(addFlags, createActivityTransitionController$default);
                    return;
                }
                Log.d("MediaSwitchingController", "Device support split mode, launch page with deep link");
                intent.setFlags(268435456);
                intent.putExtra("android.provider.extra.SETTINGS_EMBEDDED_DEEP_LINK_INTENT_URI", addFlags.toUri(1));
                intent.putExtra("android.provider.extra.SETTINGS_EMBEDDED_DEEP_LINK_HIGHLIGHT_MENU_KEY", "top_level_connected_devices");
                mediaSwitchingController2.startActivity(intent, createActivityTransitionController$default);
                return;
        }
    }
}
