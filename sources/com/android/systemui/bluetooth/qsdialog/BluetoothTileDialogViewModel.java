package com.android.systemui.bluetooth.qsdialog;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.animation.DialogTransitionAnimator$createActivityTransitionController$1;
import com.android.systemui.animation.Expandable;
import com.android.systemui.plugins.ActivityStarter;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$53;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.StandaloneCoroutine;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BluetoothTileDialogViewModel {
    public final ActivityStarter activityStarter;
    public final CoroutineDispatcher backgroundDispatcher;
    public final BluetoothAutoOnInteractor bluetoothAutoOnInteractor;
    public final BluetoothDeviceMetadataInteractor bluetoothDeviceMetadataInteractor;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$53 bluetoothDialogDelegateFactory;
    public final BluetoothStateInteractor bluetoothStateInteractor;
    public final CoroutineScope coroutineScope;
    public final DeviceItemActionInteractor deviceItemActionInteractor;
    public final DeviceItemInteractor deviceItemInteractor;
    public final DialogTransitionAnimator dialogTransitionAnimator;
    public StandaloneCoroutine job;
    public final CoroutineDispatcher mainDispatcher;
    public final SharedPreferences sharedPreferences;
    public final UiEventLogger uiEventLogger;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class UiProperties {
        public static final Companion Companion = new Companion();
        public final int autoOnToggleVisibility;
        public final int scrollViewMinHeightResId;
        public final int subTitleResId;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Companion {
        }

        public UiProperties(int i, int i2, int i3) {
            this.subTitleResId = i;
            this.autoOnToggleVisibility = i2;
            this.scrollViewMinHeightResId = i3;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof UiProperties)) {
                return false;
            }
            UiProperties uiProperties = (UiProperties) obj;
            return this.subTitleResId == uiProperties.subTitleResId && this.autoOnToggleVisibility == uiProperties.autoOnToggleVisibility && this.scrollViewMinHeightResId == uiProperties.scrollViewMinHeightResId;
        }

        public final int hashCode() {
            return Integer.hashCode(this.scrollViewMinHeightResId) + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.autoOnToggleVisibility, Integer.hashCode(this.subTitleResId) * 31, 31);
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("UiProperties(subTitleResId=");
            sb.append(this.subTitleResId);
            sb.append(", autoOnToggleVisibility=");
            sb.append(this.autoOnToggleVisibility);
            sb.append(", scrollViewMinHeightResId=");
            return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.scrollViewMinHeightResId, ")");
        }
    }

    public BluetoothTileDialogViewModel(DeviceItemInteractor deviceItemInteractor, DeviceItemActionInteractor deviceItemActionInteractor, BluetoothStateInteractor bluetoothStateInteractor, BluetoothAutoOnInteractor bluetoothAutoOnInteractor, AudioSharingInteractor audioSharingInteractor, BluetoothDeviceMetadataInteractor bluetoothDeviceMetadataInteractor, DialogTransitionAnimator dialogTransitionAnimator, ActivityStarter activityStarter, UiEventLogger uiEventLogger, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, CoroutineDispatcher coroutineDispatcher2, SharedPreferences sharedPreferences, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$53 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$53) {
        this.deviceItemInteractor = deviceItemInteractor;
        this.deviceItemActionInteractor = deviceItemActionInteractor;
        this.bluetoothStateInteractor = bluetoothStateInteractor;
        this.bluetoothAutoOnInteractor = bluetoothAutoOnInteractor;
        this.bluetoothDeviceMetadataInteractor = bluetoothDeviceMetadataInteractor;
        this.dialogTransitionAnimator = dialogTransitionAnimator;
        this.activityStarter = activityStarter;
        this.uiEventLogger = uiEventLogger;
        this.coroutineScope = coroutineScope;
        this.mainDispatcher = coroutineDispatcher;
        this.backgroundDispatcher = coroutineDispatcher2;
        this.sharedPreferences = sharedPreferences;
        this.bluetoothDialogDelegateFactory = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$53;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:38:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0027  */
    /* JADX WARN: Type inference failed for: r11v7, types: [com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogViewModel$createBluetoothTileDialog$2] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$createBluetoothTileDialog(com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogViewModel r11, kotlin.coroutines.jvm.internal.ContinuationImpl r12) {
        /*
            Method dump skipped, instructions count: 311
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogViewModel.access$createBluetoothTileDialog(com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogViewModel, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public final Object isAutoOnToggleFeatureAvailable$frameworks__base__packages__SystemUI__android_common__SystemUI_core(Continuation continuation) {
        BluetoothAutoOnRepository bluetoothAutoOnRepository = this.bluetoothAutoOnInteractor.bluetoothAutoOnRepository;
        bluetoothAutoOnRepository.getClass();
        return BuildersKt.withContext(bluetoothAutoOnRepository.backgroundDispatcher, new BluetoothAutoOnRepository$isAutoOnSupported$2(bluetoothAutoOnRepository, null), continuation);
    }

    public final void showDialog(Expandable expandable) {
        StandaloneCoroutine standaloneCoroutine = this.job;
        if (standaloneCoroutine != null) {
            standaloneCoroutine.cancel(null);
        }
        this.job = null;
        this.job = BuildersKt.launch$default(this.coroutineScope, this.mainDispatcher, null, new BluetoothTileDialogViewModel$showDialog$1(this, expandable, null), 2);
    }

    public final void startSettingsActivity(Intent intent, View view) {
        StandaloneCoroutine standaloneCoroutine = this.job;
        if (standaloneCoroutine == null || !standaloneCoroutine.isActive()) {
            return;
        }
        intent.setFlags(335544320);
        DialogTransitionAnimator$createActivityTransitionController$1 createActivityTransitionController$default = DialogTransitionAnimator.createActivityTransitionController$default(this.dialogTransitionAnimator, view);
        if (createActivityTransitionController$default == null) {
            StandaloneCoroutine standaloneCoroutine2 = this.job;
            if (standaloneCoroutine2 != null) {
                standaloneCoroutine2.cancel(null);
            }
            this.job = null;
        }
        this.activityStarter.postStartActivityDismissingKeyguard(intent, 0, createActivityTransitionController$default);
    }
}
