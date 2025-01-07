package com.android.systemui.statusbar.connectivity;

import android.content.Context;
import android.util.Log;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import com.android.systemui.dump.DumpsysTableLogger;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class SignalController {
    public final CallbackHandler mCallbackHandler;
    public final Context mContext;
    public int mHistoryIndex;
    public final NetworkControllerImpl mNetworkController;
    public final String mTag;
    public final int mTransportType;
    public static final boolean DEBUG = NetworkControllerImpl.DEBUG;
    public static final boolean CHATTY = NetworkControllerImpl.CHATTY;
    public final ConnectivityState mCurrentState = cleanState();
    public final ConnectivityState mLastState = cleanState();
    public final ConnectivityState[] mHistory = new ConnectivityState[64];

    public SignalController(String str, Context context, int i, CallbackHandler callbackHandler, NetworkControllerImpl networkControllerImpl) {
        this.mTag = AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("NetworkController.", str);
        this.mNetworkController = networkControllerImpl;
        this.mTransportType = i;
        this.mContext = context;
        this.mCallbackHandler = callbackHandler;
        for (int i2 = 0; i2 < 64; i2++) {
            this.mHistory[i2] = cleanState();
        }
    }

    public abstract ConnectivityState cleanState();

    public void dump(PrintWriter printWriter) {
        printWriter.println("  - " + this.mTag + " -----");
        StringBuilder sb = new StringBuilder("  Current State: ");
        sb.append(this.mCurrentState);
        printWriter.println(sb.toString());
        List orderedHistoryExcludingCurrentState = getOrderedHistoryExcludingCurrentState();
        int i = 0;
        while (i < ((ArrayList) orderedHistoryExcludingCurrentState).size()) {
            StringBuilder sb2 = new StringBuilder("  Previous State(");
            int i2 = i + 1;
            sb2.append(i2);
            sb2.append("): ");
            sb2.append(this.mHistory[i]);
            printWriter.println(sb2.toString());
            i = i2;
        }
    }

    public final void dumpTableData(PrintWriter printWriter) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ConnectivityState connectivityState = this.mCurrentState;
        arrayList2.add(connectivityState);
        arrayList2.addAll(getOrderedHistoryExcludingCurrentState());
        for (int i = 0; i < arrayList2.size(); i++) {
            arrayList.add(((ConnectivityState) arrayList2.get(i)).tableData());
        }
        new DumpsysTableLogger(this.mTag, connectivityState.tableColumns(), arrayList).printTableData(printWriter);
    }

    public int getContentDescription() {
        ConnectivityState connectivityState = this.mCurrentState;
        return connectivityState.connected ? connectivityState.iconGroup.contentDesc[connectivityState.level] : connectivityState.iconGroup.discContentDesc;
    }

    public int getCurrentIconId() {
        ConnectivityState connectivityState = this.mCurrentState;
        return connectivityState.connected ? connectivityState.iconGroup.sbIcons[connectivityState.inetCondition][connectivityState.level] : connectivityState.enabled ? connectivityState.iconGroup.sbDiscState : connectivityState.iconGroup.sbNullState;
    }

    public final List getOrderedHistoryExcludingCurrentState() {
        ConnectivityState[] connectivityStateArr;
        ArrayList arrayList = new ArrayList();
        int i = 0;
        int i2 = 0;
        while (true) {
            connectivityStateArr = this.mHistory;
            if (i >= 64) {
                break;
            }
            if (connectivityStateArr[i].time != 0) {
                i2++;
            }
            i++;
        }
        for (int i3 = this.mHistoryIndex + 63; i3 >= (this.mHistoryIndex + 64) - i2; i3--) {
            arrayList.add(connectivityStateArr[i3 & 63]);
        }
        return arrayList;
    }

    public final CharSequence getTextIfExists(int i) {
        return i != 0 ? this.mContext.getText(i) : "";
    }

    public final void notifyListeners() {
        notifyListeners(this.mCallbackHandler);
    }

    public abstract void notifyListeners(SignalCallback signalCallback);

    public final void notifyListenersIfNecessary() {
        ConnectivityState connectivityState = this.mLastState;
        ConnectivityState connectivityState2 = this.mCurrentState;
        if (connectivityState.equals(connectivityState2)) {
            return;
        }
        if (DEBUG) {
            Log.d(this.mTag, "Change in state from: " + connectivityState + "\n\tto: " + connectivityState2);
        }
        this.mHistory[this.mHistoryIndex].copyFrom(connectivityState);
        this.mHistoryIndex = (this.mHistoryIndex + 1) % 64;
        connectivityState2.time = System.currentTimeMillis();
        connectivityState.copyFrom(connectivityState2);
        notifyListeners();
    }
}
