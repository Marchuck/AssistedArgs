package pl.marczak.assistedargs.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.intro_fragment.*
import pl.marczak.assistedargs.Location
import pl.marczak.assistedargs.R
import pl.marczak.assistedargs.base.BaseFragment

class IntroFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.intro_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        send_button.setOnClickListener {
            val lat = latitude.text.toString().trim()
            val lon = longitude.text.toString().trim()

            val latitude = lat.toDoubleOrNull() ?: 0.0
            val longitude = lon.toDoubleOrNull() ?: 0.0

            val action = IntroFragmentDirections.navigateToFilters()
                .setLocation(
                    Location(
                        latitude,
                        longitude
                    )
                )
            findNavController().navigate(action)
        }
    }

}
