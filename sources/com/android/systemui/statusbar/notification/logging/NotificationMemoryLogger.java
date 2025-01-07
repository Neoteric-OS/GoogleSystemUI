package com.android.systemui.statusbar.notification.logging;

import android.app.StatsManager;
import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import java.util.List;
import java.util.concurrent.Executor;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationMemoryLogger implements StatsManager.StatsPullAtomCallback {
    public final Executor backgroundExecutor;
    public final CoroutineDispatcher mainDispatcher;
    public final NotifPipeline notificationPipeline;
    public final StatsManager statsManager;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class NotificationMemoryUseAtomBuilder {
        public int bigPictureBitmapCount;
        public int bigPictureObject;
        public int count;
        public int countWithInflatedViews;
        public int customViews;
        public int extenders;
        public int extras;
        public int largeIconBitmapCount;
        public int largeIconObject;
        public int largeIconViews;
        public int smallIconBitmapCount;
        public int smallIconObject;
        public int smallIconViews;
        public int softwareBitmaps;
        public final int style;
        public int styleViews;
        public int systemIconViews;
        public final int uid;

        public NotificationMemoryUseAtomBuilder(int i, int i2) {
            this.uid = i;
            this.style = i2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof NotificationMemoryUseAtomBuilder)) {
                return false;
            }
            NotificationMemoryUseAtomBuilder notificationMemoryUseAtomBuilder = (NotificationMemoryUseAtomBuilder) obj;
            return this.uid == notificationMemoryUseAtomBuilder.uid && this.style == notificationMemoryUseAtomBuilder.style;
        }

        public final int hashCode() {
            return Integer.hashCode(this.style) + (Integer.hashCode(this.uid) * 31);
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("NotificationMemoryUseAtomBuilder(uid=");
            sb.append(this.uid);
            sb.append(", style=");
            return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.style, ")");
        }
    }

    public NotificationMemoryLogger(NotifPipeline notifPipeline, StatsManager statsManager, CoroutineDispatcher coroutineDispatcher, Executor executor) {
        this.notificationPipeline = notifPipeline;
        this.statsManager = statsManager;
        this.mainDispatcher = coroutineDispatcher;
        this.backgroundExecutor = executor;
    }

    public final List getAllNotificationsOnMainThread() {
        return (List) BuildersKt.runBlocking(this.mainDispatcher, new NotificationMemoryLogger$getAllNotificationsOnMainThread$1(this, null));
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int onPullAtom(int r24, java.util.List r25) {
        /*
            r23 = this;
            java.lang.String r1 = "NotificationLogger"
            boolean r2 = android.os.Trace.isEnabled()
            if (r2 == 0) goto Ld
            java.lang.String r0 = "NML#onPullAtom"
            com.android.app.tracing.TraceUtilsKt.beginSlice(r0)
        Ld:
            r0 = 10174(0x27be, float:1.4257E-41)
            r3 = 1
            r4 = r24
            if (r4 == r0) goto L1a
            if (r2 == 0) goto L19
            com.android.app.tracing.TraceUtilsKt.endSlice()
        L19:
            return r3
        L1a:
            java.util.List r0 = r23.getAllNotificationsOnMainThread()     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            kotlin.collections.CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1 r4 = new kotlin.collections.CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            r4.<init>(r0)     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            com.android.systemui.statusbar.notification.logging.NotificationMemoryMeter$notificationMemoryUse$1 r0 = com.android.systemui.statusbar.notification.logging.NotificationMemoryMeter$notificationMemoryUse$1.INSTANCE     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            kotlin.sequences.TransformingSequence r5 = new kotlin.sequences.TransformingSequence     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            r5.<init>(r4, r0)     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            java.util.List r0 = kotlin.sequences.SequencesKt.toList(r5)     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            com.android.systemui.statusbar.notification.logging.NotificationMemoryLogger$onPullAtom$1$notificationMemoryUse$1 r4 = new kotlin.jvm.functions.Function1() { // from class: com.android.systemui.statusbar.notification.logging.NotificationMemoryLogger$onPullAtom$1$notificationMemoryUse$1
                static {
                    /*
                        com.android.systemui.statusbar.notification.logging.NotificationMemoryLogger$onPullAtom$1$notificationMemoryUse$1 r0 = new com.android.systemui.statusbar.notification.logging.NotificationMemoryLogger$onPullAtom$1$notificationMemoryUse$1
                        r0.<init>()
                        
                        // error: 0x0005: SPUT 
  (r0 I:com.android.systemui.statusbar.notification.logging.NotificationMemoryLogger$onPullAtom$1$notificationMemoryUse$1)
 com.android.systemui.statusbar.notification.logging.NotificationMemoryLogger$onPullAtom$1$notificationMemoryUse$1.INSTANCE com.android.systemui.statusbar.notification.logging.NotificationMemoryLogger$onPullAtom$1$notificationMemoryUse$1
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.logging.NotificationMemoryLogger$onPullAtom$1$notificationMemoryUse$1.<clinit>():void");
                }

                {
                    /*
                        r1 = this;
                        r0 = 1
                        r1.<init>(r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.logging.NotificationMemoryLogger$onPullAtom$1$notificationMemoryUse$1.<init>():void");
                }

                @Override // kotlin.jvm.functions.Function1
                public final java.lang.Object invoke(java.lang.Object r1) {
                    /*
                        r0 = this;
                        com.android.systemui.statusbar.notification.logging.NotificationMemoryUsage r1 = (com.android.systemui.statusbar.notification.logging.NotificationMemoryUsage) r1
                        java.lang.String r0 = r1.packageName
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.logging.NotificationMemoryLogger$onPullAtom$1$notificationMemoryUse$1.invoke(java.lang.Object):java.lang.Object");
                }
            }     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            com.android.systemui.statusbar.notification.logging.NotificationMemoryLogger$onPullAtom$1$notificationMemoryUse$2 r5 = new kotlin.jvm.functions.Function1() { // from class: com.android.systemui.statusbar.notification.logging.NotificationMemoryLogger$onPullAtom$1$notificationMemoryUse$2
                static {
                    /*
                        com.android.systemui.statusbar.notification.logging.NotificationMemoryLogger$onPullAtom$1$notificationMemoryUse$2 r0 = new com.android.systemui.statusbar.notification.logging.NotificationMemoryLogger$onPullAtom$1$notificationMemoryUse$2
                        r0.<init>()
                        
                        // error: 0x0005: SPUT 
  (r0 I:com.android.systemui.statusbar.notification.logging.NotificationMemoryLogger$onPullAtom$1$notificationMemoryUse$2)
 com.android.systemui.statusbar.notification.logging.NotificationMemoryLogger$onPullAtom$1$notificationMemoryUse$2.INSTANCE com.android.systemui.statusbar.notification.logging.NotificationMemoryLogger$onPullAtom$1$notificationMemoryUse$2
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.logging.NotificationMemoryLogger$onPullAtom$1$notificationMemoryUse$2.<clinit>():void");
                }

                {
                    /*
                        r1 = this;
                        r0 = 1
                        r1.<init>(r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.logging.NotificationMemoryLogger$onPullAtom$1$notificationMemoryUse$2.<init>():void");
                }

                @Override // kotlin.jvm.functions.Function1
                public final java.lang.Object invoke(java.lang.Object r1) {
                    /*
                        r0 = this;
                        com.android.systemui.statusbar.notification.logging.NotificationMemoryUsage r1 = (com.android.systemui.statusbar.notification.logging.NotificationMemoryUsage) r1
                        com.android.systemui.statusbar.notification.logging.NotificationObjectUsage r0 = r1.objectUsage
                        int r0 = r0.style
                        java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.logging.NotificationMemoryLogger$onPullAtom$1$notificationMemoryUse$2.invoke(java.lang.Object):java.lang.Object");
                }
            }     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            com.android.systemui.statusbar.notification.logging.NotificationMemoryLogger$onPullAtom$1$notificationMemoryUse$3 r6 = new kotlin.jvm.functions.Function1() { // from class: com.android.systemui.statusbar.notification.logging.NotificationMemoryLogger$onPullAtom$1$notificationMemoryUse$3
                static {
                    /*
                        com.android.systemui.statusbar.notification.logging.NotificationMemoryLogger$onPullAtom$1$notificationMemoryUse$3 r0 = new com.android.systemui.statusbar.notification.logging.NotificationMemoryLogger$onPullAtom$1$notificationMemoryUse$3
                        r0.<init>()
                        
                        // error: 0x0005: SPUT 
  (r0 I:com.android.systemui.statusbar.notification.logging.NotificationMemoryLogger$onPullAtom$1$notificationMemoryUse$3)
 com.android.systemui.statusbar.notification.logging.NotificationMemoryLogger$onPullAtom$1$notificationMemoryUse$3.INSTANCE com.android.systemui.statusbar.notification.logging.NotificationMemoryLogger$onPullAtom$1$notificationMemoryUse$3
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.logging.NotificationMemoryLogger$onPullAtom$1$notificationMemoryUse$3.<clinit>():void");
                }

                {
                    /*
                        r1 = this;
                        r0 = 1
                        r1.<init>(r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.logging.NotificationMemoryLogger$onPullAtom$1$notificationMemoryUse$3.<init>():void");
                }

                @Override // kotlin.jvm.functions.Function1
                public final java.lang.Object invoke(java.lang.Object r1) {
                    /*
                        r0 = this;
                        com.android.systemui.statusbar.notification.logging.NotificationMemoryUsage r1 = (com.android.systemui.statusbar.notification.logging.NotificationMemoryUsage) r1
                        java.lang.String r0 = r1.notificationKey
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.logging.NotificationMemoryLogger$onPullAtom$1$notificationMemoryUse$3.invoke(java.lang.Object):java.lang.Object");
                }
            }     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            kotlin.jvm.functions.Function1[] r4 = new kotlin.jvm.functions.Function1[]{r4, r5, r6}     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            kotlin.comparisons.ComparisonsKt__ComparisonsKt$$ExternalSyntheticLambda0 r4 = kotlin.comparisons.ComparisonsKt___ComparisonsJvmKt.compareBy(r4)     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            java.util.List r0 = kotlin.collections.CollectionsKt.sortedWith(r0, r4)     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            java.util.Map r0 = com.android.systemui.statusbar.notification.logging.NotificationMemoryLoggerKt.aggregateMemoryUsageData(r0)     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            java.util.Set r0 = r0.entrySet()     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            java.util.Iterator r0 = r0.iterator()     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
        L4c:
            boolean r4 = r0.hasNext()     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            if (r4 == 0) goto Lbf
            java.lang.Object r4 = r0.next()     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            java.lang.Object r4 = r4.getValue()     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            com.android.systemui.statusbar.notification.logging.NotificationMemoryLogger$NotificationMemoryUseAtomBuilder r4 = (com.android.systemui.statusbar.notification.logging.NotificationMemoryLogger.NotificationMemoryUseAtomBuilder) r4     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            int r5 = r4.uid     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            int r6 = r4.style     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            int r7 = r4.count     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            int r8 = r4.countWithInflatedViews     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            int r9 = r4.smallIconObject     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            int r9 = com.android.systemui.statusbar.notification.logging.NotificationMemoryLoggerKt.access$toKb(r9)     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            int r10 = r4.smallIconBitmapCount     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            int r11 = r4.largeIconObject     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            int r11 = com.android.systemui.statusbar.notification.logging.NotificationMemoryLoggerKt.access$toKb(r11)     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            int r12 = r4.largeIconBitmapCount     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            int r13 = r4.bigPictureObject     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            int r13 = com.android.systemui.statusbar.notification.logging.NotificationMemoryLoggerKt.access$toKb(r13)     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            int r14 = r4.bigPictureBitmapCount     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            int r15 = r4.extras     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            int r15 = com.android.systemui.statusbar.notification.logging.NotificationMemoryLoggerKt.access$toKb(r15)     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            int r3 = r4.extenders     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            int r16 = com.android.systemui.statusbar.notification.logging.NotificationMemoryLoggerKt.access$toKb(r3)     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            int r3 = r4.smallIconViews     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            int r17 = com.android.systemui.statusbar.notification.logging.NotificationMemoryLoggerKt.access$toKb(r3)     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            int r3 = r4.largeIconViews     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            int r18 = com.android.systemui.statusbar.notification.logging.NotificationMemoryLoggerKt.access$toKb(r3)     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            int r3 = r4.systemIconViews     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            int r19 = com.android.systemui.statusbar.notification.logging.NotificationMemoryLoggerKt.access$toKb(r3)     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            int r3 = r4.styleViews     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            int r20 = com.android.systemui.statusbar.notification.logging.NotificationMemoryLoggerKt.access$toKb(r3)     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            int r3 = r4.customViews     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            int r21 = com.android.systemui.statusbar.notification.logging.NotificationMemoryLoggerKt.access$toKb(r3)     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            int r3 = r4.softwareBitmaps     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            int r22 = com.android.systemui.statusbar.notification.logging.NotificationMemoryLoggerKt.access$toKb(r3)     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            android.util.StatsEvent r3 = com.android.systemui.shared.system.SysUiStatsLog.buildStatsEvent(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22)     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            r4 = r25
            r4.add(r3)     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb java.lang.InterruptedException -> Lbd
            r3 = 1
            goto L4c
        Lb9:
            r0 = move-exception
            goto Ld8
        Lbb:
            r0 = move-exception
            goto Lc6
        Lbd:
            r0 = move-exception
            goto Lcc
        Lbf:
            if (r2 == 0) goto Lc4
            com.android.app.tracing.TraceUtilsKt.endSlice()
        Lc4:
            r0 = 0
            return r0
        Lc6:
            java.lang.String r3 = "Failed to measure notification memory."
            android.util.Log.wtf(r1, r3, r0)     // Catch: java.lang.Throwable -> Lb9
            goto Ld1
        Lcc:
            java.lang.String r3 = "Timed out when measuring notification memory."
            android.util.Log.w(r1, r3, r0)     // Catch: java.lang.Throwable -> Lb9
        Ld1:
            if (r2 == 0) goto Ld6
            com.android.app.tracing.TraceUtilsKt.endSlice()
        Ld6:
            r1 = 1
            return r1
        Ld8:
            if (r2 == 0) goto Ldd
            com.android.app.tracing.TraceUtilsKt.endSlice()
        Ldd:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.logging.NotificationMemoryLogger.onPullAtom(int, java.util.List):int");
    }
}
