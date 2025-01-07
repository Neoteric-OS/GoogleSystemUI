package com.android.systemui.people.widget;

import android.app.backup.BackupDataOutput;
import android.app.backup.SharedPreferencesBackupHelper;
import android.app.people.IPeopleManager;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import com.android.systemui.backup.BackupHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class PeopleBackupHelper extends SharedPreferencesBackupHelper {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final AppWidgetManager mAppWidgetManager;
    public final Context mContext;
    public final IPeopleManager mIPeopleManager;
    public final PackageManager mPackageManager;
    public final UserHandle mUserHandle;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class SharedFileEntryType {
        public static final /* synthetic */ SharedFileEntryType[] $VALUES;
        public static final SharedFileEntryType CONTACT_URI;
        public static final SharedFileEntryType PEOPLE_TILE_KEY;
        public static final SharedFileEntryType UNKNOWN;
        public static final SharedFileEntryType WIDGET_ID;

        static {
            SharedFileEntryType sharedFileEntryType = new SharedFileEntryType("UNKNOWN", 0);
            UNKNOWN = sharedFileEntryType;
            SharedFileEntryType sharedFileEntryType2 = new SharedFileEntryType("WIDGET_ID", 1);
            WIDGET_ID = sharedFileEntryType2;
            SharedFileEntryType sharedFileEntryType3 = new SharedFileEntryType("PEOPLE_TILE_KEY", 2);
            PEOPLE_TILE_KEY = sharedFileEntryType3;
            SharedFileEntryType sharedFileEntryType4 = new SharedFileEntryType("CONTACT_URI", 3);
            CONTACT_URI = sharedFileEntryType4;
            $VALUES = new SharedFileEntryType[]{sharedFileEntryType, sharedFileEntryType2, sharedFileEntryType3, sharedFileEntryType4};
        }

        public static SharedFileEntryType valueOf(String str) {
            return (SharedFileEntryType) Enum.valueOf(SharedFileEntryType.class, str);
        }

        public static SharedFileEntryType[] values() {
            return (SharedFileEntryType[]) $VALUES.clone();
        }
    }

    public PeopleBackupHelper(BackupHelper backupHelper, UserHandle userHandle, String[] strArr) {
        super(backupHelper, strArr);
        this.mContext = backupHelper;
        this.mUserHandle = userHandle;
        this.mPackageManager = backupHelper.getPackageManager();
        this.mIPeopleManager = IPeopleManager.Stub.asInterface(ServiceManager.getService("people"));
        this.mAppWidgetManager = AppWidgetManager.getInstance(backupHelper);
    }

    public static SharedFileEntryType getEntryType(Map.Entry entry) {
        String str = (String) entry.getKey();
        SharedFileEntryType sharedFileEntryType = SharedFileEntryType.UNKNOWN;
        if (str == null) {
            return sharedFileEntryType;
        }
        try {
            Integer.parseInt(str);
            try {
                try {
                    return SharedFileEntryType.WIDGET_ID;
                } catch (Exception unused) {
                    Log.w("PeopleBackupHelper", "Malformed value, skipping:" + entry.getValue());
                    return sharedFileEntryType;
                }
            } catch (Exception unused2) {
                Log.w("PeopleBackupHelper", "Malformed value, skipping:" + entry.getValue());
                return sharedFileEntryType;
            }
        } catch (NumberFormatException unused3) {
            if (PeopleTileKey.fromString(str) != null) {
                return SharedFileEntryType.PEOPLE_TILE_KEY;
            }
            try {
                Uri.parse(str);
                return SharedFileEntryType.CONTACT_URI;
            } catch (Exception unused4) {
                return sharedFileEntryType;
            }
        }
    }

    public static boolean isReadyForRestore(IPeopleManager iPeopleManager, PackageManager packageManager, PeopleTileKey peopleTileKey) {
        if (!PeopleTileKey.isValid(peopleTileKey)) {
            return true;
        }
        try {
            packageManager.getPackageInfoAsUser(peopleTileKey.mPackageName, 0, peopleTileKey.mUserId);
            return iPeopleManager.isConversation(peopleTileKey.mPackageName, peopleTileKey.mUserId, peopleTileKey.mShortcutId);
        } catch (PackageManager.NameNotFoundException | Exception unused) {
            return false;
        }
    }

    public static void updateWidgets(Context context) {
        int[] appWidgetIds;
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        if (appWidgetManager == null || (appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, (Class<?>) PeopleSpaceWidgetProvider.class))) == null || appWidgetIds.length == 0) {
            return;
        }
        Intent intent = new Intent(context, (Class<?>) PeopleSpaceWidgetProvider.class);
        intent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
        intent.putExtra("appWidgetIds", appWidgetIds);
        context.sendBroadcast(intent);
    }

    @Override // android.app.backup.SharedPreferencesBackupHelper, android.app.backup.BackupHelper
    public final void performBackup(ParcelFileDescriptor parcelFileDescriptor, BackupDataOutput backupDataOutput, ParcelFileDescriptor parcelFileDescriptor2) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.mContext);
        if (defaultSharedPreferences.getAll().isEmpty()) {
            return;
        }
        final SharedPreferences.Editor edit = this.mContext.getSharedPreferences("shared_backup", 0).edit();
        edit.clear();
        int identifier = this.mUserHandle.getIdentifier();
        final ArrayList arrayList = new ArrayList();
        AppWidgetManager appWidgetManager = this.mAppWidgetManager;
        if (appWidgetManager != null) {
            for (int i : appWidgetManager.getAppWidgetIds(new ComponentName(this.mContext, (Class<?>) PeopleSpaceWidgetProvider.class))) {
                String valueOf = String.valueOf(i);
                if (this.mContext.getSharedPreferences(valueOf, 0).getInt("user_id", -1) == identifier) {
                    arrayList.add(valueOf);
                }
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        defaultSharedPreferences.getAll().entrySet().forEach(new Consumer() { // from class: com.android.systemui.people.widget.PeopleBackupHelper$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                PeopleBackupHelper peopleBackupHelper = PeopleBackupHelper.this;
                SharedPreferences.Editor editor = edit;
                final List list = arrayList;
                Map.Entry entry = (Map.Entry) obj;
                int i2 = PeopleBackupHelper.$r8$clinit;
                String str = (String) entry.getKey();
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                int ordinal = PeopleBackupHelper.getEntryType(entry).ordinal();
                if (ordinal == 1) {
                    String valueOf2 = String.valueOf(entry.getValue());
                    if (list.contains(str)) {
                        Uri parse = Uri.parse(valueOf2);
                        if (ContentProvider.uriHasUserId(parse)) {
                            editor.putInt("add_user_id_to_uri_" + str, ContentProvider.getUserIdFromUri(parse));
                            parse = ContentProvider.getUriWithoutUserId(parse);
                        }
                        editor.putString(str, parse.toString());
                        return;
                    }
                    return;
                }
                if (ordinal == 2) {
                    Set set = (Set) entry.getValue();
                    PeopleTileKey fromString = PeopleTileKey.fromString(str);
                    if (fromString.mUserId != peopleBackupHelper.mUserHandle.getIdentifier()) {
                        return;
                    }
                    Set<String> set2 = (Set) set.stream().filter(new Predicate() { // from class: com.android.systemui.people.widget.PeopleBackupHelper$$ExternalSyntheticLambda1
                        @Override // java.util.function.Predicate
                        public final boolean test(Object obj2) {
                            int i3 = PeopleBackupHelper.$r8$clinit;
                            return list.contains((String) obj2);
                        }
                    }).collect(Collectors.toSet());
                    if (set2.isEmpty()) {
                        return;
                    }
                    fromString.mUserId = -1;
                    editor.putStringSet(fromString.toString(), set2);
                    return;
                }
                if (ordinal != 3) {
                    Log.w("PeopleBackupHelper", "Key not identified, skipping: " + str);
                    return;
                }
                Set<String> set3 = (Set) entry.getValue();
                Uri parse2 = Uri.parse(String.valueOf(str));
                if (!ContentProvider.uriHasUserId(parse2)) {
                    if (peopleBackupHelper.mUserHandle.isSystem()) {
                        editor.putStringSet(parse2.toString(), set3);
                        return;
                    }
                    return;
                }
                int userIdFromUri = ContentProvider.getUserIdFromUri(parse2);
                if (userIdFromUri == peopleBackupHelper.mUserHandle.getIdentifier()) {
                    Uri uriWithoutUserId = ContentProvider.getUriWithoutUserId(parse2);
                    editor.putInt("add_user_id_to_uri_" + uriWithoutUserId.toString(), userIdFromUri);
                    editor.putStringSet(uriWithoutUserId.toString(), set3);
                }
            }
        });
        edit.apply();
        super.performBackup(parcelFileDescriptor, backupDataOutput, parcelFileDescriptor2);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0106 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x002f A[SYNTHETIC] */
    @Override // android.app.backup.SharedPreferencesBackupHelper, android.app.backup.BackupHelper
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void restoreEntity(android.app.backup.BackupDataInputStream r13) {
        /*
            Method dump skipped, instructions count: 347
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.people.widget.PeopleBackupHelper.restoreEntity(android.app.backup.BackupDataInputStream):void");
    }

    public PeopleBackupHelper(Context context, UserHandle userHandle, String[] strArr, PackageManager packageManager, IPeopleManager iPeopleManager) {
        super(context, strArr);
        this.mContext = context;
        this.mUserHandle = userHandle;
        this.mPackageManager = packageManager;
        this.mIPeopleManager = iPeopleManager;
        this.mAppWidgetManager = AppWidgetManager.getInstance(context);
    }
}
