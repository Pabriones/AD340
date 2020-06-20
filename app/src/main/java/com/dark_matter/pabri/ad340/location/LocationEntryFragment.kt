package com.dark_matter.pabri.ad340.location

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.dark_matter.pabri.ad340.Location
import com.dark_matter.pabri.ad340.LocationRepository

import com.dark_matter.pabri.ad340.R

/**
 * A simple [Fragment] subclass.
 */
class LocationEntryFragment : Fragment() {

    private lateinit var locationRepository: LocationRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        locationRepository = LocationRepository(requireContext())
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_location_entry, container, false)

        val zipcodeEditText: EditText = view.findViewById(R.id.zipcodeEditText)
        val enterButton: Button = view.findViewById(R.id.enterButton)

        enterButton.setOnClickListener{
            val zipcode: String =zipcodeEditText.text.toString()
            if(zipcode.length != 5){
                Toast.makeText(context, "Please enter valid Zipcode", Toast.LENGTH_SHORT).show()
            }else{
                locationRepository.saveLocation(Location.Zipcode(zipcode))
                findNavController().navigateUp()
            }

            //Toast.makeText(this, "Submitted", Toast.LENGTH_SHORT).show()
        }

        return view
    }

}
