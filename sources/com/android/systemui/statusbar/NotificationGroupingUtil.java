package com.android.systemui.statusbar;

import android.R;
import android.app.Notification;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.internal.widget.ConversationLayout;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.NotificationContentView;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationGroupingUtil {
    public static final AnonymousClass2 GREY_COMPARATOR;
    public static final AnonymousClass2 ICON_VISIBILITY_COMPARATOR;
    public final HashSet mDividers;
    public final ArrayList mProcessors;
    public final ExpandableNotificationRow mRow;
    public static final AnonymousClass1 TEXT_VIEW_COMPARATOR = new AnonymousClass1(4);
    public static final AppNameComparator APP_NAME_COMPARATOR = new AppNameComparator(4);
    public static final AnonymousClass1 BADGE_COMPARATOR = new AnonymousClass1(3);
    public static final AnonymousClass1 VISIBILITY_APPLICATOR = new AnonymousClass1(5);
    public static final AppNameApplicator APP_NAME_APPLICATOR = new AppNameApplicator(5);
    public static final AnonymousClass1 LEFT_ICON_APPLICATOR = new AnonymousClass1(1);
    public static final AnonymousClass1 ICON_EXTRACTOR = new AnonymousClass1(0);
    public static final AnonymousClass1 GREY_APPLICATOR = new AnonymousClass1(2);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.NotificationGroupingUtil$1, reason: invalid class name */
    public class AnonymousClass1 implements ViewComparator {
        public static final int[] MARGIN_ADJUSTED_VIEWS = {R.id.textPersonName, R.id.button_always, R.id.title, R.id.oem, R.id.numberPassword};
        public final /* synthetic */ int $r8$classId;

        public /* synthetic */ AnonymousClass1(int i) {
            this.$r8$classId = i;
        }

        /* JADX WARN: Removed duplicated region for block: B:23:0x005d  */
        /* JADX WARN: Removed duplicated region for block: B:33:0x0072  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x007a  */
        /* JADX WARN: Removed duplicated region for block: B:72:0x0074  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void apply(android.view.View r5, android.view.View r6, boolean r7, boolean r8) {
            /*
                Method dump skipped, instructions count: 238
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.NotificationGroupingUtil.AnonymousClass1.apply(android.view.View, android.view.View, boolean, boolean):void");
        }

        @Override // com.android.systemui.statusbar.NotificationGroupingUtil.ViewComparator
        public boolean compare(View view, View view2, Object obj, Object obj2) {
            switch (this.$r8$classId) {
                case 3:
                    return view.getVisibility() != 8;
                default:
                    TextView textView = (TextView) view;
                    return Objects.equals(textView == null ? "" : textView.getText(), ((TextView) view2).getText());
            }
        }

        @Override // com.android.systemui.statusbar.NotificationGroupingUtil.ViewComparator
        public boolean isEmpty(View view) {
            switch (this.$r8$classId) {
                case 3:
                    if (view == null) {
                        return true;
                    }
                    return (view instanceof ImageView) && ((ImageView) view).getDrawable() == null;
                default:
                    return view == null || TextUtils.isEmpty(((TextView) view).getText());
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class AppNameApplicator extends AnonymousClass1 {
        @Override // com.android.systemui.statusbar.NotificationGroupingUtil.AnonymousClass1
        public final void apply(View view, View view2, boolean z, boolean z2) {
            if (z2 && (view instanceof ConversationLayout)) {
                z = ((ConversationLayout) view).shouldHideAppName();
            }
            super.apply(view, view2, z, z2);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class AppNameComparator extends AnonymousClass1 {
        @Override // com.android.systemui.statusbar.NotificationGroupingUtil.AnonymousClass1, com.android.systemui.statusbar.NotificationGroupingUtil.ViewComparator
        public final boolean compare(View view, View view2, Object obj, Object obj2) {
            if (isEmpty(view2)) {
                return true;
            }
            return super.compare(view, view2, obj, obj2);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Processor {
        public final AnonymousClass1 mApplicator;
        public boolean mApply;
        public final ViewComparator mComparator;
        public final AnonymousClass1 mExtractor;
        public final int mId;
        public Object mParentData;
        public final ExpandableNotificationRow mParentRow;
        public View mParentView;

        public Processor(ExpandableNotificationRow expandableNotificationRow, int i, AnonymousClass1 anonymousClass1, ViewComparator viewComparator, AnonymousClass1 anonymousClass12) {
            this.mId = i;
            this.mExtractor = anonymousClass1;
            this.mApplicator = anonymousClass12;
            this.mComparator = viewComparator;
            this.mParentRow = expandableNotificationRow;
        }

        public final void apply(ExpandableNotificationRow expandableNotificationRow, boolean z) {
            NotificationViewWrapper notificationViewWrapper;
            NotificationViewWrapper notificationViewWrapper2;
            boolean z2 = this.mApply && !z;
            boolean z3 = expandableNotificationRow.mIsSummaryWithChildren;
            if (!z3) {
                applyToView(expandableNotificationRow.mPrivateLayout.mContractedChild, z2, z);
                applyToView(expandableNotificationRow.mPrivateLayout.mHeadsUpChild, z2, z);
                applyToView(expandableNotificationRow.mPrivateLayout.mExpandedChild, z2, z);
                return;
            }
            if (z3) {
                notificationViewWrapper = expandableNotificationRow.mChildrenContainer.mGroupHeaderWrapper;
            } else {
                NotificationContentView notificationContentView = expandableNotificationRow.mPrivateLayout;
                if ((notificationContentView.mContractedChild != null && (notificationViewWrapper2 = notificationContentView.mContractedWrapper) != null) || (notificationContentView.mExpandedChild != null && (notificationViewWrapper2 = notificationContentView.mExpandedWrapper) != null)) {
                    notificationViewWrapper = notificationViewWrapper2;
                } else if (notificationContentView.mHeadsUpChild == null || (notificationViewWrapper = notificationContentView.mHeadsUpWrapper) == null) {
                    notificationViewWrapper = null;
                }
            }
            applyToView(notificationViewWrapper.getNotificationHeader(), z2, z);
        }

        public final void applyToView(View view, boolean z, boolean z2) {
            View findViewById;
            if (view == null || (findViewById = view.findViewById(this.mId)) == null || this.mComparator.isEmpty(findViewById)) {
                return;
            }
            this.mApplicator.apply(view, findViewById, z, z2);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface ViewComparator {
        boolean compare(View view, View view2, Object obj, Object obj2);

        boolean isEmpty(View view);
    }

    /* JADX WARN: Type inference failed for: r0v7, types: [com.android.systemui.statusbar.NotificationGroupingUtil$2] */
    /* JADX WARN: Type inference failed for: r0v8, types: [com.android.systemui.statusbar.NotificationGroupingUtil$2] */
    static {
        final int i = 0;
        ICON_VISIBILITY_COMPARATOR = new ViewComparator() { // from class: com.android.systemui.statusbar.NotificationGroupingUtil.2
            @Override // com.android.systemui.statusbar.NotificationGroupingUtil.ViewComparator
            public final boolean compare(View view, View view2, Object obj, Object obj2) {
                switch (i) {
                    case 0:
                        Notification notification = (Notification) obj;
                        Notification notification2 = (Notification) obj2;
                        if ((notification.shouldUseAppIcon() ? notification.getAppIcon() : notification.getSmallIcon()).sameAs(notification2.shouldUseAppIcon() ? notification2.getAppIcon() : notification2.getSmallIcon())) {
                            if ((notification.shouldUseAppIcon() ? 0 : notification.color) == (notification2.shouldUseAppIcon() ? 0 : notification2.color)) {
                            }
                        }
                        break;
                    default:
                        Notification notification3 = (Notification) obj;
                        Notification notification4 = (Notification) obj2;
                        if ((notification3.shouldUseAppIcon() ? notification3.getAppIcon() : notification3.getSmallIcon()).sameAs(notification4.shouldUseAppIcon() ? notification4.getAppIcon() : notification4.getSmallIcon())) {
                            if ((notification3.shouldUseAppIcon() ? 0 : notification3.color) == (notification4.shouldUseAppIcon() ? 0 : notification4.color)) {
                            }
                        }
                        break;
                }
                return false;
            }

            @Override // com.android.systemui.statusbar.NotificationGroupingUtil.ViewComparator
            public final boolean isEmpty(View view) {
                return false;
            }
        };
        final int i2 = 1;
        GREY_COMPARATOR = new ViewComparator() { // from class: com.android.systemui.statusbar.NotificationGroupingUtil.2
            @Override // com.android.systemui.statusbar.NotificationGroupingUtil.ViewComparator
            public final boolean compare(View view, View view2, Object obj, Object obj2) {
                switch (i2) {
                    case 0:
                        Notification notification = (Notification) obj;
                        Notification notification2 = (Notification) obj2;
                        if ((notification.shouldUseAppIcon() ? notification.getAppIcon() : notification.getSmallIcon()).sameAs(notification2.shouldUseAppIcon() ? notification2.getAppIcon() : notification2.getSmallIcon())) {
                            if ((notification.shouldUseAppIcon() ? 0 : notification.color) == (notification2.shouldUseAppIcon() ? 0 : notification2.color)) {
                            }
                        }
                        break;
                    default:
                        Notification notification3 = (Notification) obj;
                        Notification notification4 = (Notification) obj2;
                        if ((notification3.shouldUseAppIcon() ? notification3.getAppIcon() : notification3.getSmallIcon()).sameAs(notification4.shouldUseAppIcon() ? notification4.getAppIcon() : notification4.getSmallIcon())) {
                            if ((notification3.shouldUseAppIcon() ? 0 : notification3.color) == (notification4.shouldUseAppIcon() ? 0 : notification4.color)) {
                            }
                        }
                        break;
                }
                return false;
            }

            @Override // com.android.systemui.statusbar.NotificationGroupingUtil.ViewComparator
            public final boolean isEmpty(View view) {
                return false;
            }
        };
    }

    public NotificationGroupingUtil(ExpandableNotificationRow expandableNotificationRow) {
        ArrayList arrayList = new ArrayList();
        this.mProcessors = arrayList;
        HashSet hashSet = new HashSet();
        this.mDividers = hashSet;
        this.mRow = expandableNotificationRow;
        AnonymousClass1 anonymousClass1 = ICON_EXTRACTOR;
        AnonymousClass2 anonymousClass2 = ICON_VISIBILITY_COMPARATOR;
        AnonymousClass1 anonymousClass12 = VISIBILITY_APPLICATOR;
        arrayList.add(new Processor(expandableNotificationRow, R.id.icon, anonymousClass1, anonymousClass2, anonymousClass12));
        arrayList.add(new Processor(expandableNotificationRow, R.id.sync, anonymousClass1, GREY_COMPARATOR, GREY_APPLICATOR));
        arrayList.add(new Processor(expandableNotificationRow, R.id.sync, anonymousClass1, anonymousClass2, LEFT_ICON_APPLICATOR));
        arrayList.add(new Processor(expandableNotificationRow, R.id.radio, null, BADGE_COMPARATOR, anonymousClass12));
        arrayList.add(new Processor(expandableNotificationRow, R.id.autofill_dataset_picker, null, APP_NAME_COMPARATOR, APP_NAME_APPLICATOR));
        arrayList.add(new Processor(expandableNotificationRow, R.id.hour, null, TEXT_VIEW_COMPARATOR, anonymousClass12));
        hashSet.add(Integer.valueOf(R.id.hours));
        hashSet.add(Integer.valueOf(R.id.icon_menu));
        hashSet.add(Integer.valueOf(R.id.top));
    }

    public final void sanitizeTopLine(ViewGroup viewGroup, ExpandableNotificationRow expandableNotificationRow) {
        int i;
        View view;
        boolean z;
        if (viewGroup == null) {
            return;
        }
        int childCount = viewGroup.getChildCount();
        View findViewById = viewGroup.findViewById(R.id.to_org_unit);
        int i2 = 0;
        while (true) {
            if (i2 >= childCount) {
                break;
            }
            View childAt = viewGroup.getChildAt(i2);
            if (!(childAt instanceof TextView) || childAt.getVisibility() == 8 || this.mDividers.contains(Integer.valueOf(childAt.getId())) || childAt == findViewById) {
                i2++;
            } else if (!expandableNotificationRow.mEntry.mSbn.getNotification().showsTime()) {
                i = 8;
            }
        }
        i = 0;
        findViewById.setVisibility(i);
        View view2 = null;
        int i3 = 0;
        while (i3 < childCount) {
            View childAt2 = viewGroup.getChildAt(i3);
            if (this.mDividers.contains(Integer.valueOf(childAt2.getId()))) {
                while (true) {
                    i3++;
                    if (i3 >= childCount) {
                        break;
                    }
                    view = viewGroup.getChildAt(i3);
                    if (this.mDividers.contains(Integer.valueOf(view.getId()))) {
                        i3--;
                        break;
                    } else if (view.getVisibility() != 8 && (view instanceof TextView)) {
                        if (view2 != null) {
                            z = true;
                        }
                    }
                }
                view = view2;
                z = false;
                childAt2.setVisibility(z ? 0 : 8);
                view2 = view;
            } else if (childAt2.getVisibility() != 8 && (childAt2 instanceof TextView)) {
                view2 = childAt2;
            }
            i3++;
        }
    }

    public final void sanitizeTopLineViews(ExpandableNotificationRow expandableNotificationRow) {
        NotificationViewWrapper notificationViewWrapper;
        NotificationViewWrapper notificationViewWrapper2;
        boolean z = expandableNotificationRow.mIsSummaryWithChildren;
        if (!z) {
            NotificationContentView notificationContentView = expandableNotificationRow.mPrivateLayout;
            View view = notificationContentView.mContractedChild;
            if (view != null) {
                sanitizeTopLine((ViewGroup) view.findViewById(R.id.open_cross_profile), expandableNotificationRow);
            }
            View view2 = notificationContentView.mHeadsUpChild;
            if (view2 != null) {
                sanitizeTopLine((ViewGroup) view2.findViewById(R.id.open_cross_profile), expandableNotificationRow);
            }
            View view3 = notificationContentView.mExpandedChild;
            if (view3 != null) {
                sanitizeTopLine((ViewGroup) view3.findViewById(R.id.open_cross_profile), expandableNotificationRow);
                return;
            }
            return;
        }
        if (z) {
            notificationViewWrapper = expandableNotificationRow.mChildrenContainer.mGroupHeaderWrapper;
        } else {
            NotificationContentView notificationContentView2 = expandableNotificationRow.mPrivateLayout;
            if ((notificationContentView2.mContractedChild != null && (notificationViewWrapper2 = notificationContentView2.mContractedWrapper) != null) || (notificationContentView2.mExpandedChild != null && (notificationViewWrapper2 = notificationContentView2.mExpandedWrapper) != null)) {
                notificationViewWrapper = notificationViewWrapper2;
            } else if (notificationContentView2.mHeadsUpChild == null || (notificationViewWrapper = notificationContentView2.mHeadsUpWrapper) == null) {
                notificationViewWrapper = null;
            }
        }
        sanitizeTopLine(notificationViewWrapper.getNotificationHeader(), expandableNotificationRow);
    }
}
