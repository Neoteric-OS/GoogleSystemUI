package com.android.systemui.tuner;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class TunerFragment$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ TunerFragment f$0;

    @Override // java.lang.Runnable
    public final void run() {
        TunerFragment tunerFragment = this.f$0;
        if (tunerFragment.getActivity() != null) {
            tunerFragment.getActivity().finish();
        }
    }
}
