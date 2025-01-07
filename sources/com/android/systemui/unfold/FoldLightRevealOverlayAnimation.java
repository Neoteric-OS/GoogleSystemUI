package com.android.systemui.unfold;

import android.animation.ValueAnimator;
import android.view.DisplayInfo;
import com.android.internal.foldables.FoldLockSettingAvailabilityProvider;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.systemui.display.data.repository.DeviceStateRepositoryImpl;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.statusbar.LinearSideLightRevealEffect;
import com.android.systemui.util.animation.data.repository.AnimationStatusRepositoryImpl;
import com.android.systemui.util.concurrency.PendingTasksContainer$registerTask$1;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl$SwitchingProvider$1;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CompletableDeferredImpl;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FoldLightRevealOverlayAnimation implements FullscreenLightRevealAnimation {
    public final AnimationStatusRepositoryImpl animationStatusRepository;
    public final CoroutineScope applicationScope;
    public final CoroutineDispatcher bgDispatcher;
    public FullscreenLightRevealAnimationController controller;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl$SwitchingProvider$1 controllerFactory;
    public final DeviceStateRepositoryImpl deviceStateRepository;
    public final FoldLockSettingAvailabilityProvider foldLockSettingAvailabilityProvider;
    public final InteractionJankMonitor interactionJankMonitor;
    public final PowerInteractor powerInteractor;
    public volatile CompletableDeferredImpl readyCallback;
    public final ValueAnimator revealProgressValueAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);

    public FoldLightRevealOverlayAnimation(CoroutineDispatcher coroutineDispatcher, DeviceStateRepositoryImpl deviceStateRepositoryImpl, PowerInteractor powerInteractor, CoroutineScope coroutineScope, AnimationStatusRepositoryImpl animationStatusRepositoryImpl, DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl$SwitchingProvider$1 daggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl$SwitchingProvider$1, FoldLockSettingAvailabilityProvider foldLockSettingAvailabilityProvider, InteractionJankMonitor interactionJankMonitor) {
        this.bgDispatcher = coroutineDispatcher;
        this.deviceStateRepository = deviceStateRepositoryImpl;
        this.powerInteractor = powerInteractor;
        this.applicationScope = coroutineScope;
        this.animationStatusRepository = animationStatusRepositoryImpl;
        this.controllerFactory = daggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl$SwitchingProvider$1;
        this.foldLockSettingAvailabilityProvider = foldLockSettingAvailabilityProvider;
        this.interactionJankMonitor = interactionJankMonitor;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$waitForGoToSleep(com.android.systemui.unfold.FoldLightRevealOverlayAnimation r9, kotlin.coroutines.jvm.internal.ContinuationImpl r10) {
        /*
            r9.getClass()
            boolean r0 = r10 instanceof com.android.systemui.unfold.FoldLightRevealOverlayAnimation$waitForGoToSleep$1
            if (r0 == 0) goto L16
            r0 = r10
            com.android.systemui.unfold.FoldLightRevealOverlayAnimation$waitForGoToSleep$1 r0 = (com.android.systemui.unfold.FoldLightRevealOverlayAnimation$waitForGoToSleep$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L16
            int r1 = r1 - r2
            r0.label = r1
            goto L1b
        L16:
            com.android.systemui.unfold.FoldLightRevealOverlayAnimation$waitForGoToSleep$1 r0 = new com.android.systemui.unfold.FoldLightRevealOverlayAnimation$waitForGoToSleep$1
            r0.<init>(r9, r10)
        L1b:
            java.lang.Object r10 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            r4 = 4096(0x1000, double:2.0237E-320)
            if (r2 == 0) goto L3c
            if (r2 != r3) goto L34
            int r9 = r0.I$0
            java.lang.Object r0 = r0.L$0
            java.lang.String r0 = (java.lang.String) r0
            kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Throwable -> L32
            goto L69
        L32:
            r10 = move-exception
            goto L78
        L34:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L3c:
            kotlin.ResultKt.throwOnFailure(r10)
            java.util.concurrent.ThreadLocalRandom r10 = java.util.concurrent.ThreadLocalRandom.current()
            int r10 = r10.nextInt()
            java.lang.String r2 = "FoldLightRevealOverlayAnimation"
            java.lang.String r6 = "waitForGoToSleep()"
            android.os.Trace.asyncTraceForTrackBegin(r4, r2, r6, r10)
            com.android.systemui.power.domain.interactor.PowerInteractor r9 = r9.powerInteractor     // Catch: java.lang.Throwable -> L73
            com.android.systemui.power.domain.interactor.PowerInteractor$special$$inlined$map$1 r9 = r9.isAsleep     // Catch: java.lang.Throwable -> L73
            com.android.systemui.unfold.FoldLightRevealOverlayAnimation$init$4$invokeSuspend$$inlined$map$1 r6 = new com.android.systemui.unfold.FoldLightRevealOverlayAnimation$init$4$invokeSuspend$$inlined$map$1     // Catch: java.lang.Throwable -> L73
            r7 = 1
            r6.<init>(r9, r7)     // Catch: java.lang.Throwable -> L73
            r0.L$0 = r2     // Catch: java.lang.Throwable -> L73
            r0.I$0 = r10     // Catch: java.lang.Throwable -> L73
            r0.label = r3     // Catch: java.lang.Throwable -> L73
            java.lang.Object r9 = kotlinx.coroutines.flow.FlowKt.first(r6, r0)     // Catch: java.lang.Throwable -> L73
            if (r9 != r1) goto L65
            goto L72
        L65:
            r0 = r2
            r8 = r10
            r10 = r9
            r9 = r8
        L69:
            r1 = r10
            java.lang.Boolean r1 = (java.lang.Boolean) r1     // Catch: java.lang.Throwable -> L32
            r1.getClass()     // Catch: java.lang.Throwable -> L32
            android.os.Trace.asyncTraceForTrackEnd(r4, r0, r9)
        L72:
            return r1
        L73:
            r9 = move-exception
            r0 = r2
            r8 = r10
            r10 = r9
            r9 = r8
        L78:
            android.os.Trace.asyncTraceForTrackEnd(r4, r0, r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.unfold.FoldLightRevealOverlayAnimation.access$waitForGoToSleep(com.android.systemui.unfold.FoldLightRevealOverlayAnimation, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$waitForScreenTurnedOn(com.android.systemui.unfold.FoldLightRevealOverlayAnimation r9, kotlin.coroutines.jvm.internal.ContinuationImpl r10) {
        /*
            r9.getClass()
            boolean r0 = r10 instanceof com.android.systemui.unfold.FoldLightRevealOverlayAnimation$waitForScreenTurnedOn$1
            if (r0 == 0) goto L16
            r0 = r10
            com.android.systemui.unfold.FoldLightRevealOverlayAnimation$waitForScreenTurnedOn$1 r0 = (com.android.systemui.unfold.FoldLightRevealOverlayAnimation$waitForScreenTurnedOn$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L16
            int r1 = r1 - r2
            r0.label = r1
            goto L1b
        L16:
            com.android.systemui.unfold.FoldLightRevealOverlayAnimation$waitForScreenTurnedOn$1 r0 = new com.android.systemui.unfold.FoldLightRevealOverlayAnimation$waitForScreenTurnedOn$1
            r0.<init>(r9, r10)
        L1b:
            java.lang.Object r10 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            r4 = 4096(0x1000, double:2.0237E-320)
            if (r2 == 0) goto L3c
            if (r2 != r3) goto L34
            int r9 = r0.I$0
            java.lang.Object r0 = r0.L$0
            java.lang.String r0 = (java.lang.String) r0
            kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Throwable -> L32
            goto L69
        L32:
            r10 = move-exception
            goto L75
        L34:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L3c:
            kotlin.ResultKt.throwOnFailure(r10)
            java.util.concurrent.ThreadLocalRandom r10 = java.util.concurrent.ThreadLocalRandom.current()
            int r10 = r10.nextInt()
            java.lang.String r2 = "FoldLightRevealOverlayAnimation"
            java.lang.String r6 = "waitForScreenTurnedOn()"
            android.os.Trace.asyncTraceForTrackBegin(r4, r2, r6, r10)
            com.android.systemui.power.domain.interactor.PowerInteractor r9 = r9.powerInteractor     // Catch: java.lang.Throwable -> L70
            kotlinx.coroutines.flow.ReadonlyStateFlow r9 = r9.screenPowerState     // Catch: java.lang.Throwable -> L70
            com.android.systemui.unfold.FoldLightRevealOverlayAnimation$init$4$invokeSuspend$$inlined$map$1 r6 = new com.android.systemui.unfold.FoldLightRevealOverlayAnimation$init$4$invokeSuspend$$inlined$map$1     // Catch: java.lang.Throwable -> L70
            r7 = 2
            r6.<init>(r9, r7)     // Catch: java.lang.Throwable -> L70
            r0.L$0 = r2     // Catch: java.lang.Throwable -> L70
            r0.I$0 = r10     // Catch: java.lang.Throwable -> L70
            r0.label = r3     // Catch: java.lang.Throwable -> L70
            java.lang.Object r9 = kotlinx.coroutines.flow.FlowKt.first(r6, r0)     // Catch: java.lang.Throwable -> L70
            if (r9 != r1) goto L65
            goto L6f
        L65:
            r0 = r2
            r8 = r10
            r10 = r9
            r9 = r8
        L69:
            r1 = r10
            com.android.systemui.power.shared.model.ScreenPowerState r1 = (com.android.systemui.power.shared.model.ScreenPowerState) r1     // Catch: java.lang.Throwable -> L32
            android.os.Trace.asyncTraceForTrackEnd(r4, r0, r9)
        L6f:
            return r1
        L70:
            r9 = move-exception
            r0 = r2
            r8 = r10
            r10 = r9
            r9 = r8
        L75:
            android.os.Trace.asyncTraceForTrackEnd(r4, r0, r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.unfold.FoldLightRevealOverlayAnimation.access$waitForScreenTurnedOn(com.android.systemui.unfold.FoldLightRevealOverlayAnimation, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    @Override // com.android.systemui.unfold.FullscreenLightRevealAnimation
    public final void init() {
        if (this.foldLockSettingAvailabilityProvider.isFoldLockBehaviorAvailable()) {
            FullscreenLightRevealAnimationController create = this.controllerFactory.create(new Function1() { // from class: com.android.systemui.unfold.FoldLightRevealOverlayAnimation$init$1
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
                                if (naturalWidth > naturalWidth2) {
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
            }, new Function1() { // from class: com.android.systemui.unfold.FoldLightRevealOverlayAnimation$init$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    int intValue = ((Number) obj).intValue();
                    return new LinearSideLightRevealEffect(intValue == 0 || intValue == 2);
                }
            }, "fold-animation-overlay");
            this.controller = create;
            create.init();
            FoldLightRevealOverlayAnimation$init$3 foldLightRevealOverlayAnimation$init$3 = new FoldLightRevealOverlayAnimation$init$3(this, null);
            CoroutineScope coroutineScope = this.applicationScope;
            CoroutineDispatcher coroutineDispatcher = this.bgDispatcher;
            BuildersKt.launch$default(coroutineScope, coroutineDispatcher, null, foldLightRevealOverlayAnimation$init$3, 2);
            BuildersKt.launch$default(coroutineScope, coroutineDispatcher, null, new FoldLightRevealOverlayAnimation$init$4(this, null), 2);
        }
    }

    @Override // com.android.systemui.unfold.FullscreenLightRevealAnimation
    public final void onScreenTurningOn(PendingTasksContainer$registerTask$1 pendingTasksContainer$registerTask$1) {
        CompletableDeferredImpl completableDeferredImpl = this.readyCallback;
        if (completableDeferredImpl != null) {
            completableDeferredImpl.makeCompleting$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(pendingTasksContainer$registerTask$1);
        } else {
            pendingTasksContainer$registerTask$1.run();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0021  */
    /* JADX WARN: Type inference failed for: r6v4, types: [android.view.View] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object trackCuj(int r5, com.android.systemui.statusbar.LightRevealScrim r6, kotlin.jvm.functions.Function1 r7, kotlin.coroutines.jvm.internal.ContinuationImpl r8) {
        /*
            r4 = this;
            boolean r0 = r8 instanceof com.android.systemui.unfold.FoldLightRevealOverlayAnimation$trackCuj$1
            if (r0 == 0) goto L13
            r0 = r8
            com.android.systemui.unfold.FoldLightRevealOverlayAnimation$trackCuj$1 r0 = (com.android.systemui.unfold.FoldLightRevealOverlayAnimation$trackCuj$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.unfold.FoldLightRevealOverlayAnimation$trackCuj$1 r0 = new com.android.systemui.unfold.FoldLightRevealOverlayAnimation$trackCuj$1
            r0.<init>(r4, r8)
        L18:
            java.lang.Object r8 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3c
            if (r2 != r3) goto L34
            int r5 = r0.I$0
            java.lang.Object r4 = r0.L$1
            r6 = r4
            android.view.View r6 = (android.view.View) r6
            java.lang.Object r4 = r0.L$0
            com.android.systemui.unfold.FoldLightRevealOverlayAnimation r4 = (com.android.systemui.unfold.FoldLightRevealOverlayAnimation) r4
            kotlin.ResultKt.throwOnFailure(r8)     // Catch: java.lang.Throwable -> L32
            goto L57
        L32:
            r7 = move-exception
            goto L61
        L34:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L3c:
            kotlin.ResultKt.throwOnFailure(r8)
            if (r6 == 0) goto L46
            com.android.internal.jank.InteractionJankMonitor r8 = r4.interactionJankMonitor
            r8.begin(r6, r5)
        L46:
            r0.L$0 = r4     // Catch: java.lang.Throwable -> L32
            r0.L$1 = r6     // Catch: java.lang.Throwable -> L32
            r0.I$0 = r5     // Catch: java.lang.Throwable -> L32
            r0.label = r3     // Catch: java.lang.Throwable -> L32
            com.android.systemui.unfold.FoldLightRevealOverlayAnimation$playFoldLightRevealOverlayAnimation$2 r7 = (com.android.systemui.unfold.FoldLightRevealOverlayAnimation$playFoldLightRevealOverlayAnimation$2) r7     // Catch: java.lang.Throwable -> L32
            java.lang.Object r7 = r7.invoke(r0)     // Catch: java.lang.Throwable -> L32
            if (r7 != r1) goto L57
            return r1
        L57:
            if (r6 == 0) goto L5e
            com.android.internal.jank.InteractionJankMonitor r4 = r4.interactionJankMonitor
            r4.end(r5)
        L5e:
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            return r4
        L61:
            if (r6 == 0) goto L68
            com.android.internal.jank.InteractionJankMonitor r4 = r4.interactionJankMonitor
            r4.end(r5)
        L68:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.unfold.FoldLightRevealOverlayAnimation.trackCuj(int, com.android.systemui.statusbar.LightRevealScrim, kotlin.jvm.functions.Function1, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }
}
