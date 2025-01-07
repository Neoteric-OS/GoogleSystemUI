package com.android.wm.shell.pip.phone;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class PipInputConsumer$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ PipInputConsumer f$0;

    public /* synthetic */ PipInputConsumer$$ExternalSyntheticLambda1(PipInputConsumer pipInputConsumer, int i) {
        this.$r8$classId = i;
        this.f$0 = pipInputConsumer;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        PipInputConsumer pipInputConsumer = this.f$0;
        switch (i) {
            case 0:
                PipController$$ExternalSyntheticLambda10 pipController$$ExternalSyntheticLambda10 = pipInputConsumer.mRegistrationListener;
                if (pipController$$ExternalSyntheticLambda10 != null) {
                    pipController$$ExternalSyntheticLambda10.f$0.onRegistrationChanged(pipInputConsumer.mInputEventReceiver != null);
                    break;
                }
                break;
            default:
                PipController$$ExternalSyntheticLambda10 pipController$$ExternalSyntheticLambda102 = pipInputConsumer.mRegistrationListener;
                if (pipController$$ExternalSyntheticLambda102 != null) {
                    pipController$$ExternalSyntheticLambda102.f$0.onRegistrationChanged(false);
                    break;
                }
                break;
        }
    }
}
