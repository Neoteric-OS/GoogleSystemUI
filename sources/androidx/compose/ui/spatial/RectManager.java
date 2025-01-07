package androidx.compose.ui.spatial;

import androidx.collection.MutableObjectList;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.geometry.MutableRect;
import androidx.compose.ui.graphics.Matrix;
import androidx.compose.ui.graphics.MatrixKt;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.LayoutNodeLayoutDelegate;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.node.OwnedLayer;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class RectManager {
    public final MutableRect cachedRect;
    public final MutableObjectList callbacks;
    public boolean isDirty;
    public boolean isFragmented;
    public final RectList rects;

    public RectManager() {
        RectList rectList = new RectList();
        rectList.items = new long[192];
        rectList.stack = new long[192];
        this.rects = rectList;
        this.callbacks = new MutableObjectList();
        this.cachedRect = new MutableRect();
    }

    /* renamed from: outerToInnerOffset-Bjo55l4, reason: not valid java name */
    public static long m578outerToInnerOffsetBjo55l4(LayoutNode layoutNode) {
        float[] mo543getUnderlyingMatrixsQKQjiQ;
        int m581access$analyzeComponents58bKbWc;
        NodeChain nodeChain = layoutNode.nodes;
        NodeCoordinator nodeCoordinator = nodeChain.outerCoordinator;
        NodeCoordinator nodeCoordinator2 = nodeChain.innerCoordinator;
        long j = 0;
        while (nodeCoordinator2 != null && nodeCoordinator2 != nodeCoordinator) {
            OwnedLayer ownedLayer = nodeCoordinator2.layer;
            j = IntOffsetKt.m678plusNvtHpc(j, nodeCoordinator2.position);
            nodeCoordinator2 = nodeCoordinator2.wrappedBy;
            if (ownedLayer != null && (m581access$analyzeComponents58bKbWc = RectManagerKt.m581access$analyzeComponents58bKbWc((mo543getUnderlyingMatrixsQKQjiQ = ownedLayer.mo543getUnderlyingMatrixsQKQjiQ()))) != 3) {
                if ((m581access$analyzeComponents58bKbWc & 2) == 0) {
                    return 9223372034707292159L;
                }
                j = Matrix.m380mapMKHz9U(j, mo543getUnderlyingMatrixsQKQjiQ);
            }
        }
        return IntOffsetKt.m679roundk4lQ0M(j);
    }

    public final void dispatchCallbacks() {
        if (this.isDirty) {
            this.isDirty = false;
            if (this.isFragmented) {
                this.isFragmented = false;
                RectList rectList = this.rects;
                long[] jArr = rectList.items;
                int i = rectList.itemsSize;
                long[] jArr2 = rectList.stack;
                int i2 = 0;
                for (int i3 = 0; i3 < jArr.length - 2 && i2 < jArr2.length - 2 && i3 < i; i3 += 3) {
                    int i4 = i3 + 2;
                    if (jArr[i4] != 4611686018427387903L) {
                        jArr2[i2] = jArr[i3];
                        jArr2[i2 + 1] = jArr[i3 + 1];
                        jArr2[i2 + 2] = jArr[i4];
                        i2 += 3;
                    }
                }
                rectList.itemsSize = i2;
                rectList.items = jArr2;
                rectList.stack = jArr;
            }
            MutableObjectList mutableObjectList = this.callbacks;
            Object[] objArr = mutableObjectList.content;
            int i5 = mutableObjectList._size;
            for (int i6 = 0; i6 < i5; i6++) {
                ((Function0) objArr[i6]).invoke();
            }
        }
    }

    /* renamed from: insertOrUpdateTransformedNode-70tqf50, reason: not valid java name */
    public final void m579insertOrUpdateTransformedNode70tqf50(LayoutNode layoutNode, long j, boolean z) {
        NodeCoordinator nodeCoordinator = layoutNode.nodes.outerCoordinator;
        LayoutNodeLayoutDelegate.MeasurePassDelegate measurePassDelegate = layoutNode.layoutDelegate.measurePassDelegate;
        int i = (int) (j >> 32);
        float f = i;
        int i2 = (int) (j & 4294967295L);
        float measuredWidth = i + measurePassDelegate.getMeasuredWidth();
        float measuredHeight = i2 + measurePassDelegate.getMeasuredHeight();
        MutableRect mutableRect = this.cachedRect;
        mutableRect.left = f;
        mutableRect.top = i2;
        mutableRect.right = measuredWidth;
        mutableRect.bottom = measuredHeight;
        while (nodeCoordinator != null) {
            OwnedLayer ownedLayer = nodeCoordinator.layer;
            long j2 = nodeCoordinator.position;
            long floatToRawIntBits = (Float.floatToRawIntBits((int) (j2 >> 32)) << 32) | (Float.floatToRawIntBits((int) (j2 & 4294967295L)) & 4294967295L);
            float intBitsToFloat = Float.intBitsToFloat((int) (floatToRawIntBits >> 32));
            float intBitsToFloat2 = Float.intBitsToFloat((int) (floatToRawIntBits & 4294967295L));
            mutableRect.left += intBitsToFloat;
            mutableRect.top += intBitsToFloat2;
            mutableRect.right += intBitsToFloat;
            mutableRect.bottom += intBitsToFloat2;
            nodeCoordinator = nodeCoordinator.wrappedBy;
            if (ownedLayer != null) {
                float[] mo543getUnderlyingMatrixsQKQjiQ = ownedLayer.mo543getUnderlyingMatrixsQKQjiQ();
                if (!MatrixKt.m385isIdentity58bKbWc(mo543getUnderlyingMatrixsQKQjiQ)) {
                    Matrix.m381mapimpl(mo543getUnderlyingMatrixsQKQjiQ, mutableRect);
                }
            }
        }
        int i3 = (int) mutableRect.left;
        int i4 = (int) mutableRect.top;
        int i5 = (int) mutableRect.right;
        int i6 = (int) mutableRect.bottom;
        int i7 = layoutNode.semanticsId;
        RectList rectList = this.rects;
        if (!z) {
            int i8 = i7 & 67108863;
            long[] jArr = rectList.items;
            int i9 = rectList.itemsSize;
            for (int i10 = 0; i10 < jArr.length - 2 && i10 < i9; i10 += 3) {
                if ((((int) jArr[i10 + 2]) & 67108863) == i8) {
                    jArr[i10] = (i3 << 32) | (i4 & 4294967295L);
                    jArr[i10 + 1] = (i5 << 32) | (i6 & 4294967295L);
                    break;
                }
            }
        }
        LayoutNode parent$ui_release = layoutNode.getParent$ui_release();
        RectList.insert$default(rectList, i7, i3, i4, i5, i6, parent$ui_release != null ? parent$ui_release.semanticsId : -1);
        this.isDirty = true;
    }

    public final void insertOrUpdateTransformedNodeSubhierarchy(LayoutNode layoutNode) {
        MutableVector mutableVector = layoutNode.get_children$ui_release();
        int i = mutableVector.size;
        if (i > 0) {
            Object[] objArr = mutableVector.content;
            int i2 = 0;
            do {
                LayoutNode layoutNode2 = (LayoutNode) objArr[i2];
                m579insertOrUpdateTransformedNode70tqf50(layoutNode2, layoutNode2.nodes.outerCoordinator.position, false);
                insertOrUpdateTransformedNodeSubhierarchy(layoutNode2);
                i2++;
            } while (i2 < i);
        }
    }

    public final void onLayoutLayerPositionalPropertiesChanged(LayoutNode layoutNode) {
        long m578outerToInnerOffsetBjo55l4 = m578outerToInnerOffsetBjo55l4(layoutNode);
        if (IntOffset.m674equalsimpl0(m578outerToInnerOffsetBjo55l4, 9223372034707292159L)) {
            insertOrUpdateTransformedNodeSubhierarchy(layoutNode);
            return;
        }
        layoutNode.outerToInnerOffset = m578outerToInnerOffsetBjo55l4;
        layoutNode.outerToInnerOffsetDirty = false;
        MutableVector mutableVector = layoutNode.get_children$ui_release();
        int i = mutableVector.size;
        if (i > 0) {
            Object[] objArr = mutableVector.content;
            int i2 = 0;
            do {
                LayoutNode layoutNode2 = (LayoutNode) objArr[i2];
                m580onLayoutPositionChanged70tqf50(layoutNode2, layoutNode2.nodes.outerCoordinator.position, false);
                i2++;
            } while (i2 < i);
        }
    }

    /* renamed from: onLayoutPositionChanged-70tqf50, reason: not valid java name */
    public final void m580onLayoutPositionChanged70tqf50(LayoutNode layoutNode, long j, boolean z) {
        long j2;
        int i;
        long j3;
        boolean z2;
        int i2;
        int i3;
        char c;
        int i4;
        long j4;
        float[] mo543getUnderlyingMatrixsQKQjiQ;
        int m581access$analyzeComponents58bKbWc;
        LayoutNodeLayoutDelegate.MeasurePassDelegate measurePassDelegate = layoutNode.layoutDelegate.measurePassDelegate;
        int measuredWidth = measurePassDelegate.getMeasuredWidth();
        int measuredHeight = measurePassDelegate.getMeasuredHeight();
        LayoutNode parent$ui_release = layoutNode.getParent$ui_release();
        long j5 = layoutNode.offsetFromRoot;
        long j6 = layoutNode.lastSize;
        int i5 = (int) (j6 >> 32);
        int i6 = (int) (j6 & 4294967295L);
        if (parent$ui_release != null) {
            boolean z3 = parent$ui_release.outerToInnerOffsetDirty;
            i = i5;
            long j7 = parent$ui_release.offsetFromRoot;
            j2 = j5;
            long j8 = parent$ui_release.outerToInnerOffset;
            if (!IntOffset.m674equalsimpl0(j7, 9223372034707292159L)) {
                if (z3) {
                    j8 = m578outerToInnerOffsetBjo55l4(parent$ui_release);
                    parent$ui_release.outerToInnerOffset = j8;
                    parent$ui_release.outerToInnerOffsetDirty = false;
                }
                z2 = IntOffset.m674equalsimpl0(j8, 9223372034707292159L);
                j3 = IntOffset.m676plusqkQi6aY(IntOffset.m676plusqkQi6aY(j7, j8), j);
                if (!z2 || IntOffset.m674equalsimpl0(j3, 9223372034707292159L)) {
                    m579insertOrUpdateTransformedNode70tqf50(layoutNode, j, z);
                }
                layoutNode.offsetFromRoot = j3;
                layoutNode.lastSize = (measuredWidth << 32) | (measuredHeight & 4294967295L);
                int i7 = (int) (j3 >> 32);
                int i8 = (int) (j3 & 4294967295L);
                int i9 = i7 + measuredWidth;
                int i10 = i8 + measuredHeight;
                if (!z && IntOffset.m674equalsimpl0(j3, j2) && i == measuredWidth && i6 == measuredHeight) {
                    return;
                }
                int i11 = layoutNode.semanticsId;
                RectList rectList = this.rects;
                if (!z) {
                    int i12 = i11 & 67108863;
                    long[] jArr = rectList.items;
                    int i13 = rectList.itemsSize;
                    int i14 = 0;
                    while (i14 < jArr.length - 2 && i14 < i13) {
                        int i15 = i8;
                        long j9 = jArr[i14 + 2];
                        if ((((int) j9) & 67108863) == i12) {
                            long j10 = jArr[i14];
                            jArr[i14] = (i7 << 32) | (i15 & 4294967295L);
                            jArr[i14 + 1] = (i9 << 32) | (i10 & 4294967295L);
                            if ((i7 - ((int) (j10 >> 32)) != 0) | (i15 - ((int) j10) != 0)) {
                                long j11 = -4503599560261633L;
                                long[] jArr2 = rectList.items;
                                long[] jArr3 = rectList.stack;
                                int i16 = rectList.itemsSize / 3;
                                jArr3[0] = (j9 & (-4503599560261633L)) | (((i14 + 3) & 67108863) << 26);
                                for (int i17 = 1; i17 > 0; i17 = i2) {
                                    int i18 = i17 - 1;
                                    long j12 = jArr3[i18];
                                    int i19 = ((int) j12) & 67108863;
                                    int i20 = ((int) (j12 >> 26)) & 67108863;
                                    char c2 = 1023;
                                    int i21 = ((int) (j12 >> 52)) & 1023;
                                    int i22 = i21 == 1023 ? i16 : i21 + i20;
                                    if (i20 < 0) {
                                        break;
                                    }
                                    i2 = i18;
                                    while (i20 < jArr2.length - 2 && i20 < i22) {
                                        long j13 = jArr2[i20 + 2];
                                        if ((((int) (j13 >> 26)) & 67108863) == i19) {
                                            long j14 = jArr2[i20];
                                            int i23 = i20 + 1;
                                            i4 = i2;
                                            long j15 = jArr2[i23];
                                            i3 = i19;
                                            jArr2[i20] = ((((int) j14) + r9) & 4294967295L) | ((((int) (j14 >> 32)) + r4) << 32);
                                            jArr2[i23] = ((((int) j15) + r9) & 4294967295L) | ((((int) (j15 >> 32)) + r4) << 32);
                                            c = 1023;
                                            if ((((int) (j13 >> 52)) & 1023) > 0) {
                                                i2 = i4 + 1;
                                                j4 = -4503599560261633L;
                                                jArr3[i4] = (j13 & (-4503599560261633L)) | (((i20 + 3) & 67108863) << 26);
                                                i20 += 3;
                                                i19 = i3;
                                                c2 = c;
                                                j11 = j4;
                                            } else {
                                                j4 = -4503599560261633L;
                                            }
                                        } else {
                                            i3 = i19;
                                            c = c2;
                                            i4 = i2;
                                            j4 = -4503599560261633L;
                                        }
                                        i2 = i4;
                                        i20 += 3;
                                        i19 = i3;
                                        c2 = c;
                                        j11 = j4;
                                    }
                                    j11 = j11;
                                }
                            }
                            this.isDirty = true;
                            return;
                        }
                        i14 += 3;
                        i8 = i15;
                    }
                }
                int i24 = i8;
                LayoutNode parent$ui_release2 = layoutNode.getParent$ui_release();
                RectList.insert$default(rectList, i11, i7, i24, i9, i10, parent$ui_release2 != null ? parent$ui_release2.semanticsId : -1);
                this.isDirty = true;
                return;
            }
            NodeCoordinator nodeCoordinator = layoutNode.nodes.outerCoordinator;
            long j16 = 0;
            while (true) {
                if (nodeCoordinator == null) {
                    j3 = IntOffsetKt.m679roundk4lQ0M(j16);
                    break;
                }
                OwnedLayer ownedLayer = nodeCoordinator.layer;
                j16 = IntOffsetKt.m678plusNvtHpc(j16, nodeCoordinator.position);
                nodeCoordinator = nodeCoordinator.wrappedBy;
                if (ownedLayer != null && (m581access$analyzeComponents58bKbWc = RectManagerKt.m581access$analyzeComponents58bKbWc((mo543getUnderlyingMatrixsQKQjiQ = ownedLayer.mo543getUnderlyingMatrixsQKQjiQ()))) != 3) {
                    if ((m581access$analyzeComponents58bKbWc & 2) == 0) {
                        j3 = 9223372034707292159L;
                        break;
                    }
                    j16 = Matrix.m380mapMKHz9U(j16, mo543getUnderlyingMatrixsQKQjiQ);
                }
            }
        } else {
            j2 = j5;
            i = i5;
            j3 = j;
        }
        z2 = false;
        if (z2) {
        }
        m579insertOrUpdateTransformedNode70tqf50(layoutNode, j, z);
    }

    public final void remove(LayoutNode layoutNode) {
        int i = layoutNode.semanticsId & 67108863;
        RectList rectList = this.rects;
        long[] jArr = rectList.items;
        int i2 = rectList.itemsSize;
        int i3 = 0;
        while (true) {
            if (i3 >= jArr.length - 2 || i3 >= i2) {
                break;
            }
            int i4 = i3 + 2;
            if ((((int) jArr[i4]) & 67108863) == i) {
                jArr[i3] = -1;
                jArr[i3 + 1] = -1;
                jArr[i4] = 4611686018427387903L;
                break;
            }
            i3 += 3;
        }
        this.isDirty = true;
        this.isFragmented = true;
    }
}
