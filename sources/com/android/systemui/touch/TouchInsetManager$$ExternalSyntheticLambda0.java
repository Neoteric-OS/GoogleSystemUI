package com.android.systemui.touch;

import android.graphics.Rect;
import android.graphics.Region;
import android.view.AttachedSurfaceControl;
import android.view.View;
import android.view.ViewGroup;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class TouchInsetManager$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ HashMap f$0;

    public /* synthetic */ TouchInsetManager$$ExternalSyntheticLambda0(HashMap hashMap, int i) {
        this.$r8$classId = i;
        this.f$0 = hashMap;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i = this.$r8$classId;
        HashMap hashMap = this.f$0;
        switch (i) {
            case 0:
                ((HashMap) obj).entrySet().stream().forEach(new TouchInsetManager$$ExternalSyntheticLambda0(hashMap, 2));
                break;
            case 1:
                Map.Entry entry = (Map.Entry) obj;
                AttachedSurfaceControl attachedSurfaceControl = (AttachedSurfaceControl) entry.getKey();
                if (!hashMap.containsKey(attachedSurfaceControl)) {
                    attachedSurfaceControl.setTouchableRegion(null);
                }
                ((Region) entry.getValue()).recycle();
                break;
            case 2:
                Map.Entry entry2 = (Map.Entry) obj;
                AttachedSurfaceControl attachedSurfaceControl2 = (AttachedSurfaceControl) entry2.getKey();
                if (!hashMap.containsKey(attachedSurfaceControl2)) {
                    hashMap.put(attachedSurfaceControl2, Region.obtain());
                }
                ((Region) hashMap.get(attachedSurfaceControl2)).op((Region) entry2.getValue(), Region.Op.UNION);
                break;
            default:
                View view = (View) obj;
                AttachedSurfaceControl rootSurfaceControl = view.getRootSurfaceControl();
                if (rootSurfaceControl != null) {
                    if (!hashMap.containsKey(rootSurfaceControl)) {
                        hashMap.put(rootSurfaceControl, Region.obtain());
                    }
                    Rect rect = new Rect();
                    view.getDrawingRect(rect);
                    ((ViewGroup) view.getRootView()).offsetDescendantRectToMyCoords(view, rect);
                    ((Region) hashMap.get(rootSurfaceControl)).op(rect, Region.Op.UNION);
                    break;
                }
                break;
        }
    }
}
