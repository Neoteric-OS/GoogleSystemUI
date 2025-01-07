package com.android.systemui.qs.pipeline.data.repository;

import java.util.Map;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.sync.MutexImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QSSettingsRestoredBroadcastRepository$restoreData$lambda$8$$inlined$mapNotNull$1$2 implements FlowCollector {
    public final /* synthetic */ Map $firstIntent$inlined;
    public final /* synthetic */ MutexImpl $mutex$inlined;
    public final /* synthetic */ QSSettingsRestoredBroadcastRepository $this_run$inlined;
    public final /* synthetic */ FlowCollector $this_unsafeFlow;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.qs.pipeline.data.repository.QSSettingsRestoredBroadcastRepository$restoreData$lambda$8$$inlined$mapNotNull$1$2$1, reason: invalid class name */
    public final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return QSSettingsRestoredBroadcastRepository$restoreData$lambda$8$$inlined$mapNotNull$1$2.this.emit(null, this);
        }
    }

    public QSSettingsRestoredBroadcastRepository$restoreData$lambda$8$$inlined$mapNotNull$1$2(FlowCollector flowCollector, MutexImpl mutexImpl, Map map, QSSettingsRestoredBroadcastRepository qSSettingsRestoredBroadcastRepository) {
        this.$this_unsafeFlow = flowCollector;
        this.$mutex$inlined = mutexImpl;
        this.$firstIntent$inlined = map;
        this.$this_run$inlined = qSSettingsRestoredBroadcastRepository;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0089 A[Catch: all -> 0x0095, TryCatch #0 {all -> 0x0095, blocks: (B:19:0x007c, B:21:0x0089, B:27:0x0097), top: B:18:0x007c }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0097 A[Catch: all -> 0x0095, TRY_LEAVE, TryCatch #0 {all -> 0x0095, blocks: (B:19:0x007c, B:21:0x0089, B:27:0x0097), top: B:18:0x007c }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0022  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object emit(java.lang.Object r10, kotlin.coroutines.Continuation r11) {
        /*
            r9 = this;
            boolean r0 = r11 instanceof com.android.systemui.qs.pipeline.data.repository.QSSettingsRestoredBroadcastRepository$restoreData$lambda$8$$inlined$mapNotNull$1$2.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r11
            com.android.systemui.qs.pipeline.data.repository.QSSettingsRestoredBroadcastRepository$restoreData$lambda$8$$inlined$mapNotNull$1$2$1 r0 = (com.android.systemui.qs.pipeline.data.repository.QSSettingsRestoredBroadcastRepository$restoreData$lambda$8$$inlined$mapNotNull$1$2.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.qs.pipeline.data.repository.QSSettingsRestoredBroadcastRepository$restoreData$lambda$8$$inlined$mapNotNull$1$2$1 r0 = new com.android.systemui.qs.pipeline.data.repository.QSSettingsRestoredBroadcastRepository$restoreData$lambda$8$$inlined$mapNotNull$1$2$1
            r0.<init>(r11)
        L18:
            java.lang.Object r11 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L49
            if (r2 == r4) goto L33
            if (r2 != r3) goto L2b
            kotlin.ResultKt.throwOnFailure(r11)
            goto Lc3
        L2b:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L33:
            int r9 = r0.I$0
            java.lang.Object r10 = r0.L$3
            kotlinx.coroutines.sync.Mutex r10 = (kotlinx.coroutines.sync.Mutex) r10
            java.lang.Object r2 = r0.L$2
            android.content.Intent r2 = (android.content.Intent) r2
            java.lang.Object r4 = r0.L$1
            kotlinx.coroutines.flow.FlowCollector r4 = (kotlinx.coroutines.flow.FlowCollector) r4
            java.lang.Object r5 = r0.L$0
            com.android.systemui.qs.pipeline.data.repository.QSSettingsRestoredBroadcastRepository$restoreData$lambda$8$$inlined$mapNotNull$1$2 r5 = (com.android.systemui.qs.pipeline.data.repository.QSSettingsRestoredBroadcastRepository$restoreData$lambda$8$$inlined$mapNotNull$1$2) r5
            kotlin.ResultKt.throwOnFailure(r11)
            goto L7b
        L49:
            kotlin.ResultKt.throwOnFailure(r11)
            kotlin.Pair r10 = (kotlin.Pair) r10
            java.lang.Object r11 = r10.component1()
            r2 = r11
            android.content.Intent r2 = (android.content.Intent) r2
            java.lang.Object r10 = r10.component2()
            java.lang.Number r10 = (java.lang.Number) r10
            int r10 = r10.intValue()
            r0.L$0 = r9
            kotlinx.coroutines.flow.FlowCollector r11 = r9.$this_unsafeFlow
            r0.L$1 = r11
            r0.L$2 = r2
            kotlinx.coroutines.sync.MutexImpl r5 = r9.$mutex$inlined
            r0.L$3 = r5
            r0.I$0 = r10
            r0.label = r4
            java.lang.Object r4 = r5.lock(r0)
            if (r4 != r1) goto L76
            return r1
        L76:
            r4 = r11
            r8 = r5
            r5 = r9
            r9 = r10
            r10 = r8
        L7b:
            r11 = 0
            java.lang.Integer r6 = new java.lang.Integer     // Catch: java.lang.Throwable -> L95
            r6.<init>(r9)     // Catch: java.lang.Throwable -> L95
            java.util.Map r7 = r5.$firstIntent$inlined     // Catch: java.lang.Throwable -> L95
            boolean r6 = r7.containsKey(r6)     // Catch: java.lang.Throwable -> L95
            if (r6 != 0) goto L97
            java.lang.Integer r6 = new java.lang.Integer     // Catch: java.lang.Throwable -> L95
            r6.<init>(r9)     // Catch: java.lang.Throwable -> L95
            java.util.Map r9 = r5.$firstIntent$inlined     // Catch: java.lang.Throwable -> L95
            r9.put(r6, r2)     // Catch: java.lang.Throwable -> L95
            r9 = r11
            goto Lad
        L95:
            r9 = move-exception
            goto Lc6
        L97:
            java.util.Map r6 = r5.$firstIntent$inlined     // Catch: java.lang.Throwable -> L95
            java.lang.Integer r7 = new java.lang.Integer     // Catch: java.lang.Throwable -> L95
            r7.<init>(r9)     // Catch: java.lang.Throwable -> L95
            java.lang.Object r6 = r6.remove(r7)     // Catch: java.lang.Throwable -> L95
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)     // Catch: java.lang.Throwable -> L95
            android.content.Intent r6 = (android.content.Intent) r6     // Catch: java.lang.Throwable -> L95
            com.android.systemui.qs.pipeline.data.repository.QSSettingsRestoredBroadcastRepository r5 = r5.$this_run$inlined     // Catch: java.lang.Throwable -> L95
            com.android.systemui.qs.pipeline.data.model.RestoreData r9 = com.android.systemui.qs.pipeline.data.repository.QSSettingsRestoredBroadcastRepository.access$processIntents(r5, r9, r6, r2)     // Catch: java.lang.Throwable -> L95
        Lad:
            r10.unlock(r11)
            if (r9 == 0) goto Lc3
            r0.L$0 = r11
            r0.L$1 = r11
            r0.L$2 = r11
            r0.L$3 = r11
            r0.label = r3
            java.lang.Object r9 = r4.emit(r9, r0)
            if (r9 != r1) goto Lc3
            return r1
        Lc3:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        Lc6:
            r10.unlock(r11)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.pipeline.data.repository.QSSettingsRestoredBroadcastRepository$restoreData$lambda$8$$inlined$mapNotNull$1$2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
