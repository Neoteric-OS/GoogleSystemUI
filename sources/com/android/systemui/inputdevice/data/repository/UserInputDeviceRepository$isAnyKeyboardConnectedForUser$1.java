package com.android.systemui.inputdevice.data.repository;

import com.android.systemui.inputdevice.data.model.UserDeviceConnectionStatus;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class UserInputDeviceRepository$isAnyKeyboardConnectedForUser$1 extends SuspendLambda implements Function3 {
    /* synthetic */ int I$0;
    /* synthetic */ boolean Z$0;
    int label;

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        int intValue = ((Number) obj2).intValue();
        UserInputDeviceRepository$isAnyKeyboardConnectedForUser$1 userInputDeviceRepository$isAnyKeyboardConnectedForUser$1 = new UserInputDeviceRepository$isAnyKeyboardConnectedForUser$1(3, (Continuation) obj3);
        userInputDeviceRepository$isAnyKeyboardConnectedForUser$1.Z$0 = booleanValue;
        userInputDeviceRepository$isAnyKeyboardConnectedForUser$1.I$0 = intValue;
        return userInputDeviceRepository$isAnyKeyboardConnectedForUser$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        return new UserDeviceConnectionStatus(this.I$0, this.Z$0);
    }
}
