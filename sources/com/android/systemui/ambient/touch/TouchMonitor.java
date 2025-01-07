package com.android.systemui.ambient.touch;

import android.graphics.Rect;
import android.graphics.Region;
import android.view.Choreographer;
import android.view.GestureDetector;
import android.view.IWindowManager;
import android.view.InputEvent;
import android.view.MotionEvent;
import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.constraintlayout.core.PriorityGoalRow$GoalVariableAccessor$$ExternalSyntheticOutline0;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import com.android.systemui.ambient.touch.TouchMonitor;
import com.android.systemui.common.ui.domain.interactor.ConfigurationInteractor;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.log.core.Logger;
import com.android.systemui.settings.DisplayTracker;
import com.android.systemui.shared.system.InputChannelCompat$InputEventListener;
import com.android.systemui.shared.system.InputChannelCompat$InputEventReceiver;
import com.android.systemui.shared.system.InputMonitorCompat;
import com.android.systemui.util.concurrency.GlobalConcurrencyModule_ProvideMainLooperFactory;
import com.android.systemui.util.display.DisplayHelper;
import com.android.systemui.util.kotlin.JavaAdapterKt;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.StandaloneCoroutine;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TouchMonitor {
    public static int sNextInstanceId;
    public final Executor mBackgroundExecutor;
    public StandaloneCoroutine mBoundsFlow;
    public final ConfigurationInteractor mConfigurationInteractor;
    public InputSession mCurrentInputSession;
    public final int mDisplayId;
    public AnonymousClass2 mGestureExclusionListener;
    public final Collection mHandlers;
    public boolean mInitialized;
    public final DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory mInputSessionFactory;
    public final Lifecycle mLifecycle;
    public final Logger mLogger;
    public final String mLoggingName;
    public final Executor mMainExecutor;
    public Rect mMaxBounds;
    public boolean mStopMonitoringPending;
    public final IWindowManager mWindowManagerService;
    public Rect mExclusionRect = null;
    public final TouchMonitor$$ExternalSyntheticLambda11 mMaxBoundsConsumer = new TouchMonitor$$ExternalSyntheticLambda11(0, this);
    public final AnonymousClass1 mLifecycleObserver = new DefaultLifecycleObserver() { // from class: com.android.systemui.ambient.touch.TouchMonitor.1
        @Override // androidx.lifecycle.DefaultLifecycleObserver
        public final void onDestroy$1() {
            TouchMonitor.this.destroy();
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver
        public final void onPause$1() {
            TouchMonitor.this.stopMonitoring(false);
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver
        public final void onResume$1() {
            TouchMonitor.this.startMonitoring();
        }
    };
    public final HashSet mActiveTouchSessions = new HashSet();
    public final AnonymousClass3 mInputEventListener = new AnonymousClass3();
    public final AnonymousClass4 mOnGestureListener = new GestureDetector.OnGestureListener() { // from class: com.android.systemui.ambient.touch.TouchMonitor.4
        public final boolean evaluate(final Evaluator evaluator) {
            final HashSet hashSet = new HashSet();
            boolean anyMatch = TouchMonitor.this.mActiveTouchSessions.stream().map(new Function() { // from class: com.android.systemui.ambient.touch.TouchMonitor$4$$ExternalSyntheticLambda8
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    final TouchMonitor.Evaluator evaluator2 = TouchMonitor.Evaluator.this;
                    Set set = hashSet;
                    TouchMonitor.TouchSessionImpl touchSessionImpl = (TouchMonitor.TouchSessionImpl) obj;
                    boolean anyMatch2 = touchSessionImpl.mGestureListeners.stream().map(new Function() { // from class: com.android.systemui.ambient.touch.TouchMonitor$4$$ExternalSyntheticLambda0
                        @Override // java.util.function.Function
                        public final Object apply(Object obj2) {
                            return Boolean.valueOf(TouchMonitor.Evaluator.this.evaluate((GestureDetector.OnGestureListener) obj2));
                        }
                    }).anyMatch(new TouchMonitor$4$$ExternalSyntheticLambda1(0));
                    if (anyMatch2) {
                        set.add(touchSessionImpl);
                    }
                    return Boolean.valueOf(anyMatch2);
                }
            }).anyMatch(new TouchMonitor$4$$ExternalSyntheticLambda1(1));
            if (anyMatch) {
                TouchMonitor touchMonitor = TouchMonitor.this;
                Collection<?> collection = (Collection) touchMonitor.mActiveTouchSessions.stream().filter(new Predicate() { // from class: com.android.systemui.ambient.touch.TouchMonitor$$ExternalSyntheticLambda0
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return !hashSet.contains((TouchMonitor.TouchSessionImpl) obj);
                    }
                }).collect(Collectors.toCollection(new TouchMonitor$$ExternalSyntheticLambda1()));
                collection.forEach(new TouchMonitor$$ExternalSyntheticLambda2(0));
                touchMonitor.mActiveTouchSessions.removeAll(collection);
            }
            return anyMatch;
        }

        public final void observe(Consumer consumer) {
            TouchMonitor.this.mActiveTouchSessions.stream().map(new TouchMonitor$$ExternalSyntheticLambda13(4)).flatMap(new TouchMonitor$$ExternalSyntheticLambda13(3)).forEach(new TouchMonitor$$ExternalSyntheticLambda11(2, consumer));
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public final boolean onDown(MotionEvent motionEvent) {
            return evaluate(new TouchMonitor$4$$ExternalSyntheticLambda4(motionEvent, 1));
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public final boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return evaluate(new TouchMonitor$4$$ExternalSyntheticLambda6(motionEvent, motionEvent2, f, f2, 1));
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public final void onLongPress(MotionEvent motionEvent) {
            observe(new TouchMonitor$4$$ExternalSyntheticLambda2(motionEvent, 0));
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public final boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return evaluate(new TouchMonitor$4$$ExternalSyntheticLambda6(motionEvent, motionEvent2, f, f2, 0));
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public final void onShowPress(MotionEvent motionEvent) {
            observe(new TouchMonitor$4$$ExternalSyntheticLambda2(motionEvent, 1));
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public final boolean onSingleTapUp(MotionEvent motionEvent) {
            return evaluate(new TouchMonitor$4$$ExternalSyntheticLambda4(motionEvent, 0));
        }
    };

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.ambient.touch.TouchMonitor$3, reason: invalid class name */
    public final class AnonymousClass3 implements InputChannelCompat$InputEventListener {
        public AnonymousClass3() {
        }

        @Override // com.android.systemui.shared.system.InputChannelCompat$InputEventListener
        public final void onInputEvent(final InputEvent inputEvent) {
            TouchMonitor touchMonitor = TouchMonitor.this;
            if (touchMonitor.mActiveTouchSessions.isEmpty()) {
                HashMap hashMap = new HashMap();
                for (TouchHandler touchHandler : touchMonitor.mHandlers) {
                    if (touchHandler.isEnabled().booleanValue()) {
                        Rect rect = touchMonitor.mMaxBounds;
                        Region obtain = Region.obtain();
                        touchHandler.getTouchInitiationRegion(rect, obtain, touchMonitor.mExclusionRect);
                        if (!obtain.isEmpty()) {
                            if (inputEvent instanceof MotionEvent) {
                                MotionEvent motionEvent = (MotionEvent) inputEvent;
                                if (!obtain.contains(Math.round(motionEvent.getX()), Math.round(motionEvent.getY()))) {
                                }
                            }
                        }
                        TouchSessionImpl touchSessionImpl = new TouchSessionImpl(touchMonitor, rect);
                        touchMonitor.mActiveTouchSessions.add(touchSessionImpl);
                        hashMap.put(touchHandler, touchSessionImpl);
                    }
                }
                hashMap.forEach(new BiConsumer() { // from class: com.android.systemui.ambient.touch.TouchMonitor$3$$ExternalSyntheticLambda0
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        TouchMonitor.AnonymousClass3 anonymousClass3 = TouchMonitor.AnonymousClass3.this;
                        InputEvent inputEvent2 = inputEvent;
                        final TouchHandler touchHandler2 = (TouchHandler) obj;
                        final TouchMonitor.TouchSessionImpl touchSessionImpl2 = (TouchMonitor.TouchSessionImpl) obj2;
                        anonymousClass3.getClass();
                        if (inputEvent2 instanceof MotionEvent) {
                            MotionEvent motionEvent2 = (MotionEvent) inputEvent2;
                            final int round = Math.round(motionEvent2.getX());
                            final int round2 = Math.round(motionEvent2.getY());
                            TouchMonitor.this.mLogger.i(new TouchMonitor$$ExternalSyntheticLambda5(2), new Function1() { // from class: com.android.systemui.ambient.touch.TouchMonitor$3$$ExternalSyntheticLambda5
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj3) {
                                    LogMessage logMessage = (LogMessage) obj3;
                                    logMessage.setStr1(TouchHandler.this.getClass().getSimpleName());
                                    logMessage.setLong1(round);
                                    logMessage.setLong2(round2);
                                    logMessage.setInt1(touchSessionImpl2.hashCode());
                                    return Unit.INSTANCE;
                                }
                            });
                        }
                        touchHandler2.onSessionStart(touchSessionImpl2);
                    }
                });
            }
            touchMonitor.mActiveTouchSessions.stream().map(new TouchMonitor$$ExternalSyntheticLambda13(2)).flatMap(new TouchMonitor$$ExternalSyntheticLambda13(3)).forEach(new TouchMonitor$$ExternalSyntheticLambda11(1, inputEvent));
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Evaluator {
        boolean evaluate(GestureDetector.OnGestureListener onGestureListener);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TouchSessionImpl {
        public final Rect mBounds;
        public final TouchMonitor mTouchMonitor;
        public final HashSet mEventListeners = new HashSet();
        public final HashSet mGestureListeners = new HashSet();
        public final HashSet mCallbacks = new HashSet();

        /* renamed from: -$$Nest$monRemoved, reason: not valid java name */
        public static void m782$$Nest$monRemoved(TouchSessionImpl touchSessionImpl) {
            touchSessionImpl.mEventListeners.clear();
            touchSessionImpl.mGestureListeners.clear();
            Iterator it = touchSessionImpl.mCallbacks.iterator();
            while (it.hasNext()) {
                ((TouchHandler$TouchSession$Callback) it.next()).onRemoved();
                it.remove();
            }
        }

        public TouchSessionImpl(TouchMonitor touchMonitor, Rect rect) {
            this.mTouchMonitor = touchMonitor;
            this.mBounds = rect;
        }

        public final void pop() {
            final TouchMonitor touchMonitor = this.mTouchMonitor;
            touchMonitor.getClass();
            CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: com.android.systemui.ambient.touch.TouchMonitor$$ExternalSyntheticLambda3
                @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
                public final Object attachCompleter(final CallbackToFutureAdapter.Completer completer) {
                    final TouchMonitor touchMonitor2 = TouchMonitor.this;
                    Executor executor = touchMonitor2.mMainExecutor;
                    final TouchMonitor.TouchSessionImpl touchSessionImpl = this;
                    executor.execute(new Runnable() { // from class: com.android.systemui.ambient.touch.TouchMonitor$$ExternalSyntheticLambda4
                        @Override // java.lang.Runnable
                        public final void run() {
                            TouchMonitor touchMonitor3 = TouchMonitor.this;
                            TouchMonitor.TouchSessionImpl touchSessionImpl2 = touchSessionImpl;
                            CallbackToFutureAdapter.Completer completer2 = completer;
                            touchMonitor3.mLogger.i(new TouchMonitor$$ExternalSyntheticLambda5(0), new TouchMonitor$$ExternalSyntheticLambda6(0, touchSessionImpl2));
                            if (touchMonitor3.mActiveTouchSessions.remove(touchSessionImpl2)) {
                                TouchMonitor.TouchSessionImpl.m782$$Nest$monRemoved(touchSessionImpl2);
                                completer2.set(null);
                            }
                            if (touchMonitor3.mActiveTouchSessions.isEmpty() && touchMonitor3.mInitialized) {
                                if (touchMonitor3.mStopMonitoringPending) {
                                    touchMonitor3.stopMonitoring(false);
                                } else {
                                    touchMonitor3.startMonitoring();
                                }
                            }
                        }
                    });
                    return "DreamOverlayTouchMonitor::pop";
                }
            });
        }
    }

    /* JADX WARN: Type inference failed for: r6v3, types: [com.android.systemui.ambient.touch.TouchMonitor$1] */
    /* JADX WARN: Type inference failed for: r6v6, types: [com.android.systemui.ambient.touch.TouchMonitor$4] */
    public TouchMonitor(Executor executor, Executor executor2, Lifecycle lifecycle, DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory, DisplayHelper displayHelper, ConfigurationInteractor configurationInteractor, Set set, IWindowManager iWindowManager, int i, String str, LogBuffer logBuffer) {
        this.mDisplayId = i;
        this.mHandlers = set;
        this.mInputSessionFactory = daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory;
        this.mMainExecutor = executor;
        this.mBackgroundExecutor = executor2;
        this.mLifecycle = lifecycle;
        this.mWindowManagerService = iWindowManager;
        this.mConfigurationInteractor = configurationInteractor;
        StringBuilder m = PriorityGoalRow$GoalVariableAccessor$$ExternalSyntheticOutline0.m(str, ":TouchMonitor[");
        int i2 = sNextInstanceId;
        sNextInstanceId = i2 + 1;
        String m2 = PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(m, i2, "]");
        this.mLoggingName = m2;
        this.mLogger = new Logger(logBuffer, m2);
    }

    public final void destroy() {
        if (this.mInitialized) {
            stopMonitoring(true);
            this.mLifecycle.removeObserver(this.mLifecycleObserver);
            this.mBoundsFlow.cancelInternal(new CancellationException());
            Iterator it = this.mHandlers.iterator();
            while (it.hasNext()) {
                ((TouchHandler) it.next()).onDestroy();
            }
            this.mInitialized = false;
        }
    }

    public final void init() {
        if (this.mInitialized) {
            throw new IllegalStateException("TouchMonitor already initialized");
        }
        AnonymousClass1 anonymousClass1 = this.mLifecycleObserver;
        Lifecycle lifecycle = this.mLifecycle;
        lifecycle.addObserver(anonymousClass1);
        this.mBoundsFlow = JavaAdapterKt.collectFlow(lifecycle, this.mConfigurationInteractor.maxBounds, this.mMaxBoundsConsumer);
        this.mInitialized = true;
    }

    public final void startMonitoring() {
        boolean z = this.mInitialized;
        Logger logger = this.mLogger;
        if (!z) {
            logger.w("attempting to startMonitoring when not initialized");
            return;
        }
        logger.i("startMonitoring(): monitoring started");
        stopMonitoring(true);
        this.mBackgroundExecutor.execute(new TouchMonitor$$ExternalSyntheticLambda7(this, 2));
        AnonymousClass3 anonymousClass3 = this.mInputEventListener;
        AnonymousClass4 anonymousClass4 = this.mOnGestureListener;
        DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory = this.mInputSessionFactory;
        daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory.getClass();
        String str = this.mLoggingName;
        str.getClass();
        anonymousClass3.getClass();
        anonymousClass4.getClass();
        ((DisplayTracker) daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory.sysUIGoogleSysUIComponentImpl.provideDisplayTrackerProvider.get()).getClass();
        this.mCurrentInputSession = new InputSession(new InputMonitorCompat(str, 0), new GestureDetector(anonymousClass4), anonymousClass3, (Choreographer) daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory.sysUIGoogleGlobalRootComponentImpl.providesChoreographerProvider.get(), GlobalConcurrencyModule_ProvideMainLooperFactory.provideMainLooper(), true);
    }

    public final void stopMonitoring(boolean z) {
        this.mExclusionRect = null;
        this.mBackgroundExecutor.execute(new TouchMonitor$$ExternalSyntheticLambda7(this, 0));
        if (this.mCurrentInputSession == null) {
            return;
        }
        boolean isEmpty = this.mActiveTouchSessions.isEmpty();
        Logger logger = this.mLogger;
        if (!isEmpty && !z) {
            logger.i(new TouchMonitor$$ExternalSyntheticLambda5(1), new TouchMonitor$$ExternalSyntheticLambda6(1, this));
            this.mStopMonitoringPending = true;
            return;
        }
        this.mMainExecutor.execute(new TouchMonitor$$ExternalSyntheticLambda7(this, 1));
        InputSession inputSession = this.mCurrentInputSession;
        InputChannelCompat$InputEventReceiver inputChannelCompat$InputEventReceiver = inputSession.mInputEventReceiver;
        if (inputChannelCompat$InputEventReceiver != null) {
            inputChannelCompat$InputEventReceiver.dispose();
        }
        inputSession.mInputMonitor.dispose();
        this.mCurrentInputSession = null;
        this.mStopMonitoringPending = false;
        logger.i("stopMonitoring(): monitoring finished");
    }
}
