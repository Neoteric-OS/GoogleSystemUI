package com.android.systemui.keyguard.domain.interactor;

import android.content.Context;
import com.android.systemui.back.domain.interactor.BackActionInteractor;
import com.android.systemui.media.controls.util.MediaSessionLegacyHelperWrapper;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.shade.ShadeController;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardKeyEventInteractor {
    public final BackActionInteractor backActionInteractor;
    public final Context context;
    public final MediaSessionLegacyHelperWrapper mediaSessionLegacyHelperWrapper;
    public final PowerInteractor powerInteractor;
    public final ShadeController shadeController;
    public final StatusBarKeyguardViewManager statusBarKeyguardViewManager;
    public final StatusBarStateController statusBarStateController;

    public KeyguardKeyEventInteractor(Context context, StatusBarStateController statusBarStateController, StatusBarKeyguardViewManager statusBarKeyguardViewManager, ShadeController shadeController, MediaSessionLegacyHelperWrapper mediaSessionLegacyHelperWrapper, BackActionInteractor backActionInteractor, PowerInteractor powerInteractor) {
        this.context = context;
        this.statusBarStateController = statusBarStateController;
        this.statusBarKeyguardViewManager = statusBarKeyguardViewManager;
        this.shadeController = shadeController;
        this.mediaSessionLegacyHelperWrapper = mediaSessionLegacyHelperWrapper;
        this.backActionInteractor = backActionInteractor;
        this.powerInteractor = powerInteractor;
    }
}
