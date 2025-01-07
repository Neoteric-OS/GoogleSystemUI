package com.android.systemui.keyguard.domain.interactor;

import android.content.Context;
import android.content.IntentFilter;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.deviceentry.domain.interactor.DeviceEntryFaceAuthInteractor;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.flags.FeatureFlagsClassicRelease;
import com.android.systemui.flags.Flags;
import com.android.systemui.flags.ReleasedFlag;
import com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.shade.PulsingGestureListener;
import com.android.systemui.statusbar.policy.AccessibilityManagerWrapper;
import com.android.wm.shell.R;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.StandaloneCoroutine;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StartedWhileSubscribed;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardTouchHandlingInteractor {
    public final StateFlowImpl _isMenuVisible;
    public final StateFlowImpl _shouldOpenSettings;
    public final AccessibilityManagerWrapper accessibilityManager;
    public final Context appContext;
    public StandaloneCoroutine delayedHideMenuJob;
    public final DeviceEntryFaceAuthInteractor faceAuthInteractor;
    public final FeatureFlags featureFlags;
    public final ReadonlyStateFlow isLongPressHandlingEnabled;
    public final ReadonlyStateFlow isMenuVisible;
    public final UiEventLogger logger;
    public final PulsingGestureListener pulsingGestureListener;
    public final CoroutineScope scope;
    public final ReadonlyStateFlow shouldOpenSettings;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardTouchHandlingInteractor$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return KeyguardTouchHandlingInteractor.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            AnonymousClass1 anonymousClass1 = (AnonymousClass1) create((Unit) obj, (Continuation) obj2);
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
            KeyguardTouchHandlingInteractor.this.hideMenu();
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class LogEvents implements UiEventLogger.UiEventEnum {
        public static final /* synthetic */ LogEvents[] $VALUES;
        public static final LogEvents LOCK_SCREEN_LONG_PRESS_POPUP_CLICKED;
        public static final LogEvents LOCK_SCREEN_LONG_PRESS_POPUP_SHOWN;
        private final int _id;

        static {
            LogEvents logEvents = new LogEvents("LOCK_SCREEN_LONG_PRESS_POPUP_SHOWN", 0, 1292);
            LOCK_SCREEN_LONG_PRESS_POPUP_SHOWN = logEvents;
            LogEvents logEvents2 = new LogEvents("LOCK_SCREEN_LONG_PRESS_POPUP_CLICKED", 1, 1293);
            LOCK_SCREEN_LONG_PRESS_POPUP_CLICKED = logEvents2;
            LogEvents[] logEventsArr = {logEvents, logEvents2};
            $VALUES = logEventsArr;
            EnumEntriesKt.enumEntries(logEventsArr);
        }

        public LogEvents(String str, int i, int i2) {
            this._id = i2;
        }

        public static LogEvents valueOf(String str) {
            return (LogEvents) Enum.valueOf(LogEvents.class, str);
        }

        public static LogEvents[] values() {
            return (LogEvents[]) $VALUES.clone();
        }

        public final int getId() {
            return this._id;
        }
    }

    public KeyguardTouchHandlingInteractor(Context context, CoroutineScope coroutineScope, KeyguardTransitionInteractor keyguardTransitionInteractor, KeyguardRepositoryImpl keyguardRepositoryImpl, UiEventLogger uiEventLogger, FeatureFlags featureFlags, BroadcastDispatcher broadcastDispatcher, AccessibilityManagerWrapper accessibilityManagerWrapper, PulsingGestureListener pulsingGestureListener, DeviceEntryFaceAuthInteractor deviceEntryFaceAuthInteractor) {
        int i = 0;
        this.scope = coroutineScope;
        this.logger = uiEventLogger;
        this.accessibilityManager = accessibilityManagerWrapper;
        ReleasedFlag releasedFlag = Flags.LOCK_SCREEN_LONG_PRESS_ENABLED;
        Flow flowKt__ZipKt$combine$$inlined$unsafeFlow$1 = ((FeatureFlagsClassicRelease) featureFlags).isEnabled(releasedFlag) && context.getResources().getBoolean(R.bool.long_press_keyguard_customize_lockscreen_enabled) ? new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(keyguardTransitionInteractor.isFinishedIn$1(KeyguardState.LOCKSCREEN), keyguardRepositoryImpl.isQuickSettingsVisible, new KeyguardTouchHandlingInteractor$isLongPressHandlingEnabled$1(3, null)) : new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(Boolean.FALSE);
        StartedWhileSubscribed WhileSubscribed$default = SharingStarted.Companion.WhileSubscribed$default(3);
        Boolean bool = Boolean.FALSE;
        ReadonlyStateFlow stateIn = FlowKt.stateIn(flowKt__ZipKt$combine$$inlined$unsafeFlow$1, coroutineScope, WhileSubscribed$default, bool);
        this.isLongPressHandlingEnabled = stateIn;
        this._isMenuVisible = StateFlowKt.MutableStateFlow(bool);
        this.isMenuVisible = FlowKt.stateIn(FlowKt.transformLatest(stateIn, new KeyguardTouchHandlingInteractor$special$$inlined$flatMapLatest$1(this, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), bool);
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(bool);
        this._shouldOpenSettings = MutableStateFlow;
        this.shouldOpenSettings = new ReadonlyStateFlow(MutableStateFlow);
        if (((FeatureFlagsClassicRelease) featureFlags).isEnabled(releasedFlag) && context.getResources().getBoolean(R.bool.long_press_keyguard_customize_lockscreen_enabled)) {
            FlowKt.launchIn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(BroadcastDispatcher.broadcastFlow$default(broadcastDispatcher, new IntentFilter("android.intent.action.CLOSE_SYSTEM_DIALOGS"), null, 14), new AnonymousClass1(null), i), coroutineScope);
        }
    }

    public final void hideMenu() {
        StandaloneCoroutine standaloneCoroutine = this.delayedHideMenuJob;
        if (standaloneCoroutine != null) {
            standaloneCoroutine.cancel(null);
        }
        this.delayedHideMenuJob = null;
        Boolean bool = Boolean.FALSE;
        StateFlowImpl stateFlowImpl = this._isMenuVisible;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, bool);
    }

    public final void scheduleAutomaticMenuHiding() {
        StandaloneCoroutine standaloneCoroutine = this.delayedHideMenuJob;
        if (standaloneCoroutine != null) {
            standaloneCoroutine.cancel(null);
        }
        this.delayedHideMenuJob = null;
        this.delayedHideMenuJob = BuildersKt.launch$default(this.scope, null, null, new KeyguardTouchHandlingInteractor$scheduleAutomaticMenuHiding$1(this, null), 3);
    }
}
