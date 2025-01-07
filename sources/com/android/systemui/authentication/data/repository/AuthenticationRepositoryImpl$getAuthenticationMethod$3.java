package com.android.systemui.authentication.data.repository;

import com.android.keyguard.KeyguardSecurityModel;
import com.android.systemui.authentication.shared.model.AuthenticationMethodModel;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class AuthenticationRepositoryImpl$getAuthenticationMethod$3 extends SuspendLambda implements Function2 {
    final /* synthetic */ int $userId;
    int label;
    final /* synthetic */ AuthenticationRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AuthenticationRepositoryImpl$getAuthenticationMethod$3(AuthenticationRepositoryImpl authenticationRepositoryImpl, int i, Continuation continuation) {
        super(2, continuation);
        this.this$0 = authenticationRepositoryImpl;
        this.$userId = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new AuthenticationRepositoryImpl$getAuthenticationMethod$3(this.this$0, this.$userId, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((AuthenticationRepositoryImpl$getAuthenticationMethod$3) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        switch (((KeyguardSecurityModel.SecurityMode) this.this$0.getSecurityMode.apply(new Integer(this.$userId))).ordinal()) {
            case 0:
                throw new IllegalStateException("Invalid security mode!");
            case 1:
                return AuthenticationMethodModel.None.INSTANCE;
            case 2:
                return AuthenticationMethodModel.Pattern.INSTANCE;
            case 3:
                return AuthenticationMethodModel.Password.INSTANCE;
            case 4:
                return AuthenticationMethodModel.Pin.INSTANCE;
            case 5:
            case 6:
                return AuthenticationMethodModel.Sim.INSTANCE;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
