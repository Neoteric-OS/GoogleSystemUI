package com.android.systemui.display.data.repository;

import android.util.DisplayMetrics;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DisplayMetricsRepository$displayMetrics$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ LogBuffer $logBuffer;
    /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DisplayMetricsRepository$displayMetrics$2(LogBuffer logBuffer, Continuation continuation) {
        super(2, continuation);
        this.$logBuffer = logBuffer;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        DisplayMetricsRepository$displayMetrics$2 displayMetricsRepository$displayMetrics$2 = new DisplayMetricsRepository$displayMetrics$2(this.$logBuffer, continuation);
        displayMetricsRepository$displayMetrics$2.L$0 = obj;
        return displayMetricsRepository$displayMetrics$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        DisplayMetricsRepository$displayMetrics$2 displayMetricsRepository$displayMetrics$2 = (DisplayMetricsRepository$displayMetrics$2) create((DisplayMetrics) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        displayMetricsRepository$displayMetrics$2.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        DisplayMetrics displayMetrics = (DisplayMetrics) this.L$0;
        LogBuffer logBuffer = this.$logBuffer;
        LogMessage obtain = logBuffer.obtain("DisplayMetrics", LogLevel.INFO, new Function1() { // from class: com.android.systemui.display.data.repository.DisplayMetricsRepository$displayMetrics$2.2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("New metrics: ", ((LogMessage) obj2).getStr1());
            }
        }, null);
        ((LogMessageImpl) obtain).str1 = displayMetrics.toString();
        logBuffer.commit(obtain);
        return Unit.INSTANCE;
    }
}
