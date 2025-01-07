package com.android.systemui.shade;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface QuickSettingsController {
    void closeQs();

    void closeQsCustomizer();

    boolean getExpanded();

    boolean isCustomizing();

    boolean shouldQuickSettingsIntercept(float f, float f2, float f3);
}
