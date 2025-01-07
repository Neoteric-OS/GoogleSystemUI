package com.google.android.systemui.columbus.legacy.gates;

import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.policy.DeviceProvisionedControllerImpl;
import com.google.android.systemui.columbus.legacy.actions.Action;
import dagger.Lazy;
import java.util.Iterator;
import java.util.Set;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SetupWizard extends Gate {
    public final CoroutineDispatcher bgDispatcher;
    public boolean exceptionActive;
    public final Set exceptions;
    public final Lazy provisionedController;
    public boolean setupComplete;
    public final SetupWizard$provisionedListener$1 provisionedListener = new DeviceProvisionedController.DeviceProvisionedListener() { // from class: com.google.android.systemui.columbus.legacy.gates.SetupWizard$provisionedListener$1
        @Override // com.android.systemui.statusbar.policy.DeviceProvisionedController.DeviceProvisionedListener
        public final void onDeviceProvisionedChanged() {
            SetupWizard setupWizard = SetupWizard.this;
            BuildersKt.launch$default(setupWizard.coroutineScope, null, null, new SetupWizard$provisionedListener$1$updateSetupComplete$1(setupWizard, null), 3);
        }

        @Override // com.android.systemui.statusbar.policy.DeviceProvisionedController.DeviceProvisionedListener
        public final void onUserSetupChanged() {
            SetupWizard setupWizard = SetupWizard.this;
            BuildersKt.launch$default(setupWizard.coroutineScope, null, null, new SetupWizard$provisionedListener$1$updateSetupComplete$1(setupWizard, null), 3);
        }
    };
    public final SetupWizard$actionListener$1 actionListener = new Action.Listener() { // from class: com.google.android.systemui.columbus.legacy.gates.SetupWizard$actionListener$1
        @Override // com.google.android.systemui.columbus.legacy.actions.Action.Listener
        public final void onActionAvailabilityChanged(Action action) {
            SetupWizard setupWizard = SetupWizard.this;
            BuildersKt.launch$default(setupWizard.coroutineScope, null, null, new SetupWizard$actionListener$1$onActionAvailabilityChanged$1(setupWizard, null), 3);
        }
    };

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.android.systemui.columbus.legacy.gates.SetupWizard$provisionedListener$1] */
    /* JADX WARN: Type inference failed for: r1v2, types: [com.google.android.systemui.columbus.legacy.gates.SetupWizard$actionListener$1] */
    public SetupWizard(Set set, Lazy lazy, CoroutineDispatcher coroutineDispatcher) {
        this.exceptions = set;
        this.provisionedController = lazy;
        this.bgDispatcher = coroutineDispatcher;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$isSetupComplete(com.google.android.systemui.columbus.legacy.gates.SetupWizard r8, kotlin.coroutines.jvm.internal.ContinuationImpl r9) {
        /*
            r8.getClass()
            boolean r0 = r9 instanceof com.google.android.systemui.columbus.legacy.gates.SetupWizard$isSetupComplete$1
            if (r0 == 0) goto L16
            r0 = r9
            com.google.android.systemui.columbus.legacy.gates.SetupWizard$isSetupComplete$1 r0 = (com.google.android.systemui.columbus.legacy.gates.SetupWizard$isSetupComplete$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L16
            int r1 = r1 - r2
            r0.label = r1
            goto L1b
        L16:
            com.google.android.systemui.columbus.legacy.gates.SetupWizard$isSetupComplete$1 r0 = new com.google.android.systemui.columbus.legacy.gates.SetupWizard$isSetupComplete$1
            r0.<init>(r8, r9)
        L1b:
            java.lang.Object r9 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 2
            r4 = 1
            r5 = 0
            if (r2 == 0) goto L3e
            if (r2 == r4) goto L36
            if (r2 != r3) goto L2e
            kotlin.ResultKt.throwOnFailure(r9)
            goto L75
        L2e:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L36:
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.Deferred r8 = (kotlinx.coroutines.Deferred) r8
            kotlin.ResultKt.throwOnFailure(r9)
            goto L62
        L3e:
            kotlin.ResultKt.throwOnFailure(r9)
            com.google.android.systemui.columbus.legacy.gates.SetupWizard$isSetupComplete$isDeviceProvisioned$1 r9 = new com.google.android.systemui.columbus.legacy.gates.SetupWizard$isSetupComplete$isDeviceProvisioned$1
            r9.<init>(r8, r5)
            kotlinx.coroutines.internal.ContextScope r2 = r8.coroutineScope
            kotlinx.coroutines.CoroutineDispatcher r6 = r8.bgDispatcher
            kotlinx.coroutines.DeferredCoroutine r9 = kotlinx.coroutines.BuildersKt.async$default(r2, r6, r9, r3)
            com.google.android.systemui.columbus.legacy.gates.SetupWizard$isSetupComplete$isCurrentUserSetup$1 r7 = new com.google.android.systemui.columbus.legacy.gates.SetupWizard$isSetupComplete$isCurrentUserSetup$1
            r7.<init>(r8, r5)
            kotlinx.coroutines.DeferredCoroutine r8 = kotlinx.coroutines.BuildersKt.async$default(r2, r6, r7, r3)
            r0.L$0 = r8
            r0.label = r4
            java.lang.Object r9 = r9.awaitInternal(r0)
            if (r9 != r1) goto L62
            goto L79
        L62:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            if (r9 == 0) goto L77
            r0.L$0 = r5
            r0.label = r3
            java.lang.Object r9 = r8.await(r0)
            if (r9 != r1) goto L75
            goto L79
        L75:
            r1 = r9
            goto L79
        L77:
            java.lang.Boolean r1 = java.lang.Boolean.FALSE
        L79:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.columbus.legacy.gates.SetupWizard.access$isSetupComplete(com.google.android.systemui.columbus.legacy.gates.SetupWizard, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    @Override // com.google.android.systemui.columbus.legacy.gates.Gate
    public final void onActivate() {
        ((DeviceProvisionedControllerImpl) ((DeviceProvisionedController) this.provisionedController.get())).addCallback(this.provisionedListener);
        SetupWizard$onActivate$1 setupWizard$onActivate$1 = new SetupWizard$onActivate$1(this, null);
        BuildersKt.launch$default(this.coroutineScope, this.mainDispatcher, null, setupWizard$onActivate$1, 2);
    }

    @Override // com.google.android.systemui.columbus.legacy.gates.Gate
    public final void onDeactivate() {
        Iterator it = this.exceptions.iterator();
        while (it.hasNext()) {
            ((Action) it.next()).listeners.remove(this.actionListener);
        }
        ((DeviceProvisionedControllerImpl) ((DeviceProvisionedController) this.provisionedController.get())).removeCallback(this.provisionedListener);
    }

    @Override // com.google.android.systemui.columbus.legacy.gates.Gate
    public final String toString() {
        return super.toString() + BuildersKt.runBlocking(this.mainDispatcher, new SetupWizard$toString$1(this, null));
    }
}
