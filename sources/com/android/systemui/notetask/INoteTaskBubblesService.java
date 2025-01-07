package com.android.systemui.notetask;

import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.IInterface;
import android.os.UserHandle;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface INoteTaskBubblesService extends IInterface {
    boolean areBubblesAvailable();

    void showOrHideAppBubble(Intent intent, UserHandle userHandle, Icon icon);
}
