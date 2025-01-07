package com.google.android.systemui.statusbar.phone;

import android.app.AlarmManager;
import android.content.Context;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.wm.shell.R;
import com.google.android.systemui.power.batteryhealth.HealthManager;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Optional;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class CentralSurfacesGoogleModule_ProvideHealthManagerFactory implements Provider {
    public static Optional provideHealthManager(Context context, AlarmManager alarmManager, BroadcastDispatcher broadcastDispatcher, CoroutineDispatcher coroutineDispatcher, CoroutineScope coroutineScope) {
        Optional of = context.getResources().getBoolean(R.bool.config_battery_index_enabled) ? Optional.of(new HealthManager(context, alarmManager, broadcastDispatcher, coroutineDispatcher, coroutineScope)) : Optional.empty();
        Preconditions.checkNotNullFromProvides(of);
        return of;
    }
}
