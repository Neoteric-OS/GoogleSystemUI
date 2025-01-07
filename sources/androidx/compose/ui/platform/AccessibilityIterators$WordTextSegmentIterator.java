package androidx.compose.ui.platform;

import java.text.BreakIterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AccessibilityIterators$WordTextSegmentIterator extends AccessibilityIterators$AbstractTextSegmentIterator {
    public static AccessibilityIterators$WordTextSegmentIterator instance;
    public BreakIterator impl;

    @Override // androidx.compose.ui.platform.AccessibilityIterators$TextSegmentIterator
    public final int[] following(int i) {
        String str = this.text;
        if (str == null) {
            str = null;
        }
        if (str.length() <= 0) {
            return null;
        }
        String str2 = this.text;
        if (str2 == null) {
            str2 = null;
        }
        if (i >= str2.length()) {
            return null;
        }
        if (i < 0) {
            i = 0;
        }
        while (!isLetterOrDigit(i) && (!isLetterOrDigit(i) || (i != 0 && isLetterOrDigit(i - 1)))) {
            BreakIterator breakIterator = this.impl;
            if (breakIterator == null) {
                breakIterator = null;
            }
            i = breakIterator.following(i);
            if (i == -1) {
                return null;
            }
        }
        BreakIterator breakIterator2 = this.impl;
        if (breakIterator2 == null) {
            breakIterator2 = null;
        }
        int following = breakIterator2.following(i);
        if (following == -1 || !isEndBoundary$1(following)) {
            return null;
        }
        return getRange(i, following);
    }

    public final boolean isEndBoundary$1(int i) {
        if (i > 0 && isLetterOrDigit(i - 1)) {
            String str = this.text;
            if (str == null) {
                str = null;
            }
            if (i == str.length() || !isLetterOrDigit(i)) {
                return true;
            }
        }
        return false;
    }

    public final boolean isLetterOrDigit(int i) {
        if (i < 0) {
            return false;
        }
        String str = this.text;
        if (str == null) {
            str = null;
        }
        if (i >= str.length()) {
            return false;
        }
        String str2 = this.text;
        return Character.isLetterOrDigit((str2 != null ? str2 : null).codePointAt(i));
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
        while (i > 0 && !isLetterOrDigit(i - 1) && !isEndBoundary$1(i)) {
            BreakIterator breakIterator = this.impl;
            if (breakIterator == null) {
                breakIterator = null;
            }
            i = breakIterator.preceding(i);
            if (i == -1) {
                return null;
            }
        }
        BreakIterator breakIterator2 = this.impl;
        if (breakIterator2 == null) {
            breakIterator2 = null;
        }
        int preceding = breakIterator2.preceding(i);
        if (preceding == -1 || !isLetterOrDigit(preceding) || (preceding != 0 && isLetterOrDigit(preceding - 1))) {
            return null;
        }
        return getRange(preceding, i);
    }
}
