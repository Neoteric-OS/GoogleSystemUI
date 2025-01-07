package androidx.compose.ui.focus;

import androidx.collection.MutableLongSet;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference0;
import kotlin.jvm.internal.Ref$ObjectRef;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FocusOwnerImpl implements FocusOwner {
    public final FocusInvalidationManager focusInvalidationManager;
    public MutableLongSet keysCurrentlyDown;
    public final Function0 onClearFocusForOwner;
    public final Function0 onFocusRectInterop;
    public final Function0 onLayoutDirection;
    public final Function1 onMoveFocusInterop;
    public final Function2 onRequestFocusForOwner;
    public final FocusTargetNode rootFocusNode = new FocusTargetNode(2);
    public final FocusTransactionManager focusTransactionManager = new FocusTransactionManager();
    public final FocusOwnerImpl$modifier$1 modifier = new ModifierNodeElement() { // from class: androidx.compose.ui.focus.FocusOwnerImpl$modifier$1
        @Override // androidx.compose.ui.node.ModifierNodeElement
        public final Modifier.Node create() {
            return FocusOwnerImpl.this.rootFocusNode;
        }

        public final boolean equals(Object obj) {
            return obj == this;
        }

        public final int hashCode() {
            return FocusOwnerImpl.this.rootFocusNode.hashCode();
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public final /* bridge */ /* synthetic */ void update(Modifier.Node node) {
        }
    };

    /* JADX WARN: Type inference failed for: r0v10, types: [androidx.compose.ui.focus.FocusOwnerImpl$modifier$1] */
    public FocusOwnerImpl(Function1 function1, Function2 function2, Function1 function12, Function0 function0, Function0 function02, Function0 function03) {
        this.onRequestFocusForOwner = function2;
        this.onMoveFocusInterop = function12;
        this.onClearFocusForOwner = function0;
        this.onFocusRectInterop = function02;
        this.onLayoutDirection = function03;
        this.focusInvalidationManager = new FocusInvalidationManager(function1, new FocusOwnerImpl$focusInvalidationManager$1(0, this, FocusOwnerImpl.class, "invalidateOwnerFocusState", "invalidateOwnerFocusState()V", 0), new FocusOwnerImpl$focusInvalidationManager$2(this, FocusOwnerImpl.class, "rootState", "getRootState()Landroidx/compose/ui/focus/FocusState;", 0));
    }

    /* renamed from: clearFocus-I7lrPNg, reason: not valid java name */
    public final boolean m288clearFocusI7lrPNg(int i, boolean z, boolean z2) {
        boolean clearFocus;
        int ordinal;
        FocusTransactionManager focusTransactionManager = this.focusTransactionManager;
        FocusOwnerImpl$clearFocus$clearedFocusSuccessfully$1 focusOwnerImpl$clearFocus$clearedFocusSuccessfully$1 = new Function0() { // from class: androidx.compose.ui.focus.FocusOwnerImpl$clearFocus$clearedFocusSuccessfully$1
            @Override // kotlin.jvm.functions.Function0
            public final /* bridge */ /* synthetic */ Object invoke() {
                return Unit.INSTANCE;
            }
        };
        try {
            if (focusTransactionManager.ongoingTransaction) {
                FocusTransactionManager.access$cancelTransaction(focusTransactionManager);
            }
            focusTransactionManager.ongoingTransaction = true;
            if (focusOwnerImpl$clearFocus$clearedFocusSuccessfully$1 != null) {
                focusTransactionManager.cancellationListener.add(focusOwnerImpl$clearFocus$clearedFocusSuccessfully$1);
            }
            FocusTargetNode focusTargetNode = this.rootFocusNode;
            if (!z && ((ordinal = FocusTransactionsKt.m293performCustomClearFocusMxy_nc0(focusTargetNode, i).ordinal()) == 1 || ordinal == 2 || ordinal == 3)) {
                clearFocus = false;
                if (clearFocus && z2) {
                    this.onClearFocusForOwner.invoke();
                }
                return clearFocus;
            }
            clearFocus = FocusTransactionsKt.clearFocus(focusTargetNode, z);
            if (clearFocus) {
                this.onClearFocusForOwner.invoke();
            }
            return clearFocus;
        } finally {
            FocusTransactionManager.access$commitTransaction(focusTransactionManager);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x005c, code lost:
    
        if (r7 == null) goto L33;
     */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0168 A[Catch: all -> 0x001b, TryCatch #0 {all -> 0x001b, blocks: (B:3:0x0007, B:5:0x0010, B:9:0x001e, B:13:0x0028, B:16:0x0034, B:18:0x003a, B:19:0x003f, B:21:0x0047, B:23:0x004c, B:25:0x0052, B:29:0x0058, B:34:0x0168, B:36:0x016e, B:38:0x0177, B:41:0x0183, B:45:0x018d, B:80:0x0193, B:81:0x0198, B:74:0x01d2, B:47:0x019c, B:49:0x01a2, B:51:0x01a6, B:53:0x01ae, B:55:0x01b4, B:61:0x01bc, B:63:0x01c5, B:64:0x01c9, B:59:0x01cc, B:83:0x01d7, B:86:0x01da, B:88:0x01e0, B:95:0x01e4, B:100:0x01eb, B:102:0x01f3, B:110:0x020a, B:111:0x0218, B:113:0x021c, B:152:0x0220, B:147:0x0276, B:115:0x022c, B:117:0x0235, B:119:0x0239, B:121:0x0240, B:123:0x0246, B:125:0x024a, B:128:0x024d, B:130:0x0253, B:131:0x025a, B:133:0x0262, B:134:0x0267, B:136:0x026d, B:127:0x0270, B:158:0x0281, B:162:0x0291, B:163:0x029f, B:165:0x02a3, B:204:0x02a7, B:199:0x02fd, B:167:0x02b3, B:169:0x02bc, B:171:0x02c0, B:173:0x02c7, B:175:0x02cd, B:177:0x02d1, B:180:0x02d4, B:182:0x02da, B:183:0x02e1, B:185:0x02e9, B:186:0x02ee, B:188:0x02f4, B:179:0x02f7, B:211:0x030a, B:213:0x0311, B:220:0x0324, B:221:0x0329, B:228:0x0060, B:230:0x0066, B:232:0x006c, B:235:0x0078, B:239:0x0082, B:274:0x00d5, B:276:0x00d9, B:241:0x0087, B:243:0x008d, B:245:0x0091, B:247:0x0099, B:249:0x009f, B:255:0x00a7, B:257:0x00b0, B:258:0x00b4, B:253:0x00b7, B:264:0x00bd, B:278:0x00c2, B:281:0x00c5, B:283:0x00cb, B:290:0x00cf, B:295:0x00df, B:296:0x00e4, B:297:0x00e5, B:299:0x00eb, B:301:0x00f3, B:304:0x00ff, B:308:0x0109, B:343:0x015c, B:345:0x0160, B:310:0x010e, B:312:0x0114, B:314:0x0118, B:316:0x0120, B:318:0x0126, B:324:0x012e, B:326:0x0137, B:327:0x013b, B:322:0x013e, B:333:0x0144, B:348:0x0149, B:351:0x014c, B:353:0x0152, B:360:0x0156, B:365:0x032e, B:366:0x0333), top: B:2:0x0007 }] */
    /* renamed from: dispatchKeyEvent-YhN2O0w, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean m289dispatchKeyEventYhN2O0w(android.view.KeyEvent r13, kotlin.jvm.functions.Function0 r14) {
        /*
            Method dump skipped, instructions count: 824
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.focus.FocusOwnerImpl.m289dispatchKeyEventYhN2O0w(android.view.KeyEvent, kotlin.jvm.functions.Function0):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r15v4, types: [java.lang.Boolean] */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v14 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r5v6, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r5v9, types: [androidx.compose.runtime.collection.MutableVector] */
    /* renamed from: focusSearch-ULY8qGw, reason: not valid java name */
    public final Boolean m290focusSearchULY8qGw(int i, Rect rect, Function1 function1) {
        FocusTargetNode focusTargetNode;
        FocusTargetNode focusTargetNode2;
        NodeChain nodeChain;
        boolean backwardFocusSearch;
        FocusRequester focusRequester;
        FocusRequester focusRequester2;
        FocusTargetNode focusTargetNode3 = this.rootFocusNode;
        FocusTargetNode findActiveFocusNode = FocusTraversalKt.findActiveFocusNode(focusTargetNode3);
        Function0 function0 = this.onLayoutDirection;
        int i2 = 4;
        if (findActiveFocusNode != null) {
            LayoutDirection layoutDirection = (LayoutDirection) ((MutablePropertyReference0) function0).get();
            FocusPropertiesImpl fetchFocusProperties$ui_release = findActiveFocusNode.fetchFocusProperties$ui_release();
            if (FocusDirection.m284equalsimpl0(i, 1)) {
                focusRequester = fetchFocusProperties$ui_release.next;
            } else if (FocusDirection.m284equalsimpl0(i, 2)) {
                focusRequester = fetchFocusProperties$ui_release.previous;
            } else if (FocusDirection.m284equalsimpl0(i, 5)) {
                focusRequester = fetchFocusProperties$ui_release.up;
            } else if (FocusDirection.m284equalsimpl0(i, 6)) {
                focusRequester = fetchFocusProperties$ui_release.down;
            } else if (FocusDirection.m284equalsimpl0(i, 3)) {
                int ordinal = layoutDirection.ordinal();
                if (ordinal == 0) {
                    focusRequester2 = fetchFocusProperties$ui_release.start;
                } else {
                    if (ordinal != 1) {
                        throw new NoWhenBranchMatchedException();
                    }
                    focusRequester2 = fetchFocusProperties$ui_release.end;
                }
                if (focusRequester2 == FocusRequester.Default) {
                    focusRequester2 = null;
                }
                if (focusRequester2 == null) {
                    focusRequester = fetchFocusProperties$ui_release.left;
                }
                focusRequester = focusRequester2;
            } else if (FocusDirection.m284equalsimpl0(i, 4)) {
                int ordinal2 = layoutDirection.ordinal();
                if (ordinal2 == 0) {
                    focusRequester2 = fetchFocusProperties$ui_release.end;
                } else {
                    if (ordinal2 != 1) {
                        throw new NoWhenBranchMatchedException();
                    }
                    focusRequester2 = fetchFocusProperties$ui_release.start;
                }
                if (focusRequester2 == FocusRequester.Default) {
                    focusRequester2 = null;
                }
                if (focusRequester2 == null) {
                    focusRequester = fetchFocusProperties$ui_release.right;
                }
                focusRequester = focusRequester2;
            } else if (FocusDirection.m284equalsimpl0(i, 7)) {
                focusRequester = (FocusRequester) fetchFocusProperties$ui_release.enter.invoke(new FocusDirection(i));
            } else {
                if (!FocusDirection.m284equalsimpl0(i, 8)) {
                    throw new IllegalStateException("invalid FocusDirection");
                }
                focusRequester = (FocusRequester) fetchFocusProperties$ui_release.exit.invoke(new FocusDirection(i));
            }
            if (Intrinsics.areEqual(focusRequester, FocusRequester.Cancel)) {
                return null;
            }
            focusTargetNode = null;
            if (!Intrinsics.areEqual(focusRequester, FocusRequester.Default)) {
                return Boolean.valueOf(focusRequester.findFocusTargetNode$ui_release(function1));
            }
        } else {
            focusTargetNode = null;
            findActiveFocusNode = null;
        }
        LayoutDirection layoutDirection2 = (LayoutDirection) ((MutablePropertyReference0) function0).get();
        FocusOwnerImpl$focusSearch$1 focusOwnerImpl$focusSearch$1 = new FocusOwnerImpl$focusSearch$1(findActiveFocusNode, this, function1);
        if (FocusDirection.m284equalsimpl0(i, 1) ? true : FocusDirection.m284equalsimpl0(i, 2)) {
            if (FocusDirection.m284equalsimpl0(i, 1)) {
                backwardFocusSearch = OneDimensionalFocusSearchKt.forwardFocusSearch(focusTargetNode3, focusOwnerImpl$focusSearch$1);
            } else {
                if (!FocusDirection.m284equalsimpl0(i, 2)) {
                    throw new IllegalStateException("This function should only be used for 1-D focus search");
                }
                backwardFocusSearch = OneDimensionalFocusSearchKt.backwardFocusSearch(focusTargetNode3, focusOwnerImpl$focusSearch$1);
            }
            return Boolean.valueOf(backwardFocusSearch);
        }
        if (FocusDirection.m284equalsimpl0(i, 3) ? true : FocusDirection.m284equalsimpl0(i, 4) ? true : FocusDirection.m284equalsimpl0(i, 5) ? true : FocusDirection.m284equalsimpl0(i, 6)) {
            return TwoDimensionalFocusSearchKt.m305twoDimensionalFocusSearchsMXa3k8(i, focusTargetNode3, rect, focusOwnerImpl$focusSearch$1);
        }
        if (FocusDirection.m284equalsimpl0(i, 7)) {
            int ordinal3 = layoutDirection2.ordinal();
            if (ordinal3 != 0) {
                if (ordinal3 != 1) {
                    throw new NoWhenBranchMatchedException();
                }
                i2 = 3;
            }
            FocusTargetNode findActiveFocusNode2 = FocusTraversalKt.findActiveFocusNode(focusTargetNode3);
            return findActiveFocusNode2 != null ? TwoDimensionalFocusSearchKt.m305twoDimensionalFocusSearchsMXa3k8(i2, findActiveFocusNode2, rect, focusOwnerImpl$focusSearch$1) : focusTargetNode;
        }
        if (!FocusDirection.m284equalsimpl0(i, 8)) {
            throw new IllegalStateException(("Focus search invoked with invalid FocusDirection " + ((Object) FocusDirection.m285toStringimpl(i))).toString());
        }
        FocusTargetNode findActiveFocusNode3 = FocusTraversalKt.findActiveFocusNode(focusTargetNode3);
        boolean z = false;
        if (findActiveFocusNode3 != null) {
            Modifier.Node node = findActiveFocusNode3.node;
            if (!node.isAttached) {
                throw new IllegalStateException("visitAncestors called on an unattached node");
            }
            Modifier.Node node2 = node.parent;
            LayoutNode requireLayoutNode = DelegatableNodeKt.requireLayoutNode(findActiveFocusNode3);
            loop0: while (requireLayoutNode != null) {
                if ((requireLayoutNode.nodes.head.aggregateChildKindSet & 1024) != 0) {
                    while (node2 != null) {
                        if ((node2.kindSet & 1024) != 0) {
                            Modifier.Node node3 = node2;
                            ?? r5 = focusTargetNode;
                            while (node3 != null) {
                                if (node3 instanceof FocusTargetNode) {
                                    FocusTargetNode focusTargetNode4 = (FocusTargetNode) node3;
                                    if (focusTargetNode4.fetchFocusProperties$ui_release().canFocus) {
                                        focusTargetNode2 = focusTargetNode4;
                                        break loop0;
                                    }
                                } else if ((node3.kindSet & 1024) != 0 && (node3 instanceof DelegatingNode)) {
                                    Modifier.Node node4 = ((DelegatingNode) node3).delegate;
                                    int i3 = 0;
                                    r5 = r5;
                                    while (node4 != null) {
                                        if ((node4.kindSet & 1024) != 0) {
                                            i3++;
                                            r5 = r5;
                                            if (i3 == 1) {
                                                node3 = node4;
                                            } else {
                                                if (r5 == 0) {
                                                    r5 = new MutableVector(new Modifier.Node[16]);
                                                }
                                                if (node3 != null) {
                                                    r5.add(node3);
                                                    node3 = focusTargetNode;
                                                }
                                                r5.add(node4);
                                            }
                                        }
                                        node4 = node4.child;
                                        r5 = r5;
                                    }
                                    if (i3 == 1) {
                                    }
                                }
                                node3 = DelegatableNodeKt.access$pop(r5);
                            }
                        }
                        node2 = node2.parent;
                    }
                }
                requireLayoutNode = requireLayoutNode.getParent$ui_release();
                node2 = (requireLayoutNode == null || (nodeChain = requireLayoutNode.nodes) == null) ? focusTargetNode : nodeChain.tail;
            }
        }
        focusTargetNode2 = focusTargetNode;
        if (focusTargetNode2 != null && !focusTargetNode2.equals(focusTargetNode3)) {
            z = ((Boolean) focusOwnerImpl$focusSearch$1.invoke(focusTargetNode2)).booleanValue();
        }
        return Boolean.valueOf(z);
    }

    /* renamed from: moveFocus-3ESFkO8, reason: not valid java name */
    public final boolean m291moveFocus3ESFkO8(final int i) {
        final Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
        ref$ObjectRef.element = Boolean.FALSE;
        Boolean m290focusSearchULY8qGw = m290focusSearchULY8qGw(i, (Rect) this.onFocusRectInterop.invoke(), new Function1() { // from class: androidx.compose.ui.focus.FocusOwnerImpl$moveFocus$focusSearchSuccess$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Ref$ObjectRef.this.element = FocusTransactionsKt.m296requestFocusMxy_nc0((FocusTargetNode) obj, i);
                Boolean bool = (Boolean) Ref$ObjectRef.this.element;
                return Boolean.valueOf(bool != null ? bool.booleanValue() : false);
            }
        });
        if (m290focusSearchULY8qGw == null || ref$ObjectRef.element == null) {
            return false;
        }
        Boolean bool = Boolean.TRUE;
        if (m290focusSearchULY8qGw.equals(bool) && Intrinsics.areEqual(ref$ObjectRef.element, bool)) {
            return true;
        }
        if (!(FocusDirection.m284equalsimpl0(i, 1) ? true : FocusDirection.m284equalsimpl0(i, 2))) {
            return ((Boolean) this.onMoveFocusInterop.invoke(new FocusDirection(i))).booleanValue();
        }
        if (!m288clearFocusI7lrPNg(i, false, false)) {
            return false;
        }
        Boolean m290focusSearchULY8qGw2 = m290focusSearchULY8qGw(i, null, new Function1() { // from class: androidx.compose.ui.focus.FocusOwnerImpl$takeFocus$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Boolean m296requestFocusMxy_nc0 = FocusTransactionsKt.m296requestFocusMxy_nc0((FocusTargetNode) obj, i);
                return Boolean.valueOf(m296requestFocusMxy_nc0 != null ? m296requestFocusMxy_nc0.booleanValue() : false);
            }
        });
        return m290focusSearchULY8qGw2 != null ? m290focusSearchULY8qGw2.booleanValue() : false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:101:0x0356, code lost:
    
        r10 = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0096, code lost:
    
        r13 = r29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x00a0, code lost:
    
        if (((((~r8) << 6) & r8) & (-9187201950435737472L)) == r14) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x00a2, code lost:
    
        r0 = r13.findFirstAvailableSlot(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x00a8, code lost:
    
        if (r13.growthLimit != 0) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00bc, code lost:
    
        if (((r13.metadata[r0 >> 3] >> ((r0 & 7) << 3)) & 255) != 254) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00c5, code lost:
    
        r0 = r13._capacity;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00c9, code lost:
    
        if (r0 <= 8) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00da, code lost:
    
        if (java.lang.Long.compareUnsigned(r13._size * 32, r0 * 25) > 0) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00dc, code lost:
    
        r0 = r13.metadata;
        r4 = r13._capacity;
        r7 = r13.elements;
        r8 = (r4 + 7) >> 3;
        r9 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00e8, code lost:
    
        if (r9 >= r8) goto L90;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00ea, code lost:
    
        r14 = r0[r9] & (-9187201950435737472L);
        r0[r9] = ((~r14) + (r14 >>> 7)) & (-72340172838076674L);
        r9 = r9 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00ff, code lost:
    
        r5 = r0.length;
        r6 = r5 - 1;
        r5 = r5 - 2;
        r0[r5] = (r0[r5] & 72057594037927935L) | (-72057594037927936L);
        r0[r6] = r0[0];
        r5 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0118, code lost:
    
        if (r5 == r4) goto L92;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x011a, code lost:
    
        r6 = r5 >> 3;
        r10 = (r5 & 7) << 3;
        r8 = (r0[r6] >> r10) & 255;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x012d, code lost:
    
        if (r8 != 128) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0136, code lost:
    
        if (r8 == 254) goto L91;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0139, code lost:
    
        r8 = java.lang.Long.hashCode(r7[r5]) * (-862048943);
        r9 = (r8 ^ (r8 << 16)) >>> 7;
        r16 = r13.findFirstAvailableSlot(r9);
        r9 = r9 & r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x015a, code lost:
    
        if ((((r16 - r9) & r4) / 8) != (((r5 - r9) & r4) / 8)) goto L94;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0186, code lost:
    
        r34 = r1;
        r1 = r16 >> 3;
        r14 = r0[r1];
        r9 = (r16 & 7) << 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x019c, code lost:
    
        if (((r14 >> r9) & 255) != 128) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x019e, code lost:
    
        r39 = r3;
        r36 = r11;
        r0[r1] = ((r8 & 127) << r9) | ((~(255 << r9)) & r14);
        r0[r6] = (r0[r6] & (~(255 << r10))) | (128 << r10);
        r7[r16] = r7[r5];
        r7[r5] = 0;
        r38 = r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x01e2, code lost:
    
        r0[r0.length - 1] = (r0[0] & 72057594037927935L) | Long.MIN_VALUE;
        r5 = r5 + 1;
        r3 = r39;
        r1 = r34;
        r11 = r36;
        r13 = r38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x01c5, code lost:
    
        r39 = r3;
        r36 = r11;
        r38 = r13;
        r0[r1] = ((r8 & 127) << r9) | ((~(255 << r9)) & r14);
        r1 = r7[r16];
        r7[r16] = r7[r5];
        r7[r5] = r1;
        r5 = r5 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x015c, code lost:
    
        r0[r6] = ((~(255 << r10)) & r0[r6]) | ((r8 & 127) << r10);
        r0[r0.length - 1] = (r0[0] & 72057594037927935L) | Long.MIN_VALUE;
        r5 = r5 + 1;
        r1 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x012f, code lost:
    
        r5 = r5 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x01fd, code lost:
    
        r34 = r1;
        r39 = r3;
        r36 = r11;
        r2 = r13;
        r2.growthLimit = androidx.collection.ScatterMapKt.loadedCapacity(r2._capacity) - r2._size;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x020f, code lost:
    
        r1 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0285, code lost:
    
        r0 = r1.findFirstAvailableSlot(r39);
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0289, code lost:
    
        r30 = r0;
        r1._size++;
        r0 = r1.growthLimit;
        r2 = r1.metadata;
        r4 = r30 >> 3;
        r5 = r2[r4];
        r7 = (r30 & 7) << 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x02a6, code lost:
    
        if (((r5 >> r7) & 255) != 128) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x02a8, code lost:
    
        r22 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x02aa, code lost:
    
        r1.growthLimit = r0 - r22;
        r0 = r1._capacity;
        r5 = (r5 & (~(255 << r7))) | (r36 << r7);
        r2[r4] = r5;
        r2[(((r30 - 7) & r0) + (r0 & 7)) >> 3] = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0214, code lost:
    
        r34 = r1;
        r39 = r3;
        r36 = r11;
        r2 = r13;
        r0 = androidx.collection.ScatterMapKt.nextCapacity(r2._capacity);
        r3 = r2.metadata;
        r4 = r2.elements;
        r5 = r2._capacity;
        r2.initializeStorage(r0);
        r0 = r2.metadata;
        r6 = r2.elements;
        r7 = r2._capacity;
        r8 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0232, code lost:
    
        if (r8 >= r5) goto L101;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0244, code lost:
    
        if (((r3[r8 >> 3] >> ((r8 & 7) << 3)) & 255) >= 128) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0246, code lost:
    
        r9 = r4[r8];
        r11 = java.lang.Long.hashCode(r9) * (-862048943);
        r11 = r11 ^ (r11 << 16);
        r12 = r2.findFirstAvailableSlot(r11 >>> 7);
        r13 = r11 & 127;
        r11 = r12 >> 3;
        r15 = (r12 & 7) << 3;
        r38 = r2;
        r1 = (r0[r11] & (~(255 << r15))) | (r13 << r15);
        r0[r11] = r1;
        r0[(((r12 - 7) & r7) + (r7 & 7)) >> 3] = r1;
        r6[r12] = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0280, code lost:
    
        r8 = r8 + 1;
        r2 = r38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x027e, code lost:
    
        r38 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x00be, code lost:
    
        r34 = r1;
        r36 = r11;
        r1 = r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x0354, code lost:
    
        if (((r6 & ((~r6) << 6)) & (-9187201950435737472L)) == 0) goto L83;
     */
    /* renamed from: validateKeyEvent-ZmokQxo, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean m292validateKeyEventZmokQxo(android.view.KeyEvent r40) {
        /*
            Method dump skipped, instructions count: 920
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.focus.FocusOwnerImpl.m292validateKeyEventZmokQxo(android.view.KeyEvent):boolean");
    }
}
