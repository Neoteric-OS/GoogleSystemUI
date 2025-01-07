package com.android.systemui.bouncer.domain.interactor;

import com.android.systemui.CoreStartable;
import com.android.systemui.bouncer.data.repository.BouncerMessageRepositoryImpl;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BouncerMessageAuditLogger implements CoreStartable {
    public final BouncerMessageRepositoryImpl repository;
    public final CoroutineScope scope;

    public BouncerMessageAuditLogger(CoroutineScope coroutineScope, BouncerMessageRepositoryImpl bouncerMessageRepositoryImpl) {
        this.scope = coroutineScope;
        this.repository = bouncerMessageRepositoryImpl;
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        BuildersKt.launch$default(this.scope, null, null, new BouncerMessageAuditLogger$start$1(this, null), 3);
    }
}
