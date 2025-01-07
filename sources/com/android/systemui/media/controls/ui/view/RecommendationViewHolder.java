package com.android.systemui.media.controls.ui.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import com.android.systemui.media.controls.ui.drawable.IlluminationDrawable;
import com.android.systemui.util.animation.TransitionLayout;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.SetsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class RecommendationViewHolder {
    public final TextView cardTitle;
    public final GutsViewHolder gutsViewHolder;
    public final List mediaAppIcons;
    public final List mediaCoverContainers;
    public final List mediaCoverItems;
    public final List mediaProgressBars;
    public final List mediaSubtitles;
    public final List mediaTitles;
    public final TransitionLayout recommendations;
    public static final Set controlsIds = SetsKt.setOf(Integer.valueOf(R.id.media_rec_title), Integer.valueOf(R.id.media_cover), Integer.valueOf(R.id.media_cover1_container), Integer.valueOf(R.id.media_cover2_container), Integer.valueOf(R.id.media_cover3_container), Integer.valueOf(R.id.media_title), Integer.valueOf(R.id.media_subtitle));
    public static final Set mediaTitlesAndSubtitlesIds = SetsKt.setOf(Integer.valueOf(R.id.media_title), Integer.valueOf(R.id.media_subtitle));
    public static final Set mediaContainersIds = SetsKt.setOf(Integer.valueOf(R.id.media_cover1_container), Integer.valueOf(R.id.media_cover2_container), Integer.valueOf(R.id.media_cover3_container));
    public static final int backgroundId = R.id.sizing_view;

    /* JADX WARN: Multi-variable type inference failed */
    public RecommendationViewHolder(View view) {
        this.recommendations = (TransitionLayout) view;
        this.cardTitle = (TextView) view.requireViewById(R.id.media_rec_title);
        List listOf = CollectionsKt__CollectionsKt.listOf(view.requireViewById(R.id.media_cover1_container), view.requireViewById(R.id.media_cover2_container), view.requireViewById(R.id.media_cover3_container));
        this.mediaCoverContainers = listOf;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listOf, 10));
        Iterator it = listOf.iterator();
        while (it.hasNext()) {
            arrayList.add(((ViewGroup) it.next()).requireViewById(R.id.media_rec_app_icon));
        }
        this.mediaAppIcons = arrayList;
        List list = this.mediaCoverContainers;
        ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            arrayList2.add((TextView) ((ViewGroup) it2.next()).requireViewById(R.id.media_title));
        }
        this.mediaTitles = arrayList2;
        List list2 = this.mediaCoverContainers;
        ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10));
        Iterator it3 = list2.iterator();
        while (it3.hasNext()) {
            arrayList3.add((TextView) ((ViewGroup) it3.next()).requireViewById(R.id.media_subtitle));
        }
        this.mediaSubtitles = arrayList3;
        List list3 = this.mediaCoverContainers;
        ArrayList arrayList4 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list3, 10));
        Iterator it4 = list3.iterator();
        while (it4.hasNext()) {
            SeekBar seekBar = (SeekBar) ((ViewGroup) it4.next()).requireViewById(R.id.media_progress_bar);
            seekBar.setLayoutDirection(0);
            arrayList4.add(seekBar);
        }
        this.mediaProgressBars = arrayList4;
        List list4 = this.mediaCoverContainers;
        ArrayList arrayList5 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list4, 10));
        Iterator it5 = list4.iterator();
        while (it5.hasNext()) {
            arrayList5.add((ImageView) ((ViewGroup) it5.next()).requireViewById(R.id.media_cover));
        }
        this.mediaCoverItems = arrayList5;
        this.gutsViewHolder = new GutsViewHolder(view);
        IlluminationDrawable illuminationDrawable = (IlluminationDrawable) this.recommendations.getBackground();
        Iterator it6 = this.mediaCoverContainers.iterator();
        while (it6.hasNext()) {
            illuminationDrawable.registerLightSource((ViewGroup) it6.next());
        }
        illuminationDrawable.registerLightSource(this.gutsViewHolder.cancel);
        illuminationDrawable.registerLightSource(this.gutsViewHolder.dismiss);
        illuminationDrawable.registerLightSource(this.gutsViewHolder.settings);
    }
}
