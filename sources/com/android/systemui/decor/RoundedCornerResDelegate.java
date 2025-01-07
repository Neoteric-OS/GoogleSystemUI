package com.android.systemui.decor;

import android.graphics.drawable.Drawable;
import android.util.Size;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface RoundedCornerResDelegate {
    Drawable getBottomRoundedDrawable();

    Size getBottomRoundedSize();

    boolean getHasBottom();

    boolean getHasTop();

    Drawable getTopRoundedDrawable();

    Size getTopRoundedSize();

    void updateDisplayUniqueId(Integer num, String str);
}
