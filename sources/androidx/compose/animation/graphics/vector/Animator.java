package androidx.compose.animation.graphics.vector;

import androidx.collection.MutableScatterMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class Animator {
    /* JADX WARN: Code restructure failed: missing block: B:45:0x007b, code lost:
    
        if (r6 == androidx.compose.runtime.Composer.Companion.Empty) goto L45;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void Configure(final androidx.compose.animation.core.Transition r23, final androidx.compose.animation.graphics.vector.StateVectorConfig r24, final int r25, androidx.compose.runtime.Composer r26, final int r27) {
        /*
            Method dump skipped, instructions count: 610
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.graphics.vector.Animator.Configure(androidx.compose.animation.core.Transition, androidx.compose.animation.graphics.vector.StateVectorConfig, int, androidx.compose.runtime.Composer, int):void");
    }

    public abstract void collectPropertyValues(MutableScatterMap mutableScatterMap, int i, int i2);

    public abstract int getTotalDuration();
}
