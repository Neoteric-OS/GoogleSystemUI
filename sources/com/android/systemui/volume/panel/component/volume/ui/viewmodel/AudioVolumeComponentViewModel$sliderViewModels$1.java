package com.android.systemui.volume.panel.component.volume.ui.viewmodel;

import android.content.Context;
import com.android.internal.logging.UiEventLogger;
import com.android.settingslib.volume.domain.interactor.AudioVolumeInteractor;
import com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaDeviceSessionInteractor;
import com.android.systemui.volume.panel.component.mediaoutput.shared.model.MediaDeviceSession;
import com.android.systemui.volume.panel.component.volume.domain.model.SliderType;
import com.android.systemui.volume.panel.component.volume.slider.ui.viewmodel.AudioStreamSliderViewModel;
import com.android.systemui.volume.panel.component.volume.slider.ui.viewmodel.CastVolumeSliderViewModel;
import com.android.systemui.volume.panel.component.volume.slider.ui.viewmodel.SliderViewModel;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
import java.util.ArrayList;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class AudioVolumeComponentViewModel$sliderViewModels$1 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ AudioVolumeComponentViewModel this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.volume.panel.component.volume.ui.viewmodel.AudioVolumeComponentViewModel$sliderViewModels$1$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ FlowCollector $$this$transformLatest;
        final /* synthetic */ List $sliderTypes;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ AudioVolumeComponentViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(List list, FlowCollector flowCollector, AudioVolumeComponentViewModel audioVolumeComponentViewModel, Continuation continuation) {
            super(2, continuation);
            this.$sliderTypes = list;
            this.$$this$transformLatest = flowCollector;
            this.this$0 = audioVolumeComponentViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$sliderTypes, this.$$this$transformLatest, this.this$0, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            SliderViewModel castVolumeSliderViewModel;
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                List<SliderType> list = this.$sliderTypes;
                AudioVolumeComponentViewModel audioVolumeComponentViewModel = this.this$0;
                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
                for (SliderType sliderType : list) {
                    if (sliderType instanceof SliderType.Stream) {
                        int i2 = ((SliderType.Stream) sliderType).stream;
                        audioVolumeComponentViewModel.getClass();
                        AudioStreamSliderViewModel.FactoryAudioStreamWrapper factoryAudioStreamWrapper = new AudioStreamSliderViewModel.FactoryAudioStreamWrapper(i2);
                        DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider switchingProvider = audioVolumeComponentViewModel.streamSliderViewModelFactory.this$0;
                        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = switchingProvider.sysUIGoogleGlobalRootComponentImpl;
                        Context context = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context;
                        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = switchingProvider.sysUIGoogleSysUIComponentImpl;
                        castVolumeSliderViewModel = new AudioStreamSliderViewModel(factoryAudioStreamWrapper, coroutineScope, context, (AudioVolumeInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideAudioVolumeInteractorProvider.get(), (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.volumePanelLogger());
                    } else {
                        if (!(sliderType instanceof SliderType.MediaDeviceCast)) {
                            throw new NoWhenBranchMatchedException();
                        }
                        MediaDeviceSession mediaDeviceSession = ((SliderType.MediaDeviceCast) sliderType).session;
                        DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider switchingProvider2 = audioVolumeComponentViewModel.castVolumeSliderViewModelFactory.this$0;
                        castVolumeSliderViewModel = new CastVolumeSliderViewModel(mediaDeviceSession, coroutineScope, switchingProvider2.sysUIGoogleGlobalRootComponentImpl.context, (MediaDeviceSessionInteractor) ((DaggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl) switchingProvider2.dozeComponentImpl).mediaDeviceSessionInteractorProvider.get());
                    }
                    arrayList.add(castVolumeSliderViewModel);
                }
                FlowCollector flowCollector = this.$$this$transformLatest;
                this.label = 1;
                if (flowCollector.emit(arrayList, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AudioVolumeComponentViewModel$sliderViewModels$1(AudioVolumeComponentViewModel audioVolumeComponentViewModel, Continuation continuation) {
        super(3, continuation);
        this.this$0 = audioVolumeComponentViewModel;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        AudioVolumeComponentViewModel$sliderViewModels$1 audioVolumeComponentViewModel$sliderViewModels$1 = new AudioVolumeComponentViewModel$sliderViewModels$1(this.this$0, (Continuation) obj3);
        audioVolumeComponentViewModel$sliderViewModels$1.L$0 = (FlowCollector) obj;
        audioVolumeComponentViewModel$sliderViewModels$1.L$1 = (List) obj2;
        return audioVolumeComponentViewModel$sliderViewModels$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            AnonymousClass1 anonymousClass1 = new AnonymousClass1((List) this.L$1, (FlowCollector) this.L$0, this.this$0, null);
            this.L$0 = null;
            this.label = 1;
            if (CoroutineScopeKt.coroutineScope(this, anonymousClass1) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
