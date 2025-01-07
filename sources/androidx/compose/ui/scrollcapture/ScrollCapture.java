package androidx.compose.ui.scrollcapture;

import android.graphics.Point;
import android.view.ScrollCaptureTarget;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.RectHelper_androidKt;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.semantics.SemanticsNode;
import androidx.compose.ui.semantics.SemanticsOwner;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.IntRectKt;
import java.util.function.Consumer;
import kotlin.comparisons.ComparisonsKt___ComparisonsJvmKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ScrollCapture {
    public final MutableState scrollCaptureInProgress$delegate = SnapshotStateKt.mutableStateOf$default(Boolean.FALSE);

    public final void onScrollCaptureSearch(AndroidComposeView androidComposeView, SemanticsOwner semanticsOwner, CoroutineContext coroutineContext, Consumer consumer) {
        Object obj;
        MutableVector mutableVector = new MutableVector(new ScrollCaptureCandidate[16]);
        ScrollCapture_androidKt.visitScrollCaptureCandidates(semanticsOwner.getUnmergedRootSemanticsNode(), 0, new ScrollCapture$onScrollCaptureSearch$1(1, mutableVector, MutableVector.class, "add", "add(Ljava/lang/Object;)Z", 8));
        mutableVector.sortWith(ComparisonsKt___ComparisonsJvmKt.compareBy(new Function1() { // from class: androidx.compose.ui.scrollcapture.ScrollCapture$onScrollCaptureSearch$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return Integer.valueOf(((ScrollCaptureCandidate) obj2).depth);
            }
        }, new Function1() { // from class: androidx.compose.ui.scrollcapture.ScrollCapture$onScrollCaptureSearch$3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return Integer.valueOf(((ScrollCaptureCandidate) obj2).viewportBoundsInWindow.getHeight());
            }
        }));
        int i = mutableVector.size;
        if (i == 0) {
            obj = null;
        } else {
            obj = mutableVector.content[i - 1];
        }
        ScrollCaptureCandidate scrollCaptureCandidate = (ScrollCaptureCandidate) obj;
        if (scrollCaptureCandidate == null) {
            return;
        }
        ContextScope CoroutineScope = CoroutineScopeKt.CoroutineScope(coroutineContext);
        SemanticsNode semanticsNode = scrollCaptureCandidate.node;
        IntRect intRect = scrollCaptureCandidate.viewportBoundsInWindow;
        ComposeScrollCaptureCallback composeScrollCaptureCallback = new ComposeScrollCaptureCallback(semanticsNode, intRect, CoroutineScope, this);
        NodeCoordinator nodeCoordinator = scrollCaptureCandidate.coordinates;
        Rect localBoundingBoxOf = LayoutCoordinatesKt.findRootCoordinates(nodeCoordinator).localBoundingBoxOf(nodeCoordinator, true);
        long m680getTopLeftnOccac = intRect.m680getTopLeftnOccac();
        ScrollCaptureTarget scrollCaptureTarget = new ScrollCaptureTarget(androidComposeView, RectHelper_androidKt.toAndroidRect(IntRectKt.roundToIntRect(localBoundingBoxOf)), new Point((int) (m680getTopLeftnOccac >> 32), (int) (m680getTopLeftnOccac & 4294967295L)), composeScrollCaptureCallback);
        scrollCaptureTarget.setScrollBounds(RectHelper_androidKt.toAndroidRect(intRect));
        consumer.accept(scrollCaptureTarget);
    }
}
