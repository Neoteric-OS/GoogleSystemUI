package com.android.systemui.volume.panel.component.volume.slider.ui.viewmodel;

import android.content.Context;
import android.media.AudioSystem;
import android.util.Log;
import com.android.settingslib.volume.shared.model.AudioStream;
import com.android.settingslib.volume.shared.model.AudioStreamModel;
import com.android.settingslib.volume.shared.model.RingerMode;
import com.android.systemui.common.shared.model.Icon;
import com.android.systemui.volume.panel.component.volume.slider.ui.viewmodel.AudioStreamSliderViewModel;
import com.android.wm.shell.R;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function4;
import kotlin.ranges.ClosedFloatRange;
import kotlin.ranges.IntRange;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class AudioStreamSliderViewModel$slider$1 extends SuspendLambda implements Function4 {
    /* synthetic */ int I$0;
    /* synthetic */ Object L$0;
    /* synthetic */ boolean Z$0;
    int label;
    final /* synthetic */ AudioStreamSliderViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AudioStreamSliderViewModel$slider$1(AudioStreamSliderViewModel audioStreamSliderViewModel, Continuation continuation) {
        super(4, continuation);
        this.this$0 = audioStreamSliderViewModel;
    }

    @Override // kotlin.jvm.functions.Function4
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        boolean booleanValue = ((Boolean) obj2).booleanValue();
        int i = ((RingerMode) obj3).value;
        AudioStreamSliderViewModel$slider$1 audioStreamSliderViewModel$slider$1 = new AudioStreamSliderViewModel$slider$1(this.this$0, (Continuation) obj4);
        audioStreamSliderViewModel$slider$1.L$0 = (AudioStreamModel) obj;
        audioStreamSliderViewModel$slider$1.Z$0 = booleanValue;
        audioStreamSliderViewModel$slider$1.I$0 = i;
        return audioStreamSliderViewModel$slider$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String string;
        int i;
        String str;
        String str2;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        AudioStreamModel audioStreamModel = (AudioStreamModel) this.L$0;
        boolean z = this.Z$0;
        int i2 = this.I$0;
        AudioStreamSliderViewModel audioStreamSliderViewModel = this.this$0;
        audioStreamSliderViewModel.volumePanelLogger.m894onVolumeUpdateReceivedVrMivd8(audioStreamSliderViewModel.audioStream, audioStreamModel.volume);
        AudioStreamSliderViewModel audioStreamSliderViewModel2 = this.this$0;
        Map map = audioStreamSliderViewModel2.labelsByStream;
        int i3 = audioStreamModel.audioStream;
        Integer num = (Integer) map.get(new AudioStream(i3));
        if (num == null || (string = audioStreamSliderViewModel2.context.getString(num.intValue())) == null) {
            throw new IllegalStateException("No label for the stream: ".concat(AudioSystem.streamToString(i3)).toString());
        }
        int i4 = audioStreamModel.volume;
        float f = i4;
        int i5 = audioStreamModel.minVolume;
        int i6 = audioStreamModel.maxVolume;
        new IntRange(i5, i6, 1);
        ClosedFloatRange closedFloatRange = new ClosedFloatRange(i5, new IntRange(i5, i6, 1).last);
        boolean z2 = audioStreamModel.isMuted;
        boolean z3 = audioStreamModel.isAffectedByMute;
        if (z3 && z2) {
            boolean contains = audioStreamSliderViewModel2.streamsAffectedByRing.contains(Integer.valueOf(i3));
            i = R.drawable.ic_volume_off;
            if (contains && i2 == 1) {
                i = R.drawable.ic_volume_ringer_vibrate;
            }
        } else {
            Integer num2 = (Integer) audioStreamSliderViewModel2.iconsByStream.get(new AudioStream(i3));
            if (num2 != null) {
                i = num2.intValue();
            } else {
                Log.wtf("AudioStreamSliderViewModel", "No icon for the stream: ".concat(AudioSystem.streamToString(i3)));
                i = R.drawable.ic_music_note;
            }
        }
        Icon.Resource resource = new Icon.Resource(i, null);
        String string2 = audioStreamSliderViewModel2.context.getString(((Number) audioStreamSliderViewModel2.disabledTextByStream.getOrDefault(new AudioStream(i3), Integer.valueOf(R.string.stream_alarm_unavailable))).intValue());
        int i7 = new IntRange(i5, i6, 1).step;
        if (z3) {
            str = audioStreamSliderViewModel2.context.getString(z2 ? R.string.volume_panel_hint_unmute : R.string.volume_panel_hint_mute, string);
        } else {
            str = null;
        }
        new IntRange(i5, i6, 1);
        if (i4 == i5) {
            Context context = audioStreamSliderViewModel2.context;
            boolean contains2 = audioStreamSliderViewModel2.streamsAffectedByRing.contains(Integer.valueOf(i3));
            int i8 = R.string.volume_panel_hint_muted;
            if (contains2 && i2 == 1) {
                i8 = R.string.volume_panel_hint_vibrate;
            }
            str2 = context.getString(i8);
        } else {
            str2 = null;
        }
        return new AudioStreamSliderViewModel.State(f, closedFloatRange, resource, string, string2, z, i7, str, str2, audioStreamModel.isAffectedByMute, audioStreamModel);
    }
}
