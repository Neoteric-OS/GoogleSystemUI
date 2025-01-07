package com.airbnb.lottie.model;

import com.airbnb.lottie.value.LottieValueCallback;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface KeyPathElement {
    void addValueCallback(LottieValueCallback lottieValueCallback, Object obj);

    void resolveKeyPath(KeyPath keyPath, int i, List list, KeyPath keyPath2);
}
