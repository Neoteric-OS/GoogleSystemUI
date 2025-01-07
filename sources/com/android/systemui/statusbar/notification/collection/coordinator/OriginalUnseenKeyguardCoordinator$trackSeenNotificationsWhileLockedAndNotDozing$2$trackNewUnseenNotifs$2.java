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
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.SharedFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLockedAndNotDozing$2$trackNewUnseenNotifs$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Set $notificationsSeenWhileLocked;
    final /* synthetic */ Map $trackingJobsByEntry;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ OriginalUnseenKeyguardCoordinator this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLockedAndNotDozing$2$trackNewUnseenNotifs$2(OriginalUnseenKeyguardCoordinator originalUnseenKeyguardCoordinator, Map map, Set set, Continuation continuation) {
        super(2, continuation);
        this.this$0 = originalUnseenKeyguardCoordinator;
        this.$trackingJobsByEntry = map;
        this.$notificationsSeenWhileLocked = set;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLockedAndNotDozing$2$trackNewUnseenNotifs$2 originalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLockedAndNotDozing$2$trackNewUnseenNotifs$2 = new OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLockedAndNotDozing$2$trackNewUnseenNotifs$2(this.this$0, this.$trackingJobsByEntry, this.$notificationsSeenWhileLocked, continuation);
        originalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLockedAndNotDozing$2$trackNewUnseenNotifs$2.L$0 = obj;
        return originalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLockedAndNotDozing$2$trackNewUnseenNotifs$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        ((OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLockedAndNotDozing$2$trackNewUnseenNotifs$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
        final CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        final OriginalUnseenKeyguardCoordinator originalUnseenKeyguardCoordinator = this.this$0;
        SharedFlowImpl sharedFlowImpl = originalUnseenKeyguardCoordinator.unseenEntryAdded;
        final Map map = this.$trackingJobsByEntry;
        final Set set = this.$notificationsSeenWhileLocked;
        FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLockedAndNotDozing$2$trackNewUnseenNotifs$2.1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.notification.collection.coordinator.OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLockedAndNotDozing$2$trackNewUnseenNotifs$2$1$2, reason: invalid class name */
            final class AnonymousClass2 extends SuspendLambda implements Function2 {
                final /* synthetic */ NotificationEntry $entry;
                final /* synthetic */ Set $notificationsSeenWhileLocked;
                final /* synthetic */ Map $trackingJobsByEntry;
                int label;
                final /* synthetic */ OriginalUnseenKeyguardCoordinator this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass2(NotificationEntry notificationEntry, Set set, OriginalUnseenKeyguardCoordinator originalUnseenKeyguardCoordinator, Map map, Continuation continuation) {
                    super(2, continuation);
                    this.$entry = notificationEntry;
                    this.$notificationsSeenWhileLocked = set;
                    this.this$0 = originalUnseenKeyguardCoordinator;
                    this.$trackingJobsByEntry = map;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass2(this.$entry, this.$notificationsSeenWhileLocked, this.this$0, this.$trackingJobsByEntry, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ((AnonymousClass2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        Set set = this.$notificationsSeenWhileLocked;
                        OriginalUnseenKeyguardCoordinator originalUnseenKeyguardCoordinator = this.this$0;
                        Map map = this.$trackingJobsByEntry;
                        NotificationEntry notificationEntry = this.$entry;
                        this.label = 1;
                        if (OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLockedAndNotDozing$2.access$invokeSuspend$trackSeenDurationThreshold(set, originalUnseenKeyguardCoordinator, map, notificationEntry, this) == coroutineSingletons) {
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

            @Override // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(Object obj2, Continuation continuation) {
                NotificationEntry notificationEntry = (NotificationEntry) obj2;
                OriginalUnseenKeyguardCoordinator originalUnseenKeyguardCoordinator2 = OriginalUnseenKeyguardCoordinator.this;
                KeyguardCoordinatorLogger keyguardCoordinatorLogger = originalUnseenKeyguardCoordinator2.logger;
                LogLevel logLevel = LogLevel.DEBUG;
                KeyguardCoordinatorLogger$logTrackingLockscreenSeenDuration$4 keyguardCoordinatorLogger$logTrackingLockscreenSeenDuration$4 = new Function1() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.KeyguardCoordinatorLogger$logTrackingLockscreenSeenDuration$4
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Tracking new notification for lockscreen seen duration threshold: ", ((LogMessage) obj3).getStr1());
                    }
                };
                LogBuffer logBuffer = keyguardCoordinatorLogger.buffer;
                LogMessage obtain = logBuffer.obtain("KeyguardCoordinator", logLevel, keyguardCoordinatorLogger$logTrackingLockscreenSeenDuration$4, null);
                ((LogMessageImpl) obtain).str1 = notificationEntry.mKey;
                logBuffer.commit(obtain);
                Job job = (Job) map.get(notificationEntry);
                if (job != null) {
                    job.cancel(null);
                    KeyguardCoordinatorLogger keyguardCoordinatorLogger2 = originalUnseenKeyguardCoordinator2.logger;
                    KeyguardCoordinatorLogger$logResetSeenOnLockscreen$2 keyguardCoordinatorLogger$logResetSeenOnLockscreen$2 = KeyguardCoordinatorLogger$logResetSeenOnLockscreen$2.INSTANCE;
                    LogBuffer logBuffer2 = keyguardCoordinatorLogger2.buffer;
                    LogMessage obtain2 = logBuffer2.obtain("KeyguardCoordinator", logLevel, keyguardCoordinatorLogger$logResetSeenOnLockscreen$2, null);
                    ((LogMessageImpl) obtain2).str1 = notificationEntry.mKey;
                    logBuffer2.commit(obtain2);
                }
                Map map2 = map;
                map2.put(notificationEntry, BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass2(notificationEntry, set, OriginalUnseenKeyguardCoordinator.this, map2, null), 3));
                return Unit.INSTANCE;
            }
        };
        this.label = 1;
        sharedFlowImpl.collect(flowCollector, this);
        return coroutineSingletons;
    }
}
