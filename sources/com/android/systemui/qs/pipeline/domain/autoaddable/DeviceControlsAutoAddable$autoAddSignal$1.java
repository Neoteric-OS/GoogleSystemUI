package com.android.systemui.qs.pipeline.domain.autoaddable;

import com.android.systemui.controls.dagger.ControlsComponent;
import com.android.systemui.qs.pipeline.domain.model.AutoAddSignal;
import com.android.systemui.statusbar.policy.DeviceControlsControllerImpl;
import com.android.systemui.statusbar.policy.DeviceControlsControllerImpl$setCallback$1;
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
final class DeviceControlsAutoAddable$autoAddSignal$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ DeviceControlsAutoAddable this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeviceControlsAutoAddable$autoAddSignal$1(DeviceControlsAutoAddable deviceControlsAutoAddable, Continuation continuation) {
        super(2, continuation);
        this.this$0 = deviceControlsAutoAddable;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        DeviceControlsAutoAddable$autoAddSignal$1 deviceControlsAutoAddable$autoAddSignal$1 = new DeviceControlsAutoAddable$autoAddSignal$1(this.this$0, continuation);
        deviceControlsAutoAddable$autoAddSignal$1.L$0 = obj;
        return deviceControlsAutoAddable$autoAddSignal$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((DeviceControlsAutoAddable$autoAddSignal$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ProducerScope producerScope = (ProducerScope) this.L$0;
            DeviceControlsAutoAddable deviceControlsAutoAddable = this.this$0;
            DeviceControlsAutoAddable$autoAddSignal$1$callback$1 deviceControlsAutoAddable$autoAddSignal$1$callback$1 = new DeviceControlsAutoAddable$autoAddSignal$1$callback$1(deviceControlsAutoAddable, producerScope);
            DeviceControlsControllerImpl deviceControlsControllerImpl = deviceControlsAutoAddable.deviceControlsController;
            ControlsComponent controlsComponent = deviceControlsControllerImpl.controlsComponent;
            if (controlsComponent.featureEnabled) {
                deviceControlsControllerImpl.removeCallback();
                deviceControlsControllerImpl.callback = deviceControlsAutoAddable$autoAddSignal$1$callback$1;
                if (deviceControlsControllerImpl.secureSettings.getInt(1, "controls_enabled") == 0) {
                    deviceControlsControllerImpl.fireControlsUpdate();
                } else {
                    controlsComponent.controlsController.ifPresent(new DeviceControlsControllerImpl$setCallback$1(deviceControlsControllerImpl, 1));
                    controlsComponent.controlsListingController.ifPresent(new DeviceControlsControllerImpl$setCallback$1(deviceControlsControllerImpl, 0));
                }
            } else {
                ((ProducerCoroutine) producerScope).mo1790trySendJP2dKIU(new AutoAddSignal.Remove(deviceControlsAutoAddable.spec));
            }
            final DeviceControlsAutoAddable deviceControlsAutoAddable2 = this.this$0;
            Function0 function0 = new Function0() { // from class: com.android.systemui.qs.pipeline.domain.autoaddable.DeviceControlsAutoAddable$autoAddSignal$1.1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    DeviceControlsAutoAddable.this.deviceControlsController.removeCallback();
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
