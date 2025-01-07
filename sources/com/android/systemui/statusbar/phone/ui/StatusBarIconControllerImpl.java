package com.android.systemui.statusbar.phone.ui;

import android.R;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Log;
import android.view.ViewGroup;
import com.android.internal.statusbar.StatusBarIcon;
import com.android.systemui.Dumpable;
import com.android.systemui.demomode.DemoMode;
import com.android.systemui.demomode.DemoModeController;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.StatusIconDisplayable;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.phone.StatusBarIconHolder;
import com.android.systemui.statusbar.phone.ui.StatusBarIconList;
import com.android.systemui.statusbar.pipeline.StatusBarPipelineFlags;
import com.android.systemui.statusbar.pipeline.icons.shared.BindableIconsRegistryImpl;
import com.android.systemui.statusbar.pipeline.satellite.ui.DeviceBasedSatelliteBindableIcon;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.tuner.TunerService;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StatusBarIconControllerImpl implements TunerService.Tunable, ConfigurationController.ConfigurationListener, Dumpable, StatusBarIconController, DemoMode {
    protected static final String EXTERNAL_SLOT_SUFFIX = "__external";
    public final AnonymousClass1 mCommandQueueCallbacks;
    public final Context mContext;
    public final ArrayList mIconGroups = new ArrayList();
    public final ArraySet mIconHideList = new ArraySet();
    public final StatusBarIconList mStatusBarIconList;
    public final StatusBarPipelineFlags mStatusBarPipelineFlags;

    public StatusBarIconControllerImpl(Context context, CommandQueue commandQueue, DemoModeController demoModeController, ConfigurationController configurationController, TunerService tunerService, DumpManager dumpManager, StatusBarIconList statusBarIconList, StatusBarPipelineFlags statusBarPipelineFlags, BindableIconsRegistryImpl bindableIconsRegistryImpl) {
        CommandQueue.Callbacks callbacks = new CommandQueue.Callbacks() { // from class: com.android.systemui.statusbar.phone.ui.StatusBarIconControllerImpl.1
            @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
            public final void removeIcon(String str) {
                StatusBarIconControllerImpl statusBarIconControllerImpl = StatusBarIconControllerImpl.this;
                statusBarIconControllerImpl.getClass();
                if (!str.endsWith(StatusBarIconControllerImpl.EXTERNAL_SLOT_SUFFIX)) {
                    str = str.concat(StatusBarIconControllerImpl.EXTERNAL_SLOT_SUFFIX);
                }
                statusBarIconControllerImpl.removeAllIconsForSlot(str, false);
            }

            @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
            public final void setIcon(String str, StatusBarIcon statusBarIcon) {
                StatusBarIconControllerImpl statusBarIconControllerImpl = StatusBarIconControllerImpl.this;
                statusBarIconControllerImpl.getClass();
                if (statusBarIcon == null) {
                    if (!str.endsWith(StatusBarIconControllerImpl.EXTERNAL_SLOT_SUFFIX)) {
                        str = str.concat(StatusBarIconControllerImpl.EXTERNAL_SLOT_SUFFIX);
                    }
                    statusBarIconControllerImpl.removeAllIconsForSlot(str, false);
                } else {
                    if (!str.endsWith(StatusBarIconControllerImpl.EXTERNAL_SLOT_SUFFIX)) {
                        str = str.concat(StatusBarIconControllerImpl.EXTERNAL_SLOT_SUFFIX);
                    }
                    StatusBarIconHolder statusBarIconHolder = new StatusBarIconHolder();
                    statusBarIconHolder.icon = statusBarIcon;
                    statusBarIconControllerImpl.setIcon(str, statusBarIconHolder);
                }
            }
        };
        this.mStatusBarIconList = statusBarIconList;
        this.mContext = context;
        this.mStatusBarPipelineFlags = statusBarPipelineFlags;
        ((ConfigurationControllerImpl) configurationController).addCallback(this);
        commandQueue.addCallback(callbacks);
        tunerService.addTunable(this, "icon_blacklist");
        demoModeController.addCallback((DemoMode) this);
        dumpManager.getClass();
        DumpManager.registerDumpable$default(dumpManager, "StatusBarIconControllerImpl", this);
        for (DeviceBasedSatelliteBindableIcon deviceBasedSatelliteBindableIcon : bindableIconsRegistryImpl.bindableIcons) {
            if (deviceBasedSatelliteBindableIcon.shouldBindIcon) {
                StatusBarIconList statusBarIconList2 = this.mStatusBarIconList;
                String str = deviceBasedSatelliteBindableIcon.slot;
                if (statusBarIconList2.getIconHolder(0, str) == null) {
                    setIcon(str, new StatusBarIconHolder.BindableIconHolder(deviceBasedSatelliteBindableIcon.initializer, str));
                } else {
                    Log.e("StatusBarIconController", "addBindableIcon called, but icon has already been added. Ignoring");
                }
            }
        }
    }

    public final void addIconGroup(IconManager iconManager) {
        Iterator it = this.mIconGroups.iterator();
        while (it.hasNext()) {
            if (((IconManager) it.next()).mGroup == iconManager.mGroup) {
                Log.e("StatusBarIconController", "Adding new IconManager for the same ViewGroup. This could cause unexpected results.");
            }
        }
        iconManager.mController = this;
        iconManager.mIconSize = iconManager.mContext.getResources().getDimensionPixelSize(R.dimen.text_edit_floating_toolbar_elevation);
        this.mIconGroups.add(iconManager);
        StatusBarIconList statusBarIconList = this.mStatusBarIconList;
        List list = statusBarIconList.mViewOnlySlots;
        for (int i = 0; i < list.size(); i++) {
            StatusBarIconList.Slot slot = (StatusBarIconList.Slot) list.get(i);
            List<StatusBarIconHolder> holderListInViewOrder = slot.getHolderListInViewOrder();
            ArraySet arraySet = this.mIconHideList;
            String str = slot.mName;
            boolean contains = arraySet.contains(str);
            for (StatusBarIconHolder statusBarIconHolder : holderListInViewOrder) {
                iconManager.onIconAdded(statusBarIconList.getViewIndex(statusBarIconHolder.tag, str), str, contains, statusBarIconHolder);
            }
        }
    }

    @Override // com.android.systemui.demomode.DemoMode
    public final List demoCommands() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("status");
        return arrayList;
    }

    @Override // com.android.systemui.demomode.DemoModeCommandReceiver
    public final void dispatchDemoCommand(Bundle bundle, String str) {
        Iterator it = this.mIconGroups.iterator();
        while (it.hasNext()) {
            IconManager iconManager = (IconManager) it.next();
            if (iconManager.mDemoable) {
                iconManager.dispatchDemoCommand(bundle, str);
            }
        }
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        int i;
        printWriter.println("StatusBarIconController state:");
        Iterator it = this.mIconGroups.iterator();
        while (true) {
            i = 0;
            if (!it.hasNext()) {
                break;
            }
            IconManager iconManager = (IconManager) it.next();
            if (iconManager.mShouldLog) {
                ViewGroup viewGroup = iconManager.mGroup;
                int childCount = viewGroup.getChildCount();
                printWriter.println("  icon views: " + childCount);
                while (i < childCount) {
                    printWriter.println("    [" + i + "] icon=" + ((StatusIconDisplayable) viewGroup.getChildAt(i)));
                    i++;
                }
            }
        }
        StatusBarIconList statusBarIconList = this.mStatusBarIconList;
        statusBarIconList.getClass();
        printWriter.println("StatusBarIconList state:");
        int size = statusBarIconList.mSlots.size();
        printWriter.println("  icon slots: " + size);
        while (i < size) {
            printWriter.printf("    %2d:%s\n", Integer.valueOf(i), ((StatusBarIconList.Slot) statusBarIconList.mSlots.get(i)).toString());
            i++;
        }
    }

    public final void handleSet(String str, final StatusBarIconHolder statusBarIconHolder) {
        final int viewIndex = this.mStatusBarIconList.getViewIndex(statusBarIconHolder.tag, str);
        this.mIconGroups.forEach(new Consumer() { // from class: com.android.systemui.statusbar.phone.ui.StatusBarIconControllerImpl$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                int i = viewIndex;
                StatusBarIconHolder statusBarIconHolder2 = statusBarIconHolder;
                IconManager iconManager = (IconManager) obj;
                iconManager.getClass();
                if (statusBarIconHolder2.getType() != 0) {
                    return;
                }
                iconManager.onSetIcon(i, statusBarIconHolder2.icon);
            }
        });
    }

    @Override // com.android.systemui.demomode.DemoModeCommandReceiver
    public final void onDemoModeFinished() {
        Iterator it = this.mIconGroups.iterator();
        while (it.hasNext()) {
            IconManager iconManager = (IconManager) it.next();
            if (iconManager.mDemoable) {
                iconManager.onDemoModeFinished();
            }
        }
    }

    @Override // com.android.systemui.demomode.DemoModeCommandReceiver
    public final void onDemoModeStarted() {
        Iterator it = this.mIconGroups.iterator();
        while (it.hasNext()) {
            IconManager iconManager = (IconManager) it.next();
            if (iconManager.mDemoable) {
                iconManager.onDemoModeStarted();
            }
        }
    }

    @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
    public final void onDensityOrFontScaleChanged() {
        for (int size = this.mIconGroups.size() - 1; size >= 0; size--) {
            IconManager iconManager = (IconManager) this.mIconGroups.get(size);
            iconManager.destroy();
            this.mIconGroups.remove(iconManager);
            addIconGroup(iconManager);
        }
    }

    @Override // com.android.systemui.tuner.TunerService.Tunable
    public final void onTuningChanged(String str, String str2) {
        int i;
        if ("icon_blacklist".equals(str)) {
            this.mIconHideList.clear();
            this.mIconHideList.addAll(StatusBarIconController.getIconHideList(this.mContext, str2));
            List list = this.mStatusBarIconList.mViewOnlySlots;
            ArrayMap arrayMap = new ArrayMap();
            int size = list.size();
            while (true) {
                size--;
                if (size < 0) {
                    break;
                }
                StatusBarIconList.Slot slot = (StatusBarIconList.Slot) list.get(size);
                slot.getClass();
                ArrayList arrayList = new ArrayList();
                StatusBarIconHolder statusBarIconHolder = slot.mHolder;
                if (statusBarIconHolder != null) {
                    arrayList.add(statusBarIconHolder);
                }
                ArrayList arrayList2 = slot.mSubSlots;
                if (arrayList2 != null) {
                    arrayList.addAll(arrayList2);
                }
                arrayMap.put(slot, arrayList);
                removeAllIconsForSlot(slot.mName, false);
            }
            for (i = 0; i < list.size(); i++) {
                StatusBarIconList.Slot slot2 = (StatusBarIconList.Slot) list.get(i);
                List list2 = (List) arrayMap.get(slot2);
                if (list2 != null) {
                    Iterator it = list2.iterator();
                    while (it.hasNext()) {
                        setIcon(slot2.mName, (StatusBarIconHolder) it.next());
                    }
                }
            }
        }
    }

    public final void removeAllIconsForSlot(String str, boolean z) {
        if (!z) {
            StatusBarPipelineFlags statusBarPipelineFlags = this.mStatusBarPipelineFlags;
            if (str.equals(statusBarPipelineFlags.wifiSlot) || str.equals(statusBarPipelineFlags.mobileSlot)) {
                Log.i("StatusBarIconController", "Ignoring removal of (" + str + "). It should be controlled elsewhere");
                return;
            }
        }
        StatusBarIconList statusBarIconList = this.mStatusBarIconList;
        StatusBarIconList.Slot slot = (StatusBarIconList.Slot) statusBarIconList.mSlots.get(statusBarIconList.findOrInsertSlot(str));
        if (slot.hasIconsInSlot()) {
            for (StatusBarIconHolder statusBarIconHolder : slot.getHolderListInViewOrder()) {
                final int viewIndex = statusBarIconList.getViewIndex(statusBarIconHolder.tag, str);
                int i = statusBarIconHolder.tag;
                if (i == 0) {
                    slot.mHolder = null;
                } else {
                    int indexForTag = slot.getIndexForTag(i);
                    if (indexForTag != -1) {
                        slot.mSubSlots.remove(indexForTag);
                    }
                }
                this.mIconGroups.forEach(new Consumer() { // from class: com.android.systemui.statusbar.phone.ui.StatusBarIconControllerImpl$$ExternalSyntheticLambda2
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ((IconManager) obj).onRemoveIcon(viewIndex);
                    }
                });
            }
        }
    }

    public final void setIcon(final String str, final StatusBarIconHolder statusBarIconHolder) {
        int i = statusBarIconHolder.tag;
        StatusBarIconList statusBarIconList = this.mStatusBarIconList;
        boolean z = statusBarIconList.getIconHolder(i, str) == null;
        StatusBarIconList.Slot slot = (StatusBarIconList.Slot) statusBarIconList.mSlots.get(statusBarIconList.findOrInsertSlot(str));
        slot.getClass();
        int i2 = statusBarIconHolder.tag;
        if (i2 == 0) {
            slot.mHolder = statusBarIconHolder;
        } else if (slot.mSubSlots == null) {
            ArrayList arrayList = new ArrayList();
            slot.mSubSlots = arrayList;
            arrayList.add(statusBarIconHolder);
        } else if (slot.getIndexForTag(i2) == -1) {
            slot.mSubSlots.add(statusBarIconHolder);
        }
        if (!z) {
            handleSet(str, statusBarIconHolder);
            return;
        }
        final int viewIndex = statusBarIconList.getViewIndex(statusBarIconHolder.tag, str);
        final boolean contains = this.mIconHideList.contains(str);
        this.mIconGroups.forEach(new Consumer() { // from class: com.android.systemui.statusbar.phone.ui.StatusBarIconControllerImpl$$ExternalSyntheticLambda1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((IconManager) obj).onIconAdded(viewIndex, str, contains, statusBarIconHolder);
            }
        });
    }

    public final void setIconVisibility(String str, boolean z) {
        StatusBarIconHolder iconHolder = this.mStatusBarIconList.getIconHolder(0, str);
        if (iconHolder == null || iconHolder.isVisible() == z) {
            return;
        }
        iconHolder.setVisible(z);
        handleSet(str, iconHolder);
    }

    public final void setIcon(CharSequence charSequence, String str, int i) {
        Icon createWithResource = Icon.createWithResource(this.mContext, i);
        StatusBarIcon.Type type = StatusBarIcon.Type.SystemIcon;
        StatusBarIcon.Shape shape = StatusBarIcon.Shape.WRAP_CONTENT;
        boolean z = createWithResource.getType() == 2;
        String str2 = "Expected Icon of TYPE_RESOURCE, but got " + createWithResource.getType();
        if (z) {
            String resPackage = createWithResource.getResPackage();
            if (TextUtils.isEmpty(resPackage)) {
                resPackage = this.mContext.getPackageName();
            }
            String str3 = resPackage;
            StatusBarIconHolder iconHolder = this.mStatusBarIconList.getIconHolder(0, str);
            if (iconHolder == null) {
                StatusBarIcon statusBarIcon = new StatusBarIcon(UserHandle.SYSTEM, str3, createWithResource, 0, 0, charSequence, type, shape);
                statusBarIcon.preloadedIcon = null;
                StatusBarIconHolder statusBarIconHolder = new StatusBarIconHolder();
                statusBarIconHolder.icon = statusBarIcon;
                setIcon(str, statusBarIconHolder);
                return;
            }
            StatusBarIcon statusBarIcon2 = iconHolder.icon;
            statusBarIcon2.pkg = str3;
            statusBarIcon2.icon = createWithResource;
            statusBarIcon2.contentDescription = charSequence;
            statusBarIcon2.type = type;
            statusBarIcon2.shape = shape;
            statusBarIcon2.preloadedIcon = null;
            handleSet(str, iconHolder);
            return;
        }
        throw new IllegalArgumentException(String.valueOf(str2));
    }
}
