package com.android.systemui.deviceconfig.data.repository;

import com.android.systemui.util.DeviceConfigProxy;
import java.util.concurrent.Executor;
import kotlinx.coroutines.CoroutineDispatcher;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DeviceConfigRepository {
    public final CoroutineDispatcher backgroundDispatcher;
    public final Executor backgroundExecutor;
    public final DeviceConfigProxy dataSource;

    public DeviceConfigRepository(Executor executor, CoroutineDispatcher coroutineDispatcher, DeviceConfigProxy deviceConfigProxy) {
    }
}
