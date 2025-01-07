package androidx.appcompat.view;

import android.view.MenuInflater;
import android.view.View;
import androidx.appcompat.view.menu.MenuBuilder;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ActionMode {
    public Object mTag;
    public boolean mTitleOptionalHint;

    public abstract void finish();

    public abstract View getCustomView();

    public abstract MenuBuilder getMenu();

    public abstract MenuInflater getMenuInflater();

    public abstract CharSequence getSubtitle();

    public abstract CharSequence getTitle();

    public abstract void invalidate();

    public abstract boolean isTitleOptional();

    public abstract void setCustomView(View view);

    public abstract void setSubtitle(int i);

    public abstract void setSubtitle(CharSequence charSequence);

    public abstract void setTitle(int i);

    public abstract void setTitle(CharSequence charSequence);

    public abstract void setTitleOptionalHint(boolean z);
}
