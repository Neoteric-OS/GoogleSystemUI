package androidx.compose.foundation;

import android.view.View;
import androidx.compose.animation.ChangeSize$$ExternalSyntheticOutline0;
import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;
import androidx.compose.animation.Scale$$ExternalSyntheticOutline0;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatableNode_androidKt;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.semantics.SemanticsPropertyKey;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MagnifierElement extends ModifierNodeElement {
    public final Function1 onSizeChanged;
    public final PlatformMagnifierFactory platformMagnifierFactory;
    public final Function1 sourceCenter;

    public MagnifierElement(Function1 function1, Function1 function12, PlatformMagnifierFactory platformMagnifierFactory) {
        this.sourceCenter = function1;
        this.onSizeChanged = function12;
        this.platformMagnifierFactory = platformMagnifierFactory;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        return new MagnifierNode(this.sourceCenter, this.onSizeChanged, this.platformMagnifierFactory);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MagnifierElement)) {
            return false;
        }
        Function1 function1 = ((MagnifierElement) obj).sourceCenter;
        return false;
    }

    public final int hashCode() {
        return this.platformMagnifierFactory.hashCode() + ChangeSize$$ExternalSyntheticOutline0.m(this.onSizeChanged, TransitionData$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(Scale$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(this.sourceCenter.hashCode() * 961, Float.NaN, 31), 31, true), 31, 9205357640488583168L), Float.NaN, 31), Float.NaN, 31), 31, true), 31);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        MagnifierNode magnifierNode = (MagnifierNode) node;
        magnifierNode.getClass();
        PlatformMagnifierFactory platformMagnifierFactory = magnifierNode.platformMagnifierFactory;
        View view = magnifierNode.view;
        Density density = magnifierNode.density;
        magnifierNode.sourceCenter = this.sourceCenter;
        magnifierNode.onSizeChanged = this.onSizeChanged;
        PlatformMagnifierFactory platformMagnifierFactory2 = this.platformMagnifierFactory;
        magnifierNode.platformMagnifierFactory = platformMagnifierFactory2;
        View requireView = DelegatableNode_androidKt.requireView(magnifierNode);
        Density density2 = DelegatableNodeKt.requireLayoutNode(magnifierNode).density;
        if (magnifierNode.magnifier != null) {
            SemanticsPropertyKey semanticsPropertyKey = Magnifier_androidKt.MagnifierPositionInRoot;
            if (((!Float.isNaN(Float.NaN) || !Float.isNaN(Float.NaN)) && !platformMagnifierFactory2.getCanUpdateZoom()) || !Dp.m668equalsimpl0(Float.NaN, Float.NaN) || !Dp.m668equalsimpl0(Float.NaN, Float.NaN) || !platformMagnifierFactory2.equals(platformMagnifierFactory) || !requireView.equals(view) || !Intrinsics.areEqual(density2, density)) {
                magnifierNode.recreateMagnifier();
            }
        }
        magnifierNode.updateMagnifier();
    }
}
