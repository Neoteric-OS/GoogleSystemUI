package androidx.compose.foundation.text.input.internal;

import android.R;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Handler;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.CorrectionInfo;
import android.view.inputmethod.DeleteGesture;
import android.view.inputmethod.DeleteRangeGesture;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.HandwritingGesture;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputContentInfo;
import android.view.inputmethod.InsertGesture;
import android.view.inputmethod.JoinOrSplitGesture;
import android.view.inputmethod.PreviewableHandwritingGesture;
import android.view.inputmethod.RemoveSpaceGesture;
import android.view.inputmethod.SelectGesture;
import android.view.inputmethod.SelectRangeGesture;
import androidx.compose.foundation.text.HandleState;
import androidx.compose.foundation.text.LegacyTextFieldState;
import androidx.compose.foundation.text.TextLayoutResultProxy;
import androidx.compose.foundation.text.selection.TextFieldSelectionManager;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.RectHelper_androidKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.MultiParagraph;
import androidx.compose.ui.text.TextInclusionStrategy;
import androidx.compose.ui.text.TextLayoutInput;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.input.CommitTextCommand;
import androidx.compose.ui.text.input.DeleteSurroundingTextCommand;
import androidx.compose.ui.text.input.DeleteSurroundingTextInCodePointsCommand;
import androidx.compose.ui.text.input.EditCommand;
import androidx.compose.ui.text.input.FinishComposingTextCommand;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.SetComposingRegionCommand;
import androidx.compose.ui.text.input.SetComposingTextCommand;
import androidx.compose.ui.text.input.SetSelectionCommand;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.TextFieldValueKt;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.function.IntConsumer;
import java.util.regex.Matcher;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$IntRef;
import kotlin.ranges.RangesKt;
import kotlin.text.MatcherMatchResult;
import kotlin.text.Regex;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class RecordingInputConnection implements InputConnection {
    public final boolean autoCorrect;
    public int batchDepth;
    public int currentExtractedTextRequestToken;
    public final LegacyTextInputMethodRequest$createInputConnection$1 eventCallback;
    public boolean extractedTextMonitorMode;
    public final LegacyTextFieldState legacyTextFieldState;
    public final TextFieldSelectionManager textFieldSelectionManager;
    public TextFieldValue textFieldValue;
    public final ViewConfiguration viewConfiguration;
    public final List editCommands = new ArrayList();
    public boolean isActive = true;

    public RecordingInputConnection(TextFieldValue textFieldValue, LegacyTextInputMethodRequest$createInputConnection$1 legacyTextInputMethodRequest$createInputConnection$1, boolean z, LegacyTextFieldState legacyTextFieldState, TextFieldSelectionManager textFieldSelectionManager, ViewConfiguration viewConfiguration) {
        this.eventCallback = legacyTextInputMethodRequest$createInputConnection$1;
        this.autoCorrect = z;
        this.legacyTextFieldState = legacyTextFieldState;
        this.textFieldSelectionManager = textFieldSelectionManager;
        this.viewConfiguration = viewConfiguration;
        this.textFieldValue = textFieldValue;
    }

    public final void addEditCommandWithBatch(EditCommand editCommand) {
        this.batchDepth++;
        try {
            this.editCommands.add(editCommand);
        } finally {
            endBatchEditInternal();
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean beginBatchEdit() {
        boolean z = this.isActive;
        if (!z) {
            return z;
        }
        this.batchDepth++;
        return true;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean clearMetaKeyStates(int i) {
        boolean z = this.isActive;
        if (z) {
            return false;
        }
        return z;
    }

    @Override // android.view.inputmethod.InputConnection
    public final void closeConnection() {
        this.editCommands.clear();
        this.batchDepth = 0;
        this.isActive = false;
        LegacyTextInputMethodRequest legacyTextInputMethodRequest = this.eventCallback.this$0;
        int size = ((ArrayList) legacyTextInputMethodRequest.ics).size();
        for (int i = 0; i < size; i++) {
            if (Intrinsics.areEqual(((WeakReference) ((ArrayList) legacyTextInputMethodRequest.ics).get(i)).get(), this)) {
                legacyTextInputMethodRequest.ics.remove(i);
                return;
            }
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean commitCompletion(CompletionInfo completionInfo) {
        boolean z = this.isActive;
        if (z) {
            return false;
        }
        return z;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean commitContent(InputContentInfo inputContentInfo, int i, Bundle bundle) {
        boolean z = this.isActive;
        if (z) {
            return false;
        }
        return z;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean commitCorrection(CorrectionInfo correctionInfo) {
        boolean z = this.isActive;
        return z ? this.autoCorrect : z;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean commitText(CharSequence charSequence, int i) {
        boolean z = this.isActive;
        if (z) {
            addEditCommandWithBatch(new CommitTextCommand(String.valueOf(charSequence), i));
        }
        return z;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean deleteSurroundingText(int i, int i2) {
        boolean z = this.isActive;
        if (!z) {
            return z;
        }
        addEditCommandWithBatch(new DeleteSurroundingTextCommand(i, i2));
        return true;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean deleteSurroundingTextInCodePoints(int i, int i2) {
        boolean z = this.isActive;
        if (!z) {
            return z;
        }
        addEditCommandWithBatch(new DeleteSurroundingTextInCodePointsCommand(i, i2));
        return true;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean endBatchEdit() {
        return endBatchEditInternal();
    }

    public final boolean endBatchEditInternal() {
        int i = this.batchDepth - 1;
        this.batchDepth = i;
        if (i == 0 && !this.editCommands.isEmpty()) {
            this.eventCallback.this$0.onEditCommand.invoke(new ArrayList(this.editCommands));
            this.editCommands.clear();
        }
        return this.batchDepth > 0;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean finishComposingText() {
        boolean z = this.isActive;
        if (!z) {
            return z;
        }
        addEditCommandWithBatch(new FinishComposingTextCommand());
        return true;
    }

    @Override // android.view.inputmethod.InputConnection
    public final int getCursorCapsMode(int i) {
        TextFieldValue textFieldValue = this.textFieldValue;
        return TextUtils.getCapsMode(textFieldValue.annotatedString.text, TextRange.m601getMinimpl(textFieldValue.selection), i);
    }

    @Override // android.view.inputmethod.InputConnection
    public final ExtractedText getExtractedText(ExtractedTextRequest extractedTextRequest, int i) {
        boolean z = (i & 1) != 0;
        this.extractedTextMonitorMode = z;
        if (z) {
            this.currentExtractedTextRequestToken = extractedTextRequest != null ? extractedTextRequest.token : 0;
        }
        return RecordingInputConnection_androidKt.access$toExtractedText(this.textFieldValue);
    }

    @Override // android.view.inputmethod.InputConnection
    public final Handler getHandler() {
        return null;
    }

    @Override // android.view.inputmethod.InputConnection
    public final CharSequence getSelectedText(int i) {
        if (TextRange.m598getCollapsedimpl(this.textFieldValue.selection)) {
            return null;
        }
        return TextFieldValueKt.getSelectedText(this.textFieldValue).text;
    }

    @Override // android.view.inputmethod.InputConnection
    public final CharSequence getTextAfterCursor(int i, int i2) {
        return TextFieldValueKt.getTextAfterSelection(this.textFieldValue, i).text;
    }

    @Override // android.view.inputmethod.InputConnection
    public final CharSequence getTextBeforeCursor(int i, int i2) {
        return TextFieldValueKt.getTextBeforeSelection(this.textFieldValue, i).text;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // android.view.inputmethod.InputConnection
    public final boolean performContextMenuAction(int i) {
        boolean z = this.isActive;
        if (z) {
            z = false;
            switch (i) {
                case R.id.selectAll:
                    addEditCommandWithBatch(new SetSelectionCommand(0, this.textFieldValue.annotatedString.text.length()));
                    break;
                case R.id.cut:
                    sendSynthesizedKeyEvent(277);
                    break;
                case R.id.copy:
                    sendSynthesizedKeyEvent(278);
                    break;
                case R.id.paste:
                    sendSynthesizedKeyEvent(279);
                    break;
            }
        }
        return z;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean performEditorAction(int i) {
        int i2;
        boolean z = this.isActive;
        if (z) {
            z = true;
            if (i != 0) {
                switch (i) {
                    case 2:
                        i2 = 2;
                        break;
                    case 3:
                        i2 = 3;
                        break;
                    case 4:
                        i2 = 4;
                        break;
                    case 5:
                        i2 = 6;
                        break;
                    case 6:
                        i2 = 7;
                        break;
                    case 7:
                        i2 = 5;
                        break;
                    default:
                        RecordingInputConnection$$ExternalSyntheticOutline0.m("IME sends unsupported Editor Action: ", "RecordingIC", i);
                        break;
                }
                this.eventCallback.this$0.onImeActionPerformed.invoke(new ImeAction(i2));
            }
            i2 = 1;
            this.eventCallback.this$0.onImeActionPerformed.invoke(new ImeAction(i2));
        }
        return z;
    }

    @Override // android.view.inputmethod.InputConnection
    public final void performHandwritingGesture(HandwritingGesture handwritingGesture, Executor executor, final IntConsumer intConsumer) {
        AnnotatedString annotatedString;
        long j;
        int i;
        TextLayoutResultProxy layoutResult;
        TextLayoutResult textLayoutResult;
        TextLayoutResultProxy layoutResult2;
        TextLayoutResult textLayoutResult2;
        TextLayoutResult textLayoutResult3;
        TextLayoutInput textLayoutInput;
        LegacyTextFieldState legacyTextFieldState = this.legacyTextFieldState;
        TextFieldSelectionManager textFieldSelectionManager = this.textFieldSelectionManager;
        ViewConfiguration viewConfiguration = this.viewConfiguration;
        RecordingInputConnection$performHandwritingGesture$1 recordingInputConnection$performHandwritingGesture$1 = new RecordingInputConnection$performHandwritingGesture$1(this);
        final int i2 = 3;
        if (legacyTextFieldState != null && (annotatedString = legacyTextFieldState.untransformedText) != null) {
            TextLayoutResultProxy layoutResult3 = legacyTextFieldState.getLayoutResult();
            if (annotatedString.equals((layoutResult3 == null || (textLayoutResult3 = layoutResult3.value) == null || (textLayoutInput = textLayoutResult3.layoutInput) == null) ? null : textLayoutInput.text)) {
                int i3 = 1;
                if (handwritingGesture instanceof SelectGesture) {
                    SelectGesture selectGesture = (SelectGesture) handwritingGesture;
                    long m175getRangeForScreenRectOH9lIzo = HandwritingGesture_androidKt.m175getRangeForScreenRectOH9lIzo(legacyTextFieldState, RectHelper_androidKt.toComposeRect(selectGesture.getSelectionArea()), HandwritingGestureApi34.m171toTextGranularityNUwxegE(selectGesture.getGranularity()));
                    if (TextRange.m598getCollapsedimpl(m175getRangeForScreenRectOH9lIzo)) {
                        i3 = HandwritingGestureApi34.fallbackOnLegacyTextField(selectGesture, recordingInputConnection$performHandwritingGesture$1);
                    } else {
                        recordingInputConnection$performHandwritingGesture$1.invoke(new SetSelectionCommand((int) (m175getRangeForScreenRectOH9lIzo >> 32), (int) (m175getRangeForScreenRectOH9lIzo & 4294967295L)));
                        if (textFieldSelectionManager != null) {
                            textFieldSelectionManager.enterSelectionMode$foundation_release(true);
                        }
                    }
                } else {
                    if (handwritingGesture instanceof DeleteGesture) {
                        DeleteGesture deleteGesture = (DeleteGesture) handwritingGesture;
                        int m171toTextGranularityNUwxegE = HandwritingGestureApi34.m171toTextGranularityNUwxegE(deleteGesture.getGranularity());
                        long m175getRangeForScreenRectOH9lIzo2 = HandwritingGesture_androidKt.m175getRangeForScreenRectOH9lIzo(legacyTextFieldState, RectHelper_androidKt.toComposeRect(deleteGesture.getDeletionArea()), m171toTextGranularityNUwxegE);
                        if (TextRange.m598getCollapsedimpl(m175getRangeForScreenRectOH9lIzo2)) {
                            i3 = HandwritingGestureApi34.fallbackOnLegacyTextField(deleteGesture, recordingInputConnection$performHandwritingGesture$1);
                        } else {
                            HandwritingGestureApi34.m170performDeletionOnLegacyTextFieldvJH6DeI(m175getRangeForScreenRectOH9lIzo2, annotatedString, m171toTextGranularityNUwxegE == 1, recordingInputConnection$performHandwritingGesture$1);
                        }
                    } else if (handwritingGesture instanceof SelectRangeGesture) {
                        SelectRangeGesture selectRangeGesture = (SelectRangeGesture) handwritingGesture;
                        long m173access$getRangeForScreenRectsO048IG0 = HandwritingGesture_androidKt.m173access$getRangeForScreenRectsO048IG0(legacyTextFieldState, RectHelper_androidKt.toComposeRect(selectRangeGesture.getSelectionStartArea()), RectHelper_androidKt.toComposeRect(selectRangeGesture.getSelectionEndArea()), HandwritingGestureApi34.m171toTextGranularityNUwxegE(selectRangeGesture.getGranularity()));
                        if (TextRange.m598getCollapsedimpl(m173access$getRangeForScreenRectsO048IG0)) {
                            i3 = HandwritingGestureApi34.fallbackOnLegacyTextField(selectRangeGesture, recordingInputConnection$performHandwritingGesture$1);
                        } else {
                            recordingInputConnection$performHandwritingGesture$1.invoke(new SetSelectionCommand((int) (m173access$getRangeForScreenRectsO048IG0 >> 32), (int) (m173access$getRangeForScreenRectsO048IG0 & 4294967295L)));
                            if (textFieldSelectionManager != null) {
                                textFieldSelectionManager.enterSelectionMode$foundation_release(true);
                            }
                        }
                    } else if (handwritingGesture instanceof DeleteRangeGesture) {
                        DeleteRangeGesture deleteRangeGesture = (DeleteRangeGesture) handwritingGesture;
                        int m171toTextGranularityNUwxegE2 = HandwritingGestureApi34.m171toTextGranularityNUwxegE(deleteRangeGesture.getGranularity());
                        long m173access$getRangeForScreenRectsO048IG02 = HandwritingGesture_androidKt.m173access$getRangeForScreenRectsO048IG0(legacyTextFieldState, RectHelper_androidKt.toComposeRect(deleteRangeGesture.getDeletionStartArea()), RectHelper_androidKt.toComposeRect(deleteRangeGesture.getDeletionEndArea()), m171toTextGranularityNUwxegE2);
                        if (TextRange.m598getCollapsedimpl(m173access$getRangeForScreenRectsO048IG02)) {
                            i3 = HandwritingGestureApi34.fallbackOnLegacyTextField(deleteRangeGesture, recordingInputConnection$performHandwritingGesture$1);
                        } else {
                            HandwritingGestureApi34.m170performDeletionOnLegacyTextFieldvJH6DeI(m173access$getRangeForScreenRectsO048IG02, annotatedString, m171toTextGranularityNUwxegE2 == 1, recordingInputConnection$performHandwritingGesture$1);
                        }
                    } else if (handwritingGesture instanceof JoinOrSplitGesture) {
                        JoinOrSplitGesture joinOrSplitGesture = (JoinOrSplitGesture) handwritingGesture;
                        if (viewConfiguration == null) {
                            i3 = HandwritingGestureApi34.fallbackOnLegacyTextField(joinOrSplitGesture, recordingInputConnection$performHandwritingGesture$1);
                        } else {
                            int m172access$getOffsetForHandwritingGestured4ec7I = HandwritingGesture_androidKt.m172access$getOffsetForHandwritingGestured4ec7I(legacyTextFieldState, HandwritingGesture_androidKt.access$toOffset(joinOrSplitGesture.getJoinOrSplitPoint()), viewConfiguration);
                            if (m172access$getOffsetForHandwritingGestured4ec7I == -1 || !((layoutResult2 = legacyTextFieldState.getLayoutResult()) == null || (textLayoutResult2 = layoutResult2.value) == null || !HandwritingGesture_androidKt.access$isBiDiBoundary(textLayoutResult2, m172access$getOffsetForHandwritingGestured4ec7I))) {
                                i3 = HandwritingGestureApi34.fallbackOnLegacyTextField(joinOrSplitGesture, recordingInputConnection$performHandwritingGesture$1);
                            } else {
                                int i4 = m172access$getOffsetForHandwritingGestured4ec7I;
                                while (i4 > 0) {
                                    int codePointBefore = Character.codePointBefore(annotatedString, i4);
                                    if (!HandwritingGesture_androidKt.isWhitespace(codePointBefore)) {
                                        break;
                                    } else {
                                        i4 -= Character.charCount(codePointBefore);
                                    }
                                }
                                while (m172access$getOffsetForHandwritingGestured4ec7I < annotatedString.text.length()) {
                                    int codePointAt = Character.codePointAt(annotatedString, m172access$getOffsetForHandwritingGestured4ec7I);
                                    if (!HandwritingGesture_androidKt.isWhitespace(codePointAt)) {
                                        break;
                                    } else {
                                        m172access$getOffsetForHandwritingGestured4ec7I += Character.charCount(codePointAt);
                                    }
                                }
                                long TextRange = TextRangeKt.TextRange(i4, m172access$getOffsetForHandwritingGestured4ec7I);
                                if (TextRange.m598getCollapsedimpl(TextRange)) {
                                    int i5 = (int) (TextRange >> 32);
                                    recordingInputConnection$performHandwritingGesture$1.invoke(new HandwritingGesture_androidKt$compoundEditCommand$1(new EditCommand[]{new SetSelectionCommand(i5, i5), new CommitTextCommand(" ", 1)}));
                                } else {
                                    HandwritingGestureApi34.m170performDeletionOnLegacyTextFieldvJH6DeI(TextRange, annotatedString, false, recordingInputConnection$performHandwritingGesture$1);
                                }
                            }
                        }
                    } else if (handwritingGesture instanceof InsertGesture) {
                        InsertGesture insertGesture = (InsertGesture) handwritingGesture;
                        if (viewConfiguration == null) {
                            i3 = HandwritingGestureApi34.fallbackOnLegacyTextField(insertGesture, recordingInputConnection$performHandwritingGesture$1);
                        } else {
                            int m172access$getOffsetForHandwritingGestured4ec7I2 = HandwritingGesture_androidKt.m172access$getOffsetForHandwritingGestured4ec7I(legacyTextFieldState, HandwritingGesture_androidKt.access$toOffset(insertGesture.getInsertionPoint()), viewConfiguration);
                            if (m172access$getOffsetForHandwritingGestured4ec7I2 == -1 || !((layoutResult = legacyTextFieldState.getLayoutResult()) == null || (textLayoutResult = layoutResult.value) == null || !HandwritingGesture_androidKt.access$isBiDiBoundary(textLayoutResult, m172access$getOffsetForHandwritingGestured4ec7I2))) {
                                i3 = HandwritingGestureApi34.fallbackOnLegacyTextField(insertGesture, recordingInputConnection$performHandwritingGesture$1);
                            } else {
                                recordingInputConnection$performHandwritingGesture$1.invoke(new HandwritingGesture_androidKt$compoundEditCommand$1(new EditCommand[]{new SetSelectionCommand(m172access$getOffsetForHandwritingGestured4ec7I2, m172access$getOffsetForHandwritingGestured4ec7I2), new CommitTextCommand(insertGesture.getTextToInsert(), 1)}));
                            }
                        }
                    } else if (handwritingGesture instanceof RemoveSpaceGesture) {
                        RemoveSpaceGesture removeSpaceGesture = (RemoveSpaceGesture) handwritingGesture;
                        TextLayoutResultProxy layoutResult4 = legacyTextFieldState.getLayoutResult();
                        TextLayoutResult textLayoutResult4 = layoutResult4 != null ? layoutResult4.value : null;
                        long access$toOffset = HandwritingGesture_androidKt.access$toOffset(removeSpaceGesture.getStartPoint());
                        long access$toOffset2 = HandwritingGesture_androidKt.access$toOffset(removeSpaceGesture.getEndPoint());
                        LayoutCoordinates layoutCoordinates = legacyTextFieldState.getLayoutCoordinates();
                        if (textLayoutResult4 == null || layoutCoordinates == null) {
                            j = TextRange.Zero;
                        } else {
                            long mo487screenToLocalMKHz9U = layoutCoordinates.mo487screenToLocalMKHz9U(access$toOffset);
                            long mo487screenToLocalMKHz9U2 = layoutCoordinates.mo487screenToLocalMKHz9U(access$toOffset2);
                            MultiParagraph multiParagraph = textLayoutResult4.multiParagraph;
                            int m174getLineForHandwritingGestured4ec7I = HandwritingGesture_androidKt.m174getLineForHandwritingGestured4ec7I(multiParagraph, mo487screenToLocalMKHz9U, viewConfiguration);
                            int m174getLineForHandwritingGestured4ec7I2 = HandwritingGesture_androidKt.m174getLineForHandwritingGestured4ec7I(multiParagraph, mo487screenToLocalMKHz9U2, viewConfiguration);
                            if (m174getLineForHandwritingGestured4ec7I != -1) {
                                if (m174getLineForHandwritingGestured4ec7I2 != -1) {
                                    m174getLineForHandwritingGestured4ec7I = Math.min(m174getLineForHandwritingGestured4ec7I, m174getLineForHandwritingGestured4ec7I2);
                                }
                                m174getLineForHandwritingGestured4ec7I2 = m174getLineForHandwritingGestured4ec7I;
                            } else if (m174getLineForHandwritingGestured4ec7I2 == -1) {
                                j = TextRange.Zero;
                            }
                            float lineBottom = (multiParagraph.getLineBottom(m174getLineForHandwritingGestured4ec7I2) + multiParagraph.getLineTop(m174getLineForHandwritingGestured4ec7I2)) / 2;
                            int i6 = (int) (mo487screenToLocalMKHz9U >> 32);
                            int i7 = (int) (mo487screenToLocalMKHz9U2 >> 32);
                            j = multiParagraph.m589getRangeForRect86BmAI(new Rect(Math.min(Float.intBitsToFloat(i6), Float.intBitsToFloat(i7)), lineBottom - 0.1f, Math.max(Float.intBitsToFloat(i6), Float.intBitsToFloat(i7)), lineBottom + 0.1f), 0, TextInclusionStrategy.Companion.AnyOverlap);
                        }
                        if (TextRange.m598getCollapsedimpl(j)) {
                            i3 = HandwritingGestureApi34.fallbackOnLegacyTextField(removeSpaceGesture, recordingInputConnection$performHandwritingGesture$1);
                        } else {
                            final Ref$IntRef ref$IntRef = new Ref$IntRef();
                            ref$IntRef.element = -1;
                            final Ref$IntRef ref$IntRef2 = new Ref$IntRef();
                            ref$IntRef2.element = -1;
                            String replace = new Regex("\\s+").replace(annotatedString.subSequence(TextRange.m601getMinimpl(j), TextRange.m600getMaximpl(j)).text, new Function1() { // from class: androidx.compose.foundation.text.input.internal.HandwritingGestureApi34$performRemoveSpaceGesture$newText$2
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    MatcherMatchResult matcherMatchResult = (MatcherMatchResult) obj;
                                    Ref$IntRef ref$IntRef3 = Ref$IntRef.this;
                                    if (ref$IntRef3.element == -1) {
                                        Matcher matcher = matcherMatchResult.matcher;
                                        ref$IntRef3.element = RangesKt.until(matcher.start(), matcher.end()).first;
                                    }
                                    Ref$IntRef ref$IntRef4 = ref$IntRef2;
                                    Matcher matcher2 = matcherMatchResult.matcher;
                                    ref$IntRef4.element = RangesKt.until(matcher2.start(), matcher2.end()).last + 1;
                                    return "";
                                }
                            });
                            int i8 = ref$IntRef.element;
                            if (i8 == -1 || (i = ref$IntRef2.element) == -1) {
                                i3 = HandwritingGestureApi34.fallbackOnLegacyTextField(removeSpaceGesture, recordingInputConnection$performHandwritingGesture$1);
                            } else {
                                int i9 = (int) (j >> 32);
                                recordingInputConnection$performHandwritingGesture$1.invoke(new HandwritingGesture_androidKt$compoundEditCommand$1(new EditCommand[]{new SetSelectionCommand(i9 + i8, i9 + i), new CommitTextCommand(replace.substring(i8, replace.length() - (TextRange.m599getLengthimpl(j) - ref$IntRef2.element)), 1)}));
                                i3 = 1;
                            }
                        }
                    } else {
                        i2 = 2;
                    }
                }
                i2 = i3;
            }
        }
        if (intConsumer == null) {
            return;
        }
        if (executor != null) {
            executor.execute(new Runnable() { // from class: androidx.compose.foundation.text.input.internal.Api34LegacyPerformHandwritingGestureImpl$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    intConsumer.accept(i2);
                }
            });
        } else {
            intConsumer.accept(i2);
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean performPrivateCommand(String str, Bundle bundle) {
        boolean z = this.isActive;
        if (z) {
            return true;
        }
        return z;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean previewHandwritingGesture(PreviewableHandwritingGesture previewableHandwritingGesture, CancellationSignal cancellationSignal) {
        AnnotatedString annotatedString;
        TextLayoutResult textLayoutResult;
        TextLayoutInput textLayoutInput;
        LegacyTextFieldState legacyTextFieldState = this.legacyTextFieldState;
        final TextFieldSelectionManager textFieldSelectionManager = this.textFieldSelectionManager;
        if (legacyTextFieldState == null || (annotatedString = legacyTextFieldState.untransformedText) == null) {
            return false;
        }
        TextLayoutResultProxy layoutResult = legacyTextFieldState.getLayoutResult();
        if (!annotatedString.equals((layoutResult == null || (textLayoutResult = layoutResult.value) == null || (textLayoutInput = textLayoutResult.layoutInput) == null) ? null : textLayoutInput.text)) {
            return false;
        }
        boolean z = previewableHandwritingGesture instanceof SelectGesture;
        HandleState handleState = HandleState.None;
        if (z) {
            SelectGesture selectGesture = (SelectGesture) previewableHandwritingGesture;
            if (textFieldSelectionManager != null) {
                long m175getRangeForScreenRectOH9lIzo = HandwritingGesture_androidKt.m175getRangeForScreenRectOH9lIzo(legacyTextFieldState, RectHelper_androidKt.toComposeRect(selectGesture.getSelectionArea()), HandwritingGestureApi34.m171toTextGranularityNUwxegE(selectGesture.getGranularity()));
                LegacyTextFieldState legacyTextFieldState2 = textFieldSelectionManager.state;
                if (legacyTextFieldState2 != null) {
                    legacyTextFieldState2.m158setSelectionPreviewHighlightRange5zctL8(m175getRangeForScreenRectOH9lIzo);
                }
                LegacyTextFieldState legacyTextFieldState3 = textFieldSelectionManager.state;
                if (legacyTextFieldState3 != null) {
                    legacyTextFieldState3.m157setDeletionPreviewHighlightRange5zctL8(TextRange.Zero);
                }
                if (!TextRange.m598getCollapsedimpl(m175getRangeForScreenRectOH9lIzo)) {
                    textFieldSelectionManager.updateFloatingToolbar(false);
                    textFieldSelectionManager.setHandleState(handleState);
                }
            }
        } else if (previewableHandwritingGesture instanceof DeleteGesture) {
            DeleteGesture deleteGesture = (DeleteGesture) previewableHandwritingGesture;
            if (textFieldSelectionManager != null) {
                long m175getRangeForScreenRectOH9lIzo2 = HandwritingGesture_androidKt.m175getRangeForScreenRectOH9lIzo(legacyTextFieldState, RectHelper_androidKt.toComposeRect(deleteGesture.getDeletionArea()), HandwritingGestureApi34.m171toTextGranularityNUwxegE(deleteGesture.getGranularity()));
                LegacyTextFieldState legacyTextFieldState4 = textFieldSelectionManager.state;
                if (legacyTextFieldState4 != null) {
                    legacyTextFieldState4.m157setDeletionPreviewHighlightRange5zctL8(m175getRangeForScreenRectOH9lIzo2);
                }
                LegacyTextFieldState legacyTextFieldState5 = textFieldSelectionManager.state;
                if (legacyTextFieldState5 != null) {
                    legacyTextFieldState5.m158setSelectionPreviewHighlightRange5zctL8(TextRange.Zero);
                }
                if (!TextRange.m598getCollapsedimpl(m175getRangeForScreenRectOH9lIzo2)) {
                    textFieldSelectionManager.updateFloatingToolbar(false);
                    textFieldSelectionManager.setHandleState(handleState);
                }
            }
        } else if (previewableHandwritingGesture instanceof SelectRangeGesture) {
            SelectRangeGesture selectRangeGesture = (SelectRangeGesture) previewableHandwritingGesture;
            if (textFieldSelectionManager != null) {
                long m173access$getRangeForScreenRectsO048IG0 = HandwritingGesture_androidKt.m173access$getRangeForScreenRectsO048IG0(legacyTextFieldState, RectHelper_androidKt.toComposeRect(selectRangeGesture.getSelectionStartArea()), RectHelper_androidKt.toComposeRect(selectRangeGesture.getSelectionEndArea()), HandwritingGestureApi34.m171toTextGranularityNUwxegE(selectRangeGesture.getGranularity()));
                LegacyTextFieldState legacyTextFieldState6 = textFieldSelectionManager.state;
                if (legacyTextFieldState6 != null) {
                    legacyTextFieldState6.m158setSelectionPreviewHighlightRange5zctL8(m173access$getRangeForScreenRectsO048IG0);
                }
                LegacyTextFieldState legacyTextFieldState7 = textFieldSelectionManager.state;
                if (legacyTextFieldState7 != null) {
                    legacyTextFieldState7.m157setDeletionPreviewHighlightRange5zctL8(TextRange.Zero);
                }
                if (!TextRange.m598getCollapsedimpl(m173access$getRangeForScreenRectsO048IG0)) {
                    textFieldSelectionManager.updateFloatingToolbar(false);
                    textFieldSelectionManager.setHandleState(handleState);
                }
            }
        } else {
            if (!(previewableHandwritingGesture instanceof DeleteRangeGesture)) {
                return false;
            }
            DeleteRangeGesture deleteRangeGesture = (DeleteRangeGesture) previewableHandwritingGesture;
            if (textFieldSelectionManager != null) {
                long m173access$getRangeForScreenRectsO048IG02 = HandwritingGesture_androidKt.m173access$getRangeForScreenRectsO048IG0(legacyTextFieldState, RectHelper_androidKt.toComposeRect(deleteRangeGesture.getDeletionStartArea()), RectHelper_androidKt.toComposeRect(deleteRangeGesture.getDeletionEndArea()), HandwritingGestureApi34.m171toTextGranularityNUwxegE(deleteRangeGesture.getGranularity()));
                LegacyTextFieldState legacyTextFieldState8 = textFieldSelectionManager.state;
                if (legacyTextFieldState8 != null) {
                    legacyTextFieldState8.m157setDeletionPreviewHighlightRange5zctL8(m173access$getRangeForScreenRectsO048IG02);
                }
                LegacyTextFieldState legacyTextFieldState9 = textFieldSelectionManager.state;
                if (legacyTextFieldState9 != null) {
                    legacyTextFieldState9.m158setSelectionPreviewHighlightRange5zctL8(TextRange.Zero);
                }
                if (!TextRange.m598getCollapsedimpl(m173access$getRangeForScreenRectsO048IG02)) {
                    textFieldSelectionManager.updateFloatingToolbar(false);
                    textFieldSelectionManager.setHandleState(handleState);
                }
            }
        }
        if (cancellationSignal != null) {
            cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: androidx.compose.foundation.text.input.internal.HandwritingGestureApi34$$ExternalSyntheticLambda0
                @Override // android.os.CancellationSignal.OnCancelListener
                public final void onCancel() {
                    TextFieldSelectionManager textFieldSelectionManager2 = TextFieldSelectionManager.this;
                    if (textFieldSelectionManager2 != null) {
                        LegacyTextFieldState legacyTextFieldState10 = textFieldSelectionManager2.state;
                        if (legacyTextFieldState10 != null) {
                            legacyTextFieldState10.m157setDeletionPreviewHighlightRange5zctL8(TextRange.Zero);
                        }
                        LegacyTextFieldState legacyTextFieldState11 = textFieldSelectionManager2.state;
                        if (legacyTextFieldState11 == null) {
                            return;
                        }
                        legacyTextFieldState11.m158setSelectionPreviewHighlightRange5zctL8(TextRange.Zero);
                    }
                }
            });
        }
        return true;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean reportFullscreenMode(boolean z) {
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean requestCursorUpdates(int i) {
        boolean z = this.isActive;
        if (!z) {
            return z;
        }
        boolean z2 = (i & 1) != 0;
        boolean z3 = (i & 2) != 0;
        boolean z4 = (i & 16) != 0;
        boolean z5 = (i & 8) != 0;
        boolean z6 = (i & 4) != 0;
        boolean z7 = (i & 32) != 0;
        if (!z4 && !z5 && !z6 && !z7) {
            z7 = true;
            z4 = true;
            z5 = true;
            z6 = true;
        }
        LegacyCursorAnchorInfoController legacyCursorAnchorInfoController = this.eventCallback.this$0.cursorAnchorInfoController;
        synchronized (legacyCursorAnchorInfoController.lock) {
            try {
                legacyCursorAnchorInfoController.includeInsertionMarker = z4;
                legacyCursorAnchorInfoController.includeCharacterBounds = z5;
                legacyCursorAnchorInfoController.includeEditorBounds = z6;
                legacyCursorAnchorInfoController.includeLineBounds = z7;
                if (z2) {
                    legacyCursorAnchorInfoController.hasPendingImmediateRequest = true;
                    if (legacyCursorAnchorInfoController.textFieldValue != null) {
                        legacyCursorAnchorInfoController.updateCursorAnchorInfo();
                    }
                }
                legacyCursorAnchorInfoController.monitorEnabled = z3;
            } catch (Throwable th) {
                throw th;
            }
        }
        return true;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean sendKeyEvent(KeyEvent keyEvent) {
        boolean z = this.isActive;
        if (!z) {
            return z;
        }
        ((BaseInputConnection) this.eventCallback.this$0.baseInputConnection$delegate.getValue()).sendKeyEvent(keyEvent);
        return true;
    }

    public final void sendSynthesizedKeyEvent(int i) {
        sendKeyEvent(new KeyEvent(0, i));
        sendKeyEvent(new KeyEvent(1, i));
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean setComposingRegion(int i, int i2) {
        boolean z = this.isActive;
        if (z) {
            addEditCommandWithBatch(new SetComposingRegionCommand(i, i2));
        }
        return z;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean setComposingText(CharSequence charSequence, int i) {
        boolean z = this.isActive;
        if (z) {
            addEditCommandWithBatch(new SetComposingTextCommand(String.valueOf(charSequence), i));
        }
        return z;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean setSelection(int i, int i2) {
        boolean z = this.isActive;
        if (!z) {
            return z;
        }
        addEditCommandWithBatch(new SetSelectionCommand(i, i2));
        return true;
    }
}
