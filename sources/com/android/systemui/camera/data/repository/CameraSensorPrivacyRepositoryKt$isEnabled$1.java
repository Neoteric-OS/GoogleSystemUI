package com.android.systemui.camera.data.repository;

import android.hardware.SensorPrivacyManager;
import android.os.UserHandle;
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
/* loaded from: classes.dex */
final class CameraSensorPrivacyRepositoryKt$isEnabled$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ SensorPrivacyManager $this_isEnabled;
    final /* synthetic */ UserHandle $userHandle;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CameraSensorPrivacyRepositoryKt$isEnabled$1(SensorPrivacyManager sensorPrivacyManager, UserHandle userHandle, Continuation continuation) {
        super(2, continuation);
        this.$this_isEnabled = sensorPrivacyManager;
        this.$userHandle = userHandle;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        CameraSensorPrivacyRepositoryKt$isEnabled$1 cameraSensorPrivacyRepositoryKt$isEnabled$1 = new CameraSensorPrivacyRepositoryKt$isEnabled$1(this.$this_isEnabled, this.$userHandle, continuation);
        cameraSensorPrivacyRepositoryKt$isEnabled$1.L$0 = obj;
        return cameraSensorPrivacyRepositoryKt$isEnabled$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((CameraSensorPrivacyRepositoryKt$isEnabled$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [android.hardware.SensorPrivacyManager$OnSensorPrivacyChangedListener, com.android.systemui.camera.data.repository.CameraSensorPrivacyRepositoryKt$isEnabled$1$privacyCallback$1] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            final ?? r1 = new SensorPrivacyManager.OnSensorPrivacyChangedListener() { // from class: com.android.systemui.camera.data.repository.CameraSensorPrivacyRepositoryKt$isEnabled$1$privacyCallback$1
                public final void onSensorPrivacyChanged(int i2, boolean z) {
                    if (i2 == 2) {
                        ((ProducerCoroutine) ProducerScope.this).mo1790trySendJP2dKIU(Boolean.valueOf(z));
                    }
                }
            };
            this.$this_isEnabled.addSensorPrivacyListener(2, this.$userHandle.getIdentifier(), r1);
            final SensorPrivacyManager sensorPrivacyManager = this.$this_isEnabled;
            Function0 function0 = new Function0() { // from class: com.android.systemui.camera.data.repository.CameraSensorPrivacyRepositoryKt$isEnabled$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    sensorPrivacyManager.removeSensorPrivacyListener(r1);
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
