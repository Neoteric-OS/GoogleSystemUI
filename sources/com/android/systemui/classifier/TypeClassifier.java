package com.android.systemui.classifier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TypeClassifier extends FalsingClassifier {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0029, code lost:
    
        if (r5 != false) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x002c, code lost:
    
        r3 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0030, code lost:
    
        if (r5 != false) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0035, code lost:
    
        if (r5 != false) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x003a, code lost:
    
        if (r5 != false) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0041, code lost:
    
        if (r5 == false) goto L14;
     */
    @Override // com.android.systemui.classifier.FalsingClassifier
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.android.systemui.classifier.FalsingClassifier.Result calculateFalsingResult(int r12) {
        /*
            r11 = this;
            r0 = 13
            r1 = 0
            if (r12 != r0) goto Lb
            com.android.systemui.classifier.FalsingClassifier$Result r11 = com.android.systemui.classifier.FalsingClassifier.Result.passed(r1)
            return r11
        Lb:
            com.android.systemui.classifier.FalsingDataProvider r0 = r11.mDataProvider
            boolean r3 = r0.isHorizontal()
            r4 = r3 ^ 1
            boolean r5 = r11.isUp()
            boolean r6 = r11.isRight()
            r7 = 1
            r8 = 0
            r9 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            switch(r12) {
                case 0: goto L3f;
                case 1: goto L3d;
                case 2: goto L3f;
                case 3: goto L22;
                case 4: goto L38;
                case 5: goto L33;
                case 6: goto L2e;
                case 7: goto L22;
                case 8: goto L38;
                case 9: goto L3f;
                case 10: goto L25;
                case 11: goto L23;
                case 12: goto L27;
                case 13: goto L22;
                case 14: goto L22;
                case 15: goto L3d;
                case 16: goto L22;
                case 17: goto L23;
                case 18: goto L25;
                case 19: goto L38;
                default: goto L22;
            }
        L22:
            r3 = r7
        L23:
            r1 = r9
            goto L44
        L25:
            r3 = r4
            goto L44
        L27:
            if (r3 != 0) goto L22
            if (r5 != 0) goto L2c
            goto L22
        L2c:
            r3 = r8
            goto L23
        L2e:
            if (r6 != 0) goto L22
            if (r5 != 0) goto L2c
            goto L22
        L33:
            if (r6 == 0) goto L22
            if (r5 != 0) goto L2c
            goto L22
        L38:
            if (r3 != 0) goto L22
            if (r5 != 0) goto L2c
            goto L22
        L3d:
            r3 = r4
            goto L23
        L3f:
            if (r3 != 0) goto L22
            if (r5 == 0) goto L2c
            goto L22
        L44:
            if (r3 == 0) goto L83
            boolean r0 = r0.isHorizontal()
            r0 = r0 ^ r7
            boolean r3 = r11.isUp()
            boolean r4 = r11.isRight()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "{interaction="
            r5.<init>(r6)
            r5.append(r12)
            java.lang.String r12 = ", vertical="
            r5.append(r12)
            r5.append(r0)
            java.lang.String r12 = ", up="
            r5.append(r12)
            r5.append(r3)
            java.lang.String r12 = ", right="
            r5.append(r12)
            r5.append(r4)
            java.lang.String r12 = "}"
            r5.append(r12)
            java.lang.String r12 = r5.toString()
            com.android.systemui.classifier.FalsingClassifier$Result r11 = r11.falsed(r1, r12)
            goto L89
        L83:
            r11 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            com.android.systemui.classifier.FalsingClassifier$Result r11 = com.android.systemui.classifier.FalsingClassifier.Result.passed(r11)
        L89:
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.classifier.TypeClassifier.calculateFalsingResult(int):com.android.systemui.classifier.FalsingClassifier$Result");
    }
}
