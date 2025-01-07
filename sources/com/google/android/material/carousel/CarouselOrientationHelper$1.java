package com.google.android.material.carousel;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CarouselOrientationHelper$1 {
    public final /* synthetic */ int $r8$classId;
    public final int orientation;
    public final /* synthetic */ CarouselLayoutManager val$carouselLayoutManager;

    public CarouselOrientationHelper$1(int i) {
        this.orientation = i;
    }

    public final int getParentStart() {
        switch (this.$r8$classId) {
            case 0:
                return 0;
            default:
                CarouselLayoutManager carouselLayoutManager = this.val$carouselLayoutManager;
                if (carouselLayoutManager.isLayoutRtl()) {
                    return carouselLayoutManager.mWidth;
                }
                return 0;
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CarouselOrientationHelper$1(CarouselLayoutManager carouselLayoutManager, int i) {
        this(1);
        this.$r8$classId = i;
        switch (i) {
            case 1:
                this.val$carouselLayoutManager = carouselLayoutManager;
                this(0);
                break;
            default:
                this.val$carouselLayoutManager = carouselLayoutManager;
                break;
        }
    }
}
