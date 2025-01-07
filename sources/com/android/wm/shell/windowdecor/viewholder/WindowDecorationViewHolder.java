package com.android.wm.shell.windowdecor.viewholder;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Point;
import android.view.View;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class WindowDecorationViewHolder {
    public final Context context;

    public WindowDecorationViewHolder(View view) {
        this.context = view.getContext();
    }

    public abstract void bindData(ActivityManager.RunningTaskInfo runningTaskInfo, Point point, int i, int i2, boolean z);

    public abstract void onHandleMenuClosed();

    public abstract void onHandleMenuOpened();
}
