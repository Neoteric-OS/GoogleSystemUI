package com.android.systemui.qs.panels.data.repository;

import android.content.SharedPreferences;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QSPreferencesRepository$largeTilesSpecs$lambda$5$$inlined$map$1$2 implements FlowCollector {
    public final /* synthetic */ SharedPreferences $prefs$inlined;
    public final /* synthetic */ FlowCollector $this_unsafeFlow;
    public final /* synthetic */ QSPreferencesRepository this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.qs.panels.data.repository.QSPreferencesRepository$largeTilesSpecs$lambda$5$$inlined$map$1$2$1, reason: invalid class name */
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
            return QSPreferencesRepository$largeTilesSpecs$lambda$5$$inlined$map$1$2.this.emit(null, this);
        }
    }

    public QSPreferencesRepository$largeTilesSpecs$lambda$5$$inlined$map$1$2(FlowCollector flowCollector, SharedPreferences sharedPreferences, QSPreferencesRepository qSPreferencesRepository) {
        this.$this_unsafeFlow = flowCollector;
        this.$prefs$inlined = sharedPreferences;
        this.this$0 = qSPreferencesRepository;
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x0098, code lost:
    
        if (r8 != null) goto L28;
     */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object emit(java.lang.Object r8, kotlin.coroutines.Continuation r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof com.android.systemui.qs.panels.data.repository.QSPreferencesRepository$largeTilesSpecs$lambda$5$$inlined$map$1$2.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r9
            com.android.systemui.qs.panels.data.repository.QSPreferencesRepository$largeTilesSpecs$lambda$5$$inlined$map$1$2$1 r0 = (com.android.systemui.qs.panels.data.repository.QSPreferencesRepository$largeTilesSpecs$lambda$5$$inlined$map$1$2.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.qs.panels.data.repository.QSPreferencesRepository$largeTilesSpecs$lambda$5$$inlined$map$1$2$1 r0 = new com.android.systemui.qs.panels.data.repository.QSPreferencesRepository$largeTilesSpecs$lambda$5$$inlined$map$1$2$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L30
            if (r2 != r3) goto L28
            kotlin.ResultKt.throwOnFailure(r9)
            goto Laa
        L28:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L30:
            kotlin.ResultKt.throwOnFailure(r9)
            kotlin.Unit r8 = (kotlin.Unit) r8
            android.content.SharedPreferences r8 = r7.$prefs$inlined
            com.android.systemui.qs.panels.data.repository.QSPreferencesRepository r9 = r7.this$0
            com.android.systemui.qs.panels.data.repository.DefaultLargeTilesRepositoryImpl r2 = r9.defaultLargeTilesRepository
            java.util.Set r2 = r2.defaultLargeTiles
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            java.util.ArrayList r4 = new java.util.ArrayList
            r5 = 10
            int r6 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r2, r5)
            r4.<init>(r6)
            java.util.Iterator r2 = r2.iterator()
        L4e:
            boolean r6 = r2.hasNext()
            if (r6 == 0) goto L62
            java.lang.Object r6 = r2.next()
            com.android.systemui.qs.pipeline.shared.TileSpec r6 = (com.android.systemui.qs.pipeline.shared.TileSpec) r6
            java.lang.String r6 = r6.getSpec()
            r4.add(r6)
            goto L4e
        L62:
            java.util.Set r2 = kotlin.collections.CollectionsKt.toSet(r4)
            java.lang.String r4 = "large_tiles_specs"
            java.util.Set r8 = r8.getStringSet(r4, r2)
            if (r8 == 0) goto L9b
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            java.util.ArrayList r2 = new java.util.ArrayList
            int r4 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r8, r5)
            r2.<init>(r4)
            java.util.Iterator r8 = r8.iterator()
        L7d:
            boolean r4 = r8.hasNext()
            if (r4 == 0) goto L94
            java.lang.Object r4 = r8.next()
            java.lang.String r4 = (java.lang.String) r4
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)
            com.android.systemui.qs.pipeline.shared.TileSpec r4 = com.android.systemui.qs.pipeline.shared.TileSpec.Companion.create(r4)
            r2.add(r4)
            goto L7d
        L94:
            java.util.Set r8 = kotlin.collections.CollectionsKt.toSet(r2)
            if (r8 == 0) goto L9b
            goto L9f
        L9b:
            com.android.systemui.qs.panels.data.repository.DefaultLargeTilesRepositoryImpl r8 = r9.defaultLargeTilesRepository
            java.util.Set r8 = r8.defaultLargeTiles
        L9f:
            r0.label = r3
            kotlinx.coroutines.flow.FlowCollector r7 = r7.$this_unsafeFlow
            java.lang.Object r7 = r7.emit(r8, r0)
            if (r7 != r1) goto Laa
            return r1
        Laa:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.panels.data.repository.QSPreferencesRepository$largeTilesSpecs$lambda$5$$inlined$map$1$2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
