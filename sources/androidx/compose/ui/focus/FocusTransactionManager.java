package androidx.compose.ui.focus;

import androidx.collection.MutableScatterMap;
import androidx.collection.ScatterMapKt;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.internal.InlineClassHelperKt;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FocusTransactionManager {
    public final MutableVector cancellationListener;
    public boolean ongoingTransaction;
    public final MutableScatterMap states;

    public FocusTransactionManager() {
        long[] jArr = ScatterMapKt.EmptyGroup;
        this.states = new MutableScatterMap();
        this.cancellationListener = new MutableVector(new Function0[16]);
    }

    public static final void access$cancelTransaction(FocusTransactionManager focusTransactionManager) {
        focusTransactionManager.states.clear();
        int i = 0;
        focusTransactionManager.ongoingTransaction = false;
        MutableVector mutableVector = focusTransactionManager.cancellationListener;
        int i2 = mutableVector.size;
        if (i2 > 0) {
            Object[] objArr = mutableVector.content;
            do {
                ((Function0) objArr[i]).invoke();
                i++;
            } while (i < i2);
        }
        mutableVector.clear();
    }

    public static final void access$commitTransaction(FocusTransactionManager focusTransactionManager) {
        MutableScatterMap mutableScatterMap = focusTransactionManager.states;
        Object[] objArr = mutableScatterMap.keys;
        long[] jArr = mutableScatterMap.metadata;
        int length = jArr.length - 2;
        if (length >= 0) {
            int i = 0;
            while (true) {
                long j = jArr[i];
                if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i2 = 8 - ((~(i - length)) >>> 31);
                    for (int i3 = 0; i3 < i2; i3++) {
                        if ((255 & j) < 128) {
                            FocusTargetNode focusTargetNode = (FocusTargetNode) objArr[(i << 3) + i3];
                            focusTargetNode.getClass();
                            FocusStateImpl focusStateImpl = (FocusStateImpl) FocusTargetNodeKt.requireTransactionManager(focusTargetNode).states.get(focusTargetNode);
                            if (focusStateImpl == null) {
                                InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("committing a node that was not updated in the current transaction");
                                throw null;
                            }
                            focusTargetNode.committedFocusState = focusStateImpl;
                        }
                        j >>= 8;
                    }
                    if (i2 != 8) {
                        break;
                    }
                }
                if (i == length) {
                    break;
                } else {
                    i++;
                }
            }
        }
        mutableScatterMap.clear();
        focusTransactionManager.ongoingTransaction = false;
        focusTransactionManager.cancellationListener.clear();
    }
}
