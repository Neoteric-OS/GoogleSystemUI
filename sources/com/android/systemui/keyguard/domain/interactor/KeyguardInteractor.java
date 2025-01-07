package com.android.systemui.keyguard.domain.interactor;

import com.android.app.tracing.coroutines.CoroutineTracingKt;
import com.android.compose.animation.scene.SceneKey;
import com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl;
import com.android.systemui.common.shared.model.NotificationContainerBounds;
import com.android.systemui.common.ui.domain.interactor.ConfigurationInteractor;
import com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl;
import com.android.systemui.keyguard.shared.model.CameraLaunchSourceModel;
import com.android.systemui.keyguard.shared.model.CameraLaunchType;
import com.android.systemui.keyguard.shared.model.Edge;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.scene.shared.model.Scenes;
import com.android.systemui.shade.data.repository.ShadeRepository;
import com.android.systemui.shade.data.repository.ShadeRepositoryImpl;
import com.android.systemui.statusbar.notification.stack.domain.interactor.SharedNotificationContainerInteractor;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.util.kotlin.Utils;
import com.android.wm.shell.R;
import dagger.internal.DelegateFactory;
import dagger.internal.Provider;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$3;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SafeFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StartedEagerly;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;
import kotlinx.coroutines.flow.internal.ChannelLimitedFlowMerge;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardInteractor {
    public final StateFlowImpl _notificationPlaceholderBounds = StateFlowKt.MutableStateFlow(new NotificationContainerBounds());
    public final SafeFlow alternateBouncerShowing;
    public final ReadonlyStateFlow ambientIndicationVisible;
    public final Lazy animateDozingTransitions$delegate;
    public final ReadonlyStateFlow asleepKeyguardState;
    public final ReadonlyStateFlow biometricUnlockState;
    public final ReadonlyStateFlow clockShouldBeCentered;
    public final Flow dismissAlpha;
    public final Flow dozeAmount;
    public final StateFlow dozeTimeTick;
    public final Flow dozeTransitionModel;
    public final Provider fromGoneTransitionInteractor;
    public final Provider fromLockscreenTransitionInteractor;
    public final Provider fromOccludedTransitionInteractor;
    public final ReadonlyStateFlow isAbleToDream;
    public final ReadonlyStateFlow isActiveDreamLockscreenHosted;
    public final ReadonlyStateFlow isAodAvailable;
    public final ReadonlyStateFlow isDozing;
    public final ReadonlyStateFlow isDreaming;
    public final MutableStateFlow isDreamingAny;
    public final Flow isDreamingWithOverlay;
    public final Flow isEncryptedOrLockdown;
    public final MutableStateFlow isKeyguardDismissible;
    public final MutableStateFlow isKeyguardGoingAway;
    public final MutableStateFlow isKeyguardOccluded;
    public final MutableStateFlow isKeyguardShowing;
    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 isKeyguardVisible;
    public final KeyguardInteractor$special$$inlined$map$1 isPulsing;
    public final Lazy isSecureCameraActive$delegate;
    public final StateFlow keyguardAlpha;
    public final KeyguardTransitionInteractor keyguardTransitionInteractor;
    public final ReadonlyStateFlow keyguardTranslationY;
    public final ReadonlyStateFlow lastRootViewTapPosition;
    public final Lazy notificationContainerBounds$delegate;
    public final KeyguardInteractor$special$$inlined$map$2 onCameraLaunchDetected;
    public final ReadonlyStateFlow panelAlpha;
    public final ReadonlyStateFlow primaryBouncerShowing;
    public final KeyguardRepositoryImpl repository;
    public final ReadonlyStateFlow showDismissibleKeyguard;
    public final StateFlow statusBarState;
    public final Lazy topClippingBounds$delegate;

    public KeyguardInteractor(KeyguardRepositoryImpl keyguardRepositoryImpl, PowerInteractor powerInteractor, KeyguardBouncerRepositoryImpl keyguardBouncerRepositoryImpl, ConfigurationInteractor configurationInteractor, ShadeRepository shadeRepository, KeyguardTransitionInteractor keyguardTransitionInteractor, final DelegateFactory delegateFactory, Provider provider, Provider provider2, Provider provider3, final Provider provider4, final CoroutineScope coroutineScope) {
        this.repository = keyguardRepositoryImpl;
        this.keyguardTransitionInteractor = keyguardTransitionInteractor;
        this.fromGoneTransitionInteractor = provider;
        this.fromLockscreenTransitionInteractor = provider2;
        this.fromOccludedTransitionInteractor = provider3;
        this.notificationContainerBounds$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.keyguard.domain.interactor.KeyguardInteractor$notificationContainerBounds$2

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardInteractor$notificationContainerBounds$2$1, reason: invalid class name */
            final class AnonymousClass1 extends SuspendLambda implements Function5 {
                private /* synthetic */ Object L$0;
                /* synthetic */ Object L$1;
                /* synthetic */ Object L$2;
                /* synthetic */ boolean Z$0;
                int label;

                @Override // kotlin.jvm.functions.Function5
                public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
                    boolean booleanValue = ((Boolean) obj4).booleanValue();
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(5, (Continuation) obj5);
                    anonymousClass1.L$0 = (FlowCollector) obj;
                    anonymousClass1.L$1 = (NotificationContainerBounds) obj2;
                    anonymousClass1.L$2 = (SharedNotificationContainerInteractor.ConfigurationBasedDimensions) obj3;
                    anonymousClass1.Z$0 = booleanValue;
                    return anonymousClass1.invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                    int i = this.label;
                    Unit unit = Unit.INSTANCE;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        FlowCollector flowCollector = (FlowCollector) this.L$0;
                        NotificationContainerBounds notificationContainerBounds = (NotificationContainerBounds) this.L$1;
                        SharedNotificationContainerInteractor.ConfigurationBasedDimensions configurationBasedDimensions = (SharedNotificationContainerInteractor.ConfigurationBasedDimensions) this.L$2;
                        if (this.Z$0) {
                            return unit;
                        }
                        if (configurationBasedDimensions.useSplitShade) {
                            notificationContainerBounds = NotificationContainerBounds.copy$default(notificationContainerBounds, 0.0f, notificationContainerBounds.bottom - configurationBasedDimensions.keyguardSplitShadeTopMargin, false, 5);
                        }
                        this.L$0 = null;
                        this.L$1 = null;
                        this.label = 1;
                        if (flowCollector.emit(notificationContainerBounds, this) == coroutineSingletons) {
                            return coroutineSingletons;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return unit;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                StateFlowImpl stateFlowImpl = KeyguardInteractor.this._notificationPlaceholderBounds;
                Flow flow = ((SharedNotificationContainerInteractor) provider4.get()).configurationBasedDimensions;
                KeyguardTransitionInteractor keyguardTransitionInteractor2 = KeyguardInteractor.this.keyguardTransitionInteractor;
                Edge.Companion companion = Edge.Companion;
                Edge.StateToState stateToState = new Edge.StateToState(KeyguardState.LOCKSCREEN, KeyguardState.AOD);
                String str = KeyguardTransitionInteractor.TAG;
                return FlowKt.stateIn(new SafeFlow(new FlowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$3(new Flow[]{stateFlowImpl, flow, keyguardTransitionInteractor2.isInTransition(stateToState, null)}, null, new AnonymousClass1(5, null))), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), new NotificationContainerBounds());
            }
        });
        this.isDozing = keyguardRepositoryImpl.isDozing;
        this.dozeTimeTick = keyguardRepositoryImpl.dozeTimeTick;
        this.isAodAvailable = keyguardRepositoryImpl.isAodAvailable;
        this.dozeAmount = keyguardRepositoryImpl._preSceneLinearDozeAmount;
        Flow flow = keyguardRepositoryImpl.dozeTransitionModel;
        this.dozeTransitionModel = flow;
        this.isPulsing = new KeyguardInteractor$special$$inlined$map$1(flow, 0);
        Flow flow2 = keyguardRepositoryImpl.isDreamingWithOverlay;
        this.isDreamingWithOverlay = flow2;
        ChannelLimitedFlowMerge merge = FlowKt.merge(keyguardRepositoryImpl.isDreaming, flow2);
        StartedEagerly startedEagerly = SharingStarted.Companion.Eagerly;
        Boolean bool = Boolean.FALSE;
        this.isDreaming = FlowKt.stateIn(merge, coroutineScope, startedEagerly, bool);
        this.isDreamingAny = keyguardRepositoryImpl.isDreaming;
        this.isActiveDreamLockscreenHosted = keyguardRepositoryImpl.isActiveDreamLockscreenHosted;
        this.onCameraLaunchDetected = new KeyguardInteractor$special$$inlined$map$2(keyguardRepositoryImpl.onCameraLaunchDetected, 1);
        this.showDismissibleKeyguard = new ReadonlyStateFlow(keyguardRepositoryImpl.showDismissibleKeyguard);
        ReadonlyStateFlow stateIn = FlowKt.stateIn(FlowKt.transformLatest(flow, new KeyguardInteractor$special$$inlined$flatMapLatest$2(null, this, powerInteractor)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), bool);
        this.isAbleToDream = stateIn;
        StateFlowImpl stateFlowImpl = keyguardRepositoryImpl.isKeyguardShowing;
        this.isKeyguardShowing = stateFlowImpl;
        StateFlowImpl stateFlowImpl2 = keyguardRepositoryImpl.isKeyguardDismissible;
        this.isKeyguardDismissible = stateFlowImpl2;
        StateFlowImpl stateFlowImpl3 = keyguardRepositoryImpl.isKeyguardOccluded;
        this.isKeyguardOccluded = stateFlowImpl3;
        this.isKeyguardGoingAway = keyguardRepositoryImpl.isKeyguardGoingAway;
        this.topClippingBounds$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.keyguard.domain.interactor.KeyguardInteractor$topClippingBounds$2

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardInteractor$topClippingBounds$2$2, reason: invalid class name */
            final class AnonymousClass2 extends SuspendLambda implements Function2 {
                private /* synthetic */ Object L$0;
                int label;

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    AnonymousClass2 anonymousClass2 = new AnonymousClass2(2, continuation);
                    anonymousClass2.L$0 = obj;
                    return anonymousClass2;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ((AnonymousClass2) create((FlowCollector) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        FlowCollector flowCollector = (FlowCollector) this.L$0;
                        Boolean bool = Boolean.FALSE;
                        this.label = 1;
                        if (flowCollector.emit(bool, this) == coroutineSingletons) {
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
            /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardInteractor$topClippingBounds$2$3, reason: invalid class name */
            final class AnonymousClass3 extends SuspendLambda implements Function4 {
                private /* synthetic */ Object L$0;
                /* synthetic */ Object L$1;
                /* synthetic */ boolean Z$0;
                int label;

                @Override // kotlin.jvm.functions.Function4
                public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                    boolean booleanValue = ((Boolean) obj2).booleanValue();
                    AnonymousClass3 anonymousClass3 = new AnonymousClass3(4, (Continuation) obj4);
                    anonymousClass3.L$0 = (FlowCollector) obj;
                    anonymousClass3.Z$0 = booleanValue;
                    anonymousClass3.L$1 = (Integer) obj3;
                    return anonymousClass3.invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        FlowCollector flowCollector = (FlowCollector) this.L$0;
                        boolean z = this.Z$0;
                        Integer num = (Integer) this.L$1;
                        if (!z) {
                            this.L$0 = null;
                            this.label = 1;
                            if (flowCollector.emit(num, this) == coroutineSingletons) {
                                return coroutineSingletons;
                            }
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

            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                KeyguardTransitionInteractor keyguardTransitionInteractor2 = KeyguardInteractor.this.keyguardTransitionInteractor;
                SceneKey sceneKey = Scenes.Bouncer;
                return FlowKt.distinctUntilChanged(FlowKt.combineTransform(FlowKt.distinctUntilChanged(new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new AnonymousClass2(2, null), new KeyguardInteractor$special$$inlined$map$1(keyguardTransitionInteractor2.getTransitionValueFlow(KeyguardState.GONE), 1))), KeyguardInteractor.this.repository.topClippingBounds, new AnonymousClass3(4, null)));
            }
        });
        this.lastRootViewTapPosition = new ReadonlyStateFlow(keyguardRepositoryImpl.lastRootViewTapPosition);
        this.ambientIndicationVisible = new ReadonlyStateFlow(keyguardRepositoryImpl.ambientIndicationVisible);
        this.primaryBouncerShowing = keyguardBouncerRepositoryImpl.primaryBouncerShow;
        this.alternateBouncerShowing = com.android.systemui.util.kotlin.FlowKt.sample(keyguardBouncerRepositoryImpl.alternateBouncerVisible, stateIn, new KeyguardInteractor$alternateBouncerShowing$1(3, null));
        ReadonlyStateFlow readonlyStateFlow = keyguardRepositoryImpl.statusBarState;
        this.statusBarState = readonlyStateFlow;
        this.biometricUnlockState = keyguardRepositoryImpl.biometricUnlockState;
        this.isKeyguardVisible = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(stateFlowImpl, stateFlowImpl3, new KeyguardInteractor$isKeyguardVisible$1(3, null));
        this.isSecureCameraActive$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.keyguard.domain.interactor.KeyguardInteractor$isSecureCameraActive$2

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardInteractor$isSecureCameraActive$2$1, reason: invalid class name */
            final class AnonymousClass1 extends SuspendLambda implements Function4 {
                /* synthetic */ Object L$0;
                /* synthetic */ boolean Z$0;
                /* synthetic */ boolean Z$1;
                int label;

                @Override // kotlin.jvm.functions.Function4
                public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                    boolean booleanValue = ((Boolean) obj).booleanValue();
                    boolean booleanValue2 = ((Boolean) obj2).booleanValue();
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(4, (Continuation) obj4);
                    anonymousClass1.Z$0 = booleanValue;
                    anonymousClass1.Z$1 = booleanValue2;
                    anonymousClass1.L$0 = (CameraLaunchSourceModel) obj3;
                    return anonymousClass1.invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    boolean z = this.Z$0;
                    boolean z2 = this.Z$1;
                    CameraLaunchSourceModel cameraLaunchSourceModel = (CameraLaunchSourceModel) this.L$0;
                    boolean z3 = false;
                    if (!z && !z2 && cameraLaunchSourceModel.type == CameraLaunchType.POWER_DOUBLE_TAP) {
                        z3 = true;
                    }
                    return Boolean.valueOf(z3);
                }
            }

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardInteractor$isSecureCameraActive$2$2, reason: invalid class name */
            final class AnonymousClass2 extends SuspendLambda implements Function2 {
                private /* synthetic */ Object L$0;
                int label;

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    AnonymousClass2 anonymousClass2 = new AnonymousClass2(2, continuation);
                    anonymousClass2.L$0 = obj;
                    return anonymousClass2;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ((AnonymousClass2) create((FlowCollector) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        FlowCollector flowCollector = (FlowCollector) this.L$0;
                        Boolean bool = Boolean.FALSE;
                        this.label = 1;
                        if (flowCollector.emit(bool, this) == coroutineSingletons) {
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

            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                KeyguardInteractor keyguardInteractor = KeyguardInteractor.this;
                return new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new AnonymousClass2(2, null), FlowKt.combine(keyguardInteractor.isKeyguardVisible, keyguardInteractor.primaryBouncerShowing, keyguardInteractor.onCameraLaunchDetected, new AnonymousClass1(4, null)));
            }
        });
        this.keyguardAlpha = keyguardRepositoryImpl.keyguardAlpha;
        this.panelAlpha = new ReadonlyStateFlow(keyguardRepositoryImpl.panelAlpha);
        Utils.Companion companion = Utils.Companion;
        ReadonlyStateFlow readonlyStateFlow2 = ((ShadeRepositoryImpl) shadeRepository).legacyShadeExpansion;
        SceneKey sceneKey = Scenes.Bouncer;
        this.dismissAlpha = FlowKt.distinctUntilChanged(new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new KeyguardInteractor$dismissAlpha$3(2, null), new SafeFlow(new KeyguardInteractor$special$$inlined$transform$1(new KeyguardInteractor$special$$inlined$map$2(companion.sample(readonlyStateFlow2, readonlyStateFlow, keyguardTransitionInteractor.currentKeyguardState, keyguardTransitionInteractor.transitionState, stateFlowImpl2, keyguardTransitionInteractor.isFinishedIn(KeyguardState.GLANCEABLE_HUB)), 2), null))));
        this.keyguardTranslationY = FlowKt.stateIn(FlowKt.transformLatest(configurationInteractor.dimensionPixelSize(R.dimen.keyguard_translate_distance_on_swipe_up), new KeyguardInteractor$special$$inlined$flatMapLatest$3(null, shadeRepository, this)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), Float.valueOf(0.0f));
        this.clockShouldBeCentered = keyguardRepositoryImpl.clockShouldBeCentered;
        this.animateDozingTransitions$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.keyguard.domain.interactor.KeyguardInteractor$animateDozingTransitions$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return this.repository.animateBottomAreaDozingTransitions;
            }
        });
        this.asleepKeyguardState = FlowKt.stateIn(new KeyguardInteractor$special$$inlined$map$2(keyguardRepositoryImpl.isAodAvailable, 0), coroutineScope, startedEagerly, KeyguardState.DOZING);
        this.isEncryptedOrLockdown = keyguardRepositoryImpl.isEncryptedOrLockdown;
    }

    public final boolean isKeyguardShowing() {
        return ((KeyguardStateControllerImpl) this.repository.keyguardStateController).mShowing;
    }

    public final void showKeyguard() {
        FromGoneTransitionInteractor fromGoneTransitionInteractor = (FromGoneTransitionInteractor) this.fromGoneTransitionInteractor.get();
        fromGoneTransitionInteractor.getClass();
        CoroutineTracingKt.launch$default(fromGoneTransitionInteractor.scope, null, new FromGoneTransitionInteractor$showKeyguard$1(fromGoneTransitionInteractor, null), 6);
    }
}
