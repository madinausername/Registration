package uz.itschool.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_new.*

class NewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)

        var sharedpreferences = getSharedPreferences("reg", MODE_PRIVATE)
        var edit = sharedpreferences.edit()
        var gson = Gson()
        var userList = mutableListOf<Users>()
        var type = object : TypeToken<List<Users>>() {}.type

        button_1.setOnClickListener {
            var users = sharedpreferences.getString("users", "")
            var pos = false

            if (users == "") {
                Toast.makeText(this, "Enter empty blanks!", Toast.LENGTH_SHORT).show()

            } else {
                userList = gson.fromJson(users, type)
                for (i in userList) {
                    if (i.login == text_input.text.toString() && i.password == number_password.text.toString()) {
                        pos = true
                        break
                    } else {
                        pos = false
                    }
                }

                if (pos == true) {
                    var intent = Intent(this, First_layout::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "You did not registered yet!", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}