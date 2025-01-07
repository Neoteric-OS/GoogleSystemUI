package com.android.systemui.clipboardoverlay;

import android.app.RemoteAction;
import android.content.Intent;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class ClipboardOverlayController$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId = 0;
    public final /* synthetic */ ClipboardOverlayController f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ ClipboardOverlayController$$ExternalSyntheticLambda0(ClipboardOverlayController clipboardOverlayController, RemoteAction remoteAction) {
        this.f$0 = clipboardOverlayController;
        this.f$1 = remoteAction;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                ClipboardOverlayController clipboardOverlayController = this.f$0;
                RemoteAction remoteAction = (RemoteAction) this.f$1;
                clipboardOverlayController.getClass();
                clipboardOverlayController.mView.setActionChip(remoteAction, new ClipboardOverlayController$$ExternalSyntheticLambda1(clipboardOverlayController, 0));
                break;
            default:
                ClipboardOverlayController clipboardOverlayController2 = this.f$0;
                Intent intent = (Intent) this.f$1;
                clipboardOverlayController2.getClass();
                clipboardOverlayController2.mClipboardLogger.logSessionComplete(ClipboardOverlayEvent.CLIPBOARD_OVERLAY_REMOTE_COPY_TAPPED);
                clipboardOverlayController2.mContext.startActivity(intent);
                clipboardOverlayController2.animateOut();
                break;
        }
    }

    public /* synthetic */ ClipboardOverlayController$$ExternalSyntheticLambda0(ClipboardOverlayController clipboardOverlayController, Intent intent) {
        this.f$0 = clipboardOverlayController;
        this.f$1 = intent;
    }
}
