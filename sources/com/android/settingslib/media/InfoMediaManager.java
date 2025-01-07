package com.android.settingslib.media;

import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaRoute2Info;
import android.media.RouteListingPreference;
import android.media.RoutingSessionInfo;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.os.Process;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.android.settingslib.media.LocalMediaManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class InfoMediaManager {
    public static final boolean DEBUG = Log.isLoggable("InfoMediaManager", 3);
    public final LocalBluetoothManager mBluetoothManager;
    public final Context mContext;
    public MediaDevice mCurrentConnectedDevice;
    public MediaController.PlaybackInfo mLastKnownPlaybackInfo;
    public MediaController mMediaController;
    public final String mPackageName;
    public final UserHandle mUserHandle;
    public final List mMediaDevices = new CopyOnWriteArrayList();
    public final Collection mCallbacks = new CopyOnWriteArrayList();
    public final Map mPreferenceItemMap = new ConcurrentHashMap();
    public final MediaControllerCallback mMediaControllerCallback = new MediaControllerCallback();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Api34Impl {
        public static List arrangeRouteListByPreference(List list, List list2, List list3) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                linkedHashSet.add(((MediaRoute2Info) it.next()).getId());
            }
            Iterator it2 = list2.iterator();
            while (it2.hasNext()) {
                MediaRoute2Info mediaRoute2Info = (MediaRoute2Info) it2.next();
                if (mediaRoute2Info.isSystemRoute()) {
                    linkedHashSet.add(mediaRoute2Info.getId());
                }
            }
            final Map map = (Map) Stream.concat(list.stream(), list2.stream()).collect(Collectors.toMap(new InfoMediaManager$Api34Impl$$ExternalSyntheticLambda0(), Function.identity(), new InfoMediaManager$Api34Impl$$ExternalSyntheticLambda1()));
            Iterator it3 = list3.iterator();
            while (it3.hasNext()) {
                MediaRoute2Info mediaRoute2Info2 = (MediaRoute2Info) map.get(((RouteListingPreference.Item) it3.next()).getRouteId());
                if (mediaRoute2Info2 != null) {
                    linkedHashSet.add(mediaRoute2Info2.getId());
                }
            }
            Stream stream = linkedHashSet.stream();
            Objects.requireNonNull(map);
            return (List) stream.map(new Function() { // from class: com.android.settingslib.media.InfoMediaManager$Api34Impl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return (MediaRoute2Info) map.get((String) obj);
                }
            }).collect(Collectors.toList());
        }

        public static synchronized List filterDuplicatedIds(List list) {
            ArrayList arrayList;
            synchronized (Api34Impl.class) {
                arrayList = new ArrayList();
                HashSet hashSet = new HashSet();
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    MediaRoute2Info mediaRoute2Info = (MediaRoute2Info) it.next();
                    if (Collections.disjoint(mediaRoute2Info.getDeduplicationIds(), hashSet)) {
                        arrayList.add(mediaRoute2Info);
                        hashSet.addAll(mediaRoute2Info.getDeduplicationIds());
                    }
                }
            }
            return arrayList;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MediaControllerCallback extends MediaController.Callback {
        public MediaControllerCallback() {
        }

        @Override // android.media.session.MediaController.Callback
        public final void onAudioInfoChanged(MediaController.PlaybackInfo playbackInfo) {
            if (playbackInfo.getPlaybackType() != InfoMediaManager.this.mLastKnownPlaybackInfo.getPlaybackType() || !TextUtils.equals(playbackInfo.getVolumeControlId(), InfoMediaManager.this.mLastKnownPlaybackInfo.getVolumeControlId())) {
                InfoMediaManager.this.refreshDevices();
            }
            InfoMediaManager.this.mLastKnownPlaybackInfo = playbackInfo;
        }

        @Override // android.media.session.MediaController.Callback
        public final void onSessionDestroyed() {
            InfoMediaManager infoMediaManager = InfoMediaManager.this;
            infoMediaManager.mMediaController = null;
            infoMediaManager.refreshDevices();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public class PackageNotAvailableException extends Exception {
        public PackageNotAvailableException(String str) {
            super(str);
        }
    }

    public InfoMediaManager(Context context, String str, UserHandle userHandle, LocalBluetoothManager localBluetoothManager, MediaController mediaController) {
        this.mContext = context;
        this.mBluetoothManager = localBluetoothManager;
        this.mPackageName = str;
        this.mMediaController = mediaController;
        if (mediaController != null) {
            this.mLastKnownPlaybackInfo = mediaController.getPlaybackInfo();
        }
    }

    public static InfoMediaManager createInstance(Context context, String str, UserHandle userHandle, LocalBluetoothManager localBluetoothManager, MediaSession.Token token) {
        MediaController mediaController = token != null ? new MediaController(context, token) : null;
        if (TextUtils.isEmpty(str)) {
            str = context.getPackageName();
        }
        if (userHandle == null) {
            userHandle = Process.myUserHandle();
        }
        try {
            return new RouterInfoMediaManager(context, str, userHandle, localBluetoothManager, mediaController);
        } catch (PackageNotAvailableException unused) {
            Log.w("InfoMediaManager", "Returning a no-op InfoMediaManager for package " + str);
            return new NoOpInfoMediaManager(context, str, userHandle, localBluetoothManager, mediaController);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void addMediaDevice(android.media.MediaRoute2Info r8, android.media.RoutingSessionInfo r9) {
        /*
            r7 = this;
            int r0 = r8.getType()
            r1 = 4
            if (r0 == 0) goto La3
            r2 = 26
            java.lang.String r3 = "InfoMediaManager"
            r4 = 0
            if (r0 == r2) goto L5e
            r2 = 29
            if (r0 == r2) goto L47
            r2 = 2000(0x7d0, float:2.803E-42)
            if (r0 == r2) goto La3
            r2 = 2
            if (r0 == r2) goto L47
            r2 = 3
            if (r0 == r2) goto L47
            if (r0 == r1) goto L47
            r2 = 22
            if (r0 == r2) goto L47
            r2 = 23
            if (r0 == r2) goto L5e
            switch(r0) {
                case 8: goto L5e;
                case 9: goto L47;
                case 10: goto L47;
                case 11: goto L47;
                case 12: goto L47;
                case 13: goto L47;
                default: goto L29;
            }
        L29:
            switch(r0) {
                case 1001: goto La3;
                case 1002: goto La3;
                case 1003: goto L33;
                case 1004: goto La3;
                case 1005: goto La3;
                case 1006: goto La3;
                case 1007: goto La3;
                case 1008: goto La3;
                case 1009: goto La3;
                case 1010: goto La3;
                default: goto L2c;
            }
        L2c:
            java.lang.String r2 = "addMediaDevice() unknown device type : "
            androidx.compose.foundation.text.input.internal.RecordingInputConnection$$ExternalSyntheticOutline0.m(r2, r3, r0)
            goto Lb6
        L33:
            com.android.settingslib.media.ComplexMediaDevice r4 = new com.android.settingslib.media.ComplexMediaDevice
            android.content.Context r0 = r7.mContext
            java.util.Map r2 = r7.mPreferenceItemMap
            java.lang.String r3 = r8.getId()
            java.lang.Object r2 = r2.get(r3)
            android.media.RouteListingPreference$Item r2 = (android.media.RouteListingPreference.Item) r2
            r4.<init>(r0, r8, r2)
            goto Lb6
        L47:
            com.android.settingslib.media.PhoneMediaDevice r0 = new com.android.settingslib.media.PhoneMediaDevice
            android.content.Context r2 = r7.mContext
            java.util.Map r3 = r7.mPreferenceItemMap
            java.lang.String r5 = r8.getId()
            java.util.concurrent.ConcurrentHashMap r3 = (java.util.concurrent.ConcurrentHashMap) r3
            java.lang.Object r3 = r3.getOrDefault(r5, r4)
            android.media.RouteListingPreference$Item r3 = (android.media.RouteListingPreference.Item) r3
            r0.<init>(r2, r8, r3)
            r4 = r0
            goto Lb6
        L5e:
            java.lang.String r0 = r8.getAddress()
            if (r0 != 0) goto L76
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "Ignoring bluetooth route with no set address: "
            r0.<init>(r2)
            r0.append(r8)
            java.lang.String r0 = r0.toString()
            android.util.Log.e(r3, r0)
            goto Lb6
        L76:
            android.bluetooth.BluetoothAdapter r0 = android.bluetooth.BluetoothAdapter.getDefaultAdapter()
            java.lang.String r2 = r8.getAddress()
            android.bluetooth.BluetoothDevice r0 = r0.getRemoteDevice(r2)
            com.android.settingslib.bluetooth.LocalBluetoothManager r2 = r7.mBluetoothManager
            com.android.settingslib.bluetooth.CachedBluetoothDeviceManager r2 = r2.mCachedDeviceManager
            com.android.settingslib.bluetooth.CachedBluetoothDevice r0 = r2.findDevice(r0)
            if (r0 == 0) goto Lb6
            com.android.settingslib.media.BluetoothMediaDevice r2 = new com.android.settingslib.media.BluetoothMediaDevice
            android.content.Context r3 = r7.mContext
            java.util.Map r5 = r7.mPreferenceItemMap
            java.lang.String r6 = r8.getId()
            java.util.concurrent.ConcurrentHashMap r5 = (java.util.concurrent.ConcurrentHashMap) r5
            java.lang.Object r4 = r5.getOrDefault(r6, r4)
            android.media.RouteListingPreference$Item r4 = (android.media.RouteListingPreference.Item) r4
            r2.<init>(r3, r0, r8, r4)
            r4 = r2
            goto Lb6
        La3:
            com.android.settingslib.media.InfoMediaDevice r4 = new com.android.settingslib.media.InfoMediaDevice
            android.content.Context r0 = r7.mContext
            java.util.Map r2 = r7.mPreferenceItemMap
            java.lang.String r3 = r8.getId()
            java.lang.Object r2 = r2.get(r3)
            android.media.RouteListingPreference$Item r2 = (android.media.RouteListingPreference.Item) r2
            r4.<init>(r0, r8, r2)
        Lb6:
            if (r4 == 0) goto Lcf
            java.util.List r9 = r9.getSelectedRoutes()
            java.lang.String r8 = r8.getId()
            boolean r8 = r9.contains(r8)
            if (r8 == 0) goto Lc8
            r4.mState = r1
        Lc8:
            java.util.List r7 = r7.mMediaDevices
            java.util.concurrent.CopyOnWriteArrayList r7 = (java.util.concurrent.CopyOnWriteArrayList) r7
            r7.add(r4)
        Lcf:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settingslib.media.InfoMediaManager.addMediaDevice(android.media.MediaRoute2Info, android.media.RoutingSessionInfo):void");
    }

    public abstract void deselectRoute(MediaRoute2Info mediaRoute2Info, RoutingSessionInfo routingSessionInfo);

    public final void dispatchDeviceListAdded(List list) {
        Iterator it = new CopyOnWriteArrayList(this.mCallbacks).iterator();
        while (it.hasNext()) {
            LocalMediaManager.MediaDeviceCallback mediaDeviceCallback = (LocalMediaManager.MediaDeviceCallback) it.next();
            ArrayList arrayList = new ArrayList(list);
            synchronized (LocalMediaManager.this.mMediaDevicesLock) {
                try {
                    LocalMediaManager.this.mMediaDevices.clear();
                    LocalMediaManager.this.mMediaDevices.addAll(arrayList);
                    Iterator it2 = arrayList.iterator();
                    while (it2.hasNext()) {
                        int i = ((MediaDevice) it2.next()).mType;
                        if (i == 2 || i == 3 || i == 1) {
                            PackageManager packageManager = LocalMediaManager.this.mContext.getPackageManager();
                            if (!packageManager.hasSystemFeature("android.hardware.type.television") && !packageManager.hasSystemFeature("android.software.leanback")) {
                                BluetoothMediaDevice mutingExpectedDevice = mediaDeviceCallback.getMutingExpectedDevice();
                                if (mutingExpectedDevice != null) {
                                    LocalMediaManager.this.mMediaDevices.add(mutingExpectedDevice);
                                }
                            }
                            LocalMediaManager.this.mMediaDevices.addAll(mediaDeviceCallback.buildDisconnectedBluetoothDevice());
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            LocalMediaManager localMediaManager = LocalMediaManager.this;
            MediaDevice mediaDevice = localMediaManager.mInfoMediaManager.mCurrentConnectedDevice;
            if (mediaDevice == null) {
                mediaDevice = localMediaManager.updateCurrentConnectedDevice();
            }
            localMediaManager.mCurrentConnectedDevice = mediaDevice;
            LocalMediaManager localMediaManager2 = LocalMediaManager.this;
            localMediaManager2.getClass();
            ArrayList arrayList2 = new ArrayList(localMediaManager2.mMediaDevices);
            Iterator it3 = ((CopyOnWriteArrayList) localMediaManager2.getCallbacks()).iterator();
            while (it3.hasNext()) {
                ((LocalMediaManager.DeviceCallback) it3.next()).onDeviceListUpdate(arrayList2);
            }
            MediaDevice mediaDevice2 = LocalMediaManager.this.mOnTransferBluetoothDevice;
            if (mediaDevice2 != null && mediaDevice2.isConnected()) {
                LocalMediaManager localMediaManager3 = LocalMediaManager.this;
                localMediaManager3.connectDevice(localMediaManager3.mOnTransferBluetoothDevice);
                LocalMediaManager localMediaManager4 = LocalMediaManager.this;
                MediaDevice mediaDevice3 = localMediaManager4.mOnTransferBluetoothDevice;
                mediaDevice3.mState = 0;
                Iterator it4 = ((CopyOnWriteArrayList) localMediaManager4.getCallbacks()).iterator();
                while (it4.hasNext()) {
                    ((LocalMediaManager.DeviceCallback) it4.next()).onSelectedDeviceStateChanged(mediaDevice3);
                }
                LocalMediaManager.this.mOnTransferBluetoothDevice = null;
            }
        }
    }

    public final RoutingSessionInfo getActiveRoutingSession() {
        List<RoutingSessionInfo> routingSessionsForPackage = getRoutingSessionsForPackage();
        RoutingSessionInfo routingSessionInfo = (RoutingSessionInfo) routingSessionsForPackage.get(routingSessionsForPackage.size() - 1);
        MediaController mediaController = this.mMediaController;
        if (mediaController == null) {
            return routingSessionInfo;
        }
        MediaController.PlaybackInfo playbackInfo = mediaController.getPlaybackInfo();
        if (playbackInfo.getPlaybackType() == 1) {
            return (RoutingSessionInfo) routingSessionsForPackage.get(0);
        }
        String volumeControlId = playbackInfo.getVolumeControlId();
        for (RoutingSessionInfo routingSessionInfo2 : routingSessionsForPackage) {
            if (TextUtils.equals(volumeControlId, routingSessionInfo2.getId())) {
                return routingSessionInfo2;
            }
            if (TextUtils.equals(volumeControlId, routingSessionInfo2.getOriginalId()) && TextUtils.equals(this.mMediaController.getPackageName(), routingSessionInfo2.getOwnerPackageName())) {
                return routingSessionInfo2;
            }
        }
        return routingSessionInfo;
    }

    public final synchronized List getAvailableRoutes(RoutingSessionInfo routingSessionInfo) {
        List arrayList;
        try {
            arrayList = new ArrayList();
            List selectedRoutes = getSelectedRoutes(routingSessionInfo);
            arrayList.addAll(selectedRoutes);
            arrayList.addAll(getSelectableRoutes(routingSessionInfo));
            for (MediaRoute2Info mediaRoute2Info : getTransferableRoutes(this.mPackageName)) {
                Iterator it = arrayList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        arrayList.add(mediaRoute2Info);
                        break;
                    }
                    if (TextUtils.equals(mediaRoute2Info.getId(), ((MediaRoute2Info) it.next()).getId())) {
                        break;
                    }
                }
            }
            RouteListingPreference routeListingPreference = getRouteListingPreference();
            if (routeListingPreference != null) {
                ArrayList arrayList2 = new ArrayList();
                for (RouteListingPreference.Item item : routeListingPreference.getItems()) {
                    if ((item.getFlags() & 4) != 0) {
                        arrayList2.add(0, item);
                    } else {
                        arrayList2.add(item);
                    }
                }
                arrayList = Api34Impl.arrangeRouteListByPreference(selectedRoutes, getAvailableRoutesFromRouter(), arrayList2);
            }
        } catch (Throwable th) {
            throw th;
        }
        return Api34Impl.filterDuplicatedIds(arrayList);
    }

    public abstract List getAvailableRoutesFromRouter();

    public abstract List getDeselectableRoutes(RoutingSessionInfo routingSessionInfo);

    public abstract RouteListingPreference getRouteListingPreference();

    public abstract List getRoutingSessionsForPackage();

    public abstract List getSelectableRoutes(RoutingSessionInfo routingSessionInfo);

    public abstract List getSelectedRoutes(RoutingSessionInfo routingSessionInfo);

    public abstract List getTransferableRoutes(String str);

    public final void rebuildDeviceList() {
        synchronized (this) {
            try {
                ((CopyOnWriteArrayList) this.mMediaDevices).clear();
                RoutingSessionInfo activeRoutingSession = getActiveRoutingSession();
                for (MediaRoute2Info mediaRoute2Info : getAvailableRoutes(activeRoutingSession)) {
                    if (DEBUG) {
                        Log.d("InfoMediaManager", "buildAvailableRoutes() route : " + ((Object) mediaRoute2Info.getName()) + ", volume : " + mediaRoute2Info.getVolume() + ", type : " + mediaRoute2Info.getType());
                    }
                    addMediaDevice(mediaRoute2Info, activeRoutingSession);
                }
                if (!((CopyOnWriteArrayList) this.mMediaDevices).isEmpty()) {
                    this.mCurrentConnectedDevice = (MediaDevice) ((CopyOnWriteArrayList) this.mMediaDevices).get(0);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final synchronized void refreshDevices() {
        rebuildDeviceList();
        dispatchDeviceListAdded(this.mMediaDevices);
    }

    public abstract void registerRouter();

    public abstract void releaseSession(RoutingSessionInfo routingSessionInfo);

    public abstract void selectRoute(MediaRoute2Info mediaRoute2Info, RoutingSessionInfo routingSessionInfo);

    public abstract void setRouteVolume(MediaRoute2Info mediaRoute2Info, int i);

    public abstract void startScanOnRouter();

    public abstract void stopScanOnRouter();

    public abstract void transferToRoute(MediaRoute2Info mediaRoute2Info);

    public abstract void unregisterRouter();
}
