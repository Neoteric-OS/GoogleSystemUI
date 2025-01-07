package com.android.systemui.communal.domain.interactor;

import android.app.DreamManager;
import com.android.systemui.common.usagestats.domain.UsageStatsInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.Logger;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.shared.system.TaskStackChangeListeners;
import com.android.systemui.util.time.SystemClock;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class WidgetTrampolineInteractor {
    public final ActivityStarter activityStarter;
    public final CoroutineScope bgScope;
    public final DreamManager dreamManager;
    public final KeyguardTransitionInteractor keyguardTransitionInteractor;
    public final Logger logger;
    public final SystemClock systemClock;
    public final TaskStackChangeListeners taskStackChangeListeners;
    public final UsageStatsInteractor usageStatsInteractor;

    public WidgetTrampolineInteractor(ActivityStarter activityStarter, SystemClock systemClock, KeyguardTransitionInteractor keyguardTransitionInteractor, TaskStackChangeListeners taskStackChangeListeners, UsageStatsInteractor usageStatsInteractor, DreamManager dreamManager, CoroutineScope coroutineScope, LogBuffer logBuffer) {
        this.activityStarter = activityStarter;
        this.systemClock = systemClock;
        this.keyguardTransitionInteractor = keyguardTransitionInteractor;
        this.taskStackChangeListeners = taskStackChangeListeners;
        this.usageStatsInteractor = usageStatsInteractor;
        this.dreamManager = dreamManager;
        this.bgScope = coroutineScope;
        this.logger = new Logger(logBuffer, "WidgetTrampolineInteractor");
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:36:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002b  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x00c7 -> B:11:0x0038). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$waitForActivityStartByPolling(com.android.systemui.communal.domain.interactor.WidgetTrampolineInteractor r18, long r19, kotlin.coroutines.jvm.internal.ContinuationImpl r21) {
        /*
            r0 = r21
            r18.getClass()
            boolean r1 = r0 instanceof com.android.systemui.communal.domain.interactor.WidgetTrampolineInteractor$waitForActivityStartByPolling$1
            if (r1 == 0) goto L1a
            r1 = r0
            com.android.systemui.communal.domain.interactor.WidgetTrampolineInteractor$waitForActivityStartByPolling$1 r1 = (com.android.systemui.communal.domain.interactor.WidgetTrampolineInteractor$waitForActivityStartByPolling$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L1a
            int r2 = r2 - r3
            r1.label = r2
            r2 = r18
            goto L21
        L1a:
            com.android.systemui.communal.domain.interactor.WidgetTrampolineInteractor$waitForActivityStartByPolling$1 r1 = new com.android.systemui.communal.domain.interactor.WidgetTrampolineInteractor$waitForActivityStartByPolling$1
            r2 = r18
            r1.<init>(r2, r0)
        L21:
            java.lang.Object r0 = r1.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r3 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r4 = r1.label
            r5 = 2
            r6 = 1
            if (r4 == 0) goto L4d
            if (r4 == r6) goto L43
            if (r4 != r5) goto L3b
            long r7 = r1.J$0
            java.lang.Object r2 = r1.L$0
            com.android.systemui.communal.domain.interactor.WidgetTrampolineInteractor r2 = (com.android.systemui.communal.domain.interactor.WidgetTrampolineInteractor) r2
            kotlin.ResultKt.throwOnFailure(r0)
        L38:
            r4 = r1
            r0 = r7
            goto L53
        L3b:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L43:
            long r7 = r1.J$0
            java.lang.Object r2 = r1.L$0
            com.android.systemui.communal.domain.interactor.WidgetTrampolineInteractor r2 = (com.android.systemui.communal.domain.interactor.WidgetTrampolineInteractor) r2
            kotlin.ResultKt.throwOnFailure(r0)
            goto L8f
        L4d:
            kotlin.ResultKt.throwOnFailure(r0)
            r4 = r1
            r0 = r19
        L53:
            com.android.systemui.common.usagestats.domain.UsageStatsInteractor r14 = r2.usageStatsInteractor
            r4.L$0 = r2
            r4.J$0 = r0
            r4.label = r6
            com.android.systemui.util.time.SystemClock r7 = r14.systemClock
            com.android.systemui.util.time.SystemClockImpl r7 = (com.android.systemui.util.time.SystemClockImpl) r7
            r7.getClass()
            long r11 = java.lang.System.currentTimeMillis()
            android.os.UserHandle r7 = android.os.UserHandle.CURRENT
            kotlin.collections.EmptyList r13 = kotlin.collections.EmptyList.INSTANCE
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual(r7, r7)
            if (r8 == 0) goto L78
            com.android.systemui.settings.UserTracker r7 = r14.userTracker
            com.android.systemui.settings.UserTrackerImpl r7 = (com.android.systemui.settings.UserTrackerImpl) r7
            android.os.UserHandle r7 = r7.getUserHandle()
        L78:
            r8 = r7
            com.android.systemui.common.usagestats.data.model.UsageStatsQuery r15 = new com.android.systemui.common.usagestats.data.model.UsageStatsQuery
            r7 = r15
            r9 = r0
            r7.<init>(r8, r9, r11, r13)
            com.android.systemui.common.usagestats.data.repository.UsageStatsRepositoryImpl r7 = r14.repository
            java.lang.Object r7 = r7.queryActivityEvents(r15, r4)
            if (r7 != r3) goto L89
            goto Lc9
        L89:
            r16 = r0
            r1 = r4
            r0 = r7
            r7 = r16
        L8f:
            java.util.List r0 = (java.util.List) r0
            if (r0 == 0) goto L9a
            boolean r4 = r0.isEmpty()
            if (r4 == 0) goto L9a
            goto Lb3
        L9a:
            java.util.Iterator r0 = r0.iterator()
        L9e:
            boolean r4 = r0.hasNext()
            if (r4 == 0) goto Lb3
            java.lang.Object r4 = r0.next()
            com.android.systemui.common.usagestats.shared.model.ActivityEventModel r4 = (com.android.systemui.common.usagestats.shared.model.ActivityEventModel) r4
            com.android.systemui.common.usagestats.shared.model.ActivityEventModel$Lifecycle r4 = r4.lifecycle
            com.android.systemui.common.usagestats.shared.model.ActivityEventModel$Lifecycle r9 = com.android.systemui.common.usagestats.shared.model.ActivityEventModel.Lifecycle.RESUMED
            if (r4 != r9) goto L9e
            java.lang.Boolean r3 = java.lang.Boolean.TRUE
            goto Lc9
        Lb3:
            int r0 = kotlin.time.Duration.$r8$clinit
            r0 = 200(0xc8, float:2.8E-43)
            kotlin.time.DurationUnit r4 = kotlin.time.DurationUnit.MILLISECONDS
            long r9 = kotlin.time.DurationKt.toDuration(r0, r4)
            r1.L$0 = r2
            r1.J$0 = r7
            r1.label = r5
            java.lang.Object r0 = kotlinx.coroutines.DelayKt.m1784delayVtjQ1oo(r9, r1)
            if (r0 != r3) goto L38
        Lc9:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.communal.domain.interactor.WidgetTrampolineInteractor.access$waitForActivityStartByPolling(com.android.systemui.communal.domain.interactor.WidgetTrampolineInteractor, long, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object waitForActivityStartAndDismissKeyguard(kotlin.coroutines.jvm.internal.ContinuationImpl r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.android.systemui.communal.domain.interactor.WidgetTrampolineInteractor$waitForActivityStartAndDismissKeyguard$1
            if (r0 == 0) goto L13
            r0 = r5
            com.android.systemui.communal.domain.interactor.WidgetTrampolineInteractor$waitForActivityStartAndDismissKeyguard$1 r0 = (com.android.systemui.communal.domain.interactor.WidgetTrampolineInteractor$waitForActivityStartAndDismissKeyguard$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.communal.domain.interactor.WidgetTrampolineInteractor$waitForActivityStartAndDismissKeyguard$1 r0 = new com.android.systemui.communal.domain.interactor.WidgetTrampolineInteractor$waitForActivityStartAndDismissKeyguard$1
            r0.<init>(r4, r5)
        L18:
            java.lang.Object r5 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L33
            if (r2 != r3) goto L2b
            java.lang.Object r4 = r0.L$0
            com.android.systemui.communal.domain.interactor.WidgetTrampolineInteractor r4 = (com.android.systemui.communal.domain.interactor.WidgetTrampolineInteractor) r4
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
            java.lang.Object r5 = r4.waitForActivityStartWhileOnHub(r0)
            if (r5 != r1) goto L41
            return r1
        L41:
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            if (r5 == 0) goto L5d
            com.android.systemui.log.core.Logger r5 = r4.logger
            java.lang.String r0 = "Detected trampoline, requesting unlock"
            r1 = 0
            r2 = 2
            com.android.systemui.log.core.Logger.d$default(r5, r0, r1, r2, r1)
            com.android.systemui.communal.domain.interactor.WidgetTrampolineInteractor$waitForActivityStartAndDismissKeyguard$2 r5 = new com.android.systemui.communal.domain.interactor.WidgetTrampolineInteractor$waitForActivityStartAndDismissKeyguard$2
            r5.<init>()
            r0 = 0
            com.android.systemui.plugins.ActivityStarter r4 = r4.activityStarter
            r4.dismissKeyguardThenExecute(r5, r1, r0)
        L5d:
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.communal.domain.interactor.WidgetTrampolineInteractor.waitForActivityStartAndDismissKeyguard(kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object waitForActivityStartWhileOnHub(kotlin.coroutines.jvm.internal.ContinuationImpl r9) {
        /*
            r8 = this;
            boolean r0 = r9 instanceof com.android.systemui.communal.domain.interactor.WidgetTrampolineInteractor$waitForActivityStartWhileOnHub$1
            if (r0 == 0) goto L13
            r0 = r9
            com.android.systemui.communal.domain.interactor.WidgetTrampolineInteractor$waitForActivityStartWhileOnHub$1 r0 = (com.android.systemui.communal.domain.interactor.WidgetTrampolineInteractor$waitForActivityStartWhileOnHub$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.communal.domain.interactor.WidgetTrampolineInteractor$waitForActivityStartWhileOnHub$1 r0 = new com.android.systemui.communal.domain.interactor.WidgetTrampolineInteractor$waitForActivityStartWhileOnHub$1
            r0.<init>(r8, r9)
        L18:
            java.lang.Object r9 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L2f
            if (r2 != r3) goto L27
            kotlin.ResultKt.throwOnFailure(r9)     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L59
            goto L58
        L27:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L2f:
            kotlin.ResultKt.throwOnFailure(r9)
            com.android.systemui.util.time.SystemClock r9 = r8.systemClock
            com.android.systemui.util.time.SystemClockImpl r9 = (com.android.systemui.util.time.SystemClockImpl) r9
            r9.getClass()
            long r4 = java.lang.System.currentTimeMillis()
            int r9 = kotlin.time.Duration.$r8$clinit     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L59
            kotlin.time.DurationUnit r9 = kotlin.time.DurationUnit.SECONDS     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L59
            long r6 = kotlin.time.DurationKt.toDuration(r3, r9)     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L59
            com.android.systemui.communal.domain.interactor.WidgetTrampolineInteractor$waitForActivityStartWhileOnHub$2 r9 = new com.android.systemui.communal.domain.interactor.WidgetTrampolineInteractor$waitForActivityStartWhileOnHub$2     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L59
            r2 = 0
            r9.<init>(r8, r4, r2)     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L59
            r0.label = r3     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L59
            long r2 = kotlinx.coroutines.DelayKt.m1785toDelayMillisLRDsOJo(r6)     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L59
            java.lang.Object r9 = kotlinx.coroutines.TimeoutKt.withTimeout(r2, r9, r0)     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L59
            if (r9 != r1) goto L58
            return r1
        L58:
            return r9
        L59:
            java.lang.Boolean r8 = java.lang.Boolean.FALSE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.communal.domain.interactor.WidgetTrampolineInteractor.waitForActivityStartWhileOnHub(kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }
}
