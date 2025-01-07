package androidx.compose.ui.node;

import androidx.collection.MutableObjectFloatMap;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.PlaceableKt;
import androidx.compose.ui.layout.VerticalAlignmentLine;
import androidx.compose.ui.node.LayoutNodeLayoutDelegate;
import com.android.systemui.plugins.DarkIconDispatcher;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class LookaheadCapablePlaceable extends Placeable implements MeasureScopeWithLayoutNode, MotionReferencePlacementDelegate {
    public static final Function1 onCommitAffectingRuler = new Function1() { // from class: androidx.compose.ui.node.LookaheadCapablePlaceable$Companion$onCommitAffectingRuler$1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            PlaceableResult placeableResult = (PlaceableResult) obj;
            if (placeableResult.isValidOwnerScope()) {
                placeableResult.placeable.captureRulers(placeableResult);
            }
            return Unit.INSTANCE;
        }
    };
    public boolean isPlacedUnderMotionFrameOfReference;
    public boolean isPlacingForAlignment;
    public boolean isShallowPlacing;
    public final Placeable.PlacementScope placementScope = PlaceableKt.PlacementScope(this);
    public MutableObjectFloatMap rulerValues;
    public MutableObjectFloatMap rulerValuesCache;

    public static void invalidateAlignmentLinesFromPositionChange(NodeCoordinator nodeCoordinator) {
        LayoutNodeAlignmentLines layoutNodeAlignmentLines;
        NodeCoordinator nodeCoordinator2 = nodeCoordinator.wrapped;
        LayoutNode layoutNode = nodeCoordinator2 != null ? nodeCoordinator2.layoutNode : null;
        LayoutNode layoutNode2 = nodeCoordinator.layoutNode;
        if (!Intrinsics.areEqual(layoutNode, layoutNode2)) {
            layoutNode2.layoutDelegate.measurePassDelegate.alignmentLines.onAlignmentsChanged();
            return;
        }
        AlignmentLinesOwner parentAlignmentLinesOwner = layoutNode2.layoutDelegate.measurePassDelegate.getParentAlignmentLinesOwner();
        if (parentAlignmentLinesOwner == null || (layoutNodeAlignmentLines = ((LayoutNodeLayoutDelegate.MeasurePassDelegate) parentAlignmentLinesOwner).alignmentLines) == null) {
            return;
        }
        layoutNodeAlignmentLines.onAlignmentsChanged();
    }

    public abstract int calculateAlignmentLine(AlignmentLine alignmentLine);

    /* JADX WARN: Code restructure failed: missing block: B:101:0x0330, code lost:
    
        r11 = r11 + 1;
        r3 = r15;
        r8 = r28;
        r9 = r36;
        r2 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x02f1, code lost:
    
        r13 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x032b, code lost:
    
        r15 = r3;
        r28 = r8;
        r36 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x011f, code lost:
    
        r41 = r0;
        r0 = r2;
        r48 = r3;
        r45 = r7;
        r36 = r9;
        r38 = r10;
        r39 = r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00f3, code lost:
    
        r35 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0101, code lost:
    
        if (((r5 & ((~r5) << 6)) & (-9187201950435737472L)) == 0) goto L91;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0103, code lost:
    
        r4 = r2.findFirstAvailableSlot(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0109, code lost:
    
        if (r2.growthLimit != 0) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x011d, code lost:
    
        if (((r2.metadata[r4 >> 3] >> ((r4 & 7) << 3)) & 255) != 254) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x012e, code lost:
    
        r4 = r2._capacity;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0132, code lost:
    
        if (r4 <= 8) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0144, code lost:
    
        if (java.lang.Long.compareUnsigned(r2._size * 32, r4 * 25) > 0) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0146, code lost:
    
        r4 = r2.metadata;
        r5 = r2._capacity;
        r6 = r2.keys;
        r13 = r2.values;
        r14 = (r5 + 7) >> 3;
        r15 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0153, code lost:
    
        if (r15 >= r14) goto L152;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0155, code lost:
    
        r38 = r10;
        r10 = r4[r15] & (-9187201950435737472L);
        r4[r15] = ((~r10) + (r10 >>> 7)) & (-72340172838076674L);
        r15 = r15 + 1;
        r10 = r38;
        r11 = r11;
        r0 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0179, code lost:
    
        r41 = r0;
        r38 = r10;
        r39 = r11;
        r0 = r4.length;
        r1 = r0 - 1;
        r0 = r0 - 2;
        r4[r0] = (r4[r0] & 72057594037927935L) | (-72057594037927936L);
        r4[r1] = r4[0];
        r0 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0198, code lost:
    
        if (r0 == r5) goto L154;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x019a, code lost:
    
        r1 = r0 >> 3;
        r12 = (r0 & 7) << 3;
        r10 = (r4[r1] >> r12) & 255;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x01ab, code lost:
    
        if (r10 != 128) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x01b4, code lost:
    
        if (r10 == 254) goto L153;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x01b7, code lost:
    
        r10 = r6[r0];
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x01b9, code lost:
    
        if (r10 == null) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x01bb, code lost:
    
        r10 = r10.hashCode();
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x01c1, code lost:
    
        r10 = r10 * (-862048943);
        r11 = (r10 ^ (r10 << 16)) >>> 7;
        r23 = r2.findFirstAvailableSlot(r11);
        r11 = r11 & r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x01dc, code lost:
    
        if ((((r23 - r11) & r5) / 8) != (((r0 - r11) & r5) / 8)) goto L157;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x020d, code lost:
    
        r45 = r7;
        r46 = r8;
        r7 = r23 >> 3;
        r14 = r4[r7];
        r8 = (r23 & 7) << 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0223, code lost:
    
        if (((r14 >> r8) & 255) != 128) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0225, code lost:
    
        r47 = r2;
        r48 = r3;
        r4[r7] = ((~(255 << r8)) & r14) | ((r10 & 127) << r8);
        r4[r1] = (r4[r1] & (~(255 << r12))) | (128 << r12);
        r6[r23] = r6[r0];
        r6[r0] = null;
        r13[r23] = r13[r0];
        r13[r0] = 0.0f;
        r3 = r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0275, code lost:
    
        r4[r4.length - 1] = (r4[0] & 72057594037927935L) | Long.MIN_VALUE;
        r0 = r0 + 1;
        r13 = r3;
        r7 = r45;
        r8 = r46;
        r2 = r47;
        r3 = r48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0251, code lost:
    
        r47 = r2;
        r48 = r3;
        r3 = r13;
        r4[r7] = ((r10 & 127) << r8) | ((~(255 << r8)) & r14);
        r1 = r6[r23];
        r6[r23] = r6[r0];
        r6[r0] = r1;
        r1 = r3[r23];
        r3[r23] = r3[r0];
        r3[r0] = r1;
        r0 = r0 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x01de, code lost:
    
        r4[r1] = ((~(255 << r12)) & r4[r1]) | ((r10 & 127) << r12);
        r4[r4.length - 1] = (r4[0] & 72057594037927935L) | Long.MIN_VALUE;
        r0 = r0 + 1;
        r7 = r7;
        r8 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x01c0, code lost:
    
        r10 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x01ad, code lost:
    
        r0 = r0 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x0293, code lost:
    
        r0 = r2;
        r48 = r3;
        r45 = r7;
        r46 = r8;
        r0.growthLimit = androidx.collection.ScatterMapKt.loadedCapacity(r0._capacity) - r0._size;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x02a6, code lost:
    
        r36 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0339, code lost:
    
        r4 = r0.findFirstAvailableSlot(r46);
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x033d, code lost:
    
        r0._size++;
        r1 = r0.growthLimit;
        r2 = r0.metadata;
        r3 = r4 >> 3;
        r5 = r2[r3];
        r7 = (r4 & 7) << 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x0358, code lost:
    
        if (((r5 >> r7) & 255) != 128) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x035b, code lost:
    
        r31 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x035d, code lost:
    
        r0.growthLimit = r1 - r31;
        r1 = r0._capacity;
        r5 = (r5 & (~(255 << r7))) | (r41 << r7);
        r2[r3] = r5;
        r2[(((r4 - 7) & r1) + (r1 & 7)) >> 3] = r5;
        r1 = ~r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x02ac, code lost:
    
        r41 = r0;
        r0 = r2;
        r48 = r3;
        r45 = r7;
        r46 = r8;
        r38 = r10;
        r39 = r11;
        r2 = 0;
        r1 = androidx.collection.ScatterMapKt.nextCapacity(r0._capacity);
        r3 = r0.metadata;
        r4 = r0.keys;
        r5 = r0.values;
        r6 = r0._capacity;
        r0.initializeStorage(r1);
        r1 = r0.metadata;
        r7 = r0.keys;
        r8 = r0.values;
        r10 = r0._capacity;
        r11 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x02d4, code lost:
    
        if (r11 >= r6) goto L163;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x02e6, code lost:
    
        if (((r3[r11 >> 3] >> ((r11 & 7) << 3)) & 255) >= 128) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x02e8, code lost:
    
        r12 = r4[r11];
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x02ea, code lost:
    
        if (r12 == null) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x02ec, code lost:
    
        r13 = r12.hashCode();
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x02f2, code lost:
    
        r13 = r13 * (-862048943);
        r13 = r13 ^ (r13 << 16);
        r14 = r0.findFirstAvailableSlot(r13 >>> 7);
        r15 = r3;
        r2 = r13 & 127;
        r13 = r14 >> 3;
        r23 = (r14 & 7) << 3;
        r28 = r8;
        r36 = r9;
        r2 = (r2 << r23) | (r1[r13] & (~(255 << r23)));
        r1[r13] = r2;
        r1[(((r14 - 7) & r10) + (r10 & 7)) >> 3] = r2;
        r7[r14] = r12;
        r28[r14] = r5[r11];
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void captureRulers(final androidx.compose.ui.node.PlaceableResult r50) {
        /*
            Method dump skipped, instructions count: 1213
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.LookaheadCapablePlaceable.captureRulers(androidx.compose.ui.node.PlaceableResult):void");
    }

    @Override // androidx.compose.ui.layout.Measured
    public final int get(AlignmentLine alignmentLine) {
        int calculateAlignmentLine;
        if (getHasMeasureResult() && (calculateAlignmentLine = calculateAlignmentLine(alignmentLine)) != Integer.MIN_VALUE) {
            return calculateAlignmentLine + ((int) (alignmentLine instanceof VerticalAlignmentLine ? this.apparentToRealOffset >> 32 : this.apparentToRealOffset & 4294967295L));
        }
        return Integer.MIN_VALUE;
    }

    public abstract LookaheadCapablePlaceable getChild();

    public abstract LayoutCoordinates getCoordinates();

    public abstract boolean getHasMeasureResult();

    @Override // androidx.compose.ui.node.MeasureScopeWithLayoutNode
    public abstract LayoutNode getLayoutNode();

    public abstract MeasureResult getMeasureResult$ui_release();

    public abstract LookaheadCapablePlaceable getParent();

    /* renamed from: getPosition-nOcc-ac, reason: not valid java name */
    public abstract long mo517getPositionnOccac();

    @Override // androidx.compose.ui.layout.IntrinsicMeasureScope
    public boolean isLookingAhead() {
        return false;
    }

    @Override // androidx.compose.ui.layout.MeasureScope
    public final MeasureResult layout(final int i, final int i2, final Map map, final Function1 function1) {
        if ((i & DarkIconDispatcher.DEFAULT_INVERSE_ICON_TINT) != 0 || ((-16777216) & i2) != 0) {
            InlineClassHelperKt.throwIllegalStateException("Size(" + i + " x " + i2 + ") is out of range. Each dimension must be between 0 and 16777215.");
        }
        return new MeasureResult() { // from class: androidx.compose.ui.node.LookaheadCapablePlaceable$layout$1
            @Override // androidx.compose.ui.layout.MeasureResult
            public final Map getAlignmentLines() {
                return map;
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public final int getHeight() {
                return i2;
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public final Function1 getRulers() {
                return null;
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public final int getWidth() {
                return i;
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public final void placeChildren() {
                function1.invoke(this.placementScope);
            }
        };
    }

    public abstract void replace$ui_release();

    @Override // androidx.compose.ui.node.MotionReferencePlacementDelegate
    public final void setPlacedUnderMotionFrameOfReference(boolean z) {
        this.isPlacedUnderMotionFrameOfReference = z;
    }
}
