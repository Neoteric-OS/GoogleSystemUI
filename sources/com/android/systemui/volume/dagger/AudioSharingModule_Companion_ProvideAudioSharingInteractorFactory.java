package com.android.systemui.volume.dagger;

import com.android.systemui.volume.domain.interactor.AudioSharingInteractor;
import dagger.Lazy;
import dagger.internal.Provider;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class AudioSharingModule_Companion_ProvideAudioSharingInteractorFactory implements Provider {
    public static AudioSharingInteractor provideAudioSharingInteractor(Lazy lazy) {
        Object obj = lazy.get();
        Intrinsics.checkNotNull(obj);
        return (AudioSharingInteractor) obj;
    }
}
