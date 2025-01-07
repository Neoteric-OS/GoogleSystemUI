package com.android.systemui.keyguard.domain.interactor;

import android.app.AlertDialog;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.util.StatsEvent;
import android.util.StatsLog;
import com.android.app.tracing.coroutines.CoroutineTracingKt;
import com.android.internal.widget.LockPatternUtils;
import com.android.keyguard.logging.KeyguardQuickAffordancesLogger;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.animation.Expandable$Companion$fromView$1;
import com.android.systemui.animation.TransitionAnimator;
import com.android.systemui.dock.DockManager;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig;
import com.android.systemui.keyguard.data.repository.BiometricSettingsRepositoryImpl;
import com.android.systemui.keyguard.data.repository.KeyguardQuickAffordanceRepository;
import com.android.systemui.keyguard.shared.quickaffordance.KeyguardQuickAffordancesMetricsLoggerImpl;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import dagger.Lazy;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardQuickAffordanceInteractor {
    public final ActivityStarter activityStarter;
    public final Context appContext;
    public final CoroutineDispatcher backgroundDispatcher;
    public final BiometricSettingsRepositoryImpl biometricSettingsRepository;
    public final DevicePolicyManager devicePolicyManager;
    public final DockManager dockManager;
    public final FeatureFlags featureFlags;
    public final KeyguardInteractor keyguardInteractor;
    public final KeyguardStateController keyguardStateController;
    public final DialogTransitionAnimator launchAnimator;
    public final LockPatternUtils lockPatternUtils;
    public final KeyguardQuickAffordancesLogger logger;
    public final KeyguardQuickAffordancesMetricsLoggerImpl metricsLogger;
    public final Lazy repository;
    public final ShadeInteractor shadeInteractor;
    public final UserTracker userTracker;

    public KeyguardQuickAffordanceInteractor(KeyguardInteractor keyguardInteractor, ShadeInteractor shadeInteractor, LockPatternUtils lockPatternUtils, KeyguardStateController keyguardStateController, UserTracker userTracker, ActivityStarter activityStarter, FeatureFlags featureFlags, Lazy lazy, DialogTransitionAnimator dialogTransitionAnimator, KeyguardQuickAffordancesLogger keyguardQuickAffordancesLogger, KeyguardQuickAffordancesMetricsLoggerImpl keyguardQuickAffordancesMetricsLoggerImpl, DevicePolicyManager devicePolicyManager, DockManager dockManager, BiometricSettingsRepositoryImpl biometricSettingsRepositoryImpl, CoroutineDispatcher coroutineDispatcher, Context context, Lazy lazy2) {
        this.keyguardInteractor = keyguardInteractor;
        this.shadeInteractor = shadeInteractor;
        this.lockPatternUtils = lockPatternUtils;
        this.keyguardStateController = keyguardStateController;
        this.userTracker = userTracker;
        this.activityStarter = activityStarter;
        this.featureFlags = featureFlags;
        this.repository = lazy;
        this.launchAnimator = dialogTransitionAnimator;
        this.logger = keyguardQuickAffordancesLogger;
        this.devicePolicyManager = devicePolicyManager;
        this.dockManager = dockManager;
        this.biometricSettingsRepository = biometricSettingsRepositoryImpl;
        this.backgroundDispatcher = coroutineDispatcher;
        this.appContext = context;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0022  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getPickerFlags(kotlin.coroutines.jvm.internal.ContinuationImpl r8) {
        /*
            Method dump skipped, instructions count: 237
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.KeyguardQuickAffordanceInteractor.getPickerFlags(kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00ab A[LOOP:0: B:15:0x00a5->B:17:0x00ab, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0022  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getSelections(kotlin.coroutines.jvm.internal.ContinuationImpl r7) {
        /*
            Method dump skipped, instructions count: 280
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.KeyguardQuickAffordanceInteractor.getSelections(kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getSlotPickerRepresentations(kotlin.coroutines.jvm.internal.ContinuationImpl r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.android.systemui.keyguard.domain.interactor.KeyguardQuickAffordanceInteractor$getSlotPickerRepresentations$1
            if (r0 == 0) goto L13
            r0 = r5
            com.android.systemui.keyguard.domain.interactor.KeyguardQuickAffordanceInteractor$getSlotPickerRepresentations$1 r0 = (com.android.systemui.keyguard.domain.interactor.KeyguardQuickAffordanceInteractor$getSlotPickerRepresentations$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.keyguard.domain.interactor.KeyguardQuickAffordanceInteractor$getSlotPickerRepresentations$1 r0 = new com.android.systemui.keyguard.domain.interactor.KeyguardQuickAffordanceInteractor$getSlotPickerRepresentations$1
            r0.<init>(r4, r5)
        L18:
            java.lang.Object r5 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L33
            if (r2 != r3) goto L2b
            java.lang.Object r4 = r0.L$0
            com.android.systemui.keyguard.domain.interactor.KeyguardQuickAffordanceInteractor r4 = (com.android.systemui.keyguard.domain.interactor.KeyguardQuickAffordanceInteractor) r4
            kotlin.ResultKt.throwOnFailure(r5)
            goto L41
        L2b:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L33:
            kotlin.ResultKt.throwOnFailure(r5)
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r5 = r4.isFeatureDisabledByDevicePolicy(r0)
            if (r5 != r1) goto L41
            return r1
        L41:
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            if (r5 == 0) goto L4c
            kotlin.collections.EmptyList r4 = kotlin.collections.EmptyList.INSTANCE
            return r4
        L4c:
            dagger.Lazy r4 = r4.repository
            java.lang.Object r4 = r4.get()
            com.android.systemui.keyguard.data.repository.KeyguardQuickAffordanceRepository r4 = (com.android.systemui.keyguard.data.repository.KeyguardQuickAffordanceRepository) r4
            java.util.List r4 = r4.getSlotPickerRepresentations()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.KeyguardQuickAffordanceInteractor.getSlotPickerRepresentations(kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public final Object isFeatureDisabledByDevicePolicy(ContinuationImpl continuationImpl) {
        return CoroutineTracingKt.withContext(this.backgroundDispatcher, new KeyguardQuickAffordanceInteractor$isFeatureDisabledByDevicePolicy$2(this, null), continuationImpl);
    }

    public final void onQuickAffordanceTriggered(String str, Expandable$Companion$fromView$1 expandable$Companion$fromView$1, String str2) {
        KeyguardQuickAffordanceConfig keyguardQuickAffordanceConfig;
        Object obj;
        List split$default = StringsKt.split$default(str, new String[]{"::"}, 0, 6);
        Pair pair = new Pair(split$default.get(0), split$default.get(1));
        String str3 = (String) pair.component1();
        String str4 = (String) pair.component2();
        List list = (List) ((Map) ((StateFlowImpl) ((KeyguardQuickAffordanceRepository) this.repository.get()).selections.$$delegate_0).getValue()).get(str3);
        if (list != null) {
            Iterator it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                } else {
                    obj = it.next();
                    if (Intrinsics.areEqual(((KeyguardQuickAffordanceConfig) obj).getKey(), str4)) {
                        break;
                    }
                }
            }
            keyguardQuickAffordanceConfig = (KeyguardQuickAffordanceConfig) obj;
        } else {
            keyguardQuickAffordanceConfig = null;
        }
        if (keyguardQuickAffordanceConfig == null) {
            Log.e("KeyguardQuickAffordanceInteractor", "Affordance config with key of \"" + str + "\" not found!");
            return;
        }
        this.logger.logQuickAffordanceTriggered(str3, str4);
        StatsEvent.Builder newBuilder = StatsEvent.newBuilder();
        newBuilder.setAtomId(612);
        newBuilder.writeString(str2);
        newBuilder.writeString(str);
        newBuilder.usePooledBuffer();
        StatsLog.write(newBuilder.build());
        KeyguardQuickAffordanceConfig.OnTriggeredResult onTriggered = keyguardQuickAffordanceConfig.onTriggered(expandable$Companion$fromView$1);
        if (onTriggered instanceof KeyguardQuickAffordanceConfig.OnTriggeredResult.StartActivity) {
            KeyguardQuickAffordanceConfig.OnTriggeredResult.StartActivity startActivity = (KeyguardQuickAffordanceConfig.OnTriggeredResult.StartActivity) onTriggered;
            Intent intent = startActivity.intent;
            int strongAuthForUser = this.lockPatternUtils.getStrongAuthForUser(((UserTrackerImpl) this.userTracker).getUserHandle().getIdentifier());
            ActivityStarter activityStarter = this.activityStarter;
            if (strongAuthForUser != 1 && (startActivity.canShowWhileLocked || this.keyguardStateController.isUnlocked())) {
                activityStarter.startActivity(intent, true, expandable$Companion$fromView$1.activityTransitionController(null), true);
                return;
            } else {
                activityStarter.postStartActivityDismissingKeyguard(intent, 0, expandable$Companion$fromView$1.activityTransitionController(null));
                return;
            }
        }
        if ((onTriggered instanceof KeyguardQuickAffordanceConfig.OnTriggeredResult.Handled) || !(onTriggered instanceof KeyguardQuickAffordanceConfig.OnTriggeredResult.ShowDialog)) {
            return;
        }
        KeyguardQuickAffordanceConfig.OnTriggeredResult.ShowDialog showDialog = (KeyguardQuickAffordanceConfig.OnTriggeredResult.ShowDialog) onTriggered;
        AlertDialog alertDialog = showDialog.dialog;
        DialogTransitionAnimator.Controller dialogTransitionController = showDialog.expandable.dialogTransitionController(null);
        if (dialogTransitionController != null) {
            SystemUIDialog.applyFlags(alertDialog, true);
            SystemUIDialog.setShowForAllUsers(alertDialog);
            SystemUIDialog.registerDismissListener(alertDialog, null);
            SystemUIDialog.setDialogSize(alertDialog);
            TransitionAnimator.Timings timings = DialogTransitionAnimator.TIMINGS;
            this.launchAnimator.show(alertDialog, dialogTransitionController, false);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object quickAffordance(com.android.systemui.keyguard.shared.quickaffordance.KeyguardQuickAffordancePosition r11, kotlin.coroutines.jvm.internal.ContinuationImpl r12) {
        /*
            r10 = this;
            boolean r0 = r12 instanceof com.android.systemui.keyguard.domain.interactor.KeyguardQuickAffordanceInteractor$quickAffordance$1
            if (r0 == 0) goto L13
            r0 = r12
            com.android.systemui.keyguard.domain.interactor.KeyguardQuickAffordanceInteractor$quickAffordance$1 r0 = (com.android.systemui.keyguard.domain.interactor.KeyguardQuickAffordanceInteractor$quickAffordance$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.keyguard.domain.interactor.KeyguardQuickAffordanceInteractor$quickAffordance$1 r0 = new com.android.systemui.keyguard.domain.interactor.KeyguardQuickAffordanceInteractor$quickAffordance$1
            r0.<init>(r10, r12)
        L18:
            java.lang.Object r12 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 0
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L44
            if (r2 == r5) goto L37
            if (r2 != r4) goto L2f
            java.lang.Object r10 = r0.L$0
            com.android.systemui.keyguard.domain.interactor.KeyguardQuickAffordanceInteractor r10 = (com.android.systemui.keyguard.domain.interactor.KeyguardQuickAffordanceInteractor) r10
            kotlin.ResultKt.throwOnFailure(r12)
            goto L71
        L2f:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L37:
            java.lang.Object r10 = r0.L$1
            r11 = r10
            com.android.systemui.keyguard.shared.quickaffordance.KeyguardQuickAffordancePosition r11 = (com.android.systemui.keyguard.shared.quickaffordance.KeyguardQuickAffordancePosition) r11
            java.lang.Object r10 = r0.L$0
            com.android.systemui.keyguard.domain.interactor.KeyguardQuickAffordanceInteractor r10 = (com.android.systemui.keyguard.domain.interactor.KeyguardQuickAffordanceInteractor) r10
            kotlin.ResultKt.throwOnFailure(r12)
            goto L54
        L44:
            kotlin.ResultKt.throwOnFailure(r12)
            r0.L$0 = r10
            r0.L$1 = r11
            r0.label = r5
            java.lang.Object r12 = r10.isFeatureDisabledByDevicePolicy(r0)
            if (r12 != r1) goto L54
            return r1
        L54:
            java.lang.Boolean r12 = (java.lang.Boolean) r12
            boolean r12 = r12.booleanValue()
            if (r12 == 0) goto L64
            com.android.systemui.keyguard.domain.model.KeyguardQuickAffordanceModel$Hidden r10 = com.android.systemui.keyguard.domain.model.KeyguardQuickAffordanceModel.Hidden.INSTANCE
            kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2 r11 = new kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2
            r11.<init>(r10)
            return r11
        L64:
            r0.L$0 = r10
            r0.L$1 = r3
            r0.label = r4
            java.lang.Object r12 = r10.quickAffordanceAlwaysVisible(r11, r3, r0)
            if (r12 != r1) goto L71
            return r1
        L71:
            r4 = r12
            kotlinx.coroutines.flow.Flow r4 = (kotlinx.coroutines.flow.Flow) r4
            com.android.systemui.keyguard.domain.interactor.KeyguardInteractor r11 = r10.keyguardInteractor
            kotlinx.coroutines.flow.ReadonlyStateFlow r5 = r11.isDozing
            com.android.systemui.shade.domain.interactor.ShadeInteractor r12 = r10.shadeInteractor
            com.android.systemui.shade.domain.interactor.ShadeInteractorImpl r12 = (com.android.systemui.shade.domain.interactor.ShadeInteractorImpl) r12
            com.android.systemui.shade.domain.interactor.BaseShadeInteractor r12 = r12.baseShadeInteractor
            kotlinx.coroutines.flow.StateFlow r12 = r12.getAnyExpansion()
            com.android.systemui.keyguard.domain.interactor.KeyguardQuickAffordanceInteractor$useLongPress$$inlined$map$1 r0 = new com.android.systemui.keyguard.domain.interactor.KeyguardQuickAffordanceInteractor$useLongPress$$inlined$map$1
            r1 = 1
            r0.<init>(r12, r1)
            kotlinx.coroutines.flow.Flow r7 = kotlinx.coroutines.flow.FlowKt.distinctUntilChanged(r0)
            com.android.systemui.keyguard.data.repository.BiometricSettingsRepositoryImpl r10 = r10.biometricSettingsRepository
            com.android.systemui.keyguard.data.repository.BiometricSettingsRepositoryImpl$special$$inlined$map$1 r8 = r10.isCurrentUserInLockdown
            com.android.systemui.keyguard.domain.interactor.KeyguardQuickAffordanceInteractor$quickAffordance$4 r9 = new com.android.systemui.keyguard.domain.interactor.KeyguardQuickAffordanceInteractor$quickAffordance$4
            r9.<init>(r3)
            kotlinx.coroutines.flow.MutableStateFlow r6 = r11.isKeyguardShowing
            kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 r10 = kotlinx.coroutines.flow.FlowKt.combine(r4, r5, r6, r7, r8, r9)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.KeyguardQuickAffordanceInteractor.quickAffordance(com.android.systemui.keyguard.shared.quickaffordance.KeyguardQuickAffordancePosition, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object quickAffordanceAlwaysVisible(com.android.systemui.keyguard.shared.quickaffordance.KeyguardQuickAffordancePosition r5, java.lang.String r6, kotlin.coroutines.jvm.internal.ContinuationImpl r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof com.android.systemui.keyguard.domain.interactor.KeyguardQuickAffordanceInteractor$quickAffordanceAlwaysVisible$1
            if (r0 == 0) goto L13
            r0 = r7
            com.android.systemui.keyguard.domain.interactor.KeyguardQuickAffordanceInteractor$quickAffordanceAlwaysVisible$1 r0 = (com.android.systemui.keyguard.domain.interactor.KeyguardQuickAffordanceInteractor$quickAffordanceAlwaysVisible$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.keyguard.domain.interactor.KeyguardQuickAffordanceInteractor$quickAffordanceAlwaysVisible$1 r0 = new com.android.systemui.keyguard.domain.interactor.KeyguardQuickAffordanceInteractor$quickAffordanceAlwaysVisible$1
            r0.<init>(r4, r7)
        L18:
            java.lang.Object r7 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3d
            if (r2 != r3) goto L35
            java.lang.Object r4 = r0.L$2
            r6 = r4
            java.lang.String r6 = (java.lang.String) r6
            java.lang.Object r4 = r0.L$1
            r5 = r4
            com.android.systemui.keyguard.shared.quickaffordance.KeyguardQuickAffordancePosition r5 = (com.android.systemui.keyguard.shared.quickaffordance.KeyguardQuickAffordancePosition) r5
            java.lang.Object r4 = r0.L$0
            com.android.systemui.keyguard.domain.interactor.KeyguardQuickAffordanceInteractor r4 = (com.android.systemui.keyguard.domain.interactor.KeyguardQuickAffordanceInteractor) r4
            kotlin.ResultKt.throwOnFailure(r7)
            goto L4f
        L35:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L3d:
            kotlin.ResultKt.throwOnFailure(r7)
            r0.L$0 = r4
            r0.L$1 = r5
            r0.L$2 = r6
            r0.label = r3
            java.lang.Object r7 = r4.isFeatureDisabledByDevicePolicy(r0)
            if (r7 != r1) goto L4f
            return r1
        L4f:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r7 = r7.booleanValue()
            if (r7 == 0) goto L5f
            com.android.systemui.keyguard.domain.model.KeyguardQuickAffordanceModel$Hidden r4 = com.android.systemui.keyguard.domain.model.KeyguardQuickAffordanceModel.Hidden.INSTANCE
            kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2 r5 = new kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2
            r5.<init>(r4)
            goto L78
        L5f:
            dagger.Lazy r7 = r4.repository
            java.lang.Object r7 = r7.get()
            com.android.systemui.keyguard.data.repository.KeyguardQuickAffordanceRepository r7 = (com.android.systemui.keyguard.data.repository.KeyguardQuickAffordanceRepository) r7
            kotlinx.coroutines.flow.ReadonlyStateFlow r7 = r7.selections
            com.android.systemui.keyguard.domain.interactor.KeyguardQuickAffordanceInteractor$combinedConfigs$$inlined$combine$1 r0 = new com.android.systemui.keyguard.domain.interactor.KeyguardQuickAffordanceInteractor$combinedConfigs$$inlined$combine$1
            r0.<init>(r7, r6, r5, r4)
            com.android.systemui.keyguard.domain.interactor.KeyguardQuickAffordanceInteractor$quickAffordanceInternal$$inlined$flatMapLatest$1 r6 = new com.android.systemui.keyguard.domain.interactor.KeyguardQuickAffordanceInteractor$quickAffordanceInternal$$inlined$flatMapLatest$1
            r7 = 0
            r6.<init>(r7, r4, r5)
            kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest r5 = kotlinx.coroutines.flow.FlowKt.transformLatest(r0, r6)
        L78:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.KeyguardQuickAffordanceInteractor.quickAffordanceAlwaysVisible(com.android.systemui.keyguard.shared.quickaffordance.KeyguardQuickAffordancePosition, java.lang.String, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object select(java.lang.String r5, java.lang.String r6, kotlin.coroutines.jvm.internal.ContinuationImpl r7) {
        /*
            Method dump skipped, instructions count: 243
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.KeyguardQuickAffordanceInteractor.select(java.lang.String, java.lang.String, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object unselect(java.lang.String r5, java.lang.String r6, kotlin.coroutines.jvm.internal.ContinuationImpl r7) {
        /*
            Method dump skipped, instructions count: 255
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.KeyguardQuickAffordanceInteractor.unselect(java.lang.String, java.lang.String, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }
}
