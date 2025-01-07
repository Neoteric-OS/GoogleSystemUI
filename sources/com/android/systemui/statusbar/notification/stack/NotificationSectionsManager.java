package com.android.systemui.statusbar.notification.stack;

import android.util.SparseArray;
import android.view.View;
import com.android.systemui.media.controls.ui.controller.KeyguardMediaController;
import com.android.systemui.statusbar.notification.NotificationSectionsFeatureManager;
import com.android.systemui.statusbar.notification.SourceType$Companion$from$1;
import com.android.systemui.statusbar.notification.collection.render.MediaContainerController;
import com.android.systemui.statusbar.notification.collection.render.SectionHeaderNodeControllerImpl;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.policy.ConfigurationController;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationSectionsManager {
    public static final SourceType$Companion$from$1 SECTION = new SourceType$Companion$from$1("Section");
    public final SectionHeaderNodeControllerImpl alertingHeaderController;
    public final ConfigurationController configurationController;
    public final NotificationSectionsManager$configurationListener$1 configurationListener = new ConfigurationController.ConfigurationListener() { // from class: com.android.systemui.statusbar.notification.stack.NotificationSectionsManager$configurationListener$1
        @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
        public final void onLocaleListChanged() {
            NotificationSectionsManager.this.reinflateViews();
        }
    };
    public final SectionHeaderNodeControllerImpl incomingHeaderController;
    public boolean initialized;
    public final KeyguardMediaController keyguardMediaController;
    public final MediaContainerController mediaContainerController;
    public final SectionHeaderNodeControllerImpl newsHeaderController;
    public final NotificationRoundnessManager notificationRoundnessManager;
    public NotificationStackScrollLayout parent;
    public final SectionHeaderNodeControllerImpl peopleHeaderController;
    public final SectionHeaderNodeControllerImpl promoHeaderController;
    public final SectionHeaderNodeControllerImpl recsHeaderController;
    public final NotificationSectionsFeatureManager sectionsFeatureManager;
    public final SectionHeaderNodeControllerImpl silentHeaderController;
    public final SectionHeaderNodeControllerImpl socialHeaderController;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class SectionBounds {

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Many extends SectionBounds {
            public final ExpandableView first;
            public final ExpandableView last;

            public Many(ExpandableView expandableView, ExpandableView expandableView2) {
                this.first = expandableView;
                this.last = expandableView2;
            }

            public final boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Many)) {
                    return false;
                }
                Many many = (Many) obj;
                return Intrinsics.areEqual(this.first, many.first) && Intrinsics.areEqual(this.last, many.last);
            }

            public final int hashCode() {
                return this.last.hashCode() + (this.first.hashCode() * 31);
            }

            public final String toString() {
                return "Many(first=" + this.first + ", last=" + this.last + ")";
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class None extends SectionBounds {
            public static final None INSTANCE = new None();
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class One extends SectionBounds {
            public final ExpandableView lone;

            public One(ExpandableView expandableView) {
                this.lone = expandableView;
            }

            public final boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof One) && Intrinsics.areEqual(this.lone, ((One) obj).lone);
            }

            public final int hashCode() {
                return this.lone.hashCode();
            }

            public final String toString() {
                return "One(lone=" + this.lone + ")";
            }
        }

        public static boolean setFirstAndLastVisibleChildren(NotificationSection notificationSection, ExpandableView expandableView, ExpandableView expandableView2) {
            boolean z = notificationSection.mFirstVisibleChild != expandableView;
            notificationSection.mFirstVisibleChild = expandableView;
            boolean z2 = notificationSection.mLastVisibleChild != expandableView2;
            notificationSection.mLastVisibleChild = expandableView2;
            return z || z2;
        }
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.statusbar.notification.stack.NotificationSectionsManager$configurationListener$1] */
    public NotificationSectionsManager(ConfigurationController configurationController, KeyguardMediaController keyguardMediaController, NotificationSectionsFeatureManager notificationSectionsFeatureManager, MediaContainerController mediaContainerController, NotificationRoundnessManager notificationRoundnessManager, SectionHeaderNodeControllerImpl sectionHeaderNodeControllerImpl, SectionHeaderNodeControllerImpl sectionHeaderNodeControllerImpl2, SectionHeaderNodeControllerImpl sectionHeaderNodeControllerImpl3, SectionHeaderNodeControllerImpl sectionHeaderNodeControllerImpl4, SectionHeaderNodeControllerImpl sectionHeaderNodeControllerImpl5, SectionHeaderNodeControllerImpl sectionHeaderNodeControllerImpl6, SectionHeaderNodeControllerImpl sectionHeaderNodeControllerImpl7, SectionHeaderNodeControllerImpl sectionHeaderNodeControllerImpl8) {
        this.configurationController = configurationController;
        this.keyguardMediaController = keyguardMediaController;
        this.sectionsFeatureManager = notificationSectionsFeatureManager;
        this.mediaContainerController = mediaContainerController;
        this.notificationRoundnessManager = notificationRoundnessManager;
        this.incomingHeaderController = sectionHeaderNodeControllerImpl;
        this.peopleHeaderController = sectionHeaderNodeControllerImpl2;
        this.alertingHeaderController = sectionHeaderNodeControllerImpl3;
        this.silentHeaderController = sectionHeaderNodeControllerImpl4;
        this.newsHeaderController = sectionHeaderNodeControllerImpl5;
        this.socialHeaderController = sectionHeaderNodeControllerImpl6;
        this.recsHeaderController = sectionHeaderNodeControllerImpl7;
        this.promoHeaderController = sectionHeaderNodeControllerImpl8;
    }

    public final boolean beginsSection(View view, View view2) {
        return view == this.silentHeaderController._view || view == this.mediaContainerController.mediaContainerView || view == this.peopleHeaderController._view || view == this.alertingHeaderController._view || view == this.incomingHeaderController._view || !Intrinsics.areEqual(getBucket(view), getBucket(view2));
    }

    public final Integer getBucket(View view) {
        if (view == this.silentHeaderController._view) {
            return 6;
        }
        if (view == this.incomingHeaderController._view) {
            return 2;
        }
        if (view == this.mediaContainerController.mediaContainerView) {
            return 1;
        }
        if (view == this.peopleHeaderController._view) {
            return 4;
        }
        if (view == this.alertingHeaderController._view) {
            return 5;
        }
        if (view == this.newsHeaderController._view) {
            return 10;
        }
        if (view == this.socialHeaderController._view) {
            return 11;
        }
        if (view == this.recsHeaderController._view) {
            return 12;
        }
        if (view == this.promoHeaderController._view) {
            return 13;
        }
        if (view instanceof ExpandableNotificationRow) {
            return Integer.valueOf(((ExpandableNotificationRow) view).mEntry.mBucket);
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0056  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void reinflateViews() {
        /*
            r7 = this;
            com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout r0 = r7.parent
            r1 = 0
            if (r0 != 0) goto L6
            r0 = r1
        L6:
            com.android.systemui.statusbar.notification.collection.render.SectionHeaderNodeControllerImpl r2 = r7.silentHeaderController
            r2.reinflateView(r0)
            com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout r0 = r7.parent
            if (r0 != 0) goto L10
            r0 = r1
        L10:
            com.android.systemui.statusbar.notification.collection.render.SectionHeaderNodeControllerImpl r2 = r7.alertingHeaderController
            r2.reinflateView(r0)
            com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout r0 = r7.parent
            if (r0 != 0) goto L1a
            r0 = r1
        L1a:
            com.android.systemui.statusbar.notification.collection.render.SectionHeaderNodeControllerImpl r2 = r7.peopleHeaderController
            r2.reinflateView(r0)
            com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout r0 = r7.parent
            if (r0 != 0) goto L24
            r0 = r1
        L24:
            com.android.systemui.statusbar.notification.collection.render.SectionHeaderNodeControllerImpl r2 = r7.incomingHeaderController
            r2.reinflateView(r0)
            com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout r0 = r7.parent
            if (r0 != 0) goto L2e
            goto L2f
        L2e:
            r1 = r0
        L2f:
            com.android.systemui.statusbar.notification.collection.render.MediaContainerController r0 = r7.mediaContainerController
            com.android.systemui.statusbar.notification.stack.MediaContainerView r2 = r0.mediaContainerView
            r3 = -1
            if (r2 == 0) goto L47
            r2.removeFromTransientContainer()
            android.view.ViewParent r4 = r2.getParent()
            if (r4 != r1) goto L47
            int r4 = r1.indexOfChild(r2)
            r1.removeView(r2)
            goto L48
        L47:
            r4 = r3
        L48:
            android.view.LayoutInflater r2 = r0.layoutInflater
            r5 = 2131558656(0x7f0d0100, float:1.8742634E38)
            r6 = 0
            android.view.View r2 = r2.inflate(r5, r1, r6)
            com.android.systemui.statusbar.notification.stack.MediaContainerView r2 = (com.android.systemui.statusbar.notification.stack.MediaContainerView) r2
            if (r4 == r3) goto L59
            r1.addView(r2, r4)
        L59:
            r0.mediaContainerView = r2
            com.android.systemui.media.controls.ui.controller.KeyguardMediaController r7 = r7.keyguardMediaController
            r7.attachSinglePaneContainer(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.stack.NotificationSectionsManager.reinflateViews():void");
    }

    public final void updateFirstAndLastViewsForAllSections(NotificationSection[] notificationSectionArr, List list) {
        NotificationRoundnessManager notificationRoundnessManager;
        SourceType$Companion$from$1 sourceType$Companion$from$1;
        boolean firstAndLastVisibleChildren;
        Object many;
        Object obj;
        SectionBounds.None none = SectionBounds.None.INSTANCE;
        int length = notificationSectionArr.length;
        SparseArray sparseArray = length < 0 ? new SparseArray() : new SparseArray(length);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ExpandableView expandableView = (ExpandableView) it.next();
            Integer bucket = getBucket(expandableView);
            if (bucket == null) {
                throw new IllegalArgumentException("Cannot find section bucket for view");
            }
            int intValue = bucket.intValue();
            Object obj2 = sparseArray.get(intValue);
            if (obj2 == null) {
                obj2 = none;
            }
            SectionBounds sectionBounds = (SectionBounds) obj2;
            if (sectionBounds instanceof SectionBounds.None) {
                obj = new SectionBounds.One(expandableView);
            } else {
                if (sectionBounds instanceof SectionBounds.One) {
                    many = new SectionBounds.Many(((SectionBounds.One) sectionBounds).lone, expandableView);
                } else {
                    if (!(sectionBounds instanceof SectionBounds.Many)) {
                        throw new NoWhenBranchMatchedException();
                    }
                    many = new SectionBounds.Many(((SectionBounds.Many) sectionBounds).first, expandableView);
                }
                obj = many;
            }
            sparseArray.put(intValue, obj);
        }
        ArrayList arrayList = new ArrayList();
        for (NotificationSection notificationSection : notificationSectionArr) {
            ExpandableView expandableView2 = notificationSection.mFirstVisibleChild;
            if (expandableView2 != null) {
                arrayList.add(expandableView2);
            }
        }
        Set<ExpandableView> mutableSet = CollectionsKt.toMutableSet(CollectionsKt.toSet(arrayList));
        ArrayList arrayList2 = new ArrayList();
        for (NotificationSection notificationSection2 : notificationSectionArr) {
            ExpandableView expandableView3 = notificationSection2.mLastVisibleChild;
            if (expandableView3 != null) {
                arrayList2.add(expandableView3);
            }
        }
        Set<ExpandableView> mutableSet2 = CollectionsKt.toMutableSet(CollectionsKt.toSet(arrayList2));
        boolean z = false;
        for (NotificationSection notificationSection3 : notificationSectionArr) {
            SectionBounds sectionBounds2 = (SectionBounds) sparseArray.get(notificationSection3.mBucket);
            if (sectionBounds2 == null) {
                sectionBounds2 = none;
            }
            if (sectionBounds2 instanceof SectionBounds.None) {
                firstAndLastVisibleChildren = SectionBounds.setFirstAndLastVisibleChildren(notificationSection3, null, null);
            } else if (sectionBounds2 instanceof SectionBounds.One) {
                ExpandableView expandableView4 = ((SectionBounds.One) sectionBounds2).lone;
                firstAndLastVisibleChildren = SectionBounds.setFirstAndLastVisibleChildren(notificationSection3, expandableView4, expandableView4);
            } else {
                if (!(sectionBounds2 instanceof SectionBounds.Many)) {
                    throw new NoWhenBranchMatchedException();
                }
                SectionBounds.Many many2 = (SectionBounds.Many) sectionBounds2;
                firstAndLastVisibleChildren = SectionBounds.setFirstAndLastVisibleChildren(notificationSection3, many2.first, many2.last);
            }
            z = firstAndLastVisibleChildren || z;
        }
        ArrayList arrayList3 = new ArrayList();
        for (NotificationSection notificationSection4 : notificationSectionArr) {
            ExpandableView expandableView5 = notificationSection4.mFirstVisibleChild;
            if (expandableView5 != null) {
                arrayList3.add(expandableView5);
            }
        }
        ArrayList<ExpandableView> arrayList4 = new ArrayList();
        for (NotificationSection notificationSection5 : notificationSectionArr) {
            ExpandableView expandableView6 = notificationSection5.mLastVisibleChild;
            if (expandableView6 != null) {
                arrayList4.add(expandableView6);
            }
        }
        Iterator it2 = arrayList3.iterator();
        while (true) {
            boolean hasNext = it2.hasNext();
            notificationRoundnessManager = this.notificationRoundnessManager;
            sourceType$Companion$from$1 = SECTION;
            if (!hasNext) {
                break;
            }
            ExpandableView expandableView7 = (ExpandableView) it2.next();
            if (!mutableSet.remove(expandableView7)) {
                expandableView7.requestTopRoundness(1.0f, sourceType$Companion$from$1, expandableView7.isShown() && !notificationRoundnessManager.mAnimatedChildren.contains(expandableView7));
            }
        }
        for (ExpandableView expandableView8 : arrayList4) {
            if (!mutableSet2.remove(expandableView8)) {
                expandableView8.requestBottomRoundness(1.0f, sourceType$Companion$from$1, expandableView8.isShown() && !notificationRoundnessManager.mAnimatedChildren.contains(expandableView8));
            }
        }
        for (ExpandableView expandableView9 : mutableSet) {
            expandableView9.requestTopRoundness(0.0f, sourceType$Companion$from$1, expandableView9.getRoundableState().targetView.isShown());
        }
        for (ExpandableView expandableView10 : mutableSet2) {
            expandableView10.requestBottomRoundness(0.0f, sourceType$Companion$from$1, expandableView10.getRoundableState().targetView.isShown());
        }
    }

    public static /* synthetic */ void getAlertingHeaderView$annotations() {
    }

    public static /* synthetic */ void getIncomingHeaderView$annotations() {
    }

    public static /* synthetic */ void getMediaControlsView$annotations() {
    }

    public static /* synthetic */ void getNewsHeaderView$annotations() {
    }

    public static /* synthetic */ void getPeopleHeaderView$annotations() {
    }

    public static /* synthetic */ void getPromoHeaderView$annotations() {
    }

    public static /* synthetic */ void getRecsHeaderView$annotations() {
    }

    public static /* synthetic */ void getSilentHeaderView$annotations() {
    }

    public static /* synthetic */ void getSocialHeaderView$annotations() {
    }
}
