package com.google.android.systemui.dagger;

import com.android.systemui.CoreStartable;
import java.util.Set;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.StandaloneCoroutine;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CoroutineScopeCoreStartable implements CoreStartable {
    public final Set coroutineInitializers;
    public StandaloneCoroutine job;
    public final ContextScope scope = CoroutineScopeKt.MainScope();

    public CoroutineScopeCoreStartable(Set set) {
        this.coroutineInitializers = set;
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        if (this.job != null) {
            throw new IllegalStateException("Already started");
        }
        this.job = BuildersKt.launch$default(this.scope, null, null, new CoroutineScopeCoreStartable$start$1(this, null), 3);
    }
}
