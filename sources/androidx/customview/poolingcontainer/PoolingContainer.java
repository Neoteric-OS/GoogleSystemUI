package androidx.customview.poolingcontainer;

import android.view.View;
import androidx.compose.ui.platform.ViewCompositionStrategy$DisposeOnDetachedFromWindowOrReleasedFromPool$$ExternalSyntheticLambda0;
import androidx.core.view.ViewKt;
import com.android.wm.shell.R;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.sequences.SequenceBuilderIterator;
import kotlin.sequences.SequencesKt__SequenceBuilderKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class PoolingContainer {
    /* JADX WARN: Type inference failed for: r3v2, types: [kotlin.coroutines.jvm.internal.RestrictedSuspendLambda, kotlin.jvm.functions.Function2] */
    public static final void callPoolingContainerOnRelease(View view) {
        SequenceBuilderIterator it = SequencesKt__SequenceBuilderKt.iterator(ViewKt.getAllViews(view).$block$inlined);
        while (it.hasNext()) {
            PoolingContainerListenerHolder poolingContainerListenerHolder = getPoolingContainerListenerHolder((View) it.next());
            for (int lastIndex = CollectionsKt__CollectionsKt.getLastIndex(poolingContainerListenerHolder.listeners); -1 < lastIndex; lastIndex--) {
                ((ViewCompositionStrategy$DisposeOnDetachedFromWindowOrReleasedFromPool$$ExternalSyntheticLambda0) ((PoolingContainerListener) poolingContainerListenerHolder.listeners.get(lastIndex))).f$0.disposeComposition();
            }
        }
    }

    public static final PoolingContainerListenerHolder getPoolingContainerListenerHolder(View view) {
        PoolingContainerListenerHolder poolingContainerListenerHolder = (PoolingContainerListenerHolder) view.getTag(R.id.pooling_container_listener_holder_tag);
        if (poolingContainerListenerHolder != null) {
            return poolingContainerListenerHolder;
        }
        PoolingContainerListenerHolder poolingContainerListenerHolder2 = new PoolingContainerListenerHolder();
        view.setTag(R.id.pooling_container_listener_holder_tag, poolingContainerListenerHolder2);
        return poolingContainerListenerHolder2;
    }
}
