package com.android.systemui.statusbar.phone.ui;

import android.widget.LinearLayout;
import com.android.internal.statusbar.StatusBarIcon;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.systemui.statusbar.connectivity.ui.MobileContextProvider;
import com.android.systemui.statusbar.phone.DemoStatusIcons;
import com.android.systemui.statusbar.phone.StatusBarIconHolder;
import com.android.systemui.statusbar.phone.StatusBarLocation;
import com.android.systemui.statusbar.pipeline.mobile.ui.MobileUiAdapter;
import com.android.systemui.statusbar.pipeline.wifi.ui.WifiUiAdapter;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DarkIconManager extends IconManager {
    public final DarkIconDispatcher mDarkIconDispatcher;
    public final int mIconHorizontalMargin;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Factory {
        public final DarkIconDispatcher mDarkIconDispatcher;
        public final MobileContextProvider mMobileContextProvider;
        public final MobileUiAdapter mMobileUiAdapter;
        public final WifiUiAdapter mWifiUiAdapter;

        public Factory(WifiUiAdapter wifiUiAdapter, MobileContextProvider mobileContextProvider, MobileUiAdapter mobileUiAdapter, DarkIconDispatcher darkIconDispatcher) {
            this.mWifiUiAdapter = wifiUiAdapter;
            this.mMobileContextProvider = mobileContextProvider;
            this.mMobileUiAdapter = mobileUiAdapter;
            this.mDarkIconDispatcher = darkIconDispatcher;
        }
    }

    public DarkIconManager(LinearLayout linearLayout, WifiUiAdapter wifiUiAdapter, MobileUiAdapter mobileUiAdapter, MobileContextProvider mobileContextProvider, DarkIconDispatcher darkIconDispatcher) {
        super(linearLayout, StatusBarLocation.HOME, wifiUiAdapter, mobileUiAdapter, mobileContextProvider);
        this.mIconHorizontalMargin = this.mContext.getResources().getDimensionPixelSize(R.dimen.status_bar_icon_horizontal_margin);
        this.mDarkIconDispatcher = darkIconDispatcher;
    }

    @Override // com.android.systemui.statusbar.phone.ui.IconManager
    public final DemoStatusIcons createDemoStatusIcons() {
        DemoStatusIcons demoStatusIcons = new DemoStatusIcons((LinearLayout) this.mGroup, this.mMobileIconsViewModel, this.mLocation, this.mIconSize);
        this.mDarkIconDispatcher.addDarkReceiver(demoStatusIcons);
        return demoStatusIcons;
    }

    @Override // com.android.systemui.statusbar.phone.ui.IconManager
    public final void destroy() {
        for (int i = 0; i < this.mGroup.getChildCount(); i++) {
            this.mDarkIconDispatcher.removeDarkReceiver((DarkIconDispatcher.DarkReceiver) this.mGroup.getChildAt(i));
        }
        this.mGroup.removeAllViews();
    }

    @Override // com.android.systemui.statusbar.phone.ui.IconManager
    public final void exitDemoMode() {
        this.mDarkIconDispatcher.removeDarkReceiver(this.mDemoStatusIcons);
        super.exitDemoMode();
    }

    @Override // com.android.systemui.statusbar.phone.ui.IconManager
    public final LinearLayout.LayoutParams onCreateLayoutParams(StatusBarIcon.Shape shape) {
        LinearLayout.LayoutParams onCreateLayoutParams = super.onCreateLayoutParams(shape);
        int i = this.mIconHorizontalMargin;
        onCreateLayoutParams.setMargins(i, 0, i, 0);
        return onCreateLayoutParams;
    }

    @Override // com.android.systemui.statusbar.phone.ui.IconManager
    public final void onIconAdded(int i, String str, boolean z, StatusBarIconHolder statusBarIconHolder) {
        this.mDarkIconDispatcher.addDarkReceiver(addHolder(i, str, z, statusBarIconHolder));
    }

    @Override // com.android.systemui.statusbar.phone.ui.IconManager
    public final void onRemoveIcon(int i) {
        this.mDarkIconDispatcher.removeDarkReceiver((DarkIconDispatcher.DarkReceiver) this.mGroup.getChildAt(i));
        super.onRemoveIcon(i);
    }

    @Override // com.android.systemui.statusbar.phone.ui.IconManager
    public final void onSetIcon(int i, StatusBarIcon statusBarIcon) {
        super.onSetIcon(i, statusBarIcon);
        this.mDarkIconDispatcher.applyDark((DarkIconDispatcher.DarkReceiver) this.mGroup.getChildAt(i));
    }
}
