package com.android.systemui.bouncer.domain.interactor;

import android.content.Context;
import com.android.internal.util.EmergencyAffordanceManager;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class BouncerInteractorModule_EmergencyAffordanceManagerFactory implements Provider {
    public static EmergencyAffordanceManager emergencyAffordanceManager(Context context) {
        return new EmergencyAffordanceManager(context);
    }
}
