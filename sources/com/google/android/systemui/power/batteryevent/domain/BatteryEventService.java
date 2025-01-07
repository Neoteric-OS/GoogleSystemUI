package com.google.android.systemui.power.batteryevent.domain;

import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import androidx.collection.ArrayMap;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import androidx.lifecycle.LifecycleKt;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.LifecycleService;
import com.android.systemui.broadcast.BroadcastSender;
import com.android.systemui.settings.UserTracker;
import com.google.android.systemui.power.batteryevent.aidl.SurfaceType;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.ExceptionsKt;
import kotlinx.coroutines.sync.MutexImpl;
import kotlinx.coroutines.sync.MutexKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BatteryEventService extends LifecycleService {
    public static final Set supportedCallers = SetsKt.setOf("com.android.settings", "com.android.systemui", "com.google.android.apps.turbo", "com.google.android.settings.intelligence");
    public final CoroutineDispatcher backgroundDispatcher;
    public final BroadcastSender broadcastSender;
    public final BatteryEventStateController eventStateController;
    public final UserTracker userTracker;
    public final CopyOnWriteArraySet broadcastIntentBatteryEventsListener = new CopyOnWriteArraySet();
    public final BatteryEventService$aidlBatteryEventsCallbackListener$1 aidlBatteryEventsCallbackListener = new BatteryEventService$aidlBatteryEventsCallbackListener$1(this);
    public final ArrayMap batteryEventsBroadcastCache = new ArrayMap(0);
    public final ArrayMap batteryEventsCallbackCache = new ArrayMap(0);
    public final MutexImpl batteryEventsBroadcastCacheMutex = MutexKt.Mutex$default();
    public final MutexImpl batteryEventsCallbackCacheMutex = MutexKt.Mutex$default();
    public final BatteryEventService$binder$1 binder = new BatteryEventService$binder$1(this);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class BatteryEventsBroadcastData {
        public final ComponentName componentName;
        public final String indexKey;
        public final Set subscribedEvents;
        public final int userId;

        public BatteryEventsBroadcastData(ComponentName componentName, Set set, int i, String str) {
            this.componentName = componentName;
            this.subscribedEvents = set;
            this.userId = i;
            this.indexKey = str;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof BatteryEventsBroadcastData)) {
                return false;
            }
            BatteryEventsBroadcastData batteryEventsBroadcastData = (BatteryEventsBroadcastData) obj;
            return Intrinsics.areEqual(this.componentName, batteryEventsBroadcastData.componentName) && Intrinsics.areEqual(this.subscribedEvents, batteryEventsBroadcastData.subscribedEvents) && this.userId == batteryEventsBroadcastData.userId && Intrinsics.areEqual(this.indexKey, batteryEventsBroadcastData.indexKey);
        }

        public final int hashCode() {
            return this.indexKey.hashCode() + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.userId, (this.subscribedEvents.hashCode() + (this.componentName.hashCode() * 31)) * 31, 31);
        }

        public final String toString() {
            return "BatteryEventsBroadcastData(componentName=" + this.componentName + ", subscribedEvents=" + this.subscribedEvents + ", userId=" + this.userId + ", indexKey=" + this.indexKey + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class BatteryEventsCallbackData {
        public final Set subscribedEvents;
        public final SurfaceType surfaceType;

        public BatteryEventsCallbackData(Set set, SurfaceType surfaceType) {
            this.subscribedEvents = set;
            this.surfaceType = surfaceType;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof BatteryEventsCallbackData)) {
                return false;
            }
            BatteryEventsCallbackData batteryEventsCallbackData = (BatteryEventsCallbackData) obj;
            return Intrinsics.areEqual(this.subscribedEvents, batteryEventsCallbackData.subscribedEvents) && this.surfaceType == batteryEventsCallbackData.surfaceType;
        }

        public final int hashCode() {
            int hashCode = this.subscribedEvents.hashCode() * 31;
            SurfaceType surfaceType = this.surfaceType;
            return hashCode + (surfaceType == null ? 0 : surfaceType.hashCode());
        }

        public final String toString() {
            return "BatteryEventsCallbackData(subscribedEvents=" + this.subscribedEvents + ", surfaceType=" + this.surfaceType + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CachedBatteryEvents {
        public final int batteryLevel;
        public final Set needNotifiedEvents;
        public final int pluggedType;

        public CachedBatteryEvents(Set set, int i, int i2) {
            this.needNotifiedEvents = set;
            this.batteryLevel = i;
            this.pluggedType = i2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof CachedBatteryEvents)) {
                return false;
            }
            CachedBatteryEvents cachedBatteryEvents = (CachedBatteryEvents) obj;
            return Intrinsics.areEqual(this.needNotifiedEvents, cachedBatteryEvents.needNotifiedEvents) && this.batteryLevel == cachedBatteryEvents.batteryLevel && this.pluggedType == cachedBatteryEvents.pluggedType;
        }

        public final int hashCode() {
            return Integer.hashCode(this.pluggedType) + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.batteryLevel, this.needNotifiedEvents.hashCode() * 31, 31);
        }

        public final boolean isEqual(Set set, int i, int i2) {
            return this.needNotifiedEvents.size() == set.size() && this.needNotifiedEvents.containsAll(set) && this.batteryLevel == i && this.pluggedType == i2;
        }

        public final String toString() {
            Set set = this.needNotifiedEvents;
            StringBuilder sb = new StringBuilder("CachedBatteryEvents(needNotifiedEvents=");
            sb.append(set);
            sb.append(", batteryLevel=");
            sb.append(this.batteryLevel);
            sb.append(", pluggedType=");
            return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.pluggedType, ")");
        }
    }

    public BatteryEventService(BatteryEventStateController batteryEventStateController, BroadcastSender broadcastSender, CoroutineDispatcher coroutineDispatcher, UserTracker userTracker) {
        this.eventStateController = batteryEventStateController;
        this.broadcastSender = broadcastSender;
        this.backgroundDispatcher = coroutineDispatcher;
        this.userTracker = userTracker;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x008a A[Catch: RemoteException -> 0x0038, TRY_LEAVE, TryCatch #0 {RemoteException -> 0x0038, blocks: (B:11:0x0034, B:12:0x0085, B:18:0x008a), top: B:10:0x0034 }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0028  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$notifyAidlListenerBatteryEventUpdate(com.google.android.systemui.power.batteryevent.domain.BatteryEventService r10, int r11, com.google.android.systemui.power.batteryevent.common.BatteryEvents r12, kotlin.coroutines.jvm.internal.ContinuationImpl r13) {
        /*
            r10.getClass()
            java.lang.String r0 = "aidlCallback: "
            boolean r1 = r13 instanceof com.google.android.systemui.power.batteryevent.domain.BatteryEventService$notifyAidlListenerBatteryEventUpdate$1
            if (r1 == 0) goto L18
            r1 = r13
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService$notifyAidlListenerBatteryEventUpdate$1 r1 = (com.google.android.systemui.power.batteryevent.domain.BatteryEventService$notifyAidlListenerBatteryEventUpdate$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L18
            int r2 = r2 - r3
            r1.label = r2
            goto L1d
        L18:
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService$notifyAidlListenerBatteryEventUpdate$1 r1 = new com.google.android.systemui.power.batteryevent.domain.BatteryEventService$notifyAidlListenerBatteryEventUpdate$1
            r1.<init>(r10, r13)
        L1d:
            java.lang.Object r13 = r1.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r2 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r3 = r1.label
            java.lang.String r4 = "BatteryEventService"
            r5 = 1
            if (r3 == 0) goto L43
            if (r3 != r5) goto L3b
            long r10 = r1.J$0
            java.lang.Object r12 = r1.L$1
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService$BatteryEventsCallbackData r12 = (com.google.android.systemui.power.batteryevent.domain.BatteryEventService.BatteryEventsCallbackData) r12
            java.lang.Object r1 = r1.L$0
            com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener r1 = (com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener) r1
            kotlin.ResultKt.throwOnFailure(r13)     // Catch: android.os.RemoteException -> L38
            goto L85
        L38:
            r12 = move-exception
            goto Lb9
        L3b:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L43:
            kotlin.ResultKt.throwOnFailure(r13)
            long r6 = java.lang.System.currentTimeMillis()
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService$aidlBatteryEventsCallbackListener$1 r13 = r10.aidlBatteryEventsCallbackListener     // Catch: android.os.RemoteException -> Lb7
            java.lang.Object r13 = r13.getBroadcastCookie(r11)     // Catch: android.os.RemoteException -> Lb7
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService$BatteryEventsCallbackData r13 = (com.google.android.systemui.power.batteryevent.domain.BatteryEventService.BatteryEventsCallbackData) r13     // Catch: android.os.RemoteException -> Lb7
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService$aidlBatteryEventsCallbackListener$1 r3 = r10.aidlBatteryEventsCallbackListener     // Catch: android.os.RemoteException -> Lb7
            android.os.IInterface r11 = r3.getBroadcastItem(r11)     // Catch: android.os.RemoteException -> Lb7
            com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener r11 = (com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener) r11     // Catch: android.os.RemoteException -> Lb7
            com.google.android.systemui.power.batteryevent.common.BatteryEvents r3 = new com.google.android.systemui.power.batteryevent.common.BatteryEvents     // Catch: android.os.RemoteException -> Lb7
            java.util.Set r8 = r13.subscribedEvents     // Catch: android.os.RemoteException -> Lb7
            java.lang.Iterable r8 = (java.lang.Iterable) r8     // Catch: android.os.RemoteException -> Lb7
            java.util.Set r9 = r12.eventTypes     // Catch: android.os.RemoteException -> Lb7
            java.lang.Iterable r9 = (java.lang.Iterable) r9     // Catch: android.os.RemoteException -> Lb7
            java.util.Set r8 = kotlin.collections.CollectionsKt.intersect(r8, r9)     // Catch: android.os.RemoteException -> Lb7
            int r9 = r12.batteryLevel     // Catch: android.os.RemoteException -> Lb7
            int r12 = r12.pluggedType     // Catch: android.os.RemoteException -> Lb7
            r3.<init>(r8, r9, r12)     // Catch: android.os.RemoteException -> Lb7
            kotlin.jvm.internal.Intrinsics.checkNotNull(r11)     // Catch: android.os.RemoteException -> Lb7
            r1.L$0 = r11     // Catch: android.os.RemoteException -> Lb7
            r1.L$1 = r13     // Catch: android.os.RemoteException -> Lb7
            r1.J$0 = r6     // Catch: android.os.RemoteException -> Lb7
            r1.label = r5     // Catch: android.os.RemoteException -> Lb7
            java.lang.Object r10 = r10.updateBatteryEventsCallbackCache(r13, r3, r11, r1)     // Catch: android.os.RemoteException -> Lb7
            if (r10 != r2) goto L81
            goto Lc8
        L81:
            r1 = r11
            r12 = r13
            r13 = r10
            r10 = r6
        L85:
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService$CachedBatteryEvents r13 = (com.google.android.systemui.power.batteryevent.domain.BatteryEventService.CachedBatteryEvents) r13     // Catch: android.os.RemoteException -> L38
            if (r13 != 0) goto L8a
            goto Lbe
        L8a:
            com.google.android.systemui.power.batteryevent.aidl.SurfaceType r12 = r12.surfaceType     // Catch: android.os.RemoteException -> L38
            java.util.Set r2 = r13.needNotifiedEvents     // Catch: android.os.RemoteException -> L38
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: android.os.RemoteException -> L38
            r3.<init>(r0)     // Catch: android.os.RemoteException -> L38
            r3.append(r12)     // Catch: android.os.RemoteException -> L38
            java.lang.String r12 = ", events: "
            r3.append(r12)     // Catch: android.os.RemoteException -> L38
            r3.append(r2)     // Catch: android.os.RemoteException -> L38
            java.lang.String r12 = r3.toString()     // Catch: android.os.RemoteException -> L38
            android.util.Log.d(r4, r12)     // Catch: android.os.RemoteException -> L38
            java.util.Set r12 = r13.needNotifiedEvents     // Catch: android.os.RemoteException -> L38
            java.lang.Iterable r12 = (java.lang.Iterable) r12     // Catch: android.os.RemoteException -> L38
            java.util.List r12 = kotlin.collections.CollectionsKt.toList(r12)     // Catch: android.os.RemoteException -> L38
            int r0 = r13.batteryLevel     // Catch: android.os.RemoteException -> L38
            int r13 = r13.pluggedType     // Catch: android.os.RemoteException -> L38
            r1.onBatteryEventChanged(r0, r13, r12)     // Catch: android.os.RemoteException -> L38
            goto Lbe
        Lb5:
            r10 = r6
            goto Lb9
        Lb7:
            r12 = move-exception
            goto Lb5
        Lb9:
            java.lang.String r13 = "unexpected exception"
            android.util.Log.w(r4, r13, r12)
        Lbe:
            long r12 = java.lang.System.currentTimeMillis()
            long r12 = r12 - r10
            java.lang.Long r2 = new java.lang.Long
            r2.<init>(r12)
        Lc8:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.power.batteryevent.domain.BatteryEventService.access$notifyAidlListenerBatteryEventUpdate(com.google.android.systemui.power.batteryevent.domain.BatteryEventService, int, com.google.android.systemui.power.batteryevent.common.BatteryEvents, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x005f, code lost:
    
        if (r9.lock(r0) == r1) goto L27;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /* JADX WARN: Type inference failed for: r5v6, types: [kotlinx.coroutines.sync.Mutex] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$updateBatteryEventsBroadcastCache(com.google.android.systemui.power.batteryevent.domain.BatteryEventService r5, com.google.android.systemui.power.batteryevent.domain.BatteryEventService.BatteryEventsBroadcastData r6, com.google.android.systemui.power.batteryevent.common.BatteryEvents r7, java.util.Set r8, kotlin.coroutines.jvm.internal.ContinuationImpl r9) {
        /*
            r5.getClass()
            boolean r0 = r9 instanceof com.google.android.systemui.power.batteryevent.domain.BatteryEventService$updateBatteryEventsBroadcastCache$1
            if (r0 == 0) goto L16
            r0 = r9
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService$updateBatteryEventsBroadcastCache$1 r0 = (com.google.android.systemui.power.batteryevent.domain.BatteryEventService$updateBatteryEventsBroadcastCache$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L16
            int r1 = r1 - r2
            r0.label = r1
            goto L1b
        L16:
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService$updateBatteryEventsBroadcastCache$1 r0 = new com.google.android.systemui.power.batteryevent.domain.BatteryEventService$updateBatteryEventsBroadcastCache$1
            r0.<init>(r5, r9)
        L1b:
            java.lang.Object r9 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L4a
            if (r2 != r3) goto L42
            java.lang.Object r5 = r0.L$4
            kotlinx.coroutines.sync.Mutex r5 = (kotlinx.coroutines.sync.Mutex) r5
            java.lang.Object r6 = r0.L$3
            r8 = r6
            java.util.Set r8 = (java.util.Set) r8
            java.lang.Object r6 = r0.L$2
            r7 = r6
            com.google.android.systemui.power.batteryevent.common.BatteryEvents r7 = (com.google.android.systemui.power.batteryevent.common.BatteryEvents) r7
            java.lang.Object r6 = r0.L$1
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService$BatteryEventsBroadcastData r6 = (com.google.android.systemui.power.batteryevent.domain.BatteryEventService.BatteryEventsBroadcastData) r6
            java.lang.Object r0 = r0.L$0
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService r0 = (com.google.android.systemui.power.batteryevent.domain.BatteryEventService) r0
            kotlin.ResultKt.throwOnFailure(r9)
            r9 = r5
            r5 = r0
            goto L62
        L42:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L4a:
            kotlin.ResultKt.throwOnFailure(r9)
            kotlinx.coroutines.sync.MutexImpl r9 = r5.batteryEventsBroadcastCacheMutex
            r0.L$0 = r5
            r0.L$1 = r6
            r0.L$2 = r7
            r0.L$3 = r8
            r0.L$4 = r9
            r0.label = r3
            java.lang.Object r0 = r9.lock(r0)
            if (r0 != r1) goto L62
            goto L90
        L62:
            r0 = 0
            androidx.collection.ArrayMap r1 = r5.batteryEventsBroadcastCache     // Catch: java.lang.Throwable -> L7b
            java.lang.String r2 = r6.indexKey     // Catch: java.lang.Throwable -> L7b
            java.lang.Object r1 = r1.get(r2)     // Catch: java.lang.Throwable -> L7b
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService$CachedBatteryEvents r1 = (com.google.android.systemui.power.batteryevent.domain.BatteryEventService.CachedBatteryEvents) r1     // Catch: java.lang.Throwable -> L7b
            if (r1 == 0) goto L7d
            int r2 = r7.batteryLevel     // Catch: java.lang.Throwable -> L7b
            int r4 = r7.pluggedType     // Catch: java.lang.Throwable -> L7b
            boolean r1 = r1.isEqual(r8, r2, r4)     // Catch: java.lang.Throwable -> L7b
            if (r1 != r3) goto L7d
            r1 = r0
            goto L8d
        L7b:
            r5 = move-exception
            goto L91
        L7d:
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService$CachedBatteryEvents r1 = new com.google.android.systemui.power.batteryevent.domain.BatteryEventService$CachedBatteryEvents     // Catch: java.lang.Throwable -> L7b
            int r2 = r7.batteryLevel     // Catch: java.lang.Throwable -> L7b
            int r7 = r7.pluggedType     // Catch: java.lang.Throwable -> L7b
            r1.<init>(r8, r2, r7)     // Catch: java.lang.Throwable -> L7b
            androidx.collection.ArrayMap r5 = r5.batteryEventsBroadcastCache     // Catch: java.lang.Throwable -> L7b
            java.lang.String r6 = r6.indexKey     // Catch: java.lang.Throwable -> L7b
            r5.put(r6, r1)     // Catch: java.lang.Throwable -> L7b
        L8d:
            r9.unlock(r0)
        L90:
            return r1
        L91:
            r9.unlock(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.power.batteryevent.domain.BatteryEventService.access$updateBatteryEventsBroadcastCache(com.google.android.systemui.power.batteryevent.domain.BatteryEventService, com.google.android.systemui.power.batteryevent.domain.BatteryEventService$BatteryEventsBroadcastData, com.google.android.systemui.power.batteryevent.common.BatteryEvents, java.util.Set, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    @Override // androidx.lifecycle.LifecycleService, android.app.Service
    public final IBinder onBind(Intent intent) {
        super.onBind(intent);
        Log.i("BatteryEventService", "BatteryEventService bound");
        return this.binder;
    }

    @Override // androidx.lifecycle.LifecycleService, android.app.Service
    public final void onCreate() {
        super.onCreate();
        BuildersKt.launch$default(LifecycleKt.getCoroutineScope(getLifecycle()), this.backgroundDispatcher, null, new BatteryEventService$onCreate$1(this, null), 2);
    }

    @Override // androidx.lifecycle.LifecycleService, android.app.Service
    public final void onDestroy() {
        super.onDestroy();
        CoroutineScopeKt.cancel(LifecycleOwnerKt.getLifecycleScope(this), ExceptionsKt.CancellationException("BatteryEventService destroyed", null));
        this.broadcastIntentBatteryEventsListener.clear();
        this.aidlBatteryEventsCallbackListener.kill();
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        return 1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /* JADX WARN: Type inference failed for: r4v6, types: [kotlinx.coroutines.sync.Mutex] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object updateBatteryEventsCallbackCache(com.google.android.systemui.power.batteryevent.domain.BatteryEventService.BatteryEventsCallbackData r5, com.google.android.systemui.power.batteryevent.common.BatteryEvents r6, com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener r7, kotlin.coroutines.jvm.internal.ContinuationImpl r8) {
        /*
            r4 = this;
            boolean r0 = r8 instanceof com.google.android.systemui.power.batteryevent.domain.BatteryEventService$updateBatteryEventsCallbackCache$1
            if (r0 == 0) goto L13
            r0 = r8
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService$updateBatteryEventsCallbackCache$1 r0 = (com.google.android.systemui.power.batteryevent.domain.BatteryEventService$updateBatteryEventsCallbackCache$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService$updateBatteryEventsCallbackCache$1 r0 = new com.google.android.systemui.power.batteryevent.domain.BatteryEventService$updateBatteryEventsCallbackCache$1
            r0.<init>(r4, r8)
        L18:
            java.lang.Object r8 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L47
            if (r2 != r3) goto L3f
            java.lang.Object r4 = r0.L$4
            kotlinx.coroutines.sync.Mutex r4 = (kotlinx.coroutines.sync.Mutex) r4
            java.lang.Object r5 = r0.L$3
            r7 = r5
            com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener r7 = (com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener) r7
            java.lang.Object r5 = r0.L$2
            r6 = r5
            com.google.android.systemui.power.batteryevent.common.BatteryEvents r6 = (com.google.android.systemui.power.batteryevent.common.BatteryEvents) r6
            java.lang.Object r5 = r0.L$1
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService$BatteryEventsCallbackData r5 = (com.google.android.systemui.power.batteryevent.domain.BatteryEventService.BatteryEventsCallbackData) r5
            java.lang.Object r0 = r0.L$0
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService r0 = (com.google.android.systemui.power.batteryevent.domain.BatteryEventService) r0
            kotlin.ResultKt.throwOnFailure(r8)
            r8 = r4
            r4 = r0
            goto L5f
        L3f:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L47:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlinx.coroutines.sync.MutexImpl r8 = r4.batteryEventsCallbackCacheMutex
            r0.L$0 = r4
            r0.L$1 = r5
            r0.L$2 = r6
            r0.L$3 = r7
            r0.L$4 = r8
            r0.label = r3
            java.lang.Object r0 = r8.lock(r0)
            if (r0 != r1) goto L5f
            return r1
        L5f:
            r0 = 0
            java.util.Set r5 = r5.subscribedEvents     // Catch: java.lang.Throwable -> L82
            java.lang.Iterable r5 = (java.lang.Iterable) r5     // Catch: java.lang.Throwable -> L82
            java.util.Set r1 = r6.eventTypes     // Catch: java.lang.Throwable -> L82
            java.lang.Iterable r1 = (java.lang.Iterable) r1     // Catch: java.lang.Throwable -> L82
            java.util.Set r5 = kotlin.collections.CollectionsKt.intersect(r5, r1)     // Catch: java.lang.Throwable -> L82
            androidx.collection.ArrayMap r1 = r4.batteryEventsCallbackCache     // Catch: java.lang.Throwable -> L82
            java.lang.Object r1 = r1.get(r7)     // Catch: java.lang.Throwable -> L82
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService$CachedBatteryEvents r1 = (com.google.android.systemui.power.batteryevent.domain.BatteryEventService.CachedBatteryEvents) r1     // Catch: java.lang.Throwable -> L82
            int r2 = r6.pluggedType
            int r6 = r6.batteryLevel
            if (r1 == 0) goto L84
            boolean r1 = r1.isEqual(r5, r6, r2)     // Catch: java.lang.Throwable -> L82
            if (r1 != r3) goto L84
            r1 = r0
            goto L8e
        L82:
            r4 = move-exception
            goto L92
        L84:
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService$CachedBatteryEvents r1 = new com.google.android.systemui.power.batteryevent.domain.BatteryEventService$CachedBatteryEvents     // Catch: java.lang.Throwable -> L82
            r1.<init>(r5, r6, r2)     // Catch: java.lang.Throwable -> L82
            androidx.collection.ArrayMap r4 = r4.batteryEventsCallbackCache     // Catch: java.lang.Throwable -> L82
            r4.put(r7, r1)     // Catch: java.lang.Throwable -> L82
        L8e:
            r8.unlock(r0)
            return r1
        L92:
            r8.unlock(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.power.batteryevent.domain.BatteryEventService.updateBatteryEventsCallbackCache(com.google.android.systemui.power.batteryevent.domain.BatteryEventService$BatteryEventsCallbackData, com.google.android.systemui.power.batteryevent.common.BatteryEvents, com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }
}
