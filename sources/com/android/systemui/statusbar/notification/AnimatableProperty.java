package com.android.systemui.statusbar.notification;

import android.util.FloatProperty;
import android.util.Property;
import android.view.View;
import com.android.wm.shell.R;
import java.util.function.BiConsumer;
import java.util.function.Function;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class AnimatableProperty {
    public static final AnonymousClass7 ALPHA = null;
    public static final AnonymousClass7 SCALE_X = null;
    public static final AnonymousClass7 SCALE_Y = null;
    public static final AnonymousClass7 TRANSLATION_X;
    public static final AnonymousClass7 Y;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.AnimatableProperty$5, reason: invalid class name */
    public final class AnonymousClass5 extends FloatProperty {
        public final /* synthetic */ Function val$getter;
        public final /* synthetic */ BiConsumer val$setter;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass5(String str, Function function, BiConsumer biConsumer) {
            super(str);
            this.val$getter = function;
            this.val$setter = biConsumer;
        }

        @Override // android.util.Property
        public final Float get(Object obj) {
            return (Float) this.val$getter.apply((View) obj);
        }

        @Override // android.util.FloatProperty
        public final void setValue(Object obj, float f) {
            this.val$setter.accept((View) obj, Float.valueOf(f));
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.AnimatableProperty$6, reason: invalid class name */
    public final class AnonymousClass6 extends AnimatableProperty {
        public final /* synthetic */ int val$animatorTag;
        public final /* synthetic */ int val$endValueTag;
        public final /* synthetic */ AnonymousClass5 val$property;
        public final /* synthetic */ int val$startValueTag;

        public AnonymousClass6(int i, int i2, int i3, AnonymousClass5 anonymousClass5) {
            this.val$startValueTag = i;
            this.val$endValueTag = i2;
            this.val$animatorTag = i3;
            this.val$property = anonymousClass5;
        }

        @Override // com.android.systemui.statusbar.notification.AnimatableProperty
        public final int getAnimationEndTag() {
            return this.val$endValueTag;
        }

        @Override // com.android.systemui.statusbar.notification.AnimatableProperty
        public final int getAnimationStartTag() {
            return this.val$startValueTag;
        }

        @Override // com.android.systemui.statusbar.notification.AnimatableProperty
        public final int getAnimatorTag() {
            return this.val$animatorTag;
        }

        @Override // com.android.systemui.statusbar.notification.AnimatableProperty
        public final Property getProperty() {
            return this.val$property;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.AnimatableProperty$7, reason: invalid class name */
    public final class AnonymousClass7 extends AnimatableProperty {
        public final /* synthetic */ int val$animatorTag;
        public final /* synthetic */ int val$endValueTag;
        public final /* synthetic */ Property val$property;
        public final /* synthetic */ int val$startValueTag;

        public AnonymousClass7(int i, int i2, int i3, Property property) {
            this.val$startValueTag = i;
            this.val$endValueTag = i2;
            this.val$animatorTag = i3;
            this.val$property = property;
        }

        @Override // com.android.systemui.statusbar.notification.AnimatableProperty
        public final int getAnimationEndTag() {
            return this.val$endValueTag;
        }

        @Override // com.android.systemui.statusbar.notification.AnimatableProperty
        public final int getAnimationStartTag() {
            return this.val$startValueTag;
        }

        @Override // com.android.systemui.statusbar.notification.AnimatableProperty
        public final int getAnimatorTag() {
            return this.val$animatorTag;
        }

        @Override // com.android.systemui.statusbar.notification.AnimatableProperty
        public final Property getProperty() {
            return this.val$property;
        }
    }

    static {
        Property property = View.X;
        Y = new AnonymousClass7(R.id.y_animator_tag_start_value, R.id.y_animator_tag_end_value, R.id.y_animator_tag, View.Y);
        TRANSLATION_X = new AnonymousClass7(R.id.x_animator_tag_start_value, R.id.x_animator_tag_end_value, R.id.x_animator_tag, View.TRANSLATION_X);
        new AnonymousClass7(R.id.scale_x_animator_start_value_tag, R.id.scale_x_animator_end_value_tag, R.id.scale_x_animator_tag, View.SCALE_X);
        new AnonymousClass7(R.id.scale_y_animator_start_value_tag, R.id.scale_y_animator_end_value_tag, R.id.scale_y_animator_tag, View.SCALE_Y);
        new AnonymousClass7(R.id.alpha_animator_start_value_tag, R.id.alpha_animator_end_value_tag, R.id.alpha_animator_tag, View.ALPHA);
        final int i = 0;
        new FloatProperty("ViewAbsoluteX") { // from class: com.android.systemui.statusbar.notification.AnimatableProperty.1
            @Override // android.util.Property
            public final Float get(Object obj) {
                switch (i) {
                    case 0:
                        View view = (View) obj;
                        Object tag = view.getTag(R.id.absolute_x_current_value);
                        return tag instanceof Float ? (Float) tag : (Float) View.X.get(view);
                    case 1:
                        View view2 = (View) obj;
                        Object tag2 = view2.getTag(R.id.absolute_y_current_value);
                        return tag2 instanceof Float ? (Float) tag2 : (Float) View.Y.get(view2);
                    case 2:
                        Object tag3 = ((View) obj).getTag(R.id.view_width_current_value);
                        return tag3 instanceof Float ? (Float) tag3 : Float.valueOf(r2.getWidth());
                    default:
                        Object tag4 = ((View) obj).getTag(R.id.view_height_current_value);
                        return tag4 instanceof Float ? (Float) tag4 : Float.valueOf(r2.getHeight());
                }
            }

            @Override // android.util.FloatProperty
            public final void setValue(Object obj, float f) {
                switch (i) {
                    case 0:
                        View view = (View) obj;
                        view.setTag(R.id.absolute_x_current_value, Float.valueOf(f));
                        View.X.set(view, Float.valueOf(f));
                        break;
                    case 1:
                        View view2 = (View) obj;
                        view2.setTag(R.id.absolute_y_current_value, Float.valueOf(f));
                        View.Y.set(view2, Float.valueOf(f));
                        break;
                    case 2:
                        View view3 = (View) obj;
                        view3.setTag(R.id.view_width_current_value, Float.valueOf(f));
                        view3.setRight((int) (view3.getLeft() + f));
                        break;
                    default:
                        View view4 = (View) obj;
                        view4.setTag(R.id.view_height_current_value, Float.valueOf(f));
                        view4.setBottom((int) (view4.getTop() + f));
                        break;
                }
            }
        };
        final int i2 = 1;
        new FloatProperty("ViewAbsoluteY") { // from class: com.android.systemui.statusbar.notification.AnimatableProperty.1
            @Override // android.util.Property
            public final Float get(Object obj) {
                switch (i2) {
                    case 0:
                        View view = (View) obj;
                        Object tag = view.getTag(R.id.absolute_x_current_value);
                        return tag instanceof Float ? (Float) tag : (Float) View.X.get(view);
                    case 1:
                        View view2 = (View) obj;
                        Object tag2 = view2.getTag(R.id.absolute_y_current_value);
                        return tag2 instanceof Float ? (Float) tag2 : (Float) View.Y.get(view2);
                    case 2:
                        Object tag3 = ((View) obj).getTag(R.id.view_width_current_value);
                        return tag3 instanceof Float ? (Float) tag3 : Float.valueOf(r2.getWidth());
                    default:
                        Object tag4 = ((View) obj).getTag(R.id.view_height_current_value);
                        return tag4 instanceof Float ? (Float) tag4 : Float.valueOf(r2.getHeight());
                }
            }

            @Override // android.util.FloatProperty
            public final void setValue(Object obj, float f) {
                switch (i2) {
                    case 0:
                        View view = (View) obj;
                        view.setTag(R.id.absolute_x_current_value, Float.valueOf(f));
                        View.X.set(view, Float.valueOf(f));
                        break;
                    case 1:
                        View view2 = (View) obj;
                        view2.setTag(R.id.absolute_y_current_value, Float.valueOf(f));
                        View.Y.set(view2, Float.valueOf(f));
                        break;
                    case 2:
                        View view3 = (View) obj;
                        view3.setTag(R.id.view_width_current_value, Float.valueOf(f));
                        view3.setRight((int) (view3.getLeft() + f));
                        break;
                    default:
                        View view4 = (View) obj;
                        view4.setTag(R.id.view_height_current_value, Float.valueOf(f));
                        view4.setBottom((int) (view4.getTop() + f));
                        break;
                }
            }
        };
        final int i3 = 2;
        new FloatProperty("ViewWidth") { // from class: com.android.systemui.statusbar.notification.AnimatableProperty.1
            @Override // android.util.Property
            public final Float get(Object obj) {
                switch (i3) {
                    case 0:
                        View view = (View) obj;
                        Object tag = view.getTag(R.id.absolute_x_current_value);
                        return tag instanceof Float ? (Float) tag : (Float) View.X.get(view);
                    case 1:
                        View view2 = (View) obj;
                        Object tag2 = view2.getTag(R.id.absolute_y_current_value);
                        return tag2 instanceof Float ? (Float) tag2 : (Float) View.Y.get(view2);
                    case 2:
                        Object tag3 = ((View) obj).getTag(R.id.view_width_current_value);
                        return tag3 instanceof Float ? (Float) tag3 : Float.valueOf(r2.getWidth());
                    default:
                        Object tag4 = ((View) obj).getTag(R.id.view_height_current_value);
                        return tag4 instanceof Float ? (Float) tag4 : Float.valueOf(r2.getHeight());
                }
            }

            @Override // android.util.FloatProperty
            public final void setValue(Object obj, float f) {
                switch (i3) {
                    case 0:
                        View view = (View) obj;
                        view.setTag(R.id.absolute_x_current_value, Float.valueOf(f));
                        View.X.set(view, Float.valueOf(f));
                        break;
                    case 1:
                        View view2 = (View) obj;
                        view2.setTag(R.id.absolute_y_current_value, Float.valueOf(f));
                        View.Y.set(view2, Float.valueOf(f));
                        break;
                    case 2:
                        View view3 = (View) obj;
                        view3.setTag(R.id.view_width_current_value, Float.valueOf(f));
                        view3.setRight((int) (view3.getLeft() + f));
                        break;
                    default:
                        View view4 = (View) obj;
                        view4.setTag(R.id.view_height_current_value, Float.valueOf(f));
                        view4.setBottom((int) (view4.getTop() + f));
                        break;
                }
            }
        };
        final int i4 = 3;
        new FloatProperty("ViewHeight") { // from class: com.android.systemui.statusbar.notification.AnimatableProperty.1
            @Override // android.util.Property
            public final Float get(Object obj) {
                switch (i4) {
                    case 0:
                        View view = (View) obj;
                        Object tag = view.getTag(R.id.absolute_x_current_value);
                        return tag instanceof Float ? (Float) tag : (Float) View.X.get(view);
                    case 1:
                        View view2 = (View) obj;
                        Object tag2 = view2.getTag(R.id.absolute_y_current_value);
                        return tag2 instanceof Float ? (Float) tag2 : (Float) View.Y.get(view2);
                    case 2:
                        Object tag3 = ((View) obj).getTag(R.id.view_width_current_value);
                        return tag3 instanceof Float ? (Float) tag3 : Float.valueOf(r2.getWidth());
                    default:
                        Object tag4 = ((View) obj).getTag(R.id.view_height_current_value);
                        return tag4 instanceof Float ? (Float) tag4 : Float.valueOf(r2.getHeight());
                }
            }

            @Override // android.util.FloatProperty
            public final void setValue(Object obj, float f) {
                switch (i4) {
                    case 0:
                        View view = (View) obj;
                        view.setTag(R.id.absolute_x_current_value, Float.valueOf(f));
                        View.X.set(view, Float.valueOf(f));
                        break;
                    case 1:
                        View view2 = (View) obj;
                        view2.setTag(R.id.absolute_y_current_value, Float.valueOf(f));
                        View.Y.set(view2, Float.valueOf(f));
                        break;
                    case 2:
                        View view3 = (View) obj;
                        view3.setTag(R.id.view_width_current_value, Float.valueOf(f));
                        view3.setRight((int) (view3.getLeft() + f));
                        break;
                    default:
                        View view4 = (View) obj;
                        view4.setTag(R.id.view_height_current_value, Float.valueOf(f));
                        view4.setBottom((int) (view4.getTop() + f));
                        break;
                }
            }
        };
    }

    public abstract int getAnimationEndTag();

    public abstract int getAnimationStartTag();

    public abstract int getAnimatorTag();

    public abstract Property getProperty();
}
