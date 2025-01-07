package com.android.systemui.statusbar.events;

import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface StatusEvent {
    String getContentDescription();

    boolean getForceVisible();

    int getPriority();

    boolean getShouldAnnounceAccessibilityEvent();

    boolean getShowAnimation();

    Function1 getViewCreator();

    void setForceVisible();

    default boolean shouldUpdateFromEvent(StatusEvent statusEvent) {
        return false;
    }

    default void updateFromEvent(StatusEvent statusEvent) {
    }
}
