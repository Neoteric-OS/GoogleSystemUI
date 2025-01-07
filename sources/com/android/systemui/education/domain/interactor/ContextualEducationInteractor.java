package com.android.systemui.education.domain.interactor;

import com.android.systemui.CoreStartable;
import com.android.systemui.contextualeducation.GestureType;
import com.android.systemui.education.data.model.GestureEduModel;
import com.android.systemui.education.data.repository.ContextualEducationRepository;
import com.android.systemui.education.data.repository.UserContextualEducationRepository;
import com.android.systemui.education.data.repository.UserContextualEducationRepository$readEduDeviceConnectionTime$$inlined$map$1;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import java.time.Clock;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ContextualEducationInteractor implements CoreStartable {
    public final CoroutineDispatcher backgroundDispatcher;
    public final CoroutineScope backgroundScope;
    public final Clock clock;
    public final Flow eduDeviceConnectionTimeFlow;
    public final Flow keyboardShortcutTriggered;
    public final ContextualEducationRepository repository;
    public final SelectedUserInteractor selectedUserInteractor;
    public final Flow backGestureModelFlow = readEduModelsOnSignalCountChanged(GestureType.BACK);
    public final Flow homeGestureModelFlow = readEduModelsOnSignalCountChanged(GestureType.HOME);
    public final Flow overviewGestureModelFlow = readEduModelsOnSignalCountChanged(GestureType.OVERVIEW);
    public final Flow allAppsGestureModelFlow = readEduModelsOnSignalCountChanged(GestureType.ALL_APPS);

    public ContextualEducationInteractor(CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, Clock clock, SelectedUserInteractor selectedUserInteractor, ContextualEducationRepository contextualEducationRepository) {
        this.backgroundScope = coroutineScope;
        this.backgroundDispatcher = coroutineDispatcher;
        this.clock = clock;
        this.selectedUserInteractor = selectedUserInteractor;
        this.repository = contextualEducationRepository;
        UserContextualEducationRepository userContextualEducationRepository = (UserContextualEducationRepository) contextualEducationRepository;
        this.eduDeviceConnectionTimeFlow = FlowKt.distinctUntilChanged(new UserContextualEducationRepository$readEduDeviceConnectionTime$$inlined$map$1(userContextualEducationRepository.prefData, userContextualEducationRepository));
        this.keyboardShortcutTriggered = userContextualEducationRepository.keyboardShortcutTriggered;
    }

    public final Flow readEduModelsOnSignalCountChanged(final GestureType gestureType) {
        final UserContextualEducationRepository userContextualEducationRepository = (UserContextualEducationRepository) this.repository;
        final ChannelFlowTransformLatest channelFlowTransformLatest = userContextualEducationRepository.prefData;
        return FlowKt.flowOn(FlowKt.distinctUntilChanged(new Function2() { // from class: com.android.systemui.education.domain.interactor.ContextualEducationInteractor$readEduModelsOnSignalCountChanged$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(((GestureEduModel) obj).signalCount == ((GestureEduModel) obj2).signalCount);
            }
        }, new Flow() { // from class: com.android.systemui.education.data.repository.UserContextualEducationRepository$readGestureEduModelFlow$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.education.data.repository.UserContextualEducationRepository$readGestureEduModelFlow$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ GestureType $gestureType$inlined;
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ UserContextualEducationRepository this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.education.data.repository.UserContextualEducationRepository$readGestureEduModelFlow$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, UserContextualEducationRepository userContextualEducationRepository, GestureType gestureType) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = userContextualEducationRepository;
                    this.$gestureType$inlined = gestureType;
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
                        boolean r0 = r6 instanceof com.android.systemui.education.data.repository.UserContextualEducationRepository$readGestureEduModelFlow$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.education.data.repository.UserContextualEducationRepository$readGestureEduModelFlow$$inlined$map$1$2$1 r0 = (com.android.systemui.education.data.repository.UserContextualEducationRepository$readGestureEduModelFlow$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.education.data.repository.UserContextualEducationRepository$readGestureEduModelFlow$$inlined$map$1$2$1 r0 = new com.android.systemui.education.data.repository.UserContextualEducationRepository$readGestureEduModelFlow$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L47
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        androidx.datastore.preferences.core.MutablePreferences r5 = (androidx.datastore.preferences.core.MutablePreferences) r5
                        com.android.systemui.contextualeducation.GestureType r6 = r4.$gestureType$inlined
                        com.android.systemui.education.data.repository.UserContextualEducationRepository r2 = r4.this$0
                        com.android.systemui.education.data.model.GestureEduModel r5 = com.android.systemui.education.data.repository.UserContextualEducationRepository.access$getGestureEduModel(r2, r6, r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L47
                        return r1
                    L47:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.education.data.repository.UserContextualEducationRepository$readGestureEduModelFlow$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = ChannelFlowTransformLatest.this.collect(new AnonymousClass2(flowCollector, userContextualEducationRepository, gestureType), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        }), this.backgroundDispatcher);
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        BuildersKt.launch$default(this.backgroundScope, null, null, new ContextualEducationInteractor$start$1(this, null), 3);
    }
}
