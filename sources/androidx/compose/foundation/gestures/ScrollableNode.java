package androidx.compose.foundation.gestures;

import android.view.KeyEvent;
import androidx.compose.animation.SplineBasedFloatDecayAnimationSpec;
import androidx.compose.animation.core.DecayAnimationSpecKt;
import androidx.compose.foundation.FocusedBoundsObserverNode;
import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.OverscrollEffect;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.relocation.BringIntoViewResponderNode;
import androidx.compose.ui.focus.FocusTargetNode;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.KeyInputModifierNode;
import androidx.compose.ui.input.key.Key_androidKt;
import androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher;
import androidx.compose.ui.input.nestedscroll.NestedScrollNode;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerEventType;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.ObserverModifierNode;
import androidx.compose.ui.node.ObserverModifierNodeKt;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.semantics.AccessibilityAction;
import androidx.compose.ui.semantics.SemanticsActions;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Density;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class ScrollableNode extends DragGestureNode implements ObserverModifierNode, CompositionLocalConsumerModifierNode, KeyInputModifierNode, SemanticsModifierNode {
    public final ContentInViewNode contentInViewNode;
    public final DefaultFlingBehavior defaultFlingBehavior;
    public FlingBehavior flingBehavior;
    public final ScrollableNestedScrollConnection nestedScrollConnection;
    public final NestedScrollDispatcher nestedScrollDispatcher;
    public OverscrollEffect overscrollEffect;
    public Function2 scrollByAction;
    public Function2 scrollByOffsetAction;
    public ScrollConfig scrollConfig;
    public final ScrollableContainerNode scrollableContainerNode;
    public final ScrollingLogic scrollingLogic;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v0, types: [androidx.compose.foundation.gestures.ScrollableNode, androidx.compose.ui.node.DelegatingNode] */
    /* JADX WARN: Type inference failed for: r2v3, types: [androidx.compose.foundation.gestures.FlingBehavior] */
    public ScrollableNode(OverscrollEffect overscrollEffect, BringIntoViewSpec bringIntoViewSpec, FlingBehavior flingBehavior, Orientation orientation, ScrollableState scrollableState, MutableInteractionSource mutableInteractionSource, boolean z, boolean z2) {
        super(ScrollableKt.CanDragCalculation, z, mutableInteractionSource, orientation);
        this.overscrollEffect = overscrollEffect;
        this.flingBehavior = flingBehavior;
        NestedScrollDispatcher nestedScrollDispatcher = new NestedScrollDispatcher();
        this.nestedScrollDispatcher = nestedScrollDispatcher;
        ScrollableContainerNode scrollableContainerNode = new ScrollableContainerNode();
        scrollableContainerNode.enabled = z;
        delegate(scrollableContainerNode);
        this.scrollableContainerNode = scrollableContainerNode;
        DefaultFlingBehavior defaultFlingBehavior = new DefaultFlingBehavior(DecayAnimationSpecKt.generateDecayAnimationSpec(new SplineBasedFloatDecayAnimationSpec(ScrollableKt.UnityDensity)));
        this.defaultFlingBehavior = defaultFlingBehavior;
        OverscrollEffect overscrollEffect2 = this.overscrollEffect;
        ?? r2 = this.flingBehavior;
        ScrollingLogic scrollingLogic = new ScrollingLogic(scrollableState, overscrollEffect2, r2 == 0 ? defaultFlingBehavior : r2, orientation, z2, nestedScrollDispatcher);
        this.scrollingLogic = scrollingLogic;
        ScrollableNestedScrollConnection scrollableNestedScrollConnection = new ScrollableNestedScrollConnection(scrollingLogic, z);
        this.nestedScrollConnection = scrollableNestedScrollConnection;
        ContentInViewNode contentInViewNode = new ContentInViewNode(orientation, scrollingLogic, z2, bringIntoViewSpec);
        delegate(contentInViewNode);
        this.contentInViewNode = contentInViewNode;
        delegate(new NestedScrollNode(scrollableNestedScrollConnection, nestedScrollDispatcher));
        delegate(new FocusTargetNode(2, null));
        BringIntoViewResponderNode bringIntoViewResponderNode = new BringIntoViewResponderNode();
        bringIntoViewResponderNode.responder = contentInViewNode;
        delegate(bringIntoViewResponderNode);
        Function1 function1 = new Function1() { // from class: androidx.compose.foundation.gestures.ScrollableNode.1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ScrollableNode.this.contentInViewNode.focusedChild = (LayoutCoordinates) obj;
                return Unit.INSTANCE;
            }
        };
        FocusedBoundsObserverNode focusedBoundsObserverNode = new FocusedBoundsObserverNode();
        focusedBoundsObserverNode.onPositioned = function1;
        delegate(focusedBoundsObserverNode);
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public final void applySemantics(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        if (this.enabled && (this.scrollByAction == null || this.scrollByOffsetAction == null)) {
            this.scrollByAction = new Function2() { // from class: androidx.compose.foundation.gestures.ScrollableNode$setScrollSemanticsActions$1

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: androidx.compose.foundation.gestures.ScrollableNode$setScrollSemanticsActions$1$1, reason: invalid class name */
                final class AnonymousClass1 extends SuspendLambda implements Function2 {
                    final /* synthetic */ float $x;
                    final /* synthetic */ float $y;
                    int label;
                    final /* synthetic */ ScrollableNode this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public AnonymousClass1(ScrollableNode scrollableNode, float f, float f2, Continuation continuation) {
                        super(2, continuation);
                        this.this$0 = scrollableNode;
                        this.$x = f;
                        this.$y = f2;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation create(Object obj, Continuation continuation) {
                        return new AnonymousClass1(this.this$0, this.$x, this.$y, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                        int i = this.label;
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            ScrollingLogic scrollingLogic = this.this$0.scrollingLogic;
                            float f = this.$x;
                            float f2 = this.$y;
                            long floatToRawIntBits = Float.floatToRawIntBits(f);
                            long floatToRawIntBits2 = Float.floatToRawIntBits(f2);
                            this.label = 1;
                            if (ScrollableKt.m68access$semanticsScrollByd4ec7I(scrollingLogic, (floatToRawIntBits << 32) | (floatToRawIntBits2 & 4294967295L), this) == coroutineSingletons) {
                                return coroutineSingletons;
                            }
                        } else {
                            if (i != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            ResultKt.throwOnFailure(obj);
                        }
                        return Unit.INSTANCE;
                    }
                }

                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    BuildersKt.launch$default(ScrollableNode.this.getCoroutineScope(), null, null, new AnonymousClass1(ScrollableNode.this, ((Number) obj).floatValue(), ((Number) obj2).floatValue(), null), 3);
                    return Boolean.TRUE;
                }
            };
            this.scrollByOffsetAction = new ScrollableNode$setScrollSemanticsActions$2(this, null);
        }
        Function2 function2 = this.scrollByAction;
        if (function2 != null) {
            KProperty[] kPropertyArr = SemanticsPropertiesKt.$$delegatedProperties;
            ((SemanticsConfiguration) semanticsPropertyReceiver).set(SemanticsActions.ScrollBy, new AccessibilityAction(null, function2));
        }
        Function2 function22 = this.scrollByOffsetAction;
        if (function22 != null) {
            KProperty[] kPropertyArr2 = SemanticsPropertiesKt.$$delegatedProperties;
            ((SemanticsConfiguration) semanticsPropertyReceiver).set(SemanticsActions.ScrollByOffset, function22);
        }
    }

    @Override // androidx.compose.foundation.gestures.DragGestureNode
    public final Object drag(Continuation continuation, Function2 function2) {
        MutatePriority mutatePriority = MutatePriority.UserInput;
        ScrollingLogic scrollingLogic = this.scrollingLogic;
        Object scroll = scrollingLogic.scroll(mutatePriority, new ScrollableNode$drag$2$1(scrollingLogic, null, function2), (ContinuationImpl) continuation);
        return scroll == CoroutineSingletons.COROUTINE_SUSPENDED ? scroll : Unit.INSTANCE;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final boolean getShouldAutoInvalidate() {
        return false;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final void onAttach() {
        ObserverModifierNodeKt.observeReads(this, new ScrollableNode$updateDefaultFlingBehavior$1(this));
        this.scrollConfig = AndroidConfig.INSTANCE;
    }

    @Override // androidx.compose.foundation.gestures.DragGestureNode
    /* renamed from: onDragStopped-TH1AsA0 */
    public final void mo67onDragStoppedTH1AsA0(long j) {
        if (this.isAttached) {
            BuildersKt.launch$default(getCoroutineScope(), null, null, new ScrollableNode$onDragStopped$1(this, j, null), 3);
        }
    }

    @Override // androidx.compose.ui.input.key.KeyInputModifierNode
    /* renamed from: onKeyEvent-ZmokQxo */
    public final boolean mo14onKeyEventZmokQxo(KeyEvent keyEvent) {
        long floatToRawIntBits;
        long j;
        if (!this.enabled) {
            return false;
        }
        if ((!Key.m446equalsimpl0(KeyEvent_androidKt.m448getKeyZmokQxo(keyEvent), Key.PageDown) && !Key.m446equalsimpl0(Key_androidKt.Key(keyEvent.getKeyCode()), Key.PageUp)) || !KeyEventType.m447equalsimpl0(KeyEvent_androidKt.m449getTypeZmokQxo(keyEvent), 2) || keyEvent.isCtrlPressed()) {
            return false;
        }
        boolean z = this.scrollingLogic.orientation == Orientation.Vertical;
        ContentInViewNode contentInViewNode = this.contentInViewNode;
        if (z) {
            int i = (int) (contentInViewNode.viewportSize & 4294967295L);
            float f = Key.m446equalsimpl0(Key_androidKt.Key(keyEvent.getKeyCode()), Key.PageUp) ? i : -i;
            long floatToRawIntBits2 = Float.floatToRawIntBits(0.0f);
            floatToRawIntBits = Float.floatToRawIntBits(f);
            j = floatToRawIntBits2 << 32;
        } else {
            int i2 = (int) (contentInViewNode.viewportSize >> 32);
            long floatToRawIntBits3 = Float.floatToRawIntBits(Key.m446equalsimpl0(Key_androidKt.Key(keyEvent.getKeyCode()), Key.PageUp) ? i2 : -i2);
            floatToRawIntBits = Float.floatToRawIntBits(0.0f);
            j = floatToRawIntBits3 << 32;
        }
        BuildersKt.launch$default(getCoroutineScope(), null, null, new ScrollableNode$onKeyEvent$1(this, j | (floatToRawIntBits & 4294967295L), null), 3);
        return true;
    }

    @Override // androidx.compose.ui.node.ObserverModifierNode
    public final void onObservedReadsChanged() {
        ObserverModifierNodeKt.observeReads(this, new ScrollableNode$updateDefaultFlingBehavior$1(this));
    }

    /* JADX WARN: Type inference failed for: r5v1, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    @Override // androidx.compose.foundation.gestures.DragGestureNode, androidx.compose.ui.node.PointerInputModifierNode
    /* renamed from: onPointerEvent-H0pRuoY */
    public final void mo15onPointerEventH0pRuoY(PointerEvent pointerEvent, PointerEventPass pointerEventPass, long j) {
        long j2;
        List list = pointerEvent.changes;
        int size = list.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                break;
            }
            if (((Boolean) this.canDrag.invoke((PointerInputChange) list.get(i))).booleanValue()) {
                super.mo15onPointerEventH0pRuoY(pointerEvent, pointerEventPass, j);
                break;
            }
            i++;
        }
        if (pointerEventPass == PointerEventPass.Main && PointerEventType.m461equalsimpl0(pointerEvent.type, 6)) {
            List list2 = pointerEvent.changes;
            int size2 = list2.size();
            for (int i2 = 0; i2 < size2; i2++) {
                if (((PointerInputChange) list2.get(i2)).isConsumed()) {
                    return;
                }
            }
            Intrinsics.checkNotNull(this.scrollConfig);
            Density density = DelegatableNodeKt.requireLayoutNode(this).density;
            List list3 = pointerEvent.changes;
            Offset offset = new Offset(0L);
            int size3 = list3.size();
            int i3 = 0;
            while (true) {
                j2 = offset.packedValue;
                if (i3 >= size3) {
                    break;
                }
                offset = new Offset(Offset.m315plusMKHz9U(j2, ((PointerInputChange) list3.get(i3)).scrollDelta));
                i3++;
            }
            BuildersKt.launch$default(getCoroutineScope(), null, null, new ScrollableNode$processMouseWheelEvent$2$1(this, Offset.m316timestuRUvjQ(-density.mo51toPx0680j_4(64), j2), null), 3);
            List list4 = pointerEvent.changes;
            int size4 = list4.size();
            for (int i4 = 0; i4 < size4; i4++) {
                ((PointerInputChange) list4.get(i4)).consume();
            }
        }
    }

    @Override // androidx.compose.ui.input.key.KeyInputModifierNode
    /* renamed from: onPreKeyEvent-ZmokQxo */
    public final boolean mo16onPreKeyEventZmokQxo(KeyEvent keyEvent) {
        return false;
    }

    @Override // androidx.compose.foundation.gestures.DragGestureNode
    public final boolean startDragImmediately() {
        ScrollingLogic scrollingLogic = this.scrollingLogic;
        if (!scrollingLogic.scrollableState.isScrollInProgress()) {
            OverscrollEffect overscrollEffect = scrollingLogic.overscrollEffect;
            if (!(overscrollEffect != null ? overscrollEffect.isInProgress() : false)) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.compose.foundation.gestures.DragGestureNode
    /* renamed from: onDragStarted-k-4lQ0M */
    public final void mo66onDragStartedk4lQ0M(long j) {
    }
}
