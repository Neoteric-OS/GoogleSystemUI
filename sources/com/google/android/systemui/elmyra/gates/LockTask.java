package com.google.android.systemui.elmyra.gates;

import androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0;
import com.android.systemui.shared.system.TaskStackChangeListener;
import com.android.systemui.shared.system.TaskStackChangeListeners;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class LockTask extends Gate {
    public boolean mIsBlocked;
    public final AnonymousClass1 mTaskStackListener;

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.android.systemui.elmyra.gates.LockTask$1] */
    public LockTask(Executor executor) {
        super(executor);
        this.mTaskStackListener = new TaskStackChangeListener() { // from class: com.google.android.systemui.elmyra.gates.LockTask.1
            @Override // com.android.systemui.shared.system.TaskStackChangeListener
            public final void onLockTaskModeChanged(int i) {
                ExifInterface$$ExternalSyntheticOutline0.m("Mode: ", "Elmyra/LockTask", i);
                LockTask lockTask = LockTask.this;
                if (i == 0) {
                    lockTask.mIsBlocked = false;
                } else {
                    lockTask.mIsBlocked = true;
                }
                lockTask.notifyListener();
            }
        };
    }

    @Override // com.google.android.systemui.elmyra.gates.Gate
    public final boolean isBlocked() {
        return this.mIsBlocked;
    }

    @Override // com.google.android.systemui.elmyra.gates.Gate
    public final void onActivate() {
        TaskStackChangeListeners.INSTANCE.registerTaskStackListener(this.mTaskStackListener);
    }

    @Override // com.google.android.systemui.elmyra.gates.Gate
    public final void onDeactivate() {
        TaskStackChangeListeners.INSTANCE.unregisterTaskStackListener(this.mTaskStackListener);
    }
}
