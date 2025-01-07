package com.android.systemui.statusbar.notification.collection.coordinator;

import androidx.compose.foundation.text.input.internal.AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLockedAndNotDozing$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Set $notificationsSeenWhileLocked;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ OriginalUnseenKeyguardCoordinator this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.collection.coordinator.OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLockedAndNotDozing$2$2, reason: invalid class name */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        final /* synthetic */ Set $notificationsSeenWhileLocked;
        final /* synthetic */ Map $trackingJobsByEntry;
        int label;
        final /* synthetic */ OriginalUnseenKeyguardCoordinator this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(OriginalUnseenKeyguardCoordinator originalUnseenKeyguardCoordinator, Map map, Set set, Continuation continuation) {
            super(2, continuation);
            this.this$0 = originalUnseenKeyguardCoordinator;
            this.$trackingJobsByEntry = map;
            this.$notificationsSeenWhileLocked = set;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass2(this.this$0, this.$trackingJobsByEntry, this.$notificationsSeenWhileLocked, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            ((AnonymousClass2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
            Map map = this.$trackingJobsByEntry;
            Set set = this.$notificationsSeenWhileLocked;
            this.label = 1;
            OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLockedAndNotDozing$2.access$invokeSuspend$trackNewUnseenNotifs(originalUnseenKeyguardCoordinator, map, set, this);
            return coroutineSingletons;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.collection.coordinator.OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLockedAndNotDozing$2$3, reason: invalid class name */
    final class AnonymousClass3 extends SuspendLambda implements Function2 {
        final /* synthetic */ Map $trackingJobsByEntry;
        int label;
        final /* synthetic */ OriginalUnseenKeyguardCoordinator this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(OriginalUnseenKeyguardCoordinator originalUnseenKeyguardCoordinator, Map map, Continuation continuation) {
            super(2, continuation);
            this.this$0 = originalUnseenKeyguardCoordinator;
            this.$trackingJobsByEntry = map;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass3(this.this$0, this.$trackingJobsByEntry, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            ((AnonymousClass3) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
            Map map = this.$trackingJobsByEntry;
            this.label = 1;
            OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLockedAndNotDozing$2.access$invokeSuspend$stopTrackingRemovedNotifs(originalUnseenKeyguardCoordinator, map, this);
            return coroutineSingletons;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLockedAndNotDozing$2(OriginalUnseenKeyguardCoordinator originalUnseenKeyguardCoordinator, Set set, Continuation continuation) {
        super(2, continuation);
        this.this$0 = originalUnseenKeyguardCoordinator;
        this.$notificationsSeenWhileLocked = set;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void access$invokeSuspend$stopTrackingRemovedNotifs(com.android.systemui.statusbar.notification.collection.coordinator.OriginalUnseenKeyguardCoordinator r5, java.util.Map r6, kotlin.coroutines.jvm.internal.ContinuationImpl r7) {
        /*
            r0 = 1
            boolean r1 = r7 instanceof com.android.systemui.statusbar.notification.collection.coordinator.OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLockedAndNotDozing$2$stopTrackingRemovedNotifs$1
            if (r1 == 0) goto L14
            r1 = r7
            com.android.systemui.statusbar.notification.collection.coordinator.OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLockedAndNotDozing$2$stopTrackingRemovedNotifs$1 r1 = (com.android.systemui.statusbar.notification.collection.coordinator.OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLockedAndNotDozing$2$stopTrackingRemovedNotifs$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L14
            int r2 = r2 - r3
            r1.label = r2
            goto L19
        L14:
            com.android.systemui.statusbar.notification.collection.coordinator.OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLockedAndNotDozing$2$stopTrackingRemovedNotifs$1 r1 = new com.android.systemui.statusbar.notification.collection.coordinator.OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLockedAndNotDozing$2$stopTrackingRemovedNotifs$1
            r1.<init>(r7)
        L19:
            java.lang.Object r7 = r1.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r2 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r1.label
            if (r2 == 0) goto L30
            if (r2 == r0) goto L2b
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L2b:
            kotlin.KotlinNothingValueException r5 = androidx.compose.foundation.text.input.internal.AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0.m(r7)
            throw r5
        L30:
            kotlin.ResultKt.throwOnFailure(r7)
            kotlinx.coroutines.flow.SharedFlowImpl r7 = r5.unseenEntryRemoved
            com.android.systemui.statusbar.notification.collection.coordinator.OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLocked$2$1$1 r2 = new com.android.systemui.statusbar.notification.collection.coordinator.OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLocked$2$1$1
            r2.<init>(r6, r5, r0)
            r1.label = r0
            r7.collect(r2, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.collection.coordinator.OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLockedAndNotDozing$2.access$invokeSuspend$stopTrackingRemovedNotifs(com.android.systemui.statusbar.notification.collection.coordinator.OriginalUnseenKeyguardCoordinator, java.util.Map, kotlin.coroutines.jvm.internal.ContinuationImpl):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void access$invokeSuspend$trackNewUnseenNotifs(com.android.systemui.statusbar.notification.collection.coordinator.OriginalUnseenKeyguardCoordinator r4, java.util.Map r5, java.util.Set r6, kotlin.coroutines.jvm.internal.ContinuationImpl r7) {
        /*
            boolean r0 = r7 instanceof com.android.systemui.statusbar.notification.collection.coordinator.OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLockedAndNotDozing$2$trackNewUnseenNotifs$1
            if (r0 == 0) goto L13
            r0 = r7
            com.android.systemui.statusbar.notification.collection.coordinator.OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLockedAndNotDozing$2$trackNewUnseenNotifs$1 r0 = (com.android.systemui.statusbar.notification.collection.coordinator.OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLockedAndNotDozing$2$trackNewUnseenNotifs$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.statusbar.notification.collection.coordinator.OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLockedAndNotDozing$2$trackNewUnseenNotifs$1 r0 = new com.android.systemui.statusbar.notification.collection.coordinator.OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLockedAndNotDozing$2$trackNewUnseenNotifs$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L2f
            if (r2 == r3) goto L2b
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L2b:
            kotlin.ResultKt.throwOnFailure(r7)
            goto L41
        L2f:
            kotlin.ResultKt.throwOnFailure(r7)
            com.android.systemui.statusbar.notification.collection.coordinator.OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLockedAndNotDozing$2$trackNewUnseenNotifs$2 r7 = new com.android.systemui.statusbar.notification.collection.coordinator.OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLockedAndNotDozing$2$trackNewUnseenNotifs$2
            r2 = 0
            r7.<init>(r4, r5, r6, r2)
            r0.label = r3
            java.lang.Object r4 = kotlinx.coroutines.CoroutineScopeKt.coroutineScope(r0, r7)
            if (r4 != r1) goto L41
            return
        L41:
            kotlin.KotlinNothingValueException r4 = new kotlin.KotlinNothingValueException
            r4.<init>()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.collection.coordinator.OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLockedAndNotDozing$2.access$invokeSuspend$trackNewUnseenNotifs(com.android.systemui.statusbar.notification.collection.coordinator.OriginalUnseenKeyguardCoordinator, java.util.Map, java.util.Set, kotlin.coroutines.jvm.internal.ContinuationImpl):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$invokeSuspend$trackSeenDurationThreshold(java.util.Set r8, com.android.systemui.statusbar.notification.collection.coordinator.OriginalUnseenKeyguardCoordinator r9, java.util.Map r10, com.android.systemui.statusbar.notification.collection.NotificationEntry r11, kotlin.coroutines.jvm.internal.ContinuationImpl r12) {
        /*
            boolean r0 = r12 instanceof com.android.systemui.statusbar.notification.collection.coordinator.OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLockedAndNotDozing$2$trackSeenDurationThreshold$1
            if (r0 == 0) goto L13
            r0 = r12
            com.android.systemui.statusbar.notification.collection.coordinator.OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLockedAndNotDozing$2$trackSeenDurationThreshold$1 r0 = (com.android.systemui.statusbar.notification.collection.coordinator.OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLockedAndNotDozing$2$trackSeenDurationThreshold$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.statusbar.notification.collection.coordinator.OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLockedAndNotDozing$2$trackSeenDurationThreshold$1 r0 = new com.android.systemui.statusbar.notification.collection.coordinator.OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLockedAndNotDozing$2$trackSeenDurationThreshold$1
            r0.<init>(r12)
        L18:
            java.lang.Object r12 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            java.lang.String r3 = "KeyguardCoordinator"
            r4 = 0
            r5 = 1
            if (r2 == 0) goto L45
            if (r2 != r5) goto L3d
            java.lang.Object r8 = r0.L$3
            r11 = r8
            com.android.systemui.statusbar.notification.collection.NotificationEntry r11 = (com.android.systemui.statusbar.notification.collection.NotificationEntry) r11
            java.lang.Object r8 = r0.L$2
            r10 = r8
            java.util.Map r10 = (java.util.Map) r10
            java.lang.Object r8 = r0.L$1
            r9 = r8
            com.android.systemui.statusbar.notification.collection.coordinator.OriginalUnseenKeyguardCoordinator r9 = (com.android.systemui.statusbar.notification.collection.coordinator.OriginalUnseenKeyguardCoordinator) r9
            java.lang.Object r8 = r0.L$0
            java.util.Set r8 = (java.util.Set) r8
            kotlin.ResultKt.throwOnFailure(r12)
            goto L77
        L3d:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L45:
            kotlin.ResultKt.throwOnFailure(r12)
            boolean r12 = r8.remove(r11)
            if (r12 == 0) goto L64
            com.android.systemui.statusbar.notification.collection.coordinator.KeyguardCoordinatorLogger r12 = r9.logger
            com.android.systemui.log.core.LogLevel r2 = com.android.systemui.log.core.LogLevel.DEBUG
            com.android.systemui.statusbar.notification.collection.coordinator.KeyguardCoordinatorLogger$logResetSeenOnLockscreen$2 r6 = com.android.systemui.statusbar.notification.collection.coordinator.KeyguardCoordinatorLogger$logResetSeenOnLockscreen$2.INSTANCE
            com.android.systemui.log.LogBuffer r12 = r12.buffer
            com.android.systemui.log.core.LogMessage r2 = r12.obtain(r3, r2, r6, r4)
            r6 = r2
            com.android.systemui.log.LogMessageImpl r6 = (com.android.systemui.log.LogMessageImpl) r6
            java.lang.String r7 = r11.mKey
            r6.str1 = r7
            r12.commit(r2)
        L64:
            long r6 = com.android.systemui.statusbar.notification.collection.coordinator.OriginalUnseenKeyguardCoordinator.SEEN_TIMEOUT
            r0.L$0 = r8
            r0.L$1 = r9
            r0.L$2 = r10
            r0.L$3 = r11
            r0.label = r5
            java.lang.Object r12 = kotlinx.coroutines.DelayKt.m1784delayVtjQ1oo(r6, r0)
            if (r12 != r1) goto L77
            goto L95
        L77:
            r8.add(r11)
            r10.remove(r11)
            com.android.systemui.statusbar.notification.collection.coordinator.KeyguardCoordinatorLogger r8 = r9.logger
            com.android.systemui.log.core.LogLevel r9 = com.android.systemui.log.core.LogLevel.DEBUG
            com.android.systemui.statusbar.notification.collection.coordinator.KeyguardCoordinatorLogger$logSeenOnLockscreen$2 r10 = new kotlin.jvm.functions.Function1() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.KeyguardCoordinatorLogger$logSeenOnLockscreen$2
                static {
                    /*
                        com.android.systemui.statusbar.notification.collection.coordinator.KeyguardCoordinatorLogger$logSeenOnLockscreen$2 r0 = new com.android.systemui.statusbar.notification.collection.coordinator.KeyguardCoordinatorLogger$logSeenOnLockscreen$2
                        r0.<init>()
                        
                        // error: 0x0005: SPUT 
  (r0 I:com.android.systemui.statusbar.notification.collection.coordinator.KeyguardCoordinatorLogger$logSeenOnLockscreen$2)
 com.android.systemui.statusbar.notification.collection.coordinator.KeyguardCoordinatorLogger$logSeenOnLockscreen$2.INSTANCE com.android.systemui.statusbar.notification.collection.coordinator.KeyguardCoordinatorLogger$logSeenOnLockscreen$2
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.collection.coordinator.KeyguardCoordinatorLogger$logSeenOnLockscreen$2.<clinit>():void");
                }

                {
                    /*
                        r1 = this;
                        r0 = 1
                        r1.<init>(r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.collection.coordinator.KeyguardCoordinatorLogger$logSeenOnLockscreen$2.<init>():void");
                }

                @Override // kotlin.jvm.functions.Function1
                public final java.lang.Object invoke(java.lang.Object r2) {
                    /*
                        r1 = this;
                        com.android.systemui.log.core.LogMessage r2 = (com.android.systemui.log.core.LogMessage) r2
                        java.lang.String r1 = r2.getStr1()
                        java.lang.String r2 = "Notification ["
                        java.lang.String r0 = "] on lockscreen will be marked as seen when unlocked."
                        java.lang.String r1 = androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m(r2, r1, r0)
                        return r1
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.collection.coordinator.KeyguardCoordinatorLogger$logSeenOnLockscreen$2.invoke(java.lang.Object):java.lang.Object");
                }
            }
            com.android.systemui.log.LogBuffer r8 = r8.buffer
            com.android.systemui.log.core.LogMessage r9 = r8.obtain(r3, r9, r10, r4)
            r10 = r9
            com.android.systemui.log.LogMessageImpl r10 = (com.android.systemui.log.LogMessageImpl) r10
            java.lang.String r11 = r11.mKey
            r10.str1 = r11
            r8.commit(r9)
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
        L95:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.collection.coordinator.OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLockedAndNotDozing$2.access$invokeSuspend$trackSeenDurationThreshold(java.util.Set, com.android.systemui.statusbar.notification.collection.coordinator.OriginalUnseenKeyguardCoordinator, java.util.Map, com.android.systemui.statusbar.notification.collection.NotificationEntry, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLockedAndNotDozing$2 originalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLockedAndNotDozing$2 = new OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLockedAndNotDozing$2(this.this$0, this.$notificationsSeenWhileLocked, continuation);
        originalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLockedAndNotDozing$2.L$0 = obj;
        return originalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLockedAndNotDozing$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLockedAndNotDozing$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        OriginalUnseenKeyguardCoordinator originalUnseenKeyguardCoordinator = this.this$0;
        KeyguardCoordinatorLogger keyguardCoordinatorLogger = originalUnseenKeyguardCoordinator.logger;
        Set set = originalUnseenKeyguardCoordinator.unseenNotifications;
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardCoordinatorLogger$logTrackingLockscreenSeenDuration$2 keyguardCoordinatorLogger$logTrackingLockscreenSeenDuration$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.KeyguardCoordinatorLogger$logTrackingLockscreenSeenDuration$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                LogMessage logMessage = (LogMessage) obj2;
                return "Tracking " + logMessage.getInt1() + " unseen notifications for lockscreen seen duration threshold: " + logMessage.getStr1();
            }
        };
        LogBuffer logBuffer = keyguardCoordinatorLogger.buffer;
        LogMessage obtain = logBuffer.obtain("KeyguardCoordinator", logLevel, keyguardCoordinatorLogger$logTrackingLockscreenSeenDuration$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = CollectionsKt.joinToString$default(set, null, null, null, new Function1() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.KeyguardCoordinatorLogger$logTrackingLockscreenSeenDuration$1$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return ((NotificationEntry) obj2).mKey;
            }
        }, 31);
        logMessageImpl.int1 = set.size();
        logBuffer.commit(obtain);
        OriginalUnseenKeyguardCoordinator originalUnseenKeyguardCoordinator2 = this.this$0;
        Set<NotificationEntry> set2 = originalUnseenKeyguardCoordinator2.unseenNotifications;
        Set set3 = this.$notificationsSeenWhileLocked;
        for (NotificationEntry notificationEntry : set2) {
            linkedHashMap.put(notificationEntry, BuildersKt.launch$default(coroutineScope, null, null, new OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLockedAndNotDozing$2$1$1(notificationEntry, set3, originalUnseenKeyguardCoordinator2, linkedHashMap, null), 3));
        }
        BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass2(this.this$0, linkedHashMap, this.$notificationsSeenWhileLocked, null), 3);
        return BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass3(this.this$0, linkedHashMap, null), 3);
    }
}
