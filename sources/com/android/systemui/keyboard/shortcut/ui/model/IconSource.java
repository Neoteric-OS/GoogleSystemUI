package com.android.systemui.keyboard.shortcut.ui.model;

import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.graphics.vector.ImageVector;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class IconSource {
    public final ImageVector imageVector;
    public final Painter painter;

    public IconSource(ImageVector imageVector, Painter painter, int i) {
        imageVector = (i & 1) != 0 ? null : imageVector;
        painter = (i & 2) != 0 ? null : painter;
        this.imageVector = imageVector;
        this.painter = painter;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IconSource)) {
            return false;
        }
        IconSource iconSource = (IconSource) obj;
        return Intrinsics.areEqual(this.imageVector, iconSource.imageVector) && Intrinsics.areEqual(this.painter, iconSource.painter);
    }

    public final int hashCode() {
        ImageVector imageVector = this.imageVector;
        int hashCode = (imageVector == null ? 0 : imageVector.hashCode()) * 31;
        Painter painter = this.painter;
        return hashCode + (painter != null ? painter.hashCode() : 0);
    }

    public final String toString() {
        return "IconSource(imageVector=" + this.imageVector + ", painter=" + this.painter + ")";
    }
}
