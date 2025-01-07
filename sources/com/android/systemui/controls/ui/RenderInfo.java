package com.android.systemui.controls.ui;

import android.content.ComponentName;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.ArrayMap;
import android.util.SparseArray;
import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import com.android.wm.shell.R;
import kotlin.Pair;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class RenderInfo {
    public static final Companion Companion = null;
    public final int enabledBackground;
    public final int foreground;
    public final Drawable icon;
    public static final SparseArray iconMap = new SparseArray();
    public static final ArrayMap appIconMap = new ArrayMap();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
        public static RenderInfo lookup(Context context, ComponentName componentName, int i, int i2) {
            Drawable drawable;
            if (i2 > 0) {
                i = (i * 1000) + i2;
            }
            Pair pair = (Pair) MapsKt.getValue(Integer.valueOf(i), RenderInfoKt.deviceColorMap);
            int intValue = ((Number) pair.component1()).intValue();
            int intValue2 = ((Number) pair.component2()).intValue();
            int intValue3 = ((Number) MapsKt.getValue(Integer.valueOf(i), RenderInfoKt.deviceIconMap)).intValue();
            if (intValue3 == -1) {
                ArrayMap arrayMap = RenderInfo.appIconMap;
                drawable = (Drawable) arrayMap.get(componentName);
                if (drawable == null) {
                    drawable = context.getResources().getDrawable(R.drawable.ic_device_unknown_on, null);
                    arrayMap.put(componentName, drawable);
                }
            } else {
                SparseArray sparseArray = RenderInfo.iconMap;
                drawable = (Drawable) sparseArray.get(intValue3);
                if (drawable == null) {
                    drawable = context.getResources().getDrawable(intValue3, null);
                    sparseArray.put(intValue3, drawable);
                }
            }
            Drawable.ConstantState constantState = drawable != null ? drawable.getConstantState() : null;
            if (constantState != null) {
                return new RenderInfo(constantState.newDrawable(context.getResources()), intValue, intValue2);
            }
            throw new IllegalStateException("Required value was null.");
        }
    }

    public RenderInfo(Drawable drawable, int i, int i2) {
        this.icon = drawable;
        this.foreground = i;
        this.enabledBackground = i2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RenderInfo)) {
            return false;
        }
        RenderInfo renderInfo = (RenderInfo) obj;
        return Intrinsics.areEqual(this.icon, renderInfo.icon) && this.foreground == renderInfo.foreground && this.enabledBackground == renderInfo.enabledBackground;
    }

    public final int hashCode() {
        return Integer.hashCode(this.enabledBackground) + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.foreground, this.icon.hashCode() * 31, 31);
    }

    public final String toString() {
        Drawable drawable = this.icon;
        StringBuilder sb = new StringBuilder("RenderInfo(icon=");
        sb.append(drawable);
        sb.append(", foreground=");
        sb.append(this.foreground);
        sb.append(", enabledBackground=");
        return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.enabledBackground, ")");
    }
}
