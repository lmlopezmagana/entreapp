package eu.visiton.app.ui.pois.qrScanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import eu.visiton.app.R;
import eu.visiton.app.responses.VisitPoiResponse;
import eu.visiton.app.ui.pois.details.DetallePoiActivity;
import eu.visiton.app.util.Constantes;

public class QrCodeActivity extends AppCompatActivity implements IQrScanListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);
    }

    @Override
    public void onQrScan(VisitPoiResponse visitPoi) {
        String title = "";
        String message = "";
        String animation = "";
        if(visitPoi.getNewBadges().getCount() == 0) {
            title = "Visita registrada";
            message = visitPoi.getPoi().getName() + " visitado";
            animation = "ic_checkbox_animation.json";
        } else if(visitPoi.getNewBadges().getCount() == 1) {
            title = "¡Nuevo Badge!";
            message = "Has conseguido "+visitPoi.getNewBadges().getRows().get(0).getName();
            animation = "ic_badge_animation.json";
        } else {
            title = "¡Nuevos Badges!";
            message = "Has conseguido " + visitPoi.getNewBadges().getCount() + " Badges";
            animation = "ic_badge_animation.json";
        }
        
        Intent i = new Intent(this, DetallePoiActivity.class);
        i.putExtra("id", visitPoi.getPoi().getId());
        i.putExtra(Constantes.EXTRA_DIALOG_TITLE, title);
        i.putExtra(Constantes.EXTRA_DIALOG_MESSAGE, message);
        i.putExtra(Constantes.EXTRA_DIALOG_ANIMATION, animation);
        i.putExtra("id", visitPoi.getPoi().getId());
        startActivity(i);

        finish();
    }

}
