package com.android.systemui.shade;

import android.view.MotionEvent;
import com.android.systemui.CoreStartable;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl;
import com.android.systemui.statusbar.phone.StatusBarRemoteInputCallback$$ExternalSyntheticLambda0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface ShadeController extends CoreStartable {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface ShadeVisibilityListener {
    }

    default void animateCollapseShade(int i) {
        animateCollapseShade(i, false, false, 1.0f);
    }

    void animateCollapseShade(int i, boolean z, boolean z2, float f);

    void cancelExpansionAndCollapseShade();

    void closeShadeIfOpen();

    void collapseOnMainThread();

    void collapseShade();

    void collapseShade(boolean z);

    void collapseShadeForActivityStart();

    void collapseWithDuration(int i);

    void instantCollapseShade();

    void instantExpandShade();

    boolean isExpandedVisible();

    boolean isExpandingOrCollapsing();

    boolean isShadeEnabled();

    boolean isShadeFullyOpen();

    void makeExpandedInvisible();

    void makeExpandedVisible(boolean z);

    void onStatusBarTouch(MotionEvent motionEvent);

    void performHapticFeedback();

    void postAnimateCollapseShade();

    void postAnimateExpandQs();

    void postAnimateForceCollapseShade();

    void postOnShadeExpanded(StatusBarRemoteInputCallback$$ExternalSyntheticLambda0 statusBarRemoteInputCallback$$ExternalSyntheticLambda0);

    void setVisibilityListener(CentralSurfacesImpl.AnonymousClass4 anonymousClass4);
}
