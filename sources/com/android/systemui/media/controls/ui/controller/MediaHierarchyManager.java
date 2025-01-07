package com.android.systemui.media.controls.ui.controller;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Handler;
import android.os.Trace;
import android.provider.Settings;
import android.util.MathUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import androidx.compose.foundation.text.input.internal.AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0;
import com.android.app.animation.Interpolators;
import com.android.app.tracing.TraceUtilsKt;
import com.android.systemui.communal.ui.viewmodel.CommunalTransitionViewModel;
import com.android.systemui.dreams.DreamOverlayStateController;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.media.controls.domain.pipeline.MediaDataManager;
import com.android.systemui.media.controls.ui.view.MediaCarouselScrollHandler;
import com.android.systemui.media.controls.ui.view.MediaHost;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.shade.domain.interactor.ShadeInteractorImpl;
import com.android.systemui.statusbar.CrossFadeHelper;
import com.android.systemui.statusbar.StatusBarStateControllerImpl;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.phone.KeyguardBypassController;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.statusbar.policy.SplitShadeStateControllerImpl;
import com.android.systemui.util.animation.UniqueObjectHostView;
import com.android.systemui.util.settings.SecureSettings;
import com.android.wm.shell.R;
import java.util.Collection;
import java.util.Iterator;
import kotlin.KotlinNothingValueException;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.AdaptedFunctionReference;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaHierarchyManager {
    public float animationCrossFadeProgress;
    public boolean animationPending;
    public float animationStartAlpha;
    public float animationStartCrossFadeProgress;
    public final ValueAnimator animator;
    public final KeyguardBypassController bypassController;
    public float carouselAlpha;
    public boolean collapsingShadeFromQS;
    public final Context context;
    public int currentAttachmentLocation;
    public int desiredLocation;
    public int distanceForFullShadeTransition;
    public boolean dozeAnimationRunning;
    public boolean dreamOverlayActive;
    public final DreamOverlayStateController dreamOverlayStateController;
    public float fullShadeTransitionProgress;
    public boolean fullyAwake;
    public boolean goingToSleep;
    public boolean inSplitShade;
    public boolean isAnyShadeFullyExpanded;
    public boolean isCommunalShowing;
    public boolean isCrossFadeAnimatorRunning;
    public boolean isPrimaryBouncerShowing;
    public final KeyguardInteractor keyguardInteractor;
    public final KeyguardStateController keyguardStateController;
    public final StatusBarKeyguardViewManager keyguardViewController;
    public final MediaViewLogger logger;
    public final MediaCarouselController mediaCarouselController;
    public final MediaHost[] mediaHosts;
    public final MediaDataManager mediaManager;
    public boolean onCommunalDreamingAndShadeExpanding;
    public boolean onCommunalNotDreaming;
    public int previousLocation;
    public boolean qsExpanded;
    public float qsExpansion;
    public ViewGroupOverlay rootOverlay;
    public View rootView;
    public final SecureSettings secureSettings;
    public boolean skipQqsOnExpansion;
    public final SplitShadeStateControllerImpl splitShadeStateController;
    public final MediaHierarchyManager$startAnimation$1 startAnimation;
    public final SysuiStatusBarStateController statusBarStateController;
    public int statusbarState;
    public boolean allowMediaPlayerOnLockScreen = true;
    public final Uri lockScreenMediaPlayerUri = Settings.Secure.getUriFor("media_controls_lock_screen");
    public final Rect currentBounds = new Rect();
    public final Rect animationStartBounds = new Rect();
    public final Rect animationStartClipping = new Rect();
    public Rect currentClipping = new Rect();
    public Rect targetClipping = new Rect();
    public int crossFadeAnimationStartLocation = -1;
    public int crossFadeAnimationEndLocation = -1;
    public Rect targetBounds = new Rect();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.media.controls.ui.controller.MediaHierarchyManager$10, reason: invalid class name */
    final class AnonymousClass10 extends SuspendLambda implements Function2 {
        final /* synthetic */ ShadeInteractor $shadeInteractor;
        int label;
        final /* synthetic */ MediaHierarchyManager this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass10(ShadeInteractor shadeInteractor, MediaHierarchyManager mediaHierarchyManager, Continuation continuation) {
            super(2, continuation);
            this.$shadeInteractor = shadeInteractor;
            this.this$0 = mediaHierarchyManager;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass10(this.$shadeInteractor, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            ((AnonymousClass10) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            return CoroutineSingletons.COROUTINE_SUSPENDED;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                StateFlow shadeExpansion = ((ShadeInteractorImpl) this.$shadeInteractor).baseShadeInteractor.getShadeExpansion();
                AnonymousClass7.AnonymousClass1 anonymousClass1 = new AnonymousClass7.AnonymousClass1(this.this$0, 1);
                this.label = 1;
                if (shadeExpansion.collect(anonymousClass1, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            throw new KotlinNothingValueException();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.media.controls.ui.controller.MediaHierarchyManager$11, reason: invalid class name */
    final class AnonymousClass11 extends SuspendLambda implements Function2 {
        final /* synthetic */ CommunalTransitionViewModel $communalTransitionViewModel;
        final /* synthetic */ ShadeInteractor $shadeInteractor;
        int label;
        final /* synthetic */ MediaHierarchyManager this$0;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.media.controls.ui.controller.MediaHierarchyManager$11$1, reason: invalid class name */
        final class AnonymousClass1 extends SuspendLambda implements Function2 {
            /* synthetic */ float F$0;
            int label;

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(2, continuation);
                anonymousClass1.F$0 = ((Number) obj).floatValue();
                return anonymousClass1;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass1) create(Float.valueOf(((Number) obj).floatValue()), (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return Boolean.valueOf(this.F$0 < 0.4f);
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.media.controls.ui.controller.MediaHierarchyManager$11$3, reason: invalid class name */
        final /* synthetic */ class AnonymousClass3 extends AdaptedFunctionReference implements Function4 {
            public static final AnonymousClass3 INSTANCE = new AnonymousClass3();

            public AnonymousClass3() {
                super(4, Triple.class, "<init>", "<init>(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V", 4);
            }

            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                Boolean bool = (Boolean) obj;
                bool.booleanValue();
                Boolean bool2 = (Boolean) obj2;
                bool2.booleanValue();
                Boolean bool3 = (Boolean) obj3;
                bool3.booleanValue();
                return new Triple(bool, bool2, bool3);
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.media.controls.ui.controller.MediaHierarchyManager$11$4, reason: invalid class name */
        final class AnonymousClass4 extends SuspendLambda implements Function2 {
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ MediaHierarchyManager this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass4(MediaHierarchyManager mediaHierarchyManager, Continuation continuation) {
                super(2, continuation);
                this.this$0 = mediaHierarchyManager;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                AnonymousClass4 anonymousClass4 = new AnonymousClass4(this.this$0, continuation);
                anonymousClass4.L$0 = obj;
                return anonymousClass4;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                AnonymousClass4 anonymousClass4 = (AnonymousClass4) create((Triple) obj, (Continuation) obj2);
                Unit unit = Unit.INSTANCE;
                anonymousClass4.invokeSuspend(unit);
                return unit;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                Triple triple = (Triple) this.L$0;
                boolean booleanValue = ((Boolean) triple.component1()).booleanValue();
                boolean booleanValue2 = ((Boolean) triple.component2()).booleanValue();
                boolean booleanValue3 = ((Boolean) triple.component3()).booleanValue();
                MediaHierarchyManager mediaHierarchyManager = this.this$0;
                mediaHierarchyManager.isCommunalShowing = booleanValue;
                boolean z = false;
                mediaHierarchyManager.onCommunalDreamingAndShadeExpanding = booleanValue && booleanValue2 && booleanValue3;
                if (booleanValue && !booleanValue2) {
                    z = true;
                }
                mediaHierarchyManager.onCommunalNotDreaming = z;
                MediaHierarchyManager.updateDesiredLocation$default(mediaHierarchyManager, true, 2);
                this.this$0.updateUserVisibility();
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass11(CommunalTransitionViewModel communalTransitionViewModel, MediaHierarchyManager mediaHierarchyManager, ShadeInteractor shadeInteractor, Continuation continuation) {
            super(2, continuation);
            this.$communalTransitionViewModel = communalTransitionViewModel;
            this.this$0 = mediaHierarchyManager;
            this.$shadeInteractor = shadeInteractor;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass11(this.$communalTransitionViewModel, this.this$0, this.$shadeInteractor, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass11) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ReadonlyStateFlow readonlyStateFlow = this.$communalTransitionViewModel.isUmoOnCommunal;
                KeyguardInteractor keyguardInteractor = this.this$0.keyguardInteractor;
                FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 combine = FlowKt.combine(readonlyStateFlow, keyguardInteractor.isDreaming, FlowKt.distinctUntilChanged(FlowKt.mapLatest(new AnonymousClass1(2, null), ((ShadeInteractorImpl) this.$shadeInteractor).baseShadeInteractor.getShadeExpansion())), AnonymousClass3.INSTANCE);
                AnonymousClass4 anonymousClass4 = new AnonymousClass4(this.this$0, null);
                this.label = 1;
                if (FlowKt.collectLatest(combine, anonymousClass4, this) == coroutineSingletons) {
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

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.media.controls.ui.controller.MediaHierarchyManager$5, reason: invalid class name */
    final /* synthetic */ class AnonymousClass5 extends FunctionReferenceImpl implements Function0 {
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            ((MediaHierarchyManager) this.receiver).updateUserVisibility();
            return Unit.INSTANCE;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.media.controls.ui.controller.MediaHierarchyManager$7, reason: invalid class name */
    final class AnonymousClass7 extends SuspendLambda implements Function2 {
        final /* synthetic */ ShadeInteractor $shadeInteractor;
        int label;
        final /* synthetic */ MediaHierarchyManager this$0;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.media.controls.ui.controller.MediaHierarchyManager$7$1, reason: invalid class name */
        public final class AnonymousClass1 implements FlowCollector {
            public final /* synthetic */ int $r8$classId;
            public final /* synthetic */ MediaHierarchyManager this$0;

            public /* synthetic */ AnonymousClass1(MediaHierarchyManager mediaHierarchyManager, int i) {
                this.$r8$classId = i;
                this.this$0 = mediaHierarchyManager;
            }

            @Override // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(Object obj, Continuation continuation) {
                switch (this.$r8$classId) {
                    case 0:
                        boolean booleanValue = ((Boolean) obj).booleanValue();
                        MediaHierarchyManager mediaHierarchyManager = this.this$0;
                        mediaHierarchyManager.skipQqsOnExpansion = booleanValue;
                        MediaHierarchyManager.updateDesiredLocation$default(mediaHierarchyManager, false, 3);
                        break;
                    case 1:
                        float floatValue = ((Number) obj).floatValue();
                        if (floatValue >= 1.0f || floatValue <= 0.0f) {
                            this.this$0.setTransitionToFullShadeAmount(floatValue);
                        }
                        break;
                    case 2:
                        boolean booleanValue2 = ((Boolean) obj).booleanValue();
                        MediaHierarchyManager mediaHierarchyManager2 = this.this$0;
                        mediaHierarchyManager2.isAnyShadeFullyExpanded = booleanValue2;
                        mediaHierarchyManager2.updateUserVisibility();
                        break;
                    default:
                        boolean booleanValue3 = ((Boolean) obj).booleanValue();
                        MediaHierarchyManager mediaHierarchyManager3 = this.this$0;
                        mediaHierarchyManager3.isPrimaryBouncerShowing = booleanValue3;
                        mediaHierarchyManager3.updateUserVisibility();
                        break;
                }
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass7(ShadeInteractor shadeInteractor, MediaHierarchyManager mediaHierarchyManager, Continuation continuation) {
            super(2, continuation);
            this.$shadeInteractor = shadeInteractor;
            this.this$0 = mediaHierarchyManager;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass7(this.$shadeInteractor, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass7) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow isQsBypassingShade = ((ShadeInteractorImpl) this.$shadeInteractor).baseShadeInteractor.isQsBypassingShade();
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, 0);
                this.label = 1;
                if (isQsBypassingShade.collect(anonymousClass1, this) == coroutineSingletons) {
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

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.media.controls.ui.controller.MediaHierarchyManager$8, reason: invalid class name */
    final class AnonymousClass8 extends SuspendLambda implements Function2 {
        final /* synthetic */ ShadeInteractor $shadeInteractor;
        int label;
        final /* synthetic */ MediaHierarchyManager this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass8(ShadeInteractor shadeInteractor, MediaHierarchyManager mediaHierarchyManager, Continuation continuation) {
            super(2, continuation);
            this.$shadeInteractor = shadeInteractor;
            this.this$0 = mediaHierarchyManager;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass8(this.$shadeInteractor, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            ((AnonymousClass8) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            return CoroutineSingletons.COROUTINE_SUSPENDED;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i != 0) {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                throw AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0.m(obj);
            }
            ResultKt.throwOnFailure(obj);
            ReadonlyStateFlow readonlyStateFlow = ((ShadeInteractorImpl) this.$shadeInteractor).isAnyFullyExpanded;
            AnonymousClass7.AnonymousClass1 anonymousClass1 = new AnonymousClass7.AnonymousClass1(this.this$0, 2);
            this.label = 1;
            readonlyStateFlow.collect(anonymousClass1, this);
            return coroutineSingletons;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.media.controls.ui.controller.MediaHierarchyManager$9, reason: invalid class name */
    final class AnonymousClass9 extends SuspendLambda implements Function2 {
        int label;

        public AnonymousClass9(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return MediaHierarchyManager.this.new AnonymousClass9(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            ((AnonymousClass9) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            return CoroutineSingletons.COROUTINE_SUSPENDED;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i != 0) {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                throw AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0.m(obj);
            }
            ResultKt.throwOnFailure(obj);
            MediaHierarchyManager mediaHierarchyManager = MediaHierarchyManager.this;
            KeyguardInteractor keyguardInteractor = mediaHierarchyManager.keyguardInteractor;
            AnonymousClass7.AnonymousClass1 anonymousClass1 = new AnonymousClass7.AnonymousClass1(mediaHierarchyManager, 3);
            this.label = 1;
            keyguardInteractor.primaryBouncerShowing.collect(anonymousClass1, this);
            return coroutineSingletons;
        }
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [com.android.systemui.media.controls.ui.controller.MediaHierarchyManager$6, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r5v5, types: [com.android.systemui.media.controls.ui.controller.MediaHierarchyManager$startAnimation$1] */
    public MediaHierarchyManager(Context context, SysuiStatusBarStateController sysuiStatusBarStateController, KeyguardStateController keyguardStateController, KeyguardBypassController keyguardBypassController, MediaCarouselController mediaCarouselController, MediaDataManager mediaDataManager, StatusBarKeyguardViewManager statusBarKeyguardViewManager, DreamOverlayStateController dreamOverlayStateController, KeyguardInteractor keyguardInteractor, CommunalTransitionViewModel communalTransitionViewModel, ConfigurationController configurationController, WakefulnessLifecycle wakefulnessLifecycle, ShadeInteractor shadeInteractor, SecureSettings secureSettings, final Handler handler, CoroutineScope coroutineScope, SplitShadeStateControllerImpl splitShadeStateControllerImpl, MediaViewLogger mediaViewLogger) {
        this.context = context;
        this.statusBarStateController = sysuiStatusBarStateController;
        this.keyguardStateController = keyguardStateController;
        this.bypassController = keyguardBypassController;
        this.mediaCarouselController = mediaCarouselController;
        this.mediaManager = mediaDataManager;
        this.keyguardViewController = statusBarKeyguardViewManager;
        this.dreamOverlayStateController = dreamOverlayStateController;
        this.keyguardInteractor = keyguardInteractor;
        this.secureSettings = secureSettings;
        this.splitShadeStateController = splitShadeStateControllerImpl;
        this.logger = mediaViewLogger;
        StatusBarStateControllerImpl statusBarStateControllerImpl = (StatusBarStateControllerImpl) sysuiStatusBarStateController;
        this.statusbarState = statusBarStateControllerImpl.mState;
        final ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.setInterpolator(Interpolators.FAST_OUT_SLOW_IN);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.media.controls.ui.controller.MediaHierarchyManager$animator$1$1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                float lerp;
                MediaHierarchyManager.this.updateTargetState();
                float animatedFraction = ofFloat.getAnimatedFraction();
                MediaHierarchyManager mediaHierarchyManager = MediaHierarchyManager.this;
                if (mediaHierarchyManager.isCrossFadeAnimatorRunning) {
                    mediaHierarchyManager.animationCrossFadeProgress = MathUtils.lerp(mediaHierarchyManager.animationStartCrossFadeProgress, 1.0f, ofFloat.getAnimatedFraction());
                    float f = MediaHierarchyManager.this.animationCrossFadeProgress;
                    float f2 = f < 0.5f ? 0.0f : 1.0f;
                    lerp = f <= 0.5f ? 1.0f - (f / 0.5f) : (f - 0.5f) / 0.5f;
                    animatedFraction = f2;
                } else {
                    lerp = MathUtils.lerp(mediaHierarchyManager.animationStartAlpha, 1.0f, ofFloat.getAnimatedFraction());
                }
                MediaHierarchyManager mediaHierarchyManager2 = MediaHierarchyManager.this;
                MediaHierarchyManager.interpolateBounds(mediaHierarchyManager2.animationStartBounds, mediaHierarchyManager2.targetBounds, animatedFraction, mediaHierarchyManager2.currentBounds);
                MediaHierarchyManager mediaHierarchyManager3 = MediaHierarchyManager.this;
                Rect rect = mediaHierarchyManager3.currentClipping;
                if (mediaHierarchyManager3.animationStartClipping.isEmpty()) {
                    rect.set(mediaHierarchyManager3.targetClipping);
                } else if (mediaHierarchyManager3.targetClipping.isEmpty()) {
                    rect.set(mediaHierarchyManager3.animationStartClipping);
                } else {
                    rect.setIntersect(mediaHierarchyManager3.animationStartClipping, mediaHierarchyManager3.targetClipping);
                }
                MediaHierarchyManager mediaHierarchyManager4 = MediaHierarchyManager.this;
                MediaHierarchyManager.applyState$default(mediaHierarchyManager4, mediaHierarchyManager4.currentBounds, lerp, mediaHierarchyManager4.currentClipping, 4);
            }
        });
        ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.media.controls.ui.controller.MediaHierarchyManager$animator$1$2
            public boolean cancelled;

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationCancel(Animator animator) {
                this.cancelled = true;
                MediaHierarchyManager mediaHierarchyManager = MediaHierarchyManager.this;
                mediaHierarchyManager.animationPending = false;
                View view = mediaHierarchyManager.rootView;
                if (view != null) {
                    view.removeCallbacks(mediaHierarchyManager.startAnimation);
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                MediaHierarchyManager mediaHierarchyManager = MediaHierarchyManager.this;
                mediaHierarchyManager.isCrossFadeAnimatorRunning = false;
                if (this.cancelled) {
                    return;
                }
                mediaHierarchyManager.applyTargetStateIfNotAnimating();
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationStart(Animator animator) {
                this.cancelled = false;
                MediaHierarchyManager.this.animationPending = false;
            }
        });
        this.animator = ofFloat;
        this.mediaHosts = new MediaHost[5];
        this.previousLocation = -1;
        this.desiredLocation = -1;
        this.currentAttachmentLocation = -1;
        this.startAnimation = new Runnable() { // from class: com.android.systemui.media.controls.ui.controller.MediaHierarchyManager$startAnimation$1
            @Override // java.lang.Runnable
            public final void run() {
                MediaHierarchyManager.this.animator.start();
            }
        };
        this.animationCrossFadeProgress = 1.0f;
        this.carouselAlpha = 1.0f;
        this.distanceForFullShadeTransition = context.getResources().getDimensionPixelSize(R.dimen.lockscreen_shade_media_transition_distance);
        this.inSplitShade = splitShadeStateControllerImpl.shouldUseSplitNotificationShade(context.getResources());
        ((ConfigurationControllerImpl) configurationController).addCallback(new ConfigurationController.ConfigurationListener() { // from class: com.android.systemui.media.controls.ui.controller.MediaHierarchyManager.1
            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onConfigChanged(Configuration configuration) {
                MediaHierarchyManager mediaHierarchyManager = MediaHierarchyManager.this;
                mediaHierarchyManager.distanceForFullShadeTransition = mediaHierarchyManager.context.getResources().getDimensionPixelSize(R.dimen.lockscreen_shade_media_transition_distance);
                mediaHierarchyManager.inSplitShade = mediaHierarchyManager.splitShadeStateController.shouldUseSplitNotificationShade(mediaHierarchyManager.context.getResources());
                mediaHierarchyManager.updateDesiredLocation(true, true);
            }
        });
        statusBarStateControllerImpl.addCallback(new StatusBarStateController.StateListener() { // from class: com.android.systemui.media.controls.ui.controller.MediaHierarchyManager.2
            @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
            public final void onDozeAmountChanged(float f, float f2) {
                boolean z = (f == 0.0f || f == 1.0f) ? false : true;
                MediaHierarchyManager mediaHierarchyManager = MediaHierarchyManager.this;
                if (mediaHierarchyManager.dozeAnimationRunning != z) {
                    mediaHierarchyManager.dozeAnimationRunning = z;
                    if (z) {
                        return;
                    }
                    MediaHierarchyManager.updateDesiredLocation$default(mediaHierarchyManager, false, 3);
                }
            }

            @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
            public final void onDozingChanged(boolean z) {
                MediaHierarchyManager mediaHierarchyManager = MediaHierarchyManager.this;
                if (z) {
                    MediaHierarchyManager.updateDesiredLocation$default(mediaHierarchyManager, false, 3);
                    mediaHierarchyManager.setQsExpanded(false);
                    MediaCarouselController.closeGuts(true);
                } else {
                    if (mediaHierarchyManager.dozeAnimationRunning) {
                        mediaHierarchyManager.dozeAnimationRunning = false;
                        MediaHierarchyManager.updateDesiredLocation$default(mediaHierarchyManager, false, 3);
                    }
                    if (mediaHierarchyManager.isLockScreenVisibleToUser()) {
                        mediaHierarchyManager.mediaCarouselController.logSmartspaceImpression(mediaHierarchyManager.qsExpanded);
                    }
                }
                mediaHierarchyManager.updateUserVisibility();
            }

            @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
            public final void onExpandedChanged(boolean z) {
                MediaHierarchyManager mediaHierarchyManager = MediaHierarchyManager.this;
                if (mediaHierarchyManager.isHomeScreenShadeVisibleToUser()) {
                    mediaHierarchyManager.mediaCarouselController.logSmartspaceImpression(mediaHierarchyManager.qsExpanded);
                }
                mediaHierarchyManager.updateUserVisibility();
            }

            @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
            public final void onStateChanged(int i) {
                MediaHierarchyManager mediaHierarchyManager = MediaHierarchyManager.this;
                mediaHierarchyManager.updateTargetState();
                if (i == 2 && mediaHierarchyManager.isLockScreenShadeVisibleToUser()) {
                    mediaHierarchyManager.mediaCarouselController.logSmartspaceImpression(mediaHierarchyManager.qsExpanded);
                }
                mediaHierarchyManager.updateUserVisibility();
            }

            @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
            public final void onStatePreChange(int i, int i2) {
                MediaHierarchyManager mediaHierarchyManager = MediaHierarchyManager.this;
                if (i2 == 2 && i == 1 && mediaHierarchyManager.fullShadeTransitionProgress < 1.0f) {
                    mediaHierarchyManager.setTransitionToFullShadeAmount(mediaHierarchyManager.distanceForFullShadeTransition);
                }
                mediaHierarchyManager.statusbarState = i2;
                MediaHierarchyManager.updateDesiredLocation$default(mediaHierarchyManager, false, 3);
            }
        });
        dreamOverlayStateController.addCallback(new DreamOverlayStateController.Callback() { // from class: com.android.systemui.media.controls.ui.controller.MediaHierarchyManager.3
            @Override // com.android.systemui.dreams.DreamOverlayStateController.Callback
            public final void onComplicationsChanged() {
                Collection complications = MediaHierarchyManager.this.dreamOverlayStateController.getComplications();
                if (complications.isEmpty()) {
                    return;
                }
                Iterator it = complications.iterator();
                while (it.hasNext()) {
                    if (it.next() != null) {
                        throw new ClassCastException();
                    }
                }
            }

            @Override // com.android.systemui.dreams.DreamOverlayStateController.Callback
            public final void onStateChanged() {
                MediaHierarchyManager mediaHierarchyManager = MediaHierarchyManager.this;
                boolean isOverlayActive = mediaHierarchyManager.dreamOverlayStateController.isOverlayActive();
                if (mediaHierarchyManager.dreamOverlayActive != isOverlayActive) {
                    mediaHierarchyManager.dreamOverlayActive = isOverlayActive;
                    MediaHierarchyManager.updateDesiredLocation$default(mediaHierarchyManager, true, 2);
                }
            }
        });
        wakefulnessLifecycle.mObservers.add(new WakefulnessLifecycle.Observer() { // from class: com.android.systemui.media.controls.ui.controller.MediaHierarchyManager.4
            @Override // com.android.systemui.keyguard.WakefulnessLifecycle.Observer
            public final void onFinishedGoingToSleep$1() {
                MediaHierarchyManager.access$setGoingToSleep(MediaHierarchyManager.this, false);
            }

            @Override // com.android.systemui.keyguard.WakefulnessLifecycle.Observer
            public final void onFinishedWakingUp() {
                MediaHierarchyManager mediaHierarchyManager = MediaHierarchyManager.this;
                MediaHierarchyManager.access$setGoingToSleep(mediaHierarchyManager, false);
                if (!mediaHierarchyManager.fullyAwake) {
                    mediaHierarchyManager.fullyAwake = true;
                    MediaHierarchyManager.updateDesiredLocation$default(mediaHierarchyManager, true, 2);
                }
            }

            @Override // com.android.systemui.keyguard.WakefulnessLifecycle.Observer
            public final void onStartedGoingToSleep() {
                MediaHierarchyManager mediaHierarchyManager = MediaHierarchyManager.this;
                MediaHierarchyManager.access$setGoingToSleep(mediaHierarchyManager, true);
                if (mediaHierarchyManager.fullyAwake) {
                    mediaHierarchyManager.fullyAwake = false;
                }
            }

            @Override // com.android.systemui.keyguard.WakefulnessLifecycle.Observer
            public final void onStartedWakingUp() {
                MediaHierarchyManager.access$setGoingToSleep(MediaHierarchyManager.this, false);
            }
        });
        mediaCarouselController.updateUserVisibility = new AnonymousClass5(0, this, MediaHierarchyManager.class, "updateUserVisibility", "updateUserVisibility()V", 0);
        ?? r0 = new Function0() { // from class: com.android.systemui.media.controls.ui.controller.MediaHierarchyManager.6
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                for (MediaHost mediaHost : MediaHierarchyManager.this.mediaHosts) {
                    if (mediaHost != null) {
                        mediaHost.updateViewVisibility();
                    }
                }
                return Unit.INSTANCE;
            }
        };
        mediaCarouselController.updateHostVisibility = r0;
        mediaCarouselController.mediaCarouselViewModel.updateHostVisibility = r0;
        BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass7(shadeInteractor, this, null), 3);
        BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass8(shadeInteractor, this, null), 3);
        BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass9(null), 3);
        BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass10(shadeInteractor, this, null), 3);
        secureSettings.registerContentObserverForUserSync("media_controls_lock_screen", new ContentObserver(handler) { // from class: com.android.systemui.media.controls.ui.controller.MediaHierarchyManager$settingsObserver$1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z, Uri uri) {
                if (Intrinsics.areEqual(uri, MediaHierarchyManager.this.lockScreenMediaPlayerUri)) {
                    MediaHierarchyManager mediaHierarchyManager = MediaHierarchyManager.this;
                    mediaHierarchyManager.allowMediaPlayerOnLockScreen = mediaHierarchyManager.secureSettings.getBoolForUser(-2, "media_controls_lock_screen", true);
                }
            }
        }, -1);
        BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass11(communalTransitionViewModel, this, shadeInteractor, null), 3);
    }

    public static final void access$setGoingToSleep(MediaHierarchyManager mediaHierarchyManager, boolean z) {
        if (mediaHierarchyManager.goingToSleep != z) {
            mediaHierarchyManager.goingToSleep = z;
            if (z) {
                return;
            }
            updateDesiredLocation$default(mediaHierarchyManager, false, 3);
        }
    }

    public static void applyState$default(MediaHierarchyManager mediaHierarchyManager, Rect rect, float f, Rect rect2, int i) {
        int i2;
        boolean z = true;
        boolean z2 = (i & 4) == 0;
        if ((i & 8) != 0) {
            rect2 = MediaHierarchyManagerKt.EMPTY_RECT;
        }
        MediaCarouselController mediaCarouselController = mediaHierarchyManager.mediaCarouselController;
        boolean isEnabled = Trace.isEnabled();
        if (isEnabled) {
            TraceUtilsKt.beginSlice("MediaHierarchyManager#applyState");
        }
        try {
            mediaHierarchyManager.currentBounds.set(rect);
            mediaHierarchyManager.currentClipping = rect2;
            float f2 = 1.0f;
            if (!mediaHierarchyManager.isCurrentlyFading()) {
                f = 1.0f;
            }
            if (mediaHierarchyManager.carouselAlpha != f) {
                mediaHierarchyManager.carouselAlpha = f;
                CrossFadeHelper.fadeIn((View) mediaHierarchyManager.mediaCarouselController.mediaFrame, f, false);
            }
            if (mediaHierarchyManager.isCurrentlyInGuidedTransformation() && !mediaHierarchyManager.isCurrentlyFading()) {
                z = false;
            }
            int i3 = z ? -1 : mediaHierarchyManager.previousLocation;
            if (!z) {
                f2 = mediaHierarchyManager.getTransformationProgress();
            }
            if (mediaHierarchyManager.isCrossFadeAnimatorRunning) {
                if (mediaHierarchyManager.animationCrossFadeProgress <= 0.5d && mediaHierarchyManager.previousLocation != -1) {
                    i2 = mediaHierarchyManager.crossFadeAnimationStartLocation;
                }
                i2 = mediaHierarchyManager.crossFadeAnimationEndLocation;
            } else {
                i2 = mediaHierarchyManager.desiredLocation;
            }
            mediaCarouselController.setCurrentState(i3, i2, f2, z2);
            mediaHierarchyManager.updateHostAttachment();
            if (mediaHierarchyManager.currentAttachmentLocation == -1000) {
                if (!mediaHierarchyManager.currentClipping.isEmpty()) {
                    mediaHierarchyManager.currentBounds.intersect(mediaHierarchyManager.currentClipping);
                }
                ViewGroup viewGroup = mediaCarouselController.mediaFrame;
                Rect rect3 = mediaHierarchyManager.currentBounds;
                viewGroup.setLeftTopRightBottom(rect3.left, rect3.top, rect3.right, rect3.bottom);
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

    public static Rect interpolateBounds(Rect rect, Rect rect2, float f, Rect rect3) {
        int lerp = (int) MathUtils.lerp(rect.left, rect2.left, f);
        int lerp2 = (int) MathUtils.lerp(rect.top, rect2.top, f);
        int lerp3 = (int) MathUtils.lerp(rect.right, rect2.right, f);
        int lerp4 = (int) MathUtils.lerp(rect.bottom, rect2.bottom, f);
        if (rect3 == null) {
            rect3 = new Rect();
        }
        rect3.set(lerp, lerp2, lerp3, lerp4);
        return rect3;
    }

    public static /* synthetic */ void updateDesiredLocation$default(MediaHierarchyManager mediaHierarchyManager, boolean z, int i) {
        if ((i & 1) != 0) {
            z = false;
        }
        mediaHierarchyManager.updateDesiredLocation(z, false);
    }

    public final void applyTargetStateIfNotAnimating() {
        if (this.animator.isRunning()) {
            return;
        }
        applyState$default(this, this.targetBounds, this.carouselAlpha, this.targetClipping, 4);
    }

    public final boolean areGuidedTransitionHostsVisible() {
        MediaHost host;
        MediaHost host2 = getHost(this.previousLocation);
        return host2 != null && host2.state.visible && (host = getHost(this.desiredLocation)) != null && host.state.visible;
    }

    public final int calculateLocation() {
        MediaHost host;
        if (this.goingToSleep || this.dozeAnimationRunning) {
            return this.desiredLocation;
        }
        boolean z = !this.bypassController.getBypassEnabled() && this.statusbarState == 1;
        int i = 4;
        if (!((this.onCommunalNotDreaming && this.qsExpansion == 0.0f) || this.onCommunalDreamingAndShadeExpanding)) {
            float f = this.qsExpansion;
            if (((f > 0.0f || this.inSplitShade) && !z) || ((f > 0.4f && z) || (z && this.inSplitShade && isTransitioningToFullShade()))) {
                i = 0;
            } else {
                if (!z || !isTransitioningToFullShade() || this.inSplitShade || this.fullShadeTransitionProgress <= 0.5f) {
                    if (!this.isCommunalShowing) {
                        if (z && this.allowMediaPlayerOnLockScreen) {
                            i = 2;
                        }
                    }
                }
                i = 1;
            }
        }
        if (i == 2 && (((host = getHost(i)) == null || !host.state.visible) && !((StatusBarStateControllerImpl) this.statusBarStateController).mIsDozing)) {
            return 0;
        }
        if (i == 2 && this.desiredLocation == 0 && this.collapsingShadeFromQS) {
            return 0;
        }
        if (i != 2 && this.desiredLocation == 2 && !this.fullyAwake) {
            return 2;
        }
        if (this.isCommunalShowing) {
            return i;
        }
        if (this.skipQqsOnExpansion) {
            return 0;
        }
        return i;
    }

    public final int calculateTransformationType() {
        if (isHubTransition()) {
            return 1;
        }
        if (isTransitioningToFullShade()) {
            return (this.inSplitShade && areGuidedTransitionHostsVisible()) ? 0 : 1;
        }
        int i = this.previousLocation;
        if ((i == 2 && this.desiredLocation == 0) || (i == 0 && this.desiredLocation == 2)) {
            return 1;
        }
        return (i == 2 && this.desiredLocation == 1) ? 1 : 0;
    }

    public final void cancelAnimationAndApplyDesiredState() {
        this.animator.cancel();
        MediaHost host = getHost(this.desiredLocation);
        if (host != null) {
            applyState$default(this, host.getCurrentBounds(), 1.0f, null, 8);
        }
    }

    public final Pair getAnimationParams(int i, int i2) {
        long j;
        long j2 = 0;
        if (i == 2 && i2 == 1) {
            if (this.statusbarState == 0) {
                KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) this.keyguardStateController;
                if (keyguardStateControllerImpl.mKeyguardFadingAway) {
                    j2 = keyguardStateControllerImpl.mKeyguardFadingAwayDelay;
                }
            }
            j = 224;
        } else {
            j = (i == 1 && i2 == 2) ? 464L : 200L;
        }
        return new Pair(Long.valueOf(j), Long.valueOf(j2));
    }

    public final MediaHost getHost(int i) {
        if (i < 0) {
            return null;
        }
        return this.mediaHosts[i];
    }

    public final float getQSTransformationProgress() {
        MediaHost host = getHost(this.desiredLocation);
        MediaHost host2 = getHost(this.previousLocation);
        if (host == null || host.location != 0 || this.inSplitShade || host2 == null || host2.location != 1) {
            return -1.0f;
        }
        if (host2.state.visible || this.statusbarState != 1) {
            return this.qsExpansion;
        }
        return -1.0f;
    }

    public final float getTransformationProgress() {
        if (!this.skipQqsOnExpansion && !isHubTransition()) {
            float qSTransformationProgress = getQSTransformationProgress();
            if (this.statusbarState != 1 && qSTransformationProgress >= 0.0f) {
                return qSTransformationProgress;
            }
            if (isTransitioningToFullShade()) {
                return this.fullShadeTransitionProgress;
            }
        }
        return -1.0f;
    }

    public final boolean isCurrentlyFading() {
        if (this.inSplitShade && isTransitioningToFullShade()) {
            return false;
        }
        if (isTransitioningToFullShade()) {
            return true;
        }
        return this.isCrossFadeAnimatorRunning;
    }

    public final boolean isCurrentlyInGuidedTransformation() {
        return this.previousLocation != -1 && this.desiredLocation != -1 && getTransformationProgress() >= 0.0f && (areGuidedTransitionHostsVisible() || !this.mediaManager.hasActiveMediaOrRecommendation());
    }

    public final boolean isHomeScreenShadeVisibleToUser() {
        SysuiStatusBarStateController sysuiStatusBarStateController = this.statusBarStateController;
        return !((StatusBarStateControllerImpl) sysuiStatusBarStateController).mIsDozing && ((StatusBarStateControllerImpl) sysuiStatusBarStateController).mState == 0 && ((StatusBarStateControllerImpl) sysuiStatusBarStateController).mIsExpanded;
    }

    public final boolean isHubTransition() {
        int i = this.desiredLocation;
        return i == 4 || (this.previousLocation == 4 && i == 0);
    }

    public final boolean isLockScreenShadeVisibleToUser() {
        SysuiStatusBarStateController sysuiStatusBarStateController = this.statusBarStateController;
        if (!((StatusBarStateControllerImpl) sysuiStatusBarStateController).mIsDozing && !this.keyguardViewController.isBouncerShowing()) {
            if (((StatusBarStateControllerImpl) sysuiStatusBarStateController).mState == 2) {
                return true;
            }
            if (((StatusBarStateControllerImpl) sysuiStatusBarStateController).mState == 1 && this.qsExpanded) {
                return true;
            }
        }
        return false;
    }

    public final boolean isLockScreenVisibleToUser() {
        SysuiStatusBarStateController sysuiStatusBarStateController = this.statusBarStateController;
        return !((StatusBarStateControllerImpl) sysuiStatusBarStateController).mIsDozing && !this.keyguardViewController.isBouncerShowing() && ((StatusBarStateControllerImpl) sysuiStatusBarStateController).mState == 1 && this.allowMediaPlayerOnLockScreen && ((StatusBarStateControllerImpl) sysuiStatusBarStateController).mIsExpanded && !this.qsExpanded;
    }

    public final boolean isTransitioningToFullShade() {
        return (this.fullShadeTransitionProgress == 0.0f || this.bypassController.getBypassEnabled() || this.statusbarState != 1) ? false : true;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0083 A[Catch: all -> 0x0033, TryCatch #0 {all -> 0x0033, blocks: (B:5:0x000b, B:9:0x0013, B:13:0x0025, B:15:0x002e, B:22:0x0038, B:24:0x0047, B:27:0x004d, B:30:0x0054, B:31:0x0073, B:34:0x007d, B:36:0x0083, B:39:0x008b, B:41:0x00a6, B:43:0x00d8, B:45:0x00dc, B:47:0x008f, B:49:0x0095, B:52:0x009c, B:55:0x0065, B:56:0x00e4, B:57:0x00ed, B:62:0x00f6), top: B:4:0x000b }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00d8 A[Catch: all -> 0x0033, TryCatch #0 {all -> 0x0033, blocks: (B:5:0x000b, B:9:0x0013, B:13:0x0025, B:15:0x002e, B:22:0x0038, B:24:0x0047, B:27:0x004d, B:30:0x0054, B:31:0x0073, B:34:0x007d, B:36:0x0083, B:39:0x008b, B:41:0x00a6, B:43:0x00d8, B:45:0x00dc, B:47:0x008f, B:49:0x0095, B:52:0x009c, B:55:0x0065, B:56:0x00e4, B:57:0x00ed, B:62:0x00f6), top: B:4:0x000b }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x007c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void performTransitionToNewLocation(boolean r7, boolean r8) {
        /*
            Method dump skipped, instructions count: 261
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.media.controls.ui.controller.MediaHierarchyManager.performTransitionToNewLocation(boolean, boolean):void");
    }

    public final UniqueObjectHostView register(MediaHost mediaHost) {
        final UniqueObjectHostView uniqueObjectHostView = new UniqueObjectHostView(this.context);
        uniqueObjectHostView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.android.systemui.media.controls.ui.controller.MediaHierarchyManager$createUniqueObjectHost$1
            @Override // android.view.View.OnAttachStateChangeListener
            public final void onViewAttachedToWindow(View view) {
                MediaHierarchyManager mediaHierarchyManager = MediaHierarchyManager.this;
                if (mediaHierarchyManager.rootOverlay == null) {
                    mediaHierarchyManager.rootView = uniqueObjectHostView.getViewRootImpl().getView();
                    MediaHierarchyManager mediaHierarchyManager2 = MediaHierarchyManager.this;
                    View view2 = mediaHierarchyManager2.rootView;
                    Intrinsics.checkNotNull(view2);
                    mediaHierarchyManager2.rootOverlay = (ViewGroupOverlay) view2.getOverlay();
                }
                uniqueObjectHostView.removeOnAttachStateChangeListener(this);
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public final void onViewDetachedFromWindow(View view) {
            }
        });
        mediaHost.hostView = uniqueObjectHostView;
        mediaHost.visibleChangedListeners.add(new Function1() { // from class: com.android.systemui.media.controls.ui.controller.MediaHierarchyManager$register$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ((Boolean) obj).getClass();
                MediaHierarchyManager.updateDesiredLocation$default(MediaHierarchyManager.this, true, 2);
                return Unit.INSTANCE;
            }
        });
        int i = mediaHost.location;
        this.mediaHosts[i] = mediaHost;
        if (i == this.desiredLocation) {
            this.desiredLocation = -1;
        }
        if (i == this.currentAttachmentLocation) {
            this.currentAttachmentLocation = -1;
        }
        updateDesiredLocation$default(this, false, 3);
        return uniqueObjectHostView;
    }

    public final void setQsExpanded(boolean z) {
        boolean z2 = this.qsExpanded;
        MediaCarouselController mediaCarouselController = this.mediaCarouselController;
        if (z2 != z) {
            this.qsExpanded = z;
            mediaCarouselController.mediaCarouselScrollHandler.qsExpanded = z;
        }
        if (z && (isLockScreenShadeVisibleToUser() || isHomeScreenShadeVisibleToUser())) {
            mediaCarouselController.logSmartspaceImpression(z);
        }
        updateUserVisibility();
    }

    public final void setTransitionToFullShadeAmount(float f) {
        float saturate = MathUtils.saturate(f / this.distanceForFullShadeTransition);
        if (this.fullShadeTransitionProgress == saturate) {
            return;
        }
        this.fullShadeTransitionProgress = saturate;
        if (this.bypassController.getBypassEnabled() || this.statusbarState != 1) {
            return;
        }
        updateDesiredLocation$default(this, isCurrentlyFading(), 2);
        if (saturate >= 0.0f) {
            updateTargetState();
            float f2 = this.fullShadeTransitionProgress;
            float f3 = f2 <= 0.5f ? 1.0f - (f2 / 0.5f) : (f2 - 0.5f) / 0.5f;
            if (this.carouselAlpha != f3) {
                this.carouselAlpha = f3;
                CrossFadeHelper.fadeIn((View) this.mediaCarouselController.mediaFrame, f3, false);
            }
            applyTargetStateIfNotAnimating();
        }
    }

    public final boolean shouldAnimateTransition(int i, int i2) {
        Object parent;
        if (isCurrentlyInGuidedTransformation() || this.skipQqsOnExpansion || isHubTransition()) {
            return false;
        }
        if (i2 == 2 && this.desiredLocation == 1 && this.statusbarState == 0) {
            return false;
        }
        if (i == 1 && i2 == 2 && (((StatusBarStateControllerImpl) this.statusBarStateController).mLeaveOpenOnKeyguardHide || this.statusbarState == 2)) {
            return true;
        }
        if (this.desiredLocation == 0 && i2 == 2 && this.statusbarState == 0) {
            return false;
        }
        if (this.statusbarState == 1 && (i == 2 || i2 == 2)) {
            return false;
        }
        View view = this.mediaCarouselController.mediaFrame;
        Rect rect = MediaHierarchyManagerKt.EMPTY_RECT;
        while (view.getVisibility() == 0 && view.getAlpha() != 0.0f && (parent = view.getParent()) != null) {
            if (!(parent instanceof View)) {
                break;
            }
            view = (View) parent;
        }
        if (!this.animator.isRunning() && !this.animationPending) {
            return false;
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0023, code lost:
    
        if (r5 != false) goto L50;
     */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void updateDesiredLocation(boolean r11, boolean r12) {
        /*
            r10 = this;
            boolean r0 = android.os.Trace.isEnabled()
            if (r0 == 0) goto Lb
            java.lang.String r1 = "MediaHierarchyManager#updateDesiredLocation"
            com.android.app.tracing.TraceUtilsKt.beginSlice(r1)
        Lb:
            int r3 = r10.calculateLocation()     // Catch: java.lang.Throwable -> L2c
            int r1 = r10.desiredLocation     // Catch: java.lang.Throwable -> L2c
            r2 = 1
            r4 = 0
            if (r3 != r1) goto L25
            if (r12 == 0) goto L97
            boolean r5 = r10.goingToSleep     // Catch: java.lang.Throwable -> L2c
            if (r5 != 0) goto L22
            boolean r5 = r10.dozeAnimationRunning     // Catch: java.lang.Throwable -> L2c
            if (r5 == 0) goto L20
            goto L22
        L20:
            r5 = r4
            goto L23
        L22:
            r5 = r2
        L23:
            if (r5 != 0) goto L97
        L25:
            if (r1 < 0) goto L2f
            if (r3 == r1) goto L2f
            r10.previousLocation = r1     // Catch: java.lang.Throwable -> L2c
            goto L4b
        L2c:
            r10 = move-exception
            goto L9d
        L2f:
            if (r12 == 0) goto L4b
            com.android.systemui.statusbar.phone.KeyguardBypassController r12 = r10.bypassController     // Catch: java.lang.Throwable -> L2c
            boolean r12 = r12.getBypassEnabled()     // Catch: java.lang.Throwable -> L2c
            if (r12 != 0) goto L3f
            int r12 = r10.statusbarState     // Catch: java.lang.Throwable -> L2c
            if (r12 != r2) goto L3f
            r12 = r2
            goto L40
        L3f:
            r12 = r4
        L40:
            if (r3 != 0) goto L4b
            int r1 = r10.previousLocation     // Catch: java.lang.Throwable -> L2c
            r5 = 2
            if (r1 != r5) goto L4b
            if (r12 != 0) goto L4b
            r10.previousLocation = r2     // Catch: java.lang.Throwable -> L2c
        L4b:
            int r12 = r10.desiredLocation     // Catch: java.lang.Throwable -> L2c
            r1 = -1
            if (r12 != r1) goto L52
            r12 = r2
            goto L53
        L52:
            r12 = r4
        L53:
            r10.desiredLocation = r3     // Catch: java.lang.Throwable -> L2c
            if (r11 != 0) goto L61
            int r11 = r10.previousLocation     // Catch: java.lang.Throwable -> L2c
            boolean r11 = r10.shouldAnimateTransition(r3, r11)     // Catch: java.lang.Throwable -> L2c
            if (r11 == 0) goto L61
            r11 = r2
            goto L62
        L61:
            r11 = r4
        L62:
            int r1 = r10.previousLocation     // Catch: java.lang.Throwable -> L2c
            kotlin.Pair r1 = r10.getAnimationParams(r1, r3)     // Catch: java.lang.Throwable -> L2c
            java.lang.Object r4 = r1.component1()     // Catch: java.lang.Throwable -> L2c
            java.lang.Number r4 = (java.lang.Number) r4     // Catch: java.lang.Throwable -> L2c
            long r6 = r4.longValue()     // Catch: java.lang.Throwable -> L2c
            java.lang.Object r1 = r1.component2()     // Catch: java.lang.Throwable -> L2c
            java.lang.Number r1 = (java.lang.Number) r1     // Catch: java.lang.Throwable -> L2c
            long r8 = r1.longValue()     // Catch: java.lang.Throwable -> L2c
            com.android.systemui.media.controls.ui.view.MediaHost r4 = r10.getHost(r3)     // Catch: java.lang.Throwable -> L2c
            int r1 = r10.calculateTransformationType()     // Catch: java.lang.Throwable -> L2c
            if (r1 != r2) goto L8e
            boolean r1 = r10.isCurrentlyInGuidedTransformation()     // Catch: java.lang.Throwable -> L2c
            if (r1 != 0) goto L8e
            if (r11 != 0) goto L94
        L8e:
            com.android.systemui.media.controls.ui.controller.MediaCarouselController r2 = r10.mediaCarouselController     // Catch: java.lang.Throwable -> L2c
            r5 = r11
            r2.onDesiredLocationChanged(r3, r4, r5, r6, r8)     // Catch: java.lang.Throwable -> L2c
        L94:
            r10.performTransitionToNewLocation(r12, r11)     // Catch: java.lang.Throwable -> L2c
        L97:
            if (r0 == 0) goto L9c
            com.android.app.tracing.TraceUtilsKt.endSlice()
        L9c:
            return
        L9d:
            if (r0 == 0) goto La2
            com.android.app.tracing.TraceUtilsKt.endSlice()
        La2:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.media.controls.ui.controller.MediaHierarchyManager.updateDesiredLocation(boolean, boolean):void");
    }

    public final void updateHostAttachment() {
        int i;
        MediaHost host;
        MediaHost host2;
        MediaCarouselController mediaCarouselController = this.mediaCarouselController;
        boolean isEnabled = Trace.isEnabled();
        if (isEnabled) {
            TraceUtilsKt.beginSlice("MediaHierarchyManager#updateHostAttachment");
        }
        try {
            if (this.isCrossFadeAnimatorRunning) {
                if (this.animationCrossFadeProgress <= 0.5d && this.previousLocation != -1) {
                    i = this.crossFadeAnimationStartLocation;
                }
                i = this.crossFadeAnimationEndLocation;
            } else {
                i = this.desiredLocation;
            }
            boolean z = false;
            boolean z2 = !isCurrentlyFading() && this.mediaManager.hasActiveMediaOrRecommendation();
            if (this.isCrossFadeAnimatorRunning && (host = getHost(i)) != null && host.state.visible && (host2 = getHost(i)) != null) {
                UniqueObjectHostView uniqueObjectHostView = host2.hostView;
                if (uniqueObjectHostView == null) {
                    uniqueObjectHostView = null;
                }
                if (uniqueObjectHostView != null && !uniqueObjectHostView.isShown() && i != this.desiredLocation) {
                    z2 = true;
                }
            }
            if (((isCurrentlyInGuidedTransformation() && getTransformationProgress() != 1.0f) || this.animator.isRunning() || this.animationPending) && this.rootOverlay != null && z2) {
                z = true;
            }
            if (z) {
                i = -1000;
            }
            if (this.currentAttachmentLocation != i) {
                this.currentAttachmentLocation = i;
                ViewGroup viewGroup = (ViewGroup) mediaCarouselController.mediaFrame.getParent();
                if (viewGroup != null) {
                    viewGroup.removeView(mediaCarouselController.mediaFrame);
                }
                if (z) {
                    ViewGroupOverlay viewGroupOverlay = this.rootOverlay;
                    Intrinsics.checkNotNull(viewGroupOverlay);
                    viewGroupOverlay.add(mediaCarouselController.mediaFrame);
                } else {
                    MediaHost host3 = getHost(i);
                    Intrinsics.checkNotNull(host3);
                    UniqueObjectHostView uniqueObjectHostView2 = host3.hostView;
                    if (uniqueObjectHostView2 == null) {
                        uniqueObjectHostView2 = null;
                    }
                    uniqueObjectHostView2.addView(mediaCarouselController.mediaFrame);
                }
                MediaViewLogger mediaViewLogger = this.logger;
                int i2 = this.currentAttachmentLocation;
                LogLevel logLevel = LogLevel.DEBUG;
                MediaViewLogger$logMediaHostAttachment$2 mediaViewLogger$logMediaHostAttachment$2 = new Function1() { // from class: com.android.systemui.media.controls.ui.controller.MediaViewLogger$logMediaHostAttachment$2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return AnnotationValue$1$$ExternalSyntheticOutline0.m(((LogMessage) obj).getInt1(), "Host (updateHostAttachment): ");
                    }
                };
                LogBuffer logBuffer = mediaViewLogger.buffer;
                LogMessage obtain = logBuffer.obtain("MediaView", logLevel, mediaViewLogger$logMediaHostAttachment$2, null);
                ((LogMessageImpl) obtain).int1 = i2;
                logBuffer.commit(obtain);
                if (this.isCrossFadeAnimatorRunning) {
                    mediaCarouselController.onDesiredLocationChanged(i, getHost(i), false, 200L, 0L);
                }
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

    public final void updateTargetState() {
        MediaHost host = getHost(this.previousLocation);
        MediaHost host2 = getHost(this.desiredLocation);
        if (!isCurrentlyInGuidedTransformation() || isCurrentlyFading() || host == null || host2 == null) {
            if (host2 != null) {
                this.targetBounds.set(host2.getCurrentBounds());
                this.targetClipping = host2.currentClipping;
                return;
            }
            return;
        }
        float transformationProgress = getTransformationProgress();
        if (!host2.state.visible) {
            host2 = host;
        } else if (host.state.visible) {
            host2 = host;
            host = host2;
        } else {
            host = host2;
        }
        this.targetBounds = interpolateBounds(host2.getCurrentBounds(), host.getCurrentBounds(), transformationProgress, null);
        this.targetClipping = host.currentClipping;
    }

    public final void updateUserVisibility() {
        boolean z = isLockScreenVisibleToUser() || isLockScreenShadeVisibleToUser() || isHomeScreenShadeVisibleToUser() || !(!this.isCommunalShowing || this.isPrimaryBouncerShowing || this.isAnyShadeFullyExpanded);
        boolean z2 = this.qsExpanded || this.mediaManager.hasActiveMediaOrRecommendation();
        MediaCarouselScrollHandler mediaCarouselScrollHandler = this.mediaCarouselController.mediaCarouselScrollHandler;
        boolean z3 = z && z2;
        if (mediaCarouselScrollHandler.visibleToUser != z3) {
            mediaCarouselScrollHandler.visibleToUser = z3;
            mediaCarouselScrollHandler.seekBarUpdateListener.invoke(Boolean.valueOf(z3));
            mediaCarouselScrollHandler.visibleStateLogger.log(String.valueOf(mediaCarouselScrollHandler.visibleToUser));
        }
    }
}
