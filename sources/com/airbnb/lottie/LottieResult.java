package com.airbnb.lottie;

import java.util.Arrays;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LottieResult {
    public final Throwable exception;
    public final LottieComposition value;

    public LottieResult(LottieComposition lottieComposition) {
        this.value = lottieComposition;
        this.exception = null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LottieResult)) {
            return false;
        }
        LottieResult lottieResult = (LottieResult) obj;
        LottieComposition lottieComposition = this.value;
        if (lottieComposition != null && lottieComposition.equals(lottieResult.value)) {
            return true;
        }
        Throwable th = this.exception;
        if (th == null || lottieResult.exception == null) {
            return false;
        }
        return th.toString().equals(this.exception.toString());
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.value, this.exception});
    }

    public LottieResult(Throwable th) {
        this.exception = th;
        this.value = null;
    }
}
