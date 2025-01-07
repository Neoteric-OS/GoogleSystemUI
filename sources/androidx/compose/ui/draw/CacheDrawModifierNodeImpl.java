package androidx.compose.ui.draw;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DrawModifierNodeKt;
import androidx.compose.ui.node.LayoutNodeDrawScope;
import androidx.compose.ui.node.ObserverModifierNode;
import androidx.compose.ui.node.ObserverModifierNodeKt;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class CacheDrawModifierNodeImpl extends Modifier.Node implements CacheDrawModifierNode, ObserverModifierNode, BuildDrawCacheParams {
    public Function1 block;
    public final CacheDrawScope cacheDrawScope;
    public ScopedGraphicsContext cachedGraphicsContext;
    public boolean isCacheValid;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: androidx.compose.ui.draw.CacheDrawModifierNodeImpl$1, reason: invalid class name */
    final class AnonymousClass1 extends Lambda implements Function0 {
        final /* synthetic */ CacheDrawModifierNodeImpl this$0;

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            CacheDrawModifierNodeImpl cacheDrawModifierNodeImpl = this.this$0;
            ScopedGraphicsContext scopedGraphicsContext = cacheDrawModifierNodeImpl.cachedGraphicsContext;
            if (scopedGraphicsContext == null) {
                scopedGraphicsContext = new ScopedGraphicsContext();
                cacheDrawModifierNodeImpl.cachedGraphicsContext = scopedGraphicsContext;
            }
            if (scopedGraphicsContext.graphicsContext == null) {
                GraphicsContext graphicsContext = ((AndroidComposeView) DelegatableNodeKt.requireOwner(cacheDrawModifierNodeImpl)).graphicsContext;
                scopedGraphicsContext.releaseGraphicsLayers();
                scopedGraphicsContext.graphicsContext = graphicsContext;
            }
            return scopedGraphicsContext;
        }
    }

    public CacheDrawModifierNodeImpl(CacheDrawScope cacheDrawScope, Function1 function1) {
        this.cacheDrawScope = cacheDrawScope;
        this.block = function1;
        cacheDrawScope.cacheParams = this;
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    @Override // androidx.compose.ui.node.DrawModifierNode
    public final void draw(LayoutNodeDrawScope layoutNodeDrawScope) {
        boolean z = this.isCacheValid;
        final CacheDrawScope cacheDrawScope = this.cacheDrawScope;
        if (!z) {
            cacheDrawScope.drawResult = null;
            ObserverModifierNodeKt.observeReads(this, new Function0() { // from class: androidx.compose.ui.draw.CacheDrawModifierNodeImpl$getOrBuildCachedDrawBlock$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    CacheDrawModifierNodeImpl.this.block.invoke(cacheDrawScope);
                    return Unit.INSTANCE;
                }
            });
            if (cacheDrawScope.drawResult == null) {
                InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("DrawResult not defined, did you forget to call onDraw?");
                throw null;
            }
            this.isCacheValid = true;
        }
        DrawResult drawResult = cacheDrawScope.drawResult;
        Intrinsics.checkNotNull(drawResult);
        drawResult.block.invoke(layoutNodeDrawScope);
    }

    @Override // androidx.compose.ui.draw.BuildDrawCacheParams
    public final Density getDensity() {
        return DelegatableNodeKt.requireLayoutNode(this).density;
    }

    @Override // androidx.compose.ui.draw.BuildDrawCacheParams
    public final LayoutDirection getLayoutDirection() {
        return DelegatableNodeKt.requireLayoutNode(this).layoutDirection;
    }

    @Override // androidx.compose.ui.draw.BuildDrawCacheParams
    /* renamed from: getSize-NH-jbRc */
    public final long mo279getSizeNHjbRc() {
        return IntSizeKt.m685toSizeozmzZPI(DelegatableNodeKt.m503requireCoordinator64DMado(this, 128).measuredSize);
    }

    @Override // androidx.compose.ui.draw.CacheDrawModifierNode
    public final void invalidateDrawCache() {
        ScopedGraphicsContext scopedGraphicsContext = this.cachedGraphicsContext;
        if (scopedGraphicsContext != null) {
            scopedGraphicsContext.releaseGraphicsLayers();
        }
        this.isCacheValid = false;
        this.cacheDrawScope.drawResult = null;
        DrawModifierNodeKt.invalidateDraw(this);
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final void onDetach() {
        ScopedGraphicsContext scopedGraphicsContext = this.cachedGraphicsContext;
        if (scopedGraphicsContext != null) {
            scopedGraphicsContext.releaseGraphicsLayers();
        }
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public final void onMeasureResultChanged() {
        invalidateDrawCache();
    }

    @Override // androidx.compose.ui.node.ObserverModifierNode
    public final void onObservedReadsChanged() {
        invalidateDrawCache();
    }
}
