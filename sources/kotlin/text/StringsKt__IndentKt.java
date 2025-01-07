package kotlin.text;

import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.sequences.SequencesKt;
import kotlin.sequences.TransformingSequence;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class StringsKt__IndentKt extends StringsKt__AppendableKt {
    public static String trimIndent(String str) {
        int i = 0;
        List list = SequencesKt.toList(new TransformingSequence(StringsKt.rangesDelimitedBy$StringsKt__StringsKt$default(str, new String[]{"\r\n", "\n", "\r"}, 0), new StringsKt__StringsKt$splitToSequence$1(str)));
        ArrayList<String> arrayList = new ArrayList();
        for (Object obj : list) {
            if (!StringsKt__StringsJVMKt.isBlank((String) obj)) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10));
        for (String str2 : arrayList) {
            int length = str2.length();
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    i2 = -1;
                    break;
                }
                if (!CharsKt.isWhitespace(str2.charAt(i2))) {
                    break;
                }
                i2++;
            }
            if (i2 == -1) {
                i2 = str2.length();
            }
            arrayList2.add(Integer.valueOf(i2));
        }
        Integer num = (Integer) CollectionsKt.minOrNull(arrayList2);
        int intValue = num != null ? num.intValue() : 0;
        int length2 = str.length();
        list.size();
        StringsKt__IndentKt$getIndentFunction$1 stringsKt__IndentKt$getIndentFunction$1 = StringsKt__IndentKt$getIndentFunction$1.INSTANCE;
        int lastIndex = CollectionsKt__CollectionsKt.getLastIndex(list);
        ArrayList arrayList3 = new ArrayList();
        Iterator it = list.iterator();
        while (true) {
            String str3 = null;
            if (!it.hasNext()) {
                StringBuilder sb = new StringBuilder(length2);
                CollectionsKt.joinTo$default(arrayList3, sb, "\n", null, 124);
                return sb.toString();
            }
            Object next = it.next();
            int i3 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            String str4 = (String) next;
            if ((i != 0 && i != lastIndex) || !StringsKt__StringsJVMKt.isBlank(str4)) {
                if (intValue < 0) {
                    throw new IllegalArgumentException(GenericDocument$$ExternalSyntheticOutline0.m("Requested character count ", " is less than zero.", intValue).toString());
                }
                int length3 = str4.length();
                if (intValue <= length3) {
                    length3 = intValue;
                }
                str3 = str4.substring(length3);
                stringsKt__IndentKt$getIndentFunction$1.getClass();
                if (str3 == null) {
                    str3 = str4;
                }
            }
            if (str3 != null) {
                arrayList3.add(str3);
            }
            i = i3;
        }
    }

    public static String trimMargin$default(String str) {
        if (StringsKt__StringsJVMKt.isBlank("|")) {
            throw new IllegalArgumentException("marginPrefix must be non-blank string.");
        }
        List list = SequencesKt.toList(new TransformingSequence(StringsKt.rangesDelimitedBy$StringsKt__StringsKt$default(str, new String[]{"\r\n", "\n", "\r"}, 0), new StringsKt__StringsKt$splitToSequence$1(str)));
        int length = str.length();
        list.size();
        StringsKt__IndentKt$getIndentFunction$1 stringsKt__IndentKt$getIndentFunction$1 = StringsKt__IndentKt$getIndentFunction$1.INSTANCE;
        int lastIndex = CollectionsKt__CollectionsKt.getLastIndex(list);
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        int i = 0;
        while (true) {
            String str2 = null;
            if (!it.hasNext()) {
                StringBuilder sb = new StringBuilder(length);
                CollectionsKt.joinTo$default(arrayList, sb, "\n", null, 124);
                return sb.toString();
            }
            Object next = it.next();
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            String str3 = (String) next;
            if ((i != 0 && i != lastIndex) || !StringsKt__StringsJVMKt.isBlank(str3)) {
                int length2 = str3.length();
                int i3 = 0;
                while (true) {
                    if (i3 >= length2) {
                        i3 = -1;
                        break;
                    }
                    if (!CharsKt.isWhitespace(str3.charAt(i3))) {
                        break;
                    }
                    i3++;
                }
                if (i3 != -1 && str3.startsWith("|", i3)) {
                    str2 = str3.substring("|".length() + i3);
                }
                if (str2 != null) {
                    stringsKt__IndentKt$getIndentFunction$1.getClass();
                } else {
                    str2 = str3;
                }
            }
            if (str2 != null) {
                arrayList.add(str2);
            }
            i = i2;
        }
    }
}
