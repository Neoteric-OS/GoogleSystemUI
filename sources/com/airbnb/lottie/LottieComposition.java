package com.airbnb.lottie;

import android.graphics.Bitmap;
import android.graphics.Rect;
import androidx.collection.LongSparseArray;
import androidx.collection.SparseArrayCompat;
import com.airbnb.lottie.model.Marker;
import com.airbnb.lottie.model.layer.Layer;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.Utils;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LottieComposition {
    public Rect bounds;
    public SparseArrayCompat characters;
    public float endFrame;
    public Map fonts;
    public float frameRate;
    public Map images;
    public float imagesDpScale;
    public LongSparseArray layerMap;
    public List layers;
    public List markers;
    public Map precomps;
    public float startFrame;
    public final PerformanceTracker performanceTracker = new PerformanceTracker();
    public final HashSet warnings = new HashSet();
    public int maskAndMatteCount = 0;

    public final void addWarning(String str) {
        Logger.warning(str);
        this.warnings.add(str);
    }

    public final float getDuration() {
        return (long) (((this.endFrame - this.startFrame) / this.frameRate) * 1000.0f);
    }

    public final Map getImages() {
        float dpScale = Utils.dpScale();
        if (dpScale != this.imagesDpScale) {
            for (Map.Entry entry : this.images.entrySet()) {
                Map map = this.images;
                String str = (String) entry.getKey();
                LottieImageAsset lottieImageAsset = (LottieImageAsset) entry.getValue();
                float f = this.imagesDpScale / dpScale;
                int i = (int) (lottieImageAsset.width * f);
                int i2 = (int) (lottieImageAsset.height * f);
                LottieImageAsset lottieImageAsset2 = new LottieImageAsset(i, i2, lottieImageAsset.id, lottieImageAsset.fileName, lottieImageAsset.dirName);
                Bitmap bitmap = lottieImageAsset.bitmap;
                if (bitmap != null) {
                    lottieImageAsset2.bitmap = Bitmap.createScaledBitmap(bitmap, i, i2, true);
                }
                map.put(str, lottieImageAsset2);
            }
        }
        this.imagesDpScale = dpScale;
        return this.images;
    }

    public final Marker getMarker(String str) {
        int size = this.markers.size();
        for (int i = 0; i < size; i++) {
            Marker marker = (Marker) this.markers.get(i);
            String str2 = marker.name;
            if (str2.equalsIgnoreCase(str) || (str2.endsWith("\r") && str2.substring(0, str2.length() - 1).equalsIgnoreCase(str))) {
                return marker;
            }
        }
        return null;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("LottieComposition:\n");
        Iterator it = this.layers.iterator();
        while (it.hasNext()) {
            sb.append(((Layer) it.next()).toString("\t"));
        }
        return sb.toString();
    }
}
