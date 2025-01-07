package com.android.wm.shell.bubbles;

import android.graphics.Bitmap;
import android.graphics.Path;
import com.android.wm.shell.bubbles.bar.BubbleBarExpandedView;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface BubbleViewProvider {
    Bitmap getAppBadge();

    BubbleBarExpandedView getBubbleBarExpandedView();

    Bitmap getBubbleIcon();

    int getDotColor();

    Path getDotPath();

    BubbleExpandedView getExpandedView();

    BadgedImageView getIconView$1();

    String getKey();

    int getTaskId();

    void setTaskViewVisibility();

    boolean showDot();
}
