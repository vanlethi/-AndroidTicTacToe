package com.example.androidtictactoe

import android.media.AsyncPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var tv_message = findViewById<TextView>(R.id.text_view_message)
        var btn_reset = findViewById<Button>(R.id.button_reset)
        var btn1 = findViewById<Button>(R.id.button_1)
        var btn2 = findViewById<Button>(R.id.button_2)
        var btn3 = findViewById<Button>(R.id.button_3)
        var btn4 = findViewById<Button>(R.id.button_4)
        var btn5 = findViewById<Button>(R.id.button_5)
        var btn6 = findViewById<Button>(R.id.button_6)
        var btn7 = findViewById<Button>(R.id.button_7)
        var btn8 = findViewById<Button>(R.id.button_8)
        var btn9 = findViewById<Button>(R.id.button_9)

        var array_btn = arrayOf<Button>(btn1,btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9)
        for (btn in array_btn){
            onChecked(btn,tv_message,array_btn)
        }
        btn_reset.text ="Reset"
        btn_reset.setOnClickListener {
            reset(array_btn, tv_message)
        }
    }

    fun onChecked(button: Button,message: TextView,array_btn: Array<Button>){
        button.setOnClickListener {
            var btn_text = button.getText().toString()
            if(btn_text !== ""){
                message.text = "This box is already checked" }
            else {
                setValueOnCheck(button)
                message.text = "" }
            checkFindWinner(array_btn, message)
        }
    }
    var count:Int = 0

    fun setValueOnCheck(button: Button){
        if(count%2 == 0)button.text ="X"
        else button.text ="O"
        count++
    }


    fun checkFindWinner(array_btn: Array<Button>, message: TextView){
        var group_array =arrayOf(
            arrayOf<Button>(array_btn[0],array_btn[1],array_btn[2]),
            arrayOf<Button>(array_btn[3],array_btn[4],array_btn[5]),
            arrayOf<Button>(array_btn[6],array_btn[7],array_btn[8]),
            arrayOf<Button>(array_btn[0],array_btn[3],array_btn[6]),
            arrayOf<Button>(array_btn[1],array_btn[4],array_btn[7]),
            arrayOf<Button>(array_btn[2],array_btn[5],array_btn[8]),
            arrayOf<Button>(array_btn[2],array_btn[4],array_btn[6]),
            arrayOf<Button>(array_btn[0],array_btn[4],array_btn[8]))
        for (array_child in group_array){
            checkEachCondition(array_child,array_btn,message,"X")
            checkEachCondition(array_child,array_btn,message,"O")
        }
    }

    var isWin = 0
    fun checkEachCondition(array_child_btn: Array<Button>,array_parent_btn: Array<Button>, message: TextView, player: String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Tic Tac Toe message")
        if(array_child_btn[0].getText().toString().equals(player) && array_child_btn[1].getText().toString().equals(player) && array_child_btn[2].getText().toString().equals(player)){
            builder.setMessage(player+", You are a Winner")
            builder.setPositiveButton("New match"){dialog, which ->
                Toast.makeText(applicationContext,"Ok, You have entered the new match.",Toast.LENGTH_SHORT).show()
                reset(array_parent_btn,message)
            }
            builder.show()
        }else isWin ++
        if(isWin ===140){
            builder.setMessage("Game over")
            builder.setPositiveButton("New match"){dialog, which ->
                Toast.makeText(applicationContext,"Ok, You have entered the new match.",Toast.LENGTH_SHORT).show()
                reset(array_parent_btn,message)
            }
            builder.show()
        }
    }

    fun reset(array_btn: Array<Button>, message: TextView){
        for(btn in array_btn){
            btn.text = ""
            count =0
            isWin=0
        }
        message.text=""
    }
}
