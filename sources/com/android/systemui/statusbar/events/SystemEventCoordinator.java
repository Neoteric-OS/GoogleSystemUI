package com.android.systemui.statusbar.events;

import android.content.Context;
import com.android.systemui.display.domain.interactor.ConnectedDisplayInteractorImpl;
import com.android.systemui.display.domain.interactor.ConnectedDisplayInteractorImpl$special$$inlined$map$2;
import com.android.systemui.privacy.PrivacyItem;
import com.android.systemui.privacy.PrivacyItemController;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.StandaloneCoroutine;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SystemEventCoordinator {
    public final CoroutineScope appScope;
    public final BatteryController batteryController;
    public StandaloneCoroutine connectedDisplayCollectionJob;
    public final Context context;
    public final ConnectedDisplayInteractorImpl$special$$inlined$map$2 onDisplayConnectedFlow;
    public final PrivacyItemController privacyController;
    public SystemStatusAnimationSchedulerImpl scheduler;
    public final SystemClock systemClock;
    public final SystemEventCoordinator$batteryStateListener$1 batteryStateListener = new BatteryController.BatteryStateChangeCallback() { // from class: com.android.systemui.statusbar.events.SystemEventCoordinator$batteryStateListener$1
        public boolean plugged;
        public boolean stateKnown;

        @Override // com.android.systemui.statusbar.policy.BatteryController.BatteryStateChangeCallback
        public final void onBatteryLevelChanged(int i, boolean z, boolean z2) {
            boolean z3 = this.stateKnown;
            SystemEventCoordinator systemEventCoordinator = SystemEventCoordinator.this;
            if (!z3) {
                this.stateKnown = true;
                this.plugged = z;
                if (z) {
                    SystemStatusAnimationSchedulerImpl systemStatusAnimationSchedulerImpl = systemEventCoordinator.scheduler;
                    (systemStatusAnimationSchedulerImpl != null ? systemStatusAnimationSchedulerImpl : null).onStatusEvent(new BatteryEvent(i));
                    return;
                }
                return;
            }
            if (this.plugged != z) {
                this.plugged = z;
                if (z) {
                    SystemStatusAnimationSchedulerImpl systemStatusAnimationSchedulerImpl2 = systemEventCoordinator.scheduler;
                    (systemStatusAnimationSchedulerImpl2 != null ? systemStatusAnimationSchedulerImpl2 : null).onStatusEvent(new BatteryEvent(i));
                }
            }
        }
    };
    public final SystemEventCoordinator$privacyStateListener$1 privacyStateListener = new PrivacyItemController.Callback() { // from class: com.android.systemui.statusbar.events.SystemEventCoordinator$privacyStateListener$1
        public List currentPrivacyItems;
        public List previousPrivacyItems;
        public long timeLastEmpty;

        {
            EmptyList emptyList = EmptyList.INSTANCE;
            this.currentPrivacyItems = emptyList;
            this.previousPrivacyItems = emptyList;
            ((SystemClockImpl) SystemEventCoordinator.this.systemClock).getClass();
            this.timeLastEmpty = android.os.SystemClock.elapsedRealtime();
        }

        public static boolean uniqueItemsMatch(List list, List list2) {
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
            Iterator it = list.iterator();
            while (it.hasNext()) {
                PrivacyItem privacyItem = (PrivacyItem) it.next();
                arrayList.add(new Pair(Integer.valueOf(privacyItem.application.uid), privacyItem.privacyType.getPermGroupName()));
            }
            Set set = CollectionsKt.toSet(arrayList);
            ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10));
            Iterator it2 = list2.iterator();
            while (it2.hasNext()) {
                PrivacyItem privacyItem2 = (PrivacyItem) it2.next();
                arrayList2.add(new Pair(Integer.valueOf(privacyItem2.application.uid), privacyItem2.privacyType.getPermGroupName()));
            }
            return Intrinsics.areEqual(set, CollectionsKt.toSet(arrayList2));
        }

        /* JADX WARN: Code restructure failed: missing block: B:37:0x00bc, code lost:
        
            if ((android.os.SystemClock.elapsedRealtime() - r6.timeLastEmpty) >= 3000) goto L37;
         */
        /* JADX WARN: Removed duplicated region for block: B:40:0x00ea  */
        @Override // com.android.systemui.privacy.PrivacyItemController.Callback
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void onPrivacyItemsChanged(java.util.List r7) {
            /*
                r6 = this;
                java.util.List r0 = r6.currentPrivacyItems
                boolean r0 = uniqueItemsMatch(r7, r0)
                if (r0 == 0) goto L9
                return
            L9:
                boolean r0 = r7.isEmpty()
                com.android.systemui.statusbar.events.SystemEventCoordinator r1 = com.android.systemui.statusbar.events.SystemEventCoordinator.this
                if (r0 == 0) goto L22
                java.util.List r0 = r6.currentPrivacyItems
                r6.previousPrivacyItems = r0
                com.android.systemui.util.time.SystemClock r0 = r1.systemClock
                com.android.systemui.util.time.SystemClockImpl r0 = (com.android.systemui.util.time.SystemClockImpl) r0
                r0.getClass()
                long r2 = android.os.SystemClock.elapsedRealtime()
                r6.timeLastEmpty = r2
            L22:
                r6.currentPrivacyItems = r7
                boolean r7 = r7.isEmpty()
                r0 = 1
                r2 = 0
                r3 = 0
                if (r7 == 0) goto L89
                com.android.systemui.statusbar.events.SystemStatusAnimationSchedulerImpl r6 = r1.scheduler
                if (r6 != 0) goto L32
                r6 = r3
            L32:
                r6.getClass()
                com.android.systemui.util.Assert.isMainThread()
                kotlinx.coroutines.flow.StateFlowImpl r7 = r6.scheduledEvent
                java.lang.Object r1 = r7.getValue()
                com.android.systemui.statusbar.events.StatusEvent r1 = (com.android.systemui.statusbar.events.StatusEvent) r1
                if (r1 != 0) goto L43
                goto L46
            L43:
                r1.setForceVisible()
            L46:
                boolean r1 = r6.hasPersistentDot
                if (r1 != 0) goto L4c
                goto Lee
            L4c:
                r6.hasPersistentDot = r2
                kotlinx.coroutines.flow.StateFlowImpl r1 = r6.animationState
                java.lang.Object r4 = r1.getValue()
                java.lang.Number r4 = (java.lang.Number) r4
                int r4 = r4.intValue()
                r5 = 5
                if (r4 != r5) goto L78
                r6.notifyHidePersistentDot()
                java.lang.Object r6 = r7.getValue()
                if (r6 == 0) goto L6f
                java.lang.Integer r6 = java.lang.Integer.valueOf(r0)
                r1.updateState(r3, r6)
                goto Lee
            L6f:
                java.lang.Integer r6 = java.lang.Integer.valueOf(r2)
                r1.updateState(r3, r6)
                goto Lee
            L78:
                java.lang.Object r7 = r1.getValue()
                java.lang.Number r7 = (java.lang.Number) r7
                int r7 = r7.intValue()
                r0 = 4
                if (r7 != r0) goto Lee
                r6.notifyHidePersistentDot()
                goto Lee
            L89:
                android.content.Context r7 = r1.context
                android.content.res.Resources r7 = r7.getResources()
                r4 = 2131034143(0x7f05001f, float:1.7678795E38)
                boolean r7 = r7.getBoolean(r4)
                java.lang.String r4 = "privacy"
                java.lang.String r5 = "privacy_chip_animation_enabled"
                boolean r7 = android.provider.DeviceConfig.getBoolean(r4, r5, r7)
                if (r7 == 0) goto Lbf
                java.util.List r7 = r6.currentPrivacyItems
                java.util.List r4 = r6.previousPrivacyItems
                boolean r7 = uniqueItemsMatch(r7, r4)
                if (r7 == 0) goto Lc0
                com.android.systemui.util.time.SystemClock r7 = r1.systemClock
                com.android.systemui.util.time.SystemClockImpl r7 = (com.android.systemui.util.time.SystemClockImpl) r7
                r7.getClass()
                long r4 = android.os.SystemClock.elapsedRealtime()
                long r6 = r6.timeLastEmpty
                long r4 = r4 - r6
                r6 = 3000(0xbb8, double:1.482E-320)
                int r6 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
                if (r6 < 0) goto Lbf
                goto Lc0
            Lbf:
                r0 = r2
            Lc0:
                com.android.systemui.statusbar.events.PrivacyEvent r6 = new com.android.systemui.statusbar.events.PrivacyEvent
                r6.<init>(r0)
                com.android.systemui.statusbar.events.SystemEventCoordinator$privacyStateListener$1 r7 = r1.privacyStateListener
                java.util.List r7 = r7.currentPrivacyItems
                r6.privacyItems = r7
                com.android.systemui.privacy.PrivacyChipBuilder r0 = new com.android.systemui.privacy.PrivacyChipBuilder
                android.content.Context r2 = r1.context
                r0.<init>(r2, r7)
                java.lang.String r7 = r0.joinTypes()
                android.content.Context r0 = r1.context
                r2 = 2131953633(0x7f1307e1, float:1.9543743E38)
                java.lang.Object[] r7 = new java.lang.Object[]{r7}
                java.lang.String r7 = r0.getString(r2, r7)
                r6.contentDescription = r7
                com.android.systemui.statusbar.events.SystemStatusAnimationSchedulerImpl r7 = r1.scheduler
                if (r7 != 0) goto Lea
                goto Leb
            Lea:
                r3 = r7
            Leb:
                r3.onStatusEvent(r6)
            Lee:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.events.SystemEventCoordinator$privacyStateListener$1.onPrivacyItemsChanged(java.util.List):void");
        }
    };

    /* JADX WARN: Type inference failed for: r1v2, types: [com.android.systemui.statusbar.events.SystemEventCoordinator$batteryStateListener$1] */
    /* JADX WARN: Type inference failed for: r1v3, types: [com.android.systemui.statusbar.events.SystemEventCoordinator$privacyStateListener$1] */
    public SystemEventCoordinator(SystemClock systemClock, BatteryController batteryController, PrivacyItemController privacyItemController, Context context, CoroutineScope coroutineScope, ConnectedDisplayInteractorImpl connectedDisplayInteractorImpl) {
        this.systemClock = systemClock;
        this.batteryController = batteryController;
        this.privacyController = privacyItemController;
        this.context = context;
        this.appScope = coroutineScope;
        this.onDisplayConnectedFlow = connectedDisplayInteractorImpl.connectedDisplayAddition;
    }
}
