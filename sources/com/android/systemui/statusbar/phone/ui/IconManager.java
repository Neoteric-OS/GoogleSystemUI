package com.android.systemui.statusbar.phone.ui;

import android.R;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.android.internal.statusbar.StatusBarIcon;
import com.android.systemui.demomode.DemoModeCommandReceiver;
import com.android.systemui.statusbar.StatusBarIconView;
import com.android.systemui.statusbar.StatusIconDisplayable;
import com.android.systemui.statusbar.connectivity.ui.MobileContextProvider;
import com.android.systemui.statusbar.phone.DemoStatusIcons;
import com.android.systemui.statusbar.phone.StatusBarIconHolder;
import com.android.systemui.statusbar.phone.StatusBarLocation;
import com.android.systemui.statusbar.pipeline.mobile.ui.MobileUiAdapter;
import com.android.systemui.statusbar.pipeline.mobile.ui.binder.MobileIconsBinder;
import com.android.systemui.statusbar.pipeline.mobile.ui.view.ModernStatusBarMobileView;
import com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconsViewModel;
import com.android.systemui.statusbar.pipeline.shared.ui.view.SingleBindableStatusBarIconView;
import com.android.systemui.statusbar.pipeline.wifi.ui.WifiUiAdapter;
import com.android.systemui.statusbar.pipeline.wifi.ui.view.ModernStatusBarWifiView;
import com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel.LocationBasedWifiViewModel;
import com.android.systemui.util.Assert;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class IconManager implements DemoModeCommandReceiver {
    public final Context mContext;
    public StatusBarIconControllerImpl mController;
    public DemoStatusIcons mDemoStatusIcons;
    public final ViewGroup mGroup;
    public int mIconSize;
    public boolean mIsInDemoMode;
    public final StatusBarLocation mLocation;
    public final MobileContextProvider mMobileContextProvider;
    public final MobileIconsViewModel mMobileIconsViewModel;
    public final LocationBasedWifiViewModel mWifiViewModel;
    public final Map mBindableIcons = new HashMap();
    public boolean mShouldLog = false;
    public final boolean mDemoable = true;
    public final ArrayList mBlockList = new ArrayList();

    public IconManager(ViewGroup viewGroup, StatusBarLocation statusBarLocation, WifiUiAdapter wifiUiAdapter, MobileUiAdapter mobileUiAdapter, MobileContextProvider mobileContextProvider) {
        this.mGroup = viewGroup;
        this.mMobileContextProvider = mobileContextProvider;
        Context context = viewGroup.getContext();
        this.mContext = context;
        this.mLocation = statusBarLocation;
        this.mIconSize = context.getResources().getDimensionPixelSize(R.dimen.text_edit_floating_toolbar_elevation);
        MobileIconsViewModel mobileIconsViewModel = mobileUiAdapter.mobileIconsViewModel;
        this.mMobileIconsViewModel = mobileIconsViewModel;
        MobileIconsBinder.bind(viewGroup, mobileIconsViewModel);
        this.mWifiViewModel = wifiUiAdapter.bindGroup(viewGroup, statusBarLocation);
    }

    public final StatusIconDisplayable addHolder(int i, String str, boolean z, StatusBarIconHolder statusBarIconHolder) {
        if (this.mBlockList.contains(str)) {
            z = true;
        }
        int type = statusBarIconHolder.getType();
        if (type == 0) {
            StatusBarIcon statusBarIcon = statusBarIconHolder.icon;
            StatusBarIconView statusBarIconView = new StatusBarIconView(this.mContext, str, null, z);
            statusBarIconView.set(statusBarIcon);
            this.mGroup.addView(statusBarIconView, i, onCreateLayoutParams(statusBarIcon.shape));
            return statusBarIconView;
        }
        if (type != 3) {
            if (type == 4) {
                Context context = this.mContext;
                LocationBasedWifiViewModel locationBasedWifiViewModel = this.mWifiViewModel;
                ModernStatusBarWifiView constructAndBind = ModernStatusBarWifiView.constructAndBind(context, str, locationBasedWifiViewModel);
                this.mGroup.addView(constructAndBind, i, onCreateLayoutParams(StatusBarIcon.Shape.WRAP_CONTENT));
                if (!this.mIsInDemoMode) {
                    return constructAndBind;
                }
                this.mDemoStatusIcons.addModernWifiView(locationBasedWifiViewModel);
                return constructAndBind;
            }
            if (type != 5) {
                return null;
            }
            StatusBarIconHolder.BindableIconHolder bindableIconHolder = (StatusBarIconHolder.BindableIconHolder) statusBarIconHolder;
            this.mBindableIcons.put(bindableIconHolder.slot, bindableIconHolder);
            SingleBindableStatusBarIconView createAndBind = bindableIconHolder.initializer.createAndBind(this.mContext);
            this.mGroup.addView(createAndBind, i, onCreateLayoutParams(StatusBarIcon.Shape.WRAP_CONTENT));
            if (!this.mIsInDemoMode) {
                return createAndBind;
            }
            this.mDemoStatusIcons.addBindableIcon(bindableIconHolder);
            return createAndBind;
        }
        int i2 = statusBarIconHolder.tag;
        Context context2 = this.mContext;
        MobileContextProvider mobileContextProvider = this.mMobileContextProvider;
        Context mobileContextForSub = mobileContextProvider.getMobileContextForSub(i2, context2);
        MobileIconsViewModel mobileIconsViewModel = this.mMobileIconsViewModel;
        ModernStatusBarMobileView constructAndBind2 = ModernStatusBarMobileView.constructAndBind(mobileContextForSub, mobileIconsViewModel.logger, str, mobileIconsViewModel.viewModelForSub(i2, this.mLocation));
        this.mGroup.addView(constructAndBind2, i, onCreateLayoutParams(StatusBarIcon.Shape.WRAP_CONTENT));
        if (this.mIsInDemoMode) {
            Context mobileContextForSub2 = mobileContextProvider.getMobileContextForSub(i2, this.mContext);
            DemoStatusIcons demoStatusIcons = this.mDemoStatusIcons;
            demoStatusIcons.getClass();
            Log.d("DemoStatusIcons", "addModernMobileView (subId=" + i2 + ")");
            ModernStatusBarMobileView constructAndBind3 = ModernStatusBarMobileView.constructAndBind(mobileContextForSub2, mobileIconsViewModel.logger, "mobile", demoStatusIcons.mMobileIconsViewModel.viewModelForSub(i2, demoStatusIcons.mLocation));
            demoStatusIcons.mModernMobileViews.add(constructAndBind3);
            demoStatusIcons.addView(constructAndBind3, demoStatusIcons.getChildCount(), new LinearLayout.LayoutParams(-2, demoStatusIcons.mIconSize));
        }
        return constructAndBind2;
    }

    public abstract DemoStatusIcons createDemoStatusIcons();

    public void destroy() {
        this.mGroup.removeAllViews();
    }

    @Override // com.android.systemui.demomode.DemoModeCommandReceiver
    public final void dispatchDemoCommand(Bundle bundle, String str) {
        if (this.mDemoable) {
            this.mDemoStatusIcons.dispatchDemoCommand(bundle, str);
        }
    }

    public void exitDemoMode() {
        DemoStatusIcons demoStatusIcons = this.mDemoStatusIcons;
        ((ViewGroup) demoStatusIcons.getParent()).removeView(demoStatusIcons);
        this.mDemoStatusIcons = null;
    }

    public LinearLayout.LayoutParams onCreateLayoutParams(StatusBarIcon.Shape shape) {
        return new LinearLayout.LayoutParams(-2, this.mIconSize);
    }

    @Override // com.android.systemui.demomode.DemoModeCommandReceiver
    public final void onDemoModeFinished() {
        DemoStatusIcons demoStatusIcons = this.mDemoStatusIcons;
        if (demoStatusIcons != null) {
            demoStatusIcons.onDemoModeFinished();
            exitDemoMode();
            this.mIsInDemoMode = false;
        }
    }

    @Override // com.android.systemui.demomode.DemoModeCommandReceiver
    public final void onDemoModeStarted() {
        this.mIsInDemoMode = true;
        if (this.mDemoStatusIcons == null) {
            DemoStatusIcons createDemoStatusIcons = createDemoStatusIcons();
            this.mDemoStatusIcons = createDemoStatusIcons;
            createDemoStatusIcons.addModernWifiView(this.mWifiViewModel);
            Iterator it = this.mBindableIcons.values().iterator();
            while (it.hasNext()) {
                this.mDemoStatusIcons.addBindableIcon((StatusBarIconHolder.BindableIconHolder) it.next());
            }
        }
        this.mDemoStatusIcons.onDemoModeStarted();
    }

    public abstract void onIconAdded(int i, String str, boolean z, StatusBarIconHolder statusBarIconHolder);

    public void onRemoveIcon(int i) {
        if (this.mIsInDemoMode) {
            DemoStatusIcons demoStatusIcons = this.mDemoStatusIcons;
            StatusIconDisplayable statusIconDisplayable = (StatusIconDisplayable) this.mGroup.getChildAt(i);
            demoStatusIcons.getClass();
            ModernStatusBarMobileView modernStatusBarMobileView = null;
            if (statusIconDisplayable.getSlot().equals("wifi")) {
                if (statusIconDisplayable instanceof ModernStatusBarWifiView) {
                    Log.d("DemoStatusIcons", "onRemoveIcon: removing modern wifi view");
                    demoStatusIcons.removeView(demoStatusIcons.mModernWifiView);
                    demoStatusIcons.mModernWifiView = null;
                }
            } else if (statusIconDisplayable instanceof ModernStatusBarMobileView) {
                ModernStatusBarMobileView modernStatusBarMobileView2 = (ModernStatusBarMobileView) statusIconDisplayable;
                Iterator it = demoStatusIcons.mModernMobileViews.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    ModernStatusBarMobileView modernStatusBarMobileView3 = (ModernStatusBarMobileView) it.next();
                    if (modernStatusBarMobileView3.subId == modernStatusBarMobileView2.subId) {
                        modernStatusBarMobileView = modernStatusBarMobileView3;
                        break;
                    }
                }
                if (modernStatusBarMobileView != null) {
                    demoStatusIcons.removeView(modernStatusBarMobileView);
                    demoStatusIcons.mModernMobileViews.remove(modernStatusBarMobileView);
                }
            }
        }
        this.mGroup.removeViewAt(i);
    }

    public void onSetIcon(int i, StatusBarIcon statusBarIcon) {
        ((StatusBarIconView) this.mGroup.getChildAt(i)).set(statusBarIcon);
    }

    public final void setBlockList(List list) {
        Assert.isMainThread();
        this.mBlockList.clear();
        this.mBlockList.addAll(list);
        StatusBarIconControllerImpl statusBarIconControllerImpl = this.mController;
        if (statusBarIconControllerImpl != null) {
            destroy();
            statusBarIconControllerImpl.mIconGroups.remove(this);
            statusBarIconControllerImpl.addIconGroup(this);
        }
    }
}
