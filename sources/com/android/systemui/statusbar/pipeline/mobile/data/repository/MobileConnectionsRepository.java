package com.android.systemui.statusbar.pipeline.mobile.data.repository;

import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface MobileConnectionsRepository {
    StateFlow getActiveMobileDataRepository();

    StateFlow getActiveMobileDataSubscriptionId();

    Flow getActiveSubChangedInGroupEvent();

    StateFlow getDefaultConnectionIsValidated();

    StateFlow getDefaultDataSubId();

    StateFlow getDefaultDataSubRatConfig();

    Flow getDefaultMobileIconGroup();

    Flow getDefaultMobileIconMapping();

    Flow getHasCarrierMergedConnection();

    boolean getIsAnySimSecure();

    StateFlow getMobileIsDefault();

    MobileConnectionRepository getRepoForSubId(int i);

    StateFlow getSubscriptions();

    Flow isAnySimSecure();

    StateFlow isDeviceEmergencyCallCapable();

    Object isInEcmMode(Continuation continuation);
}
