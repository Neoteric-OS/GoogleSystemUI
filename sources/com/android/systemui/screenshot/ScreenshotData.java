package com.android.systemui.screenshot;

import android.content.ComponentName;
import android.graphics.Bitmap;
import android.graphics.Insets;
import android.graphics.Rect;
import android.os.Process;
import android.os.UserHandle;
import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ScreenshotData {
    public Bitmap bitmap;
    public final int displayId;
    public Insets insets;
    public Rect screenBounds;
    public final int source;
    public final int taskId;
    public final ComponentName topComponent;
    public final int type;
    public final UserHandle userHandle;

    public ScreenshotData(int i, int i2, UserHandle userHandle, ComponentName componentName, Rect rect, int i3, Insets insets, Bitmap bitmap, int i4) {
        this.type = i;
        this.source = i2;
        this.userHandle = userHandle;
        this.topComponent = componentName;
        this.screenBounds = rect;
        this.taskId = i3;
        this.insets = insets;
        this.bitmap = bitmap;
        this.displayId = i4;
    }

    public static ScreenshotData copy$default(ScreenshotData screenshotData, int i, UserHandle userHandle, ComponentName componentName, Rect rect, int i2, Bitmap bitmap) {
        int i3 = screenshotData.source;
        Insets insets = screenshotData.insets;
        int i4 = screenshotData.displayId;
        screenshotData.getClass();
        screenshotData.getClass();
        return new ScreenshotData(i, i3, userHandle, componentName, rect, i2, insets, bitmap, i4);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ScreenshotData)) {
            return false;
        }
        ScreenshotData screenshotData = (ScreenshotData) obj;
        return this.type == screenshotData.type && this.source == screenshotData.source && Intrinsics.areEqual(this.userHandle, screenshotData.userHandle) && Intrinsics.areEqual(this.topComponent, screenshotData.topComponent) && Intrinsics.areEqual(this.screenBounds, screenshotData.screenBounds) && this.taskId == screenshotData.taskId && Intrinsics.areEqual(this.insets, screenshotData.insets) && Intrinsics.areEqual(this.bitmap, screenshotData.bitmap) && this.displayId == screenshotData.displayId && Intrinsics.areEqual((Object) null, (Object) null);
    }

    public final String getPackageNameString() {
        ComponentName componentName = this.topComponent;
        if (componentName == null) {
            return "";
        }
        Intrinsics.checkNotNull(componentName);
        return componentName.getPackageName();
    }

    public final UserHandle getUserOrDefault() {
        UserHandle userHandle = this.userHandle;
        return userHandle == null ? Process.myUserHandle() : userHandle;
    }

    public final int hashCode() {
        int m = KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.source, Integer.hashCode(this.type) * 31, 31);
        UserHandle userHandle = this.userHandle;
        int hashCode = (m + (userHandle == null ? 0 : userHandle.hashCode())) * 31;
        ComponentName componentName = this.topComponent;
        int hashCode2 = (hashCode + (componentName == null ? 0 : componentName.hashCode())) * 31;
        Rect rect = this.screenBounds;
        int hashCode3 = (this.insets.hashCode() + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.taskId, (hashCode2 + (rect == null ? 0 : rect.hashCode())) * 31, 31)) * 31;
        Bitmap bitmap = this.bitmap;
        return KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.displayId, (hashCode3 + (bitmap != null ? bitmap.hashCode() : 0)) * 31, 31);
    }

    public final String toString() {
        UserHandle userHandle = this.userHandle;
        ComponentName componentName = this.topComponent;
        Rect rect = this.screenBounds;
        Insets insets = this.insets;
        Bitmap bitmap = this.bitmap;
        StringBuilder sb = new StringBuilder("ScreenshotData(type=");
        sb.append(this.type);
        sb.append(", source=");
        sb.append(this.source);
        sb.append(", userHandle=");
        sb.append(userHandle);
        sb.append(", topComponent=");
        sb.append(componentName);
        sb.append(", screenBounds=");
        sb.append(rect);
        sb.append(", taskId=");
        sb.append(this.taskId);
        sb.append(", insets=");
        sb.append(insets);
        sb.append(", bitmap=");
        sb.append(bitmap);
        sb.append(", displayId=");
        return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.displayId, ", contextUrl=null)");
    }
}
