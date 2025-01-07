package com.android.systemui.education.domain.interactor;

import com.android.systemui.inputdevice.data.repository.UserInputDeviceRepository;
import com.android.systemui.inputdevice.tutorial.data.repository.TutorialSchedulerRepository;
import java.time.Clock;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyboardTouchpadEduStatsInteractorImpl {
    public final CoroutineScope backgroundScope;
    public final Clock clock;
    public final ContextualEducationInteractor contextualEducationInteractor;
    public final UserInputDeviceRepository inputDeviceRepository;
    public final TutorialSchedulerRepository tutorialRepository;

    public KeyboardTouchpadEduStatsInteractorImpl(CoroutineScope coroutineScope, ContextualEducationInteractor contextualEducationInteractor, UserInputDeviceRepository userInputDeviceRepository, TutorialSchedulerRepository tutorialSchedulerRepository, Clock clock) {
    }
}
