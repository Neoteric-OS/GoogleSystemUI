package com.android.systemui.statusbar.notification.collection.coordinator;

import com.android.systemui.statusbar.notification.collection.coordinator.HeadsUpCoordinator;
import com.android.systemui.util.time.SystemClockImpl;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class HeadsUpCoordinator$attach$1 {
    public final /* synthetic */ HeadsUpCoordinator $tmp0;

    public HeadsUpCoordinator$attach$1(HeadsUpCoordinator headsUpCoordinator) {
        this.$tmp0 = headsUpCoordinator;
    }

    public final void onBeforeTransformGroups() {
        final HeadsUpCoordinator headsUpCoordinator = this.$tmp0;
        ((SystemClockImpl) headsUpCoordinator.mSystemClock).getClass();
        headsUpCoordinator.mNow = System.currentTimeMillis();
        if (headsUpCoordinator.mPostedEntries.isEmpty()) {
            return;
        }
        HeadsUpCoordinatorKt.access$modifyHuns(headsUpCoordinator.mHeadsUpManager, new Function1() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.HeadsUpCoordinator$onBeforeTransformGroups$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                HunMutatorImpl hunMutatorImpl = (HunMutatorImpl) obj;
                List<HeadsUpCoordinator.PostedEntry> list = CollectionsKt.toList(HeadsUpCoordinator.this.mPostedEntries.values());
                HeadsUpCoordinator headsUpCoordinator2 = HeadsUpCoordinator.this;
                for (HeadsUpCoordinator.PostedEntry postedEntry : list) {
                    if (!postedEntry.entry.mSbn.isGroup()) {
                        HeadsUpCoordinator.access$handlePostedEntry(headsUpCoordinator2, postedEntry, hunMutatorImpl, "non-group");
                        headsUpCoordinator2.mPostedEntries.remove(postedEntry.key);
                    }
                }
                return Unit.INSTANCE;
            }
        });
    }
}
