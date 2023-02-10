package com.staynight.numbersconvertercalculator

import android.os.Bundle
import android.text.method.DigitsKeyListener
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.staynight.numbersconvertercalculator.databinding.FragmentConverterBinding

class ConverterFragment : Fragment() {
    private var _binding: FragmentConverterBinding? = null
    private val binding get() = _binding

    private val digitsTypes =
        mapOf(
            Pair("Binary", "01."),
            Pair("Octal", "01234567."),
            Pair("Decimal", "0123456789."),
            Pair("Hexadecimal", "0123456789ABCDEF.")
        )

    private val decimalBtn by lazy { RadioButton(context) }
    private val octalBtn by lazy { RadioButton(context) }
    private val binaryBtn by lazy { RadioButton(context) }
    private val hexadecimalBtn by lazy { RadioButton(context) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConverterBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()



        decimalBtn.text = "Decimal"
        octalBtn.text = "Octal"
        hexadecimalBtn.text = "Hexadecimal"
        binaryBtn.text = "Binary"
    }

    private fun setupListeners() {
        binding?.rgFrom?.setOnCheckedChangeListener { group, checkedId ->
            binding?.etInput?.setText("")
            setInputDigits(group.findViewById<RadioButton>(checkedId).text.toString())

            when (group.findViewById<RadioButton>(checkedId).text) {
                "Binary" -> {
                    binding?.rgTo?.removeAllViews()
                    binding?.rgTo?.addView(decimalBtn)
                }
                "Octal" -> {
                    binding?.rgTo?.removeAllViews()
                    binding?.rgTo?.addView(decimalBtn)
                }
                "Decimal" -> {
                    binding?.rgTo?.removeAllViews()
                    binding?.rgTo?.addView(binaryBtn)
                    binding?.rgTo?.addView(octalBtn)
                    binding?.rgTo?.addView(hexadecimalBtn)
                }
                "Hexadecimal" -> {
                    binding?.rgTo?.removeAllViews()
                    binding?.rgTo?.addView(decimalBtn)
                }
            }

        }

        binding?.rgFrom?.get(2)?.id?.let { binding?.rgFrom?.check(it) }

        binding?.rgTo?.setOnCheckedChangeListener { group, checkedId ->
            binding?.btnConvert?.isEnabled = true
            binding?.tvConvertResult?.text = ""
        }

        binding?.btnConvert?.setOnClickListener {
            when (binding?.rgFrom?.findViewById<RadioButton>(binding?.rgFrom?.checkedRadioButtonId!!)?.text) {
                "Binary" -> {
                    binding?.tvConvertResult?.text =
                        binding?.etInput?.text?.toString()?.binToDec()
                }
                "Octal" -> {
                    binding?.tvConvertResult?.text =
                        binding?.etInput?.text?.toString()?.octalToDec()
                }
                "Decimal" -> {
                    when (binding?.rgTo?.findViewById<RadioButton>(binding?.rgTo?.checkedRadioButtonId!!)?.text) {
                        "Binary" -> {
                            binding?.tvConvertResult?.text =
                                binding?.etInput?.text?.toString()?.toDouble()?.decToBin()
                        }
                        "Octal" -> {
                            binding?.tvConvertResult?.text =
                                binding?.etInput?.text?.toString()?.toDouble()?.decToOctal()
                        }
                        "Hexadecimal" -> {
                            binding?.tvConvertResult?.text =
                                binding?.etInput?.text?.toString()?.toDouble()?.decToHex()
                        }
                    }
                }
                "Hexadecimal" -> {
                    binding?.tvConvertResult?.text =
                        binding?.etInput?.text?.toString()?.hexToDec()
                }
            }
        }
    }

    private fun setInputDigits(digits: String) {
        binding?.etInput?.keyListener =
            digitsTypes[digits]?.let { DigitsKeyListener.getInstance(it) }
    }
}