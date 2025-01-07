package androidx.compose.runtime;

import androidx.collection.MutableObjectIntMap;
import androidx.collection.MutableScatterMap;
import androidx.compose.runtime.Composer;
import java.util.List;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class RecomposeScopeImpl implements RecomposeScope {
    public Anchor anchor;
    public Function2 block;
    public int currentToken;
    public int flags;
    public RecomposeScopeOwner owner;
    public MutableScatterMap trackedDependencies;
    public MutableObjectIntMap trackedInstances;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Companion {
        public static void adoptAnchoredScopes$runtime_release(SlotWriter slotWriter, List list, RecomposeScopeOwner recomposeScopeOwner) {
            if (list.isEmpty()) {
                return;
            }
            int size = list.size();
            for (int i = 0; i < size; i++) {
                int anchorIndex = slotWriter.anchorIndex((Anchor) list.get(i));
                int slotIndex = slotWriter.slotIndex(slotWriter.groups, slotWriter.groupIndexToAddress(anchorIndex));
                Object obj = slotIndex < slotWriter.dataIndex(slotWriter.groups, slotWriter.groupIndexToAddress(anchorIndex + 1)) ? slotWriter.slots[slotWriter.dataIndexToDataAddress(slotIndex)] : Composer.Companion.Empty;
                RecomposeScopeImpl recomposeScopeImpl = obj instanceof RecomposeScopeImpl ? (RecomposeScopeImpl) obj : null;
                if (recomposeScopeImpl != null) {
                    recomposeScopeImpl.owner = recomposeScopeOwner;
                }
            }
        }
    }

    public RecomposeScopeImpl(CompositionImpl compositionImpl) {
        this.owner = compositionImpl;
    }

    public final boolean getValid() {
        if (this.owner == null) {
            return false;
        }
        Anchor anchor = this.anchor;
        return anchor != null ? anchor.getValid() : false;
    }

    public final InvalidationResult invalidateForResult(Object obj) {
        InvalidationResult invalidate;
        RecomposeScopeOwner recomposeScopeOwner = this.owner;
        return (recomposeScopeOwner == null || (invalidate = recomposeScopeOwner.invalidate(this, obj)) == null) ? InvalidationResult.IGNORED : invalidate;
    }

    public final void release() {
        RecomposeScopeOwner recomposeScopeOwner = this.owner;
        if (recomposeScopeOwner != null) {
            recomposeScopeOwner.recomposeScopeReleased();
        }
        this.owner = null;
        this.trackedInstances = null;
        this.trackedDependencies = null;
    }

    public final void setRereading(boolean z) {
        if (z) {
            this.flags |= 32;
        } else {
            this.flags &= -33;
        }
    }
}
