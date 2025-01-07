package com.android.wm.shell.bubbles;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Insets;
import android.graphics.Rect;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.WindowMetrics;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DeviceConfig {
    public final Insets insets;
    public final boolean isLandscape;
    public final boolean isLargeScreen;
    public final boolean isRtl;
    public final boolean isSmallTablet;
    public final Rect windowBounds;

    public DeviceConfig(boolean z, boolean z2, boolean z3, boolean z4, Rect rect, Insets insets) {
        this.isLargeScreen = z;
        this.isSmallTablet = z2;
        this.isLandscape = z3;
        this.isRtl = z4;
        this.windowBounds = rect;
        this.insets = insets;
    }

    public static final DeviceConfig create(Context context, WindowManager windowManager) {
        WindowMetrics currentWindowMetrics = windowManager.getCurrentWindowMetrics();
        Insets insetsIgnoringVisibility = currentWindowMetrics.getWindowInsets().getInsetsIgnoringVisibility(WindowInsets.Type.navigationBars() | WindowInsets.Type.statusBars() | WindowInsets.Type.displayCutout());
        Rect bounds = currentWindowMetrics.getBounds();
        Configuration configuration = context.getResources().getConfiguration();
        boolean z = configuration.smallestScreenWidthDp >= 600;
        return new DeviceConfig(z, z && Math.max(configuration.screenWidthDp, configuration.screenHeightDp) < 960, context.getResources().getConfiguration().orientation == 2, context.getResources().getConfiguration().getLayoutDirection() == 1, bounds, insetsIgnoringVisibility);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DeviceConfig)) {
            return false;
        }
        DeviceConfig deviceConfig = (DeviceConfig) obj;
        return this.isLargeScreen == deviceConfig.isLargeScreen && this.isSmallTablet == deviceConfig.isSmallTablet && this.isLandscape == deviceConfig.isLandscape && this.isRtl == deviceConfig.isRtl && Intrinsics.areEqual(this.windowBounds, deviceConfig.windowBounds) && Intrinsics.areEqual(this.insets, deviceConfig.insets);
    }

    public final int hashCode() {
        return this.insets.hashCode() + ((this.windowBounds.hashCode() + TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(Boolean.hashCode(this.isLargeScreen) * 31, 31, this.isSmallTablet), 31, this.isLandscape), 31, this.isRtl)) * 31);
    }

    public final String toString() {
        return "DeviceConfig(isLargeScreen=" + this.isLargeScreen + ", isSmallTablet=" + this.isSmallTablet + ", isLandscape=" + this.isLandscape + ", isRtl=" + this.isRtl + ", windowBounds=" + this.windowBounds + ", insets=" + this.insets + ")";
    }
}
