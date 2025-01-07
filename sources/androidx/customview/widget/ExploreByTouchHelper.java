package androidx.customview.widget;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeProviderCompat;
import com.android.systemui.screenshot.scroll.CropView;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ExploreByTouchHelper extends AccessibilityDelegateCompat {
    public static final Rect INVALID_BOUNDS = new Rect(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
    public static final AnonymousClass1 NODE_ADAPTER = new AnonymousClass1();
    public static final AnonymousClass1 SPARSE_VALUES_ADAPTER = new AnonymousClass1();
    public final CropView mHost;
    public final AccessibilityManager mManager;
    public MyNodeProvider mNodeProvider;
    public final Rect mTempScreenRect = new Rect();
    public final Rect mTempParentRect = new Rect();
    public final Rect mTempVisibleRect = new Rect();
    public final int[] mTempGlobalRect = new int[2];
    public int mAccessibilityFocusedVirtualViewId = Integer.MIN_VALUE;
    public int mKeyboardFocusedVirtualViewId = Integer.MIN_VALUE;
    public int mHoveredVirtualViewId = Integer.MIN_VALUE;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: androidx.customview.widget.ExploreByTouchHelper$1, reason: invalid class name */
    public final class AnonymousClass1 {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MyNodeProvider extends AccessibilityNodeProviderCompat {
        public MyNodeProvider() {
        }

        @Override // androidx.core.view.accessibility.AccessibilityNodeProviderCompat
        public final AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int i) {
            return new AccessibilityNodeInfoCompat(AccessibilityNodeInfo.obtain(ExploreByTouchHelper.this.obtainAccessibilityNodeInfo(i).mInfo));
        }

        @Override // androidx.core.view.accessibility.AccessibilityNodeProviderCompat
        public final AccessibilityNodeInfoCompat findFocus(int i) {
            ExploreByTouchHelper exploreByTouchHelper = ExploreByTouchHelper.this;
            int i2 = i == 2 ? exploreByTouchHelper.mAccessibilityFocusedVirtualViewId : exploreByTouchHelper.mKeyboardFocusedVirtualViewId;
            if (i2 == Integer.MIN_VALUE) {
                return null;
            }
            return createAccessibilityNodeInfo(i2);
        }

        @Override // androidx.core.view.accessibility.AccessibilityNodeProviderCompat
        public final boolean performAction(int i, int i2, Bundle bundle) {
            int i3;
            ExploreByTouchHelper exploreByTouchHelper = ExploreByTouchHelper.this;
            if (i == -1) {
                return exploreByTouchHelper.mHost.performAccessibilityAction(i2, bundle);
            }
            boolean z = true;
            if (i2 == 1) {
                return exploreByTouchHelper.requestKeyboardFocusForVirtualView(i);
            }
            if (i2 == 2) {
                return exploreByTouchHelper.clearKeyboardFocusForVirtualView(i);
            }
            if (i2 == 64) {
                if (exploreByTouchHelper.mManager.isEnabled() && exploreByTouchHelper.mManager.isTouchExplorationEnabled() && (i3 = exploreByTouchHelper.mAccessibilityFocusedVirtualViewId) != i) {
                    CropView cropView = exploreByTouchHelper.mHost;
                    if (i3 != Integer.MIN_VALUE) {
                        exploreByTouchHelper.mAccessibilityFocusedVirtualViewId = Integer.MIN_VALUE;
                        cropView.invalidate();
                        exploreByTouchHelper.sendEventForVirtualView(i3, 65536);
                    }
                    exploreByTouchHelper.mAccessibilityFocusedVirtualViewId = i;
                    cropView.invalidate();
                    exploreByTouchHelper.sendEventForVirtualView(i, 32768);
                }
                z = false;
            } else {
                if (i2 != 128) {
                    return exploreByTouchHelper.onPerformActionForVirtualView(i, i2);
                }
                if (exploreByTouchHelper.mAccessibilityFocusedVirtualViewId == i) {
                    exploreByTouchHelper.mAccessibilityFocusedVirtualViewId = Integer.MIN_VALUE;
                    exploreByTouchHelper.mHost.invalidate();
                    exploreByTouchHelper.sendEventForVirtualView(i, 65536);
                }
                z = false;
            }
            return z;
        }
    }

    public ExploreByTouchHelper(CropView cropView) {
        this.mHost = cropView;
        this.mManager = (AccessibilityManager) cropView.getContext().getSystemService("accessibility");
        cropView.setFocusable(true);
        if (cropView.getImportantForAccessibility() == 0) {
            cropView.setImportantForAccessibility(1);
        }
    }

    public final boolean clearKeyboardFocusForVirtualView(int i) {
        if (this.mKeyboardFocusedVirtualViewId != i) {
            return false;
        }
        this.mKeyboardFocusedVirtualViewId = Integer.MIN_VALUE;
        sendEventForVirtualView(i, 8);
        return true;
    }

    public final AccessibilityEvent createEvent$1(int i, int i2) {
        CropView cropView = this.mHost;
        if (i == -1) {
            AccessibilityEvent obtain = AccessibilityEvent.obtain(i2);
            cropView.onInitializeAccessibilityEvent(obtain);
            return obtain;
        }
        AccessibilityEvent obtain2 = AccessibilityEvent.obtain(i2);
        AccessibilityNodeInfoCompat obtainAccessibilityNodeInfo = obtainAccessibilityNodeInfo(i);
        obtain2.getText().add(obtainAccessibilityNodeInfo.getText());
        obtain2.setContentDescription(obtainAccessibilityNodeInfo.mInfo.getContentDescription());
        obtain2.setScrollable(obtainAccessibilityNodeInfo.mInfo.isScrollable());
        obtain2.setPassword(obtainAccessibilityNodeInfo.mInfo.isPassword());
        obtain2.setEnabled(obtainAccessibilityNodeInfo.mInfo.isEnabled());
        obtain2.setChecked(obtainAccessibilityNodeInfo.mInfo.isChecked());
        onPopulateEventForVirtualView(i, obtain2);
        if (obtain2.getText().isEmpty() && obtain2.getContentDescription() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateEventForVirtualViewId()");
        }
        obtain2.setClassName(obtainAccessibilityNodeInfo.mInfo.getClassName());
        obtain2.setSource(cropView, i);
        obtain2.setPackageName(cropView.getContext().getPackageName());
        return obtain2;
    }

    public final AccessibilityNodeInfoCompat createNodeForChild(int i) {
        AccessibilityNodeInfo obtain = AccessibilityNodeInfo.obtain();
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = new AccessibilityNodeInfoCompat(obtain);
        obtain.setEnabled(true);
        obtain.setFocusable(true);
        accessibilityNodeInfoCompat.setClassName("android.view.View");
        Rect rect = INVALID_BOUNDS;
        obtain.setBoundsInParent(rect);
        accessibilityNodeInfoCompat.setBoundsInScreen(rect);
        accessibilityNodeInfoCompat.mParentVirtualDescendantId = -1;
        CropView cropView = this.mHost;
        obtain.setParent(cropView);
        onPopulateNodeForVirtualView(i, accessibilityNodeInfoCompat);
        if (accessibilityNodeInfoCompat.getText() == null && obtain.getContentDescription() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateNodeForVirtualViewId()");
        }
        obtain.getBoundsInParent(this.mTempParentRect);
        obtain.getBoundsInScreen(this.mTempScreenRect);
        if (this.mTempParentRect.equals(rect) && this.mTempScreenRect.equals(rect)) {
            throw new RuntimeException("Callbacks must set parent bounds or screen bounds in populateNodeForVirtualViewId()");
        }
        int actions = obtain.getActions();
        if ((actions & 64) != 0) {
            throw new RuntimeException("Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        if ((actions & 128) != 0) {
            throw new RuntimeException("Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        obtain.setPackageName(cropView.getContext().getPackageName());
        accessibilityNodeInfoCompat.mVirtualDescendantId = i;
        obtain.setSource(cropView, i);
        if (this.mAccessibilityFocusedVirtualViewId == i) {
            obtain.setAccessibilityFocused(true);
            accessibilityNodeInfoCompat.addAction(128);
        } else {
            obtain.setAccessibilityFocused(false);
            accessibilityNodeInfoCompat.addAction(64);
        }
        boolean z = this.mKeyboardFocusedVirtualViewId == i;
        if (z) {
            accessibilityNodeInfoCompat.addAction(2);
        } else if (obtain.isFocusable()) {
            accessibilityNodeInfoCompat.addAction(1);
        }
        obtain.setFocused(z);
        int[] iArr = this.mTempGlobalRect;
        cropView.getLocationOnScreen(iArr);
        if (this.mTempScreenRect.equals(rect)) {
            Rect rect2 = this.mTempParentRect;
            obtain.setBoundsInParent(rect2);
            Rect rect3 = new Rect();
            rect3.set(rect2);
            if (accessibilityNodeInfoCompat.mParentVirtualDescendantId != -1) {
                AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2 = new AccessibilityNodeInfoCompat(AccessibilityNodeInfo.obtain());
                Rect rect4 = new Rect();
                for (int i2 = accessibilityNodeInfoCompat.mParentVirtualDescendantId; i2 != -1; i2 = accessibilityNodeInfoCompat2.mParentVirtualDescendantId) {
                    accessibilityNodeInfoCompat2.mParentVirtualDescendantId = -1;
                    accessibilityNodeInfoCompat2.mInfo.setParent(cropView, -1);
                    accessibilityNodeInfoCompat2.mInfo.setBoundsInParent(INVALID_BOUNDS);
                    onPopulateNodeForVirtualView(i2, accessibilityNodeInfoCompat2);
                    accessibilityNodeInfoCompat2.mInfo.getBoundsInParent(rect4);
                    rect3.offset(rect4.left, rect4.top);
                }
            }
            cropView.getLocationOnScreen(iArr);
            rect3.offset(iArr[0] - cropView.getScrollX(), iArr[1] - cropView.getScrollY());
            accessibilityNodeInfoCompat.setBoundsInScreen(rect3);
            accessibilityNodeInfoCompat.mInfo.getBoundsInScreen(this.mTempScreenRect);
        }
        if (cropView.getLocalVisibleRect(this.mTempVisibleRect)) {
            this.mTempVisibleRect.offset(iArr[0] - cropView.getScrollX(), iArr[1] - cropView.getScrollY());
            if (this.mTempScreenRect.intersect(this.mTempVisibleRect)) {
                accessibilityNodeInfoCompat.setBoundsInScreen(this.mTempScreenRect);
                Rect rect5 = this.mTempScreenRect;
                if (rect5 != null && !rect5.isEmpty() && cropView.getWindowVisibility() == 0) {
                    Object parent = cropView.getParent();
                    while (true) {
                        if (parent instanceof View) {
                            View view = (View) parent;
                            if (view.getAlpha() <= 0.0f || view.getVisibility() != 0) {
                                break;
                            }
                            parent = view.getParent();
                        } else if (parent != null) {
                            accessibilityNodeInfoCompat.mInfo.setVisibleToUser(true);
                        }
                    }
                }
            }
        }
        return accessibilityNodeInfoCompat;
    }

    public final boolean dispatchHoverEvent(MotionEvent motionEvent) {
        int i;
        if (!this.mManager.isEnabled() || !this.mManager.isTouchExplorationEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 7 || action == 9) {
            int virtualViewAt = getVirtualViewAt(motionEvent.getX(), motionEvent.getY());
            int i2 = this.mHoveredVirtualViewId;
            if (i2 != virtualViewAt) {
                this.mHoveredVirtualViewId = virtualViewAt;
                sendEventForVirtualView(virtualViewAt, 128);
                sendEventForVirtualView(i2, 256);
            }
            return virtualViewAt != Integer.MIN_VALUE;
        }
        if (action != 10 || (i = this.mHoveredVirtualViewId) == Integer.MIN_VALUE) {
            return false;
        }
        if (i != Integer.MIN_VALUE) {
            this.mHoveredVirtualViewId = Integer.MIN_VALUE;
            sendEventForVirtualView(i, 256);
        }
        return true;
    }

    @Override // androidx.core.view.AccessibilityDelegateCompat
    public final AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View view) {
        if (this.mNodeProvider == null) {
            this.mNodeProvider = new MyNodeProvider();
        }
        return this.mNodeProvider;
    }

    public abstract int getVirtualViewAt(float f, float f2);

    public abstract void getVisibleVirtualViews(List list);

    /* JADX WARN: Removed duplicated region for block: B:26:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00f2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean moveFocus(int r20, android.graphics.Rect r21) {
        /*
            Method dump skipped, instructions count: 491
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.customview.widget.ExploreByTouchHelper.moveFocus(int, android.graphics.Rect):boolean");
    }

    public final AccessibilityNodeInfoCompat obtainAccessibilityNodeInfo(int i) {
        if (i != -1) {
            return createNodeForChild(i);
        }
        CropView cropView = this.mHost;
        AccessibilityNodeInfo obtain = AccessibilityNodeInfo.obtain(cropView);
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = new AccessibilityNodeInfoCompat(obtain);
        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
        cropView.onInitializeAccessibilityNodeInfo(obtain);
        ArrayList arrayList = new ArrayList();
        getVisibleVirtualViews(arrayList);
        if (obtain.getChildCount() > 0 && arrayList.size() > 0) {
            throw new RuntimeException("Views cannot have both real and virtual children");
        }
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            accessibilityNodeInfoCompat.mInfo.addChild(cropView, ((Integer) arrayList.get(i2)).intValue());
        }
        return accessibilityNodeInfoCompat;
    }

    @Override // androidx.core.view.AccessibilityDelegateCompat
    public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        this.mOriginalDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat.mInfo);
    }

    public abstract boolean onPerformActionForVirtualView(int i, int i2);

    public abstract void onPopulateEventForVirtualView(int i, AccessibilityEvent accessibilityEvent);

    public abstract void onPopulateNodeForVirtualView(int i, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat);

    public final boolean requestKeyboardFocusForVirtualView(int i) {
        int i2;
        CropView cropView = this.mHost;
        if ((!cropView.isFocused() && !cropView.requestFocus()) || (i2 = this.mKeyboardFocusedVirtualViewId) == i) {
            return false;
        }
        if (i2 != Integer.MIN_VALUE) {
            clearKeyboardFocusForVirtualView(i2);
        }
        if (i == Integer.MIN_VALUE) {
            return false;
        }
        this.mKeyboardFocusedVirtualViewId = i;
        sendEventForVirtualView(i, 8);
        return true;
    }

    public final void sendEventForVirtualView(int i, int i2) {
        View view;
        ViewParent parent;
        if (i == Integer.MIN_VALUE || !this.mManager.isEnabled() || (parent = (view = this.mHost).getParent()) == null) {
            return;
        }
        parent.requestSendAccessibilityEvent(view, createEvent$1(i, i2));
    }
}
