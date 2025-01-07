package androidx.appcompat.widget;

import androidx.core.view.ViewPropertyAnimatorListener;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AbsActionBarView$VisibilityAnimListener implements ViewPropertyAnimatorListener {
    public boolean mCanceled = false;
    public int mFinalVisibility;
    public final /* synthetic */ ActionBarContextView this$0;

    public AbsActionBarView$VisibilityAnimListener(ActionBarContextView actionBarContextView) {
        this.this$0 = actionBarContextView;
    }

    @Override // androidx.core.view.ViewPropertyAnimatorListener
    public final void onAnimationCancel() {
        this.mCanceled = true;
    }

    @Override // androidx.core.view.ViewPropertyAnimatorListener
    public final void onAnimationEnd() {
        if (this.mCanceled) {
            return;
        }
        ActionBarContextView actionBarContextView = this.this$0;
        actionBarContextView.mVisibilityAnim = null;
        super/*android.view.ViewGroup*/.setVisibility(this.mFinalVisibility);
    }

    @Override // androidx.core.view.ViewPropertyAnimatorListener
    public final void onAnimationStart() {
        super/*android.view.ViewGroup*/.setVisibility(0);
        this.mCanceled = false;
    }
}
