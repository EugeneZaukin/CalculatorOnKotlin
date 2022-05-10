package com.eugene.calculator

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eugene.calculator.databinding.SettingsFragmentBinding
import com.google.android.material.radiobutton.MaterialRadioButton

private const val NAME_SHARED_PREFERENCE = "calculator_themes"
private const val APP_THEME = "APP_THEME"
private const val SCHOOL_THEME = 0
private const val SPACE_THEME = 1

class SettingsFragment : Fragment() {
    private var _binding: SettingsFragmentBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        //Применяем тему
        activity?.setTheme(getAppTheme(R.style.SchoolTheme))
        _binding = SettingsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initChipGroup()

        binding.buttonOk.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    //Получить тему
    private fun getAppTheme(codeStyle: Int): Int {
        return codeStyleToStyleId(getCodeStyle(codeStyle))
    }

    //Ищем тему по номеру
    private fun codeStyleToStyleId(codestyle: Int): Int {
        return when (codestyle) {
            SCHOOL_THEME -> R.style.SchoolTheme
            SPACE_THEME -> R.style.SpaceTheme
            else -> R.style.SchoolTheme
        }
    }

    //Ищем код
    private fun getCodeStyle(codestyle: Int): Int {
        val sharedPref: SharedPreferences? = activity?.getSharedPreferences(NAME_SHARED_PREFERENCE, Context.MODE_PRIVATE)
        return sharedPref?.getInt(APP_THEME, codestyle)!!
    }

    //Инициализируем Chip
    private fun initChipGroup() {
        clickedChip(binding.schoolStyleButton, SCHOOL_THEME)
        clickedChip(binding.spaceStyleButton, SPACE_THEME)

        val b =
            binding.radioGroup.getChildAt(getCodeStyle(SCHOOL_THEME)) as MaterialRadioButton

        b.isChecked = true

    }

    //Обработка нажатия Chip
    private fun clickedChip(chip: View?, codestyle: Int) {
        chip?.setOnClickListener {
            setAppTheme(codestyle)
            activity?.recreate()
        }
    }

    //Записываем тему
    private fun setAppTheme(codestyle: Int) {
        val sharedPref: SharedPreferences? = activity?.getSharedPreferences(NAME_SHARED_PREFERENCE, Context.MODE_PRIVATE)
        val editor = sharedPref?.edit()
        editor?.putInt(APP_THEME, codestyle)
        editor?.apply()
    }

}