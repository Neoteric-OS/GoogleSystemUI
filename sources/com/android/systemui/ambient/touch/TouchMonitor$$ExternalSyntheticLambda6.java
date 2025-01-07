package com.android.systemui.ambient.touch;

import com.android.systemui.ambient.touch.TouchMonitor;
import com.android.systemui.log.core.LogMessage;
import java.util.stream.Collectors;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class TouchMonitor$$ExternalSyntheticLambda6 implements Function1 {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ TouchMonitor$$ExternalSyntheticLambda6(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        LogMessage logMessage = (LogMessage) obj;
        switch (this.$r8$classId) {
            case 0:
                logMessage.setInt1(((TouchMonitor.TouchSessionImpl) this.f$0).hashCode());
                break;
            default:
                logMessage.setStr1((String) ((TouchMonitor) this.f$0).mActiveTouchSessions.stream().map(new TouchMonitor$$ExternalSyntheticLambda13(0)).map(new TouchMonitor$$ExternalSyntheticLambda13(1)).collect(Collectors.joining(",")));
                break;
        }
        return Unit.INSTANCE;
    }
}
