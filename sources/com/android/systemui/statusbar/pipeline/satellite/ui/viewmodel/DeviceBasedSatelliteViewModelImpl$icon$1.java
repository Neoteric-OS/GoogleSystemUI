package com.android.systemui.statusbar.pipeline.satellite.ui.viewmodel;

import com.android.systemui.common.shared.model.ContentDescription;
import com.android.systemui.common.shared.model.Icon;
import com.android.systemui.statusbar.pipeline.satellite.shared.model.SatelliteConnectionState;
import com.android.wm.shell.R;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function4;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class DeviceBasedSatelliteViewModelImpl$icon$1 extends SuspendLambda implements Function4 {
    /* synthetic */ int I$0;
    /* synthetic */ Object L$0;
    /* synthetic */ boolean Z$0;
    int label;

    @Override // kotlin.jvm.functions.Function4
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        int intValue = ((Number) obj3).intValue();
        DeviceBasedSatelliteViewModelImpl$icon$1 deviceBasedSatelliteViewModelImpl$icon$1 = new DeviceBasedSatelliteViewModelImpl$icon$1(4, (Continuation) obj4);
        deviceBasedSatelliteViewModelImpl$icon$1.Z$0 = booleanValue;
        deviceBasedSatelliteViewModelImpl$icon$1.L$0 = (SatelliteConnectionState) obj2;
        deviceBasedSatelliteViewModelImpl$icon$1.I$0 = intValue;
        return deviceBasedSatelliteViewModelImpl$icon$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        boolean z = this.Z$0;
        SatelliteConnectionState satelliteConnectionState = (SatelliteConnectionState) this.L$0;
        int i = this.I$0;
        if (!z) {
            return null;
        }
        int ordinal = satelliteConnectionState.ordinal();
        if (ordinal == 0 || ordinal == 1 || ordinal == 2) {
            return new Icon.Resource(R.drawable.ic_satellite_not_connected, new ContentDescription.Resource(R.string.accessibility_status_bar_satellite_available));
        }
        if (ordinal != 3) {
            throw new NoWhenBranchMatchedException();
        }
        if (i == 0) {
            return new Icon.Resource(R.drawable.ic_satellite_connected_0, new ContentDescription.Resource(R.string.accessibility_status_bar_satellite_no_connection));
        }
        if (i == 1 || i == 2) {
            return new Icon.Resource(R.drawable.ic_satellite_connected_1, new ContentDescription.Resource(R.string.accessibility_status_bar_satellite_poor_connection));
        }
        if (i == 3 || i == 4) {
            return new Icon.Resource(R.drawable.ic_satellite_connected_2, new ContentDescription.Resource(R.string.accessibility_status_bar_satellite_good_connection));
        }
        return null;
    }
}
