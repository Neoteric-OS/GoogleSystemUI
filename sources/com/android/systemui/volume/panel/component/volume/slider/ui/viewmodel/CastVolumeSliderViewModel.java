package com.android.systemui.volume.panel.component.volume.slider.ui.viewmodel;

import android.content.Context;
import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import com.android.systemui.common.shared.model.Icon;
import com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaDeviceSessionInteractor;
import com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaDeviceSessionInteractor$playbackInfo$$inlined$map$1;
import com.android.systemui.volume.panel.component.mediaoutput.shared.model.MediaDeviceSession;
import com.android.systemui.volume.panel.component.volume.slider.ui.viewmodel.SliderState;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedFloatRange;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CastVolumeSliderViewModel implements SliderViewModel {
    public final Context context;
    public final CoroutineScope coroutineScope;
    public final MediaDeviceSessionInteractor mediaDeviceSessionInteractor;
    public final MediaDeviceSession session;
    public final ReadonlyStateFlow slider;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class State implements SliderState {
        public final Icon.Resource icon;
        public final String label;
        public final float value;
        public final ClosedFloatRange valueRange;

        public State(float f, ClosedFloatRange closedFloatRange, Icon.Resource resource, String str) {
            this.value = f;
            this.valueRange = closedFloatRange;
            this.icon = resource;
            this.label = str;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof State)) {
                return false;
            }
            State state = (State) obj;
            return Float.compare(this.value, state.value) == 0 && this.valueRange.equals(state.valueRange) && this.icon.equals(state.icon) && Intrinsics.areEqual(this.label, state.label);
        }

        @Override // com.android.systemui.volume.panel.component.volume.slider.ui.viewmodel.SliderState
        public final String getA11yClickDescription() {
            return null;
        }

        @Override // com.android.systemui.volume.panel.component.volume.slider.ui.viewmodel.SliderState
        public final String getA11yStateDescription() {
            return null;
        }

        @Override // com.android.systemui.volume.panel.component.volume.slider.ui.viewmodel.SliderState
        public final int getA11yStep() {
            return 1;
        }

        @Override // com.android.systemui.volume.panel.component.volume.slider.ui.viewmodel.SliderState
        public final String getDisabledMessage() {
            return null;
        }

        @Override // com.android.systemui.volume.panel.component.volume.slider.ui.viewmodel.SliderState
        public final Icon getIcon() {
            return this.icon;
        }

        @Override // com.android.systemui.volume.panel.component.volume.slider.ui.viewmodel.SliderState
        public final String getLabel() {
            return this.label;
        }

        @Override // com.android.systemui.volume.panel.component.volume.slider.ui.viewmodel.SliderState
        public final float getValue() {
            return this.value;
        }

        @Override // com.android.systemui.volume.panel.component.volume.slider.ui.viewmodel.SliderState
        public final ClosedFloatingPointRange getValueRange() {
            return this.valueRange;
        }

        public final int hashCode() {
            return Integer.hashCode(1) + TransitionData$$ExternalSyntheticOutline0.m(PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.label, (this.icon.hashCode() + ((this.valueRange.hashCode() + (Float.hashCode(this.value) * 31)) * 31)) * 31, 31), 31, true);
        }

        @Override // com.android.systemui.volume.panel.component.volume.slider.ui.viewmodel.SliderState
        public final boolean isEnabled() {
            return true;
        }

        @Override // com.android.systemui.volume.panel.component.volume.slider.ui.viewmodel.SliderState
        public final boolean isMutable() {
            return false;
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("State(value=");
            sb.append(this.value);
            sb.append(", valueRange=");
            sb.append(this.valueRange);
            sb.append(", icon=");
            sb.append(this.icon);
            sb.append(", label=");
            return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(sb, this.label, ", isEnabled=true, a11yStep=1)");
        }
    }

    public CastVolumeSliderViewModel(MediaDeviceSession mediaDeviceSession, CoroutineScope coroutineScope, Context context, MediaDeviceSessionInteractor mediaDeviceSessionInteractor) {
        this.session = mediaDeviceSession;
        this.coroutineScope = coroutineScope;
        this.context = context;
        this.mediaDeviceSessionInteractor = mediaDeviceSessionInteractor;
        final MediaDeviceSessionInteractor$playbackInfo$$inlined$map$1 playbackInfo = mediaDeviceSessionInteractor.playbackInfo(mediaDeviceSession);
        this.slider = FlowKt.stateIn(new Flow() { // from class: com.android.systemui.volume.panel.component.volume.slider.ui.viewmodel.CastVolumeSliderViewModel$special$$inlined$mapNotNull$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.volume.panel.component.volume.slider.ui.viewmodel.CastVolumeSliderViewModel$special$$inlined$mapNotNull$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ CastVolumeSliderViewModel this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.volume.panel.component.volume.slider.ui.viewmodel.CastVolumeSliderViewModel$special$$inlined$mapNotNull$1$2$1, reason: invalid class name */
                public final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector, CastVolumeSliderViewModel castVolumeSliderViewModel) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = castVolumeSliderViewModel;
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r9, kotlin.coroutines.Continuation r10) {
                    /*
                        r8 = this;
                        boolean r0 = r10 instanceof com.android.systemui.volume.panel.component.volume.slider.ui.viewmodel.CastVolumeSliderViewModel$special$$inlined$mapNotNull$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r10
                        com.android.systemui.volume.panel.component.volume.slider.ui.viewmodel.CastVolumeSliderViewModel$special$$inlined$mapNotNull$1$2$1 r0 = (com.android.systemui.volume.panel.component.volume.slider.ui.viewmodel.CastVolumeSliderViewModel$special$$inlined$mapNotNull$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.volume.panel.component.volume.slider.ui.viewmodel.CastVolumeSliderViewModel$special$$inlined$mapNotNull$1$2$1 r0 = new com.android.systemui.volume.panel.component.volume.slider.ui.viewmodel.CastVolumeSliderViewModel$special$$inlined$mapNotNull$1$2$1
                        r0.<init>(r10)
                    L18:
                        java.lang.Object r10 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r10)
                        goto L78
                    L27:
                        java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                        java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
                        r8.<init>(r9)
                        throw r8
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r10)
                        android.media.session.MediaController$PlaybackInfo r9 = (android.media.session.MediaController.PlaybackInfo) r9
                        r10 = 0
                        if (r9 == 0) goto L6b
                        com.android.systemui.volume.panel.component.volume.slider.ui.viewmodel.CastVolumeSliderViewModel r2 = r8.this$0
                        r2.getClass()
                        kotlin.ranges.IntRange r4 = new kotlin.ranges.IntRange
                        int r5 = r9.getMaxVolume()
                        r6 = 0
                        r4.<init>(r6, r5, r3)
                        com.android.systemui.volume.panel.component.volume.slider.ui.viewmodel.CastVolumeSliderViewModel$State r5 = new com.android.systemui.volume.panel.component.volume.slider.ui.viewmodel.CastVolumeSliderViewModel$State
                        int r9 = r9.getCurrentVolume()
                        float r9 = (float) r9
                        float r6 = (float) r6
                        int r4 = r4.last
                        float r4 = (float) r4
                        kotlin.ranges.ClosedFloatRange r7 = new kotlin.ranges.ClosedFloatRange
                        r7.<init>(r6, r4)
                        com.android.systemui.common.shared.model.Icon$Resource r4 = new com.android.systemui.common.shared.model.Icon$Resource
                        r6 = 2131232335(0x7f08064f, float:1.8080776E38)
                        r4.<init>(r6, r10)
                        android.content.Context r10 = r2.context
                        r2 = 2131953302(0x7f130696, float:1.9543071E38)
                        java.lang.String r10 = r10.getString(r2)
                        r5.<init>(r9, r7, r4, r10)
                        r10 = r5
                    L6b:
                        if (r10 == 0) goto L78
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r8 = r8.$this_unsafeFlow
                        java.lang.Object r8 = r8.emit(r10, r0)
                        if (r8 != r1) goto L78
                        return r1
                    L78:
                        kotlin.Unit r8 = kotlin.Unit.INSTANCE
                        return r8
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.volume.panel.component.volume.slider.ui.viewmodel.CastVolumeSliderViewModel$special$$inlined$mapNotNull$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = MediaDeviceSessionInteractor$playbackInfo$$inlined$map$1.this.collect(new AnonymousClass2(flowCollector, this), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        }, coroutineScope, SharingStarted.Companion.Eagerly, SliderState.Empty.INSTANCE);
    }

    @Override // com.android.systemui.volume.panel.component.volume.slider.ui.viewmodel.SliderViewModel
    public final ReadonlyStateFlow getSlider() {
        return this.slider;
    }

    @Override // com.android.systemui.volume.panel.component.volume.slider.ui.viewmodel.SliderViewModel
    public final void onValueChanged(SliderState sliderState, float f) {
        BuildersKt.launch$default(this.coroutineScope, null, null, new CastVolumeSliderViewModel$onValueChanged$1(this, f, null), 3);
    }

    @Override // com.android.systemui.volume.panel.component.volume.slider.ui.viewmodel.SliderViewModel
    public final void onValueChangeFinished() {
    }

    @Override // com.android.systemui.volume.panel.component.volume.slider.ui.viewmodel.SliderViewModel
    public final void toggleMuted(SliderState sliderState) {
    }
}
