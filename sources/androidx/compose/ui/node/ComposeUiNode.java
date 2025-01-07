package androidx.compose.ui.node;

import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.CompositionLocalMapKt;
import androidx.compose.runtime.StaticProvidableCompositionLocal;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.internal.PersistentCompositionLocalHashMap;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.CacheDrawModifierNode;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface ComposeUiNode {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final Function0 Constructor;
        public static final Function2 SetCompositeKeyHash;
        public static final Function2 SetMeasurePolicy;
        public static final Function2 SetModifier;
        public static final Function2 SetResolvedCompositionLocals;
        public static final Function0 VirtualConstructor;

        static {
            LayoutNode$Companion$ErrorMeasurePolicy$1 layoutNode$Companion$ErrorMeasurePolicy$1 = LayoutNode.ErrorMeasurePolicy;
            Constructor = LayoutNode.Constructor;
            VirtualConstructor = new Function0() { // from class: androidx.compose.ui.node.ComposeUiNode$Companion$VirtualConstructor$1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return new LayoutNode(2);
                }
            };
            SetModifier = new Function2() { // from class: androidx.compose.ui.node.ComposeUiNode$Companion$SetModifier$1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((LayoutNode) ((ComposeUiNode) obj)).setModifier((Modifier) obj2);
                    return Unit.INSTANCE;
                }
            };
            SetResolvedCompositionLocals = new Function2() { // from class: androidx.compose.ui.node.ComposeUiNode$Companion$SetResolvedCompositionLocals$1
                /* JADX WARN: Multi-variable type inference failed */
                /* JADX WARN: Type inference failed for: r0v1 */
                /* JADX WARN: Type inference failed for: r0v10 */
                /* JADX WARN: Type inference failed for: r0v11 */
                /* JADX WARN: Type inference failed for: r0v12 */
                /* JADX WARN: Type inference failed for: r0v19 */
                /* JADX WARN: Type inference failed for: r0v2, types: [androidx.compose.ui.Modifier$Node] */
                /* JADX WARN: Type inference failed for: r0v20 */
                /* JADX WARN: Type inference failed for: r0v6 */
                /* JADX WARN: Type inference failed for: r0v7, types: [androidx.compose.ui.Modifier$Node] */
                /* JADX WARN: Type inference failed for: r0v8, types: [java.lang.Object] */
                /* JADX WARN: Type inference failed for: r0v9 */
                /* JADX WARN: Type inference failed for: r1v0 */
                /* JADX WARN: Type inference failed for: r1v1 */
                /* JADX WARN: Type inference failed for: r1v12 */
                /* JADX WARN: Type inference failed for: r1v13, types: [androidx.compose.ui.Modifier$Node] */
                /* JADX WARN: Type inference failed for: r1v14, types: [java.lang.Object] */
                /* JADX WARN: Type inference failed for: r1v15 */
                /* JADX WARN: Type inference failed for: r1v16 */
                /* JADX WARN: Type inference failed for: r1v17 */
                /* JADX WARN: Type inference failed for: r1v18 */
                /* JADX WARN: Type inference failed for: r1v19 */
                /* JADX WARN: Type inference failed for: r1v2 */
                /* JADX WARN: Type inference failed for: r1v20 */
                /* JADX WARN: Type inference failed for: r1v21 */
                /* JADX WARN: Type inference failed for: r1v22 */
                /* JADX WARN: Type inference failed for: r1v23 */
                /* JADX WARN: Type inference failed for: r1v24 */
                /* JADX WARN: Type inference failed for: r1v3, types: [androidx.compose.runtime.collection.MutableVector] */
                /* JADX WARN: Type inference failed for: r1v4 */
                /* JADX WARN: Type inference failed for: r1v5 */
                /* JADX WARN: Type inference failed for: r1v6, types: [androidx.compose.runtime.collection.MutableVector] */
                /* JADX WARN: Type inference failed for: r1v8 */
                /* JADX WARN: Type inference failed for: r1v9, types: [androidx.compose.ui.Modifier$Node] */
                /* JADX WARN: Type inference failed for: r2v10 */
                /* JADX WARN: Type inference failed for: r2v11 */
                /* JADX WARN: Type inference failed for: r2v12 */
                /* JADX WARN: Type inference failed for: r2v13, types: [androidx.compose.runtime.collection.MutableVector] */
                /* JADX WARN: Type inference failed for: r2v14 */
                /* JADX WARN: Type inference failed for: r2v15 */
                /* JADX WARN: Type inference failed for: r2v16, types: [androidx.compose.runtime.collection.MutableVector] */
                /* JADX WARN: Type inference failed for: r2v18 */
                /* JADX WARN: Type inference failed for: r2v19 */
                /* JADX WARN: Type inference failed for: r2v20 */
                /* JADX WARN: Type inference failed for: r2v21 */
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    CompositionLocalMap compositionLocalMap = (CompositionLocalMap) obj2;
                    LayoutNode layoutNode = (LayoutNode) ((ComposeUiNode) obj);
                    layoutNode.compositionLocalMap = compositionLocalMap;
                    StaticProvidableCompositionLocal staticProvidableCompositionLocal = CompositionLocalsKt.LocalDensity;
                    PersistentCompositionLocalHashMap persistentCompositionLocalHashMap = (PersistentCompositionLocalHashMap) compositionLocalMap;
                    persistentCompositionLocalHashMap.getClass();
                    layoutNode.setDensity$1((Density) CompositionLocalMapKt.read(persistentCompositionLocalHashMap, staticProvidableCompositionLocal));
                    LayoutDirection layoutDirection = (LayoutDirection) CompositionLocalMapKt.read(persistentCompositionLocalHashMap, CompositionLocalsKt.LocalLayoutDirection);
                    if (layoutNode.layoutDirection != layoutDirection) {
                        layoutNode.layoutDirection = layoutDirection;
                        layoutNode.invalidateMeasurements$ui_release();
                        LayoutNode parent$ui_release = layoutNode.getParent$ui_release();
                        if (parent$ui_release != null) {
                            parent$ui_release.invalidateLayer$ui_release();
                        }
                        layoutNode.invalidateLayers$ui_release();
                        Modifier.Node node = layoutNode.nodes.head;
                        if ((node.aggregateChildKindSet & 4) != 0) {
                            while (node != null) {
                                if ((node.kindSet & 4) != 0) {
                                    DelegatingNode delegatingNode = node;
                                    ?? r2 = 0;
                                    while (delegatingNode != 0) {
                                        if (delegatingNode instanceof DrawModifierNode) {
                                            DrawModifierNode drawModifierNode = (DrawModifierNode) delegatingNode;
                                            if (drawModifierNode instanceof CacheDrawModifierNode) {
                                                ((CacheDrawModifierNode) drawModifierNode).invalidateDrawCache();
                                            }
                                        } else if ((delegatingNode.kindSet & 4) != 0 && (delegatingNode instanceof DelegatingNode)) {
                                            Modifier.Node node2 = delegatingNode.delegate;
                                            int i = 0;
                                            delegatingNode = delegatingNode;
                                            r2 = r2;
                                            while (node2 != null) {
                                                if ((node2.kindSet & 4) != 0) {
                                                    i++;
                                                    r2 = r2;
                                                    if (i == 1) {
                                                        delegatingNode = node2;
                                                    } else {
                                                        if (r2 == 0) {
                                                            r2 = new MutableVector(new Modifier.Node[16]);
                                                        }
                                                        if (delegatingNode != 0) {
                                                            r2.add(delegatingNode);
                                                            delegatingNode = 0;
                                                        }
                                                        r2.add(node2);
                                                    }
                                                }
                                                node2 = node2.child;
                                                delegatingNode = delegatingNode;
                                                r2 = r2;
                                            }
                                            if (i == 1) {
                                            }
                                        }
                                        delegatingNode = DelegatableNodeKt.access$pop(r2);
                                    }
                                }
                                if ((node.aggregateChildKindSet & 4) == 0) {
                                    break;
                                }
                                node = node.child;
                            }
                        }
                    }
                    layoutNode.setViewConfiguration((ViewConfiguration) CompositionLocalMapKt.read(persistentCompositionLocalHashMap, CompositionLocalsKt.LocalViewConfiguration));
                    Modifier.Node node3 = layoutNode.nodes.head;
                    if ((node3.aggregateChildKindSet & 32768) != 0) {
                        while (node3 != null) {
                            if ((node3.kindSet & 32768) != 0) {
                                DelegatingNode delegatingNode2 = node3;
                                ?? r1 = 0;
                                while (delegatingNode2 != 0) {
                                    if (delegatingNode2 instanceof CompositionLocalConsumerModifierNode) {
                                        Modifier.Node node4 = ((Modifier.Node) ((CompositionLocalConsumerModifierNode) delegatingNode2)).node;
                                        if (node4.isAttached) {
                                            NodeKindKt.autoInvalidateUpdatedNode(node4);
                                        } else {
                                            node4.updatedNodeAwaitingAttachForInvalidation = true;
                                        }
                                    } else if ((delegatingNode2.kindSet & 32768) != 0 && (delegatingNode2 instanceof DelegatingNode)) {
                                        Modifier.Node node5 = delegatingNode2.delegate;
                                        int i2 = 0;
                                        delegatingNode2 = delegatingNode2;
                                        r1 = r1;
                                        while (node5 != null) {
                                            if ((node5.kindSet & 32768) != 0) {
                                                i2++;
                                                r1 = r1;
                                                if (i2 == 1) {
                                                    delegatingNode2 = node5;
                                                } else {
                                                    if (r1 == 0) {
                                                        r1 = new MutableVector(new Modifier.Node[16]);
                                                    }
                                                    if (delegatingNode2 != 0) {
                                                        r1.add(delegatingNode2);
                                                        delegatingNode2 = 0;
                                                    }
                                                    r1.add(node5);
                                                }
                                            }
                                            node5 = node5.child;
                                            delegatingNode2 = delegatingNode2;
                                            r1 = r1;
                                        }
                                        if (i2 == 1) {
                                        }
                                    }
                                    delegatingNode2 = DelegatableNodeKt.access$pop(r1);
                                }
                            }
                            if ((node3.aggregateChildKindSet & 32768) == 0) {
                                break;
                            }
                            node3 = node3.child;
                        }
                    }
                    return Unit.INSTANCE;
                }
            };
            SetMeasurePolicy = new Function2() { // from class: androidx.compose.ui.node.ComposeUiNode$Companion$SetMeasurePolicy$1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((LayoutNode) ((ComposeUiNode) obj)).setMeasurePolicy((MeasurePolicy) obj2);
                    return Unit.INSTANCE;
                }
            };
            SetCompositeKeyHash = new Function2() { // from class: androidx.compose.ui.node.ComposeUiNode$Companion$SetCompositeKeyHash$1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    ((ComposeUiNode) obj).getClass();
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
