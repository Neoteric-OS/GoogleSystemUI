package com.android.systemui.shade.carrier;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.settingslib.Utils;
import com.android.settingslib.graph.SignalDrawable;
import com.android.systemui.statusbar.pipeline.mobile.ui.view.ModernShadeCarrierGroupMobileView;
import com.android.wm.shell.R;
import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class ShadeCarrier extends LinearLayout {
    public TextView mCarrierText;
    public boolean mIsSingleCarrier;
    public CellSignalState mLastSignalState;
    public View mMobileGroup;
    public ImageView mMobileRoaming;
    public ImageView mMobileSignal;
    public boolean mMobileSignalInitialized;
    public ModernShadeCarrierGroupMobileView mModernMobileView;
    public View mSpacer;

    public ShadeCarrier(Context context) {
        super(context);
        this.mMobileSignalInitialized = false;
    }

    public View getRSSIView() {
        return this.mMobileGroup;
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mCarrierText.setMaxEms(getResources().getBoolean(R.bool.config_use_large_screen_shade_header) ? Integer.MAX_VALUE : getResources().getInteger(R.integer.shade_carrier_max_em));
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.mMobileGroup = findViewById(R.id.mobile_combo);
        this.mMobileRoaming = (ImageView) findViewById(R.id.mobile_roaming);
        this.mMobileSignal = (ImageView) findViewById(R.id.mobile_signal);
        this.mCarrierText = (TextView) findViewById(R.id.shade_carrier_text);
        this.mSpacer = findViewById(R.id.spacer);
        this.mCarrierText.setMaxEms(getResources().getBoolean(R.bool.config_use_large_screen_shade_header) ? Integer.MAX_VALUE : getResources().getInteger(R.integer.shade_carrier_max_em));
    }

    public final void updateState(CellSignalState cellSignalState, boolean z) {
        if (Objects.equals(cellSignalState, this.mLastSignalState) && z == this.mIsSingleCarrier) {
            return;
        }
        this.mLastSignalState = cellSignalState;
        this.mIsSingleCarrier = z;
        boolean z2 = cellSignalState.visible && !z;
        this.mMobileGroup.setVisibility(z2 ? 0 : 8);
        this.mSpacer.setVisibility(z ? 0 : 8);
        if (z2) {
            ImageView imageView = this.mMobileRoaming;
            boolean z3 = cellSignalState.roaming;
            imageView.setVisibility(z3 ? 0 : 8);
            ColorStateList colorAttr = Utils.getColorAttr(android.R.attr.textColorPrimary, ((LinearLayout) this).mContext);
            this.mMobileRoaming.setImageTintList(colorAttr);
            this.mMobileSignal.setImageTintList(colorAttr);
            if (!this.mMobileSignalInitialized) {
                this.mMobileSignalInitialized = true;
                this.mMobileSignal.setImageDrawable(new SignalDrawable(((LinearLayout) this).mContext));
            }
            this.mMobileSignal.setImageLevel(cellSignalState.mobileSignalIconId);
            StringBuilder sb = new StringBuilder();
            String str = cellSignalState.contentDescription;
            if (str != null) {
                sb.append(str);
                sb.append(", ");
            }
            if (z3) {
                sb.append(((LinearLayout) this).mContext.getString(R.string.data_connection_roaming));
                sb.append(", ");
            }
            String string = ((LinearLayout) this).mContext.getString(R.string.data_connection_no_internet);
            String str2 = cellSignalState.typeContentDescription;
            if (TextUtils.equals(str2, string) || TextUtils.equals(str2, ((LinearLayout) this).mContext.getString(R.string.cell_data_off_content_description)) || TextUtils.equals(str2, ((LinearLayout) this).mContext.getString(R.string.not_default_data_content_description))) {
                sb.append(str2);
            }
            this.mMobileSignal.setContentDescription(sb);
        }
    }

    public ShadeCarrier(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mMobileSignalInitialized = false;
    }

    public ShadeCarrier(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mMobileSignalInitialized = false;
    }

    public ShadeCarrier(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mMobileSignalInitialized = false;
    }
}
