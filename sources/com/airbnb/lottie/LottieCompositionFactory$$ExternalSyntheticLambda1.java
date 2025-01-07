package com.airbnb.lottie;

import java.io.InputStream;
import java.util.concurrent.Callable;
import java.util.zip.ZipInputStream;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class LottieCompositionFactory$$ExternalSyntheticLambda1 implements Callable {
    public final /* synthetic */ int $r8$classId = 0;
    public final /* synthetic */ Object f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ LottieCompositionFactory$$ExternalSyntheticLambda1(InputStream inputStream, String str) {
        this.f$0 = inputStream;
        this.f$1 = str;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        switch (this.$r8$classId) {
            case 0:
                return LottieCompositionFactory.fromJsonInputStreamSync((InputStream) this.f$0, this.f$1);
            default:
                return LottieCompositionFactory.fromZipStreamSync(null, (ZipInputStream) this.f$0, this.f$1);
        }
    }

    public /* synthetic */ LottieCompositionFactory$$ExternalSyntheticLambda1(ZipInputStream zipInputStream, String str) {
        this.f$0 = zipInputStream;
        this.f$1 = str;
    }
}
