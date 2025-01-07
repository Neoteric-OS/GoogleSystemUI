package com.android.systemui.communal.data.backup;

import android.app.backup.BackupDataInputStream;
import android.app.backup.BackupDataOutput;
import android.app.backup.BackupHelper;
import android.os.ParcelFileDescriptor;
import android.os.UserHandle;
import android.util.Log;
import com.android.systemui.communal.data.db.CommunalDatabase;
import com.android.systemui.communal.data.db.CommunalItemRank;
import com.android.systemui.communal.data.db.CommunalWidgetItem;
import com.android.systemui.communal.nano.CommunalHubState;
import com.google.protobuf.nano.MessageNano;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CommunalBackupHelper implements BackupHelper {
    public final CommunalBackupUtils communalBackupUtils;
    public final UserHandle userHandle;

    public CommunalBackupHelper(UserHandle userHandle, CommunalBackupUtils communalBackupUtils) {
        this.userHandle = userHandle;
        this.communalBackupUtils = communalBackupUtils;
    }

    @Override // android.app.backup.BackupHelper
    public final void performBackup(ParcelFileDescriptor parcelFileDescriptor, BackupDataOutput backupDataOutput, ParcelFileDescriptor parcelFileDescriptor2) {
        if (backupDataOutput == null) {
            Log.e("CommunalBackupHelper", "Backup failed. Data is null");
            return;
        }
        if (!this.userHandle.isSystem()) {
            Log.d("CommunalBackupHelper", "Backup skipped for non-system user");
            return;
        }
        Map map = (Map) BuildersKt.runBlocking(EmptyCoroutineContext.INSTANCE, new CommunalBackupUtils$getCommunalHubState$widgetsFromDb$1(CommunalDatabase.Companion.getInstance(this.communalBackupUtils.context, null), null));
        ArrayList arrayList = new ArrayList();
        for (CommunalItemRank communalItemRank : map.keySet()) {
            Object obj = map.get(communalItemRank);
            Intrinsics.checkNotNull(obj);
            CommunalWidgetItem communalWidgetItem = (CommunalWidgetItem) obj;
            CommunalHubState.CommunalWidgetItem communalWidgetItem2 = new CommunalHubState.CommunalWidgetItem();
            communalWidgetItem2.rank = communalItemRank.rank;
            communalWidgetItem2.widgetId = communalWidgetItem.widgetId;
            communalWidgetItem2.componentName = communalWidgetItem.componentName;
            communalWidgetItem2.userSerialNumber = communalWidgetItem.userSerialNumber;
            arrayList.add(communalWidgetItem2);
        }
        CommunalHubState communalHubState = new CommunalHubState();
        communalHubState.widgets = (CommunalHubState.CommunalWidgetItem[]) arrayList.toArray(new CommunalHubState.CommunalWidgetItem[0]);
        Log.i("CommunalBackupHelper", "Backing up communal state: " + communalHubState);
        byte[] byteArray = MessageNano.toByteArray(communalHubState);
        try {
            backupDataOutput.writeEntityHeader("communal_hub_state", byteArray.length);
            backupDataOutput.writeEntityData(byteArray, byteArray.length);
            Log.i("CommunalBackupHelper", "Backup complete");
        } catch (IOException e) {
            Log.e("CommunalBackupHelper", "Backup failed while writing data: " + e.getLocalizedMessage());
        }
    }

    @Override // android.app.backup.BackupHelper
    public final void restoreEntity(BackupDataInputStream backupDataInputStream) {
        if (backupDataInputStream == null) {
            Log.e("CommunalBackupHelper", "Restore failed. Data is null");
            return;
        }
        if (!this.userHandle.isSystem()) {
            Log.d("CommunalBackupHelper", "Restore skipped for non-system user");
            return;
        }
        if (!Intrinsics.areEqual(backupDataInputStream.getKey(), "communal_hub_state")) {
            Log.d("CommunalBackupHelper", "Restore skipped due to mismatching entity key");
            return;
        }
        int size = backupDataInputStream.size();
        byte[] bArr = new byte[size];
        try {
            backupDataInputStream.read(bArr, 0, size);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(new File(this.communalBackupUtils.context.getFilesDir(), "communal_restore"));
                fileOutputStream.write(bArr);
                fileOutputStream.close();
                Log.i("CommunalBackupHelper", "Restore complete");
            } catch (Exception e) {
                Log.e("CommunalBackupHelper", "Restore failed while writing to disk: " + e.getLocalizedMessage());
            }
        } catch (IOException e2) {
            Log.e("CommunalBackupHelper", "Restore failed while reading data: " + e2.getLocalizedMessage());
        }
    }

    @Override // android.app.backup.BackupHelper
    public final void writeNewStateDescription(ParcelFileDescriptor parcelFileDescriptor) {
    }
}
