package com.android.systemui.biometrics.data.repository;

import android.hardware.fingerprint.FingerprintManager;
import android.hardware.fingerprint.IFingerprintAuthenticatorsRegisteredCallback;
import android.util.Log;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class FingerprintPropertyRepositoryImpl$props$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ FingerprintPropertyRepositoryImpl this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.biometrics.data.repository.FingerprintPropertyRepositoryImpl$props$1$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ FingerprintPropertyRepositoryImpl$props$1$callback$1 $callback;
        int label;
        final /* synthetic */ FingerprintPropertyRepositoryImpl this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(FingerprintPropertyRepositoryImpl fingerprintPropertyRepositoryImpl, FingerprintPropertyRepositoryImpl$props$1$callback$1 fingerprintPropertyRepositoryImpl$props$1$callback$1, Continuation continuation) {
            super(2, continuation);
            this.this$0 = fingerprintPropertyRepositoryImpl;
            this.$callback = fingerprintPropertyRepositoryImpl$props$1$callback$1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.this$0, this.$callback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            FingerprintManager fingerprintManager = this.this$0.fingerprintManager;
            if (fingerprintManager == null) {
                return null;
            }
            fingerprintManager.addAuthenticatorsRegisteredCallback(this.$callback);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FingerprintPropertyRepositoryImpl$props$1(FingerprintPropertyRepositoryImpl fingerprintPropertyRepositoryImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = fingerprintPropertyRepositoryImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        FingerprintPropertyRepositoryImpl$props$1 fingerprintPropertyRepositoryImpl$props$1 = new FingerprintPropertyRepositoryImpl$props$1(this.this$0, continuation);
        fingerprintPropertyRepositoryImpl$props$1.L$0 = obj;
        return fingerprintPropertyRepositoryImpl$props$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((FingerprintPropertyRepositoryImpl$props$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference failed for: r9v2, types: [com.android.systemui.biometrics.data.repository.FingerprintPropertyRepositoryImpl$props$1$callback$1] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        final ProducerScope producerScope;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            producerScope = (ProducerScope) this.L$0;
            ?? r9 = new IFingerprintAuthenticatorsRegisteredCallback.Stub() { // from class: com.android.systemui.biometrics.data.repository.FingerprintPropertyRepositoryImpl$props$1$callback$1
                public final void onAllAuthenticatorsRegistered(List list) {
                    if (list.isEmpty()) {
                        ProducerScope producerScope2 = ProducerScope.this;
                        Object mo1790trySendJP2dKIU = ((ProducerCoroutine) producerScope2)._channel.mo1790trySendJP2dKIU(FingerprintPropertyRepositoryImpl.DEFAULT_PROPS);
                        if (mo1790trySendJP2dKIU instanceof ChannelResult.Failed) {
                            Log.e("FingerprintPropertyRepositoryImpl", "Failed to send no registered sensors, use default props - downstream canceled or failed.", ChannelResult.m1791exceptionOrNullimpl(mo1790trySendJP2dKIU));
                            return;
                        }
                        return;
                    }
                    ProducerScope producerScope3 = ProducerScope.this;
                    Object mo1790trySendJP2dKIU2 = ((ProducerCoroutine) producerScope3)._channel.mo1790trySendJP2dKIU(list.get(0));
                    if (mo1790trySendJP2dKIU2 instanceof ChannelResult.Failed) {
                        Log.e("FingerprintPropertyRepositoryImpl", "Failed to send update properties on authenticators registered - downstream canceled or failed.", ChannelResult.m1791exceptionOrNullimpl(mo1790trySendJP2dKIU2));
                    }
                }
            };
            FingerprintPropertyRepositoryImpl fingerprintPropertyRepositoryImpl = this.this$0;
            CoroutineDispatcher coroutineDispatcher = fingerprintPropertyRepositoryImpl.backgroundDispatcher;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(fingerprintPropertyRepositoryImpl, r9, null);
            this.L$0 = producerScope;
            this.label = 1;
            if (BuildersKt.withContext(coroutineDispatcher, anonymousClass1, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
            producerScope = (ProducerScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        AnonymousClass2 anonymousClass2 = new Function0() { // from class: com.android.systemui.biometrics.data.repository.FingerprintPropertyRepositoryImpl$props$1.2
            @Override // kotlin.jvm.functions.Function0
            public final /* bridge */ /* synthetic */ Object invoke() {
                return Unit.INSTANCE;
            }
        };
        this.L$0 = null;
        this.label = 2;
        if (ProduceKt.awaitClose(producerScope, anonymousClass2, this) == coroutineSingletons) {
            return coroutineSingletons;
        }
        return Unit.INSTANCE;
    }
}
