package com.android.systemui.statusbar.notification.collection.render;

import android.os.Trace;
import com.android.app.tracing.TraceUtilsKt;
import com.android.systemui.statusbar.notification.NotificationSectionsFeatureManager;
import com.android.systemui.statusbar.notification.collection.GroupEntry;
import com.android.systemui.statusbar.notification.collection.ListEntry;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.listbuilder.NotifSection;
import com.android.systemui.statusbar.notification.collection.provider.SectionHeaderVisibilityProvider;
import com.android.systemui.util.Utils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.collections.EmptySet;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NodeSpecBuilder {
    public final MediaContainerController mediaContainerController;
    public final SectionHeaderVisibilityProvider sectionHeaderVisibilityProvider;
    public final NotificationSectionsFeatureManager sectionsFeatureManager;
    public final NotifViewBarn viewBarn;

    public NodeSpecBuilder(MediaContainerController mediaContainerController, NotificationSectionsFeatureManager notificationSectionsFeatureManager, SectionHeaderVisibilityProvider sectionHeaderVisibilityProvider, NotifViewBarn notifViewBarn) {
        this.mediaContainerController = mediaContainerController;
        this.sectionsFeatureManager = notificationSectionsFeatureManager;
        this.sectionHeaderVisibilityProvider = sectionHeaderVisibilityProvider;
        this.viewBarn = notifViewBarn;
        EmptySet emptySet = EmptySet.INSTANCE;
    }

    public final NodeSpecImpl buildNodeSpec(NodeController nodeController, List list) {
        boolean isEnabled = Trace.isEnabled();
        if (isEnabled) {
            TraceUtilsKt.beginSlice("NodeSpecBuilder.buildNodeSpec");
        }
        try {
            NodeSpecImpl nodeSpecImpl = new NodeSpecImpl(null, nodeController);
            if (Utils.useQsMediaPlayer(this.sectionsFeatureManager.context)) {
                nodeSpecImpl.children.add(new NodeSpecImpl(nodeSpecImpl, this.mediaContainerController));
            }
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            boolean z = this.sectionHeaderVisibilityProvider.sectionHeadersVisible;
            new ArrayList();
            new LinkedHashMap();
            new LinkedHashMap();
            Iterator it = list.iterator();
            NotifSection notifSection = null;
            while (it.hasNext()) {
                ListEntry listEntry = (ListEntry) it.next();
                NotifSection notifSection2 = listEntry.mAttachState.section;
                Intrinsics.checkNotNull(notifSection2);
                NodeController nodeController2 = notifSection2.headerController;
                if (linkedHashSet.contains(notifSection2)) {
                    throw new RuntimeException("Section " + notifSection2.label + " has been duplicated");
                }
                if (!notifSection2.equals(notifSection)) {
                    if (!Intrinsics.areEqual(nodeController2, notifSection != null ? notifSection.headerController : null) && z && nodeController2 != null) {
                        nodeSpecImpl.children.add(new NodeSpecImpl(nodeSpecImpl, nodeController2));
                    }
                    linkedHashSet.add(notifSection);
                    notifSection = notifSection2;
                }
                nodeSpecImpl.children.add(buildNotifNode(nodeSpecImpl, listEntry));
            }
            if (isEnabled) {
                TraceUtilsKt.endSlice();
            }
            return nodeSpecImpl;
        } catch (Throwable th) {
            if (isEnabled) {
                TraceUtilsKt.endSlice();
            }
            throw th;
        }
    }

    public final NodeSpecImpl buildNotifNode(NodeSpecImpl nodeSpecImpl, ListEntry listEntry) {
        boolean z = listEntry instanceof NotificationEntry;
        NotifViewBarn notifViewBarn = this.viewBarn;
        if (z) {
            return new NodeSpecImpl(nodeSpecImpl, notifViewBarn.requireNodeController(listEntry));
        }
        if (!(listEntry instanceof GroupEntry)) {
            throw new RuntimeException("Unexpected entry: " + listEntry);
        }
        GroupEntry groupEntry = (GroupEntry) listEntry;
        NotificationEntry notificationEntry = groupEntry.mSummary;
        if (notificationEntry == null) {
            throw new IllegalStateException("Required value was null.");
        }
        NodeSpecImpl nodeSpecImpl2 = new NodeSpecImpl(nodeSpecImpl, notifViewBarn.requireNodeController(notificationEntry));
        for (NotificationEntry notificationEntry2 : groupEntry.mUnmodifiableChildren) {
            List list = nodeSpecImpl2.children;
            Intrinsics.checkNotNull(notificationEntry2);
            list.add(buildNotifNode(nodeSpecImpl2, notificationEntry2));
        }
        return nodeSpecImpl2;
    }
}
