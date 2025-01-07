package com.android.systemui.brightness.shared.model;

import com.android.systemui.log.table.TableLogBuffer;
import com.android.systemui.util.kotlin.FlowKt;
import java.util.Arrays;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.SafeFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class LinearBrightnessKt {
    public static final String formatBrightness(float f) {
        return String.format("%.3f", Arrays.copyOf(new Object[]{Float.valueOf(f)}, 1));
    }

    /* renamed from: logDiffForTable-CVGC-8U, reason: not valid java name */
    public static final SafeFlow m792logDiffForTableCVGC8U(Flow flow, final TableLogBuffer tableLogBuffer, final String str) {
        return FlowKt.pairwiseBy(flow, new LinearBrightnessKt$logDiffForTable$1(1, new Function0() { // from class: com.android.systemui.brightness.shared.model.LinearBrightnessKt$logDiffForTable$initialValueFun$1
            final /* synthetic */ String $columnPrefix = "linear";
            final /* synthetic */ LinearBrightness $initialValue = null;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                TableLogBuffer tableLogBuffer2 = TableLogBuffer.this;
                String str2 = this.$columnPrefix;
                String str3 = str;
                LinearBrightness linearBrightness = this.$initialValue;
                tableLogBuffer2.logChange(str2, str3, linearBrightness != null ? LinearBrightnessKt.formatBrightness(linearBrightness.floatValue) : null, true);
                return this.$initialValue;
            }
        }, Intrinsics.Kotlin.class, "suspendConversion0", "logDiffForTable_CVGC_8U$suspendConversion0(Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", 0), new LinearBrightnessKt$logDiffForTable$2(tableLogBuffer, "linear", str, null));
    }
}
