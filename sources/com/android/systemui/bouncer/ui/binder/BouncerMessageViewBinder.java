package com.android.systemui.bouncer.ui.binder;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BouncerMessageViewBinder {
    public static final BouncerMessageViewBinder INSTANCE = null;

    /* JADX WARN: Removed duplicated region for block: B:13:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0057  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void access$updateView(com.android.keyguard.KeyguardMessageAreaController r4, com.android.keyguard.BouncerKeyguardMessageArea r5, com.android.systemui.bouncer.shared.model.Message r6, boolean r7) {
        /*
            if (r5 == 0) goto L5c
            if (r4 != 0) goto L5
            goto L5c
        L5:
            r0 = 0
            if (r6 == 0) goto Lb
            java.lang.String r1 = r6.message
            goto Lc
        Lb:
            r1 = r0
        Lc:
            r2 = 0
            if (r1 != 0) goto L1d
            if (r6 == 0) goto L13
            java.lang.Integer r0 = r6.messageResId
        L13:
            if (r0 == 0) goto L16
            goto L1d
        L16:
            r4.setIsVisible(r2)
            r4.setMessage(r2)
            goto L52
        L1d:
            r0 = 1
            r4.setIsVisible(r0)
            java.lang.String r0 = r6.message
            if (r0 != 0) goto L27
            java.lang.String r0 = ""
        L27:
            java.lang.Integer r1 = r6.messageResId
            if (r1 == 0) goto L4f
            int r3 = r1.intValue()
            if (r3 == 0) goto L4f
            android.content.res.Resources r0 = r5.getResources()
            int r3 = r1.intValue()
            java.lang.String r0 = r0.getString(r3)
            java.util.Map r3 = r6.formatterArgs
            if (r3 == 0) goto L4f
            android.content.res.Resources r0 = r5.getResources()
            java.util.Map r6 = r6.formatterArgs
            int r1 = r1.intValue()
            java.lang.String r0 = android.util.PluralsMessageFormatter.format(r0, r6, r1)
        L4f:
            r4.setMessage(r0, r2)
        L52:
            if (r7 == 0) goto L57
            android.text.TextUtils$TruncateAt r4 = android.text.TextUtils.TruncateAt.END
            goto L59
        L57:
            android.text.TextUtils$TruncateAt r4 = android.text.TextUtils.TruncateAt.MARQUEE
        L59:
            r5.setEllipsize(r4)
        L5c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.bouncer.ui.binder.BouncerMessageViewBinder.access$updateView(com.android.keyguard.KeyguardMessageAreaController, com.android.keyguard.BouncerKeyguardMessageArea, com.android.systemui.bouncer.shared.model.Message, boolean):void");
    }
}
