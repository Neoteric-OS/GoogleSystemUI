package com.android.wm.shell.bubbles.properties;

import android.os.SystemProperties;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ProdBubbleProperties {
    public static final ProdBubbleProperties INSTANCE = new ProdBubbleProperties();
    public static boolean _isBubbleBarEnabled = SystemProperties.getBoolean("persist.wm.debug.bubble_bar", false);
}
