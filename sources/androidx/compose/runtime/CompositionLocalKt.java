package androidx.compose.runtime;

import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class CompositionLocalKt {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v4, types: [androidx.compose.runtime.PersistentCompositionLocalMap, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v0, types: [kotlin.jvm.functions.Function2] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void CompositionLocalProvider(final androidx.compose.runtime.ProvidedValue[] r7, final kotlin.jvm.functions.Function2 r8, androidx.compose.runtime.Composer r9, final int r10) {
        /*
            androidx.compose.runtime.ComposerImpl r9 = (androidx.compose.runtime.ComposerImpl) r9
            r0 = -1390796515(0xffffffffad1a211d, float:-8.761239E-12)
            r9.startRestartGroup(r0)
            androidx.compose.runtime.OpaqueKey r0 = androidx.compose.runtime.ComposerKt.invocation
            androidx.compose.runtime.PersistentCompositionLocalMap r0 = r9.currentCompositionLocalScope()
            androidx.compose.runtime.OpaqueKey r1 = androidx.compose.runtime.ComposerKt.provider
            r2 = 201(0xc9, float:2.82E-43)
            r9.startGroup(r2, r1)
            boolean r1 = r9.inserting
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L29
            androidx.compose.runtime.internal.PersistentCompositionLocalHashMap r1 = androidx.compose.runtime.internal.PersistentCompositionLocalHashMap.Empty
            androidx.compose.runtime.internal.PersistentCompositionLocalHashMap r1 = androidx.compose.runtime.CompositionLocalMapKt.updateCompositionMap(r7, r0, r1)
            androidx.compose.runtime.internal.PersistentCompositionLocalHashMap r0 = r9.updateProviderMapGroup(r0, r1)
            r9.writerHasAProvider = r2
        L27:
            r1 = r3
            goto L6e
        L29:
            androidx.compose.runtime.SlotReader r1 = r9.reader
            int r4 = r1.currentGroup
            java.lang.Object r1 = r1.groupGet(r4, r3)
            androidx.compose.runtime.PersistentCompositionLocalMap r1 = (androidx.compose.runtime.PersistentCompositionLocalMap) r1
            androidx.compose.runtime.SlotReader r4 = r9.reader
            int r5 = r4.currentGroup
            java.lang.Object r4 = r4.groupGet(r5, r2)
            androidx.compose.runtime.PersistentCompositionLocalMap r4 = (androidx.compose.runtime.PersistentCompositionLocalMap) r4
            androidx.compose.runtime.internal.PersistentCompositionLocalHashMap r5 = androidx.compose.runtime.CompositionLocalMapKt.updateCompositionMap(r7, r0, r4)
            boolean r6 = r9.getSkipping()
            if (r6 == 0) goto L5f
            boolean r6 = r9.reusing
            if (r6 != 0) goto L5f
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L52
            goto L5f
        L52:
            int r0 = r9.groupNodeCount
            androidx.compose.runtime.SlotReader r4 = r9.reader
            int r4 = r4.skipGroup()
            int r4 = r4 + r0
            r9.groupNodeCount = r4
            r0 = r1
            goto L27
        L5f:
            androidx.compose.runtime.internal.PersistentCompositionLocalHashMap r0 = r9.updateProviderMapGroup(r0, r5)
            boolean r4 = r9.reusing
            if (r4 != 0) goto L6d
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r1 != 0) goto L27
        L6d:
            r1 = r2
        L6e:
            if (r1 == 0) goto L77
            boolean r4 = r9.inserting
            if (r4 != 0) goto L77
            r9.recordProviderUpdate(r0)
        L77:
            boolean r4 = r9.providersInvalid
            androidx.compose.runtime.IntStack r5 = r9.providersInvalidStack
            r5.push(r4)
            r9.providersInvalid = r1
            r9.providerCache = r0
            androidx.compose.runtime.OpaqueKey r1 = androidx.compose.runtime.ComposerKt.compositionLocalMap
            r4 = 202(0xca, float:2.83E-43)
            r9.m256startBaiHCIY(r4, r3, r1, r0)
            int r0 = r10 >> 3
            r0 = r0 & 14
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r8.invoke(r9, r0)
            r9.end(r3)
            r9.end(r3)
            int r0 = r5.pop()
            if (r0 == 0) goto La1
            goto La2
        La1:
            r2 = r3
        La2:
            r9.providersInvalid = r2
            r0 = 0
            r9.providerCache = r0
            androidx.compose.runtime.RecomposeScopeImpl r9 = r9.endRestartGroup()
            if (r9 == 0) goto Lb4
            androidx.compose.runtime.CompositionLocalKt$CompositionLocalProvider$1 r0 = new androidx.compose.runtime.CompositionLocalKt$CompositionLocalProvider$1
            r0.<init>()
            r9.block = r0
        Lb4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.CompositionLocalKt.CompositionLocalProvider(androidx.compose.runtime.ProvidedValue[], kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int):void");
    }

    public static final StaticProvidableCompositionLocal staticCompositionLocalOf(Function0 function0) {
        return new StaticProvidableCompositionLocal(function0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0043, code lost:
    
        if (r1 == false) goto L15;
     */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void CompositionLocalProvider(final androidx.compose.runtime.ProvidedValue r10, final kotlin.jvm.functions.Function2 r11, androidx.compose.runtime.Composer r12, final int r13) {
        /*
            Method dump skipped, instructions count: 239
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.CompositionLocalKt.CompositionLocalProvider(androidx.compose.runtime.ProvidedValue, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int):void");
    }
}
