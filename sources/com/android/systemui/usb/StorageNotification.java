package com.android.systemui.usb;

import android.R;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.os.UserHandle;
import android.os.storage.DiskInfo;
import android.os.storage.StorageEventListener;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import android.os.storage.VolumeRecord;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.Log;
import android.util.SparseArray;
import androidx.compose.foundation.text.input.internal.RecordingInputConnection$$ExternalSyntheticOutline0;
import com.android.systemui.CoreStartable;
import com.android.systemui.SystemUIApplication;
import com.android.systemui.broadcast.BroadcastDispatcher;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StorageNotification implements CoreStartable {
    public final BroadcastDispatcher mBroadcastDispatcher;
    public final Context mContext;
    public final AnonymousClass2 mFinishReceiver;
    public final NotificationManager mNotificationManager;
    public final AnonymousClass2 mSnoozeReceiver;
    public final StorageManager mStorageManager;
    public final SparseArray mMoves = new SparseArray();
    public final AnonymousClass1 mListener = new StorageEventListener() { // from class: com.android.systemui.usb.StorageNotification.1
        public final void onDiskDestroyed(DiskInfo diskInfo) {
            StorageNotification.this.mNotificationManager.cancelAsUser(diskInfo.getId(), 1396986699, UserHandle.ALL);
        }

        public final void onDiskScanned(DiskInfo diskInfo, int i) {
            StorageNotification.this.onDiskScannedInternal(diskInfo, i);
        }

        public final void onVolumeForgotten(String str) {
            StorageNotification.this.mNotificationManager.cancelAsUser(str, 1397772886, UserHandle.ALL);
        }

        public final void onVolumeRecordChanged(VolumeRecord volumeRecord) {
            VolumeInfo findVolumeByUuid = StorageNotification.this.mStorageManager.findVolumeByUuid(volumeRecord.getFsUuid());
            if (findVolumeByUuid == null || !findVolumeByUuid.isMountedReadable()) {
                return;
            }
            StorageNotification.this.onVolumeStateChangedInternal(findVolumeByUuid);
        }

        public final void onVolumeStateChanged(VolumeInfo volumeInfo, int i, int i2) {
            StorageNotification.this.onVolumeStateChangedInternal(volumeInfo);
        }
    };
    public final AnonymousClass4 mMoveCallback = new PackageManager.MoveCallback() { // from class: com.android.systemui.usb.StorageNotification.4
        public final void onCreated(int i, Bundle bundle) {
            MoveInfo moveInfo = new MoveInfo();
            moveInfo.moveId = i;
            if (bundle != null) {
                moveInfo.packageName = bundle.getString("android.intent.extra.PACKAGE_NAME");
                moveInfo.label = bundle.getString("android.intent.extra.TITLE");
                moveInfo.volumeUuid = bundle.getString("android.os.storage.extra.FS_UUID");
            }
            StorageNotification.this.mMoves.put(i, moveInfo);
        }

        public final void onStatusChanged(int i, int i2, long j) {
            PendingIntent activityAsUser;
            String string;
            String string2;
            PendingIntent activityAsUser2;
            MoveInfo moveInfo = (MoveInfo) StorageNotification.this.mMoves.get(i);
            if (moveInfo == null) {
                RecordingInputConnection$$ExternalSyntheticOutline0.m("Ignoring unknown move ", "StorageNotification", i);
                return;
            }
            if (!PackageManager.isMoveStatusFinished(i2)) {
                StorageNotification storageNotification = StorageNotification.this;
                storageNotification.getClass();
                String string3 = !TextUtils.isEmpty(moveInfo.label) ? storageNotification.mContext.getString(R.string.ext_media_status_bad_removal, moveInfo.label) : storageNotification.mContext.getString(R.string.ext_media_status_formatting);
                CharSequence formatDuration = j < 0 ? null : DateUtils.formatDuration(j);
                if (moveInfo.packageName != null) {
                    Intent intent = new Intent();
                    if (storageNotification.isTv$2()) {
                        intent.setPackage("com.android.tv.settings");
                        intent.setAction("com.android.tv.settings.action.MOVE_APP");
                    } else {
                        if (!storageNotification.isAutomotive()) {
                            intent.setClassName("com.android.settings", "com.android.settings.deviceinfo.StorageWizardMoveProgress");
                        }
                        activityAsUser = null;
                    }
                    intent.putExtra("android.content.pm.extra.MOVE_ID", moveInfo.moveId);
                    activityAsUser = PendingIntent.getActivityAsUser(storageNotification.mContext, moveInfo.moveId, intent, 335544320, null, UserHandle.CURRENT);
                } else {
                    Intent intent2 = new Intent();
                    if (storageNotification.isTv$2()) {
                        intent2.setPackage("com.android.tv.settings");
                        intent2.setAction("com.android.tv.settings.action.MIGRATE_STORAGE");
                    } else {
                        if (!storageNotification.isAutomotive()) {
                            intent2.setClassName("com.android.settings", "com.android.settings.deviceinfo.StorageWizardMigrateProgress");
                        }
                        activityAsUser = null;
                    }
                    intent2.putExtra("android.content.pm.extra.MOVE_ID", moveInfo.moveId);
                    VolumeInfo findVolumeByQualifiedUuid = storageNotification.mStorageManager.findVolumeByQualifiedUuid(moveInfo.volumeUuid);
                    if (findVolumeByQualifiedUuid != null) {
                        intent2.putExtra("android.os.storage.extra.VOLUME_ID", findVolumeByQualifiedUuid.getId());
                    }
                    activityAsUser = PendingIntent.getActivityAsUser(storageNotification.mContext, moveInfo.moveId, intent2, 335544320, null, UserHandle.CURRENT);
                }
                Notification.Builder ongoing = new Notification.Builder(storageNotification.mContext, "DSK").setSmallIcon(R.drawable.ic_qs_ui_mode_night).setColor(storageNotification.mContext.getColor(R.color.system_notification_accent_color)).setContentTitle(string3).setContentText(formatDuration).setContentIntent(activityAsUser).setStyle(new Notification.BigTextStyle().bigText(formatDuration)).setVisibility(1).setLocalOnly(true).setCategory("progress").setProgress(100, i2, false).setOngoing(true);
                SystemUIApplication.overrideNotificationAppName(storageNotification.mContext, ongoing, false);
                storageNotification.mNotificationManager.notifyAsUser(moveInfo.packageName, 1397575510, ongoing.build(), UserHandle.ALL);
                return;
            }
            StorageNotification storageNotification2 = StorageNotification.this;
            storageNotification2.getClass();
            String str = moveInfo.packageName;
            if (str != null) {
                storageNotification2.mNotificationManager.cancelAsUser(str, 1397575510, UserHandle.ALL);
                return;
            }
            VolumeInfo primaryStorageCurrentVolume = storageNotification2.mContext.getPackageManager().getPrimaryStorageCurrentVolume();
            String bestVolumeDescription = storageNotification2.mStorageManager.getBestVolumeDescription(primaryStorageCurrentVolume);
            if (i2 == -100) {
                string = storageNotification2.mContext.getString(R.string.ext_media_status_ejecting);
                string2 = storageNotification2.mContext.getString(R.string.ext_media_status_checking, bestVolumeDescription);
            } else {
                string = storageNotification2.mContext.getString(R.string.ext_media_seamless_action);
                string2 = storageNotification2.mContext.getString(R.string.ext_media_ready_notification_message);
            }
            if (primaryStorageCurrentVolume == null || primaryStorageCurrentVolume.getDisk() == null) {
                if (primaryStorageCurrentVolume != null) {
                    Intent intent3 = new Intent();
                    if (storageNotification2.isTv$2()) {
                        intent3.setPackage("com.android.tv.settings");
                        intent3.setAction("android.settings.INTERNAL_STORAGE_SETTINGS");
                    } else if (!storageNotification2.isAutomotive()) {
                        int type = primaryStorageCurrentVolume.getType();
                        if (type == 0) {
                            intent3.setClassName("com.android.settings", "com.android.settings.Settings$PublicVolumeSettingsActivity");
                        } else if (type == 1) {
                            intent3.setClassName("com.android.settings", "com.android.settings.Settings$PrivateVolumeSettingsActivity");
                        }
                    }
                    intent3.putExtra("android.os.storage.extra.VOLUME_ID", primaryStorageCurrentVolume.getId());
                    activityAsUser2 = PendingIntent.getActivityAsUser(storageNotification2.mContext, primaryStorageCurrentVolume.getId().hashCode(), intent3, 335544320, null, UserHandle.CURRENT);
                }
                activityAsUser2 = null;
            } else {
                DiskInfo disk = primaryStorageCurrentVolume.getDisk();
                Intent intent4 = new Intent();
                if (storageNotification2.isTv$2()) {
                    intent4.setPackage("com.android.tv.settings");
                    intent4.setAction("android.settings.INTERNAL_STORAGE_SETTINGS");
                } else {
                    if (!storageNotification2.isAutomotive()) {
                        intent4.setClassName("com.android.settings", "com.android.settings.deviceinfo.StorageWizardReady");
                    }
                    activityAsUser2 = null;
                }
                intent4.putExtra("android.os.storage.extra.DISK_ID", disk.getId());
                activityAsUser2 = PendingIntent.getActivityAsUser(storageNotification2.mContext, disk.getId().hashCode(), intent4, 335544320, null, UserHandle.CURRENT);
            }
            Notification.Builder autoCancel = new Notification.Builder(storageNotification2.mContext, "DSK").setSmallIcon(R.drawable.ic_qs_ui_mode_night).setColor(storageNotification2.mContext.getColor(R.color.system_notification_accent_color)).setContentTitle(string).setContentText(string2).setContentIntent(activityAsUser2).setStyle(new Notification.BigTextStyle().bigText(string2)).setVisibility(1).setLocalOnly(true).setCategory("sys").setAutoCancel(true);
            SystemUIApplication.overrideNotificationAppName(storageNotification2.mContext, autoCancel, false);
            storageNotification2.mNotificationManager.notifyAsUser(moveInfo.packageName, 1397575510, autoCancel.build(), UserHandle.ALL);
        }
    };

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MoveInfo {
        public String label;
        public int moveId;
        public String packageName;
        public String volumeUuid;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.android.systemui.usb.StorageNotification$1] */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.android.systemui.usb.StorageNotification$2] */
    /* JADX WARN: Type inference failed for: r0v3, types: [com.android.systemui.usb.StorageNotification$2] */
    /* JADX WARN: Type inference failed for: r0v4, types: [com.android.systemui.usb.StorageNotification$4] */
    public StorageNotification(Context context, BroadcastDispatcher broadcastDispatcher, NotificationManager notificationManager, StorageManager storageManager) {
        final int i = 0;
        this.mSnoozeReceiver = new BroadcastReceiver(this) { // from class: com.android.systemui.usb.StorageNotification.2
            public final /* synthetic */ StorageNotification this$0;

            {
                this.this$0 = this;
            }

            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                switch (i) {
                    case 0:
                        this.this$0.mStorageManager.setVolumeSnoozed(intent.getStringExtra("android.os.storage.extra.FS_UUID"), true);
                        break;
                    default:
                        this.this$0.mNotificationManager.cancelAsUser(null, 1397575510, UserHandle.ALL);
                        break;
                }
            }
        };
        final int i2 = 1;
        this.mFinishReceiver = new BroadcastReceiver(this) { // from class: com.android.systemui.usb.StorageNotification.2
            public final /* synthetic */ StorageNotification this$0;

            {
                this.this$0 = this;
            }

            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                switch (i2) {
                    case 0:
                        this.this$0.mStorageManager.setVolumeSnoozed(intent.getStringExtra("android.os.storage.extra.FS_UUID"), true);
                        break;
                    default:
                        this.this$0.mNotificationManager.cancelAsUser(null, 1397575510, UserHandle.ALL);
                        break;
                }
            }
        };
        this.mContext = context;
        this.mBroadcastDispatcher = broadcastDispatcher;
        this.mNotificationManager = notificationManager;
        this.mStorageManager = storageManager;
    }

    public final PendingIntent buildInitPendingIntent(VolumeInfo volumeInfo) {
        Intent intent = new Intent();
        if (isTv$2()) {
            intent.setPackage("com.android.tv.settings");
            intent.setAction("com.android.tv.settings.action.NEW_STORAGE");
        } else {
            if (isAutomotive()) {
                return null;
            }
            intent.setClassName("com.android.settings", "com.android.settings.deviceinfo.StorageWizardInit");
        }
        intent.putExtra("android.os.storage.extra.VOLUME_ID", volumeInfo.getId());
        return PendingIntent.getActivityAsUser(this.mContext, volumeInfo.getId().hashCode(), intent, 335544320, null, UserHandle.CURRENT);
    }

    public final Notification.Builder buildNotificationBuilder(VolumeInfo volumeInfo, CharSequence charSequence, CharSequence charSequence2) {
        Notification.Builder builder = new Notification.Builder(this.mContext, "DSK");
        DiskInfo disk = volumeInfo.getDisk();
        volumeInfo.getState();
        boolean isSd = disk.isSd();
        int i = R.drawable.ic_qs_ui_mode_night;
        if (!isSd && disk.isUsb()) {
            i = R.drawable.ic_test_badge_no_background;
        }
        Notification.Builder extend = builder.setSmallIcon(i).setColor(this.mContext.getColor(R.color.system_notification_accent_color)).setContentTitle(charSequence).setContentText(charSequence2).setStyle(new Notification.BigTextStyle().bigText(charSequence2)).setVisibility(1).setLocalOnly(true).extend(new Notification.TvExtender());
        SystemUIApplication.overrideNotificationAppName(this.mContext, extend, false);
        return extend;
    }

    public final PendingIntent buildSnoozeIntent(String str) {
        Intent intent = new Intent("com.android.systemui.action.SNOOZE_VOLUME");
        intent.putExtra("android.os.storage.extra.FS_UUID", str);
        return PendingIntent.getBroadcastAsUser(this.mContext, str.hashCode(), intent, 335544320, UserHandle.CURRENT);
    }

    public final PendingIntent buildUnmountPendingIntent(VolumeInfo volumeInfo) {
        Intent intent = new Intent();
        if (isTv$2()) {
            intent.setPackage("com.android.tv.settings");
            intent.setAction("com.android.tv.settings.action.UNMOUNT_STORAGE");
            intent.putExtra("android.os.storage.extra.VOLUME_ID", volumeInfo.getId());
            return PendingIntent.getActivityAsUser(this.mContext, volumeInfo.getId().hashCode(), intent, 335544320, null, UserHandle.CURRENT);
        }
        if (isAutomotive()) {
            intent.setClassName("com.android.car.settings", "com.android.car.settings.storage.StorageUnmountReceiver");
            intent.putExtra("android.os.storage.extra.VOLUME_ID", volumeInfo.getId());
            return PendingIntent.getBroadcastAsUser(this.mContext, volumeInfo.getId().hashCode(), intent, 335544320, UserHandle.CURRENT);
        }
        intent.setClassName("com.android.settings", "com.android.settings.deviceinfo.StorageUnmountReceiver");
        intent.putExtra("android.os.storage.extra.VOLUME_ID", volumeInfo.getId());
        return PendingIntent.getBroadcastAsUser(this.mContext, volumeInfo.getId().hashCode(), intent, 335544320, UserHandle.CURRENT);
    }

    public final boolean isAutomotive() {
        return this.mContext.getPackageManager().hasSystemFeature("android.hardware.type.automotive");
    }

    public final boolean isTv$2() {
        return this.mContext.getPackageManager().hasSystemFeature("android.software.leanback");
    }

    public final void onDiskScannedInternal(DiskInfo diskInfo, int i) {
        PendingIntent pendingIntent;
        if (i != 0 || diskInfo.size <= 0) {
            this.mNotificationManager.cancelAsUser(diskInfo.getId(), 1396986699, UserHandle.ALL);
            return;
        }
        String string = this.mContext.getString(R.string.face_acquired_poor_gaze, diskInfo.getDescription());
        String string2 = this.mContext.getString(R.string.face_acquired_pan_too_extreme, diskInfo.getDescription());
        Notification.Builder builder = new Notification.Builder(this.mContext, "DSK");
        boolean isSd = diskInfo.isSd();
        int i2 = R.drawable.ic_qs_ui_mode_night;
        if (!isSd && diskInfo.isUsb()) {
            i2 = R.drawable.ic_test_badge_no_background;
        }
        Notification.Builder contentText = builder.setSmallIcon(i2).setColor(this.mContext.getColor(R.color.system_notification_accent_color)).setContentTitle(string).setContentText(string2);
        Intent intent = new Intent();
        if (isTv$2()) {
            intent.setPackage("com.android.tv.settings");
            intent.setAction("com.android.tv.settings.action.NEW_STORAGE");
        } else {
            if (isAutomotive()) {
                pendingIntent = null;
                Notification.Builder extend = contentText.setContentIntent(pendingIntent).setStyle(new Notification.BigTextStyle().bigText(string2)).setVisibility(1).setLocalOnly(true).setCategory("err").extend(new Notification.TvExtender());
                SystemUIApplication.overrideNotificationAppName(this.mContext, extend, false);
                this.mNotificationManager.notifyAsUser(diskInfo.getId(), 1396986699, extend.build(), UserHandle.ALL);
            }
            intent.setClassName("com.android.settings", "com.android.settings.deviceinfo.StorageWizardInit");
        }
        intent.putExtra("android.os.storage.extra.DISK_ID", diskInfo.getId());
        pendingIntent = PendingIntent.getActivityAsUser(this.mContext, diskInfo.getId().hashCode(), intent, 335544320, null, UserHandle.CURRENT);
        Notification.Builder extend2 = contentText.setContentIntent(pendingIntent).setStyle(new Notification.BigTextStyle().bigText(string2)).setVisibility(1).setLocalOnly(true).setCategory("err").extend(new Notification.TvExtender());
        SystemUIApplication.overrideNotificationAppName(this.mContext, extend2, false);
        this.mNotificationManager.notifyAsUser(diskInfo.getId(), 1396986699, extend2.build(), UserHandle.ALL);
    }

    public final void onVolumeStateChangedInternal(VolumeInfo volumeInfo) {
        int type = volumeInfo.getType();
        if (type != 0) {
            if (type != 1) {
                return;
            }
            Log.d("StorageNotification", "Notifying about private volume: " + volumeInfo.toString());
            updateMissingPrivateVolumes();
            return;
        }
        Log.d("StorageNotification", "Notifying about public volume: " + volumeInfo.toString());
        if (volumeInfo.getMountUserId() == -10000) {
            Log.d("StorageNotification", "Ignore public volume state change event of removed user");
            return;
        }
        Notification notification = null;
        switch (volumeInfo.getState()) {
            case 1:
                DiskInfo disk = volumeInfo.getDisk();
                notification = buildNotificationBuilder(volumeInfo, this.mContext.getString(R.string.ext_media_new_notification_message, disk.getDescription()), this.mContext.getString(R.string.ext_media_move_title, disk.getDescription())).setCategory("progress").setOngoing(true).build();
                break;
            case 2:
            case 3:
                VolumeRecord findRecordByUuid = this.mStorageManager.findRecordByUuid(volumeInfo.getFsUuid());
                DiskInfo disk2 = volumeInfo.getDisk();
                if (findRecordByUuid != null && (!findRecordByUuid.isSnoozed() || !disk2.isAdoptable())) {
                    if (disk2.isAdoptable() && !findRecordByUuid.isInited() && findRecordByUuid.getType() != 0 && findRecordByUuid.getType() != 1) {
                        String description = disk2.getDescription();
                        String string = this.mContext.getString(R.string.ext_media_status_missing, disk2.getDescription());
                        PendingIntent buildInitPendingIntent = buildInitPendingIntent(volumeInfo);
                        PendingIntent buildUnmountPendingIntent = buildUnmountPendingIntent(volumeInfo);
                        if (!isAutomotive()) {
                            notification = buildNotificationBuilder(volumeInfo, description, string).addAction(new Notification.Action(R.drawable.ic_screenshot_edit, this.mContext.getString(R.string.ext_media_new_notification_title), buildInitPendingIntent)).addAction(new Notification.Action(R.drawable.ic_doc_powerpoint, this.mContext.getString(R.string.face_acquired_insufficient), buildUnmountPendingIntent)).setContentIntent(buildInitPendingIntent).setDeleteIntent(buildSnoozeIntent(volumeInfo.getFsUuid())).build();
                            break;
                        } else {
                            notification = buildNotificationBuilder(volumeInfo, description, string).setContentIntent(buildUnmountPendingIntent).setDeleteIntent(buildSnoozeIntent(volumeInfo.getFsUuid())).build();
                            break;
                        }
                    } else {
                        String description2 = disk2.getDescription();
                        String string2 = this.mContext.getString(R.string.ext_media_status_unmountable, disk2.getDescription());
                        StrictMode.VmPolicy allowVmViolations = StrictMode.allowVmViolations();
                        try {
                            PendingIntent activityAsUser = PendingIntent.getActivityAsUser(this.mContext, volumeInfo.getId().hashCode(), volumeInfo.buildBrowseIntentForUser(volumeInfo.getMountUserId()), 335544320, null, UserHandle.CURRENT);
                            StrictMode.setVmPolicy(allowVmViolations);
                            Notification.Builder category = buildNotificationBuilder(volumeInfo, description2, string2).addAction(new Notification.Action(R.drawable.ic_find_next_holo_light, this.mContext.getString(R.string.ext_media_move_success_title), activityAsUser)).addAction(new Notification.Action(R.drawable.ic_doc_powerpoint, this.mContext.getString(R.string.face_acquired_insufficient), buildUnmountPendingIntent(volumeInfo))).setContentIntent(activityAsUser).setCategory("sys");
                            if (disk2.isAdoptable()) {
                                category.setDeleteIntent(buildSnoozeIntent(volumeInfo.getFsUuid()));
                            }
                            notification = category.build();
                            break;
                        } catch (Throwable th) {
                            StrictMode.setVmPolicy(allowVmViolations);
                            throw th;
                        }
                    }
                }
                break;
            case 5:
                DiskInfo disk3 = volumeInfo.getDisk();
                notification = buildNotificationBuilder(volumeInfo, this.mContext.getString(R.string.face_acquired_obscured, disk3.getDescription()), this.mContext.getString(R.string.face_acquired_not_detected, disk3.getDescription())).setCategory("progress").setOngoing(true).build();
                break;
            case 6:
                DiskInfo disk4 = volumeInfo.getDisk();
                notification = buildNotificationBuilder(volumeInfo, this.mContext.getString(R.string.face_acquired_mouth_covering_detected_alt, disk4.getDescription()), this.mContext.getString(R.string.face_acquired_mouth_covering_detected, disk4.getDescription())).setContentIntent(isAutomotive() ? buildUnmountPendingIntent(volumeInfo) : buildInitPendingIntent(volumeInfo)).setCategory("err").build();
                break;
            case 7:
                if (volumeInfo.isPrimary()) {
                    DiskInfo disk5 = volumeInfo.getDisk();
                    notification = buildNotificationBuilder(volumeInfo, this.mContext.getString(R.string.ext_media_status_removed, disk5.getDescription()), this.mContext.getString(R.string.ext_media_status_mounted_ro, disk5.getDescription())).setCategory("err").build();
                    break;
                }
                break;
            case 8:
                if (volumeInfo.isPrimary()) {
                    DiskInfo disk6 = volumeInfo.getDisk();
                    notification = buildNotificationBuilder(volumeInfo, this.mContext.getString(R.string.ext_media_move_success_message, disk6.getDescription()), this.mContext.getString(R.string.ext_media_move_specific_title, disk6.getDescription())).setCategory("err").build();
                    break;
                }
                break;
        }
        if (notification != null) {
            this.mNotificationManager.notifyAsUser(volumeInfo.getId(), 1397773634, notification, UserHandle.of(volumeInfo.getMountUserId()));
        } else {
            this.mNotificationManager.cancelAsUser(volumeInfo.getId(), 1397773634, UserHandle.of(volumeInfo.getMountUserId()));
        }
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        this.mStorageManager.registerListener(this.mListener);
        this.mBroadcastDispatcher.registerReceiver(this.mSnoozeReceiver, new IntentFilter("com.android.systemui.action.SNOOZE_VOLUME"), null, null, 2, "android.permission.MOUNT_UNMOUNT_FILESYSTEMS");
        this.mBroadcastDispatcher.registerReceiver(this.mFinishReceiver, new IntentFilter("com.android.systemui.action.FINISH_WIZARD"), null, null, 2, "android.permission.MOUNT_UNMOUNT_FILESYSTEMS");
        for (DiskInfo diskInfo : this.mStorageManager.getDisks()) {
            onDiskScannedInternal(diskInfo, diskInfo.volumeCount);
        }
        Iterator it = this.mStorageManager.getVolumes().iterator();
        while (it.hasNext()) {
            onVolumeStateChangedInternal((VolumeInfo) it.next());
        }
        this.mContext.getPackageManager().registerMoveCallback(this.mMoveCallback, new Handler());
        updateMissingPrivateVolumes();
    }

    public final void updateMissingPrivateVolumes() {
        if (isTv$2() || isAutomotive()) {
            return;
        }
        for (VolumeRecord volumeRecord : this.mStorageManager.getVolumeRecords()) {
            if (volumeRecord.getType() == 1) {
                String fsUuid = volumeRecord.getFsUuid();
                VolumeInfo findVolumeByUuid = this.mStorageManager.findVolumeByUuid(fsUuid);
                if ((findVolumeByUuid == null || !findVolumeByUuid.isMountedWritable()) && !volumeRecord.isSnoozed()) {
                    String string = this.mContext.getString(R.string.ext_media_nomedia_notification_title, volumeRecord.getNickname());
                    String string2 = this.mContext.getString(R.string.ext_media_nomedia_notification_message);
                    Notification.Builder contentText = new Notification.Builder(this.mContext, "DSK").setSmallIcon(R.drawable.ic_qs_ui_mode_night).setColor(this.mContext.getColor(R.color.system_notification_accent_color)).setContentTitle(string).setContentText(string2);
                    Intent intent = new Intent();
                    intent.setClassName("com.android.settings", "com.android.settings.Settings$PrivateVolumeForgetActivity");
                    intent.putExtra("android.os.storage.extra.FS_UUID", volumeRecord.getFsUuid());
                    Notification.Builder extend = contentText.setContentIntent(PendingIntent.getActivityAsUser(this.mContext, volumeRecord.getFsUuid().hashCode(), intent, 335544320, null, UserHandle.CURRENT)).setStyle(new Notification.BigTextStyle().bigText(string2)).setVisibility(1).setLocalOnly(true).setCategory("sys").setDeleteIntent(buildSnoozeIntent(fsUuid)).extend(new Notification.TvExtender());
                    SystemUIApplication.overrideNotificationAppName(this.mContext, extend, false);
                    this.mNotificationManager.notifyAsUser(fsUuid, 1397772886, extend.build(), UserHandle.ALL);
                } else {
                    this.mNotificationManager.cancelAsUser(fsUuid, 1397772886, UserHandle.ALL);
                }
            }
        }
    }
}
