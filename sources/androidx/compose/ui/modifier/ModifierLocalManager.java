package androidx.compose.ui.modifier;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.node.BackwardsCompatNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.platform.AndroidComposeView;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ModifierLocalManager {
    public boolean invalidated;
    public final AndroidComposeView owner;
    public final MutableVector inserted = new MutableVector(new BackwardsCompatNode[16]);
    public final MutableVector insertedLocal = new MutableVector(new ModifierLocal[16]);
    public final MutableVector removed = new MutableVector(new LayoutNode[16]);
    public final MutableVector removedLocal = new MutableVector(new ModifierLocal[16]);

    public ModifierLocalManager(AndroidComposeView androidComposeView) {
        this.owner = androidComposeView;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r4v7, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9 */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r5v6, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r5v9 */
    public static void invalidateConsumersOfNodeForKey(Modifier.Node node, ModifierLocal modifierLocal, Set set) {
        if (!node.node.isAttached) {
            InlineClassHelperKt.throwIllegalStateException("visitSubtreeIf called on an unattached node");
        }
        MutableVector mutableVector = new MutableVector(new Modifier.Node[16]);
        Modifier.Node node2 = node.node;
        Modifier.Node node3 = node2.child;
        if (node3 == null) {
            DelegatableNodeKt.access$addLayoutNodeChildren(mutableVector, node2);
        } else {
            mutableVector.add(node3);
        }
        while (true) {
            int i = mutableVector.size;
            if (i == 0) {
                return;
            }
            Modifier.Node node4 = (Modifier.Node) mutableVector.removeAt(i - 1);
            if ((node4.aggregateChildKindSet & 32) != 0) {
                for (Modifier.Node node5 = node4; node5 != null; node5 = node5.child) {
                    if ((node5.kindSet & 32) != 0) {
                        DelegatingNode delegatingNode = node5;
                        ?? r5 = 0;
                        while (delegatingNode != 0) {
                            if (delegatingNode instanceof ModifierLocalModifierNode) {
                                ModifierLocalModifierNode modifierLocalModifierNode = (ModifierLocalModifierNode) delegatingNode;
                                if (modifierLocalModifierNode instanceof BackwardsCompatNode) {
                                    BackwardsCompatNode backwardsCompatNode = (BackwardsCompatNode) modifierLocalModifierNode;
                                    if ((backwardsCompatNode.element instanceof ModifierLocalConsumer) && backwardsCompatNode.readValues.contains(modifierLocal)) {
                                        set.add(modifierLocalModifierNode);
                                    }
                                }
                                if (modifierLocalModifierNode.getProvidedValues().contains$ui_release(modifierLocal)) {
                                    break;
                                }
                            } else if ((delegatingNode.kindSet & 32) != 0 && (delegatingNode instanceof DelegatingNode)) {
                                Modifier.Node node6 = delegatingNode.delegate;
                                int i2 = 0;
                                delegatingNode = delegatingNode;
                                r5 = r5;
                                while (node6 != null) {
                                    if ((node6.kindSet & 32) != 0) {
                                        i2++;
                                        r5 = r5;
                                        if (i2 == 1) {
                                            delegatingNode = node6;
                                        } else {
                                            if (r5 == 0) {
                                                r5 = new MutableVector(new Modifier.Node[16]);
                                            }
                                            if (delegatingNode != 0) {
                                                r5.add(delegatingNode);
                                                delegatingNode = 0;
                                            }
                                            r5.add(node6);
                                        }
                                    }
                                    node6 = node6.child;
                                    delegatingNode = delegatingNode;
                                    r5 = r5;
                                }
                                if (i2 == 1) {
                                }
                            }
                            delegatingNode = DelegatableNodeKt.access$pop(r5);
                        }
                    }
                }
            }
            DelegatableNodeKt.access$addLayoutNodeChildren(mutableVector, node4);
        }
    }

    public final void invalidate() {
        if (this.invalidated) {
            return;
        }
        this.invalidated = true;
        this.owner.registerOnEndApplyChangesListener(new Function0() { // from class: androidx.compose.ui.modifier.ModifierLocalManager$invalidate$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                ModifierLocalManager modifierLocalManager = ModifierLocalManager.this;
                int i = 0;
                modifierLocalManager.invalidated = false;
                HashSet hashSet = new HashSet();
                MutableVector mutableVector = modifierLocalManager.removed;
                int i2 = mutableVector.size;
                MutableVector mutableVector2 = modifierLocalManager.removedLocal;
                if (i2 > 0) {
                    Object[] objArr = mutableVector.content;
                    int i3 = 0;
                    do {
                        LayoutNode layoutNode = (LayoutNode) objArr[i3];
                        ModifierLocal modifierLocal = (ModifierLocal) mutableVector2.content[i3];
                        Modifier.Node node = layoutNode.nodes.head;
                        if (node.isAttached) {
                            ModifierLocalManager.invalidateConsumersOfNodeForKey(node, modifierLocal, hashSet);
                        }
                        i3++;
                    } while (i3 < i2);
                }
                mutableVector.clear();
                mutableVector2.clear();
                MutableVector mutableVector3 = modifierLocalManager.inserted;
                int i4 = mutableVector3.size;
                MutableVector mutableVector4 = modifierLocalManager.insertedLocal;
                if (i4 > 0) {
                    Object[] objArr2 = mutableVector3.content;
                    do {
                        BackwardsCompatNode backwardsCompatNode = (BackwardsCompatNode) objArr2[i];
                        ModifierLocal modifierLocal2 = (ModifierLocal) mutableVector4.content[i];
                        if (backwardsCompatNode.isAttached) {
                            ModifierLocalManager.invalidateConsumersOfNodeForKey(backwardsCompatNode, modifierLocal2, hashSet);
                        }
                        i++;
                    } while (i < i4);
                }
                mutableVector3.clear();
                mutableVector4.clear();
                Iterator it = hashSet.iterator();
                while (it.hasNext()) {
                    ((BackwardsCompatNode) it.next()).updateModifierLocalConsumer();
                }
                return Unit.INSTANCE;
            }
        });
    }
}
