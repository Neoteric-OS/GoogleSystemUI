package com.android.keyguard;

import android.content.Context;
import android.content.res.TypedArray;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.random.XorWowRandom;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PinShapeAdapter {
    public final List shapes = new ArrayList();

    public PinShapeAdapter(Context context) {
        long currentTimeMillis = System.currentTimeMillis();
        new XorWowRandom((int) currentTimeMillis, (int) (currentTimeMillis >> 32));
        TypedArray obtainTypedArray = context.getResources().obtainTypedArray(R.array.bouncer_pin_shapes);
        int length = obtainTypedArray.length();
        for (int i = 0; i < length; i++) {
            this.shapes.add(Integer.valueOf(obtainTypedArray.getResourceId(i, 0)));
        }
        Collections.shuffle(this.shapes);
        obtainTypedArray.recycle();
    }

    public final int getShape(int i) {
        List list = this.shapes;
        int size = ((ArrayList) list).size();
        int i2 = i % size;
        return ((Number) ((ArrayList) list).get(i2 + (size & (((i2 ^ size) & ((-i2) | i2)) >> 31)))).intValue();
    }
}
