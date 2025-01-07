package com.android.systemui.bouncer.domain.interactor;

import android.content.Context;
import android.os.Handler;
import android.os.Trace;
import com.android.keyguard.KeyguardSecurityContainerController;
import com.android.keyguard.KeyguardSecurityModel;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.DejankUtils;
import com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl;
import com.android.systemui.bouncer.domain.interactor.PrimaryBouncerCallbackInteractor;
import com.android.systemui.bouncer.ui.BouncerViewImpl;
import com.android.systemui.bouncer.ui.binder.KeyguardBouncerViewBinder$bind$delegate$1;
import com.android.systemui.classifier.FalsingCollector;
import com.android.systemui.deviceentry.domain.interactor.DeviceEntryFaceAuthInteractor;
import com.android.systemui.keyguard.DismissCallbackRegistry;
import com.android.systemui.keyguard.data.repository.TrustRepositoryImpl;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.shared.system.SysUiStatsLog;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl$$ExternalSyntheticLambda0;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.systemui.util.Assert;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlySharedFlow;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PrimaryBouncerInteractor {
    public final CoroutineScope applicationScope;
    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 bouncerExpansion;
    public boolean currentUserActiveUnlockRunning;
    public final DeviceEntryFaceAuthInteractor deviceEntryFaceAuthInteractor;
    public final DismissCallbackRegistry dismissCallbackRegistry;
    public final FalsingCollector falsingCollector;
    public final FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1 isBackButtonEnabled;
    public final PrimaryBouncerInteractor$special$$inlined$map$1 isInteractable;
    public final ReadonlyStateFlow isShowing;
    public final FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1 keyguardAuthenticatedBiometrics;
    public final PrimaryBouncerInteractor$special$$inlined$map$1 keyguardAuthenticatedBiometricsHandled;
    public final ReadonlySharedFlow keyguardAuthenticatedPrimaryAuth;
    public final FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1 keyguardPosition;
    public final KeyguardSecurityModel keyguardSecurityModel;
    public final KeyguardStateController keyguardStateController;
    public final KeyguardUpdateMonitor keyguardUpdateMonitor;
    public final StateFlow lastShownSecurityMode;
    public final Handler mainHandler;
    public final StateFlow panelExpansionAmount;
    public final long passiveAuthBouncerDelay;
    public final PrimaryBouncerCallbackInteractor primaryBouncerCallbackInteractor;
    public final BouncerViewImpl primaryBouncerView;
    public final KeyguardBouncerRepositoryImpl repository;
    public final PrimaryBouncerInteractor$special$$inlined$filter$1 resourceUpdateRequests;
    public final SelectedUserInteractor selectedUserInteractor;
    public final FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1 showMessage;
    public final PrimaryBouncerInteractor$showRunnable$1 showRunnable;
    public final FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1 startingDisappearAnimation;
    public final PrimaryBouncerInteractor$special$$inlined$map$1 startingToHide;
    public final TrustRepositoryImpl trustRepository;
    public final FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1 userRequestedBouncerWhenAlreadyAuthenticated;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.bouncer.domain.interactor.PrimaryBouncerInteractor$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return PrimaryBouncerInteractor.this.new AnonymousClass1(continuation);
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
                final PrimaryBouncerInteractor primaryBouncerInteractor = PrimaryBouncerInteractor.this;
                FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 = primaryBouncerInteractor.trustRepository.isCurrentUserActiveUnlockRunning;
                FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.bouncer.domain.interactor.PrimaryBouncerInteractor.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj2, Continuation continuation) {
                        PrimaryBouncerInteractor.this.currentUserActiveUnlockRunning = ((Boolean) obj2).booleanValue();
                        return Unit.INSTANCE;
                    }
                };
                this.label = 1;
                if (flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1.collect(flowCollector, this) == coroutineSingletons) {
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

    public PrimaryBouncerInteractor(KeyguardBouncerRepositoryImpl keyguardBouncerRepositoryImpl, BouncerViewImpl bouncerViewImpl, Handler handler, KeyguardStateController keyguardStateController, KeyguardSecurityModel keyguardSecurityModel, PrimaryBouncerCallbackInteractor primaryBouncerCallbackInteractor, FalsingCollector falsingCollector, DismissCallbackRegistry dismissCallbackRegistry, Context context, KeyguardUpdateMonitor keyguardUpdateMonitor, TrustRepositoryImpl trustRepositoryImpl, CoroutineScope coroutineScope, SelectedUserInteractor selectedUserInteractor, DeviceEntryFaceAuthInteractor deviceEntryFaceAuthInteractor) {
        int i = 2;
        int i2 = 1;
        this.repository = keyguardBouncerRepositoryImpl;
        this.primaryBouncerView = bouncerViewImpl;
        this.mainHandler = handler;
        this.keyguardStateController = keyguardStateController;
        this.keyguardSecurityModel = keyguardSecurityModel;
        this.primaryBouncerCallbackInteractor = primaryBouncerCallbackInteractor;
        this.falsingCollector = falsingCollector;
        this.dismissCallbackRegistry = dismissCallbackRegistry;
        this.keyguardUpdateMonitor = keyguardUpdateMonitor;
        this.trustRepository = trustRepositoryImpl;
        this.selectedUserInteractor = selectedUserInteractor;
        this.deviceEntryFaceAuthInteractor = deviceEntryFaceAuthInteractor;
        this.passiveAuthBouncerDelay = context.getResources().getInteger(R.integer.primary_bouncer_passive_auth_delay);
        int i3 = 0;
        this.showRunnable = new PrimaryBouncerInteractor$showRunnable$1(this, i3);
        this.keyguardAuthenticatedPrimaryAuth = keyguardBouncerRepositoryImpl.keyguardAuthenticatedPrimaryAuth;
        ReadonlyStateFlow readonlyStateFlow = keyguardBouncerRepositoryImpl.keyguardAuthenticatedBiometrics;
        this.keyguardAuthenticatedBiometrics = new FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1(readonlyStateFlow);
        this.keyguardAuthenticatedBiometricsHandled = new PrimaryBouncerInteractor$special$$inlined$map$1(new PrimaryBouncerInteractor$special$$inlined$filter$1(readonlyStateFlow, i3), i3);
        this.userRequestedBouncerWhenAlreadyAuthenticated = new FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1(keyguardBouncerRepositoryImpl.userRequestedBouncerWhenAlreadyAuthenticated);
        ReadonlyStateFlow readonlyStateFlow2 = keyguardBouncerRepositoryImpl.primaryBouncerShow;
        this.isShowing = readonlyStateFlow2;
        this.startingToHide = new PrimaryBouncerInteractor$special$$inlined$map$1(new PrimaryBouncerInteractor$special$$inlined$filter$1(keyguardBouncerRepositoryImpl.primaryBouncerStartingToHide, i2), i2);
        this.isBackButtonEnabled = new FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1(keyguardBouncerRepositoryImpl.isBackButtonEnabled);
        this.showMessage = new FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1(keyguardBouncerRepositoryImpl.showMessage);
        this.startingDisappearAnimation = new FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1(keyguardBouncerRepositoryImpl.primaryBouncerStartingDisappearAnimation);
        this.resourceUpdateRequests = new PrimaryBouncerInteractor$special$$inlined$filter$1(keyguardBouncerRepositoryImpl.resourceUpdateRequests, i);
        this.keyguardPosition = new FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1(keyguardBouncerRepositoryImpl.keyguardPosition);
        ReadonlyStateFlow readonlyStateFlow3 = keyguardBouncerRepositoryImpl.panelExpansionAmount;
        this.panelExpansionAmount = readonlyStateFlow3;
        this.lastShownSecurityMode = keyguardBouncerRepositoryImpl.lastShownSecurityMode;
        this.isInteractable = new PrimaryBouncerInteractor$special$$inlined$map$1(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(readonlyStateFlow3, readonlyStateFlow2, new PrimaryBouncerInteractor$bouncerExpansion$1(3, null)), i);
        BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass1(null), 3);
    }

    public final void hide() {
        Trace.beginSection("KeyguardBouncer#hide");
        if (isFullyShowing()) {
            SysUiStatsLog.write(63, 1);
            this.dismissCallbackRegistry.notifyDismissCancelled();
        }
        KeyguardBouncerRepositoryImpl keyguardBouncerRepositoryImpl = this.repository;
        keyguardBouncerRepositoryImpl._primaryBouncerDisappearAnimation.setValue(null);
        this.falsingCollector.onBouncerHidden();
        KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) this.keyguardStateController;
        if (keyguardStateControllerImpl.mPrimaryBouncerShowing) {
            keyguardStateControllerImpl.mPrimaryBouncerShowing = false;
            keyguardStateControllerImpl.invokeForEachCallback(new KeyguardStateControllerImpl$$ExternalSyntheticLambda0(1));
        }
        boolean z = DejankUtils.STRICT_MODE_ENABLED;
        Assert.isMainThread();
        ArrayList arrayList = DejankUtils.sPendingRunnables;
        PrimaryBouncerInteractor$showRunnable$1 primaryBouncerInteractor$showRunnable$1 = this.showRunnable;
        arrayList.remove(primaryBouncerInteractor$showRunnable$1);
        DejankUtils.sHandler.removeCallbacks(primaryBouncerInteractor$showRunnable$1);
        this.mainHandler.removeCallbacks(primaryBouncerInteractor$showRunnable$1);
        Boolean bool = Boolean.FALSE;
        StateFlowImpl stateFlowImpl = keyguardBouncerRepositoryImpl._primaryBouncerShowingSoon;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, bool);
        StateFlowImpl stateFlowImpl2 = keyguardBouncerRepositoryImpl._primaryBouncerShow;
        stateFlowImpl2.getClass();
        stateFlowImpl2.updateState(null, bool);
        Float valueOf = Float.valueOf(1.0f);
        StateFlowImpl stateFlowImpl3 = keyguardBouncerRepositoryImpl._panelExpansionAmount;
        stateFlowImpl3.getClass();
        stateFlowImpl3.updateState(null, valueOf);
        Iterator it = this.primaryBouncerCallbackInteractor.expansionCallbacks.iterator();
        while (it.hasNext()) {
            ((PrimaryBouncerCallbackInteractor.PrimaryBouncerExpansionCallback) it.next()).onVisibilityChanged(false);
        }
        Trace.endSection();
    }

    public final boolean isAnimatingAway() {
        return this.repository.primaryBouncerStartingDisappearAnimation.getValue() != null;
    }

    public final boolean isBouncerShowing() {
        return ((Boolean) ((StateFlowImpl) this.isShowing.$$delegate_0).getValue()).booleanValue();
    }

    public final boolean isFullyShowing() {
        KeyguardBouncerRepositoryImpl keyguardBouncerRepositoryImpl = this.repository;
        return (((Boolean) ((StateFlowImpl) keyguardBouncerRepositoryImpl.primaryBouncerShowingSoon.$$delegate_0).getValue()).booleanValue() || isBouncerShowing()) && ((Number) ((StateFlowImpl) keyguardBouncerRepositoryImpl.panelExpansionAmount.$$delegate_0).getValue()).floatValue() == 0.0f && ((StateFlowImpl) keyguardBouncerRepositoryImpl.primaryBouncerStartingDisappearAnimation.$$delegate_0).getValue() == null;
    }

    public final boolean isInTransit() {
        KeyguardBouncerRepositoryImpl keyguardBouncerRepositoryImpl = this.repository;
        return ((Boolean) keyguardBouncerRepositoryImpl.primaryBouncerShowingSoon.getValue()).booleanValue() || !(((Number) keyguardBouncerRepositoryImpl.panelExpansionAmount.getValue()).floatValue() == 1.0f || ((Number) keyguardBouncerRepositoryImpl.panelExpansionAmount.getValue()).floatValue() == 0.0f);
    }

    public final void setDismissAction(ActivityStarter.OnDismissAction onDismissAction, Runnable runnable) {
        this.repository.getClass();
        KeyguardBouncerViewBinder$bind$delegate$1 delegate = this.primaryBouncerView.getDelegate();
        if (delegate != null) {
            KeyguardSecurityContainerController keyguardSecurityContainerController = delegate.$securityContainerController;
            Runnable runnable2 = keyguardSecurityContainerController.mCancelAction;
            if (runnable2 != null) {
                runnable2.run();
            }
            keyguardSecurityContainerController.mDismissAction = onDismissAction;
            keyguardSecurityContainerController.mCancelAction = runnable;
        }
    }

    public final void setPanelExpansion(float f) {
        KeyguardBouncerRepositoryImpl keyguardBouncerRepositoryImpl = this.repository;
        float floatValue = ((Number) ((StateFlowImpl) keyguardBouncerRepositoryImpl.panelExpansionAmount.$$delegate_0).getValue()).floatValue();
        boolean z = floatValue == f;
        if (((StateFlowImpl) keyguardBouncerRepositoryImpl.primaryBouncerStartingDisappearAnimation.$$delegate_0).getValue() == null) {
            Float valueOf = Float.valueOf(f);
            StateFlowImpl stateFlowImpl = keyguardBouncerRepositoryImpl._panelExpansionAmount;
            stateFlowImpl.getClass();
            stateFlowImpl.updateState(null, valueOf);
        }
        PrimaryBouncerCallbackInteractor primaryBouncerCallbackInteractor = this.primaryBouncerCallbackInteractor;
        if (f == 0.0f && floatValue != 0.0f) {
            this.falsingCollector.onBouncerShown();
            Iterator it = primaryBouncerCallbackInteractor.expansionCallbacks.iterator();
            while (it.hasNext()) {
                ((PrimaryBouncerCallbackInteractor.PrimaryBouncerExpansionCallback) it.next()).onFullyShown();
            }
        } else if (f == 1.0f && floatValue != 1.0f) {
            hide();
            DejankUtils.postAfterTraversal(new PrimaryBouncerInteractor$showRunnable$1(this, 1));
            Iterator it2 = primaryBouncerCallbackInteractor.expansionCallbacks.iterator();
            while (it2.hasNext()) {
                ((PrimaryBouncerCallbackInteractor.PrimaryBouncerExpansionCallback) it2.next()).onFullyHidden();
            }
        } else if (f != 0.0f && floatValue == 0.0f) {
            Iterator it3 = primaryBouncerCallbackInteractor.expansionCallbacks.iterator();
            while (it3.hasNext()) {
                ((PrimaryBouncerCallbackInteractor.PrimaryBouncerExpansionCallback) it3.next()).onStartingToHide();
            }
            Boolean bool = Boolean.TRUE;
            StateFlowImpl stateFlowImpl2 = keyguardBouncerRepositoryImpl._primaryBouncerStartingToHide;
            stateFlowImpl2.getClass();
            stateFlowImpl2.updateState(null, bool);
        }
        if (z) {
            return;
        }
        Iterator it4 = primaryBouncerCallbackInteractor.expansionCallbacks.iterator();
        while (it4.hasNext()) {
            ((PrimaryBouncerCallbackInteractor.PrimaryBouncerExpansionCallback) it4.next()).onExpansionChanged(f);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0070 A[Catch: all -> 0x004a, TryCatch #0 {all -> 0x004a, blocks: (B:7:0x0013, B:10:0x0037, B:14:0x0062, B:16:0x0070, B:18:0x0076, B:19:0x007e, B:21:0x0084, B:25:0x009a, B:27:0x00a8, B:30:0x00b3, B:35:0x00c4, B:38:0x00ce, B:39:0x00de, B:41:0x00e6, B:42:0x00f1, B:43:0x00f9, B:45:0x00ff, B:50:0x00db, B:53:0x004d), top: B:6:0x0013 }] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0076 A[Catch: all -> 0x004a, TryCatch #0 {all -> 0x004a, blocks: (B:7:0x0013, B:10:0x0037, B:14:0x0062, B:16:0x0070, B:18:0x0076, B:19:0x007e, B:21:0x0084, B:25:0x009a, B:27:0x00a8, B:30:0x00b3, B:35:0x00c4, B:38:0x00ce, B:39:0x00de, B:41:0x00e6, B:42:0x00f1, B:43:0x00f9, B:45:0x00ff, B:50:0x00db, B:53:0x004d), top: B:6:0x0013 }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0084 A[Catch: all -> 0x004a, TRY_LEAVE, TryCatch #0 {all -> 0x004a, blocks: (B:7:0x0013, B:10:0x0037, B:14:0x0062, B:16:0x0070, B:18:0x0076, B:19:0x007e, B:21:0x0084, B:25:0x009a, B:27:0x00a8, B:30:0x00b3, B:35:0x00c4, B:38:0x00ce, B:39:0x00de, B:41:0x00e6, B:42:0x00f1, B:43:0x00f9, B:45:0x00ff, B:50:0x00db, B:53:0x004d), top: B:6:0x0013 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00a8 A[Catch: all -> 0x004a, TryCatch #0 {all -> 0x004a, blocks: (B:7:0x0013, B:10:0x0037, B:14:0x0062, B:16:0x0070, B:18:0x0076, B:19:0x007e, B:21:0x0084, B:25:0x009a, B:27:0x00a8, B:30:0x00b3, B:35:0x00c4, B:38:0x00ce, B:39:0x00de, B:41:0x00e6, B:42:0x00f1, B:43:0x00f9, B:45:0x00ff, B:50:0x00db, B:53:0x004d), top: B:6:0x0013 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00bd A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00c4 A[Catch: all -> 0x004a, TRY_ENTER, TryCatch #0 {all -> 0x004a, blocks: (B:7:0x0013, B:10:0x0037, B:14:0x0062, B:16:0x0070, B:18:0x0076, B:19:0x007e, B:21:0x0084, B:25:0x009a, B:27:0x00a8, B:30:0x00b3, B:35:0x00c4, B:38:0x00ce, B:39:0x00de, B:41:0x00e6, B:42:0x00f1, B:43:0x00f9, B:45:0x00ff, B:50:0x00db, B:53:0x004d), top: B:6:0x0013 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00e6 A[Catch: all -> 0x004a, TryCatch #0 {all -> 0x004a, blocks: (B:7:0x0013, B:10:0x0037, B:14:0x0062, B:16:0x0070, B:18:0x0076, B:19:0x007e, B:21:0x0084, B:25:0x009a, B:27:0x00a8, B:30:0x00b3, B:35:0x00c4, B:38:0x00ce, B:39:0x00de, B:41:0x00e6, B:42:0x00f1, B:43:0x00f9, B:45:0x00ff, B:50:0x00db, B:53:0x004d), top: B:6:0x0013 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00ff A[Catch: all -> 0x004a, LOOP:0: B:43:0x00f9->B:45:0x00ff, LOOP_END, TRY_LEAVE, TryCatch #0 {all -> 0x004a, blocks: (B:7:0x0013, B:10:0x0037, B:14:0x0062, B:16:0x0070, B:18:0x0076, B:19:0x007e, B:21:0x0084, B:25:0x009a, B:27:0x00a8, B:30:0x00b3, B:35:0x00c4, B:38:0x00ce, B:39:0x00de, B:41:0x00e6, B:42:0x00f1, B:43:0x00f9, B:45:0x00ff, B:50:0x00db, B:53:0x004d), top: B:6:0x0013 }] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x005f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean show(boolean r15) {
        /*
            Method dump skipped, instructions count: 273
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.bouncer.domain.interactor.PrimaryBouncerInteractor.show(boolean):boolean");
    }

    public final boolean willRunDismissFromKeyguard() {
        KeyguardBouncerViewBinder$bind$delegate$1 delegate = this.primaryBouncerView.getDelegate();
        return delegate != null && delegate.$securityContainerController.mWillRunDismissFromKeyguard;
    }
}
