package eu.visiton.app.ui.pois.qrScanner;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.zxing.Result;
import eu.visiton.app.responses.PoiResponse;
import eu.visiton.app.responses.VisitPoiResponse;
import eu.visiton.app.retrofit.generator.AuthType;
import eu.visiton.app.retrofit.generator.ServiceGenerator;
import eu.visiton.app.retrofit.services.PoiService;
import eu.visiton.app.util.UtilToken;

import java.util.Objects;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QrScannerFragment extends Fragment implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;
    private PoiResponse poi;
    private String jwt;
    private IQrScanListener mListener;
    private boolean scan = false;

    public QrScannerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mScannerView = new ZXingScannerView(getContext());
        jwt = UtilToken.getToken(Objects.requireNonNull(getContext()));

        return mScannerView;
    }

    // Start QR Code Actions
    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    /** Action when you scan a QRCode **/
    @Override
    public void handleResult(Result rawResult) {
        // Here goes the method to change fragment and visit the POI.

        if(!scan) {
            scan = true;

            mScannerView.resumeCameraPreview(this);

            String[] resultadoSpliteado = rawResult.toString().split("/");
            String uniqueName = resultadoSpliteado[6];

            PoiService service = ServiceGenerator.createService(PoiService.class, jwt, AuthType.JWT);
            Call<VisitPoiResponse> call = service.qrScan(uniqueName);
            call.enqueue(new Callback<VisitPoiResponse>() {
                @Override
                public void onResponse(Call<VisitPoiResponse> call, Response<VisitPoiResponse> response) {
                    mListener.onQrScan(response.body());
                }

                @Override
                public void onFailure(Call<VisitPoiResponse> call, Throwable t) {
                    Log.e("Network Failure", t.getMessage());
                    Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    // Finish QRCode Actions

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof IQrScanListener) {
            mListener = (IQrScanListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement IQrScanListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
