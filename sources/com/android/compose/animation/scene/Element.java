package com.android.compose.animation.scene;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.snapshots.SnapshotStateMap;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.unit.IntSize;
import com.android.compose.animation.scene.content.state.TransitionState;
import java.util.LinkedHashSet;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Element {
    public static final /* synthetic */ int $r8$clinit = 0;
    public static final long SizeUnspecified;
    public final ElementKey key;
    public TransitionState.Transition lastTransition;
    public final SnapshotStateMap stateByContent = new SnapshotStateMap();
    public boolean wasDrawnInAnyContent;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class State {
        public float alphaBeforeInterruption;
        public float alphaInterruptionDelta;
        public final ContentKey content;
        public float lastAlpha;
        public long lastOffset;
        public Scale lastScale;
        public long lastSize;
        public final Set nodes;
        public long offsetBeforeInterruption;
        public long offsetInterruptionDelta;
        public Scale scaleBeforeInterruption;
        public Scale scaleInterruptionDelta;
        public long sizeBeforeInterruption;
        public long sizeInterruptionDelta;
        public final MutableState targetOffset$delegate;
        public final MutableState targetSize$delegate;

        public State(ContentKey contentKey) {
            this.content = contentKey;
            long j = Element.SizeUnspecified;
            this.targetSize$delegate = SnapshotStateKt.mutableStateOf$default(new IntSize(j));
            this.targetOffset$delegate = SnapshotStateKt.mutableStateOf$default(new Offset(9205357640488583168L));
            this.lastOffset = 9205357640488583168L;
            this.lastSize = j;
            Scale scale = Scale.Unspecified;
            this.lastScale = scale;
            this.lastAlpha = Float.MAX_VALUE;
            this.offsetBeforeInterruption = 9205357640488583168L;
            this.sizeBeforeInterruption = j;
            this.scaleBeforeInterruption = scale;
            this.alphaBeforeInterruption = Float.MAX_VALUE;
            this.offsetInterruptionDelta = 0L;
            this.sizeInterruptionDelta = 0L;
            this.scaleInterruptionDelta = Scale.Zero;
            this.nodes = new LinkedHashSet();
        }

        /* renamed from: getTargetOffset-F1C5BW0, reason: not valid java name */
        public final long m729getTargetOffsetF1C5BW0() {
            return ((Offset) ((SnapshotMutableStateImpl) this.targetOffset$delegate).getValue()).packedValue;
        }

        /* renamed from: getTargetSize-YbymL2g, reason: not valid java name */
        public final long m730getTargetSizeYbymL2g() {
            return ((IntSize) ((SnapshotMutableStateImpl) this.targetSize$delegate).getValue()).packedValue;
        }
    }

    static {
        long j = Integer.MAX_VALUE;
        SizeUnspecified = (j & 4294967295L) | (j << 32);
    }

    public Element(ElementKey elementKey) {
        this.key = elementKey;
    }

    public final String toString() {
        return "Element(key=" + this.key + ")";
    }
}
