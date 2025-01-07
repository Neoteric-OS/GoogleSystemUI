package com.android.systemui.bouncer.domain.interactor;

import com.android.systemui.util.ListenerSet;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PrimaryBouncerCallbackInteractor {
    public final ListenerSet resetCallbacks = new ListenerSet();
    public final ArrayList expansionCallbacks = new ArrayList();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface PrimaryBouncerExpansionCallback {
        void onExpansionChanged(float f);

        void onFullyHidden();

        void onFullyShown();

        void onStartingToHide();

        void onStartingToShow();

        void onVisibilityChanged(boolean z);
    }
}
