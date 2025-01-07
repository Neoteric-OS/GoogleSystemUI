package com.android.wm.shell.animation;

import android.graphics.Rect;
import androidx.dynamicanimation.animation.FloatPropertyCompat;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class FloatProperties {
    public static final FloatProperties$Companion$RECT_X$1 RECT_HEIGHT;
    public static final FloatProperties$Companion$RECT_X$1 RECT_WIDTH;
    public static final FloatProperties$Companion$RECT_X$1 RECT_X;
    public static final FloatProperties$Companion$RECT_X$1 RECT_Y;

    /* JADX WARN: Type inference failed for: r0v0, types: [com.android.wm.shell.animation.FloatProperties$Companion$RECT_X$1] */
    /* JADX WARN: Type inference failed for: r0v1, types: [com.android.wm.shell.animation.FloatProperties$Companion$RECT_X$1] */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.android.wm.shell.animation.FloatProperties$Companion$RECT_X$1] */
    /* JADX WARN: Type inference failed for: r0v3, types: [com.android.wm.shell.animation.FloatProperties$Companion$RECT_X$1] */
    static {
        final int i = 0;
        RECT_X = new FloatPropertyCompat() { // from class: com.android.wm.shell.animation.FloatProperties$Companion$RECT_X$1
            @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
            public final float getValue(Object obj) {
                switch (i) {
                    case 0:
                        if (((Rect) obj) != null) {
                            return r1.left;
                        }
                        return -3.4028235E38f;
                    case 1:
                        return ((Rect) obj).height();
                    case 2:
                        return ((Rect) obj).width();
                    default:
                        if (((Rect) obj) != null) {
                            return r1.top;
                        }
                        return -3.4028235E38f;
                }
            }

            @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
            public final void setValue(Object obj, float f) {
                switch (i) {
                    case 0:
                        Rect rect = (Rect) obj;
                        if (rect != null) {
                            rect.offsetTo((int) f, rect.top);
                            break;
                        }
                        break;
                    case 1:
                        Rect rect2 = (Rect) obj;
                        rect2.bottom = rect2.top + ((int) f);
                        break;
                    case 2:
                        Rect rect3 = (Rect) obj;
                        rect3.right = rect3.left + ((int) f);
                        break;
                    default:
                        Rect rect4 = (Rect) obj;
                        if (rect4 != null) {
                            rect4.offsetTo(rect4.left, (int) f);
                            break;
                        }
                        break;
                }
            }
        };
        final int i2 = 3;
        RECT_Y = new FloatPropertyCompat() { // from class: com.android.wm.shell.animation.FloatProperties$Companion$RECT_X$1
            @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
            public final float getValue(Object obj) {
                switch (i2) {
                    case 0:
                        if (((Rect) obj) != null) {
                            return r1.left;
                        }
                        return -3.4028235E38f;
                    case 1:
                        return ((Rect) obj).height();
                    case 2:
                        return ((Rect) obj).width();
                    default:
                        if (((Rect) obj) != null) {
                            return r1.top;
                        }
                        return -3.4028235E38f;
                }
            }

            @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
            public final void setValue(Object obj, float f) {
                switch (i2) {
                    case 0:
                        Rect rect = (Rect) obj;
                        if (rect != null) {
                            rect.offsetTo((int) f, rect.top);
                            break;
                        }
                        break;
                    case 1:
                        Rect rect2 = (Rect) obj;
                        rect2.bottom = rect2.top + ((int) f);
                        break;
                    case 2:
                        Rect rect3 = (Rect) obj;
                        rect3.right = rect3.left + ((int) f);
                        break;
                    default:
                        Rect rect4 = (Rect) obj;
                        if (rect4 != null) {
                            rect4.offsetTo(rect4.left, (int) f);
                            break;
                        }
                        break;
                }
            }
        };
        final int i3 = 2;
        RECT_WIDTH = new FloatPropertyCompat() { // from class: com.android.wm.shell.animation.FloatProperties$Companion$RECT_X$1
            @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
            public final float getValue(Object obj) {
                switch (i3) {
                    case 0:
                        if (((Rect) obj) != null) {
                            return r1.left;
                        }
                        return -3.4028235E38f;
                    case 1:
                        return ((Rect) obj).height();
                    case 2:
                        return ((Rect) obj).width();
                    default:
                        if (((Rect) obj) != null) {
                            return r1.top;
                        }
                        return -3.4028235E38f;
                }
            }

            @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
            public final void setValue(Object obj, float f) {
                switch (i3) {
                    case 0:
                        Rect rect = (Rect) obj;
                        if (rect != null) {
                            rect.offsetTo((int) f, rect.top);
                            break;
                        }
                        break;
                    case 1:
                        Rect rect2 = (Rect) obj;
                        rect2.bottom = rect2.top + ((int) f);
                        break;
                    case 2:
                        Rect rect3 = (Rect) obj;
                        rect3.right = rect3.left + ((int) f);
                        break;
                    default:
                        Rect rect4 = (Rect) obj;
                        if (rect4 != null) {
                            rect4.offsetTo(rect4.left, (int) f);
                            break;
                        }
                        break;
                }
            }
        };
        final int i4 = 1;
        RECT_HEIGHT = new FloatPropertyCompat() { // from class: com.android.wm.shell.animation.FloatProperties$Companion$RECT_X$1
            @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
            public final float getValue(Object obj) {
                switch (i4) {
                    case 0:
                        if (((Rect) obj) != null) {
                            return r1.left;
                        }
                        return -3.4028235E38f;
                    case 1:
                        return ((Rect) obj).height();
                    case 2:
                        return ((Rect) obj).width();
                    default:
                        if (((Rect) obj) != null) {
                            return r1.top;
                        }
                        return -3.4028235E38f;
                }
            }

            @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
            public final void setValue(Object obj, float f) {
                switch (i4) {
                    case 0:
                        Rect rect = (Rect) obj;
                        if (rect != null) {
                            rect.offsetTo((int) f, rect.top);
                            break;
                        }
                        break;
                    case 1:
                        Rect rect2 = (Rect) obj;
                        rect2.bottom = rect2.top + ((int) f);
                        break;
                    case 2:
                        Rect rect3 = (Rect) obj;
                        rect3.right = rect3.left + ((int) f);
                        break;
                    default:
                        Rect rect4 = (Rect) obj;
                        if (rect4 != null) {
                            rect4.offsetTo(rect4.left, (int) f);
                            break;
                        }
                        break;
                }
            }
        };
    }
}
