package androidx.compose.foundation.text;

import androidx.compose.ui.text.input.TextFieldValue;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class UndoManager {
    public boolean forceNextSnapshot;
    public Long lastSnapshot;
    public Entry redoStack;
    public int storedCharacters;
    public Entry undoStack;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class Entry {
        public Entry next;
        public TextFieldValue value;

        public Entry(Entry entry, TextFieldValue textFieldValue) {
            this.next = entry;
            this.value = textFieldValue;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0067 A[LOOP:0: B:26:0x005b->B:31:0x0067, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x006a A[EDGE_INSN: B:32:0x006a->B:33:0x006a BREAK  A[LOOP:0: B:26:0x005b->B:31:0x0067], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void makeSnapshot(androidx.compose.ui.text.input.TextFieldValue r4) {
        /*
            r3 = this;
            r0 = 0
            r3.forceNextSnapshot = r0
            androidx.compose.foundation.text.UndoManager$Entry r0 = r3.undoStack
            r1 = 0
            if (r0 == 0) goto Lb
            androidx.compose.ui.text.input.TextFieldValue r0 = r0.value
            goto Lc
        Lb:
            r0 = r1
        Lc:
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r0)
            if (r0 == 0) goto L13
            return
        L13:
            androidx.compose.ui.text.AnnotatedString r0 = r4.annotatedString
            java.lang.String r0 = r0.text
            androidx.compose.foundation.text.UndoManager$Entry r2 = r3.undoStack
            if (r2 == 0) goto L24
            androidx.compose.ui.text.input.TextFieldValue r2 = r2.value
            if (r2 == 0) goto L24
            androidx.compose.ui.text.AnnotatedString r2 = r2.annotatedString
            java.lang.String r2 = r2.text
            goto L25
        L24:
            r2 = r1
        L25:
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r2)
            if (r0 == 0) goto L33
            androidx.compose.foundation.text.UndoManager$Entry r3 = r3.undoStack
            if (r3 != 0) goto L30
            goto L32
        L30:
            r3.value = r4
        L32:
            return
        L33:
            androidx.compose.foundation.text.UndoManager$Entry r0 = r3.undoStack
            androidx.compose.foundation.text.UndoManager$Entry r2 = new androidx.compose.foundation.text.UndoManager$Entry
            r2.<init>(r0, r4)
            r3.undoStack = r2
            r3.redoStack = r1
            int r0 = r3.storedCharacters
            androidx.compose.ui.text.AnnotatedString r4 = r4.annotatedString
            java.lang.String r4 = r4.text
            int r4 = r4.length()
            int r4 = r4 + r0
            r3.storedCharacters = r4
            r0 = 100000(0x186a0, float:1.4013E-40)
            if (r4 <= r0) goto L6f
            androidx.compose.foundation.text.UndoManager$Entry r3 = r3.undoStack
            if (r3 == 0) goto L57
            androidx.compose.foundation.text.UndoManager$Entry r4 = r3.next
            goto L58
        L57:
            r4 = r1
        L58:
            if (r4 != 0) goto L5b
            goto L6f
        L5b:
            if (r3 == 0) goto L64
            androidx.compose.foundation.text.UndoManager$Entry r4 = r3.next
            if (r4 == 0) goto L64
            androidx.compose.foundation.text.UndoManager$Entry r4 = r4.next
            goto L65
        L64:
            r4 = r1
        L65:
            if (r4 == 0) goto L6a
            androidx.compose.foundation.text.UndoManager$Entry r3 = r3.next
            goto L5b
        L6a:
            if (r3 != 0) goto L6d
            goto L6f
        L6d:
            r3.next = r1
        L6f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.UndoManager.makeSnapshot(androidx.compose.ui.text.input.TextFieldValue):void");
    }
}
