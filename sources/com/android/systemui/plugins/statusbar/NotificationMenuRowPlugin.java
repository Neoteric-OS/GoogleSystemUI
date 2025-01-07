package com.android.systemui.plugins.statusbar;

import android.content.Context;
import android.graphics.Point;
import android.service.notification.StatusBarNotification;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.android.systemui.plugins.Plugin;
import com.android.systemui.plugins.annotations.Dependencies;
import com.android.systemui.plugins.annotations.DependsOn;
import com.android.systemui.plugins.annotations.ProvidesInterface;
import com.android.systemui.plugins.statusbar.NotificationSwipeActionHelper;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
@Dependencies({@DependsOn(target = OnMenuEventListener.class), @DependsOn(target = MenuItem.class), @DependsOn(target = NotificationSwipeActionHelper.class), @DependsOn(target = NotificationSwipeActionHelper.SnoozeOption.class)})
@ProvidesInterface(action = NotificationMenuRowPlugin.ACTION, version = 5)
/* loaded from: classes.dex */
public interface NotificationMenuRowPlugin extends Plugin {
    public static final String ACTION = "com.android.systemui.action.PLUGIN_NOTIFICATION_MENU_ROW";
    public static final int VERSION = 5;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    @ProvidesInterface(version = 1)
    public interface MenuItem {
        public static final int VERSION = 1;

        String getContentDescription();

        View getGutsView();

        View getMenuView();
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    @ProvidesInterface(version = 1)
    public interface OnMenuEventListener {
        public static final int VERSION = 1;

        void onMenuClicked(View view, int i, int i2, MenuItem menuItem);

        void onMenuReset(View view);

        void onMenuShown(View view);
    }

    boolean canBeDismissed();

    void createMenu(ViewGroup viewGroup, StatusBarNotification statusBarNotification);

    MenuItem getFeedbackMenuItem(Context context);

    MenuItem getLongpressMenuItem(Context context);

    ArrayList getMenuItems(Context context);

    int getMenuSnapTarget();

    View getMenuView();

    default Point getRevealAnimationOrigin() {
        return new Point(0, 0);
    }

    MenuItem getSnoozeMenuItem(Context context);

    boolean isMenuVisible();

    boolean isSnappedAndOnSameSide();

    boolean isSwipedEnoughToShowMenu();

    boolean isTowardsMenu(float f);

    boolean isWithinSnapMenuThreshold();

    default MenuItem menuItemToExposeOnSnap() {
        return null;
    }

    void onDismiss();

    default boolean onInterceptTouchEvent(View view, MotionEvent motionEvent) {
        return false;
    }

    void onNotificationUpdated(StatusBarNotification statusBarNotification);

    void onParentHeightUpdate();

    void onParentTranslationUpdate(float f);

    void onSnapClosed();

    void onSnapOpen();

    void onTouchEnd();

    void onTouchMove(float f);

    void onTouchStart();

    void resetMenu();

    void setAppName(String str);

    void setMenuClickListener(OnMenuEventListener onMenuEventListener);

    void setMenuItems(ArrayList arrayList);

    default boolean shouldShowGutsOnSnapOpen() {
        return false;
    }

    boolean shouldShowMenu();

    boolean shouldSnapBack();

    default boolean shouldUseDefaultMenuItems() {
        return false;
    }

    default void onConfigurationChanged() {
    }
}
