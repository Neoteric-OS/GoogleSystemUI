package com.android.systemui.keyguard;

import android.util.Log;
import android.view.WindowManagerGlobal;
import com.android.compose.animation.scene.SceneKey;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.scene.shared.model.Scenes;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class ResourceTrimmer$start$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ ResourceTrimmer this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ResourceTrimmer$start$1(ResourceTrimmer resourceTrimmer, Continuation continuation) {
        super(2, continuation);
        this.this$0 = resourceTrimmer;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new ResourceTrimmer$start$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((ResourceTrimmer$start$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            KeyguardTransitionInteractor keyguardTransitionInteractor = this.this$0.keyguardTransitionInteractor;
            SceneKey sceneKey = Scenes.Bouncer;
            Flow isFinishedIn = keyguardTransitionInteractor.isFinishedIn(KeyguardState.GONE);
            final ResourceTrimmer resourceTrimmer = this.this$0;
            FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.keyguard.ResourceTrimmer$start$1.2
                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj2, Continuation continuation) {
                    ((Boolean) obj2).booleanValue();
                    ResourceTrimmer resourceTrimmer2 = ResourceTrimmer.this;
                    resourceTrimmer2.getClass();
                    Log.d("ResourceTrimmer", "Sending TRIM_MEMORY_UI_HIDDEN.");
                    resourceTrimmer2.globalWindowManager.getClass();
                    WindowManagerGlobal.getInstance().trimMemory(20);
                    return Unit.INSTANCE;
                }
            };
            this.label = 1;
            Object collect = isFinishedIn.collect(new ResourceTrimmer$start$1$invokeSuspend$$inlined$filter$1$2(flowCollector), this);
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
