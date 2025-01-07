package androidx.preference;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.res.TypedArrayUtils;
import androidx.core.view.ViewCompat;
import androidx.preference.DialogPreference;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.tuner.TunerActivity;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class PreferenceFragment extends Fragment implements PreferenceManager.OnPreferenceTreeClickListener, PreferenceManager.OnDisplayPreferenceDialogListener, DialogPreference.TargetFragment {
    public boolean mHavePrefs;
    public boolean mInitDone;
    public RecyclerView mList;
    public PreferenceManager mPreferenceManager;
    public Context mStyledContext;
    public final DividerDecoration mDividerDecoration = new DividerDecoration();
    public int mLayoutResId = R.layout.preference_list_fragment;
    public final AnonymousClass1 mHandler = new Handler() { // from class: androidx.preference.PreferenceFragment.1
        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            if (message.what != 1) {
                return;
            }
            PreferenceFragment preferenceFragment = PreferenceFragment.this;
            PreferenceScreen preferenceScreen = preferenceFragment.mPreferenceManager.mPreferenceScreen;
            if (preferenceScreen != null) {
                preferenceFragment.mList.setAdapter(new PreferenceGroupAdapter(preferenceScreen));
                preferenceScreen.onAttached();
            }
        }
    };
    public final AnonymousClass2 mRequestFocus = new Runnable() { // from class: androidx.preference.PreferenceFragment.2
        @Override // java.lang.Runnable
        public final void run() {
            RecyclerView recyclerView = PreferenceFragment.this.mList;
            recyclerView.focusableViewAvailable(recyclerView);
        }
    };

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DividerDecoration extends RecyclerView.ItemDecoration {
        public boolean mAllowDividerAfterLastItem = true;
        public Drawable mDivider;
        public int mDividerHeight;

        public DividerDecoration() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public final void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            if (shouldDrawDividerBelow(view, recyclerView)) {
                rect.bottom = this.mDividerHeight;
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public final void onDrawOver(Canvas canvas, RecyclerView recyclerView) {
            if (this.mDivider == null) {
                return;
            }
            int childCount = recyclerView.getChildCount();
            int width = recyclerView.getWidth();
            for (int i = 0; i < childCount; i++) {
                View childAt = recyclerView.getChildAt(i);
                if (shouldDrawDividerBelow(childAt, recyclerView)) {
                    int height = childAt.getHeight() + ((int) childAt.getY());
                    this.mDivider.setBounds(0, height, width, this.mDividerHeight + height);
                    this.mDivider.draw(canvas);
                }
            }
        }

        public final boolean shouldDrawDividerBelow(View view, RecyclerView recyclerView) {
            RecyclerView.ViewHolder childViewHolder = recyclerView.getChildViewHolder(view);
            boolean z = false;
            if (!(childViewHolder instanceof PreferenceViewHolder) || !((PreferenceViewHolder) childViewHolder).mDividerAllowedBelow) {
                return false;
            }
            boolean z2 = this.mAllowDividerAfterLastItem;
            int indexOfChild = recyclerView.indexOfChild(view);
            if (indexOfChild >= recyclerView.getChildCount() - 1) {
                return z2;
            }
            RecyclerView.ViewHolder childViewHolder2 = recyclerView.getChildViewHolder(recyclerView.getChildAt(indexOfChild + 1));
            if ((childViewHolder2 instanceof PreferenceViewHolder) && ((PreferenceViewHolder) childViewHolder2).mDividerAllowedAbove) {
                z = true;
            }
            return z;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface OnPreferenceStartFragmentCallback {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface OnPreferenceStartScreenCallback {
    }

    public final void addPreferencesFromResource(int i) {
        PreferenceManager preferenceManager = this.mPreferenceManager;
        if (preferenceManager == null) {
            throw new RuntimeException("This should be called after super.onCreate.");
        }
        Context context = this.mStyledContext;
        PreferenceScreen preferenceScreen = preferenceManager.mPreferenceScreen;
        preferenceManager.mNoCommit = true;
        PreferenceInflater preferenceInflater = new PreferenceInflater(context, preferenceManager);
        XmlResourceParser xml = context.getResources().getXml(i);
        try {
            PreferenceGroup inflate = preferenceInflater.inflate(xml, preferenceScreen);
            xml.close();
            PreferenceScreen preferenceScreen2 = (PreferenceScreen) inflate;
            preferenceScreen2.onAttachedToHierarchy(preferenceManager);
            SharedPreferences.Editor editor = preferenceManager.mEditor;
            if (editor != null) {
                editor.apply();
            }
            preferenceManager.mNoCommit = false;
            setPreferenceScreen(preferenceScreen2);
        } catch (Throwable th) {
            xml.close();
            throw th;
        }
    }

    public final Preference findPreference(CharSequence charSequence) {
        PreferenceScreen preferenceScreen;
        PreferenceManager preferenceManager = this.mPreferenceManager;
        if (preferenceManager == null || (preferenceScreen = preferenceManager.mPreferenceScreen) == null) {
            return null;
        }
        return preferenceScreen.findPreference(charSequence);
    }

    @Override // android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        TypedValue typedValue = new TypedValue();
        getActivity().getTheme().resolveAttribute(R.attr.preferenceTheme, typedValue, true);
        int i = typedValue.resourceId;
        if (i == 0) {
            i = R.style.PreferenceThemeOverlay;
        }
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getActivity(), i);
        this.mStyledContext = contextThemeWrapper;
        PreferenceManager preferenceManager = new PreferenceManager(contextThemeWrapper);
        this.mPreferenceManager = preferenceManager;
        preferenceManager.mOnNavigateToScreenListener = this;
        onCreatePreferences(getArguments() != null ? getArguments().getString("androidx.preference.PreferenceFragmentCompat.PREFERENCE_ROOT") : null);
    }

    public abstract void onCreatePreferences(String str);

    @Override // android.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        RecyclerView recyclerView;
        Context context = this.mStyledContext;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(null, R$styleable.PreferenceFragment, TypedArrayUtils.getAttr(R.attr.preferenceFragmentStyle, android.R.attr.preferenceFragmentStyle, context), 0);
        this.mLayoutResId = obtainStyledAttributes.getResourceId(0, this.mLayoutResId);
        Drawable drawable = obtainStyledAttributes.getDrawable(1);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(2, -1);
        boolean z = obtainStyledAttributes.getBoolean(3, true);
        obtainStyledAttributes.recycle();
        LayoutInflater cloneInContext = layoutInflater.cloneInContext(this.mStyledContext);
        View inflate = cloneInContext.inflate(this.mLayoutResId, viewGroup, false);
        View findViewById = inflate.findViewById(android.R.id.list_container);
        if (!(findViewById instanceof ViewGroup)) {
            throw new RuntimeException("Content has view with id attribute 'android.R.id.list_container' that is not a ViewGroup class");
        }
        ViewGroup viewGroup2 = (ViewGroup) findViewById;
        if (!this.mStyledContext.getPackageManager().hasSystemFeature("android.hardware.type.automotive") || (recyclerView = (RecyclerView) viewGroup2.findViewById(R.id.recycler_view)) == null) {
            recyclerView = (RecyclerView) cloneInContext.inflate(R.layout.preference_recyclerview, viewGroup2, false);
            getActivity();
            recyclerView.setLayoutManager(new LinearLayoutManager(1));
            PreferenceRecyclerViewAccessibilityDelegate preferenceRecyclerViewAccessibilityDelegate = new PreferenceRecyclerViewAccessibilityDelegate(recyclerView);
            recyclerView.mAccessibilityDelegate = preferenceRecyclerViewAccessibilityDelegate;
            ViewCompat.setAccessibilityDelegate(recyclerView, preferenceRecyclerViewAccessibilityDelegate);
        }
        this.mList = recyclerView;
        recyclerView.addItemDecoration(this.mDividerDecoration);
        DividerDecoration dividerDecoration = this.mDividerDecoration;
        if (drawable != null) {
            dividerDecoration.getClass();
            dividerDecoration.mDividerHeight = drawable.getIntrinsicHeight();
        } else {
            dividerDecoration.mDividerHeight = 0;
        }
        dividerDecoration.mDivider = drawable;
        PreferenceFragment.this.mList.invalidateItemDecorations();
        if (dimensionPixelSize != -1) {
            DividerDecoration dividerDecoration2 = this.mDividerDecoration;
            dividerDecoration2.mDividerHeight = dimensionPixelSize;
            PreferenceFragment.this.mList.invalidateItemDecorations();
        }
        this.mDividerDecoration.mAllowDividerAfterLastItem = z;
        if (this.mList.getParent() == null) {
            viewGroup2.addView(this.mList);
        }
        post(this.mRequestFocus);
        return inflate;
    }

    @Override // android.app.Fragment
    public final void onDestroyView() {
        PreferenceScreen preferenceScreen;
        removeCallbacks(this.mRequestFocus);
        removeMessages(1);
        if (this.mHavePrefs && (preferenceScreen = this.mPreferenceManager.mPreferenceScreen) != null) {
            preferenceScreen.onDetached();
        }
        this.mList = null;
        super.onDestroyView();
    }

    @Override // androidx.preference.PreferenceManager.OnDisplayPreferenceDialogListener
    public void onDisplayPreferenceDialog(DialogPreference dialogPreference) {
        DialogFragment multiSelectListPreferenceDialogFragment;
        getActivity();
        if (getFragmentManager().findFragmentByTag("androidx.preference.PreferenceFragment.DIALOG") != null) {
            return;
        }
        if (dialogPreference instanceof EditTextPreference) {
            String str = dialogPreference.mKey;
            multiSelectListPreferenceDialogFragment = new EditTextPreferenceDialogFragment();
            Bundle bundle = new Bundle(1);
            bundle.putString("key", str);
            multiSelectListPreferenceDialogFragment.setArguments(bundle);
        } else if (dialogPreference instanceof ListPreference) {
            String str2 = dialogPreference.mKey;
            multiSelectListPreferenceDialogFragment = new ListPreferenceDialogFragment();
            Bundle bundle2 = new Bundle(1);
            bundle2.putString("key", str2);
            multiSelectListPreferenceDialogFragment.setArguments(bundle2);
        } else {
            if (!(dialogPreference instanceof MultiSelectListPreference)) {
                throw new IllegalArgumentException("Tried to display dialog for unknown preference type. Did you forget to override onDisplayPreferenceDialog()?");
            }
            String str3 = dialogPreference.mKey;
            multiSelectListPreferenceDialogFragment = new MultiSelectListPreferenceDialogFragment();
            Bundle bundle3 = new Bundle(1);
            bundle3.putString("key", str3);
            multiSelectListPreferenceDialogFragment.setArguments(bundle3);
        }
        multiSelectListPreferenceDialogFragment.setTargetFragment(this, 0);
        multiSelectListPreferenceDialogFragment.show(getFragmentManager(), "androidx.preference.PreferenceFragment.DIALOG");
    }

    @Override // androidx.preference.PreferenceManager.OnPreferenceTreeClickListener
    public boolean onPreferenceTreeClick(Preference preference) {
        if (preference.mFragment == null || !(getActivity() instanceof OnPreferenceStartFragmentCallback)) {
            return false;
        }
        TunerActivity tunerActivity = (TunerActivity) ((OnPreferenceStartFragmentCallback) getActivity());
        tunerActivity.getClass();
        try {
            Fragment fragment = (Fragment) Class.forName(preference.mFragment).newInstance();
            Bundle bundle = new Bundle(1);
            bundle.putString("androidx.preference.PreferenceFragmentCompat.PREFERENCE_ROOT", preference.mKey);
            fragment.setArguments(bundle);
            FragmentTransaction beginTransaction = tunerActivity.getFragmentManager().beginTransaction();
            tunerActivity.setTitle(preference.getTitle());
            beginTransaction.replace(R.id.content_frame, fragment);
            beginTransaction.addToBackStack("PreferenceFragment");
            beginTransaction.commit();
            return true;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            Log.d("TunerActivity", "Problem launching fragment", e);
            return false;
        }
    }

    @Override // android.app.Fragment
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        PreferenceScreen preferenceScreen = this.mPreferenceManager.mPreferenceScreen;
        if (preferenceScreen != null) {
            Bundle bundle2 = new Bundle();
            preferenceScreen.dispatchSaveInstanceState(bundle2);
            bundle.putBundle("android:preferences", bundle2);
        }
    }

    @Override // android.app.Fragment
    public final void onStart() {
        super.onStart();
        PreferenceManager preferenceManager = this.mPreferenceManager;
        preferenceManager.mOnPreferenceTreeClickListener = this;
        preferenceManager.mOnDisplayPreferenceDialogListener = this;
    }

    @Override // android.app.Fragment
    public final void onStop() {
        super.onStop();
        PreferenceManager preferenceManager = this.mPreferenceManager;
        preferenceManager.mOnPreferenceTreeClickListener = null;
        preferenceManager.mOnDisplayPreferenceDialogListener = null;
    }

    @Override // android.app.Fragment
    public final void onViewCreated(View view, Bundle bundle) {
        PreferenceScreen preferenceScreen;
        Bundle bundle2;
        PreferenceScreen preferenceScreen2;
        super.onViewCreated(view, bundle);
        if (bundle != null && (bundle2 = bundle.getBundle("android:preferences")) != null && (preferenceScreen2 = this.mPreferenceManager.mPreferenceScreen) != null) {
            preferenceScreen2.dispatchRestoreInstanceState(bundle2);
        }
        if (this.mHavePrefs && (preferenceScreen = this.mPreferenceManager.mPreferenceScreen) != null) {
            this.mList.setAdapter(new PreferenceGroupAdapter(preferenceScreen));
            preferenceScreen.onAttached();
        }
        this.mInitDone = true;
    }

    public final void setPreferenceScreen(PreferenceScreen preferenceScreen) {
        PreferenceManager preferenceManager = this.mPreferenceManager;
        PreferenceScreen preferenceScreen2 = preferenceManager.mPreferenceScreen;
        if (preferenceScreen != preferenceScreen2) {
            if (preferenceScreen2 != null) {
                preferenceScreen2.onDetached();
            }
            preferenceManager.mPreferenceScreen = preferenceScreen;
            this.mHavePrefs = true;
            if (!this.mInitDone || hasMessages(1)) {
                return;
            }
            obtainMessage(1).sendToTarget();
        }
    }
}
