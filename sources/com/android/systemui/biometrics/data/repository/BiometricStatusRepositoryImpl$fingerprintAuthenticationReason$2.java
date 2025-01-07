package com.android.systemui.biometrics.data.repository;

import android.util.Log;
import com.android.systemui.biometrics.shared.model.AuthenticationReason;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class BiometricStatusRepositoryImpl$fingerprintAuthenticationReason$2 extends SuspendLambda implements Function2 {
    /* synthetic */ Object L$0;
    int label;

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        BiometricStatusRepositoryImpl$fingerprintAuthenticationReason$2 biometricStatusRepositoryImpl$fingerprintAuthenticationReason$2 = new BiometricStatusRepositoryImpl$fingerprintAuthenticationReason$2(2, continuation);
        biometricStatusRepositoryImpl$fingerprintAuthenticationReason$2.L$0 = obj;
        return biometricStatusRepositoryImpl$fingerprintAuthenticationReason$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        BiometricStatusRepositoryImpl$fingerprintAuthenticationReason$2 biometricStatusRepositoryImpl$fingerprintAuthenticationReason$2 = (BiometricStatusRepositoryImpl$fingerprintAuthenticationReason$2) create((AuthenticationReason) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        biometricStatusRepositoryImpl$fingerprintAuthenticationReason$2.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        Log.d("BiometricStatusRepositoryImpl", "fingerprintAuthenticationReason updated: " + ((AuthenticationReason) this.L$0));
        return Unit.INSTANCE;
    }
}
