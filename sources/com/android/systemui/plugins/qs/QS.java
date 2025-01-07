package com.android.systemui.plugins.qs;

import android.graphics.Rect;
import android.view.View;
import com.android.systemui.plugins.FragmentBase;
import com.android.systemui.plugins.annotations.DependsOn;
import com.android.systemui.plugins.annotations.ProvidesInterface;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
@ProvidesInterface(action = QS.ACTION, version = 16)
@DependsOn(target = HeightListener.class)
/* loaded from: classes.dex */
public interface QS extends FragmentBase {
    public static final String ACTION = "com.android.systemui.action.PLUGIN_QS";
    public static final String TAG = "QS";
    public static final int VERSION = 16;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    @ProvidesInterface(version = 1)
    public interface HeightListener {
        public static final int VERSION = 1;

        void onQsHeightChanged();
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    @ProvidesInterface(version = 1)
    public interface ScrollListener {
        public static final int VERSION = 1;

        void onQsPanelScrollChanged(int i);
    }

    void animateHeaderSlidingOut();

    void closeCustomizer();

    void closeDetail();

    default boolean disallowPanelTouches() {
        return isShowingDetail();
    }

    int getDesiredHeight();

    View getHeader();

    int getHeaderBottom();

    void getHeaderBoundsOnScreen(Rect rect);

    default int getHeaderHeight() {
        return getHeaderBottom() - getHeaderTop();
    }

    int getHeaderLeft();

    int getHeaderTop();

    int getHeightDiff();

    int getQsMinExpansionHeight();

    void hideImmediately();

    boolean isCustomizing();

    default boolean isFullyCollapsed() {
        return true;
    }

    boolean isHeaderShown();

    boolean isShowingDetail();

    void notifyCustomizeChanged();

    void setCollapseExpandAction(Runnable runnable);

    void setCollapsedMediaVisibilityChangedListener(Consumer consumer);

    void setContainerController(QSContainerController qSContainerController);

    void setExpanded(boolean z);

    void setFancyClipping(int i, int i2, int i3, int i4, int i5, boolean z, boolean z2);

    void setHeaderClickable(boolean z);

    void setHeaderListening(boolean z);

    void setHeightOverride(int i);

    void setInSplitShade(boolean z);

    void setIsNotificationPanelFullWidth(boolean z);

    void setListening(boolean z);

    void setOverscrolling(boolean z);

    void setPanelView(HeightListener heightListener);

    void setQsExpansion(float f, float f2, float f3, float f4);

    void setQsVisible(boolean z);

    default void setHasNotifications(boolean z) {
    }

    default void setOverScrollAmount(int i) {
    }

    default void setScrollListener(ScrollListener scrollListener) {
    }

    default void setShouldUpdateSquishinessOnMedia(boolean z) {
    }

    default void setTransitionToFullShadeProgress(boolean z, float f, float f2) {
    }
}
