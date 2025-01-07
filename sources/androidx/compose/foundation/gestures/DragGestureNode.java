package androidx.compose.foundation.gestures;

import androidx.compose.foundation.gestures.DragEvent;
import androidx.compose.foundation.interaction.DragInteraction$Cancel;
import androidx.compose.foundation.interaction.DragInteraction$Start;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNode;
import androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl;
import androidx.compose.ui.input.pointer.util.VelocityTracker;
import androidx.compose.ui.input.pointer.util.VelocityTrackerKt;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.PointerInputModifierNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.unit.Velocity;
import androidx.compose.ui.unit.VelocityKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.channels.BufferedChannel;
import kotlinx.coroutines.channels.ChannelKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class DragGestureNode extends DelegatingNode implements PointerInputModifierNode, CompositionLocalConsumerModifierNode {
    public Lambda canDrag;
    public BufferedChannel channel;
    public DragInteraction$Start dragInteraction;
    public boolean enabled;
    public MutableInteractionSource interactionSource;
    public boolean isListeningForEvents;
    public Orientation orientationLock;
    public SuspendingPointerInputModifierNode pointerInputNode;

    /* JADX WARN: Multi-variable type inference failed */
    public DragGestureNode(Function1 function1, boolean z, MutableInteractionSource mutableInteractionSource, Orientation orientation) {
        this.orientationLock = orientation;
        this.canDrag = (Lambda) function1;
        this.enabled = z;
        this.interactionSource = mutableInteractionSource;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$processDragCancel(androidx.compose.foundation.gestures.DragGestureNode r5, kotlin.coroutines.jvm.internal.ContinuationImpl r6) {
        /*
            r5.getClass()
            boolean r0 = r6 instanceof androidx.compose.foundation.gestures.DragGestureNode$processDragCancel$1
            if (r0 == 0) goto L16
            r0 = r6
            androidx.compose.foundation.gestures.DragGestureNode$processDragCancel$1 r0 = (androidx.compose.foundation.gestures.DragGestureNode$processDragCancel$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L16
            int r1 = r1 - r2
            r0.label = r1
            goto L1b
        L16:
            androidx.compose.foundation.gestures.DragGestureNode$processDragCancel$1 r0 = new androidx.compose.foundation.gestures.DragGestureNode$processDragCancel$1
            r0.<init>(r5, r6)
        L1b:
            java.lang.Object r6 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L36
            if (r2 != r3) goto L2e
            java.lang.Object r5 = r0.L$0
            androidx.compose.foundation.gestures.DragGestureNode r5 = (androidx.compose.foundation.gestures.DragGestureNode) r5
            kotlin.ResultKt.throwOnFailure(r6)
            goto L51
        L2e:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L36:
            kotlin.ResultKt.throwOnFailure(r6)
            androidx.compose.foundation.interaction.DragInteraction$Start r6 = r5.dragInteraction
            if (r6 == 0) goto L54
            androidx.compose.foundation.interaction.MutableInteractionSource r2 = r5.interactionSource
            if (r2 == 0) goto L51
            androidx.compose.foundation.interaction.DragInteraction$Cancel r4 = new androidx.compose.foundation.interaction.DragInteraction$Cancel
            r4.<init>(r6)
            r0.L$0 = r5
            r0.label = r3
            java.lang.Object r6 = r2.emit(r4, r0)
            if (r6 != r1) goto L51
            goto L5b
        L51:
            r6 = 0
            r5.dragInteraction = r6
        L54:
            r0 = 0
            r5.mo67onDragStoppedTH1AsA0(r0)
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
        L5b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureNode.access$processDragCancel(androidx.compose.foundation.gestures.DragGestureNode, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$processDragStart(androidx.compose.foundation.gestures.DragGestureNode r6, androidx.compose.foundation.gestures.DragEvent.DragStarted r7, kotlin.coroutines.jvm.internal.ContinuationImpl r8) {
        /*
            r6.getClass()
            boolean r0 = r8 instanceof androidx.compose.foundation.gestures.DragGestureNode$processDragStart$1
            if (r0 == 0) goto L16
            r0 = r8
            androidx.compose.foundation.gestures.DragGestureNode$processDragStart$1 r0 = (androidx.compose.foundation.gestures.DragGestureNode$processDragStart$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L16
            int r1 = r1 - r2
            r0.label = r1
            goto L1b
        L16:
            androidx.compose.foundation.gestures.DragGestureNode$processDragStart$1 r0 = new androidx.compose.foundation.gestures.DragGestureNode$processDragStart$1
            r0.<init>(r6, r8)
        L1b:
            java.lang.Object r8 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L4e
            if (r2 == r4) goto L41
            if (r2 != r3) goto L39
            java.lang.Object r6 = r0.L$2
            androidx.compose.foundation.interaction.DragInteraction$Start r6 = (androidx.compose.foundation.interaction.DragInteraction$Start) r6
            java.lang.Object r7 = r0.L$1
            androidx.compose.foundation.gestures.DragEvent$DragStarted r7 = (androidx.compose.foundation.gestures.DragEvent.DragStarted) r7
            java.lang.Object r0 = r0.L$0
            androidx.compose.foundation.gestures.DragGestureNode r0 = (androidx.compose.foundation.gestures.DragGestureNode) r0
            kotlin.ResultKt.throwOnFailure(r8)
            goto L85
        L39:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L41:
            java.lang.Object r6 = r0.L$1
            r7 = r6
            androidx.compose.foundation.gestures.DragEvent$DragStarted r7 = (androidx.compose.foundation.gestures.DragEvent.DragStarted) r7
            java.lang.Object r6 = r0.L$0
            androidx.compose.foundation.gestures.DragGestureNode r6 = (androidx.compose.foundation.gestures.DragGestureNode) r6
            kotlin.ResultKt.throwOnFailure(r8)
            goto L6b
        L4e:
            kotlin.ResultKt.throwOnFailure(r8)
            androidx.compose.foundation.interaction.DragInteraction$Start r8 = r6.dragInteraction
            if (r8 == 0) goto L6b
            androidx.compose.foundation.interaction.MutableInteractionSource r2 = r6.interactionSource
            if (r2 == 0) goto L6b
            androidx.compose.foundation.interaction.DragInteraction$Cancel r5 = new androidx.compose.foundation.interaction.DragInteraction$Cancel
            r5.<init>(r8)
            r0.L$0 = r6
            r0.L$1 = r7
            r0.label = r4
            java.lang.Object r8 = r2.emit(r5, r0)
            if (r8 != r1) goto L6b
            goto L90
        L6b:
            androidx.compose.foundation.interaction.DragInteraction$Start r8 = new androidx.compose.foundation.interaction.DragInteraction$Start
            r8.<init>()
            androidx.compose.foundation.interaction.MutableInteractionSource r2 = r6.interactionSource
            if (r2 == 0) goto L87
            r0.L$0 = r6
            r0.L$1 = r7
            r0.L$2 = r8
            r0.label = r3
            java.lang.Object r0 = r2.emit(r8, r0)
            if (r0 != r1) goto L83
            goto L90
        L83:
            r0 = r6
            r6 = r8
        L85:
            r8 = r6
            r6 = r0
        L87:
            r6.dragInteraction = r8
            long r7 = r7.startPoint
            r6.mo66onDragStartedk4lQ0M(r7)
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
        L90:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureNode.access$processDragStart(androidx.compose.foundation.gestures.DragGestureNode, androidx.compose.foundation.gestures.DragEvent$DragStarted, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$processDragStop(androidx.compose.foundation.gestures.DragGestureNode r5, androidx.compose.foundation.gestures.DragEvent.DragStopped r6, kotlin.coroutines.jvm.internal.ContinuationImpl r7) {
        /*
            r5.getClass()
            boolean r0 = r7 instanceof androidx.compose.foundation.gestures.DragGestureNode$processDragStop$1
            if (r0 == 0) goto L16
            r0 = r7
            androidx.compose.foundation.gestures.DragGestureNode$processDragStop$1 r0 = (androidx.compose.foundation.gestures.DragGestureNode$processDragStop$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L16
            int r1 = r1 - r2
            r0.label = r1
            goto L1b
        L16:
            androidx.compose.foundation.gestures.DragGestureNode$processDragStop$1 r0 = new androidx.compose.foundation.gestures.DragGestureNode$processDragStop$1
            r0.<init>(r5, r7)
        L1b:
            java.lang.Object r7 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3b
            if (r2 != r3) goto L33
            java.lang.Object r5 = r0.L$1
            r6 = r5
            androidx.compose.foundation.gestures.DragEvent$DragStopped r6 = (androidx.compose.foundation.gestures.DragEvent.DragStopped) r6
            java.lang.Object r5 = r0.L$0
            androidx.compose.foundation.gestures.DragGestureNode r5 = (androidx.compose.foundation.gestures.DragGestureNode) r5
            kotlin.ResultKt.throwOnFailure(r7)
            goto L58
        L33:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L3b:
            kotlin.ResultKt.throwOnFailure(r7)
            androidx.compose.foundation.interaction.DragInteraction$Start r7 = r5.dragInteraction
            if (r7 == 0) goto L5b
            androidx.compose.foundation.interaction.MutableInteractionSource r2 = r5.interactionSource
            if (r2 == 0) goto L58
            androidx.compose.foundation.interaction.DragInteraction$Stop r4 = new androidx.compose.foundation.interaction.DragInteraction$Stop
            r4.<init>(r7)
            r0.L$0 = r5
            r0.L$1 = r6
            r0.label = r3
            java.lang.Object r7 = r2.emit(r4, r0)
            if (r7 != r1) goto L58
            goto L62
        L58:
            r7 = 0
            r5.dragInteraction = r7
        L5b:
            long r6 = r6.velocity
            r5.mo67onDragStoppedTH1AsA0(r6)
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
        L62:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureNode.access$processDragStop(androidx.compose.foundation.gestures.DragGestureNode, androidx.compose.foundation.gestures.DragEvent$DragStopped, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public final void disposeInteractionSource$1() {
        DragInteraction$Start dragInteraction$Start = this.dragInteraction;
        if (dragInteraction$Start != null) {
            MutableInteractionSource mutableInteractionSource = this.interactionSource;
            if (mutableInteractionSource != null) {
                mutableInteractionSource.tryEmit(new DragInteraction$Cancel(dragInteraction$Start));
            }
            this.dragInteraction = null;
        }
    }

    public abstract Object drag(Continuation continuation, Function2 function2);

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public final void onCancelPointerInput() {
        SuspendingPointerInputModifierNode suspendingPointerInputModifierNode = this.pointerInputNode;
        if (suspendingPointerInputModifierNode != null) {
            ((SuspendingPointerInputModifierNodeImpl) suspendingPointerInputModifierNode).onCancelPointerInput();
        }
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final void onDetach() {
        this.isListeningForEvents = false;
        disposeInteractionSource$1();
    }

    /* renamed from: onDragStarted-k-4lQ0M, reason: not valid java name */
    public abstract void mo66onDragStartedk4lQ0M(long j);

    /* renamed from: onDragStopped-TH1AsA0, reason: not valid java name */
    public abstract void mo67onDragStoppedTH1AsA0(long j);

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    /* renamed from: onPointerEvent-H0pRuoY */
    public void mo15onPointerEventH0pRuoY(PointerEvent pointerEvent, PointerEventPass pointerEventPass, long j) {
        if (this.enabled && this.pointerInputNode == null) {
            SuspendingPointerInputModifierNodeImpl SuspendingPointerInputModifierNode = SuspendingPointerInputFilterKt.SuspendingPointerInputModifierNode(new PointerInputEventHandler() { // from class: androidx.compose.foundation.gestures.DragGestureNode$initializePointerInputNode$1

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: androidx.compose.foundation.gestures.DragGestureNode$initializePointerInputNode$1$1, reason: invalid class name */
                final class AnonymousClass1 extends SuspendLambda implements Function2 {
                    final /* synthetic */ Function2 $onDrag;
                    final /* synthetic */ Function0 $onDragCancel;
                    final /* synthetic */ Function1 $onDragEnd;
                    final /* synthetic */ Function3 $onDragStart;
                    final /* synthetic */ Function0 $shouldAwaitTouchSlop;
                    final /* synthetic */ PointerInputScope $this_SuspendingPointerInputModifierNode;
                    private /* synthetic */ Object L$0;
                    int label;
                    final /* synthetic */ DragGestureNode this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public AnonymousClass1(DragGestureNode dragGestureNode, PointerInputScope pointerInputScope, Function3 function3, Function1 function1, Function0 function0, Function0 function02, Function2 function2, Continuation continuation) {
                        super(2, continuation);
                        this.this$0 = dragGestureNode;
                        this.$this_SuspendingPointerInputModifierNode = pointerInputScope;
                        this.$onDragStart = function3;
                        this.$onDragEnd = function1;
                        this.$onDragCancel = function0;
                        this.$shouldAwaitTouchSlop = function02;
                        this.$onDrag = function2;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation create(Object obj, Continuation continuation) {
                        AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, this.$this_SuspendingPointerInputModifierNode, this.$onDragStart, this.$onDragEnd, this.$onDragCancel, this.$shouldAwaitTouchSlop, this.$onDrag, continuation);
                        anonymousClass1.L$0 = obj;
                        return anonymousClass1;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                    }

                    /* JADX WARN: Removed duplicated region for block: B:13:0x005d  */
                    /* JADX WARN: Removed duplicated region for block: B:16:0x0069  */
                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct add '--show-bad-code' argument
                    */
                    public final java.lang.Object invokeSuspend(java.lang.Object r15) {
                        /*
                            r14 = this;
                            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                            int r1 = r14.label
                            kotlin.Unit r2 = kotlin.Unit.INSTANCE
                            r3 = 1
                            if (r1 == 0) goto L1d
                            if (r1 != r3) goto L15
                            java.lang.Object r0 = r14.L$0
                            kotlinx.coroutines.CoroutineScope r0 = (kotlinx.coroutines.CoroutineScope) r0
                            kotlin.ResultKt.throwOnFailure(r15)     // Catch: java.util.concurrent.CancellationException -> L13
                            goto L68
                        L13:
                            r15 = move-exception
                            goto L57
                        L15:
                            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
                            java.lang.String r15 = "call to 'resume' before 'invoke' with coroutine"
                            r14.<init>(r15)
                            throw r14
                        L1d:
                            kotlin.ResultKt.throwOnFailure(r15)
                            java.lang.Object r15 = r14.L$0
                            kotlinx.coroutines.CoroutineScope r15 = (kotlinx.coroutines.CoroutineScope) r15
                            androidx.compose.foundation.gestures.DragGestureNode r1 = r14.this$0     // Catch: java.util.concurrent.CancellationException -> L55
                            androidx.compose.foundation.gestures.Orientation r7 = r1.orientationLock     // Catch: java.util.concurrent.CancellationException -> L55
                            androidx.compose.ui.input.pointer.PointerInputScope r1 = r14.$this_SuspendingPointerInputModifierNode     // Catch: java.util.concurrent.CancellationException -> L55
                            kotlin.jvm.functions.Function3 r8 = r14.$onDragStart     // Catch: java.util.concurrent.CancellationException -> L55
                            kotlin.jvm.functions.Function1 r11 = r14.$onDragEnd     // Catch: java.util.concurrent.CancellationException -> L55
                            kotlin.jvm.functions.Function0 r10 = r14.$onDragCancel     // Catch: java.util.concurrent.CancellationException -> L55
                            kotlin.jvm.functions.Function0 r5 = r14.$shouldAwaitTouchSlop     // Catch: java.util.concurrent.CancellationException -> L55
                            kotlin.jvm.functions.Function2 r9 = r14.$onDrag     // Catch: java.util.concurrent.CancellationException -> L55
                            r14.L$0 = r15     // Catch: java.util.concurrent.CancellationException -> L55
                            r14.label = r3     // Catch: java.util.concurrent.CancellationException -> L55
                            float r3 = androidx.compose.foundation.gestures.DragGestureDetectorKt.mouseToTouchSlopRatio     // Catch: java.util.concurrent.CancellationException -> L55
                            kotlin.jvm.internal.Ref$LongRef r6 = new kotlin.jvm.internal.Ref$LongRef     // Catch: java.util.concurrent.CancellationException -> L55
                            r6.<init>()     // Catch: java.util.concurrent.CancellationException -> L55
                            androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGestures$9 r3 = new androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGestures$9     // Catch: java.util.concurrent.CancellationException -> L55
                            r12 = 0
                            r4 = r3
                            r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12)     // Catch: java.util.concurrent.CancellationException -> L55
                            java.lang.Object r14 = androidx.compose.foundation.gestures.ForEachGestureKt.awaitEachGesture(r1, r3, r14)     // Catch: java.util.concurrent.CancellationException -> L55
                            if (r14 != r0) goto L4d
                            goto L4e
                        L4d:
                            r14 = r2
                        L4e:
                            if (r14 != r0) goto L68
                            return r0
                        L51:
                            r13 = r0
                            r0 = r15
                            r15 = r13
                            goto L57
                        L55:
                            r0 = move-exception
                            goto L51
                        L57:
                            androidx.compose.foundation.gestures.DragGestureNode r14 = r14.this$0
                            kotlinx.coroutines.channels.BufferedChannel r14 = r14.channel
                            if (r14 == 0) goto L62
                            androidx.compose.foundation.gestures.DragEvent$DragCancelled r1 = androidx.compose.foundation.gestures.DragEvent.DragCancelled.INSTANCE
                            r14.mo1790trySendJP2dKIU(r1)
                        L62:
                            boolean r14 = kotlinx.coroutines.CoroutineScopeKt.isActive(r0)
                            if (r14 == 0) goto L69
                        L68:
                            return r2
                        L69:
                            throw r15
                        */
                        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureNode$initializePointerInputNode$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
                    }
                }

                @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
                public final Object invoke(PointerInputScope pointerInputScope, Continuation continuation) {
                    final VelocityTracker velocityTracker = new VelocityTracker();
                    final DragGestureNode dragGestureNode = DragGestureNode.this;
                    Object coroutineScope = CoroutineScopeKt.coroutineScope(continuation, new AnonymousClass1(dragGestureNode, pointerInputScope, new Function3() { // from class: androidx.compose.foundation.gestures.DragGestureNode$initializePointerInputNode$1$onDragStart$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(3);
                        }

                        /* JADX WARN: Type inference failed for: r8v3, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj, Object obj2, Object obj3) {
                            PointerInputChange pointerInputChange = (PointerInputChange) obj;
                            PointerInputChange pointerInputChange2 = (PointerInputChange) obj2;
                            long j2 = ((Offset) obj3).packedValue;
                            if (((Boolean) DragGestureNode.this.canDrag.invoke(pointerInputChange)).booleanValue()) {
                                DragGestureNode dragGestureNode2 = DragGestureNode.this;
                                if (!dragGestureNode2.isListeningForEvents) {
                                    if (dragGestureNode2.channel == null) {
                                        dragGestureNode2.channel = ChannelKt.Channel$default(Integer.MAX_VALUE, null, null, 6);
                                    }
                                    DragGestureNode dragGestureNode3 = DragGestureNode.this;
                                    dragGestureNode3.isListeningForEvents = true;
                                    BuildersKt.launch$default(dragGestureNode3.getCoroutineScope(), null, null, new DragGestureNode$startListeningForEvents$1(dragGestureNode3, null), 3);
                                }
                                VelocityTrackerKt.addPointerInputChange(velocityTracker, pointerInputChange);
                                long m314minusMKHz9U = Offset.m314minusMKHz9U(pointerInputChange2.position, j2);
                                BufferedChannel bufferedChannel = DragGestureNode.this.channel;
                                if (bufferedChannel != null) {
                                    bufferedChannel.mo1790trySendJP2dKIU(new DragEvent.DragStarted(m314minusMKHz9U));
                                }
                            }
                            return Unit.INSTANCE;
                        }
                    }, new Function1() { // from class: androidx.compose.foundation.gestures.DragGestureNode$initializePointerInputNode$1$onDragEnd$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            VelocityTrackerKt.addPointerInputChange(velocityTracker, (PointerInputChange) obj);
                            float maximumFlingVelocity = ((ViewConfiguration) CompositionLocalConsumerModifierNodeKt.currentValueOf(dragGestureNode, CompositionLocalsKt.LocalViewConfiguration)).getMaximumFlingVelocity();
                            long m470calculateVelocityAH228Gc = velocityTracker.m470calculateVelocityAH228Gc(VelocityKt.Velocity(maximumFlingVelocity, maximumFlingVelocity));
                            velocityTracker.resetTracking();
                            BufferedChannel bufferedChannel = dragGestureNode.channel;
                            if (bufferedChannel != null) {
                                Function3 function3 = DraggableKt.NoOpOnDragStarted;
                                bufferedChannel.mo1790trySendJP2dKIU(new DragEvent.DragStopped(VelocityKt.Velocity(Float.isNaN(Velocity.m694getXimpl(m470calculateVelocityAH228Gc)) ? 0.0f : Velocity.m694getXimpl(m470calculateVelocityAH228Gc), Float.isNaN(Velocity.m695getYimpl(m470calculateVelocityAH228Gc)) ? 0.0f : Velocity.m695getYimpl(m470calculateVelocityAH228Gc))));
                            }
                            return Unit.INSTANCE;
                        }
                    }, new Function0() { // from class: androidx.compose.foundation.gestures.DragGestureNode$initializePointerInputNode$1$onDragCancel$1
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            BufferedChannel bufferedChannel = DragGestureNode.this.channel;
                            if (bufferedChannel != null) {
                                bufferedChannel.mo1790trySendJP2dKIU(DragEvent.DragCancelled.INSTANCE);
                            }
                            return Unit.INSTANCE;
                        }
                    }, new Function0() { // from class: androidx.compose.foundation.gestures.DragGestureNode$initializePointerInputNode$1$shouldAwaitTouchSlop$1
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Boolean.valueOf(!DragGestureNode.this.startDragImmediately());
                        }
                    }, new Function2() { // from class: androidx.compose.foundation.gestures.DragGestureNode$initializePointerInputNode$1$onDrag$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            long j2 = ((Offset) obj2).packedValue;
                            VelocityTrackerKt.addPointerInputChange(velocityTracker, (PointerInputChange) obj);
                            BufferedChannel bufferedChannel = dragGestureNode.channel;
                            if (bufferedChannel != null) {
                                bufferedChannel.mo1790trySendJP2dKIU(new DragEvent.DragDelta(j2));
                            }
                            return Unit.INSTANCE;
                        }
                    }, null));
                    return coroutineScope == CoroutineSingletons.COROUTINE_SUSPENDED ? coroutineScope : Unit.INSTANCE;
                }
            });
            delegate(SuspendingPointerInputModifierNode);
            this.pointerInputNode = SuspendingPointerInputModifierNode;
        }
        SuspendingPointerInputModifierNode suspendingPointerInputModifierNode = this.pointerInputNode;
        if (suspendingPointerInputModifierNode != null) {
            ((SuspendingPointerInputModifierNodeImpl) suspendingPointerInputModifierNode).mo15onPointerEventH0pRuoY(pointerEvent, pointerEventPass, j);
        }
    }

    public abstract boolean startDragImmediately();

    /* JADX WARN: Multi-variable type inference failed */
    public final void update(Function1 function1, boolean z, MutableInteractionSource mutableInteractionSource, Orientation orientation, boolean z2) {
        SuspendingPointerInputModifierNode suspendingPointerInputModifierNode;
        this.canDrag = (Lambda) function1;
        boolean z3 = true;
        if (this.enabled != z) {
            this.enabled = z;
            if (!z) {
                disposeInteractionSource$1();
                SuspendingPointerInputModifierNode suspendingPointerInputModifierNode2 = this.pointerInputNode;
                if (suspendingPointerInputModifierNode2 != null) {
                    undelegate(suspendingPointerInputModifierNode2);
                }
                this.pointerInputNode = null;
            }
            z2 = true;
        }
        if (!Intrinsics.areEqual(this.interactionSource, mutableInteractionSource)) {
            disposeInteractionSource$1();
            this.interactionSource = mutableInteractionSource;
        }
        if (this.orientationLock != orientation) {
            this.orientationLock = orientation;
        } else {
            z3 = z2;
        }
        if (!z3 || (suspendingPointerInputModifierNode = this.pointerInputNode) == null) {
            return;
        }
        ((SuspendingPointerInputModifierNodeImpl) suspendingPointerInputModifierNode).resetPointerInputHandler();
    }
}
