package androidx.compose.foundation.lazy.layout;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.unit.IntOffset;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LazyLayoutItemAnimation {
    public static final /* synthetic */ int $r8$clinit = 0;
    public static final long NotInitialized;
    public final CoroutineScope coroutineScope;
    public SpringSpec fadeInSpec;
    public SpringSpec fadeOutSpec;
    public long finalOffset;
    public final GraphicsContext graphicsContext;
    public final MutableState isAppearanceAnimationInProgress$delegate;
    public final MutableState isDisappearanceAnimationFinished$delegate;
    public final MutableState isDisappearanceAnimationInProgress$delegate;
    public final MutableState isPlacementAnimationInProgress$delegate;
    public boolean isRunningMovingAwayAnimation;
    public GraphicsLayer layer;
    public long lookaheadOffset;
    public final Function0 onLayerPropertyChanged;
    public final MutableState placementDelta$delegate;
    public final Animatable placementDeltaAnimation;
    public FiniteAnimationSpec placementSpec;
    public long rawOffset;
    public final Animatable visibilityAnimation;

    static {
        long j = Integer.MAX_VALUE;
        NotInitialized = (j & 4294967295L) | (j << 32);
    }

    public LazyLayoutItemAnimation(CoroutineScope coroutineScope, GraphicsContext graphicsContext, Function0 function0) {
        this.coroutineScope = coroutineScope;
        this.graphicsContext = graphicsContext;
        this.onLayerPropertyChanged = function0;
        Boolean bool = Boolean.FALSE;
        this.isPlacementAnimationInProgress$delegate = SnapshotStateKt.mutableStateOf$default(bool);
        this.isAppearanceAnimationInProgress$delegate = SnapshotStateKt.mutableStateOf$default(bool);
        this.isDisappearanceAnimationInProgress$delegate = SnapshotStateKt.mutableStateOf$default(bool);
        this.isDisappearanceAnimationFinished$delegate = SnapshotStateKt.mutableStateOf$default(bool);
        long j = NotInitialized;
        this.rawOffset = j;
        this.finalOffset = 0L;
        this.layer = graphicsContext != null ? graphicsContext.createGraphicsLayer() : null;
        this.placementDeltaAnimation = new Animatable(new IntOffset(0L), VectorConvertersKt.IntOffsetToVector, null, null, 12);
        this.visibilityAnimation = new Animatable(Float.valueOf(1.0f), VectorConvertersKt.FloatToVector, null, null, 12);
        this.placementDelta$delegate = SnapshotStateKt.mutableStateOf$default(new IntOffset(0L));
        this.lookaheadOffset = j;
    }

    public final void animateAppearance() {
        GraphicsLayer graphicsLayer = this.layer;
        SpringSpec springSpec = this.fadeInSpec;
        boolean booleanValue = ((Boolean) ((SnapshotMutableStateImpl) this.isAppearanceAnimationInProgress$delegate).getValue()).booleanValue();
        CoroutineScope coroutineScope = this.coroutineScope;
        if (booleanValue || springSpec == null || graphicsLayer == null) {
            if (isDisappearanceAnimationInProgress()) {
                if (graphicsLayer != null) {
                    graphicsLayer.setAlpha(1.0f);
                }
                BuildersKt.launch$default(coroutineScope, null, null, new LazyLayoutItemAnimation$animateAppearance$1(this, null), 3);
                return;
            }
            return;
        }
        setAppearanceAnimationInProgress(true);
        boolean isDisappearanceAnimationInProgress = isDisappearanceAnimationInProgress();
        boolean z = !isDisappearanceAnimationInProgress;
        if (!isDisappearanceAnimationInProgress) {
            graphicsLayer.setAlpha(0.0f);
        }
        BuildersKt.launch$default(coroutineScope, null, null, new LazyLayoutItemAnimation$animateAppearance$2(z, this, springSpec, graphicsLayer, null), 3);
    }

    public final void cancelPlacementAnimation() {
        if (((Boolean) ((SnapshotMutableStateImpl) this.isPlacementAnimationInProgress$delegate).getValue()).booleanValue()) {
            BuildersKt.launch$default(this.coroutineScope, null, null, new LazyLayoutItemAnimation$cancelPlacementAnimation$1(this, null), 3);
        }
    }

    public final boolean isDisappearanceAnimationInProgress() {
        return ((Boolean) ((SnapshotMutableStateImpl) this.isDisappearanceAnimationInProgress$delegate).getValue()).booleanValue();
    }

    public final void release() {
        GraphicsContext graphicsContext;
        boolean booleanValue = ((Boolean) ((SnapshotMutableStateImpl) this.isPlacementAnimationInProgress$delegate).getValue()).booleanValue();
        CoroutineScope coroutineScope = this.coroutineScope;
        if (booleanValue) {
            setPlacementAnimationInProgress(false);
            BuildersKt.launch$default(coroutineScope, null, null, new LazyLayoutItemAnimation$release$1(this, null), 3);
        }
        if (((Boolean) ((SnapshotMutableStateImpl) this.isAppearanceAnimationInProgress$delegate).getValue()).booleanValue()) {
            setAppearanceAnimationInProgress(false);
            BuildersKt.launch$default(coroutineScope, null, null, new LazyLayoutItemAnimation$release$2(this, null), 3);
        }
        if (isDisappearanceAnimationInProgress()) {
            setDisappearanceAnimationInProgress(false);
            BuildersKt.launch$default(coroutineScope, null, null, new LazyLayoutItemAnimation$release$3(this, null), 3);
        }
        this.isRunningMovingAwayAnimation = false;
        m133setPlacementDeltagyyYBs(0L);
        this.rawOffset = NotInitialized;
        GraphicsLayer graphicsLayer = this.layer;
        if (graphicsLayer != null && (graphicsContext = this.graphicsContext) != null) {
            graphicsContext.releaseGraphicsLayer(graphicsLayer);
        }
        this.layer = null;
        this.fadeInSpec = null;
        this.fadeOutSpec = null;
        this.placementSpec = null;
    }

    public final void setAppearanceAnimationInProgress(boolean z) {
        ((SnapshotMutableStateImpl) this.isAppearanceAnimationInProgress$delegate).setValue(Boolean.valueOf(z));
    }

    public final void setDisappearanceAnimationInProgress(boolean z) {
        ((SnapshotMutableStateImpl) this.isDisappearanceAnimationInProgress$delegate).setValue(Boolean.valueOf(z));
    }

    public final void setPlacementAnimationInProgress(boolean z) {
        ((SnapshotMutableStateImpl) this.isPlacementAnimationInProgress$delegate).setValue(Boolean.valueOf(z));
    }

    /* renamed from: setPlacementDelta--gyyYBs, reason: not valid java name */
    public final void m133setPlacementDeltagyyYBs(long j) {
        ((SnapshotMutableStateImpl) this.placementDelta$delegate).setValue(new IntOffset(j));
    }
}
