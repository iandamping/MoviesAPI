#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "") package ${PACKAGE_NAME}

#end
sealed class Epoxy${NAME}MovieData {

    data class Shimmer(val epoxyId: Int) : Epoxy${NAME}MovieData()

    data class MovieData(
        val epoxyId: Int,
        val vote_count: Int,
        val id: Int,
        val video: Boolean,
        val vote_average: Double,
        val title: String,
        val popularity: Double,
        val poster_path: String,
        val original_language: String,
        val original_title: String,
        val backdrop_path: String,
        val adult: Boolean,
        val overview: String,
        val release_date: String
    ) : Epoxy${NAME}MovieData()

    object Error : Epoxy${NAME}MovieData()
}