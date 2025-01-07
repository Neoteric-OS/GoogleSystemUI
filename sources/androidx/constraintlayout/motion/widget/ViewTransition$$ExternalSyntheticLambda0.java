package androidx.constraintlayout.motion.widget;

import android.view.View;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class ViewTransition$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ ViewTransition f$0;
    public final /* synthetic */ View[] f$1;

    public /* synthetic */ ViewTransition$$ExternalSyntheticLambda0(ViewTransition viewTransition, View[] viewArr) {
        this.f$0 = viewTransition;
        this.f$1 = viewArr;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ViewTransition viewTransition = this.f$0;
        View[] viewArr = this.f$1;
        if (viewTransition.mSetsTag != -1) {
            for (View view : viewArr) {
                view.setTag(viewTransition.mSetsTag, Long.valueOf(System.nanoTime()));
            }
        }
        if (viewTransition.mClearsTag != -1) {
            for (View view2 : viewArr) {
                view2.setTag(viewTransition.mClearsTag, null);
            }
        }
    }
}
