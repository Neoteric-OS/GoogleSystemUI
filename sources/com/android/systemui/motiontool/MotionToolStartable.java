package com.android.systemui.motiontool;

import com.android.app.motiontool.DdmHandleMotionTool;
import com.android.systemui.CoreStartable;
import org.apache.harmony.dalvik.ddmc.DdmServer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MotionToolStartable implements CoreStartable {
    public final DdmHandleMotionTool ddmHandleMotionTool;

    public MotionToolStartable(DdmHandleMotionTool ddmHandleMotionTool) {
        this.ddmHandleMotionTool = ddmHandleMotionTool;
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        DdmServer.registerHandler(DdmHandleMotionTool.CHUNK_MOTO, this.ddmHandleMotionTool);
    }
}
