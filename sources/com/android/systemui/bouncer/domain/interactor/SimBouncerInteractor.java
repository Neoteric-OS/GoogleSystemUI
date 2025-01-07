package com.android.systemui.bouncer.domain.interactor;

import android.content.Context;
import android.content.res.Resources;
import android.telephony.TelephonyManager;
import android.telephony.euicc.EuiccManager;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.bouncer.data.repository.SimBouncerRepositoryImpl;
import com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionsRepository;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharedFlowImpl;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SimBouncerInteractor {
    public final SharedFlowImpl _bouncerMessageChanged;
    public final Context applicationContext;
    public final CoroutineScope applicationScope;
    public final CoroutineDispatcher backgroundDispatcher;
    public final SharedFlowImpl bouncerMessageChanged;
    public final ReadonlyStateFlow errorDialogMessage;
    public final EuiccManager euiccManager;
    public final ReadonlyStateFlow isAnySimSecure;
    public final ReadonlyStateFlow isLockedEsim;
    public final KeyguardUpdateMonitor keyguardUpdateMonitor;
    public final SimBouncerRepositoryImpl repository;
    public final Resources resources;
    public final ReadonlyStateFlow subId;
    public final TelephonyManager telephonyManager;

    public SimBouncerInteractor(Context context, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, SimBouncerRepositoryImpl simBouncerRepositoryImpl, TelephonyManager telephonyManager, Resources resources, KeyguardUpdateMonitor keyguardUpdateMonitor, EuiccManager euiccManager, MobileConnectionsRepository mobileConnectionsRepository) {
        ReadonlyStateFlow readonlyStateFlow = simBouncerRepositoryImpl.subscriptionId;
        FlowKt.stateIn(mobileConnectionsRepository.isAnySimSecure(), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), Boolean.valueOf(mobileConnectionsRepository.getIsAnySimSecure()));
        ReadonlyStateFlow readonlyStateFlow2 = simBouncerRepositoryImpl.isLockedEsim;
        ReadonlyStateFlow readonlyStateFlow3 = simBouncerRepositoryImpl.errorDialogMessage;
        SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7);
    }
}
