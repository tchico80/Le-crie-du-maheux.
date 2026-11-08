
package com.lecridumaheux;

import android.app.Activity;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(30, 40, 30, 30);
        layout.setGravity(Gravity.CENTER_HORIZONTAL);

        TextView titre = new TextView(this);
        titre.setText("LE CRI DU MAHEUX");
        titre.setTextSize(26);
        titre.setGravity(Gravity.CENTER);
        layout.addView(titre);

        for (int i = 1; i <= 5; i++) {
            final int numero = i;
            Button bouton = new Button(this);
            bouton.setText("Call d'orignal " + i);
            layout.addView(bouton);

            bouton.setOnClickListener(v -> {
                int id = getResources().getIdentifier(
                    "call" + numero,
                    "raw",
                    getPackageName()
                );

                if (id == 0) {
                    Toast.makeText(this,
                        "Son à ajouter",
                        Toast.LENGTH_SHORT).show();
                    return;
                }

                arreterSon();
                player = MediaPlayer.create(this, id);
                if (player != null) {
                    player.start();
                }
            });
        }

        Button stop = new Button(this);
        stop.setText("ARRÊTER LE SON");
        layout.addView(stop);
        stop.setOnClickListener(v -> arreterSon());

        ScrollView scroll = new ScrollView(this);
        scroll.addView(layout);
        setContentView(scroll);
    }

    private void arreterSon() {
        if (player != null) {
            player.release();
            player = null;
        }
    }

    @Override
    protected void onDestroy() {
        arreterSon();
        super.onDestroy();
    }
}
