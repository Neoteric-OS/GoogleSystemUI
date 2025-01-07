package androidx.compose.ui.platform;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AccessibilityIterators$ParagraphTextSegmentIterator extends AccessibilityIterators$AbstractTextSegmentIterator {
    public static AccessibilityIterators$ParagraphTextSegmentIterator instance;

    @Override // androidx.compose.ui.platform.AccessibilityIterators$TextSegmentIterator
    public final int[] following(int i) {
        String str = this.text;
        if (str == null) {
            str = null;
        }
        int length = str.length();
        if (length <= 0 || i >= length) {
            return null;
        }
        if (i < 0) {
            i = 0;
        }
        while (i < length) {
            String str2 = this.text;
            if (str2 == null) {
                str2 = null;
            }
            if (str2.charAt(i) != '\n' || isStartBoundary(i)) {
                break;
            }
            i++;
        }
        if (i >= length) {
            return null;
        }
        int i2 = i + 1;
        while (i2 < length && !isEndBoundary(i2)) {
            i2++;
        }
        return getRange(i, i2);
    }

    public final boolean isEndBoundary(int i) {
        if (i > 0) {
            String str = this.text;
            if (str == null) {
                str = null;
            }
            if (str.charAt(i - 1) != '\n') {
                String str2 = this.text;
                if (str2 == null) {
                    str2 = null;
                }
                if (i != str2.length()) {
                    String str3 = this.text;
                    if ((str3 != null ? str3 : null).charAt(i) == '\n') {
                    }
                }
                return true;
            }
        }
        return false;
    }

    public final boolean isStartBoundary(int i) {
        String str = this.text;
        if (str == null) {
            str = null;
        }
        if (str.charAt(i) != '\n') {
            if (i == 0) {
                return true;
            }
            String str2 = this.text;
            if ((str2 != null ? str2 : null).charAt(i - 1) == '\n') {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.compose.ui.platform.AccessibilityIterators$TextSegmentIterator
    public final int[] preceding(int i) {
        String str = this.text;
        if (str == null) {
            str = null;
        }
        int length = str.length();
        if (length <= 0 || i <= 0) {
            return null;
        }
        if (i > length) {
            i = length;
        }
        while (i > 0) {
            String str2 = this.text;
            if (str2 == null) {
                str2 = null;
            }
            if (str2.charAt(i - 1) != '\n' || isEndBoundary(i)) {
                break;
            }
            i--;
        }
        if (i <= 0) {
            return null;
        }
        int i2 = i - 1;
        while (i2 > 0 && !isStartBoundary(i2)) {
            i2--;
        }
        return getRange(i2, i);
    }
}
