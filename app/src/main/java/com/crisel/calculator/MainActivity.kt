package com.crisel.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal
import java.math.BigInteger

class MainActivity : AppCompatActivity(){
    var num1 :Long = 0
    var num2 :Long = 0
    var resul :Long = 0;

    var operacion = 0;

    var editing = 1;

    //0: Decimal, 1: Binario, 2: Hexadecimal
    var mode = 0;

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clear(botonClear)
    }

    fun clear(view: View){
        tf_operaciones.text = ""

        num1 = 0;
        tv_num1.text = ""


        tv_signo.text = ""

        operacion = 0
        editing = 1
        setOps(true)
    }

    fun bBinario(view: View){
        setDecButtons(false)
        setHexButtons(false)
        setModeButtons(true)
        botonBinario.isEnabled = false

        when(mode){
            0 -> {
                var num = Integer.toBinaryString(getWritedNum().toInt())
                tf_operaciones.text = num

                num = Integer.toBinaryString(getSavedNum().toInt())
                tv_num1.text = num
            }
            2 ->{
                var numHexa = tf_operaciones.text.toString()

                val numDecimal = numHexaANumDecimal(numHexa)

                numHexa = tv_num1.text.toString()

                val savedDecimal = numHexaANumDecimal(numHexa)

                var num = Integer.toBinaryString(numDecimal.toInt())
                tf_operaciones.text = num

                num = Integer.toBinaryString(savedDecimal.toInt())
                tv_num1.text = num
            }
        }

        mode = 1
    }

    fun bDecimal(view: View){
        setDecButtons(true)
        setHexButtons(false)
        setModeButtons(true)
        botonDecimal.isEnabled = false;

        when(mode){
            1 -> {
                var numBinario = getWritedNum()

                val numDecimal = numBinarioANumDecimal(numBinario)

                numBinario = getSavedNum()

                val savedDecimal = numBinarioANumDecimal(numBinario)

                tf_operaciones.text = numDecimal.toString()
                tv_num1.text = savedDecimal.toString()}

            2 -> {
                var numHexa = tf_operaciones.text.toString()

                val numDecimal = numHexaANumDecimal(numHexa)

                numHexa = tv_num1.text.toString()

                val savedDecimal = numHexaANumDecimal(numHexa)

                tf_operaciones.text = numDecimal.toString()
                tv_num1.text = savedDecimal.toString()
            }
            //2 -> hexaADecimal()
        }

        mode = 0
    }

    fun bHexa(view: View){
        setDecButtons(true)
        setHexButtons(true)
        setModeButtons(true)
        botonHexadecimal.isEnabled = false

        when(mode){
            0->{
                var num = Integer.toHexString(getWritedNum().toInt())
                tf_operaciones.text = num

                num = Integer.toHexString(getSavedNum().toInt())
                tv_num1.text = num
            }

            1->{
                var numBinario = getWritedNum()

                val numDecimal = numBinarioANumDecimal(numBinario)

                numBinario = getSavedNum()

                val savedDecimal = numBinarioANumDecimal(numBinario)

                var num = Integer.toHexString(numDecimal.toInt())
                tf_operaciones.text = num

                num = Integer.toHexString(savedDecimal.toInt())
                tv_num1.text = num
            }
        }

        mode = 2
    }

    fun numHexaANumDecimal(num:String): Long {
        var i = num.length - 1
        var decimalN: Long = 0
        var base = 1
        while(i >= 0) {
            val charAtPos = num[i]

            val lastDigit = if((charAtPos >= '0') && (charAtPos <= '9')) {
                charAtPos - '0'
            } else if((charAtPos >= 'A') && (charAtPos <= 'F')) {
                charAtPos.toInt() - 55
            } else if((charAtPos >= 'a') && (charAtPos <= 'f')) {
                charAtPos.toInt() - 87
            } else {
                0
            }

            decimalN += lastDigit * base
            base *= 16

            i--
        }

        return decimalN
    }

    fun numBinarioANumDecimal(num:Long): Long {
        var numBinario = num
        var resto :Long

        var nDecimal :Long = 0
        var power = 0

        while (numBinario > 0) {
            val r = numBinario % 10
            nDecimal = (nDecimal + r * Math.pow(2.0, power.toDouble())).toLong()
            numBinario /= 10
            power++
        }

        return nDecimal
    }

    fun getWritedNum(): Long {
        if(tf_operaciones.length()>0){
            return tf_operaciones.text.toString().toLong()
        } else {
            return 0;
        }
    }

    fun getSavedNum(): Long {
        if(tv_num1.length()>0){
            return tv_num1.text.toString().toLong()
        } else {
            return 0;
        }
    }

    fun setOps (set:Boolean){
        botonSuma.isEnabled = set
        botonResta.isEnabled = set
        botonDivi.isEnabled = set
        botonMulti.isEnabled = set
    }

    fun setDecButtons(set:Boolean){
        boton0.isEnabled = set
        boton1.isEnabled = set
        boton2.isEnabled = set
        boton3.isEnabled = set
        boton4.isEnabled = set
        boton5.isEnabled = set
        boton6.isEnabled = set
        boton7.isEnabled = set
        boton8.isEnabled = set
        boton9.isEnabled = set
    }

    fun setHexButtons(set:Boolean){
        botonA.isEnabled = set
        botonB.isEnabled = set
        botonC.isEnabled = set
        botonD.isEnabled = set
        botonE.isEnabled = set
        botonF.isEnabled = set
    }

    fun setModeButtons(set:Boolean){
        botonHexadecimal.isEnabled = set
        botonDecimal.isEnabled = set
        botonBinario.isEnabled = set
    }

    fun bot0(view: View){
        tf_operaciones.append("0"); botonIgual.isEnabled = true
    }

    fun bot1(view: View){
        tf_operaciones.append("1"); botonIgual.isEnabled = true
    }
    fun bot2(view: View){
        tf_operaciones.append("2"); botonIgual.isEnabled = true
    }
    fun bot3(view: View){
        tf_operaciones.append("3"); botonIgual.isEnabled = true
    }
    fun bot4(view: View){
        tf_operaciones.append("4"); botonIgual.isEnabled = true
    }
    fun bot5(view: View){
        tf_operaciones.append("5"); botonIgual.isEnabled = true
    }
    fun bot6(view: View){
        tf_operaciones.append("6"); botonIgual.isEnabled = true
    }
    fun bot7(view: View){
        tf_operaciones.append("7"); botonIgual.isEnabled = true
    }
    fun bot8(view: View){
        tf_operaciones.append("8"); botonIgual.isEnabled = true
    }
    fun bot9(view: View){
        tf_operaciones.append("9"); botonIgual.isEnabled = true
    }

    fun botA(view: View){
        tf_operaciones.text = tf_operaciones.text.toString() + "a"; botonIgual.isEnabled = true
    }
    fun botB(view: View){
        tf_operaciones.text = tf_operaciones.text.toString() + "b"; botonIgual.isEnabled = true
    }
    fun botC(view: View){
        tf_operaciones.text = tf_operaciones.text.toString() + "c"; botonIgual.isEnabled = true
    }
    fun botD(view: View){
        tf_operaciones.text = tf_operaciones.text.toString() + "d"; botonIgual.isEnabled = true
    }
    fun botE(view: View){
        tf_operaciones.text = tf_operaciones.text.toString() + "e"; botonIgual.isEnabled = true
    }
    fun botF(view: View){
        tf_operaciones.text = tf_operaciones.text.toString() + "f"; botonIgual.isEnabled = true
    }
    fun bot1Alt(view:View){
        tf_operaciones.append("1");  botonIgual.isEnabled = true
    }

    fun botSuma(view:View){
        setOps(false)
        botonIgual.isEnabled = false

        when(mode){
            0,1->{num1 = getWritedNum(); tv_num1.setText(num1.toString())}
            2-> {num1 = numHexaANumDecimal(tf_operaciones.text.toString()); tv_num1.setText(tf_operaciones.text.toString())}
        }


        editing = 2;
        operacion = 1
        tf_operaciones.setText("")
        tv_signo.setText("+")
    }

    fun botResta(view:View){
        setOps(false)
        botonIgual.isEnabled = false

        when(mode){
            0,1->{num1 = getWritedNum(); tv_num1.setText(num1.toString())}
            2-> {num1 = numHexaANumDecimal(tf_operaciones.text.toString()); tv_num1.setText(tf_operaciones.text.toString())}
        }

        operacion = 2
        editing = 2;
        tf_operaciones.setText("0")
        tv_signo.setText("-")
    }

    fun botMulti(view: View){
        setOps(false)
        botonIgual.isEnabled = false
        when(mode){
            0,1->{num1 = getWritedNum(); tv_num1.setText(num1.toString())}
            2-> {num1 = numHexaANumDecimal(tf_operaciones.text.toString()); tv_num1.setText(tf_operaciones.text.toString())}
        }

        operacion = 3
        editing = 2;
        tf_operaciones.setText("")
        tv_signo.setText("*")
    }

    fun botDivi(view: View){
        setOps(false)
        botonIgual.isEnabled = false
        when(mode){
            0,1->{num1 = getWritedNum(); tv_num1.setText(num1.toString())}
            2-> {num1 = numHexaANumDecimal(tf_operaciones.text.toString()); tv_num1.setText(tf_operaciones.text.toString())}
        }

        operacion = 4
        editing = 2;
        tf_operaciones.setText("")
        tv_signo.setText("/")
    }

    fun botonIgual(view: View){
        setOps(true)
        botonIgual.isEnabled = false

        when(mode){
            0,1->{num2 = getWritedNum()}
            2-> {num2 = numHexaANumDecimal(tf_operaciones.text.toString())}
        }

        when(mode){
            1 -> {
                num2 = numBinarioANumDecimal(num2)
                num1 = numBinarioANumDecimal(num1)
            }
            //TODO hexadecimal (2)
        }

        when(operacion){
            1 -> resul = num1 + num2
            2 -> resul = num1 - num2
            3 -> resul = num1 * num2
            4 -> resul = num1 / num2
        }


        when(mode){
            1 -> resul = Integer.toBinaryString(resul.toInt()).toLong()
            //TODO Hexadecimal (2)
        }

            clear(botonClear)

            num1 = resul

            when(mode){
                0,1->{
                    tf_operaciones.setText(resul.toString())
                }
                2->{
                    tf_operaciones.text = Integer.toHexString(resul.toInt())
                }
            }

            tv_num1.setText("")
            tv_signo.setText("")
            editing = 1
            operacion = 0
            resul = 0
    }
}