package androidx.compose.ui.input.pointer;

import android.util.SparseBooleanArray;
import android.util.SparseLongArray;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MotionEventAdapter {
    public long nextId;
    public final SparseLongArray motionEventToComposePointerIdMap = new SparseLongArray();
    public final SparseBooleanArray activeHoverIds = new SparseBooleanArray();
    public final List pointers = new ArrayList();
    public int previousToolType = -1;
    public int previousSource = -1;

    /* JADX WARN: Removed duplicated region for block: B:64:0x019b  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01f1  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0212  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final androidx.compose.ui.input.pointer.PointerInputEvent convertToPointerInputEvent$ui_release(android.view.MotionEvent r47, androidx.compose.ui.platform.AndroidComposeView r48) {
        /*
            Method dump skipped, instructions count: 699
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.input.pointer.MotionEventAdapter.convertToPointerInputEvent$ui_release(android.view.MotionEvent, androidx.compose.ui.platform.AndroidComposeView):androidx.compose.ui.input.pointer.PointerInputEvent");
    }
}
