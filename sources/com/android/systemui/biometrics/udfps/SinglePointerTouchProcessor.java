package com.android.systemui.biometrics.udfps;

import android.graphics.PointF;
import android.util.RotationUtils;
import android.view.MotionEvent;
import com.android.systemui.biometrics.shared.model.UdfpsOverlayParams;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import kotlin.collections.CollectionsKt__IterablesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SinglePointerTouchProcessor {
    public final OverlapDetector overlapDetector;

    public SinglePointerTouchProcessor(OverlapDetector overlapDetector) {
        this.overlapDetector = overlapDetector;
    }

    public static final PreprocessedTouch processTouch$preprocess(MotionEvent motionEvent, int i, UdfpsOverlayParams udfpsOverlayParams, SinglePointerTouchProcessor singlePointerTouchProcessor) {
        int pointerCount = motionEvent.getPointerCount();
        ArrayList arrayList = new ArrayList(pointerCount);
        for (int i2 = 0; i2 < pointerCount; i2++) {
            Set set = SinglePointerTouchProcessorKt.SUPPORTED_ROTATIONS;
            PointF pointF = new PointF(motionEvent.getRawX(i2), motionEvent.getRawY(i2));
            int i3 = udfpsOverlayParams.rotation;
            Set set2 = SinglePointerTouchProcessorKt.SUPPORTED_ROTATIONS;
            if (set2.contains(Integer.valueOf(i3))) {
                RotationUtils.rotatePointF(pointF, RotationUtils.deltaRotation(i3, 0), udfpsOverlayParams.logicalDisplayWidth, udfpsOverlayParams.logicalDisplayHeight);
            }
            float f = pointF.x;
            float f2 = udfpsOverlayParams.scaleFactor;
            float f3 = f / f2;
            float f4 = pointF.y / f2;
            float touchMinor = motionEvent.getTouchMinor(i2) / f2;
            float touchMajor = motionEvent.getTouchMajor(i2) / f2;
            float orientation = motionEvent.getOrientation(i2);
            if (set2.contains(Integer.valueOf(udfpsOverlayParams.rotation))) {
                double d = ((orientation % 3.141592653589793d) + 1.5707963267948966d) % 3.141592653589793d;
                if (d >= 1.5707963267948966d) {
                    d -= 3.141592653589793d;
                }
                orientation = (float) d;
            }
            arrayList.add(new NormalizedTouchData(motionEvent.getPointerId(i2), f3, f4, touchMinor, touchMajor, orientation, motionEvent.getEventTime(), motionEvent.getDownTime()));
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (singlePointerTouchProcessor.overlapDetector.isGoodOverlap((NormalizedTouchData) obj, udfpsOverlayParams.nativeSensorBounds, udfpsOverlayParams.nativeOverlayBounds)) {
                arrayList2.add(obj);
            }
        }
        ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList2, 10));
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList3.add(Integer.valueOf(((NormalizedTouchData) it.next()).pointerId));
        }
        return new PreprocessedTouch(i, arrayList, arrayList3);
    }
}
