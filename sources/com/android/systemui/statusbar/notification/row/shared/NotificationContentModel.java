package com.android.systemui.statusbar.notification.row.shared;

import com.android.systemui.statusbar.notification.row.ui.viewmodel.SingleLineViewModel;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationContentModel {
    public final HeadsUpStatusBarModel headsUpStatusBarModel;
    public final RichOngoingContentModel richOngoingContentModel;
    public final SingleLineViewModel singleLineViewModel;

    public NotificationContentModel(HeadsUpStatusBarModel headsUpStatusBarModel, SingleLineViewModel singleLineViewModel, RichOngoingContentModel richOngoingContentModel) {
        this.headsUpStatusBarModel = headsUpStatusBarModel;
        this.singleLineViewModel = singleLineViewModel;
        this.richOngoingContentModel = richOngoingContentModel;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NotificationContentModel)) {
            return false;
        }
        NotificationContentModel notificationContentModel = (NotificationContentModel) obj;
        return this.headsUpStatusBarModel.equals(notificationContentModel.headsUpStatusBarModel) && Intrinsics.areEqual(this.singleLineViewModel, notificationContentModel.singleLineViewModel) && Intrinsics.areEqual((Object) null, (Object) null) && Intrinsics.areEqual(this.richOngoingContentModel, notificationContentModel.richOngoingContentModel);
    }

    public final int hashCode() {
        int hashCode = this.headsUpStatusBarModel.hashCode() * 31;
        SingleLineViewModel singleLineViewModel = this.singleLineViewModel;
        int hashCode2 = (hashCode + (singleLineViewModel == null ? 0 : singleLineViewModel.hashCode())) * 961;
        RichOngoingContentModel richOngoingContentModel = this.richOngoingContentModel;
        return hashCode2 + (richOngoingContentModel != null ? richOngoingContentModel.hashCode() : 0);
    }

    public final String toString() {
        return "NotificationContentModel(headsUpStatusBarModel=" + this.headsUpStatusBarModel + ", singleLineViewModel=" + this.singleLineViewModel + ", publicSingleLineViewModel=null, richOngoingContentModel=" + this.richOngoingContentModel + ")";
    }
}
