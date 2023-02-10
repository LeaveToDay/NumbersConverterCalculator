package com.staynight.numbersconvertercalculator

import android.os.Bundle
import android.text.method.DigitsKeyListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import com.staynight.numbersconvertercalculator.databinding.FragmentOperationsBinding

class OperationsFragment : Fragment() {
    private var _binding: FragmentOperationsBinding? = null
    private val binding get() = _binding

    private val digitsTypes =
        mapOf(
            Pair("Binary", "01."),
            Pair("Octal", "01234567."),
            Pair("Decimal", "0123456789."),
            Pair("Hexadecimal", "0123456789ABCDEF.")
        )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOperationsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
    }

    private fun setupListeners() {
        binding?.apply {
            btnCalculate.setOnClickListener {
                when (binding?.rgFrom?.findViewById<RadioButton>(binding?.rgFrom?.checkedRadioButtonId!!)?.text) {
                    "Binary" -> {
                        when (binding?.rgOperations?.findViewById<RadioButton>(binding?.rgOperations?.checkedRadioButtonId!!)?.text) {
                            "+" -> {
                                if (!etInputFirst.text.isNullOrEmpty() && !etInputSecond.text.isNullOrEmpty())
                                    tvCalculateResult.text =
                                        addBinary(
                                            etInputFirst.text.toString(),
                                            etInputSecond.text.toString()
                                        )
                            }
                            "-" -> {
                                if (!etInputFirst.text.isNullOrEmpty() && !etInputSecond.text.isNullOrEmpty())
                                    tvCalculateResult.text =
                                        subtractBinary(
                                            etInputFirst.text.toString(),
                                            etInputSecond.text.toString()
                                        )
                            }
                            "*" -> {
                                if (!etInputFirst.text.isNullOrEmpty() && !etInputSecond.text.isNullOrEmpty())
                                    tvCalculateResult.text =
                                        multiplyBinary(
                                            etInputFirst.text.toString(),
                                            etInputSecond.text.toString()
                                        )
                            }

                            "/" -> {
                                if (!etInputFirst.text.isNullOrEmpty() && !etInputSecond.text.isNullOrEmpty())
                                    tvCalculateResult.text =
                                        divideBinary(
                                            etInputFirst.text.toString(),
                                            etInputSecond.text.toString()
                                        )
                            }
                        }
                    }
                    "Octal" -> {
                        when (binding?.rgOperations?.findViewById<RadioButton>(binding?.rgOperations?.checkedRadioButtonId!!)?.text) {
                            "+" -> {
                                if (!etInputFirst.text.isNullOrEmpty() && !etInputSecond.text.isNullOrEmpty())
                                    tvCalculateResult.text =
                                        addOctal(
                                            etInputFirst.text.toString(),
                                            etInputSecond.text.toString()
                                        )
                            }
                            "-" -> {
                                if (!etInputFirst.text.isNullOrEmpty() && !etInputSecond.text.isNullOrEmpty())
                                    tvCalculateResult.text =
                                        subtractOctal(
                                            etInputFirst.text.toString(),
                                            etInputSecond.text.toString()
                                        )
                            }
                            "*" -> {
                                if (!etInputFirst.text.isNullOrEmpty() && !etInputSecond.text.isNullOrEmpty())
                                    tvCalculateResult.text =
                                        multiplyOctal(
                                            etInputFirst.text.toString(),
                                            etInputSecond.text.toString()
                                        )
                            }

                            "/" -> {
                                if (!etInputFirst.text.isNullOrEmpty() && !etInputSecond.text.isNullOrEmpty())
                                    tvCalculateResult.text =
                                        divideOctal(
                                            etInputFirst.text.toString(),
                                            etInputSecond.text.toString()
                                        )
                            }
                        }
                    }
                    "Hexadecimal" -> {
                        when (binding?.rgOperations?.findViewById<RadioButton>(binding?.rgOperations?.checkedRadioButtonId!!)?.text) {
                            "+" -> {
                                if (!etInputFirst.text.isNullOrEmpty() && !etInputSecond.text.isNullOrEmpty())
                                    tvCalculateResult.text =
                                        addHex(
                                            etInputFirst.text.toString(),
                                            etInputSecond.text.toString()
                                        )
                            }
                            "-" -> {
                                if (!etInputFirst.text.isNullOrEmpty() && !etInputSecond.text.isNullOrEmpty())
                                    tvCalculateResult.text =
                                        subtractHex(
                                            etInputFirst.text.toString(),
                                            etInputSecond.text.toString()
                                        )
                            }
                            "*" -> {
                                if (!etInputFirst.text.isNullOrEmpty() && !etInputSecond.text.isNullOrEmpty())
                                    tvCalculateResult.text =
                                        multiplyHex(
                                            etInputFirst.text.toString(),
                                            etInputSecond.text.toString()
                                        )
                            }

                            "/" -> {
                                if (!etInputFirst.text.isNullOrEmpty() && !etInputSecond.text.isNullOrEmpty())
                                    tvCalculateResult.text =
                                        divideHex(
                                            etInputFirst.text.toString(),
                                            etInputSecond.text.toString()
                                        )
                            }
                        }
                    }
                }
            }
        }

        binding?.rgFrom?.setOnCheckedChangeListener { group, checkedId ->
            binding?.etInputFirst?.setText("")
            binding?.etInputSecond?.setText("")
            setInputDigits(group.findViewById<RadioButton>(checkedId).text.toString())
        }
    }

    private fun setInputDigits(digits: String) {
        binding?.etInputFirst?.keyListener =
            digitsTypes[digits]?.let { DigitsKeyListener.getInstance(it) }

        binding?.etInputSecond?.keyListener =
            digitsTypes[digits]?.let { DigitsKeyListener.getInstance(it) }
    }
}