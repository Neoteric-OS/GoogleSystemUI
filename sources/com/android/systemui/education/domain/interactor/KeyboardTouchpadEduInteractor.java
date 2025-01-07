package com.android.systemui.education.domain.interactor;

import android.os.SystemProperties;
import com.android.systemui.CoreStartable;
import com.android.systemui.inputdevice.data.repository.UserInputDeviceRepository;
import java.time.Clock;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyboardTouchpadEduInteractor implements CoreStartable {
    public static final /* synthetic */ int $r8$clinit = 0;
    public static final long minIntervalBetweenEdu;
    public static final long usageSessionDuration;
    public final StateFlowImpl _educationTriggered;
    public final CoroutineScope backgroundScope;
    public final Clock clock;
    public final ContextualEducationInteractor contextualEducationInteractor;
    public final ReadonlyStateFlow educationTriggered;
    public final UserInputDeviceRepository userInputDeviceRepository;

    static {
        int i = Duration.$r8$clinit;
        DurationUnit durationUnit = DurationUnit.DAYS;
        long duration = DurationKt.toDuration(3, durationUnit);
        DurationUnit durationUnit2 = DurationUnit.SECONDS;
        usageSessionDuration = DurationKt.toDuration(SystemProperties.getLong("persist.contextual_edu.usage_session_sec", Duration.m1782toLongimpl(duration, durationUnit2)), durationUnit2);
        minIntervalBetweenEdu = DurationKt.toDuration(SystemProperties.getLong("persist.contextual_edu.edu_interval_sec", Duration.m1782toLongimpl(DurationKt.toDuration(7, durationUnit), durationUnit2)), durationUnit2);
    }

    public KeyboardTouchpadEduInteractor(CoroutineScope coroutineScope, ContextualEducationInteractor contextualEducationInteractor, UserInputDeviceRepository userInputDeviceRepository, Clock clock) {
        this.backgroundScope = coroutineScope;
        this.contextualEducationInteractor = contextualEducationInteractor;
        this.userInputDeviceRepository = userInputDeviceRepository;
        this.clock = clock;
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(null);
        this._educationTriggered = MutableStateFlow;
        this.educationTriggered = new ReadonlyStateFlow(MutableStateFlow);
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        KeyboardTouchpadEduInteractor$start$1 keyboardTouchpadEduInteractor$start$1 = new KeyboardTouchpadEduInteractor$start$1(this, null);
        CoroutineScope coroutineScope = this.backgroundScope;
        BuildersKt.launch$default(coroutineScope, null, null, keyboardTouchpadEduInteractor$start$1, 3);
        BuildersKt.launch$default(coroutineScope, null, null, new KeyboardTouchpadEduInteractor$start$2(this, null), 3);
        BuildersKt.launch$default(coroutineScope, null, null, new KeyboardTouchpadEduInteractor$start$3(this, null), 3);
        BuildersKt.launch$default(coroutineScope, null, null, new KeyboardTouchpadEduInteractor$start$4(this, null), 3);
    }
}
