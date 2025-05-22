package otus.homework.flowcats

sealed interface Result {
    data class Success(val fact: Fact?) : Result
    data class Error(val msg: String?) : Result
    data object Initial : Result
}