package com.android.systemui.people.ui.viewmodel;

import android.content.Context;
import android.content.Intent;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.android.systemui.people.data.repository.PeopleTileRepository;
import com.android.systemui.people.data.repository.PeopleWidgetRepository;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PeopleViewModel extends ViewModel {
    public final Function0 clearResult;
    public final Function1 onTileClicked;
    public final Function0 onTileRefreshRequested;
    public final Function0 onUserJourneyCancelled;
    public final Function1 onWidgetIdChanged;
    public final ReadonlyStateFlow priorityTiles;
    public final ReadonlyStateFlow recentTiles;
    public final ReadonlyStateFlow result;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Factory implements ViewModelProvider.Factory {
        public final Context context;
        public final PeopleTileRepository tileRepository;
        public final PeopleWidgetRepository widgetRepository;

        public Factory(Context context, PeopleTileRepository peopleTileRepository, PeopleWidgetRepository peopleWidgetRepository) {
            this.context = context;
            this.tileRepository = peopleTileRepository;
            this.widgetRepository = peopleWidgetRepository;
        }

        @Override // androidx.lifecycle.ViewModelProvider.Factory
        public final ViewModel create(Class cls) {
            if (!cls.equals(PeopleViewModel.class)) {
                throw new IllegalStateException("Check failed.");
            }
            Context context = this.context;
            PeopleTileRepository peopleTileRepository = this.tileRepository;
            StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(PeopleViewModelKt.PeopleViewModel$priorityTiles(peopleTileRepository, context));
            StateFlowImpl MutableStateFlow2 = StateFlowKt.MutableStateFlow(PeopleViewModelKt.PeopleViewModel$recentTiles(peopleTileRepository, context));
            StateFlowImpl MutableStateFlow3 = StateFlowKt.MutableStateFlow(0);
            StateFlowImpl MutableStateFlow4 = StateFlowKt.MutableStateFlow(null);
            return new PeopleViewModel(new ReadonlyStateFlow(MutableStateFlow), new ReadonlyStateFlow(MutableStateFlow2), new ReadonlyStateFlow(MutableStateFlow4), new PeopleViewModelKt$PeopleViewModel$1(MutableStateFlow, MutableStateFlow2, peopleTileRepository, context), new PeopleViewModelKt$PeopleViewModel$2(MutableStateFlow3), new PeopleViewModelKt$PeopleViewModel$3(MutableStateFlow4), new PeopleViewModelKt$PeopleViewModel$4(MutableStateFlow3, this.widgetRepository, MutableStateFlow4), new PeopleViewModelKt$PeopleViewModel$5(MutableStateFlow4));
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Result {

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Cancelled extends Result {
            public static final Cancelled INSTANCE = new Cancelled();
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Success extends Result {
            public final Intent data;

            public Success(Intent intent) {
                this.data = intent;
            }
        }
    }

    public PeopleViewModel(ReadonlyStateFlow readonlyStateFlow, ReadonlyStateFlow readonlyStateFlow2, ReadonlyStateFlow readonlyStateFlow3, Function0 function0, Function1 function1, Function0 function02, Function1 function12, Function0 function03) {
        this.priorityTiles = readonlyStateFlow;
        this.recentTiles = readonlyStateFlow2;
        this.result = readonlyStateFlow3;
        this.onTileRefreshRequested = function0;
        this.onWidgetIdChanged = function1;
        this.clearResult = function02;
        this.onTileClicked = function12;
        this.onUserJourneyCancelled = function03;
    }
}
