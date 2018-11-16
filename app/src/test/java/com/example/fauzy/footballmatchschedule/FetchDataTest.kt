package com.example.fauzy.footballmatchschedule

import com.example.fauzy.footballmatchschedule.models.MatchList
import com.example.fauzy.footballmatchschedule.repository.Repository
import com.example.fauzy.footballmatchschedule.repository.RepositoryCallback
import com.example.fauzy.footballmatchschedule.views.IViewMatch
import com.nhaarman.mockitokotlin2.argumentCaptor
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class FetchDataTest {

    @Mock
    lateinit var view: IViewMatch

    @Mock
    lateinit var repo: Repository

    @Mock
    private lateinit var presenter: LastMatchPresenter
    private var data: MatchList = MatchList()
    private var result: MatchList = MatchList()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = LastMatchPresenter(view, repo)
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun isRequestApiSuccess() {
        presenter.getLastMatches()
        argumentCaptor<RepositoryCallback<MatchList>>().apply {
            verify(repo).getPastEvents(capture())
            firstValue.onDataLoaded(data)
        }
        verify(view).onShowLoading()
        verify(view).onDataLoaded(data)
        verify(view).onHideLoading()
//        assertEquals("Monday, 05-11-2018", match?.dateEvent)
    }

    @Test
    fun isDateFormatterWorkCorrectly() {
        val newRepo = Repository()
        val sample = "2018-11-08"
        val result = newRepo.formatDate(sample)
        assertEquals("Thursday, 08-11-2018", result)
    }
}
