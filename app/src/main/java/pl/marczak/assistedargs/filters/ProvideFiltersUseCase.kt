package pl.marczak.assistedargs.filters

import kotlinx.coroutines.delay
import pl.marczak.assistedargs.Location
import javax.inject.Inject

class ProvideFiltersUseCase @Inject constructor() {

    suspend fun execute(location: Location): List<FilterType> {
        delay(300)
        return arrayListOf(FilterType.BLACK, FilterType.RED, FilterType.WHITE)
    }
}