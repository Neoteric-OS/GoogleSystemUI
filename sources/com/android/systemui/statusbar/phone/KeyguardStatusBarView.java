package com.android.systemui.statusbar.phone;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Insets;
import android.graphics.Rect;
import android.os.Trace;
import android.util.AttributeSet;
import android.view.DisplayCutout;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowInsets;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.systemui.ScreenDecorations;
import com.android.systemui.battery.BatteryMeterView;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.systemui.statusbar.phone.ui.TintedIconManager;
import com.android.systemui.statusbar.phone.userswitcher.StatusBarUserSwitcherContainer;
import com.android.systemui.util.Utils;
import com.android.wm.shell.R;
import java.util.ArrayList;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class KeyguardStatusBarView extends RelativeLayout {
    public boolean mBatteryCharging;
    public BatteryMeterView mBatteryView;
    public TextView mCarrierLabel;
    public final Rect mClipRect;
    public int mCutoutSideNudge;
    public View mCutoutSpace;
    public final StateFlowImpl mDarkChange;
    public DisplayCutout mDisplayCutout;
    public final ArrayList mEmptyTintRect;
    public boolean mIsPrivacyDotEnabled;
    public boolean mIsUserSwitcherEnabled;
    public boolean mKeyguardUserAvatarEnabled;
    public boolean mKeyguardUserSwitcherEnabled;
    public int mLayoutState;
    public int mMinDotWidth;
    public ImageView mMultiUserAvatar;
    public Insets mPadding;
    public boolean mShowPercentAvailable;
    public int mStatusBarPaddingEnd;
    public ViewGroup mStatusIconArea;
    public StatusIconContainer mStatusIconContainer;
    public View mSystemIcons;
    public View mSystemIconsContainer;
    public int mSystemIconsSwitcherHiddenExpandedMargin;
    public int mTopClipping;
    public StatusBarUserSwitcherContainer mUserSwitcherContainer;

    public KeyguardStatusBarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mEmptyTintRect = new ArrayList();
        this.mDarkChange = StateFlowKt.MutableStateFlow(SysuiDarkIconDispatcher$DarkChange.EMPTY);
        this.mLayoutState = 0;
        this.mCutoutSideNudge = 0;
        this.mPadding = Insets.of(0, 0, 0, 0);
        this.mClipRect = new Rect(0, 0, 0, 0);
    }

    @Override // android.view.View
    public final boolean hasOverlappingRendering() {
        return false;
    }

    public boolean isKeyguardUserAvatarEnabled() {
        return this.mKeyguardUserAvatarEnabled;
    }

    public final void loadDimens() {
        Resources resources = getResources();
        this.mSystemIconsSwitcherHiddenExpandedMargin = resources.getDimensionPixelSize(R.dimen.system_icons_switcher_hidden_expanded_margin);
        this.mStatusBarPaddingEnd = resources.getDimensionPixelSize(R.dimen.status_bar_padding_end);
        this.mMinDotWidth = resources.getDimensionPixelSize(R.dimen.ongoing_appops_dot_min_padding);
        this.mCutoutSideNudge = getResources().getDimensionPixelSize(R.dimen.display_cutout_margin_consumption);
        this.mShowPercentAvailable = getContext().getResources().getBoolean(android.R.bool.config_battery_percentage_setting_available);
        resources.getDimensionPixelSize(R.dimen.rounded_corner_content_padding);
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        loadDimens();
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mMultiUserAvatar.getLayoutParams();
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.multi_user_avatar_keyguard_size);
        marginLayoutParams.height = dimensionPixelSize;
        marginLayoutParams.width = dimensionPixelSize;
        this.mMultiUserAvatar.setLayoutParams(marginLayoutParams);
        updateSystemIconsLayoutParams();
        ViewGroup viewGroup = this.mStatusIconArea;
        viewGroup.setPaddingRelative(viewGroup.getPaddingStart(), getResources().getDimensionPixelSize(R.dimen.status_bar_padding_top), this.mStatusIconArea.getPaddingEnd(), this.mStatusIconArea.getPaddingBottom());
        StatusIconContainer statusIconContainer = this.mStatusIconContainer;
        statusIconContainer.setPaddingRelative(statusIconContainer.getPaddingStart(), this.mStatusIconContainer.getPaddingTop(), getResources().getDimensionPixelSize(R.dimen.signal_cluster_battery_padding), this.mStatusIconContainer.getPaddingBottom());
        this.mSystemIcons.setPaddingRelative(getResources().getDimensionPixelSize(R.dimen.status_bar_icons_padding_start), getResources().getDimensionPixelSize(R.dimen.status_bar_icons_padding_top), getResources().getDimensionPixelSize(R.dimen.status_bar_icons_padding_end), getResources().getDimensionPixelSize(R.dimen.status_bar_icons_padding_bottom));
        this.mCarrierLabel.setTextSize(0, getResources().getDimensionPixelSize(android.R.dimen.timepicker_center_dot_radius));
        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) this.mCarrierLabel.getLayoutParams();
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.keyguard_carrier_text_margin);
        int i = this.mPadding.left;
        marginLayoutParams2.setMarginStart(i < dimensionPixelSize2 ? dimensionPixelSize2 - i : 0);
        this.mCarrierLabel.setLayoutParams(marginLayoutParams2);
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = Utils.getStatusBarHeaderHeightKeyguard(((RelativeLayout) this).mContext);
        setLayoutParams(layoutParams);
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.mSystemIconsContainer = findViewById(R.id.system_icons_container);
        this.mSystemIcons = findViewById(R.id.system_icons);
        this.mMultiUserAvatar = (ImageView) findViewById(R.id.multi_user_avatar);
        this.mCarrierLabel = (TextView) findViewById(R.id.keyguard_carrier_text);
        this.mBatteryView = (BatteryMeterView) this.mSystemIconsContainer.findViewById(R.id.battery);
        this.mCutoutSpace = findViewById(R.id.cutout_space_view);
        this.mStatusIconArea = (ViewGroup) findViewById(R.id.status_icon_area);
        this.mStatusIconContainer = (StatusIconContainer) findViewById(R.id.statusIcons);
        this.mUserSwitcherContainer = (StatusBarUserSwitcherContainer) findViewById(R.id.user_switcher_container);
        this.mIsPrivacyDotEnabled = ((RelativeLayout) this).mContext.getResources().getBoolean(R.bool.config_enablePrivacyDot);
        loadDimens();
    }

    @Override // android.widget.RelativeLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.mClipRect.set(0, this.mTopClipping, getWidth(), getHeight());
        setClipBounds(this.mClipRect);
    }

    @Override // android.widget.RelativeLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        Trace.beginSection("KeyguardStatusBarView#onMeasure");
        super.onMeasure(i, i2);
        Trace.endSection();
    }

    public final void onThemeChanged(TintedIconManager tintedIconManager) {
        BatteryMeterView batteryMeterView = this.mBatteryView;
        Context context = ((RelativeLayout) this).mContext;
        if (context == null) {
            batteryMeterView.getClass();
        } else {
            batteryMeterView.mDualToneHandler.setColorsFromContext(context);
        }
        int colorAttrDefaultColor = com.android.settingslib.Utils.getColorAttrDefaultColor(R.attr.wallpaperTextColor, 0, ((RelativeLayout) this).mContext);
        double luminance = Color.luminance(colorAttrDefaultColor);
        int colorStateListDefaultColor = com.android.settingslib.Utils.getColorStateListDefaultColor(luminance < 0.5d ? R.color.dark_mode_icon_color_single_tone : R.color.light_mode_icon_color_single_tone, ((RelativeLayout) this).mContext);
        int i = luminance < 0.5d ? -1 : DarkIconDispatcher.DEFAULT_INVERSE_ICON_TINT;
        float f = colorAttrDefaultColor == -1 ? 0.0f : 1.0f;
        this.mCarrierLabel.setTextColor(colorStateListDefaultColor);
        TextView textView = (TextView) this.mUserSwitcherContainer.findViewById(R.id.current_user_name);
        if (textView != null) {
            textView.setTextColor(com.android.settingslib.Utils.getColorStateListDefaultColor(R.color.light_mode_icon_color_single_tone, ((RelativeLayout) this).mContext));
        }
        if (tintedIconManager != null) {
            tintedIconManager.setTint(colorStateListDefaultColor, i);
        }
        StateFlowImpl stateFlowImpl = this.mDarkChange;
        SysuiDarkIconDispatcher$DarkChange sysuiDarkIconDispatcher$DarkChange = new SysuiDarkIconDispatcher$DarkChange(colorStateListDefaultColor, this.mEmptyTintRect);
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, sysuiDarkIconDispatcher$DarkChange);
        ArrayList arrayList = this.mEmptyTintRect;
        KeyEvent.Callback findViewById = findViewById(R.id.battery);
        if (findViewById instanceof DarkIconDispatcher.DarkReceiver) {
            ((DarkIconDispatcher.DarkReceiver) findViewById).onDarkChanged(arrayList, f, colorStateListDefaultColor);
        }
        ArrayList arrayList2 = this.mEmptyTintRect;
        KeyEvent.Callback findViewById2 = findViewById(R.id.clock);
        if (findViewById2 instanceof DarkIconDispatcher.DarkReceiver) {
            ((DarkIconDispatcher.DarkReceiver) findViewById2).onDarkChanged(arrayList2, f, colorStateListDefaultColor);
        }
    }

    @Override // android.view.View
    public final void setVisibility(int i) {
        super.setVisibility(i);
        if (i == 0) {
            updateVisibilities();
            updateSystemIconsLayoutParams();
        } else {
            this.mSystemIconsContainer.animate().cancel();
            this.mSystemIconsContainer.setTranslationX(0.0f);
            this.mMultiUserAvatar.animate().cancel();
            this.mMultiUserAvatar.setAlpha(1.0f);
        }
    }

    public final boolean updateLayoutParamsNoCutout() {
        if (this.mLayoutState == 2) {
            return false;
        }
        this.mLayoutState = 2;
        View view = this.mCutoutSpace;
        if (view != null) {
            view.setVisibility(8);
        }
        ((RelativeLayout.LayoutParams) this.mCarrierLabel.getLayoutParams()).addRule(16, R.id.status_icon_area);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mStatusIconArea.getLayoutParams();
        layoutParams.removeRule(1);
        layoutParams.width = -2;
        layoutParams.setMarginStart(getResources().getDimensionPixelSize(R.dimen.system_icons_super_container_margin_start));
        return true;
    }

    public final void updateSystemIconsLayoutParams() {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mSystemIconsContainer.getLayoutParams();
        int i = this.mStatusBarPaddingEnd;
        if (this.mKeyguardUserSwitcherEnabled) {
            i = this.mSystemIconsSwitcherHiddenExpandedMargin;
        }
        if (i != layoutParams.getMarginEnd()) {
            layoutParams.setMarginEnd(i);
            this.mSystemIconsContainer.setLayoutParams(layoutParams);
        }
    }

    public final void updateVisibilities() {
        if (!this.mKeyguardUserAvatarEnabled) {
            ViewParent parent = this.mMultiUserAvatar.getParent();
            ViewGroup viewGroup = this.mStatusIconArea;
            if (parent == viewGroup) {
                viewGroup.removeView(this.mMultiUserAvatar);
                return;
            } else {
                if (this.mMultiUserAvatar.getParent() != null) {
                    getOverlay().remove(this.mMultiUserAvatar);
                    return;
                }
                return;
            }
        }
        int i = 0;
        if (this.mMultiUserAvatar.getParent() == this.mStatusIconArea || this.mKeyguardUserSwitcherEnabled) {
            ViewParent parent2 = this.mMultiUserAvatar.getParent();
            ViewGroup viewGroup2 = this.mStatusIconArea;
            if (parent2 == viewGroup2 && this.mKeyguardUserSwitcherEnabled) {
                viewGroup2.removeView(this.mMultiUserAvatar);
            }
        } else {
            if (this.mMultiUserAvatar.getParent() != null) {
                getOverlay().remove(this.mMultiUserAvatar);
            }
            this.mStatusIconArea.addView(this.mMultiUserAvatar, 0);
        }
        if (!this.mKeyguardUserSwitcherEnabled) {
            if (this.mIsUserSwitcherEnabled) {
                this.mMultiUserAvatar.setVisibility(0);
            } else {
                this.mMultiUserAvatar.setVisibility(8);
            }
        }
        BatteryMeterView batteryMeterView = this.mBatteryView;
        if (this.mBatteryCharging && this.mShowPercentAvailable) {
            i = 1;
        }
        batteryMeterView.setPercentShowMode(i);
    }

    public final WindowInsets updateWindowInsets(WindowInsets windowInsets, StatusBarContentInsetsProvider statusBarContentInsetsProvider) {
        boolean z = false;
        this.mLayoutState = 0;
        this.mDisplayCutout = getRootWindowInsets().getDisplayCutout();
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = Utils.getStatusBarHeaderHeightKeyguard(((RelativeLayout) this).mContext);
        setLayoutParams(layoutParams);
        DisplayCutout displayCutout = this.mDisplayCutout;
        int i = displayCutout == null ? 0 : displayCutout.getWaterfallInsets().top;
        this.mPadding = statusBarContentInsetsProvider.getStatusBarContentInsetsForCurrentRotation();
        setPadding((isLayoutRtl() && this.mIsPrivacyDotEnabled) ? Math.max(this.mMinDotWidth, this.mPadding.left) : this.mPadding.left, i + this.mPadding.top, (isLayoutRtl() || !this.mIsPrivacyDotEnabled) ? this.mPadding.right : Math.max(this.mMinDotWidth, this.mPadding.right), 0);
        if (this.mDisplayCutout == null || statusBarContentInsetsProvider.currentRotationHasCornerCutout()) {
            z = updateLayoutParamsNoCutout();
        } else if (this.mLayoutState != 1) {
            this.mLayoutState = 1;
            if (this.mCutoutSpace == null) {
                updateLayoutParamsNoCutout();
            }
            Rect rect = new Rect();
            ScreenDecorations.DisplayCutoutView.boundsFromDirection(48, rect, this.mDisplayCutout);
            this.mCutoutSpace.setVisibility(0);
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.mCutoutSpace.getLayoutParams();
            int i2 = rect.left;
            int i3 = this.mCutoutSideNudge;
            rect.left = i2 + i3;
            rect.right -= i3;
            layoutParams2.width = rect.width();
            layoutParams2.height = rect.height();
            layoutParams2.addRule(13);
            ((RelativeLayout.LayoutParams) this.mCarrierLabel.getLayoutParams()).addRule(16, R.id.cutout_space_view);
            RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) this.mStatusIconArea.getLayoutParams();
            layoutParams3.addRule(1, R.id.cutout_space_view);
            layoutParams3.width = -1;
            layoutParams3.setMarginStart(0);
            z = true;
        }
        if (z) {
            requestLayout();
        }
        return super.onApplyWindowInsets(windowInsets);
    }
}
