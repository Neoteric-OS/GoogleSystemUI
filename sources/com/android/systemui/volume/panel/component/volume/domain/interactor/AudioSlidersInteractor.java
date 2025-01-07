package com.android.systemui.volume.panel.component.volume.domain.interactor;

import com.android.settingslib.volume.domain.interactor.AudioModeInteractor;
import com.android.settingslib.volume.shared.model.AudioStream;
import com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaOutputInteractor;
import com.android.systemui.volume.panel.component.volume.domain.model.SliderType;
import com.android.systemui.volume.panel.shared.model.ResultKt;
import kotlin.collections.EmptyList;
import kotlin.collections.builders.ListBuilder;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$3;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SafeFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AudioSlidersInteractor {
    public final ReadonlyStateFlow volumePanelSliders;

    public AudioSlidersInteractor(ContextScope contextScope, MediaOutputInteractor mediaOutputInteractor, AudioModeInteractor audioModeInteractor) {
        this.volumePanelSliders = FlowKt.stateIn(new SafeFlow(new FlowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$3(new Flow[]{mediaOutputInteractor.activeMediaDeviceSessions, ResultKt.filterData(mediaOutputInteractor.defaultActiveMediaSession), audioModeInteractor.isOngoingCall}, null, new AudioSlidersInteractor$volumePanelSliders$1(this, null))), contextScope, SharingStarted.Companion.Eagerly, EmptyList.INSTANCE);
    }

    public static final void access$addStream(AudioSlidersInteractor audioSlidersInteractor, ListBuilder listBuilder, int i) {
        audioSlidersInteractor.getClass();
        AudioStream.m772constructorimpl(i);
        listBuilder.add(new SliderType.Stream(i));
    }
}
