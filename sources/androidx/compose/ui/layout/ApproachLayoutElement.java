package androidx.compose.ui.layout;

import androidx.compose.animation.ChangeSize$$ExternalSyntheticOutline0;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ModifierNodeElement;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ApproachLayoutElement extends ModifierNodeElement {
    public final Function3 approachMeasure;
    public final Function1 isMeasurementApproachInProgress;
    public final Function2 isPlacementApproachInProgress;

    public ApproachLayoutElement(Function3 function3, Function1 function1, Function2 function2) {
        this.approachMeasure = function3;
        this.isMeasurementApproachInProgress = function1;
        this.isPlacementApproachInProgress = function2;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        ApproachLayoutModifierNodeImpl approachLayoutModifierNodeImpl = new ApproachLayoutModifierNodeImpl();
        approachLayoutModifierNodeImpl.measureBlock = this.approachMeasure;
        approachLayoutModifierNodeImpl.isMeasurementApproachInProgress = this.isMeasurementApproachInProgress;
        approachLayoutModifierNodeImpl.isPlacementApproachInProgress = this.isPlacementApproachInProgress;
        return approachLayoutModifierNodeImpl;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ApproachLayoutElement)) {
            return false;
        }
        ApproachLayoutElement approachLayoutElement = (ApproachLayoutElement) obj;
        return Intrinsics.areEqual(this.approachMeasure, approachLayoutElement.approachMeasure) && Intrinsics.areEqual(this.isMeasurementApproachInProgress, approachLayoutElement.isMeasurementApproachInProgress) && Intrinsics.areEqual(this.isPlacementApproachInProgress, approachLayoutElement.isPlacementApproachInProgress);
    }

    public final int hashCode() {
        return this.isPlacementApproachInProgress.hashCode() + ChangeSize$$ExternalSyntheticOutline0.m(this.isMeasurementApproachInProgress, this.approachMeasure.hashCode() * 31, 31);
    }

    public final String toString() {
        return "ApproachLayoutElement(approachMeasure=" + this.approachMeasure + ", isMeasurementApproachInProgress=" + this.isMeasurementApproachInProgress + ", isPlacementApproachInProgress=" + this.isPlacementApproachInProgress + ')';
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        ApproachLayoutModifierNodeImpl approachLayoutModifierNodeImpl = (ApproachLayoutModifierNodeImpl) node;
        approachLayoutModifierNodeImpl.measureBlock = this.approachMeasure;
        approachLayoutModifierNodeImpl.isMeasurementApproachInProgress = this.isMeasurementApproachInProgress;
        approachLayoutModifierNodeImpl.isPlacementApproachInProgress = this.isPlacementApproachInProgress;
    }
}
