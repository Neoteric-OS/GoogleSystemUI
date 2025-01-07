package com.android.systemui.telephony.domain.interactor;

import com.android.systemui.telephony.data.repository.TelephonyRepositoryImpl;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TelephonyInteractor {
    public final Flow callState;
    public final ReadonlyStateFlow isInCall;
    public final TelephonyRepositoryImpl repository;

    public TelephonyInteractor(TelephonyRepositoryImpl telephonyRepositoryImpl) {
        this.repository = telephonyRepositoryImpl;
        this.callState = telephonyRepositoryImpl.callState;
        this.isInCall = telephonyRepositoryImpl.isInCall;
    }
}
