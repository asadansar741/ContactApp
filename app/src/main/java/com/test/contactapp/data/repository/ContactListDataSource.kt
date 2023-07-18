package com.test.contactapp.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.test.contactapp.data.model.Data
import retrofit2.HttpException
import java.io.IOException

class ContactListDataSource(
    private val repository: ContactRepository,
) : PagingSource<Int, Data>() {
    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Data> {
        return try {
            val nextPageNumber = params.key ?: 0
            val response = repository.getContactPagedList(nextPageNumber)
            LoadResult.Page(
                data = response?.data!!,
                prevKey = if (nextPageNumber == 0) null else nextPageNumber - 1,
//                nextKey = if (response.empty) null else nextPageNumber + 1
                nextKey =  nextPageNumber + 1
            )
        }catch (e: IOException) {
            // IOException for network failures.
            LoadResult.Error(e)
        } catch (e: HttpException) {
            // HttpException for any non-2xx HTTP status codes.
            LoadResult.Error(e)
        }
        catch (e: Exception){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
