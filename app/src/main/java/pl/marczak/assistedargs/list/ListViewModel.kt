package pl.marczak.assistedargs.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pl.marczak.assistedargs.Location
import pl.marczak.assistedargs.di.assisted.AssistedViewModelFactory
import pl.marczak.assistedargs.filters.FilterType
import javax.inject.Inject


class ListUseCase @Inject constructor() {
    suspend fun execute(filterParam: FilterType): String {
        delay(200)
        return "executed with [$filterParam]"
    }
}

class ListViewModel @AssistedInject constructor(
    private val useCase: ListUseCase,
    @Assisted private val handle: SavedStateHandle
) : ViewModel() {

    @AssistedInject.Factory
    interface Factory : AssistedViewModelFactory<ListViewModel>

    var filterParam: FilterType = handle["filter"] ?: FilterType.BLACK
    var location: Location = handle["location"] ?: Location(0.0, -1.0)

    val message = MutableLiveData<String>()

    fun setup() {
        viewModelScope.launch(Dispatchers.Main) {
            val string = useCase.execute(filterParam)
            message.value = "$string at $location"
        }
    }
}
