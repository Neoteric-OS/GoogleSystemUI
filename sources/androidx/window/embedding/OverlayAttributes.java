package androidx.window.embedding;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class OverlayAttributes {
    public final EmbeddingBounds bounds;

    public OverlayAttributes(EmbeddingBounds embeddingBounds) {
        this.bounds = embeddingBounds;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OverlayAttributes)) {
            return false;
        }
        return Intrinsics.areEqual(this.bounds, ((OverlayAttributes) obj).bounds);
    }

    public final int hashCode() {
        return this.bounds.hashCode();
    }

    public final String toString() {
        return "OverlayAttributes: {bounds=" + this.bounds + '}';
    }
}
