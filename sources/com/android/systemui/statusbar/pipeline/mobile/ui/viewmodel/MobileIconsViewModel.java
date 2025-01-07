package com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel;

import androidx.compose.foundation.text.input.internal.AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0;
import com.android.app.tracing.coroutines.TraceContextElementKt;
import com.android.app.tracing.coroutines.TraceDataThreadLocal;
import com.android.systemui.flags.FeatureFlagsClassic;
import com.android.systemui.statusbar.phone.StatusBarLocation;
import com.android.systemui.statusbar.pipeline.airplane.domain.interactor.AirplaneModeInteractor;
import com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconInteractor;
import com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconsInteractorImpl;
import com.android.systemui.statusbar.pipeline.mobile.ui.MobileViewLogger;
import com.android.systemui.statusbar.pipeline.mobile.ui.VerboseMobileViewLogger;
import com.android.systemui.statusbar.pipeline.shared.ConnectivityConstants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.EmptyList;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobImpl;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MobileIconsViewModel {
    public final AirplaneModeInteractor airplaneModeInteractor;
    public final ConnectivityConstants constants;
    public final ReadonlyStateFlow firstMobileSubShowingNetworkTypeIcon;
    public final ReadonlyStateFlow firstMobileSubViewModel;
    public final FeatureFlagsClassic flags;
    public final MobileIconsInteractorImpl interactor;
    public final MobileViewLogger logger;
    public final Map reuseCache = new LinkedHashMap();
    public final CoroutineScope scope;
    public final ReadonlyStateFlow subscriptionIdsFlow;
    public final VerboseMobileViewLogger verboseLogger;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconsViewModel$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return MobileIconsViewModel.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            return CoroutineSingletons.COROUTINE_SUSPENDED;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i != 0) {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                throw AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0.m(obj);
            }
            ResultKt.throwOnFailure(obj);
            final MobileIconsViewModel mobileIconsViewModel = MobileIconsViewModel.this;
            ReadonlyStateFlow readonlyStateFlow = mobileIconsViewModel.subscriptionIdsFlow;
            FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconsViewModel.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj2, Continuation continuation) {
                    CoroutineScope coroutineScope;
                    List list = (List) obj2;
                    MobileIconsViewModel mobileIconsViewModel2 = MobileIconsViewModel.this;
                    Set keySet = mobileIconsViewModel2.reuseCache.keySet();
                    ArrayList arrayList = new ArrayList();
                    for (Object obj3 : keySet) {
                        if (!list.contains(Integer.valueOf(((Number) obj3).intValue()))) {
                            arrayList.add(obj3);
                        }
                    }
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        Pair pair = (Pair) mobileIconsViewModel2.reuseCache.remove(Integer.valueOf(((Number) it.next()).intValue()));
                        if (pair != null && (coroutineScope = (CoroutineScope) pair.getSecond()) != null) {
                            CoroutineScopeKt.cancel(coroutineScope, null);
                        }
                    }
                    return Unit.INSTANCE;
                }
            };
            this.label = 1;
            ((StateFlowImpl) readonlyStateFlow.$$delegate_0).collect(flowCollector, this);
            return coroutineSingletons;
        }
    }

    public MobileIconsViewModel(MobileViewLogger mobileViewLogger, VerboseMobileViewLogger verboseMobileViewLogger, MobileIconsInteractorImpl mobileIconsInteractorImpl, AirplaneModeInteractor airplaneModeInteractor, ConnectivityConstants connectivityConstants, FeatureFlagsClassic featureFlagsClassic, CoroutineScope coroutineScope) {
        this.logger = mobileViewLogger;
        this.verboseLogger = verboseMobileViewLogger;
        this.interactor = mobileIconsInteractorImpl;
        this.airplaneModeInteractor = airplaneModeInteractor;
        this.constants = connectivityConstants;
        this.flags = featureFlagsClassic;
        this.scope = coroutineScope;
        final ReadonlyStateFlow stateIn = FlowKt.stateIn(FlowKt.mapLatest(new MobileIconsViewModel$subscriptionIdsFlow$1(2, null), mobileIconsInteractorImpl.filteredSubscriptions), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), EmptyList.INSTANCE);
        this.subscriptionIdsFlow = stateIn;
        this.firstMobileSubShowingNetworkTypeIcon = FlowKt.stateIn(FlowKt.transformLatest(FlowKt.stateIn(new Flow() { // from class: com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconsViewModel$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconsViewModel$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ MobileIconsViewModel this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconsViewModel$special$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, MobileIconsViewModel mobileIconsViewModel) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = mobileIconsViewModel;
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r5, kotlin.coroutines.Continuation r6) {
                    /*
                        r4 = this;
                        boolean r0 = r6 instanceof com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconsViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconsViewModel$special$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconsViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconsViewModel$special$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconsViewModel$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L57
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        java.util.List r5 = (java.util.List) r5
                        boolean r6 = r5.isEmpty()
                        if (r6 == 0) goto L3c
                        r5 = 0
                        goto L4c
                    L3c:
                        java.lang.Object r5 = kotlin.collections.CollectionsKt.last(r5)
                        java.lang.Number r5 = (java.lang.Number) r5
                        int r5 = r5.intValue()
                        com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconsViewModel r6 = r4.this$0
                        com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconViewModelCommon r5 = r6.commonViewModelForSub(r5)
                    L4c:
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L57
                        return r1
                    L57:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconsViewModel$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                ReadonlyStateFlow.this.collect(new AnonymousClass2(flowCollector, this), continuation);
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        }, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), null), new MobileIconsViewModel$special$$inlined$flatMapLatest$1(3, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), Boolean.FALSE);
        BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass1(null), 3);
    }

    public final MobileIconViewModelCommon commonViewModelForSub(int i) {
        Map map = this.reuseCache;
        Integer valueOf = Integer.valueOf(i);
        Object obj = map.get(valueOf);
        if (obj == null) {
            TraceDataThreadLocal traceDataThreadLocal = TraceContextElementKt.traceThreadLocal;
            EmptyCoroutineContext emptyCoroutineContext = EmptyCoroutineContext.INSTANCE;
            CoroutineScope coroutineScope = this.scope;
            ContextScope CoroutineScope = CoroutineScopeKt.CoroutineScope(coroutineScope.getCoroutineContext().plus(new JobImpl((Job) coroutineScope.getCoroutineContext().get(Job.Key.$$INSTANCE))).plus(emptyCoroutineContext));
            Pair pair = new Pair(new MobileIconViewModel(i, this.interactor.getMobileConnectionInteractorForSubId(i), this.airplaneModeInteractor, this.constants, this.flags, CoroutineScope), CoroutineScope);
            map.put(valueOf, pair);
            obj = pair;
        }
        return (MobileIconViewModelCommon) ((Pair) obj).getFirst();
    }

    public final LocationBasedMobileViewModel viewModelForSub(int i, StatusBarLocation statusBarLocation) {
        MobileIconViewModelCommon commonViewModelForSub = commonViewModelForSub(i);
        MobileIconInteractor mobileConnectionInteractorForSubId = this.interactor.getMobileConnectionInteractorForSubId(i);
        int ordinal = statusBarLocation.ordinal();
        if (ordinal == 0) {
            return new HomeMobileIconViewModel(commonViewModelForSub, StatusBarLocation.HOME, this.verboseLogger);
        }
        if (ordinal == 1) {
            return new KeyguardMobileIconViewModel(commonViewModelForSub, StatusBarLocation.KEYGUARD, null);
        }
        if (ordinal == 2) {
            return new QsMobileIconViewModel(commonViewModelForSub, StatusBarLocation.QS, null);
        }
        if (ordinal == 3) {
            return new ShadeCarrierGroupMobileIconViewModel(commonViewModelForSub, mobileConnectionInteractorForSubId, this.scope);
        }
        throw new NoWhenBranchMatchedException();
    }

    public static /* synthetic */ void getReuseCache$annotations() {
    }
}
