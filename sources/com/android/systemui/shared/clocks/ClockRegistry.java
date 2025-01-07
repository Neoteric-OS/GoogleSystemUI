package com.android.systemui.shared.clocks;

import android.app.ActivityManager;
import android.app.UserSwitchObserver;
import android.content.Context;
import android.database.ContentObserver;
import android.provider.Settings;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import androidx.constraintlayout.motion.widget.MotionLayout$$ExternalSyntheticOutline0;
import androidx.room.TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.log.core.LogcatOnlyMessageBuffer;
import com.android.systemui.log.core.Logger;
import com.android.systemui.log.core.MessageBuffer;
import com.android.systemui.plugins.Plugin;
import com.android.systemui.plugins.PluginLifecycleManager;
import com.android.systemui.plugins.PluginListener;
import com.android.systemui.plugins.PluginManager;
import com.android.systemui.plugins.clocks.ClockController;
import com.android.systemui.plugins.clocks.ClockMessageBuffers;
import com.android.systemui.plugins.clocks.ClockMetadata;
import com.android.systemui.plugins.clocks.ClockProvider;
import com.android.systemui.plugins.clocks.ClockProviderPlugin;
import com.android.systemui.plugins.clocks.ClockSettings;
import com.android.systemui.shared.clocks.ClockRegistry;
import com.android.systemui.util.Assert;
import com.android.systemui.util.ThreadAssert;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$BooleanRef;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ClockRegistry {
    public final String TAG;

    /* renamed from: assert, reason: not valid java name */
    public final ThreadAssert f43assert;
    public final ConcurrentHashMap availableClocks;
    public final CoroutineDispatcher bgDispatcher;
    public final ClockMessageBuffers clockBuffers;
    public final List clockChangeListeners;
    public final Context context;
    public final String fallbackClockId;
    public final AtomicBoolean isClockChanged;
    public final AtomicBoolean isClockListChanged;
    public final boolean isEnabled;
    public final AtomicBoolean isQueued;
    public boolean isRegistered;
    public final Logger logger;
    public final CoroutineDispatcher mainDispatcher;
    public final ClockRegistry$pluginListener$1 pluginListener;
    public final PluginManager pluginManager;
    public final CoroutineScope scope;
    public final ClockRegistry$settingObserver$1 settingObserver;
    public ClockSettings settings;
    public final ClockRegistry$userSwitchObserver$1 userSwitchObserver;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface ClockChangeListener {
        void onCurrentClockChanged();
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ClockInfo {
        public final PluginLifecycleManager manager;
        public final ClockMetadata metadata;
        public ClockProvider provider;

        public ClockInfo(ClockMetadata clockMetadata, ClockProvider clockProvider, PluginLifecycleManager pluginLifecycleManager) {
            this.metadata = clockMetadata;
            this.provider = clockProvider;
            this.manager = pluginLifecycleManager;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ClockInfo)) {
                return false;
            }
            ClockInfo clockInfo = (ClockInfo) obj;
            return Intrinsics.areEqual(this.metadata, clockInfo.metadata) && Intrinsics.areEqual(this.provider, clockInfo.provider) && Intrinsics.areEqual(this.manager, clockInfo.manager);
        }

        public final int hashCode() {
            int hashCode = this.metadata.hashCode() * 31;
            ClockProvider clockProvider = this.provider;
            int hashCode2 = (hashCode + (clockProvider == null ? 0 : clockProvider.hashCode())) * 31;
            PluginLifecycleManager pluginLifecycleManager = this.manager;
            return hashCode2 + (pluginLifecycleManager != null ? pluginLifecycleManager.hashCode() : 0);
        }

        public final String toString() {
            return "ClockInfo(metadata=" + this.metadata + ", provider=" + this.provider + ", manager=" + this.manager + ")";
        }
    }

    /* JADX WARN: Type inference failed for: r1v7, types: [com.android.systemui.shared.clocks.ClockRegistry$settingObserver$1] */
    /* JADX WARN: Type inference failed for: r1v8, types: [com.android.systemui.shared.clocks.ClockRegistry$pluginListener$1] */
    /* JADX WARN: Type inference failed for: r1v9, types: [com.android.systemui.shared.clocks.ClockRegistry$userSwitchObserver$1] */
    public ClockRegistry(Context context, PluginManager pluginManager, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, CoroutineDispatcher coroutineDispatcher2, boolean z, DefaultClockProvider defaultClockProvider, String str, ClockMessageBuffers clockMessageBuffers, ThreadAssert threadAssert) {
        this.context = context;
        this.pluginManager = pluginManager;
        this.scope = coroutineScope;
        this.mainDispatcher = coroutineDispatcher;
        this.bgDispatcher = coroutineDispatcher2;
        this.isEnabled = z;
        this.fallbackClockId = str;
        this.clockBuffers = clockMessageBuffers;
        this.f43assert = threadAssert;
        String m = DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m(Reflection.getOrCreateKotlinClass(ClockRegistry.class).getSimpleName(), " (System)");
        this.TAG = m;
        MessageBuffer infraMessageBuffer = clockMessageBuffers.getInfraMessageBuffer();
        this.logger = new Logger(infraMessageBuffer == null ? new LogcatOnlyMessageBuffer(LogLevel.DEBUG) : infraMessageBuffer, m);
        this.availableClocks = new ConcurrentHashMap();
        this.clockChangeListeners = new ArrayList();
        this.settingObserver = new ContentObserver() { // from class: com.android.systemui.shared.clocks.ClockRegistry$settingObserver$1
            {
                super(null);
            }

            public final void onChange(boolean z2, Collection collection, int i, int i2) {
                ClockRegistry clockRegistry = ClockRegistry.this;
                BuildersKt.launch$default(clockRegistry.scope, clockRegistry.bgDispatcher, null, new ClockRegistry$settingObserver$1$onChange$1(clockRegistry, null), 2);
            }
        };
        this.pluginListener = new PluginListener() { // from class: com.android.systemui.shared.clocks.ClockRegistry$pluginListener$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.android.systemui.plugins.PluginListener
            public final boolean onPluginAttached(PluginLifecycleManager pluginLifecycleManager) {
                final ClockRegistry clockRegistry = ClockRegistry.this;
                pluginLifecycleManager.setLogFunc(new BiConsumer() { // from class: com.android.systemui.shared.clocks.ClockRegistry$pluginListener$1$onPluginAttached$1
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        String str2 = (String) obj;
                        String str3 = (String) obj2;
                        LogBuffer logBuffer = (LogBuffer) ClockRegistry.this.clockBuffers.getInfraMessageBuffer();
                        if (logBuffer != null) {
                            Intrinsics.checkNotNull(str2);
                            LogLevel logLevel = LogLevel.DEBUG;
                            Intrinsics.checkNotNull(str3);
                            logBuffer.log(str2, logLevel, str3, null);
                        }
                    }
                });
                clockRegistry.getClass();
                List<ClockMetadata> list = (List) ClockRegistryKt.KNOWN_PLUGINS.get(pluginLifecycleManager.getPackage());
                Logger logger = clockRegistry.logger;
                if (list == null) {
                    LogMessage obtain = logger.getBuffer().obtain(logger.getTag(), LogLevel.WARNING, new Function1() { // from class: com.android.systemui.shared.clocks.ClockRegistry$pluginListener$1$onPluginAttached$2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Loading unrecognized clock package: ", ((LogMessage) obj).getStr1());
                        }
                    }, null);
                    obtain.setStr1(pluginLifecycleManager.getPackage());
                    logger.getBuffer().commit(obtain);
                    return true;
                }
                LogMessage obtain2 = logger.getBuffer().obtain(logger.getTag(), LogLevel.INFO, new Function1() { // from class: com.android.systemui.shared.clocks.ClockRegistry$pluginListener$1$onPluginAttached$4
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Skipping initial load of known clock package package: ", ((LogMessage) obj).getStr1());
                    }
                }, null);
                obtain2.setStr1(pluginLifecycleManager.getPackage());
                logger.getBuffer().commit(obtain2);
                final Ref$BooleanRef ref$BooleanRef = new Ref$BooleanRef();
                boolean z2 = false;
                for (ClockMetadata clockMetadata : list) {
                    z2 = z2 || Intrinsics.areEqual(clockRegistry.getCurrentClockId(), clockMetadata.getClockId());
                    String clockId = clockMetadata.getClockId();
                    ConcurrentHashMap concurrentHashMap = clockRegistry.availableClocks;
                    ClockRegistry.ClockInfo clockInfo = new ClockRegistry.ClockInfo(clockMetadata, null, pluginLifecycleManager);
                    Function1 function1 = new Function1() { // from class: com.android.systemui.shared.clocks.ClockRegistry$pluginListener$1$onPluginAttached$info$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            Ref$BooleanRef.this.element = true;
                            ClockRegistry.access$onConnected(clockRegistry, (ClockRegistry.ClockInfo) obj);
                            return Unit.INSTANCE;
                        }
                    };
                    Object putIfAbsent = concurrentHashMap.putIfAbsent(clockId, clockInfo);
                    if (putIfAbsent == 0) {
                        function1.invoke(clockInfo);
                    }
                    if (putIfAbsent != 0) {
                        clockInfo = putIfAbsent;
                    }
                    ClockRegistry.ClockInfo clockInfo2 = clockInfo;
                    PluginLifecycleManager pluginLifecycleManager2 = clockInfo2.manager;
                    if (pluginLifecycleManager.equals(pluginLifecycleManager2)) {
                        clockInfo2.provider = null;
                    } else {
                        LogMessage obtain3 = logger.getBuffer().obtain(logger.getTag(), LogLevel.ERROR, new Function1() { // from class: com.android.systemui.shared.clocks.ClockRegistry$pluginListener$1$onPluginAttached$6
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                LogMessage logMessage = (LogMessage) obj;
                                String str1 = logMessage.getStr1();
                                String str2 = logMessage.getStr2();
                                String str3 = logMessage.getStr3();
                                StringBuilder m2 = TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("Clock Id conflict on attach: ", str1, " is double registered by ", str2, " and ");
                                m2.append(str3);
                                return m2.toString();
                            }
                        }, null);
                        obtain3.setStr1(clockId);
                        obtain3.setStr2(String.valueOf(pluginLifecycleManager2));
                        obtain3.setStr3(pluginLifecycleManager.toString());
                        logger.getBuffer().commit(obtain3);
                    }
                }
                if (ref$BooleanRef.element) {
                    ClockRegistry.access$triggerOnAvailableClocksChanged(clockRegistry);
                }
                clockRegistry.verifyLoadedProviders();
                return z2;
            }

            @Override // com.android.systemui.plugins.PluginListener
            public final void onPluginDetached(final PluginLifecycleManager pluginLifecycleManager) {
                final ArrayList<ClockRegistry.ClockInfo> arrayList = new ArrayList();
                ClockRegistry clockRegistry = ClockRegistry.this;
                CollectionsKt__MutableCollectionsKt.filterInPlace$CollectionsKt__MutableCollectionsKt(clockRegistry.availableClocks.entrySet(), new Function1() { // from class: com.android.systemui.shared.clocks.ClockRegistry$pluginListener$1$onPluginDetached$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        Map.Entry entry = (Map.Entry) obj;
                        if (!Intrinsics.areEqual(((ClockRegistry.ClockInfo) entry.getValue()).manager, PluginLifecycleManager.this)) {
                            return Boolean.FALSE;
                        }
                        arrayList.add(entry.getValue());
                        return Boolean.TRUE;
                    }
                }, true);
                for (ClockRegistry.ClockInfo clockInfo : arrayList) {
                    boolean areEqual = Intrinsics.areEqual(clockRegistry.getCurrentClockId(), clockInfo.metadata.getClockId());
                    Logger logger = clockRegistry.logger;
                    LogMessage obtain = logger.getBuffer().obtain(logger.getTag(), areEqual ? LogLevel.INFO : LogLevel.DEBUG, new Function1() { // from class: com.android.systemui.shared.clocks.ClockRegistry$onDisconnected$1
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            LogMessage logMessage = (LogMessage) obj;
                            return MotionLayout$$ExternalSyntheticOutline0.m("Disconnected ", logMessage.getStr1(), " @", logMessage.getStr2(), logMessage.getBool1() ? " (Current Clock)" : "");
                        }
                    }, null);
                    obtain.setStr1(clockInfo.metadata.getClockId());
                    obtain.setStr2(String.valueOf(clockInfo.manager));
                    obtain.setBool1(areEqual);
                    logger.getBuffer().commit(obtain);
                }
                if (arrayList.size() > 0) {
                    ClockRegistry.access$triggerOnAvailableClocksChanged(clockRegistry);
                }
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.android.systemui.plugins.PluginListener
            public final void onPluginLoaded(Plugin plugin, Context context2, PluginLifecycleManager pluginLifecycleManager) {
                ClockProviderPlugin clockProviderPlugin = (ClockProviderPlugin) plugin;
                final ClockRegistry clockRegistry = ClockRegistry.this;
                clockProviderPlugin.initialize(clockRegistry.clockBuffers);
                final Ref$BooleanRef ref$BooleanRef = new Ref$BooleanRef();
                for (ClockMetadata clockMetadata : clockProviderPlugin.getClocks()) {
                    String clockId = clockMetadata.getClockId();
                    ConcurrentHashMap concurrentHashMap = clockRegistry.availableClocks;
                    ClockRegistry.ClockInfo clockInfo = new ClockRegistry.ClockInfo(clockMetadata, clockProviderPlugin, pluginLifecycleManager);
                    Function1 function1 = new Function1() { // from class: com.android.systemui.shared.clocks.ClockRegistry$pluginListener$1$onPluginLoaded$info$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            Ref$BooleanRef.this.element = true;
                            ClockRegistry.access$onConnected(clockRegistry, (ClockRegistry.ClockInfo) obj);
                            return Unit.INSTANCE;
                        }
                    };
                    Map map = ClockRegistryKt.KNOWN_PLUGINS;
                    Object putIfAbsent = concurrentHashMap.putIfAbsent(clockId, clockInfo);
                    if (putIfAbsent == 0) {
                        function1.invoke(clockInfo);
                    }
                    if (putIfAbsent != 0) {
                        clockInfo = putIfAbsent;
                    }
                    ClockRegistry.ClockInfo clockInfo2 = clockInfo;
                    PluginLifecycleManager pluginLifecycleManager2 = clockInfo2.manager;
                    boolean equals = pluginLifecycleManager.equals(pluginLifecycleManager2);
                    Logger logger = clockRegistry.logger;
                    if (equals) {
                        clockInfo2.provider = clockProviderPlugin;
                        String currentClockId = clockRegistry.getCurrentClockId();
                        ClockMetadata clockMetadata2 = clockInfo2.metadata;
                        boolean areEqual = Intrinsics.areEqual(currentClockId, clockMetadata2.getClockId());
                        LogMessage obtain = logger.getBuffer().obtain(logger.getTag(), areEqual ? LogLevel.INFO : LogLevel.DEBUG, new Function1() { // from class: com.android.systemui.shared.clocks.ClockRegistry$onLoaded$1
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                LogMessage logMessage = (LogMessage) obj;
                                return MotionLayout$$ExternalSyntheticOutline0.m("Loaded ", logMessage.getStr1(), " @", logMessage.getStr2(), logMessage.getBool1() ? " (Current Clock)" : "");
                            }
                        }, null);
                        obtain.setStr1(clockMetadata2.getClockId());
                        obtain.setStr2(String.valueOf(pluginLifecycleManager2));
                        obtain.setBool1(areEqual);
                        logger.getBuffer().commit(obtain);
                        if (areEqual) {
                            clockRegistry.triggerOnCurrentClockChanged();
                        }
                    } else {
                        LogMessage obtain2 = logger.getBuffer().obtain(logger.getTag(), LogLevel.ERROR, new Function1() { // from class: com.android.systemui.shared.clocks.ClockRegistry$pluginListener$1$onPluginLoaded$1
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                LogMessage logMessage = (LogMessage) obj;
                                String str1 = logMessage.getStr1();
                                String str2 = logMessage.getStr2();
                                String str3 = logMessage.getStr3();
                                StringBuilder m2 = TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("Clock Id conflict on load: ", str1, " is double registered by ", str2, " and ");
                                m2.append(str3);
                                return m2.toString();
                            }
                        }, null);
                        obtain2.setStr1(clockId);
                        obtain2.setStr2(String.valueOf(pluginLifecycleManager2));
                        obtain2.setStr3(pluginLifecycleManager.toString());
                        logger.getBuffer().commit(obtain2);
                        pluginLifecycleManager.unloadPlugin();
                    }
                }
                if (ref$BooleanRef.element) {
                    ClockRegistry.access$triggerOnAvailableClocksChanged(clockRegistry);
                }
                clockRegistry.verifyLoadedProviders();
            }

            @Override // com.android.systemui.plugins.PluginListener
            public final void onPluginUnloaded(Plugin plugin, PluginLifecycleManager pluginLifecycleManager) {
                Iterator it = ((ClockProviderPlugin) plugin).getClocks().iterator();
                while (true) {
                    boolean hasNext = it.hasNext();
                    ClockRegistry clockRegistry = ClockRegistry.this;
                    if (!hasNext) {
                        clockRegistry.verifyLoadedProviders();
                        return;
                    }
                    String clockId = ((ClockMetadata) it.next()).getClockId();
                    ClockRegistry.ClockInfo clockInfo = (ClockRegistry.ClockInfo) clockRegistry.availableClocks.get(clockId);
                    boolean areEqual = Intrinsics.areEqual(clockInfo != null ? clockInfo.manager : null, pluginLifecycleManager);
                    Logger logger = clockRegistry.logger;
                    if (areEqual) {
                        clockInfo.provider = null;
                        String currentClockId = clockRegistry.getCurrentClockId();
                        ClockMetadata clockMetadata = clockInfo.metadata;
                        boolean areEqual2 = Intrinsics.areEqual(currentClockId, clockMetadata.getClockId());
                        LogMessage obtain = logger.getBuffer().obtain(logger.getTag(), areEqual2 ? LogLevel.WARNING : LogLevel.DEBUG, new Function1() { // from class: com.android.systemui.shared.clocks.ClockRegistry$onUnloaded$1
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                LogMessage logMessage = (LogMessage) obj;
                                return MotionLayout$$ExternalSyntheticOutline0.m("Unloaded ", logMessage.getStr1(), " @", logMessage.getStr2(), logMessage.getBool1() ? " (Current Clock)" : "");
                            }
                        }, null);
                        obtain.setStr1(clockMetadata.getClockId());
                        obtain.setStr2(String.valueOf(clockInfo.manager));
                        obtain.setBool1(areEqual2);
                        logger.getBuffer().commit(obtain);
                        if (areEqual2) {
                            clockRegistry.triggerOnCurrentClockChanged();
                        }
                    } else {
                        LogMessage obtain2 = logger.getBuffer().obtain(logger.getTag(), LogLevel.ERROR, new Function1() { // from class: com.android.systemui.shared.clocks.ClockRegistry$pluginListener$1$onPluginUnloaded$1
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                LogMessage logMessage = (LogMessage) obj;
                                String str1 = logMessage.getStr1();
                                String str2 = logMessage.getStr2();
                                String str3 = logMessage.getStr3();
                                StringBuilder m2 = TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("Clock Id conflict on unload: ", str1, " is double registered by ", str2, " and ");
                                m2.append(str3);
                                return m2.toString();
                            }
                        }, null);
                        obtain2.setStr1(clockId);
                        obtain2.setStr2(String.valueOf(clockInfo != null ? clockInfo.manager : null));
                        obtain2.setStr3(pluginLifecycleManager.toString());
                        logger.getBuffer().commit(obtain2);
                    }
                }
            }
        };
        this.userSwitchObserver = new UserSwitchObserver() { // from class: com.android.systemui.shared.clocks.ClockRegistry$userSwitchObserver$1
            public final void onUserSwitchComplete(int i) {
                ClockRegistry clockRegistry = ClockRegistry.this;
                BuildersKt.launch$default(clockRegistry.scope, clockRegistry.bgDispatcher, null, new ClockRegistry$userSwitchObserver$1$onUserSwitchComplete$1(clockRegistry, null), 2);
            }
        };
        this.isClockChanged = new AtomicBoolean(false);
        this.isClockListChanged = new AtomicBoolean(false);
        defaultClockProvider.messageBuffers = clockMessageBuffers;
        for (ClockMetadata clockMetadata : defaultClockProvider.getClocks()) {
            this.availableClocks.put(clockMetadata.getClockId(), new ClockInfo(clockMetadata, defaultClockProvider, null));
        }
        if (this.availableClocks.containsKey("DEFAULT")) {
            this.isQueued = new AtomicBoolean(false);
            return;
        }
        throw new IllegalArgumentException(defaultClockProvider + " did not register clock at DEFAULT");
    }

    public static final void access$onConnected(ClockRegistry clockRegistry, ClockInfo clockInfo) {
        boolean areEqual = Intrinsics.areEqual(clockRegistry.getCurrentClockId(), clockInfo.metadata.getClockId());
        Logger logger = clockRegistry.logger;
        LogMessage obtain = logger.getBuffer().obtain(logger.getTag(), areEqual ? LogLevel.INFO : LogLevel.DEBUG, new Function1() { // from class: com.android.systemui.shared.clocks.ClockRegistry$onConnected$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return MotionLayout$$ExternalSyntheticOutline0.m("Connected ", logMessage.getStr1(), " @", logMessage.getStr2(), logMessage.getBool1() ? " (Current Clock)" : "");
            }
        }, null);
        obtain.setStr1(clockInfo.metadata.getClockId());
        obtain.setStr2(String.valueOf(clockInfo.manager));
        obtain.setBool1(areEqual);
        logger.getBuffer().commit(obtain);
    }

    public static final void access$triggerOnAvailableClocksChanged(ClockRegistry clockRegistry) {
        if (clockRegistry.isClockListChanged.compareAndSet(false, true)) {
            BuildersKt.launch$default(clockRegistry.scope, clockRegistry.mainDispatcher, null, new ClockRegistry$triggerOnAvailableClocksChanged$1(clockRegistry, null), 2);
        }
    }

    public final ClockController createClock(String str) {
        ClockProvider clockProvider;
        ClockSettings clockSettings = this.settings;
        if (clockSettings == null) {
            clockSettings = new ClockSettings(null, null, null, 7, null);
        }
        ClockSettings clockSettings2 = clockSettings;
        if (!Intrinsics.areEqual(str, clockSettings2.getClockId())) {
            clockSettings2 = ClockSettings.copy$default(clockSettings2, str, null, null, 6, null);
        }
        ClockInfo clockInfo = (ClockInfo) this.availableClocks.get(str);
        if (clockInfo == null || (clockProvider = clockInfo.provider) == null) {
            return null;
        }
        return clockProvider.createClock(clockSettings2);
    }

    public final ClockController createCurrentClock() {
        String currentClockId = getCurrentClockId();
        if (this.isEnabled && currentClockId.length() > 0) {
            ClockController createClock = createClock(currentClockId);
            Logger logger = this.logger;
            if (createClock != null) {
                ClockRegistry$createCurrentClock$1 clockRegistry$createCurrentClock$1 = new Function1() { // from class: com.android.systemui.shared.clocks.ClockRegistry$createCurrentClock$1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Rendering clock ", ((LogMessage) obj).getStr1());
                    }
                };
                LogMessage obtain = logger.getBuffer().obtain(logger.getTag(), LogLevel.INFO, clockRegistry$createCurrentClock$1, null);
                obtain.setStr1(currentClockId);
                logger.getBuffer().commit(obtain);
                return createClock;
            }
            if (this.availableClocks.containsKey(currentClockId)) {
                ClockRegistry$createCurrentClock$3 clockRegistry$createCurrentClock$3 = new Function1() { // from class: com.android.systemui.shared.clocks.ClockRegistry$createCurrentClock$3
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Clock ", ((LogMessage) obj).getStr1(), " not loaded; using default");
                    }
                };
                LogMessage obtain2 = logger.getBuffer().obtain(logger.getTag(), LogLevel.WARNING, clockRegistry$createCurrentClock$3, null);
                obtain2.setStr1(currentClockId);
                logger.getBuffer().commit(obtain2);
                verifyLoadedProviders();
            } else {
                ClockRegistry$createCurrentClock$5 clockRegistry$createCurrentClock$5 = new Function1() { // from class: com.android.systemui.shared.clocks.ClockRegistry$createCurrentClock$5
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Clock ", ((LogMessage) obj).getStr1(), " not found; using default");
                    }
                };
                LogMessage obtain3 = logger.getBuffer().obtain(logger.getTag(), LogLevel.ERROR, clockRegistry$createCurrentClock$5, null);
                obtain3.setStr1(currentClockId);
                logger.getBuffer().commit(obtain3);
            }
        }
        ClockController createClock2 = createClock("DEFAULT");
        Intrinsics.checkNotNull(createClock2);
        return createClock2;
    }

    public final String getCurrentClockId() {
        String clockId;
        ClockSettings clockSettings = this.settings;
        return (clockSettings == null || (clockId = clockSettings.getClockId()) == null) ? this.fallbackClockId : clockId;
    }

    public final void querySettings() {
        ClockSettings clockSettings;
        Assert.isNotMainThread();
        try {
            clockSettings = ClockSettings.Companion.deserialize(Settings.Secure.getStringForUser(this.context.getContentResolver(), "lock_screen_custom_clock_face", ActivityManager.getCurrentUser()));
        } catch (Exception e) {
            this.logger.e("Failed to parse clock settings", e);
            clockSettings = null;
        }
        if (Intrinsics.areEqual(this.settings, clockSettings)) {
            return;
        }
        this.settings = clockSettings;
        verifyLoadedProviders();
        triggerOnCurrentClockChanged();
    }

    public final void registerListeners() {
        if (!this.isEnabled || this.isRegistered) {
            return;
        }
        this.isRegistered = true;
        this.pluginManager.addPluginListener((PluginListener) this.pluginListener, ClockProviderPlugin.class, true);
        BuildersKt.launch$default(this.scope, this.bgDispatcher, null, new ClockRegistry$registerListeners$1(this, null), 2);
        this.context.getContentResolver().registerContentObserver(Settings.Secure.getUriFor("lock_screen_custom_clock_face"), false, this.settingObserver, -1);
        ActivityManager.getService().registerUserSwitchObserver(this.userSwitchObserver, this.TAG);
    }

    public final void triggerOnCurrentClockChanged() {
        if (this.isClockChanged.compareAndSet(false, true)) {
            BuildersKt.launch$default(this.scope, this.mainDispatcher, null, new ClockRegistry$triggerOnCurrentClockChanged$1(this, null), 2);
        }
    }

    public final void unregisterClockChangeListener(ClockChangeListener clockChangeListener) {
        this.f43assert.getClass();
        Assert.isMainThread();
        this.clockChangeListeners.remove(clockChangeListener);
    }

    public final void verifyLoadedProviders() {
        if (!this.isQueued.compareAndSet(false, true)) {
            Logger.v$default(this.logger, "verifyLoadedProviders: shouldSchedule=false", null, 2, null);
        } else {
            BuildersKt.launch$default(this.scope, this.bgDispatcher, null, new ClockRegistry$verifyLoadedProviders$1(this, null), 2);
        }
    }
}
