package com.android.systemui.volume.dagger;

import com.android.settingslib.notification.domain.interactor.NotificationsSoundPolicyInteractor;
import com.android.settingslib.volume.data.repository.AudioRepository;
import com.android.settingslib.volume.domain.interactor.AudioVolumeInteractor;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class AudioModule_Companion_ProvideAudioVolumeInteractorFactory implements Provider {
    public static AudioVolumeInteractor provideAudioVolumeInteractor(AudioRepository audioRepository, NotificationsSoundPolicyInteractor notificationsSoundPolicyInteractor) {
        return new AudioVolumeInteractor(audioRepository, notificationsSoundPolicyInteractor);
    }
}
