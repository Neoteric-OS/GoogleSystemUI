package com.android.systemui.statusbar.notification.collection.coordinator;

import com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.StatusBarState;
import com.android.systemui.statusbar.StatusBarStateControllerImpl;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DreamCoordinator implements Coordinator {
    public boolean isLockscreenHostedDream;
    public boolean isOnKeyguard;
    public final KeyguardRepositoryImpl keyguardRepository;
    public final CoroutineScope scope;
    public final SysuiStatusBarStateController statusBarStateController;
    public final DreamCoordinator$filter$1 filter = new DreamCoordinator$filter$1(this);
    public final DreamCoordinator$statusBarStateListener$1 statusBarStateListener = new StatusBarStateController.StateListener() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.DreamCoordinator$statusBarStateListener$1
        @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
        public final void onStateChanged(int i) {
            DreamCoordinator.this.recordStatusBarState(i);
        }
    };

    /* JADX WARN: Type inference failed for: r1v2, types: [com.android.systemui.statusbar.notification.collection.coordinator.DreamCoordinator$statusBarStateListener$1] */
    public DreamCoordinator(SysuiStatusBarStateController sysuiStatusBarStateController, CoroutineScope coroutineScope, KeyguardRepositoryImpl keyguardRepositoryImpl) {
        this.statusBarStateController = sysuiStatusBarStateController;
        this.scope = coroutineScope;
        this.keyguardRepository = keyguardRepositoryImpl;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void access$attachFilterOnDreamingStateChange(final com.android.systemui.statusbar.notification.collection.coordinator.DreamCoordinator r4, kotlin.coroutines.jvm.internal.ContinuationImpl r5) {
        /*
            r4.getClass()
            boolean r0 = r5 instanceof com.android.systemui.statusbar.notification.collection.coordinator.DreamCoordinator$attachFilterOnDreamingStateChange$1
            if (r0 == 0) goto L16
            r0 = r5
            com.android.systemui.statusbar.notification.collection.coordinator.DreamCoordinator$attachFilterOnDreamingStateChange$1 r0 = (com.android.systemui.statusbar.notification.collection.coordinator.DreamCoordinator$attachFilterOnDreamingStateChange$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L16
            int r1 = r1 - r2
            r0.label = r1
            goto L1b
        L16:
            com.android.systemui.statusbar.notification.collection.coordinator.DreamCoordinator$attachFilterOnDreamingStateChange$1 r0 = new com.android.systemui.statusbar.notification.collection.coordinator.DreamCoordinator$attachFilterOnDreamingStateChange$1
            r0.<init>(r4, r5)
        L1b:
            java.lang.Object r5 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L33
            if (r1 == r2) goto L2e
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L2e:
            kotlin.KotlinNothingValueException r4 = androidx.compose.foundation.text.input.internal.AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0.m(r5)
            throw r4
        L33:
            kotlin.ResultKt.throwOnFailure(r5)
            com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl r5 = r4.keyguardRepository
            kotlinx.coroutines.flow.ReadonlyStateFlow r5 = r5.isActiveDreamLockscreenHosted
            com.android.systemui.statusbar.notification.collection.coordinator.DreamCoordinator$attachFilterOnDreamingStateChange$2 r1 = new com.android.systemui.statusbar.notification.collection.coordinator.DreamCoordinator$attachFilterOnDreamingStateChange$2
            r1.<init>()
            r0.label = r2
            r5.collect(r1, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.collection.coordinator.DreamCoordinator.access$attachFilterOnDreamingStateChange(com.android.systemui.statusbar.notification.collection.coordinator.DreamCoordinator, kotlin.coroutines.jvm.internal.ContinuationImpl):void");
    }

    @Override // com.android.systemui.statusbar.notification.collection.coordinator.Coordinator
    public final void attach(NotifPipeline notifPipeline) {
        notifPipeline.addPreGroupFilter(this.filter);
        DreamCoordinator$statusBarStateListener$1 dreamCoordinator$statusBarStateListener$1 = this.statusBarStateListener;
        StatusBarStateControllerImpl statusBarStateControllerImpl = (StatusBarStateControllerImpl) this.statusBarStateController;
        statusBarStateControllerImpl.addCallback((StatusBarStateController.StateListener) dreamCoordinator$statusBarStateListener$1);
        BuildersKt.launch$default(this.scope, null, null, new DreamCoordinator$attach$1(this, null), 3);
        recordStatusBarState(statusBarStateControllerImpl.mState);
    }

    public final void recordStatusBarState(int i) {
        boolean z = false;
        this.isOnKeyguard = i == 1;
        DreamCoordinator$filter$1 dreamCoordinator$filter$1 = this.filter;
        boolean z2 = dreamCoordinator$filter$1.isFiltering;
        DreamCoordinator dreamCoordinator = dreamCoordinator$filter$1.this$0;
        if (dreamCoordinator.isLockscreenHostedDream && dreamCoordinator.isOnKeyguard) {
            z = true;
        }
        dreamCoordinator$filter$1.isFiltering = z;
        if (z2 != z) {
            dreamCoordinator$filter$1.invalidateList("recordStatusBarState: " + StatusBarState.toString(i));
        }
    }
}
