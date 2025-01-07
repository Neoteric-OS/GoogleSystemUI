package androidx.compose.ui.text.input;

import androidx.activity.BackEventCompat$$ExternalSyntheticOutline0;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Reflection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class EditProcessor {
    public EditingBuffer mBuffer;
    public TextFieldValue mBufferState;

    public final TextFieldValue apply(List list) {
        final EditCommand editCommand;
        Exception e;
        EditCommand editCommand2;
        try {
            int size = list.size();
            int i = 0;
            editCommand = null;
            while (i < size) {
                try {
                    editCommand2 = (EditCommand) list.get(i);
                } catch (Exception e2) {
                    e = e2;
                }
                try {
                    editCommand2.applyTo(this.mBuffer);
                    i++;
                    editCommand = editCommand2;
                } catch (Exception e3) {
                    e = e3;
                    editCommand = editCommand2;
                    StringBuilder sb = new StringBuilder();
                    StringBuilder sb2 = new StringBuilder("Error while applying EditCommand batch to buffer (length=");
                    sb2.append(this.mBuffer.gapBuffer.getLength());
                    sb2.append(", composition=");
                    sb2.append(this.mBuffer.m615getCompositionMzsxiRA$ui_text_release());
                    sb2.append(", selection=");
                    EditingBuffer editingBuffer = this.mBuffer;
                    sb2.append((Object) TextRange.m603toStringimpl(TextRangeKt.TextRange(editingBuffer.selectionStart, editingBuffer.selectionEnd)));
                    sb2.append("):");
                    sb.append(sb2.toString());
                    sb.append('\n');
                    CollectionsKt.joinTo$default(list, sb, "\n", new Function1() { // from class: androidx.compose.ui.text.input.EditProcessor$generateBatchErrorMessage$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            String concat;
                            EditCommand editCommand3 = (EditCommand) obj;
                            StringBuilder m = EditProcessor$generateBatchErrorMessage$1$1$$ExternalSyntheticOutline0.m(EditCommand.this == editCommand3 ? " > " : "   ");
                            this.getClass();
                            if (editCommand3 instanceof CommitTextCommand) {
                                StringBuilder sb3 = new StringBuilder("CommitTextCommand(text.length=");
                                CommitTextCommand commitTextCommand = (CommitTextCommand) editCommand3;
                                sb3.append(commitTextCommand.annotatedString.text.length());
                                sb3.append(", newCursorPosition=");
                                concat = BackEventCompat$$ExternalSyntheticOutline0.m(sb3, commitTextCommand.newCursorPosition, ')');
                            } else if (editCommand3 instanceof SetComposingTextCommand) {
                                StringBuilder sb4 = new StringBuilder("SetComposingTextCommand(text.length=");
                                SetComposingTextCommand setComposingTextCommand = (SetComposingTextCommand) editCommand3;
                                sb4.append(setComposingTextCommand.annotatedString.text.length());
                                sb4.append(", newCursorPosition=");
                                concat = BackEventCompat$$ExternalSyntheticOutline0.m(sb4, setComposingTextCommand.newCursorPosition, ')');
                            } else if (editCommand3 instanceof SetComposingRegionCommand) {
                                concat = editCommand3.toString();
                            } else if (editCommand3 instanceof DeleteSurroundingTextCommand) {
                                concat = editCommand3.toString();
                            } else if (editCommand3 instanceof DeleteSurroundingTextInCodePointsCommand) {
                                concat = editCommand3.toString();
                            } else if (editCommand3 instanceof SetSelectionCommand) {
                                concat = editCommand3.toString();
                            } else if (editCommand3 instanceof FinishComposingTextCommand) {
                                ((FinishComposingTextCommand) editCommand3).getClass();
                                concat = "FinishComposingTextCommand()";
                            } else if (editCommand3 instanceof DeleteAllCommand) {
                                ((DeleteAllCommand) editCommand3).getClass();
                                concat = "DeleteAllCommand()";
                            } else {
                                String simpleName = Reflection.getOrCreateKotlinClass(editCommand3.getClass()).getSimpleName();
                                if (simpleName == null) {
                                    simpleName = "{anonymous EditCommand}";
                                }
                                concat = "Unknown EditCommand: ".concat(simpleName);
                            }
                            m.append(concat);
                            return m.toString();
                        }
                    }, 60);
                    throw new RuntimeException(sb.toString(), e);
                }
            }
            EditingBuffer editingBuffer2 = this.mBuffer;
            editingBuffer2.getClass();
            AnnotatedString annotatedString = new AnnotatedString(editingBuffer2.gapBuffer.toString());
            EditingBuffer editingBuffer3 = this.mBuffer;
            long TextRange = TextRangeKt.TextRange(editingBuffer3.selectionStart, editingBuffer3.selectionEnd);
            TextRange textRange = TextRange.m602getReversedimpl(this.mBufferState.selection) ? null : new TextRange(TextRange);
            TextFieldValue textFieldValue = new TextFieldValue(annotatedString, textRange != null ? textRange.packedValue : TextRangeKt.TextRange(TextRange.m600getMaximpl(TextRange), TextRange.m601getMinimpl(TextRange)), this.mBuffer.m615getCompositionMzsxiRA$ui_text_release());
            this.mBufferState = textFieldValue;
            return textFieldValue;
        } catch (Exception e4) {
            editCommand = null;
            e = e4;
        }
    }
}
