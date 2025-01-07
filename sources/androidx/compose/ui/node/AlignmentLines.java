package androidx.compose.ui.node;

import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.AlignmentLineKt;
import androidx.compose.ui.layout.HorizontalAlignmentLine;
import androidx.compose.ui.layout.Placeable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class AlignmentLines {
    public final Placeable alignmentLinesOwner;
    public boolean previousUsedDuringParentLayout;
    public AlignmentLinesOwner queryOwner;
    public boolean usedByModifierLayout;
    public boolean usedByModifierMeasurement;
    public boolean usedDuringParentLayout;
    public boolean usedDuringParentMeasurement;
    public boolean dirty = true;
    public final Map alignmentLineMap = new HashMap();

    /* JADX WARN: Multi-variable type inference failed */
    public AlignmentLines(AlignmentLinesOwner alignmentLinesOwner) {
        this.alignmentLinesOwner = (Placeable) alignmentLinesOwner;
    }

    /* JADX WARN: Type inference failed for: r2v3, types: [androidx.compose.ui.layout.Placeable, androidx.compose.ui.node.AlignmentLinesOwner] */
    public static final void access$addAlignmentLine(AlignmentLines alignmentLines, AlignmentLine alignmentLine, int i, NodeCoordinator nodeCoordinator) {
        long j;
        alignmentLines.getClass();
        float f = i;
        long floatToRawIntBits = Float.floatToRawIntBits(f) << 32;
        long floatToRawIntBits2 = Float.floatToRawIntBits(f) & 4294967295L;
        loop0: while (true) {
            j = floatToRawIntBits | floatToRawIntBits2;
            do {
                j = alignmentLines.mo502calculatePositionInParentR5De75A(nodeCoordinator, j);
                nodeCoordinator = nodeCoordinator.wrappedBy;
                Intrinsics.checkNotNull(nodeCoordinator);
                if (nodeCoordinator.equals(alignmentLines.alignmentLinesOwner.getInnerCoordinator())) {
                    break loop0;
                }
            } while (!alignmentLines.getAlignmentLinesMap(nodeCoordinator).containsKey(alignmentLine));
            float positionFor = alignmentLines.getPositionFor(nodeCoordinator, alignmentLine);
            long floatToRawIntBits3 = Float.floatToRawIntBits(positionFor);
            long floatToRawIntBits4 = Float.floatToRawIntBits(positionFor);
            floatToRawIntBits = floatToRawIntBits3 << 32;
            floatToRawIntBits2 = floatToRawIntBits4 & 4294967295L;
        }
        int round = Math.round(alignmentLine instanceof HorizontalAlignmentLine ? Float.intBitsToFloat((int) (j & 4294967295L)) : Float.intBitsToFloat((int) (j >> 32)));
        Map map = alignmentLines.alignmentLineMap;
        if (map.containsKey(alignmentLine)) {
            int intValue = ((Number) MapsKt.getValue(alignmentLine, alignmentLines.alignmentLineMap)).intValue();
            HorizontalAlignmentLine horizontalAlignmentLine = AlignmentLineKt.FirstBaseline;
            round = ((Number) alignmentLine.merger.invoke(Integer.valueOf(intValue), Integer.valueOf(round))).intValue();
        }
        map.put(alignmentLine, Integer.valueOf(round));
    }

    /* renamed from: calculatePositionInParent-R5De75A, reason: not valid java name */
    public abstract long mo502calculatePositionInParentR5De75A(NodeCoordinator nodeCoordinator, long j);

    public abstract Map getAlignmentLinesMap(NodeCoordinator nodeCoordinator);

    public abstract int getPositionFor(NodeCoordinator nodeCoordinator, AlignmentLine alignmentLine);

    public final boolean getQueried$ui_release() {
        return this.usedDuringParentMeasurement || this.previousUsedDuringParentLayout || this.usedByModifierMeasurement || this.usedByModifierLayout;
    }

    public final boolean getRequired$ui_release() {
        recalculateQueryOwner();
        return this.queryOwner != null;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [androidx.compose.ui.layout.Placeable, androidx.compose.ui.node.AlignmentLinesOwner] */
    public final void onAlignmentsChanged() {
        this.dirty = true;
        ?? r0 = this.alignmentLinesOwner;
        AlignmentLinesOwner parentAlignmentLinesOwner = r0.getParentAlignmentLinesOwner();
        if (parentAlignmentLinesOwner == null) {
            return;
        }
        if (this.usedDuringParentMeasurement) {
            parentAlignmentLinesOwner.requestMeasure();
        } else if (this.previousUsedDuringParentLayout || this.usedDuringParentLayout) {
            parentAlignmentLinesOwner.requestLayout();
        }
        if (this.usedByModifierMeasurement) {
            r0.requestMeasure();
        }
        if (this.usedByModifierLayout) {
            r0.requestLayout();
        }
        parentAlignmentLinesOwner.getAlignmentLines().onAlignmentsChanged();
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [androidx.compose.ui.layout.Placeable, androidx.compose.ui.node.AlignmentLinesOwner] */
    public final void recalculate() {
        this.alignmentLineMap.clear();
        Function1 function1 = new Function1() { // from class: androidx.compose.ui.node.AlignmentLines$recalculate$1
            {
                super(1);
            }

            /* JADX WARN: Type inference failed for: r0v9, types: [androidx.compose.ui.layout.Placeable, androidx.compose.ui.node.AlignmentLinesOwner] */
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                AlignmentLinesOwner alignmentLinesOwner = (AlignmentLinesOwner) obj;
                if (alignmentLinesOwner.isPlaced()) {
                    if (alignmentLinesOwner.getAlignmentLines().dirty) {
                        alignmentLinesOwner.layoutChildren();
                    }
                    Map map = alignmentLinesOwner.getAlignmentLines().alignmentLineMap;
                    AlignmentLines alignmentLines = AlignmentLines.this;
                    for (Map.Entry entry : ((HashMap) map).entrySet()) {
                        AlignmentLines.access$addAlignmentLine(alignmentLines, (AlignmentLine) entry.getKey(), ((Number) entry.getValue()).intValue(), alignmentLinesOwner.getInnerCoordinator());
                    }
                    NodeCoordinator nodeCoordinator = alignmentLinesOwner.getInnerCoordinator().wrappedBy;
                    Intrinsics.checkNotNull(nodeCoordinator);
                    while (!nodeCoordinator.equals(AlignmentLines.this.alignmentLinesOwner.getInnerCoordinator())) {
                        Set<AlignmentLine> keySet = AlignmentLines.this.getAlignmentLinesMap(nodeCoordinator).keySet();
                        AlignmentLines alignmentLines2 = AlignmentLines.this;
                        for (AlignmentLine alignmentLine : keySet) {
                            AlignmentLines.access$addAlignmentLine(alignmentLines2, alignmentLine, alignmentLines2.getPositionFor(nodeCoordinator, alignmentLine), nodeCoordinator);
                        }
                        nodeCoordinator = nodeCoordinator.wrappedBy;
                        Intrinsics.checkNotNull(nodeCoordinator);
                    }
                }
                return Unit.INSTANCE;
            }
        };
        ?? r1 = this.alignmentLinesOwner;
        r1.forEachChildAlignmentLinesOwner(function1);
        this.alignmentLineMap.putAll(getAlignmentLinesMap(r1.getInnerCoordinator()));
        this.dirty = false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0020, code lost:
    
        if (r0 != false) goto L29;
     */
    /* JADX WARN: Type inference failed for: r1v0, types: [androidx.compose.ui.layout.Placeable, androidx.compose.ui.node.AlignmentLinesOwner] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void recalculateQueryOwner() {
        /*
            r2 = this;
            boolean r0 = r2.getQueried$ui_release()
            androidx.compose.ui.layout.Placeable r1 = r2.alignmentLinesOwner
            if (r0 == 0) goto L9
            goto L51
        L9:
            androidx.compose.ui.node.AlignmentLinesOwner r0 = r1.getParentAlignmentLinesOwner()
            if (r0 != 0) goto L10
            return
        L10:
            androidx.compose.ui.node.AlignmentLines r0 = r0.getAlignmentLines()
            androidx.compose.ui.node.AlignmentLinesOwner r1 = r0.queryOwner
            if (r1 == 0) goto L23
            androidx.compose.ui.node.AlignmentLines r0 = r1.getAlignmentLines()
            boolean r0 = r0.getQueried$ui_release()
            if (r0 == 0) goto L23
            goto L51
        L23:
            androidx.compose.ui.node.AlignmentLinesOwner r0 = r2.queryOwner
            if (r0 == 0) goto L53
            androidx.compose.ui.node.AlignmentLines r1 = r0.getAlignmentLines()
            boolean r1 = r1.getQueried$ui_release()
            if (r1 == 0) goto L32
            goto L53
        L32:
            androidx.compose.ui.node.AlignmentLinesOwner r1 = r0.getParentAlignmentLinesOwner()
            if (r1 == 0) goto L41
            androidx.compose.ui.node.AlignmentLines r1 = r1.getAlignmentLines()
            if (r1 == 0) goto L41
            r1.recalculateQueryOwner()
        L41:
            androidx.compose.ui.node.AlignmentLinesOwner r0 = r0.getParentAlignmentLinesOwner()
            if (r0 == 0) goto L50
            androidx.compose.ui.node.AlignmentLines r0 = r0.getAlignmentLines()
            if (r0 == 0) goto L50
            androidx.compose.ui.node.AlignmentLinesOwner r1 = r0.queryOwner
            goto L51
        L50:
            r1 = 0
        L51:
            r2.queryOwner = r1
        L53:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.AlignmentLines.recalculateQueryOwner():void");
    }
}
