package androidx.compose.ui.layout;

import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LookaheadCapablePlaceable;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.unit.ConstraintsKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class PlaceableKt {
    public static final Function1 DefaultLayerBlock = new Function1() { // from class: androidx.compose.ui.layout.PlaceableKt$DefaultLayerBlock$1
        @Override // kotlin.jvm.functions.Function1
        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return Unit.INSTANCE;
        }
    };
    public static final long DefaultConstraints = ConstraintsKt.Constraints$default(0, 0, 0, 0, 15);

    public static final Placeable.PlacementScope PlacementScope(LookaheadCapablePlaceable lookaheadCapablePlaceable) {
        return new LookaheadCapablePlacementScope(lookaheadCapablePlaceable);
    }

    public static final Placeable.PlacementScope PlacementScope(Owner owner) {
        return new OuterPlacementScope(owner);
    }
}
