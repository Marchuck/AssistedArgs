package pl.marczak.assistedargs.list

import kotlinx.coroutines.delay
import pl.marczak.assistedargs.filters.FilterType
import javax.inject.Inject

class ListUseCase @Inject constructor() {
    suspend fun execute(filterParam: FilterType): String {
        delay(200)
        return "executed with [$filterParam]"
    }
}
