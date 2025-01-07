package androidx.lifecycle.compose;

import androidx.compose.runtime.ProvidableCompositionLocal;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class LocalLifecycleOwnerKt {
    public static final ProvidableCompositionLocal LocalLifecycleOwner;

    /* JADX WARN: Code restructure failed: missing block: B:20:0x002d, code lost:
    
        r1 = r1.invoke(null, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0033, code lost:
    
        if ((r1 instanceof androidx.compose.runtime.ProvidableCompositionLocal) == false) goto L7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0035, code lost:
    
        r1 = (androidx.compose.runtime.ProvidableCompositionLocal) r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0026, code lost:
    
        r1 = null;
     */
    static {
        /*
            r0 = 0
            java.lang.Class<androidx.lifecycle.LifecycleOwner> r1 = androidx.lifecycle.LifecycleOwner.class
            java.lang.ClassLoader r1 = r1.getClassLoader()     // Catch: java.lang.Throwable -> L2b
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)     // Catch: java.lang.Throwable -> L2b
            java.lang.String r2 = "androidx.compose.ui.platform.AndroidCompositionLocals_androidKt"
            java.lang.String r3 = "getLocalLifecycleOwner"
            java.lang.Class r1 = r1.loadClass(r2)     // Catch: java.lang.Throwable -> L2b
            r2 = 0
            java.lang.Class[] r4 = new java.lang.Class[r2]     // Catch: java.lang.Throwable -> L2b
            java.lang.reflect.Method r1 = r1.getMethod(r3, r0)     // Catch: java.lang.Throwable -> L2b
            java.lang.annotation.Annotation[] r3 = r1.getAnnotations()     // Catch: java.lang.Throwable -> L2b
            int r4 = r3.length     // Catch: java.lang.Throwable -> L2b
        L1e:
            if (r2 >= r4) goto L2d
            r5 = r3[r2]     // Catch: java.lang.Throwable -> L2b
            boolean r5 = r5 instanceof kotlin.Deprecated     // Catch: java.lang.Throwable -> L2b
            if (r5 == 0) goto L28
        L26:
            r1 = r0
            goto L3e
        L28:
            int r2 = r2 + 1
            goto L1e
        L2b:
            r1 = move-exception
            goto L38
        L2d:
            java.lang.Object r1 = r1.invoke(r0, r0)     // Catch: java.lang.Throwable -> L2b
            boolean r2 = r1 instanceof androidx.compose.runtime.ProvidableCompositionLocal     // Catch: java.lang.Throwable -> L2b
            if (r2 == 0) goto L26
            androidx.compose.runtime.ProvidableCompositionLocal r1 = (androidx.compose.runtime.ProvidableCompositionLocal) r1     // Catch: java.lang.Throwable -> L2b
            goto L3e
        L38:
            kotlin.Result$Failure r2 = new kotlin.Result$Failure
            r2.<init>(r1)
            r1 = r2
        L3e:
            boolean r2 = r1 instanceof kotlin.Result.Failure
            if (r2 == 0) goto L43
            goto L44
        L43:
            r0 = r1
        L44:
            androidx.compose.runtime.ProvidableCompositionLocal r0 = (androidx.compose.runtime.ProvidableCompositionLocal) r0
            if (r0 != 0) goto L50
            androidx.lifecycle.compose.LocalLifecycleOwnerKt$LocalLifecycleOwner$1$1 r0 = new kotlin.jvm.functions.Function0() { // from class: androidx.lifecycle.compose.LocalLifecycleOwnerKt$LocalLifecycleOwner$1$1
                static {
                    /*
                        androidx.lifecycle.compose.LocalLifecycleOwnerKt$LocalLifecycleOwner$1$1 r0 = new androidx.lifecycle.compose.LocalLifecycleOwnerKt$LocalLifecycleOwner$1$1
                        r0.<init>()
                        
                        // error: 0x0005: SPUT (r0 I:androidx.lifecycle.compose.LocalLifecycleOwnerKt$LocalLifecycleOwner$1$1) androidx.lifecycle.compose.LocalLifecycleOwnerKt$LocalLifecycleOwner$1$1.INSTANCE androidx.lifecycle.compose.LocalLifecycleOwnerKt$LocalLifecycleOwner$1$1
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.lifecycle.compose.LocalLifecycleOwnerKt$LocalLifecycleOwner$1$1.<clinit>():void");
                }

                {
                    /*
                        r1 = this;
                        r0 = 0
                        r1.<init>(r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.lifecycle.compose.LocalLifecycleOwnerKt$LocalLifecycleOwner$1$1.<init>():void");
                }

                @Override // kotlin.jvm.functions.Function0
                public final java.lang.Object invoke() {
                    /*
                        r1 = this;
                        java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
                        java.lang.String r0 = "CompositionLocal LocalLifecycleOwner not present"
                        r1.<init>(r0)
                        throw r1
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.lifecycle.compose.LocalLifecycleOwnerKt$LocalLifecycleOwner$1$1.invoke():java.lang.Object");
                }
            }
            androidx.compose.runtime.StaticProvidableCompositionLocal r1 = new androidx.compose.runtime.StaticProvidableCompositionLocal
            r1.<init>(r0)
            r0 = r1
        L50:
            androidx.lifecycle.compose.LocalLifecycleOwnerKt.LocalLifecycleOwner = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.lifecycle.compose.LocalLifecycleOwnerKt.<clinit>():void");
    }
}
