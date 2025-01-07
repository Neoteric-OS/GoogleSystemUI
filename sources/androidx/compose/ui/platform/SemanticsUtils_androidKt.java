package androidx.compose.ui.platform;

import android.graphics.Region;
import androidx.collection.IntObjectMapKt;
import androidx.collection.MutableIntObjectMap;
import androidx.collection.MutableScatterMap;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.MutableRect;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.semantics.AccessibilityAction;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsActions;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsConfigurationKt;
import androidx.compose.ui.semantics.SemanticsNode;
import androidx.compose.ui.semantics.SemanticsNodeKt;
import androidx.compose.ui.semantics.SemanticsOwner;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.compose.ui.semantics.SemanticsPropertyKey;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.viewinterop.AndroidViewHolder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SemanticsUtils_androidKt {
    public static final Rect DefaultFakeNodeBounds = new Rect(0.0f, 0.0f, 10.0f, 10.0f);

    public static final MutableIntObjectMap getAllUncoveredSemanticsNodesToIntObjectMap(SemanticsOwner semanticsOwner) {
        SemanticsNode unmergedRootSemanticsNode = semanticsOwner.getUnmergedRootSemanticsNode();
        LayoutNode layoutNode = unmergedRootSemanticsNode.layoutNode;
        if (!layoutNode.isPlaced() || !layoutNode.isAttached()) {
            return IntObjectMapKt.EmptyIntObjectMap;
        }
        MutableIntObjectMap mutableIntObjectMap = new MutableIntObjectMap(48);
        Rect boundsInRoot = unmergedRootSemanticsNode.getBoundsInRoot();
        getAllUncoveredSemanticsNodesToIntObjectMap$findAllSemanticNodesRecursive(new Region(Math.round(boundsInRoot.left), Math.round(boundsInRoot.top), Math.round(boundsInRoot.right), Math.round(boundsInRoot.bottom)), unmergedRootSemanticsNode, mutableIntObjectMap, unmergedRootSemanticsNode, new Region());
        return mutableIntObjectMap;
    }

    public static final void getAllUncoveredSemanticsNodesToIntObjectMap$findAllSemanticNodesRecursive(Region region, SemanticsNode semanticsNode, MutableIntObjectMap mutableIntObjectMap, SemanticsNode semanticsNode2, Region region2) {
        Object outerMergingSemantics;
        boolean isPlaced = semanticsNode2.layoutNode.isPlaced();
        LayoutNode layoutNode = semanticsNode2.layoutNode;
        boolean z = (isPlaced && layoutNode.isAttached()) ? false : true;
        boolean isEmpty = region.isEmpty();
        int i = semanticsNode.id;
        int i2 = semanticsNode2.id;
        if (!isEmpty || i2 == i) {
            if (!z || semanticsNode2.isFake) {
                SemanticsConfiguration semanticsConfiguration = semanticsNode2.unmergedConfig;
                boolean z2 = semanticsConfiguration.isMergingSemanticsOfDescendants;
                Object obj = semanticsNode2.outerSemanticsNode;
                if (z2 && (outerMergingSemantics = SemanticsNodeKt.getOuterMergingSemantics(layoutNode)) != null) {
                    obj = outerMergingSemantics;
                }
                Modifier.Node node = ((Modifier.Node) obj).node;
                boolean z3 = SemanticsConfigurationKt.getOrNull(semanticsConfiguration, SemanticsActions.OnClick) != null;
                boolean z4 = node.node.isAttached;
                Rect rect = Rect.Zero;
                if (z4) {
                    if (z3) {
                        NodeCoordinator m503requireCoordinator64DMado = DelegatableNodeKt.m503requireCoordinator64DMado(node, 8);
                        if (m503requireCoordinator64DMado.getTail().isAttached) {
                            LayoutCoordinates findRootCoordinates = LayoutCoordinatesKt.findRootCoordinates(m503requireCoordinator64DMado);
                            MutableRect mutableRect = m503requireCoordinator64DMado._rectCache;
                            if (mutableRect == null) {
                                mutableRect = new MutableRect();
                                m503requireCoordinator64DMado._rectCache = mutableRect;
                            }
                            long m527calculateMinimumTouchTargetPaddingE7KxVPU = m503requireCoordinator64DMado.m527calculateMinimumTouchTargetPaddingE7KxVPU(m503requireCoordinator64DMado.m530getMinimumTouchTargetSizeNHjbRc());
                            int i3 = (int) (m527calculateMinimumTouchTargetPaddingE7KxVPU >> 32);
                            mutableRect.left = -Float.intBitsToFloat(i3);
                            int i4 = (int) (m527calculateMinimumTouchTargetPaddingE7KxVPU & 4294967295L);
                            mutableRect.top = -Float.intBitsToFloat(i4);
                            mutableRect.right = Float.intBitsToFloat(i3) + m503requireCoordinator64DMado.getMeasuredWidth();
                            mutableRect.bottom = Float.intBitsToFloat(i4) + m503requireCoordinator64DMado.getMeasuredHeight();
                            while (true) {
                                if (m503requireCoordinator64DMado == findRootCoordinates) {
                                    rect = new Rect(mutableRect.left, mutableRect.top, mutableRect.right, mutableRect.bottom);
                                    break;
                                }
                                m503requireCoordinator64DMado.rectInParent$ui_release(mutableRect, false, true);
                                if (mutableRect.isEmpty()) {
                                    break;
                                }
                                m503requireCoordinator64DMado = m503requireCoordinator64DMado.wrappedBy;
                                Intrinsics.checkNotNull(m503requireCoordinator64DMado);
                            }
                        }
                    } else {
                        NodeCoordinator m503requireCoordinator64DMado2 = DelegatableNodeKt.m503requireCoordinator64DMado(node, 8);
                        rect = LayoutCoordinatesKt.findRootCoordinates(m503requireCoordinator64DMado2).localBoundingBoxOf(m503requireCoordinator64DMado2, true);
                    }
                }
                int round = Math.round(rect.left);
                int round2 = Math.round(rect.top);
                int round3 = Math.round(rect.right);
                int round4 = Math.round(rect.bottom);
                region2.set(round, round2, round3, round4);
                if (i2 == i) {
                    i2 = -1;
                }
                if (!region2.op(region, Region.Op.INTERSECT)) {
                    if (semanticsNode2.isFake) {
                        SemanticsNode parent = semanticsNode2.getParent();
                        Rect boundsInRoot = (parent == null || !parent.layoutNode.isPlaced()) ? DefaultFakeNodeBounds : parent.getBoundsInRoot();
                        mutableIntObjectMap.set(i2, new SemanticsNodeWithAdjustedBounds(semanticsNode2, new android.graphics.Rect(Math.round(boundsInRoot.left), Math.round(boundsInRoot.top), Math.round(boundsInRoot.right), Math.round(boundsInRoot.bottom))));
                        return;
                    } else {
                        if (i2 == -1) {
                            mutableIntObjectMap.set(i2, new SemanticsNodeWithAdjustedBounds(semanticsNode2, region2.getBounds()));
                            return;
                        }
                        return;
                    }
                }
                mutableIntObjectMap.set(i2, new SemanticsNodeWithAdjustedBounds(semanticsNode2, region2.getBounds()));
                List children$ui_release$default = SemanticsNode.getChildren$ui_release$default(semanticsNode2, 4);
                for (int size = children$ui_release$default.size() - 1; -1 < size; size--) {
                    if (!((SemanticsNode) children$ui_release$default.get(size)).getConfig().props.containsKey(SemanticsProperties.LinkTestMarker)) {
                        getAllUncoveredSemanticsNodesToIntObjectMap$findAllSemanticNodesRecursive(region, semanticsNode, mutableIntObjectMap, (SemanticsNode) children$ui_release$default.get(size), region2);
                    }
                }
                if (isImportantForAccessibility(semanticsNode2)) {
                    region.op(round, round2, round3, round4, Region.Op.DIFFERENCE);
                }
            }
        }
    }

    public static final TextLayoutResult getTextLayoutResult(SemanticsConfiguration semanticsConfiguration) {
        Function1 function1;
        ArrayList arrayList = new ArrayList();
        AccessibilityAction accessibilityAction = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsConfiguration, SemanticsActions.GetTextLayoutResult);
        if (accessibilityAction == null || (function1 = (Function1) accessibilityAction.action) == null || !((Boolean) function1.invoke(arrayList)).booleanValue()) {
            return null;
        }
        return (TextLayoutResult) arrayList.get(0);
    }

    public static final boolean isImportantForAccessibility(SemanticsNode semanticsNode) {
        if (!isVisible(semanticsNode)) {
            return false;
        }
        SemanticsConfiguration semanticsConfiguration = semanticsNode.unmergedConfig;
        if (!semanticsConfiguration.isMergingSemanticsOfDescendants) {
            MutableScatterMap mutableScatterMap = semanticsConfiguration.props;
            Object[] objArr = mutableScatterMap.keys;
            Object[] objArr2 = mutableScatterMap.values;
            long[] jArr = mutableScatterMap.metadata;
            int length = jArr.length - 2;
            if (length < 0) {
                return false;
            }
            int i = 0;
            loop0: while (true) {
                long j = jArr[i];
                if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i2 = 8 - ((~(i - length)) >>> 31);
                    for (int i3 = 0; i3 < i2; i3++) {
                        if ((255 & j) < 128) {
                            int i4 = (i << 3) + i3;
                            Object obj = objArr[i4];
                            Object obj2 = objArr2[i4];
                            if (((SemanticsPropertyKey) obj).isImportantForAccessibility) {
                                break loop0;
                            }
                        }
                        j >>= 8;
                    }
                    if (i2 != 8) {
                        return false;
                    }
                }
                if (i == length) {
                    return false;
                }
                i++;
            }
        }
        return true;
    }

    public static final boolean isVisible(SemanticsNode semanticsNode) {
        NodeCoordinator findCoordinatorToGetBounds$ui_release = semanticsNode.findCoordinatorToGetBounds$ui_release();
        if (findCoordinatorToGetBounds$ui_release != null ? findCoordinatorToGetBounds$ui_release.isTransparent() : false) {
            return false;
        }
        return !semanticsNode.unmergedConfig.props.containsKey(SemanticsProperties.InvisibleToUser);
    }

    public static final AndroidViewHolder semanticsIdToView(AndroidViewsHandler androidViewsHandler, int i) {
        Object obj;
        Iterator it = androidViewsHandler.layoutNodeToHolder.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (((LayoutNode) ((Map.Entry) obj).getKey()).semanticsId == i) {
                break;
            }
        }
        Map.Entry entry = (Map.Entry) obj;
        if (entry != null) {
            return (AndroidViewHolder) entry.getValue();
        }
        return null;
    }

    /* renamed from: toLegacyClassName-V4PA4sw, reason: not valid java name */
    public static final String m572toLegacyClassNameV4PA4sw(int i) {
        if (Role.m574equalsimpl0(i, 0)) {
            return "android.widget.Button";
        }
        if (Role.m574equalsimpl0(i, 1)) {
            return "android.widget.CheckBox";
        }
        if (Role.m574equalsimpl0(i, 3)) {
            return "android.widget.RadioButton";
        }
        if (Role.m574equalsimpl0(i, 5)) {
            return "android.widget.ImageView";
        }
        if (Role.m574equalsimpl0(i, 6)) {
            return "android.widget.Spinner";
        }
        if (Role.m574equalsimpl0(i, 7)) {
            return "android.widget.NumberPicker";
        }
        return null;
    }
}
