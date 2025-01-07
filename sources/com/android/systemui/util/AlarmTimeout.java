package com.android.systemui.util;

import android.app.AlarmManager;
import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.os.Handler;
import android.os.SystemClock;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AlarmTimeout implements AlarmManager.OnAlarmListener {
    public final AlarmManager mAlarmManager;
    public final Handler mHandler;
    public final AlarmManager.OnAlarmListener mListener;
    public boolean mScheduled;
    public final String mTag;

    public AlarmTimeout(AlarmManager alarmManager, AlarmManager.OnAlarmListener onAlarmListener, String str, Handler handler) {
        this.mAlarmManager = alarmManager;
        this.mListener = onAlarmListener;
        this.mTag = str;
        this.mHandler = handler;
    }

    public final void cancel() {
        if (this.mScheduled) {
            this.mAlarmManager.cancel(this);
            this.mScheduled = false;
        }
    }

    @Override // android.app.AlarmManager.OnAlarmListener
    public final void onAlarm() {
        if (this.mScheduled) {
            this.mScheduled = false;
            this.mListener.onAlarm();
        }
    }

    public final boolean schedule(long j, int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalArgumentException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "Illegal mode: "));
                }
                if (this.mScheduled) {
                    cancel();
                }
            } else if (this.mScheduled) {
                return false;
            }
        } else if (this.mScheduled) {
            throw new IllegalStateException(this.mTag.concat(" timeout is already scheduled"));
        }
        this.mAlarmManager.setExact(2, SystemClock.elapsedRealtime() + j, this.mTag, this, this.mHandler);
        this.mScheduled = true;
        return true;
    }
}
