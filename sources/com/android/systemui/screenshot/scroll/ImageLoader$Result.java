package com.android.systemui.screenshot.scroll;

import android.graphics.Bitmap;
import java.io.File;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ImageLoader$Result {
    public Bitmap mBitmap;
    public File mFilename;

    public final String toString() {
        return "Result{uri=null, fileName=" + this.mFilename + ", bitmap=" + this.mBitmap + '}';
    }
}
