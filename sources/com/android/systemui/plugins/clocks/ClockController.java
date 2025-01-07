package com.android.systemui.plugins.clocks;

import android.content.res.Resources;
import java.io.PrintWriter;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface ClockController {
    void dump(PrintWriter printWriter);

    ClockConfig getConfig();

    ClockEvents getEvents();

    ClockFaceController getLargeClock();

    ClockFaceController getSmallClock();

    void initialize(Resources resources, float f, float f2);
}
