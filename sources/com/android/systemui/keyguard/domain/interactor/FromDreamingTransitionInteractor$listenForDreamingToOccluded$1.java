package com.android.systemui.keyguard.domain.interactor;

import com.android.systemui.communal.domain.interactor.CommunalSceneInteractor;
import com.android.systemui.communal.shared.model.CommunalScenes;
import com.android.systemui.keyguard.domain.interactor.TransitionInteractor$filterRelevantKeyguardStateAnd$$inlined$filter$1;
import com.android.systemui.keyguard.shared.model.DozeStateModel;
import com.android.systemui.keyguard.shared.model.DozeTransitionModel;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.keyguard.shared.model.TransitionStep;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.AdaptedFunctionReference;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class FromDreamingTransitionInteractor$listenForDreamingToOccluded$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ FromDreamingTransitionInteractor this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.domain.interactor.FromDreamingTransitionInteractor$listenForDreamingToOccluded$1$2, reason: invalid class name */
    final /* synthetic */ class AnonymousClass2 extends AdaptedFunctionReference implements Function3 {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(3, Pair.class, "<init>", "<init>(Ljava/lang/Object;Ljava/lang/Object;)V", 4);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            Boolean bool = (Boolean) obj;
            bool.booleanValue();
            Boolean bool2 = (Boolean) obj2;
            bool2.booleanValue();
            return new Pair(bool, bool2);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.domain.interactor.FromDreamingTransitionInteractor$listenForDreamingToOccluded$1$4, reason: invalid class name */
    public final class AnonymousClass4 implements FlowCollector {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ FromDreamingTransitionInteractor this$0;

        public /* synthetic */ AnonymousClass4(FromDreamingTransitionInteractor fromDreamingTransitionInteractor, int i) {
            this.$r8$classId = i;
            this.this$0 = fromDreamingTransitionInteractor;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(Object obj, Continuation continuation) {
            Object startTransitionTo$default;
            switch (this.$r8$classId) {
                case 0:
                    Object startTransitionTo$default2 = TransitionInteractor.startTransitionTo$default(this.this$0, KeyguardState.OCCLUDED, null, null, "Occluded but no longer dreaming", continuation, 6);
                    return startTransitionTo$default2 == CoroutineSingletons.COROUTINE_SUSPENDED ? startTransitionTo$default2 : Unit.INSTANCE;
                case 1:
                    ((Boolean) obj).getClass();
                    Object startTransitionTo$default3 = TransitionInteractor.startTransitionTo$default(this.this$0, KeyguardState.ALTERNATE_BOUNCER, null, null, null, continuation, 14);
                    return startTransitionTo$default3 == CoroutineSingletons.COROUTINE_SUSPENDED ? startTransitionTo$default3 : Unit.INSTANCE;
                case 2:
                    DozeStateModel dozeStateModel = ((DozeTransitionModel) obj).to;
                    DozeStateModel dozeStateModel2 = DozeStateModel.DOZE;
                    Unit unit = Unit.INSTANCE;
                    if (dozeStateModel == dozeStateModel2) {
                        startTransitionTo$default = TransitionInteractor.startTransitionTo$default(this.this$0, KeyguardState.DOZING, null, null, null, continuation, 14);
                        if (startTransitionTo$default != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            return unit;
                        }
                    } else {
                        if (dozeStateModel != DozeStateModel.DOZE_AOD) {
                            return unit;
                        }
                        startTransitionTo$default = TransitionInteractor.startTransitionTo$default(this.this$0, KeyguardState.AOD, null, null, null, continuation, 14);
                        if (startTransitionTo$default != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            return unit;
                        }
                    }
                    return startTransitionTo$default;
                case 3:
                    if (((Boolean) obj).booleanValue()) {
                        FromDreamingTransitionInteractor fromDreamingTransitionInteractor = this.this$0;
                        if (fromDreamingTransitionInteractor.dreamManager.canStartDreaming(false)) {
                            CommunalSceneInteractor.snapToScene$default(fromDreamingTransitionInteractor.communalSceneInteractor, CommunalScenes.Communal, "from dreaming to hub", 12);
                        }
                    }
                    return Unit.INSTANCE;
                case 4:
                    Object startTransitionTo$default4 = TransitionInteractor.startTransitionTo$default(this.this$0, KeyguardState.GONE, null, null, null, continuation, 14);
                    return startTransitionTo$default4 == CoroutineSingletons.COROUTINE_SUSPENDED ? startTransitionTo$default4 : Unit.INSTANCE;
                case 5:
                    ((Boolean) obj).getClass();
                    FromDreamingTransitionInteractor fromDreamingTransitionInteractor2 = this.this$0;
                    boolean booleanValue = ((Boolean) ((StateFlowImpl) fromDreamingTransitionInteractor2.keyguardInteractor.isKeyguardDismissible).getValue()).booleanValue();
                    Unit unit2 = Unit.INSTANCE;
                    if (!booleanValue || ((Boolean) ((StateFlowImpl) fromDreamingTransitionInteractor2.keyguardInteractor.isKeyguardShowing).getValue()).booleanValue()) {
                        return unit2;
                    }
                    Object startTransitionTo$default5 = TransitionInteractor.startTransitionTo$default(this.this$0, KeyguardState.GONE, null, null, null, continuation, 14);
                    return startTransitionTo$default5 == CoroutineSingletons.COROUTINE_SUSPENDED ? startTransitionTo$default5 : unit2;
                default:
                    Pair pair = (Pair) obj;
                    boolean booleanValue2 = ((Boolean) pair.component1()).booleanValue();
                    TransitionStep transitionStep = (TransitionStep) pair.component2();
                    Unit unit3 = Unit.INSTANCE;
                    if (!booleanValue2 || transitionStep.to != KeyguardState.DREAMING) {
                        return unit3;
                    }
                    Object startTransitionTo$default6 = TransitionInteractor.startTransitionTo$default(this.this$0, KeyguardState.PRIMARY_BOUNCER, null, null, null, continuation, 14);
                    return startTransitionTo$default6 == CoroutineSingletons.COROUTINE_SUSPENDED ? startTransitionTo$default6 : unit3;
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FromDreamingTransitionInteractor$listenForDreamingToOccluded$1(FromDreamingTransitionInteractor fromDreamingTransitionInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = fromDreamingTransitionInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new FromDreamingTransitionInteractor$listenForDreamingToOccluded$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((FromDreamingTransitionInteractor$listenForDreamingToOccluded$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FromDreamingTransitionInteractor fromDreamingTransitionInteractor = this.this$0;
            KeyguardInteractor keyguardInteractor = fromDreamingTransitionInteractor.keyguardInteractor;
            FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 flowKt__ZipKt$combine$$inlined$unsafeFlow$1 = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(keyguardInteractor.isKeyguardOccluded, keyguardInteractor.isDreaming, AnonymousClass2.INSTANCE);
            int i2 = Duration.$r8$clinit;
            Flow debounce = FlowKt.debounce(flowKt__ZipKt$combine$$inlined$unsafeFlow$1, DelayKt.m1785toDelayMillisLRDsOJo(DurationKt.toDuration(100, DurationUnit.MILLISECONDS)));
            AnonymousClass3 anonymousClass3 = new Function1() { // from class: com.android.systemui.keyguard.domain.interactor.FromDreamingTransitionInteractor$listenForDreamingToOccluded$1.3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    Pair pair = (Pair) obj2;
                    return Boolean.valueOf(((Boolean) pair.component1()).booleanValue() && !((Boolean) pair.component2()).booleanValue());
                }
            };
            AnonymousClass4 anonymousClass4 = new AnonymousClass4(this.this$0, 0);
            this.label = 1;
            Object collect = debounce.collect(new TransitionInteractor$filterRelevantKeyguardStateAnd$$inlined$filter$1.AnonymousClass2(anonymousClass4, fromDreamingTransitionInteractor, anonymousClass3), this);
            if (collect != coroutineSingletons) {
                collect = unit;
            }
            if (collect == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return unit;
    }
}
