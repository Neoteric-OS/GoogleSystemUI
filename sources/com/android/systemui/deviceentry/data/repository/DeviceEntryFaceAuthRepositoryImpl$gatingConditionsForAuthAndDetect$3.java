package com.android.systemui.deviceentry.data.repository;

import com.android.systemui.keyguard.shared.model.KeyguardState;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final /* synthetic */ class DeviceEntryFaceAuthRepositoryImpl$gatingConditionsForAuthAndDetect$3 extends FunctionReferenceImpl implements Function1 {
    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        ((KeyguardState.Companion) this.receiver).getClass();
        return Boolean.valueOf(KeyguardState.Companion.deviceIsAsleepInState((KeyguardState) obj));
    }
}
