package com.android.systemui.statusbar.chips.casttootherdevice.ui.viewmodel;

import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.LogLevel;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final /* synthetic */ class CastToOtherDeviceChipViewModel$createCastScreenToOtherDeviceDialogDelegate$1 extends FunctionReferenceImpl implements Function0 {
    @Override // kotlin.jvm.functions.Function0
    public final /* bridge */ /* synthetic */ Object invoke() {
        m870invoke();
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: collision with other method in class */
    public final void m870invoke() {
        CastToOtherDeviceChipViewModel castToOtherDeviceChipViewModel = (CastToOtherDeviceChipViewModel) this.receiver;
        castToOtherDeviceChipViewModel.getClass();
        LogLevel logLevel = LogLevel.INFO;
        CastToOtherDeviceChipViewModel$stopProjectingFromDialog$2 castToOtherDeviceChipViewModel$stopProjectingFromDialog$2 = new Function1() { // from class: com.android.systemui.statusbar.chips.casttootherdevice.ui.viewmodel.CastToOtherDeviceChipViewModel$stopProjectingFromDialog$2
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return "Stop casting requested from dialog (projection)";
            }
        };
        LogBuffer logBuffer = castToOtherDeviceChipViewModel.logger;
        logBuffer.commit(logBuffer.obtain("CastToOtherVM", logLevel, castToOtherDeviceChipViewModel$stopProjectingFromDialog$2, null));
        castToOtherDeviceChipViewModel.hideChipDuringDialogTransitionHelper.onActivityStoppedFromDialog();
        castToOtherDeviceChipViewModel.mediaProjectionChipInteractor.stopProjecting();
    }
}
