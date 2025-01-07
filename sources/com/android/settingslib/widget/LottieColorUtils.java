package com.android.settingslib.widget;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.SimpleLottieValueCallback;
import com.android.wm.shell.R;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class LottieColorUtils {
    public static final Map DARK_TO_LIGHT_THEME_COLOR_MAP;

    static {
        HashMap hashMap = new HashMap();
        hashMap.put(".grey200", Integer.valueOf(R.color.settingslib_color_grey800));
        hashMap.put(".grey600", Integer.valueOf(R.color.settingslib_color_grey400));
        hashMap.put(".grey800", Integer.valueOf(R.color.settingslib_color_grey300));
        hashMap.put(".grey900", Integer.valueOf(R.color.settingslib_color_grey50));
        Integer valueOf = Integer.valueOf(R.color.settingslib_color_red500);
        hashMap.put(".red100", valueOf);
        hashMap.put(".red200", valueOf);
        hashMap.put(".red400", Integer.valueOf(R.color.settingslib_color_red600));
        hashMap.put(".black", Integer.valueOf(android.R.color.white));
        hashMap.put(".blue200", Integer.valueOf(R.color.settingslib_color_blue700));
        hashMap.put(".blue400", Integer.valueOf(R.color.settingslib_color_blue600));
        Integer valueOf2 = Integer.valueOf(R.color.settingslib_color_green500);
        hashMap.put(".green100", valueOf2);
        hashMap.put(".green200", valueOf2);
        hashMap.put(".green400", Integer.valueOf(R.color.settingslib_color_green600));
        hashMap.put(".cream", Integer.valueOf(R.color.settingslib_color_charcoal));
        DARK_TO_LIGHT_THEME_COLOR_MAP = Collections.unmodifiableMap(hashMap);
    }

    public static void applyDynamicColors(Context context, LottieAnimationView lottieAnimationView) {
        if ((context.getResources().getConfiguration().uiMode & 48) == 32) {
            return;
        }
        for (String str : DARK_TO_LIGHT_THEME_COLOR_MAP.keySet()) {
            final int color = context.getColor(((Integer) DARK_TO_LIGHT_THEME_COLOR_MAP.get(str)).intValue());
            lottieAnimationView.addValueCallback(new KeyPath("**", str, "**"), LottieProperty.COLOR_FILTER, new SimpleLottieValueCallback() { // from class: com.android.settingslib.widget.LottieColorUtils$$ExternalSyntheticLambda0
                @Override // com.airbnb.lottie.value.SimpleLottieValueCallback
                public final Object getValue() {
                    return new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP);
                }
            });
        }
    }
}
