package com.android.systemui.qs.tiles.dialog;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class InternetDialogDelegate$$ExternalSyntheticLambda17 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;
    public final /* synthetic */ Drawable f$1;

    public /* synthetic */ InternetDialogDelegate$$ExternalSyntheticLambda17(Object obj, Drawable drawable, int i) {
        this.$r8$classId = i;
        this.f$0 = obj;
        this.f$1 = drawable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                InternetDialogDelegate internetDialogDelegate = (InternetDialogDelegate) this.f$0;
                internetDialogDelegate.mSignalIcon.setImageDrawable(this.f$1);
                break;
            default:
                ((ImageView) this.f$0).setImageDrawable(this.f$1);
                break;
        }
    }
}
