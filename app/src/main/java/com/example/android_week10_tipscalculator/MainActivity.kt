package com.example.android_week10_tipscalculator

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat



class MainActivity : AppCompatActivity() {
    //Meal Cost Amount value, to be set by user
    var mealCost:Double = 0.0
    //Tip Total
    var tipTotal:Double = 0.0

    var tipString:String = ""






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        //On Screen Elements
        val costEntry:EditText = findViewById(R.id.costEntry)
        val tipSelector:Spinner = findViewById(R.id.tipChoices)
        val tipResult:TextView = findViewById(R.id.tipOutputTxtView)

        //Goal: Streamlined Tip Entry


        costEntry.setOnKeyListener(View.OnKeyListener { v, keyCode, event -> // If the event is a key-down event on the "enter" button
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                // Perform action on key press
                val tipChoice:String = tipSelector.selectedItemId.toString()
                println("hi!")
//                //Pass Text and choice to Function

                calculateTipAmount(tipChoice,costEntry.text.toString())
                //Set the Output Text

                tipResult.text = "With A ${tipSelector.selectedItem.toString()}, Your Tip Comes Out To be: $$tipString"

//                Toast.makeText(this@HelloFormStuff, edittext.getText(), Toast.LENGTH_SHORT).show()
                return@OnKeyListener true
            }
            false
        })


        //TODO On spinner select, calculate whatever the current meal cost label string is.
        tipSelector.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                //Check if the text is none, if nothing do nothing
                if(costEntry.text.toString().isBlank()){
                    println("No Text, Do Nothing")
                }else{
                    println("Selected")
                    val tipChoice:String = tipSelector.selectedItemId.toString()
                    calculateTipAmount(tipChoice,costEntry.text.toString() )
                    tipResult.text = "With A ${tipSelector.selectedItem.toString()}, Your Tip Comes Out To be: $$tipString"

                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //Nothing to be used
            }

        }



















        //Todo Make Spinner Selection Automatically Calculate Change











    }

    /*
    Function takes in the tip choice of the spinner and
    and switches is out for the appropriate calculation type
    also calculates Tip Cost and formats it to US
     */
    private fun calculateTipAmount(tipChoice:String, mealCost:String){
        println("TIP CHOICE: $tipChoice, MEAL: $mealCost")

        when(tipChoice){
            //calculate tip at 5%
            "0"-> tipTotal = 0.05 * mealCost.toDouble()
            "1" -> tipTotal = 0.10 * mealCost.toDouble()
            "2" -> tipTotal = 0.15 * mealCost.toDouble()
            "3" -> tipTotal = 0.20 * mealCost.toDouble()
        }
        val decimalFormat = DecimalFormat("0.00")

        tipString = decimalFormat.format(tipTotal);






    }

}