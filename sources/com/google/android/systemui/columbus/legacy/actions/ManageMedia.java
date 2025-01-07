package com.google.android.systemui.columbus.legacy.actions;

import android.content.Context;
import android.media.AudioManager;
import android.os.SystemClock;
import android.view.KeyEvent;
import com.android.internal.logging.UiEventLogger;
import com.google.android.systemui.columbus.ColumbusEvent;
import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ManageMedia extends UserAction {
    public final AudioManager audioManager;
    public final String tag;
    public final UiEventLogger uiEventLogger;

    public ManageMedia(Context context, AudioManager audioManager, UiEventLogger uiEventLogger) {
        super(context, null);
        this.audioManager = audioManager;
        this.uiEventLogger = uiEventLogger;
        this.tag = "Columbus/ManageMedia";
        setAvailable(true);
    }

    @Override // com.google.android.systemui.columbus.legacy.actions.UserAction
    public final boolean availableOnLockscreen() {
        return true;
    }

    @Override // com.google.android.systemui.columbus.legacy.actions.UserAction
    public final boolean availableOnScreenOff() {
        return true;
    }

    @Override // com.google.android.systemui.columbus.legacy.actions.Action
    public final String getTag$vendor__unbundled_google__packages__SystemUIGoogle__android_common__sysuig() {
        return this.tag;
    }

    @Override // com.google.android.systemui.columbus.legacy.actions.Action
    public final void onTrigger(GestureSensor.DetectionProperties detectionProperties) {
        boolean z = this.audioManager.isMusicActive() || this.audioManager.isMusicActiveRemotely();
        long uptimeMillis = SystemClock.uptimeMillis();
        this.audioManager.dispatchMediaKeyEvent(new KeyEvent(uptimeMillis, uptimeMillis, 0, 85, 0));
        this.audioManager.dispatchMediaKeyEvent(new KeyEvent(uptimeMillis, uptimeMillis, 1, 85, 0));
        if (z) {
            this.uiEventLogger.log(ColumbusEvent.COLUMBUS_INVOKED_PAUSE_MEDIA);
        } else {
            this.uiEventLogger.log(ColumbusEvent.COLUMBUS_INVOKED_PLAY_MEDIA);
        }
    }
}
