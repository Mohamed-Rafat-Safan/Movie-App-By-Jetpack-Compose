package com.example.moviesapp

import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import com.example.moviesapp.domain.model.Movie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest


val movieDiffCallback = object : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem == newItem
}


object NoopListCallback : ListUpdateCallback {
    override fun onInserted(position: Int, count: Int) {}
    override fun onRemoved(position: Int, count: Int) {}
    override fun onMoved(fromPosition: Int, toPosition: Int) {}
    override fun onChanged(position: Int, count: Int, payload: Any?) {}
}

suspend fun <T : Any> Flow<PagingData<T>>.collectData(
    diffCallback: DiffUtil.ItemCallback<T>,
    dispatcher: CoroutineDispatcher = Dispatchers.Unconfined
): List<T> {
    val differ = AsyncPagingDataDiffer(
        diffCallback = diffCallback,
        updateCallback = NoopListCallback,
        mainDispatcher =dispatcher,
        workerDispatcher = dispatcher
    )
    this.collectLatest { pagingData -> differ.submitData(pagingData) }

    return differ.snapshot().items
}