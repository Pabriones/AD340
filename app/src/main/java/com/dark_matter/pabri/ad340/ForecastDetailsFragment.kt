package com.dark_matter.pabri.ad340


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.dark_matter.pabri.ad340.databinding.FragmentForecastDetailsBinding
import com.dark_matter.pabri.ad340.location.ForecastDetailsViewModel
import com.dark_matter.pabri.ad340.location.ForecastDetailsViewModelFactory
import com.dark_matter.pabri.ad340.location.ForecastDetailsViewState
import java.text.SimpleDateFormat
import java.util.*



class ForecastDetailsFragment : Fragment() {

    private val args: ForecastDetailsFragmentArgs by navArgs()

    private lateinit var viewModelFactory: ForecastDetailsViewModelFactory
    private val viewModel : ForecastDetailsViewModel by viewModels(
        factoryProducer = { viewModelFactory }
    )

    private var _binding: FragmentForecastDetailsBinding? = null
    //THis property only valid between onCreateview and onDestroy view
    private val binding get() = _binding!!

    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentForecastDetailsBinding.inflate(inflater, container, false)
        viewModelFactory = ForecastDetailsViewModelFactory(args)
        tempDisplaySettingManager = TempDisplaySettingManager(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewStateObserver = Observer<ForecastDetailsViewState> {viewState ->
            //update the UI
            binding.tempText.text = formatTempForDisplay(viewState.temp , tempDisplaySettingManager.getTempDisplaySetting())
            binding.descriptionText.text = viewState.description
            binding.dateText.text = viewState.date
            binding.forecastIcon.load(viewState.iconUrl)

        }

        viewModel.viewState.observe(viewLifecycleOwner, viewStateObserver)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
