package com.android.systemui.volume.panel.component.mediaoutput.data.repository;

import com.android.settingslib.volume.shared.AudioManagerEventsReceiverImpl;
import com.android.systemui.media.controls.util.LocalMediaManagerFactory;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class LocalMediaRepositoryFactoryImpl {
    public final AudioManagerEventsReceiverImpl eventsReceiver;
    public final LocalMediaManagerFactory localMediaManagerFactory;

    public LocalMediaRepositoryFactoryImpl(AudioManagerEventsReceiverImpl audioManagerEventsReceiverImpl, LocalMediaManagerFactory localMediaManagerFactory) {
        this.eventsReceiver = audioManagerEventsReceiverImpl;
        this.localMediaManagerFactory = localMediaManagerFactory;
    }
}
