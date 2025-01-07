package androidx.dynamicanimation.animation;

import android.util.FloatProperty;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class FloatPropertyCompat {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: androidx.dynamicanimation.animation.FloatPropertyCompat$1, reason: invalid class name */
    public final class AnonymousClass1 extends FloatPropertyCompat {
        public final /* synthetic */ FloatProperty val$property;

        public AnonymousClass1(FloatProperty floatProperty) {
            this.val$property = floatProperty;
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public final float getValue(Object obj) {
            return ((Float) this.val$property.get(obj)).floatValue();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public final void setValue(Object obj, float f) {
            this.val$property.setValue(obj, f);
        }
    }

    public abstract float getValue(Object obj);

    public abstract void setValue(Object obj, float f);
}
