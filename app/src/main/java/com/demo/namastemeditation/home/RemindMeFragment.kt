package com.demo.namastemeditation.home

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import android.widget.TimePicker
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.demo.namastemeditation.R
import com.demo.namastemeditation.utils.PreferenceHelper
import com.demo.namastemeditation.utils.PreferenceHelper.clearValues
import com.demo.namastemeditation.utils.PreferenceHelper.setTime
import com.demo.namastemeditation.utils.PreferenceHelper.setType
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout


class RemindMeFragment : Fragment() {

    var isTimeChanged = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val root = inflater.inflate(R.layout.fragment_remind_me, container, false)
        val constraintLayout = root.findViewById<ConstraintLayout>(R.id.constraintLayout)
        val llNotify = root.findViewById<LinearLayoutCompat>(R.id.llNotify)
        val tvNotifyReminder = root.findViewById<TextView>(R.id.tvNotifyReminder)
        val tvChange = root.findViewById<TextView>(R.id.tvChange)
        val timePicker = root.findViewById<TimePicker>(R.id.timePicker)
        val tilChooseTime = root.findViewById<TextInputLayout>(R.id.tilChooseTime)
        val btnSetReminder = root.findViewById<AppCompatButton>(R.id.btnSetReminder)
        val autoCompleteTextView =
            root.findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)

        val prefs = PreferenceHelper.customPreference(requireContext())

        timePicker.setOnTimeChangedListener { _, hour, minute ->
            isTimeChanged = true
            var hourToFormat = hour
            var amPm = ""
            when {
                hourToFormat == 0 -> {
                    hourToFormat += 12
                    amPm = "AM"
                }

                hourToFormat == 12 -> amPm = "PM"
                hourToFormat > 12 -> {
                    hourToFormat -= 12
                    amPm = "PM"
                }

                else -> amPm = "AM"
            }
            val formattedHour = if (hourToFormat < 10) "0$hourToFormat" else hourToFormat
            val min = if (minute < 10) "0$minute" else minute
            val formattedTime = "$formattedHour : $min $amPm"
            prefs.setTime = formattedTime
        }
        if (prefs.setTime?.isNotEmpty() == true && prefs.setType?.isNotEmpty() == true) {
            tvNotifyReminder.text = "Reminder set to ${prefs.setTime} ${prefs.setType}"
            tvNotifyReminder.visibility = View.VISIBLE
        } else {
            // tvNotifyReminder.visibility = View.GONE
        }

        tvChange.setOnClickListener {
            prefs.setType=""
            prefs.setTime=""
            tvChange.visibility = View.GONE
            tvNotifyReminder.visibility = View.GONE
            timePicker.visibility = View.VISIBLE
            tilChooseTime.visibility = View.VISIBLE
            btnSetReminder.visibility = View.VISIBLE
        }

        btnSetReminder.setOnClickListener {
            val type = autoCompleteTextView.text.toString()
            if (isTimeChanged) {
                if (type.isNotEmpty()) {
                    prefs.setType = type
                    tvNotifyReminder.text = "Reminder set to ${prefs.setTime} ${prefs.setType}"
                    tvNotifyReminder.visibility = View.VISIBLE
                    tvChange.visibility = View.VISIBLE
                    hideKeyboardFrom(constraintLayout)
                } else {
                    val snackbar = Snackbar
                        .make(constraintLayout, "Time field cannot be empty!", Snackbar.LENGTH_LONG)
                    snackbar.view.setBackgroundColor(ContextCompat.getColor(requireActivity(), R.color.orange));
                    snackbar.show()
                }
            } else {
                val snackbar = Snackbar
                    .make(constraintLayout, "Please change time to continue!", Snackbar.LENGTH_LONG)
                snackbar.view.setBackgroundColor(ContextCompat.getColor(requireActivity(), R.color.orange));
                snackbar.show()
            }
        }

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.reminder_array,
            android.R.layout.simple_spinner_dropdown_item
        ).let {
            autoCompleteTextView.setAdapter(it)
        }
        return root
    }

    fun hideKeyboardFrom(view: View) {
        val imm = requireContext().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    companion object {
        fun newInstance() = RemindMeFragment()
    }
}