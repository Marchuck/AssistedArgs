package pl.marczak.assistedargs.filters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pl.marczak.assistedargs.LiveEvent
import pl.marczak.assistedargs.Location
import pl.marczak.assistedargs.di.assisted.AssistedViewModelFactory
import java.io.Serializable
import javax.inject.Inject


class ProvideFiltersUseCase @Inject constructor() {

    suspend fun execute(location: Location): List<FilterType> {
        delay(300)
        return arrayListOf(FilterType.BLACK, FilterType.RED, FilterType.WHITE)
    }
}

enum class FilterType : Serializable {
    BLACK, RED, WHITE
}

class FiltersViewModel @AssistedInject constructor(
    private val provideFiltersUseCase: ProvideFiltersUseCase,
    @Assisted private val handle: SavedStateHandle
) :
    ViewModel() {

    @AssistedInject.Factory
    interface Factory : AssistedViewModelFactory<FiltersViewModel>

    var location: Location = handle["location"] ?: Location(-1.0, -1.0)
        set(value) {
            handle["location"] = value
            locationLiveData.value = value
            field = value
        }

    val pickedFilters = LiveEvent<Pair<FilterType, Location>>()
    val results = MutableLiveData<List<FilterType>>()

    val locationLiveData = MutableLiveData<Location>().apply {
        value = location
    }

    fun setup() {
        viewModelScope.launch(Dispatchers.Main) {
            val _results = provideFiltersUseCase.execute(location)
            results.value = _results
        }
    }

    fun onFilterChosen(filter: FilterType) {
        pickedFilters.value = filter to location
    }
}