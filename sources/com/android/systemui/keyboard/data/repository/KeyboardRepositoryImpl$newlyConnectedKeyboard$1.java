package com.android.systemui.keyboard.data.repository;

import com.android.systemui.inputdevice.data.repository.InputDeviceRepository;
import java.util.ArrayList;
import java.util.Collection;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.EmptyFlow;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$3;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class KeyboardRepositoryImpl$newlyConnectedKeyboard$1 extends SuspendLambda implements Function2 {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ KeyboardRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KeyboardRepositoryImpl$newlyConnectedKeyboard$1(KeyboardRepositoryImpl keyboardRepositoryImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = keyboardRepositoryImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        KeyboardRepositoryImpl$newlyConnectedKeyboard$1 keyboardRepositoryImpl$newlyConnectedKeyboard$1 = new KeyboardRepositoryImpl$newlyConnectedKeyboard$1(this.this$0, continuation);
        keyboardRepositoryImpl$newlyConnectedKeyboard$1.L$0 = obj;
        return keyboardRepositoryImpl$newlyConnectedKeyboard$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((KeyboardRepositoryImpl$newlyConnectedKeyboard$1) create((Pair) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        Pair pair = (Pair) this.L$0;
        Collection collection = (Collection) pair.component1();
        InputDeviceRepository.DeviceChange deviceChange = (InputDeviceRepository.DeviceChange) pair.component2();
        if (Intrinsics.areEqual(deviceChange, InputDeviceRepository.FreshStart.INSTANCE)) {
            KeyboardRepositoryImpl keyboardRepositoryImpl = this.this$0;
            ArrayList arrayList = new ArrayList();
            for (Object obj2 : collection) {
                if (KeyboardRepositoryImpl.access$isPhysicalFullKeyboard(keyboardRepositoryImpl, ((Number) obj2).intValue())) {
                    arrayList.add(obj2);
                }
            }
            return new FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$3(arrayList);
        }
        boolean z = deviceChange instanceof InputDeviceRepository.DeviceAdded;
        EmptyFlow emptyFlow = EmptyFlow.INSTANCE;
        if (z) {
            InputDeviceRepository.DeviceAdded deviceAdded = (InputDeviceRepository.DeviceAdded) deviceChange;
            if (KeyboardRepositoryImpl.access$isPhysicalFullKeyboard(this.this$0, deviceAdded.deviceId)) {
                return new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(new Integer(deviceAdded.deviceId));
            }
        } else if (!(deviceChange instanceof InputDeviceRepository.DeviceRemoved)) {
            throw new NoWhenBranchMatchedException();
        }
        return emptyFlow;
    }
}
