package com.robertsproats.marsrobots.presentation.features.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.robertsproats.marsrobots.presentation.R
import com.robertsproats.marsrobots.presentation.model.PresentationMarsRobotsDetailModel
import com.robertsproats.marsrobots.presentation.utils.ImageLoader
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.marsrobots_detail_layout.*
import timber.log.Timber
import javax.inject.Inject

class MarsRobotsDetailFragment : Fragment() {

    companion object {
        const val BundleDetailIndexKey = "detail_index"
    }

    private lateinit var viewModel: MarsRobotsDetailViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var detailIndex: String? = null

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        viewModel = ViewModelProviders.of(this, viewModelFactory)[MarsRobotsDetailViewModel::class.java]
        return inflater.inflate(R.layout.marsrobots_detail_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.arguments?.let {

            detailIndex = it.get(BundleDetailIndexKey) as String?
            detailIndex?.let {
                showProgress()

                viewModel.marsRobotsDetailLiveData.observe(this.viewLifecycleOwner,
                        Observer {
                            hideProgress()
                            update(it)
                        })

            }
        } ?: run {
            Timber.e("++++ no bundle detected?")
        }
    }

    override fun onResume() {
        super.onResume()

        detailIndex?.let {
            viewModel.fetchMarsRobotsDetailData(Integer.parseInt(it))
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MarsRobotsDetailViewModel::class.java)
    }

    private fun update(detail: PresentationMarsRobotsDetailModel) {
        this.context?.let {
            detail.imageAddress?.apply {
                ImageLoader.loadImage(it, this, detailImage)
            }
            detail.photoCopyright?.apply {
                detailPhotoCredit?.text = String.format(it.getString(R.string.photo_credit), detail.photoCopyright)
            } ?: run {
                detailPhotoCredit?.visibility = View.GONE
            }
        }
        detailTitle?.text = detail.title
        detailDescription?.text = detail.description
    }

    private fun hideProgress() {
        progressContainer.visibility = View.GONE
        detailsContainer.visibility = View.VISIBLE
    }

    private fun showProgress() {
        progressContainer.visibility = View.VISIBLE
        detailsContainer.visibility = View.GONE
    }
}
