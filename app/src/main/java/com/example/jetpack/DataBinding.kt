package com.example.jetpack

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.jetpack.databinding.ActivityDataBindingBinding
import kotlinx.android.synthetic.main.activity_data_binding.*


class DataBinding : AppCompatActivity() {
    private lateinit var binding : ActivityDataBindingBinding
    var result:String = ""
    var res:String = ""


        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

            




            binding  = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);
            binding.apply {

                val options = arrayOf("Android","Angular","React","IOS")
                spinner.adapter = ArrayAdapter<String>(this@DataBinding,android.R.layout.simple_list_item_1,options)
                spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }

                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        res = options.get(position)
                    }



                }


            btnSave.setOnClickListener {

                if(pizza!!.isChecked){
                    result += "Pizza"
                }
                if(coffee!!.isChecked){
                    result += "Coffee"
                }
                if(burger!!.isChecked){
                    result += "Burger"
                }
                val radioGender = when(radioMale.isChecked) {
                    true -> radioMale
                    false -> radioFemale
                }

                val dataBind = Employee(toStr(editEmpName),toText(radioGender),result,res)
                binding.dataBind = dataBind
                    }

        }
    }

    private fun toStr(editText: EditText): String? {
        return editText.text.toString()
    }

    private fun toText(radioButton: RadioButton): String? {
        return radioButton.text.toString()
    }

    private fun toast(msg:String){
        return Toast.makeText(applicationContext,msg,Toast.LENGTH_SHORT).show()
    }
}