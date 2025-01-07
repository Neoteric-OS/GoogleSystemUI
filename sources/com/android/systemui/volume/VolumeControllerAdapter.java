package com.android.systemui.volume;

import com.android.settingslib.volume.data.repository.AudioRepository;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class VolumeControllerAdapter {
    public final AudioRepository audioRepository;
    public final CoroutineScope coroutineScope;

    public VolumeControllerAdapter(CoroutineScope coroutineScope, AudioRepository audioRepository) {
        this.coroutineScope = coroutineScope;
        this.audioRepository = audioRepository;
    }
}
