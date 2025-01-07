package com.android.app.viewcapture;

import com.android.app.viewcapture.ViewCapture;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class ViewCapture$$ExternalSyntheticLambda7 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;
    public final /* synthetic */ ViewCapture.ViewPropertyRef f$1;
    public final /* synthetic */ ViewCapture.ViewPropertyRef f$2;

    public /* synthetic */ ViewCapture$$ExternalSyntheticLambda7(Object obj, ViewCapture.ViewPropertyRef viewPropertyRef, ViewCapture.ViewPropertyRef viewPropertyRef2, int i) {
        this.$r8$classId = i;
        this.f$0 = obj;
        this.f$1 = viewPropertyRef;
        this.f$2 = viewPropertyRef2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                ViewCapture viewCapture = (ViewCapture) this.f$0;
                ViewCapture.ViewPropertyRef viewPropertyRef = this.f$1;
                this.f$2.next = viewCapture.mPool;
                viewCapture.mPool = viewPropertyRef;
                break;
            default:
                ViewCapture.WindowListener windowListener = (ViewCapture.WindowListener) this.f$0;
                ViewCapture.ViewPropertyRef viewPropertyRef2 = this.f$1;
                ViewCapture.ViewPropertyRef viewPropertyRef3 = this.f$2;
                ViewCapture viewCapture2 = ViewCapture.this;
                viewPropertyRef3.next = viewCapture2.mPool;
                viewCapture2.mPool = viewPropertyRef2;
                break;
        }
    }
}
