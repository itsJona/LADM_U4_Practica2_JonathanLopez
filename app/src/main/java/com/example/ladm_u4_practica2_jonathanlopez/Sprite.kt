package com.example.ladm_u4_practica2_jonathanlopez

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.view.MotionEvent

class Sprite(){

    var x = 0f
    var y = 0f
    var radio = 0f
    var ancho = 0f
    var sprite : Bitmap?= null
    var incX = 10f
    var alto = 0f




    constructor(Bitmap : Bitmap, x:Int, y:Int) : this(){
        this.sprite = Bitmap
        this.y = y.toFloat()
        this.x = x.toFloat()
        ancho = sprite!!.width.toFloat()

    }

    fun cambiarSprite(Bitmap : Bitmap){
        this.sprite = Bitmap
    }


    fun pintar (c: Canvas, p: Paint){
        c.drawBitmap(sprite!!,x,y,p)
    }

    fun validarAcelerometro (acelerometer_x : Int,anchoPantalla :Int) :Int{
        //0 = Detenido
        //1 = Derecha
        //2 = Izquierda

        if(acelerometer_x<-1){
            if(x>(anchoPantalla- sprite!!.width)){return 3}
            x+= incX
            return 1
        }
        if(acelerometer_x<-4){
            if(x>(anchoPantalla- sprite!!.width)){return 3}
            x+= 20
            return 1
        }
        if(acelerometer_x>1){
            if(x<0){return 4}
            x-= incX
            return 2
        }

        return 0
    }





}
