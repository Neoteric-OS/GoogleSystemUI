package com.android.wm.shell.windowdecor;

import android.app.ActivityManager;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageButton;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class HandleMenuImageButton extends ImageButton {
    public ActivityManager.RunningTaskInfo taskInfo;

    public HandleMenuImageButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.View
    public final boolean onHoverEvent(MotionEvent motionEvent) {
        ActivityManager.RunningTaskInfo runningTaskInfo = this.taskInfo;
        if (runningTaskInfo == null) {
            runningTaskInfo = null;
        }
        if (runningTaskInfo.isFreeform()) {
            return super.onHoverEvent(motionEvent);
        }
        return false;
    }
}
