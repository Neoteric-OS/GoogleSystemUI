package com.android.systemui.keyguard;

import android.util.Log;
import com.android.systemui.CoreStartable;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.utils.GlobalWindowManager;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ResourceTrimmer implements CoreStartable {
    public final CoroutineScope applicationScope;
    public final CoroutineDispatcher bgDispatcher;
    public final GlobalWindowManager globalWindowManager;
    public final KeyguardTransitionInteractor keyguardTransitionInteractor;

    public ResourceTrimmer(KeyguardTransitionInteractor keyguardTransitionInteractor, GlobalWindowManager globalWindowManager, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher) {
        this.keyguardTransitionInteractor = keyguardTransitionInteractor;
        this.globalWindowManager = globalWindowManager;
        this.applicationScope = coroutineScope;
        this.bgDispatcher = coroutineDispatcher;
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        Log.d("ResourceTrimmer", "Resource trimmer registered.");
        BuildersKt.launch$default(this.applicationScope, this.bgDispatcher, null, new ResourceTrimmer$start$1(this, null), 2);
    }
}
