package com.android.systemui.qs.tiles.impl.flashlight.domain.interactor;

import com.android.systemui.qs.tiles.impl.flashlight.domain.model.FlashlightTileModel;
import com.android.systemui.statusbar.policy.FlashlightController$FlashlightListener;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class FlashlightTileDataInteractor$tileData$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ FlashlightTileDataInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlashlightTileDataInteractor$tileData$1(FlashlightTileDataInteractor flashlightTileDataInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = flashlightTileDataInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        FlashlightTileDataInteractor$tileData$1 flashlightTileDataInteractor$tileData$1 = new FlashlightTileDataInteractor$tileData$1(this.this$0, continuation);
        flashlightTileDataInteractor$tileData$1.L$0 = obj;
        return flashlightTileDataInteractor$tileData$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((FlashlightTileDataInteractor$tileData$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.qs.tiles.impl.flashlight.domain.interactor.FlashlightTileDataInteractor$tileData$1$callback$1, java.lang.Object] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            final FlashlightTileDataInteractor flashlightTileDataInteractor = this.this$0;
            final ?? r1 = new FlashlightController$FlashlightListener() { // from class: com.android.systemui.qs.tiles.impl.flashlight.domain.interactor.FlashlightTileDataInteractor$tileData$1$callback$1
                @Override // com.android.systemui.statusbar.policy.FlashlightController$FlashlightListener
                public final void onFlashlightAvailabilityChanged(boolean z) {
                    ((ProducerCoroutine) ProducerScope.this).mo1790trySendJP2dKIU(z ? new FlashlightTileModel.FlashlightAvailable(flashlightTileDataInteractor.flashlightController.isEnabled()) : FlashlightTileModel.FlashlightTemporarilyUnavailable.INSTANCE);
                }

                @Override // com.android.systemui.statusbar.policy.FlashlightController$FlashlightListener
                public final void onFlashlightChanged(boolean z) {
                    ((ProducerCoroutine) ProducerScope.this).mo1790trySendJP2dKIU(new FlashlightTileModel.FlashlightAvailable(z));
                }

                @Override // com.android.systemui.statusbar.policy.FlashlightController$FlashlightListener
                public final void onFlashlightError() {
                    ((ProducerCoroutine) ProducerScope.this).mo1790trySendJP2dKIU(new FlashlightTileModel.FlashlightAvailable(false));
                }
            };
            flashlightTileDataInteractor.flashlightController.addCallback(r1);
            final FlashlightTileDataInteractor flashlightTileDataInteractor2 = this.this$0;
            Function0 function0 = new Function0() { // from class: com.android.systemui.qs.tiles.impl.flashlight.domain.interactor.FlashlightTileDataInteractor$tileData$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    FlashlightTileDataInteractor.this.flashlightController.removeCallback(r1);
                    return Unit.INSTANCE;
                }
            };
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, function0, this) == coroutineSingletons) {
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
