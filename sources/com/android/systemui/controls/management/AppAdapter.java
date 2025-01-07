package com.android.systemui.controls.management;

import android.R;
import android.content.ComponentName;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.controls.ControlsServiceInfo;
import com.android.systemui.controls.management.ControlsListingController;
import com.android.systemui.util.PluralMessageFormaterKt;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import kotlin.collections.CollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AppAdapter extends RecyclerView.Adapter {
    public final Set authorizedPanels;
    public final FavoritesRenderer favoritesRenderer;
    public final LayoutInflater layoutInflater;
    public List listOfServices = EmptyList.INSTANCE;
    public final Function1 onAppSelected;
    public final Resources resources;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Holder extends RecyclerView.ViewHolder {
        public final FavoritesRenderer favRenderer;
        public final TextView favorites;
        public final ImageView icon;
        public final TextView title;
        public final View view;

        public Holder(View view, FavoritesRenderer favoritesRenderer) {
            super(view);
            this.favRenderer = favoritesRenderer;
            this.view = view;
            this.icon = (ImageView) view.requireViewById(R.id.icon);
            this.title = (TextView) view.requireViewById(R.id.title);
            this.favorites = (TextView) view.requireViewById(com.android.wm.shell.R.id.favorites);
        }
    }

    public AppAdapter(final Executor executor, final Executor executor2, Lifecycle lifecycle, ControlsListingController controlsListingController, LayoutInflater layoutInflater, Function1 function1, FavoritesRenderer favoritesRenderer, Resources resources, Set set) {
        this.layoutInflater = layoutInflater;
        this.onAppSelected = function1;
        this.favoritesRenderer = favoritesRenderer;
        this.resources = resources;
        this.authorizedPanels = set;
        controlsListingController.observe(lifecycle, new ControlsListingController.ControlsListingCallback() { // from class: com.android.systemui.controls.management.AppAdapter$callback$1
            @Override // com.android.systemui.controls.management.ControlsListingController.ControlsListingCallback
            public final void onServicesUpdated(final List list) {
                Executor executor3 = executor;
                final AppAdapter appAdapter = this;
                final Executor executor4 = executor2;
                executor3.execute(new Runnable() { // from class: com.android.systemui.controls.management.AppAdapter$callback$1$onServicesUpdated$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        final Collator collator = Collator.getInstance(AppAdapter.this.resources.getConfiguration().getLocales().get(0));
                        Intrinsics.checkNotNull(collator);
                        Comparator comparator = new Comparator() { // from class: com.android.systemui.controls.management.AppAdapter$callback$1$onServicesUpdated$1$run$$inlined$compareBy$1
                            @Override // java.util.Comparator
                            public final int compare(Object obj, Object obj2) {
                                return collator.compare(((ControlsServiceInfo) obj).loadLabel(), ((ControlsServiceInfo) obj2).loadLabel());
                            }
                        };
                        AppAdapter appAdapter2 = AppAdapter.this;
                        List list2 = list;
                        ArrayList arrayList = new ArrayList();
                        for (Object obj : list2) {
                            ComponentName componentName = ((ControlsServiceInfo) obj).panelActivity;
                            if (componentName == null || !CollectionsKt.contains(appAdapter2.authorizedPanels, componentName.getPackageName())) {
                                arrayList.add(obj);
                            }
                        }
                        appAdapter2.listOfServices = CollectionsKt.sortedWith(arrayList, comparator);
                        Executor executor5 = executor4;
                        final AppAdapter appAdapter3 = AppAdapter.this;
                        executor5.execute(new Runnable() { // from class: com.android.systemui.controls.management.AppAdapter$callback$1$onServicesUpdated$1.2
                            @Override // java.lang.Runnable
                            public final void run() {
                                AppAdapter.this.notifyDataSetChanged();
                            }
                        });
                    }
                });
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemCount() {
        return this.listOfServices.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        Holder holder = (Holder) viewHolder;
        ControlsServiceInfo controlsServiceInfo = (ControlsServiceInfo) this.listOfServices.get(i);
        holder.icon.setImageDrawable(controlsServiceInfo.loadIcon());
        holder.title.setText(controlsServiceInfo.loadLabel());
        String str = null;
        if (controlsServiceInfo.panelActivity == null) {
            ComponentName componentName = controlsServiceInfo.componentName;
            FavoritesRenderer favoritesRenderer = holder.favRenderer;
            int intValue = ((Number) ((ControlsProviderSelectorActivity$onStart$2) favoritesRenderer.favoriteFunction).invoke(componentName)).intValue();
            if (intValue != 0) {
                str = PluralMessageFormaterKt.icuMessageFormat(favoritesRenderer.resources, com.android.wm.shell.R.string.controls_number_of_favorites, intValue);
            }
        }
        holder.favorites.setText(str);
        holder.favorites.setVisibility(str == null ? 8 : 0);
        holder.view.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.controls.management.AppAdapter$onBindViewHolder$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AppAdapter appAdapter = AppAdapter.this;
                appAdapter.onAppSelected.invoke(appAdapter.listOfServices.get(i));
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final RecyclerView.ViewHolder onCreateViewHolder(int i, ViewGroup viewGroup) {
        return new Holder(this.layoutInflater.inflate(com.android.wm.shell.R.layout.controls_app_item, viewGroup, false), this.favoritesRenderer);
    }
}
