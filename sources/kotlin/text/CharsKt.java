package kotlin.text;

import androidx.collection.MutableObjectList$$ExternalSyntheticOutline0;
import kotlin.ranges.IntRange;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class CharsKt {
    public static void checkRadix(int i) {
        IntRange intRange = new IntRange(2, 36, 1);
        if (2 > i || i > intRange.last) {
            StringBuilder m = MutableObjectList$$ExternalSyntheticOutline0.m("radix ", " was not in valid range ", i);
            m.append(new IntRange(2, 36, 1));
            throw new IllegalArgumentException(m.toString());
        }
    }

    public static final boolean equals(char c, char c2, boolean z) {
        if (c == c2) {
            return true;
        }
        if (!z) {
            return false;
        }
        char upperCase = Character.toUpperCase(c);
        char upperCase2 = Character.toUpperCase(c2);
        return upperCase == upperCase2 || Character.toLowerCase(upperCase) == Character.toLowerCase(upperCase2);
    }

    public static boolean isWhitespace(char c) {
        return Character.isWhitespace(c) || Character.isSpaceChar(c);
    }
}
