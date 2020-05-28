package co.example.dutchpaycalculator

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.doAfterTextChanged
import co.example.dutchpaycalculator.R
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.appcompat.v7.Appcompat
import org.jetbrains.anko.sdk27.coroutines.textChangedListener
import org.jetbrains.anko.toast
import java.lang.Integer.sum
import java.text.NumberFormat

class MainActivity : AppCompatActivity(){



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()


        button2.setOnClickListener {
            CloseKeyboard()
            val builder = AlertDialog.Builder(this)
            builder.setMessage("정말 지우시겠습니까?")
                .setPositiveButton("예",
                    DialogInterface.OnClickListener { dialog, id ->
                        feone.text = null
                        fetwo.text = null
                        fethree.text = null
                        fefour.text = null
                        fefive.text = null
                        peoplenum.text = null
                        result.text = null
                        saveData(0,0,0,0,0,0,"")
                    })
                .setNegativeButton("아니오",
                    DialogInterface.OnClickListener { dialog, id ->
                        // User cancelled the dialog
                    })
            // Create the AlertDialog object and return it
            builder.create().show()

        }

        background.setOnClickListener{
            CloseKeyboard()
        }


        button.setOnClickListener {
            CloseKeyboard()
            var a = feone.text.toString().toIntOrNull()?:0
            var b = fetwo.text.toString().toIntOrNull()?:0
            var c = fethree.text.toString().toIntOrNull()?:0
            var d = fefour.text.toString().toIntOrNull()?:0
            var e = fefive.text.toString().toIntOrNull()?:0
            var n = peoplenum.text.toString().toIntOrNull()?:0
            var sum =a+b+c+d+e
            if (n==0) {
                result.text = "잘못된 계산입니다."
                peoplenum.text= null
                var n = peoplenum.text.toString().toIntOrNull()?:0
            } else {
                var aaa = sum/n
                var num = Math.ceil(aaa.toDouble() / 100) * 100
                var nu = num.toInt()
                var nus = NumberFormat.getIntegerInstance().format(nu)

                if (nu == 0) {
                    result.text = "다른 값을 입력해주세요."
                } else if (nu == 100) {
                    result.text = "잘못된 계산입니다."
                } else {
                    result.text = "한 명당 ${nus}원 입니다."
                }
            }
            saveData(a,b,c,d,e,n,result.text.toString())
        }


    }
    open fun saveData(A: Int ,B:Int,C:Int,D:Int,E:Int, F:Int, H :String) {
        val pref = this.getPreferences(0)
        val editor = pref.edit()
        editor.putInt("_ONE",A)
        editor.putInt("_TWO",B)
        editor.putInt("_THREE",C)
        editor.putInt("_FOUR",D)
        editor.putInt("_FIVE",E)
        editor.putInt("_PEOPLE",F)
        editor.putString("_STR",H)
        editor.apply()
    }
    open fun loadData() {
        val pref = this.getPreferences(0)
        var a = pref.getInt("_ONE",0)
        var b = pref.getInt("_TWO",0)
        var c = pref.getInt("_THREE",0)
        var d = pref.getInt("_FOUR",0)
        var e = pref.getInt("_FIVE",0)
        var n = pref.getInt("_PEOPLE",0)
        var h = pref.getString("_STR","")

        if (a!=0){
            feone.setText(a.toString())
        }
        if (b!=0) {
            fetwo.setText(b.toString())
        }
        if (c!=0) {
            fethree.setText(c.toString())
        }
        if (d!=0) {
            fefour.setText(d.toString())
        }
        if (e!=0) {
            fefive.setText(e.toString())
        }
        if (n!=0) {
            peoplenum.setText(n.toString())
        }
        if (n!=0) {
            result.setText(h.toString())
        }


    }

    override fun onRestart() {
        loadData()
        super.onRestart()
    }

    override fun onStop() {
        var a = feone.text.toString().toIntOrNull()?:0
        var b = fetwo.text.toString().toIntOrNull()?:0
        var c = fethree.text.toString().toIntOrNull()?:0
        var d = fefour.text.toString().toIntOrNull()?:0
        var e = fefive.text.toString().toIntOrNull()?:0
        var n = peoplenum.text.toString().toIntOrNull()?:0

        saveData(a,b,c,d,e,n,result.text.toString())
        super.onStop()
    }
    open fun CloseKeyboard()
    {
        var view = this.currentFocus

        if(view != null)
        {
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

}