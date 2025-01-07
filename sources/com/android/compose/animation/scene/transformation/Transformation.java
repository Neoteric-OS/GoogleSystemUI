package com.android.compose.animation.scene.transformation;

import com.android.compose.animation.scene.ElementMatcher;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface Transformation {
    ElementMatcher getMatcher();

    default TransformationRange getRange() {
        return null;
    }

    default Transformation reversed() {
        return this;
    }
}
