package com.android.systemui.statusbar.notification.collection.coordinator;

import com.android.systemui.statusbar.notification.collection.ListEntry;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifComparator;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifSectioner;
import com.android.systemui.statusbar.notification.collection.render.NodeController;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ConversationCoordinator$peopleSilentSectioner$1 extends NotifSectioner {
    @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifSectioner
    public final NotifComparator getComparator() {
        throw new IllegalStateException("Legacy code path not supported when android.app.sort_section_by_time is enabled.".toString());
    }

    @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifSectioner
    public final NodeController getHeaderNodeController() {
        throw new IllegalStateException("Legacy code path not supported when android.app.sort_section_by_time is enabled.".toString());
    }

    @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifSectioner
    public final boolean isInSection(ListEntry listEntry) {
        throw new IllegalStateException("Legacy code path not supported when android.app.sort_section_by_time is enabled.".toString());
    }
}
