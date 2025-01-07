package com.android.systemui.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.os.Trace;
import android.util.Log;
import android.util.Size;
import com.android.app.tracing.TraceUtilsKt;
import java.io.IOException;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ImageLoader {
    public static final Companion Companion = null;
    public final CoroutineDispatcher backgroundDispatcher;
    public final Context defaultContext;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Source {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Uri implements Source {
        public final android.net.Uri uri;

        public Uri(android.net.Uri uri) {
            this.uri = uri;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Uri) && Intrinsics.areEqual(this.uri, ((Uri) obj).uri);
        }

        public final int hashCode() {
            return this.uri.hashCode();
        }

        public final String toString() {
            return "Uri(uri=" + this.uri + ")";
        }
    }

    public ImageLoader(Context context, CoroutineDispatcher coroutineDispatcher) {
        this.defaultContext = context;
        this.backgroundDispatcher = coroutineDispatcher;
    }

    public static Bitmap loadBitmapSync(ImageDecoder.Source source, final int i, final int i2, final int i3) {
        boolean isEnabled = Trace.isEnabled();
        if (isEnabled) {
            TraceUtilsKt.beginSlice("ImageLoader#loadBitmap");
        }
        try {
            try {
                Bitmap decodeBitmap = ImageDecoder.decodeBitmap(source, new ImageDecoder.OnHeaderDecodedListener() { // from class: com.android.systemui.graphics.ImageLoader$loadBitmapSync$1$1
                    @Override // android.graphics.ImageDecoder.OnHeaderDecodedListener
                    public final void onHeaderDecoded(ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source2) {
                        Size size = imageInfo.getSize();
                        int i4 = i;
                        int i5 = i2;
                        if ((i4 != 0 || i5 != 0) && (size.getWidth() > i4 || size.getHeight() > i5)) {
                            float min = Math.min(i4 <= 0 ? 1.0f : i4 / size.getWidth(), i5 <= 0 ? 1.0f : i5 / size.getHeight());
                            if (min < 1.0f) {
                                int width = (int) (size.getWidth() * min);
                                int height = (int) (size.getHeight() * min);
                                if (Log.isLoggable("ImageLoader", 3)) {
                                    Log.d("ImageLoader", "Configured image size to " + width + " x " + height);
                                }
                                imageDecoder.setTargetSize(width, height);
                            }
                        }
                        imageDecoder.setAllocator(i3);
                    }
                });
                if (isEnabled) {
                    TraceUtilsKt.endSlice();
                }
                return decodeBitmap;
            } catch (IOException e) {
                Log.w("ImageLoader", "Failed to load source " + source, e);
                if (!isEnabled) {
                    return null;
                }
                TraceUtilsKt.endSlice();
                return null;
            }
        } catch (Throwable th) {
            if (isEnabled) {
                TraceUtilsKt.endSlice();
            }
            throw th;
        }
    }

    public final Object loadBitmap(Uri uri, int i, int i2, ContinuationImpl continuationImpl) {
        return BuildersKt.withContext(this.backgroundDispatcher, new ImageLoader$loadBitmap$2(this, uri, i, i2, 1, null), continuationImpl);
    }
}
