package androidx.compose.ui.platform;

import androidx.collection.MutableIntList;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.semantics.ScrollAxisRange;
import androidx.compose.ui.semantics.SemanticsNode;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class AndroidComposeViewAccessibilityDelegateCompat$scheduleScrollEventIfNeeded$1 extends Lambda implements Function0 {
    final /* synthetic */ ScrollObservationScope $scrollObservationScope;
    final /* synthetic */ AndroidComposeViewAccessibilityDelegateCompat this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AndroidComposeViewAccessibilityDelegateCompat$scheduleScrollEventIfNeeded$1(ScrollObservationScope scrollObservationScope, AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat) {
        super(0);
        this.$scrollObservationScope = scrollObservationScope;
        this.this$0 = androidComposeViewAccessibilityDelegateCompat;
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r1v1, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r3v18, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r5v5, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        SemanticsNode semanticsNode;
        ScrollObservationScope scrollObservationScope = this.$scrollObservationScope;
        ScrollAxisRange scrollAxisRange = scrollObservationScope.horizontalScrollAxisRange;
        ScrollAxisRange scrollAxisRange2 = scrollObservationScope.verticalScrollAxisRange;
        Float f = scrollObservationScope.oldXValue;
        Float f2 = scrollObservationScope.oldYValue;
        float floatValue = (scrollAxisRange == null || f == null) ? 0.0f : ((Number) scrollAxisRange.value.invoke()).floatValue() - f.floatValue();
        float floatValue2 = (scrollAxisRange2 == null || f2 == null) ? 0.0f : ((Number) scrollAxisRange2.value.invoke()).floatValue() - f2.floatValue();
        if (floatValue != 0.0f || floatValue2 != 0.0f) {
            AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat = this.this$0;
            int i = this.$scrollObservationScope.semanticsNodeId;
            MutableIntList mutableIntList = AndroidComposeViewAccessibilityDelegateCompat.AccessibilityActionsResourceIds;
            int semanticsNodeIdToAccessibilityVirtualNodeId = androidComposeViewAccessibilityDelegateCompat.semanticsNodeIdToAccessibilityVirtualNodeId(i);
            SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds = (SemanticsNodeWithAdjustedBounds) this.this$0.getCurrentSemanticsNodes().get(this.this$0.focusedVirtualViewId);
            if (semanticsNodeWithAdjustedBounds != null) {
                AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat2 = this.this$0;
                try {
                    AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = androidComposeViewAccessibilityDelegateCompat2.currentlyFocusedANI;
                    if (accessibilityNodeInfoCompat != null) {
                        accessibilityNodeInfoCompat.setBoundsInScreen(androidComposeViewAccessibilityDelegateCompat2.boundsInScreen(semanticsNodeWithAdjustedBounds));
                    }
                } catch (IllegalStateException unused) {
                }
            }
            this.this$0.view.invalidate();
            SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds2 = (SemanticsNodeWithAdjustedBounds) this.this$0.getCurrentSemanticsNodes().get(semanticsNodeIdToAccessibilityVirtualNodeId);
            if (semanticsNodeWithAdjustedBounds2 != null && (semanticsNode = semanticsNodeWithAdjustedBounds2.semanticsNode) != null) {
                LayoutNode layoutNode = semanticsNode.layoutNode;
                AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat3 = this.this$0;
                if (scrollAxisRange != null) {
                    androidComposeViewAccessibilityDelegateCompat3.pendingHorizontalScrollEvents.set(semanticsNodeIdToAccessibilityVirtualNodeId, scrollAxisRange);
                }
                if (scrollAxisRange2 != null) {
                    androidComposeViewAccessibilityDelegateCompat3.pendingVerticalScrollEvents.set(semanticsNodeIdToAccessibilityVirtualNodeId, scrollAxisRange2);
                }
                androidComposeViewAccessibilityDelegateCompat3.notifySubtreeAccessibilityStateChangedIfNeeded(layoutNode);
            }
        }
        if (scrollAxisRange != null) {
            this.$scrollObservationScope.oldXValue = (Float) scrollAxisRange.value.invoke();
        }
        if (scrollAxisRange2 != null) {
            this.$scrollObservationScope.oldYValue = (Float) scrollAxisRange2.value.invoke();
        }
        return Unit.INSTANCE;
    }
}
