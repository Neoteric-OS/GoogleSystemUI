package com.android.systemui.inputdevice.data.repository;

import android.hardware.input.InputManager;
import android.os.Handler;
import android.util.Log;
import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlin.Pair;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlySharedFlow;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class InputDeviceRepository {
    public final Handler backgroundHandler;
    public final ReadonlySharedFlow deviceChange;
    public final InputManager inputManager;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DeviceAdded implements DeviceChange {
        public final int deviceId;

        public DeviceAdded(int i) {
            this.deviceId = i;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof DeviceAdded) && this.deviceId == ((DeviceAdded) obj).deviceId;
        }

        public final int hashCode() {
            return Integer.hashCode(this.deviceId);
        }

        public final String toString() {
            return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(new StringBuilder("DeviceAdded(deviceId="), this.deviceId, ")");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface DeviceChange {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DeviceRemoved implements DeviceChange {
        public final int deviceId;

        public DeviceRemoved(int i) {
            this.deviceId = i;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof DeviceRemoved) && this.deviceId == ((DeviceRemoved) obj).deviceId;
        }

        public final int hashCode() {
            return Integer.hashCode(this.deviceId);
        }

        public final String toString() {
            return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(new StringBuilder("DeviceRemoved(deviceId="), this.deviceId, ")");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class FreshStart implements DeviceChange {
        public static final FreshStart INSTANCE = new FreshStart();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof FreshStart);
        }

        public final int hashCode() {
            return -375358235;
        }

        public final String toString() {
            return "FreshStart";
        }
    }

    public InputDeviceRepository(Handler handler, CoroutineScope coroutineScope, InputManager inputManager) {
        this.backgroundHandler = handler;
        this.inputManager = inputManager;
        this.deviceChange = FlowKt.shareIn(FlowConflatedKt.conflatedCallbackFlow(new InputDeviceRepository$deviceChange$1(this, null)), coroutineScope, SharingStarted.Companion.Lazily, 1);
    }

    public static final void access$sendWithLogging(InputDeviceRepository inputDeviceRepository, SendChannel sendChannel, Pair pair) {
        inputDeviceRepository.getClass();
        Object mo1790trySendJP2dKIU = sendChannel.mo1790trySendJP2dKIU(pair);
        if (mo1790trySendJP2dKIU instanceof ChannelResult.Failed) {
            Log.e("InputDeviceRepository", "Failed to send updated state - downstream canceled or failed.", ChannelResult.m1791exceptionOrNullimpl(mo1790trySendJP2dKIU));
        }
    }
}
