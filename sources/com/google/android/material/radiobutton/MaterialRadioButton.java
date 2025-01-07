package com.google.android.material.radiobutton;

import android.R;
import android.content.res.ColorStateList;
import androidx.appcompat.widget.AppCompatRadioButton;
import com.google.android.material.color.MaterialColors;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MaterialRadioButton extends AppCompatRadioButton {
    public static final int[][] ENABLED_CHECKED_STATES = {new int[]{R.attr.state_enabled, R.attr.state_checked}, new int[]{R.attr.state_enabled, -16842912}, new int[]{-16842910, R.attr.state_checked}, new int[]{-16842910, -16842912}};
    public ColorStateList materialThemeColorsTintList;
    public boolean useMaterialThemeColors;

    @Override // android.widget.TextView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.useMaterialThemeColors && getButtonTintList() == null) {
            this.useMaterialThemeColors = true;
            if (this.materialThemeColorsTintList == null) {
                int color = MaterialColors.getColor(this, com.android.wm.shell.R.attr.colorControlActivated);
                int color2 = MaterialColors.getColor(this, com.android.wm.shell.R.attr.colorOnSurface);
                int color3 = MaterialColors.getColor(this, com.android.wm.shell.R.attr.colorSurface);
                this.materialThemeColorsTintList = new ColorStateList(ENABLED_CHECKED_STATES, new int[]{MaterialColors.layer(color3, 1.0f, color), MaterialColors.layer(color3, 0.54f, color2), MaterialColors.layer(color3, 0.38f, color2), MaterialColors.layer(color3, 0.38f, color2)});
            }
            setButtonTintList(this.materialThemeColorsTintList);
        }
    }
}
