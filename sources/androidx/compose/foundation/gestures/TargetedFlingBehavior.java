package androidx.compose.foundation.gestures;

import androidx.compose.foundation.gestures.snapping.SnapFlingBehavior;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface TargetedFlingBehavior extends FlingBehavior {
    @Override // androidx.compose.foundation.gestures.FlingBehavior
    default Object performFling(ScrollingLogic$doFlingAnimation$2$reverseScope$1 scrollingLogic$doFlingAnimation$2$reverseScope$1, float f, Continuation continuation) {
        return ((SnapFlingBehavior) this).performFling(scrollingLogic$doFlingAnimation$2$reverseScope$1, f, TargetedFlingBehaviorKt.NoOnReport, (ContinuationImpl) continuation);
    }
}
