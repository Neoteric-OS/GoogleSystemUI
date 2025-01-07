package com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel;

import com.android.systemui.statusbar.phone.StatusBarLocation;
import com.android.systemui.statusbar.pipeline.mobile.ui.VerboseMobileViewLogger;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class LocationBasedMobileViewModel implements MobileIconViewModelCommon {
    public final MobileIconViewModelCommon commonImpl;
    public final StatusBarLocation location;
    public final VerboseMobileViewLogger verboseLogger;

    public LocationBasedMobileViewModel(MobileIconViewModelCommon mobileIconViewModelCommon, StatusBarLocation statusBarLocation, VerboseMobileViewLogger verboseMobileViewLogger) {
        this.commonImpl = mobileIconViewModelCommon;
        this.location = statusBarLocation;
        this.verboseLogger = verboseMobileViewLogger;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconViewModelCommon
    public final Flow getActivityContainerVisible() {
        return this.commonImpl.getActivityContainerVisible();
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconViewModelCommon
    public final Flow getActivityInVisible() {
        return this.commonImpl.getActivityInVisible();
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconViewModelCommon
    public final Flow getActivityOutVisible() {
        return this.commonImpl.getActivityOutVisible();
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconViewModelCommon
    public final Flow getContentDescription() {
        return this.commonImpl.getContentDescription();
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconViewModelCommon
    public final Flow getIcon() {
        return this.commonImpl.getIcon();
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconViewModelCommon
    public final StateFlow getNetworkTypeBackground() {
        return this.commonImpl.getNetworkTypeBackground();
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconViewModelCommon
    public final Flow getNetworkTypeIcon() {
        return this.commonImpl.getNetworkTypeIcon();
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconViewModelCommon
    public final Flow getRoaming() {
        return this.commonImpl.getRoaming();
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconViewModelCommon
    public final int getSubscriptionId() {
        return this.commonImpl.getSubscriptionId();
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconViewModelCommon
    public StateFlow isVisible() {
        return this.commonImpl.isVisible();
    }
}
