package androidx.compose.foundation.lazy.layout;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.BeyondBoundsLayout;
import androidx.compose.ui.layout.BeyondBoundsLayoutKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.modifier.ModifierLocal;
import androidx.compose.ui.modifier.ModifierLocalMap;
import androidx.compose.ui.modifier.ModifierLocalModifierNode;
import androidx.compose.ui.modifier.SingleLocalMap;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LazyLayoutBeyondBoundsModifierNode extends Modifier.Node implements ModifierLocalModifierNode, BeyondBoundsLayout, LayoutModifierNode {
    public static final LazyLayoutBeyondBoundsModifierNode$Companion$emptyBeyondBoundsScope$1 emptyBeyondBoundsScope = new LazyLayoutBeyondBoundsModifierNode$Companion$emptyBeyondBoundsScope$1();
    public LazyLayoutBeyondBoundsInfo beyondBoundsInfo;
    public LayoutDirection layoutDirection;
    public Orientation orientation;
    public boolean reverseLayout;
    public LazyLayoutBeyondBoundsState state;

    @Override // androidx.compose.ui.modifier.ModifierLocalModifierNode
    public final ModifierLocalMap getProvidedValues() {
        Pair pair = new Pair(BeyondBoundsLayoutKt.ModifierLocalBeyondBoundsLayout, this);
        ModifierLocal modifierLocal = (ModifierLocal) pair.getFirst();
        SingleLocalMap singleLocalMap = new SingleLocalMap(modifierLocal);
        ModifierLocal modifierLocal2 = (ModifierLocal) pair.getFirst();
        Object second = pair.getSecond();
        if (modifierLocal2 != modifierLocal) {
            InlineClassHelperKt.throwIllegalStateException("Check failed.");
        }
        ((SnapshotMutableStateImpl) singleLocalMap.value$delegate).setValue(second);
        return singleLocalMap;
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x0030, code lost:
    
        if (r4.orientation == androidx.compose.foundation.gestures.Orientation.Vertical) goto L10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0016, code lost:
    
        if (r4.orientation == androidx.compose.foundation.gestures.Orientation.Horizontal) goto L10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0018, code lost:
    
        r0 = true;
     */
    /* renamed from: hasMoreContent-FR3nfPY, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean m131hasMoreContentFR3nfPY(androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsInfo.Interval r5, int r6) {
        /*
            r4 = this;
            r0 = 5
            boolean r0 = androidx.compose.ui.layout.BeyondBoundsLayout.LayoutDirection.m475equalsimpl0(r6, r0)
            r1 = 1
            if (r0 == 0) goto La
            r0 = r1
            goto Lf
        La:
            r0 = 6
            boolean r0 = androidx.compose.ui.layout.BeyondBoundsLayout.LayoutDirection.m475equalsimpl0(r6, r0)
        Lf:
            r2 = 0
            if (r0 == 0) goto L1c
            androidx.compose.foundation.gestures.Orientation r0 = r4.orientation
            androidx.compose.foundation.gestures.Orientation r3 = androidx.compose.foundation.gestures.Orientation.Horizontal
            if (r0 != r3) goto L1a
        L18:
            r0 = r1
            goto L43
        L1a:
            r0 = r2
            goto L43
        L1c:
            r0 = 3
            boolean r0 = androidx.compose.ui.layout.BeyondBoundsLayout.LayoutDirection.m475equalsimpl0(r6, r0)
            if (r0 == 0) goto L25
            r0 = r1
            goto L2a
        L25:
            r0 = 4
            boolean r0 = androidx.compose.ui.layout.BeyondBoundsLayout.LayoutDirection.m475equalsimpl0(r6, r0)
        L2a:
            if (r0 == 0) goto L33
            androidx.compose.foundation.gestures.Orientation r0 = r4.orientation
            androidx.compose.foundation.gestures.Orientation r3 = androidx.compose.foundation.gestures.Orientation.Vertical
            if (r0 != r3) goto L1a
            goto L18
        L33:
            boolean r0 = androidx.compose.ui.layout.BeyondBoundsLayout.LayoutDirection.m475equalsimpl0(r6, r1)
            if (r0 == 0) goto L3b
            r0 = r1
            goto L40
        L3b:
            r0 = 2
            boolean r0 = androidx.compose.ui.layout.BeyondBoundsLayout.LayoutDirection.m475equalsimpl0(r6, r0)
        L40:
            if (r0 == 0) goto L5f
            goto L1a
        L43:
            if (r0 == 0) goto L46
            return r2
        L46:
            boolean r6 = r4.m132isForward4vf7U8o(r6)
            if (r6 == 0) goto L5a
            int r5 = r5.end
            androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsState r4 = r4.state
            int r4 = r4.getItemCount()
            int r4 = r4 - r1
            if (r5 >= r4) goto L58
            goto L5e
        L58:
            r1 = r2
            goto L5e
        L5a:
            int r4 = r5.start
            if (r4 <= 0) goto L58
        L5e:
            return r1
        L5f:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "Lazy list does not support beyond bounds layout for the specified direction"
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsModifierNode.m131hasMoreContentFR3nfPY(androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsInfo$Interval, int):boolean");
    }

    /* renamed from: isForward-4vf7U8o, reason: not valid java name */
    public final boolean m132isForward4vf7U8o(int i) {
        if (!BeyondBoundsLayout.LayoutDirection.m475equalsimpl0(i, 1)) {
            if (BeyondBoundsLayout.LayoutDirection.m475equalsimpl0(i, 2)) {
                return true;
            }
            if (BeyondBoundsLayout.LayoutDirection.m475equalsimpl0(i, 5)) {
                return this.reverseLayout;
            }
            if (BeyondBoundsLayout.LayoutDirection.m475equalsimpl0(i, 6)) {
                if (!this.reverseLayout) {
                    return true;
                }
            } else if (BeyondBoundsLayout.LayoutDirection.m475equalsimpl0(i, 3)) {
                int ordinal = this.layoutDirection.ordinal();
                if (ordinal == 0) {
                    return this.reverseLayout;
                }
                if (ordinal != 1) {
                    throw new NoWhenBranchMatchedException();
                }
                if (!this.reverseLayout) {
                    return true;
                }
            } else {
                if (!BeyondBoundsLayout.LayoutDirection.m475equalsimpl0(i, 4)) {
                    throw new IllegalStateException("Lazy list does not support beyond bounds layout for the specified direction");
                }
                int ordinal2 = this.layoutDirection.ordinal();
                if (ordinal2 != 0) {
                    if (ordinal2 == 1) {
                        return this.reverseLayout;
                    }
                    throw new NoWhenBranchMatchedException();
                }
                if (!this.reverseLayout) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* renamed from: measure-3p2s80s */
    public final MeasureResult mo6measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        MeasureResult layout$1;
        final Placeable mo479measureBRTryo0 = measurable.mo479measureBRTryo0(j);
        layout$1 = measureScope.layout$1(mo479measureBRTryo0.width, mo479measureBRTryo0.height, MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsModifierNode$measure$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ((Placeable.PlacementScope) obj).place(Placeable.this, 0, 0, 0.0f);
                return Unit.INSTANCE;
            }
        });
        return layout$1;
    }
}
