package com.android.systemui.shade.carrier;

import android.text.TextUtils;
import android.util.Log;
import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.compose.foundation.text.input.internal.RecordingInputConnection$$ExternalSyntheticOutline0;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.ListImplementation$$ExternalSyntheticOutline0;
import com.android.keyguard.CarrierTextManager;
import com.android.keyguard.logging.CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.shade.carrier.ShadeCarrierGroupController;
import java.util.function.Consumer;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class ShadeCarrierGroupController$$ExternalSyntheticLambda4 implements Consumer {
    public final /* synthetic */ ShadeCarrierGroupController f$0;

    public /* synthetic */ ShadeCarrierGroupController$$ExternalSyntheticLambda4(ShadeCarrierGroupController shadeCarrierGroupController) {
        this.f$0 = shadeCarrierGroupController;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        CellSignalState[] cellSignalStateArr;
        ShadeCarrier[] shadeCarrierArr;
        ShadeCarrierGroupController shadeCarrierGroupController = this.f$0;
        CarrierTextManager.CarrierTextCallbackInfo carrierTextCallbackInfo = (CarrierTextManager.CarrierTextCallbackInfo) obj;
        ShadeCarrierGroupController.H h = shadeCarrierGroupController.mMainHandler;
        if (!h.getLooper().isCurrentThread()) {
            h.obtainMessage(0, carrierTextCallbackInfo).sendToTarget();
            return;
        }
        ShadeCarrierGroupControllerLogger shadeCarrierGroupControllerLogger = shadeCarrierGroupController.mLogger;
        shadeCarrierGroupControllerLogger.getClass();
        LogLevel logLevel = LogLevel.VERBOSE;
        ShadeCarrierGroupControllerLogger$logHandleUpdateCarrierInfo$2 shadeCarrierGroupControllerLogger$logHandleUpdateCarrierInfo$2 = new Function1() { // from class: com.android.systemui.shade.carrier.ShadeCarrierGroupControllerLogger$logHandleUpdateCarrierInfo$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                LogMessage logMessage = (LogMessage) obj2;
                String str1 = logMessage.getStr1();
                boolean bool1 = logMessage.getBool1();
                return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0.m("handleUpdateCarrierInfo: result=(carrierText=", str1, ", anySimReady=", bool1, ", airplaneMode="), logMessage.getBool2(), ")");
            }
        };
        LogBuffer logBuffer = shadeCarrierGroupControllerLogger.buffer;
        LogMessage obtain = logBuffer.obtain("SCGC", logLevel, shadeCarrierGroupControllerLogger$logHandleUpdateCarrierInfo$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = String.valueOf(carrierTextCallbackInfo.carrierText);
        boolean z = carrierTextCallbackInfo.anySimReady;
        logMessageImpl.bool1 = z;
        boolean z2 = carrierTextCallbackInfo.airplaneMode;
        logMessageImpl.bool2 = z2;
        logBuffer.commit(obtain);
        shadeCarrierGroupController.mNoSimTextView.setVisibility(8);
        if (carrierTextCallbackInfo.isInSatelliteMode) {
            CharSequence charSequence = carrierTextCallbackInfo.carrierText;
            LogMessage obtain2 = logBuffer.obtain("SCGC", logLevel, new Function1() { // from class: com.android.systemui.shade.carrier.ShadeCarrierGroupControllerLogger$logUsingSatelliteText$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("┗ updating No SIM view with satellite text=", ((LogMessage) obj2).getStr1());
                }
            }, null);
            ((LogMessageImpl) obtain2).str1 = String.valueOf(charSequence);
            logBuffer.commit(obtain2);
            shadeCarrierGroupController.showSingleText(carrierTextCallbackInfo.carrierText);
        } else if (z2 || !z) {
            CharSequence charSequence2 = carrierTextCallbackInfo.carrierText;
            LogMessage obtain3 = logBuffer.obtain("SCGC", logLevel, new Function1() { // from class: com.android.systemui.shade.carrier.ShadeCarrierGroupControllerLogger$logUsingNoSimView$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("┗ updating No SIM view with text=", ((LogMessage) obj2).getStr1());
                }
            }, null);
            ((LogMessageImpl) obtain3).str1 = String.valueOf(charSequence2);
            logBuffer.commit(obtain3);
            shadeCarrierGroupController.showSingleText(carrierTextCallbackInfo.carrierText);
        } else {
            boolean[] zArr = new boolean[3];
            CharSequence[] charSequenceArr = carrierTextCallbackInfo.listOfCarriers;
            int length = charSequenceArr.length;
            int[] iArr = carrierTextCallbackInfo.subscriptionIds;
            if (length == iArr.length) {
                logBuffer.commit(logBuffer.obtain("SCGC", logLevel, new Function1() { // from class: com.android.systemui.shade.carrier.ShadeCarrierGroupControllerLogger$logUsingSimViews$2
                    @Override // kotlin.jvm.functions.Function1
                    public final /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                        return "┗ updating SIM views";
                    }
                }, null));
                int i = 0;
                while (true) {
                    cellSignalStateArr = shadeCarrierGroupController.mInfos;
                    shadeCarrierArr = shadeCarrierGroupController.mCarrierGroups;
                    if (i >= 3 || i >= carrierTextCallbackInfo.listOfCarriers.length) {
                        break;
                    }
                    int slotIndex = shadeCarrierGroupController.getSlotIndex(iArr[i]);
                    if (slotIndex >= 3) {
                        RecordingInputConnection$$ExternalSyntheticOutline0.m("updateInfoCarrier - slot: ", "ShadeCarrierGroup", slotIndex);
                    } else if (slotIndex == -1) {
                        Log.e("ShadeCarrierGroup", "Invalid SIM slot index for subscription: " + iArr[i]);
                    } else {
                        String trim = carrierTextCallbackInfo.listOfCarriers[i].toString().trim();
                        if (!TextUtils.isEmpty(trim)) {
                            cellSignalStateArr[slotIndex] = cellSignalStateArr[slotIndex].changeVisibility(true);
                            zArr[slotIndex] = true;
                            shadeCarrierArr[slotIndex].mCarrierText.setText(trim);
                            shadeCarrierArr[slotIndex].setVisibility(0);
                        }
                    }
                    i++;
                }
                for (int i2 = 0; i2 < 3; i2++) {
                    if (!zArr[i2]) {
                        cellSignalStateArr[i2] = cellSignalStateArr[i2].changeVisibility(false);
                        shadeCarrierArr[i2].setVisibility(8);
                    }
                }
            } else {
                int length2 = charSequenceArr.length;
                int length3 = iArr.length;
                LogMessage obtain4 = logBuffer.obtain("SCGC", LogLevel.ERROR, new Function1() { // from class: com.android.systemui.shade.carrier.ShadeCarrierGroupControllerLogger$logInvalidArrayLengths$2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        LogMessage logMessage = (LogMessage) obj2;
                        return ListImplementation$$ExternalSyntheticOutline0.m("┗ carriers.length != subIds.length. carriers.length=", logMessage.getInt1(), logMessage.getInt2(), " subs.length=");
                    }
                }, null);
                LogMessageImpl logMessageImpl2 = (LogMessageImpl) obtain4;
                logMessageImpl2.int1 = length2;
                logMessageImpl2.int2 = length3;
                logBuffer.commit(obtain4);
            }
        }
        shadeCarrierGroupController.handleUpdateState();
    }
}
