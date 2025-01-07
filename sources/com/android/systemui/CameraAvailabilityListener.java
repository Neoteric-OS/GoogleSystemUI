package com.android.systemui;

import android.graphics.Path;
import android.graphics.Rect;
import android.hardware.camera2.CameraManager;
import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import com.android.systemui.CameraAvailabilityListener;
import com.android.systemui.ScreenDecorations;
import com.android.systemui.log.ScreenDecorationsLogger;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.util.concurrency.ExecutorImpl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CameraAvailabilityListener {
    public CameraProtectionInfo activeProtectionInfo;
    public final CameraManager cameraManager;
    public final List cameraProtectionInfoList;
    public final Set excludedPackageIds;
    public final ExecutorImpl executor;
    public OpenCameraInfo openCamera;
    public final Set unavailablePhysicalCameras = new LinkedHashSet();
    public final List listeners = new ArrayList();
    public final CameraAvailabilityListener$availabilityCallback$1 availabilityCallback = new CameraManager.AvailabilityCallback() { // from class: com.android.systemui.CameraAvailabilityListener$availabilityCallback$1
        public final void onCameraClosed(String str) {
            CameraAvailabilityListener cameraAvailabilityListener = CameraAvailabilityListener.this;
            cameraAvailabilityListener.openCamera = null;
            CameraProtectionInfo cameraProtectionInfo = cameraAvailabilityListener.activeProtectionInfo;
            if (Intrinsics.areEqual(cameraProtectionInfo != null ? cameraProtectionInfo.logicalCameraId : null, str)) {
                CameraAvailabilityListener.access$notifyCameraInactive(CameraAvailabilityListener.this);
            }
            CameraAvailabilityListener.this.activeProtectionInfo = null;
        }

        public final void onCameraOpened(String str, String str2) {
            Object obj;
            CameraAvailabilityListener cameraAvailabilityListener = CameraAvailabilityListener.this;
            cameraAvailabilityListener.openCamera = new CameraAvailabilityListener.OpenCameraInfo(str, str2);
            if (cameraAvailabilityListener.excludedPackageIds.contains(str2)) {
                return;
            }
            CameraAvailabilityListener cameraAvailabilityListener2 = CameraAvailabilityListener.this;
            Iterator it = cameraAvailabilityListener2.cameraProtectionInfoList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                CameraProtectionInfo cameraProtectionInfo = (CameraProtectionInfo) obj;
                if (str.equals(cameraProtectionInfo.logicalCameraId) && !CollectionsKt.contains(cameraAvailabilityListener2.unavailablePhysicalCameras, cameraProtectionInfo.physicalCameraId)) {
                    break;
                }
            }
            CameraProtectionInfo cameraProtectionInfo2 = (CameraProtectionInfo) obj;
            if (cameraProtectionInfo2 != null) {
                CameraAvailabilityListener cameraAvailabilityListener3 = CameraAvailabilityListener.this;
                cameraAvailabilityListener3.activeProtectionInfo = cameraProtectionInfo2;
                CameraAvailabilityListener.access$notifyCameraActive(cameraAvailabilityListener3, cameraProtectionInfo2);
            }
        }

        @Override // android.hardware.camera2.CameraManager.AvailabilityCallback
        public final void onPhysicalCameraAvailable(String str, String str2) {
            Object obj;
            CameraAvailabilityListener.this.unavailablePhysicalCameras.remove(str2);
            CameraAvailabilityListener.OpenCameraInfo openCameraInfo = CameraAvailabilityListener.this.openCamera;
            if (openCameraInfo == null || !openCameraInfo.logicalCameraId.equals(str) || CameraAvailabilityListener.this.excludedPackageIds.contains(openCameraInfo.packageId)) {
                return;
            }
            Iterator it = CameraAvailabilityListener.this.cameraProtectionInfoList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                CameraProtectionInfo cameraProtectionInfo = (CameraProtectionInfo) obj;
                if (Intrinsics.areEqual(cameraProtectionInfo.logicalCameraId, str) && Intrinsics.areEqual(cameraProtectionInfo.physicalCameraId, str2)) {
                    break;
                }
            }
            CameraProtectionInfo cameraProtectionInfo2 = (CameraProtectionInfo) obj;
            if (cameraProtectionInfo2 != null) {
                CameraAvailabilityListener cameraAvailabilityListener = CameraAvailabilityListener.this;
                cameraAvailabilityListener.activeProtectionInfo = cameraProtectionInfo2;
                CameraAvailabilityListener.access$notifyCameraActive(cameraAvailabilityListener, cameraProtectionInfo2);
            }
        }

        @Override // android.hardware.camera2.CameraManager.AvailabilityCallback
        public final void onPhysicalCameraUnavailable(String str, String str2) {
            CameraAvailabilityListener.this.unavailablePhysicalCameras.add(str2);
            CameraProtectionInfo cameraProtectionInfo = CameraAvailabilityListener.this.activeProtectionInfo;
            if (cameraProtectionInfo != null && Intrinsics.areEqual(cameraProtectionInfo.logicalCameraId, str) && Intrinsics.areEqual(cameraProtectionInfo.physicalCameraId, str2)) {
                CameraAvailabilityListener cameraAvailabilityListener = CameraAvailabilityListener.this;
                cameraAvailabilityListener.activeProtectionInfo = null;
                CameraAvailabilityListener.access$notifyCameraInactive(cameraAvailabilityListener);
            }
        }
    };

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class OpenCameraInfo {
        public final String logicalCameraId;
        public final String packageId;

        public OpenCameraInfo(String str, String str2) {
            this.logicalCameraId = str;
            this.packageId = str2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof OpenCameraInfo)) {
                return false;
            }
            OpenCameraInfo openCameraInfo = (OpenCameraInfo) obj;
            return Intrinsics.areEqual(this.logicalCameraId, openCameraInfo.logicalCameraId) && Intrinsics.areEqual(this.packageId, openCameraInfo.packageId);
        }

        public final int hashCode() {
            return this.packageId.hashCode() + (this.logicalCameraId.hashCode() * 31);
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("OpenCameraInfo(logicalCameraId=");
            sb.append(this.logicalCameraId);
            sb.append(", packageId=");
            return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(sb, this.packageId, ")");
        }
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [com.android.systemui.CameraAvailabilityListener$availabilityCallback$1] */
    public CameraAvailabilityListener(CameraManager cameraManager, List list, String str, ExecutorImpl executorImpl) {
        this.cameraManager = cameraManager;
        this.cameraProtectionInfoList = list;
        this.executor = executorImpl;
        this.excludedPackageIds = CollectionsKt.toSet(StringsKt.split$default(str, new String[]{","}, 0, 6));
    }

    public static final void access$notifyCameraActive(CameraAvailabilityListener cameraAvailabilityListener, CameraProtectionInfo cameraProtectionInfo) {
        for (ScreenDecorations.AnonymousClass1 anonymousClass1 : cameraAvailabilityListener.listeners) {
            Path path = cameraProtectionInfo.cutoutProtectionPath;
            Rect rect = cameraProtectionInfo.bounds;
            ScreenDecorations screenDecorations = ScreenDecorations.this;
            ScreenDecorationsLogger screenDecorationsLogger = screenDecorations.mLogger;
            screenDecorationsLogger.getClass();
            screenDecorationsLogger.logBuffer.log("ScreenDecorationsLog", LogLevel.DEBUG, "onApplyCameraProtection", null);
            screenDecorations.showCameraProtection(path, rect);
        }
    }

    public static final void access$notifyCameraInactive(CameraAvailabilityListener cameraAvailabilityListener) {
        Iterator it = cameraAvailabilityListener.listeners.iterator();
        while (it.hasNext()) {
            ScreenDecorations screenDecorations = ScreenDecorations.this;
            ScreenDecorationsLogger screenDecorationsLogger = screenDecorations.mLogger;
            screenDecorationsLogger.getClass();
            screenDecorationsLogger.logBuffer.log("ScreenDecorationsLog", LogLevel.DEBUG, "onHideCameraProtection", null);
            screenDecorations.hideCameraProtection();
        }
    }
}
