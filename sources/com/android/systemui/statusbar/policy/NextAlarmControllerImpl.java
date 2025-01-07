package com.android.systemui.statusbar.policy;

import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.UserHandle;
import com.android.systemui.Dumpable;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.policy.NextAlarmController;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NextAlarmControllerImpl extends BroadcastReceiver implements NextAlarmController, Dumpable {
    public final AlarmManager mAlarmManager;
    public final ArrayList mChangeCallbacks = new ArrayList();
    public AlarmManager.AlarmClockInfo mNextAlarm;
    public final UserTracker.Callback mUserChangedCallback;
    public final UserTracker mUserTracker;

    public NextAlarmControllerImpl(Executor executor, AlarmManager alarmManager, BroadcastDispatcher broadcastDispatcher, DumpManager dumpManager, UserTracker userTracker) {
        UserTracker.Callback callback = new UserTracker.Callback() { // from class: com.android.systemui.statusbar.policy.NextAlarmControllerImpl.1
            @Override // com.android.systemui.settings.UserTracker.Callback
            public final void onUserChanged(int i, Context context) {
                NextAlarmControllerImpl.this.updateNextAlarm$1();
            }
        };
        this.mUserChangedCallback = callback;
        dumpManager.getClass();
        DumpManager.registerDumpable$default(dumpManager, "NextAlarmController", this);
        this.mAlarmManager = alarmManager;
        this.mUserTracker = userTracker;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.app.action.NEXT_ALARM_CLOCK_CHANGED");
        broadcastDispatcher.registerReceiver(this, intentFilter, null, UserHandle.ALL);
        ((UserTrackerImpl) userTracker).addCallback(callback, executor);
        updateNextAlarm$1();
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void addCallback(Object obj) {
        NextAlarmController.NextAlarmChangeCallback nextAlarmChangeCallback = (NextAlarmController.NextAlarmChangeCallback) obj;
        this.mChangeCallbacks.add(nextAlarmChangeCallback);
        nextAlarmChangeCallback.onNextAlarmChanged(this.mNextAlarm);
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.print("mNextAlarm=");
        if (this.mNextAlarm != null) {
            printWriter.println(new Date(this.mNextAlarm.getTriggerTime()));
            printWriter.print("  PendingIntentPkg=");
            if (this.mNextAlarm.getShowIntent() != null) {
                printWriter.println(this.mNextAlarm.getShowIntent().getCreatorPackage());
            } else {
                printWriter.println("showIntent=null");
            }
        } else {
            printWriter.println("null");
        }
        printWriter.println("Registered Callbacks:");
        Iterator it = this.mChangeCallbacks.iterator();
        while (it.hasNext()) {
            NextAlarmController.NextAlarmChangeCallback nextAlarmChangeCallback = (NextAlarmController.NextAlarmChangeCallback) it.next();
            printWriter.print("    ");
            printWriter.println(nextAlarmChangeCallback.toString());
        }
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.app.action.NEXT_ALARM_CLOCK_CHANGED")) {
            updateNextAlarm$1();
        }
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void removeCallback(Object obj) {
        this.mChangeCallbacks.remove((NextAlarmController.NextAlarmChangeCallback) obj);
    }

    public final void updateNextAlarm$1() {
        this.mNextAlarm = this.mAlarmManager.getNextAlarmClock(((UserTrackerImpl) this.mUserTracker).getUserId());
        ArrayList arrayList = new ArrayList(this.mChangeCallbacks);
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ((NextAlarmController.NextAlarmChangeCallback) arrayList.get(i)).onNextAlarmChanged(this.mNextAlarm);
        }
    }
}
