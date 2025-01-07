package com.android.systemui.biometrics;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Point;
import android.hardware.biometrics.BiometricFingerprintConstants;
import android.hardware.biometrics.BiometricSourceType;
import android.util.DisplayMetrics;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.app.animation.Interpolators;
import com.android.internal.graphics.ColorUtils;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.keyguard.logging.KeyguardLogger;
import com.android.systemui.CoreStartable;
import com.android.systemui.biometrics.AuthController;
import com.android.systemui.biometrics.AuthRippleController.AuthRippleCommand;
import com.android.systemui.biometrics.UdfpsController;
import com.android.systemui.biometrics.data.repository.FacePropertyRepositoryImpl;
import com.android.systemui.biometrics.shared.model.UdfpsOverlayParams;
import com.android.systemui.deviceentry.domain.interactor.AuthRippleInteractor;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.keyguard.shared.model.BiometricUnlockSource;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.shade.NotificationShadeWindowControllerImpl;
import com.android.systemui.statusbar.CircleReveal;
import com.android.systemui.statusbar.LiftReveal;
import com.android.systemui.statusbar.LightRevealScrim;
import com.android.systemui.statusbar.NotificationShadeWindowController;
import com.android.systemui.statusbar.commandline.Command;
import com.android.systemui.statusbar.commandline.CommandRegistry;
import com.android.systemui.statusbar.phone.BiometricUnlockController;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.util.ViewController;
import com.android.wm.shell.R;
import dagger.internal.DelegateFactory;
import java.io.PrintWriter;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringNumberConversionsJVMKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.internal.ChannelLimitedFlowMerge;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AuthRippleController extends ViewController implements CoreStartable, KeyguardStateController.Callback, WakefulnessLifecycle.Observer {
    public final AuthController authController;
    public final AuthRippleController$authControllerCallback$1 authControllerCallback;
    public final AuthRippleInteractor authRippleInteractor;
    public final AuthRippleController$biometricModeListener$1 biometricModeListener;
    public final BiometricUnlockController biometricUnlockController;
    public CircleReveal circleReveal;
    public final CommandRegistry commandRegistry;
    public final AuthRippleController$configurationChangedListener$1 configurationChangedListener;
    public final ConfigurationController configurationController;
    public final DisplayMetrics displayMetrics;
    public final FacePropertyRepositoryImpl facePropertyRepository;
    public Point faceSensorLocation;
    public Point fingerprintSensorLocation;
    public final KeyguardStateController keyguardStateController;
    public final KeyguardUpdateMonitor keyguardUpdateMonitor;
    public final AuthRippleController$keyguardUpdateMonitorCallback$1 keyguardUpdateMonitorCallback;
    public final LightRevealScrim lightRevealScrim;
    public ValueAnimator lightRevealScrimAnimator;
    public final KeyguardLogger logger;
    public final NotificationShadeWindowController notificationShadeWindowController;
    public boolean startLightRevealScrimOnKeyguardFadingAway;
    public final StatusBarStateController statusBarStateController;
    public final Context sysuiContext;
    public UdfpsController udfpsController;
    public final AuthRippleController$udfpsControllerCallback$1 udfpsControllerCallback;
    public final DelegateFactory udfpsControllerProvider;
    public float udfpsRadius;
    public final WakefulnessLifecycle wakefulnessLifecycle;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.biometrics.AuthRippleController$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function3 {
        private /* synthetic */ Object L$0;
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.biometrics.AuthRippleController$1$1, reason: invalid class name and collision with other inner class name */
        final class C00241 extends SuspendLambda implements Function2 {
            int label;
            final /* synthetic */ AuthRippleController this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00241(AuthRippleController authRippleController, Continuation continuation) {
                super(2, continuation);
                this.this$0 = authRippleController;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new C00241(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((C00241) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    final AuthRippleController authRippleController = this.this$0;
                    ChannelLimitedFlowMerge channelLimitedFlowMerge = authRippleController.authRippleInteractor.showUnlockRipple;
                    FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.biometrics.AuthRippleController.1.1.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public final Object emit(Object obj2, Continuation continuation) {
                            BiometricUnlockSource biometricUnlockSource = (BiometricUnlockSource) obj2;
                            BiometricUnlockSource biometricUnlockSource2 = BiometricUnlockSource.FINGERPRINT_SENSOR;
                            AuthRippleController authRippleController2 = AuthRippleController.this;
                            if (biometricUnlockSource == biometricUnlockSource2) {
                                authRippleController2.showUnlockRippleInternal(BiometricSourceType.FINGERPRINT);
                            } else {
                                authRippleController2.showUnlockRippleInternal(BiometricSourceType.FACE);
                            }
                            return Unit.INSTANCE;
                        }
                    };
                    this.label = 1;
                    if (channelLimitedFlowMerge.collect(flowCollector, this) == coroutineSingletons) {
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

        public AnonymousClass1(Continuation continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            AnonymousClass1 anonymousClass1 = AuthRippleController.this.new AnonymousClass1((Continuation) obj3);
            anonymousClass1.L$0 = (LifecycleOwner) obj;
            return anonymousClass1.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
                Lifecycle.State state = Lifecycle.State.CREATED;
                C00241 c00241 = new C00241(AuthRippleController.this, null);
                this.label = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycleOwner, state, c00241, this) == coroutineSingletons) {
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
    public final class AuthRippleCommand implements Command {
        public AuthRippleCommand() {
        }

        public static void invalidCommand(PrintWriter printWriter) {
            printWriter.println("invalid command");
            printWriter.println("Usage: adb shell cmd statusbar auth-ripple <command>");
            printWriter.println("Available commands:");
            printWriter.println("  dwell");
            printWriter.println("  fingerprint");
            printWriter.println("  face");
            printWriter.println("  custom <x-location: int> <y-location: int>");
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Override // com.android.systemui.statusbar.commandline.Command
        public final void execute(PrintWriter printWriter, List list) {
            if (list.isEmpty()) {
                invalidCommand(printWriter);
                return;
            }
            String str = (String) list.get(0);
            int hashCode = str.hashCode();
            AuthRippleController authRippleController = AuthRippleController.this;
            switch (hashCode) {
                case -1375934236:
                    if (str.equals("fingerprint")) {
                        printWriter.println("fingerprint ripple sensorLocation=" + authRippleController.fingerprintSensorLocation);
                        authRippleController.showUnlockRippleInternal(BiometricSourceType.FINGERPRINT);
                        return;
                    }
                    break;
                case -1349088399:
                    if (str.equals("custom")) {
                        if (list.size() != 3 || StringsKt__StringNumberConversionsJVMKt.toFloatOrNull((String) list.get(1)) == null || StringsKt__StringNumberConversionsJVMKt.toFloatOrNull((String) list.get(2)) == null) {
                            invalidCommand(printWriter);
                            return;
                        }
                        printWriter.println("custom ripple sensorLocation=" + list.get(1) + ", " + list.get(2));
                        ((AuthRippleView) authRippleController.mView).setSensorLocation(new Point(Integer.parseInt((String) list.get(1)), Integer.parseInt((String) list.get(2))));
                        authRippleController.showUnlockedRipple();
                        return;
                    }
                    break;
                case 3135069:
                    if (str.equals("face")) {
                        printWriter.println("face ripple sensorLocation=" + authRippleController.faceSensorLocation);
                        authRippleController.showUnlockRippleInternal(BiometricSourceType.FACE);
                        return;
                    }
                    break;
                case 95997746:
                    if (str.equals("dwell")) {
                        AuthRippleController.access$showDwellRipple(authRippleController);
                        printWriter.println("lock screen dwell ripple: \n\tsensorLocation=" + authRippleController.fingerprintSensorLocation + "\n\tudfpsRadius=" + authRippleController.udfpsRadius);
                        return;
                    }
                    break;
            }
            invalidCommand(printWriter);
        }
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [com.android.systemui.biometrics.AuthRippleController$keyguardUpdateMonitorCallback$1] */
    /* JADX WARN: Type inference failed for: r1v3, types: [com.android.systemui.biometrics.AuthRippleController$configurationChangedListener$1] */
    /* JADX WARN: Type inference failed for: r1v4, types: [com.android.systemui.biometrics.AuthRippleController$udfpsControllerCallback$1] */
    /* JADX WARN: Type inference failed for: r1v5, types: [com.android.systemui.biometrics.AuthRippleController$authControllerCallback$1] */
    public AuthRippleController(Context context, AuthController authController, ConfigurationController configurationController, KeyguardUpdateMonitor keyguardUpdateMonitor, KeyguardStateController keyguardStateController, WakefulnessLifecycle wakefulnessLifecycle, CommandRegistry commandRegistry, NotificationShadeWindowController notificationShadeWindowController, DelegateFactory delegateFactory, StatusBarStateController statusBarStateController, DisplayMetrics displayMetrics, KeyguardLogger keyguardLogger, BiometricUnlockController biometricUnlockController, LightRevealScrim lightRevealScrim, AuthRippleInteractor authRippleInteractor, FacePropertyRepositoryImpl facePropertyRepositoryImpl, AuthRippleView authRippleView) {
        super(authRippleView);
        this.sysuiContext = context;
        this.authController = authController;
        this.configurationController = configurationController;
        this.keyguardUpdateMonitor = keyguardUpdateMonitor;
        this.keyguardStateController = keyguardStateController;
        this.wakefulnessLifecycle = wakefulnessLifecycle;
        this.commandRegistry = commandRegistry;
        this.notificationShadeWindowController = notificationShadeWindowController;
        this.udfpsControllerProvider = delegateFactory;
        this.statusBarStateController = statusBarStateController;
        this.displayMetrics = displayMetrics;
        this.logger = keyguardLogger;
        this.biometricUnlockController = biometricUnlockController;
        this.lightRevealScrim = lightRevealScrim;
        this.authRippleInteractor = authRippleInteractor;
        this.facePropertyRepository = facePropertyRepositoryImpl;
        this.udfpsRadius = -1.0f;
        if (authRippleView != null) {
            RepeatWhenAttachedKt.repeatWhenAttached(authRippleView, EmptyCoroutineContext.INSTANCE, new AnonymousClass1(null));
        }
        this.biometricModeListener = new AuthRippleController$biometricModeListener$1();
        this.keyguardUpdateMonitorCallback = new KeyguardUpdateMonitorCallback() { // from class: com.android.systemui.biometrics.AuthRippleController$keyguardUpdateMonitorCallback$1
            @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
            public final void onBiometricAcquired(BiometricSourceType biometricSourceType, int i) {
                if (biometricSourceType == BiometricSourceType.FINGERPRINT && BiometricFingerprintConstants.shouldDisableUdfpsDisplayMode(i) && i != 0) {
                    ((AuthRippleView) AuthRippleController.this.mView).retractDwellRipple();
                }
            }

            @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
            public final void onBiometricAuthFailed(BiometricSourceType biometricSourceType) {
                if (biometricSourceType == BiometricSourceType.FINGERPRINT) {
                    ((AuthRippleView) AuthRippleController.this.mView).retractDwellRipple();
                }
            }

            @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
            public final void onBiometricAuthenticated(int i, BiometricSourceType biometricSourceType, boolean z) {
                if (biometricSourceType == BiometricSourceType.FINGERPRINT) {
                    ((AuthRippleView) AuthRippleController.this.mView).fadeDwellRipple();
                }
            }

            @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
            public final void onKeyguardBouncerStateChanged(boolean z) {
                if (z) {
                    ((AuthRippleView) AuthRippleController.this.mView).fadeDwellRipple();
                }
            }
        };
        this.configurationChangedListener = new ConfigurationController.ConfigurationListener() { // from class: com.android.systemui.biometrics.AuthRippleController$configurationChangedListener$1
            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onThemeChanged() {
                AuthRippleController.this.updateRippleColor();
            }

            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onUiModeChanged() {
                AuthRippleController.this.updateRippleColor();
            }
        };
        this.udfpsControllerCallback = new UdfpsController.Callback() { // from class: com.android.systemui.biometrics.AuthRippleController$udfpsControllerCallback$1
            @Override // com.android.systemui.biometrics.UdfpsController.Callback
            public final void onFingerDown() {
                AuthRippleController authRippleController = AuthRippleController.this;
                if (authRippleController.keyguardUpdateMonitor.isFingerprintDetectionRunning()) {
                    AuthRippleController.access$showDwellRipple(authRippleController);
                }
            }

            @Override // com.android.systemui.biometrics.UdfpsController.Callback
            public final void onFingerUp() {
                ((AuthRippleView) AuthRippleController.this.mView).retractDwellRipple();
            }
        };
        this.authControllerCallback = new AuthController.Callback() { // from class: com.android.systemui.biometrics.AuthRippleController$authControllerCallback$1
            @Override // com.android.systemui.biometrics.AuthController.Callback
            public final void onAllAuthenticatorsRegistered(int i) {
                AuthRippleController.this.updateUdfpsDependentParams();
            }

            @Override // com.android.systemui.biometrics.AuthController.Callback
            public final void onUdfpsLocationChanged(UdfpsOverlayParams udfpsOverlayParams) {
                AuthRippleController.this.updateUdfpsDependentParams();
            }
        };
    }

    public static final void access$showDwellRipple(AuthRippleController authRippleController) {
        authRippleController.fingerprintSensorLocation = authRippleController.authController.mFingerprintSensorLocation;
        authRippleController.faceSensorLocation = (Point) authRippleController.facePropertyRepository.sensorLocation.getValue();
        Point point = authRippleController.fingerprintSensorLocation;
        if (point != null) {
            ((AuthRippleView) authRippleController.mView).setFingerprintSensorLocation(point, authRippleController.udfpsRadius);
            AuthRippleView authRippleView = (AuthRippleView) authRippleController.mView;
            boolean isDozing = authRippleController.statusBarStateController.isDozing();
            Animator animator = authRippleView.unlockedRippleAnimator;
            if (animator == null || !animator.isRunning()) {
                Animator animator2 = authRippleView.dwellPulseOutAnimator;
                if (animator2 == null || !animator2.isRunning()) {
                    if (isDozing) {
                        authRippleView.dwellShader.setColor(-1);
                    } else {
                        authRippleView.dwellShader.setColor(authRippleView.lockScreenColorVal);
                    }
                    DwellRippleShader dwellRippleShader = authRippleView.dwellShader;
                    dwellRippleShader.setColor(ColorUtils.setAlphaComponent(dwellRippleShader.color, 255));
                    ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 0.8f);
                    ofFloat.setInterpolator(Interpolators.LINEAR);
                    ofFloat.setDuration(authRippleView.dwellPulseDuration);
                    ofFloat.addUpdateListener(new AuthRippleView$fadeDwellRipple$1$1(authRippleView, 3));
                    ValueAnimator ofFloat2 = ValueAnimator.ofFloat(0.8f, 1.0f);
                    ofFloat2.setInterpolator(Interpolators.LINEAR_OUT_SLOW_IN);
                    ofFloat2.setDuration(authRippleView.dwellExpandDuration);
                    ofFloat2.addUpdateListener(new AuthRippleView$fadeDwellRipple$1$1(authRippleView, 4));
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.playSequentially(ofFloat, ofFloat2);
                    animatorSet.addListener(new AuthRippleView$fadeDwellRipple$1$2(authRippleView, 2));
                    animatorSet.start();
                    authRippleView.dwellPulseOutAnimator = animatorSet;
                }
            }
        }
    }

    @Override // com.android.systemui.statusbar.policy.KeyguardStateController.Callback
    public final void onKeyguardFadingAwayChanged() {
        KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) this.keyguardStateController;
        if (keyguardStateControllerImpl.mKeyguardFadingAway && this.startLightRevealScrimOnKeyguardFadingAway) {
            ValueAnimator valueAnimator = this.lightRevealScrimAnimator;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
            final ValueAnimator ofFloat = ValueAnimator.ofFloat(0.1f, 1.0f);
            ofFloat.setInterpolator(Interpolators.LINEAR_OUT_SLOW_IN);
            ofFloat.setDuration(800L);
            ofFloat.setStartDelay(keyguardStateControllerImpl.mKeyguardFadingAwayDelay);
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.biometrics.AuthRippleController$onKeyguardFadingAwayChanged$1$1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                    AuthRippleController authRippleController = AuthRippleController.this;
                    if (Intrinsics.areEqual(authRippleController.lightRevealScrim.revealEffect, authRippleController.circleReveal)) {
                        AuthRippleController.this.lightRevealScrim.setRevealAmount(((Float) valueAnimator2.getAnimatedValue()).floatValue());
                    } else {
                        ofFloat.cancel();
                    }
                }
            });
            ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.biometrics.AuthRippleController$onKeyguardFadingAwayChanged$1$2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animator) {
                    AuthRippleController authRippleController = AuthRippleController.this;
                    if (Intrinsics.areEqual(authRippleController.lightRevealScrim.revealEffect, authRippleController.circleReveal)) {
                        AuthRippleController.this.lightRevealScrim.setRevealEffect(LiftReveal.INSTANCE);
                    }
                    AuthRippleController.this.lightRevealScrimAnimator = null;
                }
            });
            ofFloat.start();
            this.lightRevealScrimAnimator = ofFloat;
            this.startLightRevealScrimOnKeyguardFadingAway = false;
        }
    }

    @Override // com.android.systemui.keyguard.WakefulnessLifecycle.Observer
    public final void onStartedGoingToSleep() {
        this.startLightRevealScrimOnKeyguardFadingAway = false;
    }

    @Override // com.android.systemui.util.ViewController
    public void onViewAttached() {
        this.authController.addCallback(this.authControllerCallback);
        updateRippleColor();
        updateUdfpsDependentParams();
        UdfpsController udfpsController = this.udfpsController;
        if (udfpsController != null) {
            udfpsController.mCallbacks.add(this.udfpsControllerCallback);
        }
        ((ConfigurationControllerImpl) this.configurationController).addCallback(this.configurationChangedListener);
        this.keyguardUpdateMonitor.registerCallback(this.keyguardUpdateMonitorCallback);
        ((KeyguardStateControllerImpl) this.keyguardStateController).addCallback(this);
        this.wakefulnessLifecycle.mObservers.add(this);
        this.commandRegistry.registerCommand("auth-ripple", new Function0() { // from class: com.android.systemui.biometrics.AuthRippleController$onViewAttached$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AuthRippleController.this.new AuthRippleCommand();
            }
        });
    }

    @Override // com.android.systemui.util.ViewController
    public void onViewDetached() {
        UdfpsController udfpsController = this.udfpsController;
        if (udfpsController != null) {
            udfpsController.mCallbacks.remove(this.udfpsControllerCallback);
        }
        this.authController.removeCallback(this.authControllerCallback);
        this.keyguardUpdateMonitor.removeCallback(this.keyguardUpdateMonitorCallback);
        ((ConfigurationControllerImpl) this.configurationController).removeCallback(this.configurationChangedListener);
        ((KeyguardStateControllerImpl) this.keyguardStateController).removeCallback(this);
        this.wakefulnessLifecycle.removeObserver(this);
        CommandRegistry commandRegistry = this.commandRegistry;
        synchronized (commandRegistry) {
            commandRegistry.commandMap.remove("auth-ripple");
        }
        BiometricUnlockController biometricUnlockController = this.biometricUnlockController;
        biometricUnlockController.mBiometricUnlockEventsListeners.remove(this.biometricModeListener);
        ((NotificationShadeWindowControllerImpl) this.notificationShadeWindowController).setForcePluginOpen(this, false);
    }

    public final void showUnlockRippleInternal(BiometricSourceType biometricSourceType) {
        boolean z = ((KeyguardStateControllerImpl) this.keyguardStateController).mShowing;
        boolean z2 = !z;
        boolean isUnlockingWithBiometricAllowed = this.keyguardUpdateMonitor.isUnlockingWithBiometricAllowed(biometricSourceType);
        boolean z3 = !isUnlockingWithBiometricAllowed;
        KeyguardLogger keyguardLogger = this.logger;
        if (!z || !isUnlockingWithBiometricAllowed) {
            keyguardLogger.notShowingUnlockRipple(z2, z3);
            return;
        }
        this.fingerprintSensorLocation = this.authController.mFingerprintSensorLocation;
        Point point = (Point) this.facePropertyRepository.sensorLocation.getValue();
        this.faceSensorLocation = point;
        if (biometricSourceType != BiometricSourceType.FINGERPRINT) {
            if (biometricSourceType != BiometricSourceType.FACE || point == null) {
                return;
            }
            ((AuthRippleView) this.mView).setSensorLocation(point);
            int i = point.x;
            int i2 = point.y;
            int max = Math.max(i, this.displayMetrics.widthPixels - i);
            int i3 = point.y;
            this.circleReveal = new CircleReveal(i, i2, Math.max(max, Math.max(i3, this.displayMetrics.heightPixels - i3)));
            keyguardLogger.showingUnlockRippleAt("Face unlock ripple", point.x, point.y);
            showUnlockedRipple();
            return;
        }
        Point point2 = this.fingerprintSensorLocation;
        if (point2 != null) {
            ((AuthRippleView) this.mView).setFingerprintSensorLocation(point2, this.udfpsRadius);
            int i4 = point2.x;
            int i5 = point2.y;
            int max2 = Math.max(i4, this.displayMetrics.widthPixels - i4);
            int i6 = point2.y;
            this.circleReveal = new CircleReveal(i4, i5, Math.max(max2, Math.max(i6, this.displayMetrics.heightPixels - i6)));
            keyguardLogger.showingUnlockRippleAt("FP sensor radius: " + this.udfpsRadius, point2.x, point2.y);
            showUnlockedRipple();
        }
    }

    public final void showUnlockedRipple() {
        CircleReveal circleReveal;
        ((NotificationShadeWindowControllerImpl) this.notificationShadeWindowController).setForcePluginOpen(this, true);
        if ((this.statusBarStateController.isDozing() || this.biometricUnlockController.isWakeAndUnlock()) && (circleReveal = this.circleReveal) != null) {
            LightRevealScrim lightRevealScrim = this.lightRevealScrim;
            lightRevealScrim.setRevealAmount(0.0f);
            lightRevealScrim.setRevealEffect(circleReveal);
            this.startLightRevealScrimOnKeyguardFadingAway = true;
        }
        final AuthRippleView authRippleView = (AuthRippleView) this.mView;
        final AuthRippleController$showUnlockedRipple$2 authRippleController$showUnlockedRipple$2 = new AuthRippleController$showUnlockedRipple$2(this);
        Animator animator = authRippleView.unlockedRippleAnimator;
        if (animator != null) {
            animator.cancel();
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.setDuration(800L);
        ofFloat.addUpdateListener(new AuthRippleView$fadeDwellRipple$1$1(authRippleView, 5));
        ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.biometrics.AuthRippleView$startUnlockedRipple$1$1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator2) {
                authRippleController$showUnlockedRipple$2.run();
                AuthRippleView authRippleView2 = AuthRippleView.this;
                authRippleView2.drawRipple = false;
                authRippleView2.setVisibility(8);
                AuthRippleView.this.unlockedRippleAnimator = null;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationStart(Animator animator2) {
                AuthRippleView authRippleView2 = AuthRippleView.this;
                authRippleView2.drawRipple = true;
                authRippleView2.setVisibility(0);
            }
        });
        authRippleView.unlockedRippleAnimator = ofFloat;
        ofFloat.start();
    }

    public final void updateRippleColor() {
        AuthRippleView authRippleView = (AuthRippleView) this.mView;
        int colorAttrDefaultColor = com.android.settingslib.Utils.getColorAttrDefaultColor(R.attr.wallpaperTextColorAccent, 0, this.sysuiContext);
        authRippleView.lockScreenColorVal = colorAttrDefaultColor;
        authRippleView.rippleShader.setColorUniform("in_color", ColorUtils.setAlphaComponent(colorAttrDefaultColor, 62));
    }

    public final void updateUdfpsDependentParams() {
        UdfpsController udfpsController;
        AuthController authController = this.authController;
        List list = authController.mUdfpsProps;
        if (list == null || list.size() <= 0) {
            return;
        }
        this.udfpsController = (UdfpsController) this.udfpsControllerProvider.get();
        this.udfpsRadius = authController.getUdfpsRadius();
        if (!((AuthRippleView) this.mView).isAttachedToWindow() || (udfpsController = this.udfpsController) == null) {
            return;
        }
        udfpsController.mCallbacks.add(this.udfpsControllerCallback);
    }

    public static /* synthetic */ void getStartLightRevealScrimOnKeyguardFadingAway$frameworks__base__packages__SystemUI__android_common__SystemUI_core$annotations() {
    }
}
