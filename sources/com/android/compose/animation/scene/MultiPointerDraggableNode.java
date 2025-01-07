package com.android.compose.animation.scene;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNode;
import androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl;
import androidx.compose.ui.input.pointer.util.VelocityTracker;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.ObserverModifierNode;
import androidx.compose.ui.node.ObserverModifierNodeKt;
import androidx.compose.ui.node.PointerInputModifierNode;
import com.android.compose.ui.util.SpaceVectorConverter;
import com.android.compose.ui.util.SpaceVectorConverterKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MultiPointerDraggableNode extends DelegatingNode implements PointerInputModifierNode, CompositionLocalConsumerModifierNode, ObserverModifierNode, SpaceVectorConverter {
    public SpaceVectorConverter converter;
    public final NestedScrollDispatcher dispatcher;
    public final Function0 enabled;
    public final Function3 onDragStarted;
    public final Function0 onFirstPointerDown;
    public Orientation orientation;
    public final SuspendingPointerInputModifierNode pointerInput;
    public final SuspendingPointerInputModifierNode pointerTracker;
    public int pointersDown;
    public boolean previousEnabled;
    public final Function1 startDragImmediately;
    public Offset startedPosition;
    public final SwipeDetector swipeDetector;
    public final VelocityTracker velocityTracker;

    public MultiPointerDraggableNode(Orientation orientation, Function0 function0, Function1 function1, Function3 function3, Function0 function02, SwipeDetector swipeDetector, NestedScrollDispatcher nestedScrollDispatcher) {
        this.startDragImmediately = function1;
        this.onDragStarted = function3;
        this.onFirstPointerDown = function02;
        this.swipeDetector = swipeDetector;
        this.dispatcher = nestedScrollDispatcher;
        final int i = 1;
        SuspendingPointerInputModifierNodeImpl SuspendingPointerInputModifierNode = SuspendingPointerInputFilterKt.SuspendingPointerInputModifierNode(new PointerInputEventHandler(this) { // from class: com.android.compose.animation.scene.MultiPointerDraggableNode$pointerInput$1
            public final /* synthetic */ MultiPointerDraggableNode this$0;

            {
                this.this$0 = this;
            }

            @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
            public final Object invoke(PointerInputScope pointerInputScope, Continuation continuation) {
                Object awaitPointerEventScope;
                switch (i) {
                    case 0:
                        MultiPointerDraggableNode multiPointerDraggableNode = this.this$0;
                        boolean booleanValue = ((Boolean) ((SwipeToSceneNode$multiPointerDraggableNode$1) multiPointerDraggableNode.enabled).invoke()).booleanValue();
                        Unit unit = Unit.INSTANCE;
                        if (!booleanValue || (awaitPointerEventScope = pointerInputScope.awaitPointerEventScope(continuation, new MultiPointerDraggableNode$pointerInput$3(continuation.getContext(), multiPointerDraggableNode, null))) != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            awaitPointerEventScope = unit;
                        }
                        return awaitPointerEventScope == CoroutineSingletons.COROUTINE_SUSPENDED ? awaitPointerEventScope : unit;
                    default:
                        MultiPointerDraggableNode multiPointerDraggableNode2 = this.this$0;
                        multiPointerDraggableNode2.getClass();
                        Object awaitPointerEventScope2 = pointerInputScope.awaitPointerEventScope(continuation, new MultiPointerDraggableNode$pointerTracker$3(continuation.getContext(), multiPointerDraggableNode2, null));
                        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                        Unit unit2 = Unit.INSTANCE;
                        if (awaitPointerEventScope2 != coroutineSingletons) {
                            awaitPointerEventScope2 = unit2;
                        }
                        return awaitPointerEventScope2 == coroutineSingletons ? awaitPointerEventScope2 : unit2;
                }
            }
        });
        delegate(SuspendingPointerInputModifierNode);
        this.pointerTracker = SuspendingPointerInputModifierNode;
        final int i2 = 0;
        SuspendingPointerInputModifierNodeImpl SuspendingPointerInputModifierNode2 = SuspendingPointerInputFilterKt.SuspendingPointerInputModifierNode(new PointerInputEventHandler(this) { // from class: com.android.compose.animation.scene.MultiPointerDraggableNode$pointerInput$1
            public final /* synthetic */ MultiPointerDraggableNode this$0;

            {
                this.this$0 = this;
            }

            @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
            public final Object invoke(PointerInputScope pointerInputScope, Continuation continuation) {
                Object awaitPointerEventScope;
                switch (i2) {
                    case 0:
                        MultiPointerDraggableNode multiPointerDraggableNode = this.this$0;
                        boolean booleanValue = ((Boolean) ((SwipeToSceneNode$multiPointerDraggableNode$1) multiPointerDraggableNode.enabled).invoke()).booleanValue();
                        Unit unit = Unit.INSTANCE;
                        if (!booleanValue || (awaitPointerEventScope = pointerInputScope.awaitPointerEventScope(continuation, new MultiPointerDraggableNode$pointerInput$3(continuation.getContext(), multiPointerDraggableNode, null))) != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            awaitPointerEventScope = unit;
                        }
                        return awaitPointerEventScope == CoroutineSingletons.COROUTINE_SUSPENDED ? awaitPointerEventScope : unit;
                    default:
                        MultiPointerDraggableNode multiPointerDraggableNode2 = this.this$0;
                        multiPointerDraggableNode2.getClass();
                        Object awaitPointerEventScope2 = pointerInputScope.awaitPointerEventScope(continuation, new MultiPointerDraggableNode$pointerTracker$3(continuation.getContext(), multiPointerDraggableNode2, null));
                        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                        Unit unit2 = Unit.INSTANCE;
                        if (awaitPointerEventScope2 != coroutineSingletons) {
                            awaitPointerEventScope2 = unit2;
                        }
                        return awaitPointerEventScope2 == coroutineSingletons ? awaitPointerEventScope2 : unit2;
                }
            }
        });
        delegate(SuspendingPointerInputModifierNode2);
        this.pointerInput = SuspendingPointerInputModifierNode2;
        this.velocityTracker = new VelocityTracker();
        this.enabled = function0;
        this.converter = SpaceVectorConverterKt.SpaceVectorConverter(orientation);
        this.orientation = orientation;
    }

    /* JADX WARN: Removed duplicated region for block: B:119:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x028b A[Catch: all -> 0x0061, TryCatch #0 {all -> 0x0061, blocks: (B:14:0x0059, B:16:0x0280, B:18:0x028b, B:23:0x02b6, B:35:0x030b, B:38:0x0312, B:41:0x031b, B:45:0x0263, B:54:0x02c0, B:56:0x02c6, B:58:0x02cf, B:63:0x02df, B:66:0x02e4, B:60:0x02db, B:70:0x02eb, B:72:0x02fb, B:20:0x02a4), top: B:13:0x0059 }] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x02bc  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0349  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x034d  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x030b A[Catch: all -> 0x0061, TryCatch #0 {all -> 0x0061, blocks: (B:14:0x0059, B:16:0x0280, B:18:0x028b, B:23:0x02b6, B:35:0x030b, B:38:0x0312, B:41:0x031b, B:45:0x0263, B:54:0x02c0, B:56:0x02c6, B:58:0x02cf, B:63:0x02df, B:66:0x02e4, B:60:0x02db, B:70:0x02eb, B:72:0x02fb, B:20:0x02a4), top: B:13:0x0059 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0318  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x031b A[Catch: all -> 0x0061, TRY_LEAVE, TryCatch #0 {all -> 0x0061, blocks: (B:14:0x0059, B:16:0x0280, B:18:0x028b, B:23:0x02b6, B:35:0x030b, B:38:0x0312, B:41:0x031b, B:45:0x0263, B:54:0x02c0, B:56:0x02c6, B:58:0x02cf, B:63:0x02df, B:66:0x02e4, B:60:0x02db, B:70:0x02eb, B:72:0x02fb, B:20:0x02a4), top: B:13:0x0059 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x027e  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x02c0 A[Catch: all -> 0x0061, TryCatch #0 {all -> 0x0061, blocks: (B:14:0x0059, B:16:0x0280, B:18:0x028b, B:23:0x02b6, B:35:0x030b, B:38:0x0312, B:41:0x031b, B:45:0x0263, B:54:0x02c0, B:56:0x02c6, B:58:0x02cf, B:63:0x02df, B:66:0x02e4, B:60:0x02db, B:70:0x02eb, B:72:0x02fb, B:20:0x02a4), top: B:13:0x0059 }] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x02ae A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x01cc  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01fd  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:44:0x027c -> B:16:0x0280). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$detectDragGestures(com.android.compose.animation.scene.MultiPointerDraggableNode r20, final androidx.compose.ui.input.pointer.AwaitPointerEventScope r21, androidx.compose.foundation.gestures.Orientation r22, kotlin.jvm.functions.Function1 r23, kotlin.jvm.functions.Function3 r24, kotlin.jvm.functions.Function2 r25, kotlin.jvm.functions.Function1 r26, kotlin.jvm.functions.Function1 r27, com.android.compose.animation.scene.SwipeDetector r28, kotlin.coroutines.jvm.internal.BaseContinuationImpl r29) {
        /*
            Method dump skipped, instructions count: 864
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.compose.animation.scene.MultiPointerDraggableNode.access$detectDragGestures(com.android.compose.animation.scene.MultiPointerDraggableNode, androidx.compose.ui.input.pointer.AwaitPointerEventScope, androidx.compose.foundation.gestures.Orientation, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function3, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, com.android.compose.animation.scene.SwipeDetector, kotlin.coroutines.jvm.internal.BaseContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x006c, code lost:
    
        r4 = r2.size();
        r6 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0071, code lost:
    
        if (r6 >= r4) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0073, code lost:
    
        r7 = (androidx.compose.ui.input.pointer.PointerInputChange) r2.get(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x007d, code lost:
    
        if (androidx.compose.ui.input.pointer.PointerEventKt.changedToDown(r7) != false) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x008c, code lost:
    
        if (r11.converter.mo735toFloatk4lQ0M(androidx.compose.ui.input.pointer.PointerEventKt.positionChangeInternal(r7, false)) != 0.0f) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x008f, code lost:
    
        r6 = r6 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0092, code lost:
    
        return r14;
     */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0041 A[EDGE_INSN: B:32:0x0041->B:23:0x0041 BREAK  A[LOOP:0: B:11:0x0060->B:14:0x0093], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:13:0x0053 -> B:10:0x0056). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object awaitConsumableEvent(androidx.compose.ui.input.pointer.AwaitPointerEventScope r12, kotlin.jvm.functions.Function0 r13, kotlin.coroutines.jvm.internal.ContinuationImpl r14) {
        /*
            r11 = this;
            boolean r0 = r14 instanceof com.android.compose.animation.scene.MultiPointerDraggableNode$awaitConsumableEvent$1
            if (r0 == 0) goto L13
            r0 = r14
            com.android.compose.animation.scene.MultiPointerDraggableNode$awaitConsumableEvent$1 r0 = (com.android.compose.animation.scene.MultiPointerDraggableNode$awaitConsumableEvent$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.compose.animation.scene.MultiPointerDraggableNode$awaitConsumableEvent$1 r0 = new com.android.compose.animation.scene.MultiPointerDraggableNode$awaitConsumableEvent$1
            r0.<init>(r11, r14)
        L18:
            java.lang.Object r14 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3e
            if (r2 != r3) goto L36
            java.lang.Object r11 = r0.L$2
            kotlin.jvm.functions.Function0 r11 = (kotlin.jvm.functions.Function0) r11
            java.lang.Object r12 = r0.L$1
            androidx.compose.ui.input.pointer.AwaitPointerEventScope r12 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r12
            java.lang.Object r13 = r0.L$0
            com.android.compose.animation.scene.MultiPointerDraggableNode r13 = (com.android.compose.animation.scene.MultiPointerDraggableNode) r13
            kotlin.ResultKt.throwOnFailure(r14)
            r10 = r13
            r13 = r11
            r11 = r10
            goto L56
        L36:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L3e:
            kotlin.ResultKt.throwOnFailure(r14)
        L41:
            java.lang.Object r14 = r13.invoke()
            androidx.compose.ui.input.pointer.PointerEventPass r14 = (androidx.compose.ui.input.pointer.PointerEventPass) r14
            r0.L$0 = r11
            r0.L$1 = r12
            r0.L$2 = r13
            r0.label = r3
            java.lang.Object r14 = r12.awaitPointerEvent(r14, r0)
            if (r14 != r1) goto L56
            return r1
        L56:
            androidx.compose.ui.input.pointer.PointerEvent r14 = (androidx.compose.ui.input.pointer.PointerEvent) r14
            java.util.List r2 = r14.changes
            int r4 = r2.size()
            r5 = 0
            r6 = r5
        L60:
            if (r6 >= r4) goto L41
            java.lang.Object r7 = r2.get(r6)
            androidx.compose.ui.input.pointer.PointerInputChange r7 = (androidx.compose.ui.input.pointer.PointerInputChange) r7
            boolean r7 = r7.pressed
            if (r7 == 0) goto L93
            int r4 = r2.size()
            r6 = r5
        L71:
            if (r6 >= r4) goto L92
            java.lang.Object r7 = r2.get(r6)
            androidx.compose.ui.input.pointer.PointerInputChange r7 = (androidx.compose.ui.input.pointer.PointerInputChange) r7
            boolean r8 = androidx.compose.ui.input.pointer.PointerEventKt.changedToDown(r7)
            if (r8 != 0) goto L8f
            long r7 = androidx.compose.ui.input.pointer.PointerEventKt.positionChangeInternal(r7, r5)
            com.android.compose.ui.util.SpaceVectorConverter r9 = r11.converter
            float r7 = r9.mo735toFloatk4lQ0M(r7)
            r8 = 0
            int r7 = (r7 > r8 ? 1 : (r7 == r8 ? 0 : -1))
            if (r7 != 0) goto L8f
            goto L41
        L8f:
            int r6 = r6 + 1
            goto L71
        L92:
            return r14
        L93:
            int r6 = r6 + 1
            goto L60
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.compose.animation.scene.MultiPointerDraggableNode.awaitConsumableEvent(androidx.compose.ui.input.pointer.AwaitPointerEventScope, kotlin.jvm.functions.Function0, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final void onAttach() {
        this.previousEnabled = ((Boolean) this.enabled.invoke()).booleanValue();
        onObservedReadsChanged();
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public final void onCancelPointerInput() {
        ((SuspendingPointerInputModifierNodeImpl) this.pointerTracker).onCancelPointerInput();
        ((SuspendingPointerInputModifierNodeImpl) this.pointerInput).onCancelPointerInput();
    }

    @Override // androidx.compose.ui.node.ObserverModifierNode
    public final void onObservedReadsChanged() {
        ObserverModifierNodeKt.observeReads(this, new Function0() { // from class: com.android.compose.animation.scene.MultiPointerDraggableNode$onObservedReadsChanged$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                boolean booleanValue = ((Boolean) MultiPointerDraggableNode.this.enabled.invoke()).booleanValue();
                MultiPointerDraggableNode multiPointerDraggableNode = MultiPointerDraggableNode.this;
                if (booleanValue != multiPointerDraggableNode.previousEnabled) {
                    ((SuspendingPointerInputModifierNodeImpl) multiPointerDraggableNode.pointerInput).resetPointerInputHandler();
                }
                MultiPointerDraggableNode.this.previousEnabled = booleanValue;
                return Unit.INSTANCE;
            }
        });
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    /* renamed from: onPointerEvent-H0pRuoY */
    public final void mo15onPointerEventH0pRuoY(PointerEvent pointerEvent, PointerEventPass pointerEventPass, long j) {
        ((SuspendingPointerInputModifierNodeImpl) this.pointerTracker).mo15onPointerEventH0pRuoY(pointerEvent, pointerEventPass, j);
        ((SuspendingPointerInputModifierNodeImpl) this.pointerInput).mo15onPointerEventH0pRuoY(pointerEvent, pointerEventPass, j);
    }

    @Override // com.android.compose.ui.util.SpaceVectorConverter
    /* renamed from: toFloat-TH1AsA0, reason: not valid java name */
    public final float mo734toFloatTH1AsA0(long j) {
        return this.converter.mo734toFloatTH1AsA0(j);
    }

    @Override // com.android.compose.ui.util.SpaceVectorConverter
    /* renamed from: toFloat-k-4lQ0M, reason: not valid java name */
    public final float mo735toFloatk4lQ0M(long j) {
        return this.converter.mo735toFloatk4lQ0M(j);
    }

    @Override // com.android.compose.ui.util.SpaceVectorConverter
    /* renamed from: toOffset-tuRUvjQ, reason: not valid java name */
    public final long mo736toOffsettuRUvjQ(float f) {
        return this.converter.mo736toOffsettuRUvjQ(f);
    }

    @Override // com.android.compose.ui.util.SpaceVectorConverter
    /* renamed from: toVelocity-adjELrA, reason: not valid java name */
    public final long mo737toVelocityadjELrA(float f) {
        return this.converter.mo737toVelocityadjELrA(f);
    }
}
