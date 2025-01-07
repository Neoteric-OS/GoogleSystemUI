package com.android.systemui.media.controls.ui.controller;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.os.Trace;
import android.provider.Settings;
import android.util.Log;
import android.util.MathUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.PathInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.compose.foundation.text.ValidatingOffsetMappingKt$$ExternalSyntheticOutline0;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import androidx.viewpager.widget.ViewPager$$ExternalSyntheticOutline0;
import com.android.app.tracing.TraceUtilsKt;
import com.android.compose.animation.scene.SceneKey;
import com.android.keyguard.ActiveUnlockConfig$$ExternalSyntheticOutline0;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.settingslib.Utils;
import com.android.settingslib.bluetooth.LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0;
import com.android.systemui.Dumpable;
import com.android.systemui.deviceentry.domain.interactor.DeviceEntryInteractor;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.media.controls.domain.pipeline.MediaDataManager;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.android.systemui.media.controls.shared.model.SmartspaceMediaData;
import com.android.systemui.media.controls.ui.controller.MediaHierarchyManager;
import com.android.systemui.media.controls.ui.controller.MediaHostStatesManager;
import com.android.systemui.media.controls.ui.controller.MediaPlayerData;
import com.android.systemui.media.controls.ui.controller.MediaViewController;
import com.android.systemui.media.controls.ui.view.MediaCarouselScrollHandler;
import com.android.systemui.media.controls.ui.view.MediaHostState;
import com.android.systemui.media.controls.ui.view.MediaScrollView;
import com.android.systemui.media.controls.ui.view.MediaViewHolder;
import com.android.systemui.media.controls.ui.view.RecommendationViewHolder;
import com.android.systemui.media.controls.ui.viewmodel.MediaCarouselViewModel;
import com.android.systemui.media.controls.ui.viewmodel.SeekBarViewModel;
import com.android.systemui.media.controls.ui.viewmodel.SeekBarViewModel$listening$1;
import com.android.systemui.media.controls.util.MediaFlags;
import com.android.systemui.media.controls.util.MediaUiEvent;
import com.android.systemui.media.controls.util.MediaUiEventLogger;
import com.android.systemui.media.controls.util.SmallHash;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.qs.PageIndicator;
import com.android.systemui.scene.shared.model.Scenes;
import com.android.systemui.shared.system.SysUiStatsLog;
import com.android.systemui.statusbar.notification.collection.provider.OnReorderingAllowedListener;
import com.android.systemui.statusbar.notification.collection.provider.VisualStabilityProvider;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.util.animation.MeasurementInput;
import com.android.systemui.util.animation.TransitionLayout;
import com.android.systemui.util.animation.TransitionViewState;
import com.android.systemui.util.animation.UniqueObjectHostView;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.settings.GlobalSettings;
import com.android.systemui.util.settings.SecureSettings;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import com.android.wm.shell.R;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.Executor;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaCarouselController implements Dumpable {
    public static final PathInterpolator TRANSFORM_BEZIER = new PathInterpolator(0.68f, 0.0f, 0.0f, 1.0f);
    public final ActivityStarter activityStarter;
    public boolean allowMediaPlayerOnLockScreen;
    public final MediaCarouselController$animationScaleObserver$1 animationScaleObserver;
    public final CoroutineDispatcher backgroundDispatcher;
    public final Executor bgExecutor;
    public Locale carouselLocale;
    public int carouselMeasureHeight;
    public int carouselMeasureWidth;
    public final List commonViewModels;
    public final Context context;
    public final Map controllerById;
    public int currentCarouselHeight;
    public int currentCarouselWidth;
    public boolean currentlyDisablePagination;
    public boolean currentlyShowingOnlyActive;
    public final MediaCarouselControllerLogger debugLogger;
    public MediaHostState desiredHostState;
    public final FalsingManager falsingManager;
    public final GlobalSettings globalSettings;
    public final ReadonlyStateFlow isOnGone;
    public boolean isRtl;
    public boolean isUserInitiatedRemovalQueued;
    public final KeyguardTransitionInteractor keyguardTransitionInteractor;
    public final KeyguardUpdateMonitor keyguardUpdateMonitor;
    public final MediaCarouselController$keyguardUpdateMonitorCallback$1 keyguardUpdateMonitorCallback;
    public final MediaUiEventLogger logger;
    public final CoroutineDispatcher mainDispatcher;
    public final MediaScrollView mediaCarousel;
    public final MediaCarouselScrollHandler mediaCarouselScrollHandler;
    public final MediaCarouselViewModel mediaCarouselViewModel;
    public final ViewGroup mediaContent;
    public final DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider mediaControlPanelFactory;
    public final MediaFlags mediaFlags;
    public final ViewGroup mediaFrame;
    public final MediaHostStatesManager mediaHostStatesManager;
    public final MediaDataManager mediaManager;
    public final DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider mediaViewControllerFactory;
    public boolean needsReordering;
    public final PageIndicator pageIndicator;
    public boolean playersVisible;
    public final SecureSettings secureSettings;
    public View settingsButton;
    public boolean shouldScrollToKey;
    public final SystemClock systemClock;
    public Lambda updateHostVisibility;
    public Function0 updateUserVisibility;
    public final VisualStabilityProvider visualStabilityProvider;
    public int desiredLocation = -1;
    public int currentEndLocation = -1;
    public int currentStartLocation = -1;
    public float currentTransitionProgress = 1.0f;
    public final Set keysNeedRemoval = new LinkedHashSet();
    public boolean currentlyExpanded = true;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.media.controls.ui.controller.MediaCarouselController$1, reason: invalid class name */
    final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function0 {
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            ((MediaCarouselController) this.receiver).onSwipeToDismiss();
            return Unit.INSTANCE;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.media.controls.ui.controller.MediaCarouselController$2, reason: invalid class name */
    final /* synthetic */ class AnonymousClass2 extends FunctionReferenceImpl implements Function0 {
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            ((MediaCarouselController) this.receiver).updatePageIndicatorLocation();
            return Unit.INSTANCE;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.media.controls.ui.controller.MediaCarouselController$3, reason: invalid class name */
    final /* synthetic */ class AnonymousClass3 extends FunctionReferenceImpl implements Function1 {
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            ((MediaCarouselController) this.receiver).updateSeekbarListening(((Boolean) obj).booleanValue());
            return Unit.INSTANCE;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.media.controls.ui.controller.MediaCarouselController$4, reason: invalid class name */
    final /* synthetic */ class AnonymousClass4 extends FunctionReferenceImpl implements Function1 {
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            boolean booleanValue = ((Boolean) obj).booleanValue();
            ((MediaCarouselController) this.receiver).getClass();
            MediaCarouselController.closeGuts(booleanValue);
            return Unit.INSTANCE;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.media.controls.ui.controller.MediaCarouselController$5, reason: invalid class name */
    final /* synthetic */ class AnonymousClass5 extends FunctionReferenceImpl implements Function1 {
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            ((MediaCarouselController) this.receiver).logSmartspaceImpression(((Boolean) obj).booleanValue());
            return Unit.INSTANCE;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.media.controls.ui.controller.MediaCarouselController$8, reason: invalid class name */
    final class AnonymousClass8 extends SuspendLambda implements Function3 {
        private /* synthetic */ Object L$0;
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.media.controls.ui.controller.MediaCarouselController$8$1, reason: invalid class name */
        final class AnonymousClass1 extends SuspendLambda implements Function2 {
            private /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ MediaCarouselController this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(MediaCarouselController mediaCarouselController, Continuation continuation) {
                super(2, continuation);
                this.this$0 = mediaCarouselController;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, continuation);
                anonymousClass1.L$0 = obj;
                return anonymousClass1;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                AnonymousClass1 anonymousClass1 = (AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2);
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
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                this.this$0.listenForAnyStateToGoneKeyguardTransition$frameworks__base__packages__SystemUI__android_common__SystemUI_core(coroutineScope);
                this.this$0.listenForAnyStateToLockscreenTransition$frameworks__base__packages__SystemUI__android_common__SystemUI_core(coroutineScope);
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass8(Continuation continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            AnonymousClass8 anonymousClass8 = MediaCarouselController.this.new AnonymousClass8((Continuation) obj3);
            anonymousClass8.L$0 = (LifecycleOwner) obj;
            return anonymousClass8.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
                Lifecycle.State state = Lifecycle.State.STARTED;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(MediaCarouselController.this, null);
                this.label = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycleOwner, state, anonymousClass1, this) == coroutineSingletons) {
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

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [com.android.keyguard.KeyguardUpdateMonitorCallback, com.android.systemui.media.controls.ui.controller.MediaCarouselController$keyguardUpdateMonitorCallback$1] */
    /* JADX WARN: Type inference failed for: r6v10, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r6v14 */
    /* JADX WARN: Type inference failed for: r6v9 */
    public MediaCarouselController(CoroutineScope coroutineScope, Context context, DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider, VisualStabilityProvider visualStabilityProvider, MediaHostStatesManager mediaHostStatesManager, ActivityStarter activityStarter, SystemClock systemClock, CoroutineDispatcher coroutineDispatcher, DelayableExecutor delayableExecutor, Executor executor, CoroutineDispatcher coroutineDispatcher2, MediaDataManager mediaDataManager, ConfigurationController configurationController, FalsingManager falsingManager, DumpManager dumpManager, MediaUiEventLogger mediaUiEventLogger, MediaCarouselControllerLogger mediaCarouselControllerLogger, MediaFlags mediaFlags, KeyguardUpdateMonitor keyguardUpdateMonitor, KeyguardTransitionInteractor keyguardTransitionInteractor, GlobalSettings globalSettings, SecureSettings secureSettings, MediaCarouselViewModel mediaCarouselViewModel, DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider2, DeviceEntryInteractor deviceEntryInteractor) {
        MediaScrollView mediaScrollView;
        ViewGroup viewGroup;
        int i;
        this.context = context;
        this.mediaControlPanelFactory = switchingProvider;
        this.visualStabilityProvider = visualStabilityProvider;
        this.mediaHostStatesManager = mediaHostStatesManager;
        this.activityStarter = activityStarter;
        this.systemClock = systemClock;
        this.bgExecutor = executor;
        this.backgroundDispatcher = coroutineDispatcher2;
        this.mediaManager = mediaDataManager;
        this.falsingManager = falsingManager;
        this.logger = mediaUiEventLogger;
        this.debugLogger = mediaCarouselControllerLogger;
        this.mediaFlags = mediaFlags;
        this.keyguardUpdateMonitor = keyguardUpdateMonitor;
        this.keyguardTransitionInteractor = keyguardTransitionInteractor;
        this.globalSettings = globalSettings;
        this.secureSettings = secureSettings;
        this.mediaCarouselViewModel = mediaCarouselViewModel;
        this.animationScaleObserver = new MediaCarouselController$animationScaleObserver$1(delayableExecutor, 0);
        ConfigurationController.ConfigurationListener configurationListener = new ConfigurationController.ConfigurationListener() { // from class: com.android.systemui.media.controls.ui.controller.MediaCarouselController$configListener$1
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r1v0 */
            /* JADX WARN: Type inference failed for: r1v1, types: [boolean, int] */
            /* JADX WARN: Type inference failed for: r1v2 */
            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onConfigChanged(Configuration configuration) {
                if (configuration == null) {
                    return;
                }
                int i2 = 0;
                ?? r1 = configuration.getLayoutDirection() != 1 ? 0 : 1;
                MediaCarouselController mediaCarouselController = MediaCarouselController.this;
                if (r1 != mediaCarouselController.isRtl) {
                    mediaCarouselController.isRtl = r1;
                    mediaCarouselController.mediaFrame.setLayoutDirection(r1);
                    MediaScrollView mediaScrollView2 = mediaCarouselController.mediaCarouselScrollHandler.scrollView;
                    if (mediaScrollView2.isLayoutRtl()) {
                        ViewGroup viewGroup2 = mediaScrollView2.contentContainer;
                        if (viewGroup2 == null) {
                            viewGroup2 = null;
                        }
                        i2 = viewGroup2.getWidth() - mediaScrollView2.getWidth();
                    }
                    mediaScrollView2.setScrollX(i2);
                }
            }

            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onDensityOrFontScaleChanged() {
                MediaCarouselController mediaCarouselController = MediaCarouselController.this;
                mediaCarouselController.updatePlayers(true);
                mediaCarouselController.inflateSettingsButton();
            }

            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onLocaleListChanged() {
                MediaCarouselController mediaCarouselController = MediaCarouselController.this;
                if (Intrinsics.areEqual(mediaCarouselController.carouselLocale, mediaCarouselController.context.getResources().getConfiguration().getLocales().get(0))) {
                    return;
                }
                mediaCarouselController.carouselLocale = mediaCarouselController.context.getResources().getConfiguration().getLocales().get(0);
                mediaCarouselController.updatePlayers(true);
                mediaCarouselController.inflateSettingsButton();
            }

            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onThemeChanged() {
                MediaCarouselController mediaCarouselController = MediaCarouselController.this;
                mediaCarouselController.updatePlayers(false);
                mediaCarouselController.inflateSettingsButton();
            }

            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onUiModeChanged() {
                MediaCarouselController mediaCarouselController = MediaCarouselController.this;
                mediaCarouselController.updatePlayers(false);
                mediaCarouselController.inflateSettingsButton();
            }
        };
        ?? r2 = new KeyguardUpdateMonitorCallback() { // from class: com.android.systemui.media.controls.ui.controller.MediaCarouselController$keyguardUpdateMonitorCallback$1
            @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
            public final void onStrongAuthStateChanged(int i2) {
                MediaCarouselController mediaCarouselController = MediaCarouselController.this;
                boolean isUserInLockdown = mediaCarouselController.keyguardUpdateMonitor.isUserInLockdown(i2);
                MediaCarouselControllerLogger mediaCarouselControllerLogger2 = mediaCarouselController.debugLogger;
                if (isUserInLockdown) {
                    mediaCarouselControllerLogger2.getClass();
                    LogLevel logLevel = LogLevel.DEBUG;
                    MediaCarouselControllerLogger$logCarouselHidden$2 mediaCarouselControllerLogger$logCarouselHidden$2 = new Function1() { // from class: com.android.systemui.media.controls.ui.controller.MediaCarouselControllerLogger$logCarouselHidden$2
                        @Override // kotlin.jvm.functions.Function1
                        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                            return "hiding carousel";
                        }
                    };
                    LogBuffer logBuffer = mediaCarouselControllerLogger2.buffer;
                    logBuffer.commit(logBuffer.obtain("MediaCarouselCtlrLog", logLevel, mediaCarouselControllerLogger$logCarouselHidden$2, null));
                    mediaCarouselController.mediaCarousel.setVisibility(8);
                    return;
                }
                if (mediaCarouselController.keyguardUpdateMonitor.mUserIsUnlocked.get(i2)) {
                    mediaCarouselControllerLogger2.getClass();
                    LogLevel logLevel2 = LogLevel.DEBUG;
                    MediaCarouselControllerLogger$logCarouselVisible$2 mediaCarouselControllerLogger$logCarouselVisible$2 = new Function1() { // from class: com.android.systemui.media.controls.ui.controller.MediaCarouselControllerLogger$logCarouselVisible$2
                        @Override // kotlin.jvm.functions.Function1
                        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                            return "showing carousel";
                        }
                    };
                    LogBuffer logBuffer2 = mediaCarouselControllerLogger2.buffer;
                    logBuffer2.commit(logBuffer2.obtain("MediaCarouselCtlrLog", logLevel2, mediaCarouselControllerLogger$logCarouselVisible$2, null));
                    mediaCarouselController.mediaCarousel.setVisibility(0);
                }
            }
        };
        this.keyguardUpdateMonitorCallback = r2;
        this.updateHostVisibility = new Function0() { // from class: com.android.systemui.media.controls.ui.controller.MediaCarouselController$updateHostVisibility$1
            @Override // kotlin.jvm.functions.Function0
            public final /* bridge */ /* synthetic */ Object invoke() {
                return Unit.INSTANCE;
            }
        };
        new LinkedHashMap();
        this.commonViewModels = new ArrayList();
        SceneKey sceneKey = Scenes.Bouncer;
        this.isOnGone = FlowKt.stateIn(keyguardTransitionInteractor.isFinishedIn(KeyguardState.GONE), coroutineScope, SharingStarted.Companion.Eagerly, Boolean.TRUE);
        DumpManager.registerDumpable$default(dumpManager, "MediaCarouselController", this);
        ViewGroup viewGroup2 = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.media_carousel, (ViewGroup) new UniqueObjectHostView(context), false);
        viewGroup2.setLayoutDirection(3);
        this.mediaFrame = viewGroup2;
        MediaScrollView mediaScrollView2 = (MediaScrollView) viewGroup2.requireViewById(R.id.media_carousel_scroller);
        this.mediaCarousel = mediaScrollView2;
        PageIndicator pageIndicator = (PageIndicator) viewGroup2.requireViewById(R.id.media_page_indicator);
        this.pageIndicator = pageIndicator;
        this.mediaCarouselScrollHandler = new MediaCarouselScrollHandler(mediaScrollView2, pageIndicator, delayableExecutor, new AnonymousClass1(0, this, MediaCarouselController.class, "onSwipeToDismiss", "onSwipeToDismiss()V", 0), new AnonymousClass2(0, this, MediaCarouselController.class, "updatePageIndicatorLocation", "updatePageIndicatorLocation()V", 0), new AnonymousClass3(1, this, MediaCarouselController.class, "updateSeekbarListening", "updateSeekbarListening(Z)V", 0), new AnonymousClass4(1, this, MediaCarouselController.class, "closeGuts", "closeGuts(Z)V", 0), falsingManager, new AnonymousClass5(1, this, MediaCarouselController.class, "logSmartspaceImpression", "logSmartspaceImpression(Z)V", 0), mediaUiEventLogger);
        this.carouselLocale = context.getResources().getConfiguration().getLocales().get(0);
        ?? r6 = context.getResources().getConfiguration().getLayoutDirection() == 1 ? 1 : 0;
        if (r6 != this.isRtl) {
            this.isRtl = r6;
            ViewGroup viewGroup3 = viewGroup2;
            viewGroup3.setLayoutDirection(r6);
            if (mediaScrollView2.isLayoutRtl()) {
                mediaScrollView = mediaScrollView2;
                ViewGroup viewGroup4 = mediaScrollView.contentContainer;
                i = (viewGroup4 == null ? null : viewGroup4).getWidth() - mediaScrollView.getWidth();
            } else {
                mediaScrollView = mediaScrollView2;
                i = 0;
            }
            mediaScrollView.setScrollX(i);
            viewGroup = viewGroup3;
        } else {
            mediaScrollView = mediaScrollView2;
            viewGroup = viewGroup2;
        }
        inflateSettingsButton();
        this.mediaContent = (ViewGroup) mediaScrollView.requireViewById(R.id.media_carousel);
        ((ConfigurationControllerImpl) configurationController).addCallback(configurationListener);
        OnReorderingAllowedListener onReorderingAllowedListener = new OnReorderingAllowedListener() { // from class: com.android.systemui.media.controls.ui.controller.MediaCarouselController$setUpListeners$visualStabilityCallback$1
            /* JADX WARN: Type inference failed for: r0v11, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
            @Override // com.android.systemui.statusbar.notification.collection.provider.OnReorderingAllowedListener
            public final void onReorderingAllowed() {
                MediaCarouselController mediaCarouselController = MediaCarouselController.this;
                int i2 = 0;
                if (mediaCarouselController.needsReordering) {
                    mediaCarouselController.needsReordering = false;
                    mediaCarouselController.reorderAllPlayers(null, null);
                }
                Iterator it = mediaCarouselController.keysNeedRemoval.iterator();
                while (it.hasNext()) {
                    MediaCarouselController.removePlayer$default(mediaCarouselController, (String) it.next(), mediaCarouselController.isUserInitiatedRemovalQueued, 6);
                }
                if (mediaCarouselController.keysNeedRemoval.size() > 0) {
                    mediaCarouselController.updateHostVisibility.invoke();
                }
                mediaCarouselController.keysNeedRemoval.clear();
                mediaCarouselController.isUserInitiatedRemovalQueued = false;
                Function0 function0 = mediaCarouselController.updateUserVisibility;
                if (function0 != null) {
                    ((MediaHierarchyManager.AnonymousClass5) function0).invoke();
                }
                MediaScrollView mediaScrollView3 = mediaCarouselController.mediaCarouselScrollHandler.scrollView;
                if (mediaScrollView3.isLayoutRtl()) {
                    ViewGroup viewGroup5 = mediaScrollView3.contentContainer;
                    i2 = (viewGroup5 != null ? viewGroup5 : null).getWidth() - mediaScrollView3.getWidth();
                }
                mediaScrollView3.setScrollX(i2);
            }
        };
        visualStabilityProvider.temporaryListeners.remove(onReorderingAllowedListener);
        visualStabilityProvider.allListeners.addIfAbsent(onReorderingAllowedListener);
        mediaDataManager.addListener(new MediaDataManager.Listener() { // from class: com.android.systemui.media.controls.ui.controller.MediaCarouselController$setUpListeners$1
            /* JADX WARN: Removed duplicated region for block: B:14:0x0102  */
            /* JADX WARN: Removed duplicated region for block: B:30:0x0108  */
            @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager.Listener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final void onMediaDataLoaded(java.lang.String r27, java.lang.String r28, com.android.systemui.media.controls.shared.model.MediaData r29, boolean r30, int r31, boolean r32) {
                /*
                    Method dump skipped, instructions count: 330
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.media.controls.ui.controller.MediaCarouselController$setUpListeners$1.onMediaDataLoaded(java.lang.String, java.lang.String, com.android.systemui.media.controls.shared.model.MediaData, boolean, int, boolean):void");
            }

            @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager.Listener
            public final void onMediaDataRemoved(String str, boolean z) {
                MediaCarouselController mediaCarouselController = MediaCarouselController.this;
                MediaCarouselControllerLogger mediaCarouselControllerLogger2 = mediaCarouselController.debugLogger;
                mediaCarouselControllerLogger2.getClass();
                LogLevel logLevel = LogLevel.DEBUG;
                MediaCarouselControllerLogger$logMediaRemoved$2 mediaCarouselControllerLogger$logMediaRemoved$2 = new Function1() { // from class: com.android.systemui.media.controls.ui.controller.MediaCarouselControllerLogger$logMediaRemoved$2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        LogMessage logMessage = (LogMessage) obj;
                        return "removing player " + logMessage.getStr1() + ", by user " + logMessage.getBool1();
                    }
                };
                LogBuffer logBuffer = mediaCarouselControllerLogger2.buffer;
                LogMessage obtain = logBuffer.obtain("MediaCarouselCtlrLog", logLevel, mediaCarouselControllerLogger$logMediaRemoved$2, null);
                LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
                logMessageImpl.str1 = str;
                logMessageImpl.bool1 = z;
                logBuffer.commit(obtain);
                MediaCarouselController.removePlayer$default(mediaCarouselController, str, z, 6);
            }

            @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager.Listener
            public final void onSmartspaceMediaDataLoaded(String str, SmartspaceMediaData smartspaceMediaData, boolean z) {
                boolean z2;
                long j;
                SystemClock systemClock2;
                boolean z3;
                MediaCarouselController mediaCarouselController = MediaCarouselController.this;
                MediaCarouselControllerLogger mediaCarouselControllerLogger2 = mediaCarouselController.debugLogger;
                mediaCarouselControllerLogger2.getClass();
                LogLevel logLevel = LogLevel.DEBUG;
                MediaCarouselControllerLogger$logRecommendationLoaded$2 mediaCarouselControllerLogger$logRecommendationLoaded$2 = new Function1() { // from class: com.android.systemui.media.controls.ui.controller.MediaCarouselControllerLogger$logRecommendationLoaded$2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        LogMessage logMessage = (LogMessage) obj;
                        return "add recommendation " + logMessage.getStr1() + ", active " + logMessage.getBool1();
                    }
                };
                LogBuffer logBuffer = mediaCarouselControllerLogger2.buffer;
                LogMessage obtain = logBuffer.obtain("MediaCarouselCtlrLog", logLevel, mediaCarouselControllerLogger$logRecommendationLoaded$2, null);
                LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
                logMessageImpl.str1 = str;
                boolean z4 = smartspaceMediaData.isActive;
                logMessageImpl.bool1 = z4;
                logBuffer.commit(obtain);
                boolean z5 = false;
                if (z4) {
                    MediaDataManager mediaDataManager2 = mediaCarouselController.mediaManager;
                    boolean hasActiveMedia = mediaDataManager2.hasActiveMedia();
                    SystemClock systemClock3 = mediaCarouselController.systemClock;
                    int i2 = 5;
                    long j2 = smartspaceMediaData.headphoneConnectionTimeMillis;
                    if (!hasActiveMedia && mediaDataManager2.hasAnyMedia() && z) {
                        MediaPlayerData.INSTANCE.getClass();
                        int i3 = 0;
                        for (Object obj : MediaPlayerData.mediaPlayers.values()) {
                            int i4 = i3 + 1;
                            if (i3 < 0) {
                                CollectionsKt__CollectionsKt.throwIndexOverflow();
                                throw null;
                            }
                            MediaControlPanel mediaControlPanel = (MediaControlPanel) obj;
                            if (mediaControlPanel.mRecommendationViewHolder == null) {
                                int i5 = mediaControlPanel.mUid;
                                ((SystemClockImpl) systemClock3).getClass();
                                int hash = SmallHash.hash(i5 + ((int) System.currentTimeMillis()));
                                mediaControlPanel.mSmartspaceId = hash;
                                mediaControlPanel.mIsImpressed = z5;
                                ((SystemClockImpl) systemClock3).getClass();
                                j = j2;
                                int i6 = i3;
                                systemClock2 = systemClock3;
                                z3 = z5;
                                MediaCarouselController.logSmartspaceCardReported$default(mediaCarouselController, 759, hash, mediaControlPanel.mUid, new int[]{4, 2, i2}, 0, 0, i6, (int) (System.currentTimeMillis() - j2), 304);
                            } else {
                                j = j2;
                                systemClock2 = systemClock3;
                                z3 = z5;
                            }
                            systemClock3 = systemClock2;
                            i3 = i4;
                            z5 = z3;
                            j2 = j;
                            i2 = 5;
                        }
                    }
                    long j3 = j2;
                    SystemClock systemClock4 = systemClock3;
                    z2 = z5;
                    mediaCarouselController.addSmartspaceMediaRecommendations(str, smartspaceMediaData, z);
                    MediaPlayerData.INSTANCE.getClass();
                    MediaControlPanel mediaPlayer = MediaPlayerData.getMediaPlayer(str);
                    if (mediaPlayer != null) {
                        int mediaPlayerIndex = MediaPlayerData.getMediaPlayerIndex(str);
                        ((SystemClockImpl) systemClock4).getClass();
                        MediaCarouselController.logSmartspaceCardReported$default(mediaCarouselController, 759, mediaPlayer.mSmartspaceId, mediaPlayer.mUid, new int[]{4, 2, 5}, 0, 0, mediaPlayerIndex, (int) (System.currentTimeMillis() - j3), 304);
                    }
                    MediaCarouselScrollHandler mediaCarouselScrollHandler = mediaCarouselController.mediaCarouselScrollHandler;
                    if (mediaCarouselScrollHandler.visibleToUser && mediaCarouselScrollHandler.visibleMediaIndex == MediaPlayerData.getMediaPlayerIndex(str)) {
                        mediaCarouselController.logSmartspaceImpression(mediaCarouselScrollHandler.qsExpanded);
                    }
                } else {
                    z2 = false;
                    mediaCarouselController.mediaFlags.isPersistentSsCardEnabled();
                    onSmartspaceMediaDataRemoved(smartspaceMediaData.targetId, true);
                }
                MediaPlayerData.INSTANCE.getClass();
                MediaPlayerData.isSwipedAway = z2;
            }

            /* JADX WARN: Type inference failed for: r5v2, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
            @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager.Listener
            public final void onSmartspaceMediaDataRemoved(String str, boolean z) {
                MediaCarouselController mediaCarouselController = MediaCarouselController.this;
                MediaCarouselControllerLogger mediaCarouselControllerLogger2 = mediaCarouselController.debugLogger;
                mediaCarouselControllerLogger2.getClass();
                LogLevel logLevel = LogLevel.DEBUG;
                MediaCarouselControllerLogger$logRecommendationRemoved$2 mediaCarouselControllerLogger$logRecommendationRemoved$2 = new Function1() { // from class: com.android.systemui.media.controls.ui.controller.MediaCarouselControllerLogger$logRecommendationRemoved$2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        LogMessage logMessage = (LogMessage) obj;
                        return "removing recommendation " + logMessage.getStr1() + ", immediate=" + logMessage.getBool1();
                    }
                };
                LogBuffer logBuffer = mediaCarouselControllerLogger2.buffer;
                LogMessage obtain = logBuffer.obtain("MediaCarouselCtlrLog", logLevel, mediaCarouselControllerLogger$logRecommendationRemoved$2, null);
                LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
                logMessageImpl.str1 = str;
                logMessageImpl.bool1 = z;
                logBuffer.commit(obtain);
                if (!z && !mediaCarouselController.visualStabilityProvider.isReorderingAllowed) {
                    mediaCarouselController.keysNeedRemoval.add(str);
                    return;
                }
                MediaCarouselController.removePlayer$default(mediaCarouselController, str, false, 14);
                if (z) {
                    return;
                }
                mediaCarouselController.updateHostVisibility.invoke();
            }
        });
        viewGroup.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.android.systemui.media.controls.ui.controller.MediaCarouselController.6
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
                MediaCarouselController.this.updatePageIndicatorLocation();
            }
        });
        mediaHostStatesManager.callbacks.add(new MediaHostStatesManager.Callback() { // from class: com.android.systemui.media.controls.ui.controller.MediaCarouselController.7
            @Override // com.android.systemui.media.controls.ui.controller.MediaHostStatesManager.Callback
            public final void onHostStateChanged(int i2, MediaHostState mediaHostState) {
                MediaCarouselController mediaCarouselController = MediaCarouselController.this;
                Function0 function0 = mediaCarouselController.updateUserVisibility;
                if (function0 == null) {
                    function0 = null;
                }
                ((MediaHierarchyManager.AnonymousClass5) function0).invoke();
                int i3 = mediaCarouselController.desiredLocation;
                if (i2 == i3) {
                    mediaCarouselController.onDesiredLocationChanged(i3, mediaHostState, false, 200L, 0L);
                }
            }
        });
        keyguardUpdateMonitor.registerCallback(r2);
        RepeatWhenAttachedKt.repeatWhenAttached(mediaScrollView, EmptyCoroutineContext.INSTANCE, new AnonymousClass8(null));
        listenForLockscreenSettingChanges$frameworks__base__packages__SystemUI__android_common__SystemUI_core(coroutineScope);
        executor.execute(new Runnable() { // from class: com.android.systemui.media.controls.ui.controller.MediaCarouselController.9
            @Override // java.lang.Runnable
            public final void run() {
                MediaCarouselController.this.globalSettings.registerContentObserverSync(Settings.Global.getUriFor("animator_duration_scale"), false, MediaCarouselController.this.animationScaleObserver);
            }
        });
    }

    public static final void access$updateCarouselDimensions(MediaCarouselController mediaCarouselController) {
        mediaCarouselController.getClass();
        MediaPlayerData.INSTANCE.getClass();
        Iterator it = MediaPlayerData.mediaPlayers.values().iterator();
        int i = 0;
        int i2 = 0;
        while (it.hasNext()) {
            MediaViewController mediaViewController = ((MediaControlPanel) it.next()).mMediaViewController;
            int i3 = mediaViewController.currentWidth;
            TransitionLayout transitionLayout = mediaViewController.transitionLayout;
            float f = 0.0f;
            i = Math.max(i, i3 + ((int) (transitionLayout != null ? transitionLayout.getTranslationX() : 0.0f)));
            int i4 = mediaViewController.currentHeight;
            TransitionLayout transitionLayout2 = mediaViewController.transitionLayout;
            if (transitionLayout2 != null) {
                f = transitionLayout2.getTranslationY();
            }
            i2 = Math.max(i2, i4 + ((int) f));
        }
        if (i == mediaCarouselController.currentCarouselWidth && i2 == mediaCarouselController.currentCarouselHeight) {
            return;
        }
        mediaCarouselController.currentCarouselWidth = i;
        mediaCarouselController.currentCarouselHeight = i2;
        MediaCarouselScrollHandler mediaCarouselScrollHandler = mediaCarouselController.mediaCarouselScrollHandler;
        int i5 = mediaCarouselScrollHandler.carouselHeight;
        if (i2 != i5 || i != i5) {
            mediaCarouselScrollHandler.carouselWidth = i;
            mediaCarouselScrollHandler.carouselHeight = i2;
            mediaCarouselScrollHandler.scrollView.invalidateOutline();
        }
        mediaCarouselController.updatePageIndicatorLocation();
        mediaCarouselController.updatePageIndicatorAlpha();
    }

    public static void closeGuts(boolean z) {
        MediaPlayerData.INSTANCE.getClass();
        Iterator it = MediaPlayerData.mediaPlayers.values().iterator();
        while (it.hasNext()) {
            ((MediaControlPanel) it.next()).closeGuts(z);
        }
    }

    public static void logSmartspaceCardReported$default(MediaCarouselController mediaCarouselController, int i, int i2, int i3, int[] iArr, int i4, int i5, int i6, int i7, int i8) {
        int i9;
        int i10;
        int i11;
        int[] iArr2 = iArr;
        int i12 = 0;
        int i13 = (i8 & 16) != 0 ? 0 : i4;
        int i14 = (i8 & 32) != 0 ? 0 : i5;
        int i15 = (i8 & 64) != 0 ? mediaCarouselController.mediaCarouselScrollHandler.visibleMediaIndex : i6;
        int i16 = (i8 & 128) != 0 ? 0 : i7;
        boolean z = (i8 & 256) == 0;
        mediaCarouselController.getClass();
        MediaPlayerData.INSTANCE.getClass();
        if (MediaPlayerData.mediaPlayers.values().size() <= i15) {
            return;
        }
        MediaPlayerData.MediaSortKey mediaSortKey = (MediaPlayerData.MediaSortKey) CollectionsKt.elementAt(MediaPlayerData.visibleMediaPlayers.values(), i15);
        boolean z2 = mediaSortKey.isSsMediaRec;
        if (z2 || mediaCarouselController.mediaManager.isRecommendationActive() || MediaPlayerData.smartspaceMediaData != null) {
            int childCount = mediaCarouselController.mediaContent.getChildCount();
            int length = iArr2.length;
            while (i12 < length) {
                int i17 = iArr2[i12];
                int i18 = z ? -1 : i15;
                boolean z3 = mediaSortKey.isSsReactivated;
                boolean z4 = z;
                int i19 = length;
                int i20 = i12;
                boolean z5 = z2;
                MediaPlayerData.MediaSortKey mediaSortKey2 = mediaSortKey;
                int i21 = i16;
                int i22 = i13;
                int i23 = i15;
                int i24 = i14;
                SysUiStatsLog.write(i, i2, i17, i18, childCount, z2 ? 15 : z3 ? 43 : 31, i3, i13, i14, i21, null, null);
                if (MediaCarouselControllerKt.DEBUG) {
                    StringBuilder m = ValidatingOffsetMappingKt$$ExternalSyntheticOutline0.m(i, i2, "Log Smartspace card event id: ", " instance id: ", " surface: ");
                    ViewPager$$ExternalSyntheticOutline0.m(m, i17, " rank: ", i23, " cardinality: ");
                    m.append(childCount);
                    m.append(" isRecommendationCard: ");
                    m.append(z5);
                    m.append(" isSsReactivated: ");
                    m.append(z3);
                    m.append("uid: ");
                    m.append(i3);
                    m.append(" interactedSubcardRank: ");
                    i10 = i22;
                    i11 = i24;
                    ViewPager$$ExternalSyntheticOutline0.m(m, i10, " interactedSubcardCardinality: ", i11, " received_latency_millis: ");
                    i9 = i21;
                    LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0.m(m, i9, "MediaCarouselController");
                } else {
                    i9 = i21;
                    i10 = i22;
                    i11 = i24;
                }
                iArr2 = iArr;
                i15 = i23;
                z2 = z5;
                i16 = i9;
                i13 = i10;
                i14 = i11;
                length = i19;
                mediaSortKey = mediaSortKey2;
                i12 = i20 + 1;
                z = z4;
            }
        }
    }

    public static /* synthetic */ MediaControlPanel removePlayer$default(MediaCarouselController mediaCarouselController, String str, boolean z, int i) {
        boolean z2 = (i & 2) != 0;
        boolean z3 = (i & 4) != 0;
        if ((i & 8) != 0) {
            z = false;
        }
        return mediaCarouselController.removePlayer(str, z2, z3, z);
    }

    public final boolean addOrUpdatePlayer(String str, String str2, MediaData mediaData, boolean z) {
        boolean z2;
        MediaPlayerData mediaPlayerData;
        MediaControlPanel mediaPlayer;
        MediaPlayerData.MediaSortKey mediaSortKey;
        boolean z3;
        String str3;
        MediaCarouselScrollHandler mediaCarouselScrollHandler = this.mediaCarouselScrollHandler;
        boolean isEnabled = Trace.isEnabled();
        if (isEnabled) {
            TraceUtilsKt.beginSlice("MediaCarouselController#addOrUpdatePlayer");
        }
        try {
            mediaPlayerData = MediaPlayerData.INSTANCE;
            mediaPlayerData.getClass();
            if (str2 != null && !str2.equals(str)) {
                Map map = MediaPlayerData.mediaData;
                MediaPlayerData.MediaSortKey mediaSortKey2 = (MediaPlayerData.MediaSortKey) map.remove(str2);
                if (mediaSortKey2 != null) {
                    MediaControlPanel removeMediaPlayer = MediaPlayerData.removeMediaPlayer(str, false);
                    if (removeMediaPlayer != null) {
                        removeMediaPlayer.onDestroy();
                    }
                }
            }
            mediaPlayer = MediaPlayerData.getMediaPlayer(str);
            mediaSortKey = (MediaPlayerData.MediaSortKey) CollectionsKt.elementAtOrNull(MediaPlayerData.visibleMediaPlayers.values(), mediaCarouselScrollHandler.visibleMediaIndex);
        } catch (Throwable th) {
            th = th;
            z2 = isEnabled;
        }
        try {
            if (mediaPlayer == null) {
                MediaControlPanel mediaControlPanel = (MediaControlPanel) this.mediaControlPanelFactory.get();
                Set set = MediaViewHolder.controlsIds;
                View inflate = LayoutInflater.from(this.context).inflate(R.layout.media_session_view, this.mediaContent, false);
                inflate.setLayerType(2, null);
                inflate.setLayoutDirection(3);
                MediaViewHolder mediaViewHolder = new MediaViewHolder(inflate);
                mediaViewHolder.seekBar.setLayoutDirection(0);
                mediaControlPanel.attachPlayer(mediaViewHolder);
                MediaViewController mediaViewController = mediaControlPanel.mMediaViewController;
                z2 = isEnabled;
                z3 = true;
                mediaViewController.sizeChangedListener = new MediaCarouselController$addOrUpdatePlayer$1$1(0, this, MediaCarouselController.class, "updateCarouselDimensions", "updateCarouselDimensions()V", 0);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
                MediaViewHolder mediaViewHolder2 = mediaControlPanel.mMediaViewHolder;
                if (mediaViewHolder2 != null) {
                    mediaViewHolder2.player.setLayoutParams(layoutParams);
                }
                mediaControlPanel.bindPlayer(mediaData, str);
                boolean z4 = mediaCarouselScrollHandler.visibleToUser && this.currentlyExpanded;
                SeekBarViewModel seekBarViewModel = mediaControlPanel.mSeekBarViewModel;
                seekBarViewModel.bgExecutor.execute(new SeekBarViewModel$listening$1(seekBarViewModel, z4));
                mediaPlayerData.addMediaPlayer(str, mediaData, mediaControlPanel, z, this.debugLogger);
                updateViewControllerToState(mediaViewController, true);
                if (!(this.shouldScrollToKey && Intrinsics.areEqual(mediaData.isPlaying, Boolean.TRUE)) && (this.shouldScrollToKey || !mediaData.active)) {
                    this.needsReordering = true;
                } else {
                    reorderAllPlayers(mediaSortKey, str);
                }
            } else {
                z2 = isEnabled;
                z3 = true;
                mediaPlayer.bindPlayer(mediaData, str);
                mediaPlayerData.addMediaPlayer(str, mediaData, mediaPlayer, z, this.debugLogger);
                SmartspaceMediaData smartspaceMediaData = MediaPlayerData.smartspaceMediaData;
                if (smartspaceMediaData == null || (str3 = smartspaceMediaData.packageName) == null) {
                    str3 = new String();
                }
                if (!this.visualStabilityProvider.isReorderingAllowed && (!this.shouldScrollToKey || !Intrinsics.areEqual(mediaData.isPlaying, Boolean.TRUE) || !str3.equals(mediaData.packageName))) {
                    this.needsReordering = true;
                }
                reorderAllPlayers(mediaSortKey, str);
            }
            updatePageIndicator$1();
            mediaCarouselScrollHandler.onPlayersChanged();
            this.mediaFrame.setTag(R.id.requires_remeasuring, Boolean.TRUE);
            boolean z5 = mediaPlayer == null ? z3 : false;
            if (z2) {
                TraceUtilsKt.endSlice();
            }
            return z5;
        } catch (Throwable th2) {
            th = th2;
            if (z2) {
                TraceUtilsKt.endSlice();
            }
            throw th;
        }
    }

    public final void addSmartspaceMediaRecommendations(String str, SmartspaceMediaData smartspaceMediaData, boolean z) {
        MediaControlPanel removePlayer$default;
        int i = 1;
        boolean isEnabled = Trace.isEnabled();
        if (isEnabled) {
            TraceUtilsKt.beginSlice("MediaCarouselController#addSmartspaceMediaRecommendations");
        }
        try {
            if (MediaCarouselControllerKt.DEBUG) {
                Log.d("MediaCarouselController", "Updating smartspace target in carousel");
            }
            MediaPlayerData mediaPlayerData = MediaPlayerData.INSTANCE;
            mediaPlayerData.getClass();
            if (MediaPlayerData.getMediaPlayer(str) != null) {
                this.mediaFlags.isPersistentSsCardEnabled();
                Log.w("MediaCarouselController", "Skip adding smartspace target in carousel");
                if (isEnabled) {
                    TraceUtilsKt.endSlice();
                    return;
                }
                return;
            }
            String smartspaceMediaKey = MediaPlayerData.smartspaceMediaKey();
            if (smartspaceMediaKey != null && (removePlayer$default = removePlayer$default(this, smartspaceMediaKey, false, 12)) != null) {
                this.debugLogger.logPotentialMemoryLeak(smartspaceMediaKey);
                removePlayer$default.onDestroy();
            }
            MediaControlPanel mediaControlPanel = (MediaControlPanel) this.mediaControlPanelFactory.get();
            Set set = RecommendationViewHolder.controlsIds;
            View inflate = LayoutInflater.from(this.context).inflate(R.layout.media_recommendations, this.mediaContent, false);
            inflate.setLayoutDirection(3);
            RecommendationViewHolder recommendationViewHolder = new RecommendationViewHolder(inflate);
            mediaControlPanel.mRecommendationViewHolder = recommendationViewHolder;
            MediaViewController mediaViewController = mediaControlPanel.mMediaViewController;
            mediaViewController.attach(recommendationViewHolder.recommendations, MediaViewController.TYPE.RECOMMENDATION);
            mediaViewController.configurationChangeListener = new MediaControlPanel$$ExternalSyntheticLambda5(mediaControlPanel, i);
            mediaControlPanel.mRecommendationViewHolder.recommendations.setOnLongClickListener(new MediaControlPanel$$ExternalSyntheticLambda0(mediaControlPanel, i));
            mediaViewController.sizeChangedListener = new MediaCarouselController$addSmartspaceMediaRecommendations$1$3(0, this, MediaCarouselController.class, "updateCarouselDimensions", "updateCarouselDimensions()V", 0);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            RecommendationViewHolder recommendationViewHolder2 = mediaControlPanel.mRecommendationViewHolder;
            if (recommendationViewHolder2 != null) {
                recommendationViewHolder2.recommendations.setLayoutParams(layoutParams);
            }
            mediaControlPanel.bindRecommendation(smartspaceMediaData);
            MediaPlayerData.MediaSortKey mediaSortKey = (MediaPlayerData.MediaSortKey) CollectionsKt.elementAtOrNull(MediaPlayerData.visibleMediaPlayers.values(), this.mediaCarouselScrollHandler.visibleMediaIndex);
            mediaPlayerData.addMediaRecommendation(str, smartspaceMediaData, mediaControlPanel, z, this.debugLogger);
            updateViewControllerToState(mediaViewController, true);
            reorderAllPlayers(mediaSortKey, null);
            updatePageIndicator$1();
            this.mediaFrame.setTag(R.id.requires_remeasuring, Boolean.TRUE);
            TreeMap treeMap = MediaPlayerData.mediaPlayers;
            if (treeMap.values().size() != this.mediaContent.getChildCount()) {
                Log.e("MediaCarouselController", "Size of players list and number of views in carousel are out of sync. Players size is " + treeMap.values().size() + ". View count is " + this.mediaContent.getChildCount() + ".");
            }
            if (isEnabled) {
                TraceUtilsKt.endSlice();
            }
        } catch (Throwable th) {
            if (isEnabled) {
                TraceUtilsKt.endSlice();
            }
            throw th;
        }
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("keysNeedRemoval: " + this.keysNeedRemoval);
        MediaPlayerData.INSTANCE.getClass();
        printWriter.println("dataKeys: " + MediaPlayerData.mediaData.keySet());
        printWriter.println("orderedPlayerSortKeys: " + MediaPlayerData.mediaPlayers.keySet());
        printWriter.println("visiblePlayerSortKeys: " + MediaPlayerData.visibleMediaPlayers.values());
        printWriter.println("commonViewModels: " + this.commonViewModels);
        printWriter.println("smartspaceMediaData: " + MediaPlayerData.smartspaceMediaData);
        ActiveUnlockConfig$$ExternalSyntheticOutline0.m("shouldPrioritizeSs: ", MediaPlayerData.shouldPrioritizeSs, printWriter);
        printWriter.println("current size: " + this.currentCarouselWidth + " x " + this.currentCarouselHeight);
        int i = this.desiredLocation;
        StringBuilder sb = new StringBuilder("location: ");
        sb.append(i);
        printWriter.println(sb.toString());
        MediaHostState mediaHostState = this.desiredHostState;
        Float valueOf = mediaHostState != null ? Float.valueOf(mediaHostState.getExpansion()) : null;
        MediaHostState mediaHostState2 = this.desiredHostState;
        printWriter.println("state: " + valueOf + ", only active " + (mediaHostState2 != null ? Boolean.valueOf(mediaHostState2.getShowsOnlyActiveMedia()) : null));
        ActiveUnlockConfig$$ExternalSyntheticOutline0.m("isSwipedAway: ", MediaPlayerData.isSwipedAway, printWriter);
        ActiveUnlockConfig$$ExternalSyntheticOutline0.m("allowMediaPlayerOnLockScreen: ", this.allowMediaPlayerOnLockScreen, printWriter);
    }

    public final void inflateSettingsButton() {
        View inflate = LayoutInflater.from(this.context).inflate(R.layout.media_carousel_settings_button, this.mediaFrame, false);
        View view = this.settingsButton;
        if (view != null) {
            this.mediaFrame.removeView(view);
        }
        this.settingsButton = inflate;
        this.mediaFrame.addView(inflate);
        MediaCarouselScrollHandler mediaCarouselScrollHandler = this.mediaCarouselScrollHandler;
        mediaCarouselScrollHandler.settingsButton = inflate;
        Resources resources = inflate.getResources();
        View view2 = mediaCarouselScrollHandler.settingsButton;
        if (view2 == null) {
            view2 = null;
        }
        mediaCarouselScrollHandler.cornerRadius = resources.getDimensionPixelSize(Utils.getThemeAttr(android.R.attr.dialogCornerRadius, view2.getContext()));
        mediaCarouselScrollHandler.updateSettingsPresentation();
        mediaCarouselScrollHandler.scrollView.invalidateOutline();
        View view3 = this.settingsButton;
        (view3 != null ? view3 : null).setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.media.controls.ui.controller.MediaCarouselController$inflateSettingsButton$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view4) {
                MediaCarouselController.this.logger.logger.log(MediaUiEvent.OPEN_SETTINGS_CAROUSEL);
                MediaCarouselController.this.activityStarter.startActivity(MediaCarouselControllerKt.settingsIntent, true);
            }
        });
    }

    public final Job listenForAnyStateToGoneKeyguardTransition$frameworks__base__packages__SystemUI__android_common__SystemUI_core(CoroutineScope coroutineScope) {
        return BuildersKt.launch$default(coroutineScope, null, null, new MediaCarouselController$listenForAnyStateToGoneKeyguardTransition$1(this, null), 3);
    }

    public final Job listenForAnyStateToLockscreenTransition$frameworks__base__packages__SystemUI__android_common__SystemUI_core(CoroutineScope coroutineScope) {
        return BuildersKt.launch$default(coroutineScope, null, null, new MediaCarouselController$listenForAnyStateToLockscreenTransition$1(this, null), 3);
    }

    public final Job listenForLockscreenSettingChanges$frameworks__base__packages__SystemUI__android_common__SystemUI_core(CoroutineScope coroutineScope) {
        return BuildersKt.launch$default(coroutineScope, null, null, new MediaCarouselController$listenForLockscreenSettingChanges$1(this, null), 3);
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x006e, code lost:
    
        if (r4 == (-1)) goto L25;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void logSmartspaceImpression(boolean r15) {
        /*
            r14 = this;
            com.android.systemui.media.controls.ui.view.MediaCarouselScrollHandler r0 = r14.mediaCarouselScrollHandler
            int r0 = r0.visibleMediaIndex
            com.android.systemui.media.controls.ui.controller.MediaPlayerData r1 = com.android.systemui.media.controls.ui.controller.MediaPlayerData.INSTANCE
            r1.getClass()
            java.util.TreeMap r1 = com.android.systemui.media.controls.ui.controller.MediaPlayerData.mediaPlayers
            java.util.Collection r2 = r1.values()
            int r2 = r2.size()
            if (r2 <= r0) goto L92
            java.util.LinkedHashMap r2 = com.android.systemui.media.controls.ui.controller.MediaPlayerData.visibleMediaPlayers
            java.util.Collection r2 = r2.values()
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            java.lang.Object r0 = kotlin.collections.CollectionsKt.elementAt(r2, r0)
            java.lang.Object r0 = r1.get(r0)
            com.android.systemui.media.controls.ui.controller.MediaControlPanel r0 = (com.android.systemui.media.controls.ui.controller.MediaControlPanel) r0
            com.android.systemui.media.controls.shared.model.SmartspaceMediaData r2 = com.android.systemui.media.controls.ui.controller.MediaPlayerData.smartspaceMediaData
            r3 = 1
            if (r2 == 0) goto L32
            boolean r2 = r2.isActive
            if (r2 == 0) goto L32
        L30:
            r2 = r3
            goto L71
        L32:
            java.util.Set r1 = r1.entrySet()
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.Iterator r1 = r1.iterator()
            r2 = 0
            r4 = r2
        L3e:
            boolean r5 = r1.hasNext()
            r6 = -1
            if (r5 == 0) goto L6d
            java.lang.Object r5 = r1.next()
            int r7 = r4 + 1
            if (r4 < 0) goto L68
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            java.lang.Object r8 = r5.getKey()
            com.android.systemui.media.controls.ui.controller.MediaPlayerData$MediaSortKey r8 = (com.android.systemui.media.controls.ui.controller.MediaPlayerData.MediaSortKey) r8
            boolean r8 = r8.isSsMediaRec
            if (r8 != 0) goto L66
            java.lang.Object r5 = r5.getKey()
            com.android.systemui.media.controls.ui.controller.MediaPlayerData$MediaSortKey r5 = (com.android.systemui.media.controls.ui.controller.MediaPlayerData.MediaSortKey) r5
            com.android.systemui.media.controls.shared.model.MediaData r5 = r5.data
            boolean r5 = r5.active
            if (r5 == 0) goto L66
            goto L6e
        L66:
            r4 = r7
            goto L3e
        L68:
            kotlin.collections.CollectionsKt__CollectionsKt.throwIndexOverflow()
            r14 = 0
            throw r14
        L6d:
            r4 = r6
        L6e:
            if (r4 == r6) goto L71
            goto L30
        L71:
            if (r2 != 0) goto L76
            if (r15 != 0) goto L76
            return
        L76:
            if (r0 == 0) goto L92
            int r6 = r0.mSmartspaceId
            int r7 = r0.mUid
            int r15 = r0.getSurfaceForSmartspaceLogging()
            int[] r8 = new int[]{r15}
            r11 = 0
            r12 = 0
            r5 = 800(0x320, float:1.121E-42)
            r9 = 0
            r10 = 0
            r13 = 496(0x1f0, float:6.95E-43)
            r4 = r14
            logSmartspaceCardReported$default(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
            r0.mIsImpressed = r3
        L92:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.media.controls.ui.controller.MediaCarouselController.logSmartspaceImpression(boolean):void");
    }

    public final Unit onDesiredLocationChanged(final int i, MediaHostState mediaHostState, boolean z, long j, long j2) {
        Unit unit;
        TransitionViewState obtainViewState;
        boolean isEnabled = Trace.isEnabled();
        if (isEnabled) {
            TraceUtilsKt.beginSlice("MediaCarouselController#onDesiredLocationChanged");
        }
        if (mediaHostState != null) {
            try {
                if (this.desiredLocation != i) {
                    this.bgExecutor.execute(new Runnable() { // from class: com.android.systemui.media.controls.ui.controller.MediaCarouselController$onDesiredLocationChanged$1$1$1
                        @Override // java.lang.Runnable
                        public final void run() {
                            MediaUiEvent mediaUiEvent;
                            MediaUiEventLogger mediaUiEventLogger = MediaCarouselController.this.logger;
                            int i2 = i;
                            mediaUiEventLogger.getClass();
                            if (i2 == 0) {
                                mediaUiEvent = MediaUiEvent.MEDIA_CAROUSEL_LOCATION_QS;
                            } else if (i2 == 1) {
                                mediaUiEvent = MediaUiEvent.MEDIA_CAROUSEL_LOCATION_QQS;
                            } else if (i2 == 2) {
                                mediaUiEvent = MediaUiEvent.MEDIA_CAROUSEL_LOCATION_LOCKSCREEN;
                            } else if (i2 == 3) {
                                mediaUiEvent = MediaUiEvent.MEDIA_CAROUSEL_LOCATION_DREAM;
                            } else {
                                if (i2 != 4) {
                                    throw new IllegalArgumentException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i2, "Unknown media carousel location "));
                                }
                                mediaUiEvent = MediaUiEvent.MEDIA_CAROUSEL_LOCATION_COMMUNAL;
                            }
                            mediaUiEventLogger.logger.log(mediaUiEvent);
                        }
                    });
                }
                this.desiredLocation = i;
                this.desiredHostState = mediaHostState;
                boolean z2 = mediaHostState.getExpansion() > 0.0f;
                boolean z3 = this.currentlyExpanded;
                MediaCarouselScrollHandler mediaCarouselScrollHandler = this.mediaCarouselScrollHandler;
                if (z3 != z2) {
                    this.currentlyExpanded = z2;
                    updateSeekbarListening(mediaCarouselScrollHandler.visibleToUser);
                }
                boolean z4 = (this.currentlyExpanded || this.mediaManager.hasActiveMediaOrRecommendation() || !mediaHostState.getShowsOnlyActiveMedia()) ? false : true;
                MediaPlayerData.INSTANCE.getClass();
                for (MediaControlPanel mediaControlPanel : MediaPlayerData.mediaPlayers.values()) {
                    if (z) {
                        MediaViewController mediaViewController = mediaControlPanel.mMediaViewController;
                        mediaViewController.animateNextStateChange = true;
                        mediaViewController.animationDuration = j;
                        mediaViewController.animationDelay = j2;
                    }
                    if (z4 && mediaControlPanel.mMediaViewController.isGutsVisible) {
                        mediaControlPanel.closeGuts(!z);
                    }
                    MediaViewController mediaViewController2 = mediaControlPanel.mMediaViewController;
                    MediaHostState mediaHostState2 = (MediaHostState) mediaViewController2.mediaHostStatesManager.mediaHostStates.get(Integer.valueOf(i));
                    if (mediaHostState2 == null) {
                        obtainViewState = null;
                    } else {
                        obtainViewState = mediaViewController2.obtainViewState(mediaHostState2, false);
                        if (obtainViewState != null) {
                            TransitionViewState transitionViewState = mediaViewController2.tmpState;
                            mediaViewController2.updateViewStateSize(obtainViewState, i, transitionViewState);
                            obtainViewState = transitionViewState;
                        }
                    }
                    if (obtainViewState != null) {
                        mediaViewController2.layoutController.setMeasureState(obtainViewState);
                    }
                }
                mediaCarouselScrollHandler.showsSettingsButton = !mediaHostState.getShowsOnlyActiveMedia();
                mediaCarouselScrollHandler.falsingProtectionNeeded = mediaHostState.getFalsingProtectionNeeded();
                boolean visible = mediaHostState.getVisible();
                if (visible != this.playersVisible) {
                    this.playersVisible = visible;
                    if (visible) {
                        mediaCarouselScrollHandler.resetTranslation(false);
                    }
                }
                updateCarouselSize();
                unit = Unit.INSTANCE;
            } catch (Throwable th) {
                if (isEnabled) {
                    TraceUtilsKt.endSlice();
                }
                throw th;
            }
        } else {
            unit = null;
        }
        if (isEnabled) {
            TraceUtilsKt.endSlice();
        }
        return unit;
    }

    public final void onSwipeToDismiss() {
        MediaPlayerData.INSTANCE.getClass();
        int i = 0;
        for (Object obj : MediaPlayerData.mediaPlayers.values()) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            MediaControlPanel mediaControlPanel = (MediaControlPanel) obj;
            if (mediaControlPanel.mIsImpressed) {
                logSmartspaceCardReported$default(this, 761, mediaControlPanel.mSmartspaceId, mediaControlPanel.mUid, new int[]{mediaControlPanel.getSurfaceForSmartspaceLogging()}, 0, 0, i, 0, 176);
                mediaControlPanel.mIsImpressed = false;
            }
            i = i2;
        }
        MediaPlayerData.INSTANCE.getClass();
        MediaPlayerData.isSwipedAway = true;
        this.logger.logger.log(MediaUiEvent.DISMISS_SWIPE);
        this.mediaManager.onSwipeToDismiss();
    }

    public final MediaControlPanel removePlayer(String str, boolean z, boolean z2, boolean z3) {
        SmartspaceMediaData smartspaceMediaData;
        MediaPlayerData.INSTANCE.getClass();
        if (str.equals(MediaPlayerData.smartspaceMediaKey()) && (smartspaceMediaData = MediaPlayerData.smartspaceMediaData) != null) {
            this.logger.logger.logWithInstanceId(MediaUiEvent.MEDIA_RECOMMENDATION_REMOVED, 0, smartspaceMediaData.packageName, smartspaceMediaData.instanceId);
        }
        MediaControlPanel removeMediaPlayer = MediaPlayerData.removeMediaPlayer(str, z || z2);
        if (removeMediaPlayer == null) {
            return null;
        }
        MediaViewHolder mediaViewHolder = removeMediaPlayer.mMediaViewHolder;
        TransitionLayout transitionLayout = mediaViewHolder != null ? mediaViewHolder.player : null;
        MediaCarouselScrollHandler mediaCarouselScrollHandler = this.mediaCarouselScrollHandler;
        int indexOfChild = mediaCarouselScrollHandler.mediaContent.indexOfChild(transitionLayout);
        int i = mediaCarouselScrollHandler.visibleMediaIndex;
        boolean z4 = true;
        boolean z5 = indexOfChild <= i;
        if (z5) {
            mediaCarouselScrollHandler.visibleMediaIndex = Math.max(0, i - 1);
        }
        MediaScrollView mediaScrollView = mediaCarouselScrollHandler.scrollView;
        if (!mediaScrollView.isLayoutRtl() || mediaCarouselScrollHandler.visibleMediaIndex == 0) {
            z4 = z5;
        } else if (z5) {
            z4 = false;
        }
        if (z4) {
            mediaScrollView.setScrollX(Math.max(mediaScrollView.getScrollX() - mediaCarouselScrollHandler.playerWidthPlusPadding, 0));
        }
        ViewGroup viewGroup = this.mediaContent;
        MediaViewHolder mediaViewHolder2 = removeMediaPlayer.mMediaViewHolder;
        viewGroup.removeView(mediaViewHolder2 != null ? mediaViewHolder2.player : null);
        ViewGroup viewGroup2 = this.mediaContent;
        RecommendationViewHolder recommendationViewHolder = removeMediaPlayer.mRecommendationViewHolder;
        viewGroup2.removeView(recommendationViewHolder != null ? recommendationViewHolder.recommendations : null);
        removeMediaPlayer.onDestroy();
        mediaCarouselScrollHandler.onPlayersChanged();
        updatePageIndicator$1();
        MediaDataManager mediaDataManager = this.mediaManager;
        if (z) {
            mediaDataManager.dismissMediaData(str, 0L, z3);
        }
        if (!z2) {
            return removeMediaPlayer;
        }
        mediaDataManager.dismissSmartspaceRecommendation(0L, str);
        return removeMediaPlayer;
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x00af, code lost:
    
        r0.scrollToPlayer(r3, r8);
        r1 = kotlin.Unit.INSTANCE;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void reorderAllPlayers(com.android.systemui.media.controls.ui.controller.MediaPlayerData.MediaSortKey r7, java.lang.String r8) {
        /*
            Method dump skipped, instructions count: 254
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.media.controls.ui.controller.MediaCarouselController.reorderAllPlayers(com.android.systemui.media.controls.ui.controller.MediaPlayerData$MediaSortKey, java.lang.String):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x00a4, code lost:
    
        if (r0 == r1) goto L41;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void setCurrentState(int r4, int r5, float r6, boolean r7) {
        /*
            r3 = this;
            int r0 = r3.currentStartLocation
            if (r4 != r0) goto L10
            int r0 = r3.currentEndLocation
            if (r5 != r0) goto L10
            float r0 = r3.currentTransitionProgress
            int r0 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r0 != 0) goto L10
            if (r7 == 0) goto Lb2
        L10:
            r3.currentStartLocation = r4
            r3.currentEndLocation = r5
            r3.currentTransitionProgress = r6
            com.android.systemui.media.controls.ui.controller.MediaPlayerData r4 = com.android.systemui.media.controls.ui.controller.MediaPlayerData.INSTANCE
            r4.getClass()
            java.util.TreeMap r4 = com.android.systemui.media.controls.ui.controller.MediaPlayerData.mediaPlayers
            java.util.Collection r4 = r4.values()
            java.util.Iterator r4 = r4.iterator()
        L25:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L37
            java.lang.Object r5 = r4.next()
            com.android.systemui.media.controls.ui.controller.MediaControlPanel r5 = (com.android.systemui.media.controls.ui.controller.MediaControlPanel) r5
            com.android.systemui.media.controls.ui.controller.MediaViewController r5 = r5.mMediaViewController
            r3.updateViewControllerToState(r5, r7)
            goto L25
        L37:
            com.android.systemui.media.controls.ui.controller.MediaHostStatesManager r4 = r3.mediaHostStatesManager
            java.util.Map r4 = r4.mediaHostStates
            int r5 = r3.currentEndLocation
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            java.lang.Object r5 = r4.get(r5)
            com.android.systemui.media.controls.ui.view.MediaHostState r5 = (com.android.systemui.media.controls.ui.view.MediaHostState) r5
            r6 = 1
            if (r5 == 0) goto L4f
            boolean r5 = r5.getShowsOnlyActiveMedia()
            goto L50
        L4f:
            r5 = r6
        L50:
            int r7 = r3.currentStartLocation
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            java.lang.Object r7 = r4.get(r7)
            com.android.systemui.media.controls.ui.view.MediaHostState r7 = (com.android.systemui.media.controls.ui.view.MediaHostState) r7
            if (r7 == 0) goto L63
            boolean r7 = r7.getShowsOnlyActiveMedia()
            goto L64
        L63:
            r7 = r5
        L64:
            int r0 = r3.currentStartLocation
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.Object r0 = r4.get(r0)
            com.android.systemui.media.controls.ui.view.MediaHostState r0 = (com.android.systemui.media.controls.ui.view.MediaHostState) r0
            r1 = 0
            if (r0 == 0) goto L78
            boolean r0 = r0.getDisablePagination()
            goto L79
        L78:
            r0 = r1
        L79:
            int r2 = r3.currentEndLocation
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            java.lang.Object r4 = r4.get(r2)
            com.android.systemui.media.controls.ui.view.MediaHostState r4 = (com.android.systemui.media.controls.ui.view.MediaHostState) r4
            if (r4 == 0) goto L8b
            boolean r1 = r4.getDisablePagination()
        L8b:
            boolean r4 = r3.currentlyShowingOnlyActive
            if (r4 != r5) goto La6
            boolean r4 = r3.currentlyDisablePagination
            if (r4 != r1) goto La6
            float r4 = r3.currentTransitionProgress
            r2 = 1065353216(0x3f800000, float:1.0)
            int r2 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r2 != 0) goto L9c
            goto Laf
        L9c:
            r2 = 0
            int r4 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r4 != 0) goto La2
            goto Laf
        La2:
            if (r7 != r5) goto La6
            if (r0 == r1) goto Laf
        La6:
            r3.currentlyShowingOnlyActive = r5
            r3.currentlyDisablePagination = r1
            com.android.systemui.media.controls.ui.view.MediaCarouselScrollHandler r4 = r3.mediaCarouselScrollHandler
            r4.resetTranslation(r6)
        Laf:
            r3.updatePageIndicatorAlpha()
        Lb2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.media.controls.ui.controller.MediaCarouselController.setCurrentState(int, int, float, boolean):void");
    }

    public final void updateCarouselSize() {
        MeasurementInput measurementInput;
        MeasurementInput measurementInput2;
        MeasurementInput measurementInput3;
        MeasurementInput measurementInput4;
        MediaHostState mediaHostState = this.desiredHostState;
        int size = (mediaHostState == null || (measurementInput4 = mediaHostState.getMeasurementInput()) == null) ? 0 : View.MeasureSpec.getSize(measurementInput4.widthMeasureSpec);
        MediaHostState mediaHostState2 = this.desiredHostState;
        int size2 = (mediaHostState2 == null || (measurementInput3 = mediaHostState2.getMeasurementInput()) == null) ? 0 : View.MeasureSpec.getSize(measurementInput3.heightMeasureSpec);
        if ((size == this.carouselMeasureWidth || size == 0) && (size2 == this.carouselMeasureHeight || size2 == 0)) {
            return;
        }
        this.carouselMeasureWidth = size;
        this.carouselMeasureHeight = size2;
        int dimensionPixelSize = this.context.getResources().getDimensionPixelSize(R.dimen.qs_media_padding) + size;
        MediaHostState mediaHostState3 = this.desiredHostState;
        int i = (mediaHostState3 == null || (measurementInput2 = mediaHostState3.getMeasurementInput()) == null) ? 0 : measurementInput2.widthMeasureSpec;
        MediaHostState mediaHostState4 = this.desiredHostState;
        int i2 = (mediaHostState4 == null || (measurementInput = mediaHostState4.getMeasurementInput()) == null) ? 0 : measurementInput.heightMeasureSpec;
        MediaScrollView mediaScrollView = this.mediaCarousel;
        mediaScrollView.measure(i, i2);
        mediaScrollView.layout(0, 0, size, mediaScrollView.getMeasuredHeight());
        MediaCarouselScrollHandler mediaCarouselScrollHandler = this.mediaCarouselScrollHandler;
        mediaCarouselScrollHandler.playerWidthPlusPadding = dimensionPixelSize;
        int i3 = mediaCarouselScrollHandler.visibleMediaIndex * dimensionPixelSize;
        int i4 = mediaCarouselScrollHandler.scrollIntoCurrentMedia;
        int i5 = i4 > dimensionPixelSize ? (dimensionPixelSize - (i4 - dimensionPixelSize)) + i3 : i3 + i4;
        MediaScrollView mediaScrollView2 = mediaCarouselScrollHandler.scrollView;
        if (mediaScrollView2.isLayoutRtl()) {
            ViewGroup viewGroup = mediaScrollView2.contentContainer;
            if (viewGroup == null) {
                viewGroup = null;
            }
            i5 = (viewGroup.getWidth() - mediaScrollView2.getWidth()) - i5;
        }
        mediaScrollView2.setScrollX(i5);
    }

    public final void updatePageIndicator$1() {
        int childCount = this.mediaContent.getChildCount();
        PageIndicator pageIndicator = this.pageIndicator;
        pageIndicator.setNumPages(childCount);
        if (childCount == 1) {
            pageIndicator.setLocation(0.0f);
        }
        updatePageIndicatorAlpha();
    }

    public final void updatePageIndicatorAlpha() {
        Map map = this.mediaHostStatesManager.mediaHostStates;
        MediaHostState mediaHostState = (MediaHostState) map.get(Integer.valueOf(this.currentEndLocation));
        boolean visible = mediaHostState != null ? mediaHostState.getVisible() : false;
        MediaHostState mediaHostState2 = (MediaHostState) map.get(Integer.valueOf(this.currentStartLocation));
        boolean visible2 = mediaHostState2 != null ? mediaHostState2.getVisible() : false;
        float f = 1.0f;
        float f2 = visible2 ? 1.0f : 0.0f;
        MediaHostState mediaHostState3 = (MediaHostState) map.get(Integer.valueOf(this.currentEndLocation));
        float squishFraction = mediaHostState3 != null ? mediaHostState3.getSquishFraction() : 1.0f;
        float f3 = visible ? 1.0f : 0.0f;
        PageIndicator pageIndicator = this.pageIndicator;
        float translationY = (pageIndicator.getTranslationY() + pageIndicator.getHeight()) / this.mediaCarousel.getMeasuredHeight();
        float interpolation = TRANSFORM_BEZIER.getInterpolation(MathUtils.constrain((squishFraction - translationY) / (1.0f - translationY), 0.0f, 1.0f)) * f3;
        if (!visible || !visible2) {
            float f4 = this.currentTransitionProgress;
            if (!visible) {
                f4 = 1.0f - f4;
            }
            f = MathUtils.lerp(f2, interpolation, MathUtils.constrain(MathUtils.map(0.95f, 1.0f, 0.0f, 1.0f, f4), 0.0f, 1.0f));
        }
        pageIndicator.setAlpha(f);
    }

    public final void updatePageIndicatorLocation() {
        int i;
        int width;
        boolean z = this.isRtl;
        PageIndicator pageIndicator = this.pageIndicator;
        if (z) {
            i = pageIndicator.getWidth();
            width = this.currentCarouselWidth;
        } else {
            i = this.currentCarouselWidth;
            width = pageIndicator.getWidth();
        }
        pageIndicator.setTranslationX(((i - width) / 2.0f) + this.mediaCarouselScrollHandler.contentTranslation);
        pageIndicator.setTranslationY((this.mediaCarousel.getMeasuredHeight() - pageIndicator.getHeight()) - ((ViewGroup.MarginLayoutParams) pageIndicator.getLayoutParams()).bottomMargin);
    }

    public final void updatePlayers(boolean z) {
        ColorStateList valueOf = ColorStateList.valueOf(this.context.getColor(R.color.media_paging_indicator));
        PageIndicator pageIndicator = this.pageIndicator;
        if (!valueOf.equals(pageIndicator.mTint)) {
            pageIndicator.mTint = valueOf;
            int childCount = pageIndicator.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = pageIndicator.getChildAt(i);
                if (childAt instanceof ImageView) {
                    ((ImageView) childAt).setImageTintList(pageIndicator.mTint);
                }
            }
        }
        MediaPlayerData.INSTANCE.getClass();
        MediaPlayerData.MediaSortKey mediaSortKey = (MediaPlayerData.MediaSortKey) CollectionsKt.elementAtOrNull(MediaPlayerData.visibleMediaPlayers.values(), this.mediaCarouselScrollHandler.visibleMediaIndex);
        Set<Map.Entry> entrySet = MediaPlayerData.mediaData.entrySet();
        ArrayList<Triple> arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(entrySet, 10));
        for (Map.Entry entry : entrySet) {
            arrayList.add(new Triple(entry.getKey(), ((MediaPlayerData.MediaSortKey) entry.getValue()).data, Boolean.valueOf(((MediaPlayerData.MediaSortKey) entry.getValue()).isSsMediaRec)));
        }
        for (Triple triple : arrayList) {
            String str = (String) triple.component1();
            MediaData mediaData = (MediaData) triple.component2();
            if (((Boolean) triple.component3()).booleanValue()) {
                MediaPlayerData.INSTANCE.getClass();
                SmartspaceMediaData smartspaceMediaData = MediaPlayerData.smartspaceMediaData;
                removePlayer$default(this, str, false, 8);
                if (smartspaceMediaData != null) {
                    addSmartspaceMediaRecommendations(smartspaceMediaData.targetId, smartspaceMediaData, MediaPlayerData.shouldPrioritizeSs);
                }
            } else {
                MediaPlayerData.INSTANCE.getClass();
                MediaPlayerData.MediaSortKey mediaSortKey2 = (MediaPlayerData.MediaSortKey) MediaPlayerData.mediaData.get(str);
                boolean z2 = mediaSortKey2 != null ? mediaSortKey2.isSsReactivated : false;
                if (z) {
                    removePlayer$default(this, str, false, 8);
                }
                addOrUpdatePlayer(str, null, mediaData, z2);
            }
            if (z) {
                reorderAllPlayers(mediaSortKey, null);
            }
        }
    }

    public final void updateSeekbarListening(boolean z) {
        MediaPlayerData.INSTANCE.getClass();
        for (MediaControlPanel mediaControlPanel : MediaPlayerData.mediaPlayers.values()) {
            boolean z2 = z && this.currentlyExpanded;
            SeekBarViewModel seekBarViewModel = mediaControlPanel.mSeekBarViewModel;
            seekBarViewModel.bgExecutor.execute(new SeekBarViewModel$listening$1(seekBarViewModel, z2));
        }
    }

    public final void updateViewControllerToState(MediaViewController mediaViewController, boolean z) {
        mediaViewController.setCurrentState(this.currentStartLocation, this.currentEndLocation, this.currentTransitionProgress, z, false);
    }

    public static /* synthetic */ void getCurrentEndLocation$annotations() {
    }

    public static /* synthetic */ void getCurrentlyExpanded$annotations() {
    }

    public static /* synthetic */ void getMediaCarousel$annotations() {
    }

    public static /* synthetic */ void getPageIndicator$annotations() {
    }

    public static /* synthetic */ void getSettingsButton$annotations() {
    }
}
