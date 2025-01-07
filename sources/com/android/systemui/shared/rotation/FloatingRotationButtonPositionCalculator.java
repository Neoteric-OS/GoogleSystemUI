package com.android.systemui.shared.rotation;

import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FloatingRotationButtonPositionCalculator {
    public final int defaultMargin;
    public final boolean floatingRotationButtonPositionLeft;
    public final int taskbarMarginBottom;
    public final int taskbarMarginLeft;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Position {
        public final int gravity;
        public final int translationX;
        public final int translationY;

        public Position(int i, int i2, int i3) {
            this.gravity = i;
            this.translationX = i2;
            this.translationY = i3;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Position)) {
                return false;
            }
            Position position = (Position) obj;
            return this.gravity == position.gravity && this.translationX == position.translationX && this.translationY == position.translationY;
        }

        public final int hashCode() {
            return Integer.hashCode(this.translationY) + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.translationX, Integer.hashCode(this.gravity) * 31, 31);
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("Position(gravity=");
            sb.append(this.gravity);
            sb.append(", translationX=");
            sb.append(this.translationX);
            sb.append(", translationY=");
            return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.translationY, ")");
        }
    }

    public FloatingRotationButtonPositionCalculator(int i, int i2, int i3, boolean z) {
        this.defaultMargin = i;
        this.taskbarMarginLeft = i2;
        this.taskbarMarginBottom = i3;
        this.floatingRotationButtonPositionLeft = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x004e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.android.systemui.shared.rotation.FloatingRotationButtonPositionCalculator.Position calculatePosition(int r9, boolean r10, boolean r11) {
        /*
            r8 = this;
            r0 = 1
            if (r9 == 0) goto L5
            if (r9 != r0) goto Lb
        L5:
            if (r10 == 0) goto Lb
            if (r11 != 0) goto Lb
            r10 = r0
            goto Lc
        Lb:
            r10 = 0
        Lc:
            boolean r11 = r8.floatingRotationButtonPositionLeft
            r1 = 83
            r2 = 85
            r3 = 53
            r4 = 51
            java.lang.String r5 = "Invalid rotation "
            r6 = 3
            r7 = 2
            if (r11 == 0) goto L34
            if (r9 == 0) goto L47
            if (r9 == r0) goto L32
            if (r9 == r7) goto L30
            if (r9 != r6) goto L26
        L24:
            r1 = r4
            goto L47
        L26:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r9 = android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0.m(r9, r5)
            r8.<init>(r9)
            throw r8
        L30:
            r1 = r3
            goto L47
        L32:
            r1 = r2
            goto L47
        L34:
            if (r9 == 0) goto L32
            if (r9 == r0) goto L30
            if (r9 == r7) goto L24
            if (r9 != r6) goto L3d
            goto L47
        L3d:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r9 = android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0.m(r9, r5)
            r8.<init>(r9)
            throw r8
        L47:
            int r9 = r8.defaultMargin
            if (r10 == 0) goto L4e
            int r11 = r8.taskbarMarginLeft
            goto L4f
        L4e:
            r11 = r9
        L4f:
            if (r10 == 0) goto L53
            int r9 = r8.taskbarMarginBottom
        L53:
            r8 = r1 & 5
            r10 = 5
            if (r8 != r10) goto L59
            int r11 = -r11
        L59:
            r8 = r1 & 80
            r10 = 80
            if (r8 != r10) goto L60
            int r9 = -r9
        L60:
            com.android.systemui.shared.rotation.FloatingRotationButtonPositionCalculator$Position r8 = new com.android.systemui.shared.rotation.FloatingRotationButtonPositionCalculator$Position
            r8.<init>(r1, r11, r9)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.shared.rotation.FloatingRotationButtonPositionCalculator.calculatePosition(int, boolean, boolean):com.android.systemui.shared.rotation.FloatingRotationButtonPositionCalculator$Position");
    }
}
