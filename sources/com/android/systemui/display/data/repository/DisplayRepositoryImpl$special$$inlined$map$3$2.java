package com.android.systemui.display.data.repository;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DisplayRepositoryImpl$special$$inlined$map$3$2 implements FlowCollector {
    public final /* synthetic */ FlowCollector $this_unsafeFlow;
    public final /* synthetic */ DisplayRepositoryImpl this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.display.data.repository.DisplayRepositoryImpl$special$$inlined$map$3$2$1, reason: invalid class name */
    public final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DisplayRepositoryImpl$special$$inlined$map$3$2.this.emit(null, this);
        }
    }

    public DisplayRepositoryImpl$special$$inlined$map$3$2(FlowCollector flowCollector, DisplayRepositoryImpl displayRepositoryImpl) {
        this.$this_unsafeFlow = flowCollector;
        this.this$0 = displayRepositoryImpl;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object emit(java.lang.Object r10, kotlin.coroutines.Continuation r11) {
        /*
            r9 = this;
            boolean r0 = r11 instanceof com.android.systemui.display.data.repository.DisplayRepositoryImpl$special$$inlined$map$3$2.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r11
            com.android.systemui.display.data.repository.DisplayRepositoryImpl$special$$inlined$map$3$2$1 r0 = (com.android.systemui.display.data.repository.DisplayRepositoryImpl$special$$inlined$map$3$2.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.display.data.repository.DisplayRepositoryImpl$special$$inlined$map$3$2$1 r0 = new com.android.systemui.display.data.repository.DisplayRepositoryImpl$special$$inlined$map$3$2$1
            r0.<init>(r11)
        L18:
            java.lang.Object r11 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L30
            if (r2 != r3) goto L28
            kotlin.ResultKt.throwOnFailure(r11)
            goto Lb1
        L28:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L30:
            kotlin.ResultKt.throwOnFailure(r11)
            java.util.Set r10 = (java.util.Set) r10
            boolean r11 = android.os.Trace.isEnabled()
            if (r11 == 0) goto L40
            java.lang.String r2 = "DisplayRepository#filteringExternalDisplays"
            com.android.app.tracing.TraceUtilsKt.beginSlice(r2)
        L40:
            java.lang.Iterable r10 = (java.lang.Iterable) r10     // Catch: java.lang.Throwable -> L95
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L95
            r2.<init>()     // Catch: java.lang.Throwable -> L95
            java.util.Iterator r10 = r10.iterator()     // Catch: java.lang.Throwable -> L95
        L4b:
            boolean r4 = r10.hasNext()     // Catch: java.lang.Throwable -> L95
            if (r4 == 0) goto L9d
            java.lang.Object r4 = r10.next()     // Catch: java.lang.Throwable -> L95
            r5 = r4
            java.lang.Number r5 = (java.lang.Number) r5     // Catch: java.lang.Throwable -> L95
            int r5 = r5.intValue()     // Catch: java.lang.Throwable -> L95
            com.android.systemui.display.data.repository.DisplayRepositoryImpl r6 = r9.this$0     // Catch: java.lang.Throwable -> L95
            boolean r7 = com.android.systemui.display.data.repository.DisplayRepositoryImpl.DEBUG     // Catch: java.lang.Throwable -> L95
            r6.getClass()     // Catch: java.lang.Throwable -> L95
            boolean r7 = android.os.Trace.isEnabled()     // Catch: java.lang.Throwable -> L95
            if (r7 == 0) goto L6e
            java.lang.String r8 = "DisplayRepository#getDisplayType"
            com.android.app.tracing.TraceUtilsKt.beginSlice(r8)     // Catch: java.lang.Throwable -> L95
        L6e:
            android.hardware.display.DisplayManager r6 = r6.displayManager     // Catch: java.lang.Throwable -> L7f
            android.view.Display r5 = r6.getDisplay(r5)     // Catch: java.lang.Throwable -> L7f
            if (r5 == 0) goto L81
            int r5 = r5.getType()     // Catch: java.lang.Throwable -> L7f
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch: java.lang.Throwable -> L7f
            goto L82
        L7f:
            r9 = move-exception
            goto L97
        L81:
            r5 = 0
        L82:
            if (r7 == 0) goto L87
            com.android.app.tracing.TraceUtilsKt.endSlice()     // Catch: java.lang.Throwable -> L95
        L87:
            if (r5 != 0) goto L8a
            goto L4b
        L8a:
            int r5 = r5.intValue()     // Catch: java.lang.Throwable -> L95
            r6 = 2
            if (r5 != r6) goto L4b
            r2.add(r4)     // Catch: java.lang.Throwable -> L95
            goto L4b
        L95:
            r9 = move-exception
            goto Lb4
        L97:
            if (r7 == 0) goto L9c
            com.android.app.tracing.TraceUtilsKt.endSlice()     // Catch: java.lang.Throwable -> L95
        L9c:
            throw r9     // Catch: java.lang.Throwable -> L95
        L9d:
            java.util.Set r10 = kotlin.collections.CollectionsKt.toSet(r2)     // Catch: java.lang.Throwable -> L95
            if (r11 == 0) goto La6
            com.android.app.tracing.TraceUtilsKt.endSlice()
        La6:
            r0.label = r3
            kotlinx.coroutines.flow.FlowCollector r9 = r9.$this_unsafeFlow
            java.lang.Object r9 = r9.emit(r10, r0)
            if (r9 != r1) goto Lb1
            return r1
        Lb1:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        Lb4:
            if (r11 == 0) goto Lb9
            com.android.app.tracing.TraceUtilsKt.endSlice()
        Lb9:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.display.data.repository.DisplayRepositoryImpl$special$$inlined$map$3$2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
