package androidx.compose.ui.platform;

import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.style.ResolvedTextDirection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AccessibilityIterators$LineTextSegmentIterator extends AccessibilityIterators$AbstractTextSegmentIterator {
    public static AccessibilityIterators$LineTextSegmentIterator lineInstance;
    public TextLayoutResult layoutResult;

    @Override // androidx.compose.ui.platform.AccessibilityIterators$TextSegmentIterator
    public final int[] following(int i) {
        int i2;
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
        ResolvedTextDirection resolvedTextDirection = ResolvedTextDirection.Rtl;
        if (i < 0) {
            TextLayoutResult textLayoutResult = this.layoutResult;
            if (textLayoutResult == null) {
                textLayoutResult = null;
            }
            i2 = textLayoutResult.getLineForOffset(0);
        } else {
            TextLayoutResult textLayoutResult2 = this.layoutResult;
            if (textLayoutResult2 == null) {
                textLayoutResult2 = null;
            }
            int lineForOffset = textLayoutResult2.getLineForOffset(i);
            i2 = getLineEdgeIndex(lineForOffset, resolvedTextDirection) == i ? lineForOffset : lineForOffset + 1;
        }
        TextLayoutResult textLayoutResult3 = this.layoutResult;
        if (textLayoutResult3 == null) {
            textLayoutResult3 = null;
        }
        if (i2 >= textLayoutResult3.multiParagraph.lineCount) {
            return null;
        }
        return getRange(getLineEdgeIndex(i2, resolvedTextDirection), getLineEdgeIndex(i2, ResolvedTextDirection.Ltr) + 1);
    }

    public final int getLineEdgeIndex(int i, ResolvedTextDirection resolvedTextDirection) {
        TextLayoutResult textLayoutResult = this.layoutResult;
        if (textLayoutResult == null) {
            textLayoutResult = null;
        }
        int lineStart = textLayoutResult.getLineStart(i);
        TextLayoutResult textLayoutResult2 = this.layoutResult;
        if (textLayoutResult2 == null) {
            textLayoutResult2 = null;
        }
        if (resolvedTextDirection != textLayoutResult2.getParagraphDirection(lineStart)) {
            TextLayoutResult textLayoutResult3 = this.layoutResult;
            return (textLayoutResult3 != null ? textLayoutResult3 : null).getLineStart(i);
        }
        return (this.layoutResult != null ? r3 : null).getLineEnd(i, false) - 1;
    }

    @Override // androidx.compose.ui.platform.AccessibilityIterators$TextSegmentIterator
    public final int[] preceding(int i) {
        int i2;
        String str = this.text;
        if (str == null) {
            str = null;
        }
        if (str.length() <= 0 || i <= 0) {
            return null;
        }
        String str2 = this.text;
        if (str2 == null) {
            str2 = null;
        }
        int length = str2.length();
        ResolvedTextDirection resolvedTextDirection = ResolvedTextDirection.Ltr;
        if (i > length) {
            TextLayoutResult textLayoutResult = this.layoutResult;
            if (textLayoutResult == null) {
                textLayoutResult = null;
            }
            String str3 = this.text;
            if (str3 == null) {
                str3 = null;
            }
            i2 = textLayoutResult.getLineForOffset(str3.length());
        } else {
            TextLayoutResult textLayoutResult2 = this.layoutResult;
            if (textLayoutResult2 == null) {
                textLayoutResult2 = null;
            }
            int lineForOffset = textLayoutResult2.getLineForOffset(i);
            i2 = getLineEdgeIndex(lineForOffset, resolvedTextDirection) + 1 == i ? lineForOffset : lineForOffset - 1;
        }
        if (i2 < 0) {
            return null;
        }
        return getRange(getLineEdgeIndex(i2, ResolvedTextDirection.Rtl), getLineEdgeIndex(i2, resolvedTextDirection) + 1);
    }
}
