package com.android.wm.shell.startingsurface;

import android.content.res.TypedArray;
import java.util.function.UnaryOperator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class SplashscreenContentDrawer$$ExternalSyntheticLambda0 implements UnaryOperator {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ TypedArray f$0;

    public /* synthetic */ SplashscreenContentDrawer$$ExternalSyntheticLambda0(TypedArray typedArray, int i) {
        this.$r8$classId = i;
        this.f$0 = typedArray;
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        int i = this.$r8$classId;
        TypedArray typedArray = this.f$0;
        switch (i) {
            case 0:
                return Integer.valueOf(typedArray.getColor(56, ((Integer) obj).intValue()));
            case 1:
                return typedArray.getDrawable(57);
            case 2:
                return typedArray.getDrawable(59);
            case 3:
                return Integer.valueOf(typedArray.getColor(60, ((Integer) obj).intValue()));
            default:
                return Integer.valueOf(typedArray.getResourceId(47, ((Integer) obj).intValue()));
        }
    }
}
