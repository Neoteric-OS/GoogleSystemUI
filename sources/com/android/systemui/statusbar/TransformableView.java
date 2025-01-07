package com.android.systemui.statusbar;

import com.android.systemui.statusbar.notification.TransformState;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface TransformableView {
    TransformState getCurrentState(int i);

    void setVisible(boolean z);

    void transformFrom(float f, TransformableView transformableView);

    void transformFrom(TransformableView transformableView);

    void transformTo(float f, TransformableView transformableView);

    void transformTo(TransformableView transformableView, Runnable runnable);
}
