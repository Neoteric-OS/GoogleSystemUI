package com.android.compose.modifiers;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.platform.InspectableValueKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class FadingBackgroundKt {
    /* renamed from: background-RPmYEkk, reason: not valid java name */
    public static final Modifier m741backgroundRPmYEkk(Modifier modifier, long j, Function0 function0, Shape shape) {
        SolidColor solidColor = new SolidColor(j);
        Function1 function1 = InspectableValueKt.NoInspectorInfo;
        return modifier.then(new FadingBackground(solidColor, shape, function0));
    }
}
