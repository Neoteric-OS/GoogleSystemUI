package com.android.systemui.keyguard.ui.composable.section;

import com.android.compose.animation.scene.ElementKey;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class BottomAreaSectionKt {
    public static final ElementKey EndButtonElementKey = null;
    public static final ElementKey IndicationAreaElementKey;
    public static final ElementKey StartButtonElementKey = null;

    static {
        new ElementKey("StartButton", null, null, 14);
        new ElementKey("EndButton", null, null, 14);
        IndicationAreaElementKey = new ElementKey("IndicationArea", null, null, 14);
    }
}
