package com.android.systemui.plugins.clocks;

import android.view.View;
import kotlin.Deprecated;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface ClockFaceController {
    ClockAnimations getAnimations();

    ClockFaceConfig getConfig();

    ClockFaceEvents getEvents();

    ClockFaceLayout getLayout();

    View getView();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DefaultImpls {
        @Deprecated
        public static /* synthetic */ void getView$annotations() {
        }
    }
}
