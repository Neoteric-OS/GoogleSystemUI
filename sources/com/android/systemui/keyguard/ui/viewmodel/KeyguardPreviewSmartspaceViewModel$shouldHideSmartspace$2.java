package com.android.systemui.keyguard.ui.viewmodel;

import com.android.systemui.keyguard.shared.model.ClockSizeSetting;
import kotlin.Pair;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.AdaptedFunctionReference;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final /* synthetic */ class KeyguardPreviewSmartspaceViewModel$shouldHideSmartspace$2 extends AdaptedFunctionReference implements Function3 {
    public static final KeyguardPreviewSmartspaceViewModel$shouldHideSmartspace$2 INSTANCE = new KeyguardPreviewSmartspaceViewModel$shouldHideSmartspace$2();

    public KeyguardPreviewSmartspaceViewModel$shouldHideSmartspace$2() {
        super(3, Pair.class, "<init>", "<init>(Ljava/lang/Object;Ljava/lang/Object;)V", 4);
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        return new Pair((ClockSizeSetting) obj, (String) obj2);
    }
}
