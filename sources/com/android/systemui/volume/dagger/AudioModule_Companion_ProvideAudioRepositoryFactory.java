package com.android.systemui.volume.dagger;

import android.content.ContentResolver;
import android.media.AudioManager;
import com.android.settingslib.volume.data.repository.AudioRepositoryImpl;
import com.android.settingslib.volume.shared.AudioManagerEventsReceiverImpl;
import com.android.systemui.volume.shared.VolumeLogger;
import dagger.internal.Provider;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class AudioModule_Companion_ProvideAudioRepositoryFactory implements Provider {
    public static AudioRepositoryImpl provideAudioRepository(AudioManagerEventsReceiverImpl audioManagerEventsReceiverImpl, AudioManager audioManager, ContentResolver contentResolver, CoroutineContext coroutineContext, CoroutineScope coroutineScope, VolumeLogger volumeLogger) {
        return new AudioRepositoryImpl(audioManagerEventsReceiverImpl, audioManager, contentResolver, coroutineContext, coroutineScope, volumeLogger);
    }
}
