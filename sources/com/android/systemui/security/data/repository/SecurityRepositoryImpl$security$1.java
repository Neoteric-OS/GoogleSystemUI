package com.android.systemui.security.data.repository;

import com.android.systemui.statusbar.policy.SecurityController;
import com.android.systemui.statusbar.policy.SecurityControllerImpl;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class SecurityRepositoryImpl$security$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ SecurityRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SecurityRepositoryImpl$security$1(SecurityRepositoryImpl securityRepositoryImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = securityRepositoryImpl;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /* JADX WARN: Type inference failed for: r5v5, types: [kotlinx.coroutines.channels.SendChannel] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object invokeSuspend$updateState(kotlinx.coroutines.channels.ProducerScope r5, com.android.systemui.security.data.repository.SecurityRepositoryImpl r6, kotlin.coroutines.jvm.internal.ContinuationImpl r7) {
        /*
            boolean r0 = r7 instanceof com.android.systemui.security.data.repository.SecurityRepositoryImpl$security$1$updateState$1
            if (r0 == 0) goto L13
            r0 = r7
            com.android.systemui.security.data.repository.SecurityRepositoryImpl$security$1$updateState$1 r0 = (com.android.systemui.security.data.repository.SecurityRepositoryImpl$security$1$updateState$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.security.data.repository.SecurityRepositoryImpl$security$1$updateState$1 r0 = new com.android.systemui.security.data.repository.SecurityRepositoryImpl$security$1$updateState$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L37
            if (r2 != r3) goto L2f
            java.lang.Object r5 = r0.L$1
            kotlinx.coroutines.channels.SendChannel r5 = (kotlinx.coroutines.channels.SendChannel) r5
            java.lang.Object r6 = r0.L$0
            com.android.systemui.common.coroutine.ChannelExt r6 = (com.android.systemui.common.coroutine.ChannelExt) r6
            kotlin.ResultKt.throwOnFailure(r7)
            goto L50
        L2f:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L37:
            kotlin.ResultKt.throwOnFailure(r7)
            com.android.systemui.common.coroutine.ChannelExt r7 = com.android.systemui.common.coroutine.ChannelExt.INSTANCE
            com.android.systemui.statusbar.policy.SecurityController r2 = r6.securityController
            r0.L$0 = r7
            r0.L$1 = r5
            r0.label = r3
            kotlinx.coroutines.CoroutineDispatcher r6 = r6.bgDispatcher
            java.lang.Object r6 = com.android.systemui.security.data.model.SecurityModel.Companion.create(r2, r6, r0)
            if (r6 != r1) goto L4d
            return r1
        L4d:
            r4 = r7
            r7 = r6
            r6 = r4
        L50:
            java.lang.String r0 = "SecurityRepositoryImpl"
            com.android.systemui.common.coroutine.ChannelExt.trySendWithFailureLogging$default(r6, r5, r7, r0)
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.security.data.repository.SecurityRepositoryImpl$security$1.invokeSuspend$updateState(kotlinx.coroutines.channels.ProducerScope, com.android.systemui.security.data.repository.SecurityRepositoryImpl, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        SecurityRepositoryImpl$security$1 securityRepositoryImpl$security$1 = new SecurityRepositoryImpl$security$1(this.this$0, continuation);
        securityRepositoryImpl$security$1.L$0 = obj;
        return securityRepositoryImpl$security$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((SecurityRepositoryImpl$security$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        final SecurityController.SecurityControllerCallback securityControllerCallback;
        ProducerScope producerScope;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope2 = (ProducerScope) this.L$0;
            final SecurityRepositoryImpl securityRepositoryImpl = this.this$0;
            securityControllerCallback = new SecurityController.SecurityControllerCallback() { // from class: com.android.systemui.security.data.repository.SecurityRepositoryImpl$security$1$callback$1

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.security.data.repository.SecurityRepositoryImpl$security$1$callback$1$1, reason: invalid class name */
                final class AnonymousClass1 extends SuspendLambda implements Function2 {
                    final /* synthetic */ ProducerScope $$this$conflatedCallbackFlow;
                    int label;
                    final /* synthetic */ SecurityRepositoryImpl this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public AnonymousClass1(ProducerScope producerScope, SecurityRepositoryImpl securityRepositoryImpl, Continuation continuation) {
                        super(2, continuation);
                        this.$$this$conflatedCallbackFlow = producerScope;
                        this.this$0 = securityRepositoryImpl;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation create(Object obj, Continuation continuation) {
                        return new AnonymousClass1(this.$$this$conflatedCallbackFlow, this.this$0, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                        int i = this.label;
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            ProducerScope producerScope = this.$$this$conflatedCallbackFlow;
                            SecurityRepositoryImpl securityRepositoryImpl = this.this$0;
                            this.label = 1;
                            if (SecurityRepositoryImpl$security$1.invokeSuspend$updateState(producerScope, securityRepositoryImpl, this) == coroutineSingletons) {
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

                @Override // com.android.systemui.statusbar.policy.SecurityController.SecurityControllerCallback
                public final void onStateChanged() {
                    ProducerScope producerScope3 = ProducerScope.this;
                    BuildersKt.launch$default(producerScope3, null, null, new AnonymousClass1(producerScope3, securityRepositoryImpl, null), 3);
                }
            };
            ((SecurityControllerImpl) securityRepositoryImpl.securityController).addCallback(securityControllerCallback);
            SecurityRepositoryImpl securityRepositoryImpl2 = this.this$0;
            this.L$0 = producerScope2;
            this.L$1 = securityControllerCallback;
            this.label = 1;
            if (invokeSuspend$updateState(producerScope2, securityRepositoryImpl2, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
            producerScope = producerScope2;
        } else {
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
            securityControllerCallback = (SecurityController.SecurityControllerCallback) this.L$1;
            producerScope = (ProducerScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        final SecurityRepositoryImpl securityRepositoryImpl3 = this.this$0;
        Function0 function0 = new Function0() { // from class: com.android.systemui.security.data.repository.SecurityRepositoryImpl$security$1.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                ((SecurityControllerImpl) SecurityRepositoryImpl.this.securityController).removeCallback(securityControllerCallback);
                return Unit.INSTANCE;
            }
        };
        this.L$0 = null;
        this.L$1 = null;
        this.label = 2;
        if (ProduceKt.awaitClose(producerScope, function0, this) == coroutineSingletons) {
            return coroutineSingletons;
        }
        return Unit.INSTANCE;
    }
}
