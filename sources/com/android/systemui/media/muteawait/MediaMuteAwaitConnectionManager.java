package com.android.systemui.media.muteawait;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.AudioDeviceAttributes;
import android.media.AudioManager;
import androidx.room.TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0;
import com.android.settingslib.media.DeviceIconUtil;
import com.android.settingslib.media.LocalMediaManager;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaMuteAwaitConnectionManager {
    public final AudioManager audioManager;
    public AudioDeviceAttributes currentMutedDevice;
    public final DeviceIconUtil deviceIconUtil;
    public final LocalMediaManager localMediaManager;
    public final MediaMuteAwaitLogger logger;
    public final Executor mainExecutor;
    public final MediaMuteAwaitConnectionManager$muteAwaitConnectionChangeListener$1 muteAwaitConnectionChangeListener = new AudioManager.MuteAwaitConnectionCallback() { // from class: com.android.systemui.media.muteawait.MediaMuteAwaitConnectionManager$muteAwaitConnectionChangeListener$1
        public final void onMutedUntilConnection(AudioDeviceAttributes audioDeviceAttributes, int[] iArr) {
            MediaMuteAwaitLogger mediaMuteAwaitLogger = MediaMuteAwaitConnectionManager.this.logger;
            String address = audioDeviceAttributes.getAddress();
            String name = audioDeviceAttributes.getName();
            MediaMuteAwaitConnectionManager.this.getClass();
            boolean contains = ArraysKt.contains(iArr, 1);
            mediaMuteAwaitLogger.getClass();
            LogLevel logLevel = LogLevel.DEBUG;
            MediaMuteAwaitLogger$logMutedDeviceAdded$2 mediaMuteAwaitLogger$logMutedDeviceAdded$2 = new Function1() { // from class: com.android.systemui.media.muteawait.MediaMuteAwaitLogger$logMutedDeviceAdded$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    LogMessage logMessage = (LogMessage) obj;
                    String str1 = logMessage.getStr1();
                    String str2 = logMessage.getStr2();
                    boolean bool1 = logMessage.getBool1();
                    StringBuilder m = TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("Muted device added: address=", str1, " name=", str2, " hasMediaUsage=");
                    m.append(bool1);
                    return m.toString();
                }
            };
            LogBuffer logBuffer = mediaMuteAwaitLogger.buffer;
            LogMessage obtain = logBuffer.obtain("MediaMuteAwait", logLevel, mediaMuteAwaitLogger$logMutedDeviceAdded$2, null);
            LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
            logMessageImpl.str1 = address;
            logMessageImpl.str2 = name;
            logMessageImpl.bool1 = contains;
            logBuffer.commit(obtain);
            MediaMuteAwaitConnectionManager.this.getClass();
            if (ArraysKt.contains(iArr, 1)) {
                MediaMuteAwaitConnectionManager mediaMuteAwaitConnectionManager = MediaMuteAwaitConnectionManager.this;
                mediaMuteAwaitConnectionManager.currentMutedDevice = audioDeviceAttributes;
                LocalMediaManager localMediaManager = mediaMuteAwaitConnectionManager.localMediaManager;
                String address2 = audioDeviceAttributes.getAddress();
                String name2 = audioDeviceAttributes.getName();
                MediaMuteAwaitConnectionManager mediaMuteAwaitConnectionManager2 = MediaMuteAwaitConnectionManager.this;
                mediaMuteAwaitConnectionManager2.getClass();
                int type = audioDeviceAttributes.getType();
                DeviceIconUtil deviceIconUtil = mediaMuteAwaitConnectionManager2.deviceIconUtil;
                Drawable drawable = deviceIconUtil.mContext.getDrawable(deviceIconUtil.getIconResIdFromMediaRouteType(DeviceIconUtil.AUDIO_DEVICE_TO_MEDIA_ROUTE_TYPE.get(type, -1)));
                Iterator it = ((CopyOnWriteArrayList) localMediaManager.getCallbacks()).iterator();
                while (it.hasNext()) {
                    ((LocalMediaManager.DeviceCallback) it.next()).onAboutToConnectDeviceAdded(address2, name2, drawable);
                }
            }
        }

        public final void onUnmutedEvent(int i, AudioDeviceAttributes audioDeviceAttributes, int[] iArr) {
            boolean areEqual = Intrinsics.areEqual(MediaMuteAwaitConnectionManager.this.currentMutedDevice, audioDeviceAttributes);
            MediaMuteAwaitLogger mediaMuteAwaitLogger = MediaMuteAwaitConnectionManager.this.logger;
            String address = audioDeviceAttributes.getAddress();
            String name = audioDeviceAttributes.getName();
            MediaMuteAwaitConnectionManager.this.getClass();
            boolean contains = ArraysKt.contains(iArr, 1);
            mediaMuteAwaitLogger.getClass();
            LogLevel logLevel = LogLevel.DEBUG;
            MediaMuteAwaitLogger$logMutedDeviceRemoved$2 mediaMuteAwaitLogger$logMutedDeviceRemoved$2 = new Function1() { // from class: com.android.systemui.media.muteawait.MediaMuteAwaitLogger$logMutedDeviceRemoved$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    LogMessage logMessage = (LogMessage) obj;
                    String str1 = logMessage.getStr1();
                    String str2 = logMessage.getStr2();
                    boolean bool1 = logMessage.getBool1();
                    boolean bool2 = logMessage.getBool2();
                    StringBuilder m = TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("Muted device removed: address=", str1, " name=", str2, " hasMediaUsage=");
                    m.append(bool1);
                    m.append(" isMostRecentDevice=");
                    m.append(bool2);
                    return m.toString();
                }
            };
            LogBuffer logBuffer = mediaMuteAwaitLogger.buffer;
            LogMessage obtain = logBuffer.obtain("MediaMuteAwait", logLevel, mediaMuteAwaitLogger$logMutedDeviceRemoved$2, null);
            LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
            logMessageImpl.str1 = address;
            logMessageImpl.str2 = name;
            logMessageImpl.bool1 = contains;
            logMessageImpl.bool2 = areEqual;
            logBuffer.commit(obtain);
            if (areEqual) {
                MediaMuteAwaitConnectionManager.this.getClass();
                if (ArraysKt.contains(iArr, 1)) {
                    MediaMuteAwaitConnectionManager mediaMuteAwaitConnectionManager = MediaMuteAwaitConnectionManager.this;
                    mediaMuteAwaitConnectionManager.currentMutedDevice = null;
                    Iterator it = ((CopyOnWriteArrayList) mediaMuteAwaitConnectionManager.localMediaManager.getCallbacks()).iterator();
                    while (it.hasNext()) {
                        ((LocalMediaManager.DeviceCallback) it.next()).onAboutToConnectDeviceRemoved();
                    }
                }
            }
        }
    };

    /* JADX WARN: Type inference failed for: r1v4, types: [com.android.systemui.media.muteawait.MediaMuteAwaitConnectionManager$muteAwaitConnectionChangeListener$1] */
    public MediaMuteAwaitConnectionManager(Executor executor, LocalMediaManager localMediaManager, Context context, DeviceIconUtil deviceIconUtil, MediaMuteAwaitLogger mediaMuteAwaitLogger) {
        this.mainExecutor = executor;
        this.localMediaManager = localMediaManager;
        this.deviceIconUtil = deviceIconUtil;
        this.logger = mediaMuteAwaitLogger;
        this.audioManager = (AudioManager) context.getSystemService("audio");
    }
}
