package com.android.systemui.unfold;

import android.view.SurfaceControl;
import android.view.WindowlessWindowManager;
import android.window.InputTransferToken;
import java.util.function.Consumer;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.ExecutorsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FullscreenLightRevealAnimationController$init$1 implements Consumer {
    public final /* synthetic */ FullscreenLightRevealAnimationController this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.unfold.FullscreenLightRevealAnimationController$init$1$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ SurfaceControl.Builder $builder;
        int label;
        final /* synthetic */ FullscreenLightRevealAnimationController this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(SurfaceControl.Builder builder, FullscreenLightRevealAnimationController fullscreenLightRevealAnimationController, Continuation continuation) {
            super(2, continuation);
            this.$builder = builder;
            this.this$0 = fullscreenLightRevealAnimationController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.$builder, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            AnonymousClass1 anonymousClass1 = (AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2);
            Unit unit = Unit.INSTANCE;
            anonymousClass1.invokeSuspend(unit);
            return unit;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            SurfaceControl build = this.$builder.build();
            new SurfaceControl.Transaction().setLayer(build, 2147483646).show(build).apply();
            this.this$0.wwm = new WindowlessWindowManager(this.this$0.context.getResources().getConfiguration(), build, (InputTransferToken) null);
            return Unit.INSTANCE;
        }
    }

    public FullscreenLightRevealAnimationController$init$1(FullscreenLightRevealAnimationController fullscreenLightRevealAnimationController) {
        this.this$0 = fullscreenLightRevealAnimationController;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        FullscreenLightRevealAnimationController fullscreenLightRevealAnimationController = this.this$0;
        BuildersKt.launch$default(fullscreenLightRevealAnimationController.applicationScope, ExecutorsKt.from(fullscreenLightRevealAnimationController.executor), null, new AnonymousClass1((SurfaceControl.Builder) obj, this.this$0, null), 2);
    }
}
