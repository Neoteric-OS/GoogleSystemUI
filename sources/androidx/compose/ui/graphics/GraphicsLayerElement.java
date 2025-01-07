package androidx.compose.ui.graphics;

import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;
import androidx.compose.animation.Scale$$ExternalSyntheticOutline0;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.foundation.OverscrollConfiguration$$ExternalSyntheticOutline0;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.node.NodeCoordinator;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class GraphicsLayerElement extends ModifierNodeElement {
    public final float alpha;
    public final long ambientShadowColor;
    public final boolean clip;
    public final int compositingStrategy;
    public final RenderEffect renderEffect;
    public final float rotationZ;
    public final float scaleX;
    public final float scaleY;
    public final float shadowElevation;
    public final Shape shape;
    public final long spotShadowColor;
    public final long transformOrigin;
    public final float translationX;
    public final float translationY;

    public GraphicsLayerElement(float f, float f2, float f3, float f4, float f5, float f6, float f7, long j, Shape shape, boolean z, RenderEffect renderEffect, long j2, long j3, int i) {
        this.scaleX = f;
        this.scaleY = f2;
        this.alpha = f3;
        this.translationX = f4;
        this.translationY = f5;
        this.shadowElevation = f6;
        this.rotationZ = f7;
        this.transformOrigin = j;
        this.shape = shape;
        this.clip = z;
        this.ambientShadowColor = j2;
        this.spotShadowColor = j3;
        this.compositingStrategy = i;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        final SimpleGraphicsLayerModifier simpleGraphicsLayerModifier = new SimpleGraphicsLayerModifier();
        simpleGraphicsLayerModifier.scaleX = this.scaleX;
        simpleGraphicsLayerModifier.scaleY = this.scaleY;
        simpleGraphicsLayerModifier.alpha = this.alpha;
        simpleGraphicsLayerModifier.translationX = this.translationX;
        simpleGraphicsLayerModifier.translationY = this.translationY;
        simpleGraphicsLayerModifier.shadowElevation = this.shadowElevation;
        simpleGraphicsLayerModifier.rotationZ = this.rotationZ;
        simpleGraphicsLayerModifier.cameraDistance = 8.0f;
        simpleGraphicsLayerModifier.transformOrigin = this.transformOrigin;
        simpleGraphicsLayerModifier.shape = this.shape;
        simpleGraphicsLayerModifier.clip = this.clip;
        simpleGraphicsLayerModifier.ambientShadowColor = this.ambientShadowColor;
        simpleGraphicsLayerModifier.spotShadowColor = this.spotShadowColor;
        simpleGraphicsLayerModifier.compositingStrategy = this.compositingStrategy;
        simpleGraphicsLayerModifier.layerBlock = new Function1() { // from class: androidx.compose.ui.graphics.SimpleGraphicsLayerModifier$layerBlock$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ReusableGraphicsLayerScope reusableGraphicsLayerScope = (ReusableGraphicsLayerScope) ((GraphicsLayerScope) obj);
                reusableGraphicsLayerScope.setScaleX(SimpleGraphicsLayerModifier.this.scaleX);
                reusableGraphicsLayerScope.setScaleY(SimpleGraphicsLayerModifier.this.scaleY);
                reusableGraphicsLayerScope.setAlpha(SimpleGraphicsLayerModifier.this.alpha);
                reusableGraphicsLayerScope.setTranslationX(SimpleGraphicsLayerModifier.this.translationX);
                reusableGraphicsLayerScope.setTranslationY(SimpleGraphicsLayerModifier.this.translationY);
                reusableGraphicsLayerScope.setShadowElevation(SimpleGraphicsLayerModifier.this.shadowElevation);
                SimpleGraphicsLayerModifier.this.getClass();
                SimpleGraphicsLayerModifier.this.getClass();
                reusableGraphicsLayerScope.setRotationZ(SimpleGraphicsLayerModifier.this.rotationZ);
                SimpleGraphicsLayerModifier simpleGraphicsLayerModifier2 = SimpleGraphicsLayerModifier.this;
                float f = simpleGraphicsLayerModifier2.cameraDistance;
                if (reusableGraphicsLayerScope.cameraDistance != f) {
                    reusableGraphicsLayerScope.mutatedFields |= 2048;
                    reusableGraphicsLayerScope.cameraDistance = f;
                }
                reusableGraphicsLayerScope.m391setTransformOrigin__ExYCQ(simpleGraphicsLayerModifier2.transformOrigin);
                reusableGraphicsLayerScope.setShape(SimpleGraphicsLayerModifier.this.shape);
                reusableGraphicsLayerScope.setClip(SimpleGraphicsLayerModifier.this.clip);
                RenderEffect renderEffect = SimpleGraphicsLayerModifier.this.renderEffect;
                if (!Intrinsics.areEqual(reusableGraphicsLayerScope.renderEffect, renderEffect)) {
                    reusableGraphicsLayerScope.mutatedFields |= 131072;
                    reusableGraphicsLayerScope.renderEffect = renderEffect;
                }
                reusableGraphicsLayerScope.m388setAmbientShadowColor8_81llA(SimpleGraphicsLayerModifier.this.ambientShadowColor);
                reusableGraphicsLayerScope.m390setSpotShadowColor8_81llA(SimpleGraphicsLayerModifier.this.spotShadowColor);
                reusableGraphicsLayerScope.m389setCompositingStrategyaDBOjCE(SimpleGraphicsLayerModifier.this.compositingStrategy);
                return Unit.INSTANCE;
            }
        };
        return simpleGraphicsLayerModifier;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GraphicsLayerElement)) {
            return false;
        }
        GraphicsLayerElement graphicsLayerElement = (GraphicsLayerElement) obj;
        return Float.compare(this.scaleX, graphicsLayerElement.scaleX) == 0 && Float.compare(this.scaleY, graphicsLayerElement.scaleY) == 0 && Float.compare(this.alpha, graphicsLayerElement.alpha) == 0 && Float.compare(this.translationX, graphicsLayerElement.translationX) == 0 && Float.compare(this.translationY, graphicsLayerElement.translationY) == 0 && Float.compare(this.shadowElevation, graphicsLayerElement.shadowElevation) == 0 && Float.compare(0.0f, 0.0f) == 0 && Float.compare(0.0f, 0.0f) == 0 && Float.compare(this.rotationZ, graphicsLayerElement.rotationZ) == 0 && Float.compare(8.0f, 8.0f) == 0 && TransformOrigin.m398equalsimpl0(this.transformOrigin, graphicsLayerElement.transformOrigin) && Intrinsics.areEqual(this.shape, graphicsLayerElement.shape) && this.clip == graphicsLayerElement.clip && Intrinsics.areEqual(this.renderEffect, graphicsLayerElement.renderEffect) && Color.m363equalsimpl0(this.ambientShadowColor, graphicsLayerElement.ambientShadowColor) && Color.m363equalsimpl0(this.spotShadowColor, graphicsLayerElement.spotShadowColor) && CompositingStrategy.m374equalsimpl0(this.compositingStrategy, graphicsLayerElement.compositingStrategy);
    }

    public final int hashCode() {
        int m = FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(Float.hashCode(this.scaleX) * 31, this.scaleY, 31), this.alpha, 31), this.translationX, 31), this.translationY, 31), this.shadowElevation, 31), 0.0f, 31), 0.0f, 31), this.rotationZ, 31), 8.0f, 31);
        int i = TransformOrigin.$r8$clinit;
        int m2 = (TransitionData$$ExternalSyntheticOutline0.m((this.shape.hashCode() + Scale$$ExternalSyntheticOutline0.m(m, 31, this.transformOrigin)) * 31, 31, this.clip) + 0) * 31;
        int i2 = Color.$r8$clinit;
        return Integer.hashCode(this.compositingStrategy) + Scale$$ExternalSyntheticOutline0.m(Scale$$ExternalSyntheticOutline0.m(m2, 31, this.ambientShadowColor), 31, this.spotShadowColor);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("GraphicsLayerElement(scaleX=");
        sb.append(this.scaleX);
        sb.append(", scaleY=");
        sb.append(this.scaleY);
        sb.append(", alpha=");
        sb.append(this.alpha);
        sb.append(", translationX=");
        sb.append(this.translationX);
        sb.append(", translationY=");
        sb.append(this.translationY);
        sb.append(", shadowElevation=");
        sb.append(this.shadowElevation);
        sb.append(", rotationX=0.0, rotationY=0.0, rotationZ=");
        sb.append(this.rotationZ);
        sb.append(", cameraDistance=8.0, transformOrigin=");
        sb.append((Object) TransformOrigin.m401toStringimpl(this.transformOrigin));
        sb.append(", shape=");
        sb.append(this.shape);
        sb.append(", clip=");
        sb.append(this.clip);
        sb.append(", renderEffect=");
        sb.append(this.renderEffect);
        sb.append(", ambientShadowColor=");
        OverscrollConfiguration$$ExternalSyntheticOutline0.m(this.ambientShadowColor, ", spotShadowColor=", sb);
        sb.append((Object) Color.m369toStringimpl(this.spotShadowColor));
        sb.append(", compositingStrategy=");
        sb.append((Object) ("CompositingStrategy(value=" + this.compositingStrategy + ')'));
        sb.append(')');
        return sb.toString();
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        SimpleGraphicsLayerModifier simpleGraphicsLayerModifier = (SimpleGraphicsLayerModifier) node;
        simpleGraphicsLayerModifier.scaleX = this.scaleX;
        simpleGraphicsLayerModifier.scaleY = this.scaleY;
        simpleGraphicsLayerModifier.alpha = this.alpha;
        simpleGraphicsLayerModifier.translationX = this.translationX;
        simpleGraphicsLayerModifier.translationY = this.translationY;
        simpleGraphicsLayerModifier.shadowElevation = this.shadowElevation;
        simpleGraphicsLayerModifier.rotationZ = this.rotationZ;
        simpleGraphicsLayerModifier.cameraDistance = 8.0f;
        simpleGraphicsLayerModifier.transformOrigin = this.transformOrigin;
        simpleGraphicsLayerModifier.shape = this.shape;
        simpleGraphicsLayerModifier.clip = this.clip;
        simpleGraphicsLayerModifier.renderEffect = this.renderEffect;
        simpleGraphicsLayerModifier.ambientShadowColor = this.ambientShadowColor;
        simpleGraphicsLayerModifier.spotShadowColor = this.spotShadowColor;
        simpleGraphicsLayerModifier.compositingStrategy = this.compositingStrategy;
        NodeCoordinator nodeCoordinator = DelegatableNodeKt.m503requireCoordinator64DMado(simpleGraphicsLayerModifier, 2).wrapped;
        if (nodeCoordinator != null) {
            nodeCoordinator.updateLayerBlock(simpleGraphicsLayerModifier.layerBlock, true);
        }
    }
}
