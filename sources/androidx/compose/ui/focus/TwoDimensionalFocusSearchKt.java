package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.BeyondBoundsLayout;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class TwoDimensionalFocusSearchKt {
    /* JADX WARN: Code restructure failed: missing block: B:12:0x005b, code lost:
    
        if (androidx.compose.ui.focus.FocusDirection.m284equalsimpl0(r20, 3) != false) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0061, code lost:
    
        if (androidx.compose.ui.focus.FocusDirection.m284equalsimpl0(r20, 4) == false) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0068, code lost:
    
        if (androidx.compose.ui.focus.FocusDirection.m284equalsimpl0(r20, 3) == false) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x006a, code lost:
    
        r1 = r0 - r18.right;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0090, code lost:
    
        if (r1 >= 0.0f) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0092, code lost:
    
        r1 = 0.0f;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0097, code lost:
    
        if (androidx.compose.ui.focus.FocusDirection.m284equalsimpl0(r20, 3) == false) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0099, code lost:
    
        r0 = r0 - r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00b9, code lost:
    
        if (r0 >= 1.0f) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00bb, code lost:
    
        r0 = 1.0f;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00be, code lost:
    
        if (r1 >= r0) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x009f, code lost:
    
        if (androidx.compose.ui.focus.FocusDirection.m284equalsimpl0(r20, 4) == false) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00a1, code lost:
    
        r0 = r2 - r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00a8, code lost:
    
        if (androidx.compose.ui.focus.FocusDirection.m284equalsimpl0(r20, 5) == false) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00aa, code lost:
    
        r0 = r5 - r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00b1, code lost:
    
        if (androidx.compose.ui.focus.FocusDirection.m284equalsimpl0(r20, 6) == false) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00b3, code lost:
    
        r0 = r13 - r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00c9, code lost:
    
        throw new java.lang.IllegalStateException("This function should only be used for 2-D focus search");
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0073, code lost:
    
        if (androidx.compose.ui.focus.FocusDirection.m284equalsimpl0(r20, 4) == false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0075, code lost:
    
        r1 = r18.left - r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x007d, code lost:
    
        if (androidx.compose.ui.focus.FocusDirection.m284equalsimpl0(r20, 5) == false) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x007f, code lost:
    
        r1 = r5 - r18.bottom;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0088, code lost:
    
        if (androidx.compose.ui.focus.FocusDirection.m284equalsimpl0(r20, 6) == false) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x008a, code lost:
    
        r1 = r18.top - r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00cf, code lost:
    
        throw new java.lang.IllegalStateException("This function should only be used for 2-D focus search");
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x003f, code lost:
    
        if (r7 <= r14) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x004a, code lost:
    
        if (r5 >= r13) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0055, code lost:
    
        if (r15 <= r12) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0034, code lost:
    
        if (r0 >= r2) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x00c0, code lost:
    
        return true;
     */
    /* renamed from: beamBeats-I7lrPNg, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final boolean m299beamBeatsI7lrPNg(androidx.compose.ui.geometry.Rect r17, androidx.compose.ui.geometry.Rect r18, androidx.compose.ui.geometry.Rect r19, int r20) {
        /*
            Method dump skipped, instructions count: 215
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.focus.TwoDimensionalFocusSearchKt.m299beamBeatsI7lrPNg(androidx.compose.ui.geometry.Rect, androidx.compose.ui.geometry.Rect, androidx.compose.ui.geometry.Rect, int):boolean");
    }

    public static final boolean beamBeats_I7lrPNg$inSourceBeam(int i, Rect rect, Rect rect2) {
        if (FocusDirection.m284equalsimpl0(i, 3) ? true : FocusDirection.m284equalsimpl0(i, 4)) {
            if (rect.bottom > rect2.top && rect.top < rect2.bottom) {
                return true;
            }
        } else {
            if (!(FocusDirection.m284equalsimpl0(i, 5) ? true : FocusDirection.m284equalsimpl0(i, 6))) {
                throw new IllegalStateException("This function should only be used for 2-D focus search");
            }
            if (rect.right > rect2.left && rect.left < rect2.right) {
                return true;
            }
        }
        return false;
    }

    public static final void collectAccessibleChildren(FocusTargetNode focusTargetNode, MutableVector mutableVector) {
        Modifier.Node node = focusTargetNode.node;
        if (!node.isAttached) {
            throw new IllegalStateException("visitChildren called on an unattached node");
        }
        MutableVector mutableVector2 = new MutableVector(new Modifier.Node[16]);
        Modifier.Node node2 = node.child;
        if (node2 == null) {
            DelegatableNodeKt.access$addLayoutNodeChildren(mutableVector2, node);
        } else {
            mutableVector2.add(node2);
        }
        while (true) {
            int i = mutableVector2.size;
            if (i == 0) {
                return;
            }
            Modifier.Node node3 = (Modifier.Node) mutableVector2.removeAt(i - 1);
            if ((node3.aggregateChildKindSet & 1024) == 0) {
                DelegatableNodeKt.access$addLayoutNodeChildren(mutableVector2, node3);
            } else {
                while (true) {
                    if (node3 == null) {
                        break;
                    }
                    if ((node3.kindSet & 1024) != 0) {
                        MutableVector mutableVector3 = null;
                        while (node3 != null) {
                            if (node3 instanceof FocusTargetNode) {
                                FocusTargetNode focusTargetNode2 = (FocusTargetNode) node3;
                                if (focusTargetNode2.isAttached && !DelegatableNodeKt.requireLayoutNode(focusTargetNode2).isDeactivated) {
                                    if (focusTargetNode2.fetchFocusProperties$ui_release().canFocus) {
                                        mutableVector.add(focusTargetNode2);
                                    } else {
                                        collectAccessibleChildren(focusTargetNode2, mutableVector);
                                    }
                                }
                            } else if ((node3.kindSet & 1024) != 0 && (node3 instanceof DelegatingNode)) {
                                int i2 = 0;
                                for (Modifier.Node node4 = ((DelegatingNode) node3).delegate; node4 != null; node4 = node4.child) {
                                    if ((node4.kindSet & 1024) != 0) {
                                        i2++;
                                        if (i2 == 1) {
                                            node3 = node4;
                                        } else {
                                            if (mutableVector3 == null) {
                                                mutableVector3 = new MutableVector(new Modifier.Node[16]);
                                            }
                                            if (node3 != null) {
                                                mutableVector3.add(node3);
                                                node3 = null;
                                            }
                                            mutableVector3.add(node4);
                                        }
                                    }
                                }
                                if (i2 == 1) {
                                }
                            }
                            node3 = DelegatableNodeKt.access$pop(mutableVector3);
                        }
                    } else {
                        node3 = node3.child;
                    }
                }
            }
        }
    }

    /* renamed from: findBestCandidate-4WY_MpI, reason: not valid java name */
    public static final FocusTargetNode m300findBestCandidate4WY_MpI(MutableVector mutableVector, Rect rect, int i) {
        Rect translate;
        if (FocusDirection.m284equalsimpl0(i, 3)) {
            translate = rect.translate((rect.right - rect.left) + 1, 0.0f);
        } else if (FocusDirection.m284equalsimpl0(i, 4)) {
            translate = rect.translate(-((rect.right - rect.left) + 1), 0.0f);
        } else if (FocusDirection.m284equalsimpl0(i, 5)) {
            translate = rect.translate(0.0f, (rect.bottom - rect.top) + 1);
        } else {
            if (!FocusDirection.m284equalsimpl0(i, 6)) {
                throw new IllegalStateException("This function should only be used for 2-D focus search");
            }
            translate = rect.translate(0.0f, -((rect.bottom - rect.top) + 1));
        }
        int i2 = mutableVector.size;
        FocusTargetNode focusTargetNode = null;
        if (i2 > 0) {
            Object[] objArr = mutableVector.content;
            int i3 = 0;
            do {
                FocusTargetNode focusTargetNode2 = (FocusTargetNode) objArr[i3];
                if (FocusTraversalKt.isEligibleForFocusSearch(focusTargetNode2)) {
                    Rect focusRect = FocusTraversalKt.focusRect(focusTargetNode2);
                    if (m303isBetterCandidateI7lrPNg(focusRect, translate, rect, i)) {
                        focusTargetNode = focusTargetNode2;
                        translate = focusRect;
                    }
                }
                i3++;
            } while (i3 < i2);
        }
        return focusTargetNode;
    }

    /* renamed from: findChildCorrespondingToFocusEnter--OM-vw8, reason: not valid java name */
    public static final boolean m301findChildCorrespondingToFocusEnterOMvw8(FocusTargetNode focusTargetNode, int i, Function1 function1) {
        Rect rect;
        MutableVector mutableVector = new MutableVector(new FocusTargetNode[16]);
        collectAccessibleChildren(focusTargetNode, mutableVector);
        int i2 = mutableVector.size;
        if (i2 <= 1) {
            FocusTargetNode focusTargetNode2 = (FocusTargetNode) (i2 == 0 ? null : mutableVector.content[0]);
            if (focusTargetNode2 != null) {
                return ((Boolean) function1.invoke(focusTargetNode2)).booleanValue();
            }
            return false;
        }
        if (FocusDirection.m284equalsimpl0(i, 7)) {
            i = 4;
        }
        if (FocusDirection.m284equalsimpl0(i, 4) ? true : FocusDirection.m284equalsimpl0(i, 6)) {
            Rect focusRect = FocusTraversalKt.focusRect(focusTargetNode);
            float f = focusRect.top;
            float f2 = focusRect.left;
            rect = new Rect(f2, f, f2, f);
        } else {
            if (!(FocusDirection.m284equalsimpl0(i, 3) ? true : FocusDirection.m284equalsimpl0(i, 5))) {
                throw new IllegalStateException("This function should only be used for 2-D focus search");
            }
            Rect focusRect2 = FocusTraversalKt.focusRect(focusTargetNode);
            float f3 = focusRect2.bottom;
            float f4 = focusRect2.right;
            rect = new Rect(f4, f3, f4, f3);
        }
        FocusTargetNode m300findBestCandidate4WY_MpI = m300findBestCandidate4WY_MpI(mutableVector, rect, i);
        if (m300findBestCandidate4WY_MpI != null) {
            return ((Boolean) function1.invoke(m300findBestCandidate4WY_MpI)).booleanValue();
        }
        return false;
    }

    /* renamed from: generateAndSearchChildren-4C6V_qg, reason: not valid java name */
    public static final boolean m302generateAndSearchChildren4C6V_qg(final int i, final FocusTargetNode focusTargetNode, final Rect rect, final Function1 function1) {
        if (m304searchChildren4C6V_qg(i, focusTargetNode, rect, function1)) {
            return true;
        }
        Boolean bool = (Boolean) BeyondBoundsLayoutKt.m283searchBeyondBoundsOMvw8(focusTargetNode, i, new Function1() { // from class: androidx.compose.ui.focus.TwoDimensionalFocusSearchKt$generateAndSearchChildren$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                BeyondBoundsLayout.BeyondBoundsScope beyondBoundsScope = (BeyondBoundsLayout.BeyondBoundsScope) obj;
                boolean m304searchChildren4C6V_qg = TwoDimensionalFocusSearchKt.m304searchChildren4C6V_qg(i, focusTargetNode, rect, function1);
                Boolean valueOf = Boolean.valueOf(m304searchChildren4C6V_qg);
                if (m304searchChildren4C6V_qg || !beyondBoundsScope.getHasMoreContent()) {
                    return valueOf;
                }
                return null;
            }
        });
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    /* renamed from: isBetterCandidate-I7lrPNg, reason: not valid java name */
    public static final boolean m303isBetterCandidateI7lrPNg(Rect rect, Rect rect2, Rect rect3, int i) {
        if (isBetterCandidate_I7lrPNg$isCandidate(i, rect, rect3)) {
            return !isBetterCandidate_I7lrPNg$isCandidate(i, rect2, rect3) || m299beamBeatsI7lrPNg(rect3, rect, rect2, i) || (!m299beamBeatsI7lrPNg(rect3, rect2, rect, i) && isBetterCandidate_I7lrPNg$weightedDistance(i, rect3, rect) < isBetterCandidate_I7lrPNg$weightedDistance(i, rect3, rect2));
        }
        return false;
    }

    public static final boolean isBetterCandidate_I7lrPNg$isCandidate(int i, Rect rect, Rect rect2) {
        boolean m284equalsimpl0 = FocusDirection.m284equalsimpl0(i, 3);
        float f = rect.left;
        float f2 = rect.right;
        if (m284equalsimpl0) {
            float f3 = rect2.right;
            float f4 = rect2.left;
            if ((f3 <= f2 && f4 < f2) || f4 <= f) {
                return false;
            }
        } else if (FocusDirection.m284equalsimpl0(i, 4)) {
            float f5 = rect2.left;
            float f6 = rect2.right;
            if ((f5 >= f && f6 > f) || f6 >= f2) {
                return false;
            }
        } else {
            boolean m284equalsimpl02 = FocusDirection.m284equalsimpl0(i, 5);
            float f7 = rect.top;
            float f8 = rect.bottom;
            if (m284equalsimpl02) {
                float f9 = rect2.bottom;
                float f10 = rect2.top;
                if ((f9 <= f8 && f10 < f8) || f10 <= f7) {
                    return false;
                }
            } else {
                if (!FocusDirection.m284equalsimpl0(i, 6)) {
                    throw new IllegalStateException("This function should only be used for 2-D focus search");
                }
                float f11 = rect2.top;
                float f12 = rect2.bottom;
                if ((f11 >= f7 && f12 > f7) || f12 >= f8) {
                    return false;
                }
            }
        }
        return true;
    }

    public static final long isBetterCandidate_I7lrPNg$weightedDistance(int i, Rect rect, Rect rect2) {
        float f;
        float f2;
        boolean m284equalsimpl0 = FocusDirection.m284equalsimpl0(i, 3);
        float f3 = rect2.top;
        float f4 = rect2.bottom;
        float f5 = rect2.left;
        float f6 = rect2.right;
        if (m284equalsimpl0) {
            f = rect.left - f6;
        } else if (FocusDirection.m284equalsimpl0(i, 4)) {
            f = f5 - rect.right;
        } else if (FocusDirection.m284equalsimpl0(i, 5)) {
            f = rect.top - f4;
        } else {
            if (!FocusDirection.m284equalsimpl0(i, 6)) {
                throw new IllegalStateException("This function should only be used for 2-D focus search");
            }
            f = f3 - rect.bottom;
        }
        if (f < 0.0f) {
            f = 0.0f;
        }
        long j = (long) f;
        if (FocusDirection.m284equalsimpl0(i, 3) ? true : FocusDirection.m284equalsimpl0(i, 4)) {
            float f7 = rect.bottom;
            float f8 = rect.top;
            float f9 = 2;
            f2 = (((f7 - f8) / f9) + f8) - (((f4 - f3) / f9) + f3);
        } else {
            if (!(FocusDirection.m284equalsimpl0(i, 5) ? true : FocusDirection.m284equalsimpl0(i, 6))) {
                throw new IllegalStateException("This function should only be used for 2-D focus search");
            }
            float f10 = rect.right;
            float f11 = rect.left;
            float f12 = 2;
            f2 = (((f10 - f11) / f12) + f11) - (((f6 - f5) / f12) + f5);
        }
        long j2 = (long) f2;
        return (j2 * j2) + (13 * j * j);
    }

    /* renamed from: searchChildren-4C6V_qg, reason: not valid java name */
    public static final boolean m304searchChildren4C6V_qg(int i, FocusTargetNode focusTargetNode, Rect rect, Function1 function1) {
        FocusTargetNode m300findBestCandidate4WY_MpI;
        MutableVector mutableVector = new MutableVector(new FocusTargetNode[16]);
        Modifier.Node node = focusTargetNode.node;
        if (!node.isAttached) {
            throw new IllegalStateException("visitChildren called on an unattached node");
        }
        MutableVector mutableVector2 = new MutableVector(new Modifier.Node[16]);
        Modifier.Node node2 = node.child;
        if (node2 == null) {
            DelegatableNodeKt.access$addLayoutNodeChildren(mutableVector2, node);
        } else {
            mutableVector2.add(node2);
        }
        while (true) {
            int i2 = mutableVector2.size;
            if (i2 == 0) {
                break;
            }
            Modifier.Node node3 = (Modifier.Node) mutableVector2.removeAt(i2 - 1);
            if ((node3.aggregateChildKindSet & 1024) == 0) {
                DelegatableNodeKt.access$addLayoutNodeChildren(mutableVector2, node3);
            } else {
                while (true) {
                    if (node3 == null) {
                        break;
                    }
                    if ((node3.kindSet & 1024) != 0) {
                        MutableVector mutableVector3 = null;
                        while (node3 != null) {
                            if (node3 instanceof FocusTargetNode) {
                                FocusTargetNode focusTargetNode2 = (FocusTargetNode) node3;
                                if (focusTargetNode2.isAttached) {
                                    mutableVector.add(focusTargetNode2);
                                }
                            } else if ((node3.kindSet & 1024) != 0 && (node3 instanceof DelegatingNode)) {
                                int i3 = 0;
                                for (Modifier.Node node4 = ((DelegatingNode) node3).delegate; node4 != null; node4 = node4.child) {
                                    if ((node4.kindSet & 1024) != 0) {
                                        i3++;
                                        if (i3 == 1) {
                                            node3 = node4;
                                        } else {
                                            if (mutableVector3 == null) {
                                                mutableVector3 = new MutableVector(new Modifier.Node[16]);
                                            }
                                            if (node3 != null) {
                                                mutableVector3.add(node3);
                                                node3 = null;
                                            }
                                            mutableVector3.add(node4);
                                        }
                                    }
                                }
                                if (i3 == 1) {
                                }
                            }
                            node3 = DelegatableNodeKt.access$pop(mutableVector3);
                        }
                    } else {
                        node3 = node3.child;
                    }
                }
            }
        }
        while (mutableVector.size != 0 && (m300findBestCandidate4WY_MpI = m300findBestCandidate4WY_MpI(mutableVector, rect, i)) != null) {
            if (m300findBestCandidate4WY_MpI.fetchFocusProperties$ui_release().canFocus) {
                return ((Boolean) function1.invoke(m300findBestCandidate4WY_MpI)).booleanValue();
            }
            if (m302generateAndSearchChildren4C6V_qg(i, m300findBestCandidate4WY_MpI, rect, function1)) {
                return true;
            }
            mutableVector.remove(m300findBestCandidate4WY_MpI);
        }
        return false;
    }

    /* renamed from: twoDimensionalFocusSearch-sMXa3k8, reason: not valid java name */
    public static final Boolean m305twoDimensionalFocusSearchsMXa3k8(int i, FocusTargetNode focusTargetNode, Rect rect, Function1 function1) {
        int ordinal = focusTargetNode.getFocusState().ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                FocusTargetNode activeChild = FocusTraversalKt.getActiveChild(focusTargetNode);
                if (activeChild == null) {
                    throw new IllegalStateException("ActiveParent must have a focusedChild");
                }
                int ordinal2 = activeChild.getFocusState().ordinal();
                if (ordinal2 != 0) {
                    if (ordinal2 == 1) {
                        Boolean m305twoDimensionalFocusSearchsMXa3k8 = m305twoDimensionalFocusSearchsMXa3k8(i, activeChild, rect, function1);
                        if (!Intrinsics.areEqual(m305twoDimensionalFocusSearchsMXa3k8, Boolean.FALSE)) {
                            return m305twoDimensionalFocusSearchsMXa3k8;
                        }
                        if (rect == null) {
                            if (activeChild.getFocusState() != FocusStateImpl.ActiveParent) {
                                throw new IllegalStateException("Searching for active node in inactive hierarchy");
                            }
                            FocusTargetNode findActiveFocusNode = FocusTraversalKt.findActiveFocusNode(activeChild);
                            if (findActiveFocusNode == null) {
                                throw new IllegalStateException("ActiveParent must have a focusedChild");
                            }
                            rect = FocusTraversalKt.focusRect(findActiveFocusNode);
                        }
                        return Boolean.valueOf(m302generateAndSearchChildren4C6V_qg(i, focusTargetNode, rect, function1));
                    }
                    if (ordinal2 != 2) {
                        if (ordinal2 != 3) {
                            throw new NoWhenBranchMatchedException();
                        }
                        throw new IllegalStateException("ActiveParent must have a focusedChild");
                    }
                }
                if (rect == null) {
                    rect = FocusTraversalKt.focusRect(activeChild);
                }
                return Boolean.valueOf(m302generateAndSearchChildren4C6V_qg(i, focusTargetNode, rect, function1));
            }
            if (ordinal != 2) {
                if (ordinal == 3) {
                    return focusTargetNode.fetchFocusProperties$ui_release().canFocus ? (Boolean) ((FocusOwnerImpl$focusSearch$1) function1).invoke(focusTargetNode) : rect == null ? Boolean.valueOf(m301findChildCorrespondingToFocusEnterOMvw8(focusTargetNode, i, function1)) : Boolean.valueOf(m304searchChildren4C6V_qg(i, focusTargetNode, rect, function1));
                }
                throw new NoWhenBranchMatchedException();
            }
        }
        return Boolean.valueOf(m301findChildCorrespondingToFocusEnterOMvw8(focusTargetNode, i, function1));
    }
}
