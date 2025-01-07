package com.android.systemui.battery;

import android.animation.Animator;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.app.animation.Interpolators;
import com.android.settingslib.graph.ThemedBatteryDrawable;
import com.android.settingslib.graph.ThemedBatteryDrawable$sam$java_lang_Runnable$0;
import com.android.systemui.DejankUtils;
import com.android.systemui.DualToneHandler;
import com.android.systemui.battery.unified.BatteryColors;
import com.android.systemui.battery.unified.BatteryDrawableState;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.systemui.res.R$styleable;
import com.android.systemui.statusbar.policy.BatteryControllerImpl;
import com.android.systemui.statusbar.policy.BatteryControllerImpl$$ExternalSyntheticLambda0;
import com.android.wm.shell.R;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.function.Supplier;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class BatteryMeterView extends LinearLayout implements DarkIconDispatcher.DarkReceiver {
    public static final /* synthetic */ int $r8$clinit = 0;
    public BatteryMeterViewController$$ExternalSyntheticLambda0 mBatteryEstimateFetcher;
    public final ImageView mBatteryIconView;
    public TextView mBatteryPercentView;
    public boolean mBatteryStateUnknown;
    public final AccessorizedBatteryDrawable mDrawable;
    public final DualToneHandler mDualToneHandler;
    public String mEstimateText;
    public boolean mIsBatteryDefender;
    public boolean mIsIncompatibleCharging;
    public boolean mIsStaticColor;
    public int mLevel;
    public final int mPercentageStyleId;
    public boolean mPluggedIn;
    public boolean mPowerSaveEnabled;
    public final boolean mShowPercentAvailable;
    public int mShowPercentMode;
    public int mTextColor;
    public final BatteryColors.LightThemeColors mUnifiedBatteryColors;
    public final BatteryDrawableState mUnifiedBatteryState;
    public Drawable mUnknownStateDrawable;

    public BatteryMeterView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TextView getBatteryPercentView() {
        return this.mBatteryPercentView;
    }

    public CharSequence getBatteryPercentViewText() {
        return this.mBatteryPercentView.getText();
    }

    public BatteryDrawableState getUnifiedBatteryState() {
        return this.mUnifiedBatteryState;
    }

    @Override // android.view.View
    public final boolean hasOverlappingRendering() {
        return false;
    }

    public boolean isCharging() {
        return this.mPluggedIn && !this.mIsIncompatibleCharging;
    }

    public final void onBatteryLevelChanged(int i, boolean z) {
        isCharging();
        this.mPluggedIn = z;
        this.mLevel = i;
        boolean isCharging = isCharging();
        ThemedBatteryDrawable themedBatteryDrawable = (ThemedBatteryDrawable) this.mDrawable.getDrawable();
        themedBatteryDrawable.charging = isCharging;
        themedBatteryDrawable.unscheduleSelf(new ThemedBatteryDrawable$sam$java_lang_Runnable$0(themedBatteryDrawable.invalidateRunnable));
        themedBatteryDrawable.scheduleSelf(new ThemedBatteryDrawable$sam$java_lang_Runnable$0(themedBatteryDrawable.invalidateRunnable), 0L);
        ThemedBatteryDrawable themedBatteryDrawable2 = (ThemedBatteryDrawable) this.mDrawable.getDrawable();
        themedBatteryDrawable2.invertFillIcon = i >= 67 ? true : i <= 33 ? false : themedBatteryDrawable2.invertFillIcon;
        themedBatteryDrawable2.batteryLevel = i;
        themedBatteryDrawable2.levelColor = themedBatteryDrawable2.batteryColorForLevel(i);
        themedBatteryDrawable2.invalidateSelf();
        updatePercentText();
    }

    public final void onBatteryUnknownStateChanged(boolean z) {
        if (this.mBatteryStateUnknown == z) {
            return;
        }
        this.mBatteryStateUnknown = z;
        updateContentDescription();
        if (this.mBatteryStateUnknown) {
            ImageView imageView = this.mBatteryIconView;
            if (this.mUnknownStateDrawable == null) {
                Drawable drawable = ((LinearLayout) this).mContext.getDrawable(R.drawable.ic_battery_unknown);
                this.mUnknownStateDrawable = drawable;
                drawable.setTint(this.mTextColor);
            }
            imageView.setImageDrawable(this.mUnknownStateDrawable);
        } else {
            this.mBatteryIconView.setImageDrawable(this.mDrawable);
        }
        updateShowPercent();
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        TextView textView = this.mBatteryPercentView;
        if (textView != null) {
            removeView(textView);
            this.mBatteryPercentView = null;
        }
        updateShowPercent();
        AccessorizedBatteryDrawable accessorizedBatteryDrawable = this.mDrawable;
        accessorizedBatteryDrawable.density = accessorizedBatteryDrawable.context.getResources().getDisplayMetrics().density;
    }

    @Override // com.android.systemui.plugins.DarkIconDispatcher.DarkReceiver
    public final void onDarkChanged(ArrayList arrayList, float f, int i) {
        if (this.mIsStaticColor) {
            return;
        }
        if (!DarkIconDispatcher.isInAreas(arrayList, this)) {
            f = 0.0f;
        }
        DualToneHandler dualToneHandler = this.mDualToneHandler;
        DualToneHandler.Color color = dualToneHandler.lightColor;
        if (color == null) {
            color = null;
        }
        int i2 = color.single;
        DualToneHandler.Color color2 = dualToneHandler.darkColor;
        if (color2 == null) {
            color2 = null;
        }
        int colorForDarkIntensity = DualToneHandler.getColorForDarkIntensity(i2, f, color2.single);
        DualToneHandler dualToneHandler2 = this.mDualToneHandler;
        DualToneHandler.Color color3 = dualToneHandler2.lightColor;
        if (color3 == null) {
            color3 = null;
        }
        int i3 = color3.fill;
        DualToneHandler.Color color4 = dualToneHandler2.darkColor;
        if (color4 == null) {
            color4 = null;
        }
        int colorForDarkIntensity2 = DualToneHandler.getColorForDarkIntensity(i3, f, color4.fill);
        DualToneHandler dualToneHandler3 = this.mDualToneHandler;
        DualToneHandler.Color color5 = dualToneHandler3.lightColor;
        if (color5 == null) {
            color5 = null;
        }
        int i4 = color5.background;
        DualToneHandler.Color color6 = dualToneHandler3.darkColor;
        updateColors(colorForDarkIntensity2, DualToneHandler.getColorForDarkIntensity(i4, f, (color6 != null ? color6 : null).background), colorForDarkIntensity);
    }

    public final void scaleBatteryMeterViews() {
        Resources resources = getContext().getResources();
        TypedValue typedValue = new TypedValue();
        resources.getValue(R.dimen.status_bar_icon_scale_factor, typedValue, true);
        float f = typedValue.getFloat();
        float dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.status_bar_battery_icon_height) * f;
        float dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.status_bar_battery_icon_width) * f;
        boolean z = this.mIsBatteryDefender;
        float f2 = !z ? dimensionPixelSize : (dimensionPixelSize / 20.0f) * 23.0f;
        if (z) {
            dimensionPixelSize2 = (dimensionPixelSize2 / 12.0f) * 18.0f;
        }
        int round = z ? Math.round(f2 - dimensionPixelSize) - resources.getDimensionPixelSize(R.dimen.status_bar_battery_extra_vertical_spacing) : 0;
        int dimensionPixelSize3 = resources.getDimensionPixelSize(R.dimen.battery_margin_bottom);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(Math.round(dimensionPixelSize2), Math.round(f2));
        layoutParams.setMargins(0, round, 0, dimensionPixelSize3);
        AccessorizedBatteryDrawable accessorizedBatteryDrawable = this.mDrawable;
        accessorizedBatteryDrawable.displayShield = z;
        final Function0 function0 = accessorizedBatteryDrawable.invalidateRunnable;
        accessorizedBatteryDrawable.unscheduleSelf(new Runnable() { // from class: com.android.systemui.battery.AccessorizedBatteryDrawable$sam$java_lang_Runnable$0
            @Override // java.lang.Runnable
            public final /* synthetic */ void run() {
                Function0.this.invoke();
            }
        });
        final Function0 function02 = accessorizedBatteryDrawable.invalidateRunnable;
        accessorizedBatteryDrawable.scheduleSelf(new Runnable() { // from class: com.android.systemui.battery.AccessorizedBatteryDrawable$sam$java_lang_Runnable$0
            @Override // java.lang.Runnable
            public final /* synthetic */ void run() {
                Function0.this.invoke();
            }
        }, 0L);
        this.mBatteryIconView.setLayoutParams(layoutParams);
        this.mBatteryIconView.invalidateDrawable(this.mDrawable);
    }

    public final void setPercentShowMode(int i) {
        if (i == this.mShowPercentMode) {
            return;
        }
        this.mShowPercentMode = i;
        updateShowPercent();
        updatePercentText();
    }

    public final void setPercentTextAtCurrentLevel() {
        if (this.mBatteryPercentView != null) {
            this.mEstimateText = null;
            String format = NumberFormat.getPercentInstance().format(this.mLevel / 100.0f);
            if (!TextUtils.equals(this.mBatteryPercentView.getText(), format)) {
                this.mBatteryPercentView.setText(format);
            }
        }
        updateContentDescription();
    }

    public final void updateColors(int i, int i2, int i3) {
        AccessorizedBatteryDrawable accessorizedBatteryDrawable = this.mDrawable;
        accessorizedBatteryDrawable.shieldPaint.setColor(accessorizedBatteryDrawable.dualTone ? i : i3);
        ThemedBatteryDrawable themedBatteryDrawable = (ThemedBatteryDrawable) accessorizedBatteryDrawable.getDrawable();
        if (!themedBatteryDrawable.dualTone) {
            i = i3;
        }
        themedBatteryDrawable.fillColor = i;
        themedBatteryDrawable.fillPaint.setColor(i);
        themedBatteryDrawable.fillColorStrokePaint.setColor(themedBatteryDrawable.fillColor);
        themedBatteryDrawable.dualToneBackgroundFill.setColor(i2);
        themedBatteryDrawable.levelColor = themedBatteryDrawable.batteryColorForLevel(themedBatteryDrawable.batteryLevel);
        themedBatteryDrawable.invalidateSelf();
        this.mTextColor = i3;
        TextView textView = this.mBatteryPercentView;
        if (textView != null) {
            textView.setTextColor(i3);
        }
        Drawable drawable = this.mUnknownStateDrawable;
        if (drawable != null) {
            drawable.setTint(i3);
        }
    }

    public final void updateContentDescription() {
        String string;
        Context context = getContext();
        if (this.mBatteryStateUnknown) {
            string = context.getString(R.string.accessibility_battery_unknown);
        } else if (this.mShowPercentMode != 3 || TextUtils.isEmpty(this.mEstimateText)) {
            string = this.mIsBatteryDefender ? context.getString(R.string.accessibility_battery_level_charging_paused, Integer.valueOf(this.mLevel)) : isCharging() ? context.getString(R.string.accessibility_battery_level_charging, Integer.valueOf(this.mLevel)) : context.getString(R.string.accessibility_battery_level, Integer.valueOf(this.mLevel));
        } else {
            string = context.getString(this.mIsBatteryDefender ? R.string.accessibility_battery_level_charging_paused_with_estimate : R.string.accessibility_battery_level_with_estimate, Integer.valueOf(this.mLevel), this.mEstimateText);
        }
        setContentDescription(string);
    }

    public final void updatePercentText() {
        if (this.mBatteryStateUnknown) {
            return;
        }
        if (this.mBatteryEstimateFetcher == null) {
            setPercentTextAtCurrentLevel();
            return;
        }
        if (this.mBatteryPercentView == null) {
            updateContentDescription();
            return;
        }
        if (this.mShowPercentMode != 3 || isCharging()) {
            setPercentTextAtCurrentLevel();
            return;
        }
        BatteryMeterViewController$$ExternalSyntheticLambda0 batteryMeterViewController$$ExternalSyntheticLambda0 = this.mBatteryEstimateFetcher;
        BatteryMeterView$$ExternalSyntheticLambda0 batteryMeterView$$ExternalSyntheticLambda0 = new BatteryMeterView$$ExternalSyntheticLambda0(this);
        BatteryControllerImpl batteryControllerImpl = (BatteryControllerImpl) batteryMeterViewController$$ExternalSyntheticLambda0.f$0;
        synchronized (batteryControllerImpl.mFetchCallbacks) {
            batteryControllerImpl.mFetchCallbacks.add(batteryMeterView$$ExternalSyntheticLambda0);
        }
        if (batteryControllerImpl.mFetchingEstimate) {
            return;
        }
        batteryControllerImpl.mFetchingEstimate = true;
        batteryControllerImpl.mBgHandler.post(new BatteryControllerImpl$$ExternalSyntheticLambda0(batteryControllerImpl, 0));
    }

    public final void updateShowPercent() {
        int i;
        boolean z = this.mBatteryPercentView != null;
        boolean z2 = ((Integer) DejankUtils.whitelistIpcs(new Supplier() { // from class: com.android.systemui.battery.BatteryMeterView$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                BatteryMeterView batteryMeterView = BatteryMeterView.this;
                int i2 = BatteryMeterView.$r8$clinit;
                return Integer.valueOf(Settings.System.getIntForUser(batteryMeterView.getContext().getContentResolver(), "status_bar_show_battery_percent", batteryMeterView.getContext().getResources().getBoolean(android.R.bool.config_defaultBinderHeavyHitterAutoSamplerEnabled) ? 1 : 0, -2));
            }
        })).intValue() != 0;
        if (!((this.mShowPercentAvailable && z2 && this.mShowPercentMode != 2) || (i = this.mShowPercentMode) == 1 || i == 3) || this.mBatteryStateUnknown) {
            if (z) {
                removeView(this.mBatteryPercentView);
                this.mBatteryPercentView = null;
                return;
            }
            return;
        }
        if (z) {
            return;
        }
        TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.battery_percentage_view, (ViewGroup) null);
        this.mBatteryPercentView = textView;
        int i2 = this.mPercentageStyleId;
        if (i2 != 0) {
            textView.setTextAppearance(i2);
        }
        float fontMetricsInt = this.mBatteryPercentView.getPaint().getFontMetricsInt(null);
        this.mBatteryPercentView.setLineHeight(0, fontMetricsInt);
        int i3 = this.mTextColor;
        if (i3 != 0) {
            this.mBatteryPercentView.setTextColor(i3);
        }
        addView(this.mBatteryPercentView, new LinearLayout.LayoutParams(-2, (int) Math.ceil(fontMetricsInt)));
        updatePercentText();
    }

    public BatteryMeterView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mShowPercentMode = 0;
        this.mEstimateText = null;
        this.mIsStaticColor = false;
        BatteryColors.LightThemeColors lightThemeColors = BatteryColors.LIGHT_THEME_COLORS;
        this.mUnifiedBatteryState = BatteryDrawableState.DefaultInitialState;
        setOrientation(0);
        setGravity(8388627);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.BatteryMeterView, i, 0);
        int color = obtainStyledAttributes.getColor(0, context.getColor(R.color.meter_background_color));
        this.mPercentageStyleId = obtainStyledAttributes.getResourceId(1, 0);
        AccessorizedBatteryDrawable accessorizedBatteryDrawable = new AccessorizedBatteryDrawable(color, context);
        this.mDrawable = accessorizedBatteryDrawable;
        obtainStyledAttributes.recycle();
        this.mShowPercentAvailable = context.getResources().getBoolean(android.R.bool.config_battery_percentage_setting_available);
        LayoutTransition layoutTransition = new LayoutTransition();
        layoutTransition.setDuration(200L);
        layoutTransition.setAnimator(2, ObjectAnimator.ofFloat((Object) null, "alpha", 0.0f, 1.0f));
        layoutTransition.setInterpolator(2, Interpolators.ALPHA_IN);
        Animator ofFloat = ObjectAnimator.ofFloat((Object) null, "alpha", 1.0f, 0.0f);
        layoutTransition.setInterpolator(3, Interpolators.ALPHA_OUT);
        layoutTransition.setAnimator(3, ofFloat);
        layoutTransition.setAnimator(0, null);
        layoutTransition.setAnimator(1, null);
        layoutTransition.setAnimator(4, null);
        setLayoutTransition(layoutTransition);
        ImageView imageView = new ImageView(context);
        this.mBatteryIconView = imageView;
        imageView.setImageDrawable(accessorizedBatteryDrawable);
        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(getResources().getDimensionPixelSize(R.dimen.status_bar_battery_icon_width), getResources().getDimensionPixelSize(R.dimen.status_bar_battery_icon_height));
        marginLayoutParams.setMargins(0, 0, 0, getResources().getDimensionPixelOffset(R.dimen.battery_margin_bottom));
        addView(imageView, marginLayoutParams);
        updateShowPercent();
        DualToneHandler dualToneHandler = new DualToneHandler();
        dualToneHandler.setColorsFromContext(context);
        this.mDualToneHandler = dualToneHandler;
        onDarkChanged(new ArrayList(), 0.0f, -1);
        setClipChildren(false);
        setClipToPadding(false);
    }
}
