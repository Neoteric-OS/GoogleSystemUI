package com.android.systemui.qs.tiles.dialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.compose.foundation.text.input.internal.RecordingInputConnection$$ExternalSyntheticOutline0;
import com.android.settingslib.satellite.SatelliteDialogUtils;
import com.android.systemui.qs.tiles.dialog.InternetDialogDelegate;
import com.android.wifitrackerlib.WifiEntry;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.StandaloneCoroutine;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class InternetDialogDelegate$$ExternalSyntheticLambda8 implements View.OnClickListener {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ InternetDialogDelegate f$0;

    public /* synthetic */ InternetDialogDelegate$$ExternalSyntheticLambda8(InternetDialogDelegate internetDialogDelegate, int i) {
        this.$r8$classId = i;
        this.f$0 = internetDialogDelegate;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i = this.$r8$classId;
        final InternetDialogDelegate internetDialogDelegate = this.f$0;
        switch (i) {
            case 0:
                WifiEntry wifiEntry = internetDialogDelegate.mConnectedWifiEntry;
                if (wifiEntry != null) {
                    internetDialogDelegate.mInternetDialogController.launchWifiDetailsSetting(view, wifiEntry.getKey());
                    break;
                }
                break;
            case 1:
                WifiEntry wifiEntry2 = internetDialogDelegate.mConnectedWifiEntry;
                InternetDialogController internetDialogController = internetDialogDelegate.mInternetDialogController;
                Intent configuratorQrCodeGeneratorIntentOrNull = internetDialogController.getConfiguratorQrCodeGeneratorIntentOrNull(wifiEntry2);
                if (configuratorQrCodeGeneratorIntentOrNull != null) {
                    internetDialogController.startActivity(configuratorQrCodeGeneratorIntentOrNull, view);
                    internetDialogDelegate.mUiEventLogger.log(InternetDialogDelegate.InternetDialogEvent.SHARE_WIFI_QS_BUTTON_CLICKED);
                    break;
                }
                break;
            case 2:
                final boolean isChecked = internetDialogDelegate.mWiFiToggle.isChecked();
                StandaloneCoroutine standaloneCoroutine = internetDialogDelegate.mClickJob;
                if (standaloneCoroutine == null || standaloneCoroutine.isCompleted()) {
                    internetDialogDelegate.mClickJob = SatelliteDialogUtils.mayStartSatelliteWarningDialog(internetDialogDelegate.mDialog.getContext(), internetDialogDelegate.mCoroutineScope, 0, new Function1() { // from class: com.android.systemui.qs.tiles.dialog.InternetDialogDelegate$$ExternalSyntheticLambda18
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            InternetDialogDelegate internetDialogDelegate2 = InternetDialogDelegate.this;
                            internetDialogDelegate2.getClass();
                            boolean booleanValue = ((Boolean) obj).booleanValue();
                            boolean z = isChecked;
                            if (!booleanValue) {
                                internetDialogDelegate2.mWiFiToggle.setChecked(!z);
                                return null;
                            }
                            InternetDialogController internetDialogController2 = internetDialogDelegate2.mInternetDialogController;
                            if (internetDialogController2.mWifiStateWorker.isWifiEnabled() == z) {
                                return null;
                            }
                            internetDialogController2.mWifiStateWorker.setWifiEnabled(z);
                            return null;
                        }
                    });
                    break;
                }
                break;
            case 3:
                internetDialogDelegate.mInternetDialogController.mConnectivityManager.setAirplaneMode(false);
                break;
            case 4:
                InternetDialogController internetDialogController2 = internetDialogDelegate.mInternetDialogController;
                int activeAutoSwitchNonDdsSubId = internetDialogController2.getActiveAutoSwitchNonDdsSubId();
                if (activeAutoSwitchNonDdsSubId != -1) {
                    Intent intent = new Intent("android.settings.NETWORK_OPERATOR_SETTINGS");
                    Bundle bundle = new Bundle();
                    bundle.putString(":settings:fragment_args_key", "auto_data_switch");
                    intent.putExtra("android.provider.extra.SUB_ID", activeAutoSwitchNonDdsSubId);
                    intent.putExtra(":settings:show_fragment_args", bundle);
                    internetDialogController2.startActivity(intent, view);
                    break;
                } else {
                    RecordingInputConnection$$ExternalSyntheticOutline0.m("launchMobileNetworkSettings fail, invalid subId:", "InternetDialogController", activeAutoSwitchNonDdsSubId);
                    break;
                }
            default:
                InternetDialogController internetDialogController3 = internetDialogDelegate.mInternetDialogController;
                internetDialogController3.startActivity(internetDialogController3.getSettingsIntent(), view);
                break;
        }
    }
}
