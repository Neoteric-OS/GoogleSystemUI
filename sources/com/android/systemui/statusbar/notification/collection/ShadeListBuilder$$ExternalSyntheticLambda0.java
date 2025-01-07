package com.android.systemui.statusbar.notification.collection;

import com.android.systemui.statusbar.notification.collection.listbuilder.NotifSection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class ShadeListBuilder$$ExternalSyntheticLambda0 {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ShadeListBuilder f$0;

    public /* synthetic */ ShadeListBuilder$$ExternalSyntheticLambda0(ShadeListBuilder shadeListBuilder, int i) {
        this.$r8$classId = i;
        this.f$0 = shadeListBuilder;
    }

    public Integer getRank(Object obj) {
        int i;
        ListEntry listEntry = (ListEntry) obj;
        if (this.f$0.getStabilityManager().isEntryReorderingAllowed(listEntry)) {
            return null;
        }
        NotifSection notifSection = listEntry.mAttachState.section;
        int i2 = notifSection != null ? notifSection.index : -1;
        ListAttachState listAttachState = listEntry.mPreviousAttachState;
        NotifSection notifSection2 = listAttachState.section;
        if (i2 == (notifSection2 != null ? notifSection2.index : -1) && (i = listAttachState.stableIndex) != -1) {
            return Integer.valueOf(i);
        }
        return null;
    }
}
