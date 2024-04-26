package com.ian.app.muviepedia.core.data.repository.model

import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.VideoDataResponse

data class Video(
    val id: String,
    val results: List<ItemVideoData>,
) {
    data class ItemVideoData(
        val id: String,
        val site: String,
        val key: String,
        val type: String,
    )
}


fun VideoDataResponse.mapToDomain(): Video {
    return Video(
        id = this.id,
        results = this.results.mapListItemDataToDomain()
    )
}

fun VideoDataResponse.ItemVideoDataResponse.mapItemDataToDomain(): Video.ItemVideoData {
    return Video.ItemVideoData(
        id = this.id,
        site = this.site,
        key = this.key,
        type = this.type
    )
}

fun List<VideoDataResponse.ItemVideoDataResponse>.mapListItemDataToDomain():List<Video.ItemVideoData>{
    return this.map { it.mapItemDataToDomain() }
}
