package com.android.app.motiontool;

import android.view.View;
import android.view.WindowManagerGlobal;
import com.android.app.viewcapture.SimpleViewCapture;
import com.android.app.viewcapture.ViewCapture;
import com.android.app.viewcapture.ViewCapture$$ExternalSyntheticLambda9;
import com.android.app.viewcapture.data.FrameData;
import com.android.app.viewcapture.data.MotionWindowData;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MotionToolManager {
    public static final Companion Companion = new Companion();
    public static MotionToolManager INSTANCE;
    public int traceIdCounter;
    public final WindowManagerGlobal windowManagerGlobal;
    public final SimpleViewCapture viewCapture = new SimpleViewCapture(2000, 300, ViewCapture.createAndStartNewLooperExecutor(-2, "MTViewCapture"));
    public final Map traces = new LinkedHashMap();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
    }

    public MotionToolManager(WindowManagerGlobal windowManagerGlobal) {
        this.windowManagerGlobal = windowManagerGlobal;
    }

    public final MotionWindowData getDataFromViewCapture(TraceMetadata traceMetadata) {
        WindowManagerGlobal windowManagerGlobal = this.windowManagerGlobal;
        String str = traceMetadata.windowId;
        final View rootView = windowManagerGlobal.getRootView(str);
        if (rootView == null) {
            throw new WindowNotFoundException(str);
        }
        SimpleViewCapture simpleViewCapture = this.viewCapture;
        simpleViewCapture.getClass();
        ArrayList arrayList = new ArrayList();
        Optional optional = (Optional) simpleViewCapture.getWindowData(rootView.getContext().getApplicationContext(), arrayList, new Predicate() { // from class: com.android.app.viewcapture.ViewCapture$$ExternalSyntheticLambda8
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((ViewCapture.WindowListener) obj).mRoot.equals(rootView);
            }
        }).thenApply((Function) new ViewCapture$$ExternalSyntheticLambda9(arrayList, 0)).get();
        MotionWindowData motionWindowData = optional != null ? (MotionWindowData) optional.orElse(null) : null;
        if (motionWindowData == null) {
            return (MotionWindowData) MotionWindowData.newBuilder().build();
        }
        Internal.ProtobufList frameDataList = motionWindowData.getFrameDataList();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : frameDataList) {
            if (((FrameData) obj).getTimestamp() > traceMetadata.lastPolledTime) {
                arrayList2.add(obj);
            }
        }
        GeneratedMessageLite.Builder builder = (GeneratedMessageLite.Builder) motionWindowData.dynamicMethod(GeneratedMessageLite.MethodToInvoke.NEW_BUILDER);
        if (!builder.defaultInstance.equals(motionWindowData)) {
            builder.copyOnWrite();
            GeneratedMessageLite.Builder.mergeFromInstance(builder.instance, motionWindowData);
        }
        MotionWindowData.Builder builder2 = (MotionWindowData.Builder) builder;
        builder2.copyOnWrite();
        MotionWindowData.access$500((MotionWindowData) builder2.instance);
        builder2.copyOnWrite();
        MotionWindowData.access$400((MotionWindowData) builder2.instance, arrayList2);
        return (MotionWindowData) builder2.build();
    }

    public final synchronized MotionWindowData pollTrace(int i) {
        MotionWindowData dataFromViewCapture;
        Object obj = this.traces.get(Integer.valueOf(i));
        if (obj == null) {
            throw new UnknownTraceIdException(i);
        }
        TraceMetadata traceMetadata = (TraceMetadata) obj;
        dataFromViewCapture = getDataFromViewCapture(traceMetadata);
        traceMetadata.updateLastPolledTime(dataFromViewCapture);
        return dataFromViewCapture;
    }

    public final synchronized void reset() {
        try {
            Iterator it = this.traces.values().iterator();
            while (it.hasNext()) {
                ((TraceMetadata) it.next()).stopTrace.invoke();
            }
            this.traces.clear();
            this.traceIdCounter = 0;
        } catch (Throwable th) {
            throw th;
        }
    }
}
