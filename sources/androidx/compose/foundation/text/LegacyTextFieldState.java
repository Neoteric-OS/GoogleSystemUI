package androidx.compose.foundation.text;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.graphics.AndroidPaint;
import androidx.compose.ui.graphics.AndroidPaint_androidKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.platform.SoftwareKeyboardController;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.AnnotatedStringKt;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.input.EditProcessor;
import androidx.compose.ui.text.input.EditingBuffer;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.TextInputSession;
import androidx.compose.ui.unit.Dp;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LegacyTextFieldState {
    public LayoutCoordinates _layoutCoordinates;
    public final MutableState deletionPreviewHighlightRange$delegate;
    public final MutableState handleState$delegate;
    public final MutableState hasFocus$delegate;
    public final AndroidPaint highlightPaint;
    public TextInputSession inputSession;
    public final MutableState isInTouchMode$delegate;
    public boolean isLayoutResultStale;
    public final KeyboardActionRunner keyboardActionRunner;
    public final SoftwareKeyboardController keyboardController;
    public final MutableState layoutResultState;
    public final MutableState minHeightForSingleLineField$delegate;
    public final Function1 onImeActionPerformed;
    public final Function1 onValueChange;
    public Function1 onValueChangeOriginal;
    public final EditProcessor processor;
    public final RecomposeScopeImpl recomposeScope;
    public long selectionBackgroundColor;
    public final MutableState selectionPreviewHighlightRange$delegate;
    public final MutableState showCursorHandle$delegate;
    public final MutableState showFloatingToolbar$delegate;
    public final MutableState showSelectionHandleEnd$delegate;
    public final MutableState showSelectionHandleStart$delegate;
    public TextDelegate textDelegate;
    public AnnotatedString untransformedText;

    public LegacyTextFieldState(TextDelegate textDelegate, RecomposeScopeImpl recomposeScopeImpl, SoftwareKeyboardController softwareKeyboardController) {
        this.textDelegate = textDelegate;
        this.recomposeScope = recomposeScopeImpl;
        this.keyboardController = softwareKeyboardController;
        EditProcessor editProcessor = new EditProcessor();
        AnnotatedString annotatedString = AnnotatedStringKt.EmptyAnnotatedString;
        long j = TextRange.Zero;
        TextFieldValue textFieldValue = new TextFieldValue(annotatedString, j, (TextRange) null);
        editProcessor.mBufferState = textFieldValue;
        editProcessor.mBuffer = new EditingBuffer(annotatedString, textFieldValue.selection);
        this.processor = editProcessor;
        Boolean bool = Boolean.FALSE;
        this.hasFocus$delegate = SnapshotStateKt.mutableStateOf$default(bool);
        this.minHeightForSingleLineField$delegate = SnapshotStateKt.mutableStateOf$default(new Dp(0));
        this.layoutResultState = SnapshotStateKt.mutableStateOf$default(null);
        this.handleState$delegate = SnapshotStateKt.mutableStateOf$default(HandleState.None);
        this.showFloatingToolbar$delegate = SnapshotStateKt.mutableStateOf$default(bool);
        this.showSelectionHandleStart$delegate = SnapshotStateKt.mutableStateOf$default(bool);
        this.showSelectionHandleEnd$delegate = SnapshotStateKt.mutableStateOf$default(bool);
        this.showCursorHandle$delegate = SnapshotStateKt.mutableStateOf$default(bool);
        this.isLayoutResultStale = true;
        this.isInTouchMode$delegate = SnapshotStateKt.mutableStateOf$default(Boolean.TRUE);
        this.keyboardActionRunner = new KeyboardActionRunner(softwareKeyboardController);
        this.onValueChangeOriginal = new Function1() { // from class: androidx.compose.foundation.text.LegacyTextFieldState$onValueChangeOriginal$1
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return Unit.INSTANCE;
            }
        };
        this.onValueChange = new LegacyTextFieldState$onValueChange$1(this);
        this.onImeActionPerformed = new LegacyTextFieldState$onImeActionPerformed$1(this);
        this.highlightPaint = AndroidPaint_androidKt.Paint();
        this.selectionBackgroundColor = Color.Unspecified;
        this.selectionPreviewHighlightRange$delegate = SnapshotStateKt.mutableStateOf$default(new TextRange(j));
        this.deletionPreviewHighlightRange$delegate = SnapshotStateKt.mutableStateOf$default(new TextRange(j));
    }

    public final HandleState getHandleState() {
        return (HandleState) ((SnapshotMutableStateImpl) this.handleState$delegate).getValue();
    }

    public final boolean getHasFocus() {
        return ((Boolean) ((SnapshotMutableStateImpl) this.hasFocus$delegate).getValue()).booleanValue();
    }

    public final LayoutCoordinates getLayoutCoordinates() {
        LayoutCoordinates layoutCoordinates = this._layoutCoordinates;
        if (layoutCoordinates == null || !layoutCoordinates.isAttached()) {
            return null;
        }
        return layoutCoordinates;
    }

    public final TextLayoutResultProxy getLayoutResult() {
        return (TextLayoutResultProxy) ((SnapshotMutableStateImpl) this.layoutResultState).getValue();
    }

    /* renamed from: setDeletionPreviewHighlightRange-5zc-tL8, reason: not valid java name */
    public final void m157setDeletionPreviewHighlightRange5zctL8(long j) {
        ((SnapshotMutableStateImpl) this.deletionPreviewHighlightRange$delegate).setValue(new TextRange(j));
    }

    /* renamed from: setSelectionPreviewHighlightRange-5zc-tL8, reason: not valid java name */
    public final void m158setSelectionPreviewHighlightRange5zctL8(long j) {
        ((SnapshotMutableStateImpl) this.selectionPreviewHighlightRange$delegate).setValue(new TextRange(j));
    }
}
