package id.ac.polinema.idealbodyweight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import id.ac.polinema.idealbodyweight.fragments.AboutFragment;
import id.ac.polinema.idealbodyweight.fragments.BodyMassIndexFragment;
import id.ac.polinema.idealbodyweight.fragments.BrocaindexFragment;
import id.ac.polinema.idealbodyweight.fragments.MenuFragment;
import id.ac.polinema.idealbodyweight.fragments.ResultFragment;

public class MainActivity extends AppCompatActivity implements
        MenuFragment.OnFragmentInteractionListener,
        BrocaindexFragment.OnFragmentInteractionListener,
        ResultFragment.OnFragmentInteractionListener,
        BodyMassIndexFragment.OnFragmentInteractionListener {

    // Deklarasikan atribut Fragment di sini
    private AboutFragment aboutFragment;
    private MenuFragment menuFragment;
    private BrocaindexFragment brocaIndexFragment;
    private BodyMassIndexFragment bodyMassIndexFragment;
    private ResultFragment resultFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aboutFragment = AboutFragment.newInstance("Reffan Pandu A");
        menuFragment = new MenuFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, menuFragment)
                .commit();

        brocaIndexFragment = new BrocaindexFragment();
        bodyMassIndexFragment = new BodyMassIndexFragment();
        resultFragment = new ResultFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // TODO: Tambahkan penanganan menu di sini
        if (item.getItemId() == R.id.menu_about) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, aboutFragment)
                    .addToBackStack(null)
                    .commit();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBrocaIndexButtonClicked() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, brocaIndexFragment)
                .commit();
    }

    @Override
    public void onBodyMassIndexButtonClicked() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, bodyMassIndexFragment)
                .commit();
    }

    @Override
    public void onCalculateBrocaIndexClicked(float index) {
        resultFragment.setInformation(String.format("Your ideal weight is %.2f kg", index));
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, resultFragment, "BROCA_INDEX")
                .commit();
    }

    @Override
    public void onTryAgainButtonClicked(String tag) {
        if (tag.equals("BROCA_INDEX")){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, brocaIndexFragment)
                    .commit();
        } else if (tag.equals("BODY_MASS_INDEX")) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, bodyMassIndexFragment)
                    .commit();
        }
    }

    @Override
    public void onCalculateBodyMassIndexClicked(float index) {
        resultFragment.setInformation(String.format("Your Body Mass Index is %.2f", index));
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, resultFragment, "BODY_MASS_INDEX")
                .commit();
    }
}
