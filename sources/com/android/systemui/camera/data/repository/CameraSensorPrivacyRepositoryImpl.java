package com.android.systemui.camera.data.repository;

import android.hardware.SensorPrivacyManager;
import android.os.UserHandle;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CameraSensorPrivacyRepositoryImpl {
    public final CoroutineContext bgCoroutineContext;
    public final SensorPrivacyManager privacyManager;
    public final CoroutineScope scope;
    public final Map userMap = new LinkedHashMap();

    public CameraSensorPrivacyRepositoryImpl(CoroutineContext coroutineContext, CoroutineScope coroutineScope, SensorPrivacyManager sensorPrivacyManager) {
        this.bgCoroutineContext = coroutineContext;
        this.scope = coroutineScope;
        this.privacyManager = sensorPrivacyManager;
    }

    public final StateFlow isEnabled(UserHandle userHandle) {
        Map map = this.userMap;
        Integer valueOf = Integer.valueOf(userHandle.getIdentifier());
        Object obj = map.get(valueOf);
        if (obj == null) {
            SensorPrivacyManager sensorPrivacyManager = this.privacyManager;
            obj = FlowKt.stateIn(FlowKt.flowOn(FlowKt.distinctUntilChanged(new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new CameraSensorPrivacyRepositoryKt$isEnabled$2(sensorPrivacyManager, null), FlowConflatedKt.conflatedCallbackFlow(new CameraSensorPrivacyRepositoryKt$isEnabled$1(sensorPrivacyManager, userHandle, null)))), this.bgCoroutineContext), this.scope, SharingStarted.Companion.WhileSubscribed$default(3), Boolean.FALSE);
            map.put(valueOf, obj);
        }
        return (StateFlow) obj;
    }
}
