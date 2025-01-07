package androidx.compose.ui.viewinterop;

import android.view.View;
import androidx.compose.ui.node.LayoutNode;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class AndroidViewHolder_androidKt {
    public static final AndroidViewHolder_androidKt$NoOpScrollConnection$1 NoOpScrollConnection = new AndroidViewHolder_androidKt$NoOpScrollConnection$1();

    public static final void access$layoutAccordingTo(View view, LayoutNode layoutNode) {
        long mo484localToRootMKHz9U = layoutNode.nodes.innerCoordinator.mo484localToRootMKHz9U(0L);
        int round = Math.round(Float.intBitsToFloat((int) (mo484localToRootMKHz9U >> 32)));
        int round2 = Math.round(Float.intBitsToFloat((int) (mo484localToRootMKHz9U & 4294967295L)));
        view.layout(round, round2, view.getMeasuredWidth() + round, view.getMeasuredHeight() + round2);
    }
}
