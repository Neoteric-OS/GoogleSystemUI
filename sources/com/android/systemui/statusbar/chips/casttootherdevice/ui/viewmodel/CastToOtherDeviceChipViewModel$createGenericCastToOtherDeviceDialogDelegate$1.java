package com.android.systemui.statusbar.chips.casttootherdevice.ui.viewmodel;

import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.statusbar.chips.casttootherdevice.domain.interactor.MediaRouterChipInteractor;
import com.android.systemui.statusbar.policy.CastDevice;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final /* synthetic */ class CastToOtherDeviceChipViewModel$createGenericCastToOtherDeviceDialogDelegate$1 extends FunctionReferenceImpl implements Function0 {
    @Override // kotlin.jvm.functions.Function0
    public final /* bridge */ /* synthetic */ Object invoke() {
        m871invoke();
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: collision with other method in class */
    public final void m871invoke() {
        CastToOtherDeviceChipViewModel castToOtherDeviceChipViewModel = (CastToOtherDeviceChipViewModel) this.receiver;
        castToOtherDeviceChipViewModel.getClass();
        LogLevel logLevel = LogLevel.INFO;
        CastToOtherDeviceChipViewModel$stopMediaRouterCastingFromDialog$2 castToOtherDeviceChipViewModel$stopMediaRouterCastingFromDialog$2 = new Function1() { // from class: com.android.systemui.statusbar.chips.casttootherdevice.ui.viewmodel.CastToOtherDeviceChipViewModel$stopMediaRouterCastingFromDialog$2
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return "Stop casting requested from dialog (router)";
            }
        };
        LogBuffer logBuffer = castToOtherDeviceChipViewModel.logger;
        logBuffer.commit(logBuffer.obtain("CastToOtherVM", logLevel, castToOtherDeviceChipViewModel$stopMediaRouterCastingFromDialog$2, null));
        castToOtherDeviceChipViewModel.hideChipDuringDialogTransitionHelper.onActivityStoppedFromDialog();
        MediaRouterChipInteractor mediaRouterChipInteractor = castToOtherDeviceChipViewModel.mediaRouterChipInteractor;
        CastDevice castDevice = (CastDevice) ((StateFlowImpl) mediaRouterChipInteractor.activeCastDevice.$$delegate_0).getValue();
        if (castDevice != null) {
            mediaRouterChipInteractor.mediaRouterRepository.castController.stopCasting(castDevice);
        }
    }
}
