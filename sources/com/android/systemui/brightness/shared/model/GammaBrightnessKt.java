package com.android.systemui.brightness.shared.model;

import com.android.systemui.log.table.TableLogBuffer;
import com.android.systemui.util.kotlin.FlowKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1;
import kotlinx.coroutines.flow.SafeFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class GammaBrightnessKt {
    /* renamed from: logDiffForTable-GAU2kQA, reason: not valid java name */
    public static final SafeFlow m790logDiffForTableGAU2kQA(FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1, final TableLogBuffer tableLogBuffer) {
        return FlowKt.pairwiseBy(flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1, new GammaBrightnessKt$logDiffForTable$1(1, new Function0() { // from class: com.android.systemui.brightness.shared.model.GammaBrightnessKt$logDiffForTable$initialValueFun$1
            final /* synthetic */ String $columnPrefix = "gamma";
            final /* synthetic */ String $columnName = "brightness";
            final /* synthetic */ GammaBrightness $initialValue = null;

            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                TableLogBuffer tableLogBuffer2 = TableLogBuffer.this;
                String str = this.$columnPrefix;
                String str2 = this.$columnName;
                GammaBrightness gammaBrightness = this.$initialValue;
                tableLogBuffer2.logChange(str, str2, gammaBrightness != null ? Integer.valueOf(gammaBrightness.value) : null, true);
                return this.$initialValue;
            }
        }, Intrinsics.Kotlin.class, "suspendConversion0", "logDiffForTable_GAU2kQA$suspendConversion0(Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", 0), new GammaBrightnessKt$logDiffForTable$2(tableLogBuffer, "gamma", "brightness", null));
    }
}
