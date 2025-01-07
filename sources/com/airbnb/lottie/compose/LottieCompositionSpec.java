package com.airbnb.lottie.compose;

import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface LottieCompositionSpec {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class RawRes implements LottieCompositionSpec {
        public final int resId;

        public final boolean equals(Object obj) {
            if (obj instanceof RawRes) {
                return this.resId == ((RawRes) obj).resId;
            }
            return false;
        }

        public final int hashCode() {
            return Integer.hashCode(this.resId);
        }

        public final String toString() {
            return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(new StringBuilder("RawRes(resId="), this.resId, ")");
        }
    }
}
