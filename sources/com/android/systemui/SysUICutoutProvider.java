package com.android.systemui;

import android.content.Context;
import android.graphics.Rect;
import android.util.RotationUtils;
import android.view.Display;
import android.view.DisplayCutout;
import android.view.DisplayInfo;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SysUICutoutProvider {
    public final Lazy cameraProtectionList$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.SysUICutoutProvider$cameraProtectionList$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return SysUICutoutProvider.this.cameraProtectionLoader.loadCameraProtectionInfoList();
        }
    });
    public final CameraProtectionLoaderImpl cameraProtectionLoader;
    public final Context context;

    public SysUICutoutProvider(Context context, CameraProtectionLoaderImpl cameraProtectionLoaderImpl) {
        this.context = context;
        this.cameraProtectionLoader = cameraProtectionLoaderImpl;
    }

    public final SysUICutoutInformation cutoutInfoForCurrentDisplayAndRotation() {
        Object obj;
        Display display = this.context.getDisplay();
        DisplayCutout cutout = display.getCutout();
        CameraProtectionInfo cameraProtectionInfo = null;
        if (cutout == null) {
            return null;
        }
        String uniqueId = display.getUniqueId();
        if (uniqueId != null && uniqueId.length() != 0) {
            Iterator it = ((List) this.cameraProtectionList$delegate.getValue()).iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (Intrinsics.areEqual(((CameraProtectionInfo) obj).displayUniqueId, uniqueId)) {
                    break;
                }
            }
            CameraProtectionInfo cameraProtectionInfo2 = (CameraProtectionInfo) obj;
            if (cameraProtectionInfo2 != null) {
                Rect rect = cameraProtectionInfo2.bounds;
                DisplayInfo displayInfo = new DisplayInfo();
                display.getDisplayInfo(displayInfo);
                Rect rect2 = new Rect(0, 0, displayInfo.getNaturalWidth(), displayInfo.getNaturalHeight());
                Rect rect3 = new Rect(rect);
                RotationUtils.rotateBounds(rect3, rect2.width(), rect2.height(), display.getRotation());
                cameraProtectionInfo = new CameraProtectionInfo(cameraProtectionInfo2.logicalCameraId, cameraProtectionInfo2.physicalCameraId, cameraProtectionInfo2.cutoutProtectionPath, rect3, cameraProtectionInfo2.displayUniqueId);
            }
        }
        return new SysUICutoutInformation(cutout, cameraProtectionInfo);
    }
}
