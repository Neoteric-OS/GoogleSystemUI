package com.android.systemui.classifier;

import android.graphics.Point;
import android.provider.DeviceConfig;
import android.view.MotionEvent;
import androidx.appcompat.view.menu.CascadingMenuPopup$$ExternalSyntheticOutline0;
import com.android.systemui.classifier.FalsingClassifier;
import com.android.systemui.util.DeviceConfigProxy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ZigZagClassifier extends FalsingClassifier {
    public float mLastDevianceX;
    public float mLastDevianceY;
    public float mLastMaxXDeviance;
    public float mLastMaxYDeviance;
    public final float mMaxXPrimaryDeviance;
    public final float mMaxXSecondaryDeviance;
    public final float mMaxYPrimaryDeviance;
    public final float mMaxYSecondaryDeviance;

    public ZigZagClassifier(FalsingDataProvider falsingDataProvider, DeviceConfigProxy deviceConfigProxy) {
        super(falsingDataProvider);
        deviceConfigProxy.getClass();
        this.mMaxXPrimaryDeviance = DeviceConfig.getFloat("systemui", "brightline_falsing_zigzag_x_primary_deviance", 0.05f);
        this.mMaxYPrimaryDeviance = DeviceConfig.getFloat("systemui", "brightline_falsing_zigzag_y_primary_deviance", 0.15f);
        this.mMaxXSecondaryDeviance = DeviceConfig.getFloat("systemui", "brightline_falsing_zigzag_x_secondary_deviance", 0.4f);
        this.mMaxYSecondaryDeviance = DeviceConfig.getFloat("systemui", "brightline_falsing_zigzag_y_secondary_deviance", 0.3f);
    }

    public static List rotateMotionEvents(List list, double d) {
        ArrayList arrayList = new ArrayList();
        double cos = Math.cos(d);
        double sin = Math.sin(d);
        MotionEvent motionEvent = (MotionEvent) list.get(0);
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        for (Iterator it = list.iterator(); it.hasNext(); it = it) {
            MotionEvent motionEvent2 = (MotionEvent) it.next();
            double x2 = motionEvent2.getX() - x;
            double y2 = motionEvent2.getY() - y;
            arrayList.add(new Point((int) ((sin * y2) + (cos * x2) + x), (int) ((y2 * cos) + ((-sin) * x2) + y)));
            motionEvent = motionEvent;
        }
        MotionEvent motionEvent3 = motionEvent;
        MotionEvent motionEvent4 = (MotionEvent) list.get(list.size() - 1);
        Point point = (Point) arrayList.get(0);
        Point point2 = (Point) CascadingMenuPopup$$ExternalSyntheticOutline0.m(arrayList, 1);
        BrightLineFalsingManager.logDebug("Before: (" + motionEvent3.getX() + "," + motionEvent3.getY() + "), (" + motionEvent4.getX() + "," + motionEvent4.getY() + ")");
        BrightLineFalsingManager.logDebug("After: (" + point.x + "," + point.y + "), (" + point2.x + "," + point2.y + ")");
        return arrayList;
    }

    @Override // com.android.systemui.classifier.FalsingClassifier
    public final FalsingClassifier.Result calculateFalsingResult(int i) {
        List<Point> rotateMotionEvents;
        float f;
        float f2;
        if (i == 10 || i == 18 || i == 11) {
            return FalsingClassifier.Result.passed(0.0d);
        }
        FalsingDataProvider falsingDataProvider = this.mDataProvider;
        if (falsingDataProvider.getRecentMotionEvents().size() < 3) {
            return FalsingClassifier.Result.passed(0.0d);
        }
        if (falsingDataProvider.isHorizontal()) {
            double atan2LastPoint = getAtan2LastPoint();
            BrightLineFalsingManager.logDebug("Rotating to horizontal by: " + atan2LastPoint);
            rotateMotionEvents = rotateMotionEvents(falsingDataProvider.getRecentMotionEvents(), atan2LastPoint);
        } else {
            double atan2LastPoint2 = 1.5707963267948966d - getAtan2LastPoint();
            BrightLineFalsingManager.logDebug("Rotating to vertical by: " + atan2LastPoint2);
            rotateMotionEvents = rotateMotionEvents(falsingDataProvider.getRecentMotionEvents(), -atan2LastPoint2);
        }
        boolean z = true;
        float abs = Math.abs(((Point) rotateMotionEvents.get(0)).x - ((Point) rotateMotionEvents.get(rotateMotionEvents.size() - 1)).x);
        float abs2 = Math.abs(((Point) rotateMotionEvents.get(0)).y - ((Point) rotateMotionEvents.get(rotateMotionEvents.size() - 1)).y);
        BrightLineFalsingManager.logDebug("Actual: (" + abs + "," + abs2 + ")");
        float f3 = 0.0f;
        float f4 = 0.0f;
        float f5 = 0.0f;
        float f6 = 0.0f;
        for (Point point : rotateMotionEvents) {
            if (z) {
                f5 = point.x;
                f6 = point.y;
                z = false;
            } else {
                f3 += Math.abs(point.x - f5);
                f4 += Math.abs(point.y - f6);
                f5 = point.x;
                f6 = point.y;
                boolean z2 = BrightLineFalsingManager.DEBUG;
            }
        }
        float f7 = f3 - abs;
        float f8 = f4 - abs2;
        float f9 = falsingDataProvider.mXdpi;
        float f10 = abs / f9;
        float f11 = falsingDataProvider.mYdpi;
        float f12 = abs2 / f11;
        float sqrt = (float) Math.sqrt((f12 * f12) + (f10 * f10));
        if (abs > abs2) {
            f = this.mMaxXPrimaryDeviance * sqrt * f9;
            f2 = this.mMaxYSecondaryDeviance;
        } else {
            f = this.mMaxXSecondaryDeviance * sqrt * f9;
            f2 = this.mMaxYPrimaryDeviance;
        }
        float f13 = f2 * sqrt * f11;
        this.mLastDevianceX = f7;
        this.mLastDevianceY = f8;
        this.mLastMaxXDeviance = f;
        this.mLastMaxYDeviance = f13;
        BrightLineFalsingManager.logDebug("Straightness Deviance: (" + f7 + "," + f8 + ") vs (" + f + "," + f13 + ")");
        return (f7 > f || f8 > f13) ? falsed(0.5d, String.format(null, "{devianceX=%f, maxDevianceX=%s, devianceY=%s, maxDevianceY=%s}", Float.valueOf(this.mLastDevianceX), Float.valueOf(this.mLastMaxXDeviance), Float.valueOf(this.mLastDevianceY), Float.valueOf(this.mLastMaxYDeviance))) : FalsingClassifier.Result.passed(0.5d);
    }

    public final float getAtan2LastPoint() {
        FalsingDataProvider falsingDataProvider = this.mDataProvider;
        falsingDataProvider.recalculateData();
        MotionEvent motionEvent = falsingDataProvider.mFirstRecentMotionEvent;
        falsingDataProvider.recalculateData();
        MotionEvent motionEvent2 = falsingDataProvider.mLastMotionEvent;
        float x = motionEvent.getX();
        return (float) Math.atan2(motionEvent2.getY() - motionEvent.getY(), motionEvent2.getX() - x);
    }
}
