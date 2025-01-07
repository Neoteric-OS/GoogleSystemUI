package androidx.asynclayoutinflater.view;

import androidx.asynclayoutinflater.view.AsyncLayoutInflater;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class AsyncLayoutInflater$1$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;
    public final /* synthetic */ AsyncLayoutInflater.InflateRequest f$1;

    public /* synthetic */ AsyncLayoutInflater$1$$ExternalSyntheticLambda0(Object obj, AsyncLayoutInflater.InflateRequest inflateRequest, int i) {
        this.$r8$classId = i;
        this.f$0 = obj;
        this.f$1 = inflateRequest;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                AsyncLayoutInflater.triggerCallbacks(this.f$1, ((AsyncLayoutInflater.AnonymousClass1) this.f$0).this$0.mInflateThread);
                break;
            default:
                AsyncLayoutInflater.InflateThread inflateThread = (AsyncLayoutInflater.InflateThread) this.f$0;
                AsyncLayoutInflater.InflateRequest inflateRequest = this.f$1;
                inflateThread.getClass();
                AsyncLayoutInflater.triggerCallbacks(inflateRequest, inflateThread);
                break;
        }
    }
}
