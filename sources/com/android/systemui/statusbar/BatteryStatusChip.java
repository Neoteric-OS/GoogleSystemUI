package com.android.systemui.statusbar;

import android.content.Context;
import android.content.res.Configuration;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.android.systemui.battery.BatteryMeterView;
import com.android.systemui.statusbar.events.BackgroundAnimatableView;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BatteryStatusChip extends FrameLayout implements BackgroundAnimatableView {
    public final BatteryMeterView batteryMeterView;
    public final LinearLayout roundedContainer;

    public BatteryStatusChip(Context context) {
        super(context, null);
        FrameLayout.inflate(context, R.layout.battery_status_chip, this);
        LinearLayout linearLayout = (LinearLayout) requireViewById(R.id.rounded_container);
        this.roundedContainer = linearLayout;
        BatteryMeterView batteryMeterView = (BatteryMeterView) requireViewById(R.id.battery_meter_view);
        this.batteryMeterView = batteryMeterView;
        batteryMeterView.mIsStaticColor = true;
        int color = context.getResources().getColor(android.R.color.black, context.getTheme());
        batteryMeterView.updateColors(color, color, color);
        linearLayout.setBackground(((FrameLayout) this).mContext.getDrawable(R.drawable.statusbar_chip_bg));
    }

    @Override // com.android.systemui.statusbar.events.BackgroundAnimatableView
    public final View getContentView() {
        return this.batteryMeterView;
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.roundedContainer.setBackground(((FrameLayout) this).mContext.getDrawable(R.drawable.statusbar_chip_bg));
    }

    @Override // com.android.systemui.statusbar.events.BackgroundAnimatableView
    public final void setBoundsForAnimation(int i, int i2, int i3, int i4) {
        this.roundedContainer.setLeftTopRightBottom(i - getLeft(), i2 - getTop(), i3 - getLeft(), i4 - getTop());
    }
}
