package com.airbnb.lottie;

import com.airbnb.lottie.utils.Utils;
import java.io.InputStream;
import java.util.zip.ZipInputStream;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class LottieCompositionFactory$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ int $r8$classId = 0;
    public final /* synthetic */ Object f$0;

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                Utils.closeQuietly((InputStream) obj);
                break;
            default:
                Utils.closeQuietly((ZipInputStream) obj);
                break;
        }
    }
}
