package com.android.wm.shell.pip;

import com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler$$ExternalSyntheticLambda1;
import com.android.systemui.wmshell.WMShell;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface Pip {
    void addPipExclusionBoundsChangeListener(Consumer consumer);

    void onSystemUiStateChanged(long j, boolean z);

    void removePipExclusionBoundsChangeListener(Consumer consumer);

    void setOnIsInPipStateChangedListener(EdgeBackGestureHandler$$ExternalSyntheticLambda1 edgeBackGestureHandler$$ExternalSyntheticLambda1);

    void showPictureInPictureMenu();

    default void registerPipTransitionCallback(WMShell.AnonymousClass6 anonymousClass6, Executor executor) {
    }
}
