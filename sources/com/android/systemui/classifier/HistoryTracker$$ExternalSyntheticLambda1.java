package com.android.systemui.classifier;

import java.util.function.BinaryOperator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class HistoryTracker$$ExternalSyntheticLambda1 implements BinaryOperator {
    public final /* synthetic */ int $r8$classId;

    @Override // java.util.function.BiFunction
    public final Object apply(Object obj, Object obj2) {
        Double d = (Double) obj;
        switch (this.$r8$classId) {
            case 0:
                return Double.valueOf(Double.sum(d.doubleValue(), ((Double) obj2).doubleValue()));
            default:
                Double d2 = (Double) obj2;
                return Double.valueOf((d2.doubleValue() * d.doubleValue()) / (((1.0d - d2.doubleValue()) * (1.0d - d.doubleValue())) + (d2.doubleValue() * d.doubleValue())));
        }
    }
}
