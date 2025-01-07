package com.android.systemui.fragments;

import android.app.Fragment;
import android.content.Context;
import android.util.Log;
import android.view.View;
import com.android.systemui.fragments.FragmentHostManager;
import com.android.systemui.plugins.FragmentBase;
import com.android.systemui.plugins.qs.QS;
import com.android.systemui.statusbar.policy.ExtensionControllerImpl;
import com.android.wm.shell.R;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ExtensionFragmentListener implements Consumer {
    public final ExtensionControllerImpl.ExtensionImpl mExtension;
    public final FragmentHostManager mFragmentHostManager;
    public final int mId;
    public String mOldClass;

    public ExtensionFragmentListener(FragmentService fragmentService, View view, ExtensionControllerImpl.ExtensionImpl extensionImpl) {
        FragmentHostManager fragmentHostManager = fragmentService.getFragmentHostManager(view);
        this.mFragmentHostManager = fragmentHostManager;
        this.mExtension = extensionImpl;
        this.mId = R.id.qs_frame;
        fragmentHostManager.mFragments.getFragmentManager().beginTransaction().replace(R.id.qs_frame, (Fragment) extensionImpl.mItem, QS.TAG).commit();
        extensionImpl.mItem = null;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        FragmentBase fragmentBase = (FragmentBase) obj;
        if (Fragment.class.isInstance(fragmentBase)) {
            FragmentHostManager.ExtensionFragmentManager extensionFragmentManager = this.mFragmentHostManager.mPlugins;
            int i = this.mId;
            String str = this.mOldClass;
            String name = fragmentBase.getClass().getName();
            ExtensionControllerImpl.ExtensionImpl extensionImpl = this.mExtension;
            Context context = extensionImpl.mPluginContext;
            if (context == null) {
                context = ExtensionControllerImpl.this.mDefaultContext;
            }
            if (str != null) {
                extensionFragmentManager.mExtensionLookup.remove(str);
            }
            extensionFragmentManager.mExtensionLookup.put(name, context);
            FragmentHostManager fragmentHostManager = FragmentHostManager.this;
            fragmentHostManager.mFragments.getFragmentManager().beginTransaction().replace(i, extensionFragmentManager.instantiate(context, name, null), QS.TAG).commit();
            fragmentHostManager.reloadFragments();
            this.mOldClass = fragmentBase.getClass().getName();
        } else {
            Log.e("ExtensionFragmentListener", fragmentBase.getClass().getName().concat(" must be a Fragment"));
        }
        ExtensionControllerImpl.ExtensionImpl extensionImpl2 = this.mExtension;
        if (extensionImpl2.mItem != null) {
            ExtensionControllerImpl.this.mLeakDetector.getClass();
        }
        extensionImpl2.mItem = null;
    }
}
