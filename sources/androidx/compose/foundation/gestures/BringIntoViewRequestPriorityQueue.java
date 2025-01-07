package androidx.compose.foundation.gestures;

import androidx.compose.foundation.gestures.ContentInViewNode;
import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.runtime.collection.MutableVector;
import kotlin.Unit;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CancellableContinuation;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BringIntoViewRequestPriorityQueue {
    public final MutableVector requests = new MutableVector(new ContentInViewNode.Request[16]);

    public final void cancelAndRemoveAll(Throwable th) {
        MutableVector mutableVector = this.requests;
        int i = mutableVector.size;
        CancellableContinuation[] cancellableContinuationArr = new CancellableContinuation[i];
        for (int i2 = 0; i2 < i; i2++) {
            cancellableContinuationArr[i2] = ((ContentInViewNode.Request) mutableVector.content[i2]).continuation;
        }
        for (int i3 = 0; i3 < i; i3++) {
            cancellableContinuationArr[i3].cancel(th);
        }
        if (mutableVector.size == 0) {
            return;
        }
        InlineClassHelperKt.throwIllegalStateException("uncancelled requests present");
    }

    public final void resumeAndRemoveAll() {
        MutableVector mutableVector = this.requests;
        IntRange until = RangesKt.until(0, mutableVector.size);
        int i = until.first;
        int i2 = until.last;
        if (i <= i2) {
            while (true) {
                ((ContentInViewNode.Request) mutableVector.content[i]).continuation.resumeWith(Unit.INSTANCE);
                if (i == i2) {
                    break;
                } else {
                    i++;
                }
            }
        }
        mutableVector.clear();
    }
}
