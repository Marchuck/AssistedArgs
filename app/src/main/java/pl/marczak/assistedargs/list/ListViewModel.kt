package pl.marczak.assistedargs.list

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pl.marczak.assistedargs.filters.FilterParam
import javax.inject.Inject


class ListUseCase @Inject constructor() {
    suspend fun execute(filterParam: FilterParam): String {
        delay(200)
        return "executed with $filterParam"
    }
}

class ListViewModel @AssistedInject constructor(
    private val useCase: ListUseCase,
    @Assisted private val filterParam: FilterParam
) : ViewModel() {


    interface Factory {
        fun create(): ListViewModel
    }

    fun setup() {
        viewModelScope.launch(Dispatchers.Main) {
            useCase.execute(filterParam)
        }
    }
}
