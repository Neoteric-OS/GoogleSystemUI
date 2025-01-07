package com.android.systemui.brightness.ui.compose;

import com.android.settingslib.RestrictedLockUtils;
import com.android.systemui.brightness.domain.interactor.BrightnessPolicyEnforcementInteractor;
import com.android.systemui.brightness.ui.viewmodel.BrightnessSliderViewModel;
import com.android.systemui.utils.PolicyRestriction;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final /* synthetic */ class BrightnessSliderKt$BrightnessSliderContainer$1 extends FunctionReferenceImpl implements Function1 {
    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        BrightnessPolicyEnforcementInteractor brightnessPolicyEnforcementInteractor = ((BrightnessSliderViewModel) this.receiver).brightnessPolicyEnforcementInteractor;
        brightnessPolicyEnforcementInteractor.getClass();
        brightnessPolicyEnforcementInteractor.activityStarter.postStartActivityDismissingKeyguard(RestrictedLockUtils.getShowAdminSupportDetailsIntent(((PolicyRestriction.Restricted) obj).admin), 0);
        return Unit.INSTANCE;
    }
}
