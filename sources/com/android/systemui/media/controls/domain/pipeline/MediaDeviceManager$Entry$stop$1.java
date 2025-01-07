package com.android.systemui.media.controls.domain.pipeline;

import android.graphics.drawable.Drawable;
import android.media.AudioDeviceAttributes;
import android.media.session.MediaController;
import com.android.settingslib.media.DeviceIconUtil;
import com.android.settingslib.media.LocalMediaManager;
import com.android.systemui.media.controls.domain.pipeline.MediaDeviceManager;
import com.android.systemui.media.muteawait.MediaMuteAwaitConnectionManager;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaDeviceManager$Entry$stop$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ MediaDeviceManager.Entry this$0;
    public final /* synthetic */ MediaDeviceManager this$1;

    public /* synthetic */ MediaDeviceManager$Entry$stop$1(MediaDeviceManager.Entry entry, MediaDeviceManager mediaDeviceManager, int i) {
        this.$r8$classId = i;
        this.this$0 = entry;
        this.this$1 = mediaDeviceManager;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MediaController.PlaybackInfo playbackInfo;
        MediaController.PlaybackInfo playbackInfo2;
        switch (this.$r8$classId) {
            case 0:
                MediaDeviceManager.Entry entry = this.this$0;
                if (entry.started) {
                    entry.started = false;
                    MediaController mediaController = entry.controller;
                    if (mediaController != null) {
                        mediaController.unregisterCallback(entry);
                    }
                    MediaDeviceManager.Entry entry2 = this.this$0;
                    entry2.localMediaManager.unregisterCallback(entry2);
                    MediaMuteAwaitConnectionManager mediaMuteAwaitConnectionManager = this.this$0.muteAwaitConnectionManager;
                    mediaMuteAwaitConnectionManager.audioManager.unregisterMuteAwaitConnectionCallback(mediaMuteAwaitConnectionManager.muteAwaitConnectionChangeListener);
                    ((ConfigurationControllerImpl) this.this$1.configurationController).removeCallback(this.this$0.configListener);
                    break;
                }
                break;
            default:
                MediaDeviceManager.Entry entry3 = this.this$0;
                if (!entry3.started) {
                    entry3.localMediaManager.registerCallback(entry3);
                    MediaMuteAwaitConnectionManager mediaMuteAwaitConnectionManager2 = this.this$0.muteAwaitConnectionManager;
                    mediaMuteAwaitConnectionManager2.audioManager.registerMuteAwaitConnectionCallback(mediaMuteAwaitConnectionManager2.mainExecutor, mediaMuteAwaitConnectionManager2.muteAwaitConnectionChangeListener);
                    AudioDeviceAttributes mutingExpectedDevice = mediaMuteAwaitConnectionManager2.audioManager.getMutingExpectedDevice();
                    if (mutingExpectedDevice != null) {
                        mediaMuteAwaitConnectionManager2.currentMutedDevice = mutingExpectedDevice;
                        String address = mutingExpectedDevice.getAddress();
                        String name = mutingExpectedDevice.getName();
                        int type = mutingExpectedDevice.getType();
                        DeviceIconUtil deviceIconUtil = mediaMuteAwaitConnectionManager2.deviceIconUtil;
                        Drawable drawable = deviceIconUtil.mContext.getDrawable(deviceIconUtil.getIconResIdFromMediaRouteType(DeviceIconUtil.AUDIO_DEVICE_TO_MEDIA_ROUTE_TYPE.get(type, -1)));
                        Iterator it = ((CopyOnWriteArrayList) mediaMuteAwaitConnectionManager2.localMediaManager.getCallbacks()).iterator();
                        while (it.hasNext()) {
                            ((LocalMediaManager.DeviceCallback) it.next()).onAboutToConnectDeviceAdded(address, name, drawable);
                        }
                    }
                    MediaDeviceManager.Entry entry4 = this.this$0;
                    MediaController mediaController2 = entry4.controller;
                    entry4.playbackType = (mediaController2 == null || (playbackInfo2 = mediaController2.getPlaybackInfo()) == null) ? 0 : playbackInfo2.getPlaybackType();
                    MediaDeviceManager.Entry entry5 = this.this$0;
                    MediaController mediaController3 = entry5.controller;
                    entry5.playbackVolumeControlId = (mediaController3 == null || (playbackInfo = mediaController3.getPlaybackInfo()) == null) ? null : playbackInfo.getVolumeControlId();
                    MediaDeviceManager.Entry entry6 = this.this$0;
                    MediaController mediaController4 = entry6.controller;
                    if (mediaController4 != null) {
                        mediaController4.registerCallback(entry6);
                    }
                    this.this$0.updateCurrent();
                    MediaDeviceManager.Entry entry7 = this.this$0;
                    entry7.started = true;
                    ((ConfigurationControllerImpl) this.this$1.configurationController).addCallback(entry7.configListener);
                    break;
                }
                break;
        }
    }
}
