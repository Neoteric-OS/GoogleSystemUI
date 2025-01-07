package androidx.core.animation;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class Keyframe implements Cloneable {
    public float mFraction;
    public boolean mHasValue;
    public boolean mValueWasSetOnStart;

    /* renamed from: clone */
    public abstract Keyframe m715clone();

    public abstract Object getValue();

    public abstract void setValue(Object obj);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class FloatKeyframe extends Keyframe {
        public float mValue;

        public FloatKeyframe(float f) {
            this.mFraction = f;
        }

        @Override // androidx.core.animation.Keyframe
        public final Object getValue() {
            return Float.valueOf(this.mValue);
        }

        @Override // androidx.core.animation.Keyframe
        public final void setValue(Object obj) {
            Float f = (Float) obj;
            if (f == null || f.getClass() != Float.class) {
                return;
            }
            this.mValue = f.floatValue();
            this.mHasValue = true;
        }

        public FloatKeyframe(float f, float f2) {
            this.mFraction = f;
            this.mValue = f2;
            this.mHasValue = true;
        }

        @Override // androidx.core.animation.Keyframe
        /* renamed from: clone, reason: merged with bridge method [inline-methods] */
        public final FloatKeyframe m714clone() {
            FloatKeyframe floatKeyframe;
            if (this.mHasValue) {
                floatKeyframe = new FloatKeyframe(this.mFraction, this.mValue);
            } else {
                floatKeyframe = new FloatKeyframe(this.mFraction);
            }
            floatKeyframe.mValueWasSetOnStart = this.mValueWasSetOnStart;
            return floatKeyframe;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class IntKeyframe extends Keyframe {
        public int mValue;

        public IntKeyframe(float f) {
            this.mFraction = f;
        }

        @Override // androidx.core.animation.Keyframe
        public final Object getValue() {
            return Integer.valueOf(this.mValue);
        }

        @Override // androidx.core.animation.Keyframe
        public final void setValue(Object obj) {
            Integer num = (Integer) obj;
            if (num == null || num.getClass() != Integer.class) {
                return;
            }
            this.mValue = num.intValue();
            this.mHasValue = true;
        }

        public IntKeyframe(int i, float f) {
            this.mFraction = f;
            this.mValue = i;
            this.mHasValue = true;
        }

        @Override // androidx.core.animation.Keyframe
        /* renamed from: clone, reason: merged with bridge method [inline-methods] */
        public final IntKeyframe m715clone() {
            IntKeyframe intKeyframe;
            if (this.mHasValue) {
                intKeyframe = new IntKeyframe(this.mValue, this.mFraction);
            } else {
                intKeyframe = new IntKeyframe(this.mFraction);
            }
            intKeyframe.mValueWasSetOnStart = this.mValueWasSetOnStart;
            return intKeyframe;
        }
    }
}
