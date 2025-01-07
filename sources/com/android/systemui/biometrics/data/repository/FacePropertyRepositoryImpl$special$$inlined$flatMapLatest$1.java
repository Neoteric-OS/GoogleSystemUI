package com.android.systemui.biometrics.data.repository;

import com.android.systemui.common.ui.data.repository.ConfigurationRepository;
import com.android.systemui.common.ui.data.repository.ConfigurationRepositoryImpl;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FacePropertyRepositoryImpl$special$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ ConfigurationRepository $configurationRepository$inlined;
    final /* synthetic */ DisplayStateRepository $displayStateRepository$inlined;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ FacePropertyRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FacePropertyRepositoryImpl$special$$inlined$flatMapLatest$1(Continuation continuation, FacePropertyRepositoryImpl facePropertyRepositoryImpl, DisplayStateRepository displayStateRepository, ConfigurationRepository configurationRepository) {
        super(3, continuation);
        this.this$0 = facePropertyRepositoryImpl;
        this.$displayStateRepository$inlined = displayStateRepository;
        this.$configurationRepository$inlined = configurationRepository;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        FacePropertyRepositoryImpl$special$$inlined$flatMapLatest$1 facePropertyRepositoryImpl$special$$inlined$flatMapLatest$1 = new FacePropertyRepositoryImpl$special$$inlined$flatMapLatest$1((Continuation) obj3, this.this$0, this.$displayStateRepository$inlined, this.$configurationRepository$inlined);
        facePropertyRepositoryImpl$special$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        facePropertyRepositoryImpl$special$$inlined$flatMapLatest$1.L$1 = obj2;
        return facePropertyRepositoryImpl$special$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Flow combine;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            if (((FaceSensorInfo) this.L$1) == null) {
                combine = new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(null);
            } else {
                FacePropertyRepositoryImpl facePropertyRepositoryImpl = this.this$0;
                ReadonlyStateFlow readonlyStateFlow = facePropertyRepositoryImpl.defaultSensorLocation;
                DisplayStateRepositoryImpl displayStateRepositoryImpl = (DisplayStateRepositoryImpl) this.$displayStateRepository$inlined;
                combine = FlowKt.combine(readonlyStateFlow, displayStateRepositoryImpl.currentRotation, displayStateRepositoryImpl.currentDisplaySize, ((ConfigurationRepositoryImpl) this.$configurationRepository$inlined).scaleForResolution, new FacePropertyRepositoryImpl$sensorLocation$1$1(facePropertyRepositoryImpl, null));
            }
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, combine, this) == coroutineSingletons) {
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
