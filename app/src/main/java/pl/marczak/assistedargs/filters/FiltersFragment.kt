package pl.marczak.assistedargs.filters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.filters_fragment.*
import pl.marczak.assistedargs.R
import pl.marczak.assistedargs.base.BaseFragment
import javax.inject.Inject

class FiltersFragment : BaseFragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
    }

    val args: FiltersFragmentArgs by safeNavArgs()

    @Inject
    lateinit var factory: FiltersViewModel.Factory

    val viewModel: FiltersViewModel by assistedViewModel(args.toBundle(), factory)

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
            viewModel.onFilterChosen(FilterType.BLACK)
        }
        red.setOnClickListener {
            viewModel.onFilterChosen(FilterType.RED)
        }
        white.setOnClickListener {
            viewModel.onFilterChosen(FilterType.WHITE)
        }

        viewModel.locationLiveData.observe(viewLifecycleOwner, Observer {
            current_location.text = "${it.latitude} | ${it.longitude}"
        })

        viewModel.pickedFilters.observe(viewLifecycleOwner, Observer {
            val (filterType, location) = it
            val action = FiltersFragmentDirections.navigateToList().setLocation(location)
                .setFilter(filterType)
            findNavController().navigate(action)
        })
    }

}
