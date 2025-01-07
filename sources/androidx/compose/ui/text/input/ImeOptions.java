package androidx.compose.ui.text.input;

import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import androidx.compose.ui.text.intl.LocaleList;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ImeOptions {
    public static final ImeOptions Default = new ImeOptions(false, 0, true, 1, 1, LocaleList.Empty);
    public final boolean autoCorrect;
    public final int capitalization;
    public final LocaleList hintLocales;
    public final int imeAction;
    public final int keyboardType;
    public final boolean singleLine;

    public ImeOptions(boolean z, int i, boolean z2, int i2, int i3, LocaleList localeList) {
        this.singleLine = z;
        this.capitalization = i;
        this.autoCorrect = z2;
        this.keyboardType = i2;
        this.imeAction = i3;
        this.hintLocales = localeList;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ImeOptions)) {
            return false;
        }
        ImeOptions imeOptions = (ImeOptions) obj;
        return this.singleLine == imeOptions.singleLine && KeyboardCapitalization.m619equalsimpl0(this.capitalization, imeOptions.capitalization) && this.autoCorrect == imeOptions.autoCorrect && KeyboardType.m621equalsimpl0(this.keyboardType, imeOptions.keyboardType) && ImeAction.m617equalsimpl0(this.imeAction, imeOptions.imeAction) && Intrinsics.areEqual((Object) null, (Object) null) && Intrinsics.areEqual(this.hintLocales, imeOptions.hintLocales);
    }

    public final int hashCode() {
        return this.hintLocales.localeList.hashCode() + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.imeAction, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.keyboardType, TransitionData$$ExternalSyntheticOutline0.m(KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.capitalization, Boolean.hashCode(this.singleLine) * 31, 31), 31, this.autoCorrect), 31), 961);
    }

    public final String toString() {
        return "ImeOptions(singleLine=" + this.singleLine + ", capitalization=" + ((Object) KeyboardCapitalization.m620toStringimpl(this.capitalization)) + ", autoCorrect=" + this.autoCorrect + ", keyboardType=" + ((Object) KeyboardType.m622toStringimpl(this.keyboardType)) + ", imeAction=" + ((Object) ImeAction.m618toStringimpl(this.imeAction)) + ", platformImeOptions=null, hintLocales=" + this.hintLocales + ')';
    }
}
