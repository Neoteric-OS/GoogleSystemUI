package com.android.systemui.accessibility;

import android.util.Size;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class WindowMagnificationFrameSpec {
    public final int index;
    public final Size size;

    public WindowMagnificationFrameSpec(int i, Size size) {
        this.index = i;
        this.size = size;
    }

    public static final WindowMagnificationFrameSpec deserialize(String str) {
        int indexOf$default = StringsKt.indexOf$default((CharSequence) str, ',', 0, 6);
        if (indexOf$default < 0) {
            throw new NumberFormatException(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Invalid WindowMagnificationFrameSpec: \"", str, "\""));
        }
        try {
            return new WindowMagnificationFrameSpec(Integer.parseInt(str.substring(0, indexOf$default)), Size.parseSize(str.substring(indexOf$default + 1)));
        } catch (NumberFormatException unused) {
            throw new NumberFormatException(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Invalid WindowMagnificationFrameSpec: \"", str, "\""));
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WindowMagnificationFrameSpec)) {
            return false;
        }
        WindowMagnificationFrameSpec windowMagnificationFrameSpec = (WindowMagnificationFrameSpec) obj;
        return this.index == windowMagnificationFrameSpec.index && Intrinsics.areEqual(this.size, windowMagnificationFrameSpec.size);
    }

    public final int hashCode() {
        return this.size.hashCode() + (Integer.hashCode(this.index) * 31);
    }

    public final String toString() {
        return "WindowMagnificationFrameSpec(index=" + this.index + ", size=" + this.size + ")";
    }
}
