package com.android.systemui.people.widget;

import android.app.INotificationManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Person;
import android.app.backup.BackupManager;
import android.app.job.JobScheduler;
import android.app.people.ConversationChannel;
import android.app.people.IPeopleManager;
import android.app.people.PeopleManager;
import android.app.people.PeopleSpaceTile;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.LauncherApps;
import android.content.pm.PackageManager;
import android.content.pm.ShortcutInfo;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.Trace;
import android.os.UserHandle;
import android.os.UserManager;
import android.preference.PreferenceManager;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.widget.RemoteViews;
import androidx.compose.foundation.text.input.internal.RecordingInputConnection$$ExternalSyntheticOutline0;
import com.android.internal.logging.UiEventLogger;
import com.android.internal.logging.UiEventLoggerImpl;
import com.android.internal.util.ArrayUtils;
import com.android.internal.widget.MessagingMessage;
import com.android.keyguard.ClockEventController$zenModeCallback$1$$ExternalSyntheticOutline0;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.settingslib.utils.ThreadUtils;
import com.android.systemui.Dumpable;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.people.NotificationHelper;
import com.android.systemui.people.NotificationHelper$$ExternalSyntheticLambda0;
import com.android.systemui.people.PeopleSpaceUtils;
import com.android.systemui.people.PeopleSpaceUtils$$ExternalSyntheticLambda0;
import com.android.systemui.people.PeopleTileViewHelper;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.NotificationListener;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.notifcollection.CommonNotifCollection;
import com.android.wm.shell.R;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class PeopleSpaceWidgetManager implements Dumpable {
    public static final Map mListeners = new HashMap();
    public static final Map mTiles = new HashMap();
    public final Optional mAppWidgetManagerOptional;
    public final BackupManager mBackupManager;
    public final AnonymousClass3 mBaseBroadcastReceiver;
    public final Executor mBgExecutor;
    public final BroadcastDispatcher mBroadcastDispatcher;
    public final Optional mBubblesOptional;
    public final Context mContext;
    public final INotificationManager mINotificationManager;
    public final IPeopleManager mIPeopleManager;
    public final KeyguardUpdateMonitorCallback mKeyguardUpdateMonitorCallback;
    public final LauncherApps mLauncherApps;
    public final AnonymousClass2 mListener;
    public final Object mLock;
    public final PeopleSpaceWidgetManager mManager;
    public final CommonNotifCollection mNotifCollection;
    public final Map mNotificationKeyToWidgetIdsMatchedByUri;
    public final NotificationManager mNotificationManager;
    public final PackageManager mPackageManager;
    public final PeopleManager mPeopleManager;
    public boolean mRegisteredReceivers;
    public final SharedPreferences mSharedPrefs;
    public final UiEventLogger mUiEventLogger;
    public final SparseBooleanArray mUpdatedPreviews;
    public final UserManager mUserManager;
    public final UserTracker mUserTracker;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.people.widget.PeopleSpaceWidgetManager$3, reason: invalid class name */
    public final class AnonymousClass3 extends BroadcastReceiver {
        public AnonymousClass3() {
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            PeopleSpaceWidgetManager.this.mBgExecutor.execute(new PeopleSpaceWidgetManager$$ExternalSyntheticLambda5(2, this, intent));
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TileConversationListener implements PeopleManager.ConversationListener {
        public TileConversationListener() {
        }

        public final void onConversationUpdate(ConversationChannel conversationChannel) {
            PeopleSpaceWidgetManager.this.mBgExecutor.execute(new PeopleSpaceWidgetManager$$ExternalSyntheticLambda5(3, this, conversationChannel));
        }
    }

    public PeopleSpaceWidgetManager(Context context, Optional optional, LauncherApps launcherApps, CommonNotifCollection commonNotifCollection, PackageManager packageManager, Optional optional2, UserManager userManager, NotificationManager notificationManager, BroadcastDispatcher broadcastDispatcher, Executor executor, DumpManager dumpManager, UserTracker userTracker, KeyguardUpdateMonitor keyguardUpdateMonitor) {
        this.mLock = new Object();
        this.mUiEventLogger = new UiEventLoggerImpl();
        this.mNotificationKeyToWidgetIdsMatchedByUri = new HashMap();
        this.mUpdatedPreviews = new SparseBooleanArray();
        KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback = new KeyguardUpdateMonitorCallback() { // from class: com.android.systemui.people.widget.PeopleSpaceWidgetManager.1
            @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
            public final void onUserUnlocked() {
                PeopleSpaceWidgetManager peopleSpaceWidgetManager = PeopleSpaceWidgetManager.this;
                peopleSpaceWidgetManager.updateGeneratedPreviewForUser(((UserTrackerImpl) peopleSpaceWidgetManager.mUserTracker).getUserHandle());
            }
        };
        this.mKeyguardUpdateMonitorCallback = keyguardUpdateMonitorCallback;
        this.mListener = new AnonymousClass2();
        this.mBaseBroadcastReceiver = new AnonymousClass3();
        this.mContext = context;
        this.mAppWidgetManagerOptional = optional;
        this.mIPeopleManager = IPeopleManager.Stub.asInterface(ServiceManager.getService("people"));
        this.mLauncherApps = launcherApps;
        this.mSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        this.mPeopleManager = (PeopleManager) context.getSystemService(PeopleManager.class);
        this.mNotifCollection = commonNotifCollection;
        this.mPackageManager = packageManager;
        this.mINotificationManager = INotificationManager.Stub.asInterface(ServiceManager.getService("notification"));
        this.mBubblesOptional = optional2;
        this.mUserManager = userManager;
        this.mBackupManager = new BackupManager(context);
        this.mNotificationManager = notificationManager;
        this.mManager = this;
        this.mBroadcastDispatcher = broadcastDispatcher;
        this.mBgExecutor = executor;
        dumpManager.registerNormalDumpable("PeopleSpaceWidgetMgr", this);
        this.mUserTracker = userTracker;
        keyguardUpdateMonitor.registerCallback(keyguardUpdateMonitorCallback);
    }

    public static Set getNewWidgets(Set set, final Map map) {
        return (Set) set.stream().map(new Function() { // from class: com.android.systemui.people.widget.PeopleSpaceWidgetManager$$ExternalSyntheticLambda3
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return (String) map.get((String) obj);
            }
        }).filter(new PeopleSpaceWidgetManager$$ExternalSyntheticLambda4(0)).collect(Collectors.toSet());
    }

    public final void addNewWidget(final int i, PeopleTileKey peopleTileKey) {
        PeopleTileKey keyFromStorageByWidgetId;
        try {
            PeopleSpaceTile tileFromPersistentStorage = getTileFromPersistentStorage(peopleTileKey, i, false);
            if (tileFromPersistentStorage == null) {
                return;
            }
            final PeopleSpaceTile augmentTileFromNotificationEntryManager = augmentTileFromNotificationEntryManager(tileFromPersistentStorage, Optional.of(Integer.valueOf(i)));
            synchronized (this.mLock) {
                keyFromStorageByWidgetId = getKeyFromStorageByWidgetId(i);
            }
            if (PeopleTileKey.isValid(keyFromStorageByWidgetId)) {
                deleteWidgets(new int[]{i});
            } else {
                this.mUiEventLogger.log(PeopleSpaceUtils.PeopleSpaceWidgetEvent.PEOPLE_SPACE_WIDGET_ADDED);
            }
            synchronized (this.mLock) {
                PeopleSpaceUtils.setSharedPreferencesStorageForTile(this.mContext, peopleTileKey, i, augmentTileFromNotificationEntryManager.getContactUri(), this.mBackupManager);
            }
            registerConversationListenerIfNeeded(i, peopleTileKey);
            try {
                this.mLauncherApps.cacheShortcuts(augmentTileFromNotificationEntryManager.getPackageName(), Collections.singletonList(augmentTileFromNotificationEntryManager.getId()), augmentTileFromNotificationEntryManager.getUserHandle(), 2);
            } catch (Exception e) {
                Log.w("PeopleSpaceWidgetMgr", "failed to cache shortcut for widget " + i, e);
            }
            this.mBgExecutor.execute(new Runnable() { // from class: com.android.systemui.people.widget.PeopleSpaceWidgetManager$$ExternalSyntheticLambda6
                @Override // java.lang.Runnable
                public final void run() {
                    PeopleSpaceWidgetManager peopleSpaceWidgetManager = PeopleSpaceWidgetManager.this;
                    int i2 = i;
                    PeopleSpaceTile peopleSpaceTile = augmentTileFromNotificationEntryManager;
                    Map map = PeopleSpaceWidgetManager.mListeners;
                    peopleSpaceWidgetManager.updateAppWidgetOptionsAndView(i2, peopleSpaceTile);
                }
            });
        } catch (PackageManager.NameNotFoundException unused) {
            Log.e("PeopleSpaceWidgetMgr", "Cannot add widget " + i + " since app was uninstalled");
        }
    }

    public final PeopleSpaceTile augmentTileFromNotificationEntryManager(PeopleSpaceTile peopleSpaceTile, Optional optional) {
        return augmentTileFromNotifications(peopleSpaceTile, new PeopleTileKey(peopleSpaceTile), peopleSpaceTile.getContactUri() != null ? peopleSpaceTile.getContactUri().toString() : null, groupConversationNotifications(((NotifPipeline) this.mNotifCollection).getAllNotifs()), optional);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v16, types: [java.util.List] */
    public final PeopleSpaceTile augmentTileFromNotifications(PeopleSpaceTile peopleSpaceTile, PeopleTileKey peopleTileKey, final String str, Map map, Optional optional) {
        Person senderPerson;
        List messagingStyleMessages;
        boolean z = this.mPackageManager.checkPermission("android.permission.READ_CONTACTS", peopleSpaceTile.getPackageName()) == 0;
        ArrayList arrayList = new ArrayList();
        if (z) {
            final PackageManager packageManager = this.mPackageManager;
            PeopleTileKey peopleTileKey2 = PeopleSpaceUtils.EMPTY_KEY;
            arrayList = TextUtils.isEmpty(str) ? new ArrayList() : (List) map.entrySet().stream().flatMap(new PeopleSpaceUtils$$ExternalSyntheticLambda0()).filter(new Predicate() { // from class: com.android.systemui.people.PeopleSpaceUtils$$ExternalSyntheticLambda1
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    PackageManager packageManager2 = packageManager;
                    String str2 = str;
                    NotificationEntry notificationEntry = (NotificationEntry) obj;
                    StatusBarNotification statusBarNotification = notificationEntry.mSbn;
                    NotificationHelper.AnonymousClass1 anonymousClass1 = NotificationHelper.notificationEntryComparator;
                    if (packageManager2.checkPermission("android.permission.READ_CONTACTS", statusBarNotification.getPackageName()) != 0) {
                        return false;
                    }
                    Notification notification = notificationEntry.mSbn.getNotification();
                    return (notification == null ? false : NotificationHelper.isMissedCall(notification)) && Objects.equals(str2, NotificationHelper.getContactUri(notificationEntry.mSbn));
                }
            }).collect(Collectors.toList());
            arrayList.isEmpty();
        }
        Set set = (Set) map.get(peopleTileKey);
        if (set == null) {
            set = new HashSet();
        }
        if (set.isEmpty() && arrayList.isEmpty()) {
            return PeopleSpaceUtils.removeNotificationFields(peopleSpaceTile);
        }
        set.addAll(arrayList);
        PeopleTileKey peopleTileKey3 = PeopleSpaceUtils.EMPTY_KEY;
        Iterator it = set.iterator();
        int i = 0;
        while (it.hasNext()) {
            Notification notification = ((NotificationEntry) it.next()).mSbn.getNotification();
            if (!NotificationHelper.isMissedCall(notification) && (messagingStyleMessages = NotificationHelper.getMessagingStyleMessages(notification)) != null) {
                i = messagingStyleMessages.size() + i;
            }
        }
        NotificationHelper.AnonymousClass1 anonymousClass1 = NotificationHelper.notificationEntryComparator;
        CharSequence charSequence = null;
        NotificationEntry notificationEntry = set.isEmpty() ? null : (NotificationEntry) set.stream().filter(new NotificationHelper$$ExternalSyntheticLambda0()).sorted(NotificationHelper.notificationEntryComparator).findFirst().orElse(null);
        Context context = this.mContext;
        BackupManager backupManager = this.mBackupManager;
        if (notificationEntry == null || notificationEntry.mSbn.getNotification() == null) {
            return PeopleSpaceUtils.removeNotificationFields(peopleSpaceTile);
        }
        StatusBarNotification statusBarNotification = notificationEntry.mSbn;
        Notification notification2 = statusBarNotification.getNotification();
        PeopleSpaceTile.Builder builder = peopleSpaceTile.toBuilder();
        String contactUri = NotificationHelper.getContactUri(statusBarNotification);
        if (optional.isPresent() && peopleSpaceTile.getContactUri() == null && !TextUtils.isEmpty(contactUri)) {
            Uri parse = Uri.parse(contactUri);
            PeopleSpaceUtils.setSharedPreferencesStorageForTile(context, new PeopleTileKey(peopleSpaceTile), ((Integer) optional.get()).intValue(), parse, backupManager);
            builder.setContactUri(parse);
        }
        boolean isMissedCall = NotificationHelper.isMissedCall(notification2);
        List messagingStyleMessages2 = NotificationHelper.getMessagingStyleMessages(notification2);
        if (!isMissedCall && ArrayUtils.isEmpty(messagingStyleMessages2)) {
            return PeopleSpaceUtils.removeNotificationFields(builder.build());
        }
        Notification.MessagingStyle.Message message = messagingStyleMessages2 != null ? (Notification.MessagingStyle.Message) messagingStyleMessages2.get(0) : null;
        CharSequence text = (!isMissedCall || ((message == null || TextUtils.isEmpty(message.getText())) ? false : true)) ? message.getText() : context.getString(R.string.missed_call);
        Uri dataUri = (message == null || !MessagingMessage.hasImage(message)) ? null : message.getDataUri();
        if (notification2.extras.getBoolean("android.isGroupConversation", false) && (senderPerson = message.getSenderPerson()) != null) {
            charSequence = senderPerson.getName();
        }
        return builder.setLastInteractionTimestamp(statusBarNotification.getPostTime()).setNotificationKey(statusBarNotification.getKey()).setNotificationCategory(notification2.category).setNotificationContent(text).setNotificationSender(charSequence).setNotificationDataUri(dataUri).setMessagesCount(i).build();
    }

    public final void deleteWidgets(int[] iArr) {
        PeopleTileKey peopleTileKey;
        String string;
        String string2;
        HashSet hashSet;
        String string3;
        for (int i : iArr) {
            this.mUiEventLogger.log(PeopleSpaceUtils.PeopleSpaceWidgetEvent.PEOPLE_SPACE_WIDGET_DELETED);
            synchronized (this.mLock) {
                try {
                    SharedPreferences sharedPreferences = this.mContext.getSharedPreferences(String.valueOf(i), 0);
                    string = sharedPreferences.getString("shortcut_id", null);
                    int i2 = sharedPreferences.getInt("user_id", -1);
                    string2 = sharedPreferences.getString("package_name", null);
                    peopleTileKey = new PeopleTileKey(string, string2, i2);
                    if (!PeopleTileKey.isValid(peopleTileKey)) {
                        Log.e("PeopleSpaceWidgetMgr", "Invalid tile key trying to remove widget " + i);
                        return;
                    }
                    hashSet = new HashSet(this.mSharedPrefs.getStringSet(peopleTileKey.toString(), new HashSet()));
                    string3 = this.mSharedPrefs.getString(String.valueOf(i), null);
                } finally {
                }
            }
            synchronized (this.mLock) {
                PeopleSpaceUtils.removeSharedPreferencesStorageForTile(this.mContext, peopleTileKey, i, string3);
            }
            if (hashSet.contains(String.valueOf(i)) && hashSet.size() == 1) {
                Map map = mListeners;
                synchronized (map) {
                    try {
                        TileConversationListener tileConversationListener = (TileConversationListener) map.get(peopleTileKey);
                        if (tileConversationListener != null) {
                            map.remove(peopleTileKey);
                            this.mPeopleManager.unregisterConversationListener(tileConversationListener);
                        }
                    } finally {
                    }
                }
                try {
                    this.mLauncherApps.uncacheShortcuts(string2, Collections.singletonList(string), UserHandle.of(peopleTileKey.mUserId), 2);
                } catch (Exception e) {
                    Log.d("PeopleSpaceWidgetMgr", "failed to uncache shortcut", e);
                }
            }
        }
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        Trace.traceBegin(4096L, "PeopleSpaceWidgetMgr.dump");
        Map<String, ?> all = PreferenceManager.getDefaultSharedPreferences(this.mContext).getAll();
        printWriter.println("People widget list:");
        for (Map.Entry<String, ?> entry : all.entrySet()) {
            String key = entry.getKey();
            int ordinal = PeopleBackupHelper.getEntryType(entry).ordinal();
            if (ordinal == 1) {
                SharedPreferences sharedPreferences = this.mContext.getSharedPreferences(key, 0);
                printWriter.print("People widget (valid) [");
                printWriter.print(key);
                printWriter.print("] shortcut id: \"");
                printWriter.print(sharedPreferences.getString("shortcut_id", ""));
                printWriter.print("\", user id: ");
                printWriter.print(sharedPreferences.getInt("user_id", -1));
                printWriter.print(", package: ");
                printWriter.println(sharedPreferences.getString("package_name", ""));
            } else if (ordinal == 2 || ordinal == 3) {
                printWriter.print("Extra data [");
                printWriter.print(key);
                printWriter.print(" : ");
                printWriter.print((Set) entry.getValue());
                printWriter.println("]");
            }
        }
        Trace.traceEnd(4096L);
    }

    public final PeopleTileKey getKeyFromStorageByWidgetId(int i) {
        SharedPreferences sharedPreferences = this.mContext.getSharedPreferences(String.valueOf(i), 0);
        return new PeopleTileKey(sharedPreferences.getString("shortcut_id", ""), sharedPreferences.getString("package_name", ""), sharedPreferences.getInt("user_id", -1));
    }

    public final Set getMatchingKeyWidgetIds(PeopleTileKey peopleTileKey) {
        return !PeopleTileKey.isValid(peopleTileKey) ? new HashSet() : new HashSet(this.mSharedPrefs.getStringSet(peopleTileKey.toString(), new HashSet()));
    }

    public final Set getMatchingUriWidgetIds(StatusBarNotification statusBarNotification, PeopleSpaceUtils.NotificationAction notificationAction) {
        String contactUri;
        if (notificationAction.equals(PeopleSpaceUtils.NotificationAction.POSTED)) {
            NotificationHelper.AnonymousClass1 anonymousClass1 = NotificationHelper.notificationEntryComparator;
            Notification notification = statusBarNotification.getNotification();
            HashSet hashSet = null;
            if ((notification == null ? false : NotificationHelper.isMissedCall(notification)) && (contactUri = NotificationHelper.getContactUri(statusBarNotification)) != null) {
                HashSet hashSet2 = new HashSet(this.mSharedPrefs.getStringSet(contactUri, new HashSet()));
                if (!hashSet2.isEmpty()) {
                    hashSet = hashSet2;
                }
            }
            if (hashSet != null && !hashSet.isEmpty()) {
                this.mNotificationKeyToWidgetIdsMatchedByUri.put(statusBarNotification.getKey(), hashSet);
                return hashSet;
            }
        } else {
            Set set = (Set) this.mNotificationKeyToWidgetIdsMatchedByUri.remove(statusBarNotification.getKey());
            if (set != null && !set.isEmpty()) {
                return set;
            }
        }
        return new HashSet();
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0041  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int getNotificationPolicyState() {
        /*
            r4 = this;
            android.app.NotificationManager r0 = r4.mNotificationManager
            android.app.NotificationManager$Policy r0 = r0.getNotificationPolicy()
            int r1 = r0.suppressedVisualEffects
            boolean r1 = android.app.NotificationManager.Policy.areAllVisualEffectsSuppressed(r1)
            r2 = 1
            if (r1 != 0) goto L10
            return r2
        L10:
            android.app.NotificationManager r4 = r4.mNotificationManager
            int r4 = r4.getCurrentInterruptionFilter()
            if (r4 == r2) goto L45
            r1 = 2
            if (r4 == r1) goto L1c
            goto L44
        L1c:
            boolean r4 = r0.allowConversations()
            if (r4 == 0) goto L2b
            int r4 = r0.priorityConversationSenders
            if (r4 != r2) goto L27
            return r2
        L27:
            if (r4 != r1) goto L2b
            r4 = 4
            goto L2c
        L2b:
            r4 = 0
        L2c:
            boolean r3 = r0.allowMessages()
            if (r3 == 0) goto L41
            int r0 = r0.allowMessagesFrom()
            if (r0 == r2) goto L3e
            if (r0 == r1) goto L3b
            return r2
        L3b:
            r4 = r4 | 8
            return r4
        L3e:
            r4 = r4 | 16
            return r4
        L41:
            if (r4 == 0) goto L44
            return r4
        L44:
            return r1
        L45:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.people.widget.PeopleSpaceWidgetManager.getNotificationPolicyState():int");
    }

    public final boolean getPackageSuspended(PeopleSpaceTile peopleSpaceTile) {
        boolean z = !TextUtils.isEmpty(peopleSpaceTile.getPackageName()) && this.mPackageManager.isPackageSuspended(peopleSpaceTile.getPackageName());
        PackageManager packageManager = this.mPackageManager;
        String packageName = peopleSpaceTile.getPackageName();
        PeopleTileKey peopleTileKey = PeopleSpaceUtils.EMPTY_KEY;
        packageManager.getApplicationInfoAsUser(packageName, 128, peopleSpaceTile.getUserHandle().getIdentifier());
        return z;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x003d A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.widget.RemoteViews getPreview(java.lang.String r4, android.os.UserHandle r5, java.lang.String r6, android.os.Bundle r7) {
        /*
            r3 = this;
            r0 = 0
            android.app.people.IPeopleManager r1 = r3.mIPeopleManager     // Catch: java.lang.Exception -> L52
            int r5 = r5.getIdentifier()     // Catch: java.lang.Exception -> L52
            android.app.people.ConversationChannel r4 = r1.getConversation(r6, r5, r4)     // Catch: java.lang.Exception -> L52
            android.content.pm.LauncherApps r5 = r3.mLauncherApps     // Catch: java.lang.Exception -> L52
            com.android.systemui.people.widget.PeopleTileKey r6 = com.android.systemui.people.PeopleSpaceUtils.EMPTY_KEY     // Catch: java.lang.Exception -> L52
            java.lang.String r6 = "PeopleSpaceUtils"
            r1 = 0
            if (r4 != 0) goto L1b
            java.lang.String r4 = "ConversationChannel is null"
            android.util.Log.i(r6, r4)     // Catch: java.lang.Exception -> L52
        L19:
            r4 = r0
            goto L3b
        L1b:
            android.app.people.PeopleSpaceTile$Builder r2 = new android.app.people.PeopleSpaceTile$Builder     // Catch: java.lang.Exception -> L52
            r2.<init>(r4, r5)     // Catch: java.lang.Exception -> L52
            android.app.people.PeopleSpaceTile r4 = r2.build()     // Catch: java.lang.Exception -> L52
            if (r4 == 0) goto L32
            java.lang.CharSequence r5 = r4.getUserName()     // Catch: java.lang.Exception -> L52
            boolean r5 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Exception -> L52
            if (r5 != 0) goto L32
            r5 = 1
            goto L33
        L32:
            r5 = r1
        L33:
            if (r5 != 0) goto L3b
            java.lang.String r4 = "PeopleSpaceTile is not valid"
            android.util.Log.i(r6, r4)     // Catch: java.lang.Exception -> L52
            goto L19
        L3b:
            if (r4 != 0) goto L3e
            return r0
        L3e:
            java.util.Optional r5 = java.util.Optional.empty()
            android.app.people.PeopleSpaceTile r4 = r3.augmentTileFromNotificationEntryManager(r4, r5)
            android.content.Context r3 = r3.mContext
            com.android.systemui.people.widget.PeopleTileKey r5 = new com.android.systemui.people.widget.PeopleTileKey
            r5.<init>(r4)
            android.widget.RemoteViews r3 = com.android.systemui.people.PeopleTileViewHelper.createRemoteViews(r3, r4, r1, r7, r5)
            return r3
        L52:
            r3 = move-exception
            java.lang.String r4 = "PeopleSpaceWidgetMgr"
            java.lang.String r5 = "failed to get conversation or tile"
            android.util.Log.w(r4, r5, r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.people.widget.PeopleSpaceWidgetManager.getPreview(java.lang.String, android.os.UserHandle, java.lang.String, android.os.Bundle):android.widget.RemoteViews");
    }

    public final PeopleSpaceTile getTileForExistingWidget(int i) {
        try {
            return getTileForExistingWidgetThrowing(i);
        } catch (Exception e) {
            Log.e("PeopleSpaceWidgetMgr", "failed to retrieve tile for existing widget " + i, e);
            return null;
        }
    }

    public final PeopleSpaceTile getTileForExistingWidgetThrowing(int i) {
        PeopleSpaceTile peopleSpaceTile;
        Map map = mTiles;
        synchronized (map) {
            peopleSpaceTile = (PeopleSpaceTile) map.get(Integer.valueOf(i));
        }
        if (peopleSpaceTile != null) {
            return peopleSpaceTile;
        }
        SharedPreferences sharedPreferences = this.mContext.getSharedPreferences(String.valueOf(i), 0);
        return getTileFromPersistentStorage(new PeopleTileKey(sharedPreferences.getString("shortcut_id", ""), sharedPreferences.getString("package_name", ""), sharedPreferences.getInt("user_id", -1)), i, true);
    }

    public final PeopleSpaceTile getTileFromPersistentStorage(PeopleTileKey peopleTileKey, int i, boolean z) {
        if (!PeopleTileKey.isValid(peopleTileKey)) {
            ClockEventController$zenModeCallback$1$$ExternalSyntheticOutline0.m("Invalid tile key finding tile for existing widget ", "PeopleSpaceWidgetMgr", i);
            return null;
        }
        IPeopleManager iPeopleManager = this.mIPeopleManager;
        if (iPeopleManager == null || this.mLauncherApps == null) {
            Log.d("PeopleSpaceWidgetMgr", "System services are null");
            return null;
        }
        try {
            ConversationChannel conversation = iPeopleManager.getConversation(peopleTileKey.mPackageName, peopleTileKey.mUserId, peopleTileKey.mShortcutId);
            if (conversation == null) {
                return null;
            }
            PeopleSpaceTile.Builder builder = new PeopleSpaceTile.Builder(conversation, this.mLauncherApps);
            String string = this.mSharedPrefs.getString(String.valueOf(i), null);
            if (z && string != null && builder.build().getContactUri() == null) {
                builder.setContactUri(Uri.parse(string));
            }
            return getTileWithCurrentState(builder.build(), "android.intent.action.BOOT_COMPLETED");
        } catch (RemoteException e) {
            Log.e("PeopleSpaceWidgetMgr", "getTileFromPersistentStorage failing for widget " + i, e);
            return null;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final PeopleSpaceTile getTileWithCurrentState(PeopleSpaceTile peopleSpaceTile, String str) {
        char c;
        PeopleSpaceTile.Builder builder = peopleSpaceTile.toBuilder();
        switch (str.hashCode()) {
            case -1238404651:
                if (str.equals("android.intent.action.MANAGED_PROFILE_UNAVAILABLE")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -1001645458:
                if (str.equals("android.intent.action.PACKAGES_SUSPENDED")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -864107122:
                if (str.equals("android.intent.action.MANAGED_PROFILE_AVAILABLE")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -19011148:
                if (str.equals("android.intent.action.LOCALE_CHANGED")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 798292259:
                if (str.equals("android.intent.action.BOOT_COMPLETED")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 833559602:
                if (str.equals("android.intent.action.USER_UNLOCKED")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1290767157:
                if (str.equals("android.intent.action.PACKAGES_UNSUSPENDED")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 2106958107:
                if (str.equals("android.app.action.INTERRUPTION_FILTER_CHANGED")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                builder.setNotificationPolicyState(getNotificationPolicyState());
                break;
            case 1:
            case 2:
                builder.setIsPackageSuspended(getPackageSuspended(peopleSpaceTile));
                break;
            case 3:
            case 4:
            case 5:
                builder.setIsUserQuieted(peopleSpaceTile.getUserHandle() != null && this.mUserManager.isQuietModeEnabled(peopleSpaceTile.getUserHandle()));
                break;
            case 6:
                break;
            default:
                builder.setIsUserQuieted(peopleSpaceTile.getUserHandle() != null && this.mUserManager.isQuietModeEnabled(peopleSpaceTile.getUserHandle())).setIsPackageSuspended(getPackageSuspended(peopleSpaceTile)).setNotificationPolicyState(getNotificationPolicyState());
                break;
        }
        return builder.build();
    }

    public final Map groupConversationNotifications(Collection collection) {
        return (Map) collection.stream().filter(new PeopleSpaceWidgetManager$$ExternalSyntheticLambda0(0, this)).collect(Collectors.groupingBy(new PeopleSpaceWidgetManager$$ExternalSyntheticLambda1(0), Collectors.mapping(Function.identity(), Collectors.toSet())));
    }

    public final void registerConversationListenerIfNeeded(int i, PeopleTileKey peopleTileKey) {
        if (!PeopleTileKey.isValid(peopleTileKey)) {
            RecordingInputConnection$$ExternalSyntheticOutline0.m("Invalid tile key registering listener for widget ", "PeopleSpaceWidgetMgr", i);
            return;
        }
        TileConversationListener tileConversationListener = new TileConversationListener();
        Map map = mListeners;
        synchronized (map) {
            try {
                if (map.containsKey(peopleTileKey)) {
                    return;
                }
                map.put(peopleTileKey, tileConversationListener);
                this.mPeopleManager.registerConversationListener(peopleTileKey.mPackageName, peopleTileKey.mUserId, peopleTileKey.mShortcutId, tileConversationListener, this.mContext.getMainExecutor());
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void updateAppWidgetOptionsAndView(int i, PeopleSpaceTile peopleSpaceTile) {
        if (this.mAppWidgetManagerOptional.isEmpty()) {
            return;
        }
        if (peopleSpaceTile == null) {
            RecordingInputConnection$$ExternalSyntheticOutline0.m("Storing null tile for widget ", "PeopleSpaceWidgetMgr", i);
        }
        Map map = mTiles;
        synchronized (map) {
            map.put(Integer.valueOf(i), peopleSpaceTile);
        }
        Bundle appWidgetOptions = ((AppWidgetManager) this.mAppWidgetManagerOptional.get()).getAppWidgetOptions(i);
        if (this.mAppWidgetManagerOptional.isEmpty()) {
            return;
        }
        PeopleTileKey keyFromStorageByWidgetId = getKeyFromStorageByWidgetId(i);
        if (!PeopleTileKey.isValid(keyFromStorageByWidgetId)) {
            ClockEventController$zenModeCallback$1$$ExternalSyntheticOutline0.m("Invalid tile key updating widget ", "PeopleSpaceWidgetMgr", i);
        } else {
            ((AppWidgetManager) this.mAppWidgetManagerOptional.get()).updateAppWidget(i, PeopleTileViewHelper.createRemoteViews(this.mContext, peopleSpaceTile, i, appWidgetOptions, keyFromStorageByWidgetId));
        }
    }

    public void updateGeneratedPreviewForUser(UserHandle userHandle) {
        if (this.mUpdatedPreviews.get(userHandle.getIdentifier()) || !this.mUserManager.isUserUnlocked(userHandle) || this.mAppWidgetManagerOptional.isEmpty()) {
            return;
        }
        ComponentName componentName = new ComponentName(this.mContext, (Class<?>) PeopleSpaceWidgetProvider.class);
        if (((AppWidgetManager) this.mAppWidgetManagerOptional.get()).getInstalledProvidersForPackage(this.mContext.getPackageName(), userHandle).stream().noneMatch(new PeopleSpaceWidgetManager$$ExternalSyntheticLambda0(1, componentName))) {
            return;
        }
        this.mUpdatedPreviews.put(userHandle.getIdentifier(), ((AppWidgetManager) this.mAppWidgetManagerOptional.get()).setWidgetPreview(componentName, 3, new RemoteViews(this.mContext.getPackageName(), R.layout.people_space_placeholder_layout)));
    }

    public final void updateSingleConversationWidgets(final int[] iArr) {
        final HashMap hashMap = new HashMap();
        for (int i : iArr) {
            PeopleSpaceTile tileForExistingWidget = getTileForExistingWidget(i);
            if (tileForExistingWidget == null) {
                ClockEventController$zenModeCallback$1$$ExternalSyntheticOutline0.m("Matching conversation not found for widget ", "PeopleSpaceWidgetMgr", i);
            }
            updateAppWidgetOptionsAndView(i, tileForExistingWidget);
            hashMap.put(Integer.valueOf(i), tileForExistingWidget);
            if (tileForExistingWidget != null) {
                registerConversationListenerIfNeeded(i, new PeopleTileKey(tileForExistingWidget));
            }
        }
        final Context context = this.mContext;
        PeopleTileKey peopleTileKey = PeopleSpaceUtils.EMPTY_KEY;
        final PeopleSpaceWidgetManager peopleSpaceWidgetManager = this.mManager;
        ThreadUtils.postOnBackgroundThread(new Runnable() { // from class: com.android.systemui.people.PeopleSpaceUtils$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                PeopleSpaceUtils.getDataFromContacts(context, peopleSpaceWidgetManager, hashMap, iArr);
            }
        });
    }

    public final void updateStorageAndViewWithConversationData(ConversationChannel conversationChannel, int i) {
        PeopleSpaceTile tileForExistingWidget = getTileForExistingWidget(i);
        if (tileForExistingWidget == null) {
            return;
        }
        PeopleSpaceTile.Builder builder = tileForExistingWidget.toBuilder();
        ShortcutInfo shortcutInfo = conversationChannel.getShortcutInfo();
        Uri uri = null;
        if (shortcutInfo.getPersons() != null && shortcutInfo.getPersons().length > 0) {
            Person person = shortcutInfo.getPersons()[0];
            if (person.getUri() != null) {
                uri = Uri.parse(person.getUri());
            }
        }
        CharSequence label = shortcutInfo.getLabel();
        if (label != null) {
            builder.setUserName(label);
        }
        Icon convertDrawableToIcon = PeopleSpaceTile.convertDrawableToIcon(this.mLauncherApps.getShortcutIconDrawable(shortcutInfo, 0));
        if (convertDrawableToIcon != null) {
            builder.setUserIcon(convertDrawableToIcon);
        }
        NotificationChannel notificationChannel = conversationChannel.getNotificationChannel();
        if (notificationChannel != null) {
            builder.setIsImportantConversation(notificationChannel.isImportantConversation());
        }
        builder.setContactUri(uri).setStatuses(conversationChannel.getStatuses()).setLastInteractionTimestamp(conversationChannel.getLastEventTimestamp());
        updateAppWidgetOptionsAndView(i, builder.build());
    }

    public final void updateWidgetIdsBasedOnNotifications(Set set, Collection collection) {
        if (set.isEmpty()) {
            return;
        }
        try {
            final Map groupConversationNotifications = groupConversationNotifications(collection);
            ((Map) set.stream().map(new PeopleSpaceWidgetManager$$ExternalSyntheticLambda1(3)).collect(Collectors.toMap(Function.identity(), new Function() { // from class: com.android.systemui.people.widget.PeopleSpaceWidgetManager$$ExternalSyntheticLambda14
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    PeopleSpaceWidgetManager peopleSpaceWidgetManager = PeopleSpaceWidgetManager.this;
                    Map map = groupConversationNotifications;
                    Integer num = (Integer) obj;
                    Map map2 = PeopleSpaceWidgetManager.mListeners;
                    peopleSpaceWidgetManager.getClass();
                    int intValue = num.intValue();
                    PeopleSpaceTile tileForExistingWidget = peopleSpaceWidgetManager.getTileForExistingWidget(intValue);
                    if (tileForExistingWidget != null) {
                        return Optional.ofNullable(peopleSpaceWidgetManager.augmentTileFromNotifications(tileForExistingWidget, new PeopleTileKey(tileForExistingWidget), peopleSpaceWidgetManager.mSharedPrefs.getString(String.valueOf(intValue), null), map, Optional.of(num)));
                    }
                    Log.w("PeopleSpaceWidgetMgr", "Null tile for existing widget " + intValue + ", skipping update.");
                    return Optional.empty();
                }
            }))).forEach(new BiConsumer() { // from class: com.android.systemui.people.widget.PeopleSpaceWidgetManager$$ExternalSyntheticLambda15
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    PeopleSpaceWidgetManager peopleSpaceWidgetManager = PeopleSpaceWidgetManager.this;
                    Optional optional = (Optional) obj2;
                    Map map = PeopleSpaceWidgetManager.mListeners;
                    peopleSpaceWidgetManager.getClass();
                    int intValue = ((Integer) obj).intValue();
                    if (optional.isPresent()) {
                        peopleSpaceWidgetManager.updateAppWidgetOptionsAndView(intValue, (PeopleSpaceTile) optional.get());
                    }
                }
            });
        } catch (Exception e) {
            Log.e("PeopleSpaceWidgetMgr", "updateWidgetIdsBasedOnNotifications failing", e);
        }
    }

    public void updateWidgetsFromBroadcastInBackground(String str) {
        int[] appWidgetIds;
        if (this.mAppWidgetManagerOptional.isEmpty() || (appWidgetIds = ((AppWidgetManager) this.mAppWidgetManagerOptional.get()).getAppWidgetIds(new ComponentName(this.mContext, (Class<?>) PeopleSpaceWidgetProvider.class))) == null) {
            return;
        }
        for (int i : appWidgetIds) {
            try {
                synchronized (this.mLock) {
                    try {
                        PeopleSpaceTile tileForExistingWidgetThrowing = getTileForExistingWidgetThrowing(i);
                        if (tileForExistingWidgetThrowing == null) {
                            Log.e("PeopleSpaceWidgetMgr", "Matching conversation not found for widget " + i);
                        } else {
                            updateAppWidgetOptionsAndView(i, getTileWithCurrentState(tileForExistingWidgetThrowing, str));
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            } catch (PackageManager.NameNotFoundException e) {
                Log.e("PeopleSpaceWidgetMgr", "Package no longer found for widget " + i, e);
                JobScheduler jobScheduler = (JobScheduler) this.mContext.getSystemService(JobScheduler.class);
                if (jobScheduler == null || jobScheduler.getPendingJob(74823873) == null) {
                    synchronized (this.mLock) {
                        updateAppWidgetOptionsAndView(i, null);
                        deleteWidgets(new int[]{i});
                    }
                } else {
                    continue;
                }
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.people.widget.PeopleSpaceWidgetManager$2, reason: invalid class name */
    public final class AnonymousClass2 implements NotificationListener.NotificationHandler {
        public AnonymousClass2() {
        }

        @Override // com.android.systemui.statusbar.NotificationListener.NotificationHandler
        public final void onNotificationChannelModified(String str, UserHandle userHandle, NotificationChannel notificationChannel, int i) {
            PeopleSpaceWidgetManager peopleSpaceWidgetManager = PeopleSpaceWidgetManager.this;
            if (!peopleSpaceWidgetManager.mAppWidgetManagerOptional.isEmpty() && notificationChannel.isConversation()) {
                peopleSpaceWidgetManager.mBgExecutor.execute(new PeopleSpaceWidgetManager$$ExternalSyntheticLambda5(1, this, userHandle));
            }
        }

        @Override // com.android.systemui.statusbar.NotificationListener.NotificationHandler
        public final void onNotificationPosted(StatusBarNotification statusBarNotification, NotificationListenerService.RankingMap rankingMap) {
            PeopleSpaceUtils.NotificationAction notificationAction = PeopleSpaceUtils.NotificationAction.POSTED;
            PeopleSpaceWidgetManager peopleSpaceWidgetManager = PeopleSpaceWidgetManager.this;
            peopleSpaceWidgetManager.mBgExecutor.execute(new PeopleSpaceWidgetManager$$ExternalSyntheticLambda12(peopleSpaceWidgetManager, statusBarNotification, notificationAction, ((NotifPipeline) peopleSpaceWidgetManager.mNotifCollection).getAllNotifs()));
        }

        @Override // com.android.systemui.statusbar.NotificationListener.NotificationHandler
        public final void onNotificationRemoved(StatusBarNotification statusBarNotification, NotificationListenerService.RankingMap rankingMap, int i) {
            PeopleSpaceUtils.NotificationAction notificationAction = PeopleSpaceUtils.NotificationAction.REMOVED;
            PeopleSpaceWidgetManager peopleSpaceWidgetManager = PeopleSpaceWidgetManager.this;
            peopleSpaceWidgetManager.mBgExecutor.execute(new PeopleSpaceWidgetManager$$ExternalSyntheticLambda12(peopleSpaceWidgetManager, statusBarNotification, notificationAction, ((NotifPipeline) peopleSpaceWidgetManager.mNotifCollection).getAllNotifs()));
        }

        @Override // com.android.systemui.statusbar.NotificationListener.NotificationHandler
        public final void onNotificationsInitialized() {
        }

        @Override // com.android.systemui.statusbar.NotificationListener.NotificationHandler
        public final void onNotificationRankingUpdate(NotificationListenerService.RankingMap rankingMap) {
        }
    }

    public PeopleSpaceWidgetManager(Context context, Optional optional, IPeopleManager iPeopleManager, PeopleManager peopleManager, LauncherApps launcherApps, CommonNotifCollection commonNotifCollection, PackageManager packageManager, Optional optional2, UserManager userManager, BackupManager backupManager, INotificationManager iNotificationManager, NotificationManager notificationManager, Executor executor, UserTracker userTracker) {
        this.mLock = new Object();
        this.mUiEventLogger = new UiEventLoggerImpl();
        this.mNotificationKeyToWidgetIdsMatchedByUri = new HashMap();
        this.mUpdatedPreviews = new SparseBooleanArray();
        this.mKeyguardUpdateMonitorCallback = new KeyguardUpdateMonitorCallback() { // from class: com.android.systemui.people.widget.PeopleSpaceWidgetManager.1
            @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
            public final void onUserUnlocked() {
                PeopleSpaceWidgetManager peopleSpaceWidgetManager = PeopleSpaceWidgetManager.this;
                peopleSpaceWidgetManager.updateGeneratedPreviewForUser(((UserTrackerImpl) peopleSpaceWidgetManager.mUserTracker).getUserHandle());
            }
        };
        this.mListener = new AnonymousClass2();
        this.mBaseBroadcastReceiver = new AnonymousClass3();
        this.mContext = context;
        this.mAppWidgetManagerOptional = optional;
        this.mIPeopleManager = iPeopleManager;
        this.mPeopleManager = peopleManager;
        this.mLauncherApps = launcherApps;
        this.mNotifCollection = commonNotifCollection;
        this.mPackageManager = packageManager;
        this.mBubblesOptional = optional2;
        this.mUserManager = userManager;
        this.mBackupManager = backupManager;
        this.mINotificationManager = iNotificationManager;
        this.mNotificationManager = notificationManager;
        this.mManager = this;
        this.mSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        this.mBgExecutor = executor;
        this.mUserTracker = userTracker;
    }
}
