package kotlin.text;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import androidx.compose.runtime.collection.MutableVectorKt$$ExternalSyntheticOutline0;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.functions.Function2;
import kotlin.ranges.IntProgression;
import kotlin.ranges.IntProgressionIterator;
import kotlin.ranges.IntRange;
import kotlin.sequences.SequencesKt___SequencesKt$asIterable$$inlined$Iterable$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class StringsKt extends StringsKt__StringsJVMKt {
    public static boolean contains$default(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence2 instanceof String) {
            if (indexOf$default(charSequence, (String) charSequence2, 0, 2) < 0) {
                return false;
            }
        } else if (indexOf$StringsKt__StringsKt(charSequence, charSequence2, 0, charSequence.length(), false, false) < 0) {
            return false;
        }
        return true;
    }

    public static final int getLastIndex(CharSequence charSequence) {
        return charSequence.length() - 1;
    }

    public static final int indexOf(CharSequence charSequence, String str, int i, boolean z) {
        return (z || !(charSequence instanceof String)) ? indexOf$StringsKt__StringsKt(charSequence, str, i, charSequence.length(), z, false) : ((String) charSequence).indexOf(str, i);
    }

    public static final int indexOf$StringsKt__StringsKt(CharSequence charSequence, CharSequence charSequence2, int i, int i2, boolean z, boolean z2) {
        IntProgression intProgression;
        if (z2) {
            int lastIndex = getLastIndex(charSequence);
            if (i > lastIndex) {
                i = lastIndex;
            }
            if (i2 < 0) {
                i2 = 0;
            }
            intProgression = new IntProgression(i, i2, -1);
        } else {
            if (i < 0) {
                i = 0;
            }
            int length = charSequence.length();
            if (i2 > length) {
                i2 = length;
            }
            intProgression = new IntRange(i, i2, 1);
        }
        if ((charSequence instanceof String) && (charSequence2 instanceof String)) {
            int i3 = intProgression.first;
            int i4 = intProgression.last;
            int i5 = intProgression.step;
            if ((i5 > 0 && i3 <= i4) || (i5 < 0 && i4 <= i3)) {
                while (true) {
                    String str = (String) charSequence2;
                    String str2 = (String) charSequence;
                    int length2 = ((String) charSequence2).length();
                    if (!(!z ? str.regionMatches(0, str2, i3, length2) : str.regionMatches(z, 0, str2, i3, length2))) {
                        if (i3 == i4) {
                            break;
                        }
                        i3 += i5;
                    } else {
                        return i3;
                    }
                }
            }
        } else {
            int i6 = intProgression.first;
            int i7 = intProgression.last;
            int i8 = intProgression.step;
            if ((i8 > 0 && i6 <= i7) || (i8 < 0 && i7 <= i6)) {
                while (!regionMatchesImpl(charSequence2, 0, charSequence, i6, charSequence2.length(), z)) {
                    if (i6 != i7) {
                        i6 += i8;
                    }
                }
                return i6;
            }
        }
        return -1;
    }

    public static int indexOf$default(CharSequence charSequence, char c, int i, int i2) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return !(charSequence instanceof String) ? indexOfAny(charSequence, new char[]{c}, i, false) : ((String) charSequence).indexOf(c, i);
    }

    public static final int indexOfAny(CharSequence charSequence, char[] cArr, int i, boolean z) {
        if (!z && cArr.length == 1 && (charSequence instanceof String)) {
            int length = cArr.length;
            if (length == 0) {
                throw new NoSuchElementException("Array is empty.");
            }
            if (length != 1) {
                throw new IllegalArgumentException("Array has more than one element.");
            }
            return ((String) charSequence).indexOf(cArr[0], i);
        }
        if (i < 0) {
            i = 0;
        }
        IntProgressionIterator it = new IntRange(i, getLastIndex(charSequence), 1).iterator();
        while (it.hasNext) {
            int nextInt = it.nextInt();
            char charAt = charSequence.charAt(nextInt);
            for (char c : cArr) {
                if (CharsKt.equals(c, charAt, z)) {
                    return nextInt;
                }
            }
        }
        return -1;
    }

    public static String padEnd$default(int i, String str) {
        CharSequence charSequence;
        if (i < 0) {
            throw new IllegalArgumentException(GenericDocument$$ExternalSyntheticOutline0.m("Desired length ", " is less than zero.", i));
        }
        if (i <= str.length()) {
            charSequence = str.subSequence(0, str.length());
        } else {
            StringBuilder sb = new StringBuilder(i);
            sb.append((CharSequence) str);
            IntProgressionIterator it = new IntRange(1, i - str.length(), 1).iterator();
            while (it.hasNext) {
                it.nextInt();
                sb.append(' ');
            }
            charSequence = sb;
        }
        return charSequence.toString();
    }

    public static DelimitedRangesSequence rangesDelimitedBy$StringsKt__StringsKt$default(CharSequence charSequence, String[] strArr, int i) {
        requireNonNegativeLimit(i);
        final List asList = Arrays.asList(strArr);
        return new DelimitedRangesSequence(charSequence, i, new Function2() { // from class: kotlin.text.StringsKt__StringsKt$rangesDelimitedBy$2
            final /* synthetic */ boolean $ignoreCase = false;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Object obj3;
                Pair pair;
                Object obj4;
                CharSequence charSequence2 = (CharSequence) obj;
                int intValue = ((Number) obj2).intValue();
                List list = asList;
                boolean z = this.$ignoreCase;
                if (z || list.size() != 1) {
                    if (intValue < 0) {
                        intValue = 0;
                    }
                    IntRange intRange = new IntRange(intValue, charSequence2.length(), 1);
                    if (charSequence2 instanceof String) {
                        int i2 = intRange.last;
                        int i3 = intRange.step;
                        if ((i3 > 0 && intValue <= i2) || (i3 < 0 && i2 <= intValue)) {
                            while (true) {
                                Iterator it = list.iterator();
                                while (true) {
                                    if (!it.hasNext()) {
                                        obj4 = null;
                                        break;
                                    }
                                    obj4 = it.next();
                                    String str = (String) obj4;
                                    String str2 = (String) charSequence2;
                                    int length = str.length();
                                    if (!z ? str.regionMatches(0, str2, intValue, length) : str.regionMatches(z, 0, str2, intValue, length)) {
                                        break;
                                    }
                                }
                                String str3 = (String) obj4;
                                if (str3 == null) {
                                    if (intValue == i2) {
                                        break;
                                    }
                                    intValue += i3;
                                } else {
                                    pair = new Pair(Integer.valueOf(intValue), str3);
                                    break;
                                }
                            }
                        }
                        pair = null;
                    } else {
                        int i4 = intRange.last;
                        int i5 = intRange.step;
                        if ((i5 > 0 && intValue <= i4) || (i5 < 0 && i4 <= intValue)) {
                            while (true) {
                                Iterator it2 = list.iterator();
                                while (true) {
                                    if (!it2.hasNext()) {
                                        obj3 = null;
                                        break;
                                    }
                                    obj3 = it2.next();
                                    String str4 = (String) obj3;
                                    if (StringsKt.regionMatchesImpl(str4, 0, charSequence2, intValue, str4.length(), z)) {
                                        break;
                                    }
                                }
                                String str5 = (String) obj3;
                                if (str5 == null) {
                                    if (intValue == i4) {
                                        break;
                                    }
                                    intValue += i5;
                                } else {
                                    pair = new Pair(Integer.valueOf(intValue), str5);
                                    break;
                                }
                            }
                        }
                        pair = null;
                    }
                } else {
                    String str6 = (String) CollectionsKt.single(list);
                    int indexOf$default = StringsKt.indexOf$default(charSequence2, str6, intValue, 4);
                    if (indexOf$default >= 0) {
                        pair = new Pair(Integer.valueOf(indexOf$default), str6);
                    }
                    pair = null;
                }
                if (pair != null) {
                    return new Pair(pair.getFirst(), Integer.valueOf(((String) pair.getSecond()).length()));
                }
                return null;
            }
        });
    }

    public static final boolean regionMatchesImpl(CharSequence charSequence, int i, CharSequence charSequence2, int i2, int i3, boolean z) {
        if (i2 < 0 || i < 0 || i > charSequence.length() - i3 || i2 > charSequence2.length() - i3) {
            return false;
        }
        for (int i4 = 0; i4 < i3; i4++) {
            if (!CharsKt.equals(charSequence.charAt(i + i4), charSequence2.charAt(i2 + i4), z)) {
                return false;
            }
        }
        return true;
    }

    public static CharSequence replaceRange(CharSequence charSequence, int i, int i2, CharSequence charSequence2) {
        if (i2 < i) {
            throw new IndexOutOfBoundsException(MutableVectorKt$$ExternalSyntheticOutline0.m(i2, i, "End index (", ") is less than start index (", ")."));
        }
        StringBuilder sb = new StringBuilder();
        sb.append(charSequence, 0, i);
        sb.append(charSequence2);
        sb.append(charSequence, i2, charSequence.length());
        return sb;
    }

    public static final void requireNonNegativeLimit(int i) {
        if (i < 0) {
            throw new IllegalArgumentException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "Limit must be non-negative, but was ").toString());
        }
    }

    public static final List split$StringsKt__StringsKt(CharSequence charSequence, String str, int i) {
        requireNonNegativeLimit(i);
        int indexOf = indexOf(charSequence, str, 0, false);
        if (indexOf == -1 || i == 1) {
            return Collections.singletonList(charSequence.toString());
        }
        boolean z = i > 0;
        int i2 = 10;
        if (z && i <= 10) {
            i2 = i;
        }
        ArrayList arrayList = new ArrayList(i2);
        int i3 = 0;
        do {
            arrayList.add(charSequence.subSequence(i3, indexOf).toString());
            i3 = str.length() + indexOf;
            if (z && arrayList.size() == i - 1) {
                break;
            }
            indexOf = indexOf(charSequence, str, i3, false);
        } while (indexOf != -1);
        arrayList.add(charSequence.subSequence(i3, charSequence.length()).toString());
        return arrayList;
    }

    public static List split$default(CharSequence charSequence, String[] strArr, int i, int i2) {
        if ((i2 & 4) != 0) {
            i = 0;
        }
        if (strArr.length == 1) {
            String str = strArr[0];
            if (str.length() != 0) {
                return split$StringsKt__StringsKt(charSequence, str, i);
            }
        }
        SequencesKt___SequencesKt$asIterable$$inlined$Iterable$1 sequencesKt___SequencesKt$asIterable$$inlined$Iterable$1 = new SequencesKt___SequencesKt$asIterable$$inlined$Iterable$1(rangesDelimitedBy$StringsKt__StringsKt$default(charSequence, strArr, i));
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(sequencesKt___SequencesKt$asIterable$$inlined$Iterable$1, 10));
        Iterator it = sequencesKt___SequencesKt$asIterable$$inlined$Iterable$1.iterator();
        while (true) {
            DelimitedRangesSequence$iterator$1 delimitedRangesSequence$iterator$1 = (DelimitedRangesSequence$iterator$1) it;
            if (!delimitedRangesSequence$iterator$1.hasNext()) {
                return arrayList;
            }
            IntRange intRange = (IntRange) delimitedRangesSequence$iterator$1.next();
            arrayList.add(charSequence.subSequence(intRange.first, intRange.last + 1).toString());
        }
    }

    public static String substringAfter$default(String str, String str2) {
        int indexOf$default = indexOf$default(str, str2, 0, 6);
        return indexOf$default == -1 ? str : str.substring(str2.length() + indexOf$default, str.length());
    }

    public static String substringBefore$default(String str, String str2) {
        int indexOf$default = indexOf$default(str, str2, 0, 6);
        return indexOf$default == -1 ? str : str.substring(0, indexOf$default);
    }

    public static String take(int i, String str) {
        if (i < 0) {
            throw new IllegalArgumentException(GenericDocument$$ExternalSyntheticOutline0.m("Requested character count ", " is less than zero.", i).toString());
        }
        int length = str.length();
        if (i > length) {
            i = length;
        }
        return str.substring(0, i);
    }

    public static CharSequence trim(CharSequence charSequence) {
        int length = charSequence.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean isWhitespace = CharsKt.isWhitespace(charSequence.charAt(!z ? i : length));
            if (z) {
                if (!isWhitespace) {
                    break;
                }
                length--;
            } else if (isWhitespace) {
                i++;
            } else {
                z = true;
            }
        }
        return charSequence.subSequence(i, length + 1);
    }

    public static /* synthetic */ int indexOf$default(CharSequence charSequence, String str, int i, int i2) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return indexOf(charSequence, str, i, false);
    }

    public static boolean contains$default(CharSequence charSequence, char c) {
        return indexOf$default(charSequence, c, 0, 2) >= 0;
    }

    public static List split$default(CharSequence charSequence, final char[] cArr) {
        if (cArr.length == 1) {
            return split$StringsKt__StringsKt(charSequence, String.valueOf(cArr[0]), 2);
        }
        requireNonNegativeLimit(2);
        SequencesKt___SequencesKt$asIterable$$inlined$Iterable$1 sequencesKt___SequencesKt$asIterable$$inlined$Iterable$1 = new SequencesKt___SequencesKt$asIterable$$inlined$Iterable$1(new DelimitedRangesSequence(charSequence, 2, new Function2() { // from class: kotlin.text.StringsKt__StringsKt$rangesDelimitedBy$1
            final /* synthetic */ boolean $ignoreCase = false;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                int indexOfAny = StringsKt.indexOfAny((CharSequence) obj, cArr, ((Number) obj2).intValue(), this.$ignoreCase);
                if (indexOfAny < 0) {
                    return null;
                }
                return new Pair(Integer.valueOf(indexOfAny), 1);
            }
        }));
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(sequencesKt___SequencesKt$asIterable$$inlined$Iterable$1, 10));
        Iterator it = sequencesKt___SequencesKt$asIterable$$inlined$Iterable$1.iterator();
        while (true) {
            DelimitedRangesSequence$iterator$1 delimitedRangesSequence$iterator$1 = (DelimitedRangesSequence$iterator$1) it;
            if (!delimitedRangesSequence$iterator$1.hasNext()) {
                return arrayList;
            }
            IntRange intRange = (IntRange) delimitedRangesSequence$iterator$1.next();
            arrayList.add(((String) charSequence).subSequence(intRange.first, intRange.last + 1).toString());
        }
    }
}
