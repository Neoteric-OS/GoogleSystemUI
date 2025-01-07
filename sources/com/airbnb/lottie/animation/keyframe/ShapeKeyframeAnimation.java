package com.airbnb.lottie.animation.keyframe;

import android.graphics.Path;
import com.airbnb.lottie.model.content.ShapeData;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ShapeKeyframeAnimation extends BaseKeyframeAnimation {
    public List shapeModifiers;
    public final Path tempPath;
    public final ShapeData tempShapeData;
    public Path valueCallbackEndPath;
    public Path valueCallbackStartPath;

    public ShapeKeyframeAnimation(List list) {
        super(list);
        this.tempShapeData = new ShapeData();
        this.tempPath = new Path();
    }

    /* JADX WARN: Code restructure failed: missing block: B:84:0x02b0, code lost:
    
        if (r10 == (r8.size() - 1)) goto L97;
     */
    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getValue(com.airbnb.lottie.value.Keyframe r25, float r26) {
        /*
            Method dump skipped, instructions count: 1107
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.animation.keyframe.ShapeKeyframeAnimation.getValue(com.airbnb.lottie.value.Keyframe, float):java.lang.Object");
    }
}
