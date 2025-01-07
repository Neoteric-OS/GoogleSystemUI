package com.google.common.util.concurrent;

import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import com.android.settingslib.notification.modes.ZenIconLoader$$ExternalSyntheticLambda3;
import com.google.common.util.concurrent.FluentFuture;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AbstractCatchingFuture$CatchingFuture extends FluentFuture.TrustedFuture implements Runnable {
    public Class exceptionType;
    public ZenIconLoader$$ExternalSyntheticLambda3 fallback;
    public FluentFuture inputFuture;

    @Override // com.google.common.util.concurrent.AbstractFuture
    public final void afterDone() {
        maybePropagateCancellationTo(this.inputFuture);
        this.inputFuture = null;
        this.exceptionType = null;
        this.fallback = null;
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public final String pendingToString() {
        String str;
        FluentFuture fluentFuture = this.inputFuture;
        Class cls = this.exceptionType;
        ZenIconLoader$$ExternalSyntheticLambda3 zenIconLoader$$ExternalSyntheticLambda3 = this.fallback;
        String pendingToString = super.pendingToString();
        if (fluentFuture != null) {
            str = "inputFuture=[" + fluentFuture + "], ";
        } else {
            str = "";
        }
        if (cls == null || zenIconLoader$$ExternalSyntheticLambda3 == null) {
            if (pendingToString != null) {
                return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m(str, pendingToString);
            }
            return null;
        }
        return str + "exceptionType=[" + cls + "], fallback=[" + zenIconLoader$$ExternalSyntheticLambda3 + "]";
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x006d  */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void run() {
        /*
            r8 = this;
            com.google.common.util.concurrent.FluentFuture r0 = r8.inputFuture
            java.lang.Class r1 = r8.exceptionType
            com.android.settingslib.notification.modes.ZenIconLoader$$ExternalSyntheticLambda3 r2 = r8.fallback
            r3 = 0
            r4 = 1
            if (r0 != 0) goto Lc
            r5 = r4
            goto Ld
        Lc:
            r5 = r3
        Ld:
            if (r1 != 0) goto L11
            r6 = r4
            goto L12
        L11:
            r6 = r3
        L12:
            r5 = r5 | r6
            if (r2 != 0) goto L16
            r3 = r4
        L16:
            r3 = r3 | r5
            if (r3 != 0) goto L9d
            java.lang.Object r3 = r8.value
            boolean r3 = r3 instanceof com.google.common.util.concurrent.AbstractFuture.Cancellation
            if (r3 == 0) goto L21
            goto L9d
        L21:
            r3 = 0
            r8.inputFuture = r3
            if (r0 == 0) goto L2b
            java.lang.Throwable r4 = r0.tryInternalFastPathGetFailure()     // Catch: java.lang.Throwable -> L33 java.util.concurrent.ExecutionException -> L36
            goto L2c
        L2b:
            r4 = r3
        L2c:
            if (r4 != 0) goto L34
            java.lang.Object r5 = com.google.common.util.concurrent.Futures.getDone(r0)     // Catch: java.lang.Throwable -> L33 java.util.concurrent.ExecutionException -> L36
            goto L67
        L33:
            r4 = move-exception
        L34:
            r5 = r3
            goto L67
        L36:
            r4 = move-exception
            java.lang.Throwable r5 = r4.getCause()
            if (r5 != 0) goto L65
            java.lang.NullPointerException r5 = new java.lang.NullPointerException
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "Future type "
            r6.<init>(r7)
            java.lang.Class r7 = r0.getClass()
            r6.append(r7)
            java.lang.String r7 = " threw "
            r6.append(r7)
            java.lang.Class r4 = r4.getClass()
            r6.append(r4)
            java.lang.String r4 = " without a cause"
            r6.append(r4)
            java.lang.String r4 = r6.toString()
            r5.<init>(r4)
        L65:
            r4 = r5
            goto L34
        L67:
            if (r4 != 0) goto L6d
            r8.set(r5)
            return
        L6d:
            boolean r1 = r1.isInstance(r4)
            if (r1 != 0) goto L77
            r8.setFuture(r0)
            return
        L77:
            java.lang.Object r0 = r2.apply(r4)     // Catch: java.lang.Throwable -> L83
            r8.exceptionType = r3
            r8.fallback = r3
            r8.set(r0)
            return
        L83:
            r0 = move-exception
            boolean r1 = r0 instanceof java.lang.InterruptedException     // Catch: java.lang.Throwable -> L97
            if (r1 == 0) goto L8f
            java.lang.Thread r1 = java.lang.Thread.currentThread()     // Catch: java.lang.Throwable -> L97
            r1.interrupt()     // Catch: java.lang.Throwable -> L97
        L8f:
            r8.setException(r0)     // Catch: java.lang.Throwable -> L97
            r8.exceptionType = r3
            r8.fallback = r3
            return
        L97:
            r0 = move-exception
            r8.exceptionType = r3
            r8.fallback = r3
            throw r0
        L9d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.AbstractCatchingFuture$CatchingFuture.run():void");
    }
}
