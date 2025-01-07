package com.android.systemui.statusbar.policy;

import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaRouter;
import android.media.projection.MediaProjectionInfo;
import android.media.projection.MediaProjectionManager;
import android.os.Handler;
import android.util.ArrayMap;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.appsearch.platformstorage.converter.GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0;
import com.android.keyguard.logging.KeyguardUpdateMonitorLogger$allowFingerprintOnCurrentOccludingActivityChanged$2$$ExternalSyntheticOutline0;
import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.policy.CastControllerLogger;
import com.android.systemui.statusbar.policy.CastDevice;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CastControllerImpl implements CallbackController, Dumpable {
    public boolean mCallbackRegistered;
    public final Context mContext;
    public final CastControllerLogger mLogger;
    public final MediaRouter mMediaRouter;
    public final PackageManager mPackageManager;
    public MediaProjectionInfo mProjection;
    public final AnonymousClass2 mProjectionCallback;
    public final MediaProjectionManager mProjectionManager;
    public final ArrayList mCallbacks = new ArrayList();
    public final ArrayMap mRoutes = new ArrayMap();
    public final Object mDiscoveringLock = new Object();
    public final Object mProjectionLock = new Object();
    public final AnonymousClass1 mMediaCallback = new MediaRouter.SimpleCallback() { // from class: com.android.systemui.statusbar.policy.CastControllerImpl.1
        @Override // android.media.MediaRouter.SimpleCallback, android.media.MediaRouter.Callback
        public final void onRouteAdded(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
            CastControllerLogger castControllerLogger = CastControllerImpl.this.mLogger;
            castControllerLogger.getClass();
            LogLevel logLevel = LogLevel.DEBUG;
            CastControllerLogger$logRouteAdded$2 castControllerLogger$logRouteAdded$2 = new Function1() { // from class: com.android.systemui.statusbar.policy.CastControllerLogger$logRouteAdded$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("onRouteAdded: ", ((LogMessage) obj).getStr1());
                }
            };
            LogBuffer logBuffer = castControllerLogger.logger;
            LogMessage obtain = logBuffer.obtain("CastController", logLevel, castControllerLogger$logRouteAdded$2, null);
            ((LogMessageImpl) obtain).str1 = CastControllerLogger.Companion.toLogString(routeInfo);
            logBuffer.commit(obtain);
            CastControllerImpl.m886$$Nest$mupdateRemoteDisplays(CastControllerImpl.this);
        }

        @Override // android.media.MediaRouter.SimpleCallback, android.media.MediaRouter.Callback
        public final void onRouteChanged(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
            CastControllerLogger castControllerLogger = CastControllerImpl.this.mLogger;
            castControllerLogger.getClass();
            LogLevel logLevel = LogLevel.DEBUG;
            CastControllerLogger$logRouteChanged$2 castControllerLogger$logRouteChanged$2 = new Function1() { // from class: com.android.systemui.statusbar.policy.CastControllerLogger$logRouteChanged$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("onRouteChanged: ", ((LogMessage) obj).getStr1());
                }
            };
            LogBuffer logBuffer = castControllerLogger.logger;
            LogMessage obtain = logBuffer.obtain("CastController", logLevel, castControllerLogger$logRouteChanged$2, null);
            ((LogMessageImpl) obtain).str1 = CastControllerLogger.Companion.toLogString(routeInfo);
            logBuffer.commit(obtain);
            CastControllerImpl.m886$$Nest$mupdateRemoteDisplays(CastControllerImpl.this);
        }

        @Override // android.media.MediaRouter.SimpleCallback, android.media.MediaRouter.Callback
        public final void onRouteRemoved(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
            CastControllerLogger castControllerLogger = CastControllerImpl.this.mLogger;
            castControllerLogger.getClass();
            LogLevel logLevel = LogLevel.DEBUG;
            CastControllerLogger$logRouteRemoved$2 castControllerLogger$logRouteRemoved$2 = new Function1() { // from class: com.android.systemui.statusbar.policy.CastControllerLogger$logRouteRemoved$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("onRouteRemoved: ", ((LogMessage) obj).getStr1());
                }
            };
            LogBuffer logBuffer = castControllerLogger.logger;
            LogMessage obtain = logBuffer.obtain("CastController", logLevel, castControllerLogger$logRouteRemoved$2, null);
            ((LogMessageImpl) obtain).str1 = CastControllerLogger.Companion.toLogString(routeInfo);
            logBuffer.commit(obtain);
            CastControllerImpl.m886$$Nest$mupdateRemoteDisplays(CastControllerImpl.this);
        }

        @Override // android.media.MediaRouter.SimpleCallback, android.media.MediaRouter.Callback
        public final void onRouteSelected(MediaRouter mediaRouter, int i, MediaRouter.RouteInfo routeInfo) {
            CastControllerLogger castControllerLogger = CastControllerImpl.this.mLogger;
            castControllerLogger.getClass();
            LogLevel logLevel = LogLevel.DEBUG;
            CastControllerLogger$logRouteSelected$2 castControllerLogger$logRouteSelected$2 = new Function1() { // from class: com.android.systemui.statusbar.policy.CastControllerLogger$logRouteSelected$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    LogMessage logMessage = (LogMessage) obj;
                    return "onRouteSelected(" + logMessage.getInt1() + "): " + logMessage.getStr1();
                }
            };
            LogBuffer logBuffer = castControllerLogger.logger;
            LogMessage obtain = logBuffer.obtain("CastController", logLevel, castControllerLogger$logRouteSelected$2, null);
            LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
            logMessageImpl.str1 = CastControllerLogger.Companion.toLogString(routeInfo);
            logMessageImpl.int1 = i;
            logBuffer.commit(obtain);
            CastControllerImpl.m886$$Nest$mupdateRemoteDisplays(CastControllerImpl.this);
        }

        @Override // android.media.MediaRouter.SimpleCallback, android.media.MediaRouter.Callback
        public final void onRouteUnselected(MediaRouter mediaRouter, int i, MediaRouter.RouteInfo routeInfo) {
            CastControllerLogger castControllerLogger = CastControllerImpl.this.mLogger;
            castControllerLogger.getClass();
            LogLevel logLevel = LogLevel.DEBUG;
            CastControllerLogger$logRouteUnselected$2 castControllerLogger$logRouteUnselected$2 = new Function1() { // from class: com.android.systemui.statusbar.policy.CastControllerLogger$logRouteUnselected$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    LogMessage logMessage = (LogMessage) obj;
                    return "onRouteUnselected(" + logMessage.getInt1() + "): " + logMessage.getStr1();
                }
            };
            LogBuffer logBuffer = castControllerLogger.logger;
            LogMessage obtain = logBuffer.obtain("CastController", logLevel, castControllerLogger$logRouteUnselected$2, null);
            LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
            logMessageImpl.str1 = CastControllerLogger.Companion.toLogString(routeInfo);
            logMessageImpl.int1 = i;
            logBuffer.commit(obtain);
            CastControllerImpl.m886$$Nest$mupdateRemoteDisplays(CastControllerImpl.this);
        }
    };

    /* renamed from: -$$Nest$msetProjection, reason: not valid java name */
    public static void m885$$Nest$msetProjection(CastControllerImpl castControllerImpl, MediaProjectionInfo mediaProjectionInfo, boolean z) {
        boolean z2;
        MediaProjectionInfo mediaProjectionInfo2 = castControllerImpl.mProjection;
        synchronized (castControllerImpl.mProjectionLock) {
            try {
                boolean equals = Objects.equals(mediaProjectionInfo, castControllerImpl.mProjection);
                z2 = true;
                if (z && !equals) {
                    castControllerImpl.mProjection = mediaProjectionInfo;
                } else if (z || !equals) {
                    z2 = false;
                } else {
                    castControllerImpl.mProjection = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (z2) {
            CastControllerLogger castControllerLogger = castControllerImpl.mLogger;
            MediaProjectionInfo mediaProjectionInfo3 = castControllerImpl.mProjection;
            castControllerLogger.getClass();
            LogLevel logLevel = LogLevel.DEBUG;
            CastControllerLogger$logSetProjection$2 castControllerLogger$logSetProjection$2 = new Function1() { // from class: com.android.systemui.statusbar.policy.CastControllerLogger$logSetProjection$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    LogMessage logMessage = (LogMessage) obj;
                    return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("setProjection: ", logMessage.getStr1(), " -> ", logMessage.getStr2());
                }
            };
            LogBuffer logBuffer = castControllerLogger.logger;
            LogMessage obtain = logBuffer.obtain("CastController", logLevel, castControllerLogger$logSetProjection$2, null);
            LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
            logMessageImpl.str1 = String.valueOf(mediaProjectionInfo2);
            logMessageImpl.str2 = String.valueOf(mediaProjectionInfo3);
            logBuffer.commit(obtain);
            castControllerImpl.fireOnCastDevicesChanged();
        }
    }

    /* renamed from: -$$Nest$mupdateRemoteDisplays, reason: not valid java name */
    public static void m886$$Nest$mupdateRemoteDisplays(CastControllerImpl castControllerImpl) {
        synchronized (castControllerImpl.mRoutes) {
            try {
                castControllerImpl.mRoutes.clear();
                int routeCount = castControllerImpl.mMediaRouter.getRouteCount();
                for (int i = 0; i < routeCount; i++) {
                    MediaRouter.RouteInfo routeAt = castControllerImpl.mMediaRouter.getRouteAt(i);
                    if (routeAt.isEnabled() && routeAt.matchesTypes(4)) {
                        if (routeAt.getTag() == null) {
                            routeAt.setTag(UUID.randomUUID().toString());
                        }
                        castControllerImpl.mRoutes.put(routeAt.getTag().toString(), routeAt);
                    }
                }
                MediaRouter.RouteInfo selectedRoute = castControllerImpl.mMediaRouter.getSelectedRoute(4);
                if (selectedRoute != null && !selectedRoute.isDefault()) {
                    if (selectedRoute.getTag() == null) {
                        selectedRoute.setTag(UUID.randomUUID().toString());
                    }
                    castControllerImpl.mRoutes.put(selectedRoute.getTag().toString(), selectedRoute);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        castControllerImpl.fireOnCastDevicesChanged();
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [com.android.systemui.statusbar.policy.CastControllerImpl$1] */
    public CastControllerImpl(Context context, PackageManager packageManager, DumpManager dumpManager, CastControllerLogger castControllerLogger) {
        MediaProjectionManager.Callback callback = new MediaProjectionManager.Callback() { // from class: com.android.systemui.statusbar.policy.CastControllerImpl.2
            public final void onStart(MediaProjectionInfo mediaProjectionInfo) {
                CastControllerImpl.m885$$Nest$msetProjection(CastControllerImpl.this, mediaProjectionInfo, true);
            }

            public final void onStop(MediaProjectionInfo mediaProjectionInfo) {
                CastControllerImpl.m885$$Nest$msetProjection(CastControllerImpl.this, mediaProjectionInfo, false);
            }
        };
        this.mContext = context;
        this.mPackageManager = packageManager;
        this.mLogger = castControllerLogger;
        MediaRouter mediaRouter = (MediaRouter) context.getSystemService("media_router");
        this.mMediaRouter = mediaRouter;
        mediaRouter.setRouterGroupId("android.media.mirroring_group");
        MediaProjectionManager mediaProjectionManager = (MediaProjectionManager) context.getSystemService("media_projection");
        this.mProjectionManager = mediaProjectionManager;
        this.mProjection = mediaProjectionManager.getActiveProjectionInfo();
        mediaProjectionManager.addCallback(callback, new Handler());
        dumpManager.registerNormalDumpable("CastController", this);
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void addCallback(Object obj) {
        CastController$Callback castController$Callback = (CastController$Callback) obj;
        synchronized (this.mCallbacks) {
            this.mCallbacks.add(castController$Callback);
        }
        castController$Callback.onCastDevicesChanged();
        synchronized (this.mDiscoveringLock) {
            handleDiscoveryChangeLocked();
        }
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("CastController state:");
        printWriter.print("  mDiscovering=");
        printWriter.println(false);
        printWriter.print("  mCallbackRegistered=");
        printWriter.println(this.mCallbackRegistered);
        printWriter.print("  mCallbacks.size=");
        synchronized (this.mCallbacks) {
            printWriter.println(this.mCallbacks.size());
        }
        printWriter.print("  mRoutes.size=");
        printWriter.println(this.mRoutes.size());
        for (int i = 0; i < this.mRoutes.size(); i++) {
            MediaRouter.RouteInfo routeInfo = (MediaRouter.RouteInfo) this.mRoutes.valueAt(i);
            printWriter.print("    ");
            printWriter.println(CastControllerLogger.Companion.toLogString(routeInfo));
        }
        printWriter.print("  mProjection=");
        printWriter.println(this.mProjection);
    }

    public void fireOnCastDevicesChanged() {
        ArrayList arrayList;
        synchronized (this.mCallbacks) {
            arrayList = new ArrayList(this.mCallbacks);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((CastController$Callback) it.next()).onCastDevicesChanged();
        }
    }

    public final List getCastDevices() {
        CastDevice.CastState castState;
        ArrayList arrayList = new ArrayList();
        synchronized (this.mRoutes) {
            try {
                for (MediaRouter.RouteInfo routeInfo : this.mRoutes.values()) {
                    Context context = this.mContext;
                    if (routeInfo.getStatusCode() == 2) {
                        castState = CastDevice.CastState.Connecting;
                    } else {
                        if (!routeInfo.isSelected() && routeInfo.getStatusCode() != 6) {
                            castState = CastDevice.CastState.Disconnected;
                        }
                        castState = CastDevice.CastState.Connected;
                    }
                    CastDevice.CastState castState2 = castState;
                    String obj = routeInfo.getTag().toString();
                    CharSequence name = routeInfo.getName(context);
                    String obj2 = name != null ? name.toString() : null;
                    CharSequence description = routeInfo.getDescription();
                    arrayList.add(new CastDevice(obj, obj2, description != null ? description.toString() : null, castState2, CastDevice.CastOrigin.MediaRouter, routeInfo));
                }
            } finally {
            }
        }
        synchronized (this.mProjectionLock) {
            try {
                MediaProjectionInfo mediaProjectionInfo = this.mProjection;
                if (mediaProjectionInfo != null) {
                    arrayList.add(CastDevice.Companion.toCastDevice(mediaProjectionInfo, this.mContext, this.mPackageManager, this.mLogger));
                }
            } finally {
            }
        }
        return arrayList;
    }

    public final void handleDiscoveryChangeLocked() {
        boolean isEmpty;
        if (this.mCallbackRegistered) {
            this.mMediaRouter.removeCallback(this.mMediaCallback);
            this.mCallbackRegistered = false;
        }
        synchronized (this.mCallbacks) {
            isEmpty = this.mCallbacks.isEmpty();
        }
        if (isEmpty) {
            return;
        }
        this.mMediaRouter.addCallback(4, this.mMediaCallback, 8);
        this.mCallbackRegistered = true;
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void removeCallback(Object obj) {
        CastController$Callback castController$Callback = (CastController$Callback) obj;
        synchronized (this.mCallbacks) {
            this.mCallbacks.remove(castController$Callback);
        }
        synchronized (this.mDiscoveringLock) {
            handleDiscoveryChangeLocked();
        }
    }

    public final void stopCasting(CastDevice castDevice) {
        boolean z = castDevice.tag instanceof MediaProjectionInfo;
        CastControllerLogger castControllerLogger = this.mLogger;
        castControllerLogger.getClass();
        LogLevel logLevel = LogLevel.DEBUG;
        CastControllerLogger$logStopCasting$2 castControllerLogger$logStopCasting$2 = new Function1() { // from class: com.android.systemui.statusbar.policy.CastControllerLogger$logStopCasting$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return KeyguardUpdateMonitorLogger$allowFingerprintOnCurrentOccludingActivityChanged$2$$ExternalSyntheticOutline0.m("stopCasting. isProjection=", ((LogMessage) obj).getBool1());
            }
        };
        LogBuffer logBuffer = castControllerLogger.logger;
        LogMessage obtain = logBuffer.obtain("CastController", logLevel, castControllerLogger$logStopCasting$2, null);
        ((LogMessageImpl) obtain).bool1 = z;
        logBuffer.commit(obtain);
        if (!z) {
            castControllerLogger.getClass();
            CastControllerLogger$logStopCastingMediaRouter$2 castControllerLogger$logStopCastingMediaRouter$2 = new Function1() { // from class: com.android.systemui.statusbar.policy.CastControllerLogger$logStopCastingMediaRouter$2
                @Override // kotlin.jvm.functions.Function1
                public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    return "stopCasting is selecting fallback route in MediaRouter";
                }
            };
            LogBuffer logBuffer2 = castControllerLogger.logger;
            logBuffer2.commit(logBuffer2.obtain("CastController", logLevel, castControllerLogger$logStopCastingMediaRouter$2, null));
            this.mMediaRouter.getFallbackRoute().select();
            return;
        }
        MediaProjectionInfo mediaProjectionInfo = (MediaProjectionInfo) castDevice.tag;
        if (Objects.equals(this.mProjectionManager.getActiveProjectionInfo(), mediaProjectionInfo)) {
            this.mProjectionManager.stopActiveProjection();
            return;
        }
        castControllerLogger.getClass();
        LogLevel logLevel2 = LogLevel.WARNING;
        CastControllerLogger$logStopCastingNoProjection$2 castControllerLogger$logStopCastingNoProjection$2 = new Function1() { // from class: com.android.systemui.statusbar.policy.CastControllerLogger$logStopCastingNoProjection$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("stopCasting failed because projection is no longer active: ", ((LogMessage) obj).getStr1());
            }
        };
        LogBuffer logBuffer3 = castControllerLogger.logger;
        LogMessage obtain2 = logBuffer3.obtain("CastController", logLevel2, castControllerLogger$logStopCastingNoProjection$2, null);
        ((LogMessageImpl) obtain2).str1 = mediaProjectionInfo.toString();
        logBuffer3.commit(obtain2);
    }
}
