package com.android.systemui.deviceentry.domain.interactor;

import android.content.res.Resources;
import com.android.systemui.biometrics.FaceHelpMessageDebouncer;
import com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryImpl;
import com.android.wm.shell.R;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SafeFlow;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DeviceEntryFaceAuthStatusInteractor {
    public final ReadonlyStateFlow authenticationStatus;
    public final Set faceAcquiredInfoIgnoreList;
    public final FaceHelpMessageDebouncer faceHelpMessageDebouncer = new FaceHelpMessageDebouncer(0.0f, 4, 200, 200);

    public DeviceEntryFaceAuthStatusInteractor(DeviceEntryFaceAuthRepositoryImpl deviceEntryFaceAuthRepositoryImpl, Resources resources, CoroutineScope coroutineScope) {
        this.faceAcquiredInfoIgnoreList = (Set) Arrays.stream(resources.getIntArray(R.array.config_face_acquire_device_entry_ignorelist)).boxed().collect(Collectors.toSet());
        this.authenticationStatus = FlowKt.stateIn(new SafeFlow(new DeviceEntryFaceAuthStatusInteractor$special$$inlined$transform$1(new FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1(deviceEntryFaceAuthRepositoryImpl._authenticationStatus), null, this)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), null);
    }
}
