package com.android.systemui.statusbar.phone;

import android.graphics.Rect;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class StatusBarContentInsetsProviderKt {
    /* JADX WARN: Code restructure failed: missing block: B:75:0x018d, code lost:
    
        r1 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x019e, code lost:
    
        if (r15.right >= r9) goto L93;
     */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0140 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0144  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final android.graphics.Rect calculateInsetsForRotationWithRotatedResources(int r16, int r17, com.android.systemui.SysUICutoutInformation r18, android.graphics.Rect r19, int r20, int r21, int r22, boolean r23, int r24, int r25, int r26) {
        /*
            Method dump skipped, instructions count: 466
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.phone.StatusBarContentInsetsProviderKt.calculateInsetsForRotationWithRotatedResources(int, int, com.android.systemui.SysUICutoutInformation, android.graphics.Rect, int, int, int, boolean, int, int, int):android.graphics.Rect");
    }

    public static final Rect getPrivacyChipBoundingRectForInsets(Rect rect, int i, int i2, boolean z) {
        if (z) {
            int i3 = rect.left;
            return new Rect(i3 - i, rect.top, i3 + i2, rect.bottom);
        }
        int i4 = rect.right;
        return new Rect(i4 - i2, rect.top, i4 + i, rect.bottom);
    }
}
