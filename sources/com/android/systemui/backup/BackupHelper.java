package com.android.systemui.backup;

import android.app.backup.BackupAgentHelper;
import android.app.backup.BackupDataInputStream;
import android.app.backup.BackupDataOutput;
import android.app.backup.FileBackupHelper;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.os.PersistableBundle;
import android.os.Trace;
import android.os.UserHandle;
import android.util.Log;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import com.android.app.tracing.TraceUtilsKt;
import com.android.systemui.communal.data.backup.CommunalBackupHelper;
import com.android.systemui.communal.data.backup.CommunalBackupUtils;
import com.android.systemui.communal.domain.backup.CommunalPrefsBackupHelper;
import com.android.systemui.controls.controller.AuxiliaryPersistenceWrapper;
import com.android.systemui.keyguard.domain.backup.KeyguardQuickAffordanceBackupHelper;
import com.android.systemui.people.widget.PeopleBackupHelper;
import com.android.systemui.settings.UserFileManagerImpl;
import com.android.wm.shell.R;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.io.CloseableKt;
import kotlin.io.FileAlreadyExistsException;
import kotlin.io.FileSystemException;
import kotlin.io.NoSuchFileException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class BackupHelper extends BackupAgentHelper {
    public static final Object controlsDataLock = new Object();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class NoOverwriteFileBackupHelper extends FileBackupHelper {
        public final BackupHelper context;
        public final Map fileNamesAndPostProcess;
        public final Object lock;

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public NoOverwriteFileBackupHelper(com.android.systemui.backup.BackupHelper r4, java.util.Map r5) {
            /*
                r3 = this;
                java.lang.Object r0 = com.android.systemui.backup.BackupHelper.controlsDataLock
                java.util.Set r1 = r5.keySet()
                java.util.Collection r1 = (java.util.Collection) r1
                r2 = 0
                java.lang.String[] r2 = new java.lang.String[r2]
                java.lang.Object[] r1 = r1.toArray(r2)
                java.lang.String[] r1 = (java.lang.String[]) r1
                int r2 = r1.length
                java.lang.Object[] r1 = java.util.Arrays.copyOf(r1, r2)
                java.lang.String[] r1 = (java.lang.String[]) r1
                r3.<init>(r4, r1)
                r3.lock = r0
                r3.context = r4
                r3.fileNamesAndPostProcess = r5
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.backup.BackupHelper.NoOverwriteFileBackupHelper.<init>(com.android.systemui.backup.BackupHelper, java.util.Map):void");
        }

        @Override // android.app.backup.FileBackupHelper, android.app.backup.BackupHelper
        public final void performBackup(ParcelFileDescriptor parcelFileDescriptor, BackupDataOutput backupDataOutput, ParcelFileDescriptor parcelFileDescriptor2) {
            synchronized (this.lock) {
                super.performBackup(parcelFileDescriptor, backupDataOutput, parcelFileDescriptor2);
            }
        }

        @Override // android.app.backup.FileBackupHelper, android.app.backup.BackupHelper
        public final void restoreEntity(BackupDataInputStream backupDataInputStream) {
            Log.d("BackupHelper", "Starting restore for " + backupDataInputStream.getKey() + " for user " + this.context.getUserId());
            if (Environment.buildPath(this.context.getFilesDir(), new String[]{backupDataInputStream.getKey()}).exists()) {
                Log.w("BackupHelper", "File " + backupDataInputStream.getKey() + " already exists. Skipping restore.");
                return;
            }
            synchronized (this.lock) {
                String str = "File restore: " + backupDataInputStream.getKey();
                boolean isEnabled = Trace.isEnabled();
                if (isEnabled) {
                    TraceUtilsKt.beginSlice(str);
                }
                try {
                    super.restoreEntity(backupDataInputStream);
                    if (isEnabled) {
                        TraceUtilsKt.endSlice();
                    }
                    Log.d("BackupHelper", "Finishing restore for " + backupDataInputStream.getKey() + " for user " + this.context.getUserId() + ". Starting postProcess.");
                    String key = backupDataInputStream.getKey();
                    StringBuilder sb = new StringBuilder("Postprocess: ");
                    sb.append(key);
                    String sb2 = sb.toString();
                    isEnabled = Trace.isEnabled();
                    if (isEnabled) {
                        TraceUtilsKt.beginSlice(sb2);
                    }
                    try {
                        Function0 function0 = (Function0) this.fileNamesAndPostProcess.get(backupDataInputStream.getKey());
                        if (function0 != null) {
                            function0.invoke();
                        }
                        Log.d("BackupHelper", "Finishing postprocess for " + backupDataInputStream.getKey() + " for user " + this.context.getUserId() + ".");
                    } finally {
                        if (isEnabled) {
                            TraceUtilsKt.endSlice();
                        }
                    }
                } finally {
                    if (isEnabled) {
                        TraceUtilsKt.endSlice();
                    }
                }
            }
        }
    }

    public final void onCreate(UserHandle userHandle) {
        super.onCreate(userHandle);
        final int identifier = userHandle.getIdentifier();
        addHelper("systemui.files_no_overwrite", new NoOverwriteFileBackupHelper(this, MapsKt__MapsJVMKt.mapOf(new Pair((new UserHandle(identifier).isSystem() ? new File("controls_favorites.xml") : new File(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m(GenericDocument$$ExternalSyntheticOutline0.m("__USER_", "_", identifier), "controls_favorites.xml"))).getPath(), new Function0() { // from class: com.android.systemui.backup.BackupHelperKt$getPPControlsFile$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                File createFile = UserFileManagerImpl.Companion.createFile(identifier, "controls_favorites.xml");
                if (createFile.exists()) {
                    File createFile2 = UserFileManagerImpl.Companion.createFile(identifier, "aux_controls_favorites.xml");
                    if (!createFile.exists()) {
                        throw new NoSuchFileException(createFile, null, "The source file doesn't exist.");
                    }
                    if (createFile2.exists()) {
                        throw new FileAlreadyExistsException(createFile, createFile2, "The destination file already exists.");
                    }
                    if (!createFile.isDirectory()) {
                        File parentFile = createFile2.getParentFile();
                        if (parentFile != null) {
                            parentFile.mkdirs();
                        }
                        FileInputStream fileInputStream = new FileInputStream(createFile);
                        try {
                            FileOutputStream fileOutputStream = new FileOutputStream(createFile2);
                            try {
                                byte[] bArr = new byte[8192];
                                for (int read = fileInputStream.read(bArr); read >= 0; read = fileInputStream.read(bArr)) {
                                    fileOutputStream.write(bArr, 0, read);
                                }
                                CloseableKt.closeFinally(fileOutputStream, null);
                                CloseableKt.closeFinally(fileInputStream, null);
                            } finally {
                            }
                        } finally {
                        }
                    } else if (!createFile2.mkdirs()) {
                        throw new FileSystemException(createFile, createFile2, "Failed to create target directory.");
                    }
                    JobScheduler jobScheduler = (JobScheduler) this.getSystemService(JobScheduler.class);
                    if (jobScheduler != null) {
                        int i = AuxiliaryPersistenceWrapper.DeletionJobService.$r8$clinit;
                        Context context = this;
                        int i2 = identifier;
                        int userId = context.getUserId() + 1000;
                        ComponentName componentName = new ComponentName(context, (Class<?>) AuxiliaryPersistenceWrapper.DeletionJobService.class);
                        PersistableBundle persistableBundle = new PersistableBundle();
                        persistableBundle.putInt("USER", i2);
                        jobScheduler.schedule(new JobInfo.Builder(userId, componentName).setMinimumLatency(AuxiliaryPersistenceWrapper.DeletionJobService.WEEK_IN_MILLIS).setPersisted(true).setExtras(persistableBundle).build());
                    }
                }
                return Unit.INSTANCE;
            }
        }))));
        int i = PeopleBackupHelper.$r8$clinit;
        List singletonList = Collections.singletonList("shared_backup");
        Intrinsics.checkNotNull(singletonList);
        addHelper("systemui.people.shared_preferences", new PeopleBackupHelper(this, userHandle, (String[]) singletonList.toArray(new String[0])));
        addHelper("systemui.keyguard.quickaffordance.shared_preferences", new KeyguardQuickAffordanceBackupHelper(userHandle.getIdentifier(), this));
        if (getResources().getBoolean(R.bool.config_communalServiceEnabled)) {
            int identifier2 = userHandle.getIdentifier();
            addHelper("systemui.communal.shared_preferences", new CommunalPrefsBackupHelper(this, (new UserHandle(identifier2).isSystem() ? new File("communal_hub_prefs") : new File(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m(GenericDocument$$ExternalSyntheticOutline0.m("__USER_", "_", identifier2), "communal_hub_prefs"))).getPath()));
            addHelper("systemui.communal_state", new CommunalBackupHelper(userHandle, new CommunalBackupUtils(this)));
        }
    }

    @Override // android.app.backup.BackupAgent
    public final void onRestoreFinished() {
        super.onRestoreFinished();
        Intent intent = new Intent("com.android.systemui.backup.RESTORE_FINISHED");
        intent.setPackage(getPackageName());
        intent.putExtra("android.intent.extra.USER_ID", getUserId());
        intent.setFlags(1073741824);
        sendBroadcastAsUser(intent, UserHandle.SYSTEM, "com.android.systemui.permission.SELF");
    }
}
