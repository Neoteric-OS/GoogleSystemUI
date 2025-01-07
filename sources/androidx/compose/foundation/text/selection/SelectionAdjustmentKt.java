package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.text.selection.Selection;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SelectionAdjustmentKt {
    public static final Selection access$adjustToBoundaries(SelectionLayout selectionLayout, BoundaryFunction boundaryFunction) {
        SingleSelectionLayout singleSelectionLayout = (SingleSelectionLayout) selectionLayout;
        boolean z = singleSelectionLayout.getCrossStatus() == CrossStatus.CROSSED;
        SelectableInfo selectableInfo = singleSelectionLayout.info;
        return new Selection(anchorOnBoundary(selectableInfo, z, true, boundaryFunction), anchorOnBoundary(selectableInfo, z, false, boundaryFunction), z);
    }

    public static final Selection.AnchorInfo access$updateSelectionBoundary(final SelectionLayout selectionLayout, final SelectableInfo selectableInfo, Selection.AnchorInfo anchorInfo) {
        int i = selectableInfo.rawEndHandleOffset;
        int i2 = selectableInfo.rawStartHandleOffset;
        boolean z = ((SingleSelectionLayout) selectionLayout).isStartHandle;
        final int i3 = z ? i2 : i;
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.NONE;
        final Lazy lazy = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: androidx.compose.foundation.text.selection.SelectionAdjustmentKt$updateSelectionBoundary$currentRawLine$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Integer.valueOf(SelectableInfo.this.textLayoutResult.getLineForOffset(i3));
            }
        });
        final int i4 = z ? i : i2;
        final int i5 = i3;
        Lazy lazy2 = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: androidx.compose.foundation.text.selection.SelectionAdjustmentKt$updateSelectionBoundary$anchorSnappedToWordBoundary$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                SelectableInfo selectableInfo2 = SelectableInfo.this;
                int intValue = ((Number) lazy.getValue()).intValue();
                int i6 = i5;
                int i7 = i4;
                SingleSelectionLayout singleSelectionLayout = (SingleSelectionLayout) selectionLayout;
                boolean z2 = singleSelectionLayout.isStartHandle;
                boolean z3 = singleSelectionLayout.getCrossStatus() == CrossStatus.CROSSED;
                long m596getWordBoundaryjx7JFs = selectableInfo2.textLayoutResult.m596getWordBoundaryjx7JFs(i6);
                int i8 = TextRange.$r8$clinit;
                int i9 = (int) (m596getWordBoundaryjx7JFs >> 32);
                TextLayoutResult textLayoutResult = selectableInfo2.textLayoutResult;
                int lineForOffset = textLayoutResult.getLineForOffset(i9);
                int i10 = textLayoutResult.multiParagraph.lineCount;
                if (lineForOffset != intValue) {
                    i9 = intValue >= i10 ? textLayoutResult.getLineStart(i10 - 1) : textLayoutResult.getLineStart(intValue);
                }
                int i11 = (int) (m596getWordBoundaryjx7JFs & 4294967295L);
                if (textLayoutResult.getLineForOffset(i11) != intValue) {
                    i11 = intValue >= i10 ? textLayoutResult.getLineEnd(i10 - 1, false) : textLayoutResult.getLineEnd(intValue, false);
                }
                if (i9 == i7) {
                    return selectableInfo2.anchorForOffset(i11);
                }
                if (i11 == i7) {
                    return selectableInfo2.anchorForOffset(i9);
                }
                if (!(z3 ^ z2) ? i6 >= i9 : i6 > i11) {
                    i9 = i11;
                }
                return selectableInfo2.anchorForOffset(i9);
            }
        });
        if (1 != anchorInfo.selectableId) {
            return (Selection.AnchorInfo) lazy2.getValue();
        }
        int i6 = selectableInfo.rawPreviousHandleOffset;
        if (i3 == i6) {
            return anchorInfo;
        }
        TextLayoutResult textLayoutResult = selectableInfo.textLayoutResult;
        if (((Number) lazy.getValue()).intValue() != textLayoutResult.getLineForOffset(i6)) {
            return (Selection.AnchorInfo) lazy2.getValue();
        }
        int i7 = anchorInfo.offset;
        long m596getWordBoundaryjx7JFs = textLayoutResult.m596getWordBoundaryjx7JFs(i7);
        if (i6 != -1) {
            if (i3 != i6) {
                CrossStatus crossStatus = CrossStatus.CROSSED;
                if (!(z ^ ((i2 < i ? CrossStatus.NOT_CROSSED : i2 > i ? crossStatus : CrossStatus.COLLAPSED) == crossStatus))) {
                }
            }
            return selectableInfo.anchorForOffset(i3);
        }
        int i8 = TextRange.$r8$clinit;
        return (i7 == ((int) (m596getWordBoundaryjx7JFs >> 32)) || i7 == ((int) (4294967295L & m596getWordBoundaryjx7JFs))) ? (Selection.AnchorInfo) lazy2.getValue() : selectableInfo.anchorForOffset(i3);
    }

    public static final Selection.AnchorInfo anchorOnBoundary(SelectableInfo selectableInfo, boolean z, boolean z2, BoundaryFunction boundaryFunction) {
        long j;
        long mo182getBoundaryfzxv0v0 = boundaryFunction.mo182getBoundaryfzxv0v0(selectableInfo, z2 ? selectableInfo.rawStartHandleOffset : selectableInfo.rawEndHandleOffset);
        if (z ^ z2) {
            int i = TextRange.$r8$clinit;
            j = mo182getBoundaryfzxv0v0 >> 32;
        } else {
            int i2 = TextRange.$r8$clinit;
            j = 4294967295L & mo182getBoundaryfzxv0v0;
        }
        return selectableInfo.anchorForOffset((int) j);
    }

    public static final Selection.AnchorInfo changeOffset(Selection.AnchorInfo anchorInfo, SelectableInfo selectableInfo, int i) {
        return new Selection.AnchorInfo(selectableInfo.textLayoutResult.getBidiRunDirection(i), i, anchorInfo.selectableId);
    }
}
