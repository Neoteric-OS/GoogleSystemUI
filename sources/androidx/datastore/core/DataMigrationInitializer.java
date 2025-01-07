package androidx.datastore.core;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class DataMigrationInitializer {
    public static final Companion Companion = new Companion();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
        /* JADX WARN: Removed duplicated region for block: B:16:0x0071  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x0097  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x009a  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x0047  */
        /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x0088 -> B:13:0x006b). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:29:0x008b -> B:13:0x006b). Please report as a decompilation issue!!! */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public static final java.lang.Object access$runMigrations(androidx.datastore.core.DataMigrationInitializer.Companion r5, java.util.List r6, androidx.datastore.core.DataStoreImpl$InitDataStore$doRun$initData$1$api$1 r7, kotlin.coroutines.jvm.internal.ContinuationImpl r8) {
            /*
                r5.getClass()
                boolean r0 = r8 instanceof androidx.datastore.core.DataMigrationInitializer$Companion$runMigrations$1
                if (r0 == 0) goto L16
                r0 = r8
                androidx.datastore.core.DataMigrationInitializer$Companion$runMigrations$1 r0 = (androidx.datastore.core.DataMigrationInitializer$Companion$runMigrations$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L16
                int r1 = r1 - r2
                r0.label = r1
                goto L1b
            L16:
                androidx.datastore.core.DataMigrationInitializer$Companion$runMigrations$1 r0 = new androidx.datastore.core.DataMigrationInitializer$Companion$runMigrations$1
                r0.<init>(r5, r8)
            L1b:
                java.lang.Object r5 = r0.result
                kotlin.coroutines.intrinsics.CoroutineSingletons r8 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r1 = r0.label
                r2 = 2
                r3 = 1
                if (r1 == 0) goto L47
                if (r1 == r3) goto L3f
                if (r1 != r2) goto L37
                java.lang.Object r6 = r0.L$1
                java.util.Iterator r6 = (java.util.Iterator) r6
                java.lang.Object r7 = r0.L$0
                kotlin.jvm.internal.Ref$ObjectRef r7 = (kotlin.jvm.internal.Ref$ObjectRef) r7
                kotlin.ResultKt.throwOnFailure(r5)     // Catch: java.lang.Throwable -> L35
                goto L6b
            L35:
                r5 = move-exception
                goto L84
            L37:
                java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                r5.<init>(r6)
                throw r5
            L3f:
                java.lang.Object r6 = r0.L$0
                java.util.List r6 = (java.util.List) r6
                kotlin.ResultKt.throwOnFailure(r5)
                goto L61
            L47:
                kotlin.ResultKt.throwOnFailure(r5)
                java.util.ArrayList r5 = new java.util.ArrayList
                r5.<init>()
                androidx.datastore.core.DataMigrationInitializer$Companion$runMigrations$2 r1 = new androidx.datastore.core.DataMigrationInitializer$Companion$runMigrations$2
                r4 = 0
                r1.<init>(r6, r5, r4)
                r0.L$0 = r5
                r0.label = r3
                java.lang.Object r6 = r7.updateData(r1, r0)
                if (r6 != r8) goto L60
                goto L99
            L60:
                r6 = r5
            L61:
                kotlin.jvm.internal.Ref$ObjectRef r5 = new kotlin.jvm.internal.Ref$ObjectRef
                r5.<init>()
                java.util.Iterator r6 = r6.iterator()
                r7 = r5
            L6b:
                boolean r5 = r6.hasNext()
                if (r5 == 0) goto L91
                java.lang.Object r5 = r6.next()
                kotlin.jvm.functions.Function1 r5 = (kotlin.jvm.functions.Function1) r5
                r0.L$0 = r7     // Catch: java.lang.Throwable -> L35
                r0.L$1 = r6     // Catch: java.lang.Throwable -> L35
                r0.label = r2     // Catch: java.lang.Throwable -> L35
                java.lang.Object r5 = r5.invoke(r0)     // Catch: java.lang.Throwable -> L35
                if (r5 != r8) goto L6b
                goto L99
            L84:
                java.lang.Object r1 = r7.element
                if (r1 != 0) goto L8b
                r7.element = r5
                goto L6b
            L8b:
                java.lang.Throwable r1 = (java.lang.Throwable) r1
                kotlin.ExceptionsKt.addSuppressed(r1, r5)
                goto L6b
            L91:
                java.lang.Object r5 = r7.element
                java.lang.Throwable r5 = (java.lang.Throwable) r5
                if (r5 != 0) goto L9a
                kotlin.Unit r8 = kotlin.Unit.INSTANCE
            L99:
                return r8
            L9a:
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.core.DataMigrationInitializer.Companion.access$runMigrations(androidx.datastore.core.DataMigrationInitializer$Companion, java.util.List, androidx.datastore.core.DataStoreImpl$InitDataStore$doRun$initData$1$api$1, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
        }
    }
}
