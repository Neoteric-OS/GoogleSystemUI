package com.android.systemui.shade;

import androidx.compose.foundation.text.input.internal.AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0;
import com.android.systemui.shade.ShadeController;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class ShadeControllerSceneImpl$setVisibilityListener$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ ShadeController.ShadeVisibilityListener $listener;
    int label;
    final /* synthetic */ ShadeControllerSceneImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ShadeControllerSceneImpl$setVisibilityListener$1(ShadeControllerSceneImpl shadeControllerSceneImpl, ShadeController.ShadeVisibilityListener shadeVisibilityListener, Continuation continuation) {
        super(2, continuation);
        this.this$0 = shadeControllerSceneImpl;
        this.$listener = shadeVisibilityListener;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new ShadeControllerSceneImpl$setVisibilityListener$1(this.this$0, this.$listener, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        ((ShadeControllerSceneImpl$setVisibilityListener$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
        final ShadeControllerSceneImpl shadeControllerSceneImpl = this.this$0;
        ReadonlyStateFlow readonlyStateFlow = shadeControllerSceneImpl.sceneInteractor.isVisible;
        final ShadeController.ShadeVisibilityListener shadeVisibilityListener = this.$listener;
        FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.shade.ShadeControllerSceneImpl$setVisibilityListener$1.1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.shade.ShadeControllerSceneImpl$setVisibilityListener$1$1$1, reason: invalid class name and collision with other inner class name */
            final class C01871 extends SuspendLambda implements Function2 {
                final /* synthetic */ boolean $isVisible;
                final /* synthetic */ ShadeController.ShadeVisibilityListener $listener;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C01871(ShadeController.ShadeVisibilityListener shadeVisibilityListener, boolean z, Continuation continuation) {
                    super(2, continuation);
                    this.$listener = shadeVisibilityListener;
                    this.$isVisible = z;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    return new C01871(this.$listener, this.$isVisible, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    C01871 c01871 = (C01871) create((CoroutineScope) obj, (Continuation) obj2);
                    Unit unit = Unit.INSTANCE;
                    c01871.invokeSuspend(unit);
                    return unit;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    ((CentralSurfacesImpl.AnonymousClass4) this.$listener).expandedVisibleChanged(this.$isVisible);
                    return Unit.INSTANCE;
                }
            }

            @Override // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(Object obj2, Continuation continuation) {
                Object withContext = BuildersKt.withContext(ShadeControllerSceneImpl.this.mainDispatcher, new C01871(shadeVisibilityListener, ((Boolean) obj2).booleanValue(), null), continuation);
                return withContext == CoroutineSingletons.COROUTINE_SUSPENDED ? withContext : Unit.INSTANCE;
            }
        };
        this.label = 1;
        ((StateFlowImpl) readonlyStateFlow.$$delegate_0).collect(flowCollector, this);
        return coroutineSingletons;
    }
}
