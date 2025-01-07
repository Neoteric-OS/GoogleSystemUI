package com.android.systemui.media.nearby;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.media.INearbyMediaDevicesProvider;
import android.media.INearbyMediaDevicesUpdateCallback;
import android.os.IBinder;
import com.android.systemui.CoreStartable;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.CommandQueue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class NearbyMediaDevicesManager implements CoreStartable {
    public final CommandQueue commandQueue;
    public final NearbyMediaDevicesLogger logger;
    public final List providers = new ArrayList();
    public final List activeCallbacks = new ArrayList();
    public final NearbyMediaDevicesManager$commandQueueCallbacks$1 commandQueueCallbacks = new CommandQueue.Callbacks() { // from class: com.android.systemui.media.nearby.NearbyMediaDevicesManager$commandQueueCallbacks$1
        @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
        public final void registerNearbyMediaDevicesProvider(INearbyMediaDevicesProvider iNearbyMediaDevicesProvider) {
            NearbyMediaDevicesManager nearbyMediaDevicesManager = NearbyMediaDevicesManager.this;
            if (nearbyMediaDevicesManager.providers.contains(iNearbyMediaDevicesProvider)) {
                return;
            }
            Iterator it = nearbyMediaDevicesManager.activeCallbacks.iterator();
            while (it.hasNext()) {
                iNearbyMediaDevicesProvider.registerNearbyDevicesCallback((INearbyMediaDevicesUpdateCallback) it.next());
            }
            nearbyMediaDevicesManager.providers.add(iNearbyMediaDevicesProvider);
            NearbyMediaDevicesLogger nearbyMediaDevicesLogger = nearbyMediaDevicesManager.logger;
            int size = ((ArrayList) nearbyMediaDevicesManager.providers).size();
            nearbyMediaDevicesLogger.getClass();
            LogLevel logLevel = LogLevel.DEBUG;
            NearbyMediaDevicesLogger$logProviderRegistered$2 nearbyMediaDevicesLogger$logProviderRegistered$2 = new Function1() { // from class: com.android.systemui.media.nearby.NearbyMediaDevicesLogger$logProviderRegistered$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AnnotationValue$1$$ExternalSyntheticOutline0.m(((LogMessage) obj).getInt1(), "Provider registered; total providers = ");
                }
            };
            LogBuffer logBuffer = nearbyMediaDevicesLogger.buffer;
            LogMessage obtain = logBuffer.obtain("NearbyMediaDevices", logLevel, nearbyMediaDevicesLogger$logProviderRegistered$2, null);
            ((LogMessageImpl) obtain).int1 = size;
            logBuffer.commit(obtain);
            iNearbyMediaDevicesProvider.asBinder().linkToDeath(nearbyMediaDevicesManager.deathRecipient, 0);
        }

        @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
        public final void unregisterNearbyMediaDevicesProvider(INearbyMediaDevicesProvider iNearbyMediaDevicesProvider) {
            NearbyMediaDevicesManager nearbyMediaDevicesManager = NearbyMediaDevicesManager.this;
            if (nearbyMediaDevicesManager.providers.remove(iNearbyMediaDevicesProvider)) {
                NearbyMediaDevicesLogger nearbyMediaDevicesLogger = nearbyMediaDevicesManager.logger;
                int size = ((ArrayList) nearbyMediaDevicesManager.providers).size();
                nearbyMediaDevicesLogger.getClass();
                LogLevel logLevel = LogLevel.DEBUG;
                NearbyMediaDevicesLogger$logProviderUnregistered$2 nearbyMediaDevicesLogger$logProviderUnregistered$2 = new Function1() { // from class: com.android.systemui.media.nearby.NearbyMediaDevicesLogger$logProviderUnregistered$2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return AnnotationValue$1$$ExternalSyntheticOutline0.m(((LogMessage) obj).getInt1(), "Provider unregistered; total providers = ");
                    }
                };
                LogBuffer logBuffer = nearbyMediaDevicesLogger.buffer;
                LogMessage obtain = logBuffer.obtain("NearbyMediaDevices", logLevel, nearbyMediaDevicesLogger$logProviderUnregistered$2, null);
                ((LogMessageImpl) obtain).int1 = size;
                logBuffer.commit(obtain);
            }
        }
    };
    public final NearbyMediaDevicesManager$deathRecipient$1 deathRecipient = new IBinder.DeathRecipient() { // from class: com.android.systemui.media.nearby.NearbyMediaDevicesManager$deathRecipient$1
        @Override // android.os.IBinder.DeathRecipient
        public final void binderDied() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:11:0x0026, code lost:
        
            r5.providers.remove(r1);
            r6 = r5.logger;
            r5 = ((java.util.ArrayList) r5.providers).size();
            r6.getClass();
            r1 = com.android.systemui.log.core.LogLevel.DEBUG;
            r2 = new kotlin.jvm.functions.Function1() { // from class: com.android.systemui.media.nearby.NearbyMediaDevicesLogger$logProviderBinderDied$2
                static {
                    /*
                        com.android.systemui.media.nearby.NearbyMediaDevicesLogger$logProviderBinderDied$2 r0 = new com.android.systemui.media.nearby.NearbyMediaDevicesLogger$logProviderBinderDied$2
                        r0.<init>()
                        
                        // error: 0x0005: SPUT (r0 I:com.android.systemui.media.nearby.NearbyMediaDevicesLogger$logProviderBinderDied$2) com.android.systemui.media.nearby.NearbyMediaDevicesLogger$logProviderBinderDied$2.INSTANCE com.android.systemui.media.nearby.NearbyMediaDevicesLogger$logProviderBinderDied$2
                        return
                    *\/
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.media.nearby.NearbyMediaDevicesLogger$logProviderBinderDied$2.<clinit>():void");
                }
        
                {
                    /*
                        r1 = this;
                        r0 = 1
                        r1.<init>(r0)
                        return
                    *\/
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.media.nearby.NearbyMediaDevicesLogger$logProviderBinderDied$2.<init>():void");
                }
        
                @Override // kotlin.jvm.functions.Function1
                public final java.lang.Object invoke(java.lang.Object r1) {
                    /*
                        r0 = this;
                        com.android.systemui.log.core.LogMessage r1 = (com.android.systemui.log.core.LogMessage) r1
                        int r0 = r1.getInt1()
                        java.lang.String r1 = "Provider binder died; total providers = "
                        java.lang.String r0 = android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0.m(r0, r1)
                        return r0
                    *\/
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.media.nearby.NearbyMediaDevicesLogger$logProviderBinderDied$2.invoke(java.lang.Object):java.lang.Object");
                }
            };
            r6 = r6.buffer;
            r1 = r6.obtain("NearbyMediaDevices", r1, r2, null);
            ((com.android.systemui.log.LogMessageImpl) r1).int1 = r5;
            r6.commit(r1);
         */
        @Override // android.os.IBinder.DeathRecipient
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void binderDied(android.os.IBinder r6) {
            /*
                r5 = this;
                com.android.systemui.media.nearby.NearbyMediaDevicesManager r5 = com.android.systemui.media.nearby.NearbyMediaDevicesManager.this
                java.util.List r0 = r5.providers
                monitor-enter(r0)
                java.util.List r1 = r5.providers     // Catch: java.lang.Throwable -> L4e
                java.util.ArrayList r1 = (java.util.ArrayList) r1     // Catch: java.lang.Throwable -> L4e
                int r1 = r1.size()     // Catch: java.lang.Throwable -> L4e
                int r1 = r1 + (-1)
            Lf:
                r2 = -1
                if (r2 >= r1) goto L53
                java.util.List r2 = r5.providers     // Catch: java.lang.Throwable -> L4e
                java.util.ArrayList r2 = (java.util.ArrayList) r2     // Catch: java.lang.Throwable -> L4e
                java.lang.Object r2 = r2.get(r1)     // Catch: java.lang.Throwable -> L4e
                android.media.INearbyMediaDevicesProvider r2 = (android.media.INearbyMediaDevicesProvider) r2     // Catch: java.lang.Throwable -> L4e
                android.os.IBinder r2 = r2.asBinder()     // Catch: java.lang.Throwable -> L4e
                boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r6)     // Catch: java.lang.Throwable -> L4e
                if (r2 == 0) goto L50
                java.util.List r6 = r5.providers     // Catch: java.lang.Throwable -> L4e
                r6.remove(r1)     // Catch: java.lang.Throwable -> L4e
                com.android.systemui.media.nearby.NearbyMediaDevicesLogger r6 = r5.logger     // Catch: java.lang.Throwable -> L4e
                java.util.List r5 = r5.providers     // Catch: java.lang.Throwable -> L4e
                java.util.ArrayList r5 = (java.util.ArrayList) r5     // Catch: java.lang.Throwable -> L4e
                int r5 = r5.size()     // Catch: java.lang.Throwable -> L4e
                r6.getClass()     // Catch: java.lang.Throwable -> L4e
                com.android.systemui.log.core.LogLevel r1 = com.android.systemui.log.core.LogLevel.DEBUG     // Catch: java.lang.Throwable -> L4e
                com.android.systemui.media.nearby.NearbyMediaDevicesLogger$logProviderBinderDied$2 r2 = com.android.systemui.media.nearby.NearbyMediaDevicesLogger$logProviderBinderDied$2.INSTANCE     // Catch: java.lang.Throwable -> L4e
                com.android.systemui.log.LogBuffer r6 = r6.buffer     // Catch: java.lang.Throwable -> L4e
                java.lang.String r3 = "NearbyMediaDevices"
                r4 = 0
                com.android.systemui.log.core.LogMessage r1 = r6.obtain(r3, r1, r2, r4)     // Catch: java.lang.Throwable -> L4e
                r2 = r1
                com.android.systemui.log.LogMessageImpl r2 = (com.android.systemui.log.LogMessageImpl) r2     // Catch: java.lang.Throwable -> L4e
                r2.int1 = r5     // Catch: java.lang.Throwable -> L4e
                r6.commit(r1)     // Catch: java.lang.Throwable -> L4e
                goto L53
            L4e:
                r5 = move-exception
                goto L55
            L50:
                int r1 = r1 + (-1)
                goto Lf
            L53:
                monitor-exit(r0)
                return
            L55:
                monitor-exit(r0)
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.media.nearby.NearbyMediaDevicesManager$deathRecipient$1.binderDied(android.os.IBinder):void");
        }
    };

    /* JADX WARN: Type inference failed for: r1v3, types: [com.android.systemui.media.nearby.NearbyMediaDevicesManager$commandQueueCallbacks$1] */
    /* JADX WARN: Type inference failed for: r1v4, types: [com.android.systemui.media.nearby.NearbyMediaDevicesManager$deathRecipient$1] */
    public NearbyMediaDevicesManager(CommandQueue commandQueue, NearbyMediaDevicesLogger nearbyMediaDevicesLogger) {
        this.commandQueue = commandQueue;
        this.logger = nearbyMediaDevicesLogger;
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        this.commandQueue.addCallback((CommandQueue.Callbacks) this.commandQueueCallbacks);
    }
}
