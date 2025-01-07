package androidx.compose.foundation.layout;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.layout.LayoutModifier;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.modifier.ModifierLocalConsumer;
import androidx.compose.ui.modifier.ModifierLocalProvider;
import androidx.compose.ui.modifier.ModifierLocalReadScope;
import androidx.compose.ui.modifier.ProvidableModifierLocal;
import androidx.compose.ui.unit.ConstraintsKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class InsetsPaddingModifier implements LayoutModifier, ModifierLocalConsumer, ModifierLocalProvider {
    public final MutableState consumedInsets$delegate;
    public final WindowInsets insets;
    public final MutableState unconsumedInsets$delegate;

    public InsetsPaddingModifier(WindowInsets windowInsets) {
        this.insets = windowInsets;
        this.unconsumedInsets$delegate = SnapshotStateKt.mutableStateOf$default(windowInsets);
        this.consumedInsets$delegate = SnapshotStateKt.mutableStateOf$default(windowInsets);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof InsetsPaddingModifier) {
            return Intrinsics.areEqual(((InsetsPaddingModifier) obj).insets, this.insets);
        }
        return false;
    }

    @Override // androidx.compose.ui.modifier.ModifierLocalProvider
    public final ProvidableModifierLocal getKey() {
        return WindowInsetsPaddingKt.ModifierLocalConsumedWindowInsets;
    }

    @Override // androidx.compose.ui.modifier.ModifierLocalProvider
    public final WindowInsets getValue$1() {
        return (WindowInsets) ((SnapshotMutableStateImpl) this.consumedInsets$delegate).getValue();
    }

    public final int hashCode() {
        return this.insets.hashCode();
    }

    @Override // androidx.compose.ui.layout.LayoutModifier
    /* renamed from: measure-3p2s80s */
    public final MeasureResult mo3measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        MeasureResult layout$1;
        MutableState mutableState = this.unconsumedInsets$delegate;
        final int left = ((WindowInsets) ((SnapshotMutableStateImpl) mutableState).getValue()).getLeft(measureScope, measureScope.getLayoutDirection());
        final int top = ((WindowInsets) ((SnapshotMutableStateImpl) mutableState).getValue()).getTop(measureScope);
        int right = ((WindowInsets) ((SnapshotMutableStateImpl) mutableState).getValue()).getRight(measureScope, measureScope.getLayoutDirection()) + left;
        int bottom = ((WindowInsets) ((SnapshotMutableStateImpl) mutableState).getValue()).getBottom(measureScope) + top;
        final Placeable mo479measureBRTryo0 = measurable.mo479measureBRTryo0(ConstraintsKt.m666offsetNN6EwU(-right, -bottom, j));
        layout$1 = measureScope.layout$1(ConstraintsKt.m665constrainWidthK40F9xA(j, mo479measureBRTryo0.width + right), ConstraintsKt.m664constrainHeightK40F9xA(j, mo479measureBRTryo0.height + bottom), MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.foundation.layout.InsetsPaddingModifier$measure$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ((Placeable.PlacementScope) obj).place(mo479measureBRTryo0, left, top, 0.0f);
                return Unit.INSTANCE;
            }
        });
        return layout$1;
    }

    @Override // androidx.compose.ui.modifier.ModifierLocalConsumer
    public final void onModifierLocalsUpdated(ModifierLocalReadScope modifierLocalReadScope) {
        WindowInsets windowInsets = (WindowInsets) modifierLocalReadScope.getCurrent(WindowInsetsPaddingKt.ModifierLocalConsumedWindowInsets);
        WindowInsets windowInsets2 = this.insets;
        ((SnapshotMutableStateImpl) this.unconsumedInsets$delegate).setValue(new ExcludeInsets(windowInsets2, windowInsets));
        ((SnapshotMutableStateImpl) this.consumedInsets$delegate).setValue(new UnionInsets(windowInsets, windowInsets2));
    }
}
