package com.google.android.systemui.dreamliner;

import android.content.Context;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.os.Process;
import android.os.ResultReceiver;
import android.os.ServiceManager;
import android.os.ServiceSpecificException;
import android.util.Log;
import androidx.compose.foundation.text.input.internal.RecordingInputConnection$$ExternalSyntheticOutline0;
import androidx.viewpager.widget.ViewPager$$ExternalSyntheticOutline0;
import com.android.keyguard.ClockEventController$zenModeCallback$1$$ExternalSyntheticOutline0;
import com.android.settingslib.bluetooth.LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0;
import com.android.systemui.statusbar.KeyguardIndicationController$$ExternalSyntheticLambda14;
import com.android.wm.shell.R;
import com.google.android.systemui.dreamliner.WirelessCharger;
import com.google.android.systemui.dreamliner.WirelessChargerCommander$doChallenge$1;
import com.google.android.systemui.dreamliner.WirelessChargerCommander$getFanInfo$1;
import com.google.android.systemui.dreamliner.WirelessChargerCommander$setFan$1;
import com.google.android.systemui.statusbar.KeyguardIndicationControllerGoogle;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import kotlin.collections.CollectionsKt;
import vendor.google.wireless_charger.AlignInfo;
import vendor.google.wireless_charger.DockPresent;
import vendor.google.wireless_charger.FanInfo;
import vendor.google.wireless_charger.IWirelessCharger;
import vendor.google.wireless_charger.IWirelessChargerFanLevelChangedCallback;
import vendor.google.wireless_charger.IWirelessChargerInfoCallback;
import vendor.google.wireless_charger.KeyExchangeResponse;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class WirelessChargerImpl extends WirelessCharger implements IBinder.DeathRecipient {
    public static final /* synthetic */ int $r8$clinit = 0;
    public static final long MAX_POLLING_TIMEOUT_NS = TimeUnit.SECONDS.toNanos(5);
    public final Context mContext;
    public long mPollingStartedTimeNs;
    public IWirelessCharger mWirelessCharger;
    public final Handler mHandler = new Handler(Looper.getMainLooper());
    public final WirelessChargerImpl$$ExternalSyntheticLambda0 mRunnable = new Runnable() { // from class: com.google.android.systemui.dreamliner.WirelessChargerImpl$$ExternalSyntheticLambda0
        @Override // java.lang.Runnable
        public final void run() {
            WirelessChargerImpl wirelessChargerImpl = WirelessChargerImpl.this;
            int i = WirelessChargerImpl.$r8$clinit;
            if (wirelessChargerImpl.initHALInterface()) {
                try {
                    DockPresent isDockPresent = ((IWirelessCharger.Stub.Proxy) wirelessChargerImpl.mWirelessCharger).isDockPresent();
                    if (System.nanoTime() >= wirelessChargerImpl.mPollingStartedTimeNs + WirelessChargerImpl.MAX_POLLING_TIMEOUT_NS || isDockPresent.id != 0) {
                        WirelessCharger.IsDockPresentCallback isDockPresentCallback = wirelessChargerImpl.mCallback;
                        if (isDockPresentCallback != null) {
                            isDockPresentCallback.onCallback(isDockPresent.docked, isDockPresent.type, isDockPresent.orientation, isDockPresent.isGetInfoSupported, isDockPresent.id, isDockPresent.ptmc);
                            wirelessChargerImpl.mCallback = null;
                        }
                    } else {
                        wirelessChargerImpl.mHandler.postDelayed(wirelessChargerImpl.mRunnable, 100L);
                    }
                } catch (Exception e) {
                    Log.i("Dreamliner-WLC_HAL", "isDockPresent fail: " + e.getMessage());
                }
            }
        }
    };
    public final CopyOnWriteArraySet mFanLevelEventListeners = new CopyOnWriteArraySet();
    public final AtomicBoolean mIsFanLevelCallbackRegistered = new AtomicBoolean(false);
    public final AnonymousClass1 mIWirelessChargerFanLevelChangedCallback = new AnonymousClass1();
    public WirelessCharger.IsDockPresentCallback mCallback = null;

    /* JADX WARN: Type inference failed for: r0v1, types: [com.google.android.systemui.dreamliner.WirelessChargerImpl$$ExternalSyntheticLambda0] */
    public WirelessChargerImpl(Context context) {
        this.mContext = context;
    }

    public static Bundle convertFanDetailedInfo(byte b, FanInfo fanInfo) {
        Bundle bundle = new Bundle();
        bundle.putByte("fan_id", b);
        bundle.putByte("fan_mode", fanInfo.fanMode);
        bundle.putInt("fan_current_rpm", fanInfo.currentRpm);
        bundle.putInt("fan_min_rpm", fanInfo.minimumRpm);
        bundle.putInt("fan_max_rpm", fanInfo.maximumRpm);
        bundle.putByte("fan_type", fanInfo.type);
        bundle.putByte("fan_count", fanInfo.count);
        return bundle;
    }

    public static ArrayList convertPrimitiveArrayToArrayList(byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (byte b : bArr) {
            arrayList.add(Byte.valueOf(b));
        }
        return arrayList;
    }

    public static int mapError(Exception exc) {
        if (!(exc instanceof ServiceSpecificException)) {
            return 2;
        }
        int i = ((ServiceSpecificException) exc).errorCode;
        if (i >= 0) {
            return i;
        }
        ClockEventController$zenModeCallback$1$$ExternalSyntheticOutline0.m("Got a ServiceSpecificExepction but failed to map erroCode: ", "Dreamliner-WLC_HAL", i);
        return 2;
    }

    @Override // com.google.android.systemui.dreamliner.WirelessCharger
    public void asyncIsDockPresent(WirelessCharger.IsDockPresentCallback isDockPresentCallback) {
        if (initHALInterface()) {
            this.mPollingStartedTimeNs = System.nanoTime();
            this.mCallback = isDockPresentCallback;
            this.mHandler.removeCallbacks(this.mRunnable);
            this.mHandler.postDelayed(this.mRunnable, 100L);
        }
    }

    @Override // android.os.IBinder.DeathRecipient
    public final void binderDied() {
        Log.i("Dreamliner-WLC_HAL", "serviceDied");
        this.mWirelessCharger = null;
    }

    @Override // com.google.android.systemui.dreamliner.WirelessCharger
    public void challenge(byte b, byte[] bArr, WirelessCharger.ChallengeCallback challengeCallback) {
        int mapError;
        ArrayList arrayList;
        if (initHALInterface()) {
            Bundle bundle = null;
            try {
                arrayList = convertPrimitiveArrayToArrayList(((IWirelessCharger.Stub.Proxy) this.mWirelessCharger).challenge(b, bArr));
                mapError = 0;
            } catch (Exception e) {
                mapError = mapError(e);
                Log.i("Dreamliner-WLC_HAL", "challenge fail: " + e.getMessage());
                arrayList = null;
            }
            WirelessChargerCommander$doChallenge$1.AnonymousClass1 anonymousClass1 = (WirelessChargerCommander$doChallenge$1.AnonymousClass1) challengeCallback;
            anonymousClass1.getClass();
            Log.d("WirelessChargerCommander", "C() result: " + mapError);
            if (mapError != 0 || arrayList == null) {
                anonymousClass1.$resultReceiver.send(mapError, null);
                return;
            }
            Log.d("WirelessChargerCommander", "C() response: " + arrayList);
            ResultReceiver resultReceiver = anonymousClass1.$resultReceiver;
            anonymousClass1.this$0.getClass();
            if (!arrayList.isEmpty()) {
                bundle = new Bundle();
                bundle.putByteArray("challenge_response", CollectionsKt.toByteArray(arrayList));
            }
            resultReceiver.send(0, bundle);
        }
    }

    @Override // com.google.android.systemui.dreamliner.WirelessCharger
    public void getFanInformation(byte b, WirelessCharger.GetFanInformationCallback getFanInformationCallback) {
        int mapError;
        Bundle bundle;
        if (initHALInterface()) {
            Log.d("Dreamliner-WLC_HAL", "command=0");
            try {
                FanInfo fanInfo = ((IWirelessCharger.Stub.Proxy) this.mWirelessCharger).getFanInfo(b);
                Log.d("Dreamliner-WLC_HAL", "command=0, i=" + ((int) b) + ", m=" + ((int) fanInfo.fanMode) + ", cr=" + fanInfo.currentRpm + ", mir=" + fanInfo.minimumRpm + ", mxr=" + fanInfo.maximumRpm + ", t=" + ((int) fanInfo.type) + ", c=" + ((int) fanInfo.count));
                bundle = convertFanDetailedInfo(b, fanInfo);
                mapError = 0;
            } catch (Exception e) {
                mapError = mapError(e);
                Log.i("Dreamliner-WLC_HAL", "command=0 fail: " + e.getMessage());
                bundle = null;
            }
            WirelessChargerCommander$getFanInfo$1.AnonymousClass1 anonymousClass1 = (WirelessChargerCommander$getFanInfo$1.AnonymousClass1) getFanInformationCallback;
            anonymousClass1.getClass();
            StringBuilder sb = new StringBuilder("GFI(), result=");
            sb.append(mapError);
            sb.append(", i=");
            LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0.m(sb, anonymousClass1.$fanId, "WirelessChargerCommander");
            if (mapError != 0 || bundle == null) {
                anonymousClass1.$resultReceiver.send(mapError, null);
                return;
            }
            Byte b2 = bundle.getByte("fan_id", (byte) -1);
            Byte b3 = bundle.getByte("fan_mode", (byte) -1);
            int i = bundle.getInt("fan_current_rpm", -1);
            int i2 = bundle.getInt("fan_min_rpm", -1);
            int i3 = bundle.getInt("fan_max_rpm", -1);
            Byte b4 = bundle.getByte("fan_type", (byte) -1);
            Byte b5 = bundle.getByte("fan_count", (byte) -1);
            StringBuilder sb2 = new StringBuilder("GFI() response: i=");
            sb2.append(b2);
            sb2.append(", m=");
            sb2.append(b3);
            sb2.append(", cr=");
            ViewPager$$ExternalSyntheticOutline0.m(sb2, i, ", mnr=", i2, ", mxr=");
            sb2.append(i3);
            sb2.append(", t=");
            sb2.append(b4);
            sb2.append(", c=");
            sb2.append(b5);
            Log.d("WirelessChargerCommander", sb2.toString());
            anonymousClass1.$resultReceiver.send(0, bundle);
        }
    }

    public void getFanSimpleInformation(byte b, WirelessCharger.GetFanSimpleInformationCallback getFanSimpleInformationCallback) {
        if (initHALInterface()) {
            Log.d("Dreamliner-WLC_HAL", "command=3");
            try {
                FanInfo fanInfo = ((IWirelessCharger.Stub.Proxy) this.mWirelessCharger).getFanInfo(b);
                Bundle bundle = new Bundle();
                bundle.putByte("fan_id", b);
                bundle.putByte("fan_mode", fanInfo.fanMode);
                bundle.putInt("fan_current_rpm", fanInfo.currentRpm);
            } catch (Exception e) {
                mapError(e);
                Log.i("Dreamliner-WLC_HAL", "command=3 fail: " + e.getMessage());
            }
            getFanSimpleInformationCallback.onCallback();
        }
    }

    @Override // com.google.android.systemui.dreamliner.WirelessCharger
    public void getInformation(WirelessCharger.GetInformationCallback getInformationCallback) {
        int mapError;
        DockInfo dockInfo;
        if (initHALInterface()) {
            Bundle bundle = null;
            try {
                vendor.google.wireless_charger.DockInfo information = ((IWirelessCharger.Stub.Proxy) this.mWirelessCharger).getInformation();
                String str = information.manufacturer;
                String str2 = information.model;
                String str3 = information.serial;
                int intValue = Byte.valueOf(information.type).intValue();
                DockInfo dockInfo2 = new DockInfo();
                dockInfo2.manufacturer = str;
                dockInfo2.model = str2;
                dockInfo2.serialNumber = str3;
                dockInfo2.accessoryType = intValue;
                dockInfo = dockInfo2;
                mapError = 0;
            } catch (Exception e) {
                mapError = mapError(e);
                Log.i("Dreamliner-WLC_HAL", "getInformation fail: " + e.getMessage());
                dockInfo = null;
            }
            WirelessChargerCommander$setFan$1.AnonymousClass1 anonymousClass1 = (WirelessChargerCommander$setFan$1.AnonymousClass1) getInformationCallback;
            anonymousClass1.getClass();
            Log.d("WirelessChargerCommander", "GI() result: " + mapError);
            if (mapError != 0) {
                if (mapError != 1) {
                    ((ResultReceiver) anonymousClass1.$resultReceiver).send(mapError, null);
                    return;
                }
                return;
            }
            Log.d("WirelessChargerCommander", "GI() response: di=" + dockInfo);
            ResultReceiver resultReceiver = (ResultReceiver) anonymousClass1.$resultReceiver;
            if (dockInfo != null) {
                bundle = new Bundle();
                bundle.putString("manufacturer", dockInfo.manufacturer);
                bundle.putString("model", dockInfo.model);
                bundle.putString("serialNumber", dockInfo.serialNumber);
                bundle.putInt("accessoryType", dockInfo.accessoryType);
            }
            resultReceiver.send(0, bundle);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v6, types: [vendor.google.wireless_charger.IWirelessCharger] */
    public final boolean initHALInterface() {
        IWirelessCharger.Stub.Proxy proxy;
        if (this.mWirelessCharger != null) {
            return true;
        }
        try {
            IBinder service = ServiceManager.getService("vendor.google.wireless_charger.IWirelessCharger/default");
            if (service != null) {
                int i = IWirelessCharger.Stub.$r8$clinit;
                IInterface queryLocalInterface = service.queryLocalInterface(IWirelessCharger.DESCRIPTOR);
                if (queryLocalInterface == null || !(queryLocalInterface instanceof IWirelessCharger)) {
                    IWirelessCharger.Stub.Proxy proxy2 = new IWirelessCharger.Stub.Proxy();
                    proxy2.mRemote = service;
                    proxy = proxy2;
                } else {
                    proxy = (IWirelessCharger) queryLocalInterface;
                }
                this.mWirelessCharger = proxy;
                service.linkToDeath(this, 0);
                Log.i("Dreamliner-WLC_HAL", "mWirelessCharger service connected!!!!");
            }
        } catch (Exception e) {
            Log.i("Dreamliner-WLC_HAL", "WirelessCharger HAL not found: " + e.getMessage());
            this.mWirelessCharger = null;
        }
        return this.mWirelessCharger != null;
    }

    @Override // com.google.android.systemui.dreamliner.WirelessCharger
    public void keyExchange(byte[] bArr, WirelessCharger.KeyExchangeCallback keyExchangeCallback) {
        ArrayList arrayList;
        int mapError;
        byte b;
        WirelessChargerCommander$doChallenge$1.AnonymousClass1 anonymousClass1;
        KeyExchangeResponse keyExchange;
        if (initHALInterface()) {
            Bundle bundle = null;
            try {
                keyExchange = ((IWirelessCharger.Stub.Proxy) this.mWirelessCharger).keyExchange(bArr);
                arrayList = convertPrimitiveArrayToArrayList(keyExchange.dockPublicKey);
            } catch (Exception e) {
                e = e;
                arrayList = null;
            }
            try {
                b = keyExchange.dockIdentifier;
                mapError = 0;
            } catch (Exception e2) {
                e = e2;
                mapError = mapError(e);
                Log.i("Dreamliner-WLC_HAL", "keyExchange fail: " + e.getMessage());
                b = -1;
                anonymousClass1 = (WirelessChargerCommander$doChallenge$1.AnonymousClass1) keyExchangeCallback;
                anonymousClass1.getClass();
                Log.d("WirelessChargerCommander", "KE() result: " + mapError);
                if (mapError == 0) {
                }
                anonymousClass1.$resultReceiver.send(mapError, null);
                return;
            }
            anonymousClass1 = (WirelessChargerCommander$doChallenge$1.AnonymousClass1) keyExchangeCallback;
            anonymousClass1.getClass();
            Log.d("WirelessChargerCommander", "KE() result: " + mapError);
            if (mapError == 0 || arrayList == null) {
                anonymousClass1.$resultReceiver.send(mapError, null);
                return;
            }
            Log.d("WirelessChargerCommander", "KE() response: pk=" + arrayList);
            ResultReceiver resultReceiver = anonymousClass1.$resultReceiver;
            anonymousClass1.this$0.getClass();
            if (!arrayList.isEmpty()) {
                bundle = new Bundle();
                bundle.putByte("dock_id", b);
                bundle.putByteArray("dock_public_key", CollectionsKt.toByteArray(arrayList));
            }
            resultReceiver.send(0, bundle);
        }
    }

    @Override // com.google.android.systemui.dreamliner.WirelessCharger
    public void registerAlignInfo(WirelessCharger.AlignInfoListener alignInfoListener) {
        if (initHALInterface()) {
            try {
                ((IWirelessCharger.Stub.Proxy) this.mWirelessCharger).registerCallback(new WirelessChargerInfoCallback(alignInfoListener));
            } catch (Exception e) {
                Log.i("Dreamliner-WLC_HAL", "register alignInfo callback fail: " + e.getMessage());
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    @Override // com.google.android.systemui.dreamliner.WirelessCharger
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setFan(byte r16, byte r17, int r18, com.google.android.systemui.dreamliner.WirelessCharger.SetFanCallback r19) {
        /*
            r15 = this;
            r0 = r16
            r1 = r17
            r2 = r18
            java.lang.String r3 = "fan_current_rpm"
            java.lang.String r4 = "fan_mode"
            java.lang.String r5 = "fan_id"
            java.lang.String r6 = "command=1 spending time: "
            boolean r7 = r15.initHALInterface()
            if (r7 != 0) goto L15
            return
        L15:
            java.lang.String r7 = "command=1, i="
            java.lang.String r8 = ", m="
            java.lang.String r9 = ", r="
            java.lang.StringBuilder r7 = androidx.compose.foundation.text.ValidatingOffsetMappingKt$$ExternalSyntheticOutline0.m(r0, r1, r7, r8, r9)
            java.lang.String r9 = "Dreamliner-WLC_HAL"
            com.android.settingslib.bluetooth.LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0.m(r7, r2, r9)
            r7 = 0
            r10 = 0
            long r11 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Exception -> L60
            r13 = r15
            vendor.google.wireless_charger.IWirelessCharger r13 = r13.mWirelessCharger     // Catch: java.lang.Exception -> L60
            char r2 = (char) r2     // Catch: java.lang.Exception -> L60
            vendor.google.wireless_charger.IWirelessCharger$Stub$Proxy r13 = (vendor.google.wireless_charger.IWirelessCharger.Stub.Proxy) r13     // Catch: java.lang.Exception -> L60
            vendor.google.wireless_charger.FanInfo r1 = r13.setFan(r0, r1, r2)     // Catch: java.lang.Exception -> L60
            android.os.Bundle r2 = new android.os.Bundle     // Catch: java.lang.Exception -> L60
            r2.<init>()     // Catch: java.lang.Exception -> L60
            r2.putByte(r5, r0)     // Catch: java.lang.Exception -> L60
            byte r0 = r1.fanMode     // Catch: java.lang.Exception -> L60
            r2.putByte(r4, r0)     // Catch: java.lang.Exception -> L60
            char r0 = r1.currentRpm     // Catch: java.lang.Exception -> L60
            r2.putInt(r3, r0)     // Catch: java.lang.Exception -> L60
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L5c
            r0.<init>(r6)     // Catch: java.lang.Exception -> L5c
            long r13 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Exception -> L5c
            long r13 = r13 - r11
            r0.append(r13)     // Catch: java.lang.Exception -> L5c
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Exception -> L5c
            android.util.Log.d(r9, r0)     // Catch: java.lang.Exception -> L5c
            r1 = r10
            goto L7b
        L5c:
            r0 = move-exception
            goto L62
        L5e:
            r2 = r7
            goto L62
        L60:
            r0 = move-exception
            goto L5e
        L62:
            int r1 = mapError(r0)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r11 = "command=1 fail: "
            r6.<init>(r11)
            java.lang.String r0 = r0.getMessage()
            r6.append(r0)
            java.lang.String r0 = r6.toString()
            android.util.Log.i(r9, r0)
        L7b:
            r0 = r19
            com.google.android.systemui.dreamliner.WirelessChargerCommander$setFan$1$1 r0 = (com.google.android.systemui.dreamliner.WirelessChargerCommander$setFan$1.AnonymousClass1) r0
            r0.getClass()
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r9 = "SF() result="
            r6.<init>(r9)
            r6.append(r1)
            java.lang.String r6 = r6.toString()
            java.lang.String r9 = "WirelessChargerCommander"
            android.util.Log.w(r9, r6)
            if (r1 != 0) goto Lce
            if (r2 != 0) goto L9a
            goto Lce
        L9a:
            r1 = -1
            java.lang.Byte r5 = r2.getByte(r5, r1)
            java.lang.Byte r4 = r2.getByte(r4, r1)
            int r1 = r2.getInt(r3, r1)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "SF() response: i="
            r2.<init>(r3)
            r2.append(r5)
            r2.append(r8)
            r2.append(r4)
            java.lang.String r3 = ", cr="
            r2.append(r3)
            com.android.settingslib.bluetooth.LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0.m(r2, r1, r9)
            java.lang.Object r0 = r0.$resultReceiver
            android.os.ResultReceiver r0 = (android.os.ResultReceiver) r0
            if (r0 == 0) goto Ld7
            android.os.Bundle r1 = new android.os.Bundle
            r1.<init>()
            r0.send(r10, r1)
            goto Ld7
        Lce:
            java.lang.Object r0 = r0.$resultReceiver
            android.os.ResultReceiver r0 = (android.os.ResultReceiver) r0
            if (r0 == 0) goto Ld7
            r0.send(r1, r7)
        Ld7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.dreamliner.WirelessChargerImpl.setFan(byte, byte, int, com.google.android.systemui.dreamliner.WirelessCharger$SetFanCallback):void");
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.google.android.systemui.dreamliner.WirelessChargerImpl$1, reason: invalid class name */
    public final class AnonymousClass1 extends Binder implements IWirelessChargerFanLevelChangedCallback {
        public int mPreviousFanLevel;

        public AnonymousClass1() {
            markVintfStability();
            attachInterface(this, IWirelessChargerFanLevelChangedCallback.DESCRIPTOR);
            this.mPreviousFanLevel = -1;
        }

        @Override // android.os.Binder
        public final void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            printWriter.printf("fan level callback in [%d]%s\n", Integer.valueOf(Process.myPid()), WirelessChargerImpl.this.mContext.getPackageName());
        }

        @Override // android.os.Binder
        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            String str = IWirelessChargerFanLevelChangedCallback.DESCRIPTOR;
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(str);
            }
            if (i == 1598968902) {
                parcel2.writeString(str);
                return true;
            }
            if (i == 16777215) {
                parcel2.writeNoException();
                parcel2.writeInt(2);
                return true;
            }
            if (i == 16777214) {
                parcel2.writeNoException();
                parcel2.writeString("dfeae26730e4bd7209e33722e100616f4d3b6c41");
                return true;
            }
            if (i != 1) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            final int readInt = parcel.readInt();
            parcel.enforceNoDataAvail();
            Log.i("Dreamliner-WLC_HAL", "fan level changed: " + this.mPreviousFanLevel + " > " + readInt);
            this.mPreviousFanLevel = readInt;
            WirelessChargerImpl.this.mFanLevelEventListeners.forEach(new Consumer() { // from class: com.google.android.systemui.dreamliner.WirelessChargerImpl$1$$ExternalSyntheticLambda0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    int i3 = readInt;
                    Iterator it = ((WirelessChargerCommander$fanLevelEventListener$1) obj).this$0.wirelessChargerFanLevelChangedCallback.iterator();
                    while (it.hasNext()) {
                        ((DockObserver$$ExternalSyntheticLambda2) it.next()).onFanLevelChanged(i3);
                    }
                }
            });
            return true;
        }

        @Override // android.os.IInterface
        public final IBinder asBinder() {
            return this;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class WirelessChargerInfoCallback extends Binder implements IWirelessChargerInfoCallback {
        public final WirelessCharger.AlignInfoListener mListener;

        public WirelessChargerInfoCallback(WirelessCharger.AlignInfoListener alignInfoListener) {
            markVintfStability();
            attachInterface(this, IWirelessChargerInfoCallback.DESCRIPTOR);
            this.mListener = alignInfoListener;
        }

        @Override // android.os.Binder
        public final void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            printWriter.printf("alignment callback in [%d]%s\n", Integer.valueOf(Process.myPid()), WirelessChargerImpl.this.mContext.getPackageName());
        }

        @Override // android.os.Binder
        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            String str = IWirelessChargerInfoCallback.DESCRIPTOR;
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(str);
            }
            if (i == 1598968902) {
                parcel2.writeString(str);
                return true;
            }
            final int i3 = 2;
            if (i == 16777215) {
                parcel2.writeNoException();
                parcel2.writeInt(2);
                return true;
            }
            if (i == 16777214) {
                parcel2.writeNoException();
                parcel2.writeString("dfeae26730e4bd7209e33722e100616f4d3b6c41");
                return true;
            }
            if (i != 1) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            AlignInfo alignInfo = (AlignInfo) parcel.readTypedObject(AlignInfo.CREATOR);
            parcel.enforceNoDataAvail();
            WirelessCharger.AlignInfoListener alignInfoListener = this.mListener;
            int intValue = Byte.valueOf(alignInfo.alignState).intValue();
            int intValue2 = Byte.valueOf(alignInfo.alignPct).intValue();
            DockAlignmentController dockAlignmentController = ((DockAlignmentController$$ExternalSyntheticLambda0) alignInfoListener).f$0;
            int i4 = dockAlignmentController.mAlignmentState;
            boolean z = DockAlignmentController.DEBUG;
            if (z) {
                Log.d("DockAlignmentController", "onAlignInfo, state: " + intValue + ", alignPct: " + intValue2);
            }
            if (intValue == 0) {
                i3 = i4;
            } else if (intValue != 1) {
                if (intValue != 2) {
                    if (intValue != 3) {
                        RecordingInputConnection$$ExternalSyntheticOutline0.m("Unexpected state: ", "DockAlignmentController", intValue);
                    }
                } else if (intValue2 >= 0) {
                    i3 = intValue2 < 100 ? 1 : 0;
                }
                i3 = -1;
            }
            dockAlignmentController.mAlignmentState = i3;
            if (i4 != i3) {
                Iterator it = dockAlignmentController.mDockAlignmentStateChangeListeners.iterator();
                while (it.hasNext()) {
                    DockObserver dockObserver = ((DockObserver$$ExternalSyntheticLambda3) it.next()).f$0;
                    dockObserver.getClass();
                    Log.d("DLObserver", "onAlignStateChanged alignState = " + i3);
                    dockObserver.mLastAlignState = i3;
                    Iterator it2 = dockObserver.mAlignmentStateListeners.iterator();
                    while (it2.hasNext()) {
                        final KeyguardIndicationControllerGoogle keyguardIndicationControllerGoogle = ((KeyguardIndicationController$$ExternalSyntheticLambda14) it2.next()).f$0;
                        keyguardIndicationControllerGoogle.mHandler.post(new Runnable() { // from class: com.android.systemui.statusbar.KeyguardIndicationController$$ExternalSyntheticLambda15
                            @Override // java.lang.Runnable
                            public final void run() {
                                KeyguardIndicationControllerGoogle keyguardIndicationControllerGoogle2 = KeyguardIndicationControllerGoogle.this;
                                int i5 = i3;
                                String string = i5 == 1 ? ((KeyguardIndicationController) keyguardIndicationControllerGoogle2).mContext.getResources().getString(R.string.dock_alignment_slow_charging) : i5 == 2 ? ((KeyguardIndicationController) keyguardIndicationControllerGoogle2).mContext.getResources().getString(R.string.dock_alignment_not_charging) : "";
                                if (string.equals(keyguardIndicationControllerGoogle2.mAlignmentIndication)) {
                                    return;
                                }
                                keyguardIndicationControllerGoogle2.mAlignmentIndication = string;
                                keyguardIndicationControllerGoogle2.updateDeviceEntryIndication(false);
                            }
                        });
                    }
                    dockObserver.runPhotoAction();
                    dockObserver.notifyDreamlinerAlignStateChanged(i3);
                }
                if (z) {
                    LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0.m(new StringBuilder("onAlignStateChanged, state: "), dockAlignmentController.mAlignmentState, "DockAlignmentController");
                }
            }
            return true;
        }

        @Override // android.os.IInterface
        public final IBinder asBinder() {
            return this;
        }
    }
}
