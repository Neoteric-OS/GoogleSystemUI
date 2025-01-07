package com.android.systemui.statusbar.notification.init;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.SystemClock;
import android.os.Trace;
import android.service.notification.SnoozeCriterion;
import android.service.notification.StatusBarNotification;
import android.util.ArrayMap;
import android.util.Log;
import com.android.app.tracing.TraceUtilsKt;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.people.widget.PeopleSpaceWidgetManager;
import com.android.systemui.plugins.statusbar.NotificationSwipeActionHelper;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.NotificationListener;
import com.android.systemui.statusbar.NotificationMediaManager;
import com.android.systemui.statusbar.notification.AnimatedImageNotificationManager;
import com.android.systemui.statusbar.notification.AnimatedImageNotificationManager$bind$3;
import com.android.systemui.statusbar.notification.NotifPipelineFlags;
import com.android.systemui.statusbar.notification.NotificationClicker;
import com.android.systemui.statusbar.notification.NotificationSectionsFeatureManager;
import com.android.systemui.statusbar.notification.collection.NotifCollection;
import com.android.systemui.statusbar.notification.collection.NotifCollection$$ExternalSyntheticLambda4;
import com.android.systemui.statusbar.notification.collection.NotifLiveDataStoreImpl;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.ShadeListBuilder;
import com.android.systemui.statusbar.notification.collection.TargetSdkResolver;
import com.android.systemui.statusbar.notification.collection.coalescer.GroupCoalescer;
import com.android.systemui.statusbar.notification.collection.coordinator.HeadsUpCoordinator$attach$1;
import com.android.systemui.statusbar.notification.collection.coordinator.VisualStabilityCoordinator;
import com.android.systemui.statusbar.notification.collection.inflation.NotificationRowBinderImpl;
import com.android.systemui.statusbar.notification.collection.init.NotifPipelineInitializer;
import com.android.systemui.statusbar.notification.collection.listbuilder.NotifSection;
import com.android.systemui.statusbar.notification.collection.listbuilder.OnAfterRenderListListener;
import com.android.systemui.statusbar.notification.collection.listbuilder.OnBeforeFinalizeFilterListener;
import com.android.systemui.statusbar.notification.collection.listbuilder.OnBeforeRenderListListener;
import com.android.systemui.statusbar.notification.collection.listbuilder.PipelineState;
import com.android.systemui.statusbar.notification.collection.listbuilder.SemiStableSort;
import com.android.systemui.statusbar.notification.collection.listbuilder.ShadeListBuilderLogger;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifPromoter;
import com.android.systemui.statusbar.notification.collection.notifcollection.CommonNotifCollection;
import com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionInconsistencyTracker;
import com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener;
import com.android.systemui.statusbar.notification.collection.provider.SectionHeaderVisibilityProvider;
import com.android.systemui.statusbar.notification.collection.render.MediaContainerController;
import com.android.systemui.statusbar.notification.collection.render.NodeSpecBuilderLogger;
import com.android.systemui.statusbar.notification.collection.render.NotifViewBarn;
import com.android.systemui.statusbar.notification.collection.render.RenderStageManager;
import com.android.systemui.statusbar.notification.collection.render.RenderStageManager$attach$1;
import com.android.systemui.statusbar.notification.collection.render.ShadeViewDifferLogger;
import com.android.systemui.statusbar.notification.collection.render.ShadeViewManager;
import com.android.systemui.statusbar.notification.collection.render.ShadeViewManager$viewRenderer$1;
import com.android.systemui.statusbar.notification.icon.IconManager;
import com.android.systemui.statusbar.notification.interruption.HeadsUpViewBinder;
import com.android.systemui.statusbar.notification.logging.NotificationLogger;
import com.android.systemui.statusbar.notification.logging.NotificationLogger$$ExternalSyntheticLambda2;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.NotifBindPipelineInitializer;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainer;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.notification.stack.NotificationSwipeHelper;
import com.android.systemui.statusbar.phone.CentralSurfaces;
import com.android.systemui.statusbar.phone.StatusBarNotificationActivityStarter;
import com.android.systemui.statusbar.phone.StatusBarNotificationPresenter;
import com.android.systemui.statusbar.policy.BaseHeadsUpManager;
import com.android.systemui.statusbar.policy.OnHeadsUpChangedListener;
import com.android.systemui.util.Assert;
import com.android.systemui.util.NamedListenerSet;
import com.android.systemui.util.time.SystemClockImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__MutableCollectionsJVMKt;
import kotlin.comparisons.ComparisonsKt___ComparisonsJvmKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationsControllerImpl implements NotificationsController {
    public final AnimatedImageNotificationManager animatedImageNotificationManager;
    public final Optional bubblesOptional;
    public final NotificationClicker.Builder clickerBuilder;
    public final Lazy commonNotifCollection;
    public final HeadsUpViewBinder headsUpViewBinder;
    public final NotifBindPipelineInitializer notifBindPipelineInitializer;
    public final NotifLiveDataStoreImpl notifLiveDataStore;
    public final Lazy notifPipeline;
    public final Lazy notifPipelineInitializer;
    public final NotificationListener notificationListener;
    public final Optional notificationLoggerOptional;
    public final NotificationRowBinderImpl notificationRowBinder;
    public final NotificationMediaManager notificationsMediaManager;
    public final PeopleSpaceWidgetManager peopleSpaceWidgetManager;
    public final TargetSdkResolver targetSdkResolver;

    public NotificationsControllerImpl(NotificationListener notificationListener, Lazy lazy, Lazy lazy2, NotifLiveDataStoreImpl notifLiveDataStoreImpl, TargetSdkResolver targetSdkResolver, Lazy lazy3, NotifBindPipelineInitializer notifBindPipelineInitializer, Optional optional, NotificationRowBinderImpl notificationRowBinderImpl, NotificationMediaManager notificationMediaManager, HeadsUpViewBinder headsUpViewBinder, NotificationClicker.Builder builder, AnimatedImageNotificationManager animatedImageNotificationManager, PeopleSpaceWidgetManager peopleSpaceWidgetManager, Optional optional2) {
        this.notificationListener = notificationListener;
        this.commonNotifCollection = lazy;
        this.notifPipeline = lazy2;
        this.notifLiveDataStore = notifLiveDataStoreImpl;
        this.targetSdkResolver = targetSdkResolver;
        this.notifPipelineInitializer = lazy3;
        this.notifBindPipelineInitializer = notifBindPipelineInitializer;
        this.notificationLoggerOptional = optional;
        this.notificationRowBinder = notificationRowBinderImpl;
        this.notificationsMediaManager = notificationMediaManager;
        this.headsUpViewBinder = headsUpViewBinder;
        this.clickerBuilder = builder;
        this.animatedImageNotificationManager = animatedImageNotificationManager;
        this.peopleSpaceWidgetManager = peopleSpaceWidgetManager;
        this.bubblesOptional = optional2;
    }

    @Override // com.android.systemui.statusbar.notification.init.NotificationsController
    public final int getActiveNotificationsCount() {
        return ((Number) this.notifLiveDataStore.activeNotifCount.atomicValue.get()).intValue();
    }

    @Override // com.android.systemui.statusbar.notification.init.NotificationsController
    public final void initialize(StatusBarNotificationPresenter statusBarNotificationPresenter, final NotificationStackScrollLayoutController.NotificationListContainerImpl notificationListContainerImpl, NotificationStackScrollLayoutController.NotifStackControllerImpl notifStackControllerImpl, StatusBarNotificationActivityStarter statusBarNotificationActivityStarter) {
        NotificationListener notificationListener = this.notificationListener;
        notificationListener.registerAsSystemService();
        Lazy lazy = this.notifPipeline;
        ((NotifPipeline) lazy.get()).addCollectionListener(new NotifCollectionListener() { // from class: com.android.systemui.statusbar.notification.init.NotificationsControllerImpl$initialize$1
            @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener
            public final void onEntryRemoved(NotificationEntry notificationEntry, int i) {
                NotificationStackScrollLayout notificationStackScrollLayout = NotificationStackScrollLayoutController.this.mView;
                notificationStackScrollLayout.getClass();
                ExpandableNotificationRow expandableNotificationRow = notificationEntry.row;
                NotificationSwipeHelper notificationSwipeHelper = notificationStackScrollLayout.mSwipeHelper;
                if (expandableNotificationRow == notificationSwipeHelper.mTranslatingParentView) {
                    notificationSwipeHelper.setTranslatingParentView(null);
                }
            }
        });
        Optional optional = this.bubblesOptional;
        NotificationClicker.Builder builder = this.clickerBuilder;
        NotificationClicker notificationClicker = new NotificationClicker(builder.mLogger, builder.mPowerInteractor, optional, statusBarNotificationActivityStarter);
        NotificationRowBinderImpl notificationRowBinderImpl = this.notificationRowBinder;
        notificationRowBinderImpl.mNotificationClicker = notificationClicker;
        notificationRowBinderImpl.mPresenter = statusBarNotificationPresenter;
        notificationRowBinderImpl.mListContainer = notificationListContainerImpl;
        IconManager iconManager = notificationRowBinderImpl.mIconManager;
        ((NotifPipeline) iconManager.notifCollection).addCollectionListener(iconManager.entryListener);
        this.headsUpViewBinder.mNotificationPresenter = statusBarNotificationPresenter;
        this.notifBindPipelineInitializer.initialize();
        final AnimatedImageNotificationManager animatedImageNotificationManager = this.animatedImageNotificationManager;
        animatedImageNotificationManager.getClass();
        ((BaseHeadsUpManager) animatedImageNotificationManager.headsUpManager).addListener(new OnHeadsUpChangedListener() { // from class: com.android.systemui.statusbar.notification.AnimatedImageNotificationManager$bind$1
            @Override // com.android.systemui.statusbar.policy.OnHeadsUpChangedListener
            public final void onHeadsUpStateChanged(NotificationEntry notificationEntry, boolean z) {
                AnimatedImageNotificationManager.access$updateAnimatedImageDrawables(AnimatedImageNotificationManager.this, notificationEntry);
            }
        });
        animatedImageNotificationManager.statusBarStateController.addCallback(new StatusBarStateController.StateListener() { // from class: com.android.systemui.statusbar.notification.AnimatedImageNotificationManager$bind$2
            @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
            public final void onExpandedChanged(boolean z) {
                AnimatedImageNotificationManager animatedImageNotificationManager2 = AnimatedImageNotificationManager.this;
                animatedImageNotificationManager2.isStatusBarExpanded = z;
                Iterator it = ((NotifPipeline) animatedImageNotificationManager2.notifCollection).getAllNotifs().iterator();
                while (it.hasNext()) {
                    AnimatedImageNotificationManager.access$updateAnimatedImageDrawables(animatedImageNotificationManager2, (NotificationEntry) it.next());
                }
            }
        });
        animatedImageNotificationManager.bindEventManager.listeners.listeners.addIfAbsent(new AnimatedImageNotificationManager$bind$3(animatedImageNotificationManager));
        NotifPipelineInitializer notifPipelineInitializer = (NotifPipelineInitializer) this.notifPipelineInitializer.get();
        DumpManager dumpManager = notifPipelineInitializer.mDumpManager;
        dumpManager.getClass();
        DumpManager.registerDumpable$default(dumpManager, "NotifPipeline", notifPipelineInitializer);
        notifPipelineInitializer.mNotificationService = notificationListener;
        notifPipelineInitializer.mNotifInflater.mNotificationRowBinder = notificationRowBinderImpl;
        notifPipelineInitializer.mNotifPluggableCoordinators.attach(notifPipelineInitializer.mPipelineWrapper);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider = notifPipelineInitializer.mShadeViewManagerFactory.this$0;
        Context context = switchingProvider.sysUIGoogleGlobalRootComponentImpl.context;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl;
        MediaContainerController mediaContainerController = (MediaContainerController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.mediaContainerControllerProvider.get();
        NotificationSectionsFeatureManager notificationSectionsFeatureManager = (NotificationSectionsFeatureManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationSectionsFeatureManagerProvider.get();
        SectionHeaderVisibilityProvider sectionHeaderVisibilityProvider = (SectionHeaderVisibilityProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sectionHeaderVisibilityProvider.get();
        NotifPipelineFlags notifPipelineFlags = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notifPipelineFlags();
        ShadeViewManager shadeViewManager = new ShadeViewManager(context, notificationListContainerImpl, notifStackControllerImpl, mediaContainerController, notificationSectionsFeatureManager, sectionHeaderVisibilityProvider, new NodeSpecBuilderLogger(notifPipelineFlags), new ShadeViewDifferLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideNotificationsLogBufferProvider.get()), (NotifViewBarn) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notifViewBarnProvider.get());
        notifPipelineInitializer.mShadeViewManager = shadeViewManager;
        ShadeViewManager$viewRenderer$1 shadeViewManager$viewRenderer$1 = shadeViewManager.viewRenderer;
        RenderStageManager renderStageManager = notifPipelineInitializer.mRenderStageManager;
        renderStageManager.viewRenderer = shadeViewManager$viewRenderer$1;
        RenderStageManager$attach$1 renderStageManager$attach$1 = new RenderStageManager$attach$1(renderStageManager);
        Assert.isMainThread();
        final ShadeListBuilder shadeListBuilder = notifPipelineInitializer.mListBuilder;
        shadeListBuilder.mPipelineState.requireState();
        shadeListBuilder.mOnRenderListListener = renderStageManager$attach$1;
        Assert.isMainThread();
        DumpManager dumpManager2 = shadeListBuilder.mDumpManager;
        dumpManager2.getClass();
        DumpManager.registerDumpable$default(dumpManager2, "ShadeListBuilder", shadeListBuilder);
        NotifCollection notifCollection = notifPipelineInitializer.mNotifCollection;
        notifCollection.getClass();
        Assert.isMainThread();
        NamedListenerSet namedListenerSet = notifCollection.mNotifCollectionListeners;
        namedListenerSet.listeners.addIfAbsent(new NamedListenerSet.NamedListener(namedListenerSet, shadeListBuilder.mInteractionTracker));
        Assert.isMainThread();
        notifCollection.mBuildListener = shadeListBuilder.mReadyForBuildListener;
        shadeListBuilder.mChoreographer.listeners.addIfAbsent(new Runnable() { // from class: com.android.systemui.statusbar.notification.collection.ShadeListBuilder$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                SemiStableSort.Companion companion;
                final ShadeListBuilder$$ExternalSyntheticLambda0 shadeListBuilder$$ExternalSyntheticLambda0;
                SemiStableSort semiStableSort;
                boolean isSorted;
                final ShadeListBuilder shadeListBuilder2 = ShadeListBuilder.this;
                shadeListBuilder2.getClass();
                Trace.beginSection("ShadeListBuilder.buildList");
                PipelineState pipelineState = shadeListBuilder2.mPipelineState;
                if (pipelineState.mState >= 1) {
                    throw new IllegalStateException("Required state is <1 but actual state is " + pipelineState.mState);
                }
                Collection collection = shadeListBuilder2.mPendingEntries;
                List list = null;
                if (collection != null) {
                    shadeListBuilder2.mAllEntries = collection;
                    shadeListBuilder2.mPendingEntries = null;
                }
                VisualStabilityCoordinator visualStabilityCoordinator = VisualStabilityCoordinator.this;
                boolean z = visualStabilityCoordinator.mIsSuppressingPipelineRun;
                boolean z2 = visualStabilityCoordinator.mPipelineRunAllowed;
                visualStabilityCoordinator.mIsSuppressingPipelineRun = z | (!z2);
                ShadeListBuilderLogger shadeListBuilderLogger = shadeListBuilder2.mLogger;
                if (!z2) {
                    shadeListBuilderLogger.logPipelineRunSuppressed();
                    Trace.endSection();
                    return;
                }
                pipelineState.mState = 1;
                pipelineState.incrementTo(2);
                for (GroupEntry groupEntry : ((ArrayMap) shadeListBuilder2.mGroups).values()) {
                    groupEntry.beginNewAttachState();
                    groupEntry.mChildren.clear();
                    groupEntry.mSummary = null;
                }
                Iterator it = shadeListBuilder2.mAllEntries.iterator();
                while (it.hasNext()) {
                    ((NotificationEntry) it.next()).beginNewAttachState();
                }
                shadeListBuilder2.mNotifList.clear();
                shadeListBuilder2.getStabilityManager().onBeginRun$1();
                pipelineState.incrementTo(3);
                shadeListBuilder2.filterNotifs(shadeListBuilder2.mAllEntries, shadeListBuilder2.mNotifList, shadeListBuilder2.mNotifPreGroupFilters);
                pipelineState.incrementTo(4);
                List list2 = shadeListBuilder2.mNotifList;
                List list3 = shadeListBuilder2.mNewNotifList;
                Trace.beginSection("ShadeListBuilder.groupNotifs");
                Iterator it2 = list2.iterator();
                while (it2.hasNext()) {
                    NotificationEntry notificationEntry = (NotificationEntry) ((ListEntry) it2.next());
                    boolean isGroup = notificationEntry.mSbn.isGroup();
                    ListAttachState listAttachState = notificationEntry.mAttachState;
                    if (isGroup) {
                        String groupKey = notificationEntry.mSbn.getGroupKey();
                        GroupEntry groupEntry2 = (GroupEntry) ((ArrayMap) shadeListBuilder2.mGroups).get(groupKey);
                        if (groupEntry2 == null) {
                            ((SystemClockImpl) shadeListBuilder2.mSystemClock).getClass();
                            groupEntry2 = new GroupEntry(SystemClock.uptimeMillis(), groupKey);
                            ((ArrayMap) shadeListBuilder2.mGroups).put(groupKey, groupEntry2);
                        }
                        ListAttachState listAttachState2 = groupEntry2.mAttachState;
                        if (listAttachState2.parent == null) {
                            listAttachState2.parent = GroupEntry.ROOT_ENTRY;
                            list3.add(groupEntry2);
                        }
                        listAttachState.parent = groupEntry2;
                        if (notificationEntry.mSbn.getNotification().isGroupSummary()) {
                            NotificationEntry notificationEntry2 = groupEntry2.mSummary;
                            if (notificationEntry2 == null) {
                                groupEntry2.mSummary = notificationEntry;
                            } else {
                                shadeListBuilderLogger.logDuplicateSummary(shadeListBuilder2.mIterationCount, groupEntry2, notificationEntry2, notificationEntry);
                                if (notificationEntry.mSbn.getPostTime() > notificationEntry2.mSbn.getPostTime()) {
                                    groupEntry2.mSummary = notificationEntry;
                                    ShadeListBuilder.annulAddition(notificationEntry2, list3);
                                } else {
                                    ShadeListBuilder.annulAddition(notificationEntry, list3);
                                }
                            }
                        } else {
                            groupEntry2.mChildren.add(notificationEntry);
                        }
                    } else {
                        ArrayMap arrayMap = (ArrayMap) shadeListBuilder2.mGroups;
                        String str = notificationEntry.mKey;
                        if (arrayMap.containsKey(str)) {
                            shadeListBuilderLogger.logDuplicateTopLevelKey(shadeListBuilder2.mIterationCount, str);
                        } else {
                            listAttachState.parent = GroupEntry.ROOT_ENTRY;
                            list3.add(notificationEntry);
                        }
                    }
                }
                Trace.endSection();
                shadeListBuilder2.applyNewNotifList();
                shadeListBuilder2.pruneIncompleteGroups(shadeListBuilder2.mNotifList);
                final List list4 = shadeListBuilder2.mReadOnlyNotifList;
                Trace.beginSection("ShadeListBuilder.dispatchOnBeforeTransformGroups");
                final int i = 0;
                shadeListBuilder2.mOnBeforeTransformGroupsListeners.forEachTraced(new Consumer() { // from class: com.android.systemui.statusbar.notification.collection.ShadeListBuilder$$ExternalSyntheticLambda11
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        switch (i) {
                            case 0:
                                ((HeadsUpCoordinator$attach$1) obj).onBeforeTransformGroups();
                                break;
                            case 1:
                                ((OnBeforeRenderListListener) obj).onBeforeRenderList$1(list4);
                                break;
                            default:
                                ((OnBeforeFinalizeFilterListener) obj).onBeforeFinalizeFilter(list4);
                                break;
                        }
                    }
                });
                Trace.endSection();
                pipelineState.incrementTo(5);
                List list5 = shadeListBuilder2.mNotifList;
                Trace.beginSection("ShadeListBuilder.promoteNotifs");
                int i2 = 0;
                while (true) {
                    final ArrayList arrayList = (ArrayList) list5;
                    if (i2 >= arrayList.size()) {
                        break;
                    }
                    ListEntry listEntry = (ListEntry) arrayList.get(i2);
                    if (listEntry instanceof GroupEntry) {
                        ((ArrayList) ((GroupEntry) listEntry).mChildren).removeIf(new Predicate() { // from class: com.android.systemui.statusbar.notification.collection.ShadeListBuilder$$ExternalSyntheticLambda15
                            @Override // java.util.function.Predicate
                            public final boolean test(Object obj) {
                                NotifPromoter notifPromoter;
                                ShadeListBuilder shadeListBuilder3 = ShadeListBuilder.this;
                                List list6 = arrayList;
                                NotificationEntry notificationEntry3 = (NotificationEntry) obj;
                                int i3 = 0;
                                while (true) {
                                    if (i3 >= ((ArrayList) shadeListBuilder3.mNotifPromoters).size()) {
                                        notifPromoter = null;
                                        break;
                                    }
                                    notifPromoter = (NotifPromoter) ((ArrayList) shadeListBuilder3.mNotifPromoters).get(i3);
                                    if (notifPromoter.shouldPromoteToTopLevel(notificationEntry3)) {
                                        break;
                                    }
                                    i3++;
                                }
                                ListAttachState listAttachState3 = notificationEntry3.mAttachState;
                                listAttachState3.promoter = notifPromoter;
                                boolean z3 = notifPromoter != null;
                                if (z3) {
                                    listAttachState3.parent = GroupEntry.ROOT_ENTRY;
                                    list6.add(notificationEntry3);
                                }
                                return z3;
                            }
                        });
                    }
                    i2++;
                }
                Trace.endSection();
                shadeListBuilder2.pruneIncompleteGroups(shadeListBuilder2.mNotifList);
                pipelineState.incrementTo(6);
                List list6 = shadeListBuilder2.mNotifList;
                if (!shadeListBuilder2.getStabilityManager().isEveryChangeAllowed()) {
                    Trace.beginSection("ShadeListBuilder.stabilizeGroupingNotifs");
                    int i3 = 0;
                    while (true) {
                        ArrayList arrayList2 = (ArrayList) list6;
                        if (i3 >= arrayList2.size()) {
                            break;
                        }
                        ListEntry listEntry2 = (ListEntry) arrayList2.get(i3);
                        if (listEntry2 instanceof GroupEntry) {
                            GroupEntry groupEntry3 = (GroupEntry) listEntry2;
                            List list7 = groupEntry3.mChildren;
                            int i4 = 0;
                            while (i4 < groupEntry3.mUnmodifiableChildren.size()) {
                                ArrayList arrayList3 = (ArrayList) list7;
                                if (shadeListBuilder2.maybeSuppressGroupChange((NotificationEntry) arrayList3.get(i4), arrayList2)) {
                                    arrayList3.remove(i4);
                                    i4--;
                                }
                                i4++;
                            }
                        } else if (shadeListBuilder2.maybeSuppressGroupChange(listEntry2.getRepresentativeEntry(), arrayList2)) {
                            arrayList2.remove(i3);
                            i3--;
                        }
                        i3++;
                    }
                    Trace.endSection();
                }
                Trace.beginSection("ShadeListBuilder.dispatchOnBeforeSort");
                shadeListBuilder2.mOnBeforeSortListeners.forEachTraced(new ShadeListBuilder$$ExternalSyntheticLambda13());
                Trace.endSection();
                pipelineState.incrementTo(7);
                Trace.beginSection("ShadeListBuilder.assignSections");
                for (ListEntry listEntry3 : shadeListBuilder2.mNotifList) {
                    for (int i5 = 0; i5 < ((ArrayList) shadeListBuilder2.mNotifSections).size(); i5++) {
                        NotifSection notifSection = (NotifSection) ((ArrayList) shadeListBuilder2.mNotifSections).get(i5);
                        if (notifSection.sectioner.isInSection(listEntry3)) {
                            ListAttachState listAttachState3 = listEntry3.mPreviousAttachState;
                            GroupEntry groupEntry4 = listAttachState3.parent;
                            ListAttachState listAttachState4 = listEntry3.mAttachState;
                            if (groupEntry4 != null && notifSection != listAttachState3.section && !shadeListBuilder2.getStabilityManager().isSectionChangeAllowed(listEntry3.getRepresentativeEntry())) {
                                listAttachState4.suppressedChanges.section = notifSection;
                                notifSection = listAttachState3.section;
                            }
                            listAttachState4.section = notifSection;
                            NotificationEntry representativeEntry = listEntry3.getRepresentativeEntry();
                            if (representativeEntry != null) {
                                representativeEntry.mAttachState.section = notifSection;
                                if (notifSection != null) {
                                    representativeEntry.mBucket = notifSection.bucket;
                                }
                            }
                            if (listEntry3 instanceof GroupEntry) {
                                for (NotificationEntry notificationEntry3 : ((GroupEntry) listEntry3).mUnmodifiableChildren) {
                                    notificationEntry3.mAttachState.section = notifSection;
                                    notificationEntry3.mAttachState.section = notifSection;
                                    if (notifSection != null) {
                                        notificationEntry3.mBucket = notifSection.bucket;
                                    }
                                }
                            }
                        }
                    }
                    throw new RuntimeException("Missing default sectioner!");
                }
                Trace.endSection();
                Trace.beginSection("ShadeListBuilder.notifySectionEntriesUpdated");
                shadeListBuilder2.mTempSectionMembers.clear();
                for (NotifSection notifSection2 : shadeListBuilder2.mNotifSections) {
                    for (ListEntry listEntry4 : shadeListBuilder2.mNotifList) {
                        if (notifSection2 == listEntry4.mAttachState.section) {
                            shadeListBuilder2.mTempSectionMembers.add(listEntry4);
                        }
                    }
                    Trace.beginSection(notifSection2.label);
                    notifSection2.sectioner.onEntriesUpdated(shadeListBuilder2.mTempSectionMembers);
                    Trace.endSection();
                    shadeListBuilder2.mTempSectionMembers.clear();
                }
                Trace.endSection();
                Trace.beginSection("ShadeListBuilder.sortListAndGroups");
                Iterator it3 = shadeListBuilder2.mNotifList.iterator();
                boolean z3 = true;
                while (true) {
                    boolean hasNext = it3.hasNext();
                    companion = SemiStableSort.Companion;
                    shadeListBuilder$$ExternalSyntheticLambda0 = shadeListBuilder2.mStableOrder;
                    semiStableSort = shadeListBuilder2.mSemiStableSort;
                    if (!hasNext) {
                        break;
                    }
                    ListEntry listEntry5 = (ListEntry) it3.next();
                    if (listEntry5 instanceof GroupEntry) {
                        List list8 = ((GroupEntry) listEntry5).mChildren;
                        boolean isEveryChangeAllowed = shadeListBuilder2.getStabilityManager().isEveryChangeAllowed();
                        ShadeListBuilder$$ExternalSyntheticLambda2 shadeListBuilder$$ExternalSyntheticLambda2 = shadeListBuilder2.mGroupChildrenComparator;
                        if (isEveryChangeAllowed) {
                            ((ArrayList) list8).sort(shadeListBuilder$$ExternalSyntheticLambda2);
                            isSorted = true;
                        } else {
                            ((ArrayList) semiStableSort.preallocatedWorkspace$delegate.getValue()).clear();
                            ArrayList arrayList4 = (ArrayList) semiStableSort.preallocatedWorkspace$delegate.getValue();
                            List list9 = arrayList4.isEmpty() ? arrayList4 : list;
                            if (list9 == null) {
                                list9 = arrayList4.subList(arrayList4.size(), arrayList4.size());
                            }
                            for (Object obj : list8) {
                                if (shadeListBuilder$$ExternalSyntheticLambda0.getRank(obj) != null) {
                                    list9.add(obj);
                                }
                            }
                            if (list9.size() > 1) {
                                final int i6 = 0;
                                CollectionsKt__MutableCollectionsJVMKt.sortWith(list9, new Comparator() { // from class: com.android.systemui.statusbar.notification.collection.listbuilder.SemiStableSort$sortTo$$inlined$sortBy$1
                                    @Override // java.util.Comparator
                                    public final int compare(Object obj2, Object obj3) {
                                        switch (i6) {
                                            case 0:
                                                Integer rank = shadeListBuilder$$ExternalSyntheticLambda0.getRank(obj2);
                                                Intrinsics.checkNotNull(rank);
                                                Integer rank2 = shadeListBuilder$$ExternalSyntheticLambda0.getRank(obj3);
                                                Intrinsics.checkNotNull(rank2);
                                                return ComparisonsKt___ComparisonsJvmKt.compareValues(rank, rank2);
                                            default:
                                                Integer rank3 = shadeListBuilder$$ExternalSyntheticLambda0.getRank(obj2);
                                                Intrinsics.checkNotNull(rank3);
                                                Integer rank4 = shadeListBuilder$$ExternalSyntheticLambda0.getRank(obj3);
                                                Intrinsics.checkNotNull(rank4);
                                                return ComparisonsKt___ComparisonsJvmKt.compareValues(rank3, rank4);
                                        }
                                    }
                                });
                            }
                            isSorted = companion.isSorted(list9, shadeListBuilder$$ExternalSyntheticLambda2);
                            semiStableSort.getPreallocatedAdditions().clear();
                            ArrayList preallocatedAdditions = semiStableSort.getPreallocatedAdditions();
                            for (Object obj2 : list8) {
                                if (shadeListBuilder$$ExternalSyntheticLambda0.getRank(obj2) == null) {
                                    preallocatedAdditions.add(obj2);
                                }
                            }
                            CollectionsKt__MutableCollectionsJVMKt.sortWith(preallocatedAdditions, shadeListBuilder$$ExternalSyntheticLambda2);
                            SemiStableSort.Companion.access$insertPreSortedElementsWithFewestMisOrderings(list9, preallocatedAdditions, shadeListBuilder$$ExternalSyntheticLambda2);
                            semiStableSort.getPreallocatedAdditions().clear();
                            list8.clear();
                            list8.addAll(arrayList4);
                        }
                        z3 &= isSorted;
                    }
                    list = null;
                }
                ((ArrayList) shadeListBuilder2.mNotifList).sort(shadeListBuilder2.mTopLevelComparator);
                if (!shadeListBuilder2.getStabilityManager().isEveryChangeAllowed()) {
                    List list10 = shadeListBuilder2.mNotifList;
                    ArrayList<List> arrayList5 = new ArrayList();
                    ArrayList arrayList6 = (ArrayList) list10;
                    int size = arrayList6.size();
                    int i7 = 0;
                    Integer num = null;
                    for (int i8 = 0; i8 < size; i8++) {
                        NotifSection notifSection3 = ((ListEntry) arrayList6.get(i8)).mAttachState.section;
                        Integer valueOf = Integer.valueOf(notifSection3 != null ? notifSection3.index : -1);
                        if (num != null) {
                            if (!num.equals(valueOf)) {
                                if (i8 - i7 >= 1) {
                                    arrayList5.add(arrayList6.subList(i7, i8));
                                }
                                i7 = i8;
                            }
                        }
                        num = valueOf;
                    }
                    if (size - i7 >= 1) {
                        arrayList5.add(arrayList6.subList(i7, size));
                    }
                    for (List list11 : arrayList5) {
                        List list12 = shadeListBuilder2.mNewNotifList;
                        semiStableSort.getClass();
                        List list13 = list12.isEmpty() ? list12 : null;
                        if (list13 == null) {
                            ArrayList arrayList7 = (ArrayList) list12;
                            list13 = list12.subList(arrayList7.size(), arrayList7.size());
                        }
                        for (Object obj3 : list11) {
                            if (shadeListBuilder$$ExternalSyntheticLambda0.getRank(obj3) != null) {
                                list13.add(obj3);
                            }
                        }
                        final int i9 = 1;
                        Comparator comparator = new Comparator() { // from class: com.android.systemui.statusbar.notification.collection.listbuilder.SemiStableSort$sortTo$$inlined$sortBy$1
                            @Override // java.util.Comparator
                            public final int compare(Object obj22, Object obj32) {
                                switch (i9) {
                                    case 0:
                                        Integer rank = shadeListBuilder$$ExternalSyntheticLambda0.getRank(obj22);
                                        Intrinsics.checkNotNull(rank);
                                        Integer rank2 = shadeListBuilder$$ExternalSyntheticLambda0.getRank(obj32);
                                        Intrinsics.checkNotNull(rank2);
                                        return ComparisonsKt___ComparisonsJvmKt.compareValues(rank, rank2);
                                    default:
                                        Integer rank3 = shadeListBuilder$$ExternalSyntheticLambda0.getRank(obj22);
                                        Intrinsics.checkNotNull(rank3);
                                        Integer rank4 = shadeListBuilder$$ExternalSyntheticLambda0.getRank(obj32);
                                        Intrinsics.checkNotNull(rank4);
                                        return ComparisonsKt___ComparisonsJvmKt.compareValues(rank3, rank4);
                                }
                            }
                        };
                        boolean isSorted2 = companion.isSorted(list13, comparator);
                        if (!isSorted2) {
                            CollectionsKt__MutableCollectionsJVMKt.sortWith(list13, comparator);
                        }
                        if (list13.isEmpty()) {
                            for (Object obj4 : list11) {
                                if (shadeListBuilder$$ExternalSyntheticLambda0.getRank(obj4) == null) {
                                    list13.add(obj4);
                                }
                            }
                        } else {
                            semiStableSort.getPreallocatedAdditions().clear();
                            ArrayList preallocatedAdditions2 = semiStableSort.getPreallocatedAdditions();
                            for (Object obj5 : list11) {
                                if (shadeListBuilder$$ExternalSyntheticLambda0.getRank(obj5) == null) {
                                    preallocatedAdditions2.add(obj5);
                                }
                            }
                            if (!preallocatedAdditions2.isEmpty()) {
                                kotlin.Lazy lazy2 = semiStableSort.preallocatedMapToIndex$delegate;
                                ((HashMap) lazy2.getValue()).clear();
                                int i10 = 0;
                                for (Object obj6 : list11) {
                                    int i11 = i10 + 1;
                                    if (i10 < 0) {
                                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                                        throw null;
                                    }
                                    ((HashMap) lazy2.getValue()).put(obj6, Integer.valueOf(i10));
                                    i10 = i11;
                                }
                                SemiStableSort.Companion.access$insertPreSortedElementsWithFewestMisOrderings(list13, preallocatedAdditions2, (Comparator) semiStableSort.preallocatedMapToIndexComparator$delegate.getValue());
                                ((HashMap) lazy2.getValue()).clear();
                            }
                            semiStableSort.getPreallocatedAdditions().clear();
                        }
                        z3 &= isSorted2;
                    }
                    shadeListBuilder2.applyNewNotifList();
                }
                ArrayList arrayList8 = (ArrayList) shadeListBuilder2.mNotifList;
                if (arrayList8.size() != 0) {
                    NotifSection notifSection4 = ((ListEntry) arrayList8.get(0)).mAttachState.section;
                    Objects.requireNonNull(notifSection4);
                    NotifSection notifSection5 = notifSection4;
                    int i12 = 0;
                    for (int i13 = 0; i13 < arrayList8.size(); i13++) {
                        ListEntry listEntry6 = (ListEntry) arrayList8.get(i13);
                        NotifSection notifSection6 = listEntry6.mAttachState.section;
                        Objects.requireNonNull(notifSection6);
                        if (notifSection6.index != notifSection5.index) {
                            notifSection5 = notifSection6;
                            i12 = 0;
                        }
                        int i14 = i12 + 1;
                        listEntry6.mAttachState.stableIndex = i12;
                        if (listEntry6 instanceof GroupEntry) {
                            GroupEntry groupEntry5 = (GroupEntry) listEntry6;
                            NotificationEntry notificationEntry4 = groupEntry5.mSummary;
                            if (notificationEntry4 != null) {
                                notificationEntry4.mAttachState.stableIndex = i14;
                                i14 = i12 + 2;
                            }
                            Iterator it4 = groupEntry5.mUnmodifiableChildren.iterator();
                            while (it4.hasNext()) {
                                ((NotificationEntry) it4.next()).mAttachState.stableIndex = i14;
                                i14++;
                            }
                        }
                        i12 = i14;
                    }
                }
                if (!z3) {
                    shadeListBuilder2.getStabilityManager().onEntryReorderSuppressed();
                }
                Trace.endSection();
                final List list14 = shadeListBuilder2.mReadOnlyNotifList;
                Trace.beginSection("ShadeListBuilder.dispatchOnBeforeFinalizeFilter");
                final int i15 = 2;
                shadeListBuilder2.mOnBeforeFinalizeFilterListeners.forEachTraced(new Consumer() { // from class: com.android.systemui.statusbar.notification.collection.ShadeListBuilder$$ExternalSyntheticLambda11
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj7) {
                        switch (i15) {
                            case 0:
                                ((HeadsUpCoordinator$attach$1) obj7).onBeforeTransformGroups();
                                break;
                            case 1:
                                ((OnBeforeRenderListListener) obj7).onBeforeRenderList$1(list14);
                                break;
                            default:
                                ((OnBeforeFinalizeFilterListener) obj7).onBeforeFinalizeFilter(list14);
                                break;
                        }
                    }
                });
                Trace.endSection();
                pipelineState.incrementTo(8);
                shadeListBuilder2.filterNotifs(shadeListBuilder2.mNotifList, shadeListBuilder2.mNewNotifList, shadeListBuilder2.mNotifFinalizeFilters);
                shadeListBuilder2.applyNewNotifList();
                shadeListBuilder2.pruneIncompleteGroups(shadeListBuilder2.mNotifList);
                pipelineState.incrementTo(9);
                Trace.beginSection("ShadeListBuilder.logChanges");
                Iterator it5 = shadeListBuilder2.mAllEntries.iterator();
                while (it5.hasNext()) {
                    shadeListBuilder2.logAttachStateChanges((NotificationEntry) it5.next());
                }
                Iterator it6 = ((ArrayMap) shadeListBuilder2.mGroups).values().iterator();
                while (it6.hasNext()) {
                    shadeListBuilder2.logAttachStateChanges((GroupEntry) it6.next());
                }
                Trace.endSection();
                Trace.beginSection("ShadeListBuilder.freeEmptyGroups");
                ((ArrayMap) shadeListBuilder2.mGroups).values().removeIf(new ShadeListBuilder$$ExternalSyntheticLambda12());
                Trace.endSection();
                Trace.beginSection("ShadeListBuilder.cleanupPluggables");
                ShadeListBuilder.callOnCleanup(shadeListBuilder2.mNotifPreGroupFilters);
                ShadeListBuilder.callOnCleanup(shadeListBuilder2.mNotifPromoters);
                ShadeListBuilder.callOnCleanup(shadeListBuilder2.mNotifFinalizeFilters);
                ShadeListBuilder.callOnCleanup(shadeListBuilder2.mNotifComparators);
                for (int i16 = 0; i16 < ((ArrayList) shadeListBuilder2.mNotifSections).size(); i16++) {
                    ((NotifSection) ((ArrayList) shadeListBuilder2.mNotifSections).get(i16)).sectioner.getClass();
                }
                ShadeListBuilder.callOnCleanup(List.of(shadeListBuilder2.getStabilityManager()));
                Trace.endSection();
                final List list15 = shadeListBuilder2.mReadOnlyNotifList;
                Trace.beginSection("ShadeListBuilder.dispatchOnBeforeRenderList");
                final int i17 = 1;
                shadeListBuilder2.mOnBeforeRenderListListeners.forEachTraced(new Consumer() { // from class: com.android.systemui.statusbar.notification.collection.ShadeListBuilder$$ExternalSyntheticLambda11
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj7) {
                        switch (i17) {
                            case 0:
                                ((HeadsUpCoordinator$attach$1) obj7).onBeforeTransformGroups();
                                break;
                            case 1:
                                ((OnBeforeRenderListListener) obj7).onBeforeRenderList$1(list15);
                                break;
                            default:
                                ((OnBeforeFinalizeFilterListener) obj7).onBeforeFinalizeFilter(list15);
                                break;
                        }
                    }
                });
                Trace.endSection();
                Trace.beginSection("ShadeListBuilder.onRenderList");
                RenderStageManager$attach$1 renderStageManager$attach$12 = shadeListBuilder2.mOnRenderListListener;
                if (renderStageManager$attach$12 != null) {
                    List list16 = shadeListBuilder2.mReadOnlyNotifList;
                    RenderStageManager renderStageManager2 = renderStageManager$attach$12.$tmp0;
                    renderStageManager2.getClass();
                    boolean isEnabled = Trace.isEnabled();
                    if (isEnabled) {
                        TraceUtilsKt.beginSlice("RenderStageManager.onRenderList");
                    }
                    try {
                        ShadeViewManager$viewRenderer$1 shadeViewManager$viewRenderer$12 = renderStageManager2.viewRenderer;
                        if (shadeViewManager$viewRenderer$12 != null) {
                            ShadeViewManager shadeViewManager2 = shadeViewManager$viewRenderer$12.this$0;
                            boolean isEnabled2 = Trace.isEnabled();
                            if (isEnabled2) {
                                TraceUtilsKt.beginSlice("ShadeViewManager.onRenderList");
                            }
                            try {
                                shadeViewManager2.viewDiffer.applySpec(shadeViewManager2.specBuilder.buildNodeSpec(shadeViewManager2.rootController, list16));
                                if (isEnabled2) {
                                    TraceUtilsKt.endSlice();
                                }
                                isEnabled = Trace.isEnabled();
                                if (isEnabled) {
                                    TraceUtilsKt.beginSlice("RenderStageManager.dispatchOnAfterRenderList");
                                }
                                try {
                                    Iterator it7 = renderStageManager2.onAfterRenderListListeners.iterator();
                                    while (it7.hasNext()) {
                                        ((OnAfterRenderListListener) it7.next()).onAfterRenderList(list16);
                                    }
                                    if (isEnabled) {
                                        TraceUtilsKt.endSlice();
                                    }
                                    renderStageManager2.dispatchOnAfterRenderGroups(shadeViewManager$viewRenderer$12, list16);
                                    renderStageManager2.dispatchOnAfterRenderEntries(shadeViewManager$viewRenderer$12, list16);
                                    if (isEnabled) {
                                    }
                                } finally {
                                    if (isEnabled) {
                                        TraceUtilsKt.endSlice();
                                    }
                                }
                            } finally {
                                if (isEnabled2) {
                                    TraceUtilsKt.endSlice();
                                }
                            }
                        }
                    } catch (Throwable th) {
                        if (isEnabled) {
                            TraceUtilsKt.endSlice();
                        }
                        throw th;
                    }
                }
                Trace.endSection();
                Trace.beginSection("ShadeListBuilder.logEndBuildList");
                int i18 = shadeListBuilder2.mIterationCount;
                int size2 = shadeListBuilder2.mReadOnlyNotifList.size();
                List list17 = shadeListBuilder2.mReadOnlyNotifList;
                int i19 = 0;
                for (int i20 = 0; i20 < list17.size(); i20++) {
                    ListEntry listEntry7 = (ListEntry) list17.get(i20);
                    if (listEntry7 instanceof GroupEntry) {
                        i19 = ((GroupEntry) listEntry7).mUnmodifiableChildren.size() + i19;
                    }
                }
                shadeListBuilderLogger.logEndBuildList(i18, size2, i19, !VisualStabilityCoordinator.this.mReorderingAllowed);
                if (shadeListBuilder2.mIterationCount % 10 == 0) {
                    Trace.beginSection("ShadeListBuilder.logFinalList");
                    shadeListBuilderLogger.logFinalList(shadeListBuilder2.mNotifList);
                    Trace.endSection();
                }
                Trace.endSection();
                pipelineState.mState = 0;
                shadeListBuilder2.mIterationCount++;
                Trace.endSection();
            }
        });
        Assert.isMainThread();
        if (notifCollection.mAttached) {
            throw new RuntimeException("attach() called twice");
        }
        notifCollection.mAttached = true;
        DumpManager dumpManager3 = notifCollection.mDumpManager;
        dumpManager3.getClass();
        DumpManager.registerDumpable$default(dumpManager3, "NotifCollection", notifCollection);
        GroupCoalescer groupCoalescer = notifPipelineInitializer.mGroupCoalescer;
        groupCoalescer.mHandler = notifCollection.mNotifHandler;
        Map map = notifCollection.mNotificationSet;
        Objects.requireNonNull(map);
        NotifCollection$$ExternalSyntheticLambda4 notifCollection$$ExternalSyntheticLambda4 = new NotifCollection$$ExternalSyntheticLambda4(0, map);
        NotifCollection$$ExternalSyntheticLambda4 notifCollection$$ExternalSyntheticLambda42 = new NotifCollection$$ExternalSyntheticLambda4(1, groupCoalescer);
        NotifCollectionInconsistencyTracker notifCollectionInconsistencyTracker = notifCollection.mInconsistencyTracker;
        if (notifCollectionInconsistencyTracker.attached) {
            throw new RuntimeException("attach() called twice");
        }
        notifCollectionInconsistencyTracker.attached = true;
        notifCollectionInconsistencyTracker.collectedKeySetAccessor = notifCollection$$ExternalSyntheticLambda4;
        notifCollectionInconsistencyTracker.coalescedKeySetAccessor = notifCollection$$ExternalSyntheticLambda42;
        NotificationListener notificationListener2 = notifPipelineInitializer.mNotificationService;
        List list = notificationListener2.mNotificationHandlers;
        GroupCoalescer.AnonymousClass1 anonymousClass1 = groupCoalescer.mListener;
        if (list.contains(anonymousClass1)) {
            throw new IllegalArgumentException("Listener is already added");
        }
        notificationListener2.mNotificationHandlers.add(anonymousClass1);
        Log.d("NotifPipeline", "Notif pipeline initialized. rendering=true");
        CommonNotifCollection commonNotifCollection = (CommonNotifCollection) lazy.get();
        final TargetSdkResolver targetSdkResolver = this.targetSdkResolver;
        targetSdkResolver.getClass();
        ((NotifPipeline) commonNotifCollection).addCollectionListener(new NotifCollectionListener() { // from class: com.android.systemui.statusbar.notification.collection.TargetSdkResolver$initialize$1
            @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener
            public final void onEntryBind(NotificationEntry notificationEntry, StatusBarNotification statusBarNotification) {
                ApplicationInfo applicationInfo;
                TargetSdkResolver targetSdkResolver2 = TargetSdkResolver.this;
                targetSdkResolver2.getClass();
                ApplicationInfo applicationInfo2 = (ApplicationInfo) statusBarNotification.getNotification().extras.getParcelable("android.appInfo", ApplicationInfo.class);
                if (applicationInfo2 == null) {
                    try {
                        applicationInfo = CentralSurfaces.getPackageManagerForUser(statusBarNotification.getUser().getIdentifier(), targetSdkResolver2.context).getApplicationInfo(statusBarNotification.getPackageName(), 0);
                    } catch (PackageManager.NameNotFoundException e) {
                        Log.e("TargetSdkResolver", "Failed looking up ApplicationInfo for " + statusBarNotification.getPackageName(), e);
                        applicationInfo = null;
                    }
                    applicationInfo2 = applicationInfo;
                }
                notificationEntry.targetSdk = applicationInfo2 != null ? applicationInfo2.targetSdkVersion : 0;
            }
        });
        this.notificationsMediaManager.getClass();
        this.notificationLoggerOptional.ifPresent(new Consumer() { // from class: com.android.systemui.statusbar.notification.init.NotificationsControllerImpl$initialize$2
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                NotificationLogger notificationLogger = (NotificationLogger) obj;
                NotificationStackScrollLayoutController.NotificationListContainerImpl notificationListContainerImpl2 = NotificationStackScrollLayoutController.NotificationListContainerImpl.this;
                notificationLogger.mListContainer = notificationListContainerImpl2;
                if (notificationLogger.mLogging) {
                    NotificationStackScrollLayoutController.this.mView.mLegacyLocationsChangedListener = new NotificationLogger$$ExternalSyntheticLambda2(notificationLogger);
                }
            }
        });
        PeopleSpaceWidgetManager.AnonymousClass2 anonymousClass2 = this.peopleSpaceWidgetManager.mListener;
        if (notificationListener.mNotificationHandlers.contains(anonymousClass2)) {
            throw new IllegalArgumentException("Listener is already added");
        }
        notificationListener.mNotificationHandlers.add(anonymousClass2);
    }

    @Override // com.android.systemui.statusbar.notification.init.NotificationsController
    public final void resetUserExpandedStates() {
        Iterator it = ((NotifPipeline) ((CommonNotifCollection) this.commonNotifCollection.get())).getAllNotifs().iterator();
        while (it.hasNext()) {
            ExpandableNotificationRow expandableNotificationRow = ((NotificationEntry) it.next()).row;
            if (expandableNotificationRow != null) {
                boolean isExpanded = expandableNotificationRow.isExpanded(false);
                expandableNotificationRow.mHasUserChangedExpansion = false;
                expandableNotificationRow.mUserExpanded = false;
                if (isExpanded != expandableNotificationRow.isExpanded(false)) {
                    if (expandableNotificationRow.mIsSummaryWithChildren) {
                        NotificationChildrenContainer notificationChildrenContainer = expandableNotificationRow.mChildrenContainer;
                        if (notificationChildrenContainer.mIsMinimized) {
                            boolean z = notificationChildrenContainer.mUserLocked;
                            if (z) {
                                notificationChildrenContainer.setUserLocked(z);
                            }
                            notificationChildrenContainer.updateHeaderVisibility(true);
                        }
                    }
                    expandableNotificationRow.notifyHeightChanged(false);
                }
                expandableNotificationRow.updateShelfIconColor();
            }
        }
    }

    @Override // com.android.systemui.statusbar.notification.init.NotificationsController
    public final void setNotificationSnoozed(StatusBarNotification statusBarNotification, NotificationSwipeActionHelper.SnoozeOption snoozeOption) {
        SnoozeCriterion snoozeCriterion = snoozeOption.getSnoozeCriterion();
        NotificationListener notificationListener = this.notificationListener;
        if (snoozeCriterion != null) {
            notificationListener.snoozeNotification(statusBarNotification.getKey(), snoozeOption.getSnoozeCriterion().getId());
        } else {
            notificationListener.snoozeNotification(statusBarNotification.getKey(), snoozeOption.getMinutesToSnoozeFor() * 60 * 1000);
        }
    }
}
