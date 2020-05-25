package com.example.ladm_u4_practica2_jonathanlopez

import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.view.View
import android.widget.Toast

class Lienzo(p:MainActivity): View(p),SensorEventListener{
    var punteroSprite : Sprite ?= null
    var flag=0
    var flag2 =0
    //rgb cielo
    var alphaCielo = 0
    //0 -> Noche
    //1 -> Día
    var statusCielo= 0

    var apuntador = p
    var sonic = Sprite(BitmapFactory.decodeResource(resources,R.drawable.status_0_right),425,1000)
    var fondoLogo = Sprite(BitmapFactory.decodeResource(resources,R.drawable.logo_fondo),150,200)
    var sonicLogo = Sprite(BitmapFactory.decodeResource(resources,R.drawable.logo_sonic),300,200)
    var sonicNombre = Sprite(BitmapFactory.decodeResource(resources,R.drawable.nombre),350,1350)
    private val SENSOR_SENSITIVITY = 4

    override fun onDraw(c: Canvas) {
        super.onDraw(c)
        var p = Paint()

        //CIELO

        //Noche
        p.color= Color.rgb(0,47,64)
        c.drawRect(0f,0f,width.toFloat(),height.toFloat(),p)
        //Dia
        p.color= Color.argb(alphaCielo,126,219,254)
        c.drawRect(0f,0f,width.toFloat(),height.toFloat(),p)


        //MONTAÑAS
        p.color= Color.rgb(111,150,46)
        c.drawCircle(-1250f     ,3550f,3000f,p)
        c.drawCircle(width+1350f,3550f,3000f,p)

        p.color= Color.rgb(149,201,63)
        c.drawCircle(540f,4000f,3000f,p)





            if(flag==0 ){
                if(flag2==1){
                    sonic.cambiarSprite(BitmapFactory.decodeResource(resources,R.drawable.status_0_right))
                }
                if(flag2==2){
                    sonic.cambiarSprite(BitmapFactory.decodeResource(resources,R.drawable.status_0_left))
                }

            }
        if(flag==3 ){
            if(flag2==1){
                sonic.cambiarSprite(BitmapFactory.decodeResource(resources,R.drawable.status_0_right))
            }
            if(flag2==2){
                sonic.cambiarSprite(BitmapFactory.decodeResource(resources,R.drawable.status_0_left))
            }

        }
        if(flag==4 ){
            if(flag2==1){
                sonic.cambiarSprite(BitmapFactory.decodeResource(resources,R.drawable.status_0_right))
            }
            if(flag2==2){
                sonic.cambiarSprite(BitmapFactory.decodeResource(resources,R.drawable.status_0_left))
            }

        }


            if(flag==1){
                sonic.cambiarSprite(BitmapFactory.decodeResource(resources,R.drawable.status_2_right))
                flag2=1
            }
            if(flag==2){
                sonic.cambiarSprite(BitmapFactory.decodeResource(resources,R.drawable.status_2_left))
                flag2=2
            }

        sonic.pintar(c,p)
        fondoLogo.pintar(c,p)

        if(statusCielo==0){
            sonicLogo.pintar(c,p)
            sonicNombre.pintar(c,p)
        }



    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onSensorChanged(sensor: SensorEvent) {
        if(sensor.sensor.getType() == Sensor.TYPE_PROXIMITY)
        {
            if (sensor.values[0] >= -SENSOR_SENSITIVITY && sensor.values[0] <= SENSOR_SENSITIVITY) {
                //near

                statusCielo=0

            } else {
                //far

                statusCielo=1

            }
            invalidate()
        }
        if(sensor.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            flag=sonic.validarAcelerometro(sensor.values[0].toInt(),width)
            if(sensor.values[0].toInt() >= -2 && sensor.values[0].toInt() <=-0.75){
            flag2=1
             }
            if(sensor.values[0].toInt() >0.75 && sensor.values[0].toInt() <=2){
            flag2=2
            }
            invalidate()
        }
    }

}

class Hilo(L:MainActivity) : Thread() {
    var puntero = L


    override fun run() {  //Se ejecuta una sola vez en segundo plano
        super.run()
        while (true) {

            sleep(25)
            if(puntero.lienzo!!.statusCielo==0){ //noche
                if(puntero.lienzo!!.alphaCielo>0){
                puntero.lienzo!!.alphaCielo-=5
                }
            }
            if(puntero.lienzo!!.statusCielo==1){ //noche
                if(puntero.lienzo!!.alphaCielo<255){
                    puntero.lienzo!!.alphaCielo+=5
                }
            }


        }
    }
}