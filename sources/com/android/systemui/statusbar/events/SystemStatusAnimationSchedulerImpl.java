package com.android.systemui.statusbar.events;

import android.graphics.Insets;
import android.graphics.Rect;
import android.os.Process;
import android.provider.DeviceConfig;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.compose.foundation.text.input.internal.AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0;
import androidx.core.animation.Animator;
import androidx.core.animation.AnimatorSet;
import androidx.core.animation.ValueAnimator;
import com.android.keyguard.ActiveUnlockConfig$$ExternalSyntheticOutline0;
import com.android.keyguard.logging.CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0;
import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.phone.StatusBarContentInsetsChangedListener;
import com.android.systemui.statusbar.phone.StatusBarContentInsetsProvider;
import com.android.systemui.statusbar.policy.BatteryControllerImpl;
import com.android.systemui.statusbar.policy.CallbackController;
import com.android.systemui.statusbar.window.StatusBarWindowControllerImpl;
import com.android.systemui.util.Assert;
import com.android.systemui.util.leak.RotationUtils;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import com.android.wm.shell.R;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.StandaloneCoroutine;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SystemStatusAnimationSchedulerImpl implements CallbackController, Dumpable {
    public final SystemEventChipAnimationController chipAnimationController;
    public final SystemEventCoordinator coordinator;
    public final CoroutineScope coroutineScope;
    public StatusEvent currentlyDisplayedEvent;
    public StandaloneCoroutine currentlyRunningAnimationJob;
    public StandaloneCoroutine eventCancellationJob;
    public boolean hasPersistentDot;
    public final SystemStatusAnimationSchedulerLogger logger;
    public final StatusBarWindowControllerImpl statusBarWindowController;
    public final SystemClock systemClock;
    public final StateFlowImpl scheduledEvent = StateFlowKt.MutableStateFlow(null);
    public final StateFlowImpl animationState = StateFlowKt.MutableStateFlow(0);
    public final Set listeners = new LinkedHashSet();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.events.SystemStatusAnimationSchedulerImpl$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.statusbar.events.SystemStatusAnimationSchedulerImpl$1$1, reason: invalid class name and collision with other inner class name */
        final class C01901 extends SuspendLambda implements Function3 {
            /* synthetic */ int I$0;
            /* synthetic */ Object L$0;
            int label;

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                int intValue = ((Number) obj).intValue();
                C01901 c01901 = new C01901(3, (Continuation) obj3);
                c01901.I$0 = intValue;
                c01901.L$0 = (StatusEvent) obj2;
                return c01901.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                int i = this.I$0;
                return new Pair(new Integer(i), (StatusEvent) this.L$0);
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.statusbar.events.SystemStatusAnimationSchedulerImpl$1$2, reason: invalid class name */
        public final class AnonymousClass2 implements FlowCollector {
            public final /* synthetic */ int $r8$classId;
            public final /* synthetic */ SystemStatusAnimationSchedulerImpl this$0;

            public /* synthetic */ AnonymousClass2(SystemStatusAnimationSchedulerImpl systemStatusAnimationSchedulerImpl, int i) {
                this.$r8$classId = i;
                this.this$0 = systemStatusAnimationSchedulerImpl;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(Object obj, Continuation continuation) {
                Unit unit = Unit.INSTANCE;
                SystemStatusAnimationSchedulerImpl systemStatusAnimationSchedulerImpl = this.this$0;
                switch (this.$r8$classId) {
                    case 0:
                        Pair pair = (Pair) obj;
                        int intValue = ((Number) pair.component1()).intValue();
                        StatusEvent statusEvent = (StatusEvent) pair.component2();
                        if (intValue == 1 && statusEvent != null) {
                            systemStatusAnimationSchedulerImpl.getClass();
                            Assert.isMainThread();
                            systemStatusAnimationSchedulerImpl.hasPersistentDot = statusEvent.getForceVisible();
                            if (statusEvent.getShowAnimation() || !statusEvent.getForceVisible()) {
                                systemStatusAnimationSchedulerImpl.currentlyDisplayedEvent = statusEvent;
                                Function1 viewCreator = statusEvent.getViewCreator();
                                final SystemEventChipAnimationController systemEventChipAnimationController = systemStatusAnimationSchedulerImpl.chipAnimationController;
                                boolean z = systemEventChipAnimationController.initialized;
                                StatusBarContentInsetsProvider statusBarContentInsetsProvider = systemEventChipAnimationController.contentInsetsProvider;
                                if (!z) {
                                    systemEventChipAnimationController.initialized = true;
                                    ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(systemEventChipAnimationController.context, R.style.Theme_SystemUI_QuickSettings);
                                    systemEventChipAnimationController.themedContext = contextThemeWrapper;
                                    systemEventChipAnimationController.animationWindowView = (FrameLayout) LayoutInflater.from(contextThemeWrapper).inflate(R.layout.system_event_animation_window, (ViewGroup) null);
                                    ContextThemeWrapper contextThemeWrapper2 = systemEventChipAnimationController.themedContext;
                                    if (contextThemeWrapper2 == null) {
                                        contextThemeWrapper2 = null;
                                    }
                                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, contextThemeWrapper2.getResources().getDimensionPixelSize(R.dimen.status_bar_height));
                                    layoutParams.gravity = 8388661;
                                    FrameLayout frameLayout = systemEventChipAnimationController.animationWindowView;
                                    if (frameLayout == null) {
                                        frameLayout = null;
                                    }
                                    systemEventChipAnimationController.statusBarWindowController.mStatusBarWindowView.addView(frameLayout, layoutParams);
                                    FrameLayout frameLayout2 = systemEventChipAnimationController.animationWindowView;
                                    if (frameLayout2 == null) {
                                        frameLayout2 = null;
                                    }
                                    frameLayout2.setClipToPadding(false);
                                    FrameLayout frameLayout3 = systemEventChipAnimationController.animationWindowView;
                                    if (frameLayout3 == null) {
                                        frameLayout3 = null;
                                    }
                                    frameLayout3.setClipChildren(false);
                                    statusBarContentInsetsProvider.listeners.add(new StatusBarContentInsetsChangedListener() { // from class: com.android.systemui.statusbar.events.SystemEventChipAnimationController$init$1
                                        @Override // com.android.systemui.statusbar.phone.StatusBarContentInsetsChangedListener
                                        public final void onStatusBarContentInsetsChanged() {
                                            final SystemEventChipAnimationController systemEventChipAnimationController2 = SystemEventChipAnimationController.this;
                                            StatusBarContentInsetsProvider statusBarContentInsetsProvider2 = systemEventChipAnimationController2.contentInsetsProvider;
                                            Rect statusBarContentAreaForRotation = statusBarContentInsetsProvider2.getStatusBarContentAreaForRotation(RotationUtils.getExactRotation(statusBarContentInsetsProvider2.context));
                                            FrameLayout frameLayout4 = systemEventChipAnimationController2.animationWindowView;
                                            if (frameLayout4 == null) {
                                                frameLayout4 = null;
                                            }
                                            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) frameLayout4.getLayoutParams();
                                            layoutParams2.height = statusBarContentAreaForRotation.height();
                                            FrameLayout frameLayout5 = systemEventChipAnimationController2.animationWindowView;
                                            (frameLayout5 != null ? frameLayout5 : null).setLayoutParams(layoutParams2);
                                            BackgroundAnimatableView backgroundAnimatableView = systemEventChipAnimationController2.currentAnimatedView;
                                            if (backgroundAnimatableView != null) {
                                                systemEventChipAnimationController2.updateChipBounds(backgroundAnimatableView, statusBarContentAreaForRotation);
                                                ValueAnimator ofInt = ValueAnimator.ofInt(0, 1);
                                                ofInt.setDuration(0L);
                                                ofInt.addUpdateListener(new Animator.AnimatorUpdateListener() { // from class: com.android.systemui.statusbar.events.SystemEventChipAnimationController$init$1$onStatusBarContentInsetsChanged$1$1
                                                    @Override // androidx.core.animation.Animator.AnimatorUpdateListener
                                                    public final void onAnimationUpdate(Animator animator) {
                                                        SystemEventChipAnimationController.this.updateCurrentAnimatedView();
                                                    }
                                                });
                                                ofInt.start(false);
                                            }
                                        }
                                    });
                                }
                                FrameLayout frameLayout4 = systemEventChipAnimationController.animationWindowView;
                                if (frameLayout4 == null) {
                                    frameLayout4 = null;
                                }
                                systemEventChipAnimationController.animationDirection = frameLayout4.isLayoutRtl() ? 2 : 1;
                                Insets statusBarContentInsetsForCurrentRotation = statusBarContentInsetsProvider.getStatusBarContentInsetsForCurrentRotation();
                                ContextThemeWrapper contextThemeWrapper3 = systemEventChipAnimationController.themedContext;
                                if (contextThemeWrapper3 == null) {
                                    contextThemeWrapper3 = null;
                                }
                                BackgroundAnimatableView backgroundAnimatableView = (BackgroundAnimatableView) viewCreator.invoke(contextThemeWrapper3);
                                FrameLayout frameLayout5 = systemEventChipAnimationController.animationWindowView;
                                if (frameLayout5 == null) {
                                    frameLayout5 = null;
                                }
                                backgroundAnimatableView.getClass();
                                View view = (View) backgroundAnimatableView;
                                FrameLayout frameLayout6 = systemEventChipAnimationController.animationWindowView;
                                if (frameLayout6 == null) {
                                    frameLayout6 = null;
                                }
                                int i = frameLayout6.isLayoutRtl() ? statusBarContentInsetsForCurrentRotation.left : statusBarContentInsetsForCurrentRotation.right;
                                FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-2, -2);
                                layoutParams2.gravity = 8388629;
                                layoutParams2.setMarginEnd(i);
                                frameLayout5.addView(view, layoutParams2);
                                view.setAlpha(0.0f);
                                FrameLayout frameLayout7 = systemEventChipAnimationController.animationWindowView;
                                if (frameLayout7 == null) {
                                    frameLayout7 = null;
                                }
                                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(((View) frameLayout7.getParent()).getWidth(), Integer.MIN_VALUE);
                                FrameLayout frameLayout8 = systemEventChipAnimationController.animationWindowView;
                                if (frameLayout8 == null) {
                                    frameLayout8 = null;
                                }
                                view.measure(makeMeasureSpec, View.MeasureSpec.makeMeasureSpec(((View) frameLayout8.getParent()).getHeight(), Integer.MIN_VALUE));
                                systemEventChipAnimationController.updateChipBounds(backgroundAnimatableView, statusBarContentInsetsProvider.getStatusBarContentAreaForRotation(RotationUtils.getExactRotation(statusBarContentInsetsProvider.context)));
                                systemEventChipAnimationController.currentAnimatedView = backgroundAnimatableView;
                                systemStatusAnimationSchedulerImpl.currentlyRunningAnimationJob = BuildersKt.launch$default(systemStatusAnimationSchedulerImpl.coroutineScope, null, null, new SystemStatusAnimationSchedulerImpl$startAnimationLifecycle$1(systemStatusAnimationSchedulerImpl, statusEvent, null), 3);
                            } else {
                                StateFlowImpl stateFlowImpl = systemStatusAnimationSchedulerImpl.animationState;
                                stateFlowImpl.getClass();
                                stateFlowImpl.updateState(null, 5);
                                systemStatusAnimationSchedulerImpl.notifyTransitionToPersistentDot(statusEvent);
                            }
                            systemStatusAnimationSchedulerImpl.scheduledEvent.setValue(null);
                            break;
                        }
                        break;
                    default:
                        int intValue2 = ((Number) obj).intValue();
                        final SystemStatusAnimationSchedulerLogger systemStatusAnimationSchedulerLogger = systemStatusAnimationSchedulerImpl.logger;
                        if (systemStatusAnimationSchedulerLogger != null) {
                            LogLevel logLevel = LogLevel.DEBUG;
                            Function1 function1 = new Function1() { // from class: com.android.systemui.statusbar.events.SystemStatusAnimationSchedulerLogger$logAnimationStateUpdate$2
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj2) {
                                    SystemStatusAnimationSchedulerLogger systemStatusAnimationSchedulerLogger2 = SystemStatusAnimationSchedulerLogger.this;
                                    int int1 = ((LogMessage) obj2).getInt1();
                                    systemStatusAnimationSchedulerLogger2.getClass();
                                    return "AnimationState update: ".concat(SystemStatusAnimationSchedulerLogger.name(int1));
                                }
                            };
                            LogBuffer logBuffer = systemStatusAnimationSchedulerLogger.logBuffer;
                            LogMessage obtain = logBuffer.obtain("SystemStatusAnimationSchedulerLog", logLevel, function1, null);
                            ((LogMessageImpl) obtain).int1 = intValue2;
                            logBuffer.commit(obtain);
                            break;
                        }
                        break;
                }
                return unit;
            }
        }

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return SystemStatusAnimationSchedulerImpl.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SystemStatusAnimationSchedulerImpl systemStatusAnimationSchedulerImpl = SystemStatusAnimationSchedulerImpl.this;
                Flow debounce = FlowKt.debounce(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(systemStatusAnimationSchedulerImpl.animationState, systemStatusAnimationSchedulerImpl.scheduledEvent, new C01901(3, null)), 100L);
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(SystemStatusAnimationSchedulerImpl.this, 0);
                this.label = 1;
                if (debounce.collect(anonymousClass2, this) == coroutineSingletons) {
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
    /* renamed from: com.android.systemui.statusbar.events.SystemStatusAnimationSchedulerImpl$2, reason: invalid class name */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        int label;

        public AnonymousClass2(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return SystemStatusAnimationSchedulerImpl.this.new AnonymousClass2(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            ((AnonymousClass2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
            SystemStatusAnimationSchedulerImpl systemStatusAnimationSchedulerImpl = SystemStatusAnimationSchedulerImpl.this;
            StateFlowImpl stateFlowImpl = systemStatusAnimationSchedulerImpl.animationState;
            AnonymousClass1.AnonymousClass2 anonymousClass2 = new AnonymousClass1.AnonymousClass2(systemStatusAnimationSchedulerImpl, 1);
            this.label = 1;
            stateFlowImpl.collect(anonymousClass2, this);
            return coroutineSingletons;
        }
    }

    public SystemStatusAnimationSchedulerImpl(SystemEventCoordinator systemEventCoordinator, SystemEventChipAnimationController systemEventChipAnimationController, StatusBarWindowControllerImpl statusBarWindowControllerImpl, DumpManager dumpManager, SystemClock systemClock, CoroutineScope coroutineScope, SystemStatusAnimationSchedulerLogger systemStatusAnimationSchedulerLogger) {
        this.coordinator = systemEventCoordinator;
        this.chipAnimationController = systemEventChipAnimationController;
        this.statusBarWindowController = statusBarWindowControllerImpl;
        this.systemClock = systemClock;
        this.coroutineScope = coroutineScope;
        this.logger = systemStatusAnimationSchedulerLogger;
        systemEventCoordinator.scheduler = this;
        dumpManager.registerCriticalDumpable("SystemStatusAnimationSchedulerImpl", this);
        BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass1(null), 3);
        BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass2(null), 3);
    }

    public static final void access$runChipDisappearAnimation(SystemStatusAnimationSchedulerImpl systemStatusAnimationSchedulerImpl) {
        AnimatorSet notifyTransitionToPersistentDot;
        systemStatusAnimationSchedulerImpl.getClass();
        Assert.isMainThread();
        ArrayList arrayList = new ArrayList();
        Iterator it = systemStatusAnimationSchedulerImpl.listeners.iterator();
        while (it.hasNext()) {
            Animator onSystemEventAnimationFinish = ((SystemStatusAnimationCallback) it.next()).onSystemEventAnimationFinish(systemStatusAnimationSchedulerImpl.hasPersistentDot);
            if (onSystemEventAnimationFinish != null) {
                arrayList.add(onSystemEventAnimationFinish);
            }
        }
        arrayList.add(systemStatusAnimationSchedulerImpl.chipAnimationController.onSystemEventAnimationFinish(systemStatusAnimationSchedulerImpl.hasPersistentDot));
        if (systemStatusAnimationSchedulerImpl.hasPersistentDot && (notifyTransitionToPersistentDot = systemStatusAnimationSchedulerImpl.notifyTransitionToPersistentDot(systemStatusAnimationSchedulerImpl.currentlyDisplayedEvent)) != null) {
            arrayList.add(notifyTransitionToPersistentDot);
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(arrayList);
        StateFlowImpl stateFlowImpl = systemStatusAnimationSchedulerImpl.animationState;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, 4);
        animatorSet.addListener(new SystemStatusAnimationSchedulerImpl$runChipAppearAnimation$1(systemStatusAnimationSchedulerImpl, 1));
        animatorSet.start();
        systemStatusAnimationSchedulerImpl.currentlyDisplayedEvent = null;
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void addCallback(Object obj) {
        SystemStatusAnimationCallback systemStatusAnimationCallback = (SystemStatusAnimationCallback) obj;
        Assert.isMainThread();
        if (this.listeners.isEmpty()) {
            SystemEventCoordinator systemEventCoordinator = this.coordinator;
            systemEventCoordinator.batteryController.addCallback(systemEventCoordinator.batteryStateListener);
            systemEventCoordinator.privacyController.addCallback(systemEventCoordinator.privacyStateListener);
            ConnectedDisplayEvent connectedDisplayEvent = new ConnectedDisplayEvent();
            connectedDisplayEvent.contentDescription = systemEventCoordinator.context.getString(R.string.connected_display_icon_desc);
            systemEventCoordinator.connectedDisplayCollectionJob = FlowKt.launchIn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(systemEventCoordinator.onDisplayConnectedFlow, new SystemEventCoordinator$startConnectedDisplayCollection$1(systemEventCoordinator, connectedDisplayEvent, null), 0), systemEventCoordinator.appScope);
        }
        this.listeners.add(systemStatusAnimationCallback);
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("Scheduled event: " + this.scheduledEvent.getValue());
        printWriter.println("Currently displayed event: " + this.currentlyDisplayedEvent);
        ActiveUnlockConfig$$ExternalSyntheticOutline0.m("Has persistent privacy dot: ", this.hasPersistentDot, printWriter);
        printWriter.println("Animation state: " + this.animationState.getValue());
        printWriter.println("Listeners:");
        if (this.listeners.isEmpty()) {
            printWriter.println("(none)");
            return;
        }
        Iterator it = this.listeners.iterator();
        while (it.hasNext()) {
            printWriter.println("  " + ((SystemStatusAnimationCallback) it.next()));
        }
    }

    public final void notifyHidePersistentDot() {
        Assert.isMainThread();
        SystemStatusAnimationSchedulerLogger systemStatusAnimationSchedulerLogger = this.logger;
        if (systemStatusAnimationSchedulerLogger != null) {
            systemStatusAnimationSchedulerLogger.logBuffer.log("SystemStatusAnimationSchedulerLog", LogLevel.DEBUG, "Hide persistent dot callback invoked", null);
        }
        Set set = this.listeners;
        ArrayList arrayList = new ArrayList();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            ((SystemStatusAnimationCallback) it.next()).onHidePersistentDot();
        }
        if (arrayList.isEmpty()) {
            return;
        }
        new AnimatorSet().playTogether(arrayList);
    }

    public final AnimatorSet notifyTransitionToPersistentDot(StatusEvent statusEvent) {
        SystemStatusAnimationSchedulerLogger systemStatusAnimationSchedulerLogger = this.logger;
        if (systemStatusAnimationSchedulerLogger != null) {
            systemStatusAnimationSchedulerLogger.logBuffer.log("SystemStatusAnimationSchedulerLog", LogLevel.DEBUG, "Transition to persistent dot callback invoked", null);
        }
        Set set = this.listeners;
        ArrayList arrayList = new ArrayList();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            ((SystemStatusAnimationCallback) it.next()).onSystemStatusAnimationTransitionToPersistentDot(statusEvent != null ? statusEvent.getContentDescription() : null);
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(arrayList);
        return animatorSet;
    }

    public final void onStatusEvent(StatusEvent statusEvent) {
        StandaloneCoroutine standaloneCoroutine;
        Assert.isMainThread();
        ((SystemClockImpl) this.systemClock).getClass();
        if ((android.os.SystemClock.uptimeMillis() - Process.getStartUptimeMillis() >= 5000 || statusEvent.getForceVisible()) && DeviceConfig.getBoolean("privacy", "enable_immersive_indicator", true)) {
            int priority = statusEvent.getPriority();
            StateFlowImpl stateFlowImpl = this.scheduledEvent;
            StatusEvent statusEvent2 = (StatusEvent) stateFlowImpl.getValue();
            int priority2 = statusEvent2 != null ? statusEvent2.getPriority() : -1;
            StateFlowImpl stateFlowImpl2 = this.animationState;
            SystemStatusAnimationSchedulerLogger systemStatusAnimationSchedulerLogger = this.logger;
            if (priority > priority2) {
                int priority3 = statusEvent.getPriority();
                StatusEvent statusEvent3 = this.currentlyDisplayedEvent;
                if (priority3 > (statusEvent3 != null ? statusEvent3.getPriority() : -1) && !this.hasPersistentDot) {
                    if (systemStatusAnimationSchedulerLogger != null) {
                        LogLevel logLevel = LogLevel.DEBUG;
                        SystemStatusAnimationSchedulerLogger$logScheduleEvent$2 systemStatusAnimationSchedulerLogger$logScheduleEvent$2 = new Function1() { // from class: com.android.systemui.statusbar.events.SystemStatusAnimationSchedulerLogger$logScheduleEvent$2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                LogMessage logMessage = (LogMessage) obj;
                                String str1 = logMessage.getStr1();
                                boolean bool1 = logMessage.getBool1();
                                int int1 = logMessage.getInt1();
                                boolean bool2 = logMessage.getBool2();
                                StringBuilder m = CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0.m("Scheduling event: ", str1, "(forceVisible=", bool1, ", priority=");
                                m.append(int1);
                                m.append(", showAnimation=");
                                m.append(bool2);
                                m.append(")");
                                return m.toString();
                            }
                        };
                        LogBuffer logBuffer = systemStatusAnimationSchedulerLogger.logBuffer;
                        LogMessage obtain = logBuffer.obtain("SystemStatusAnimationSchedulerLog", logLevel, systemStatusAnimationSchedulerLogger$logScheduleEvent$2, null);
                        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
                        logMessageImpl.str1 = statusEvent.getClass().getSimpleName();
                        logMessageImpl.int1 = statusEvent.getPriority();
                        logMessageImpl.bool1 = statusEvent.getForceVisible();
                        logMessageImpl.bool2 = statusEvent.getShowAnimation();
                        logBuffer.commit(obtain);
                    }
                    stateFlowImpl.getClass();
                    stateFlowImpl.updateState(null, statusEvent);
                    if (this.currentlyDisplayedEvent != null && ((standaloneCoroutine = this.eventCancellationJob) == null || !standaloneCoroutine.isActive())) {
                        this.eventCancellationJob = BuildersKt.launch$default(this.coroutineScope, null, null, new SystemStatusAnimationSchedulerImpl$cancelCurrentlyDisplayedEvent$1(this, null), 3);
                        return;
                    } else {
                        if (((Number) stateFlowImpl2.getValue()).intValue() == 0) {
                            stateFlowImpl2.updateState(null, 1);
                            return;
                        }
                        return;
                    }
                }
            }
            StatusEvent statusEvent4 = this.currentlyDisplayedEvent;
            if (statusEvent4 != null && statusEvent4.shouldUpdateFromEvent(statusEvent)) {
                if (systemStatusAnimationSchedulerLogger != null) {
                    systemStatusAnimationSchedulerLogger.logUpdateEvent(statusEvent, ((Number) stateFlowImpl2.getValue()).intValue());
                }
                StatusEvent statusEvent5 = this.currentlyDisplayedEvent;
                if (statusEvent5 != null) {
                    statusEvent5.updateFromEvent(statusEvent);
                }
                if (statusEvent.getForceVisible()) {
                    this.hasPersistentDot = true;
                    return;
                }
                return;
            }
            StatusEvent statusEvent6 = (StatusEvent) stateFlowImpl.getValue();
            if (statusEvent6 != null && statusEvent6.shouldUpdateFromEvent(statusEvent)) {
                if (systemStatusAnimationSchedulerLogger != null) {
                    systemStatusAnimationSchedulerLogger.logUpdateEvent(statusEvent, ((Number) stateFlowImpl2.getValue()).intValue());
                }
                StatusEvent statusEvent7 = (StatusEvent) stateFlowImpl.getValue();
                if (statusEvent7 != null) {
                    statusEvent7.updateFromEvent(statusEvent);
                    return;
                }
                return;
            }
            if (systemStatusAnimationSchedulerLogger != null) {
                LogLevel logLevel2 = LogLevel.DEBUG;
                SystemStatusAnimationSchedulerLogger$logIgnoreEvent$2 systemStatusAnimationSchedulerLogger$logIgnoreEvent$2 = new Function1() { // from class: com.android.systemui.statusbar.events.SystemStatusAnimationSchedulerLogger$logIgnoreEvent$2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        LogMessage logMessage = (LogMessage) obj;
                        String str1 = logMessage.getStr1();
                        boolean bool1 = logMessage.getBool1();
                        int int1 = logMessage.getInt1();
                        boolean bool2 = logMessage.getBool2();
                        StringBuilder m = CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0.m("Ignore event: ", str1, "(forceVisible=", bool1, ", priority=");
                        m.append(int1);
                        m.append(", showAnimation=");
                        m.append(bool2);
                        m.append(")");
                        return m.toString();
                    }
                };
                LogBuffer logBuffer2 = systemStatusAnimationSchedulerLogger.logBuffer;
                LogMessage obtain2 = logBuffer2.obtain("SystemStatusAnimationSchedulerLog", logLevel2, systemStatusAnimationSchedulerLogger$logIgnoreEvent$2, null);
                LogMessageImpl logMessageImpl2 = (LogMessageImpl) obtain2;
                logMessageImpl2.str1 = statusEvent.getClass().getSimpleName();
                logMessageImpl2.int1 = statusEvent.getPriority();
                logMessageImpl2.bool1 = statusEvent.getForceVisible();
                logMessageImpl2.bool2 = statusEvent.getShowAnimation();
                logBuffer2.commit(obtain2);
            }
        }
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void removeCallback(Object obj) {
        Assert.isMainThread();
        this.listeners.remove((SystemStatusAnimationCallback) obj);
        if (this.listeners.isEmpty()) {
            SystemEventCoordinator systemEventCoordinator = this.coordinator;
            ((BatteryControllerImpl) systemEventCoordinator.batteryController).removeCallback(systemEventCoordinator.batteryStateListener);
            systemEventCoordinator.privacyController.removeCallback(systemEventCoordinator.privacyStateListener);
            StandaloneCoroutine standaloneCoroutine = systemEventCoordinator.connectedDisplayCollectionJob;
            if (standaloneCoroutine != null) {
                standaloneCoroutine.cancel(null);
            }
        }
    }
}
