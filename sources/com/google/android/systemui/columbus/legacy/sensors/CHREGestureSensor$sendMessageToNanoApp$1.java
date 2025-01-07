package com.google.android.systemui.columbus.legacy.sensors;

import android.hardware.location.ContextHubClient;
import android.hardware.location.NanoAppMessage;
import android.util.Log;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CHREGestureSensor$sendMessageToNanoApp$1 implements Runnable {
    public final /* synthetic */ byte[] $bytes;
    public final /* synthetic */ int $messageType;
    public final /* synthetic */ Function0 $onFail;
    public final /* synthetic */ Function0 $onSuccess;
    public final /* synthetic */ CHREGestureSensor this$0;

    public CHREGestureSensor$sendMessageToNanoApp$1(CHREGestureSensor cHREGestureSensor, int i, byte[] bArr, Function0 function0, Function0 function02) {
        this.this$0 = cHREGestureSensor;
        this.$messageType = i;
        this.$bytes = bArr;
        this.$onFail = function0;
        this.$onSuccess = function02;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.this$0.contextHubClient == null) {
            Log.w("Columbus/GestureSensor", "ContextHubClient null");
            return;
        }
        NanoAppMessage createMessageToNanoApp = NanoAppMessage.createMessageToNanoApp(5147455389092024345L, this.$messageType, this.$bytes);
        ContextHubClient contextHubClient = this.this$0.contextHubClient;
        Integer valueOf = contextHubClient != null ? Integer.valueOf(contextHubClient.sendMessageToNanoApp(createMessageToNanoApp)) : null;
        if (valueOf != null && valueOf.intValue() == 0) {
            Function0 function0 = this.$onSuccess;
            if (function0 != null) {
                function0.invoke();
                return;
            }
            return;
        }
        Log.e("Columbus/GestureSensor", "Unable to send message " + this.$messageType + " to nanoapp, error code " + valueOf);
        Function0 function02 = this.$onFail;
        if (function02 != null) {
            ((CHREGestureSensor$sendScreenState$2) function02).invoke();
        }
    }
}
