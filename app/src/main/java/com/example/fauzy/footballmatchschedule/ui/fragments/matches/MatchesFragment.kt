package com.example.fauzy.footballmatchschedule.ui.activities.fragments.matches

import android.content.DialogInterface
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.support.design.R.layout.support_simple_spinner_dropdown_item
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import android.widget.*
import com.example.fauzy.footballmatchschedule.R
import com.example.fauzy.footballmatchschedule.R.array.league
import com.example.fauzy.footballmatchschedule.R.id.filter_icon
import com.example.fauzy.footballmatchschedule.R.id.search_icon
import com.example.fauzy.footballmatchschedule.R.menu.home_menu_item
import com.example.fauzy.footballmatchschedule.adapters.MatchListAdapter
import com.example.fauzy.footballmatchschedule.models.Match
import com.example.fauzy.footballmatchschedule.models.MatchList
import com.example.fauzy.footballmatchschedule.repository.Repository
import com.example.fauzy.footballmatchschedule.ui.activities.detailmatch.DetailMatchActivity
import com.example.fauzy.footballmatchschedule.ui.activities.searchmatch.SearchMatchActivity
import com.example.fauzy.footballmatchschedule.views.IViewMatch
import kotlinx.android.synthetic.main.matches_layout.*
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.toast

class MatchesFragment : Fragment(), IViewMatch {
    lateinit var matchList: List<Match>
    lateinit var spinner : Spinner
    lateinit var radioGroup : RadioGroup
    lateinit var matchPresenter : MatchesPresenter
    var dialog : DialogInterface? = null

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(home_menu_item, menu)
    }

    var colorStateList = ColorStateList(
        arrayOf(
            intArrayOf(-android.R.attr.state_enabled), //disabled
            intArrayOf(android.R.attr.state_enabled) //enabled
        ),
        intArrayOf(
            Color.BLACK //disabled
            , Color.parseColor("#323e5e") //enabled
        )
    )

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            filter_icon -> {
                dialog = alert{
                    title="Filter list"
                    customView{
                        linearLayout {
                            orientation = LinearLayout.VERTICAL
                            padding = dip(15)
                            spinner = spinner{
                                adapter = ArrayAdapter(ctx,support_simple_spinner_dropdown_item, resources.getStringArray(league))
                            }
                            relativeLayout {
                                radioGroup = radioGroup {
                                    orientation = LinearLayout.HORIZONTAL
                                    val lastButton = radioButton {
                                        text = "Last matches"
                                        buttonTintList = colorStateList
                                    }.lparams { width= matchParent; weight = 1F }

                                    val nextButton = radioButton {
                                        text = "Next matches"
                                        buttonTintList = colorStateList
                                    }.lparams { width= matchParent; weight = 1F }

                                    check(lastButton.id)
                                }.lparams(width = matchParent)
                            }.lparams(width = matchParent)
                        }
                        positiveButton("Apply"){
                            val selectedRadio = radioGroup.findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
                            updateDataFromFilter(spinner.selectedItem.toString(), getLeagueIdByName(spinner.selectedItem.toString()),selectedRadio.text.toString())
                            dialog?.dismiss()
                        }
                    }
                }.show()
                true
            }
            search_icon -> {
                startActivity(
                    intentFor<SearchMatchActivity>()
                )
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    fun getLeagueIdByName(name : String) : String{
        val splittedLeaguePart = name.split(" ")
        val splittedLeagueName = splittedLeaguePart[0]
        val fixedName = splittedLeagueName+"_id"
        return resources.getString(resources.getIdentifier(fixedName,"string",activity?.packageName))
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
       matches_list?.layoutManager = LinearLayoutManager(activity)
        matchPresenter = MatchesPresenter(this, Repository())
        matchPresenter.getMatches("4328","Last matches")
        result_title.text = "English Premier League : Last Matches"
    }


    fun updateDataFromFilter(leagueName : String , leagueId : String, matchMode : String){
        result_title.text = leagueName+" : "+matchMode
        matchPresenter.getMatches(leagueId,matchMode)
    }

    override fun onShowLoading() {
        prog_bar.visibility = View.VISIBLE
    }

    override fun onHideLoading() {
        prog_bar.visibility = View.GONE
    }

    override fun onDataLoaded(data: MatchList) {
        matchList = data.eventList
        matches_list?.adapter = MatchListAdapter(
            activity?.applicationContext,
            matchList
        ) {
            startActivity(
                intentFor<DetailMatchActivity>(
                    "matchId" to it.idEvent,
                    "reformattedDate" to matchList?.find { item -> item.idEvent == it.idEvent }?.dateEvent
                )
            )
        }
    }

    override fun onDataError(data : String) {
        toast(data)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(context).inflate(R.layout.matches_layout, container, false)
    }
}