package androidx.slice.widget;

import android.graphics.Rect;
import android.view.View;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LocationBasedViewTracker implements Runnable, View.OnLayoutChangeListener {
    public final Rect mFocusRect;
    public final SliceView mParent;
    public final AnonymousClass1 mSelectionLogic;
    public static final AnonymousClass1 INPUT_FOCUS = new AnonymousClass1(0);
    public static final AnonymousClass1 A11Y_FOCUS = new AnonymousClass1(1);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: androidx.slice.widget.LocationBasedViewTracker$1, reason: invalid class name */
    public final class AnonymousClass1 {
        public final /* synthetic */ int $r8$classId;

        public /* synthetic */ AnonymousClass1(int i) {
            this.$r8$classId = i;
        }
    }

    public LocationBasedViewTracker(SliceView sliceView, View view, AnonymousClass1 anonymousClass1) {
        Rect rect = new Rect();
        this.mFocusRect = rect;
        this.mParent = sliceView;
        this.mSelectionLogic = anonymousClass1;
        view.getDrawingRect(rect);
        sliceView.offsetDescendantRectToMyCoords(view, rect);
        sliceView.addOnLayoutChangeListener(this);
        sliceView.requestLayout();
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.mParent.removeOnLayoutChangeListener(this);
        this.mParent.post(this);
    }

    @Override // java.lang.Runnable
    public final void run() {
        ArrayList<View> arrayList = new ArrayList<>();
        this.mParent.addFocusables(arrayList, 2, 0);
        Rect rect = new Rect();
        Iterator<View> it = arrayList.iterator();
        int i = Integer.MAX_VALUE;
        View view = null;
        while (it.hasNext()) {
            View next = it.next();
            next.getDrawingRect(rect);
            this.mParent.offsetDescendantRectToMyCoords(next, rect);
            if (this.mFocusRect.intersect(rect)) {
                int abs = Math.abs(this.mFocusRect.bottom - rect.bottom) + Math.abs(this.mFocusRect.top - rect.top) + Math.abs(this.mFocusRect.right - rect.right) + Math.abs(this.mFocusRect.left - rect.left);
                if (i > abs) {
                    view = next;
                    i = abs;
                }
            }
        }
        if (view != null) {
            switch (this.mSelectionLogic.$r8$classId) {
                case 0:
                    view.requestFocus();
                    break;
                default:
                    view.performAccessibilityAction(64, null);
                    break;
            }
        }
    }
}
