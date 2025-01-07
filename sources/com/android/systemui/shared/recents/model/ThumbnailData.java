package com.android.systemui.shared.recents.model;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.support.v4.media.session.MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0;
import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ThumbnailData {
    public final int appearance;
    public final Rect insets;
    public final boolean isRealSnapshot;
    public final boolean isTranslucent;
    public final Rect letterboxInsets;
    public final int orientation;
    public final boolean reducedResolution;
    public final int rotation;
    public final float scale;
    public final long snapshotId;
    public final Bitmap thumbnail;
    public final int windowingMode;

    public ThumbnailData(Bitmap bitmap, int i, int i2, Rect rect, Rect rect2, boolean z, boolean z2, boolean z3, int i3, int i4, float f, long j) {
        this.thumbnail = bitmap;
        this.orientation = i;
        this.rotation = i2;
        this.insets = rect;
        this.letterboxInsets = rect2;
        this.reducedResolution = z;
        this.isRealSnapshot = z2;
        this.isTranslucent = z3;
        this.windowingMode = i3;
        this.appearance = i4;
        this.scale = f;
        this.snapshotId = j;
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x003a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final com.android.systemui.shared.recents.model.ThumbnailData fromSnapshot(android.window.TaskSnapshot r16) {
        /*
            r1 = 0
            android.hardware.HardwareBuffer r2 = r16.getHardwareBuffer()     // Catch: java.lang.IllegalArgumentException -> L20
            if (r2 == 0) goto L38
            android.graphics.ColorSpace r0 = r16.getColorSpace()     // Catch: java.lang.Throwable -> L17
            android.graphics.Bitmap r3 = android.graphics.Bitmap.wrapHardwareBuffer(r2, r0)     // Catch: java.lang.Throwable -> L17
            kotlin.jdk7.AutoCloseableKt.closeFinally(r2, r1)     // Catch: java.lang.IllegalArgumentException -> L14
            r1 = r3
            goto L38
        L14:
            r0 = move-exception
            r1 = r3
            goto L21
        L17:
            r0 = move-exception
            r3 = r0
            throw r3     // Catch: java.lang.Throwable -> L1a
        L1a:
            r0 = move-exception
            r4 = r0
            kotlin.jdk7.AutoCloseableKt.closeFinally(r2, r3)     // Catch: java.lang.IllegalArgumentException -> L20
            throw r4     // Catch: java.lang.IllegalArgumentException -> L20
        L20:
            r0 = move-exception
        L21:
            android.hardware.HardwareBuffer r2 = r16.getHardwareBuffer()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Unexpected snapshot without USAGE_GPU_SAMPLED_IMAGE: "
            r3.<init>(r4)
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            java.lang.String r3 = "ThumbnailData"
            android.util.Log.e(r3, r2, r0)
        L38:
            if (r1 != 0) goto L51
            android.graphics.Point r0 = r16.getTaskSize()
            int r0 = r0.x
            android.graphics.Point r1 = r16.getTaskSize()
            int r1 = r1.y
            android.graphics.Bitmap$Config r2 = android.graphics.Bitmap.Config.ARGB_8888
            android.graphics.Bitmap r1 = android.graphics.Bitmap.createBitmap(r0, r1, r2)
            r0 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r1.eraseColor(r0)
        L51:
            r3 = r1
            android.graphics.Rect r6 = new android.graphics.Rect
            android.graphics.Rect r0 = r16.getContentInsets()
            r6.<init>(r0)
            android.graphics.Rect r7 = new android.graphics.Rect
            android.graphics.Rect r0 = r16.getLetterboxInsets()
            r7.<init>(r0)
            int r4 = r16.getOrientation()
            int r5 = r16.getRotation()
            boolean r8 = r16.isLowResolution()
            int r0 = r3.getWidth()
            float r0 = (float) r0
            android.graphics.Point r1 = r16.getTaskSize()
            int r1 = r1.x
            float r1 = (float) r1
            float r13 = r0 / r1
            boolean r9 = r16.isRealSnapshot()
            boolean r10 = r16.isTranslucent()
            int r11 = r16.getWindowingMode()
            int r12 = r16.getAppearance()
            long r14 = r16.getId()
            com.android.systemui.shared.recents.model.ThumbnailData r0 = new com.android.systemui.shared.recents.model.ThumbnailData
            r2 = r0
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.shared.recents.model.ThumbnailData.fromSnapshot(android.window.TaskSnapshot):com.android.systemui.shared.recents.model.ThumbnailData");
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ThumbnailData)) {
            return false;
        }
        ThumbnailData thumbnailData = (ThumbnailData) obj;
        return Intrinsics.areEqual(this.thumbnail, thumbnailData.thumbnail) && this.orientation == thumbnailData.orientation && this.rotation == thumbnailData.rotation && Intrinsics.areEqual(this.insets, thumbnailData.insets) && Intrinsics.areEqual(this.letterboxInsets, thumbnailData.letterboxInsets) && this.reducedResolution == thumbnailData.reducedResolution && this.isRealSnapshot == thumbnailData.isRealSnapshot && this.isTranslucent == thumbnailData.isTranslucent && this.windowingMode == thumbnailData.windowingMode && this.appearance == thumbnailData.appearance && Float.compare(this.scale, thumbnailData.scale) == 0 && this.snapshotId == thumbnailData.snapshotId;
    }

    public final int hashCode() {
        Bitmap bitmap = this.thumbnail;
        return Long.hashCode(this.snapshotId) + FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.appearance, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.windowingMode, TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m((this.letterboxInsets.hashCode() + ((this.insets.hashCode() + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.rotation, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.orientation, (bitmap == null ? 0 : bitmap.hashCode()) * 31, 31), 31)) * 31)) * 31, 31, this.reducedResolution), 31, this.isRealSnapshot), 31, this.isTranslucent), 31), 31), this.scale, 31);
    }

    public final String toString() {
        Bitmap bitmap = this.thumbnail;
        Rect rect = this.insets;
        Rect rect2 = this.letterboxInsets;
        StringBuilder sb = new StringBuilder("ThumbnailData(thumbnail=");
        sb.append(bitmap);
        sb.append(", orientation=");
        sb.append(this.orientation);
        sb.append(", rotation=");
        sb.append(this.rotation);
        sb.append(", insets=");
        sb.append(rect);
        sb.append(", letterboxInsets=");
        sb.append(rect2);
        sb.append(", reducedResolution=");
        sb.append(this.reducedResolution);
        sb.append(", isRealSnapshot=");
        sb.append(this.isRealSnapshot);
        sb.append(", isTranslucent=");
        sb.append(this.isTranslucent);
        sb.append(", windowingMode=");
        sb.append(this.windowingMode);
        sb.append(", appearance=");
        sb.append(this.appearance);
        sb.append(", scale=");
        sb.append(this.scale);
        sb.append(", snapshotId=");
        return MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0.m(this.snapshotId, ")", sb);
    }

    public ThumbnailData() {
        this(null, 0, -1, new Rect(), new Rect(), false, true, false, 0, 0, 1.0f, 0L);
    }
}
