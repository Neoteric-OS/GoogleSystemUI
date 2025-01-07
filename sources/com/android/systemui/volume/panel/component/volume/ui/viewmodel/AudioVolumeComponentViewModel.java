package com.android.systemui.volume.panel.component.volume.ui.viewmodel;

import com.android.settingslib.volume.domain.interactor.AudioModeInteractor;
import com.android.systemui.biometrics.AuthContainerView$$ExternalSyntheticOutline0;
import com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaDeviceSessionInteractor;
import com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaOutputInteractor;
import com.android.systemui.volume.panel.component.volume.domain.interactor.AudioSlidersInteractor;
import com.android.systemui.volume.panel.component.volume.ui.viewmodel.SlidersExpandableViewModel;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl$SwitchingProvider$1;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl$SwitchingProvider$2;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.EmptyList;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StartedEagerly;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AudioVolumeComponentViewModel {
    public final DaggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl$SwitchingProvider$2 castVolumeSliderViewModelFactory;
    public final ReadonlyStateFlow isActive;
    public final StateFlowImpl mutableIsExpanded = StateFlowKt.MutableStateFlow(null);
    public final ReadonlyStateFlow portraitExpandable;
    public final ContextScope scope;
    public final ReadonlyStateFlow sliderViewModels;
    public final DaggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl$SwitchingProvider$1 streamSliderViewModelFactory;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.volume.panel.component.volume.ui.viewmodel.AudioVolumeComponentViewModel$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        /* synthetic */ boolean Z$0;
        int label;

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = AudioVolumeComponentViewModel.this.new AnonymousClass1(continuation);
            anonymousClass1.Z$0 = ((Boolean) obj).booleanValue();
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            Boolean bool = (Boolean) obj;
            bool.booleanValue();
            AnonymousClass1 anonymousClass1 = (AnonymousClass1) create(bool, (Continuation) obj2);
            Unit unit = Unit.INSTANCE;
            anonymousClass1.invokeSuspend(unit);
            return unit;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            AuthContainerView$$ExternalSyntheticOutline0.m(!this.Z$0, AudioVolumeComponentViewModel.this.mutableIsExpanded, null);
            return Unit.INSTANCE;
        }
    }

    public AudioVolumeComponentViewModel(ContextScope contextScope, MediaOutputInteractor mediaOutputInteractor, MediaDeviceSessionInteractor mediaDeviceSessionInteractor, DaggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl$SwitchingProvider$1 daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl$SwitchingProvider$1, DaggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl$SwitchingProvider$2 daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl$SwitchingProvider$2, AudioModeInteractor audioModeInteractor, AudioSlidersInteractor audioSlidersInteractor) {
        this.scope = contextScope;
        this.streamSliderViewModelFactory = daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl$SwitchingProvider$1;
        this.castVolumeSliderViewModelFactory = daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl$SwitchingProvider$2;
        FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 flowKt__ZipKt$combine$$inlined$unsafeFlow$1 = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(audioModeInteractor.isOngoingCall, FlowKt.transformLatest(com.android.systemui.volume.panel.shared.model.ResultKt.filterData(mediaOutputInteractor.defaultActiveMediaSession), new AudioVolumeComponentViewModel$special$$inlined$flatMapLatest$1(null, mediaDeviceSessionInteractor)), new AudioVolumeComponentViewModel$isActive$2(3, null));
        StartedEagerly startedEagerly = SharingStarted.Companion.Eagerly;
        ReadonlyStateFlow stateIn = FlowKt.stateIn(flowKt__ZipKt$combine$$inlined$unsafeFlow$1, contextScope, startedEagerly, null);
        this.portraitExpandable = FlowKt.stateIn(FlowKt.transformLatest(new FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1(stateIn), new AudioVolumeComponentViewModel$special$$inlined$flatMapLatest$2(this, null)), contextScope, startedEagerly, SlidersExpandableViewModel.Unavailable.INSTANCE);
        this.sliderViewModels = FlowKt.stateIn(FlowKt.transformLatest(audioSlidersInteractor.volumePanelSliders, new AudioVolumeComponentViewModel$sliderViewModels$1(this, null)), contextScope, startedEagerly, EmptyList.INSTANCE);
        FlowKt.launchIn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(new FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1(stateIn), new AnonymousClass1(null), 0), contextScope);
    }

    public final void onExpandedChanged(boolean z) {
        BuildersKt.launch$default(this.scope, null, null, new AudioVolumeComponentViewModel$onExpandedChanged$1(this, z, null), 3);
    }
}
