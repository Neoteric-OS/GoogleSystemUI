package com.android.systemui.statusbar.notification.collection.coordinator;

import android.os.Trace;
import com.android.app.tracing.TraceUtilsKt;
import com.android.systemui.statusbar.notification.collection.GroupEntry;
import com.android.systemui.statusbar.notification.collection.ListEntry;
import com.android.systemui.statusbar.notification.collection.NotifLiveDataImpl;
import com.android.systemui.statusbar.notification.collection.NotifLiveDataStoreImpl;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.PipelineDumpable;
import com.android.systemui.statusbar.notification.collection.PipelineDumper;
import com.android.systemui.statusbar.notification.collection.listbuilder.OnAfterRenderListListener;
import com.android.systemui.util.Assert;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DataStoreCoordinator implements Coordinator, PipelineDumpable {
    public final NotifLiveDataStoreImpl notifLiveDataStoreImpl;

    public DataStoreCoordinator(NotifLiveDataStoreImpl notifLiveDataStoreImpl) {
        this.notifLiveDataStoreImpl = notifLiveDataStoreImpl;
    }

    @Override // com.android.systemui.statusbar.notification.collection.coordinator.Coordinator
    public final void attach(NotifPipeline notifPipeline) {
        notifPipeline.mRenderStageManager.onAfterRenderListListeners.add(new OnAfterRenderListListener() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.DataStoreCoordinator$attach$1
            @Override // com.android.systemui.statusbar.notification.collection.listbuilder.OnAfterRenderListListener
            public final void onAfterRenderList(List list) {
                DataStoreCoordinator dataStoreCoordinator = DataStoreCoordinator.this;
                dataStoreCoordinator.getClass();
                ArrayList arrayList = new ArrayList();
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    ListEntry listEntry = (ListEntry) it.next();
                    if (listEntry instanceof NotificationEntry) {
                        arrayList.add(listEntry);
                    } else {
                        if (!(listEntry instanceof GroupEntry)) {
                            throw new IllegalStateException(("Unexpected entry " + listEntry).toString());
                        }
                        GroupEntry groupEntry = (GroupEntry) listEntry;
                        NotificationEntry notificationEntry = groupEntry.mSummary;
                        if (notificationEntry == null) {
                            throw new IllegalStateException(("No Summary: " + groupEntry).toString());
                        }
                        arrayList.add(notificationEntry);
                        arrayList.addAll(groupEntry.mUnmodifiableChildren);
                    }
                }
                NotifLiveDataStoreImpl notifLiveDataStoreImpl = dataStoreCoordinator.notifLiveDataStoreImpl;
                notifLiveDataStoreImpl.getClass();
                boolean isEnabled = Trace.isEnabled();
                if (isEnabled) {
                    TraceUtilsKt.beginSlice("NotifLiveDataStore.setActiveNotifList");
                }
                try {
                    Assert.isMainThread();
                    List unmodifiableList = Collections.unmodifiableList(CollectionsKt.toList(arrayList));
                    NotifLiveDataImpl notifLiveDataImpl = notifLiveDataStoreImpl.activeNotifListPrivate;
                    Intrinsics.checkNotNull(unmodifiableList);
                    Iterator it2 = CollectionsKt__CollectionsKt.listOf(notifLiveDataImpl.setValueAndProvideDispatcher(unmodifiableList), notifLiveDataStoreImpl.activeNotifCountPrivate.setValueAndProvideDispatcher(Integer.valueOf(unmodifiableList.size())), notifLiveDataStoreImpl.hasActiveNotifsPrivate.setValueAndProvideDispatcher(Boolean.valueOf(!unmodifiableList.isEmpty()))).iterator();
                    while (it2.hasNext()) {
                        ((Function0) it2.next()).invoke();
                    }
                } finally {
                    if (isEnabled) {
                        TraceUtilsKt.endSlice();
                    }
                }
            }
        });
    }

    @Override // com.android.systemui.statusbar.notification.collection.PipelineDumpable
    public final void dumpPipeline(PipelineDumper pipelineDumper) {
        pipelineDumper.dump(this.notifLiveDataStoreImpl, "notifLiveDataStoreImpl");
    }
}
