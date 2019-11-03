package pl.marczak.assistedargs.filters

import android.location.Location
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pl.marczak.assistedargs.LiveEvent
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

data class FilterParam(
    val filterType: FilterType,
    val location: Location
) : Serializable

class FiltersViewModel @AssistedInject constructor(
    private val provideFiltersUseCase: ProvideFiltersUseCase,
    @Assisted private val location: Location
) :
    ViewModel() {

    val pickedFilter = LiveEvent<FilterParam>()
    val results = MutableLiveData<List<FilterType>>()

    fun setup() {
        viewModelScope.launch(Dispatchers.Main) {
            val _results = provideFiltersUseCase.execute(location)
            results.value = _results
        }
    }

    fun onFilterChosen(filter: FilterType) {
        pickedFilter.value = FilterParam(filter, location)
    }
}
