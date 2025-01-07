package com.android.settingslib.volume.data.repository;

import com.android.settingslib.media.LocalMediaManager;
import com.android.settingslib.media.MediaDevice;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class LocalMediaRepositoryImpl$mediaDevicesUpdates$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ LocalMediaRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LocalMediaRepositoryImpl$mediaDevicesUpdates$1(LocalMediaRepositoryImpl localMediaRepositoryImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = localMediaRepositoryImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        LocalMediaRepositoryImpl$mediaDevicesUpdates$1 localMediaRepositoryImpl$mediaDevicesUpdates$1 = new LocalMediaRepositoryImpl$mediaDevicesUpdates$1(this.this$0, continuation);
        localMediaRepositoryImpl$mediaDevicesUpdates$1.L$0 = obj;
        return localMediaRepositoryImpl$mediaDevicesUpdates$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((LocalMediaRepositoryImpl$mediaDevicesUpdates$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.settingslib.media.LocalMediaManager$DeviceCallback, com.android.settingslib.volume.data.repository.LocalMediaRepositoryImpl$mediaDevicesUpdates$1$callback$1] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            final ?? r1 = new LocalMediaManager.DeviceCallback() { // from class: com.android.settingslib.volume.data.repository.LocalMediaRepositoryImpl$mediaDevicesUpdates$1$callback$1
                @Override // com.android.settingslib.media.LocalMediaManager.DeviceCallback
                public final void onDeviceAttributesChanged() {
                    ((ProducerCoroutine) ProducerScope.this).mo1790trySendJP2dKIU(LocalMediaRepositoryImpl$DevicesUpdate$DeviceAttributesChanged.INSTANCE);
                }

                @Override // com.android.settingslib.media.LocalMediaManager.DeviceCallback
                public final void onDeviceListUpdate(List list) {
                    ((ProducerCoroutine) ProducerScope.this).mo1790trySendJP2dKIU(new Object(list) { // from class: com.android.settingslib.volume.data.repository.LocalMediaRepositoryImpl$DevicesUpdate$DeviceListUpdate
                        public final List newDevices;

                        {
                            this.newDevices = list;
                        }

                        public final boolean equals(Object obj2) {
                            if (this == obj2) {
                                return true;
                            }
                            return (obj2 instanceof LocalMediaRepositoryImpl$DevicesUpdate$DeviceListUpdate) && Intrinsics.areEqual(this.newDevices, ((LocalMediaRepositoryImpl$DevicesUpdate$DeviceListUpdate) obj2).newDevices);
                        }

                        public final int hashCode() {
                            List list2 = this.newDevices;
                            if (list2 == null) {
                                return 0;
                            }
                            return list2.hashCode();
                        }

                        public final String toString() {
                            return "DeviceListUpdate(newDevices=" + this.newDevices + ")";
                        }
                    });
                }

                @Override // com.android.settingslib.media.LocalMediaManager.DeviceCallback
                public final void onSelectedDeviceStateChanged(MediaDevice mediaDevice) {
                    ((ProducerCoroutine) ProducerScope.this).mo1790trySendJP2dKIU(LocalMediaRepositoryImpl$DevicesUpdate$SelectedDeviceStateChanged.INSTANCE);
                }
            };
            this.this$0.localMediaManager.registerCallback(r1);
            final LocalMediaRepositoryImpl localMediaRepositoryImpl = this.this$0;
            Function0 function0 = new Function0() { // from class: com.android.settingslib.volume.data.repository.LocalMediaRepositoryImpl$mediaDevicesUpdates$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    LocalMediaRepositoryImpl.this.localMediaManager.unregisterCallback(r1);
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
