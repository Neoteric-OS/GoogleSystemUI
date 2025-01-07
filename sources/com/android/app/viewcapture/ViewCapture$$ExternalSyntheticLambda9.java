package com.android.app.viewcapture;

import com.android.app.viewcapture.data.MotionWindowData;
import com.android.app.viewcapture.data.WindowData;
import com.google.protobuf.Internal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class ViewCapture$$ExternalSyntheticLambda9 implements Function {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ArrayList f$0;

    public /* synthetic */ ViewCapture$$ExternalSyntheticLambda9(ArrayList arrayList, int i) {
        this.$r8$classId = i;
        this.f$0 = arrayList;
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        int i = this.$r8$classId;
        ArrayList arrayList = this.f$0;
        switch (i) {
            case 0:
                return ((List) obj).stream().findFirst().map(new ViewCapture$$ExternalSyntheticLambda9(arrayList, 1));
            default:
                MotionWindowData.Builder newBuilder = MotionWindowData.newBuilder();
                Internal.ProtobufList frameDataList = ((WindowData) obj).getFrameDataList();
                newBuilder.copyOnWrite();
                MotionWindowData.access$400((MotionWindowData) newBuilder.instance, frameDataList);
                List list = arrayList.stream().map(new ViewCapture$$ExternalSyntheticLambda10()).toList();
                newBuilder.copyOnWrite();
                MotionWindowData.access$900((MotionWindowData) newBuilder.instance, list);
                return (MotionWindowData) newBuilder.build();
        }
    }
}
