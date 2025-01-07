package com.android.systemui.statusbar.notification.collection.render;

import android.view.View;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface NodeController {
    default void addChildAt(NodeController nodeController, int i) {
        throw new RuntimeException("Not supported");
    }

    default View getChildAt(int i) {
        throw new RuntimeException("Not supported");
    }

    default int getChildCount() {
        return 0;
    }

    String getNodeLabel();

    View getView();

    default void moveChildTo(NodeController nodeController, int i) {
        throw new RuntimeException("Not supported");
    }

    boolean offerToKeepInParentForAnimation();

    default void removeChild(NodeController nodeController, boolean z) {
        throw new RuntimeException("Not supported");
    }

    boolean removeFromParentIfKeptForAnimation();

    void resetKeepInParentForAnimation();

    default void onViewAdded() {
    }
}
