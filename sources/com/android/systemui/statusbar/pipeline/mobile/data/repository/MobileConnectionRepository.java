package com.android.systemui.statusbar.pipeline.mobile.data.repository;

import android.telephony.CellSignalStrength;
import com.android.systemui.log.table.TableLogBuffer;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface MobileConnectionRepository {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final int DEFAULT_NUM_LEVELS = CellSignalStrength.getNumSignalStrengthLevels();
    }

    StateFlow getAllowNetworkSliceIndicator();

    StateFlow getCarrierId();

    StateFlow getCarrierName();

    StateFlow getCarrierNetworkChangeActive();

    StateFlow getCdmaLevel();

    StateFlow getCdmaRoaming();

    StateFlow getDataActivityDirection();

    StateFlow getDataConnectionState();

    StateFlow getDataEnabled();

    StateFlow getHasPrioritizedNetworkCapabilities();

    StateFlow getInflateSignalStrength();

    StateFlow getNetworkName();

    StateFlow getNumberOfLevels();

    StateFlow getOperatorAlphaShort();

    StateFlow getPrimaryLevel();

    StateFlow getResolvedNetworkType();

    TableLogBuffer getTableLogBuffer();

    StateFlow isAllowedDuringAirplaneMode();

    StateFlow isEmergencyOnly();

    StateFlow isGsm();

    Object isInEcmMode(Continuation continuation);

    StateFlow isInService();

    StateFlow isNonTerrestrial();

    StateFlow isRoaming();
}
