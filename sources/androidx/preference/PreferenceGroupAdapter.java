package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.preference.Preference;
import androidx.recyclerview.widget.RecyclerView;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PreferenceGroupAdapter extends RecyclerView.Adapter {
    public final PreferenceGroup mPreferenceGroup;
    public final List mPreferenceResourceDescriptors;
    public List mPreferences;
    public List mVisiblePreferences;
    public final AnonymousClass1 mSyncRunnable = new Runnable() { // from class: androidx.preference.PreferenceGroupAdapter.1
        @Override // java.lang.Runnable
        public final void run() {
            PreferenceGroupAdapter.this.updatePreferences();
        }
    };
    public final Handler mHandler = new Handler(Looper.getMainLooper());

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PreferenceResourceDescriptor {
        public final String mClassName;
        public final int mLayoutResId;
        public final int mWidgetLayoutResId;

        public PreferenceResourceDescriptor(Preference preference) {
            this.mClassName = preference.getClass().getName();
            this.mLayoutResId = preference.mLayoutResId;
            this.mWidgetLayoutResId = preference.mWidgetLayoutResId;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof PreferenceResourceDescriptor)) {
                return false;
            }
            PreferenceResourceDescriptor preferenceResourceDescriptor = (PreferenceResourceDescriptor) obj;
            return this.mLayoutResId == preferenceResourceDescriptor.mLayoutResId && this.mWidgetLayoutResId == preferenceResourceDescriptor.mWidgetLayoutResId && TextUtils.equals(this.mClassName, preferenceResourceDescriptor.mClassName);
        }

        public final int hashCode() {
            return this.mClassName.hashCode() + ((((527 + this.mLayoutResId) * 31) + this.mWidgetLayoutResId) * 31);
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.preference.PreferenceGroupAdapter$1] */
    public PreferenceGroupAdapter(PreferenceGroup preferenceGroup) {
        this.mPreferenceGroup = preferenceGroup;
        preferenceGroup.mListener = this;
        this.mPreferences = new ArrayList();
        this.mVisiblePreferences = new ArrayList();
        this.mPreferenceResourceDescriptors = new ArrayList();
        if (preferenceGroup instanceof PreferenceScreen) {
            setHasStableIds(((PreferenceScreen) preferenceGroup).mShouldUseGeneratedIds);
        } else {
            setHasStableIds(true);
        }
        updatePreferences();
    }

    public static boolean isGroupExpandable(PreferenceGroup preferenceGroup) {
        return preferenceGroup.mInitialExpandedChildrenCount != Integer.MAX_VALUE;
    }

    public final List createVisiblePreferencesList(final PreferenceGroup preferenceGroup) {
        ArrayList arrayList = new ArrayList();
        ArrayList<Preference> arrayList2 = new ArrayList();
        int preferenceCount = preferenceGroup.getPreferenceCount();
        int i = 0;
        for (int i2 = 0; i2 < preferenceCount; i2++) {
            Preference preference = preferenceGroup.getPreference(i2);
            if (preference.mVisible) {
                if (!isGroupExpandable(preferenceGroup) || i < preferenceGroup.mInitialExpandedChildrenCount) {
                    arrayList.add(preference);
                } else {
                    arrayList2.add(preference);
                }
                if (preference instanceof PreferenceGroup) {
                    PreferenceGroup preferenceGroup2 = (PreferenceGroup) preference;
                    if (preferenceGroup2 instanceof PreferenceScreen) {
                        continue;
                    } else {
                        if (isGroupExpandable(preferenceGroup) && isGroupExpandable(preferenceGroup2)) {
                            throw new IllegalStateException("Nesting an expandable group inside of another expandable group is not supported!");
                        }
                        for (Preference preference2 : createVisiblePreferencesList(preferenceGroup2)) {
                            if (!isGroupExpandable(preferenceGroup) || i < preferenceGroup.mInitialExpandedChildrenCount) {
                                arrayList.add(preference2);
                            } else {
                                arrayList2.add(preference2);
                            }
                            i++;
                        }
                    }
                } else {
                    i++;
                }
            }
        }
        if (isGroupExpandable(preferenceGroup) && i > preferenceGroup.mInitialExpandedChildrenCount) {
            Context context = preferenceGroup.mContext;
            long j = preferenceGroup.mId;
            CharSequence charSequence = null;
            ExpandButton expandButton = new ExpandButton(context, null);
            expandButton.mLayoutResId = R.layout.expand_button;
            expandButton.setIcon(AppCompatResources.getDrawable(R.drawable.ic_arrow_down_24dp, expandButton.mContext));
            expandButton.mIconResId = R.drawable.ic_arrow_down_24dp;
            expandButton.setTitle(R.string.expand_button_title);
            if (999 != expandButton.mOrder) {
                expandButton.mOrder = 999;
                PreferenceGroupAdapter preferenceGroupAdapter = expandButton.mListener;
                if (preferenceGroupAdapter != null) {
                    Handler handler = preferenceGroupAdapter.mHandler;
                    AnonymousClass1 anonymousClass1 = preferenceGroupAdapter.mSyncRunnable;
                    handler.removeCallbacks(anonymousClass1);
                    handler.post(anonymousClass1);
                }
            }
            ArrayList arrayList3 = new ArrayList();
            for (Preference preference3 : arrayList2) {
                CharSequence title = preference3.getTitle();
                boolean z = preference3 instanceof PreferenceGroup;
                if (z && !TextUtils.isEmpty(title)) {
                    arrayList3.add((PreferenceGroup) preference3);
                }
                if (arrayList3.contains(preference3.mParentGroup)) {
                    if (z) {
                        arrayList3.add((PreferenceGroup) preference3);
                    }
                } else if (!TextUtils.isEmpty(title)) {
                    charSequence = charSequence == null ? title : expandButton.mContext.getString(R.string.summary_collapsed_preference_list, charSequence, title);
                }
            }
            expandButton.setSummary(charSequence);
            expandButton.mId = j + 1000000;
            expandButton.mOnClickListener = new Preference.OnPreferenceClickListener() { // from class: androidx.preference.PreferenceGroupAdapter.3
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final void onPreferenceClick(Preference preference4) {
                    preferenceGroup.mInitialExpandedChildrenCount = Integer.MAX_VALUE;
                    PreferenceGroupAdapter preferenceGroupAdapter2 = PreferenceGroupAdapter.this;
                    Handler handler2 = preferenceGroupAdapter2.mHandler;
                    AnonymousClass1 anonymousClass12 = preferenceGroupAdapter2.mSyncRunnable;
                    handler2.removeCallbacks(anonymousClass12);
                    handler2.post(anonymousClass12);
                }
            };
            arrayList.add(expandButton);
        }
        return arrayList;
    }

    public final void flattenPreferenceGroup(List list, PreferenceGroup preferenceGroup) {
        synchronized (preferenceGroup) {
            Collections.sort(preferenceGroup.mPreferences);
        }
        int preferenceCount = preferenceGroup.getPreferenceCount();
        for (int i = 0; i < preferenceCount; i++) {
            Preference preference = preferenceGroup.getPreference(i);
            list.add(preference);
            PreferenceResourceDescriptor preferenceResourceDescriptor = new PreferenceResourceDescriptor(preference);
            if (!this.mPreferenceResourceDescriptors.contains(preferenceResourceDescriptor)) {
                this.mPreferenceResourceDescriptors.add(preferenceResourceDescriptor);
            }
            if (preference instanceof PreferenceGroup) {
                PreferenceGroup preferenceGroup2 = (PreferenceGroup) preference;
                if (!(preferenceGroup2 instanceof PreferenceScreen)) {
                    flattenPreferenceGroup(list, preferenceGroup2);
                }
            }
            preference.mListener = this;
        }
    }

    public final Preference getItem(int i) {
        if (i < 0 || i >= this.mVisiblePreferences.size()) {
            return null;
        }
        return (Preference) this.mVisiblePreferences.get(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemCount() {
        return this.mVisiblePreferences.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final long getItemId(int i) {
        if (this.mHasStableIds) {
            return getItem(i).getId();
        }
        return -1L;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemViewType(int i) {
        PreferenceResourceDescriptor preferenceResourceDescriptor = new PreferenceResourceDescriptor(getItem(i));
        int indexOf = this.mPreferenceResourceDescriptors.indexOf(preferenceResourceDescriptor);
        if (indexOf != -1) {
            return indexOf;
        }
        int size = this.mPreferenceResourceDescriptors.size();
        this.mPreferenceResourceDescriptors.add(preferenceResourceDescriptor);
        return size;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        PreferenceViewHolder preferenceViewHolder = (PreferenceViewHolder) viewHolder;
        Preference item = getItem(i);
        Drawable background = preferenceViewHolder.itemView.getBackground();
        Drawable drawable = preferenceViewHolder.mBackground;
        if (background != drawable) {
            preferenceViewHolder.itemView.setBackground(drawable);
        }
        TextView textView = (TextView) preferenceViewHolder.findViewById(android.R.id.title);
        if (textView != null && preferenceViewHolder.mTitleTextColors != null && !textView.getTextColors().equals(preferenceViewHolder.mTitleTextColors)) {
            textView.setTextColor(preferenceViewHolder.mTitleTextColors);
        }
        item.onBindViewHolder(preferenceViewHolder);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final RecyclerView.ViewHolder onCreateViewHolder(int i, ViewGroup viewGroup) {
        PreferenceResourceDescriptor preferenceResourceDescriptor = (PreferenceResourceDescriptor) ((ArrayList) this.mPreferenceResourceDescriptors).get(i);
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        TypedArray obtainStyledAttributes = viewGroup.getContext().obtainStyledAttributes((AttributeSet) null, R$styleable.BackgroundStyle);
        Drawable drawable = obtainStyledAttributes.getDrawable(0);
        if (drawable == null) {
            drawable = AppCompatResources.getDrawable(android.R.drawable.list_selector_background, viewGroup.getContext());
        }
        obtainStyledAttributes.recycle();
        View inflate = from.inflate(preferenceResourceDescriptor.mLayoutResId, viewGroup, false);
        if (inflate.getBackground() == null) {
            inflate.setBackground(drawable);
        }
        ViewGroup viewGroup2 = (ViewGroup) inflate.findViewById(android.R.id.widget_frame);
        if (viewGroup2 != null) {
            int i2 = preferenceResourceDescriptor.mWidgetLayoutResId;
            if (i2 != 0) {
                from.inflate(i2, viewGroup2);
            } else {
                viewGroup2.setVisibility(8);
            }
        }
        return new PreferenceViewHolder(inflate);
    }

    public final void updatePreferences() {
        Iterator it = this.mPreferences.iterator();
        while (it.hasNext()) {
            ((Preference) it.next()).mListener = null;
        }
        ArrayList arrayList = new ArrayList(((ArrayList) this.mPreferences).size());
        this.mPreferences = arrayList;
        PreferenceGroup preferenceGroup = this.mPreferenceGroup;
        flattenPreferenceGroup(arrayList, preferenceGroup);
        this.mVisiblePreferences = createVisiblePreferencesList(preferenceGroup);
        notifyDataSetChanged();
        Iterator it2 = this.mPreferences.iterator();
        while (it2.hasNext()) {
            ((Preference) it2.next()).getClass();
        }
    }
}
