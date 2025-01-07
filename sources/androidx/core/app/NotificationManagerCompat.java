package androidx.core.app;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Message;
import android.os.Parcel;
import android.os.RemoteException;
import android.provider.Settings;
import android.support.v4.app.INotificationSideChannel;
import android.util.Log;
import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class NotificationManagerCompat {
    public static String sEnabledNotificationListeners;
    public static SideChannelManager sSideChannelManager;
    public final Context mContext;
    public final NotificationManager mNotificationManager;
    public static final Object sEnabledNotificationListenersLock = new Object();
    public static Set sEnabledNotificationListenerPackages = new HashSet();
    public static final Object sLock = new Object();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class NotifyTask {
        public final int id;
        public final Notification notif;
        public final String packageName;

        public NotifyTask(String str, int i, Notification notification) {
            this.packageName = str;
            this.id = i;
            this.notif = notification;
        }

        public final void send(INotificationSideChannel iNotificationSideChannel) {
            String str = this.packageName;
            int i = this.id;
            Notification notification = this.notif;
            INotificationSideChannel.Stub.Proxy proxy = (INotificationSideChannel.Stub.Proxy) iNotificationSideChannel;
            proxy.getClass();
            Parcel obtain = Parcel.obtain();
            try {
                obtain.writeInterfaceToken(INotificationSideChannel.DESCRIPTOR);
                obtain.writeString(str);
                obtain.writeInt(i);
                obtain.writeString(null);
                obtain.writeInt(1);
                notification.writeToParcel(obtain, 0);
                proxy.mRemote.transact(1, obtain, null, 1);
            } finally {
                obtain.recycle();
            }
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("NotifyTask[packageName:");
            sb.append(this.packageName);
            sb.append(", id:");
            return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.id, ", tag:null]");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ServiceConnectedEvent {
        public final ComponentName componentName;
        public final IBinder iBinder;

        public ServiceConnectedEvent(ComponentName componentName, IBinder iBinder) {
            this.componentName = componentName;
            this.iBinder = iBinder;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SideChannelManager implements Handler.Callback, ServiceConnection {
        public final Context mContext;
        public final Handler mHandler;
        public final Map mRecordMap = new HashMap();
        public Set mCachedEnabledPackages = new HashSet();

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class ListenerRecord {
            public final ComponentName componentName;
            public INotificationSideChannel service;
            public boolean bound = false;
            public final ArrayDeque taskQueue = new ArrayDeque();
            public int retryCount = 0;

            public ListenerRecord(ComponentName componentName) {
                this.componentName = componentName;
            }
        }

        public SideChannelManager(Context context) {
            this.mContext = context;
            HandlerThread handlerThread = new HandlerThread("NotificationManagerCompat");
            handlerThread.start();
            this.mHandler = new Handler(handlerThread.getLooper(), this);
        }

        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            Set set;
            int i = message.what;
            INotificationSideChannel iNotificationSideChannel = null;
            if (i != 0) {
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            return false;
                        }
                        ListenerRecord listenerRecord = (ListenerRecord) this.mRecordMap.get((ComponentName) message.obj);
                        if (listenerRecord != null) {
                            processListenerQueue(listenerRecord);
                        }
                        return true;
                    }
                    ListenerRecord listenerRecord2 = (ListenerRecord) this.mRecordMap.get((ComponentName) message.obj);
                    if (listenerRecord2 != null) {
                        if (listenerRecord2.bound) {
                            this.mContext.unbindService(this);
                            listenerRecord2.bound = false;
                        }
                        listenerRecord2.service = null;
                    }
                    return true;
                }
                ServiceConnectedEvent serviceConnectedEvent = (ServiceConnectedEvent) message.obj;
                ComponentName componentName = serviceConnectedEvent.componentName;
                IBinder iBinder = serviceConnectedEvent.iBinder;
                ListenerRecord listenerRecord3 = (ListenerRecord) this.mRecordMap.get(componentName);
                if (listenerRecord3 != null) {
                    int i2 = INotificationSideChannel.Stub.$r8$clinit;
                    if (iBinder != null) {
                        IInterface queryLocalInterface = iBinder.queryLocalInterface(INotificationSideChannel.DESCRIPTOR);
                        if (queryLocalInterface == null || !(queryLocalInterface instanceof INotificationSideChannel)) {
                            INotificationSideChannel.Stub.Proxy proxy = new INotificationSideChannel.Stub.Proxy();
                            proxy.mRemote = iBinder;
                            iNotificationSideChannel = proxy;
                        } else {
                            iNotificationSideChannel = (INotificationSideChannel) queryLocalInterface;
                        }
                    }
                    listenerRecord3.service = iNotificationSideChannel;
                    listenerRecord3.retryCount = 0;
                    processListenerQueue(listenerRecord3);
                }
                return true;
            }
            NotifyTask notifyTask = (NotifyTask) message.obj;
            String string = Settings.Secure.getString(this.mContext.getContentResolver(), "enabled_notification_listeners");
            synchronized (NotificationManagerCompat.sEnabledNotificationListenersLock) {
                if (string != null) {
                    try {
                        if (!string.equals(NotificationManagerCompat.sEnabledNotificationListeners)) {
                            String[] split = string.split(":", -1);
                            HashSet hashSet = new HashSet(split.length);
                            for (String str : split) {
                                ComponentName unflattenFromString = ComponentName.unflattenFromString(str);
                                if (unflattenFromString != null) {
                                    hashSet.add(unflattenFromString.getPackageName());
                                }
                            }
                            NotificationManagerCompat.sEnabledNotificationListenerPackages = hashSet;
                            NotificationManagerCompat.sEnabledNotificationListeners = string;
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                set = NotificationManagerCompat.sEnabledNotificationListenerPackages;
            }
            if (!set.equals(this.mCachedEnabledPackages)) {
                this.mCachedEnabledPackages = set;
                List<ResolveInfo> queryIntentServices = this.mContext.getPackageManager().queryIntentServices(new Intent().setAction("android.support.BIND_NOTIFICATION_SIDE_CHANNEL"), 0);
                HashSet hashSet2 = new HashSet();
                for (ResolveInfo resolveInfo : queryIntentServices) {
                    if (set.contains(resolveInfo.serviceInfo.packageName)) {
                        ServiceInfo serviceInfo = resolveInfo.serviceInfo;
                        ComponentName componentName2 = new ComponentName(serviceInfo.packageName, serviceInfo.name);
                        if (resolveInfo.serviceInfo.permission != null) {
                            Log.w("NotifManCompat", "Permission present on component " + componentName2 + ", not adding listener record.");
                        } else {
                            hashSet2.add(componentName2);
                        }
                    }
                }
                Iterator it = hashSet2.iterator();
                while (it.hasNext()) {
                    ComponentName componentName3 = (ComponentName) it.next();
                    if (!this.mRecordMap.containsKey(componentName3)) {
                        if (Log.isLoggable("NotifManCompat", 3)) {
                            Log.d("NotifManCompat", "Adding listener record for " + componentName3);
                        }
                        this.mRecordMap.put(componentName3, new ListenerRecord(componentName3));
                    }
                }
                Iterator it2 = ((HashMap) this.mRecordMap).entrySet().iterator();
                while (it2.hasNext()) {
                    Map.Entry entry = (Map.Entry) it2.next();
                    if (!hashSet2.contains(entry.getKey())) {
                        if (Log.isLoggable("NotifManCompat", 3)) {
                            Log.d("NotifManCompat", "Removing listener record for " + entry.getKey());
                        }
                        ListenerRecord listenerRecord4 = (ListenerRecord) entry.getValue();
                        if (listenerRecord4.bound) {
                            this.mContext.unbindService(this);
                            listenerRecord4.bound = false;
                        }
                        listenerRecord4.service = null;
                        it2.remove();
                    }
                }
            }
            for (ListenerRecord listenerRecord5 : this.mRecordMap.values()) {
                listenerRecord5.taskQueue.add(notifyTask);
                processListenerQueue(listenerRecord5);
            }
            return true;
        }

        @Override // android.content.ServiceConnection
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (Log.isLoggable("NotifManCompat", 3)) {
                Log.d("NotifManCompat", "Connected to service " + componentName);
            }
            this.mHandler.obtainMessage(1, new ServiceConnectedEvent(componentName, iBinder)).sendToTarget();
        }

        @Override // android.content.ServiceConnection
        public final void onServiceDisconnected(ComponentName componentName) {
            if (Log.isLoggable("NotifManCompat", 3)) {
                Log.d("NotifManCompat", "Disconnected from service " + componentName);
            }
            this.mHandler.obtainMessage(2, componentName).sendToTarget();
        }

        public final void processListenerQueue(ListenerRecord listenerRecord) {
            boolean z;
            if (Log.isLoggable("NotifManCompat", 3)) {
                Log.d("NotifManCompat", "Processing component " + listenerRecord.componentName + ", " + listenerRecord.taskQueue.size() + " queued tasks");
            }
            if (listenerRecord.taskQueue.isEmpty()) {
                return;
            }
            if (listenerRecord.bound) {
                z = true;
            } else {
                boolean bindService = this.mContext.bindService(new Intent("android.support.BIND_NOTIFICATION_SIDE_CHANNEL").setComponent(listenerRecord.componentName), this, 33);
                listenerRecord.bound = bindService;
                if (bindService) {
                    listenerRecord.retryCount = 0;
                } else {
                    Log.w("NotifManCompat", "Unable to bind to listener " + listenerRecord.componentName);
                    this.mContext.unbindService(this);
                }
                z = listenerRecord.bound;
            }
            if (!z || listenerRecord.service == null) {
                scheduleListenerRetry(listenerRecord);
                return;
            }
            while (true) {
                NotifyTask notifyTask = (NotifyTask) listenerRecord.taskQueue.peek();
                if (notifyTask == null) {
                    break;
                }
                try {
                    if (Log.isLoggable("NotifManCompat", 3)) {
                        Log.d("NotifManCompat", "Sending task " + notifyTask);
                    }
                    notifyTask.send(listenerRecord.service);
                    listenerRecord.taskQueue.remove();
                } catch (DeadObjectException unused) {
                    if (Log.isLoggable("NotifManCompat", 3)) {
                        Log.d("NotifManCompat", "Remote service has died: " + listenerRecord.componentName);
                    }
                } catch (RemoteException e) {
                    Log.w("NotifManCompat", "RemoteException communicating with " + listenerRecord.componentName, e);
                }
            }
            if (listenerRecord.taskQueue.isEmpty()) {
                return;
            }
            scheduleListenerRetry(listenerRecord);
        }

        public final void scheduleListenerRetry(ListenerRecord listenerRecord) {
            if (this.mHandler.hasMessages(3, listenerRecord.componentName)) {
                return;
            }
            int i = listenerRecord.retryCount;
            int i2 = i + 1;
            listenerRecord.retryCount = i2;
            if (i2 <= 6) {
                int i3 = (1 << i) * 1000;
                if (Log.isLoggable("NotifManCompat", 3)) {
                    Log.d("NotifManCompat", "Scheduling retry for " + i3 + " ms");
                }
                this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(3, listenerRecord.componentName), i3);
                return;
            }
            Log.w("NotifManCompat", "Giving up on delivering " + listenerRecord.taskQueue.size() + " tasks to " + listenerRecord.componentName + " after " + listenerRecord.retryCount + " retries");
            listenerRecord.taskQueue.clear();
        }
    }

    public NotificationManagerCompat(Context context) {
        this.mContext = context;
        this.mNotificationManager = (NotificationManager) context.getSystemService("notification");
    }
}
