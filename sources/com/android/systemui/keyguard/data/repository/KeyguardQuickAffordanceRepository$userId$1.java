package com.android.systemui.keyguard.data.repository;

import android.content.Context;
import com.android.systemui.biometrics.data.repository.FacePropertyRepositoryImpl$cameraInfo$1$callback$1$$ExternalSyntheticOutline0;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import java.util.concurrent.Executor;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class KeyguardQuickAffordanceRepository$userId$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ KeyguardQuickAffordanceRepository this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.data.repository.KeyguardQuickAffordanceRepository$userId$1$1, reason: invalid class name */
    public final class AnonymousClass1 implements Executor {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        @Override // java.util.concurrent.Executor
        public final void execute(Runnable runnable) {
            runnable.run();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KeyguardQuickAffordanceRepository$userId$1(KeyguardQuickAffordanceRepository keyguardQuickAffordanceRepository, Continuation continuation) {
        super(2, continuation);
        this.this$0 = keyguardQuickAffordanceRepository;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        KeyguardQuickAffordanceRepository$userId$1 keyguardQuickAffordanceRepository$userId$1 = new KeyguardQuickAffordanceRepository$userId$1(this.this$0, continuation);
        keyguardQuickAffordanceRepository$userId$1.L$0 = obj;
        return keyguardQuickAffordanceRepository$userId$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((KeyguardQuickAffordanceRepository$userId$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.keyguard.data.repository.KeyguardQuickAffordanceRepository$userId$1$callback$1, com.android.systemui.settings.UserTracker$Callback] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            final ?? r1 = new UserTracker.Callback() { // from class: com.android.systemui.keyguard.data.repository.KeyguardQuickAffordanceRepository$userId$1$callback$1
                @Override // com.android.systemui.settings.UserTracker.Callback
                public final void onUserChanged(int i2, Context context) {
                    Object mo1790trySendJP2dKIU = ((ProducerCoroutine) ProducerScope.this)._channel.mo1790trySendJP2dKIU(Integer.valueOf(i2));
                    if (mo1790trySendJP2dKIU instanceof ChannelResult.Failed) {
                        FacePropertyRepositoryImpl$cameraInfo$1$callback$1$$ExternalSyntheticOutline0.m("Failed to send ", "updated state", " - downstream canceled or failed.", "KeyguardQuickAffordanceRepository", ChannelResult.m1791exceptionOrNullimpl(mo1790trySendJP2dKIU));
                    }
                }
            };
            ((UserTrackerImpl) this.this$0.userTracker).addCallback(r1, AnonymousClass1.INSTANCE);
            Object mo1790trySendJP2dKIU = ((ProducerCoroutine) producerScope)._channel.mo1790trySendJP2dKIU(new Integer(((UserTrackerImpl) this.this$0.userTracker).getUserId()));
            if (mo1790trySendJP2dKIU instanceof ChannelResult.Failed) {
                FacePropertyRepositoryImpl$cameraInfo$1$callback$1$$ExternalSyntheticOutline0.m("Failed to send ", "updated state", " - downstream canceled or failed.", "KeyguardQuickAffordanceRepository", ChannelResult.m1791exceptionOrNullimpl(mo1790trySendJP2dKIU));
            }
            final KeyguardQuickAffordanceRepository keyguardQuickAffordanceRepository = this.this$0;
            Function0 function0 = new Function0() { // from class: com.android.systemui.keyguard.data.repository.KeyguardQuickAffordanceRepository$userId$1.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    ((UserTrackerImpl) KeyguardQuickAffordanceRepository.this.userTracker).removeCallback(r1);
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
