package com.example.gpacalculator

import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.new_item.view.*
import android.widget.Spinner
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.view.*
import java.util.ArrayList


class MainActivity : AppCompatActivity() {
public val subjects  = arrayOf("Art of computing","Calculus 2", "Calculus 1", "Linear algebra", "Discreet", "Mobil Programming" ,
        "Database Management System", "System Programming", "Software Engineering", "Turkish 1","Turkish 2", "History 1" ,
         "History 2", "Embedded", "Object Oriented Programming", "Algorithm Thinking" , "Data Structures" ,"Machine Learning",
    "Physics","Chemistry", "Biology")

public var allsubjects : ArrayList<subjects> = ArrayList(5)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button2.visibility = View.INVISIBLE

        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, subjects )
        text.setAdapter(adapter)

        button1.setOnClickListener {
            if(!text.text.isNullOrEmpty()){
                var inflater = LayoutInflater.from(this)

                var newclass: View = inflater.inflate(R.layout.new_item,null)

                newclass.new_text.setAdapter(adapter)

                var name_class = text.text.toString()
                var credit = spinner1.selectedItem.toString()
                var letter = spinner2.selectedItem.toString()

                newclass.new_text.setText(name_class)
                newclass.new_spinner1.setSelection(find_index(spinner1,credit))
                newclass.new_spinner2.setSelection(find_index(spinner2,letter))



                newclass.new_button1.setOnClickListener {
                    scroll_root.removeView(newclass)
                    if(scroll_root.childCount == 0){
                        button2.visibility = View.INVISIBLE
                    }else{
                        button2.visibility = View.VISIBLE
                    }

                }
                scroll_root.addView(newclass)
                button2.visibility = View.VISIBLE

                text.setText("")
                spinner1.setSelection(0)
                spinner2.setSelection(0)

            }
            else{
                Toast.makeText(this, "Enter your class name", Toast.LENGTH_LONG).show()
            }

            }
        button2.setOnClickListener {
            calculate(scroll_root)
        }



        }



    fun calculate(view: View)  {
        var sum_credit : Int = 0
        var total_not : Double = 0.0

       for(i in 0..scroll_root.childCount-1){

           var line = scroll_root.getChildAt(i)

            var bufferline = subjects( (line.new_spinner1.selectedItemPosition) +1, line.new_spinner2.selectedItem.toString())

           allsubjects.add(bufferline)

       }
        for(j in allsubjects){
            sum_credit += j.subject_credit

            total_not += j.subject_credit * letter_to_double(j.subject_grade)
        }




        Toast.makeText(this, "gpa is ${String.format("%.2f",total_not /sum_credit)}", Toast.LENGTH_LONG).show()

        allsubjects.clear()

    }

    fun find_index(spinner: Spinner, a: String) : Int{
      var index = 0

    for(i in 0..spinner.count){

        if(spinner.getItemAtPosition(i).toString().equals(a)){
            index = i
            break
        }

    }
        return index
    }

    fun letter_to_double(a : String) : Double {
        var result: Double = 0.0

        when (a) {
            "A+" -> result = 4.3
            "A" -> result = 4.0
            "A-" -> result = 3.7
            "B+" -> result = 3.3
            "B" -> result = 3.0
            "B-" -> result = 2.7
            "C+" -> result = 2.3
            "C" -> result = 2.0
            "C-" -> result = 1.7
            "D+" -> result = 1.3
            "D" -> result = 1.0
            "F" -> result = 0.0

        }
        return result


    }

}