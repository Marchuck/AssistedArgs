package pl.marczak.assistedargs.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.list_fragment.*
import pl.marczak.assistedargs.R
import pl.marczak.assistedargs.base.BaseFragment
import javax.inject.Inject

class ListFragment : BaseFragment() {

    val args: ListFragmentArgs by safeNavArgs()

    @Inject
    lateinit var factory: ListViewModel.Factory

    val viewModel: ListViewModel by assistedViewModel(args.toBundle(), factory)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.setup()

        viewModel.message.observe(viewLifecycleOwner, Observer {
            results.text = it
        })

    }

}
