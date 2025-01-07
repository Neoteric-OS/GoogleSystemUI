package com.android.systemui.clipboardoverlay;

import android.content.Context;
import android.content.Intent;
import com.android.systemui.clipboardoverlay.ClipboardModel;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class ClipboardOverlayController$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ClipboardOverlayController f$0;

    public /* synthetic */ ClipboardOverlayController$$ExternalSyntheticLambda1(ClipboardOverlayController clipboardOverlayController, int i) {
        this.$r8$classId = i;
        this.f$0 = clipboardOverlayController;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        ClipboardOverlayController clipboardOverlayController = this.f$0;
        switch (i) {
            case 0:
                clipboardOverlayController.getClass();
                clipboardOverlayController.mClipboardLogger.logSessionComplete(ClipboardOverlayEvent.CLIPBOARD_OVERLAY_ACTION_TAPPED);
                clipboardOverlayController.animateOut();
                break;
            case 1:
                clipboardOverlayController.getClass();
                clipboardOverlayController.mClipboardLogger.logSessionComplete(ClipboardOverlayEvent.CLIPBOARD_OVERLAY_EDIT_TAPPED);
                Context context = clipboardOverlayController.mContext;
                Intent intent = new Intent(context, (Class<?>) EditTextActivity.class);
                intent.addFlags(268468224);
                context.startActivity(intent);
                clipboardOverlayController.animateOut();
                break;
            case 2:
                clipboardOverlayController.getClass();
                clipboardOverlayController.mClipboardLogger.logSessionComplete(ClipboardOverlayEvent.CLIPBOARD_OVERLAY_DISMISSED_OTHER);
                clipboardOverlayController.hideImmediate();
                break;
            case 3:
                ClipboardOverlayWindow clipboardOverlayWindow = clipboardOverlayController.mWindow;
                ClipboardOverlayView clipboardOverlayView = clipboardOverlayController.mView;
                clipboardOverlayWindow.setContentView(clipboardOverlayView);
                clipboardOverlayView.setInsets(clipboardOverlayWindow.mWindowManager.getCurrentWindowMetrics().getWindowInsets(), clipboardOverlayController.mContext.getResources().getConfiguration().orientation);
                break;
            case 4:
                clipboardOverlayController.getClass();
                clipboardOverlayController.mClipboardLogger.logSessionComplete(ClipboardOverlayEvent.CLIPBOARD_OVERLAY_TIMED_OUT);
                clipboardOverlayController.animateOut();
                break;
            case 5:
                clipboardOverlayController.animateIn();
                break;
            default:
                ClipboardModel.Type type = clipboardOverlayController.mClipboardModel.type;
                clipboardOverlayController.mView.announceForAccessibility(type == ClipboardModel.Type.TEXT ? clipboardOverlayController.mContext.getString(R.string.clipboard_text_copied) : type == ClipboardModel.Type.IMAGE ? clipboardOverlayController.mContext.getString(R.string.clipboard_image_copied) : clipboardOverlayController.mContext.getString(R.string.clipboard_content_copied));
                break;
        }
    }
}
