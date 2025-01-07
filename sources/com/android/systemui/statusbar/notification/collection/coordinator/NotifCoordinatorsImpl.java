package com.android.systemui.statusbar.notification.collection.coordinator;

import com.android.systemui.flags.Flags;
import com.android.systemui.flags.UnreleasedFlag;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.PipelineDumpable;
import com.android.systemui.statusbar.notification.collection.PipelineDumper;
import com.android.systemui.statusbar.notification.collection.coordinator.RankingCoordinator;
import com.android.systemui.statusbar.notification.collection.provider.SectionStyleProvider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotifCoordinatorsImpl implements Coordinator, PipelineDumpable {
    public final List mCoordinators;
    public final List mCoreCoordinators;
    public final List mOrderedSections;

    public NotifCoordinatorsImpl(SectionStyleProvider sectionStyleProvider, DataStoreCoordinator dataStoreCoordinator, HideLocallyDismissedNotifsCoordinator hideLocallyDismissedNotifsCoordinator, HideNotifsForOtherUsersCoordinator hideNotifsForOtherUsersCoordinator, KeyguardCoordinator keyguardCoordinator, OriginalUnseenKeyguardCoordinator originalUnseenKeyguardCoordinator, RankingCoordinator rankingCoordinator, ColorizedFgsCoordinator colorizedFgsCoordinator, DeviceProvisionedCoordinator deviceProvisionedCoordinator, BubbleCoordinator bubbleCoordinator, HeadsUpCoordinator headsUpCoordinator, GutsCoordinator gutsCoordinator, ConversationCoordinator conversationCoordinator, DebugModeCoordinator debugModeCoordinator, GroupCountCoordinator groupCountCoordinator, GroupWhenCoordinator groupWhenCoordinator, MediaCoordinator mediaCoordinator, PreparationCoordinator preparationCoordinator, RemoteInputCoordinator remoteInputCoordinator, RowAlertTimeCoordinator rowAlertTimeCoordinator, RowAppearanceCoordinator rowAppearanceCoordinator, StackCoordinator stackCoordinator, ShadeEventCoordinator shadeEventCoordinator, SmartspaceDedupingCoordinator smartspaceDedupingCoordinator, ViewConfigCoordinator viewConfigCoordinator, VisualStabilityCoordinator visualStabilityCoordinator, SensitiveContentCoordinatorImpl sensitiveContentCoordinatorImpl, DismissibilityCoordinator dismissibilityCoordinator) {
        ArrayList arrayList = new ArrayList();
        this.mCoreCoordinators = arrayList;
        ArrayList arrayList2 = new ArrayList();
        this.mCoordinators = arrayList2;
        ArrayList arrayList3 = new ArrayList();
        this.mOrderedSections = arrayList3;
        arrayList.add(dataStoreCoordinator);
        arrayList2.add(hideLocallyDismissedNotifsCoordinator);
        arrayList2.add(hideNotifsForOtherUsersCoordinator);
        arrayList2.add(keyguardCoordinator);
        arrayList2.add(originalUnseenKeyguardCoordinator);
        arrayList2.add(rankingCoordinator);
        arrayList2.add(colorizedFgsCoordinator);
        arrayList2.add(deviceProvisionedCoordinator);
        arrayList2.add(bubbleCoordinator);
        arrayList2.add(debugModeCoordinator);
        arrayList2.add(conversationCoordinator);
        arrayList2.add(groupCountCoordinator);
        arrayList2.add(groupWhenCoordinator);
        arrayList2.add(mediaCoordinator);
        arrayList2.add(rowAlertTimeCoordinator);
        arrayList2.add(rowAppearanceCoordinator);
        arrayList2.add(stackCoordinator);
        arrayList2.add(shadeEventCoordinator);
        arrayList2.add(viewConfigCoordinator);
        arrayList2.add(visualStabilityCoordinator);
        arrayList2.add(sensitiveContentCoordinatorImpl);
        arrayList2.add(smartspaceDedupingCoordinator);
        arrayList2.add(headsUpCoordinator);
        arrayList2.add(gutsCoordinator);
        arrayList2.add(preparationCoordinator);
        arrayList2.add(remoteInputCoordinator);
        arrayList2.add(dismissibilityCoordinator);
        UnreleasedFlag unreleasedFlag = Flags.NULL_FLAG;
        arrayList3.add(headsUpCoordinator.sectioner);
        arrayList3.add(colorizedFgsCoordinator.mNotifSectioner);
        arrayList3.add(conversationCoordinator.priorityPeopleSectioner);
        arrayList3.add(conversationCoordinator.peopleAlertingSectioner);
        arrayList3.add(rankingCoordinator.mAlertingNotifSectioner);
        RankingCoordinator.AnonymousClass1 anonymousClass1 = rankingCoordinator.mSilentNotifSectioner;
        arrayList3.add(anonymousClass1);
        RankingCoordinator.AnonymousClass1 anonymousClass12 = rankingCoordinator.mMinimizedNotifSectioner;
        arrayList3.add(anonymousClass12);
        sectionStyleProvider.lowPrioritySections = CollectionsKt.toSet(Collections.singleton(anonymousClass12));
        sectionStyleProvider.silentSections = CollectionsKt.toSet(CollectionsKt__CollectionsKt.listOf(anonymousClass1, anonymousClass12));
    }

    @Override // com.android.systemui.statusbar.notification.collection.coordinator.Coordinator
    public final void attach(NotifPipeline notifPipeline) {
        Iterator it = this.mCoreCoordinators.iterator();
        while (it.hasNext()) {
            ((DataStoreCoordinator) it.next()).attach(notifPipeline);
        }
        Iterator it2 = this.mCoordinators.iterator();
        while (it2.hasNext()) {
            ((Coordinator) it2.next()).attach(notifPipeline);
        }
        notifPipeline.mShadeListBuilder.setSectioners(this.mOrderedSections);
    }

    @Override // com.android.systemui.statusbar.notification.collection.PipelineDumpable
    public final void dumpPipeline(PipelineDumper pipelineDumper) {
        pipelineDumper.dump(this.mCoreCoordinators, "core coordinators");
        pipelineDumper.dump(this.mCoordinators, "normal coordinators");
    }
}
