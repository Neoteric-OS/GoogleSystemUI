package com.android.systemui.brightness.domain.interactor;

import com.android.systemui.brightness.data.repository.BrightnessPolicyRepositoryImpl;
import com.android.systemui.plugins.ActivityStarter;
import kotlinx.coroutines.flow.Flow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BrightnessPolicyEnforcementInteractor {
    public final ActivityStarter activityStarter;
    public final Flow brightnessPolicyRestriction;

    public BrightnessPolicyEnforcementInteractor(BrightnessPolicyRepositoryImpl brightnessPolicyRepositoryImpl, ActivityStarter activityStarter) {
        this.activityStarter = activityStarter;
        this.brightnessPolicyRestriction = brightnessPolicyRepositoryImpl.restrictionPolicy;
    }
}
