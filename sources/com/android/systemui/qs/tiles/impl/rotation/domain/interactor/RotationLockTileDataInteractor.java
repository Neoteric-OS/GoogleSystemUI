package com.android.systemui.qs.tiles.impl.rotation.domain.interactor;

import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.UserHandle;
import com.android.systemui.camera.data.repository.CameraAutoRotateRepositoryImpl;
import com.android.systemui.camera.data.repository.CameraSensorPrivacyRepositoryImpl;
import com.android.systemui.qs.tiles.base.interactor.QSTileDataInteractor;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.RotationLockController;
import com.android.systemui.util.kotlin.BatteryControllerExtKt;
import com.android.systemui.util.kotlin.RotationLockControllerExtKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RotationLockTileDataInteractor implements QSTileDataInteractor {
    public final BatteryController batteryController;
    public final CameraAutoRotateRepositoryImpl cameraAutoRotateRepository;
    public final CameraSensorPrivacyRepositoryImpl cameraSensorPrivacyRepository;
    public final PackageManager packageManager;
    public final Resources resources;
    public final RotationLockController rotationLockController;

    public RotationLockTileDataInteractor(RotationLockController rotationLockController, BatteryController batteryController, CameraAutoRotateRepositoryImpl cameraAutoRotateRepositoryImpl, CameraSensorPrivacyRepositoryImpl cameraSensorPrivacyRepositoryImpl, PackageManager packageManager, Resources resources) {
        this.rotationLockController = rotationLockController;
        this.batteryController = batteryController;
        this.cameraAutoRotateRepository = cameraAutoRotateRepositoryImpl;
        this.cameraSensorPrivacyRepository = cameraSensorPrivacyRepositoryImpl;
        this.packageManager = packageManager;
        this.resources = resources;
    }

    @Override // com.android.systemui.qs.tiles.base.interactor.QSTileDataInteractor
    public final Flow availability(UserHandle userHandle) {
        return new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(Boolean.TRUE);
    }

    @Override // com.android.systemui.qs.tiles.base.interactor.QSTileDataInteractor
    public final Flow tileData(UserHandle userHandle, ReadonlyStateFlow readonlyStateFlow) {
        return FlowKt.combine(RotationLockControllerExtKt.isRotationLockEnabled(this.rotationLockController), this.cameraSensorPrivacyRepository.isEnabled(userHandle), BatteryControllerExtKt.isBatteryPowerSaveEnabled(this.batteryController), this.cameraAutoRotateRepository.isCameraAutoRotateSettingEnabled(userHandle), new RotationLockTileDataInteractor$tileData$1(this, null));
    }
}
