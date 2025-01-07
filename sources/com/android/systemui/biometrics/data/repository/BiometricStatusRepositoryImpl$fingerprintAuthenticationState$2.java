package com.android.systemui.biometrics.data.repository;

import android.util.Log;
import com.android.systemui.biometrics.shared.model.AuthenticationState;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class BiometricStatusRepositoryImpl$fingerprintAuthenticationState$2 extends SuspendLambda implements Function2 {
    /* synthetic */ Object L$0;
    int label;

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        BiometricStatusRepositoryImpl$fingerprintAuthenticationState$2 biometricStatusRepositoryImpl$fingerprintAuthenticationState$2 = new BiometricStatusRepositoryImpl$fingerprintAuthenticationState$2(2, continuation);
        biometricStatusRepositoryImpl$fingerprintAuthenticationState$2.L$0 = obj;
        return biometricStatusRepositoryImpl$fingerprintAuthenticationState$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        BiometricStatusRepositoryImpl$fingerprintAuthenticationState$2 biometricStatusRepositoryImpl$fingerprintAuthenticationState$2 = (BiometricStatusRepositoryImpl$fingerprintAuthenticationState$2) create((AuthenticationState) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        biometricStatusRepositoryImpl$fingerprintAuthenticationState$2.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        Log.d("BiometricStatusRepositoryImpl", "fingerprintAuthenticationState updated: " + ((AuthenticationState) this.L$0));
        return Unit.INSTANCE;
    }
}
