package com.android.systemui.util.sensors;

import android.content.Context;
import android.hardware.HardwareBuffer;
import android.hardware.Sensor;
import android.hardware.SensorAdditionalInfo;
import android.hardware.SensorDirectChannel;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.TriggerEventListener;
import android.os.Handler;
import android.os.MemoryFile;
import android.util.Log;
import com.android.internal.util.Preconditions;
import com.android.systemui.plugins.Plugin;
import com.android.systemui.plugins.PluginListener;
import com.android.systemui.plugins.PluginManager;
import com.android.systemui.plugins.SensorManagerPlugin;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.systemui.util.concurrency.ThreadFactoryImpl;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AsyncSensorManager extends SensorManager implements PluginListener {
    public final ExecutorImpl mExecutor;
    public final SensorManager mInner;
    public final List mPlugins = new ArrayList();
    public final List mSensorCache;

    public AsyncSensorManager(SensorManager sensorManager, ThreadFactoryImpl threadFactoryImpl, PluginManager pluginManager) {
        this.mInner = sensorManager;
        this.mExecutor = threadFactoryImpl.buildExecutorOnNewThread("async_sensor");
        this.mSensorCache = sensorManager.getSensorList(-1);
        if (pluginManager != null) {
            pluginManager.addPluginListener((PluginListener) this, SensorManagerPlugin.class, true);
        }
    }

    public final boolean cancelTriggerSensorImpl(TriggerEventListener triggerEventListener, Sensor sensor, boolean z) {
        Preconditions.checkArgument(z);
        this.mExecutor.execute(new AsyncSensorManager$$ExternalSyntheticLambda6(this, triggerEventListener, sensor, 0));
        return true;
    }

    public final int configureDirectChannelImpl(SensorDirectChannel sensorDirectChannel, Sensor sensor, int i) {
        throw new UnsupportedOperationException("not implemented");
    }

    public final SensorDirectChannel createDirectChannelImpl(MemoryFile memoryFile, HardwareBuffer hardwareBuffer) {
        throw new UnsupportedOperationException("not implemented");
    }

    public final void destroyDirectChannelImpl(SensorDirectChannel sensorDirectChannel) {
        throw new UnsupportedOperationException("not implemented");
    }

    public final boolean flushImpl(SensorEventListener sensorEventListener) {
        return this.mInner.flush(sensorEventListener);
    }

    public final List getFullDynamicSensorList() {
        return this.mInner.getSensorList(-1);
    }

    public final List getFullSensorList() {
        return this.mSensorCache;
    }

    public final boolean initDataInjectionImpl(boolean z, int i) {
        throw new UnsupportedOperationException("not implemented");
    }

    public final boolean injectSensorDataImpl(Sensor sensor, float[] fArr, int i, long j) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override // com.android.systemui.plugins.PluginListener
    public final void onPluginConnected(Plugin plugin, Context context) {
        this.mPlugins.add((SensorManagerPlugin) plugin);
    }

    @Override // com.android.systemui.plugins.PluginListener
    public final void onPluginDisconnected(Plugin plugin) {
        this.mPlugins.remove((SensorManagerPlugin) plugin);
    }

    public final void registerDynamicSensorCallbackImpl(SensorManager.DynamicSensorCallback dynamicSensorCallback, Handler handler) {
        this.mExecutor.execute(new AsyncSensorManager$$ExternalSyntheticLambda4(this, dynamicSensorCallback, handler, 1));
    }

    public final boolean registerListenerImpl(final SensorEventListener sensorEventListener, final Sensor sensor, final int i, final Handler handler, final int i2, int i3) {
        this.mExecutor.execute(new Runnable() { // from class: com.android.systemui.util.sensors.AsyncSensorManager$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                AsyncSensorManager asyncSensorManager = AsyncSensorManager.this;
                SensorEventListener sensorEventListener2 = sensorEventListener;
                Sensor sensor2 = sensor;
                if (asyncSensorManager.mInner.registerListener(sensorEventListener2, sensor2, i, i2, handler)) {
                    return;
                }
                Log.e("AsyncSensorManager", "Registering " + sensorEventListener2 + " for " + sensor2 + " failed.");
            }
        });
        return true;
    }

    public final boolean requestTriggerSensorImpl(TriggerEventListener triggerEventListener, Sensor sensor) {
        if (triggerEventListener == null) {
            throw new IllegalArgumentException("listener cannot be null");
        }
        if (sensor == null) {
            throw new IllegalArgumentException("sensor cannot be null");
        }
        this.mExecutor.execute(new AsyncSensorManager$$ExternalSyntheticLambda6(this, triggerEventListener, sensor, 1));
        return true;
    }

    public final boolean setOperationParameterImpl(SensorAdditionalInfo sensorAdditionalInfo) {
        this.mExecutor.execute(new AsyncSensorManager$$ExternalSyntheticLambda1(this, sensorAdditionalInfo, 0));
        return true;
    }

    public final void unregisterDynamicSensorCallbackImpl(SensorManager.DynamicSensorCallback dynamicSensorCallback) {
        this.mExecutor.execute(new AsyncSensorManager$$ExternalSyntheticLambda1(this, dynamicSensorCallback, 1));
    }

    public final void unregisterListenerImpl(SensorEventListener sensorEventListener, Sensor sensor) {
        this.mExecutor.execute(new AsyncSensorManager$$ExternalSyntheticLambda4(this, sensor, sensorEventListener, 0));
    }
}
