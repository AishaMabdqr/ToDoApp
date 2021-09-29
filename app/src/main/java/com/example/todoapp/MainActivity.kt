package com.example.todoapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    lateinit var rvMain : RecyclerView
    lateinit var addButton : FloatingActionButton
    lateinit var itemList : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemList = ArrayList()
        addButton = findViewById(R.id.addButton)

        rvMain = findViewById(R.id.rvMain)
        rvMain.adapter = RVAdapter(itemList)
        rvMain.layoutManager = LinearLayoutManager(this)

        addButton.setOnClickListener{
         customAlert()
        }

    }

    fun customAlert(){

        val dialogBuilder = AlertDialog.Builder(this)
        val input = EditText (this)
        dialogBuilder.setMessage("Enter Your item:")
            .setPositiveButton("Add",DialogInterface.OnClickListener{
                    dialog, id -> itemList.add(input.text.toString())
                })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener{
                dialog, id -> dialog.cancel()
            })

        val alert = dialogBuilder.create()
        alert.setTitle("New Item: ")
        alert.setView(input)
        alert.show()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.deleteIcon ->{
                Toast.makeText(this, " This will be deleted ", Toast.LENGTH_LONG).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}