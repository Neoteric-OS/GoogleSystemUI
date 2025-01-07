package com.android.systemui.shade.transition;

import android.content.Context;
import android.content.res.Configuration;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.SplitShadeStateControllerImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class LargeScreenShadeInterpolatorImpl implements LargeScreenShadeInterpolator {
    public final Context context;
    public boolean inSplitShade;
    public final LargeScreenPortraitShadeInterpolator portraitShadeInterpolator;
    public final SplitShadeInterpolator splitShadeInterpolator;
    public final SplitShadeStateControllerImpl splitShadeStateController;

    public LargeScreenShadeInterpolatorImpl(ConfigurationController configurationController, Context context, SplitShadeInterpolator splitShadeInterpolator, LargeScreenPortraitShadeInterpolator largeScreenPortraitShadeInterpolator, SplitShadeStateControllerImpl splitShadeStateControllerImpl) {
        this.context = context;
        this.splitShadeInterpolator = splitShadeInterpolator;
        this.portraitShadeInterpolator = largeScreenPortraitShadeInterpolator;
        this.splitShadeStateController = splitShadeStateControllerImpl;
        ((ConfigurationControllerImpl) configurationController).addCallback(new ConfigurationController.ConfigurationListener() { // from class: com.android.systemui.shade.transition.LargeScreenShadeInterpolatorImpl.1
            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onConfigChanged(Configuration configuration) {
                LargeScreenShadeInterpolatorImpl largeScreenShadeInterpolatorImpl = LargeScreenShadeInterpolatorImpl.this;
                largeScreenShadeInterpolatorImpl.inSplitShade = largeScreenShadeInterpolatorImpl.splitShadeStateController.shouldUseSplitNotificationShade(largeScreenShadeInterpolatorImpl.context.getResources());
            }
        });
        this.inSplitShade = splitShadeStateControllerImpl.shouldUseSplitNotificationShade(context.getResources());
    }

    @Override // com.android.systemui.shade.transition.LargeScreenShadeInterpolator
    public final float getBehindScrimAlpha(float f) {
        return getImpl().getBehindScrimAlpha(f);
    }

    public final LargeScreenShadeInterpolator getImpl() {
        return this.inSplitShade ? this.splitShadeInterpolator : this.portraitShadeInterpolator;
    }

    @Override // com.android.systemui.shade.transition.LargeScreenShadeInterpolator
    public final float getNotificationContentAlpha(float f) {
        return getImpl().getNotificationContentAlpha(f);
    }

    @Override // com.android.systemui.shade.transition.LargeScreenShadeInterpolator
    public final float getNotificationFooterAlpha(float f) {
        return getImpl().getNotificationFooterAlpha(f);
    }

    @Override // com.android.systemui.shade.transition.LargeScreenShadeInterpolator
    public final float getNotificationScrimAlpha(float f) {
        return getImpl().getNotificationScrimAlpha(f);
    }

    @Override // com.android.systemui.shade.transition.LargeScreenShadeInterpolator
    public final float getQsAlpha(float f) {
        return getImpl().getQsAlpha(f);
    }
}
