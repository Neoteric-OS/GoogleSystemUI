package com.android.systemui.statusbar.notification.collection.coordinator;

import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class OriginalUnseenKeyguardCoordinator$trackSeenNotifications$isKeyguardPresentFlow$3 extends SuspendLambda implements Function2 {
    /* synthetic */ boolean Z$0;
    int label;
    final /* synthetic */ OriginalUnseenKeyguardCoordinator this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OriginalUnseenKeyguardCoordinator$trackSeenNotifications$isKeyguardPresentFlow$3(OriginalUnseenKeyguardCoordinator originalUnseenKeyguardCoordinator, Continuation continuation) {
        super(2, continuation);
        this.this$0 = originalUnseenKeyguardCoordinator;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        OriginalUnseenKeyguardCoordinator$trackSeenNotifications$isKeyguardPresentFlow$3 originalUnseenKeyguardCoordinator$trackSeenNotifications$isKeyguardPresentFlow$3 = new OriginalUnseenKeyguardCoordinator$trackSeenNotifications$isKeyguardPresentFlow$3(this.this$0, continuation);
        originalUnseenKeyguardCoordinator$trackSeenNotifications$isKeyguardPresentFlow$3.Z$0 = ((Boolean) obj).booleanValue();
        return originalUnseenKeyguardCoordinator$trackSeenNotifications$isKeyguardPresentFlow$3;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        Boolean bool = (Boolean) obj;
        bool.booleanValue();
        OriginalUnseenKeyguardCoordinator$trackSeenNotifications$isKeyguardPresentFlow$3 originalUnseenKeyguardCoordinator$trackSeenNotifications$isKeyguardPresentFlow$3 = (OriginalUnseenKeyguardCoordinator$trackSeenNotifications$isKeyguardPresentFlow$3) create(bool, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        originalUnseenKeyguardCoordinator$trackSeenNotifications$isKeyguardPresentFlow$3.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        boolean z = this.Z$0;
        KeyguardCoordinatorLogger keyguardCoordinatorLogger = this.this$0.logger;
        keyguardCoordinatorLogger.getClass();
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardCoordinatorLogger$logTrackingUnseen$2 keyguardCoordinatorLogger$logTrackingUnseen$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.KeyguardCoordinatorLogger$logTrackingUnseen$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return (((LogMessage) obj2).getBool1() ? "Start" : "Stop").concat(" tracking unseen notifications.");
            }
        };
        LogBuffer logBuffer = keyguardCoordinatorLogger.buffer;
        LogMessage obtain = logBuffer.obtain("KeyguardCoordinator", logLevel, keyguardCoordinatorLogger$logTrackingUnseen$2, null);
        ((LogMessageImpl) obtain).bool1 = z;
        logBuffer.commit(obtain);
        return Unit.INSTANCE;
    }
}
