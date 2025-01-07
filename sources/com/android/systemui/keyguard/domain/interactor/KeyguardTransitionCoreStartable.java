package com.android.systemui.keyguard.domain.interactor;

import android.util.Log;
import com.android.systemui.CoreStartable;
import java.util.Set;
import kotlin.NoWhenBranchMatchedException;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardTransitionCoreStartable implements CoreStartable {
    public final KeyguardTransitionAuditLogger auditLogger;
    public final KeyguardTransitionBootInteractor bootInteractor;
    public final Set interactors;
    public final KeyguardStateCallbackInteractor keyguardStateCallbackInteractor;
    public final StatusBarDisableFlagsInteractor statusBarDisableFlagsInteractor;

    public KeyguardTransitionCoreStartable(Set set, KeyguardTransitionAuditLogger keyguardTransitionAuditLogger, KeyguardTransitionBootInteractor keyguardTransitionBootInteractor, StatusBarDisableFlagsInteractor statusBarDisableFlagsInteractor, KeyguardStateCallbackInteractor keyguardStateCallbackInteractor) {
        this.interactors = set;
        this.auditLogger = keyguardTransitionAuditLogger;
        this.bootInteractor = keyguardTransitionBootInteractor;
        this.statusBarDisableFlagsInteractor = statusBarDisableFlagsInteractor;
        this.keyguardStateCallbackInteractor = keyguardStateCallbackInteractor;
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        for (TransitionInteractor transitionInteractor : this.interactors) {
            if (transitionInteractor instanceof FromPrimaryBouncerTransitionInteractor) {
                Log.d("KeyguardTransitionCoreStartable", "Started " + transitionInteractor);
            } else if (transitionInteractor instanceof FromAodTransitionInteractor) {
                Log.d("KeyguardTransitionCoreStartable", "Started " + transitionInteractor);
            } else if (transitionInteractor instanceof FromGoneTransitionInteractor) {
                Log.d("KeyguardTransitionCoreStartable", "Started " + transitionInteractor);
            } else if (transitionInteractor instanceof FromLockscreenTransitionInteractor) {
                Log.d("KeyguardTransitionCoreStartable", "Started " + transitionInteractor);
            } else if (transitionInteractor instanceof FromDreamingTransitionInteractor) {
                Log.d("KeyguardTransitionCoreStartable", "Started " + transitionInteractor);
            } else if (transitionInteractor instanceof FromGlanceableHubTransitionInteractor) {
                Log.d("KeyguardTransitionCoreStartable", "Started " + transitionInteractor);
            } else if (transitionInteractor instanceof FromOccludedTransitionInteractor) {
                Log.d("KeyguardTransitionCoreStartable", "Started " + transitionInteractor);
            } else if (transitionInteractor instanceof FromDozingTransitionInteractor) {
                Log.d("KeyguardTransitionCoreStartable", "Started " + transitionInteractor);
            } else if (transitionInteractor instanceof FromAlternateBouncerTransitionInteractor) {
                Log.d("KeyguardTransitionCoreStartable", "Started " + transitionInteractor);
            } else {
                if (!(transitionInteractor instanceof FromDreamingLockscreenHostedTransitionInteractor)) {
                    throw new NoWhenBranchMatchedException();
                }
                Log.d("KeyguardTransitionCoreStartable", "Started " + transitionInteractor);
            }
            transitionInteractor.start();
        }
        KeyguardTransitionAuditLogger keyguardTransitionAuditLogger = this.auditLogger;
        keyguardTransitionAuditLogger.getClass();
        KeyguardTransitionAuditLogger$start$1 keyguardTransitionAuditLogger$start$1 = new KeyguardTransitionAuditLogger$start$1(keyguardTransitionAuditLogger, null);
        CoroutineScope coroutineScope = keyguardTransitionAuditLogger.scope;
        BuildersKt.launch$default(coroutineScope, null, null, keyguardTransitionAuditLogger$start$1, 3);
        BuildersKt.launch$default(coroutineScope, null, null, new KeyguardTransitionAuditLogger$start$2(keyguardTransitionAuditLogger, null), 3);
        BuildersKt.launch$default(coroutineScope, null, null, new KeyguardTransitionAuditLogger$start$3(keyguardTransitionAuditLogger, null), 3);
        BuildersKt.launch$default(coroutineScope, null, null, new KeyguardTransitionAuditLogger$start$4(keyguardTransitionAuditLogger, null), 3);
        BuildersKt.launch$default(coroutineScope, null, null, new KeyguardTransitionAuditLogger$start$5(keyguardTransitionAuditLogger, null), 3);
        BuildersKt.launch$default(coroutineScope, null, null, new KeyguardTransitionAuditLogger$start$6(keyguardTransitionAuditLogger, null), 3);
        BuildersKt.launch$default(coroutineScope, null, null, new KeyguardTransitionAuditLogger$start$7(keyguardTransitionAuditLogger, null), 3);
        BuildersKt.launch$default(coroutineScope, null, null, new KeyguardTransitionAuditLogger$start$8(keyguardTransitionAuditLogger, null), 3);
        BuildersKt.launch$default(coroutineScope, null, null, new KeyguardTransitionAuditLogger$start$9(keyguardTransitionAuditLogger, null), 3);
        BuildersKt.launch$default(coroutineScope, null, null, new KeyguardTransitionAuditLogger$start$10(keyguardTransitionAuditLogger, null), 3);
        BuildersKt.launch$default(coroutineScope, null, null, new KeyguardTransitionAuditLogger$start$11(keyguardTransitionAuditLogger, null), 3);
        BuildersKt.launch$default(coroutineScope, null, null, new KeyguardTransitionAuditLogger$start$12(keyguardTransitionAuditLogger, null), 3);
        BuildersKt.launch$default(coroutineScope, null, null, new KeyguardTransitionAuditLogger$start$13(keyguardTransitionAuditLogger, null), 3);
        BuildersKt.launch$default(coroutineScope, null, null, new KeyguardTransitionAuditLogger$start$14(keyguardTransitionAuditLogger, null), 3);
        BuildersKt.launch$default(coroutineScope, null, null, new KeyguardTransitionAuditLogger$start$15(keyguardTransitionAuditLogger, null), 3);
        BuildersKt.launch$default(coroutineScope, null, null, new KeyguardTransitionAuditLogger$start$16(keyguardTransitionAuditLogger, null), 3);
        BuildersKt.launch$default(coroutineScope, null, null, new KeyguardTransitionAuditLogger$start$17(keyguardTransitionAuditLogger, null), 3);
        BuildersKt.launch$default(coroutineScope, null, null, new KeyguardTransitionAuditLogger$start$18(keyguardTransitionAuditLogger, null), 3);
        BuildersKt.launch$default(coroutineScope, null, null, new KeyguardTransitionAuditLogger$start$19(keyguardTransitionAuditLogger, null), 3);
        BuildersKt.launch$default(coroutineScope, null, null, new KeyguardTransitionAuditLogger$start$20(keyguardTransitionAuditLogger, null), 3);
        this.bootInteractor.start();
        this.statusBarDisableFlagsInteractor.getClass();
        this.keyguardStateCallbackInteractor.getClass();
    }
}
