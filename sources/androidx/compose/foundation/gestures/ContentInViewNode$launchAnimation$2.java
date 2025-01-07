package androidx.compose.foundation.gestures;

import androidx.compose.foundation.MutatePriority;
import java.util.concurrent.CancellationException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.ExceptionsKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class ContentInViewNode$launchAnimation$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ UpdatableAnimationState $animationState;
    final /* synthetic */ BringIntoViewSpec $bringIntoViewSpec;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ ContentInViewNode this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: androidx.compose.foundation.gestures.ContentInViewNode$launchAnimation$2$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ Job $animationJob;
        final /* synthetic */ UpdatableAnimationState $animationState;
        final /* synthetic */ BringIntoViewSpec $bringIntoViewSpec;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ ContentInViewNode this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(UpdatableAnimationState updatableAnimationState, ContentInViewNode contentInViewNode, BringIntoViewSpec bringIntoViewSpec, Job job, Continuation continuation) {
            super(2, continuation);
            this.$animationState = updatableAnimationState;
            this.this$0 = contentInViewNode;
            this.$bringIntoViewSpec = bringIntoViewSpec;
            this.$animationJob = job;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$animationState, this.this$0, this.$bringIntoViewSpec, this.$animationJob, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass1) create((NestedScrollScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final NestedScrollScope nestedScrollScope = (NestedScrollScope) this.L$0;
                this.$animationState.value = ContentInViewNode.access$calculateScrollDelta(this.this$0, this.$bringIntoViewSpec);
                final UpdatableAnimationState updatableAnimationState = this.$animationState;
                final ContentInViewNode contentInViewNode = this.this$0;
                final Job job = this.$animationJob;
                Function1 function1 = new Function1() { // from class: androidx.compose.foundation.gestures.ContentInViewNode.launchAnimation.2.1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        float floatValue = ((Number) obj2).floatValue();
                        ContentInViewNode contentInViewNode2 = ContentInViewNode.this;
                        float f = contentInViewNode2.reverseDirection ? 1.0f : -1.0f;
                        NestedScrollScope nestedScrollScope2 = nestedScrollScope;
                        ScrollingLogic scrollingLogic = contentInViewNode2.scrollingLogic;
                        long m73reverseIfNeededMKHz9U = scrollingLogic.m73reverseIfNeededMKHz9U(scrollingLogic.m75toOffsettuRUvjQ(f * floatValue));
                        ScrollingLogic scrollingLogic2 = ((ScrollingLogic$nestedScrollScope$1) nestedScrollScope2).this$0;
                        float m74toFloatk4lQ0M = scrollingLogic.m74toFloatk4lQ0M(scrollingLogic.m73reverseIfNeededMKHz9U(ScrollingLogic.m71access$performScroll3eAAhYA(scrollingLogic2, scrollingLogic2.outerStateScope, m73reverseIfNeededMKHz9U, 1))) * f;
                        if (Math.abs(m74toFloatk4lQ0M) < Math.abs(floatValue)) {
                            job.cancel(ExceptionsKt.CancellationException("Scroll animation cancelled because scroll was not consumed (" + m74toFloatk4lQ0M + " < " + floatValue + ')', null));
                        }
                        return Unit.INSTANCE;
                    }
                };
                final BringIntoViewSpec bringIntoViewSpec = this.$bringIntoViewSpec;
                Function0 function0 = new Function0() { // from class: androidx.compose.foundation.gestures.ContentInViewNode.launchAnimation.2.1.2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX WARN: Code restructure failed: missing block: B:11:0x0044, code lost:
                    
                        r0 = r1;
                     */
                    /* JADX WARN: Code restructure failed: missing block: B:12:0x0048, code lost:
                    
                        if (r0.trackingFocusedChild == false) goto L24;
                     */
                    /* JADX WARN: Code restructure failed: missing block: B:13:0x004a, code lost:
                    
                        r0 = r0.getFocusedChildBounds();
                     */
                    /* JADX WARN: Code restructure failed: missing block: B:14:0x004f, code lost:
                    
                        if (r0 == null) goto L21;
                     */
                    /* JADX WARN: Code restructure failed: missing block: B:15:0x0051, code lost:
                    
                        r2 = r1;
                     */
                    /* JADX WARN: Code restructure failed: missing block: B:16:0x0059, code lost:
                    
                        if (r2.m55isMaxVisibleO0kMr_c(r0, r2.viewportSize) != true) goto L21;
                     */
                    /* JADX WARN: Code restructure failed: missing block: B:17:0x005d, code lost:
                    
                        if (r5 == false) goto L24;
                     */
                    /* JADX WARN: Code restructure failed: missing block: B:18:0x005f, code lost:
                    
                        r1.trackingFocusedChild = false;
                     */
                    /* JADX WARN: Code restructure failed: missing block: B:19:0x005c, code lost:
                    
                        r5 = false;
                     */
                    /* JADX WARN: Code restructure failed: missing block: B:20:0x0063, code lost:
                    
                        r2.value = androidx.compose.foundation.gestures.ContentInViewNode.access$calculateScrollDelta(r1, r3);
                     */
                    /* JADX WARN: Code restructure failed: missing block: B:21:0x006f, code lost:
                    
                        return r4;
                     */
                    @Override // kotlin.jvm.functions.Function0
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct add '--show-bad-code' argument
                    */
                    public final java.lang.Object invoke() {
                        /*
                            r8 = this;
                            androidx.compose.foundation.gestures.ContentInViewNode r0 = androidx.compose.foundation.gestures.ContentInViewNode.this
                            androidx.compose.foundation.gestures.BringIntoViewRequestPriorityQueue r1 = r0.bringIntoViewRequests
                        L4:
                            androidx.compose.runtime.collection.MutableVector r2 = r1.requests
                            int r3 = r2.size
                            kotlin.Unit r4 = kotlin.Unit.INSTANCE
                            r5 = 1
                            if (r3 == 0) goto L44
                            if (r3 == 0) goto L3c
                            int r3 = r3 + (-1)
                            java.lang.Object[] r2 = r2.content
                            r2 = r2[r3]
                            androidx.compose.foundation.gestures.ContentInViewNode$Request r2 = (androidx.compose.foundation.gestures.ContentInViewNode.Request) r2
                            kotlin.jvm.functions.Function0 r2 = r2.currentBounds
                            java.lang.Object r2 = r2.invoke()
                            androidx.compose.ui.geometry.Rect r2 = (androidx.compose.ui.geometry.Rect) r2
                            if (r2 != 0) goto L23
                            r2 = r5
                            goto L29
                        L23:
                            long r6 = r0.viewportSize
                            boolean r2 = r0.m55isMaxVisibleO0kMr_c(r2, r6)
                        L29:
                            if (r2 == 0) goto L44
                            androidx.compose.runtime.collection.MutableVector r2 = r1.requests
                            int r3 = r2.size
                            int r3 = r3 - r5
                            java.lang.Object r2 = r2.removeAt(r3)
                            androidx.compose.foundation.gestures.ContentInViewNode$Request r2 = (androidx.compose.foundation.gestures.ContentInViewNode.Request) r2
                            kotlinx.coroutines.CancellableContinuationImpl r2 = r2.continuation
                            r2.resumeWith(r4)
                            goto L4
                        L3c:
                            java.util.NoSuchElementException r8 = new java.util.NoSuchElementException
                            java.lang.String r0 = "MutableVector is empty."
                            r8.<init>(r0)
                            throw r8
                        L44:
                            androidx.compose.foundation.gestures.ContentInViewNode r0 = androidx.compose.foundation.gestures.ContentInViewNode.this
                            boolean r1 = r0.trackingFocusedChild
                            if (r1 == 0) goto L63
                            androidx.compose.ui.geometry.Rect r0 = r0.getFocusedChildBounds()
                            r1 = 0
                            if (r0 == 0) goto L5c
                            androidx.compose.foundation.gestures.ContentInViewNode r2 = androidx.compose.foundation.gestures.ContentInViewNode.this
                            long r6 = r2.viewportSize
                            boolean r0 = r2.m55isMaxVisibleO0kMr_c(r0, r6)
                            if (r0 != r5) goto L5c
                            goto L5d
                        L5c:
                            r5 = r1
                        L5d:
                            if (r5 == 0) goto L63
                            androidx.compose.foundation.gestures.ContentInViewNode r0 = androidx.compose.foundation.gestures.ContentInViewNode.this
                            r0.trackingFocusedChild = r1
                        L63:
                            androidx.compose.foundation.gestures.UpdatableAnimationState r0 = r2
                            androidx.compose.foundation.gestures.ContentInViewNode r1 = androidx.compose.foundation.gestures.ContentInViewNode.this
                            androidx.compose.foundation.gestures.BringIntoViewSpec r8 = r3
                            float r8 = androidx.compose.foundation.gestures.ContentInViewNode.access$calculateScrollDelta(r1, r8)
                            r0.value = r8
                            return r4
                        */
                        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.ContentInViewNode$launchAnimation$2.AnonymousClass1.AnonymousClass2.invoke():java.lang.Object");
                    }
                };
                this.label = 1;
                if (updatableAnimationState.animateToZero(function1, function0, this) == coroutineSingletons) {
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

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ContentInViewNode$launchAnimation$2(ContentInViewNode contentInViewNode, UpdatableAnimationState updatableAnimationState, BringIntoViewSpec bringIntoViewSpec, Continuation continuation) {
        super(2, continuation);
        this.this$0 = contentInViewNode;
        this.$animationState = updatableAnimationState;
        this.$bringIntoViewSpec = bringIntoViewSpec;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        ContentInViewNode$launchAnimation$2 contentInViewNode$launchAnimation$2 = new ContentInViewNode$launchAnimation$2(this.this$0, this.$animationState, this.$bringIntoViewSpec, continuation);
        contentInViewNode$launchAnimation$2.L$0 = obj;
        return contentInViewNode$launchAnimation$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((ContentInViewNode$launchAnimation$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        CancellationException cancellationException = null;
        try {
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    Job job = JobKt.getJob(((CoroutineScope) this.L$0).getCoroutineContext());
                    ContentInViewNode contentInViewNode = this.this$0;
                    contentInViewNode.isAnimationRunning = true;
                    ScrollingLogic scrollingLogic = contentInViewNode.scrollingLogic;
                    MutatePriority mutatePriority = MutatePriority.Default;
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$animationState, contentInViewNode, this.$bringIntoViewSpec, job, null);
                    this.label = 1;
                    if (scrollingLogic.scroll(mutatePriority, anonymousClass1, this) == coroutineSingletons) {
                        return coroutineSingletons;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                this.this$0.bringIntoViewRequests.resumeAndRemoveAll();
                ContentInViewNode contentInViewNode2 = this.this$0;
                contentInViewNode2.isAnimationRunning = false;
                contentInViewNode2.bringIntoViewRequests.cancelAndRemoveAll(null);
                this.this$0.trackingFocusedChild = false;
                return Unit.INSTANCE;
            } catch (CancellationException e) {
                cancellationException = e;
                throw cancellationException;
            }
        } catch (Throwable th) {
            ContentInViewNode contentInViewNode3 = this.this$0;
            contentInViewNode3.isAnimationRunning = false;
            contentInViewNode3.bringIntoViewRequests.cancelAndRemoveAll(cancellationException);
            this.this$0.trackingFocusedChild = false;
            throw th;
        }
    }
}
