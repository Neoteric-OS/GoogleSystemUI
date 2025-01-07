package com.android.systemui.statusbar.notification.stack.ui.viewbinder;

import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.wm.shell.R;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationListViewBinder$bindEmptyShade$4 implements FlowCollector {
    public final /* synthetic */ NotificationStackScrollLayout $parentView;
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ NotificationListViewBinder$bindEmptyShade$4(NotificationStackScrollLayout notificationStackScrollLayout, int i) {
        this.$r8$classId = i;
        this.$parentView = notificationStackScrollLayout;
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public final Object emit(Object obj, Continuation continuation) {
        Unit unit = Unit.INSTANCE;
        NotificationStackScrollLayout notificationStackScrollLayout = this.$parentView;
        switch (this.$r8$classId) {
            case 0:
                Triple triple = (Triple) obj;
                boolean booleanValue = ((Boolean) triple.component1()).booleanValue();
                boolean booleanValue2 = ((Boolean) triple.component2()).booleanValue();
                boolean booleanValue3 = ((Boolean) triple.component3()).booleanValue();
                notificationStackScrollLayout.mEmptyShadeView.setVisible(booleanValue, notificationStackScrollLayout.mIsExpanded && notificationStackScrollLayout.mAnimationsEnabled);
                if (!booleanValue2) {
                    if (!booleanValue3) {
                        notificationStackScrollLayout.updateEmptyShadeViewResources(R.string.empty_shade_text, 0, 0);
                        break;
                    } else {
                        notificationStackScrollLayout.updateEmptyShadeViewResources(R.string.no_unseen_notif_text, R.string.unlock_to_see_notif_text, R.drawable.ic_friction_lock_closed);
                        break;
                    }
                } else {
                    notificationStackScrollLayout.updateEmptyShadeViewResources(R.string.dnd_suppressing_shade_text, 0, 0);
                    break;
                }
            default:
                notificationStackScrollLayout.setImportantForAccessibility(((Boolean) obj).booleanValue() ? 1 : 2);
                break;
        }
        return unit;
    }
}
