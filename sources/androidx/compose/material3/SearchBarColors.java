package androidx.compose.material3;

import androidx.compose.animation.Scale$$ExternalSyntheticOutline0;
import androidx.compose.ui.graphics.Color;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SearchBarColors {
    public final long containerColor;
    public final long dividerColor;
    public final TextFieldColors inputFieldColors;

    public SearchBarColors(long j, long j2, TextFieldColors textFieldColors) {
        this.containerColor = j;
        this.dividerColor = j2;
        this.inputFieldColors = textFieldColors;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SearchBarColors)) {
            return false;
        }
        SearchBarColors searchBarColors = (SearchBarColors) obj;
        return Color.m363equalsimpl0(this.containerColor, searchBarColors.containerColor) && Color.m363equalsimpl0(this.dividerColor, searchBarColors.dividerColor) && Intrinsics.areEqual(this.inputFieldColors, searchBarColors.inputFieldColors);
    }

    public final int hashCode() {
        int i = Color.$r8$clinit;
        return this.inputFieldColors.hashCode() + Scale$$ExternalSyntheticOutline0.m(Long.hashCode(this.containerColor) * 31, 31, this.dividerColor);
    }
}
