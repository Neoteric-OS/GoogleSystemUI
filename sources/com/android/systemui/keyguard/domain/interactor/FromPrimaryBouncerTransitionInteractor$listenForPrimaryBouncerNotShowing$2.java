package com.android.systemui.keyguard.domain.interactor;

import android.util.Log;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.util.kotlin.Quad;
import com.android.systemui.util.kotlin.Utils;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.SafeFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class FromPrimaryBouncerTransitionInteractor$listenForPrimaryBouncerNotShowing$2 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ FromPrimaryBouncerTransitionInteractor this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.domain.interactor.FromPrimaryBouncerTransitionInteractor$listenForPrimaryBouncerNotShowing$2$2, reason: invalid class name */
    public final class AnonymousClass2 implements FlowCollector {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ FromPrimaryBouncerTransitionInteractor this$0;

        public /* synthetic */ AnonymousClass2(FromPrimaryBouncerTransitionInteractor fromPrimaryBouncerTransitionInteractor, int i) {
            this.$r8$classId = i;
            this.this$0 = fromPrimaryBouncerTransitionInteractor;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(Object obj, Continuation continuation) {
            KeyguardState keyguardState;
            switch (this.$r8$classId) {
                case 0:
                    Quad quad = (Quad) obj;
                    boolean booleanValue = ((Boolean) quad.second).booleanValue();
                    boolean booleanValue2 = ((Boolean) quad.third).booleanValue();
                    boolean booleanValue3 = ((Boolean) quad.fourth).booleanValue();
                    FromPrimaryBouncerTransitionInteractor fromPrimaryBouncerTransitionInteractor = this.this$0;
                    boolean booleanValue4 = ((Boolean) ((StateFlowImpl) fromPrimaryBouncerTransitionInteractor.keyguardInteractor.isKeyguardOccluded).getValue()).booleanValue();
                    if (booleanValue) {
                        keyguardState = (!booleanValue4 || booleanValue2) ? booleanValue3 ? KeyguardState.GLANCEABLE_HUB : booleanValue2 ? KeyguardState.DREAMING : KeyguardState.LOCKSCREEN : KeyguardState.OCCLUDED;
                    } else {
                        Log.i("FromPrimaryBouncerTransitionInteractor", "Going back to sleeping state to correct an attempt to show bouncer");
                        keyguardState = (KeyguardState) ((StateFlowImpl) fromPrimaryBouncerTransitionInteractor.keyguardInteractor.asleepKeyguardState.$$delegate_0).getValue();
                    }
                    Object startTransitionTo$default = TransitionInteractor.startTransitionTo$default(this.this$0, keyguardState, null, null, null, continuation, 14);
                    if (startTransitionTo$default != CoroutineSingletons.COROUTINE_SUSPENDED) {
                        break;
                    }
                    break;
                default:
                    Object startTransitionTo$default2 = TransitionInteractor.startTransitionTo$default(this.this$0, KeyguardState.DREAMING_LOCKSCREEN_HOSTED, null, null, null, continuation, 14);
                    if (startTransitionTo$default2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                        break;
                    }
                    break;
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FromPrimaryBouncerTransitionInteractor$listenForPrimaryBouncerNotShowing$2(FromPrimaryBouncerTransitionInteractor fromPrimaryBouncerTransitionInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = fromPrimaryBouncerTransitionInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new FromPrimaryBouncerTransitionInteractor$listenForPrimaryBouncerNotShowing$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((FromPrimaryBouncerTransitionInteractor$listenForPrimaryBouncerNotShowing$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Utils.Companion companion = Utils.Companion;
            FromPrimaryBouncerTransitionInteractor fromPrimaryBouncerTransitionInteractor = this.this$0;
            KeyguardInteractor keyguardInteractor = fromPrimaryBouncerTransitionInteractor.keyguardInteractor;
            SafeFlow sample = companion.sample(new TransitionInteractor$filterRelevantKeyguardStateAnd$$inlined$filter$1(keyguardInteractor.primaryBouncerShowing, fromPrimaryBouncerTransitionInteractor, new Function1() { // from class: com.android.systemui.keyguard.domain.interactor.FromPrimaryBouncerTransitionInteractor$listenForPrimaryBouncerNotShowing$2.1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return Boolean.valueOf(!((Boolean) obj2).booleanValue());
                }
            }), fromPrimaryBouncerTransitionInteractor.powerInteractor.isAwake, keyguardInteractor.isDreaming, fromPrimaryBouncerTransitionInteractor.communalSceneInteractor.isIdleOnCommunal);
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.this$0, 0);
            this.label = 1;
            if (sample.collect(anonymousClass2, this) == coroutineSingletons) {
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
