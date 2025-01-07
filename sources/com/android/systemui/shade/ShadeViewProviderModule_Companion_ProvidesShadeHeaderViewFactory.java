package com.android.systemui.shade;

import android.view.ViewStub;
import androidx.constraintlayout.motion.widget.MotionLayout;
import com.android.wm.shell.R;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ShadeViewProviderModule_Companion_ProvidesShadeHeaderViewFactory implements Provider {
    public static MotionLayout providesShadeHeaderView(NotificationShadeWindowView notificationShadeWindowView) {
        ViewStub viewStub = (ViewStub) notificationShadeWindowView.requireViewById(R.id.qs_header_stub);
        viewStub.setLayoutResource(R.layout.combined_qs_header);
        return (MotionLayout) viewStub.inflate();
    }
}
