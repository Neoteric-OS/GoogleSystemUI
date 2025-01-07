package androidx.compose.ui.layout;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.node.NodeCoordinator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class LayoutCoordinatesKt {
    public static final Rect boundsInParent(LayoutCoordinates layoutCoordinates) {
        LayoutCoordinates parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
        return parentLayoutCoordinates != null ? parentLayoutCoordinates.localBoundingBoxOf(layoutCoordinates, true) : new Rect(0.0f, 0.0f, (int) (layoutCoordinates.mo481getSizeYbymL2g() >> 32), (int) (layoutCoordinates.mo481getSizeYbymL2g() & 4294967295L));
    }

    public static final Rect boundsInWindow(LayoutCoordinates layoutCoordinates) {
        LayoutCoordinates findRootCoordinates = findRootCoordinates(layoutCoordinates);
        float mo481getSizeYbymL2g = (int) (findRootCoordinates.mo481getSizeYbymL2g() >> 32);
        float mo481getSizeYbymL2g2 = (int) (findRootCoordinates.mo481getSizeYbymL2g() & 4294967295L);
        Rect localBoundingBoxOf = findRootCoordinates(layoutCoordinates).localBoundingBoxOf(layoutCoordinates, true);
        float f = localBoundingBoxOf.left;
        if (f < 0.0f) {
            f = 0.0f;
        }
        if (f > mo481getSizeYbymL2g) {
            f = mo481getSizeYbymL2g;
        }
        float f2 = localBoundingBoxOf.top;
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        if (f2 > mo481getSizeYbymL2g2) {
            f2 = mo481getSizeYbymL2g2;
        }
        float f3 = localBoundingBoxOf.right;
        if (f3 < 0.0f) {
            f3 = 0.0f;
        }
        if (f3 <= mo481getSizeYbymL2g) {
            mo481getSizeYbymL2g = f3;
        }
        float f4 = localBoundingBoxOf.bottom;
        float f5 = f4 >= 0.0f ? f4 : 0.0f;
        if (f5 <= mo481getSizeYbymL2g2) {
            mo481getSizeYbymL2g2 = f5;
        }
        if (f == mo481getSizeYbymL2g || f2 == mo481getSizeYbymL2g2) {
            return Rect.Zero;
        }
        long mo486localToWindowMKHz9U = findRootCoordinates.mo486localToWindowMKHz9U((Float.floatToRawIntBits(f) << 32) | (Float.floatToRawIntBits(f2) & 4294967295L));
        long mo486localToWindowMKHz9U2 = findRootCoordinates.mo486localToWindowMKHz9U((Float.floatToRawIntBits(f2) & 4294967295L) | (Float.floatToRawIntBits(mo481getSizeYbymL2g) << 32));
        long mo486localToWindowMKHz9U3 = findRootCoordinates.mo486localToWindowMKHz9U((Float.floatToRawIntBits(mo481getSizeYbymL2g) << 32) | (Float.floatToRawIntBits(mo481getSizeYbymL2g2) & 4294967295L));
        long mo486localToWindowMKHz9U4 = findRootCoordinates.mo486localToWindowMKHz9U((Float.floatToRawIntBits(mo481getSizeYbymL2g2) & 4294967295L) | (Float.floatToRawIntBits(f) << 32));
        float intBitsToFloat = Float.intBitsToFloat((int) (mo486localToWindowMKHz9U >> 32));
        float intBitsToFloat2 = Float.intBitsToFloat((int) (mo486localToWindowMKHz9U2 >> 32));
        float intBitsToFloat3 = Float.intBitsToFloat((int) (mo486localToWindowMKHz9U4 >> 32));
        float intBitsToFloat4 = Float.intBitsToFloat((int) (mo486localToWindowMKHz9U3 >> 32));
        float min = Math.min(intBitsToFloat, Math.min(intBitsToFloat2, Math.min(intBitsToFloat3, intBitsToFloat4)));
        float max = Math.max(intBitsToFloat, Math.max(intBitsToFloat2, Math.max(intBitsToFloat3, intBitsToFloat4)));
        float intBitsToFloat5 = Float.intBitsToFloat((int) (mo486localToWindowMKHz9U & 4294967295L));
        float intBitsToFloat6 = Float.intBitsToFloat((int) (mo486localToWindowMKHz9U2 & 4294967295L));
        float intBitsToFloat7 = Float.intBitsToFloat((int) (mo486localToWindowMKHz9U4 & 4294967295L));
        float intBitsToFloat8 = Float.intBitsToFloat((int) (mo486localToWindowMKHz9U3 & 4294967295L));
        return new Rect(min, Math.min(intBitsToFloat5, Math.min(intBitsToFloat6, Math.min(intBitsToFloat7, intBitsToFloat8))), max, Math.max(intBitsToFloat5, Math.max(intBitsToFloat6, Math.max(intBitsToFloat7, intBitsToFloat8))));
    }

    public static final LayoutCoordinates findRootCoordinates(LayoutCoordinates layoutCoordinates) {
        LayoutCoordinates layoutCoordinates2;
        LayoutCoordinates parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
        while (true) {
            LayoutCoordinates layoutCoordinates3 = parentLayoutCoordinates;
            layoutCoordinates2 = layoutCoordinates;
            layoutCoordinates = layoutCoordinates3;
            if (layoutCoordinates == null) {
                break;
            }
            parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
        }
        NodeCoordinator nodeCoordinator = layoutCoordinates2 instanceof NodeCoordinator ? (NodeCoordinator) layoutCoordinates2 : null;
        if (nodeCoordinator == null) {
            return layoutCoordinates2;
        }
        NodeCoordinator nodeCoordinator2 = nodeCoordinator.wrappedBy;
        while (nodeCoordinator2 != null) {
            NodeCoordinator nodeCoordinator3 = nodeCoordinator2;
            nodeCoordinator2 = nodeCoordinator2.wrappedBy;
            nodeCoordinator = nodeCoordinator3;
        }
        return nodeCoordinator;
    }
}
