package com.android.systemui.media.controls.ui.animation;

import com.android.systemui.monet.ColorScheme;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final /* synthetic */ class ColorSchemeTransition$textPrimary$1 extends FunctionReferenceImpl implements Function1 {
    public static final ColorSchemeTransition$textPrimary$1 INSTANCE = new ColorSchemeTransition$textPrimary$1();

    public ColorSchemeTransition$textPrimary$1() {
        super(1, MediaColorSchemesKt.class, "textPrimaryFromScheme", "textPrimaryFromScheme(Lcom/android/systemui/monet/ColorScheme;)I", 1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        return Integer.valueOf(((ColorScheme) obj).mNeutral1.getS50());
    }
}
