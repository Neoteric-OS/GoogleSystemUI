package com.android.compose.animation.scene;

import com.android.compose.animation.scene.transformation.PropertyTransformation;
import com.android.compose.animation.scene.transformation.SharedElementTransformation;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ElementTransformations {
    public final PropertyTransformation alpha;
    public final PropertyTransformation drawScale;
    public final PropertyTransformation offset;
    public final SharedElementTransformation shared;
    public final PropertyTransformation size;

    public ElementTransformations(SharedElementTransformation sharedElementTransformation, PropertyTransformation propertyTransformation, PropertyTransformation propertyTransformation2, PropertyTransformation propertyTransformation3, PropertyTransformation propertyTransformation4) {
        this.offset = propertyTransformation;
        this.size = propertyTransformation2;
        this.drawScale = propertyTransformation3;
        this.alpha = propertyTransformation4;
    }
}
