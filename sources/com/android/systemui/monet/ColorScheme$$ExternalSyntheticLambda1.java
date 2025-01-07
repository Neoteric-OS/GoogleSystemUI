package com.android.systemui.monet;

import android.graphics.Color;
import com.google.ux.material.libmonet.hct.Hct;
import java.util.Map;
import java.util.function.Function;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class ColorScheme$$ExternalSyntheticLambda1 implements Function {
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ ColorScheme$$ExternalSyntheticLambda1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                return (Integer) ((Map.Entry) obj).getKey();
            case 1:
                return ColorScheme.stringForColor(((Integer) obj).intValue());
            case 2:
                return (Hct) ((Map.Entry) obj).getValue();
            case 3:
                return Integer.valueOf(((Color) obj).toArgb());
            default:
                return new Hct(((Integer) ((Map.Entry) obj).getKey()).intValue());
        }
    }
}
