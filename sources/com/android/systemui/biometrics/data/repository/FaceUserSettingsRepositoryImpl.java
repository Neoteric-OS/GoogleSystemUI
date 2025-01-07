package com.android.systemui.biometrics.data.repository;

import android.os.Handler;
import com.android.systemui.util.settings.SecureSettings;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FaceUserSettingsRepositoryImpl implements FaceUserSettingsRepository {
    public final StateFlowImpl _alwaysRequireConfirmationInApps;
    public final ReadonlyStateFlow alwaysRequireConfirmationInApps;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Empty implements FaceUserSettingsRepository {
        public static final Empty INSTANCE = new Empty();
        public static final FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2 alwaysRequireConfirmationInApps = new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(Boolean.FALSE);

        @Override // com.android.systemui.biometrics.data.repository.FaceUserSettingsRepository
        public final Flow getAlwaysRequireConfirmationInApps() {
            return alwaysRequireConfirmationInApps;
        }
    }

    public FaceUserSettingsRepositoryImpl(int i, Handler handler, SecureSettings secureSettings) {
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(Boolean.FALSE);
        this._alwaysRequireConfirmationInApps = MutableStateFlow;
        this.alwaysRequireConfirmationInApps = new ReadonlyStateFlow(MutableStateFlow);
    }

    @Override // com.android.systemui.biometrics.data.repository.FaceUserSettingsRepository
    public final Flow getAlwaysRequireConfirmationInApps() {
        return this.alwaysRequireConfirmationInApps;
    }
}
