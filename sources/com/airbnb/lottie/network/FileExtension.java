package com.airbnb.lottie.network;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public enum FileExtension {
    JSON(0),
    ZIP(1),
    GZIP(2);

    public final String extension;

    FileExtension(int i) {
        this.extension = r2;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return this.extension;
    }
}
