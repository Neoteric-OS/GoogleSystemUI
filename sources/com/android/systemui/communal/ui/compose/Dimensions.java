package com.android.systemui.communal.ui.compose;

import android.content.Context;
import android.content.res.Configuration;
import android.view.IWindowManager;
import androidx.compose.foundation.layout.PaddingValuesImpl;
import androidx.window.layout.WindowMetrics;
import androidx.window.layout.WindowMetricsCalculator;
import com.android.systemui.communal.util.DensityUtils;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Dimensions {
    public static final PaddingValuesImpl ButtonPadding;
    public static final float IconSize;
    public static final float SlideOffsetY;
    public final Configuration config;
    public final Context context;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Companion {
        /* renamed from: getItemSpacing-D9Ej5fM, reason: not valid java name */
        public static float m797getItemSpacingD9Ej5fM() {
            IWindowManager iWindowManager = DensityUtils.windowManagerService;
            return DensityUtils.Companion.m799getAdjustedDpu2uoSUM(50);
        }
    }

    static {
        IWindowManager iWindowManager = DensityUtils.windowManagerService;
        float m799getAdjustedDpu2uoSUM = DensityUtils.Companion.m799getAdjustedDpu2uoSUM(16);
        float m799getAdjustedDpu2uoSUM2 = DensityUtils.Companion.m799getAdjustedDpu2uoSUM(24);
        ButtonPadding = new PaddingValuesImpl(m799getAdjustedDpu2uoSUM2, m799getAdjustedDpu2uoSUM, m799getAdjustedDpu2uoSUM2, m799getAdjustedDpu2uoSUM);
        IconSize = DensityUtils.Companion.m799getAdjustedDpu2uoSUM(40);
        SlideOffsetY = DensityUtils.Companion.m799getAdjustedDpu2uoSUM(30);
    }

    public Dimensions(Context context, Configuration configuration) {
        this.context = context;
        this.config = configuration;
    }

    /* renamed from: getGridTopSpacing-D9Ej5fM, reason: not valid java name */
    public final float m796getGridTopSpacingD9Ej5fM() {
        if (this.config.orientation == 2) {
            return 114;
        }
        WindowMetricsCalculator.Companion.getClass();
        WindowMetrics computeCurrentWindowMetrics = WindowMetricsCalculator.Companion.getOrCreate().computeCurrentWindowMetrics(this.context);
        float height = computeCurrentWindowMetrics._bounds.toRect().height() / this.context.getResources().getDisplayMetrics().density;
        IWindowManager iWindowManager = DensityUtils.windowManagerService;
        return (height - DensityUtils.Companion.m799getAdjustedDpu2uoSUM(530)) / 2;
    }
}
