package com.airbnb.lottie.utils;

import android.graphics.Path;
import android.graphics.PointF;
import androidx.compose.animation.AndroidFlingSpline$$ExternalSyntheticOutline0;
import com.airbnb.lottie.animation.content.KeyPathElementContent;
import com.airbnb.lottie.model.CubicCurveData;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.content.ShapeData;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class MiscUtils {
    public static final PointF pathFromDataCurrentPoint = new PointF();

    public static PointF addPoints(PointF pointF, PointF pointF2) {
        return new PointF(pointF.x + pointF2.x, pointF.y + pointF2.y);
    }

    public static float clamp(float f, float f2, float f3) {
        return Math.max(f2, Math.min(f3, f));
    }

    public static int floorMod(float f, float f2) {
        int i = (int) f;
        int i2 = (int) f2;
        int i3 = i / i2;
        int i4 = i % i2;
        if (!((i ^ i2) >= 0) && i4 != 0) {
            i3--;
        }
        return i - (i2 * i3);
    }

    public static void getPathFromData(ShapeData shapeData, Path path) {
        path.reset();
        PointF pointF = shapeData.initialPoint;
        path.moveTo(pointF.x, pointF.y);
        pathFromDataCurrentPoint.set(pointF.x, pointF.y);
        for (int i = 0; i < ((ArrayList) shapeData.curves).size(); i++) {
            CubicCurveData cubicCurveData = (CubicCurveData) ((ArrayList) shapeData.curves).get(i);
            PointF pointF2 = cubicCurveData.controlPoint1;
            PointF pointF3 = cubicCurveData.controlPoint2;
            PointF pointF4 = cubicCurveData.vertex;
            PointF pointF5 = pathFromDataCurrentPoint;
            if (pointF2.equals(pointF5) && pointF3.equals(pointF4)) {
                path.lineTo(pointF4.x, pointF4.y);
            } else {
                path.cubicTo(pointF2.x, pointF2.y, pointF3.x, pointF3.y, pointF4.x, pointF4.y);
            }
            pointF5.set(pointF4.x, pointF4.y);
        }
        if (shapeData.closed) {
            path.close();
        }
    }

    public static float lerp(float f, float f2, float f3) {
        return AndroidFlingSpline$$ExternalSyntheticOutline0.m(f2, f, f3, f);
    }

    public static void resolveKeyPath(KeyPath keyPath, int i, List list, KeyPath keyPath2, KeyPathElementContent keyPathElementContent) {
        if (keyPath.fullyResolvesTo(i, keyPathElementContent.getName())) {
            String name = keyPathElementContent.getName();
            KeyPath keyPath3 = new KeyPath(keyPath2);
            keyPath3.keys.add(name);
            KeyPath keyPath4 = new KeyPath(keyPath3);
            keyPath4.resolvedElement = keyPathElementContent;
            list.add(keyPath4);
        }
    }
}
