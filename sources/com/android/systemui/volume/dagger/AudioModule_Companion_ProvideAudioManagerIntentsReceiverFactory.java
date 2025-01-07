package com.android.systemui.volume.dagger;

import android.content.Context;
import com.android.settingslib.volume.shared.AudioManagerEventsReceiverImpl;
import dagger.internal.Provider;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class AudioModule_Companion_ProvideAudioManagerIntentsReceiverFactory implements Provider {
    public static AudioManagerEventsReceiverImpl provideAudioManagerIntentsReceiver(Context context, CoroutineScope coroutineScope) {
        return new AudioManagerEventsReceiverImpl(context, coroutineScope);
    }
}
