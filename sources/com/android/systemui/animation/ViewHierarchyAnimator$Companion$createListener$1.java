package com.android.systemui.animation;

import android.view.View;
import android.view.animation.Interpolator;
import com.android.systemui.animation.ViewHierarchyAnimator;
import com.android.systemui.temporarydisplay.chipbar.ChipbarCoordinator$animateViewIn$onAnimationEnd$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ViewHierarchyAnimator$Companion$createListener$1 implements View.OnLayoutChangeListener {
    public final /* synthetic */ long $duration;
    public final /* synthetic */ boolean $ignorePreviousValues;
    public final /* synthetic */ Interpolator $interpolator;
    public final /* synthetic */ ChipbarCoordinator$animateViewIn$onAnimationEnd$1 $onAnimationEnd;
    public final /* synthetic */ ViewHierarchyAnimator.Hotspot $origin;

    public ViewHierarchyAnimator$Companion$createListener$1(ViewHierarchyAnimator.Hotspot hotspot, boolean z, Interpolator interpolator, long j, ChipbarCoordinator$animateViewIn$onAnimationEnd$1 chipbarCoordinator$animateViewIn$onAnimationEnd$1) {
        this.$origin = hotspot;
        this.$ignorePreviousValues = z;
        this.$interpolator = interpolator;
        this.$duration = j;
        this.$onAnimationEnd = chipbarCoordinator$animateViewIn$onAnimationEnd$1;
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00fe  */
    @Override // android.view.View.OnLayoutChangeListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onLayoutChange(android.view.View r19, int r20, int r21, int r22, int r23, int r24, int r25, int r26, int r27) {
        /*
            Method dump skipped, instructions count: 534
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.animation.ViewHierarchyAnimator$Companion$createListener$1.onLayoutChange(android.view.View, int, int, int, int, int, int, int, int):void");
    }
}
