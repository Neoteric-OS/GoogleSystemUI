package androidx.compose.foundation.text.input.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class CursorAnimationState$snapToVisibleAndAnimate$2 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ CursorAnimationState this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: androidx.compose.foundation.text.input.internal.CursorAnimationState$snapToVisibleAndAnimate$2$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ Job $oldJob;
        int label;
        final /* synthetic */ CursorAnimationState this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Job job, CursorAnimationState cursorAnimationState, Continuation continuation) {
            super(2, continuation);
            this.$oldJob = job;
            this.this$0 = cursorAnimationState;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.$oldJob, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            return CoroutineSingletons.COROUTINE_SUSPENDED;
        }

        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        /* JADX WARN: Removed duplicated region for block: B:12:0x0063 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:16:0x0075 A[RETURN] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x0073 -> B:9:0x0076). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r11) {
            /*
                r10 = this;
                kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r1 = r10.label
                r2 = 0
                r3 = 500(0x1f4, double:2.47E-321)
                r5 = 1065353216(0x3f800000, float:1.0)
                r6 = 4
                r7 = 3
                r8 = 2
                r9 = 1
                if (r1 == 0) goto L36
                if (r1 == r9) goto L32
                if (r1 == r8) goto L29
                if (r1 == r7) goto L25
                if (r1 != r6) goto L1d
                kotlin.ResultKt.throwOnFailure(r11)     // Catch: java.lang.Throwable -> L1b
                goto L76
            L1b:
                r11 = move-exception
                goto L80
            L1d:
                java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
                r10.<init>(r11)
                throw r10
            L25:
                kotlin.ResultKt.throwOnFailure(r11)     // Catch: java.lang.Throwable -> L1b
                goto L64
            L29:
                kotlin.ResultKt.throwOnFailure(r11)     // Catch: java.lang.Throwable -> L1b
                kotlin.KotlinNothingValueException r11 = new kotlin.KotlinNothingValueException     // Catch: java.lang.Throwable -> L1b
                r11.<init>()     // Catch: java.lang.Throwable -> L1b
                throw r11     // Catch: java.lang.Throwable -> L1b
            L32:
                kotlin.ResultKt.throwOnFailure(r11)
                goto L46
            L36:
                kotlin.ResultKt.throwOnFailure(r11)
                kotlinx.coroutines.Job r11 = r10.$oldJob
                if (r11 == 0) goto L46
                r10.label = r9
                java.lang.Object r11 = kotlinx.coroutines.JobKt.cancelAndJoin(r11, r10)
                if (r11 != r0) goto L46
                return r0
            L46:
                androidx.compose.foundation.text.input.internal.CursorAnimationState r11 = r10.this$0     // Catch: java.lang.Throwable -> L1b
                androidx.compose.runtime.MutableFloatState r11 = r11.cursorAlpha$delegate     // Catch: java.lang.Throwable -> L1b
                androidx.compose.runtime.SnapshotMutableFloatStateImpl r11 = (androidx.compose.runtime.SnapshotMutableFloatStateImpl) r11     // Catch: java.lang.Throwable -> L1b
                r11.setFloatValue(r5)     // Catch: java.lang.Throwable -> L1b
                androidx.compose.foundation.text.input.internal.CursorAnimationState r11 = r10.this$0     // Catch: java.lang.Throwable -> L1b
                boolean r11 = r11.animate     // Catch: java.lang.Throwable -> L1b
                if (r11 != 0) goto L5b
                r10.label = r8     // Catch: java.lang.Throwable -> L1b
                kotlinx.coroutines.DelayKt.awaitCancellation(r10)     // Catch: java.lang.Throwable -> L1b
                return r0
            L5b:
                r10.label = r7     // Catch: java.lang.Throwable -> L1b
                java.lang.Object r11 = kotlinx.coroutines.DelayKt.delay(r3, r10)     // Catch: java.lang.Throwable -> L1b
                if (r11 != r0) goto L64
                return r0
            L64:
                androidx.compose.foundation.text.input.internal.CursorAnimationState r11 = r10.this$0     // Catch: java.lang.Throwable -> L1b
                androidx.compose.runtime.MutableFloatState r11 = r11.cursorAlpha$delegate     // Catch: java.lang.Throwable -> L1b
                androidx.compose.runtime.SnapshotMutableFloatStateImpl r11 = (androidx.compose.runtime.SnapshotMutableFloatStateImpl) r11     // Catch: java.lang.Throwable -> L1b
                r11.setFloatValue(r2)     // Catch: java.lang.Throwable -> L1b
                r10.label = r6     // Catch: java.lang.Throwable -> L1b
                java.lang.Object r11 = kotlinx.coroutines.DelayKt.delay(r3, r10)     // Catch: java.lang.Throwable -> L1b
                if (r11 != r0) goto L76
                return r0
            L76:
                androidx.compose.foundation.text.input.internal.CursorAnimationState r11 = r10.this$0     // Catch: java.lang.Throwable -> L1b
                androidx.compose.runtime.MutableFloatState r11 = r11.cursorAlpha$delegate     // Catch: java.lang.Throwable -> L1b
                androidx.compose.runtime.SnapshotMutableFloatStateImpl r11 = (androidx.compose.runtime.SnapshotMutableFloatStateImpl) r11     // Catch: java.lang.Throwable -> L1b
                r11.setFloatValue(r5)     // Catch: java.lang.Throwable -> L1b
                goto L5b
            L80:
                androidx.compose.foundation.text.input.internal.CursorAnimationState r10 = r10.this$0
                androidx.compose.runtime.MutableFloatState r10 = r10.cursorAlpha$delegate
                androidx.compose.runtime.SnapshotMutableFloatStateImpl r10 = (androidx.compose.runtime.SnapshotMutableFloatStateImpl) r10
                r10.setFloatValue(r2)
                throw r11
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.input.internal.CursorAnimationState$snapToVisibleAndAnimate$2.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CursorAnimationState$snapToVisibleAndAnimate$2(CursorAnimationState cursorAnimationState, Continuation continuation) {
        super(2, continuation);
        this.this$0 = cursorAnimationState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        CursorAnimationState$snapToVisibleAndAnimate$2 cursorAnimationState$snapToVisibleAndAnimate$2 = new CursorAnimationState$snapToVisibleAndAnimate$2(this.this$0, continuation);
        cursorAnimationState$snapToVisibleAndAnimate$2.L$0 = obj;
        return cursorAnimationState$snapToVisibleAndAnimate$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((CursorAnimationState$snapToVisibleAndAnimate$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        Job job = (Job) this.this$0.animationJob.getAndSet(null);
        CursorAnimationState cursorAnimationState = this.this$0;
        return Boolean.valueOf(cursorAnimationState.animationJob.compareAndSet(null, BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass1(job, cursorAnimationState, null), 3)));
    }
}
