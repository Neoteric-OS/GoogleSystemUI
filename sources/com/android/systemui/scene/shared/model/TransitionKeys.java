package com.android.systemui.scene.shared.model;

import com.android.compose.animation.scene.TransitionKey;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class TransitionKeys {
    public static final TransitionKey SlightlyFasterShadeCollapse;
    public static final TransitionKey ToSplitShade = null;

    static {
        new TransitionKey("GoneToSplitShade");
        SlightlyFasterShadeCollapse = new TransitionKey("SlightlyFasterShadeCollapse");
    }
}
