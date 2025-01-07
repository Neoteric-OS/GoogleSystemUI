package com.android.systemui.statusbar.notification.collection.coordinator;

import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.compose.foundation.text.input.internal.AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import java.util.Map;
import java.util.Set;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharedFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLocked$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Set $notificationsSeenWhileLocked;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ OriginalUnseenKeyguardCoordinator this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.collection.coordinator.OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLocked$2$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ Set $notificationsSeenWhileLocked;
        int label;
        final /* synthetic */ OriginalUnseenKeyguardCoordinator this$0;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.statusbar.notification.collection.coordinator.OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLocked$2$1$1, reason: invalid class name and collision with other inner class name */
        public final class C01921 implements FlowCollector {
            public final /* synthetic */ Object $notificationsSeenWhileLocked;
            public final /* synthetic */ int $r8$classId;
            public final /* synthetic */ OriginalUnseenKeyguardCoordinator this$0;

            public /* synthetic */ C01921(Object obj, OriginalUnseenKeyguardCoordinator originalUnseenKeyguardCoordinator, int i) {
                this.$r8$classId = i;
                this.$notificationsSeenWhileLocked = obj;
                this.this$0 = originalUnseenKeyguardCoordinator;
            }

            @Override // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(Object obj, Continuation continuation) {
                switch (this.$r8$classId) {
                    case 0:
                        NotificationEntry notificationEntry = (NotificationEntry) obj;
                        if (((Set) this.$notificationsSeenWhileLocked).remove(notificationEntry)) {
                            KeyguardCoordinatorLogger keyguardCoordinatorLogger = this.this$0.logger;
                            keyguardCoordinatorLogger.getClass();
                            LogLevel logLevel = LogLevel.DEBUG;
                            KeyguardCoordinatorLogger$logRemoveSeenOnLockscreen$2 keyguardCoordinatorLogger$logRemoveSeenOnLockscreen$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.KeyguardCoordinatorLogger$logRemoveSeenOnLockscreen$2
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj2) {
                                    return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Notification marked as seen on lockscreen removed: ", ((LogMessage) obj2).getStr1());
                                }
                            };
                            LogBuffer logBuffer = keyguardCoordinatorLogger.buffer;
                            LogMessage obtain = logBuffer.obtain("KeyguardCoordinator", logLevel, keyguardCoordinatorLogger$logRemoveSeenOnLockscreen$2, null);
                            ((LogMessageImpl) obtain).str1 = notificationEntry.mKey;
                            logBuffer.commit(obtain);
                        }
                        break;
                    default:
                        NotificationEntry notificationEntry2 = (NotificationEntry) obj;
                        Job job = (Job) ((Map) this.$notificationsSeenWhileLocked).remove(notificationEntry2);
                        if (job != null) {
                            job.cancel(null);
                            KeyguardCoordinatorLogger keyguardCoordinatorLogger2 = this.this$0.logger;
                            LogLevel logLevel2 = LogLevel.DEBUG;
                            KeyguardCoordinatorLogger$logStopTrackingLockscreenSeenDuration$2 keyguardCoordinatorLogger$logStopTrackingLockscreenSeenDuration$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.KeyguardCoordinatorLogger$logStopTrackingLockscreenSeenDuration$2
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj2) {
                                    return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Stop tracking removed notification for lockscreen seen duration threshold: ", ((LogMessage) obj2).getStr1());
                                }
                            };
                            LogBuffer logBuffer2 = keyguardCoordinatorLogger2.buffer;
                            LogMessage obtain2 = logBuffer2.obtain("KeyguardCoordinator", logLevel2, keyguardCoordinatorLogger$logStopTrackingLockscreenSeenDuration$2, null);
                            ((LogMessageImpl) obtain2).str1 = notificationEntry2.mKey;
                            logBuffer2.commit(obtain2);
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(OriginalUnseenKeyguardCoordinator originalUnseenKeyguardCoordinator, Set set, Continuation continuation) {
            super(2, continuation);
            this.this$0 = originalUnseenKeyguardCoordinator;
            this.$notificationsSeenWhileLocked = set;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.this$0, this.$notificationsSeenWhileLocked, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            return CoroutineSingletons.COROUTINE_SUSPENDED;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i != 0) {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                throw AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0.m(obj);
            }
            ResultKt.throwOnFailure(obj);
            OriginalUnseenKeyguardCoordinator originalUnseenKeyguardCoordinator = this.this$0;
            SharedFlowImpl sharedFlowImpl = originalUnseenKeyguardCoordinator.unseenEntryRemoved;
            C01921 c01921 = new C01921(this.$notificationsSeenWhileLocked, originalUnseenKeyguardCoordinator, 0);
            this.label = 1;
            sharedFlowImpl.collect(c01921, this);
            return coroutineSingletons;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.collection.coordinator.OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLocked$2$2, reason: invalid class name */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        final /* synthetic */ Set $notificationsSeenWhileLocked;
        /* synthetic */ boolean Z$0;
        int label;
        final /* synthetic */ OriginalUnseenKeyguardCoordinator this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(OriginalUnseenKeyguardCoordinator originalUnseenKeyguardCoordinator, Set set, Continuation continuation) {
            super(2, continuation);
            this.this$0 = originalUnseenKeyguardCoordinator;
            this.$notificationsSeenWhileLocked = set;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.this$0, this.$notificationsSeenWhileLocked, continuation);
            anonymousClass2.Z$0 = ((Boolean) obj).booleanValue();
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            Boolean bool = (Boolean) obj;
            bool.booleanValue();
            return ((AnonymousClass2) create(bool, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                if (!this.Z$0) {
                    OriginalUnseenKeyguardCoordinator originalUnseenKeyguardCoordinator = this.this$0;
                    Set set = this.$notificationsSeenWhileLocked;
                    this.label = 1;
                    int i2 = OriginalUnseenKeyguardCoordinator.$r8$clinit;
                    originalUnseenKeyguardCoordinator.getClass();
                    if (CoroutineScopeKt.coroutineScope(this, new OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLockedAndNotDozing$2(originalUnseenKeyguardCoordinator, set, null)) == coroutineSingletons) {
                        return coroutineSingletons;
                    }
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

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLocked$2(OriginalUnseenKeyguardCoordinator originalUnseenKeyguardCoordinator, Set set, Continuation continuation) {
        super(2, continuation);
        this.this$0 = originalUnseenKeyguardCoordinator;
        this.$notificationsSeenWhileLocked = set;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLocked$2 originalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLocked$2 = new OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLocked$2(this.this$0, this.$notificationsSeenWhileLocked, continuation);
        originalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLocked$2.L$0 = obj;
        return originalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLocked$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLocked$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            BuildersKt.launch$default((CoroutineScope) this.L$0, null, null, new AnonymousClass1(this.this$0, this.$notificationsSeenWhileLocked, null), 3);
            OriginalUnseenKeyguardCoordinator originalUnseenKeyguardCoordinator = this.this$0;
            ReadonlyStateFlow readonlyStateFlow = originalUnseenKeyguardCoordinator.keyguardRepository.isDozing;
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(originalUnseenKeyguardCoordinator, this.$notificationsSeenWhileLocked, null);
            this.label = 1;
            if (FlowKt.collectLatest(readonlyStateFlow, anonymousClass2, this) == coroutineSingletons) {
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
