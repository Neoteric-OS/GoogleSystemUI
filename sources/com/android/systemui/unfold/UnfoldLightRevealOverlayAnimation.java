package com.android.systemui.unfold;

import android.content.ContentResolver;
import android.content.Context;
import android.hardware.devicestate.DeviceStateManager;
import android.hardware.input.InputManagerGlobal;
import android.os.Handler;
import android.os.Trace;
import android.provider.Settings;
import android.view.DisplayInfo;
import android.view.SurfaceControlViewHost;
import com.android.app.tracing.TraceUtilsKt;
import com.android.systemui.flags.FeatureFlagsClassic;
import com.android.systemui.flags.Flags;
import com.android.systemui.flags.UnreleasedFlag;
import com.android.systemui.statusbar.LightRevealScrim;
import com.android.systemui.statusbar.LinearLightRevealEffect;
import com.android.systemui.unfold.UnfoldLightRevealOverlayAnimation;
import com.android.systemui.unfold.UnfoldTransitionProgressProvider;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.systemui.util.concurrency.PendingTasksContainer$registerTask$1;
import com.android.systemui.util.concurrency.ThreadFactoryImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl$SwitchingProvider$1;
import dagger.internal.InstanceFactory;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt__StringNumberConversionsJVMKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UnfoldLightRevealOverlayAnimation implements FullscreenLightRevealAnimation {
    public ExecutorImpl bgExecutor;
    public final ContentResolver contentResolver;
    public final Context context;
    public FullscreenLightRevealAnimationController controller;
    public final DeviceStateManager deviceStateManager;
    public final FeatureFlagsClassic featureFlags;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl$SwitchingProvider$1 fullscreenLightRevealAnimationControllerFactory;
    public boolean isFolded;
    public final ThreadFactoryImpl threadFactory;
    public final Handler unfoldProgressHandler;
    public final InstanceFactory unfoldTransitionBgProgressProvider;
    public final InstanceFactory unfoldTransitionProgressProvider;
    public final TransitionListener transitionListener = new TransitionListener();
    public boolean isUnfoldHandled = true;
    public AddOverlayReason overlayAddReason = AddOverlayReason.UNFOLD;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class AddOverlayReason {
        public static final /* synthetic */ AddOverlayReason[] $VALUES;
        public static final AddOverlayReason FOLD;
        public static final AddOverlayReason UNFOLD;

        static {
            AddOverlayReason addOverlayReason = new AddOverlayReason("FOLD", 0);
            FOLD = addOverlayReason;
            AddOverlayReason addOverlayReason2 = new AddOverlayReason("UNFOLD", 1);
            UNFOLD = addOverlayReason2;
            AddOverlayReason[] addOverlayReasonArr = {addOverlayReason, addOverlayReason2};
            $VALUES = addOverlayReasonArr;
            EnumEntriesKt.enumEntries(addOverlayReasonArr);
        }

        public static AddOverlayReason valueOf(String str) {
            return (AddOverlayReason) Enum.valueOf(AddOverlayReason.class, str);
        }

        public static AddOverlayReason[] values() {
            return (AddOverlayReason[]) $VALUES.clone();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class FoldListener extends DeviceStateManager.FoldStateListener {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TransitionListener implements UnfoldTransitionProgressProvider.TransitionProgressListener {
        public TransitionListener() {
        }

        @Override // com.android.systemui.unfold.UnfoldTransitionProgressProvider.TransitionProgressListener
        public final void onTransitionFinished() {
            final UnfoldLightRevealOverlayAnimation unfoldLightRevealOverlayAnimation = UnfoldLightRevealOverlayAnimation.this;
            unfoldLightRevealOverlayAnimation.executeInBackground(new Function0() { // from class: com.android.systemui.unfold.UnfoldLightRevealOverlayAnimation$TransitionListener$onTransitionFinished$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    FullscreenLightRevealAnimationController fullscreenLightRevealAnimationController = UnfoldLightRevealOverlayAnimation.this.controller;
                    if (fullscreenLightRevealAnimationController == null) {
                        fullscreenLightRevealAnimationController = null;
                    }
                    fullscreenLightRevealAnimationController.ensureOverlayRemoved();
                    return Unit.INSTANCE;
                }
            });
        }

        @Override // com.android.systemui.unfold.UnfoldTransitionProgressProvider.TransitionProgressListener
        public final void onTransitionProgress(final float f) {
            final UnfoldLightRevealOverlayAnimation unfoldLightRevealOverlayAnimation = UnfoldLightRevealOverlayAnimation.this;
            unfoldLightRevealOverlayAnimation.executeInBackground(new Function0() { // from class: com.android.systemui.unfold.UnfoldLightRevealOverlayAnimation$TransitionListener$onTransitionProgress$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    UnfoldLightRevealOverlayAnimation unfoldLightRevealOverlayAnimation2 = UnfoldLightRevealOverlayAnimation.this;
                    FullscreenLightRevealAnimationController fullscreenLightRevealAnimationController = unfoldLightRevealOverlayAnimation2.controller;
                    if (fullscreenLightRevealAnimationController == null) {
                        fullscreenLightRevealAnimationController = null;
                    }
                    float calculateRevealAmount = unfoldLightRevealOverlayAnimation2.calculateRevealAmount(Float.valueOf(f));
                    LightRevealScrim lightRevealScrim = fullscreenLightRevealAnimationController.scrimView;
                    if (lightRevealScrim != null) {
                        lightRevealScrim.setRevealAmount(calculateRevealAmount);
                    }
                    UnfoldLightRevealOverlayAnimation unfoldLightRevealOverlayAnimation3 = UnfoldLightRevealOverlayAnimation.this;
                    FullscreenLightRevealAnimationController fullscreenLightRevealAnimationController2 = unfoldLightRevealOverlayAnimation3.controller;
                    FullscreenLightRevealAnimationController fullscreenLightRevealAnimationController3 = fullscreenLightRevealAnimationController2 != null ? fullscreenLightRevealAnimationController2 : null;
                    boolean z = unfoldLightRevealOverlayAnimation3.overlayAddReason == UnfoldLightRevealOverlayAnimation.AddOverlayReason.FOLD || f < 0.8f;
                    if (z != fullscreenLightRevealAnimationController3.isTouchBlocked) {
                        boolean isEnabled = Trace.isEnabled();
                        if (isEnabled) {
                            TraceUtilsKt.beginSlice("FullscreenLightRevealAnimation#relayoutToUpdateTouch");
                        }
                        try {
                            SurfaceControlViewHost surfaceControlViewHost = fullscreenLightRevealAnimationController3.root;
                            if (surfaceControlViewHost != null) {
                                surfaceControlViewHost.relayout(fullscreenLightRevealAnimationController3.getLayoutParams());
                            }
                            fullscreenLightRevealAnimationController3.isTouchBlocked = z;
                        } finally {
                            if (isEnabled) {
                                TraceUtilsKt.endSlice();
                            }
                        }
                    }
                    return Unit.INSTANCE;
                }
            });
        }

        @Override // com.android.systemui.unfold.UnfoldTransitionProgressProvider.TransitionProgressListener
        public final void onTransitionStarted() {
            final UnfoldLightRevealOverlayAnimation unfoldLightRevealOverlayAnimation = UnfoldLightRevealOverlayAnimation.this;
            FullscreenLightRevealAnimationController fullscreenLightRevealAnimationController = unfoldLightRevealOverlayAnimation.controller;
            if (fullscreenLightRevealAnimationController == null) {
                fullscreenLightRevealAnimationController = null;
            }
            if (fullscreenLightRevealAnimationController.scrimView == null) {
                unfoldLightRevealOverlayAnimation.executeInBackground(new Function0() { // from class: com.android.systemui.unfold.UnfoldLightRevealOverlayAnimation$TransitionListener$onTransitionStarted$1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        UnfoldLightRevealOverlayAnimation unfoldLightRevealOverlayAnimation2 = UnfoldLightRevealOverlayAnimation.this;
                        unfoldLightRevealOverlayAnimation2.overlayAddReason = UnfoldLightRevealOverlayAnimation.AddOverlayReason.FOLD;
                        FullscreenLightRevealAnimationController fullscreenLightRevealAnimationController2 = unfoldLightRevealOverlayAnimation2.controller;
                        if (fullscreenLightRevealAnimationController2 == null) {
                            fullscreenLightRevealAnimationController2 = null;
                        }
                        fullscreenLightRevealAnimationController2.addOverlay(unfoldLightRevealOverlayAnimation2.calculateRevealAmount(null), null);
                        return Unit.INSTANCE;
                    }
                });
            }
            InputManagerGlobal.getInstance().cancelCurrentTouch();
        }
    }

    public UnfoldLightRevealOverlayAnimation(Context context, FeatureFlagsClassic featureFlagsClassic, ContentResolver contentResolver, Handler handler, InstanceFactory instanceFactory, InstanceFactory instanceFactory2, DeviceStateManager deviceStateManager, ThreadFactoryImpl threadFactoryImpl, DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl$SwitchingProvider$1 daggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl$SwitchingProvider$1) {
        this.context = context;
        this.featureFlags = featureFlagsClassic;
        this.contentResolver = contentResolver;
        this.unfoldProgressHandler = handler;
        this.unfoldTransitionBgProgressProvider = instanceFactory;
        this.deviceStateManager = deviceStateManager;
        this.fullscreenLightRevealAnimationControllerFactory = daggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl$SwitchingProvider$1;
    }

    public final float calculateRevealAmount(Float f) {
        AddOverlayReason addOverlayReason = this.overlayAddReason;
        if (f != null) {
            UnreleasedFlag unreleasedFlag = Flags.NULL_FLAG;
            this.featureFlags.getClass();
            if (addOverlayReason == AddOverlayReason.FOLD) {
                return 1.0f;
            }
            return f.floatValue();
        }
        int ordinal = addOverlayReason.ordinal();
        if (ordinal == 0) {
            return 1.0f;
        }
        if (ordinal == 1) {
            return 0.0f;
        }
        throw new NoWhenBranchMatchedException();
    }

    public final void executeInBackground(final Function0 function0) {
        Handler handler = this.unfoldProgressHandler;
        if (handler.getLooper().isCurrentThread()) {
            function0.invoke();
        } else {
            handler.post(new Runnable(function0) { // from class: com.android.systemui.unfold.UnfoldLightRevealOverlayAnimation$sam$java_lang_Runnable$0
                public final /* synthetic */ Lambda function;

                /* JADX WARN: Multi-variable type inference failed */
                {
                    this.function = (Lambda) function0;
                }

                /* JADX WARN: Type inference failed for: r0v1, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
                @Override // java.lang.Runnable
                public final /* synthetic */ void run() {
                    this.function.invoke();
                }
            });
        }
    }

    @Override // com.android.systemui.unfold.FullscreenLightRevealAnimation
    public final void init() {
        FullscreenLightRevealAnimationController create = this.fullscreenLightRevealAnimationControllerFactory.create(new Function1() { // from class: com.android.systemui.unfold.UnfoldLightRevealOverlayAnimation$init$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Object obj2;
                Iterator it = ((List) obj).iterator();
                if (it.hasNext()) {
                    Object next = it.next();
                    if (it.hasNext()) {
                        int naturalWidth = ((DisplayInfo) next).getNaturalWidth();
                        do {
                            Object next2 = it.next();
                            int naturalWidth2 = ((DisplayInfo) next2).getNaturalWidth();
                            if (naturalWidth < naturalWidth2) {
                                next = next2;
                                naturalWidth = naturalWidth2;
                            }
                        } while (it.hasNext());
                    }
                    obj2 = next;
                } else {
                    obj2 = null;
                }
                return (DisplayInfo) obj2;
            }
        }, new Function1() { // from class: com.android.systemui.unfold.UnfoldLightRevealOverlayAnimation$init$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                int intValue = ((Number) obj).intValue();
                return new LinearLightRevealEffect(intValue == 0 || intValue == 2);
            }
        }, "unfold-animation-overlay");
        this.controller = create;
        create.init();
        this.deviceStateManager.registerCallback(new ExecutorImpl(this.unfoldProgressHandler.getLooper()), new FoldListener(this.context, new Consumer() { // from class: com.android.systemui.unfold.UnfoldLightRevealOverlayAnimation.FoldListener.1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Boolean bool = (Boolean) obj;
                Intrinsics.checkNotNull(bool);
                if (bool.booleanValue()) {
                    FullscreenLightRevealAnimationController fullscreenLightRevealAnimationController = UnfoldLightRevealOverlayAnimation.this.controller;
                    if (fullscreenLightRevealAnimationController == null) {
                        fullscreenLightRevealAnimationController = null;
                    }
                    fullscreenLightRevealAnimationController.ensureOverlayRemoved();
                    UnfoldLightRevealOverlayAnimation.this.isUnfoldHandled = false;
                }
                UnfoldLightRevealOverlayAnimation.this.isFolded = bool.booleanValue();
            }
        }));
        this.unfoldTransitionBgProgressProvider.instance.addCallback(this.transitionListener);
    }

    @Override // com.android.systemui.unfold.FullscreenLightRevealAnimation
    public final void onScreenTurningOn(final PendingTasksContainer$registerTask$1 pendingTasksContainer$registerTask$1) {
        executeInBackground(new Function0() { // from class: com.android.systemui.unfold.UnfoldLightRevealOverlayAnimation$onScreenTurningOn$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Float floatOrNull;
                Trace.beginSection("UnfoldLightRevealOverlayAnimation#onScreenTurningOn");
                try {
                    UnfoldLightRevealOverlayAnimation unfoldLightRevealOverlayAnimation = UnfoldLightRevealOverlayAnimation.this;
                    FullscreenLightRevealAnimationController fullscreenLightRevealAnimationController = null;
                    if (!unfoldLightRevealOverlayAnimation.isFolded && !unfoldLightRevealOverlayAnimation.isUnfoldHandled) {
                        String string = Settings.Global.getString(unfoldLightRevealOverlayAnimation.contentResolver, "animator_duration_scale");
                        if (((string == null || (floatOrNull = StringsKt__StringNumberConversionsJVMKt.toFloatOrNull(string)) == null) ? 1.0f : floatOrNull.floatValue()) != 0.0f) {
                            UnfoldLightRevealOverlayAnimation unfoldLightRevealOverlayAnimation2 = UnfoldLightRevealOverlayAnimation.this;
                            unfoldLightRevealOverlayAnimation2.overlayAddReason = UnfoldLightRevealOverlayAnimation.AddOverlayReason.UNFOLD;
                            FullscreenLightRevealAnimationController fullscreenLightRevealAnimationController2 = unfoldLightRevealOverlayAnimation2.controller;
                            if (fullscreenLightRevealAnimationController2 == null) {
                                fullscreenLightRevealAnimationController2 = null;
                            }
                            fullscreenLightRevealAnimationController2.addOverlay(unfoldLightRevealOverlayAnimation2.calculateRevealAmount(null), pendingTasksContainer$registerTask$1);
                            UnfoldLightRevealOverlayAnimation.this.isUnfoldHandled = true;
                            Trace.endSection();
                            return Unit.INSTANCE;
                        }
                    }
                    FullscreenLightRevealAnimationController fullscreenLightRevealAnimationController3 = UnfoldLightRevealOverlayAnimation.this.controller;
                    if (fullscreenLightRevealAnimationController3 != null) {
                        fullscreenLightRevealAnimationController = fullscreenLightRevealAnimationController3;
                    }
                    fullscreenLightRevealAnimationController.ensureOverlayRemoved();
                    pendingTasksContainer$registerTask$1.run();
                    Trace.endSection();
                    return Unit.INSTANCE;
                } catch (Throwable th) {
                    Trace.endSection();
                    throw th;
                }
            }
        });
    }
}
