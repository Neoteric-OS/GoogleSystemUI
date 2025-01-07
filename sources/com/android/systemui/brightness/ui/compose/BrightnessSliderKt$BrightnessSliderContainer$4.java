package com.android.systemui.brightness.ui.compose;

import com.android.systemui.brightness.ui.viewmodel.BrightnessSliderViewModel;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final /* synthetic */ class BrightnessSliderKt$BrightnessSliderContainer$4 extends FunctionReferenceImpl implements Function1 {
    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        int intValue = ((Number) obj).intValue();
        int i = ((BrightnessSliderViewModel) this.receiver).maxBrightness;
        return ((RangesKt.coerceIn(intValue, 0, i) * 100) / i) + "%";
    }
}
