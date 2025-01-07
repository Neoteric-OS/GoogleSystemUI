package com.android.systemui.keyguard.data.repository;

import com.android.systemui.biometrics.data.repository.FacePropertyRepositoryImpl$cameraInfo$1$callback$1$$ExternalSyntheticOutline0;
import com.android.systemui.keyguard.shared.model.DevicePosture;
import com.android.systemui.statusbar.policy.DevicePostureController;
import com.android.systemui.statusbar.policy.DevicePostureControllerImpl;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DevicePostureRepositoryImpl$currentDevicePosture$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ DevicePostureRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DevicePostureRepositoryImpl$currentDevicePosture$1(DevicePostureRepositoryImpl devicePostureRepositoryImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = devicePostureRepositoryImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        DevicePostureRepositoryImpl$currentDevicePosture$1 devicePostureRepositoryImpl$currentDevicePosture$1 = new DevicePostureRepositoryImpl$currentDevicePosture$1(this.this$0, continuation);
        devicePostureRepositoryImpl$currentDevicePosture$1.L$0 = obj;
        return devicePostureRepositoryImpl$currentDevicePosture$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((DevicePostureRepositoryImpl$currentDevicePosture$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [com.android.systemui.keyguard.data.repository.DevicePostureRepositoryImpl$currentDevicePosture$1$callback$1, java.lang.Object] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            final Function1 function1 = new Function1() { // from class: com.android.systemui.keyguard.data.repository.DevicePostureRepositoryImpl$currentDevicePosture$1$sendPostureUpdate$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    int intValue = ((Number) obj2).intValue();
                    DevicePosture.Companion.getClass();
                    DevicePosture posture = DevicePosture.Companion.toPosture(intValue);
                    String str = "Error sending posture update to " + posture;
                    Object mo1790trySendJP2dKIU = ((ProducerCoroutine) ProducerScope.this)._channel.mo1790trySendJP2dKIU(posture);
                    if (mo1790trySendJP2dKIU instanceof ChannelResult.Failed) {
                        FacePropertyRepositoryImpl$cameraInfo$1$callback$1$$ExternalSyntheticOutline0.m("Failed to send ", str, " - downstream canceled or failed.", "PostureRepositoryImpl", ChannelResult.m1791exceptionOrNullimpl(mo1790trySendJP2dKIU));
                    }
                    return Unit.INSTANCE;
                }
            };
            final ?? r3 = new DevicePostureController.Callback() { // from class: com.android.systemui.keyguard.data.repository.DevicePostureRepositoryImpl$currentDevicePosture$1$callback$1
                @Override // com.android.systemui.statusbar.policy.DevicePostureController.Callback
                public final void onPostureChanged(int i2) {
                    Function1.this.invoke(Integer.valueOf(i2));
                }
            };
            ((DevicePostureControllerImpl) this.this$0.postureController).addCallback(r3);
            function1.invoke(new Integer(((DevicePostureControllerImpl) this.this$0.postureController).getDevicePosture()));
            final DevicePostureRepositoryImpl devicePostureRepositoryImpl = this.this$0;
            Function0 function0 = new Function0() { // from class: com.android.systemui.keyguard.data.repository.DevicePostureRepositoryImpl$currentDevicePosture$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    ((DevicePostureControllerImpl) DevicePostureRepositoryImpl.this.postureController).removeCallback(r3);
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
