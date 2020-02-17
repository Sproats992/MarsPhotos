package com.robertsproats.marsrobots.presentation.features.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.robertsproats.marsrobots.presentation.BaseFragment
import com.robertsproats.marsrobots.presentation.R
import com.robertsproats.marsrobots.presentation.features.detail.MarsRobotsDetailFragment.Companion.BundleDetailIndexKey
import com.robertsproats.marsrobots.presentation.model.PresentationSimpleMarsRobotsItem
import kotlinx.android.synthetic.main.marsrobots_home_layout.*
import javax.inject.Inject

class MarsRobotsFragment @Inject constructor() : BaseFragment(), MarsRobotsDetailListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MarsRobotsViewModel

    private var marsRobotsListAdapter: MarsRobotsListAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        viewModel = ViewModelProviders.of(this, viewModelFactory)[MarsRobotsViewModel::class.java]
        return inflater.inflate(R.layout.marsrobots_home_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.let {
            marsRobotsListAdapter = MarsRobotsListAdapter(it, this)
        }

        marsRecyclerView?.apply {
            layoutManager = GridLayoutManager(activity, 2)
            marsRobotsListAdapter?.let {
                adapter = it
            }
        }

        showProgress()

        viewModel.marsRobotsLiveData.observe(this.viewLifecycleOwner, Observer {
            hideProgress()
            updateList(it)
        })

    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchMarsRobotsData()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MarsRobotsViewModel::class.java)
    }

    override fun onDetailSelected(item: Int) {
        findNavController().navigate(R.id.navigate_to_detail, bundleOf(Pair(BundleDetailIndexKey, item.toString())))
    }

    private fun updateList(marsList: List<PresentationSimpleMarsRobotsItem>) {
        marsRecyclerView?.apply {
            marsRobotsListAdapter?.let {
                it.updateList(marsList = marsList)
            }
        }
    }

    private fun hideProgress() {
        progressContainer.visibility = View.GONE
        marsRecyclerView.visibility = View.VISIBLE
    }

    private fun showProgress() {
        progressContainer.visibility = View.VISIBLE
        marsRecyclerView.visibility = View.GONE
    }
}

