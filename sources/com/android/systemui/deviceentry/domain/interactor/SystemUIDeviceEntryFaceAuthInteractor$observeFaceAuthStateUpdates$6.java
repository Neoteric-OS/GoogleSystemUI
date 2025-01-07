package com.android.systemui.deviceentry.domain.interactor;

import android.content.pm.UserInfo;
import kotlin.Pair;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.AdaptedFunctionReference;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final /* synthetic */ class SystemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$6 extends AdaptedFunctionReference implements Function3 {
    public static final SystemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$6 INSTANCE = new SystemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$6();

    public SystemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$6() {
        super(3, Pair.class, "<init>", "<init>(Ljava/lang/Object;Ljava/lang/Object;)V", 4);
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        Boolean bool = (Boolean) obj;
        bool.booleanValue();
        return new Pair(bool, (UserInfo) obj2);
    }
}
