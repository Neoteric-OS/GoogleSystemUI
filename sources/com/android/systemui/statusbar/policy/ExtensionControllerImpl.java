package com.android.systemui.statusbar.policy;

import android.content.Context;
import com.android.systemui.plugins.Plugin;
import com.android.systemui.plugins.PluginListener;
import com.android.systemui.plugins.PluginManager;
import com.android.systemui.util.leak.LeakDetector;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Supplier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ExtensionControllerImpl {
    public final Context mDefaultContext;
    public final LeakDetector mLeakDetector;
    public final PluginManager mPluginManager;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ExtensionImpl {
        public Object mItem;
        public Context mPluginContext;
        public final ArrayList mProducers = new ArrayList();
        public final ArrayList mCallbacks = new ArrayList();

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Default implements Item {
            public final Supplier mSupplier;

            public Default(Supplier supplier) {
                this.mSupplier = supplier;
            }

            @Override // com.android.systemui.statusbar.policy.ExtensionControllerImpl.Item
            public final Object get() {
                return this.mSupplier.get();
            }

            @Override // com.android.systemui.statusbar.policy.ExtensionControllerImpl.Item
            public final int sortOrder() {
                return 4;
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class PluginItem implements Item, PluginListener {
            public Plugin mItem;

            public PluginItem(String str, Class cls) {
                ExtensionControllerImpl.this.mPluginManager.addPluginListener(str, this, cls);
            }

            @Override // com.android.systemui.statusbar.policy.ExtensionControllerImpl.Item
            public final Object get() {
                return this.mItem;
            }

            @Override // com.android.systemui.plugins.PluginListener
            public final void onPluginConnected(Plugin plugin, Context context) {
                ExtensionImpl extensionImpl = ExtensionImpl.this;
                extensionImpl.mPluginContext = context;
                this.mItem = plugin;
                ExtensionImpl.m887$$Nest$mnotifyChanged(extensionImpl);
            }

            @Override // com.android.systemui.plugins.PluginListener
            public final void onPluginDisconnected(Plugin plugin) {
                ExtensionImpl extensionImpl = ExtensionImpl.this;
                extensionImpl.mPluginContext = null;
                this.mItem = null;
                ExtensionImpl.m887$$Nest$mnotifyChanged(extensionImpl);
            }

            @Override // com.android.systemui.statusbar.policy.ExtensionControllerImpl.Item
            public final int sortOrder() {
                return 0;
            }
        }

        /* renamed from: -$$Nest$mnotifyChanged, reason: not valid java name */
        public static void m887$$Nest$mnotifyChanged(ExtensionImpl extensionImpl) {
            if (extensionImpl.mItem != null) {
                ExtensionControllerImpl.this.mLeakDetector.getClass();
            }
            extensionImpl.mItem = null;
            int i = 0;
            while (true) {
                if (i >= extensionImpl.mProducers.size()) {
                    break;
                }
                Object obj = ((Item) extensionImpl.mProducers.get(i)).get();
                if (obj != null) {
                    extensionImpl.mItem = obj;
                    break;
                }
                i++;
            }
            for (int i2 = 0; i2 < extensionImpl.mCallbacks.size(); i2++) {
                ((Consumer) extensionImpl.mCallbacks.get(i2)).accept(extensionImpl.mItem);
            }
        }

        public ExtensionImpl() {
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Item {
        Object get();

        int sortOrder();
    }

    public ExtensionControllerImpl(Context context, LeakDetector leakDetector, PluginManager pluginManager) {
        this.mDefaultContext = context;
        this.mLeakDetector = leakDetector;
        this.mPluginManager = pluginManager;
    }
}
