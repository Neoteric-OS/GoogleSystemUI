package com.google.android.systemui.backup;

import android.app.backup.BlobBackupHelper;
import android.content.ContentResolver;
import android.os.UserHandle;
import android.provider.Settings;
import android.util.Log;
import com.android.systemui.backup.BackupHelper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BackupHelperGoogle extends BackupHelper {
    public static final List SECURE_SETTINGS_INT_KEYS = CollectionsKt__CollectionsKt.listOf("columbus_enabled", "columbus_low_sensitivity");
    public static final List SECURE_SETTINGS_STRING_KEYS = CollectionsKt__CollectionsKt.listOf("columbus_action", "columbus_launch_app", "columbus_launch_app_shortcut");

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SecureSettingsBackupHelper extends BlobBackupHelper {
        public final ContentResolver contentResolver;
        public final UserHandle userHandle;

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public SecureSettingsBackupHelper(android.content.ContentResolver r5, android.os.UserHandle r6) {
            /*
                r4 = this;
                kotlin.jvm.internal.SpreadBuilder r0 = new kotlin.jvm.internal.SpreadBuilder
                r1 = 2
                r0.<init>(r1)
                java.util.List r1 = com.google.android.systemui.backup.BackupHelperGoogle.SECURE_SETTINGS_INT_KEYS
                r2 = 0
                java.lang.String[] r3 = new java.lang.String[r2]
                java.lang.Object[] r1 = r1.toArray(r3)
                r0.addSpread(r1)
                java.util.List r1 = com.google.android.systemui.backup.BackupHelperGoogle.SECURE_SETTINGS_STRING_KEYS
                java.lang.String[] r2 = new java.lang.String[r2]
                java.lang.Object[] r1 = r1.toArray(r2)
                r0.addSpread(r1)
                java.util.ArrayList r1 = r0.list
                int r1 = r1.size()
                java.lang.String[] r1 = new java.lang.String[r1]
                java.util.ArrayList r0 = r0.list
                java.lang.Object[] r0 = r0.toArray(r1)
                java.lang.String[] r0 = (java.lang.String[]) r0
                r1 = 1
                r4.<init>(r1, r0)
                r4.contentResolver = r5
                r4.userHandle = r6
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.backup.BackupHelperGoogle.SecureSettingsBackupHelper.<init>(android.content.ContentResolver, android.os.UserHandle):void");
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final void applyRestoredPayload(String str, byte[] bArr) {
            if (!CollectionsKt.contains(BackupHelperGoogle.SECURE_SETTINGS_INT_KEYS, str)) {
                if (!CollectionsKt.contains(BackupHelperGoogle.SECURE_SETTINGS_STRING_KEYS, str) || str == null || bArr == null || str.length() == 0 || bArr.length == 0) {
                    return;
                }
                DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
                try {
                    String readUTF = dataInputStream.readUTF();
                    dataInputStream.close();
                    Settings.Secure.putStringForUser(this.contentResolver, str, readUTF, this.userHandle.getIdentifier());
                    return;
                } catch (IOException unused) {
                    Log.e("BackupHelper", "Failed to restore ".concat(str));
                    return;
                } finally {
                    dataInputStream.close();
                }
            }
            if (str == null || bArr == null || str.length() == 0 || bArr.length == 0) {
                return;
            }
            DataInputStream dataInputStream2 = new DataInputStream(new ByteArrayInputStream(bArr));
            try {
                try {
                    int readInt = dataInputStream2.readInt();
                    dataInputStream2.close();
                    ContentResolver contentResolver = this.contentResolver;
                    Settings.Secure.putIntForUser(contentResolver, str, readInt, this.userHandle.getIdentifier());
                    dataInputStream2 = contentResolver;
                } catch (Throwable th) {
                    dataInputStream2.close();
                    throw th;
                }
            } catch (IOException unused2) {
                Log.e("BackupHelper", "Failed to restore ".concat(str));
            }
        }

        public final byte[] getBackupPayload(String str) {
            String stringForUser;
            byte[] bArr = null;
            if (CollectionsKt.contains(BackupHelperGoogle.SECURE_SETTINGS_INT_KEYS, str)) {
                try {
                    int intForUser = Settings.Secure.getIntForUser(this.contentResolver, str, this.userHandle.getIdentifier());
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                    try {
                        try {
                            dataOutputStream.writeInt(intForUser);
                            bArr = byteArrayOutputStream.toByteArray();
                        } catch (IOException unused) {
                            dataOutputStream.close();
                            Log.e("BackupHelper", "Failed to backup " + str);
                        }
                    } finally {
                    }
                } catch (Settings.SettingNotFoundException unused2) {
                }
            } else if (CollectionsKt.contains(BackupHelperGoogle.SECURE_SETTINGS_STRING_KEYS, str) && (stringForUser = Settings.Secure.getStringForUser(this.contentResolver, str, this.userHandle.getIdentifier())) != null) {
                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                try {
                    try {
                        new DataOutputStream(byteArrayOutputStream2).writeUTF(stringForUser);
                        bArr = byteArrayOutputStream2.toByteArray();
                    } catch (IOException unused3) {
                        Log.e("BackupHelper", "Failed to backup " + str);
                    }
                } finally {
                }
            }
            return bArr;
        }
    }

    public final void onCreate(UserHandle userHandle, int i) {
        super.onCreate(userHandle, i);
        addHelper("systemui.google.secure_settings_backup", new SecureSettingsBackupHelper(getContentResolver(), userHandle));
    }
}
