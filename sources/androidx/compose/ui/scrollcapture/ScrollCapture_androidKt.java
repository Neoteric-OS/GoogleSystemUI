package androidx.compose.ui.scrollcapture;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.platform.SemanticsUtils_androidKt;
import androidx.compose.ui.semantics.ScrollAxisRange;
import androidx.compose.ui.semantics.SemanticsActions;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsConfigurationKt;
import androidx.compose.ui.semantics.SemanticsNode;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.compose.ui.semantics.SemanticsPropertyKey;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.IntRectKt;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ScrollCapture_androidKt {
    /* JADX WARN: Type inference failed for: r3v3, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
    public static final void visitScrollCaptureCandidates(SemanticsNode semanticsNode, int i, Function1 function1) {
        SemanticsNode semanticsNode2;
        MutableVector mutableVector = new MutableVector(new SemanticsNode[16]);
        List children$ui_release = semanticsNode.getChildren$ui_release(false, false);
        while (true) {
            mutableVector.addAll(mutableVector.size, children$ui_release);
            while (true) {
                int i2 = mutableVector.size;
                if (i2 == 0) {
                    return;
                }
                semanticsNode2 = (SemanticsNode) mutableVector.removeAt(i2 - 1);
                if (SemanticsUtils_androidKt.isVisible(semanticsNode2)) {
                    SemanticsPropertyKey semanticsPropertyKey = SemanticsProperties.Disabled;
                    SemanticsConfiguration semanticsConfiguration = semanticsNode2.unmergedConfig;
                    if (semanticsConfiguration.props.containsKey(semanticsPropertyKey)) {
                        continue;
                    } else {
                        NodeCoordinator findCoordinatorToGetBounds$ui_release = semanticsNode2.findCoordinatorToGetBounds$ui_release();
                        if (findCoordinatorToGetBounds$ui_release == null) {
                            InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("Expected semantics node to have a coordinator.");
                            throw null;
                        }
                        IntRect roundToIntRect = IntRectKt.roundToIntRect(LayoutCoordinatesKt.boundsInWindow(findCoordinatorToGetBounds$ui_release));
                        if (roundToIntRect.left < roundToIntRect.right && roundToIntRect.top < roundToIntRect.bottom) {
                            Function2 function2 = (Function2) SemanticsConfigurationKt.getOrNull(semanticsConfiguration, SemanticsActions.ScrollByOffset);
                            ScrollAxisRange scrollAxisRange = (ScrollAxisRange) SemanticsConfigurationKt.getOrNull(semanticsConfiguration, SemanticsProperties.VerticalScrollAxisRange);
                            if (function2 != null && scrollAxisRange != null && ((Number) scrollAxisRange.maxValue.invoke()).floatValue() > 0.0f) {
                                int i3 = i + 1;
                                ((ScrollCapture$onScrollCaptureSearch$1) function1).invoke(new ScrollCaptureCandidate(semanticsNode2, i3, roundToIntRect, findCoordinatorToGetBounds$ui_release));
                                visitScrollCaptureCandidates(semanticsNode2, i3, function1);
                            }
                        }
                    }
                }
            }
            children$ui_release = semanticsNode2.getChildren$ui_release(false, false);
        }
    }
}
