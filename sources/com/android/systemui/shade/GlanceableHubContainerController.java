package com.android.systemui.shade;

import android.content.Context;
import android.graphics.Rect;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.ArraySet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import androidx.compose.foundation.text.ValidatingOffsetMappingKt$$ExternalSyntheticOutline0;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import com.android.keyguard.logging.BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0;
import com.android.keyguard.logging.CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0;
import com.android.systemui.ambient.touch.TouchMonitor;
import com.android.systemui.communal.domain.interactor.CommunalInteractor;
import com.android.systemui.communal.ui.compose.CommunalContent;
import com.android.systemui.communal.ui.viewmodel.CommunalViewModel;
import com.android.systemui.communal.util.CommunalColors;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.keyguard.shared.model.Edge;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.log.core.Logger;
import com.android.systemui.media.controls.ui.controller.KeyguardMediaController;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;
import com.android.systemui.scene.shared.model.SceneDataSourceDelegator;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.shade.domain.interactor.ShadeInteractorImpl;
import com.android.systemui.statusbar.lockscreen.LockscreenSmartspaceController;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.util.animation.UniqueObjectHostView;
import com.android.systemui.util.kotlin.BooleanFlowOperators$not$$inlined$map$1;
import com.android.systemui.util.kotlin.JavaAdapterKt;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$AmbientTouchComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$BooleanRef;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class GlanceableHubContainerController implements LifecycleOwner {
    public final DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory ambientTouchComponentFactory;
    public boolean anyBouncerShowing;
    public final CommunalColors communalColors;
    public View communalContainerView;
    public CommunalWrapper communalContainerWrapper;
    public final CommunalContent communalContent;
    public final CommunalInteractor communalInteractor;
    public final CommunalViewModel communalViewModel;
    public final SceneDataSourceDelegator dataSourceDelegator;
    public boolean hubShowing;
    public boolean inEditModeTransition;
    public boolean isDreaming;
    public boolean isTrackingHubTouch;
    public final KeyguardInteractor keyguardInteractor;
    public final KeyguardMediaController keyguardMediaController;
    public final KeyguardTransitionInteractor keyguardTransitionInteractor;
    public final LockscreenSmartspaceController lockscreenSmartspaceController;
    public final Logger logger;
    public final NotificationStackScrollLayoutController notificationStackScrollLayoutController;
    public boolean onLockscreen;
    public final PowerManager powerManager;
    public boolean shadeConsumingTouches;
    public final ShadeInteractor shadeInteractor;
    public boolean shadeShowing;
    public boolean shadeShowingAndConsumingTouches;
    public TouchMonitor touchMonitor;
    public boolean touchTakenByKeyguardGesture;
    public boolean userNotInteractiveAtShadeFullyExpanded;
    public final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
    public final GlanceableHubContainerController$touchLifecycleLogger$1 touchLifecycleLogger = new LifecycleEventObserver() { // from class: com.android.systemui.shade.GlanceableHubContainerController$touchLifecycleLogger$1
        @Override // androidx.lifecycle.LifecycleEventObserver
        public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
            GlanceableHubContainerController glanceableHubContainerController = GlanceableHubContainerController.this;
            Logger logger = glanceableHubContainerController.logger;
            AnonymousClass1 anonymousClass1 = new Function1() { // from class: com.android.systemui.shade.GlanceableHubContainerController$touchLifecycleLogger$1.1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    LogMessage logMessage = (LogMessage) obj;
                    String str1 = logMessage.getStr1();
                    boolean bool1 = logMessage.getBool1();
                    boolean bool2 = logMessage.getBool2();
                    boolean bool3 = logMessage.getBool3();
                    boolean bool4 = logMessage.getBool4();
                    StringBuilder m = CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0.m("Touch handler lifecycle changed to ", str1, ". hubShowing: ", bool1, ", shadeShowingAndConsumingTouches: ");
                    BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0.m(m, bool2, ", anyBouncerShowing: ", bool3, ", inEditModeTransition: ");
                    m.append(bool4);
                    return m.toString();
                }
            };
            LogMessage obtain = logger.getBuffer().obtain(logger.getTag(), LogLevel.DEBUG, anonymousClass1, null);
            obtain.setStr1(event.toString());
            obtain.setBool1(glanceableHubContainerController.hubShowing);
            obtain.setBool2(glanceableHubContainerController.shadeShowingAndConsumingTouches);
            obtain.setBool3(glanceableHubContainerController.anyBouncerShowing);
            obtain.setBool4(glanceableHubContainerController.inEditModeTransition);
            logger.getBuffer().commit(obtain);
        }
    };

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CommunalWrapper extends FrameLayout {
        public final Set consumers;

        public CommunalWrapper(Context context) {
            super(context);
            this.consumers = new ArraySet();
        }

        @Override // android.view.ViewGroup, android.view.ViewParent
        public final void requestDisallowInterceptTouchEvent(boolean z) {
            Iterator it = this.consumers.iterator();
            while (it.hasNext()) {
                ((Consumer) it.next()).accept(Boolean.valueOf(z));
            }
            super.requestDisallowInterceptTouchEvent(z);
        }
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [com.android.systemui.shade.GlanceableHubContainerController$touchLifecycleLogger$1] */
    public GlanceableHubContainerController(CommunalInteractor communalInteractor, CommunalViewModel communalViewModel, KeyguardInteractor keyguardInteractor, KeyguardTransitionInteractor keyguardTransitionInteractor, ShadeInteractor shadeInteractor, PowerManager powerManager, CommunalColors communalColors, DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory, CommunalContent communalContent, SceneDataSourceDelegator sceneDataSourceDelegator, NotificationStackScrollLayoutController notificationStackScrollLayoutController, KeyguardMediaController keyguardMediaController, LockscreenSmartspaceController lockscreenSmartspaceController, LogBuffer logBuffer) {
        this.communalInteractor = communalInteractor;
        this.communalViewModel = communalViewModel;
        this.keyguardInteractor = keyguardInteractor;
        this.keyguardTransitionInteractor = keyguardTransitionInteractor;
        this.shadeInteractor = shadeInteractor;
        this.powerManager = powerManager;
        this.communalColors = communalColors;
        this.ambientTouchComponentFactory = daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory;
        this.communalContent = communalContent;
        this.dataSourceDelegator = sceneDataSourceDelegator;
        this.notificationStackScrollLayoutController = notificationStackScrollLayoutController;
        this.keyguardMediaController = keyguardMediaController;
        this.lockscreenSmartspaceController = lockscreenSmartspaceController;
        this.logger = new Logger(logBuffer, "GlanceableHubContainer");
    }

    public static final void access$updateTouchHandlingState(GlanceableHubContainerController glanceableHubContainerController) {
        boolean z = glanceableHubContainerController.hubShowing;
        LifecycleRegistry lifecycleRegistry = glanceableHubContainerController.lifecycleRegistry;
        if (z && !glanceableHubContainerController.shadeShowingAndConsumingTouches && !glanceableHubContainerController.anyBouncerShowing && !glanceableHubContainerController.inEditModeTransition) {
            lifecycleRegistry.setCurrentState(Lifecycle.State.RESUMED);
            return;
        }
        lifecycleRegistry.setCurrentState(Lifecycle.State.STARTED);
        View view = glanceableHubContainerController.communalContainerView;
        Intrinsics.checkNotNull(view);
        view.setSystemGestureExclusionRects(EmptyList.INSTANCE);
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public final Lifecycle getLifecycle() {
        return this.lifecycleRegistry;
    }

    public final View initView$frameworks__base__packages__SystemUI__android_common__SystemUI_core(View view) {
        int i = 0;
        int i2 = 2;
        if (this.communalContainerView != null) {
            throw new RuntimeException("Communal view has already been initialized");
        }
        TouchMonitor touchMonitor = this.touchMonitor;
        if (touchMonitor != null) {
            touchMonitor.destroy();
            this.touchMonitor = null;
        }
        HashSet hashSet = new HashSet();
        DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory = this.ambientTouchComponentFactory;
        TouchMonitor touchMonitor2 = new DaggerSysUIGoogleGlobalRootComponent$AmbientTouchComponentImpl(daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory.sysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory.sysUIGoogleSysUIComponentImpl, this, hashSet, "GlanceableHubContainer").getTouchMonitor();
        touchMonitor2.init();
        this.touchMonitor = touchMonitor2;
        LifecycleRegistry lifecycleRegistry = this.lifecycleRegistry;
        lifecycleRegistry.addObserver(this.touchLifecycleLogger);
        lifecycleRegistry.setCurrentState(Lifecycle.State.CREATED);
        this.communalContainerView = view;
        KeyguardInteractor keyguardInteractor = this.keyguardInteractor;
        JavaAdapterKt.collectFlow$default(view, FlowKt.distinctUntilChanged(new BooleanFlowOperators$not$$inlined$map$1(i2, (Flow[]) CollectionsKt.toList(ArraysKt.asIterable(new Flow[]{keyguardInteractor.primaryBouncerShowing, keyguardInteractor.alternateBouncerShowing})).toArray(new Flow[0]))), new GlanceableHubContainerController$initView$4(i, this), null, 24);
        KeyguardState keyguardState = KeyguardState.LOCKSCREEN;
        KeyguardTransitionInteractor keyguardTransitionInteractor = this.keyguardTransitionInteractor;
        JavaAdapterKt.collectFlow$default(view, keyguardTransitionInteractor.isFinishedIn$1(keyguardState), new GlanceableHubContainerController$initView$4(3, this), null, 24);
        CommunalInteractor communalInteractor = this.communalInteractor;
        JavaAdapterKt.collectFlow$default(view, communalInteractor.isCommunalVisible, new GlanceableHubContainerController$initView$4(4, this), null, 24);
        Edge.Companion companion = Edge.Companion;
        JavaAdapterKt.collectFlow$default(view, FlowKt.distinctUntilChanged(new BooleanFlowOperators$not$$inlined$map$1(i2, (Flow[]) CollectionsKt.toList(ArraysKt.asIterable(new Flow[]{communalInteractor.editActivityShowing, keyguardTransitionInteractor.isInTransition(new Edge.StateToState(KeyguardState.GONE, KeyguardState.GLANCEABLE_HUB), null)})).toArray(new Flow[0]))), new GlanceableHubContainerController$initView$4(5, this), null, 24);
        ShadeInteractorImpl shadeInteractorImpl = (ShadeInteractorImpl) this.shadeInteractor;
        JavaAdapterKt.collectFlow$default(view, FlowKt.combine(shadeInteractorImpl.isAnyFullyExpanded, shadeInteractorImpl.isUserInteracting, shadeInteractorImpl.isShadeFullyCollapsed, GlanceableHubContainerController$initView$9.INSTANCE), new GlanceableHubContainerController$initView$4(1, this), null, 24);
        JavaAdapterKt.collectFlow$default(view, keyguardInteractor.isDreaming, new GlanceableHubContainerController$initView$4(i2, this), null, 24);
        CommunalWrapper communalWrapper = new CommunalWrapper(view.getContext());
        this.communalContainerWrapper = communalWrapper;
        communalWrapper.addView(this.communalContainerView);
        Logger.d$default(this.logger, "Hub container initialized", null, 2, null);
        CommunalWrapper communalWrapper2 = this.communalContainerWrapper;
        Intrinsics.checkNotNull(communalWrapper2);
        return communalWrapper2;
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z;
        boolean z2;
        CommunalWrapper communalWrapper;
        if (this.communalContainerView == null) {
            return false;
        }
        boolean isBelowLastNotification = this.notificationStackScrollLayoutController.mView.isBelowLastNotification(motionEvent.getX(), motionEvent.getY());
        boolean z3 = !isBelowLastNotification;
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        KeyguardMediaController keyguardMediaController = this.keyguardMediaController;
        keyguardMediaController.getClass();
        Rect rect = new Rect();
        UniqueObjectHostView uniqueObjectHostView = keyguardMediaController.mediaHost.hostView;
        if (uniqueObjectHostView == null) {
            uniqueObjectHostView = null;
        }
        uniqueObjectHostView.getBoundsOnScreen(rect);
        boolean contains = rect.contains(x, y);
        int x2 = (int) motionEvent.getX();
        int y2 = (int) motionEvent.getY();
        Iterator it = this.lockscreenSmartspaceController.smartspaceViews.iterator();
        while (true) {
            z = true;
            if (!it.hasNext()) {
                z2 = false;
                break;
            }
            Object obj = (BcSmartspaceDataPlugin.SmartspaceView) it.next();
            Rect rect2 = new Rect();
            ((View) obj).getBoundsOnScreen(rect2);
            if (rect2.contains(x2, y2)) {
                z2 = true;
                break;
            }
        }
        boolean z4 = this.hubShowing;
        Logger logger = this.logger;
        if (!z4 && (!isBelowLastNotification || contains || z2)) {
            LogMessage obtain = logger.getBuffer().obtain(logger.getTag(), LogLevel.DEBUG, new Function1() { // from class: com.android.systemui.shade.GlanceableHubContainerController$onTouchEvent$1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    LogMessage logMessage = (LogMessage) obj2;
                    boolean bool1 = logMessage.getBool1();
                    boolean bool2 = logMessage.getBool2();
                    boolean bool3 = logMessage.getBool3();
                    StringBuilder m = BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0.m("Lockscreen touch ignored: touchOnNotifications: ", ", touchOnUmo: ", ", touchOnSmartspace: ", bool1, bool2);
                    m.append(bool3);
                    return m.toString();
                }
            }, null);
            obtain.setBool1(z3);
            obtain.setBool2(contains);
            obtain.setBool3(z2);
            logger.getBuffer().commit(obtain);
            return false;
        }
        boolean z5 = motionEvent.getActionMasked() == 0;
        boolean z6 = motionEvent.getActionMasked() == 1;
        boolean z7 = motionEvent.getActionMasked() == 2;
        boolean z8 = motionEvent.getActionMasked() == 3;
        boolean z9 = this.anyBouncerShowing || this.shadeConsumingTouches || this.shadeShowing;
        if ((z5 || z7) && !z9) {
            if (z5) {
                LogMessage obtain2 = logger.getBuffer().obtain(logger.getTag(), LogLevel.DEBUG, new Function1() { // from class: com.android.systemui.shade.GlanceableHubContainerController$handleTouchEventOnCommunalView$1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        LogMessage logMessage = (LogMessage) obj2;
                        int int1 = logMessage.getInt1();
                        int int2 = logMessage.getInt2();
                        boolean bool1 = logMessage.getBool1();
                        boolean bool2 = logMessage.getBool2();
                        boolean bool3 = logMessage.getBool3();
                        StringBuilder m = ValidatingOffsetMappingKt$$ExternalSyntheticOutline0.m(int1, int2, "Touch started. x: ", ", y: ", ", hubShowing: ");
                        BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0.m(m, bool1, ", isDreaming: ", bool2, ", onLockscreen: ");
                        m.append(bool3);
                        return m.toString();
                    }
                }, null);
                obtain2.setInt1((int) motionEvent.getX());
                obtain2.setInt2((int) motionEvent.getY());
                obtain2.setBool1(this.hubShowing);
                obtain2.setBool2(this.isDreaming);
                obtain2.setBool3(this.onLockscreen);
                logger.getBuffer().commit(obtain2);
            }
            this.isTrackingHubTouch = true;
        }
        if (!this.isTrackingHubTouch) {
            return false;
        }
        boolean z10 = this.onLockscreen && (this.shadeConsumingTouches || this.anyBouncerShowing);
        if (z10 != this.touchTakenByKeyguardGesture && z10) {
            Logger.d$default(logger, "Lock screen touch consumed by shade or bouncer, ignoring subsequent touches", null, 2, null);
        }
        this.touchTakenByKeyguardGesture = z10;
        if (z6 || z8) {
            LogMessage obtain3 = logger.getBuffer().obtain(logger.getTag(), LogLevel.DEBUG, new Function1() { // from class: com.android.systemui.shade.GlanceableHubContainerController$handleTouchEventOnCommunalView$4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    LogMessage logMessage = (LogMessage) obj2;
                    String str = logMessage.getBool1() ? "up" : "cancel";
                    int int1 = logMessage.getInt1();
                    int int2 = logMessage.getInt2();
                    boolean bool2 = logMessage.getBool2();
                    boolean bool3 = logMessage.getBool3();
                    StringBuilder m = GenericDocument$$ExternalSyntheticOutline0.m("Touch ended with ", str, ". x: ", int1, ", y: ");
                    m.append(int2);
                    m.append(", shadeConsumingTouches: ");
                    m.append(bool2);
                    m.append(", anyBouncerShowing: ");
                    m.append(bool3);
                    return m.toString();
                }
            }, null);
            obtain3.setInt1((int) motionEvent.getX());
            obtain3.setInt2((int) motionEvent.getY());
            obtain3.setBool1(z6);
            obtain3.setBool2(this.shadeConsumingTouches);
            obtain3.setBool3(this.anyBouncerShowing);
            logger.getBuffer().commit(obtain3);
            this.isTrackingHubTouch = false;
            this.touchTakenByKeyguardGesture = false;
        }
        if (!this.inEditModeTransition) {
            try {
                Ref$BooleanRef ref$BooleanRef = new Ref$BooleanRef();
                if (!this.touchTakenByKeyguardGesture && (communalWrapper = this.communalContainerWrapper) != null) {
                    ((ArraySet) communalWrapper.consumers).add(new GlanceableHubContainerController$initView$4(6, ref$BooleanRef));
                    try {
                        communalWrapper.dispatchTouchEvent(motionEvent);
                        ((ArraySet) communalWrapper.consumers).clear();
                    } catch (Throwable th) {
                        ((ArraySet) communalWrapper.consumers).clear();
                        throw th;
                    }
                }
                if (!ref$BooleanRef.element && !this.hubShowing) {
                    z = false;
                }
                this.powerManager.userActivity(SystemClock.uptimeMillis(), 2, 0);
            } catch (Throwable th2) {
                this.powerManager.userActivity(SystemClock.uptimeMillis(), 2, 0);
                throw th2;
            }
        }
        return z;
    }
}
