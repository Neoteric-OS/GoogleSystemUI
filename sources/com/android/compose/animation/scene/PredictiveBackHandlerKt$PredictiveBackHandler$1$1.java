package com.android.compose.animation.scene;

import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.SnapSpec;
import androidx.compose.foundation.gestures.Orientation;
import com.android.compose.animation.scene.UserActionResult;
import com.android.compose.animation.scene.transition.SeekKt;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class PredictiveBackHandlerKt$PredictiveBackHandler$1$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ SceneTransitionLayoutImpl $layoutImpl;
    final /* synthetic */ UserActionResult $result;
    /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PredictiveBackHandlerKt$PredictiveBackHandler$1$1(UserActionResult userActionResult, SceneTransitionLayoutImpl sceneTransitionLayoutImpl, Continuation continuation) {
        super(2, continuation);
        this.$result = userActionResult;
        this.$layoutImpl = sceneTransitionLayoutImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        PredictiveBackHandlerKt$PredictiveBackHandler$1$1 predictiveBackHandlerKt$PredictiveBackHandler$1$1 = new PredictiveBackHandlerKt$PredictiveBackHandler$1$1(this.$result, this.$layoutImpl, continuation);
        predictiveBackHandlerKt$PredictiveBackHandler$1$1.L$0 = obj;
        return predictiveBackHandlerKt$PredictiveBackHandler$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((PredictiveBackHandlerKt$PredictiveBackHandler$1$1) create((Flow) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference failed for: r5v2, types: [com.android.compose.animation.scene.PredictiveBackHandlerKt$PredictiveBackHandler$1$1$invokeSuspend$$inlined$map$1] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        UserActionResult replaceByOverlay;
        UserActionResult userActionResult;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i != 0) {
            if (i == 1) {
                ResultKt.throwOnFailure(obj);
            }
            if (i == 2) {
                ResultKt.throwOnFailure(obj);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        final Flow flow = (Flow) this.L$0;
        UserActionResult userActionResult2 = this.$result;
        if (userActionResult2 == null) {
            this.label = 1;
            return FlowKt.first(flow, this) == coroutineSingletons ? coroutineSingletons : unit;
        }
        SceneTransitionLayoutImpl sceneTransitionLayoutImpl = this.$layoutImpl;
        if (userActionResult2.getTransitionKey() != null) {
            userActionResult = this.$result;
        } else {
            UserActionResult userActionResult3 = this.$result;
            TransitionKey transitionKey = TransitionKey.PredictiveBack;
            if (userActionResult3 instanceof UserActionResult.ChangeScene) {
                UserActionResult.ChangeScene changeScene = (UserActionResult.ChangeScene) userActionResult3;
                replaceByOverlay = new UserActionResult.ChangeScene(changeScene.toScene, transitionKey, changeScene.requiresFullDistanceSwipe);
            } else if (userActionResult3 instanceof UserActionResult.ShowOverlay) {
                UserActionResult.ShowOverlay showOverlay = (UserActionResult.ShowOverlay) userActionResult3;
                replaceByOverlay = new UserActionResult.ShowOverlay(showOverlay.overlay, transitionKey, showOverlay.requiresFullDistanceSwipe);
            } else if (userActionResult3 instanceof UserActionResult.HideOverlay) {
                UserActionResult.HideOverlay hideOverlay = (UserActionResult.HideOverlay) userActionResult3;
                replaceByOverlay = new UserActionResult.HideOverlay(hideOverlay.overlay, transitionKey, hideOverlay.requiresFullDistanceSwipe);
            } else {
                if (!(userActionResult3 instanceof UserActionResult.ReplaceByOverlay)) {
                    throw new NoWhenBranchMatchedException();
                }
                UserActionResult.ReplaceByOverlay replaceByOverlay2 = (UserActionResult.ReplaceByOverlay) userActionResult3;
                replaceByOverlay = new UserActionResult.ReplaceByOverlay(replaceByOverlay2.overlay, transitionKey, replaceByOverlay2.requiresFullDistanceSwipe);
            }
            userActionResult = replaceByOverlay;
        }
        SwipeAnimation createSwipeAnimation = SwipeAnimationKt.createSwipeAnimation(sceneTransitionLayoutImpl, userActionResult, false, Orientation.Horizontal, 1.0f);
        MutableSceneTransitionLayoutStateImpl mutableSceneTransitionLayoutStateImpl = this.$layoutImpl.state;
        ?? r5 = new Flow() { // from class: com.android.compose.animation.scene.PredictiveBackHandlerKt$PredictiveBackHandler$1$1$invokeSuspend$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.compose.animation.scene.PredictiveBackHandlerKt$PredictiveBackHandler$1$1$invokeSuspend$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.compose.animation.scene.PredictiveBackHandlerKt$PredictiveBackHandler$1$1$invokeSuspend$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector) {
                    this.$this_unsafeFlow = flowCollector;
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
                        boolean r0 = r6 instanceof com.android.compose.animation.scene.PredictiveBackHandlerKt$PredictiveBackHandler$1$1$invokeSuspend$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.compose.animation.scene.PredictiveBackHandlerKt$PredictiveBackHandler$1$1$invokeSuspend$$inlined$map$1$2$1 r0 = (com.android.compose.animation.scene.PredictiveBackHandlerKt$PredictiveBackHandler$1$1$invokeSuspend$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.compose.animation.scene.PredictiveBackHandlerKt$PredictiveBackHandler$1$1$invokeSuspend$$inlined$map$1$2$1 r0 = new com.android.compose.animation.scene.PredictiveBackHandlerKt$PredictiveBackHandler$1$1$invokeSuspend$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L46
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        androidx.activity.BackEventCompat r5 = (androidx.activity.BackEventCompat) r5
                        float r5 = r5.progress
                        java.lang.Float r6 = new java.lang.Float
                        r6.<init>(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r6, r0)
                        if (r4 != r1) goto L46
                        return r1
                    L46:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.compose.animation.scene.PredictiveBackHandlerKt$PredictiveBackHandler$1$1$invokeSuspend$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = Flow.this.collect(new AnonymousClass2(flowCollector), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        };
        SnapSpec snap$default = AnimationSpecKt.snap$default();
        this.label = 2;
        return SeekKt.animateProgress(mutableSceneTransitionLayoutStateImpl, createSwipeAnimation, r5, snap$default, this) == coroutineSingletons ? coroutineSingletons : unit;
    }
}
