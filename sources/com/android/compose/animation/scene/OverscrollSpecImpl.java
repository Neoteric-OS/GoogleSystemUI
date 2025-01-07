package com.android.compose.animation.scene;

import androidx.compose.foundation.gestures.Orientation;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class OverscrollSpecImpl {
    public final ContentKey content;
    public final Orientation orientation;
    public final TransformationSpecImpl transformationSpec;

    public OverscrollSpecImpl(ContentKey contentKey, Orientation orientation, TransformationSpecImpl transformationSpecImpl) {
        this.content = contentKey;
        this.orientation = orientation;
        this.transformationSpec = transformationSpecImpl;
    }
}
