package androidx.compose.ui.viewinterop;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.Region;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.compose.runtime.ComposeNodeLifecycleCallback;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.graphics.AndroidCanvas;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher;
import androidx.compose.ui.input.nestedscroll.NestedScrollModifierKt;
import androidx.compose.ui.input.nestedscroll.NestedScrollNode;
import androidx.compose.ui.input.pointer.PointerInteropFilter_androidKt;
import androidx.compose.ui.input.pointer.RequestDisallowInterceptTouchEvent;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.OnGloballyPositionedModifierKt;
import androidx.compose.ui.node.InnerNodeCoordinator;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.node.OwnerScope;
import androidx.compose.ui.node.TraversableNodeKt;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.platform.NestedScrollInteropConnectionKt;
import androidx.compose.ui.platform.WindowRecomposer_androidKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DensityKt;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.VelocityKt;
import androidx.core.graphics.Insets;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.NestedScrollingParent3;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsAnimationCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.savedstate.SavedStateRegistryOwner;
import com.android.wm.shell.R;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class AndroidViewHolder extends ViewGroup implements NestedScrollingParent3, ComposeNodeLifecycleCallback, OwnerScope, OnApplyWindowInsetsListener {
    public static final Function1 OnCommitAffectingUpdate = new Function1() { // from class: androidx.compose.ui.viewinterop.AndroidViewHolder$Companion$OnCommitAffectingUpdate$1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            AndroidViewHolder androidViewHolder = (AndroidViewHolder) obj;
            androidViewHolder.getHandler().post(new AndroidViewHolder$$ExternalSyntheticLambda0(1, androidViewHolder.runUpdate));
            return Unit.INSTANCE;
        }
    };
    public Density density;
    public final NestedScrollDispatcher dispatcher;
    public boolean hasUpdateBlock;
    public boolean isDrawing;
    public int lastHeightMeasureSpec;
    public int lastWidthMeasureSpec;
    public final LayoutNode layoutNode;
    public LifecycleOwner lifecycleOwner;
    public final int[] location;
    public Modifier modifier;
    public final NestedScrollingParentHelper nestedScrollingParentHelper;
    public final Function1 onDensityChanged;
    public final Function1 onModifierChanged;
    public RequestDisallowInterceptTouchEvent onRequestDisallowInterceptTouchEvent;
    public final Owner owner;
    public final int[] position;
    public Lambda release;
    public Lambda reset;
    public final Function0 runInvalidate;
    public final Function0 runUpdate;
    public SavedStateRegistryOwner savedStateRegistryOwner;
    public long size;
    public Lambda update;
    public final View view;

    public AndroidViewHolder(Context context, CompositionContext compositionContext, int i, NestedScrollDispatcher nestedScrollDispatcher, View view, Owner owner) {
        super(context);
        this.dispatcher = nestedScrollDispatcher;
        this.view = view;
        this.owner = owner;
        if (compositionContext != null) {
            Map map = WindowRecomposer_androidKt.animationScale;
            setTag(R.id.androidx_compose_ui_view_composition_context, compositionContext);
        }
        setSaveFromParentEnabled(false);
        addView(view);
        WindowInsetsAnimationCompat.Callback callback = new WindowInsetsAnimationCompat.Callback() { // from class: androidx.compose.ui.viewinterop.AndroidViewHolder.2
            @Override // androidx.core.view.WindowInsetsAnimationCompat.Callback
            public final WindowInsetsCompat onProgress(WindowInsetsCompat windowInsetsCompat, List list) {
                Function1 function1 = AndroidViewHolder.OnCommitAffectingUpdate;
                return AndroidViewHolder.this.insetToLayoutPosition(windowInsetsCompat);
            }

            @Override // androidx.core.view.WindowInsetsAnimationCompat.Callback
            public final WindowInsetsAnimationCompat.BoundsCompat onStart(WindowInsetsAnimationCompat.BoundsCompat boundsCompat) {
                InnerNodeCoordinator innerNodeCoordinator = AndroidViewHolder.this.layoutNode.nodes.innerCoordinator;
                if (!innerNodeCoordinator.tail.isAttached) {
                    return boundsCompat;
                }
                long m679roundk4lQ0M = IntOffsetKt.m679roundk4lQ0M(innerNodeCoordinator.mo484localToRootMKHz9U(0L));
                int i2 = (int) (m679roundk4lQ0M >> 32);
                if (i2 < 0) {
                    i2 = 0;
                }
                int i3 = (int) (m679roundk4lQ0M & 4294967295L);
                if (i3 < 0) {
                    i3 = 0;
                }
                long mo481getSizeYbymL2g = LayoutCoordinatesKt.findRootCoordinates(innerNodeCoordinator).mo481getSizeYbymL2g();
                int i4 = (int) (mo481getSizeYbymL2g >> 32);
                int i5 = (int) (mo481getSizeYbymL2g & 4294967295L);
                long j = innerNodeCoordinator.measuredSize;
                long m679roundk4lQ0M2 = IntOffsetKt.m679roundk4lQ0M(innerNodeCoordinator.mo484localToRootMKHz9U((Float.floatToRawIntBits((int) (j >> 32)) << 32) | (Float.floatToRawIntBits((int) (j & 4294967295L)) & 4294967295L)));
                int i6 = i4 - ((int) (m679roundk4lQ0M2 >> 32));
                if (i6 < 0) {
                    i6 = 0;
                }
                int i7 = i5 - ((int) (4294967295L & m679roundk4lQ0M2));
                int i8 = i7 >= 0 ? i7 : 0;
                return (i2 == 0 && i3 == 0 && i6 == 0 && i8 == 0) ? boundsCompat : new WindowInsetsAnimationCompat.BoundsCompat(AndroidViewHolder.inset(boundsCompat.mLowerBound, i2, i3, i6, i8), AndroidViewHolder.inset(boundsCompat.mUpperBound, i2, i3, i6, i8));
            }
        };
        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
        setWindowInsetsAnimationCallback(new WindowInsetsAnimationCompat.Impl30.ProxyCallback(callback));
        ViewCompat.Api21Impl.setOnApplyWindowInsetsListener(this, this);
        this.update = new Function0() { // from class: androidx.compose.ui.viewinterop.AndroidViewHolder$update$1
            @Override // kotlin.jvm.functions.Function0
            public final /* bridge */ /* synthetic */ Object invoke() {
                return Unit.INSTANCE;
            }
        };
        this.reset = new Function0() { // from class: androidx.compose.ui.viewinterop.AndroidViewHolder$reset$1
            @Override // kotlin.jvm.functions.Function0
            public final /* bridge */ /* synthetic */ Object invoke() {
                return Unit.INSTANCE;
            }
        };
        this.release = new Function0() { // from class: androidx.compose.ui.viewinterop.AndroidViewHolder$release$1
            @Override // kotlin.jvm.functions.Function0
            public final /* bridge */ /* synthetic */ Object invoke() {
                return Unit.INSTANCE;
            }
        };
        Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
        this.modifier = companion;
        this.density = DensityKt.Density$default();
        this.position = new int[2];
        this.size = 0L;
        this.runUpdate = new AndroidViewHolder$runUpdate$1(this);
        this.runInvalidate = new Function0() { // from class: androidx.compose.ui.viewinterop.AndroidViewHolder$runInvalidate$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                AndroidViewHolder.this.layoutNode.invalidateLayer$ui_release();
                return Unit.INSTANCE;
            }
        };
        this.location = new int[2];
        this.lastWidthMeasureSpec = Integer.MIN_VALUE;
        this.lastHeightMeasureSpec = Integer.MIN_VALUE;
        this.nestedScrollingParentHelper = new NestedScrollingParentHelper();
        final LayoutNode layoutNode = new LayoutNode(3);
        layoutNode.forceUseOldLayers = true;
        layoutNode.interopViewFactoryHolder = this;
        Modifier onGloballyPositioned = OnGloballyPositionedModifierKt.onGloballyPositioned(DrawModifierKt.drawBehind(GraphicsLayerModifierKt.m376graphicsLayerAp8cVGQ$default(PointerInteropFilter_androidKt.pointerInteropFilter(SemanticsModifierKt.semantics(NestedScrollModifierKt.nestedScroll(companion, AndroidViewHolder_androidKt.NoOpScrollConnection, nestedScrollDispatcher), true, new Function1() { // from class: androidx.compose.ui.viewinterop.AndroidViewHolder$layoutNode$1$coreModifier$1
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return Unit.INSTANCE;
            }
        }), this), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, null, false, null, 131071), new Function1() { // from class: androidx.compose.ui.viewinterop.AndroidViewHolder$layoutNode$1$coreModifier$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                AndroidViewHolder androidViewHolder = AndroidViewHolder.this;
                LayoutNode layoutNode2 = layoutNode;
                AndroidViewHolder androidViewHolder2 = this;
                Canvas canvas = ((DrawScope) obj).getDrawContext().getCanvas();
                if (androidViewHolder.view.getVisibility() != 8) {
                    androidViewHolder.isDrawing = true;
                    AndroidComposeView androidComposeView = layoutNode2.owner;
                    if (androidComposeView == null) {
                        androidComposeView = null;
                    }
                    if (androidComposeView != null) {
                        android.graphics.Canvas canvas2 = AndroidCanvas_androidKt.EmptyCanvas;
                        android.graphics.Canvas canvas3 = ((AndroidCanvas) canvas).internalCanvas;
                        androidComposeView.getAndroidViewsHandler$ui_release();
                        androidViewHolder2.draw(canvas3);
                    }
                    androidViewHolder.isDrawing = false;
                }
                return Unit.INSTANCE;
            }
        }), new Function1() { // from class: androidx.compose.ui.viewinterop.AndroidViewHolder$layoutNode$1$coreModifier$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                AndroidViewHolder_androidKt.access$layoutAccordingTo(AndroidViewHolder.this, layoutNode);
                AndroidViewHolder androidViewHolder = AndroidViewHolder.this;
                ((AndroidComposeView) androidViewHolder.owner).isPendingInteropViewLayoutChangeDispatch = true;
                int[] iArr = androidViewHolder.position;
                int i2 = iArr[0];
                int i3 = iArr[1];
                androidViewHolder.view.getLocationOnScreen(iArr);
                AndroidViewHolder androidViewHolder2 = AndroidViewHolder.this;
                long j = androidViewHolder2.size;
                androidViewHolder2.size = ((LayoutCoordinates) obj).mo481getSizeYbymL2g();
                AndroidViewHolder androidViewHolder3 = AndroidViewHolder.this;
                int[] iArr2 = androidViewHolder3.position;
                if (i2 != iArr2[0] || i3 != iArr2[1] || !IntSize.m683equalsimpl0(j, androidViewHolder3.size)) {
                    AndroidViewHolder.this.view.requestApplyInsets();
                }
                return Unit.INSTANCE;
            }
        });
        layoutNode.setModifier(this.modifier.then(onGloballyPositioned));
        this.onModifierChanged = new AndroidViewHolder$layoutNode$1$1(layoutNode, onGloballyPositioned);
        layoutNode.setDensity$1(this.density);
        this.onDensityChanged = new AndroidViewHolder$layoutNode$1$2(layoutNode);
        layoutNode.onAttach = new Function1() { // from class: androidx.compose.ui.viewinterop.AndroidViewHolder$layoutNode$1$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Owner owner2 = (Owner) obj;
                final AndroidComposeView androidComposeView = owner2 instanceof AndroidComposeView ? (AndroidComposeView) owner2 : null;
                if (androidComposeView != null) {
                    AndroidViewHolder androidViewHolder = AndroidViewHolder.this;
                    final LayoutNode layoutNode2 = layoutNode;
                    androidComposeView.getAndroidViewsHandler$ui_release().holderToLayoutNode.put(androidViewHolder, layoutNode2);
                    androidComposeView.getAndroidViewsHandler$ui_release().addView(androidViewHolder);
                    androidComposeView.getAndroidViewsHandler$ui_release().layoutNodeToHolder.put(layoutNode2, androidViewHolder);
                    androidViewHolder.setImportantForAccessibility(1);
                    ViewCompat.setAccessibilityDelegate(androidViewHolder, new AccessibilityDelegateCompat() { // from class: androidx.compose.ui.platform.AndroidComposeView$addAndroidView$1
                        /* JADX WARN: Code restructure failed: missing block: B:10:0x0038, code lost:
                        
                            if (r0.intValue() == r6.semanticsOwner.getUnmergedRootSemanticsNode().id) goto L13;
                         */
                        @Override // androidx.core.view.AccessibilityDelegateCompat
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                            To view partially-correct add '--show-bad-code' argument
                        */
                        public final void onInitializeAccessibilityNodeInfo(android.view.View r6, androidx.core.view.accessibility.AccessibilityNodeInfoCompat r7) {
                            /*
                                r5 = this;
                                android.view.View$AccessibilityDelegate r0 = r5.mOriginalDelegate
                                android.view.accessibility.AccessibilityNodeInfo r1 = r7.mInfo
                                r0.onInitializeAccessibilityNodeInfo(r6, r1)
                                androidx.compose.ui.platform.AndroidComposeView r6 = androidx.compose.ui.platform.AndroidComposeView.this
                                androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat r0 = r6.composeAccessibilityDelegate
                                boolean r0 = r0.isEnabled$ui_release()
                                if (r0 == 0) goto L17
                                android.view.accessibility.AccessibilityNodeInfo r0 = r7.mInfo
                                r1 = 0
                                r0.setVisibleToUser(r1)
                            L17:
                                androidx.compose.ui.platform.AndroidComposeView$addAndroidView$1$onInitializeAccessibilityNodeInfo$parentId$1 r0 = new kotlin.jvm.functions.Function1() { // from class: androidx.compose.ui.platform.AndroidComposeView$addAndroidView$1$onInitializeAccessibilityNodeInfo$parentId$1
                                    static {
                                        /*
                                            androidx.compose.ui.platform.AndroidComposeView$addAndroidView$1$onInitializeAccessibilityNodeInfo$parentId$1 r0 = new androidx.compose.ui.platform.AndroidComposeView$addAndroidView$1$onInitializeAccessibilityNodeInfo$parentId$1
                                            r0.<init>()
                                            
                                            // error: 0x0005: SPUT (r0 I:androidx.compose.ui.platform.AndroidComposeView$addAndroidView$1$onInitializeAccessibilityNodeInfo$parentId$1) androidx.compose.ui.platform.AndroidComposeView$addAndroidView$1$onInitializeAccessibilityNodeInfo$parentId$1.INSTANCE androidx.compose.ui.platform.AndroidComposeView$addAndroidView$1$onInitializeAccessibilityNodeInfo$parentId$1
                                            return
                                        */
                                        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeView$addAndroidView$1$onInitializeAccessibilityNodeInfo$parentId$1.<clinit>():void");
                                    }

                                    {
                                        /*
                                            r1 = this;
                                            r0 = 1
                                            r1.<init>(r0)
                                            return
                                        */
                                        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeView$addAndroidView$1$onInitializeAccessibilityNodeInfo$parentId$1.<init>():void");
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public final java.lang.Object invoke(java.lang.Object r1) {
                                        /*
                                            r0 = this;
                                            androidx.compose.ui.node.LayoutNode r1 = (androidx.compose.ui.node.LayoutNode) r1
                                            androidx.compose.ui.node.NodeChain r0 = r1.nodes
                                            r1 = 8
                                            boolean r0 = r0.m525hasH91voCI$ui_release(r1)
                                            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
                                            return r0
                                        */
                                        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeView$addAndroidView$1$onInitializeAccessibilityNodeInfo$parentId$1.invoke(java.lang.Object):java.lang.Object");
                                    }
                                }
                                androidx.compose.ui.node.LayoutNode r1 = r2
                                androidx.compose.ui.node.LayoutNode r0 = androidx.compose.ui.semantics.SemanticsNodeKt.findClosestParentNode(r1, r0)
                                if (r0 == 0) goto L28
                                int r0 = r0.semanticsId
                                java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
                                goto L29
                            L28:
                                r0 = 0
                            L29:
                                r2 = -1
                                if (r0 == 0) goto L3a
                                androidx.compose.ui.semantics.SemanticsOwner r3 = r6.semanticsOwner
                                androidx.compose.ui.semantics.SemanticsNode r3 = r3.getUnmergedRootSemanticsNode()
                                int r4 = r0.intValue()
                                int r3 = r3.id
                                if (r4 != r3) goto L3e
                            L3a:
                                java.lang.Integer r0 = java.lang.Integer.valueOf(r2)
                            L3e:
                                int r0 = r0.intValue()
                                androidx.compose.ui.platform.AndroidComposeView r5 = r3
                                r7.mParentVirtualDescendantId = r0
                                android.view.accessibility.AccessibilityNodeInfo r3 = r7.mInfo
                                r3.setParent(r5, r0)
                                int r0 = r1.semanticsId
                                androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat r1 = r6.composeAccessibilityDelegate
                                androidx.collection.MutableIntIntMap r1 = r1.idToBeforeMap
                                int r1 = r1.getOrDefault(r0)
                                if (r1 == r2) goto L75
                                androidx.compose.ui.platform.AndroidViewsHandler r3 = r6.getAndroidViewsHandler$ui_release()
                                androidx.compose.ui.viewinterop.AndroidViewHolder r3 = androidx.compose.ui.platform.SemanticsUtils_androidKt.semanticsIdToView(r3, r1)
                                if (r3 == 0) goto L67
                                android.view.accessibility.AccessibilityNodeInfo r1 = r7.mInfo
                                r1.setTraversalBefore(r3)
                                goto L6c
                            L67:
                                android.view.accessibility.AccessibilityNodeInfo r3 = r7.mInfo
                                r3.setTraversalBefore(r5, r1)
                            L6c:
                                android.view.accessibility.AccessibilityNodeInfo r1 = r7.mInfo
                                androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat r3 = r6.composeAccessibilityDelegate
                                java.lang.String r3 = r3.ExtraDataTestTraversalBeforeVal
                                androidx.compose.ui.platform.AndroidComposeView.access$addExtraDataToAccessibilityNodeInfoHelper(r6, r0, r1, r3)
                            L75:
                                androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat r1 = r6.composeAccessibilityDelegate
                                androidx.collection.MutableIntIntMap r1 = r1.idToAfterMap
                                int r1 = r1.getOrDefault(r0)
                                if (r1 == r2) goto L9d
                                androidx.compose.ui.platform.AndroidViewsHandler r2 = r6.getAndroidViewsHandler$ui_release()
                                androidx.compose.ui.viewinterop.AndroidViewHolder r2 = androidx.compose.ui.platform.SemanticsUtils_androidKt.semanticsIdToView(r2, r1)
                                if (r2 == 0) goto L8f
                                android.view.accessibility.AccessibilityNodeInfo r5 = r7.mInfo
                                r5.setTraversalAfter(r2)
                                goto L94
                            L8f:
                                android.view.accessibility.AccessibilityNodeInfo r2 = r7.mInfo
                                r2.setTraversalAfter(r5, r1)
                            L94:
                                android.view.accessibility.AccessibilityNodeInfo r5 = r7.mInfo
                                androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat r7 = r6.composeAccessibilityDelegate
                                java.lang.String r7 = r7.ExtraDataTestTraversalAfterVal
                                androidx.compose.ui.platform.AndroidComposeView.access$addExtraDataToAccessibilityNodeInfoHelper(r6, r0, r5, r7)
                            L9d:
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeView$addAndroidView$1.onInitializeAccessibilityNodeInfo(android.view.View, androidx.core.view.accessibility.AccessibilityNodeInfoCompat):void");
                        }
                    });
                }
                ViewParent parent = AndroidViewHolder.this.view.getParent();
                AndroidViewHolder androidViewHolder2 = AndroidViewHolder.this;
                if (parent != androidViewHolder2) {
                    androidViewHolder2.addView(androidViewHolder2.view);
                }
                return Unit.INSTANCE;
            }
        };
        layoutNode.onDetach = new Function1() { // from class: androidx.compose.ui.viewinterop.AndroidViewHolder$layoutNode$1$4
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Owner owner2 = (Owner) obj;
                AndroidComposeView androidComposeView = owner2 instanceof AndroidComposeView ? (AndroidComposeView) owner2 : null;
                if (androidComposeView != null) {
                    androidComposeView.removeAndroidView(AndroidViewHolder.this);
                }
                AndroidViewHolder.this.removeAllViewsInLayout();
                return Unit.INSTANCE;
            }
        };
        layoutNode.setMeasurePolicy(new MeasurePolicy() { // from class: androidx.compose.ui.viewinterop.AndroidViewHolder$layoutNode$1$5
            @Override // androidx.compose.ui.layout.MeasurePolicy
            public final int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i2) {
                AndroidViewHolder androidViewHolder = AndroidViewHolder.this;
                androidViewHolder.measure(AndroidViewHolder.access$obtainMeasureSpec(androidViewHolder, 0, i2, androidViewHolder.getLayoutParams().width), View.MeasureSpec.makeMeasureSpec(0, 0));
                return androidViewHolder.getMeasuredHeight();
            }

            @Override // androidx.compose.ui.layout.MeasurePolicy
            public final int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i2) {
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                AndroidViewHolder androidViewHolder = AndroidViewHolder.this;
                androidViewHolder.measure(makeMeasureSpec, AndroidViewHolder.access$obtainMeasureSpec(androidViewHolder, 0, i2, androidViewHolder.getLayoutParams().height));
                return androidViewHolder.getMeasuredWidth();
            }

            @Override // androidx.compose.ui.layout.MeasurePolicy
            /* renamed from: measure-3p2s80s */
            public final MeasureResult mo2measure3p2s80s(MeasureScope measureScope, List list, long j) {
                MeasureResult layout$1;
                MeasureResult layout$12;
                final AndroidViewHolder androidViewHolder = AndroidViewHolder.this;
                if (androidViewHolder.getChildCount() == 0) {
                    layout$12 = measureScope.layout$1(Constraints.m657getMinWidthimpl(j), Constraints.m656getMinHeightimpl(j), MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.ui.viewinterop.AndroidViewHolder$layoutNode$1$5$measure$1
                        @Override // kotlin.jvm.functions.Function1
                        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                            return Unit.INSTANCE;
                        }
                    });
                    return layout$12;
                }
                if (Constraints.m657getMinWidthimpl(j) != 0) {
                    androidViewHolder.getChildAt(0).setMinimumWidth(Constraints.m657getMinWidthimpl(j));
                }
                if (Constraints.m656getMinHeightimpl(j) != 0) {
                    androidViewHolder.getChildAt(0).setMinimumHeight(Constraints.m656getMinHeightimpl(j));
                }
                androidViewHolder.measure(AndroidViewHolder.access$obtainMeasureSpec(androidViewHolder, Constraints.m657getMinWidthimpl(j), Constraints.m655getMaxWidthimpl(j), androidViewHolder.getLayoutParams().width), AndroidViewHolder.access$obtainMeasureSpec(androidViewHolder, Constraints.m656getMinHeightimpl(j), Constraints.m654getMaxHeightimpl(j), androidViewHolder.getLayoutParams().height));
                int measuredWidth = androidViewHolder.getMeasuredWidth();
                int measuredHeight = androidViewHolder.getMeasuredHeight();
                final LayoutNode layoutNode2 = layoutNode;
                layout$1 = measureScope.layout$1(measuredWidth, measuredHeight, MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.ui.viewinterop.AndroidViewHolder$layoutNode$1$5$measure$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        AndroidViewHolder_androidKt.access$layoutAccordingTo(AndroidViewHolder.this, layoutNode2);
                        return Unit.INSTANCE;
                    }
                });
                return layout$1;
            }

            @Override // androidx.compose.ui.layout.MeasurePolicy
            public final int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i2) {
                AndroidViewHolder androidViewHolder = AndroidViewHolder.this;
                androidViewHolder.measure(AndroidViewHolder.access$obtainMeasureSpec(androidViewHolder, 0, i2, androidViewHolder.getLayoutParams().width), View.MeasureSpec.makeMeasureSpec(0, 0));
                return androidViewHolder.getMeasuredHeight();
            }

            @Override // androidx.compose.ui.layout.MeasurePolicy
            public final int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i2) {
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                AndroidViewHolder androidViewHolder = AndroidViewHolder.this;
                androidViewHolder.measure(makeMeasureSpec, AndroidViewHolder.access$obtainMeasureSpec(androidViewHolder, 0, i2, androidViewHolder.getLayoutParams().height));
                return androidViewHolder.getMeasuredWidth();
            }
        });
        this.layoutNode = layoutNode;
    }

    public static final int access$obtainMeasureSpec(AndroidViewHolder androidViewHolder, int i, int i2, int i3) {
        androidViewHolder.getClass();
        return (i3 >= 0 || i == i2) ? View.MeasureSpec.makeMeasureSpec(RangesKt.coerceIn(i3, i, i2), 1073741824) : (i3 != -2 || i2 == Integer.MAX_VALUE) ? (i3 != -1 || i2 == Integer.MAX_VALUE) ? View.MeasureSpec.makeMeasureSpec(0, 0) : View.MeasureSpec.makeMeasureSpec(i2, 1073741824) : View.MeasureSpec.makeMeasureSpec(i2, Integer.MIN_VALUE);
    }

    public static Insets inset(Insets insets, int i, int i2, int i3, int i4) {
        int i5 = insets.left - i;
        if (i5 < 0) {
            i5 = 0;
        }
        int i6 = insets.top - i2;
        if (i6 < 0) {
            i6 = 0;
        }
        int i7 = insets.right - i3;
        if (i7 < 0) {
            i7 = 0;
        }
        int i8 = insets.bottom - i4;
        return Insets.of(i5, i6, i7, i8 >= 0 ? i8 : 0);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean gatherTransparentRegion(Region region) {
        if (region == null) {
            return true;
        }
        getLocationInWindow(this.location);
        int[] iArr = this.location;
        int i = iArr[0];
        region.op(i, iArr[1], getWidth() + i, getHeight() + this.location[1], Region.Op.DIFFERENCE);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final CharSequence getAccessibilityClassName() {
        return getClass().getName();
    }

    @Override // android.view.View
    public final ViewGroup.LayoutParams getLayoutParams() {
        ViewGroup.LayoutParams layoutParams = this.view.getLayoutParams();
        return layoutParams == null ? new ViewGroup.LayoutParams(-1, -1) : layoutParams;
    }

    @Override // android.view.ViewGroup
    public final int getNestedScrollAxes() {
        NestedScrollingParentHelper nestedScrollingParentHelper = this.nestedScrollingParentHelper;
        return nestedScrollingParentHelper.mNestedScrollAxesNonTouch | nestedScrollingParentHelper.mNestedScrollAxesTouch;
    }

    public final WindowInsetsCompat insetToLayoutPosition(WindowInsetsCompat windowInsetsCompat) {
        WindowInsetsCompat.Impl impl = windowInsetsCompat.mImpl;
        Insets insets = impl.getInsets(-1);
        Insets insets2 = Insets.NONE;
        if (insets.equals(insets2) && impl.getInsetsIgnoringVisibility(-9).equals(insets2) && impl.getDisplayCutout() == null) {
            return windowInsetsCompat;
        }
        InnerNodeCoordinator innerNodeCoordinator = this.layoutNode.nodes.innerCoordinator;
        if (!innerNodeCoordinator.tail.isAttached) {
            return windowInsetsCompat;
        }
        long m679roundk4lQ0M = IntOffsetKt.m679roundk4lQ0M(innerNodeCoordinator.mo484localToRootMKHz9U(0L));
        int i = (int) (m679roundk4lQ0M >> 32);
        if (i < 0) {
            i = 0;
        }
        int i2 = (int) (m679roundk4lQ0M & 4294967295L);
        if (i2 < 0) {
            i2 = 0;
        }
        long mo481getSizeYbymL2g = LayoutCoordinatesKt.findRootCoordinates(innerNodeCoordinator).mo481getSizeYbymL2g();
        int i3 = (int) (mo481getSizeYbymL2g >> 32);
        int i4 = (int) (mo481getSizeYbymL2g & 4294967295L);
        long j = innerNodeCoordinator.measuredSize;
        long m679roundk4lQ0M2 = IntOffsetKt.m679roundk4lQ0M(innerNodeCoordinator.mo484localToRootMKHz9U((Float.floatToRawIntBits((int) (j >> 32)) << 32) | (Float.floatToRawIntBits((int) (j & 4294967295L)) & 4294967295L)));
        int i5 = i3 - ((int) (m679roundk4lQ0M2 >> 32));
        if (i5 < 0) {
            i5 = 0;
        }
        int i6 = i4 - ((int) (4294967295L & m679roundk4lQ0M2));
        int i7 = i6 >= 0 ? i6 : 0;
        return (i == 0 && i2 == 0 && i5 == 0 && i7 == 0) ? windowInsetsCompat : windowInsetsCompat.mImpl.inset(i, i2, i5, i7);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final ViewParent invalidateChildInParent(int[] iArr, Rect rect) {
        super.invalidateChildInParent(iArr, rect);
        if (this.isDrawing) {
            this.view.postOnAnimation(new AndroidViewHolder$$ExternalSyntheticLambda0(0, this.runInvalidate));
            return null;
        }
        this.layoutNode.invalidateLayer$ui_release();
        return null;
    }

    @Override // android.view.View
    public final boolean isNestedScrollingEnabled() {
        return this.view.isNestedScrollingEnabled();
    }

    @Override // androidx.compose.ui.node.OwnerScope
    public final boolean isValidOwnerScope() {
        return isAttachedToWindow();
    }

    @Override // androidx.core.view.OnApplyWindowInsetsListener
    public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
        return insetToLayoutPosition(windowInsetsCompat);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        ((AndroidViewHolder$runUpdate$1) this.runUpdate).invoke();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
    @Override // androidx.compose.runtime.ComposeNodeLifecycleCallback
    public final void onDeactivate() {
        this.reset.invoke();
        removeAllViewsInLayout();
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onDescendantInvalidated(View view, View view2) {
        super.onDescendantInvalidated(view, view2);
        if (this.isDrawing) {
            this.view.postOnAnimation(new AndroidViewHolder$$ExternalSyntheticLambda0(0, this.runInvalidate));
        } else {
            this.layoutNode.invalidateLayer$ui_release();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (!isAttachedToWindow()) {
            InlineClassHelperKt.throwIllegalStateException("Expected AndroidViewHolder to be attached when observing reads.");
        }
        ((AndroidComposeView) this.owner).snapshotObserver.observer.clear(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.view.layout(0, 0, i3 - i, i4 - i2);
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        if (this.view.getParent() != this) {
            setMeasuredDimension(View.MeasureSpec.getSize(i), View.MeasureSpec.getSize(i2));
            return;
        }
        if (this.view.getVisibility() == 8) {
            setMeasuredDimension(0, 0);
            return;
        }
        this.view.measure(i, i2);
        setMeasuredDimension(this.view.getMeasuredWidth(), this.view.getMeasuredHeight());
        this.lastWidthMeasureSpec = i;
        this.lastHeightMeasureSpec = i2;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedFling(View view, float f, float f2, boolean z) {
        if (!this.view.isNestedScrollingEnabled()) {
            return false;
        }
        BuildersKt.launch$default(this.dispatcher.getCoroutineScope(), null, null, new AndroidViewHolder$onNestedFling$1(z, this, VelocityKt.Velocity(f * (-1.0f), f2 * (-1.0f)), null), 3);
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedPreFling(View view, float f, float f2) {
        if (!this.view.isNestedScrollingEnabled()) {
            return false;
        }
        BuildersKt.launch$default(this.dispatcher.getCoroutineScope(), null, null, new AndroidViewHolder$onNestedPreFling$1(this, VelocityKt.Velocity(f * (-1.0f), f2 * (-1.0f)), null), 3);
        return false;
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public final void onNestedPreScroll(View view, int i, int i2, int[] iArr, int i3) {
        if (this.view.isNestedScrollingEnabled()) {
            NestedScrollDispatcher nestedScrollDispatcher = this.dispatcher;
            float f = i;
            float f2 = -1;
            long floatToRawIntBits = (Float.floatToRawIntBits(f * f2) << 32) | (Float.floatToRawIntBits(i2 * f2) & 4294967295L);
            int i4 = i3 == 0 ? 1 : 2;
            NestedScrollNode nestedScrollNode = nestedScrollDispatcher.nestedScrollNode;
            NestedScrollNode nestedScrollNode2 = null;
            if (nestedScrollNode != null && nestedScrollNode.isAttached) {
                nestedScrollNode2 = (NestedScrollNode) TraversableNodeKt.findNearestAncestor(nestedScrollNode);
            }
            long mo139onPreScrollOzD1aCk = nestedScrollNode2 != null ? nestedScrollNode2.mo139onPreScrollOzD1aCk(floatToRawIntBits, i4) : 0L;
            iArr[0] = NestedScrollInteropConnectionKt.composeToViewOffset(Float.intBitsToFloat((int) (mo139onPreScrollOzD1aCk >> 32)));
            iArr[1] = NestedScrollInteropConnectionKt.composeToViewOffset(Float.intBitsToFloat((int) (mo139onPreScrollOzD1aCk & 4294967295L)));
        }
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public final void onNestedScroll(View view, int i, int i2, int i3, int i4, int i5) {
        if (this.view.isNestedScrollingEnabled()) {
            float f = -1;
            this.dispatcher.m452dispatchPostScrollDzOQY0M(i5 == 0 ? 1 : 2, (Float.floatToRawIntBits(i2 * f) & 4294967295L) | (Float.floatToRawIntBits(i * f) << 32), (Float.floatToRawIntBits(i3 * f) << 32) | (Float.floatToRawIntBits(i4 * f) & 4294967295L));
        }
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public final void onNestedScrollAccepted(View view, View view2, int i, int i2) {
        NestedScrollingParentHelper nestedScrollingParentHelper = this.nestedScrollingParentHelper;
        if (i2 == 1) {
            nestedScrollingParentHelper.mNestedScrollAxesNonTouch = i;
        } else {
            nestedScrollingParentHelper.mNestedScrollAxesTouch = i;
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
    @Override // androidx.compose.runtime.ComposeNodeLifecycleCallback
    public final void onRelease() {
        this.release.invoke();
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
    @Override // androidx.compose.runtime.ComposeNodeLifecycleCallback
    public final void onReuse() {
        if (this.view.getParent() != this) {
            addView(this.view);
        } else {
            this.reset.invoke();
        }
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public final boolean onStartNestedScroll(View view, View view2, int i, int i2) {
        return ((i & 2) == 0 && (i & 1) == 0) ? false : true;
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public final void onStopNestedScroll(View view, int i) {
        NestedScrollingParentHelper nestedScrollingParentHelper = this.nestedScrollingParentHelper;
        if (i == 1) {
            nestedScrollingParentHelper.mNestedScrollAxesNonTouch = 0;
        } else {
            nestedScrollingParentHelper.mNestedScrollAxesTouch = 0;
        }
    }

    @Override // android.view.View
    public final void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestDisallowInterceptTouchEvent(boolean z) {
        RequestDisallowInterceptTouchEvent requestDisallowInterceptTouchEvent = this.onRequestDisallowInterceptTouchEvent;
        if (requestDisallowInterceptTouchEvent != null) {
            requestDisallowInterceptTouchEvent.invoke(Boolean.valueOf(z));
        }
        super.requestDisallowInterceptTouchEvent(z);
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return true;
    }

    @Override // androidx.core.view.NestedScrollingParent3
    public final void onNestedScroll(View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
        if (this.view.isNestedScrollingEnabled()) {
            float f = -1;
            long m452dispatchPostScrollDzOQY0M = this.dispatcher.m452dispatchPostScrollDzOQY0M(i5 == 0 ? 1 : 2, (Float.floatToRawIntBits(i * f) << 32) | (Float.floatToRawIntBits(i2 * f) & 4294967295L), (Float.floatToRawIntBits(i3 * f) << 32) | (Float.floatToRawIntBits(i4 * f) & 4294967295L));
            iArr[0] = NestedScrollInteropConnectionKt.composeToViewOffset(Float.intBitsToFloat((int) (m452dispatchPostScrollDzOQY0M >> 32)));
            iArr[1] = NestedScrollInteropConnectionKt.composeToViewOffset(Float.intBitsToFloat((int) (m452dispatchPostScrollDzOQY0M & 4294967295L)));
        }
    }
}
