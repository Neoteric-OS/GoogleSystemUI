package androidx.room.util;

import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import java.util.Collection;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.sequences.TransformingSequence;
import kotlin.text.StringsKt;
import kotlin.text.StringsKt__StringsKt$splitToSequence$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class TableInfoKt {
    public static final boolean defaultValueEqualsCommon(String str, String str2) {
        if (str.equals(str2)) {
            return true;
        }
        if (str.length() != 0) {
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            while (true) {
                if (i < str.length()) {
                    char charAt = str.charAt(i);
                    int i4 = i3 + 1;
                    if (i3 == 0 && charAt != '(') {
                        break;
                    }
                    if (charAt != '(') {
                        if (charAt == ')' && i2 - 1 == 0 && i3 != str.length() - 1) {
                            break;
                        }
                    } else {
                        i2++;
                    }
                    i++;
                    i3 = i4;
                } else if (i2 == 0) {
                    return Intrinsics.areEqual(StringsKt.trim(str.substring(1, str.length() - 1)).toString(), str2);
                }
            }
        }
        return false;
    }

    public static final String formatString(Collection collection) {
        String joinToString$default;
        if (collection.isEmpty()) {
            return " }";
        }
        joinToString$default = SequencesKt.joinToString$default(new TransformingSequence(new TransformingSequence(StringsKt.rangesDelimitedBy$StringsKt__StringsKt$default(r7, new String[]{"\r\n", "\n", "\r"}, 0), new StringsKt__StringsKt$splitToSequence$1(CollectionsKt.joinToString$default(collection, ",\n", "\n", "\n", null, 56))), new Function1() { // from class: kotlin.text.StringsKt__IndentKt$prependIndent$1
            final /* synthetic */ String $indent = "    ";

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                String str = (String) obj;
                return StringsKt__StringsJVMKt.isBlank(str) ? str.length() < this.$indent.length() ? this.$indent : str : ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(new StringBuilder(), this.$indent, str);
            }
        }), "\n", null, 62);
        return joinToString$default.concat("},");
    }
}
