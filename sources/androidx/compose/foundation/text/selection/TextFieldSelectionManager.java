package androidx.compose.foundation.text.selection;

import android.view.ActionMode;
import androidx.compose.foundation.text.Handle;
import androidx.compose.foundation.text.HandleState;
import androidx.compose.foundation.text.LegacyTextFieldState;
import androidx.compose.foundation.text.TextDragObserver;
import androidx.compose.foundation.text.TextLayoutResultProxy;
import androidx.compose.foundation.text.UndoManager;
import androidx.compose.foundation.text.ValidatingOffsetMappingKt;
import androidx.compose.foundation.text.selection.SelectionAdjustment;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.hapticfeedback.HapticFeedback;
import androidx.compose.ui.hapticfeedback.PlatformHapticFeedback;
import androidx.compose.ui.platform.AndroidClipboardManager;
import androidx.compose.ui.platform.AndroidTextToolbar;
import androidx.compose.ui.platform.ClipboardManager;
import androidx.compose.ui.platform.TextToolbar;
import androidx.compose.ui.platform.TextToolbarStatus;
import androidx.compose.ui.text.AndroidParagraph;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.MultiParagraph;
import androidx.compose.ui.text.MultiParagraphKt;
import androidx.compose.ui.text.ParagraphInfo;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.android.TextLayout;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.TextFieldValueKt;
import androidx.compose.ui.text.input.VisualTransformation;
import java.util.ArrayList;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TextFieldSelectionManager {
    public ClipboardManager clipboardManager;
    public final MutableState currentDragPosition$delegate;
    public Integer dragBeginOffsetInText;
    public long dragBeginPosition;
    public long dragTotalDistance;
    public final MutableState draggingHandle$delegate;
    public final MutableState editable$delegate;
    public final MutableState enabled$delegate;
    public FocusRequester focusRequester;
    public HapticFeedback hapticFeedBack;
    public final TextFieldSelectionManager$mouseSelectionObserver$1 mouseSelectionObserver;
    public TextFieldValue oldValue;
    public int previousRawDragOffset;
    public SelectionLayout previousSelectionLayout;
    public LegacyTextFieldState state;
    public TextToolbar textToolbar;
    public final TextFieldSelectionManager$touchSelectionObserver$1 touchSelectionObserver;
    public final UndoManager undoManager;
    public OffsetMapping offsetMapping = ValidatingOffsetMappingKt.ValidatingEmptyOffsetMappingIdentity;
    public Lambda onValueChange = new Function1() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$onValueChange$1
        @Override // kotlin.jvm.functions.Function1
        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return Unit.INSTANCE;
        }
    };
    public final MutableState value$delegate = SnapshotStateKt.mutableStateOf$default(new TextFieldValue(7, 0, (String) null));
    public VisualTransformation visualTransformation = VisualTransformation.Companion.None;

    /* JADX WARN: Type inference failed for: r6v12, types: [androidx.compose.foundation.text.selection.TextFieldSelectionManager$touchSelectionObserver$1] */
    /* JADX WARN: Type inference failed for: r6v13, types: [androidx.compose.foundation.text.selection.TextFieldSelectionManager$mouseSelectionObserver$1] */
    public TextFieldSelectionManager(UndoManager undoManager) {
        this.undoManager = undoManager;
        Boolean bool = Boolean.TRUE;
        this.editable$delegate = SnapshotStateKt.mutableStateOf$default(bool);
        this.enabled$delegate = SnapshotStateKt.mutableStateOf$default(bool);
        this.dragBeginPosition = 0L;
        this.dragTotalDistance = 0L;
        this.draggingHandle$delegate = SnapshotStateKt.mutableStateOf$default(null);
        this.currentDragPosition$delegate = SnapshotStateKt.mutableStateOf$default(null);
        this.previousRawDragOffset = -1;
        this.oldValue = new TextFieldValue(7, 0L, (String) null);
        this.touchSelectionObserver = new TextDragObserver() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$touchSelectionObserver$1
            @Override // androidx.compose.foundation.text.TextDragObserver
            public final void onCancel() {
                onEnd();
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* renamed from: onDrag-k-4lQ0M */
            public final void mo160onDragk4lQ0M(long j) {
                TextLayoutResultProxy layoutResult;
                TextFieldSelectionManager textFieldSelectionManager = TextFieldSelectionManager.this;
                if (!textFieldSelectionManager.getEnabled() || textFieldSelectionManager.getValue$foundation_release().annotatedString.text.length() == 0) {
                    return;
                }
                textFieldSelectionManager.dragTotalDistance = Offset.m315plusMKHz9U(textFieldSelectionManager.dragTotalDistance, j);
                LegacyTextFieldState legacyTextFieldState = textFieldSelectionManager.state;
                if (legacyTextFieldState != null && (layoutResult = legacyTextFieldState.getLayoutResult()) != null) {
                    ((SnapshotMutableStateImpl) textFieldSelectionManager.currentDragPosition$delegate).setValue(new Offset(Offset.m315plusMKHz9U(textFieldSelectionManager.dragBeginPosition, textFieldSelectionManager.dragTotalDistance)));
                    Integer num = textFieldSelectionManager.dragBeginOffsetInText;
                    SelectionAdjustment$Companion$$ExternalSyntheticLambda0 selectionAdjustment$Companion$$ExternalSyntheticLambda0 = SelectionAdjustment.Companion.Word;
                    if (num == null) {
                        Offset m190getCurrentDragPosition_m7T9E = textFieldSelectionManager.m190getCurrentDragPosition_m7T9E();
                        Intrinsics.checkNotNull(m190getCurrentDragPosition_m7T9E);
                        if (!layoutResult.m167isPositionOnTextk4lQ0M(m190getCurrentDragPosition_m7T9E.packedValue)) {
                            int transformedToOriginal = textFieldSelectionManager.offsetMapping.transformedToOriginal(layoutResult.m166getOffsetForPosition3MmeM6k(textFieldSelectionManager.dragBeginPosition, true));
                            OffsetMapping offsetMapping = textFieldSelectionManager.offsetMapping;
                            Offset m190getCurrentDragPosition_m7T9E2 = textFieldSelectionManager.m190getCurrentDragPosition_m7T9E();
                            Intrinsics.checkNotNull(m190getCurrentDragPosition_m7T9E2);
                            if (transformedToOriginal == offsetMapping.transformedToOriginal(layoutResult.m166getOffsetForPosition3MmeM6k(m190getCurrentDragPosition_m7T9E2.packedValue, true))) {
                                selectionAdjustment$Companion$$ExternalSyntheticLambda0 = SelectionAdjustment.Companion.None;
                            }
                            TextFieldValue value$foundation_release = textFieldSelectionManager.getValue$foundation_release();
                            Offset m190getCurrentDragPosition_m7T9E3 = textFieldSelectionManager.m190getCurrentDragPosition_m7T9E();
                            Intrinsics.checkNotNull(m190getCurrentDragPosition_m7T9E3);
                            TextFieldSelectionManager.m187access$updateSelection8UEBfa8(textFieldSelectionManager, value$foundation_release, m190getCurrentDragPosition_m7T9E3.packedValue, false, false, selectionAdjustment$Companion$$ExternalSyntheticLambda0, true);
                            int i = TextRange.$r8$clinit;
                        }
                    }
                    Integer num2 = textFieldSelectionManager.dragBeginOffsetInText;
                    int intValue = num2 != null ? num2.intValue() : layoutResult.m166getOffsetForPosition3MmeM6k(textFieldSelectionManager.dragBeginPosition, false);
                    Offset m190getCurrentDragPosition_m7T9E4 = textFieldSelectionManager.m190getCurrentDragPosition_m7T9E();
                    Intrinsics.checkNotNull(m190getCurrentDragPosition_m7T9E4);
                    int m166getOffsetForPosition3MmeM6k = layoutResult.m166getOffsetForPosition3MmeM6k(m190getCurrentDragPosition_m7T9E4.packedValue, false);
                    if (textFieldSelectionManager.dragBeginOffsetInText == null && intValue == m166getOffsetForPosition3MmeM6k) {
                        return;
                    }
                    TextFieldValue value$foundation_release2 = textFieldSelectionManager.getValue$foundation_release();
                    Offset m190getCurrentDragPosition_m7T9E5 = textFieldSelectionManager.m190getCurrentDragPosition_m7T9E();
                    Intrinsics.checkNotNull(m190getCurrentDragPosition_m7T9E5);
                    TextFieldSelectionManager.m187access$updateSelection8UEBfa8(textFieldSelectionManager, value$foundation_release2, m190getCurrentDragPosition_m7T9E5.packedValue, false, false, selectionAdjustment$Companion$$ExternalSyntheticLambda0, true);
                    int i2 = TextRange.$r8$clinit;
                }
                textFieldSelectionManager.updateFloatingToolbar(false);
            }

            public final void onEnd() {
                TextFieldSelectionManager textFieldSelectionManager = TextFieldSelectionManager.this;
                ((SnapshotMutableStateImpl) textFieldSelectionManager.draggingHandle$delegate).setValue(null);
                ((SnapshotMutableStateImpl) textFieldSelectionManager.currentDragPosition$delegate).setValue(null);
                textFieldSelectionManager.updateFloatingToolbar(true);
                textFieldSelectionManager.dragBeginOffsetInText = null;
                boolean m598getCollapsedimpl = TextRange.m598getCollapsedimpl(textFieldSelectionManager.getValue$foundation_release().selection);
                textFieldSelectionManager.setHandleState(m598getCollapsedimpl ? HandleState.Cursor : HandleState.Selection);
                LegacyTextFieldState legacyTextFieldState = textFieldSelectionManager.state;
                if (legacyTextFieldState != null) {
                    ((SnapshotMutableStateImpl) legacyTextFieldState.showSelectionHandleStart$delegate).setValue(Boolean.valueOf(!m598getCollapsedimpl && TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(textFieldSelectionManager, true)));
                }
                LegacyTextFieldState legacyTextFieldState2 = textFieldSelectionManager.state;
                if (legacyTextFieldState2 != null) {
                    ((SnapshotMutableStateImpl) legacyTextFieldState2.showSelectionHandleEnd$delegate).setValue(Boolean.valueOf(!m598getCollapsedimpl && TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(textFieldSelectionManager, false)));
                }
                LegacyTextFieldState legacyTextFieldState3 = textFieldSelectionManager.state;
                if (legacyTextFieldState3 == null) {
                    return;
                }
                ((SnapshotMutableStateImpl) legacyTextFieldState3.showCursorHandle$delegate).setValue(Boolean.valueOf(m598getCollapsedimpl && TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(textFieldSelectionManager, true)));
            }

            /* JADX WARN: Type inference failed for: r1v10, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
            @Override // androidx.compose.foundation.text.TextDragObserver
            /* renamed from: onStart-k-4lQ0M */
            public final void mo161onStartk4lQ0M(long j) {
                TextLayoutResultProxy layoutResult;
                TextLayoutResultProxy layoutResult2;
                TextFieldSelectionManager textFieldSelectionManager = TextFieldSelectionManager.this;
                if (textFieldSelectionManager.getEnabled()) {
                    MutableState mutableState = textFieldSelectionManager.draggingHandle$delegate;
                    if (((Handle) ((SnapshotMutableStateImpl) mutableState).getValue()) != null) {
                        return;
                    }
                    ((SnapshotMutableStateImpl) mutableState).setValue(Handle.SelectionEnd);
                    textFieldSelectionManager.previousRawDragOffset = -1;
                    textFieldSelectionManager.hideSelectionToolbar$foundation_release();
                    LegacyTextFieldState legacyTextFieldState = textFieldSelectionManager.state;
                    if (legacyTextFieldState == null || (layoutResult2 = legacyTextFieldState.getLayoutResult()) == null || !layoutResult2.m167isPositionOnTextk4lQ0M(j)) {
                        LegacyTextFieldState legacyTextFieldState2 = textFieldSelectionManager.state;
                        if (legacyTextFieldState2 != null && (layoutResult = legacyTextFieldState2.getLayoutResult()) != null) {
                            int transformedToOriginal = textFieldSelectionManager.offsetMapping.transformedToOriginal(layoutResult.m166getOffsetForPosition3MmeM6k(j, true));
                            TextFieldValue m188createTextFieldValueFDrldGo = TextFieldSelectionManager.m188createTextFieldValueFDrldGo(textFieldSelectionManager.getValue$foundation_release().annotatedString, TextRangeKt.TextRange(transformedToOriginal, transformedToOriginal));
                            textFieldSelectionManager.enterSelectionMode$foundation_release(false);
                            HapticFeedback hapticFeedback = textFieldSelectionManager.hapticFeedBack;
                            if (hapticFeedback != null) {
                                ((PlatformHapticFeedback) hapticFeedback).m445performHapticFeedbackCdsT49E(9);
                            }
                            textFieldSelectionManager.onValueChange.invoke(m188createTextFieldValueFDrldGo);
                        }
                    } else {
                        if (textFieldSelectionManager.getValue$foundation_release().annotatedString.text.length() == 0) {
                            return;
                        }
                        textFieldSelectionManager.enterSelectionMode$foundation_release(false);
                        textFieldSelectionManager.dragBeginOffsetInText = Integer.valueOf((int) (TextFieldSelectionManager.m187access$updateSelection8UEBfa8(textFieldSelectionManager, TextFieldValue.m623copy3r_uNRQ$default(textFieldSelectionManager.getValue$foundation_release(), null, TextRange.Zero, 5), j, true, false, SelectionAdjustment.Companion.Word, true) >> 32));
                    }
                    textFieldSelectionManager.setHandleState(HandleState.None);
                    textFieldSelectionManager.dragBeginPosition = j;
                    ((SnapshotMutableStateImpl) textFieldSelectionManager.currentDragPosition$delegate).setValue(new Offset(j));
                    textFieldSelectionManager.dragTotalDistance = 0L;
                }
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public final void onStop() {
                onEnd();
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* renamed from: onDown-k-4lQ0M */
            public final void mo159onDownk4lQ0M() {
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public final void onUp() {
            }
        };
        this.mouseSelectionObserver = new MouseSelectionObserver() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$mouseSelectionObserver$1
            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            /* renamed from: onDrag-3MmeM6k */
            public final boolean mo183onDrag3MmeM6k(long j, SelectionAdjustment selectionAdjustment) {
                LegacyTextFieldState legacyTextFieldState;
                TextFieldSelectionManager textFieldSelectionManager = TextFieldSelectionManager.this;
                if (!textFieldSelectionManager.getEnabled() || textFieldSelectionManager.getValue$foundation_release().annotatedString.text.length() == 0 || (legacyTextFieldState = textFieldSelectionManager.state) == null || legacyTextFieldState.getLayoutResult() == null) {
                    return false;
                }
                updateMouseSelection(textFieldSelectionManager.getValue$foundation_release(), j, false, selectionAdjustment);
                return true;
            }

            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            /* renamed from: onStart-3MmeM6k */
            public final boolean mo184onStart3MmeM6k(long j, SelectionAdjustment selectionAdjustment) {
                LegacyTextFieldState legacyTextFieldState;
                TextFieldSelectionManager textFieldSelectionManager = TextFieldSelectionManager.this;
                if (!textFieldSelectionManager.getEnabled() || textFieldSelectionManager.getValue$foundation_release().annotatedString.text.length() == 0 || (legacyTextFieldState = textFieldSelectionManager.state) == null || legacyTextFieldState.getLayoutResult() == null) {
                    return false;
                }
                FocusRequester focusRequester = textFieldSelectionManager.focusRequester;
                if (focusRequester != null) {
                    focusRequester.focus$ui_release();
                }
                textFieldSelectionManager.dragBeginPosition = j;
                textFieldSelectionManager.previousRawDragOffset = -1;
                textFieldSelectionManager.enterSelectionMode$foundation_release(true);
                updateMouseSelection(textFieldSelectionManager.getValue$foundation_release(), textFieldSelectionManager.dragBeginPosition, true, selectionAdjustment);
                return true;
            }

            public final void updateMouseSelection(TextFieldValue textFieldValue, long j, boolean z, SelectionAdjustment selectionAdjustment) {
                TextFieldSelectionManager.this.setHandleState(TextRange.m598getCollapsedimpl(TextFieldSelectionManager.m187access$updateSelection8UEBfa8(TextFieldSelectionManager.this, textFieldValue, j, z, false, selectionAdjustment, false)) ? HandleState.Cursor : HandleState.Selection);
            }

            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            public final void onDragDone() {
            }
        };
    }

    /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r10v1 androidx.compose.foundation.text.selection.SingleSelectionLayout, still in use, count: 2, list:
          (r10v1 androidx.compose.foundation.text.selection.SingleSelectionLayout) from 0x008c: MOVE (r20v0 androidx.compose.foundation.text.selection.SingleSelectionLayout) = (r10v1 androidx.compose.foundation.text.selection.SingleSelectionLayout) (LINE:141)
          (r10v1 androidx.compose.foundation.text.selection.SingleSelectionLayout) from 0x0067: MOVE (r20v2 androidx.compose.foundation.text.selection.SingleSelectionLayout) = (r10v1 androidx.compose.foundation.text.selection.SingleSelectionLayout) (LINE:104)
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:91)
        	at jadx.core.utils.InsnRemover.addAndUnbind(InsnRemover.java:57)
        	at jadx.core.dex.visitors.ModVisitor.removeStep(ModVisitor.java:447)
        	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:96)
        */
    /* JADX WARN: Type inference failed for: r4v8, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    /* renamed from: access$updateSelection-8UEBfa8, reason: not valid java name */
    public static final long m187access$updateSelection8UEBfa8(androidx.compose.foundation.text.selection.TextFieldSelectionManager r21, androidx.compose.ui.text.input.TextFieldValue r22, long r23, boolean r25, boolean r26, androidx.compose.foundation.text.selection.SelectionAdjustment r27, boolean r28) {
        /*
            Method dump skipped, instructions count: 458
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.TextFieldSelectionManager.m187access$updateSelection8UEBfa8(androidx.compose.foundation.text.selection.TextFieldSelectionManager, androidx.compose.ui.text.input.TextFieldValue, long, boolean, boolean, androidx.compose.foundation.text.selection.SelectionAdjustment, boolean):long");
    }

    /* renamed from: createTextFieldValue-FDrldGo, reason: not valid java name */
    public static TextFieldValue m188createTextFieldValueFDrldGo(AnnotatedString annotatedString, long j) {
        return new TextFieldValue(annotatedString, j, (TextRange) null);
    }

    /* JADX WARN: Type inference failed for: r0v7, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    public final void copy$foundation_release(boolean z) {
        if (TextRange.m598getCollapsedimpl(getValue$foundation_release().selection)) {
            return;
        }
        ClipboardManager clipboardManager = this.clipboardManager;
        if (clipboardManager != null) {
            ((AndroidClipboardManager) clipboardManager).setText(TextFieldValueKt.getSelectedText(getValue$foundation_release()));
        }
        if (z) {
            int m600getMaximpl = TextRange.m600getMaximpl(getValue$foundation_release().selection);
            this.onValueChange.invoke(m188createTextFieldValueFDrldGo(getValue$foundation_release().annotatedString, TextRangeKt.TextRange(m600getMaximpl, m600getMaximpl)));
            setHandleState(HandleState.None);
        }
    }

    /* JADX WARN: Type inference failed for: r1v10, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    public final void cut$foundation_release() {
        if (TextRange.m598getCollapsedimpl(getValue$foundation_release().selection)) {
            return;
        }
        ClipboardManager clipboardManager = this.clipboardManager;
        if (clipboardManager != null) {
            ((AndroidClipboardManager) clipboardManager).setText(TextFieldValueKt.getSelectedText(getValue$foundation_release()));
        }
        AnnotatedString textBeforeSelection = TextFieldValueKt.getTextBeforeSelection(getValue$foundation_release(), getValue$foundation_release().annotatedString.text.length());
        AnnotatedString textAfterSelection = TextFieldValueKt.getTextAfterSelection(getValue$foundation_release(), getValue$foundation_release().annotatedString.text.length());
        AnnotatedString.Builder builder = new AnnotatedString.Builder(textBeforeSelection);
        builder.append(textAfterSelection);
        AnnotatedString annotatedString = builder.toAnnotatedString();
        int m601getMinimpl = TextRange.m601getMinimpl(getValue$foundation_release().selection);
        this.onValueChange.invoke(m188createTextFieldValueFDrldGo(annotatedString, TextRangeKt.TextRange(m601getMinimpl, m601getMinimpl)));
        setHandleState(HandleState.None);
        this.undoManager.forceNextSnapshot = true;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    /* renamed from: deselect-_kEHs6E$foundation_release, reason: not valid java name */
    public final void m189deselect_kEHs6E$foundation_release(Offset offset) {
        if (!TextRange.m598getCollapsedimpl(getValue$foundation_release().selection)) {
            LegacyTextFieldState legacyTextFieldState = this.state;
            TextLayoutResultProxy layoutResult = legacyTextFieldState != null ? legacyTextFieldState.getLayoutResult() : null;
            int m600getMaximpl = (offset == null || layoutResult == null) ? TextRange.m600getMaximpl(getValue$foundation_release().selection) : this.offsetMapping.transformedToOriginal(layoutResult.m166getOffsetForPosition3MmeM6k(offset.packedValue, true));
            this.onValueChange.invoke(TextFieldValue.m623copy3r_uNRQ$default(getValue$foundation_release(), null, TextRangeKt.TextRange(m600getMaximpl, m600getMaximpl), 5));
        }
        setHandleState((offset == null || getValue$foundation_release().annotatedString.text.length() <= 0) ? HandleState.None : HandleState.Cursor);
        updateFloatingToolbar(false);
    }

    public final void enterSelectionMode$foundation_release(boolean z) {
        FocusRequester focusRequester;
        LegacyTextFieldState legacyTextFieldState = this.state;
        if (legacyTextFieldState != null && !legacyTextFieldState.getHasFocus() && (focusRequester = this.focusRequester) != null) {
            focusRequester.focus$ui_release();
        }
        this.oldValue = getValue$foundation_release();
        updateFloatingToolbar(z);
        setHandleState(HandleState.Selection);
    }

    /* renamed from: getCurrentDragPosition-_m7T9-E, reason: not valid java name */
    public final Offset m190getCurrentDragPosition_m7T9E() {
        return (Offset) ((SnapshotMutableStateImpl) this.currentDragPosition$delegate).getValue();
    }

    public final boolean getEnabled() {
        return ((Boolean) ((SnapshotMutableStateImpl) this.enabled$delegate).getValue()).booleanValue();
    }

    /* renamed from: getHandlePosition-tuRUvjQ$foundation_release, reason: not valid java name */
    public final long m191getHandlePositiontuRUvjQ$foundation_release(boolean z) {
        TextLayoutResultProxy layoutResult;
        TextLayoutResult textLayoutResult;
        long j;
        LegacyTextFieldState legacyTextFieldState = this.state;
        if (legacyTextFieldState == null || (layoutResult = legacyTextFieldState.getLayoutResult()) == null || (textLayoutResult = layoutResult.value) == null) {
            return 9205357640488583168L;
        }
        LegacyTextFieldState legacyTextFieldState2 = this.state;
        AnnotatedString annotatedString = legacyTextFieldState2 != null ? legacyTextFieldState2.textDelegate.text : null;
        if (annotatedString == null) {
            return 9205357640488583168L;
        }
        if (!Intrinsics.areEqual(annotatedString.text, textLayoutResult.layoutInput.text.text)) {
            return 9205357640488583168L;
        }
        TextFieldValue value$foundation_release = getValue$foundation_release();
        if (z) {
            long j2 = value$foundation_release.selection;
            int i = TextRange.$r8$clinit;
            j = j2 >> 32;
        } else {
            long j3 = value$foundation_release.selection;
            int i2 = TextRange.$r8$clinit;
            j = j3 & 4294967295L;
        }
        int originalToTransformed = this.offsetMapping.originalToTransformed((int) j);
        boolean m602getReversedimpl = TextRange.m602getReversedimpl(getValue$foundation_release().selection);
        int lineForOffset = textLayoutResult.getLineForOffset(originalToTransformed);
        MultiParagraph multiParagraph = textLayoutResult.multiParagraph;
        if (lineForOffset >= multiParagraph.lineCount) {
            return 9205357640488583168L;
        }
        boolean z2 = textLayoutResult.getBidiRunDirection(((!z || m602getReversedimpl) && (z || !m602getReversedimpl)) ? Math.max(originalToTransformed + (-1), 0) : originalToTransformed) == textLayoutResult.getParagraphDirection(originalToTransformed);
        multiParagraph.requireIndexInRangeInclusiveEnd(originalToTransformed);
        ParagraphInfo paragraphInfo = (ParagraphInfo) ((ArrayList) multiParagraph.paragraphInfoList).get(originalToTransformed == multiParagraph.intrinsics.annotatedString.text.length() ? CollectionsKt__CollectionsKt.getLastIndex(multiParagraph.paragraphInfoList) : MultiParagraphKt.findParagraphByIndex(originalToTransformed, multiParagraph.paragraphInfoList));
        AndroidParagraph androidParagraph = paragraphInfo.paragraph;
        int localIndex = paragraphInfo.toLocalIndex(originalToTransformed);
        TextLayout textLayout = androidParagraph.layout;
        float primaryHorizontal = z2 ? textLayout.getPrimaryHorizontal(localIndex, false) : textLayout.getSecondaryHorizontal(localIndex, false);
        long j4 = textLayoutResult.size;
        return (Float.floatToRawIntBits(RangesKt.coerceIn(primaryHorizontal, 0.0f, (int) (j4 >> 32))) << 32) | (Float.floatToRawIntBits(RangesKt.coerceIn(multiParagraph.getLineBottom(lineForOffset), 0.0f, (int) (j4 & 4294967295L))) & 4294967295L);
    }

    public final TextFieldValue getValue$foundation_release() {
        return (TextFieldValue) ((SnapshotMutableStateImpl) this.value$delegate).getValue();
    }

    public final void hideSelectionToolbar$foundation_release() {
        TextToolbar textToolbar = this.textToolbar;
        if ((textToolbar != null ? ((AndroidTextToolbar) textToolbar).status : null) != TextToolbarStatus.Shown || textToolbar == null) {
            return;
        }
        AndroidTextToolbar androidTextToolbar = (AndroidTextToolbar) textToolbar;
        androidTextToolbar.status = TextToolbarStatus.Hidden;
        ActionMode actionMode = androidTextToolbar.actionMode;
        if (actionMode != null) {
            actionMode.finish();
        }
        androidTextToolbar.actionMode = null;
    }

    /* JADX WARN: Type inference failed for: r1v4, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    public final void paste$foundation_release() {
        AnnotatedString text;
        ClipboardManager clipboardManager = this.clipboardManager;
        if (clipboardManager == null || (text = ((AndroidClipboardManager) clipboardManager).getText()) == null) {
            return;
        }
        AnnotatedString.Builder builder = new AnnotatedString.Builder(TextFieldValueKt.getTextBeforeSelection(getValue$foundation_release(), getValue$foundation_release().annotatedString.text.length()));
        builder.append(text);
        AnnotatedString annotatedString = builder.toAnnotatedString();
        AnnotatedString textAfterSelection = TextFieldValueKt.getTextAfterSelection(getValue$foundation_release(), getValue$foundation_release().annotatedString.text.length());
        AnnotatedString.Builder builder2 = new AnnotatedString.Builder(annotatedString);
        builder2.append(textAfterSelection);
        AnnotatedString annotatedString2 = builder2.toAnnotatedString();
        int length = text.text.length() + TextRange.m601getMinimpl(getValue$foundation_release().selection);
        this.onValueChange.invoke(m188createTextFieldValueFDrldGo(annotatedString2, TextRangeKt.TextRange(length, length)));
        setHandleState(HandleState.None);
        this.undoManager.forceNextSnapshot = true;
    }

    /* JADX WARN: Type inference failed for: r1v5, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    public final void selectAll$foundation_release() {
        TextFieldValue m188createTextFieldValueFDrldGo = m188createTextFieldValueFDrldGo(getValue$foundation_release().annotatedString, TextRangeKt.TextRange(0, getValue$foundation_release().annotatedString.text.length()));
        this.onValueChange.invoke(m188createTextFieldValueFDrldGo);
        this.oldValue = TextFieldValue.m623copy3r_uNRQ$default(this.oldValue, null, m188createTextFieldValueFDrldGo.selection, 5);
        enterSelectionMode$foundation_release(true);
    }

    public final void setHandleState(HandleState handleState) {
        LegacyTextFieldState legacyTextFieldState = this.state;
        if (legacyTextFieldState != null) {
            if (legacyTextFieldState.getHandleState() == handleState) {
                legacyTextFieldState = null;
            }
            if (legacyTextFieldState != null) {
                ((SnapshotMutableStateImpl) legacyTextFieldState.handleState$delegate).setValue(handleState);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01e6  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01f9  */
    /* JADX WARN: Removed duplicated region for block: B:85:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00ab  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void showSelectionToolbar$foundation_release() {
        /*
            Method dump skipped, instructions count: 509
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.TextFieldSelectionManager.showSelectionToolbar$foundation_release():void");
    }

    public final void updateFloatingToolbar(boolean z) {
        LegacyTextFieldState legacyTextFieldState = this.state;
        if (legacyTextFieldState != null) {
            ((SnapshotMutableStateImpl) legacyTextFieldState.showFloatingToolbar$delegate).setValue(Boolean.valueOf(z));
        }
        if (z) {
            showSelectionToolbar$foundation_release();
        } else {
            hideSelectionToolbar$foundation_release();
        }
    }
}
