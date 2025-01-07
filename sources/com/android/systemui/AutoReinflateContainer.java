package com.android.systemui;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import com.android.systemui.res.R$styleable;
import com.google.android.systemui.ambientmusic.AmbientIndicationContainer;
import com.google.android.systemui.ambientmusic.AmbientIndicationContainer$$ExternalSyntheticLambda2;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class AutoReinflateContainer extends FrameLayout {
    public static final Set SUPPORTED_CHANGES = Set.of(4, 512, Integer.MIN_VALUE, 4096, 1073741824);
    public final List mInflateListeners;
    public final Configuration mLastConfig;
    public final int mLayout;

    public AutoReinflateContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mInflateListeners = new ArrayList();
        this.mLastConfig = new Configuration();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.AutoReinflateContainer);
        if (!obtainStyledAttributes.hasValue(0)) {
            throw new IllegalArgumentException("AutoReinflateContainer must contain a layout");
        }
        this.mLayout = obtainStyledAttributes.getResourceId(0, 0);
        obtainStyledAttributes.recycle();
        inflateLayout();
    }

    public final void inflateLayout() {
        removeAllViews();
        LayoutInflater.from(getContext()).inflate(this.mLayout, this);
        int size = ((ArrayList) this.mInflateListeners).size();
        for (int i = 0; i < size; i++) {
            AmbientIndicationContainer$$ExternalSyntheticLambda2 ambientIndicationContainer$$ExternalSyntheticLambda2 = (AmbientIndicationContainer$$ExternalSyntheticLambda2) ((ArrayList) this.mInflateListeners).get(i);
            getChildAt(0);
            AmbientIndicationContainer.$r8$lambda$DFan0h9JQgIimo3ogLWaY_C9MMU(ambientIndicationContainer$$ExternalSyntheticLambda2.f$0);
        }
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        int updateFrom = this.mLastConfig.updateFrom(configuration);
        Iterator it = SUPPORTED_CHANGES.iterator();
        while (it.hasNext()) {
            if ((((Integer) it.next()).intValue() & updateFrom) != 0) {
                inflateLayout();
                return;
            }
        }
    }
}
