package com.android.systemui.keyguard.domain.interactor;

import com.android.compose.animation.scene.SceneKey;
import com.android.systemui.communal.domain.interactor.CommunalSceneInteractor;
import com.android.systemui.communal.shared.model.CommunalScenes;
import com.android.systemui.communal.shared.model.CommunalTransitionKeys;
import com.android.systemui.communal.shared.model.EditModeState;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.util.kotlin.BooleanFlowOperators$not$$inlined$map$1;
import com.android.systemui.util.kotlin.FlowKt;
import java.util.Arrays;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.AdaptedFunctionReference;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.SafeFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class FromGlanceableHubTransitionInteractor$listenForHubToGone$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ FromGlanceableHubTransitionInteractor this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.domain.interactor.FromGlanceableHubTransitionInteractor$listenForHubToGone$1$3, reason: invalid class name */
    final /* synthetic */ class AnonymousClass3 extends AdaptedFunctionReference implements Function3 {
        public static final AnonymousClass3 INSTANCE = new AnonymousClass3();

        public AnonymousClass3() {
            super(3, Pair.class, "<init>", "<init>(Ljava/lang/Object;Ljava/lang/Object;)V", 4);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            Boolean bool = (Boolean) obj;
            bool.booleanValue();
            return new Pair(bool, (EditModeState) obj2);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.domain.interactor.FromGlanceableHubTransitionInteractor$listenForHubToGone$1$4, reason: invalid class name */
    public final class AnonymousClass4 implements FlowCollector {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ FromGlanceableHubTransitionInteractor this$0;

        public /* synthetic */ AnonymousClass4(FromGlanceableHubTransitionInteractor fromGlanceableHubTransitionInteractor, int i) {
            this.$r8$classId = i;
            this.this$0 = fromGlanceableHubTransitionInteractor;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(Object obj, Continuation continuation) {
            FromGlanceableHubTransitionInteractor fromGlanceableHubTransitionInteractor = this.this$0;
            Unit unit = Unit.INSTANCE;
            switch (this.$r8$classId) {
                case 0:
                    EditModeState editModeState = (EditModeState) ((Pair) obj).component2();
                    if (editModeState == EditModeState.STARTING || editModeState == EditModeState.SHOWING) {
                        Object startTransitionTo$default = TransitionInteractor.startTransitionTo$default(this.this$0, KeyguardState.GONE, null, null, null, continuation, 14);
                        return startTransitionTo$default == CoroutineSingletons.COROUTINE_SUSPENDED ? startTransitionTo$default : unit;
                    }
                    fromGlanceableHubTransitionInteractor.communalSceneInteractor.changeScene(CommunalScenes.Blank, "hub to gone", CommunalTransitionKeys.SimpleFade, KeyguardState.GONE);
                    return unit;
                case 1:
                    ((Boolean) obj).getClass();
                    Object startTransitionTo$default2 = TransitionInteractor.startTransitionTo$default(this.this$0, KeyguardState.ALTERNATE_BOUNCER, null, null, null, continuation, 14);
                    return startTransitionTo$default2 == CoroutineSingletons.COROUTINE_SUSPENDED ? startTransitionTo$default2 : unit;
                case 2:
                    ((Boolean) obj).getClass();
                    CommunalSceneInteractor communalSceneInteractor = fromGlanceableHubTransitionInteractor.communalSceneInteractor;
                    SceneKey sceneKey = CommunalScenes.Blank;
                    KeyguardState.Companion companion = KeyguardState.Companion;
                    CommunalSceneInteractor.snapToScene$default(communalSceneInteractor, sceneKey, "hub to dozing", 4);
                    return unit;
                case 3:
                    fromGlanceableHubTransitionInteractor.communalSceneInteractor.changeScene(CommunalScenes.Blank, "hub to occluded", CommunalTransitionKeys.SimpleFade, KeyguardState.OCCLUDED);
                    return unit;
                default:
                    ((Boolean) obj).getClass();
                    Object startTransitionTo$default3 = TransitionInteractor.startTransitionTo$default(this.this$0, KeyguardState.PRIMARY_BOUNCER, null, null, null, continuation, 14);
                    return startTransitionTo$default3 == CoroutineSingletons.COROUTINE_SUSPENDED ? startTransitionTo$default3 : unit;
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FromGlanceableHubTransitionInteractor$listenForHubToGone$1(FromGlanceableHubTransitionInteractor fromGlanceableHubTransitionInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = fromGlanceableHubTransitionInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new FromGlanceableHubTransitionInteractor$listenForHubToGone$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((FromGlanceableHubTransitionInteractor$listenForHubToGone$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FromGlanceableHubTransitionInteractor fromGlanceableHubTransitionInteractor = this.this$0;
            SafeFlow sample = FlowKt.sample(new TransitionInteractor$filterRelevantKeyguardStateAnd$$inlined$filter$1(kotlinx.coroutines.flow.FlowKt.distinctUntilChanged(new BooleanFlowOperators$not$$inlined$map$1(1, (Flow[]) CollectionsKt.toList(ArraysKt.asIterable(new Flow[]{fromGlanceableHubTransitionInteractor.keyguardInteractor.isKeyguardGoingAway, new BooleanFlowOperators$not$$inlined$map$1(0, kotlinx.coroutines.flow.FlowKt.distinctUntilChanged(new BooleanFlowOperators$not$$inlined$map$1(2, (Flow[]) CollectionsKt.toList(ArraysKt.asIterable((Flow[]) Arrays.copyOf(new Flow[]{fromGlanceableHubTransitionInteractor.communalSceneInteractor.isLaunchingWidget}, 1))).toArray(new Flow[0]))))})).toArray(new Flow[0]))), fromGlanceableHubTransitionInteractor, new Function1() { // from class: com.android.systemui.keyguard.domain.interactor.FromGlanceableHubTransitionInteractor$listenForHubToGone$1.1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    Boolean bool = (Boolean) obj2;
                    bool.booleanValue();
                    return bool;
                }
            }), this.this$0.communalSceneInteractor.editModeState, AnonymousClass3.INSTANCE);
            AnonymousClass4 anonymousClass4 = new AnonymousClass4(this.this$0, 0);
            this.label = 1;
            if (sample.collect(anonymousClass4, this) == coroutineSingletons) {
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
