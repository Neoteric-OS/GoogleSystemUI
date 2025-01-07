package com.google.android.systemui.power.batteryevent.repository;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.util.settings.GlobalSettings;
import com.google.android.systemui.googlebattery.GoogleBatteryManager;
import com.google.android.systemui.power.batteryevent.aidl.BatteryEventType;
import com.google.android.systemui.power.batteryevent.common.FrameworkApiDataType;
import com.google.android.systemui.power.batteryevent.common.HalDataType;
import com.google.android.systemui.power.batteryevent.common.SettingsDataType;
import com.google.android.systemui.power.batteryevent.common.data.EventData;
import com.google.android.systemui.power.batteryevent.common.data.FrameworkApiEventData;
import com.google.android.systemui.power.batteryevent.common.data.HalEventData;
import com.google.android.systemui.power.batteryevent.common.data.SettingsEventData;
import com.google.android.systemui.power.batteryevent.common.data.SystemEventData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import vendor.google.google_battery.IGoogleBattery;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class SystemEventDataSource$processIntent$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Context $context;
    final /* synthetic */ String $intentAction;
    final /* synthetic */ Intent $receivedIntent;
    int label;
    final /* synthetic */ SystemEventDataSource this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SystemEventDataSource$processIntent$2(Context context, Intent intent, SystemEventDataSource systemEventDataSource, String str, Continuation continuation) {
        super(2, continuation);
        this.$intentAction = str;
        this.$receivedIntent = intent;
        this.$context = context;
        this.this$0 = systemEventDataSource;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        String str = this.$intentAction;
        return new SystemEventDataSource$processIntent$2(this.$context, this.$receivedIntent, this.this$0, str, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        SystemEventDataSource$processIntent$2 systemEventDataSource$processIntent$2 = (SystemEventDataSource$processIntent$2) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        systemEventDataSource$processIntent$2.invokeSuspend(unit);
        return unit;
    }

    /* JADX WARN: Type inference failed for: r0v25, types: [kotlin.jvm.functions.Function2, kotlin.jvm.internal.Lambda] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Integer num;
        Iterator it;
        Bundle bundle;
        Iterator it2;
        int i;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        Intent registerReceiver = Intrinsics.areEqual(this.$intentAction, "android.intent.action.BATTERY_CHANGED") ? this.$receivedIntent : this.$context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        int intExtra = registerReceiver.getIntExtra("plugged", 0);
        int intExtra2 = registerReceiver.getIntExtra("scale", 100);
        int intExtra3 = registerReceiver.getIntExtra("level", 0);
        int intExtra4 = registerReceiver.getIntExtra("android.os.extra.CHARGING_STATUS", 1);
        int intExtra5 = registerReceiver.getIntExtra("max_charging_current", 0);
        int intExtra6 = registerReceiver.getIntExtra("max_charging_voltage", 0);
        int intExtra7 = registerReceiver.getIntExtra("status", 1);
        SystemEventDataSource systemEventDataSource = this.this$0;
        HalDataSource halDataSource = systemEventDataSource.halDataSource;
        List access$getAllEventDataType = SystemEventDataSource.access$getAllEventDataType(systemEventDataSource, this.$intentAction);
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : access$getAllEventDataType) {
            if (obj2 instanceof HalDataType) {
                arrayList.add(obj2);
            }
        }
        HalDataSource$deathRecipient$1 halDataSource$deathRecipient$1 = HalDataSource$deathRecipient$1.INSTANCE;
        GoogleBatteryManagerWrapperImpl googleBatteryManagerWrapperImpl = halDataSource.googleBatteryManager;
        IGoogleBattery initHalInterface = GoogleBatteryManager.initHalInterface(halDataSource$deathRecipient$1);
        Iterator it3 = arrayList.iterator();
        while (it3.hasNext()) {
            int ordinal = ((HalDataType) it3.next()).ordinal();
            if (ordinal == 0) {
                if (intExtra == 8) {
                    if (initHalInterface == null) {
                        Log.w("GoogleBatteryDataSource", "getDockDefendStatus failed. googleBattery is null");
                    } else {
                        try {
                            int dockDefendStatus = ((IGoogleBattery.Stub.Proxy) initHalInterface).getDockDefendStatus();
                            Log.i("GoogleBatteryDataSource", "fetchDockDefendStatus: dockDefendStatus:" + dockDefendStatus);
                            i = dockDefendStatus;
                        } catch (Exception e) {
                            Log.w("GoogleBatteryDataSource", "fetchDockDefendStatus failed.", e);
                        }
                        halDataSource.lastGoogleBatteryDockDefendStatus = i;
                    }
                }
                i = -3;
                halDataSource.lastGoogleBatteryDockDefendStatus = i;
            } else if (ordinal == 1) {
                String fetchFeatureStatus = halDataSource.fetchFeatureStatus(initHalInterface, 1, true);
                Log.d("GoogleBatteryDataSource", "fetchTempDefendStatus: ".concat(fetchFeatureStatus));
                halDataSource.lastTempDefendStatus = StringsKt.contains$default(fetchFeatureStatus, " t=1");
            } else if (ordinal == 2) {
                String fetchFeatureStatus2 = halDataSource.fetchFeatureStatus(initHalInterface, 3, true);
                Log.d("GoogleBatteryDataSource", "fetchDwellDefendStatus: ".concat(fetchFeatureStatus2));
                halDataSource.lastDwellDefendStatus = fetchFeatureStatus2.equals("ACTIVE");
            }
        }
        halDataSource.destroyGoogleBattery(initHalInterface);
        int i2 = halDataSource.lastGoogleBatteryDockDefendStatus;
        boolean z = halDataSource.lastTempDefendStatus;
        boolean z2 = halDataSource.lastDwellDefendStatus;
        Integer valueOf = Integer.valueOf(i2);
        Boolean valueOf2 = Boolean.valueOf(z);
        Boolean valueOf3 = Boolean.valueOf(z2);
        SystemEventDataSource systemEventDataSource2 = this.this$0;
        SettingsDataSource settingsDataSource = systemEventDataSource2.settingsDataSource;
        List access$getAllEventDataType2 = SystemEventDataSource.access$getAllEventDataType(systemEventDataSource2, this.$intentAction);
        ArrayList arrayList2 = new ArrayList();
        for (Object obj3 : access$getAllEventDataType2) {
            if (obj3 instanceof SettingsDataType) {
                arrayList2.add(obj3);
            }
        }
        settingsDataSource.getClass();
        Iterator it4 = arrayList2.iterator();
        while (it4.hasNext()) {
            int ordinal2 = ((SettingsDataType) it4.next()).ordinal();
            GlobalSettings globalSettings = settingsDataSource.globalSettings;
            if (ordinal2 == 0) {
                it2 = it4;
                settingsDataSource.lastAirplaneState = globalSettings.getInt(0, "airplane_mode_on") != 0;
            } else if (ordinal2 == 1) {
                it2 = it4;
                settingsDataSource.lastChargingLimitSettings = settingsDataSource.secureSettings.getIntForUser("charge_optimization_mode", 0, ((UserTrackerImpl) settingsDataSource.userTracker).getUserId());
            } else if (ordinal2 == 2) {
                settingsDataSource.lastDndState = globalSettings.getInt(0, "zen_mode") != 0;
            } else if (ordinal2 != 3) {
                it2 = it4;
            } else {
                settingsDataSource.lastDockDefenderByPass = globalSettings.getInt(0, "dock_defender_bypass");
            }
            it4 = it2;
        }
        int i3 = settingsDataSource.lastDockDefenderByPass;
        int i4 = settingsDataSource.lastChargingLimitSettings;
        boolean z3 = settingsDataSource.lastDndState;
        boolean z4 = settingsDataSource.lastAirplaneState;
        Integer valueOf4 = Integer.valueOf(i3);
        Integer valueOf5 = Integer.valueOf(i4);
        Boolean valueOf6 = Boolean.valueOf(z3);
        Boolean valueOf7 = Boolean.valueOf(z4);
        SystemEventDataSource systemEventDataSource3 = this.this$0;
        FrameworkDataSource frameworkDataSource = systemEventDataSource3.frameworkDataSource;
        List access$getAllEventDataType3 = SystemEventDataSource.access$getAllEventDataType(systemEventDataSource3, this.$intentAction);
        ArrayList arrayList3 = new ArrayList();
        Iterator it5 = access$getAllEventDataType3.iterator();
        while (it5.hasNext()) {
            Integer num2 = valueOf5;
            Object next = it5.next();
            Iterator it6 = it5;
            if (next instanceof FrameworkApiDataType) {
                arrayList3.add(next);
            }
            valueOf5 = num2;
            it5 = it6;
        }
        Integer num3 = valueOf5;
        frameworkDataSource.getClass();
        Iterator it7 = arrayList3.iterator();
        while (it7.hasNext()) {
            int ordinal3 = ((FrameworkApiDataType) it7.next()).ordinal();
            if (ordinal3 == 0) {
                num = valueOf4;
                it = it7;
                frameworkDataSource.lastBatterySaverState = frameworkDataSource.powerManager.isPowerSaveMode();
            } else if (ordinal3 != 1) {
                num = valueOf4;
                it = it7;
            } else {
                if (frameworkDataSource.powerManager.isPowerSaveMode()) {
                    it = it7;
                    num = valueOf4;
                    bundle = frameworkDataSource.context.getContentResolver().call("com.google.android.flipendo.api", "get_flipendo_state", (String) null, (Bundle) null);
                } else {
                    num = valueOf4;
                    it = it7;
                    bundle = null;
                }
                frameworkDataSource.lastExtremeBatterySaverState = bundle != null && bundle.getBoolean("flipendo_state");
            }
            it7 = it;
            valueOf4 = num;
        }
        Integer num4 = valueOf4;
        boolean z5 = frameworkDataSource.lastBatterySaverState;
        boolean z6 = frameworkDataSource.lastExtremeBatterySaverState;
        Boolean valueOf8 = Boolean.valueOf(z5);
        Boolean valueOf9 = Boolean.valueOf(z6);
        String str = this.$intentAction;
        Integer valueOf10 = Integer.valueOf(intExtra);
        Integer valueOf11 = Integer.valueOf(intExtra2);
        Integer valueOf12 = Integer.valueOf(intExtra3);
        Integer valueOf13 = Integer.valueOf(intExtra4);
        Integer valueOf14 = Integer.valueOf(intExtra5);
        Integer valueOf15 = Integer.valueOf(intExtra6);
        Integer valueOf16 = Integer.valueOf(intExtra7);
        SystemEventData systemEventData = this.this$0.lastSystemEventData;
        EventData eventData = systemEventData.plugged;
        if (Intrinsics.areEqual(eventData.value, valueOf10)) {
            eventData.isChanged = false;
        } else {
            eventData = new EventData(valueOf10);
            eventData.isChanged = true;
        }
        EventData eventData2 = eventData;
        EventData eventData3 = systemEventData.batteryScale;
        if (Intrinsics.areEqual(eventData3.value, valueOf11)) {
            eventData3.isChanged = false;
        } else {
            eventData3 = new EventData(valueOf11);
            eventData3.isChanged = true;
        }
        EventData eventData4 = eventData3;
        EventData eventData5 = systemEventData.batteryLevel;
        if (Intrinsics.areEqual(eventData5.value, valueOf12)) {
            eventData5.isChanged = false;
        } else {
            eventData5 = new EventData(valueOf12);
            eventData5.isChanged = true;
        }
        EventData eventData6 = eventData5;
        EventData eventData7 = systemEventData.chargingStatus;
        if (Intrinsics.areEqual(eventData7.value, valueOf13)) {
            eventData7.isChanged = false;
        } else {
            eventData7 = new EventData(valueOf13);
            eventData7.isChanged = true;
        }
        EventData eventData8 = eventData7;
        EventData eventData9 = systemEventData.maxChargingCurrent;
        if (Intrinsics.areEqual(eventData9.value, valueOf14)) {
            eventData9.isChanged = false;
        } else {
            eventData9 = new EventData(valueOf14);
            eventData9.isChanged = true;
        }
        EventData eventData10 = eventData9;
        EventData eventData11 = systemEventData.maxChargingVoltage;
        if (Intrinsics.areEqual(eventData11.value, valueOf15)) {
            eventData11.isChanged = false;
        } else {
            eventData11 = new EventData(valueOf15);
            eventData11.isChanged = true;
        }
        EventData eventData12 = eventData11;
        EventData eventData13 = systemEventData.batteryStatus;
        if (Intrinsics.areEqual(eventData13.value, valueOf16)) {
            eventData13.isChanged = false;
        } else {
            eventData13 = new EventData(valueOf16);
            eventData13.isChanged = true;
        }
        EventData eventData14 = eventData13;
        HalEventData halEventData = systemEventData.halEventData;
        EventData eventData15 = halEventData.dockDefendStatus;
        if (Intrinsics.areEqual(eventData15.value, valueOf)) {
            eventData15.isChanged = false;
        } else {
            eventData15 = new EventData(valueOf);
            eventData15.isChanged = true;
        }
        EventData eventData16 = halEventData.tempDefendEventData;
        if (Intrinsics.areEqual(eventData16.value, valueOf2)) {
            eventData16.isChanged = false;
        } else {
            eventData16 = new EventData(valueOf2);
            eventData16.isChanged = true;
        }
        EventData eventData17 = halEventData.dwellDefendEventData;
        if (Intrinsics.areEqual(eventData17.value, valueOf3)) {
            eventData17.isChanged = false;
        } else {
            eventData17 = new EventData(valueOf3);
            eventData17.isChanged = true;
        }
        HalEventData halEventData2 = new HalEventData(eventData15, eventData16, eventData17);
        SettingsEventData settingsEventData = systemEventData.settingsEventData;
        EventData eventData18 = settingsEventData.dockDefenderBypass;
        if (Intrinsics.areEqual(eventData18.value, num4)) {
            eventData18.isChanged = false;
        } else {
            eventData18 = new EventData(num4);
            eventData18.isChanged = true;
        }
        EventData eventData19 = settingsEventData.chargingLimitSettings;
        if (Intrinsics.areEqual(eventData19.value, num3)) {
            eventData19.isChanged = false;
        } else {
            eventData19 = new EventData(num3);
            eventData19.isChanged = true;
        }
        EventData eventData20 = settingsEventData.dndState;
        if (Intrinsics.areEqual(eventData20.value, valueOf6)) {
            eventData20.isChanged = false;
        } else {
            eventData20 = new EventData(valueOf6);
            eventData20.isChanged = true;
        }
        EventData eventData21 = settingsEventData.airplaneState;
        if (Intrinsics.areEqual(eventData21.value, valueOf7)) {
            eventData21.isChanged = false;
        } else {
            eventData21 = new EventData(valueOf7);
            eventData21.isChanged = true;
        }
        SettingsEventData settingsEventData2 = new SettingsEventData(eventData18, eventData19, eventData20, eventData21);
        FrameworkApiEventData frameworkApiEventData = systemEventData.frameworkApiEventData;
        EventData eventData22 = frameworkApiEventData.batterySaverState;
        if (Intrinsics.areEqual(eventData22.value, valueOf8)) {
            eventData22.isChanged = false;
        } else {
            eventData22 = new EventData(valueOf8);
            eventData22.isChanged = true;
        }
        EventData eventData23 = frameworkApiEventData.extremeBatterySaverState;
        if (Intrinsics.areEqual(eventData23.value, valueOf9)) {
            eventData23.isChanged = false;
        } else {
            eventData23 = new EventData(valueOf9);
            eventData23.isChanged = true;
        }
        SystemEventData systemEventData2 = new SystemEventData(str, eventData2, eventData4, eventData6, eventData8, eventData10, eventData12, eventData14, halEventData2, settingsEventData2, new FrameworkApiEventData(eventData22, eventData23));
        Log.d("SystemEventDataSource", "updatedEventData: " + systemEventData2);
        SystemEventDataSource systemEventDataSource4 = this.this$0;
        String str2 = this.$intentAction;
        systemEventDataSource4.getClass();
        boolean areEqual = Intrinsics.areEqual(str2, "android.hardware.usb.action.USB_PORT_COMPLIANCE_CHANGED");
        Unit unit = Unit.INSTANCE;
        if (!areEqual && systemEventData2.equals(this.this$0.lastSystemEventData)) {
            Log.d("SystemEventDataSource", "extra doesn't changed, no need to onEventSourceUpdate");
            return unit;
        }
        SystemEventDataSource systemEventDataSource5 = this.this$0;
        systemEventDataSource5.lastSystemEventData = systemEventData2;
        systemEventDataSource5.onEventSourceUpdate.invoke(systemEventData2, BatteryEventType.$ENTRIES);
        return unit;
    }
}
