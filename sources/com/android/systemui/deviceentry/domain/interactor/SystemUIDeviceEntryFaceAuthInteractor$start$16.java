package com.android.systemui.deviceentry.domain.interactor;

import kotlin.Pair;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.AdaptedFunctionReference;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final /* synthetic */ class SystemUIDeviceEntryFaceAuthInteractor$start$16 extends AdaptedFunctionReference implements Function3 {
    public static final SystemUIDeviceEntryFaceAuthInteractor$start$16 INSTANCE = new SystemUIDeviceEntryFaceAuthInteractor$start$16();

    public SystemUIDeviceEntryFaceAuthInteractor$start$16() {
        super(3, Pair.class, "<init>", "<init>(Ljava/lang/Object;Ljava/lang/Object;)V", 4);
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        int intValue = ((Number) obj).intValue();
        Boolean bool = (Boolean) obj2;
        bool.booleanValue();
        return new Pair(new Integer(intValue), bool);
    }
}
