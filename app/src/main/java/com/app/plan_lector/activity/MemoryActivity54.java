package com.app.plan_lector.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.plan_lector.R;

import java.util.Random;


public class MemoryActivity54 extends ActionBarActivity implements Runnable {

    private TextView lblPuntaje,lblFallas,logo;

    private int puntaje,fallas;
    private ImageView img1,img2,img3,img4,img5,img6,img7,img8,img9,img10,
            img11,img12,img13,img14,img15,img16,img17,img18,img19,img20;
    private Button botonSalir,botonReiniciar;
    private int []valores={1,2,3,4,5,6,7,8,9,10,1,2,3,4,5,6,7,8,9,10}; //son valores para idetificar con las cartas
    private ImageView imgBotones[];
    private int valorSeleccionado=-1; //variable para saber el ultimo valor escogido
    private int valorBorrar=0; //es para grabar que valor tiene que girar de nuevo cunado se equivoque
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        puntaje=0;
        fallas=0;
        setContentView(R.layout.memory54);
        setToolbar();
        iniciarCartas();
        imgBotones=new ImageView[]{img1,img2,img3,img4,img5,img6,img7,img8,img9,img10,img11,img12,img13,img14,img15,img16,img17,img18,img19,img20}; //arrglo de todos las cartas para poder utilizar
        desordenarCartas();
        botonSalir=(Button)findViewById(R.id.btnSalir);
        botonReiniciar=(Button)findViewById(R.id.btnReiniciar);

        botonSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
        botonReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                puntaje=0;
                fallas=0;
                valorSeleccionado=-1;
               // int indice=1;
                Bitmap bpm= BitmapFactory.decodeResource(getResources(),R.drawable.carta0);
                for (ImageView img:imgBotones)
                {
                    //bpm= BitmapFactory.decodeResource(getResources(),R.drawable.carta0
                    img.setImageBitmap(bpm);
                }
                lblPuntaje.setText(puntaje+"");
                lblFallas.setText(fallas+"");
                desordenarCartas();

            }
        });

        agregarEventosCartas();

        lblPuntaje= (TextView) findViewById(R.id.txtPuntaje);
        lblFallas= (TextView) findViewById(R.id.txtFallas);
        //lblPuntaje.setText(0);

    }

    private Handler puente = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bitmap bpm = BitmapFactory.decodeResource(getResources(), R.drawable.carta0);
           imgBotones[valorSeleccionado].setImageBitmap(bpm);
            valorSeleccionado = -1;
            imgBotones[valorBorrar].setImageBitmap(bpm);
        }
    };

    private void desordenarCartas()
    {
        Random rnd=new Random();
        int aux;
        int indiceAux;

        for(int i=0;i<valores.length;i++)
        {
            aux=valores[i]; //respaldo el valor del indice
            indiceAux=rnd.nextInt(10); //nuevo indice para cambiar el valor

            valores[i]=valores[indiceAux];
            valores[indiceAux]=aux;

        }

    }

    private void controlador(int opcion,ImageView img)
    {
        Bitmap bpm=null;
        opcion--;
        switch (valores[opcion])
        {
            case 1: bpm= BitmapFactory.decodeResource(getResources(),R.drawable.carta1);break;
            case 2: bpm= BitmapFactory.decodeResource(getResources(),R.drawable.carta2);break;
            case 3: bpm= BitmapFactory.decodeResource(getResources(),R.drawable.carta3);break;
            case 4: bpm= BitmapFactory.decodeResource(getResources(),R.drawable.carta4);break;
            case 5: bpm= BitmapFactory.decodeResource(getResources(),R.drawable.carta5);break;
            case 6: bpm= BitmapFactory.decodeResource(getResources(),R.drawable.carta6);break;
            case 7: bpm= BitmapFactory.decodeResource(getResources(),R.drawable.carta7);break;
            case 8: bpm= BitmapFactory.decodeResource(getResources(),R.drawable.carta8);break;
            case 9: bpm= BitmapFactory.decodeResource(getResources(),R.drawable.carta9);break;
            case 10: bpm= BitmapFactory.decodeResource(getResources(),R.drawable.carta10);break;


        }

        if(valorSeleccionado==-1) //para verificar que es la primera carta seleccionada
        {
            valorSeleccionado=opcion;
            img.setImageBitmap(bpm); //dibujas la carta
        }
        else
        {
            if(valores[valorSeleccionado]==valores[opcion]) //las dos son iguales
            {
                puntaje++;
                lblPuntaje.setText(puntaje+"");

                Toast.makeText(this,"!Bien!",Toast.LENGTH_LONG).show(); //solo es un mensaje
                img.setImageBitmap(bpm);
                valorSeleccionado=-1; //para indicar que otra vez no hya carta girada
               // Toast.makeText(this, "mensaje", Toast.LENGTH_LONG).show();
            }
            else //son diferente
            {
                fallas++;
                lblFallas.setText(fallas+"");
                valorBorrar=opcion; //el valor que tengo que girar
                img.setImageBitmap(bpm);
                //runOnUiThread(new Runnable()
                Thread hilo=new Thread(this);
                hilo.start(); //
               // Toast.makeText(this,"!Mal!",Toast.LENGTH_SHORT).show();
                //Toast.makeText(this,"!Mal!",100).show();


            }
        }


    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        logo = (TextView)toolbar.findViewById(R.id.logo);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setHomeAsUpIndicator(R.drawable.ic_menu);
            ab.setDisplayHomeAsUpEnabled(true);
        }
        logo.setText("Memoria");
    }

    private void iniciarCartas()
    {
        //carga todas las vistas a la clase para anejar
        img1= (ImageView) findViewById(R.id.carta1);
        img2= (ImageView) findViewById(R.id.carta2);
        img3= (ImageView) findViewById(R.id.carta3);
        img4= (ImageView) findViewById(R.id.carta4);
        img5= (ImageView) findViewById(R.id.carta5);
        img6= (ImageView) findViewById(R.id.carta6);
        img7= (ImageView) findViewById(R.id.carta7);
        img8= (ImageView) findViewById(R.id.carta8);
        img9= (ImageView) findViewById(R.id.carta9);
        img10= (ImageView) findViewById(R.id.carta10);
        img11= (ImageView) findViewById(R.id.carta11);
        img12= (ImageView) findViewById(R.id.carta12);
        img13= (ImageView) findViewById(R.id.carta13);
        img14= (ImageView) findViewById(R.id.carta14);
        img15= (ImageView) findViewById(R.id.carta15);
        img16= (ImageView) findViewById(R.id.carta16);
        img17= (ImageView) findViewById(R.id.carta17);
        img18= (ImageView) findViewById(R.id.carta18);
        img19= (ImageView) findViewById(R.id.carta19);
        img20= (ImageView) findViewById(R.id.carta20);
    }

    private void agregarEventosCartas()
    {

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(1, img1);
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(2, img2);
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(3, img3);
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(4, img4);
            }
        });
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(5, img5);
            }
        });
        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(6, img6);
            }
        });
        img7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(7, img7);
            }
        });
        img8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(8, img8);
            }
        });
        img9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(9, img9);
            }
        });
        img10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(10, img10);
            }
        });
        img11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(11, img11);
            }
        });
        img12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(12, img12);
            }
        });
        img13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(13, img13);
            }
        });
        img14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(14, img14);
            }
        });
        img15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(15, img15);
            }
        });
        img16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(16, img16);
            }
        });
        img17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(17, img17);
            }
        });
        img18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(18, img18);
            }
        });
        img19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(19, img19);
            }
        });
        img20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(20, img20);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void run() {
        SystemClock.sleep(1000);
       // Bitmap bpm= BitmapFactory.decodeResource(getResources(),R.drawable.carta0);
       // imgBotones[valorSeleccionado].setImageBitmap(bpm);
      //  valorSeleccionado=-1;
      //  imgBotones[valorBorrar].setImageBitmap(bpm);
        Message msg = new Message();
        msg.obj = 20;
        puente.sendMessage(msg);

    }
}
