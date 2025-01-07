package com.android.systemui.statusbar.notification.collection.coordinator;

import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import java.util.Set;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class OriginalUnseenKeyguardCoordinator$trackSeenNotifications$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Set $notificationsSeenWhileLocked;
    /* synthetic */ boolean Z$0;
    int label;
    final /* synthetic */ OriginalUnseenKeyguardCoordinator this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OriginalUnseenKeyguardCoordinator$trackSeenNotifications$2(OriginalUnseenKeyguardCoordinator originalUnseenKeyguardCoordinator, Set set, Continuation continuation) {
        super(2, continuation);
        this.this$0 = originalUnseenKeyguardCoordinator;
        this.$notificationsSeenWhileLocked = set;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        OriginalUnseenKeyguardCoordinator$trackSeenNotifications$2 originalUnseenKeyguardCoordinator$trackSeenNotifications$2 = new OriginalUnseenKeyguardCoordinator$trackSeenNotifications$2(this.this$0, this.$notificationsSeenWhileLocked, continuation);
        originalUnseenKeyguardCoordinator$trackSeenNotifications$2.Z$0 = ((Boolean) obj).booleanValue();
        return originalUnseenKeyguardCoordinator$trackSeenNotifications$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        Boolean bool = (Boolean) obj;
        bool.booleanValue();
        return ((OriginalUnseenKeyguardCoordinator$trackSeenNotifications$2) create(bool, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (this.Z$0) {
                OriginalUnseenKeyguardCoordinator originalUnseenKeyguardCoordinator = this.this$0;
                Set set = this.$notificationsSeenWhileLocked;
                this.label = 1;
                int i2 = OriginalUnseenKeyguardCoordinator.$r8$clinit;
                originalUnseenKeyguardCoordinator.getClass();
                Object coroutineScope = CoroutineScopeKt.coroutineScope(this, new OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLocked$2(originalUnseenKeyguardCoordinator, set, null));
                if (coroutineScope != coroutineSingletons) {
                    coroutineScope = unit;
                }
                if (coroutineScope == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else {
                if (!this.$notificationsSeenWhileLocked.isEmpty()) {
                    this.this$0.unseenNotifications.removeAll(this.$notificationsSeenWhileLocked);
                    KeyguardCoordinatorLogger keyguardCoordinatorLogger = this.this$0.logger;
                    int size = this.$notificationsSeenWhileLocked.size();
                    int size2 = this.this$0.unseenNotifications.size();
                    LogLevel logLevel = LogLevel.DEBUG;
                    KeyguardCoordinatorLogger$logAllMarkedSeenOnUnlock$2 keyguardCoordinatorLogger$logAllMarkedSeenOnUnlock$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.KeyguardCoordinatorLogger$logAllMarkedSeenOnUnlock$2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            LogMessage logMessage = (LogMessage) obj2;
                            return logMessage.getInt1() + " Notifications have been marked as seen now that device is unlocked. " + logMessage.getInt2() + " notifications remain unseen.";
                        }
                    };
                    LogBuffer logBuffer = keyguardCoordinatorLogger.buffer;
                    LogMessage obtain = logBuffer.obtain("KeyguardCoordinator", logLevel, keyguardCoordinatorLogger$logAllMarkedSeenOnUnlock$2, null);
                    LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
                    logMessageImpl.int1 = size;
                    logMessageImpl.int2 = size2;
                    logBuffer.commit(obtain);
                    this.$notificationsSeenWhileLocked.clear();
                }
                invalidateList("keyguard no longer showing");
                OriginalUnseenKeyguardCoordinator originalUnseenKeyguardCoordinator2 = this.this$0;
                this.label = 2;
                originalUnseenKeyguardCoordinator2.getClass();
                Object coroutineScope2 = CoroutineScopeKt.coroutineScope(this, new OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileUnlocked$2(originalUnseenKeyguardCoordinator2, null));
                if (coroutineScope2 != coroutineSingletons) {
                    coroutineScope2 = unit;
                }
                if (coroutineScope2 == coroutineSingletons) {
                    return coroutineSingletons;
                }
            }
        } else {
            if (i != 1 && i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return unit;
    }
}
