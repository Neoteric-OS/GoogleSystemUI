package com.android.systemui.media.controls.ui.viewmodel;

import android.content.Context;
import com.android.systemui.media.controls.domain.pipeline.interactor.MediaCarouselInteractor;
import com.android.systemui.media.controls.shared.MediaLogger;
import com.android.systemui.media.controls.shared.model.MediaCommonModel;
import com.android.systemui.media.controls.ui.viewmodel.MediaCommonViewModel;
import com.android.systemui.media.controls.util.MediaFlags;
import com.android.systemui.media.controls.util.MediaUiEventLogger;
import com.android.systemui.statusbar.notification.collection.provider.VisualStabilityProvider;
import com.android.systemui.util.Utils;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$14;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import kotlin.Unit;
import kotlin.collections.EmptyList;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaCarouselViewModel {
    public boolean allowReorder;
    public final Context applicationContext;
    public final CoroutineDispatcher backgroundDispatcher;
    public final Executor backgroundExecutor;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$14 controlInteractorFactory;
    public final MediaCarouselInteractor interactor;
    public final MediaUiEventLogger logger;
    public final Map mediaControlByInstanceId;
    public final MediaFlags mediaFlags;
    public final ReadonlyStateFlow mediaItems;
    public final MediaLogger mediaLogger;
    public MediaCommonViewModel.MediaRecommendations mediaRecs;
    public final Set modelsPendingRemoval;
    public final MediaRecommendationsViewModel recommendationsViewModel;
    public Lambda updateHostVisibility;
    public final VisualStabilityProvider visualStabilityProvider;

    public MediaCarouselViewModel(CoroutineScope coroutineScope, Context context, CoroutineDispatcher coroutineDispatcher, Executor executor, VisualStabilityProvider visualStabilityProvider, MediaCarouselInteractor mediaCarouselInteractor, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$14 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$14, MediaRecommendationsViewModel mediaRecommendationsViewModel, MediaUiEventLogger mediaUiEventLogger, MediaFlags mediaFlags, MediaLogger mediaLogger) {
        this.applicationContext = context;
        this.backgroundDispatcher = coroutineDispatcher;
        this.backgroundExecutor = executor;
        this.visualStabilityProvider = visualStabilityProvider;
        this.interactor = mediaCarouselInteractor;
        this.controlInteractorFactory = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$14;
        this.recommendationsViewModel = mediaRecommendationsViewModel;
        this.logger = mediaUiEventLogger;
        this.mediaFlags = mediaFlags;
        this.mediaLogger = mediaLogger;
        final ReadonlyStateFlow readonlyStateFlow = mediaCarouselInteractor.currentMedia;
        FlowKt.stateIn(new Flow() { // from class: com.android.systemui.media.controls.ui.viewmodel.MediaCarouselViewModel$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.media.controls.ui.viewmodel.MediaCarouselViewModel$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ MediaCarouselViewModel this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.media.controls.ui.viewmodel.MediaCarouselViewModel$special$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, MediaCarouselViewModel mediaCarouselViewModel) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = mediaCarouselViewModel;
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x0034  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
                /* JADX WARN: Type inference failed for: r4v7, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r29, kotlin.coroutines.Continuation r30) {
                    /*
                        Method dump skipped, instructions count: 519
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.media.controls.ui.viewmodel.MediaCarouselViewModel$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                ReadonlyStateFlow.this.collect(new AnonymousClass2(flowCollector, this), continuation);
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        }, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), EmptyList.INSTANCE);
        this.updateHostVisibility = new Function0() { // from class: com.android.systemui.media.controls.ui.viewmodel.MediaCarouselViewModel$updateHostVisibility$1
            @Override // kotlin.jvm.functions.Function0
            public final /* bridge */ /* synthetic */ Object invoke() {
                return Unit.INSTANCE;
            }
        };
        this.mediaControlByInstanceId = new LinkedHashMap();
        this.modelsPendingRemoval = new LinkedHashSet();
    }

    public static final void access$onMediaControlAddedOrUpdated(MediaCarouselViewModel mediaCarouselViewModel, MediaCommonViewModel mediaCommonViewModel, MediaCommonModel.MediaControl mediaControl) {
        mediaCarouselViewModel.getClass();
        if (!mediaControl.canBeRemoved || Utils.useMediaResumption(mediaCarouselViewModel.applicationContext)) {
            mediaCarouselViewModel.modelsPendingRemoval.remove(mediaControl);
        } else if (mediaCarouselViewModel.visualStabilityProvider.isReorderingAllowed) {
            mediaCommonViewModel.getOnRemoved().invoke(Boolean.TRUE);
        } else {
            mediaCarouselViewModel.modelsPendingRemoval.add(mediaControl);
        }
    }
}
