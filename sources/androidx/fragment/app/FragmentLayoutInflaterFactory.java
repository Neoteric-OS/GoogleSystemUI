package androidx.fragment.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import androidx.fragment.R$styleable;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FragmentLayoutInflaterFactory implements LayoutInflater.Factory2 {
    public final FragmentManagerImpl mFragmentManager;

    public FragmentLayoutInflaterFactory(FragmentManagerImpl fragmentManagerImpl) {
        this.mFragmentManager = fragmentManagerImpl;
    }

    @Override // android.view.LayoutInflater.Factory
    public final View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }

    @Override // android.view.LayoutInflater.Factory2
    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        boolean z;
        if (FragmentContainerView.class.getName().equals(str)) {
            return new FragmentContainerView(context, attributeSet, this.mFragmentManager);
        }
        if (!"fragment".equals(str)) {
            return null;
        }
        String attributeValue = attributeSet.getAttributeValue(null, "class");
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Fragment);
        if (attributeValue == null) {
            attributeValue = obtainStyledAttributes.getString(0);
        }
        int resourceId = obtainStyledAttributes.getResourceId(1, -1);
        String string = obtainStyledAttributes.getString(2);
        obtainStyledAttributes.recycle();
        if (attributeValue != null) {
            try {
                z = Fragment.class.isAssignableFrom(FragmentManager$3.loadClass(context.getClassLoader(), attributeValue));
            } catch (ClassNotFoundException unused) {
                z = false;
            }
            if (z) {
                int id = view != null ? view.getId() : 0;
                if (id == -1 && resourceId == -1 && string == null) {
                    throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + attributeValue);
                }
                if (resourceId != -1) {
                    this.mFragmentManager.findFragmentById();
                }
                if (string != null) {
                    FragmentStore fragmentStore = this.mFragmentManager.mFragmentStore;
                    for (int size = fragmentStore.mAdded.size() - 1; size >= 0; size--) {
                        if (fragmentStore.mAdded.get(size) != null) {
                            throw new ClassCastException();
                        }
                    }
                    Iterator it = fragmentStore.mActive.values().iterator();
                    while (it.hasNext()) {
                        if (it.next() != null) {
                            throw new ClassCastException();
                        }
                    }
                }
                if (id != -1) {
                    this.mFragmentManager.findFragmentById();
                }
                FragmentManager$3 fragmentManager$3 = this.mFragmentManager.mHostFragmentFactory;
                context.getClassLoader();
                fragmentManager$3.instantiate(attributeValue);
                throw null;
            }
        }
        return null;
    }
}
