package com.android.settingslib.mobile;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.Log;
import com.android.wm.shell.R;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Pair;
import kotlin.collections.MapsKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MobileIconCarrierIdOverridesImpl {
    public static final Companion Companion = new Companion();
    public static final Map MAPPING = MapsKt__MapsJVMKt.mapOf(new Pair(2032, Integer.valueOf(R.array.carrierId_2032_iconOverrides)));

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
        public final Map parseNetworkIconOverrideTypedArray(TypedArray typedArray) {
            if (typedArray.length() % 2 != 0) {
                Log.w("MobileIconOverrides", "override must contain an even number of (key, value) entries. skipping");
                return MapsKt.emptyMap();
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            IntProgression step = RangesKt.step(RangesKt.until(0, typedArray.length()));
            int i = step.first;
            int i2 = step.last;
            int i3 = step.step;
            if ((i3 > 0 && i <= i2) || (i3 < 0 && i2 <= i)) {
                while (true) {
                    String string = typedArray.getString(i);
                    int resourceId = typedArray.getResourceId(i + 1, 0);
                    if (string == null || resourceId == 0) {
                        Log.w("MobileIconOverrides", "Invalid override found. Skipping");
                    } else {
                        linkedHashMap.put(string, Integer.valueOf(resourceId));
                    }
                    if (i == i2) {
                        break;
                    }
                    i += i3;
                }
            }
            return linkedHashMap;
        }
    }

    public static final Map parseNetworkIconOverrideTypedArray(TypedArray typedArray) {
        return Companion.parseNetworkIconOverrideTypedArray(typedArray);
    }

    public final int getOverrideFor(int i, Resources resources, String str) {
        Integer num = (Integer) MAPPING.get(Integer.valueOf(i));
        if (num == null) {
            return 0;
        }
        TypedArray obtainTypedArray = resources.obtainTypedArray(num.intValue());
        Map parseNetworkIconOverrideTypedArray = Companion.parseNetworkIconOverrideTypedArray(obtainTypedArray);
        obtainTypedArray.recycle();
        Integer num2 = (Integer) parseNetworkIconOverrideTypedArray.get(str);
        if (num2 != null) {
            return num2.intValue();
        }
        return 0;
    }
}
