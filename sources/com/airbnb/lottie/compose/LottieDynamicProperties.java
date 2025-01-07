package com.airbnb.lottie.compose;

import android.graphics.Bitmap;
import android.graphics.ColorFilter;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Typeface;
import com.airbnb.lottie.value.ScaleXY;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LottieDynamicProperties {
    public final List bitmapProperties;
    public final List charSequenceProperties;
    public final List colorFilterProperties;
    public final List floatProperties;
    public final List intArrayProperties;
    public final List intProperties;
    public final List pathProperties;
    public final List pointFProperties;
    public final List scaleProperties;
    public final List typefaceProperties;

    public LottieDynamicProperties(List list) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (((LottieDynamicProperty) obj).property instanceof Integer) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj2 : list) {
            if (((LottieDynamicProperty) obj2).property instanceof PointF) {
                arrayList2.add(obj2);
            }
        }
        ArrayList arrayList3 = new ArrayList();
        for (Object obj3 : list) {
            if (((LottieDynamicProperty) obj3).property instanceof Float) {
                arrayList3.add(obj3);
            }
        }
        ArrayList arrayList4 = new ArrayList();
        for (Object obj4 : list) {
            if (((LottieDynamicProperty) obj4).property instanceof ScaleXY) {
                arrayList4.add(obj4);
            }
        }
        ArrayList arrayList5 = new ArrayList();
        for (Object obj5 : list) {
            if (((LottieDynamicProperty) obj5).property instanceof ColorFilter) {
                arrayList5.add(obj5);
            }
        }
        ArrayList arrayList6 = new ArrayList();
        for (Object obj6 : list) {
            if (((LottieDynamicProperty) obj6).property instanceof Object[]) {
                arrayList6.add(obj6);
            }
        }
        ArrayList arrayList7 = new ArrayList();
        for (Object obj7 : list) {
            if (((LottieDynamicProperty) obj7).property instanceof Typeface) {
                arrayList7.add(obj7);
            }
        }
        ArrayList arrayList8 = new ArrayList();
        for (Object obj8 : list) {
            if (((LottieDynamicProperty) obj8).property instanceof Bitmap) {
                arrayList8.add(obj8);
            }
        }
        ArrayList arrayList9 = new ArrayList();
        for (Object obj9 : list) {
            if (((LottieDynamicProperty) obj9).property instanceof CharSequence) {
                arrayList9.add(obj9);
            }
        }
        ArrayList arrayList10 = new ArrayList();
        for (Object obj10 : list) {
            if (((LottieDynamicProperty) obj10).property instanceof Path) {
                arrayList10.add(obj10);
            }
        }
        this.intProperties = arrayList;
        this.pointFProperties = arrayList2;
        this.floatProperties = arrayList3;
        this.scaleProperties = arrayList4;
        this.colorFilterProperties = arrayList5;
        this.intArrayProperties = arrayList6;
        this.typefaceProperties = arrayList7;
        this.bitmapProperties = arrayList8;
        this.charSequenceProperties = arrayList9;
        this.pathProperties = arrayList10;
    }
}
