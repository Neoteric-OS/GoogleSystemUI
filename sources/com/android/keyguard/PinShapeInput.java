package com.android.keyguard;

import android.view.View;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface PinShapeInput {
    void append();

    void delete();

    View getView();

    void reset();

    void setDrawColor(int i);
}
