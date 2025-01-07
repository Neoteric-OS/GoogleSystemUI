package androidx.compose.ui.semantics;

import androidx.collection.MutableScatterMap;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeCoordinator;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SemanticsNode {
    public SemanticsNode fakeNodeParent;
    public final int id;
    public boolean isFake;
    public final LayoutNode layoutNode;
    public final boolean mergingEnabled;
    public final Modifier.Node outerSemanticsNode;
    public final SemanticsConfiguration unmergedConfig;

    public SemanticsNode(Modifier.Node node, boolean z, LayoutNode layoutNode, SemanticsConfiguration semanticsConfiguration) {
        this.outerSemanticsNode = node;
        this.mergingEnabled = z;
        this.layoutNode = layoutNode;
        this.unmergedConfig = semanticsConfiguration;
        this.id = layoutNode.semanticsId;
    }

    public static /* synthetic */ List getChildren$ui_release$default(SemanticsNode semanticsNode, int i) {
        return semanticsNode.getChildren$ui_release((i & 1) != 0 ? !semanticsNode.mergingEnabled : false, (i & 2) == 0);
    }

    /* renamed from: fakeSemanticsNode-ypyhhiA, reason: not valid java name */
    public final SemanticsNode m576fakeSemanticsNodeypyhhiA(Role role, Function1 function1) {
        SemanticsConfiguration semanticsConfiguration = new SemanticsConfiguration();
        semanticsConfiguration.isMergingSemanticsOfDescendants = false;
        semanticsConfiguration.isClearingSemantics = false;
        function1.invoke(semanticsConfiguration);
        SemanticsNode semanticsNode = new SemanticsNode(new SemanticsNode$fakeSemanticsNode$fakeNode$1(function1), false, new LayoutNode(this.id + (role != null ? 1000000000 : 2000000000), true), semanticsConfiguration);
        semanticsNode.isFake = true;
        semanticsNode.fakeNodeParent = this;
        return semanticsNode;
    }

    public final void fillOneLayerOfSemanticsWrappers(LayoutNode layoutNode, List list) {
        MutableVector zSortedChildren = layoutNode.getZSortedChildren();
        int i = zSortedChildren.size;
        if (i > 0) {
            Object[] objArr = zSortedChildren.content;
            int i2 = 0;
            do {
                LayoutNode layoutNode2 = (LayoutNode) objArr[i2];
                if (layoutNode2.isAttached() && !layoutNode2.isDeactivated) {
                    if (layoutNode2.nodes.m525hasH91voCI$ui_release(8)) {
                        list.add(SemanticsNodeKt.SemanticsNode(layoutNode2, this.mergingEnabled));
                    } else {
                        fillOneLayerOfSemanticsWrappers(layoutNode2, list);
                    }
                }
                i2++;
            } while (i2 < i);
        }
    }

    public final NodeCoordinator findCoordinatorToGetBounds$ui_release() {
        if (this.isFake) {
            SemanticsNode parent = getParent();
            if (parent != null) {
                return parent.findCoordinatorToGetBounds$ui_release();
            }
            return null;
        }
        DelegatableNode outerMergingSemantics = SemanticsNodeKt.getOuterMergingSemantics(this.layoutNode);
        if (outerMergingSemantics == null) {
            outerMergingSemantics = this.outerSemanticsNode;
        }
        return DelegatableNodeKt.m503requireCoordinator64DMado(outerMergingSemantics, 8);
    }

    public final void findOneLayerOfMergingSemanticsNodes(List list, List list2) {
        ArrayList arrayList = (ArrayList) list;
        unmergedChildren$ui_release(list, false);
        int size = arrayList.size();
        for (int size2 = arrayList.size(); size2 < size; size2++) {
            SemanticsNode semanticsNode = (SemanticsNode) arrayList.get(size2);
            if (semanticsNode.isMergingSemanticsOfDescendants()) {
                list2.add(semanticsNode);
            } else if (!semanticsNode.unmergedConfig.isClearingSemantics) {
                semanticsNode.findOneLayerOfMergingSemanticsNodes(list, list2);
            }
        }
    }

    public final Rect getBoundsInRoot() {
        NodeCoordinator findCoordinatorToGetBounds$ui_release = findCoordinatorToGetBounds$ui_release();
        if (findCoordinatorToGetBounds$ui_release != null) {
            if (!findCoordinatorToGetBounds$ui_release.getTail().isAttached) {
                findCoordinatorToGetBounds$ui_release = null;
            }
            if (findCoordinatorToGetBounds$ui_release != null) {
                return LayoutCoordinatesKt.findRootCoordinates(findCoordinatorToGetBounds$ui_release).localBoundingBoxOf(findCoordinatorToGetBounds$ui_release, true);
            }
        }
        return Rect.Zero;
    }

    public final Rect getBoundsInWindow() {
        NodeCoordinator findCoordinatorToGetBounds$ui_release = findCoordinatorToGetBounds$ui_release();
        if (findCoordinatorToGetBounds$ui_release != null) {
            if (!findCoordinatorToGetBounds$ui_release.getTail().isAttached) {
                findCoordinatorToGetBounds$ui_release = null;
            }
            if (findCoordinatorToGetBounds$ui_release != null) {
                return LayoutCoordinatesKt.boundsInWindow(findCoordinatorToGetBounds$ui_release);
            }
        }
        return Rect.Zero;
    }

    public final List getChildren$ui_release(boolean z, boolean z2) {
        if (!z && this.unmergedConfig.isClearingSemantics) {
            return EmptyList.INSTANCE;
        }
        ArrayList arrayList = new ArrayList();
        if (!isMergingSemanticsOfDescendants()) {
            return unmergedChildren$ui_release(arrayList, z2);
        }
        ArrayList arrayList2 = new ArrayList();
        findOneLayerOfMergingSemanticsNodes(arrayList, arrayList2);
        return arrayList2;
    }

    public final SemanticsConfiguration getConfig() {
        boolean isMergingSemanticsOfDescendants = isMergingSemanticsOfDescendants();
        SemanticsConfiguration semanticsConfiguration = this.unmergedConfig;
        if (!isMergingSemanticsOfDescendants) {
            return semanticsConfiguration;
        }
        SemanticsConfiguration semanticsConfiguration2 = new SemanticsConfiguration();
        semanticsConfiguration2.isMergingSemanticsOfDescendants = semanticsConfiguration.isMergingSemanticsOfDescendants;
        semanticsConfiguration2.isClearingSemantics = semanticsConfiguration.isClearingSemantics;
        MutableScatterMap mutableScatterMap = semanticsConfiguration2.props;
        MutableScatterMap mutableScatterMap2 = semanticsConfiguration.props;
        mutableScatterMap.getClass();
        Object[] objArr = mutableScatterMap2.keys;
        Object[] objArr2 = mutableScatterMap2.values;
        long[] jArr = mutableScatterMap2.metadata;
        int length = jArr.length - 2;
        if (length >= 0) {
            int i = 0;
            while (true) {
                long j = jArr[i];
                if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i2 = 8 - ((~(i - length)) >>> 31);
                    for (int i3 = 0; i3 < i2; i3++) {
                        if ((255 & j) < 128) {
                            int i4 = (i << 3) + i3;
                            mutableScatterMap.set(objArr[i4], objArr2[i4]);
                        }
                        j >>= 8;
                    }
                    if (i2 != 8) {
                        break;
                    }
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        mergeConfig(new ArrayList(), semanticsConfiguration2);
        return semanticsConfiguration2;
    }

    public final SemanticsNode getParent() {
        SemanticsNode semanticsNode = this.fakeNodeParent;
        if (semanticsNode != null) {
            return semanticsNode;
        }
        LayoutNode layoutNode = this.layoutNode;
        boolean z = this.mergingEnabled;
        LayoutNode findClosestParentNode = z ? SemanticsNodeKt.findClosestParentNode(layoutNode, new Function1() { // from class: androidx.compose.ui.semantics.SemanticsNode$parent$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                SemanticsConfiguration collapsedSemantics$ui_release = ((LayoutNode) obj).getCollapsedSemantics$ui_release();
                boolean z2 = false;
                if (collapsedSemantics$ui_release != null && collapsedSemantics$ui_release.isMergingSemanticsOfDescendants) {
                    z2 = true;
                }
                return Boolean.valueOf(z2);
            }
        }) : null;
        if (findClosestParentNode == null) {
            findClosestParentNode = SemanticsNodeKt.findClosestParentNode(layoutNode, new Function1() { // from class: androidx.compose.ui.semantics.SemanticsNode$parent$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(((LayoutNode) obj).nodes.m525hasH91voCI$ui_release(8));
                }
            });
        }
        if (findClosestParentNode == null) {
            return null;
        }
        return SemanticsNodeKt.SemanticsNode(findClosestParentNode, z);
    }

    public final boolean isMergingSemanticsOfDescendants() {
        return this.mergingEnabled && this.unmergedConfig.isMergingSemanticsOfDescendants;
    }

    public final boolean isUnmergedLeafNode$ui_release() {
        return !this.isFake && getChildren$ui_release$default(this, 4).isEmpty() && SemanticsNodeKt.findClosestParentNode(this.layoutNode, new Function1() { // from class: androidx.compose.ui.semantics.SemanticsNode$isUnmergedLeafNode$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                SemanticsConfiguration collapsedSemantics$ui_release = ((LayoutNode) obj).getCollapsedSemantics$ui_release();
                boolean z = false;
                if (collapsedSemantics$ui_release != null && collapsedSemantics$ui_release.isMergingSemanticsOfDescendants) {
                    z = true;
                }
                return Boolean.valueOf(z);
            }
        }) == null;
    }

    public final void mergeConfig(List list, SemanticsConfiguration semanticsConfiguration) {
        int i;
        ArrayList arrayList;
        long[] jArr;
        int i2;
        ArrayList arrayList2;
        long[] jArr2;
        int i3;
        if (this.unmergedConfig.isClearingSemantics) {
            return;
        }
        ArrayList arrayList3 = (ArrayList) list;
        int size = arrayList3.size();
        int i4 = 0;
        unmergedChildren$ui_release(list, false);
        int size2 = arrayList3.size();
        while (size < size2) {
            SemanticsNode semanticsNode = (SemanticsNode) arrayList3.get(size);
            if (semanticsNode.isMergingSemanticsOfDescendants()) {
                i = size2;
                arrayList = arrayList3;
            } else {
                MutableScatterMap mutableScatterMap = semanticsNode.unmergedConfig.props;
                Object[] objArr = mutableScatterMap.keys;
                Object[] objArr2 = mutableScatterMap.values;
                long[] jArr3 = mutableScatterMap.metadata;
                int length = jArr3.length - 2;
                if (length >= 0) {
                    int i5 = i4;
                    while (true) {
                        long j = jArr3[i5];
                        if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                            int i6 = 8;
                            int i7 = 8 - ((~(i5 - length)) >>> 31);
                            while (i4 < i7) {
                                if ((j & 255) < 128) {
                                    int i8 = (i5 << 3) + i4;
                                    Object obj = objArr[i8];
                                    Object obj2 = objArr2[i8];
                                    i2 = size2;
                                    SemanticsPropertyKey semanticsPropertyKey = (SemanticsPropertyKey) obj;
                                    arrayList2 = arrayList3;
                                    jArr2 = jArr3;
                                    Object invoke = semanticsPropertyKey.mergePolicy.invoke(semanticsConfiguration.props.get(semanticsPropertyKey), obj2);
                                    if (invoke != null) {
                                        semanticsConfiguration.props.set(semanticsPropertyKey, invoke);
                                    }
                                    i3 = 8;
                                } else {
                                    i2 = size2;
                                    arrayList2 = arrayList3;
                                    jArr2 = jArr3;
                                    i3 = i6;
                                }
                                j >>= i3;
                                i4++;
                                i6 = i3;
                                size2 = i2;
                                arrayList3 = arrayList2;
                                jArr3 = jArr2;
                            }
                            i = size2;
                            arrayList = arrayList3;
                            jArr = jArr3;
                            if (i7 != i6) {
                                break;
                            }
                        } else {
                            i = size2;
                            arrayList = arrayList3;
                            jArr = jArr3;
                        }
                        if (i5 == length) {
                            break;
                        }
                        i5++;
                        size2 = i;
                        arrayList3 = arrayList;
                        jArr3 = jArr;
                        i4 = 0;
                    }
                } else {
                    i = size2;
                    arrayList = arrayList3;
                }
                semanticsNode.mergeConfig(list, semanticsConfiguration);
            }
            size++;
            size2 = i;
            arrayList3 = arrayList;
            i4 = 0;
        }
    }

    public final List unmergedChildren$ui_release(List list, boolean z) {
        if (this.isFake) {
            return EmptyList.INSTANCE;
        }
        fillOneLayerOfSemanticsWrappers(this.layoutNode, list);
        if (z) {
            SemanticsPropertyKey semanticsPropertyKey = SemanticsProperties.Role;
            SemanticsConfiguration semanticsConfiguration = this.unmergedConfig;
            final Role role = (Role) SemanticsConfigurationKt.getOrNull(semanticsConfiguration, semanticsPropertyKey);
            if (role != null && semanticsConfiguration.isMergingSemanticsOfDescendants && !list.isEmpty()) {
                list.add(m576fakeSemanticsNodeypyhhiA(role, new Function1() { // from class: androidx.compose.ui.semantics.SemanticsNode$emitFakeNodes$fakeNode$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        SemanticsPropertiesKt.m577setRolekuIjeqM((SemanticsPropertyReceiver) obj, Role.this.value);
                        return Unit.INSTANCE;
                    }
                }));
            }
            SemanticsPropertyKey semanticsPropertyKey2 = SemanticsProperties.ContentDescription;
            if (semanticsConfiguration.props.containsKey(semanticsPropertyKey2) && !list.isEmpty() && semanticsConfiguration.isMergingSemanticsOfDescendants) {
                List list2 = (List) SemanticsConfigurationKt.getOrNull(semanticsConfiguration, semanticsPropertyKey2);
                final String str = list2 != null ? (String) CollectionsKt.firstOrNull(list2) : null;
                if (str != null) {
                    list.add(0, m576fakeSemanticsNodeypyhhiA(null, new Function1() { // from class: androidx.compose.ui.semantics.SemanticsNode$emitFakeNodes$fakeNode$2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            SemanticsPropertiesKt.setContentDescription((SemanticsPropertyReceiver) obj, str);
                            return Unit.INSTANCE;
                        }
                    }));
                }
            }
        }
        return list;
    }
}
