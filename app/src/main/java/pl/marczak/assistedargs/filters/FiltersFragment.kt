package pl.marczak.assistedargs.filters

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.core.view.children
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.filters_fragment.*
import pl.marczak.assistedargs.R
import pl.marczak.assistedargs.base.BaseFragment
import pl.marczak.assistedargs.filters.FilterType.*
import javax.inject.Inject

class FiltersFragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
    }

    @Inject
    lateinit var factory: FiltersViewModel.Factory

    val viewModel: FiltersViewModel by assistedViewModel { factory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.filters_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.setup()

        black.setOnClickListener {
            viewModel.onFilterChosen(BLACK)
        }
        red.setOnClickListener {
            viewModel.onFilterChosen(RED)
        }
        white.setOnClickListener {
            viewModel.onFilterChosen(WHITE)
        }

        viewModel.locationLiveData.observe(viewLifecycleOwner, Observer {
            current_location.text = "${it.latitude} | ${it.longitude}"
        })

        viewModel.selectedFilter.observe(viewLifecycleOwner, Observer {
            clearSelection()
            when (it) {
                BLACK -> black.setTextColor(Color.RED)
                RED -> red.setTextColor(Color.RED)
                WHITE -> white.setTextColor(Color.RED)
            }
        })
        viewModel.pickedFilters.observe(viewLifecycleOwner, Observer {
            val (filterType, location) = it
            findNavController().navigate(
                R.id.navigate_to_list, bundleOf(
                    "filter" to filterType,
                    "location" to location
                )
            )
        })
    }

    private fun clearSelection() {
        for (v in filters_fragment_rootview.children) {
            if (v is Button) {
                v.setTextColor(Color.BLACK)
            }
        }
    }
}
