package androidx.compose.animation.graphics.vector;

import androidx.compose.ui.graphics.vector.ImageVector;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AnimatedImageVector {
    public static final Companion Companion = null;
    public final ImageVector imageVector;
    public final List targets;
    public final int totalDuration;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
    }

    public AnimatedImageVector(ImageVector imageVector, List list) {
        Object obj;
        this.imageVector = imageVector;
        this.targets = list;
        if (list.isEmpty()) {
            obj = null;
        } else {
            ArrayList arrayList = (ArrayList) list;
            Object obj2 = arrayList.get(0);
            int totalDuration = ((AnimatedVectorTarget) obj2).animator.getTotalDuration();
            int lastIndex = CollectionsKt__CollectionsKt.getLastIndex(list);
            int i = 1;
            if (1 <= lastIndex) {
                while (true) {
                    Object obj3 = arrayList.get(i);
                    int totalDuration2 = ((AnimatedVectorTarget) obj3).animator.getTotalDuration();
                    if (totalDuration < totalDuration2) {
                        obj2 = obj3;
                        totalDuration = totalDuration2;
                    }
                    if (i == lastIndex) {
                        break;
                    } else {
                        i++;
                    }
                }
            }
            obj = obj2;
        }
        AnimatedVectorTarget animatedVectorTarget = (AnimatedVectorTarget) obj;
        this.totalDuration = animatedVectorTarget != null ? animatedVectorTarget.animator.getTotalDuration() : 0;
    }
}
