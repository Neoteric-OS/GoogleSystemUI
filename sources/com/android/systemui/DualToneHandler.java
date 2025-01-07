package com.android.systemui;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.view.ContextThemeWrapper;
import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import com.android.settingslib.Utils;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DualToneHandler {
    public Color darkColor;
    public Color lightColor;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Color {
        public final int background;
        public final int fill;
        public final int single;

        public Color(int i, int i2, int i3) {
            this.single = i;
            this.background = i2;
            this.fill = i3;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Color)) {
                return false;
            }
            Color color = (Color) obj;
            return this.single == color.single && this.background == color.background && this.fill == color.fill;
        }

        public final int hashCode() {
            return Integer.hashCode(this.fill) + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.background, Integer.hashCode(this.single) * 31, 31);
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("Color(single=");
            sb.append(this.single);
            sb.append(", background=");
            sb.append(this.background);
            sb.append(", fill=");
            return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.fill, ")");
        }
    }

    public static int getColorForDarkIntensity(int i, float f, int i2) {
        return ((Integer) ArgbEvaluator.getInstance().evaluate(f, Integer.valueOf(i), Integer.valueOf(i2))).intValue();
    }

    public final void setColorsFromContext(Context context) {
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, Utils.getThemeAttr(R.attr.darkIconTheme, context));
        ContextThemeWrapper contextThemeWrapper2 = new ContextThemeWrapper(context, Utils.getThemeAttr(R.attr.lightIconTheme, context));
        this.darkColor = new Color(Utils.getColorAttrDefaultColor(R.attr.singleToneColor, 0, contextThemeWrapper), Utils.getColorAttrDefaultColor(R.attr.iconBackgroundColor, 0, contextThemeWrapper), Utils.getColorAttrDefaultColor(R.attr.fillColor, 0, contextThemeWrapper));
        this.lightColor = new Color(Utils.getColorAttrDefaultColor(R.attr.singleToneColor, 0, contextThemeWrapper2), Utils.getColorAttrDefaultColor(R.attr.iconBackgroundColor, 0, contextThemeWrapper2), Utils.getColorAttrDefaultColor(R.attr.fillColor, 0, contextThemeWrapper2));
    }
}
