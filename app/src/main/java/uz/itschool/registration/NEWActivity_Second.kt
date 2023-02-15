package uz.itschool.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_new.*

class NEWActivity_Second : AppCompatActivity() {
    var pos:Boolean=true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newsecond)
        var sharedpreferences=getSharedPreferences("reg", MODE_PRIVATE)
        var edit=sharedpreferences.edit()
        var gson= Gson()
        var userList= mutableListOf<Users>()
        var type=object : TypeToken<List<Users>>(){}.type

        button_1.setOnClickListener {


            var users = sharedpreferences.getString("users","")
            var pos=false
            if (users==""){
                userList = mutableListOf()
                userList.add(Users(text_input.text.toString(),number_password.text.toString()))
                Toast.makeText(this,"Succesfully registered", Toast.LENGTH_SHORT).show()
                val str = gson.toJson(userList)
                edit.putString("users", str).commit()

            }else{
                userList = gson.fromJson(users,type)
                for( i in userList){
                    if(i.login!=text_input.text.toString() && i.password!=number_password.text.toString()){
                        pos=true
                    }
                    else{
                        pos=false
                        break
                    }

                }
                if(pos==true){
                    userList.add(Users(text_input.text.toString(),number_password.text.toString()))
                    Toast.makeText(this,"Succesfully registered", Toast.LENGTH_SHORT).show()
                    val str = gson.toJson(userList)
                    edit.putString("users", str).commit()
                    var intent= Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(this,"Change inputs!", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}