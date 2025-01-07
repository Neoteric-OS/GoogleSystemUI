package com.android.systemui.brightness.ui.viewmodel;

import com.android.systemui.brightness.domain.interactor.BrightnessPolicyEnforcementInteractor;
import com.android.systemui.brightness.domain.interactor.ScreenBrightnessInteractor;
import com.android.systemui.brightness.shared.model.GammaBrightness;
import com.android.systemui.brightness.ui.viewmodel.Drag;
import com.android.systemui.common.shared.model.ContentDescription;
import com.android.systemui.common.shared.model.Icon;
import com.android.systemui.common.shared.model.Text;
import com.android.wm.shell.R;
import kotlin.Unit;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BrightnessSliderViewModel {
    public final BrightnessPolicyEnforcementInteractor brightnessPolicyEnforcementInteractor;
    public final ReadonlyStateFlow currentBrightness;
    public final Flow policyRestriction;
    public final ScreenBrightnessInteractor screenBrightnessInteractor;
    public final int maxBrightness = 65535;
    public final Text.Resource label = new Text.Resource(R.string.quick_settings_brightness_dialog_title);
    public final Icon.Resource icon = new Icon.Resource(R.drawable.ic_brightness_full, new ContentDescription.Resource(R.string.quick_settings_brightness_dialog_title));

    public BrightnessSliderViewModel(ScreenBrightnessInteractor screenBrightnessInteractor, BrightnessPolicyEnforcementInteractor brightnessPolicyEnforcementInteractor, CoroutineScope coroutineScope) {
        this.screenBrightnessInteractor = screenBrightnessInteractor;
        this.brightnessPolicyEnforcementInteractor = brightnessPolicyEnforcementInteractor;
        this.currentBrightness = FlowKt.stateIn(screenBrightnessInteractor.gammaBrightness, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), new GammaBrightness(0));
        this.policyRestriction = brightnessPolicyEnforcementInteractor.brightnessPolicyRestriction;
    }

    public final Object onDrag(Drag drag, SuspendLambda suspendLambda) {
        Object m786setBrightnesssaDbZGg;
        boolean z = drag instanceof Drag.Dragging;
        Unit unit = Unit.INSTANCE;
        ScreenBrightnessInteractor screenBrightnessInteractor = this.screenBrightnessInteractor;
        if (!z) {
            return ((drag instanceof Drag.Stopped) && (m786setBrightnesssaDbZGg = screenBrightnessInteractor.m786setBrightnesssaDbZGg(((Drag.Stopped) drag).brightness, suspendLambda)) == CoroutineSingletons.COROUTINE_SUSPENDED) ? m786setBrightnesssaDbZGg : unit;
        }
        Object m787setTemporaryBrightnesssaDbZGg = screenBrightnessInteractor.m787setTemporaryBrightnesssaDbZGg(((Drag.Dragging) drag).brightness, suspendLambda);
        return m787setTemporaryBrightnesssaDbZGg == CoroutineSingletons.COROUTINE_SUSPENDED ? m787setTemporaryBrightnesssaDbZGg : unit;
    }
}
