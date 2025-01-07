package com.android.systemui.statusbar.phone;

import android.view.InsetsFlags;
import android.view.ViewDebug;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class LetterboxAppearance {
    public final int appearance;
    public final List appearanceRegions;

    public LetterboxAppearance(int i, List list) {
        this.appearance = i;
        this.appearanceRegions = list;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LetterboxAppearance)) {
            return false;
        }
        LetterboxAppearance letterboxAppearance = (LetterboxAppearance) obj;
        return this.appearance == letterboxAppearance.appearance && Intrinsics.areEqual(this.appearanceRegions, letterboxAppearance.appearanceRegions);
    }

    public final int hashCode() {
        return this.appearanceRegions.hashCode() + (Integer.hashCode(this.appearance) * 31);
    }

    public final String toString() {
        return "LetterboxAppearance{" + ViewDebug.flagsToString(InsetsFlags.class, "appearance", this.appearance) + ", " + this.appearanceRegions + "}";
    }
}
