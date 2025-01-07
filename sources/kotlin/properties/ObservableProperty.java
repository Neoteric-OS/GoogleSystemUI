package kotlin.properties;

import kotlin.reflect.KProperty;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ObservableProperty {
    public Object value;

    public ObservableProperty(Object obj) {
        this.value = obj;
    }

    public abstract void afterChange(Object obj, Object obj2);

    public final void setValue(Object obj, KProperty kProperty, Object obj2) {
        Object obj3 = this.value;
        this.value = obj2;
        afterChange(obj3, obj2);
    }

    public final String toString() {
        return "ObservableProperty(value=" + this.value + ')';
    }
}
