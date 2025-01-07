package com.android.systemui.statusbar.notification.collection.render;

import android.os.Trace;
import com.android.app.tracing.TraceUtilsKt;
import com.android.systemui.statusbar.notification.collection.GroupEntry;
import com.android.systemui.statusbar.notification.collection.ListEntry;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.PipelineDumpable;
import com.android.systemui.statusbar.notification.collection.PipelineDumper;
import com.android.systemui.statusbar.notification.collection.listbuilder.OnAfterRenderEntryListener;
import com.android.systemui.statusbar.notification.collection.listbuilder.OnAfterRenderGroupListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1;
import kotlin.jvm.functions.Function1;
import kotlin.sequences.FilteringSequence$iterator$1;
import kotlin.sequences.SequencesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RenderStageManager implements PipelineDumpable {
    public ShadeViewManager$viewRenderer$1 viewRenderer;
    public final List onAfterRenderListListeners = new ArrayList();
    public final List onAfterRenderGroupListeners = new ArrayList();
    public final List onAfterRenderEntryListeners = new ArrayList();

    public final void dispatchOnAfterRenderEntries(ShadeViewManager$viewRenderer$1 shadeViewManager$viewRenderer$1, List list) {
        boolean isEnabled = Trace.isEnabled();
        if (isEnabled) {
            TraceUtilsKt.beginSlice("RenderStageManager.dispatchOnAfterRenderEntries");
        }
        try {
            if (this.onAfterRenderEntryListeners.isEmpty()) {
                if (isEnabled) {
                    TraceUtilsKt.endSlice();
                    return;
                }
                return;
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                ListEntry listEntry = (ListEntry) it.next();
                if (listEntry instanceof NotificationEntry) {
                    NotificationEntry notificationEntry = (NotificationEntry) listEntry;
                    NotifViewController rowController = shadeViewManager$viewRenderer$1.getRowController(notificationEntry);
                    Iterator it2 = this.onAfterRenderEntryListeners.iterator();
                    while (it2.hasNext()) {
                        ((OnAfterRenderEntryListener) it2.next()).onAfterRenderEntry(notificationEntry, rowController);
                    }
                } else {
                    if (!(listEntry instanceof GroupEntry)) {
                        throw new IllegalStateException(("Unhandled entry: " + listEntry).toString());
                    }
                    GroupEntry groupEntry = (GroupEntry) listEntry;
                    NotificationEntry notificationEntry2 = groupEntry.mSummary;
                    if (notificationEntry2 == null) {
                        throw new IllegalStateException(("No Summary: " + groupEntry).toString());
                    }
                    NotifViewController rowController2 = shadeViewManager$viewRenderer$1.getRowController(notificationEntry2);
                    Iterator it3 = this.onAfterRenderEntryListeners.iterator();
                    while (it3.hasNext()) {
                        ((OnAfterRenderEntryListener) it3.next()).onAfterRenderEntry(notificationEntry2, rowController2);
                    }
                    for (NotificationEntry notificationEntry3 : ((GroupEntry) listEntry).mUnmodifiableChildren) {
                        NotifViewController rowController3 = shadeViewManager$viewRenderer$1.getRowController(notificationEntry3);
                        Iterator it4 = this.onAfterRenderEntryListeners.iterator();
                        while (it4.hasNext()) {
                            ((OnAfterRenderEntryListener) it4.next()).onAfterRenderEntry(notificationEntry3, rowController3);
                        }
                    }
                }
            }
            if (isEnabled) {
                TraceUtilsKt.endSlice();
            }
        } catch (Throwable th) {
            if (isEnabled) {
                TraceUtilsKt.endSlice();
            }
            throw th;
        }
    }

    public final void dispatchOnAfterRenderGroups(ShadeViewManager$viewRenderer$1 shadeViewManager$viewRenderer$1, List list) {
        boolean isEnabled = Trace.isEnabled();
        if (isEnabled) {
            TraceUtilsKt.beginSlice("RenderStageManager.dispatchOnAfterRenderGroups");
        }
        try {
            if (this.onAfterRenderGroupListeners.isEmpty()) {
                if (isEnabled) {
                    return;
                } else {
                    return;
                }
            }
            FilteringSequence$iterator$1 filteringSequence$iterator$1 = new FilteringSequence$iterator$1(SequencesKt.filter(new CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1(list), new Function1() { // from class: com.android.systemui.statusbar.notification.collection.render.RenderStageManager$dispatchOnAfterRenderGroups$lambda$6$$inlined$filterIsInstance$1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(obj instanceof GroupEntry);
                }
            }));
            while (filteringSequence$iterator$1.hasNext()) {
                GroupEntry groupEntry = (GroupEntry) filteringSequence$iterator$1.next();
                NotifViewController groupController = shadeViewManager$viewRenderer$1.getGroupController(groupEntry);
                Iterator it = this.onAfterRenderGroupListeners.iterator();
                while (it.hasNext()) {
                    ((OnAfterRenderGroupListener) it.next()).onAfterRenderGroup(groupEntry, groupController);
                }
            }
            if (isEnabled) {
                TraceUtilsKt.endSlice();
            }
        } finally {
            if (isEnabled) {
                TraceUtilsKt.endSlice();
            }
        }
    }

    @Override // com.android.systemui.statusbar.notification.collection.PipelineDumpable
    public final void dumpPipeline(PipelineDumper pipelineDumper) {
        pipelineDumper.dump(this.viewRenderer, "viewRenderer");
        pipelineDumper.dump(this.onAfterRenderListListeners, "onAfterRenderListListeners");
        pipelineDumper.dump(this.onAfterRenderGroupListeners, "onAfterRenderGroupListeners");
        pipelineDumper.dump(this.onAfterRenderEntryListeners, "onAfterRenderEntryListeners");
    }
}
