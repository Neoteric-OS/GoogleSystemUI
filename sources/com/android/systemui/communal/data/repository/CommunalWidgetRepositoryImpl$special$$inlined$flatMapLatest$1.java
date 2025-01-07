package com.android.systemui.communal.data.repository;

import android.appwidget.AppWidgetProviderInfo;
import com.android.systemui.common.data.repository.PackageChangeRepository;
import com.android.systemui.common.data.repository.PackageChangeRepositoryImpl;
import com.android.systemui.communal.data.repository.CommunalWidgetRepositoryImpl;
import com.android.systemui.communal.shared.model.CommunalWidgetContentModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CommunalWidgetRepositoryImpl$special$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ PackageChangeRepository $packageChangeRepository$inlined;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ CommunalWidgetRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CommunalWidgetRepositoryImpl$special$$inlined$flatMapLatest$1(Continuation continuation, PackageChangeRepository packageChangeRepository, CommunalWidgetRepositoryImpl communalWidgetRepositoryImpl) {
        super(3, continuation);
        this.$packageChangeRepository$inlined = packageChangeRepository;
        this.this$0 = communalWidgetRepositoryImpl;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        CommunalWidgetRepositoryImpl$special$$inlined$flatMapLatest$1 communalWidgetRepositoryImpl$special$$inlined$flatMapLatest$1 = new CommunalWidgetRepositoryImpl$special$$inlined$flatMapLatest$1((Continuation) obj3, this.$packageChangeRepository$inlined, this.this$0);
        communalWidgetRepositoryImpl$special$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        communalWidgetRepositoryImpl$special$$inlined$flatMapLatest$1.L$1 = obj2;
        return communalWidgetRepositoryImpl$special$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Flow flow;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            final List<CommunalWidgetRepositoryImpl.CommunalWidgetEntry> list = (List) this.L$1;
            if (list == null || !list.isEmpty()) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    if (((CommunalWidgetRepositoryImpl.CommunalWidgetEntry) it.next()).providerInfo == null) {
                        final ReadonlyStateFlow readonlyStateFlow = ((PackageChangeRepositoryImpl) this.$packageChangeRepository$inlined).packageInstallSessionsForPrimaryUser;
                        final CommunalWidgetRepositoryImpl communalWidgetRepositoryImpl = this.this$0;
                        flow = new Flow() { // from class: com.android.systemui.communal.data.repository.CommunalWidgetRepositoryImpl$communalWidgets$lambda$3$$inlined$map$1

                            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                            /* renamed from: com.android.systemui.communal.data.repository.CommunalWidgetRepositoryImpl$communalWidgets$lambda$3$$inlined$map$1$2, reason: invalid class name */
                            public final class AnonymousClass2 implements FlowCollector {
                                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                                public final /* synthetic */ List $widgetEntries$inlined;
                                public final /* synthetic */ CommunalWidgetRepositoryImpl this$0;

                                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                                /* renamed from: com.android.systemui.communal.data.repository.CommunalWidgetRepositoryImpl$communalWidgets$lambda$3$$inlined$map$1$2$1, reason: invalid class name */
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
                                        return AnonymousClass2.this.emit(null, this);
                                    }
                                }

                                public AnonymousClass2(FlowCollector flowCollector, List list, CommunalWidgetRepositoryImpl communalWidgetRepositoryImpl) {
                                    this.$this_unsafeFlow = flowCollector;
                                    this.$widgetEntries$inlined = list;
                                    this.this$0 = communalWidgetRepositoryImpl;
                                }

                                /* JADX WARN: Removed duplicated region for block: B:15:0x0030  */
                                /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
                                @Override // kotlinx.coroutines.flow.FlowCollector
                                /*
                                    Code decompiled incorrectly, please refer to instructions dump.
                                    To view partially-correct add '--show-bad-code' argument
                                */
                                public final java.lang.Object emit(java.lang.Object r13, kotlin.coroutines.Continuation r14) {
                                    /*
                                        r12 = this;
                                        boolean r0 = r14 instanceof com.android.systemui.communal.data.repository.CommunalWidgetRepositoryImpl$communalWidgets$lambda$3$$inlined$map$1.AnonymousClass2.AnonymousClass1
                                        if (r0 == 0) goto L13
                                        r0 = r14
                                        com.android.systemui.communal.data.repository.CommunalWidgetRepositoryImpl$communalWidgets$lambda$3$$inlined$map$1$2$1 r0 = (com.android.systemui.communal.data.repository.CommunalWidgetRepositoryImpl$communalWidgets$lambda$3$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                                        int r1 = r0.label
                                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                                        r3 = r1 & r2
                                        if (r3 == 0) goto L13
                                        int r1 = r1 - r2
                                        r0.label = r1
                                        goto L18
                                    L13:
                                        com.android.systemui.communal.data.repository.CommunalWidgetRepositoryImpl$communalWidgets$lambda$3$$inlined$map$1$2$1 r0 = new com.android.systemui.communal.data.repository.CommunalWidgetRepositoryImpl$communalWidgets$lambda$3$$inlined$map$1$2$1
                                        r0.<init>(r14)
                                    L18:
                                        java.lang.Object r14 = r0.result
                                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                                        int r2 = r0.label
                                        r3 = 1
                                        if (r2 == 0) goto L30
                                        if (r2 != r3) goto L28
                                        kotlin.ResultKt.throwOnFailure(r14)
                                        goto Lb0
                                    L28:
                                        java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
                                        java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
                                        r12.<init>(r13)
                                        throw r12
                                    L30:
                                        kotlin.ResultKt.throwOnFailure(r14)
                                        java.util.List r13 = (java.util.List) r13
                                        java.util.List r14 = r12.$widgetEntries$inlined
                                        java.util.ArrayList r2 = new java.util.ArrayList
                                        r2.<init>()
                                        java.util.Iterator r14 = r14.iterator()
                                    L40:
                                        boolean r4 = r14.hasNext()
                                        if (r4 == 0) goto La5
                                        java.lang.Object r4 = r14.next()
                                        com.android.systemui.communal.data.repository.CommunalWidgetRepositoryImpl$CommunalWidgetEntry r4 = (com.android.systemui.communal.data.repository.CommunalWidgetRepositoryImpl.CommunalWidgetEntry) r4
                                        com.android.systemui.communal.data.repository.CommunalWidgetRepositoryImpl r5 = r12.this$0
                                        r5.getClass()
                                        android.appwidget.AppWidgetProviderInfo r5 = r4.providerInfo
                                        if (r5 == 0) goto L5f
                                        com.android.systemui.communal.shared.model.CommunalWidgetContentModel$Available r6 = new com.android.systemui.communal.shared.model.CommunalWidgetContentModel$Available
                                        int r7 = r4.appWidgetId
                                        int r4 = r4.rank
                                        r6.<init>(r7, r5, r4)
                                        goto L9f
                                    L5f:
                                        java.lang.String r5 = r4.componentName
                                        android.content.ComponentName r9 = android.content.ComponentName.unflattenFromString(r5)
                                        java.util.Iterator r5 = r13.iterator()
                                    L69:
                                        boolean r6 = r5.hasNext()
                                        r7 = 0
                                        if (r6 == 0) goto L88
                                        java.lang.Object r6 = r5.next()
                                        r8 = r6
                                        com.android.systemui.common.shared.model.PackageInstallSession r8 = (com.android.systemui.common.shared.model.PackageInstallSession) r8
                                        java.lang.String r8 = r8.packageName
                                        if (r9 == 0) goto L80
                                        java.lang.String r10 = r9.getPackageName()
                                        goto L81
                                    L80:
                                        r10 = r7
                                    L81:
                                        boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual(r8, r10)
                                        if (r8 == 0) goto L69
                                        goto L89
                                    L88:
                                        r6 = r7
                                    L89:
                                        com.android.systemui.common.shared.model.PackageInstallSession r6 = (com.android.systemui.common.shared.model.PackageInstallSession) r6
                                        if (r9 == 0) goto L9e
                                        if (r6 == 0) goto L9e
                                        com.android.systemui.communal.shared.model.CommunalWidgetContentModel$Pending r5 = new com.android.systemui.communal.shared.model.CommunalWidgetContentModel$Pending
                                        android.graphics.Bitmap r10 = r6.icon
                                        android.os.UserHandle r11 = r6.user
                                        int r7 = r4.appWidgetId
                                        int r8 = r4.rank
                                        r6 = r5
                                        r6.<init>(r7, r8, r9, r10, r11)
                                        goto L9f
                                    L9e:
                                        r6 = r7
                                    L9f:
                                        if (r6 == 0) goto L40
                                        r2.add(r6)
                                        goto L40
                                    La5:
                                        r0.label = r3
                                        kotlinx.coroutines.flow.FlowCollector r12 = r12.$this_unsafeFlow
                                        java.lang.Object r12 = r12.emit(r2, r0)
                                        if (r12 != r1) goto Lb0
                                        return r1
                                    Lb0:
                                        kotlin.Unit r12 = kotlin.Unit.INSTANCE
                                        return r12
                                    */
                                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.communal.data.repository.CommunalWidgetRepositoryImpl$communalWidgets$lambda$3$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                                }
                            }

                            @Override // kotlinx.coroutines.flow.Flow
                            public final Object collect(FlowCollector flowCollector2, Continuation continuation) {
                                ReadonlyStateFlow.this.collect(new AnonymousClass2(flowCollector2, list, communalWidgetRepositoryImpl), continuation);
                                return CoroutineSingletons.COROUTINE_SUSPENDED;
                            }
                        };
                        break;
                    }
                }
            }
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
            for (CommunalWidgetRepositoryImpl.CommunalWidgetEntry communalWidgetEntry : list) {
                this.this$0.getClass();
                int i2 = communalWidgetEntry.appWidgetId;
                AppWidgetProviderInfo appWidgetProviderInfo = communalWidgetEntry.providerInfo;
                Intrinsics.checkNotNull(appWidgetProviderInfo);
                arrayList.add(new CommunalWidgetContentModel.Available(i2, appWidgetProviderInfo, communalWidgetEntry.rank));
            }
            flow = new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(arrayList);
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, flow, this) == coroutineSingletons) {
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
