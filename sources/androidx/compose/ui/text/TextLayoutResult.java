package androidx.compose.ui.text;

import android.graphics.RectF;
import androidx.collection.MutableObjectList$$ExternalSyntheticOutline0;
import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;
import androidx.compose.animation.Scale$$ExternalSyntheticOutline0;
import androidx.compose.foundation.text.ValidatingOffsetMappingKt$$ExternalSyntheticOutline0;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.AndroidPath;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.text.android.LayoutHelper;
import androidx.compose.ui.text.android.TextLayout;
import androidx.compose.ui.text.android.selection.WordIterator;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.compose.ui.unit.IntSize;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TextLayoutResult {
    public final float firstBaseline;
    public final float lastBaseline;
    public final TextLayoutInput layoutInput;
    public final MultiParagraph multiParagraph;
    public final List placeholderRects;
    public final long size;

    public TextLayoutResult(TextLayoutInput textLayoutInput, MultiParagraph multiParagraph, long j) {
        this.layoutInput = textLayoutInput;
        this.multiParagraph = multiParagraph;
        this.size = j;
        float f = 0.0f;
        this.firstBaseline = multiParagraph.paragraphInfoList.isEmpty() ? 0.0f : ((ParagraphInfo) ((ArrayList) multiParagraph.paragraphInfoList).get(0)).paragraph.layout.getLineBaseline(0);
        if (!multiParagraph.paragraphInfoList.isEmpty()) {
            ParagraphInfo paragraphInfo = (ParagraphInfo) CollectionsKt.last(multiParagraph.paragraphInfoList);
            f = paragraphInfo.paragraph.layout.getLineBaseline(r3.lineCount - 1) + paragraphInfo.top;
        }
        this.lastBaseline = f;
        this.placeholderRects = multiParagraph.placeholderRects;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TextLayoutResult)) {
            return false;
        }
        TextLayoutResult textLayoutResult = (TextLayoutResult) obj;
        return Intrinsics.areEqual(this.layoutInput, textLayoutResult.layoutInput) && Intrinsics.areEqual(this.multiParagraph, textLayoutResult.multiParagraph) && IntSize.m683equalsimpl0(this.size, textLayoutResult.size) && this.firstBaseline == textLayoutResult.firstBaseline && this.lastBaseline == textLayoutResult.lastBaseline && Intrinsics.areEqual(this.placeholderRects, textLayoutResult.placeholderRects);
    }

    public final ResolvedTextDirection getBidiRunDirection(int i) {
        MultiParagraph multiParagraph = this.multiParagraph;
        multiParagraph.requireIndexInRangeInclusiveEnd(i);
        ParagraphInfo paragraphInfo = (ParagraphInfo) ((ArrayList) multiParagraph.paragraphInfoList).get(i == multiParagraph.intrinsics.annotatedString.text.length() ? CollectionsKt__CollectionsKt.getLastIndex(multiParagraph.paragraphInfoList) : MultiParagraphKt.findParagraphByIndex(i, multiParagraph.paragraphInfoList));
        return paragraphInfo.paragraph.layout.layout.isRtlCharAt(paragraphInfo.toLocalIndex(i)) ? ResolvedTextDirection.Rtl : ResolvedTextDirection.Ltr;
    }

    public final Rect getBoundingBox(int i) {
        float secondaryHorizontal;
        float secondaryHorizontal2;
        float primaryHorizontal;
        float primaryHorizontal2;
        MultiParagraph multiParagraph = this.multiParagraph;
        multiParagraph.requireIndexInRange(i);
        ParagraphInfo paragraphInfo = (ParagraphInfo) ((ArrayList) multiParagraph.paragraphInfoList).get(MultiParagraphKt.findParagraphByIndex(i, multiParagraph.paragraphInfoList));
        AndroidParagraph androidParagraph = paragraphInfo.paragraph;
        int localIndex = paragraphInfo.toLocalIndex(i);
        if (localIndex < 0 || localIndex >= androidParagraph.charSequence.length()) {
            StringBuilder m = MutableObjectList$$ExternalSyntheticOutline0.m("offset(", ") is out of bounds [0,", localIndex);
            m.append(androidParagraph.charSequence.length());
            m.append(')');
            throw new IllegalArgumentException(m.toString().toString());
        }
        TextLayout textLayout = androidParagraph.layout;
        int lineForOffset = textLayout.layout.getLineForOffset(localIndex);
        float lineTop = textLayout.getLineTop(lineForOffset);
        float lineBottom = textLayout.getLineBottom(lineForOffset);
        boolean z = textLayout.layout.getParagraphDirection(lineForOffset) == 1;
        boolean isRtlCharAt = textLayout.layout.isRtlCharAt(localIndex);
        if (!z || isRtlCharAt) {
            if (z && isRtlCharAt) {
                primaryHorizontal = textLayout.getSecondaryHorizontal(localIndex, false);
                primaryHorizontal2 = textLayout.getSecondaryHorizontal(localIndex + 1, true);
            } else if (isRtlCharAt) {
                primaryHorizontal = textLayout.getPrimaryHorizontal(localIndex, false);
                primaryHorizontal2 = textLayout.getPrimaryHorizontal(localIndex + 1, true);
            } else {
                secondaryHorizontal = textLayout.getSecondaryHorizontal(localIndex, false);
                secondaryHorizontal2 = textLayout.getSecondaryHorizontal(localIndex + 1, true);
            }
            float f = primaryHorizontal;
            secondaryHorizontal = primaryHorizontal2;
            secondaryHorizontal2 = f;
        } else {
            secondaryHorizontal = textLayout.getPrimaryHorizontal(localIndex, false);
            secondaryHorizontal2 = textLayout.getPrimaryHorizontal(localIndex + 1, true);
        }
        RectF rectF = new RectF(secondaryHorizontal, lineTop, secondaryHorizontal2, lineBottom);
        return paragraphInfo.toGlobal(new Rect(rectF.left, rectF.top, rectF.right, rectF.bottom));
    }

    public final Rect getCursorRect(int i) {
        MultiParagraph multiParagraph = this.multiParagraph;
        multiParagraph.requireIndexInRangeInclusiveEnd(i);
        ParagraphInfo paragraphInfo = (ParagraphInfo) ((ArrayList) multiParagraph.paragraphInfoList).get(i == multiParagraph.intrinsics.annotatedString.text.length() ? CollectionsKt__CollectionsKt.getLastIndex(multiParagraph.paragraphInfoList) : MultiParagraphKt.findParagraphByIndex(i, multiParagraph.paragraphInfoList));
        AndroidParagraph androidParagraph = paragraphInfo.paragraph;
        int localIndex = paragraphInfo.toLocalIndex(i);
        if (localIndex < 0 || localIndex > androidParagraph.charSequence.length()) {
            StringBuilder m = MutableObjectList$$ExternalSyntheticOutline0.m("offset(", ") is out of bounds [0,", localIndex);
            m.append(androidParagraph.charSequence.length());
            m.append(']');
            throw new IllegalArgumentException(m.toString().toString());
        }
        TextLayout textLayout = androidParagraph.layout;
        float primaryHorizontal = textLayout.getPrimaryHorizontal(localIndex, false);
        int lineForOffset = textLayout.layout.getLineForOffset(localIndex);
        return paragraphInfo.toGlobal(new Rect(primaryHorizontal, textLayout.getLineTop(lineForOffset), primaryHorizontal, textLayout.getLineBottom(lineForOffset)));
    }

    public final int getLineEnd(int i, boolean z) {
        int lineEnd;
        MultiParagraph multiParagraph = this.multiParagraph;
        multiParagraph.requireLineIndexInRange(i);
        ParagraphInfo paragraphInfo = (ParagraphInfo) ((ArrayList) multiParagraph.paragraphInfoList).get(MultiParagraphKt.findParagraphByLineIndex(i, multiParagraph.paragraphInfoList));
        AndroidParagraph androidParagraph = paragraphInfo.paragraph;
        int i2 = i - paragraphInfo.startLineIndex;
        TextLayout textLayout = androidParagraph.layout;
        if (!z) {
            lineEnd = textLayout.getLineEnd(i2);
        } else if (textLayout.layout.getEllipsisStart(i2) == 0) {
            LayoutHelper layoutHelper = textLayout.getLayoutHelper();
            lineEnd = layoutHelper.lineEndToVisibleEnd(layoutHelper.layout.getLineEnd(i2), layoutHelper.layout.getLineStart(i2));
        } else {
            lineEnd = textLayout.layout.getEllipsisStart(i2) + textLayout.layout.getLineStart(i2);
        }
        return lineEnd + paragraphInfo.startIndex;
    }

    public final int getLineForOffset(int i) {
        MultiParagraph multiParagraph = this.multiParagraph;
        ParagraphInfo paragraphInfo = (ParagraphInfo) ((ArrayList) multiParagraph.paragraphInfoList).get(i >= multiParagraph.intrinsics.annotatedString.text.length() ? CollectionsKt__CollectionsKt.getLastIndex(multiParagraph.paragraphInfoList) : i < 0 ? 0 : MultiParagraphKt.findParagraphByIndex(i, multiParagraph.paragraphInfoList));
        return paragraphInfo.paragraph.layout.layout.getLineForOffset(paragraphInfo.toLocalIndex(i)) + paragraphInfo.startLineIndex;
    }

    public final float getLineLeft(int i) {
        MultiParagraph multiParagraph = this.multiParagraph;
        multiParagraph.requireLineIndexInRange(i);
        ParagraphInfo paragraphInfo = (ParagraphInfo) ((ArrayList) multiParagraph.paragraphInfoList).get(MultiParagraphKt.findParagraphByLineIndex(i, multiParagraph.paragraphInfoList));
        AndroidParagraph androidParagraph = paragraphInfo.paragraph;
        int i2 = i - paragraphInfo.startLineIndex;
        TextLayout textLayout = androidParagraph.layout;
        return textLayout.layout.getLineLeft(i2) + (i2 == textLayout.lineCount + (-1) ? textLayout.leftPadding : 0.0f);
    }

    public final float getLineRight(int i) {
        MultiParagraph multiParagraph = this.multiParagraph;
        multiParagraph.requireLineIndexInRange(i);
        ParagraphInfo paragraphInfo = (ParagraphInfo) ((ArrayList) multiParagraph.paragraphInfoList).get(MultiParagraphKt.findParagraphByLineIndex(i, multiParagraph.paragraphInfoList));
        AndroidParagraph androidParagraph = paragraphInfo.paragraph;
        int i2 = i - paragraphInfo.startLineIndex;
        TextLayout textLayout = androidParagraph.layout;
        return textLayout.layout.getLineRight(i2) + (i2 == textLayout.lineCount + (-1) ? textLayout.rightPadding : 0.0f);
    }

    public final int getLineStart(int i) {
        MultiParagraph multiParagraph = this.multiParagraph;
        multiParagraph.requireLineIndexInRange(i);
        ParagraphInfo paragraphInfo = (ParagraphInfo) ((ArrayList) multiParagraph.paragraphInfoList).get(MultiParagraphKt.findParagraphByLineIndex(i, multiParagraph.paragraphInfoList));
        AndroidParagraph androidParagraph = paragraphInfo.paragraph;
        return androidParagraph.layout.layout.getLineStart(i - paragraphInfo.startLineIndex) + paragraphInfo.startIndex;
    }

    public final ResolvedTextDirection getParagraphDirection(int i) {
        MultiParagraph multiParagraph = this.multiParagraph;
        multiParagraph.requireIndexInRangeInclusiveEnd(i);
        ParagraphInfo paragraphInfo = (ParagraphInfo) ((ArrayList) multiParagraph.paragraphInfoList).get(i == multiParagraph.intrinsics.annotatedString.text.length() ? CollectionsKt__CollectionsKt.getLastIndex(multiParagraph.paragraphInfoList) : MultiParagraphKt.findParagraphByIndex(i, multiParagraph.paragraphInfoList));
        AndroidParagraph androidParagraph = paragraphInfo.paragraph;
        int localIndex = paragraphInfo.toLocalIndex(i);
        TextLayout textLayout = androidParagraph.layout;
        return textLayout.layout.getParagraphDirection(textLayout.layout.getLineForOffset(localIndex)) == 1 ? ResolvedTextDirection.Ltr : ResolvedTextDirection.Rtl;
    }

    public final AndroidPath getPathForRange(final int i, final int i2) {
        MultiParagraph multiParagraph = this.multiParagraph;
        AnnotatedString annotatedString = multiParagraph.intrinsics.annotatedString;
        if (i < 0 || i > i2 || i2 > annotatedString.text.length()) {
            StringBuilder m = ValidatingOffsetMappingKt$$ExternalSyntheticOutline0.m(i, i2, "Start(", ") or End(", ") is out of range [0..");
            m.append(annotatedString.text.length());
            m.append("), or start > end!");
            throw new IllegalArgumentException(m.toString().toString());
        }
        if (i == i2) {
            return AndroidPath_androidKt.Path();
        }
        final AndroidPath Path = AndroidPath_androidKt.Path();
        MultiParagraphKt.m590findParagraphsByRangeSbBc2M(multiParagraph.paragraphInfoList, TextRangeKt.TextRange(i, i2), new Function1() { // from class: androidx.compose.ui.text.MultiParagraph$getPathForRange$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ParagraphInfo paragraphInfo = (ParagraphInfo) obj;
                Path path = Path;
                int i3 = i;
                int i4 = i2;
                AndroidParagraph androidParagraph = paragraphInfo.paragraph;
                int localIndex = paragraphInfo.toLocalIndex(i3);
                int localIndex2 = paragraphInfo.toLocalIndex(i4);
                if (localIndex < 0 || localIndex > localIndex2 || localIndex2 > androidParagraph.charSequence.length()) {
                    StringBuilder m2 = ValidatingOffsetMappingKt$$ExternalSyntheticOutline0.m(localIndex, localIndex2, "start(", ") or end(", ") is out of range [0..");
                    m2.append(androidParagraph.charSequence.length());
                    m2.append("], or start > end!");
                    throw new IllegalArgumentException(m2.toString().toString());
                }
                android.graphics.Path path2 = new android.graphics.Path();
                TextLayout textLayout = androidParagraph.layout;
                textLayout.layout.getSelectionPath(localIndex, localIndex2, path2);
                int i5 = textLayout.topPadding;
                if (i5 != 0 && !path2.isEmpty()) {
                    path2.offset(0.0f, i5);
                }
                new AndroidPath(path2).m355translatek4lQ0M((Float.floatToRawIntBits(paragraphInfo.top) & 4294967295L) | (Float.floatToRawIntBits(0.0f) << 32));
                int i6 = (int) 0;
                ((AndroidPath) path).internalPath.addPath(path2, Float.intBitsToFloat(i6), Float.intBitsToFloat(i6));
                return Unit.INSTANCE;
            }
        });
        return Path;
    }

    /* renamed from: getWordBoundary--jx7JFs, reason: not valid java name */
    public final long m596getWordBoundaryjx7JFs(int i) {
        int prevBoundary;
        int i2;
        int nextBoundary;
        MultiParagraph multiParagraph = this.multiParagraph;
        multiParagraph.requireIndexInRangeInclusiveEnd(i);
        ParagraphInfo paragraphInfo = (ParagraphInfo) ((ArrayList) multiParagraph.paragraphInfoList).get(i == multiParagraph.intrinsics.annotatedString.text.length() ? CollectionsKt__CollectionsKt.getLastIndex(multiParagraph.paragraphInfoList) : MultiParagraphKt.findParagraphByIndex(i, multiParagraph.paragraphInfoList));
        AndroidParagraph androidParagraph = paragraphInfo.paragraph;
        int localIndex = paragraphInfo.toLocalIndex(i);
        WordIterator wordIterator = androidParagraph.layout.getWordIterator();
        if (wordIterator.isOnPunctuation(wordIterator.prevBoundary(localIndex))) {
            wordIterator.checkOffsetIsValid(localIndex);
            prevBoundary = localIndex;
            while (prevBoundary != -1 && (!wordIterator.isOnPunctuation(prevBoundary) || wordIterator.isAfterPunctuation(prevBoundary))) {
                prevBoundary = wordIterator.prevBoundary(prevBoundary);
            }
        } else {
            wordIterator.checkOffsetIsValid(localIndex);
            prevBoundary = wordIterator.isOnLetterOrDigitOrEmoji(localIndex) ? (!wordIterator.isBoundary(localIndex) || wordIterator.isAfterLetterOrDigitOrEmoji(localIndex)) ? wordIterator.prevBoundary(localIndex) : localIndex : wordIterator.isAfterLetterOrDigitOrEmoji(localIndex) ? wordIterator.prevBoundary(localIndex) : -1;
        }
        if (prevBoundary == -1) {
            prevBoundary = localIndex;
        }
        if (wordIterator.isAfterPunctuation(wordIterator.nextBoundary(localIndex))) {
            wordIterator.checkOffsetIsValid(localIndex);
            i2 = localIndex;
            while (i2 != -1 && (wordIterator.isOnPunctuation(i2) || !wordIterator.isAfterPunctuation(i2))) {
                i2 = wordIterator.nextBoundary(i2);
            }
        } else {
            wordIterator.checkOffsetIsValid(localIndex);
            if (wordIterator.isAfterLetterOrDigitOrEmoji(localIndex)) {
                nextBoundary = (!wordIterator.isBoundary(localIndex) || wordIterator.isOnLetterOrDigitOrEmoji(localIndex)) ? wordIterator.nextBoundary(localIndex) : localIndex;
            } else if (wordIterator.isOnLetterOrDigitOrEmoji(localIndex)) {
                nextBoundary = wordIterator.nextBoundary(localIndex);
            } else {
                i2 = -1;
            }
            i2 = nextBoundary;
        }
        if (i2 != -1) {
            localIndex = i2;
        }
        return paragraphInfo.m591toGlobalxdX6G0(TextRangeKt.TextRange(prevBoundary, localIndex), false);
    }

    public final int hashCode() {
        return this.placeholderRects.hashCode() + FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(Scale$$ExternalSyntheticOutline0.m((this.multiParagraph.hashCode() + (this.layoutInput.hashCode() * 31)) * 31, 31, this.size), this.firstBaseline, 31), this.lastBaseline, 31);
    }

    public final String toString() {
        return "TextLayoutResult(layoutInput=" + this.layoutInput + ", multiParagraph=" + this.multiParagraph + ", size=" + ((Object) IntSize.m684toStringimpl(this.size)) + ", firstBaseline=" + this.firstBaseline + ", lastBaseline=" + this.lastBaseline + ", placeholderRects=" + this.placeholderRects + ')';
    }
}
