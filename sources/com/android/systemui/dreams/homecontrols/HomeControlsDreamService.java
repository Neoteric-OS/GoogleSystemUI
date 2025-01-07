package com.android.systemui.dreams.homecontrols;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.SystemClock;
import android.service.dreams.DreamService;
import android.window.TaskFragmentCreationParams;
import android.window.TaskFragmentOperation;
import android.window.TaskFragmentOrganizerToken;
import android.window.WindowContainerTransaction;
import com.android.app.tracing.coroutines.TraceContextElementKt;
import com.android.app.tracing.coroutines.TraceDataThreadLocal;
import com.android.systemui.controls.settings.ControlsSettingsRepository;
import com.android.systemui.controls.settings.ControlsSettingsRepositoryImpl;
import com.android.systemui.dreams.DreamLogger;
import com.android.systemui.dreams.homecontrols.TaskFragmentComponent;
import com.android.systemui.dreams.homecontrols.domain.interactor.HomeControlsComponentInteractor;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.Logger;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.time.SystemClockImpl;
import com.android.systemui.util.wakelock.WakeLock;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$64;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.SupervisorJobImpl;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class HomeControlsDreamService extends DreamService {
    public static final /* synthetic */ int $r8$clinit = 0;
    public static final long ACTIVITY_RESTART_DELAY;
    public static final long CANCELLATION_DELAY_AFTER_DETACHED;
    public final ControlsSettingsRepository controlsSettingsRepository;
    public final DreamServiceDelegate dreamServiceDelegate;
    public final HomeControlsComponentInteractor homeControlsComponentInteractor;
    public final DreamLogger logger;
    public final SupervisorJobImpl serviceJob;
    public final ContextScope serviceScope;
    public TaskFragmentComponent taskFragmentComponent;
    public final TaskFragmentComponent.Factory taskFragmentFactory;
    public final Lazy wakeLock$delegate;
    public final WakeLock.Builder wakeLockBuilder;

    static {
        int i = Duration.$r8$clinit;
        CANCELLATION_DELAY_AFTER_DETACHED = DurationKt.toDuration(5, DurationUnit.SECONDS);
        ACTIVITY_RESTART_DELAY = DurationKt.toDuration(334, DurationUnit.MILLISECONDS);
    }

    public HomeControlsDreamService(ControlsSettingsRepository controlsSettingsRepository, TaskFragmentComponent.Factory factory, HomeControlsComponentInteractor homeControlsComponentInteractor, WakeLock.Builder builder, DreamServiceDelegate dreamServiceDelegate, CoroutineDispatcher coroutineDispatcher, LogBuffer logBuffer) {
        this.controlsSettingsRepository = controlsSettingsRepository;
        this.taskFragmentFactory = factory;
        this.homeControlsComponentInteractor = homeControlsComponentInteractor;
        this.wakeLockBuilder = builder;
        this.dreamServiceDelegate = dreamServiceDelegate;
        SupervisorJobImpl SupervisorJob$default = SupervisorKt.SupervisorJob$default();
        this.serviceJob = SupervisorJob$default;
        CoroutineContext plus = CoroutineContext.DefaultImpls.plus(coroutineDispatcher, SupervisorJob$default);
        TraceDataThreadLocal traceDataThreadLocal = TraceContextElementKt.traceThreadLocal;
        this.serviceScope = CoroutineScopeKt.CoroutineScope(plus.plus(EmptyCoroutineContext.INSTANCE));
        this.logger = new DreamLogger(logBuffer, "HomeControlsDreamService");
        this.wakeLock$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.dreams.homecontrols.HomeControlsDreamService$wakeLock$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                WakeLock.Builder builder2 = HomeControlsDreamService.this.wakeLockBuilder;
                builder2.mMaxTimeout = -1L;
                builder2.mTag = "HomeControlsDreamService";
                builder2.mLevelsAndFlags = 10;
                return builder2.build();
            }
        });
    }

    public static final void access$launchActivity(HomeControlsDreamService homeControlsDreamService) {
        boolean booleanValue = ((Boolean) ((StateFlowImpl) ((ControlsSettingsRepositoryImpl) homeControlsDreamService.controlsSettingsRepository).allowActionOnTrivialControlsInLockscreen.$$delegate_0).getValue()).booleanValue();
        ComponentName componentName = (ComponentName) ((StateFlowImpl) homeControlsDreamService.homeControlsComponentInteractor.panelComponent.$$delegate_0).getValue();
        Logger.d$default(homeControlsDreamService.logger, "Starting embedding " + componentName, null, 2, null);
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("android.service.controls.extra.LOCKSCREEN_ALLOW_TRIVIAL_CONTROLS", booleanValue);
        intent.putExtra("android.service.controls.extra.CONTROLS_SURFACE", 1);
        TaskFragmentComponent taskFragmentComponent = homeControlsDreamService.taskFragmentComponent;
        if (taskFragmentComponent == null) {
            taskFragmentComponent = null;
        }
        TaskFragmentComponent.Organizer organizer = taskFragmentComponent.organizer;
        WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
        Binder binder = taskFragmentComponent.fragmentToken;
        IBinder activityToken = taskFragmentComponent.activity.getActivityToken();
        Intrinsics.checkNotNull(activityToken);
        organizer.applyTransaction(windowContainerTransaction.startActivityInTaskFragment(binder, activityToken, intent, (Bundle) null), 1, false);
    }

    public final void endDream(boolean z) {
        HomeControlsComponentInteractor homeControlsComponentInteractor = this.homeControlsComponentInteractor;
        PowerManager powerManager = homeControlsComponentInteractor.powerManager;
        ((SystemClockImpl) homeControlsComponentInteractor.systemClock).getClass();
        powerManager.userActivity(SystemClock.uptimeMillis(), 0, 1);
        homeControlsComponentInteractor.taskFragmentFinished.tryEmit(Long.valueOf(System.currentTimeMillis()));
        if (z) {
            ((DreamServiceDelegateImpl) this.dreamServiceDelegate).getClass();
            if (getRedirectWake()) {
                ((DreamServiceDelegateImpl) this.dreamServiceDelegate).getClass();
                wakeUp();
                BuildersKt.launch$default(this.serviceScope, null, null, new HomeControlsDreamService$endDream$1(this, null), 3);
                return;
            }
        }
        ((DreamServiceDelegateImpl) this.dreamServiceDelegate).getClass();
        finish();
    }

    @Override // android.service.dreams.DreamService, android.view.Window.Callback
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        ((DreamServiceDelegateImpl) this.dreamServiceDelegate).getClass();
        Activity activity = getActivity();
        BuildersKt.launch$default(this.serviceScope, null, null, new HomeControlsDreamService$onAttachedToWindow$1(this, null), 3);
        TaskFragmentComponent taskFragmentComponent = new TaskFragmentComponent(activity, new HomeControlsDreamService$onAttachedToWindow$2(this), new HomeControlsDreamService$onAttachedToWindow$3(1, this, HomeControlsDreamService.class, "onTaskFragmentInfoChanged", "onTaskFragmentInfoChanged(Landroid/window/TaskFragmentInfo;)V", 0), new HomeControlsDreamService$onAttachedToWindow$4(this), (DelayableExecutor) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$64) this.taskFragmentFactory).this$0.sysUIGoogleGlobalRootComponentImpl.provideMainDelayableExecutorProvider.get());
        TaskFragmentComponent.Organizer organizer = taskFragmentComponent.organizer;
        TaskFragmentOrganizerToken organizerToken = organizer.getOrganizerToken();
        Binder binder = taskFragmentComponent.fragmentToken;
        IBinder activityToken = activity.getActivityToken();
        Intrinsics.checkNotNull(activityToken);
        organizer.applyTransaction(new WindowContainerTransaction().createTaskFragment(new TaskFragmentCreationParams.Builder(organizerToken, binder, activityToken).setInitialRelativeBounds(new Rect()).setWindowingMode(1).build()), 6, false);
        this.taskFragmentComponent = taskFragmentComponent;
        ((WakeLock) this.wakeLock$delegate.getValue()).acquire("HomeControlsDreamService");
    }

    @Override // android.service.dreams.DreamService, android.view.Window.Callback
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ((WakeLock) this.wakeLock$delegate.getValue()).release("HomeControlsDreamService");
        TaskFragmentComponent taskFragmentComponent = this.taskFragmentComponent;
        if (taskFragmentComponent == null) {
            taskFragmentComponent = null;
        }
        taskFragmentComponent.organizer.applyTransaction(new WindowContainerTransaction().addTaskFragmentOperation(taskFragmentComponent.fragmentToken, new TaskFragmentOperation.Builder(1).build()), 2, false);
        taskFragmentComponent.organizer.unregisterOrganizer();
        BuildersKt.launch$default(this.serviceScope, null, null, new HomeControlsDreamService$onDetachedFromWindow$1(this, null), 3);
    }
}
