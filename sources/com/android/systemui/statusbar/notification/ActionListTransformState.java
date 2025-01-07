package com.android.systemui.statusbar.notification;

import android.util.Pools;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ActionListTransformState extends TransformState {
    public static final Pools.SimplePool sInstancePool = new Pools.SimplePool(40);

    @Override // com.android.systemui.statusbar.notification.TransformState
    public final void recycle() {
        super.recycle();
        sInstancePool.release(this);
    }

    @Override // com.android.systemui.statusbar.notification.TransformState
    public final void resetTransformedView() {
        float translationY = this.mTransformedView.getTranslationY();
        super.resetTransformedView();
        this.mTransformedView.setTranslationY(translationY);
    }

    @Override // com.android.systemui.statusbar.notification.TransformState
    public final boolean sameAs(TransformState transformState) {
        return transformState instanceof ActionListTransformState;
    }

    @Override // com.android.systemui.statusbar.notification.TransformState
    public final void transformViewFullyFrom(TransformState transformState, float f) {
    }

    @Override // com.android.systemui.statusbar.notification.TransformState
    public final void transformViewFullyTo(TransformState transformState, float f) {
    }
}
