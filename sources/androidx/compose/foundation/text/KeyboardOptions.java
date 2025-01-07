package androidx.compose.foundation.text;

import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.KeyboardCapitalization;
import androidx.compose.ui.text.input.KeyboardType;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyboardOptions {
    public static final KeyboardOptions Default = new KeyboardOptions(0, 127);
    public final int imeAction;
    public final int keyboardType;

    public KeyboardOptions(int i, int i2) {
        int i3 = (i2 & 4) != 0 ? 0 : 7;
        i = (i2 & 8) != 0 ? -1 : i;
        this.keyboardType = i3;
        this.imeAction = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof KeyboardOptions)) {
            return false;
        }
        KeyboardOptions keyboardOptions = (KeyboardOptions) obj;
        keyboardOptions.getClass();
        return KeyboardCapitalization.m619equalsimpl0(-1, -1) && Intrinsics.areEqual((Object) null, (Object) null) && KeyboardType.m621equalsimpl0(this.keyboardType, keyboardOptions.keyboardType) && ImeAction.m617equalsimpl0(this.imeAction, keyboardOptions.imeAction) && Intrinsics.areEqual((Object) null, (Object) null) && Intrinsics.areEqual((Object) null, (Object) null) && Intrinsics.areEqual((Object) null, (Object) null);
    }

    public final int hashCode() {
        return KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.imeAction, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.keyboardType, Integer.hashCode(-1) * 961, 31), 29791);
    }

    public final String toString() {
        return "KeyboardOptions(capitalization=" + ((Object) KeyboardCapitalization.m620toStringimpl(-1)) + ", autoCorrectEnabled=null, keyboardType=" + ((Object) KeyboardType.m622toStringimpl(this.keyboardType)) + ", imeAction=" + ((Object) ImeAction.m618toStringimpl(this.imeAction)) + ", platformImeOptions=nullshowKeyboardOnFocus=null, hintLocales=null)";
    }
}
