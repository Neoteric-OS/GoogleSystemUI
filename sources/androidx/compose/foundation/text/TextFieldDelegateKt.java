package androidx.compose.foundation.text;

import androidx.compose.ui.text.AndroidParagraph;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.platform.AndroidParagraphIntrinsics;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import kotlin.collections.EmptyList;
import kotlin.text.StringsKt__StringsJVMKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class TextFieldDelegateKt {
    public static final String EmptyTextReplacement = StringsKt__StringsJVMKt.repeat(10, "H");

    public static final long computeSizeForDefaultText(TextStyle textStyle, Density density, FontFamily.Resolver resolver, String str, int i) {
        EmptyList emptyList = EmptyList.INSTANCE;
        long Constraints$default = ConstraintsKt.Constraints$default(0, 0, 0, 0, 15);
        AndroidParagraph androidParagraph = new AndroidParagraph(new AndroidParagraphIntrinsics(str, textStyle, emptyList, emptyList, resolver, density), i, 1, Constraints$default);
        return (TextDelegateKt.ceilToIntPx(androidParagraph.getHeight()) & 4294967295L) | (TextDelegateKt.ceilToIntPx(r12.getMinIntrinsicWidth()) << 32);
    }
}
