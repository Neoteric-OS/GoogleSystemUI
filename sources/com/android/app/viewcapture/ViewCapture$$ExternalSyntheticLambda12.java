package com.android.app.viewcapture;

import com.android.app.viewcapture.ViewCapture;
import com.android.app.viewcapture.data.FrameData;
import com.android.app.viewcapture.data.ViewNode;
import com.android.app.viewcapture.data.WindowData;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class ViewCapture$$ExternalSyntheticLambda12 implements Function {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ViewCapture.ViewIdProvider f$0;
    public final /* synthetic */ ArrayList f$1;

    public /* synthetic */ ViewCapture$$ExternalSyntheticLambda12(ViewCapture.ViewIdProvider viewIdProvider, ArrayList arrayList, int i) {
        this.$r8$classId = i;
        this.f$0 = viewIdProvider;
        this.f$1 = arrayList;
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                return ((List) obj).stream().map(new ViewCapture$$ExternalSyntheticLambda12(this.f$0, this.f$1, 1)).toList();
            default:
                ViewCapture.ViewIdProvider viewIdProvider = this.f$0;
                ArrayList arrayList = this.f$1;
                ViewCapture.WindowListener windowListener = (ViewCapture.WindowListener) obj;
                windowListener.getClass();
                WindowData.Builder newBuilder = WindowData.newBuilder();
                String str = windowListener.name;
                newBuilder.copyOnWrite();
                WindowData.access$700((WindowData) newBuilder.instance, str);
                ViewCapture.ViewPropertyRef[] viewPropertyRefArr = windowListener.mNodesBg;
                int i = ViewCapture.this.mMemorySize;
                if (viewPropertyRefArr[i - 1] == null) {
                    i = windowListener.mFrameIndexBg + 1;
                }
                for (int i2 = i - 1; i2 >= 0; i2--) {
                    int i3 = ViewCapture.this.mMemorySize;
                    int i4 = ((windowListener.mFrameIndexBg + i3) - i2) % i3;
                    ViewNode.Builder newBuilder2 = ViewNode.newBuilder();
                    windowListener.mNodesBg[i4].toProto(viewIdProvider, arrayList, newBuilder2);
                    FrameData.Builder newBuilder3 = FrameData.newBuilder();
                    newBuilder3.copyOnWrite();
                    FrameData.access$300((FrameData) newBuilder3.instance, (ViewNode) newBuilder2.build());
                    long j = windowListener.mFrameTimesNanosBg[i4];
                    newBuilder3.copyOnWrite();
                    FrameData.access$100((FrameData) newBuilder3.instance, j);
                    newBuilder.copyOnWrite();
                    WindowData.access$200((WindowData) newBuilder.instance, (FrameData) newBuilder3.build());
                }
                return (WindowData) newBuilder.build();
        }
    }
}
