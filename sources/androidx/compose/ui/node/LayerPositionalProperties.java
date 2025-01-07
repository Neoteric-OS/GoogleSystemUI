package androidx.compose.ui.node;

import androidx.compose.ui.graphics.TransformOrigin;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class LayerPositionalProperties {
    public float rotationZ;
    public long transformOrigin;
    public float translationX;
    public float translationY;
    public float scaleX = 1.0f;
    public float scaleY = 1.0f;
    public float cameraDistance = 8.0f;

    public LayerPositionalProperties() {
        int i = TransformOrigin.$r8$clinit;
        this.transformOrigin = TransformOrigin.Center;
    }
}
