package com.android.systemui.unfold.util;

import android.content.Context;
import com.android.systemui.unfold.UnfoldTransitionProgressProvider;
import com.android.systemui.unfold.updates.RotationChangeProvider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NaturalRotationUnfoldProgressProvider implements UnfoldTransitionProgressProvider {
    public final Context context;
    public boolean isNaturalRotation;
    public final RotationChangeProvider rotationChangeProvider;
    public final NaturalRotationUnfoldProgressProvider$rotationListener$1 rotationListener = new NaturalRotationUnfoldProgressProvider$rotationListener$1(this);
    public final ScopedUnfoldTransitionProgressProvider scopedUnfoldTransitionProgressProvider;

    public NaturalRotationUnfoldProgressProvider(Context context, RotationChangeProvider rotationChangeProvider, UnfoldTransitionProgressProvider unfoldTransitionProgressProvider) {
        this.context = context;
        this.rotationChangeProvider = rotationChangeProvider;
        this.scopedUnfoldTransitionProgressProvider = new ScopedUnfoldTransitionProgressProvider(unfoldTransitionProgressProvider);
    }

    @Override // com.android.systemui.unfold.util.CallbackController
    public final void addCallback(Object obj) {
        this.scopedUnfoldTransitionProgressProvider.listeners.add((UnfoldTransitionProgressProvider.TransitionProgressListener) obj);
    }

    @Override // com.android.systemui.unfold.util.CallbackController
    public final void removeCallback(Object obj) {
        this.scopedUnfoldTransitionProgressProvider.listeners.remove((UnfoldTransitionProgressProvider.TransitionProgressListener) obj);
    }
}
