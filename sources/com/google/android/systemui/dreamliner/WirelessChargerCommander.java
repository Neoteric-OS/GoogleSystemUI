package com.google.android.systemui.dreamliner;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.util.Log;
import androidx.compose.foundation.text.ValidatingOffsetMappingKt$$ExternalSyntheticOutline0;
import androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0;
import androidx.fragment.app.FragmentManagerViewModel$$ExternalSyntheticOutline0;
import androidx.viewpager.widget.ViewPager$$ExternalSyntheticOutline0;
import com.google.android.systemui.dreamliner.WirelessCharger;
import com.google.android.systemui.dreamliner.WirelessChargerCommander$doChallenge$1;
import com.google.android.systemui.dreamliner.WirelessChargerCommander$setFan$1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import vendor.google.wireless_charger.IWirelessCharger;
import vendor.google.wireless_charger.WpcAuthDigests;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class WirelessChargerCommander {
    public static ExecutorService singleThreadExecutor;
    public final IntentFilter commandIntents;
    public final WirelessChargerCommander$commandReceiver$1 commandReceiver;
    public final Optional wirelessCharger;
    public final AtomicBoolean isFanLevelCallbackRegistered = new AtomicBoolean(false);
    public final CopyOnWriteArrayList wirelessChargerFanLevelChangedCallback = new CopyOnWriteArrayList();
    public final WirelessChargerCommander$fanLevelEventListener$1 fanLevelEventListener = new WirelessChargerCommander$fanLevelEventListener$1(this);

    /* JADX WARN: Type inference failed for: r2v5, types: [com.google.android.systemui.dreamliner.WirelessChargerCommander$commandReceiver$1] */
    public WirelessChargerCommander(Optional optional) {
        this.wirelessCharger = optional;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.google.android.systemui.dreamliner.ACTION_GET_DOCK_INFO");
        intentFilter.addAction("com.google.android.systemui.dreamliner.ACTION_KEY_EXCHANGE");
        intentFilter.addAction("com.google.android.systemui.dreamliner.ACTION_CHALLENGE");
        intentFilter.addAction("com.google.android.systemui.dreamliner.ACTION_GET_FAN_INFO");
        intentFilter.addAction("com.google.android.systemui.dreamliner.ACTION_GET_FAN_LEVEL");
        intentFilter.addAction("com.google.android.systemui.dreamliner.ACTION_SET_FAN");
        intentFilter.addAction("com.google.android.systemui.dreamliner.ACTION_GET_WPC_DIGESTS");
        intentFilter.addAction("com.google.android.systemui.dreamliner.ACTION_GET_WPC_CERTIFICATE");
        intentFilter.addAction("com.google.android.systemui.dreamliner.ACTION_GET_WPC_CHALLENGE_RESPONSE");
        intentFilter.addAction("com.google.android.systemui.dreamliner.ACTION_GET_FEATURES");
        intentFilter.addAction("com.google.android.systemui.dreamliner.ACTION_SET_FEATURES");
        this.commandIntents = intentFilter;
        this.commandReceiver = new BroadcastReceiver() { // from class: com.google.android.systemui.dreamliner.WirelessChargerCommander$commandReceiver$1
            /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context, Intent intent) {
                FragmentManagerViewModel$$ExternalSyntheticOutline0.m("onReceive(): ", intent != null ? intent.getAction() : null, "WirelessChargerCommander");
                String action = intent != null ? intent.getAction() : null;
                if (action != null) {
                    switch (action.hashCode()) {
                        case -2133451883:
                            if (action.equals("com.google.android.systemui.dreamliner.ACTION_GET_FAN_LEVEL")) {
                                final WirelessChargerCommander wirelessChargerCommander = WirelessChargerCommander.this;
                                wirelessChargerCommander.getClass();
                                Log.d("WirelessChargerCommander", "GFL()");
                                final ResultReceiver resultReceiver = (ResultReceiver) intent.getParcelableExtra("android.intent.extra.RESULT_RECEIVER");
                                WirelessChargerCommander.asyncIfPresent(wirelessChargerCommander.wirelessCharger, new Function1() { // from class: com.google.android.systemui.dreamliner.WirelessChargerCommander$getFanLevel$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(Object obj) {
                                        long currentTimeMillis = System.currentTimeMillis();
                                        WirelessChargerImpl wirelessChargerImpl = (WirelessChargerImpl) ((WirelessCharger) obj);
                                        int i = -1;
                                        if (wirelessChargerImpl.initHALInterface()) {
                                            Log.d("Dreamliner-WLC_HAL", "command=2");
                                            try {
                                                i = ((IWirelessCharger.Stub.Proxy) wirelessChargerImpl.mWirelessCharger).getFanLevel();
                                            } catch (Exception e) {
                                                Log.i("Dreamliner-WLC_HAL", "command=2 fail: " + e.getMessage());
                                            }
                                        }
                                        Log.d("WirelessChargerCommander", "GFL() response: l=" + i + ", spending time=" + (System.currentTimeMillis() - currentTimeMillis));
                                        ResultReceiver resultReceiver2 = resultReceiver;
                                        if (resultReceiver2 != null) {
                                            Bundle bundle = new Bundle();
                                            bundle.putInt("fan_level", i);
                                            resultReceiver2.send(0, bundle);
                                        } else {
                                            Iterator it = wirelessChargerCommander.wirelessChargerFanLevelChangedCallback.iterator();
                                            while (it.hasNext()) {
                                                ((DockObserver$$ExternalSyntheticLambda2) it.next()).onFanLevelChanged(i);
                                            }
                                        }
                                        return Unit.INSTANCE;
                                    }
                                });
                                break;
                            }
                            break;
                        case -1863595884:
                            if (action.equals("com.google.android.systemui.dreamliner.ACTION_SET_FEATURES")) {
                                WirelessChargerCommander wirelessChargerCommander2 = WirelessChargerCommander.this;
                                wirelessChargerCommander2.getClass();
                                final long longExtra = intent.getLongExtra("charger_id", -1L);
                                final long longExtra2 = intent.getLongExtra("charger_feature", -1L);
                                final ResultReceiver resultReceiver2 = (ResultReceiver) intent.getParcelableExtra("android.intent.extra.RESULT_RECEIVER");
                                if (resultReceiver2 != null) {
                                    Log.d("WirelessChargerCommander", "SF(), c=" + longExtra + ", feature=" + longExtra2);
                                    if (longExtra != -1 && longExtra2 != -1) {
                                        WirelessChargerCommander.asyncIfPresent(wirelessChargerCommander2.wirelessCharger, new Function1() { // from class: com.google.android.systemui.dreamliner.WirelessChargerCommander$setFeatures$1
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public final Object invoke(Object obj) {
                                                int i;
                                                long j = longExtra;
                                                long j2 = longExtra2;
                                                ResultReceiver resultReceiver3 = resultReceiver2;
                                                WirelessChargerImpl wirelessChargerImpl = (WirelessChargerImpl) ((WirelessCharger) obj);
                                                if (wirelessChargerImpl.initHALInterface()) {
                                                    try {
                                                        ((IWirelessCharger.Stub.Proxy) wirelessChargerImpl.mWirelessCharger).setFeatures(j, j2);
                                                        i = 0;
                                                    } catch (Exception e) {
                                                        int mapError = WirelessChargerImpl.mapError(e);
                                                        Log.i("Dreamliner-WLC_HAL", "set features fail: " + e.getMessage());
                                                        i = mapError;
                                                    }
                                                    resultReceiver3.send(i, null);
                                                }
                                                return Unit.INSTANCE;
                                            }
                                        });
                                        break;
                                    } else {
                                        resultReceiver2.send(-1, null);
                                        break;
                                    }
                                }
                            }
                            break;
                        case -1627881412:
                            if (action.equals("com.google.android.systemui.dreamliner.ACTION_SET_FAN")) {
                                WirelessChargerCommander wirelessChargerCommander3 = WirelessChargerCommander.this;
                                wirelessChargerCommander3.getClass();
                                final byte byteExtra = intent.getByteExtra("fan_id", (byte) 0);
                                final byte byteExtra2 = intent.getByteExtra("fan_mode", (byte) 0);
                                final int intExtra = intent.getIntExtra("fan_rpm", -1);
                                StringBuilder m = ValidatingOffsetMappingKt$$ExternalSyntheticOutline0.m(byteExtra, byteExtra2, "SF(), i=", ", m=", ", r=");
                                m.append(intExtra);
                                Log.d("WirelessChargerCommander", m.toString());
                                final ResultReceiver resultReceiver3 = (ResultReceiver) intent.getParcelableExtra("android.intent.extra.RESULT_RECEIVER");
                                if (byteExtra2 != 1 || intExtra != -1) {
                                    WirelessChargerCommander.asyncIfPresent(wirelessChargerCommander3.wirelessCharger, new Function1() { // from class: com.google.android.systemui.dreamliner.WirelessChargerCommander$setFan$1

                                        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                                        /* renamed from: com.google.android.systemui.dreamliner.WirelessChargerCommander$setFan$1$1, reason: invalid class name */
                                        public final class AnonymousClass1 implements WirelessCharger.GetInformationCallback, WirelessCharger.SetFanCallback, WirelessCharger.IsDockPresentCallback {
                                            public final /* synthetic */ Object $resultReceiver;

                                            public /* synthetic */ AnonymousClass1(Object obj) {
                                                this.$resultReceiver = obj;
                                            }

                                            @Override // com.google.android.systemui.dreamliner.WirelessCharger.IsDockPresentCallback
                                            public void onCallback(boolean z, byte b, byte b2, boolean z2, int i, int i2) {
                                                StringBuilder sb = new StringBuilder("IDP() response: d=");
                                                sb.append(z);
                                                sb.append(", i=");
                                                sb.append(i);
                                                sb.append(", m=");
                                                ViewPager$$ExternalSyntheticOutline0.m(sb, i2, ", t=", b, ", o=");
                                                sb.append((int) b2);
                                                sb.append(", sgi=");
                                                sb.append(z2);
                                                Log.i("WirelessChargerCommander", sb.toString());
                                                ((WirelessCharger.IsDockPresentCallback) this.$resultReceiver).onCallback(z, b, b2, z2, i, i2);
                                            }
                                        }

                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public final Object invoke(Object obj) {
                                            ((WirelessCharger) obj).setFan(byteExtra, byteExtra2, intExtra, new AnonymousClass1(resultReceiver3));
                                            return Unit.INSTANCE;
                                        }
                                    });
                                    break;
                                } else {
                                    Log.e("WirelessChargerCommander", "Failed to get r.");
                                    break;
                                }
                            }
                            break;
                        case -1616532553:
                            if (action.equals("com.google.android.systemui.dreamliner.ACTION_GET_DOCK_INFO")) {
                                WirelessChargerCommander wirelessChargerCommander4 = WirelessChargerCommander.this;
                                wirelessChargerCommander4.getClass();
                                Log.d("WirelessChargerCommander", "GI()");
                                final ResultReceiver resultReceiver4 = (ResultReceiver) intent.getParcelableExtra("android.intent.extra.RESULT_RECEIVER");
                                if (resultReceiver4 != null) {
                                    WirelessChargerCommander.asyncIfPresent(wirelessChargerCommander4.wirelessCharger, new Function1() { // from class: com.google.android.systemui.dreamliner.WirelessChargerCommander$getInformation$1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public final Object invoke(Object obj) {
                                            ((WirelessCharger) obj).getInformation(new WirelessChargerCommander$setFan$1.AnonymousClass1(resultReceiver4));
                                            return Unit.INSTANCE;
                                        }
                                    });
                                    break;
                                }
                            }
                            break;
                        case -1598391011:
                            if (action.equals("com.google.android.systemui.dreamliner.ACTION_KEY_EXCHANGE")) {
                                final WirelessChargerCommander wirelessChargerCommander5 = WirelessChargerCommander.this;
                                wirelessChargerCommander5.getClass();
                                Log.d("WirelessChargerCommander", "KE()");
                                final ResultReceiver resultReceiver5 = (ResultReceiver) intent.getParcelableExtra("android.intent.extra.RESULT_RECEIVER");
                                if (resultReceiver5 != null) {
                                    final byte[] byteArrayExtra = intent.getByteArrayExtra("public_key");
                                    if (byteArrayExtra != null && byteArrayExtra.length != 0) {
                                        WirelessChargerCommander.asyncIfPresent(wirelessChargerCommander5.wirelessCharger, new Function1() { // from class: com.google.android.systemui.dreamliner.WirelessChargerCommander$doKeyExchange$1
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public final Object invoke(Object obj) {
                                                ((WirelessCharger) obj).keyExchange(byteArrayExtra, new WirelessChargerCommander$doChallenge$1.AnonymousClass1(resultReceiver5, wirelessChargerCommander5));
                                                return Unit.INSTANCE;
                                            }
                                        });
                                        break;
                                    } else {
                                        resultReceiver5.send(-1, null);
                                        break;
                                    }
                                }
                            }
                            break;
                        case -1458969207:
                            if (action.equals("com.google.android.systemui.dreamliner.ACTION_CHALLENGE")) {
                                final WirelessChargerCommander wirelessChargerCommander6 = WirelessChargerCommander.this;
                                wirelessChargerCommander6.getClass();
                                Log.d("WirelessChargerCommander", "C()");
                                final ResultReceiver resultReceiver6 = (ResultReceiver) intent.getParcelableExtra("android.intent.extra.RESULT_RECEIVER");
                                if (resultReceiver6 != null) {
                                    final byte byteExtra3 = intent.getByteExtra("challenge_dock_id", (byte) -1);
                                    final byte[] byteArrayExtra2 = intent.getByteArrayExtra("challenge_data");
                                    if (byteArrayExtra2 != null && byteArrayExtra2.length != 0 && byteExtra3 >= 0) {
                                        WirelessChargerCommander.asyncIfPresent(wirelessChargerCommander6.wirelessCharger, new Function1() { // from class: com.google.android.systemui.dreamliner.WirelessChargerCommander$doChallenge$1

                                            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                                            /* renamed from: com.google.android.systemui.dreamliner.WirelessChargerCommander$doChallenge$1$1, reason: invalid class name */
                                            public final class AnonymousClass1 implements WirelessCharger.ChallengeCallback, WirelessCharger.KeyExchangeCallback {
                                                public final /* synthetic */ ResultReceiver $resultReceiver;
                                                public final /* synthetic */ WirelessChargerCommander this$0;

                                                public /* synthetic */ AnonymousClass1(ResultReceiver resultReceiver, WirelessChargerCommander wirelessChargerCommander) {
                                                    this.$resultReceiver = resultReceiver;
                                                    this.this$0 = wirelessChargerCommander;
                                                }
                                            }

                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public final Object invoke(Object obj) {
                                                ((WirelessCharger) obj).challenge(byteExtra3, byteArrayExtra2, new AnonymousClass1(resultReceiver6, wirelessChargerCommander6));
                                                return Unit.INSTANCE;
                                            }
                                        });
                                        break;
                                    } else {
                                        resultReceiver6.send(-1, null);
                                        break;
                                    }
                                }
                            }
                            break;
                        case -686255721:
                            if (action.equals("com.google.android.systemui.dreamliner.ACTION_GET_WPC_DIGESTS")) {
                                final WirelessChargerCommander wirelessChargerCommander7 = WirelessChargerCommander.this;
                                wirelessChargerCommander7.getClass();
                                final byte byteExtra4 = intent.getByteExtra("slot_mask", (byte) -1);
                                Log.d("WirelessChargerCommander", "GWAD(), s=" + ((int) byteExtra4));
                                final ResultReceiver resultReceiver7 = (ResultReceiver) intent.getParcelableExtra("android.intent.extra.RESULT_RECEIVER");
                                if (resultReceiver7 != null) {
                                    if (byteExtra4 != -1) {
                                        WirelessChargerCommander.asyncIfPresent(wirelessChargerCommander7.wirelessCharger, new Function1() { // from class: com.google.android.systemui.dreamliner.WirelessChargerCommander$getWpcDigests$1
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public final Object invoke(Object obj) {
                                                ArrayList<byte[]> arrayList;
                                                byte b;
                                                byte b2;
                                                int mapError;
                                                byte b3 = byteExtra4;
                                                ResultReceiver resultReceiver8 = resultReceiver7;
                                                WirelessChargerCommander wirelessChargerCommander8 = wirelessChargerCommander7;
                                                WirelessChargerImpl wirelessChargerImpl = (WirelessChargerImpl) ((WirelessCharger) obj);
                                                if (wirelessChargerImpl.initHALInterface()) {
                                                    try {
                                                        WpcAuthDigests wpcAuthDigests = ((IWirelessCharger.Stub.Proxy) wirelessChargerImpl.mWirelessCharger).getWpcAuthDigests(b3);
                                                        b = wpcAuthDigests.slotPopulatedMask;
                                                        try {
                                                            b2 = wpcAuthDigests.slotReturnedMask;
                                                            try {
                                                                arrayList = new ArrayList(wpcAuthDigests.digests.length);
                                                                try {
                                                                    Collections.addAll(arrayList, wpcAuthDigests.digests);
                                                                    mapError = 0;
                                                                } catch (Exception e) {
                                                                    e = e;
                                                                    mapError = WirelessChargerImpl.mapError(e);
                                                                    Log.i("Dreamliner-WLC_HAL", "get wpc digests fail: " + e.getMessage());
                                                                    ExifInterface$$ExternalSyntheticOutline0.m("GWAD() result: ", "WirelessChargerCommander", mapError);
                                                                    if (mapError == 0) {
                                                                    }
                                                                    resultReceiver8.send(mapError, null);
                                                                    return Unit.INSTANCE;
                                                                }
                                                            } catch (Exception e2) {
                                                                e = e2;
                                                                arrayList = null;
                                                            }
                                                        } catch (Exception e3) {
                                                            e = e3;
                                                            arrayList = null;
                                                            b2 = 0;
                                                        }
                                                    } catch (Exception e4) {
                                                        e = e4;
                                                        arrayList = null;
                                                        b = 0;
                                                        b2 = 0;
                                                    }
                                                    ExifInterface$$ExternalSyntheticOutline0.m("GWAD() result: ", "WirelessChargerCommander", mapError);
                                                    if (mapError == 0 || arrayList == null) {
                                                        resultReceiver8.send(mapError, null);
                                                    } else {
                                                        StringBuilder m2 = ValidatingOffsetMappingKt$$ExternalSyntheticOutline0.m(b, b2, "GWAD() response: pm=", ", rm=", ", d=");
                                                        m2.append(arrayList);
                                                        Log.d("WirelessChargerCommander", m2.toString());
                                                        wirelessChargerCommander8.getClass();
                                                        Bundle bundle = new Bundle();
                                                        bundle.putByte("slot_populated_mask", b);
                                                        bundle.putByte("slot_returned_mask", b2);
                                                        ArrayList<? extends Parcelable> arrayList2 = new ArrayList<>();
                                                        for (byte[] bArr : arrayList) {
                                                            Bundle bundle2 = new Bundle();
                                                            bundle2.putByteArray("wpc_digest", bArr);
                                                            arrayList2.add(bundle2);
                                                        }
                                                        bundle.putParcelableArrayList("wpc_digests", arrayList2);
                                                        resultReceiver8.send(0, bundle);
                                                    }
                                                }
                                                return Unit.INSTANCE;
                                            }
                                        });
                                        break;
                                    } else {
                                        resultReceiver7.send(-1, null);
                                        break;
                                    }
                                }
                            }
                            break;
                        case 882378784:
                            if (action.equals("com.google.android.systemui.dreamliner.ACTION_GET_FEATURES")) {
                                WirelessChargerCommander wirelessChargerCommander8 = WirelessChargerCommander.this;
                                wirelessChargerCommander8.getClass();
                                final long longExtra3 = intent.getLongExtra("charger_id", -1L);
                                Log.d("WirelessChargerCommander", "GF(), c=" + longExtra3);
                                final ResultReceiver resultReceiver8 = (ResultReceiver) intent.getParcelableExtra("android.intent.extra.RESULT_RECEIVER");
                                if (resultReceiver8 != null) {
                                    if (longExtra3 != -1) {
                                        WirelessChargerCommander.asyncIfPresent(wirelessChargerCommander8.wirelessCharger, new Function1() { // from class: com.google.android.systemui.dreamliner.WirelessChargerCommander$getFeatures$1
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public final Object invoke(Object obj) {
                                                int mapError;
                                                long j;
                                                long j2 = longExtra3;
                                                ResultReceiver resultReceiver9 = resultReceiver8;
                                                WirelessChargerImpl wirelessChargerImpl = (WirelessChargerImpl) ((WirelessCharger) obj);
                                                if (wirelessChargerImpl.initHALInterface()) {
                                                    try {
                                                        j = ((IWirelessCharger.Stub.Proxy) wirelessChargerImpl.mWirelessCharger).getFeatures(j2);
                                                        mapError = 0;
                                                    } catch (Exception e) {
                                                        mapError = WirelessChargerImpl.mapError(e);
                                                        Log.i("Dreamliner-WLC_HAL", "get features fail: " + e.getMessage());
                                                        j = 0;
                                                    }
                                                    if (mapError != 0) {
                                                        resultReceiver9.send(mapError, null);
                                                    } else {
                                                        Log.d("WirelessChargerCommander", "GF() response: f=" + j);
                                                        Bundle bundle = new Bundle();
                                                        bundle.putLong("charger_feature", j);
                                                        resultReceiver9.send(0, bundle);
                                                    }
                                                }
                                                return Unit.INSTANCE;
                                            }
                                        });
                                        break;
                                    } else {
                                        resultReceiver8.send(-1, null);
                                        break;
                                    }
                                }
                            }
                            break;
                        case 1954561023:
                            if (action.equals("com.google.android.systemui.dreamliner.ACTION_GET_WPC_CERTIFICATE")) {
                                final WirelessChargerCommander wirelessChargerCommander9 = WirelessChargerCommander.this;
                                wirelessChargerCommander9.getClass();
                                final byte byteExtra5 = intent.getByteExtra("slot_number", (byte) -1);
                                final short shortExtra = intent.getShortExtra("cert_offset", (short) -1);
                                final short shortExtra2 = intent.getShortExtra("cert_length", (short) -1);
                                StringBuilder m2 = ValidatingOffsetMappingKt$$ExternalSyntheticOutline0.m(byteExtra5, shortExtra, "GWAC(), s=", ", offset=", ", length=");
                                m2.append((int) shortExtra2);
                                Log.d("WirelessChargerCommander", m2.toString());
                                final ResultReceiver resultReceiver9 = (ResultReceiver) intent.getParcelableExtra("android.intent.extra.RESULT_RECEIVER");
                                if (resultReceiver9 != null) {
                                    if (byteExtra5 != -1 && shortExtra != -1 && shortExtra2 != -1) {
                                        WirelessChargerCommander.asyncIfPresent(wirelessChargerCommander9.wirelessCharger, new Function1() { // from class: com.google.android.systemui.dreamliner.WirelessChargerCommander$getWpcCertificate$1
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public final Object invoke(Object obj) {
                                                int mapError;
                                                ArrayList arrayList;
                                                byte b = byteExtra5;
                                                short s = shortExtra;
                                                short s2 = shortExtra2;
                                                ResultReceiver resultReceiver10 = resultReceiver9;
                                                WirelessChargerCommander wirelessChargerCommander10 = wirelessChargerCommander9;
                                                WirelessChargerImpl wirelessChargerImpl = (WirelessChargerImpl) ((WirelessCharger) obj);
                                                if (wirelessChargerImpl.initHALInterface()) {
                                                    Bundle bundle = null;
                                                    try {
                                                        arrayList = WirelessChargerImpl.convertPrimitiveArrayToArrayList(((IWirelessCharger.Stub.Proxy) wirelessChargerImpl.mWirelessCharger).getWpcAuthCertificate(b, (char) s, (char) s2));
                                                        mapError = 0;
                                                    } catch (Exception e) {
                                                        mapError = WirelessChargerImpl.mapError(e);
                                                        Log.i("Dreamliner-WLC_HAL", "get wpc cert fail: " + e.getMessage());
                                                        arrayList = null;
                                                    }
                                                    ExifInterface$$ExternalSyntheticOutline0.m("GWAC() result: ", "WirelessChargerCommander", mapError);
                                                    if (mapError != 0 || arrayList == null) {
                                                        resultReceiver10.send(mapError, null);
                                                    } else {
                                                        Log.d("WirelessChargerCommander", "GWAC() response: c=" + arrayList);
                                                        wirelessChargerCommander10.getClass();
                                                        if (!arrayList.isEmpty()) {
                                                            bundle = new Bundle();
                                                            bundle.putByteArray("wpc_cert", CollectionsKt.toByteArray(arrayList));
                                                        }
                                                        resultReceiver10.send(0, bundle);
                                                    }
                                                }
                                                return Unit.INSTANCE;
                                            }
                                        });
                                        break;
                                    } else {
                                        resultReceiver9.send(-1, null);
                                        break;
                                    }
                                }
                            }
                            break;
                        case 2009307741:
                            if (action.equals("com.google.android.systemui.dreamliner.ACTION_GET_FAN_INFO")) {
                                WirelessChargerCommander wirelessChargerCommander10 = WirelessChargerCommander.this;
                                wirelessChargerCommander10.getClass();
                                final byte byteExtra6 = intent.getByteExtra("fan_id", (byte) -1);
                                Log.d("WirelessChargerCommander", "GFI(), i=" + ((int) byteExtra6));
                                final ResultReceiver resultReceiver10 = (ResultReceiver) intent.getParcelableExtra("android.intent.extra.RESULT_RECEIVER");
                                if (resultReceiver10 != null) {
                                    WirelessChargerCommander.asyncIfPresent(wirelessChargerCommander10.wirelessCharger, new Function1() { // from class: com.google.android.systemui.dreamliner.WirelessChargerCommander$getFanInfo$1

                                        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                                        /* renamed from: com.google.android.systemui.dreamliner.WirelessChargerCommander$getFanInfo$1$1, reason: invalid class name */
                                        public final class AnonymousClass1 implements WirelessCharger.GetFanInformationCallback {
                                            public final /* synthetic */ byte $fanId;
                                            public final /* synthetic */ ResultReceiver $resultReceiver;

                                            public AnonymousClass1(byte b, ResultReceiver resultReceiver) {
                                                this.$fanId = b;
                                                this.$resultReceiver = resultReceiver;
                                            }
                                        }

                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public final Object invoke(Object obj) {
                                            byte b = byteExtra6;
                                            ((WirelessCharger) obj).getFanInformation(b, new AnonymousClass1(b, resultReceiver10));
                                            return Unit.INSTANCE;
                                        }
                                    });
                                    break;
                                }
                            }
                            break;
                        case 2121889077:
                            if (action.equals("com.google.android.systemui.dreamliner.ACTION_GET_WPC_CHALLENGE_RESPONSE")) {
                                final WirelessChargerCommander wirelessChargerCommander11 = WirelessChargerCommander.this;
                                wirelessChargerCommander11.getClass();
                                final byte byteExtra7 = intent.getByteExtra("slot_number", (byte) -1);
                                Log.d("WirelessChargerCommander", "GWACR(), s=" + ((int) byteExtra7));
                                final ResultReceiver resultReceiver11 = (ResultReceiver) intent.getParcelableExtra("android.intent.extra.RESULT_RECEIVER");
                                if (resultReceiver11 != null) {
                                    final byte[] byteArrayExtra3 = intent.getByteArrayExtra("wpc_nonce");
                                    if (byteArrayExtra3 != null && byteArrayExtra3.length != 0) {
                                        WirelessChargerCommander.asyncIfPresent(wirelessChargerCommander11.wirelessCharger, new Function1() { // from class: com.google.android.systemui.dreamliner.WirelessChargerCommander$getWpcChallengeResponse$1
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(1);
                                            }

                                            /* JADX WARN: Removed duplicated region for block: B:20:0x006b A[ADDED_TO_REGION] */
                                            @Override // kotlin.jvm.functions.Function1
                                            /*
                                                Code decompiled incorrectly, please refer to instructions dump.
                                                To view partially-correct add '--show-bad-code' argument
                                            */
                                            public final java.lang.Object invoke(java.lang.Object r11) {
                                                /*
                                                    r10 = this;
                                                    com.google.android.systemui.dreamliner.WirelessCharger r11 = (com.google.android.systemui.dreamliner.WirelessCharger) r11
                                                    byte r0 = r1
                                                    byte[] r1 = r2
                                                    android.os.ResultReceiver r2 = r3
                                                    com.google.android.systemui.dreamliner.WirelessChargerCommander r10 = r4
                                                    com.google.android.systemui.dreamliner.WirelessChargerImpl r11 = (com.google.android.systemui.dreamliner.WirelessChargerImpl) r11
                                                    boolean r3 = r11.initHALInterface()
                                                    if (r3 != 0) goto L14
                                                    goto Lc4
                                                L14:
                                                    r3 = 0
                                                    r4 = 0
                                                    vendor.google.wireless_charger.IWirelessCharger r11 = r11.mWirelessCharger     // Catch: java.lang.Exception -> L41
                                                    vendor.google.wireless_charger.IWirelessCharger$Stub$Proxy r11 = (vendor.google.wireless_charger.IWirelessCharger.Stub.Proxy) r11     // Catch: java.lang.Exception -> L41
                                                    vendor.google.wireless_charger.WpcAuthChallengeResponse r11 = r11.getWpcAuthChallengeResponse(r0, r1)     // Catch: java.lang.Exception -> L41
                                                    byte r0 = r11.maxProtocolVersion     // Catch: java.lang.Exception -> L41
                                                    byte r1 = r11.slotPopulatedMask     // Catch: java.lang.Exception -> L3c
                                                    byte r5 = r11.certificateChainHashLsb     // Catch: java.lang.Exception -> L38
                                                    byte[] r6 = r11.signatureR     // Catch: java.lang.Exception -> L36
                                                    java.util.ArrayList r6 = com.google.android.systemui.dreamliner.WirelessChargerImpl.convertPrimitiveArrayToArrayList(r6)     // Catch: java.lang.Exception -> L36
                                                    byte[] r11 = r11.signatureS     // Catch: java.lang.Exception -> L32
                                                    java.util.ArrayList r11 = com.google.android.systemui.dreamliner.WirelessChargerImpl.convertPrimitiveArrayToArrayList(r11)     // Catch: java.lang.Exception -> L32
                                                    r7 = r4
                                                    goto L62
                                                L32:
                                                    r11 = move-exception
                                                    goto L46
                                                L34:
                                                    r6 = r3
                                                    goto L46
                                                L36:
                                                    r11 = move-exception
                                                    goto L34
                                                L38:
                                                    r11 = move-exception
                                                    r6 = r3
                                                    r5 = r4
                                                    goto L46
                                                L3c:
                                                    r11 = move-exception
                                                    r6 = r3
                                                    r1 = r4
                                                L3f:
                                                    r5 = r1
                                                    goto L46
                                                L41:
                                                    r11 = move-exception
                                                    r6 = r3
                                                    r0 = r4
                                                    r1 = r0
                                                    goto L3f
                                                L46:
                                                    int r7 = com.google.android.systemui.dreamliner.WirelessChargerImpl.mapError(r11)
                                                    java.lang.StringBuilder r8 = new java.lang.StringBuilder
                                                    java.lang.String r9 = "get wpc challenge response fail: "
                                                    r8.<init>(r9)
                                                    java.lang.String r11 = r11.getMessage()
                                                    r8.append(r11)
                                                    java.lang.String r11 = r8.toString()
                                                    java.lang.String r8 = "Dreamliner-WLC_HAL"
                                                    android.util.Log.i(r8, r11)
                                                    r11 = r3
                                                L62:
                                                    java.lang.String r8 = "GWACR() result: "
                                                    java.lang.String r9 = "WirelessChargerCommander"
                                                    androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0.m(r8, r9, r7)
                                                    if (r7 != 0) goto Lc1
                                                    if (r6 == 0) goto Lc1
                                                    if (r11 != 0) goto L70
                                                    goto Lc1
                                                L70:
                                                    java.lang.String r3 = "GWACR() response: mpv="
                                                    java.lang.String r7 = ", pm="
                                                    java.lang.String r8 = ", chl="
                                                    java.lang.StringBuilder r3 = androidx.compose.foundation.text.ValidatingOffsetMappingKt$$ExternalSyntheticOutline0.m(r0, r1, r3, r7, r8)
                                                    r3.append(r5)
                                                    java.lang.String r7 = ", rv="
                                                    r3.append(r7)
                                                    r3.append(r6)
                                                    java.lang.String r7 = ", sv="
                                                    r3.append(r7)
                                                    r3.append(r11)
                                                    java.lang.String r3 = r3.toString()
                                                    android.util.Log.d(r9, r3)
                                                    r10.getClass()
                                                    android.os.Bundle r10 = new android.os.Bundle
                                                    r10.<init>()
                                                    java.lang.String r3 = "max_protocol_ver"
                                                    r10.putByte(r3, r0)
                                                    java.lang.String r0 = "slot_populated_mask"
                                                    r10.putByte(r0, r1)
                                                    java.lang.String r0 = "cert_lsb"
                                                    r10.putByte(r0, r5)
                                                    byte[] r0 = kotlin.collections.CollectionsKt.toByteArray(r6)
                                                    java.lang.String r1 = "signature_r"
                                                    r10.putByteArray(r1, r0)
                                                    byte[] r11 = kotlin.collections.CollectionsKt.toByteArray(r11)
                                                    java.lang.String r0 = "signature_s"
                                                    r10.putByteArray(r0, r11)
                                                    r2.send(r4, r10)
                                                    goto Lc4
                                                Lc1:
                                                    r2.send(r7, r3)
                                                Lc4:
                                                    kotlin.Unit r10 = kotlin.Unit.INSTANCE
                                                    return r10
                                                */
                                                throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.dreamliner.WirelessChargerCommander$getWpcChallengeResponse$1.invoke(java.lang.Object):java.lang.Object");
                                            }
                                        });
                                        break;
                                    } else {
                                        resultReceiver11.send(-1, null);
                                        break;
                                    }
                                }
                            }
                            break;
                    }
                }
            }
        };
    }

    public static void asyncIfPresent(final Optional optional, final Function1 function1) {
        Runnable runnable = new Runnable(optional, function1) { // from class: com.google.android.systemui.dreamliner.WirelessChargerCommander$asyncIfPresent$1
            public final /* synthetic */ Lambda $block;
            public final /* synthetic */ Optional $this_asyncIfPresent;

            /* JADX WARN: Multi-variable type inference failed */
            {
                this.$block = (Lambda) function1;
            }

            /* JADX WARN: Type inference failed for: r2v1, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
            @Override // java.lang.Runnable
            public final void run() {
                this.$this_asyncIfPresent.ifPresent(new Consumer(this.$block) { // from class: com.google.android.systemui.dreamliner.WirelessChargerCommander$asyncIfPresent$1.1
                    public final /* synthetic */ Lambda $block;

                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        this.$block = (Lambda) r1;
                    }

                    /* JADX WARN: Type inference failed for: r0v1, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        this.$block.invoke((WirelessCharger) obj);
                    }
                });
            }
        };
        if (singleThreadExecutor == null) {
            singleThreadExecutor = Executors.newSingleThreadExecutor();
        }
        ExecutorService executorService = singleThreadExecutor;
        if (executorService != null) {
            executorService.execute(runnable);
        }
    }

    public static /* synthetic */ void getCommandReceiver$annotations() {
    }
}
