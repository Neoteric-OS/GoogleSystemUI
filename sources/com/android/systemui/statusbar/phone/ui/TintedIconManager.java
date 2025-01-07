package com.android.systemui.statusbar.phone.ui;

import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.android.systemui.statusbar.StatusIconDisplayable;
import com.android.systemui.statusbar.connectivity.ui.MobileContextProvider;
import com.android.systemui.statusbar.phone.DemoStatusIcons;
import com.android.systemui.statusbar.phone.StatusBarIconHolder;
import com.android.systemui.statusbar.phone.StatusBarLocation;
import com.android.systemui.statusbar.pipeline.mobile.ui.MobileUiAdapter;
import com.android.systemui.statusbar.pipeline.wifi.ui.WifiUiAdapter;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TintedIconManager extends IconManager {
    public int mColor;
    public int mForegroundColor;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Factory {
        public final MobileContextProvider mMobileContextProvider;
        public final MobileUiAdapter mMobileUiAdapter;
        public final WifiUiAdapter mWifiUiAdapter;

        public Factory(WifiUiAdapter wifiUiAdapter, MobileUiAdapter mobileUiAdapter, MobileContextProvider mobileContextProvider) {
            this.mWifiUiAdapter = wifiUiAdapter;
            this.mMobileUiAdapter = mobileUiAdapter;
            this.mMobileContextProvider = mobileContextProvider;
        }

        public final TintedIconManager create(ViewGroup viewGroup, StatusBarLocation statusBarLocation) {
            return new TintedIconManager(viewGroup, statusBarLocation, this.mWifiUiAdapter, this.mMobileUiAdapter, this.mMobileContextProvider);
        }
    }

    @Override // com.android.systemui.statusbar.phone.ui.IconManager
    public final DemoStatusIcons createDemoStatusIcons() {
        DemoStatusIcons demoStatusIcons = new DemoStatusIcons((LinearLayout) this.mGroup, this.mMobileIconsViewModel, this.mLocation, this.mIconSize);
        demoStatusIcons.setColor(this.mColor, this.mForegroundColor);
        return demoStatusIcons;
    }

    @Override // com.android.systemui.statusbar.phone.ui.IconManager
    public final void onIconAdded(int i, String str, boolean z, StatusBarIconHolder statusBarIconHolder) {
        StatusIconDisplayable addHolder = addHolder(i, str, z, statusBarIconHolder);
        addHolder.setStaticDrawableColor(this.mColor, this.mForegroundColor);
        addHolder.setDecorColor(this.mColor);
    }

    public final void setTint(int i, int i2) {
        this.mColor = i;
        this.mForegroundColor = i2;
        for (int i3 = 0; i3 < this.mGroup.getChildCount(); i3++) {
            KeyEvent.Callback childAt = this.mGroup.getChildAt(i3);
            if (childAt instanceof StatusIconDisplayable) {
                StatusIconDisplayable statusIconDisplayable = (StatusIconDisplayable) childAt;
                statusIconDisplayable.setStaticDrawableColor(this.mColor, this.mForegroundColor);
                statusIconDisplayable.setDecorColor(this.mColor);
            }
        }
        DemoStatusIcons demoStatusIcons = this.mDemoStatusIcons;
        if (demoStatusIcons != null) {
            demoStatusIcons.setColor(i, i2);
        }
    }
}
