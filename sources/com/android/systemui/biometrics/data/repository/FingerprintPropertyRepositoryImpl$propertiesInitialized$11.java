package com.android.systemui.biometrics.data.repository;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function6;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class FingerprintPropertyRepositoryImpl$propertiesInitialized$11 extends SuspendLambda implements Function6 {
    /* synthetic */ boolean Z$0;
    int label;

    public FingerprintPropertyRepositoryImpl$propertiesInitialized$11(Continuation continuation) {
        super(6, continuation);
    }

    @Override // kotlin.jvm.functions.Function6
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        FingerprintPropertyRepositoryImpl$propertiesInitialized$11 fingerprintPropertyRepositoryImpl$propertiesInitialized$11 = new FingerprintPropertyRepositoryImpl$propertiesInitialized$11((Continuation) obj6);
        fingerprintPropertyRepositoryImpl$propertiesInitialized$11.Z$0 = booleanValue;
        return fingerprintPropertyRepositoryImpl$propertiesInitialized$11.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        return Boolean.valueOf(this.Z$0);
    }
}
