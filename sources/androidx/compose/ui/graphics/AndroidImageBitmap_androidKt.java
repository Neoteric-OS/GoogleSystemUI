package androidx.compose.ui.graphics;

import android.graphics.Bitmap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class AndroidImageBitmap_androidKt {
    public static final Bitmap asAndroidBitmap(ImageBitmap imageBitmap) {
        if (imageBitmap instanceof AndroidImageBitmap) {
            return ((AndroidImageBitmap) imageBitmap).bitmap;
        }
        throw new UnsupportedOperationException("Unable to obtain android.graphics.Bitmap");
    }

    /* renamed from: toBitmapConfig-1JJdX4A, reason: not valid java name */
    public static final Bitmap.Config m342toBitmapConfig1JJdX4A(int i) {
        return ImageBitmapConfig.m377equalsimpl0(i, 0) ? Bitmap.Config.ARGB_8888 : ImageBitmapConfig.m377equalsimpl0(i, 1) ? Bitmap.Config.ALPHA_8 : ImageBitmapConfig.m377equalsimpl0(i, 2) ? Bitmap.Config.RGB_565 : ImageBitmapConfig.m377equalsimpl0(i, 3) ? Bitmap.Config.RGBA_F16 : ImageBitmapConfig.m377equalsimpl0(i, 4) ? Bitmap.Config.HARDWARE : Bitmap.Config.ARGB_8888;
    }
}
