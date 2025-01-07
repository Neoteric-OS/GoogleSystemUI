package com.android.systemui.statusbar.notification.collection.coordinator;

import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.StatusBarStateControllerExtKt;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.policy.BaseHeadsUpManager;
import com.android.systemui.statusbar.policy.HeadsUpManager;
import com.android.systemui.statusbar.policy.HeadsUpManagerExtKt;
import java.util.function.Consumer;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileUnlocked$2 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ OriginalUnseenKeyguardCoordinator this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.collection.coordinator.OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileUnlocked$2$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;
        final /* synthetic */ OriginalUnseenKeyguardCoordinator this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(OriginalUnseenKeyguardCoordinator originalUnseenKeyguardCoordinator, Continuation continuation) {
            super(2, continuation);
            this.this$0 = originalUnseenKeyguardCoordinator;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            Unit unit = Unit.INSTANCE;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                OriginalUnseenKeyguardCoordinator originalUnseenKeyguardCoordinator = this.this$0;
                this.label = 1;
                Object collectLatest = FlowKt.collectLatest(StatusBarStateControllerExtKt.getExpansionChanges(originalUnseenKeyguardCoordinator.statusBarStateController), new OriginalUnseenKeyguardCoordinator$clearUnseenNotificationsWhenShadeIsExpanded$2(originalUnseenKeyguardCoordinator, null), this);
                if (collectLatest != coroutineSingletons) {
                    collectLatest = unit;
                }
                if (collectLatest == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return unit;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.collection.coordinator.OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileUnlocked$2$2, reason: invalid class name */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        int label;
        final /* synthetic */ OriginalUnseenKeyguardCoordinator this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(OriginalUnseenKeyguardCoordinator originalUnseenKeyguardCoordinator, Continuation continuation) {
            super(2, continuation);
            this.this$0 = originalUnseenKeyguardCoordinator;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass2(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            Unit unit = Unit.INSTANCE;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final OriginalUnseenKeyguardCoordinator originalUnseenKeyguardCoordinator = this.this$0;
                this.label = 1;
                HeadsUpManager headsUpManager = originalUnseenKeyguardCoordinator.headsUpManager;
                ((BaseHeadsUpManager) headsUpManager).getAllEntries().filter(OriginalUnseenKeyguardCoordinator$markHeadsUpNotificationsAsSeen$2.INSTANCE).forEach(new Consumer() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.OriginalUnseenKeyguardCoordinator$markHeadsUpNotificationsAsSeen$3
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj2) {
                        OriginalUnseenKeyguardCoordinator.this.unseenNotifications.remove((NotificationEntry) obj2);
                    }
                });
                Object collect = HeadsUpManagerExtKt.getHeadsUpEvents(headsUpManager).collect(new FlowCollector() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.OriginalUnseenKeyguardCoordinator$markHeadsUpNotificationsAsSeen$4
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj2, Continuation continuation) {
                        Pair pair = (Pair) obj2;
                        NotificationEntry notificationEntry = (NotificationEntry) pair.component1();
                        if (((Boolean) pair.component2()).booleanValue()) {
                            OriginalUnseenKeyguardCoordinator originalUnseenKeyguardCoordinator2 = OriginalUnseenKeyguardCoordinator.this;
                            KeyguardCoordinatorLogger keyguardCoordinatorLogger = originalUnseenKeyguardCoordinator2.logger;
                            String str = notificationEntry.mKey;
                            keyguardCoordinatorLogger.getClass();
                            LogLevel logLevel = LogLevel.DEBUG;
                            KeyguardCoordinatorLogger$logUnseenHun$2 keyguardCoordinatorLogger$logUnseenHun$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.KeyguardCoordinatorLogger$logUnseenHun$2
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj3) {
                                    return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Unseen notif has become heads up: ", ((LogMessage) obj3).getStr1());
                                }
                            };
                            LogBuffer logBuffer = keyguardCoordinatorLogger.buffer;
                            LogMessage obtain = logBuffer.obtain("KeyguardCoordinator", logLevel, keyguardCoordinatorLogger$logUnseenHun$2, null);
                            ((LogMessageImpl) obtain).str1 = str;
                            logBuffer.commit(obtain);
                            originalUnseenKeyguardCoordinator2.unseenNotifications.remove(notificationEntry);
                        }
                        return Unit.INSTANCE;
                    }
                }, this);
                if (collect != coroutineSingletons) {
                    collect = unit;
                }
                if (collect == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return unit;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileUnlocked$2(OriginalUnseenKeyguardCoordinator originalUnseenKeyguardCoordinator, Continuation continuation) {
        super(2, continuation);
        this.this$0 = originalUnseenKeyguardCoordinator;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileUnlocked$2 originalUnseenKeyguardCoordinator$trackSeenNotificationsWhileUnlocked$2 = new OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileUnlocked$2(this.this$0, continuation);
        originalUnseenKeyguardCoordinator$trackSeenNotificationsWhileUnlocked$2.L$0 = obj;
        return originalUnseenKeyguardCoordinator$trackSeenNotificationsWhileUnlocked$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileUnlocked$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass1(this.this$0, null), 3);
        return BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass2(this.this$0, null), 3);
    }
}
