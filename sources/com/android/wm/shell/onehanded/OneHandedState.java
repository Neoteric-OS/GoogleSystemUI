package com.android.wm.shell.onehanded;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class OneHandedState {
    public static int sCurrentState;
    public List mStateChangeListeners;

    public final void setState(final int i) {
        sCurrentState = i;
        if (this.mStateChangeListeners.isEmpty()) {
            return;
        }
        ((ArrayList) this.mStateChangeListeners).forEach(new Consumer() { // from class: com.android.wm.shell.onehanded.OneHandedState$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                int i2 = i;
                OneHandedTutorialHandler oneHandedTutorialHandler = (OneHandedTutorialHandler) obj;
                oneHandedTutorialHandler.mCurrentState = i2;
                oneHandedTutorialHandler.mBackgroundWindowManager.mCurrentState = i2;
                if (i2 != 0) {
                    if (i2 == 1) {
                        oneHandedTutorialHandler.createViewAndAttachToWindow(oneHandedTutorialHandler.mContext);
                        oneHandedTutorialHandler.updateThemeColor();
                        oneHandedTutorialHandler.setupAlphaTransition(true);
                        return;
                    } else if (i2 == 2) {
                        oneHandedTutorialHandler.checkTransitionEnd();
                        oneHandedTutorialHandler.setupAlphaTransition(false);
                        return;
                    } else if (i2 != 3) {
                        return;
                    }
                }
                oneHandedTutorialHandler.checkTransitionEnd();
                oneHandedTutorialHandler.removeTutorialFromWindowManager();
            }
        });
    }
}
