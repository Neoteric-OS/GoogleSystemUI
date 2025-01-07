package androidx.compose.foundation.lazy.layout;

import androidx.collection.MutableScatterMap;
import androidx.collection.MutableScatterSet;
import androidx.collection.ScatterMapKt;
import androidx.collection.ScatterSetKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.graphics.layer.GraphicsLayerKt;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.LayoutNodeDrawScope;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LazyLayoutItemAnimator {
    public final List disappearingItems;
    public DrawModifierNode displayingNode;
    public int firstVisibleIndex;
    public LazyLayoutKeyIndexMap keyIndexMap;
    public final MutableScatterMap keyToItemInfoMap;
    public final Modifier modifier;
    public final MutableScatterSet movingAwayKeys;
    public final List movingAwayToEndBound;
    public final List movingAwayToStartBound;
    public final List movingInFromEndBound;
    public final List movingInFromStartBound;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class DisplayingDisappearingItemsElement extends ModifierNodeElement {
        public final LazyLayoutItemAnimator animator;

        public DisplayingDisappearingItemsElement(LazyLayoutItemAnimator lazyLayoutItemAnimator) {
            this.animator = lazyLayoutItemAnimator;
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public final Modifier.Node create() {
            DisplayingDisappearingItemsNode displayingDisappearingItemsNode = new DisplayingDisappearingItemsNode();
            displayingDisappearingItemsNode.animator = this.animator;
            return displayingDisappearingItemsNode;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof DisplayingDisappearingItemsElement) && Intrinsics.areEqual(this.animator, ((DisplayingDisappearingItemsElement) obj).animator);
        }

        public final int hashCode() {
            return this.animator.hashCode();
        }

        public final String toString() {
            return "DisplayingDisappearingItemsElement(animator=" + this.animator + ')';
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public final void update(Modifier.Node node) {
            DisplayingDisappearingItemsNode displayingDisappearingItemsNode = (DisplayingDisappearingItemsNode) node;
            LazyLayoutItemAnimator lazyLayoutItemAnimator = displayingDisappearingItemsNode.animator;
            LazyLayoutItemAnimator lazyLayoutItemAnimator2 = this.animator;
            if (Intrinsics.areEqual(lazyLayoutItemAnimator, lazyLayoutItemAnimator2) || !displayingDisappearingItemsNode.node.isAttached) {
                return;
            }
            LazyLayoutItemAnimator lazyLayoutItemAnimator3 = displayingDisappearingItemsNode.animator;
            lazyLayoutItemAnimator3.releaseAnimations();
            lazyLayoutItemAnimator3.keyIndexMap = null;
            lazyLayoutItemAnimator3.firstVisibleIndex = -1;
            lazyLayoutItemAnimator2.displayingNode = displayingDisappearingItemsNode;
            displayingDisappearingItemsNode.animator = lazyLayoutItemAnimator2;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class DisplayingDisappearingItemsNode extends Modifier.Node implements DrawModifierNode {
        public LazyLayoutItemAnimator animator;

        @Override // androidx.compose.ui.node.DrawModifierNode
        public final void draw(LayoutNodeDrawScope layoutNodeDrawScope) {
            ArrayList arrayList = (ArrayList) this.animator.disappearingItems;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                LazyLayoutItemAnimation lazyLayoutItemAnimation = (LazyLayoutItemAnimation) arrayList.get(i);
                GraphicsLayer graphicsLayer = lazyLayoutItemAnimation.layer;
                if (graphicsLayer != null) {
                    long j = lazyLayoutItemAnimation.finalOffset;
                    long j2 = graphicsLayer.topLeft;
                    float f = ((int) (j >> 32)) - ((int) (j2 >> 32));
                    float f2 = ((int) (j & 4294967295L)) - ((int) (4294967295L & j2));
                    CanvasDrawScope canvasDrawScope = layoutNodeDrawScope.canvasDrawScope;
                    canvasDrawScope.drawContext.transform.translate(f, f2);
                    try {
                        GraphicsLayerKt.drawLayer(layoutNodeDrawScope, graphicsLayer);
                    } finally {
                        canvasDrawScope.drawContext.transform.translate(-f, -f2);
                    }
                }
            }
            layoutNodeDrawScope.drawContent();
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof DisplayingDisappearingItemsNode) && Intrinsics.areEqual(this.animator, ((DisplayingDisappearingItemsNode) obj).animator);
        }

        public final int hashCode() {
            return this.animator.hashCode();
        }

        @Override // androidx.compose.ui.Modifier.Node
        public final void onAttach() {
            this.animator.displayingNode = this;
        }

        @Override // androidx.compose.ui.Modifier.Node
        public final void onDetach() {
            LazyLayoutItemAnimator lazyLayoutItemAnimator = this.animator;
            lazyLayoutItemAnimator.releaseAnimations();
            lazyLayoutItemAnimator.keyIndexMap = null;
            lazyLayoutItemAnimator.firstVisibleIndex = -1;
        }

        public final String toString() {
            return "DisplayingDisappearingItemsNode(animator=" + this.animator + ')';
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class ItemInfo {
        public Constraints constraints;
        public int crossAxisOffset;
        public int lane;
        public int layoutMaxOffset;
        public int layoutMinOffset;
        public LazyLayoutItemAnimation[] animations = LazyLayoutItemAnimatorKt.EmptyArray;
        public int span = 1;

        public ItemInfo() {
        }

        public static void updateAnimation$default(ItemInfo itemInfo, LazyLayoutMeasuredItem lazyLayoutMeasuredItem, CoroutineScope coroutineScope, GraphicsContext graphicsContext, int i, int i2) {
            LazyLayoutItemAnimator.this.getClass();
            long mo124getOffsetBjo55l4 = lazyLayoutMeasuredItem.mo124getOffsetBjo55l4(0);
            itemInfo.updateAnimation(lazyLayoutMeasuredItem, coroutineScope, graphicsContext, i, i2, (int) (!lazyLayoutMeasuredItem.isVertical() ? mo124getOffsetBjo55l4 & 4294967295L : mo124getOffsetBjo55l4 >> 32));
        }

        public final void updateAnimation(LazyLayoutMeasuredItem lazyLayoutMeasuredItem, CoroutineScope coroutineScope, GraphicsContext graphicsContext, int i, int i2, int i3) {
            LazyLayoutItemAnimation[] lazyLayoutItemAnimationArr = this.animations;
            int length = lazyLayoutItemAnimationArr.length;
            int i4 = 0;
            while (true) {
                if (i4 >= length) {
                    this.layoutMinOffset = i;
                    this.layoutMaxOffset = i2;
                    break;
                } else {
                    LazyLayoutItemAnimation lazyLayoutItemAnimation = lazyLayoutItemAnimationArr[i4];
                    if (lazyLayoutItemAnimation != null && lazyLayoutItemAnimation.isRunningMovingAwayAnimation) {
                        break;
                    } else {
                        i4++;
                    }
                }
            }
            int length2 = this.animations.length;
            for (int placeablesCount = lazyLayoutMeasuredItem.getPlaceablesCount(); placeablesCount < length2; placeablesCount++) {
                LazyLayoutItemAnimation lazyLayoutItemAnimation2 = this.animations[placeablesCount];
                if (lazyLayoutItemAnimation2 != null) {
                    lazyLayoutItemAnimation2.release();
                }
            }
            if (this.animations.length != lazyLayoutMeasuredItem.getPlaceablesCount()) {
                this.animations = (LazyLayoutItemAnimation[]) Arrays.copyOf(this.animations, lazyLayoutMeasuredItem.getPlaceablesCount());
            }
            this.constraints = new Constraints(lazyLayoutMeasuredItem.mo122getConstraintsmsEJaDk());
            this.crossAxisOffset = i3;
            this.lane = lazyLayoutMeasuredItem.getLane();
            this.span = lazyLayoutMeasuredItem.getSpan();
            int placeablesCount2 = lazyLayoutMeasuredItem.getPlaceablesCount();
            for (int i5 = 0; i5 < placeablesCount2; i5++) {
                Object parentData = lazyLayoutMeasuredItem.getParentData(i5);
                LazyLayoutAnimationSpecsNode lazyLayoutAnimationSpecsNode = parentData instanceof LazyLayoutAnimationSpecsNode ? (LazyLayoutAnimationSpecsNode) parentData : null;
                if (lazyLayoutAnimationSpecsNode == null) {
                    LazyLayoutItemAnimation lazyLayoutItemAnimation3 = this.animations[i5];
                    if (lazyLayoutItemAnimation3 != null) {
                        lazyLayoutItemAnimation3.release();
                    }
                    this.animations[i5] = null;
                } else {
                    LazyLayoutItemAnimation lazyLayoutItemAnimation4 = this.animations[i5];
                    if (lazyLayoutItemAnimation4 == null) {
                        lazyLayoutItemAnimation4 = new LazyLayoutItemAnimation(coroutineScope, graphicsContext, new LazyLayoutItemAnimator$ItemInfo$updateAnimation$1$animation$1(LazyLayoutItemAnimator.this));
                        this.animations[i5] = lazyLayoutItemAnimation4;
                    }
                    lazyLayoutItemAnimation4.fadeInSpec = lazyLayoutAnimationSpecsNode.fadeInSpec;
                    lazyLayoutItemAnimation4.placementSpec = lazyLayoutAnimationSpecsNode.placementSpec;
                    lazyLayoutItemAnimation4.fadeOutSpec = lazyLayoutAnimationSpecsNode.fadeOutSpec;
                }
            }
        }
    }

    public LazyLayoutItemAnimator() {
        long[] jArr = ScatterMapKt.EmptyGroup;
        this.keyToItemInfoMap = new MutableScatterMap();
        int i = ScatterSetKt.$r8$clinit;
        this.movingAwayKeys = new MutableScatterSet();
        this.movingInFromStartBound = new ArrayList();
        this.movingInFromEndBound = new ArrayList();
        this.movingAwayToStartBound = new ArrayList();
        this.movingAwayToEndBound = new ArrayList();
        this.disappearingItems = new ArrayList();
        this.modifier = new DisplayingDisappearingItemsElement(this);
    }

    public static void initializeAnimation(LazyLayoutMeasuredItem lazyLayoutMeasuredItem, int i, ItemInfo itemInfo) {
        int i2 = 0;
        long mo124getOffsetBjo55l4 = lazyLayoutMeasuredItem.mo124getOffsetBjo55l4(0);
        long m673copyiSbpLlY$default = lazyLayoutMeasuredItem.isVertical() ? IntOffset.m673copyiSbpLlY$default(0, i, mo124getOffsetBjo55l4, 1) : IntOffset.m673copyiSbpLlY$default(i, 0, mo124getOffsetBjo55l4, 2);
        LazyLayoutItemAnimation[] lazyLayoutItemAnimationArr = itemInfo.animations;
        int length = lazyLayoutItemAnimationArr.length;
        int i3 = 0;
        while (i2 < length) {
            LazyLayoutItemAnimation lazyLayoutItemAnimation = lazyLayoutItemAnimationArr[i2];
            int i4 = i3 + 1;
            if (lazyLayoutItemAnimation != null) {
                lazyLayoutItemAnimation.rawOffset = IntOffset.m676plusqkQi6aY(m673copyiSbpLlY$default, IntOffset.m675minusqkQi6aY(lazyLayoutMeasuredItem.mo124getOffsetBjo55l4(i3), mo124getOffsetBjo55l4));
            }
            i2++;
            i3 = i4;
        }
    }

    public static int updateAndReturnOffsetFor(int[] iArr, LazyLayoutMeasuredItem lazyLayoutMeasuredItem) {
        int lane = lazyLayoutMeasuredItem.getLane();
        int span = lazyLayoutMeasuredItem.getSpan() + lane;
        int i = 0;
        while (lane < span) {
            int mainAxisSizeWithSpacings = lazyLayoutMeasuredItem.getMainAxisSizeWithSpacings() + iArr[lane];
            iArr[lane] = mainAxisSizeWithSpacings;
            i = Math.max(i, mainAxisSizeWithSpacings);
            lane++;
        }
        return i;
    }

    public final LazyLayoutItemAnimation getAnimation(int i, Object obj) {
        ItemInfo itemInfo = (ItemInfo) this.keyToItemInfoMap.get(obj);
        if (itemInfo != null) {
            return itemInfo.animations[i];
        }
        return null;
    }

    /* renamed from: getMinSizeToFitDisappearingItems-YbymL2g, reason: not valid java name */
    public final long m134getMinSizeToFitDisappearingItemsYbymL2g() {
        List list = this.disappearingItems;
        int size = list.size();
        long j = 0;
        for (int i = 0; i < size; i++) {
            LazyLayoutItemAnimation lazyLayoutItemAnimation = (LazyLayoutItemAnimation) ((ArrayList) list).get(i);
            GraphicsLayer graphicsLayer = lazyLayoutItemAnimation.layer;
            if (graphicsLayer != null) {
                j = (Math.max((int) (j & 4294967295L), ((int) (lazyLayoutItemAnimation.rawOffset & 4294967295L)) + ((int) (graphicsLayer.size & 4294967295L))) & 4294967295L) | (Math.max((int) (j >> 32), ((int) (lazyLayoutItemAnimation.rawOffset >> 32)) + ((int) (graphicsLayer.size >> 32))) << 32);
            }
        }
        return j;
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x01ff, code lost:
    
        r28 = r4;
        r29 = r14;
        r50 = r15;
        r14 = r26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x0207, code lost:
    
        if (r12 == false) goto L118;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x0209, code lost:
    
        r3 = r10.animations;
        r4 = r3.length;
        r5 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x020d, code lost:
    
        if (r5 >= r4) goto L281;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x020f, code lost:
    
        r8 = r3[r5];
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x0211, code lost:
    
        if (r8 == null) goto L283;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x0217, code lost:
    
        if (r8.isDisappearanceAnimationInProgress() == false) goto L116;
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x0219, code lost:
    
        r40.disappearingItems.remove(r8);
        r9 = r40.displayingNode;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x0220, code lost:
    
        if (r9 == null) goto L116;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x0222, code lost:
    
        androidx.compose.ui.node.DrawModifierNodeKt.invalidateDraw(r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x0225, code lost:
    
        r8.animateAppearance();
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x0228, code lost:
    
        r5 = r5 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x022b, code lost:
    
        startPlacementAnimationsIfNeeded(r6, false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x0137, code lost:
    
        r12 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x0130, code lost:
    
        r11 = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x0115, code lost:
    
        r12 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:122:0x0242, code lost:
    
        r28 = r4;
        r50 = r8;
        r34 = r9;
        r29 = r14;
        r14 = r26;
        removeInfoForKey(r6.getKey());
     */
    /* JADX WARN: Code restructure failed: missing block: B:125:0x0263, code lost:
    
        r2 = r49;
        r28 = r4;
        r29 = r14;
        r1 = new int[r2];
     */
    /* JADX WARN: Code restructure failed: missing block: B:126:0x026b, code lost:
    
        if (r13 == false) goto L145;
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x026d, code lost:
    
        if (r7 == null) goto L145;
     */
    /* JADX WARN: Code restructure failed: missing block: B:129:0x0275, code lost:
    
        if (r40.movingInFromStartBound.isEmpty() != false) goto L134;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0051, code lost:
    
        r9 = r40.firstVisibleIndex;
        r10 = (androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem) kotlin.collections.CollectionsKt.firstOrNull(r44);
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x0277, code lost:
    
        r3 = (java.util.ArrayList) r40.movingInFromStartBound;
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x0280, code lost:
    
        if (r3.size() <= 1) goto L130;
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x0282, code lost:
    
        kotlin.collections.CollectionsKt__MutableCollectionsJVMKt.sortWith(r3, new androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator$onMeasured$$inlined$sortByDescending$1(r7));
     */
    /* JADX WARN: Code restructure failed: missing block: B:133:0x028a, code lost:
    
        r3 = (java.util.ArrayList) r40.movingInFromStartBound;
        r4 = r3.size();
        r6 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:134:0x0293, code lost:
    
        if (r6 >= r4) goto L284;
     */
    /* JADX WARN: Code restructure failed: missing block: B:135:0x0295, code lost:
    
        r8 = (androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem) r3.get(r6);
        r9 = r51 - updateAndReturnOffsetFor(r1, r8);
        r11 = r29;
        r10 = r11.get(r8.getKey());
        kotlin.jvm.internal.Intrinsics.checkNotNull(r10);
        initializeAnimation(r8, r9, (androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator.ItemInfo) r10);
        startPlacementAnimationsIfNeeded(r8, false);
        r6 = r6 + 1;
        r29 = r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x02bc, code lost:
    
        r11 = r29;
        java.util.Arrays.fill(r1, 0, r2, 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:139:0x02cb, code lost:
    
        if (r40.movingInFromEndBound.isEmpty() != false) goto L144;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0059, code lost:
    
        if (r10 == null) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:140:0x02cd, code lost:
    
        r3 = (java.util.ArrayList) r40.movingInFromEndBound;
     */
    /* JADX WARN: Code restructure failed: missing block: B:141:0x02d6, code lost:
    
        if (r3.size() <= 1) goto L140;
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x02d8, code lost:
    
        kotlin.collections.CollectionsKt__MutableCollectionsJVMKt.sortWith(r3, new androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator$onMeasured$$inlined$sortBy$1(r7));
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x02e0, code lost:
    
        r3 = (java.util.ArrayList) r40.movingInFromEndBound;
        r4 = r3.size();
        r6 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x02e9, code lost:
    
        if (r6 >= r4) goto L285;
     */
    /* JADX WARN: Code restructure failed: missing block: B:145:0x02eb, code lost:
    
        r8 = (androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem) r3.get(r6);
        r9 = (updateAndReturnOffsetFor(r1, r8) + r52) - r8.getMainAxisSizeWithSpacings();
        r10 = r11.get(r8.getKey());
        kotlin.jvm.internal.Intrinsics.checkNotNull(r10);
        initializeAnimation(r8, r9, (androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator.ItemInfo) r10);
        startPlacementAnimationsIfNeeded(r8, false);
        r6 = r6 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x0313, code lost:
    
        java.util.Arrays.fill(r1, 0, r2, 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x0317, code lost:
    
        r3 = r28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x031d, code lost:
    
        r4 = r3.elements;
        r6 = r3.metadata;
        r8 = r6.length - 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x005b, code lost:
    
        r10 = r10.getIndex();
     */
    /* JADX WARN: Code restructure failed: missing block: B:150:0x0324, code lost:
    
        if (r8 < 0) goto L221;
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x0326, code lost:
    
        r9 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:152:0x0327, code lost:
    
        r14 = r6[r9];
        r10 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:153:0x0332, code lost:
    
        if (((((~r14) << 7) & r14) & (-9187201950435737472L)) == (-9187201950435737472L)) goto L218;
     */
    /* JADX WARN: Code restructure failed: missing block: B:154:0x0334, code lost:
    
        r5 = 8 - ((~(r9 - r8)) >>> 31);
        r6 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:155:0x033e, code lost:
    
        if (r6 >= r5) goto L288;
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x0344, code lost:
    
        if ((r14 & 255) >= 128) goto L212;
     */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x0346, code lost:
    
        r12 = r4[(r9 << 3) + r6];
        r28 = r3;
        r3 = (androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator.ItemInfo) r11.get(r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:159:0x0355, code lost:
    
        if (r3 != null) goto L158;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0061, code lost:
    
        r40.firstVisibleIndex = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:160:0x0357, code lost:
    
        r38 = r1;
        r26 = r4;
        r27 = r10;
        r39 = r11;
        r37 = r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:161:0x04c1, code lost:
    
        r13 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:163:0x04c3, code lost:
    
        r14 = r14 >> 8;
        r6 = r6 + 1;
        r2 = r49;
        r5 = r13;
        r4 = r26;
        r10 = r27;
        r3 = r28;
        r13 = r37;
        r1 = r38;
        r11 = r39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:164:0x0363, code lost:
    
        r26 = r4;
        r27 = r10;
        r10 = ((androidx.compose.foundation.lazy.layout.NearestRangeKeyIndexMap) r45).getIndex(r12);
        r37 = r13;
        r13 = java.lang.Math.min(r2, r3.span);
        r3.span = r13;
        r3.lane = java.lang.Math.min(r2 - r13, r3.lane);
     */
    /* JADX WARN: Code restructure failed: missing block: B:165:0x0385, code lost:
    
        if (r10 != (-1)) goto L194;
     */
    /* JADX WARN: Code restructure failed: missing block: B:166:0x0387, code lost:
    
        r10 = r3.animations;
        r13 = r10.length;
        r2 = 0;
        r29 = false;
        r30 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:167:0x038f, code lost:
    
        if (r2 >= r13) goto L289;
     */
    /* JADX WARN: Code restructure failed: missing block: B:168:0x0391, code lost:
    
        r31 = r13;
        r13 = r10[r2];
        r32 = r30 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:169:0x0397, code lost:
    
        if (r13 == null) goto L189;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0065, code lost:
    
        if (r47 == false) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:171:0x039d, code lost:
    
        if (r13.isDisappearanceAnimationInProgress() == false) goto L168;
     */
    /* JADX WARN: Code restructure failed: missing block: B:172:0x039f, code lost:
    
        r38 = r1;
        r33 = r10;
        r39 = r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:173:0x03a5, code lost:
    
        r29 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:175:0x041e, code lost:
    
        r2 = r2 + 1;
        r13 = r31;
        r30 = r32;
        r10 = r33;
        r1 = r38;
        r11 = r39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:176:0x03aa, code lost:
    
        r33 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:177:0x03ba, code lost:
    
        if (((java.lang.Boolean) ((androidx.compose.runtime.SnapshotMutableStateImpl) r13.isDisappearanceAnimationFinished$delegate).getValue()).booleanValue() == false) goto L175;
     */
    /* JADX WARN: Code restructure failed: missing block: B:178:0x03bc, code lost:
    
        r13.release();
        r3.animations[r30] = null;
        r40.disappearingItems.remove(r13);
        r10 = r40.displayingNode;
     */
    /* JADX WARN: Code restructure failed: missing block: B:179:0x03cc, code lost:
    
        if (r10 == null) goto L173;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0067, code lost:
    
        r15 = r12;
        r11 = (r41 & 4294967295L) | (0 << 32);
     */
    /* JADX WARN: Code restructure failed: missing block: B:180:0x03ce, code lost:
    
        androidx.compose.ui.node.DrawModifierNodeKt.invalidateDraw(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:181:0x03d1, code lost:
    
        r38 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:182:0x03d3, code lost:
    
        r39 = r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:184:0x03d7, code lost:
    
        r10 = r13.layer;
        r38 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:185:0x03db, code lost:
    
        if (r10 == null) goto L182;
     */
    /* JADX WARN: Code restructure failed: missing block: B:186:0x03dd, code lost:
    
        r1 = r13.fadeOutSpec;
     */
    /* JADX WARN: Code restructure failed: missing block: B:187:0x03e3, code lost:
    
        if (r13.isDisappearanceAnimationInProgress() != false) goto L182;
     */
    /* JADX WARN: Code restructure failed: missing block: B:188:0x03e5, code lost:
    
        if (r1 != null) goto L181;
     */
    /* JADX WARN: Code restructure failed: missing block: B:189:0x03e8, code lost:
    
        r39 = r11;
        r13.setDisappearanceAnimationInProgress(true);
        kotlinx.coroutines.BuildersKt.launch$default(r13.coroutineScope, null, null, new androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation$animateDisappearance$1(r13, r1, r10, null), 3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0082, code lost:
    
        if (r48 != false) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:191:0x0401, code lost:
    
        if (r13.isDisappearanceAnimationInProgress() == false) goto L188;
     */
    /* JADX WARN: Code restructure failed: missing block: B:192:0x0403, code lost:
    
        r40.disappearingItems.add(r13);
        r1 = r40.displayingNode;
     */
    /* JADX WARN: Code restructure failed: missing block: B:193:0x040a, code lost:
    
        if (r1 == null) goto L167;
     */
    /* JADX WARN: Code restructure failed: missing block: B:194:0x040c, code lost:
    
        androidx.compose.ui.node.DrawModifierNodeKt.invalidateDraw(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:195:0x0410, code lost:
    
        r13.release();
        r3.animations[r30] = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:197:0x03fb, code lost:
    
        r39 = r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:198:0x0419, code lost:
    
        r38 = r1;
        r33 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0084, code lost:
    
        if (r50 != false) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:200:0x042e, code lost:
    
        r38 = r1;
        r39 = r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:201:0x0433, code lost:
    
        if (r29 != false) goto L213;
     */
    /* JADX WARN: Code restructure failed: missing block: B:202:0x0435, code lost:
    
        removeInfoForKey(r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:203:0x043a, code lost:
    
        r38 = r1;
        r39 = r11;
        r1 = r3.constraints;
        kotlin.jvm.internal.Intrinsics.checkNotNull(r1);
        r13 = r5;
        r1 = r46.mo125getAndMeasurehBUhpc(r10, r3.lane, r1.value, r3.span);
        r1.setNonScrollableItem();
        r2 = r3.animations;
        r4 = r2.length;
        r5 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:204:0x0460, code lost:
    
        if (r5 >= r4) goto L293;
     */
    /* JADX WARN: Code restructure failed: missing block: B:205:0x0462, code lost:
    
        r11 = r2[r5];
     */
    /* JADX WARN: Code restructure failed: missing block: B:206:0x0464, code lost:
    
        if (r11 == null) goto L201;
     */
    /* JADX WARN: Code restructure failed: missing block: B:207:0x0466, code lost:
    
        r29 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:208:0x0477, code lost:
    
        if (((java.lang.Boolean) ((androidx.compose.runtime.SnapshotMutableStateImpl) r11.isPlacementAnimationInProgress$delegate).getValue()).booleanValue() != true) goto L296;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0087, code lost:
    
        r14 = r15;
        r13 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:210:0x0490, code lost:
    
        r3.updateAnimation(r1, r53, r54, r51, r52, r3.crossAxisOffset);
     */
    /* JADX WARN: Code restructure failed: missing block: B:211:0x04a5, code lost:
    
        if (r10 >= r40.firstVisibleIndex) goto L210;
     */
    /* JADX WARN: Code restructure failed: missing block: B:212:0x04a7, code lost:
    
        r40.movingAwayToStartBound.add(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:213:0x04ad, code lost:
    
        r40.movingAwayToEndBound.add(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:215:0x047c, code lost:
    
        r5 = r5 + 1;
        r2 = r29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:216:0x047a, code lost:
    
        r29 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:219:0x0481, code lost:
    
        if (r7 == null) goto L207;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x008c, code lost:
    
        r15 = r14.keys;
        r1 = r14.metadata;
        r10 = r1.length - 2;
        r4 = r40.movingAwayKeys;
     */
    /* JADX WARN: Code restructure failed: missing block: B:221:0x048a, code lost:
    
        if (r10 != ((androidx.compose.foundation.lazy.layout.NearestRangeKeyIndexMap) r7).getIndex(r12)) goto L207;
     */
    /* JADX WARN: Code restructure failed: missing block: B:222:0x048c, code lost:
    
        removeInfoForKey(r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:223:0x04b5, code lost:
    
        r38 = r1;
        r28 = r3;
        r26 = r4;
        r27 = r10;
        r39 = r11;
        r37 = r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:225:0x04d7, code lost:
    
        r38 = r1;
        r28 = r3;
        r26 = r4;
        r27 = r10;
        r39 = r11;
        r37 = r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:226:0x04e6, code lost:
    
        if (r5 != 8) goto L286;
     */
    /* JADX WARN: Code restructure failed: missing block: B:227:0x04f7, code lost:
    
        if (r9 == r8) goto L287;
     */
    /* JADX WARN: Code restructure failed: missing block: B:228:0x04f9, code lost:
    
        r9 = r9 + 1;
        r2 = r49;
        r4 = r26;
        r6 = r27;
        r3 = r28;
        r13 = r37;
        r1 = r38;
        r11 = r39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x00a0, code lost:
    
        if (r10 < 0) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:231:0x051a, code lost:
    
        if (r40.movingAwayToStartBound.isEmpty() != false) goto L242;
     */
    /* JADX WARN: Code restructure failed: missing block: B:232:0x051c, code lost:
    
        r1 = (java.util.ArrayList) r40.movingAwayToStartBound;
     */
    /* JADX WARN: Code restructure failed: missing block: B:233:0x0525, code lost:
    
        if (r1.size() <= 1) goto L227;
     */
    /* JADX WARN: Code restructure failed: missing block: B:234:0x0527, code lost:
    
        r3 = r45;
        kotlin.collections.CollectionsKt__MutableCollectionsJVMKt.sortWith(r1, new androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator$onMeasured$$inlined$sortByDescending$2(r3));
     */
    /* JADX WARN: Code restructure failed: missing block: B:235:0x0534, code lost:
    
        r1 = (java.util.ArrayList) r40.movingAwayToStartBound;
        r2 = r1.size();
        r4 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:236:0x053d, code lost:
    
        if (r4 >= r2) goto L297;
     */
    /* JADX WARN: Code restructure failed: missing block: B:237:0x053f, code lost:
    
        r5 = (androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem) r1.get(r4);
        r7 = r39;
        r6 = r7.get(r5.getKey());
        kotlin.jvm.internal.Intrinsics.checkNotNull(r6);
        r6 = (androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator.ItemInfo) r6;
        r8 = r38;
        r9 = updateAndReturnOffsetFor(r8, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:238:0x055a, code lost:
    
        if (r48 == false) goto L236;
     */
    /* JADX WARN: Code restructure failed: missing block: B:239:0x055c, code lost:
    
        r10 = (androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem) kotlin.collections.CollectionsKt.first(r44);
        r12 = r10.mo124getOffsetBjo55l4(0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x00a2, code lost:
    
        r2 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:240:0x056b, code lost:
    
        if (r10.isVertical() == false) goto L235;
     */
    /* JADX WARN: Code restructure failed: missing block: B:241:0x056d, code lost:
    
        r10 = (int) (r12 & 4294967295L);
     */
    /* JADX WARN: Code restructure failed: missing block: B:242:0x057d, code lost:
    
        r5.position(r10 - r9, r6.crossAxisOffset, r42, r43);
     */
    /* JADX WARN: Code restructure failed: missing block: B:243:0x0587, code lost:
    
        if (r37 == false) goto L299;
     */
    /* JADX WARN: Code restructure failed: missing block: B:244:0x0589, code lost:
    
        startPlacementAnimationsIfNeeded(r5, true);
     */
    /* JADX WARN: Code restructure failed: missing block: B:246:0x058d, code lost:
    
        r4 = r4 + 1;
        r39 = r7;
        r38 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:248:0x0575, code lost:
    
        r10 = (int) (r12 >> 32);
     */
    /* JADX WARN: Code restructure failed: missing block: B:249:0x057b, code lost:
    
        r10 = r6.layoutMinOffset;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x00a3, code lost:
    
        r5 = r1[r2];
        r26 = r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:251:0x0594, code lost:
    
        r9 = r42;
        r11 = r43;
        r8 = r38;
        r7 = r39;
        java.util.Arrays.fill(r8, 0, r49, 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:253:0x05b3, code lost:
    
        if (r40.movingAwayToEndBound.isEmpty() != false) goto L261;
     */
    /* JADX WARN: Code restructure failed: missing block: B:254:0x05b5, code lost:
    
        r1 = (java.util.ArrayList) r40.movingAwayToEndBound;
     */
    /* JADX WARN: Code restructure failed: missing block: B:255:0x05be, code lost:
    
        if (r1.size() <= 1) goto L248;
     */
    /* JADX WARN: Code restructure failed: missing block: B:256:0x05c0, code lost:
    
        kotlin.collections.CollectionsKt__MutableCollectionsJVMKt.sortWith(r1, new androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator$onMeasured$$inlined$sortBy$2(r3));
     */
    /* JADX WARN: Code restructure failed: missing block: B:257:0x05c8, code lost:
    
        r1 = (java.util.ArrayList) r40.movingAwayToEndBound;
        r2 = r1.size();
        r3 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:258:0x05d1, code lost:
    
        if (r3 >= r2) goto L300;
     */
    /* JADX WARN: Code restructure failed: missing block: B:259:0x05d3, code lost:
    
        r4 = (androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem) r1.get(r3);
        r5 = r7.get(r4.getKey());
        kotlin.jvm.internal.Intrinsics.checkNotNull(r5);
        r5 = (androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator.ItemInfo) r5;
        r6 = updateAndReturnOffsetFor(r8, r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00af, code lost:
    
        if (((((~r5) << 7) & r5) & (-9187201950435737472L)) == (-9187201950435737472L)) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:260:0x05ea, code lost:
    
        if (r48 == false) goto L256;
     */
    /* JADX WARN: Code restructure failed: missing block: B:261:0x05ec, code lost:
    
        r10 = (androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem) kotlin.collections.CollectionsKt.last(r44);
        r13 = r10.mo124getOffsetBjo55l4(0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:262:0x05fb, code lost:
    
        if (r10.isVertical() == false) goto L255;
     */
    /* JADX WARN: Code restructure failed: missing block: B:263:0x05fd, code lost:
    
        r12 = (int) (r13 & 4294967295L);
     */
    /* JADX WARN: Code restructure failed: missing block: B:264:0x0622, code lost:
    
        r4.position(r12 + r6, r5.crossAxisOffset, r9, r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:265:0x0629, code lost:
    
        if (r37 == false) goto L302;
     */
    /* JADX WARN: Code restructure failed: missing block: B:266:0x062b, code lost:
    
        startPlacementAnimationsIfNeeded(r4, true);
     */
    /* JADX WARN: Code restructure failed: missing block: B:268:0x062e, code lost:
    
        r3 = r3 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00b1, code lost:
    
        r11 = 8 - ((~(r2 - r10)) >>> 31);
        r12 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:270:0x0609, code lost:
    
        r12 = (int) (r13 >> 32);
     */
    /* JADX WARN: Code restructure failed: missing block: B:271:0x0614, code lost:
    
        r12 = r5.layoutMaxOffset - r4.getMainAxisSizeWithSpacings();
     */
    /* JADX WARN: Code restructure failed: missing block: B:273:0x0631, code lost:
    
        r1 = r40.movingAwayToStartBound;
        java.util.Collections.reverse(r1);
        r44.addAll(0, r1);
        r44.addAll(r40.movingAwayToEndBound);
        r40.movingInFromStartBound.clear();
        r40.movingInFromEndBound.clear();
        r40.movingAwayToStartBound.clear();
        r40.movingAwayToEndBound.clear();
        r28.clear();
     */
    /* JADX WARN: Code restructure failed: missing block: B:274:0x065a, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:276:0x0532, code lost:
    
        r3 = r45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:277:0x05a3, code lost:
    
        r9 = r42;
        r11 = r43;
        r3 = r45;
        r8 = r38;
        r7 = r39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:279:0x04e9, code lost:
    
        r38 = r1;
        r28 = r3;
        r26 = r4;
        r27 = r10;
        r39 = r11;
        r37 = r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00bb, code lost:
    
        if (r12 >= r11) goto L268;
     */
    /* JADX WARN: Code restructure failed: missing block: B:280:0x050c, code lost:
    
        r38 = r1;
        r28 = r3;
        r39 = r11;
        r37 = r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:281:0x02c3, code lost:
    
        r11 = r29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:282:0x031a, code lost:
    
        r11 = r29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:284:0x00e1, code lost:
    
        r29 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:285:0x00ec, code lost:
    
        r26 = r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:286:0x008a, code lost:
    
        r14 = r15;
        r13 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:287:0x0075, code lost:
    
        r15 = r12;
        r11 = (r41 << 32) | (0 & 4294967295L);
     */
    /* JADX WARN: Code restructure failed: missing block: B:288:0x0060, code lost:
    
        r10 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00c1, code lost:
    
        if ((r5 & 255) >= 128) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00c3, code lost:
    
        r29 = r1;
        r4.add(r15[(r2 << 3) + r12]);
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00d4, code lost:
    
        r5 = r5 >> 8;
        r12 = r12 + 1;
        r1 = r29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00d1, code lost:
    
        r29 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00da, code lost:
    
        r29 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00de, code lost:
    
        if (r11 != 8) goto L267;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00e3, code lost:
    
        if (r2 == r10) goto L266;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00e5, code lost:
    
        r2 = r2 + 1;
        r11 = r26;
        r1 = r29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00ee, code lost:
    
        r1 = r8.size();
        r2 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00f4, code lost:
    
        if (r2 >= r1) goto L269;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00f6, code lost:
    
        r6 = (androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem) r8.get(r2);
        r4.remove(r6.getKey());
        r10 = r6.getPlaceablesCount();
        r11 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0108, code lost:
    
        if (r11 >= r10) goto L274;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x010a, code lost:
    
        r12 = r6.getParentData(r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0110, code lost:
    
        if ((r12 instanceof androidx.compose.foundation.lazy.layout.LazyLayoutAnimationSpecsNode) == false) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0112, code lost:
    
        r12 = (androidx.compose.foundation.lazy.layout.LazyLayoutAnimationSpecsNode) r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0116, code lost:
    
        if (r12 == null) goto L119;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0230, code lost:
    
        r11 = r11 + 1;
        r14 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0118, code lost:
    
        r10 = (androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator.ItemInfo) r14.get(r6.getKey());
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0122, code lost:
    
        if (r7 == null) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0124, code lost:
    
        r11 = ((androidx.compose.foundation.lazy.layout.NearestRangeKeyIndexMap) r7).getIndex(r6.getKey());
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0131, code lost:
    
        if (r11 != (-1)) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0133, code lost:
    
        if (r7 == null) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0135, code lost:
    
        r12 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0138, code lost:
    
        if (r10 != null) goto L93;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x013a, code lost:
    
        r10 = new androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator.ItemInfo(r40);
        androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator.ItemInfo.updateAnimation$default(r10, r6, r53, r54, r51, r52);
        r14.set(r6.getKey(), r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0159, code lost:
    
        if (r6.getIndex() == r11) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x015b, code lost:
    
        if (r11 == (-1)) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x015d, code lost:
    
        if (r11 >= r9) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x015f, code lost:
    
        r40.movingInFromStartBound.add(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0164, code lost:
    
        r28 = r4;
        r50 = r8;
        r34 = r9;
        r29 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x016c, code lost:
    
        r14 = r26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0253, code lost:
    
        r2 = r2 + 1;
        r8 = r50;
        r26 = r14;
        r4 = r28;
        r14 = r29;
        r9 = r34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0170, code lost:
    
        r40.movingInFromEndBound.add(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0176, code lost:
    
        r28 = r6.mo124getOffsetBjo55l4(0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x017f, code lost:
    
        if (r6.isVertical() == false) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0181, code lost:
    
        r15 = r8;
        r34 = r9;
        r8 = r28 & 4294967295L;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0195, code lost:
    
        initializeAnimation(r6, (int) r8, r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0198, code lost:
    
        if (r12 == false) goto L92;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x019a, code lost:
    
        r5 = r10.animations;
        r6 = r5.length;
        r8 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x019e, code lost:
    
        if (r8 >= r6) goto L275;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x01a0, code lost:
    
        r9 = r5[r8];
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x01a2, code lost:
    
        if (r9 == null) goto L277;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x01a4, code lost:
    
        r9.animateAppearance();
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x01a7, code lost:
    
        r8 = r8 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x01aa, code lost:
    
        r28 = r4;
        r29 = r14;
        r50 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x018d, code lost:
    
        r15 = r8;
        r34 = r9;
        r8 = r28 >> 32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x01b1, code lost:
    
        r15 = r8;
        r34 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x01b4, code lost:
    
        if (r13 == false) goto L92;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x01b6, code lost:
    
        androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator.ItemInfo.updateAnimation$default(r10, r6, r53, r54, r51, r52);
        r5 = r10.animations;
        r8 = r5.length;
        r9 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x01c9, code lost:
    
        if (r9 >= r8) goto L278;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x01cb, code lost:
    
        r11 = r5[r9];
        r28 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x01cf, code lost:
    
        if (r11 == null) goto L103;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x01d1, code lost:
    
        r29 = r14;
        r50 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x01dd, code lost:
    
        if (androidx.compose.ui.unit.IntOffset.m674equalsimpl0(r11.rawOffset, androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation.NotInitialized) != false) goto L102;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x01df, code lost:
    
        r14 = r26;
        r11.rawOffset = androidx.compose.ui.unit.IntOffset.m676plusqkQi6aY(r11.rawOffset, r14);
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x01f2, code lost:
    
        r9 = r9 + 1;
        r26 = r14;
        r4 = r28;
        r14 = r29;
        r15 = r50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x01ea, code lost:
    
        r14 = r26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x01ed, code lost:
    
        r29 = r14;
        r50 = r15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onMeasured(int r41, int r42, int r43, java.util.List r44, androidx.compose.foundation.lazy.layout.LazyLayoutKeyIndexMap r45, androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItemProvider r46, boolean r47, boolean r48, int r49, boolean r50, int r51, int r52, kotlinx.coroutines.CoroutineScope r53, androidx.compose.ui.graphics.GraphicsContext r54) {
        /*
            Method dump skipped, instructions count: 1627
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator.onMeasured(int, int, int, java.util.List, androidx.compose.foundation.lazy.layout.LazyLayoutKeyIndexMap, androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItemProvider, boolean, boolean, int, boolean, int, int, kotlinx.coroutines.CoroutineScope, androidx.compose.ui.graphics.GraphicsContext):void");
    }

    public final void releaseAnimations() {
        MutableScatterMap mutableScatterMap = this.keyToItemInfoMap;
        if (mutableScatterMap._size != 0) {
            Object[] objArr = mutableScatterMap.values;
            long[] jArr = mutableScatterMap.metadata;
            int length = jArr.length - 2;
            if (length >= 0) {
                int i = 0;
                while (true) {
                    long j = jArr[i];
                    if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                        int i2 = 8 - ((~(i - length)) >>> 31);
                        for (int i3 = 0; i3 < i2; i3++) {
                            if ((255 & j) < 128) {
                                for (LazyLayoutItemAnimation lazyLayoutItemAnimation : ((ItemInfo) objArr[(i << 3) + i3]).animations) {
                                    if (lazyLayoutItemAnimation != null) {
                                        lazyLayoutItemAnimation.release();
                                    }
                                }
                            }
                            j >>= 8;
                        }
                        if (i2 != 8) {
                            break;
                        }
                    }
                    if (i == length) {
                        break;
                    } else {
                        i++;
                    }
                }
            }
            mutableScatterMap.clear();
        }
    }

    public final void removeInfoForKey(Object obj) {
        LazyLayoutItemAnimation[] lazyLayoutItemAnimationArr;
        ItemInfo itemInfo = (ItemInfo) this.keyToItemInfoMap.remove(obj);
        if (itemInfo == null || (lazyLayoutItemAnimationArr = itemInfo.animations) == null) {
            return;
        }
        for (LazyLayoutItemAnimation lazyLayoutItemAnimation : lazyLayoutItemAnimationArr) {
            if (lazyLayoutItemAnimation != null) {
                lazyLayoutItemAnimation.release();
            }
        }
    }

    public final void startPlacementAnimationsIfNeeded(LazyLayoutMeasuredItem lazyLayoutMeasuredItem, boolean z) {
        Object obj = this.keyToItemInfoMap.get(lazyLayoutMeasuredItem.getKey());
        Intrinsics.checkNotNull(obj);
        LazyLayoutItemAnimation[] lazyLayoutItemAnimationArr = ((ItemInfo) obj).animations;
        int length = lazyLayoutItemAnimationArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            LazyLayoutItemAnimation lazyLayoutItemAnimation = lazyLayoutItemAnimationArr[i];
            int i3 = i2 + 1;
            if (lazyLayoutItemAnimation != null) {
                long mo124getOffsetBjo55l4 = lazyLayoutMeasuredItem.mo124getOffsetBjo55l4(i2);
                long j = lazyLayoutItemAnimation.rawOffset;
                if (!IntOffset.m674equalsimpl0(j, LazyLayoutItemAnimation.NotInitialized) && !IntOffset.m674equalsimpl0(j, mo124getOffsetBjo55l4)) {
                    long m675minusqkQi6aY = IntOffset.m675minusqkQi6aY(mo124getOffsetBjo55l4, j);
                    FiniteAnimationSpec finiteAnimationSpec = lazyLayoutItemAnimation.placementSpec;
                    if (finiteAnimationSpec != null) {
                        long m675minusqkQi6aY2 = IntOffset.m675minusqkQi6aY(((IntOffset) ((SnapshotMutableStateImpl) lazyLayoutItemAnimation.placementDelta$delegate).getValue()).packedValue, m675minusqkQi6aY);
                        lazyLayoutItemAnimation.m133setPlacementDeltagyyYBs(m675minusqkQi6aY2);
                        lazyLayoutItemAnimation.setPlacementAnimationInProgress(true);
                        lazyLayoutItemAnimation.isRunningMovingAwayAnimation = z;
                        BuildersKt.launch$default(lazyLayoutItemAnimation.coroutineScope, null, null, new LazyLayoutItemAnimation$animatePlacementDelta$1(lazyLayoutItemAnimation, finiteAnimationSpec, m675minusqkQi6aY2, null), 3);
                    }
                }
                lazyLayoutItemAnimation.rawOffset = mo124getOffsetBjo55l4;
            }
            i++;
            i2 = i3;
        }
    }
}
