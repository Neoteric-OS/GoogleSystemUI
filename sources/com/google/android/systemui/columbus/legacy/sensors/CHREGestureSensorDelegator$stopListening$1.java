package com.google.android.systemui.columbus.legacy.sensors;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CHREGestureSensorDelegator$stopListening$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ CHREGestureSensorDelegator this$0;

    public /* synthetic */ CHREGestureSensorDelegator$stopListening$1(CHREGestureSensorDelegator cHREGestureSensorDelegator, int i) {
        this.$r8$classId = i;
        this.this$0 = cHREGestureSensorDelegator;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                this.this$0.gestureSensor.stopListening();
                break;
            default:
                this.this$0.gestureSensor.startListening();
                break;
        }
    }
}
