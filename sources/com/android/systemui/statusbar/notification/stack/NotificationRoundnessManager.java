package com.android.systemui.statusbar.notification.stack;

import com.android.keyguard.KeyguardClockSwitchController$$ExternalSyntheticOutline0;
import com.android.systemui.Dumpable;
import com.android.systemui.statusbar.notification.Roundable;
import com.android.systemui.statusbar.notification.SourceType$Companion$from$1;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationRoundnessManager implements Dumpable {
    public static final SourceType$Companion$from$1 DISMISS_ANIMATION = new SourceType$Companion$from$1("DismissAnimation");
    public HashSet mAnimatedChildren;
    public boolean mIsClearAllInProgress;
    public boolean mRoundForPulsingViews;
    public ExpandableNotificationRow mSwipedView;
    public ExpandableView mViewAfterSwipedView;
    public Roundable mViewBeforeSwipedView;

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(new StringBuilder("roundForPulsingViews="), this.mRoundForPulsingViews, printWriter, "isClearAllInProgress="), this.mIsClearAllInProgress, printWriter);
    }

    public final void setViewsAffectedBySwipe(Roundable roundable, ExpandableNotificationRow expandableNotificationRow, ExpandableView expandableView) {
        HashSet hashSet = new HashSet();
        Roundable roundable2 = this.mViewBeforeSwipedView;
        if (roundable2 != null) {
            hashSet.add(roundable2);
        }
        ExpandableNotificationRow expandableNotificationRow2 = this.mSwipedView;
        if (expandableNotificationRow2 != null) {
            hashSet.add(expandableNotificationRow2);
        }
        ExpandableView expandableView2 = this.mViewAfterSwipedView;
        if (expandableView2 != null) {
            hashSet.add(expandableView2);
        }
        this.mViewBeforeSwipedView = roundable;
        SourceType$Companion$from$1 sourceType$Companion$from$1 = DISMISS_ANIMATION;
        if (roundable != null) {
            hashSet.remove(roundable);
            roundable.requestRoundness(0.0f, 1.0f, sourceType$Companion$from$1);
        }
        this.mSwipedView = expandableNotificationRow;
        if (expandableNotificationRow != null) {
            hashSet.remove(expandableNotificationRow);
            expandableNotificationRow.requestRoundness(1.0f, 1.0f, sourceType$Companion$from$1);
        }
        this.mViewAfterSwipedView = expandableView;
        if (expandableView != null) {
            hashSet.remove(expandableView);
            expandableView.requestRoundness(1.0f, 0.0f, sourceType$Companion$from$1);
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            ((Roundable) it.next()).requestRoundnessReset(sourceType$Companion$from$1);
        }
    }
}
