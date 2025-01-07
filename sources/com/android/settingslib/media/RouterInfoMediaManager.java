package com.android.settingslib.media;

import android.content.Context;
import android.media.MediaRoute2Info;
import android.media.MediaRouter2;
import android.media.MediaRouter2Manager;
import android.media.RouteDiscoveryPreference;
import android.media.RouteListingPreference;
import android.media.RoutingSessionInfo;
import android.media.session.MediaController;
import android.os.UserHandle;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.android.settingslib.media.InfoMediaManager;
import com.android.settingslib.media.LocalMediaManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class RouterInfoMediaManager extends InfoMediaManager {
    public final ControllerCallback mControllerCallback;
    public final Executor mExecutor;
    public final RouteCallback mRouteCallback;
    public final RouterInfoMediaManager$$ExternalSyntheticLambda3 mRouteListingPreferenceCallback;
    public final MediaRouter2 mRouter;
    public MediaRouter2.ScanToken mScanToken;
    public final TransferCallback mTransferCallback;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ControllerCallback extends MediaRouter2.ControllerCallback {
        public ControllerCallback() {
        }

        @Override // android.media.MediaRouter2.ControllerCallback
        public final void onControllerUpdated(MediaRouter2.RoutingController routingController) {
            RouterInfoMediaManager.this.refreshDevices();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class RouteCallback extends MediaRouter2.RouteCallback {
        public RouteCallback() {
        }

        public final void onPreferredFeaturesChanged(List list) {
            RouterInfoMediaManager.this.refreshDevices();
        }

        @Override // android.media.MediaRouter2.RouteCallback
        public final void onRoutesUpdated(List list) {
            RouterInfoMediaManager.this.refreshDevices();
        }
    }

    public RouterInfoMediaManager(Context context, String str, UserHandle userHandle, LocalBluetoothManager localBluetoothManager, MediaController mediaController) {
        super(context, str, userHandle, localBluetoothManager, mediaController);
        MediaRouter2 mediaRouter2;
        this.mExecutor = Executors.newSingleThreadExecutor();
        this.mRouteCallback = new RouteCallback();
        this.mTransferCallback = new TransferCallback();
        this.mControllerCallback = new ControllerCallback();
        this.mRouteListingPreferenceCallback = new RouterInfoMediaManager$$ExternalSyntheticLambda3(3, this);
        try {
            mediaRouter2 = MediaRouter2.getInstance(context, str, userHandle);
        } catch (IllegalArgumentException unused) {
            mediaRouter2 = null;
        }
        if (mediaRouter2 == null) {
            throw new InfoMediaManager.PackageNotAvailableException(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Package name ", str, " does not exist."));
        }
        this.mRouter = mediaRouter2;
        MediaRouter2Manager.getInstance(context);
    }

    @Override // com.android.settingslib.media.InfoMediaManager
    public final void deselectRoute(MediaRoute2Info mediaRoute2Info, RoutingSessionInfo routingSessionInfo) {
        MediaRouter2.RoutingController controller = this.mRouter.getController(routingSessionInfo.getId());
        if (controller != null) {
            controller.deselectRoute(mediaRoute2Info);
        }
    }

    @Override // com.android.settingslib.media.InfoMediaManager
    public final List getAvailableRoutesFromRouter() {
        return this.mRouter.getRoutes();
    }

    public final MediaRouter2.RoutingController getControllerForSession(RoutingSessionInfo routingSessionInfo) {
        return this.mRouter.getController(routingSessionInfo.getId());
    }

    @Override // com.android.settingslib.media.InfoMediaManager
    public final List getDeselectableRoutes(RoutingSessionInfo routingSessionInfo) {
        MediaRouter2.RoutingController controllerForSession = getControllerForSession(routingSessionInfo);
        return controllerForSession == null ? Collections.emptyList() : controllerForSession.getDeselectableRoutes();
    }

    @Override // com.android.settingslib.media.InfoMediaManager
    public final RouteListingPreference getRouteListingPreference() {
        return this.mRouter.getRouteListingPreference();
    }

    @Override // com.android.settingslib.media.InfoMediaManager
    public final List getRoutingSessionsForPackage() {
        return (List) this.mRouter.getControllers().stream().map(new RouterInfoMediaManager$$ExternalSyntheticLambda1()).collect(Collectors.toList());
    }

    @Override // com.android.settingslib.media.InfoMediaManager
    public final List getSelectableRoutes(RoutingSessionInfo routingSessionInfo) {
        MediaRouter2.RoutingController controllerForSession = getControllerForSession(routingSessionInfo);
        if (controllerForSession == null) {
            return Collections.emptyList();
        }
        final List<String> selectedRoutes = controllerForSession.getRoutingSessionInfo().getSelectedRoutes();
        return (List) controllerForSession.getSelectableRoutes().stream().filter(new Predicate() { // from class: com.android.settingslib.media.RouterInfoMediaManager$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return !selectedRoutes.contains(((MediaRoute2Info) obj).getId());
            }
        }).collect(Collectors.toList());
    }

    @Override // com.android.settingslib.media.InfoMediaManager
    public final List getSelectedRoutes(RoutingSessionInfo routingSessionInfo) {
        MediaRouter2.RoutingController controllerForSession = getControllerForSession(routingSessionInfo);
        return controllerForSession == null ? Collections.emptyList() : controllerForSession.getSelectedRoutes();
    }

    @Override // com.android.settingslib.media.InfoMediaManager
    public final List getTransferableRoutes(String str) {
        MediaRouter2.RoutingController routingController = this.mRouter.getControllers().get(r5.size() - 1);
        HashMap hashMap = new HashMap();
        routingController.getTransferableRoutes().forEach(new RouterInfoMediaManager$$ExternalSyntheticLambda3(0, hashMap));
        if (routingController.getRoutingSessionInfo().isSystemSession()) {
            final int i = 0;
            this.mRouter.getRoutes().stream().filter(new Predicate() { // from class: com.android.settingslib.media.RouterInfoMediaManager$$ExternalSyntheticLambda4
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    MediaRoute2Info mediaRoute2Info = (MediaRoute2Info) obj;
                    switch (i) {
                        case 0:
                            return !mediaRoute2Info.isSystemRoute();
                        default:
                            return mediaRoute2Info.isSystemRoute();
                    }
                }
            }).forEach(new RouterInfoMediaManager$$ExternalSyntheticLambda3(1, hashMap));
        } else {
            final int i2 = 1;
            this.mRouter.getRoutes().stream().filter(new Predicate() { // from class: com.android.settingslib.media.RouterInfoMediaManager$$ExternalSyntheticLambda4
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    MediaRoute2Info mediaRoute2Info = (MediaRoute2Info) obj;
                    switch (i2) {
                        case 0:
                            return !mediaRoute2Info.isSystemRoute();
                        default:
                            return mediaRoute2Info.isSystemRoute();
                    }
                }
            }).forEach(new RouterInfoMediaManager$$ExternalSyntheticLambda3(2, hashMap));
        }
        return new ArrayList(hashMap.values());
    }

    @Override // com.android.settingslib.media.InfoMediaManager
    public final void registerRouter() {
        this.mRouter.registerRouteCallback(this.mExecutor, this.mRouteCallback, RouteDiscoveryPreference.EMPTY);
        this.mRouter.registerRouteListingPreferenceUpdatedCallback(this.mExecutor, this.mRouteListingPreferenceCallback);
        this.mRouter.registerTransferCallback(this.mExecutor, this.mTransferCallback);
        this.mRouter.registerControllerCallback(this.mExecutor, this.mControllerCallback);
    }

    @Override // com.android.settingslib.media.InfoMediaManager
    public final void releaseSession(RoutingSessionInfo routingSessionInfo) {
        MediaRouter2.RoutingController controllerForSession = getControllerForSession(routingSessionInfo);
        if (controllerForSession != null) {
            controllerForSession.release();
        }
    }

    @Override // com.android.settingslib.media.InfoMediaManager
    public final void selectRoute(MediaRoute2Info mediaRoute2Info, RoutingSessionInfo routingSessionInfo) {
        MediaRouter2.RoutingController controller = this.mRouter.getController(routingSessionInfo.getId());
        if (controller != null) {
            controller.selectRoute(mediaRoute2Info);
        }
    }

    @Override // com.android.settingslib.media.InfoMediaManager
    public final void setRouteVolume(MediaRoute2Info mediaRoute2Info, int i) {
        this.mRouter.setRouteVolume(mediaRoute2Info, i);
    }

    @Override // com.android.settingslib.media.InfoMediaManager
    public final void startScanOnRouter() {
        synchronized (this) {
            try {
                if (this.mScanToken == null) {
                    this.mScanToken = this.mRouter.requestScan(new MediaRouter2.ScanRequest.Builder().build());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.android.settingslib.media.InfoMediaManager
    public final void stopScanOnRouter() {
        synchronized (this) {
            try {
                MediaRouter2.ScanToken scanToken = this.mScanToken;
                if (scanToken != null) {
                    this.mRouter.cancelScanRequest(scanToken);
                    this.mScanToken = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.android.settingslib.media.InfoMediaManager
    public final void transferToRoute(MediaRoute2Info mediaRoute2Info) {
        this.mRouter.transferTo(mediaRoute2Info);
    }

    @Override // com.android.settingslib.media.InfoMediaManager
    public final void unregisterRouter() {
        this.mRouter.unregisterControllerCallback(this.mControllerCallback);
        this.mRouter.unregisterTransferCallback(this.mTransferCallback);
        this.mRouter.unregisterRouteListingPreferenceUpdatedCallback(this.mRouteListingPreferenceCallback);
        this.mRouter.unregisterRouteCallback(this.mRouteCallback);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TransferCallback extends MediaRouter2.TransferCallback {
        public TransferCallback() {
        }

        public final void onRequestFailed(int i) {
            RouterInfoMediaManager routerInfoMediaManager = RouterInfoMediaManager.this;
            routerInfoMediaManager.getClass();
            Iterator it = new CopyOnWriteArrayList(routerInfoMediaManager.mCallbacks).iterator();
            while (it.hasNext()) {
                LocalMediaManager.MediaDeviceCallback mediaDeviceCallback = (LocalMediaManager.MediaDeviceCallback) it.next();
                synchronized (LocalMediaManager.this.mMediaDevicesLock) {
                    try {
                        for (MediaDevice mediaDevice : LocalMediaManager.this.mMediaDevices) {
                            if (mediaDevice.mState == 1) {
                                mediaDevice.mState = 3;
                            }
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                Iterator it2 = ((CopyOnWriteArrayList) LocalMediaManager.this.getCallbacks()).iterator();
                while (it2.hasNext()) {
                    ((LocalMediaManager.DeviceCallback) it2.next()).onRequestFailed(i);
                }
            }
        }

        @Override // android.media.MediaRouter2.TransferCallback
        public final void onStop(MediaRouter2.RoutingController routingController) {
            RouterInfoMediaManager.this.refreshDevices();
        }

        @Override // android.media.MediaRouter2.TransferCallback
        public final void onTransfer(MediaRouter2.RoutingController routingController, MediaRouter2.RoutingController routingController2) {
            RouterInfoMediaManager.this.rebuildDeviceList();
            RouterInfoMediaManager routerInfoMediaManager = RouterInfoMediaManager.this;
            MediaDevice mediaDevice = routerInfoMediaManager.mCurrentConnectedDevice;
            String id = mediaDevice != null ? mediaDevice.getId() : null;
            Iterator it = new CopyOnWriteArrayList(routerInfoMediaManager.mCallbacks).iterator();
            while (it.hasNext()) {
                LocalMediaManager localMediaManager = LocalMediaManager.this;
                MediaDevice mediaDeviceById = localMediaManager.getMediaDeviceById(id);
                if (mediaDeviceById == null) {
                    mediaDeviceById = localMediaManager.updateCurrentConnectedDevice();
                }
                localMediaManager.mCurrentConnectedDevice = mediaDeviceById;
                if (mediaDeviceById != null) {
                    mediaDeviceById.mState = 0;
                    Iterator it2 = ((CopyOnWriteArrayList) localMediaManager.getCallbacks()).iterator();
                    while (it2.hasNext()) {
                        ((LocalMediaManager.DeviceCallback) it2.next()).onSelectedDeviceStateChanged(mediaDeviceById);
                    }
                }
            }
        }

        @Override // android.media.MediaRouter2.TransferCallback
        public final void onTransferFailure(MediaRoute2Info mediaRoute2Info) {
        }
    }
}
