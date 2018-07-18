package com.application.omistaja.flystride10;



import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

/**
 * Aktiiviinen näkymä, joka sisältää juoksutekniikan korjaukseen ohjeistuksia eri osioille.
 * Näkymät avautuvat laajentumisanimaatiolla painamalla ostikkopainikkeen kohtaan. Otsikkopainikkeesta
 * voi myös sulkea näkymän painamalla sitä uudelleen ja sulekutuminen tapahtuu vastaavalla
 * sulkeutumis -animaatiolla. Jos jokin näkymä on auki, niin se sulkeutuu, jos uusi näkymä avataan.
 */
public class TipsActivity extends AppCompatActivity {

    private ImageButton kneeLiftingButton, HeelStrikingButton, bodyPostureButton, overStridingButton;

    private RelativeLayout contentKneeLayout, contentHeelLayout, contentPostureLayout, contentOverLayout;

    private ImageView openImageView, openImageView1, openImageView2, openImageView3;

    private TextView kneeBadText, kneeGoodText, heelBadText, heelGoodText, overBadText, overGoodText, posBadText, posGoodText;


    //taulukko, jossa on kaikki kuvanäkymät
    private ImageView[] imageOpenArray = new ImageView[4];

    private int openDownBadDrawableInt;

    private int heightLayoutGuide = 315;

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.gc();
    }

    @Override
    public void onStop() {
        super.onStop();
        System.gc();
    }

    private int languageId = 0;
    private TextView headerKneeText;
    private TextView headerForefootText;
    private TextView headerBodyText;
    private TextView headerPostureText;
    private TextView kneeContent, heel2Text, heel1Text, posture2Text, posture1Text, over2Text, over1Text, contentHeel,overContent, postureContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        setContentView(R.layout.activity_tips);

        languageId = MainActivity.getLanguageChoice();

        kneeBadText = (TextView) findViewById(R.id.badKnee);
        kneeGoodText = (TextView) findViewById(R.id.goodKnee);
        heelBadText = (TextView) findViewById(R.id.badHeel);
        heelGoodText = (TextView) findViewById(R.id.goodHeel);
        overBadText = (TextView) findViewById(R.id.badOver);
        overGoodText = (TextView) findViewById(R.id.goodOver);
        posBadText = (TextView) findViewById(R.id.badPosture);
        posGoodText = (TextView) findViewById(R.id.goodPosture);



        ImageView kneeGood = (ImageView) findViewById(R.id.imageViewKneeGood);
        final int kneeGoodInt = this.getResources().getIdentifier("knee_lifting_good", "drawable", this.getPackageName());
        setResizeImage(56,80,kneeGoodInt, kneeGood);

        ImageView kneeBad = (ImageView) findViewById(R.id.imageViewKneeBad);
        final int kneeBadInt = this.getResources().getIdentifier("kneelifting_bad", "drawable", this.getPackageName());
        setResizeImage(56, 80, kneeBadInt, kneeBad);

        ImageView overGood = (ImageView) findViewById(R.id.imageViewOverGood);
        final int overGoodInt = this.getResources().getIdentifier("overstriding_good", "drawable", this.getPackageName());
        setResizeImage(56, 80, overGoodInt, overGood);

        ImageView overBad = (ImageView) findViewById(R.id.imageViewOverBad);
        final int overBadInt = this.getResources().getIdentifier("overstriding_bad", "drawable", this.getPackageName());
        setResizeImage(56,80,overBadInt, overBad);

        ImageView heelGood = (ImageView) findViewById(R.id.imageViewHeelGood);
        final int heelGoodInt = this.getResources().getIdentifier("landing_good", "drawable", this.getPackageName());

        setResizeImage(56,80, heelGoodInt, heelGood);

        ImageView heelBad = (ImageView) findViewById(R.id.imageViewHeelBad);
        final int heelBadInt = this.getResources().getIdentifier("landing_bad", "drawable", this.getPackageName());
        setResizeImage(56,80,heelBadInt, heelBad);


        ImageView positionGood = (ImageView) findViewById(R.id.imageViewPostureGood);
        final int positionGoodInt = this.getResources().getIdentifier("position_good", "drawable", this.getPackageName());
        setResizeImage(56,80,positionGoodInt, positionGood);

        ImageView positionBad = (ImageView) findViewById(R.id.imageViewPostureBad);
        final int positionBadInt = this.getResources().getIdentifier("position_bad", "drawable", this.getPackageName());
        setResizeImage(56,80,positionBadInt, positionBad);

        final ImageButton backButton = (ImageButton) findViewById(R.id.imageButtonBack);
        ImageView backButtonImage = (ImageView) findViewById(R.id.backImageView);

        final int backDrawableInt = this.getResources().getIdentifier("back_button", "drawable", this.getPackageName());
        setResizedView(backDrawableInt, backButtonImage);



        kneeLiftingButton = (ImageButton) findViewById(R.id.knee_lifting_btn);
        HeelStrikingButton = (ImageButton) findViewById(R.id.heel_btn);
        bodyPostureButton = (ImageButton) findViewById(R.id.posture_btn);
        overStridingButton = (ImageButton) findViewById(R.id.over_btn);

        headerKneeText = (TextView) findViewById(R.id.kneeHeaderText);
        headerForefootText = (TextView) findViewById(R.id.forefootText);
        headerBodyText = (TextView) findViewById(R.id.bodyHeaderText);
        headerPostureText = (TextView) findViewById(R.id.overHeaderText);

        kneeContent = (TextView) findViewById(R.id.content_knee);

        heel2Text = (TextView) findViewById(R.id.text2Heel);
        heel1Text = (TextView) findViewById(R.id.text1Heel);
        contentHeel = (TextView) findViewById(R.id.content_heel);

        postureContent = (TextView) findViewById(R.id.content_posture);
        posture2Text = (TextView) findViewById(R.id.text2Posture);
        posture1Text = (TextView) findViewById(R.id.text1Posture);

        overContent = (TextView) findViewById(R.id.content_Over);
        over2Text = (TextView) findViewById(R.id.text2Over);
        over1Text = (TextView) findViewById(R.id.text1Over);

        contentKneeLayout = (RelativeLayout) findViewById(R.id.layout_content_knee);
        contentHeelLayout = (RelativeLayout) findViewById(R.id.layout_content_heel);
        contentPostureLayout = (RelativeLayout) findViewById(R.id.layout_content_posture);
        contentOverLayout= (RelativeLayout) findViewById(R.id.layout_content_over);


        //painikkeen plus -ikoni, jos merkiksi, jos osion näkymä on auki
        openDownBadDrawableInt = this.getResources().getIdentifier("plus_icon", "drawable", this.getPackageName());
        //painikkeen miinus -ikoni, jos merkiksi, jos osion näkymä on kiinni
        final int openUpBadDrawableInt = this.getResources().getIdentifier("minus_icon", "drawable", this.getPackageName());
        openImageView = (ImageView) findViewById(R.id.imageOpen);
        setResizedView(openDownBadDrawableInt, openImageView);
        openImageView1 = (ImageView) findViewById(R.id.imageOpen1);
        setResizedView(openDownBadDrawableInt, openImageView1);
        openImageView2 = (ImageView) findViewById(R.id.imageOpen2);
        setResizedView(openDownBadDrawableInt, openImageView2);
        openImageView3 = (ImageView) findViewById(R.id.imageOpen3);
        setResizedView(openDownBadDrawableInt, openImageView3);


        final ViewGroup.LayoutParams params = contentKneeLayout.getLayoutParams();
        final ViewGroup.LayoutParams paramsHeel = contentHeelLayout.getLayoutParams();
        final ViewGroup.LayoutParams paramsPosture = contentPostureLayout.getLayoutParams();
        final ViewGroup.LayoutParams paramsOver = contentOverLayout.getLayoutParams();


        openArray[0] = false;
        openArray[1] = false;
        openArray[2] = false;
        openArray[3] = false;

        buttonArray[0] = kneeLiftingButton;
        buttonArray[1] = HeelStrikingButton;
        buttonArray[2] = bodyPostureButton;
        buttonArray[2] = overStridingButton;

        imageOpenArray[0] = openImageView;
        imageOpenArray[1] = openImageView1;
        imageOpenArray[2] = openImageView2;
        imageOpenArray[3] = openImageView3;

        contentLayoutArray[0] = contentKneeLayout;
        contentLayoutArray[1] = contentHeelLayout;
        contentLayoutArray[2] = contentPostureLayout;
        contentLayoutArray[3] = contentOverLayout;

        counterArray[0] = 3;
        counterArray[1] = 2;
        counterArray[2] = 2;
        counterArray[3] = 2;

        setLanguage();

        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                slideViewRight();
            }
        });

        setKneeLiftingView(openUpBadDrawableInt);
        setHeelStrikingView(openUpBadDrawableInt);
        setBodyPostureView(openUpBadDrawableInt);
        setOverStridingView(openUpBadDrawableInt);



        kneeLiftingButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setKneeLiftingView(openUpBadDrawableInt);
            }
        });

        HeelStrikingButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setHeelStrikingView(openUpBadDrawableInt);
            }
        });

        bodyPostureButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setBodyPostureView(openUpBadDrawableInt);
            }
        });

        overStridingButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setOverStridingView(openUpBadDrawableInt);
            }
        });
    }



    /**
     * Asettaa OverStriding näkymän ja sulkee toisen näkymän, jos se on auki
     * @param openUpBadDrawableInt kuvan id, joka avataan
     */
    public void setOverStridingView(int openUpBadDrawableInt){

        if(counterArray[3]%2==0){
            setResizedView(openDownBadDrawableInt, imageOpenArray[3]);
            openArray[3]=false;
            closeView(contentOverLayout);
        }
        else{
            closeIfSomeAnotherOpen(3);
            setResizedView(openUpBadDrawableInt, imageOpenArray[3]);
            openArray[3]=true;
            openView(contentOverLayout);
        }
        counterArray[3]++;
    }


    /**
     * asettaa BodyPosture näkymän ja sulkee toisen näkymän, jos se on auki
     * @param openUpBadDrawableInt kuvan id, joka avataan
     */
    public void setBodyPostureView(int openUpBadDrawableInt){
        if(counterArray[2]%2==0){
            setResizedView(openDownBadDrawableInt, imageOpenArray[2]);
            openArray[2]=false;
            closeView(contentPostureLayout);
        }
        else{
            closeIfSomeAnotherOpen(2);
            setResizedView(openUpBadDrawableInt, imageOpenArray[2]);
            openArray[2]=true;
            openView(contentPostureLayout);
        }
        counterArray[2]++;
    }



    /**
     * Asettaa HeelStriking näkymän ja sulkee toisen näkymän, jos se on auki
     * @param openUpBadDrawableInt kuvan id, joka avataan
     */
    public void setHeelStrikingView(int openUpBadDrawableInt){

        if(counterArray[1]%2==0){
            setResizedView(openDownBadDrawableInt, imageOpenArray[1]);
            openArray[1]=false;
            closeView(contentHeelLayout);
        }
        else{
            closeIfSomeAnotherOpen(1);
            setResizedView(openUpBadDrawableInt, imageOpenArray[1]);
            openArray[1]=true;
            openView(contentHeelLayout);
        }
        counterArray[1]++;
    }



    /**
     * settaa KneeLifting näkymän ja sulkee toisen näkymän, jos se on auki
     * @param openUpBadDrawableInt kuvan id, joka avataan
     */
    public void setKneeLiftingView(int openUpBadDrawableInt){
        if(counterArray[0]%2==0){
            setResizedView(openDownBadDrawableInt, imageOpenArray[0]);
            openArray[0]=false;
            closeView(contentKneeLayout);
        }
        else{
            closeIfSomeAnotherOpen(0);
            setResizedView(openUpBadDrawableInt, imageOpenArray[0]);
            openArray[0]=true;
            openView(contentKneeLayout);
        }
        counterArray[0]++;
    }


    /**
     * Sulkee layout näkymän, joka on auki silloin, kun jokin toinen näkymä avataan
     * @param view suljettava näkymä
     */
    public void closeView(RelativeLayout view){
        ResizeAnimation resizeAnimation = new ResizeAnimation(
                view,
                convertDpToPx(-heightLayoutGuide),
                convertDpToPx(heightLayoutGuide)
        );
        resizeAnimation.setDuration(200);
        view.startAnimation(resizeAnimation);
    }


    /**
     * Avaa näkymän näkymän laajentumisanimaatiolla
     * @param view layout näkymä, joka avautuu ja laajennetaan
     */
    public void openView(RelativeLayout view){
        ResizeAnimation resizeAnimation = new ResizeAnimation(
                view,
                convertDpToPx(heightLayoutGuide),
                0
        );
        resizeAnimation.setDuration(200);
        view.startAnimation(resizeAnimation);
    }


    /**
     * suorittaa layoutin vaihdon yhteydessä siirtymäanimaation (kun poistuu tips_activity layoutista)
     */
    public void slideViewRight(){
        overridePendingTransition(R.anim.slide_right, R.anim.slide_rightout);
    }


    /**
     * Asettaa sisällön tekstit joko englanniksi tai suomeksi
     */
    public void setLanguage(){

        if(languageId==0){

            setBadTextEng(kneeBadText);
            setBadTextEng(heelBadText);
            setBadTextEng(overBadText);
            setBadTextEng(posBadText);

            setGoodTextEng(kneeGoodText);
            setGoodTextEng(heelGoodText);
            setGoodTextEng(overGoodText);
            setGoodTextEng(posGoodText);


            headerKneeText.setText("Knee lifting");
            headerForefootText.setText("Forefoot landing");
            headerBodyText.setText("Straight body position");
            headerPostureText.setText("Avoid overstriding");


            kneeContent.setTextSize(13);
            kneeContent.setText("Lift knee at the same time when another foot pushes off the ground. The upward knee movement produces a more power that can push you forward when you are running.");


            contentHeel.setText("Forefoot running is more energy-efficient than heel strike running because forefoot landing can stores energy in the Achilles tendon. Heel striking produces a more impact and lost all the stored energy. Use a more forefoot landing but make the change a very slow.");
            contentHeel.setTextSize(13);
            heel2Text.setText("Heel striking");
            heel1Text.setText("Forefoot landing");


            postureContent.setText("Straight body position allows reusing energy a more effectively when you hit the ground.");
            postureContent.setTextSize(13);
            posture2Text.setText("Keep body posture in alignment.");
            posture1Text.setText("Avoid that body drop towards a sitting position.");


            overContent.setText("What is overstriding? When your foot lands too far in front of your center of mass. Then you are breaking every time your foot hits the ground.");
            overContent.setTextSize(13);
            over2Text.setText("Overstriding");
            over1Text.setText("Landing ahead of the center of mass.");
        }
        if(languageId==1){

            setBadTextFin(kneeBadText);
            setBadTextFin(heelBadText);
            setBadTextFin(overBadText);
            setBadTextFin(posBadText);

            setGoodTextFin(kneeGoodText);
            setGoodTextFin(heelGoodText);
            setGoodTextFin(overGoodText);
            setGoodTextFin(posGoodText);


            headerKneeText.setText("Polven nostaminen");
            headerForefootText.setText("Päkiäaskellus");
            headerBodyText.setText("Suora vartalonasento");
            headerPostureText.setText("Vältä yliaskellusta");

            kneeContent.setText("Nosta polvea samaan aikaan, kun toinen jalka työntää eteenpäin maasta. Polven nostaminen tuottaa lisää työntövoimaa eteenpäin.");

            contentHeel.setText("Päkiäaskellus on energiatehokkaampi tapa juosta, kun kanta-askellus, koska päkiäaskellus mahdollistaa energian säilömisen akillesjänteeseen. Kanta-askellus tuottaa enemmän iskuvoimaa, jota ei voida hyödyntää uudelleen. Siirry päkiäaskellukseen kuitenkin erittäin varovasti. ");
            heel2Text.setText("Kanta-askellus");
            heel1Text.setText("Päkiäaskellus");


            postureContent.setText("Suora vartalon asento mahdollistaa joustoenergian uudelleen käytön, kun jalka iskee päkiä edellä maahan. ");
            posture2Text.setText("Pidä vartalon asento suorassa");
            posture1Text.setText("Vältä, että asento tippuu istuma-asentoon");


            overContent.setText("Mitä on yliaskellus? Kun jalka osuu maahan liian eteen suhteessa vartalon keskikohtaa. Yliaskellus jarruttaa ja hidastaa vauhtia.   ");
            over2Text.setText("Yliaskellus");
            over1Text.setText("Askellus suoraan painopisteen alle");
        }
    }

    /**
     * asettaa tekstin "huono" englanniksi
     * @param text lisättävä teksti
     */
    public void setBadTextEng(TextView text){
        text.setText("Bad");
    }

    /**
     * asettaa tekstin "hyvä" englanniksi
     * @param text lisättävä teksti
     */
    public void setGoodTextEng(TextView text){
        text.setText("Good");
    }

    /**
     * asettaa tekstin "huono" suomeksi
     * @param text lisättävä teksti
     */
    public void setBadTextFin(TextView text){
        text.setText("Huono");
    }

    /**
     * asettaa tekstin "hyvä" suomeksi
     * @param text lisättävä teksti
     */
    public void setGoodTextFin(TextView text){
        text.setText("Hyvä");
    }



    /**
     * lisää kuvan ja skaalaa kuvan koon uudelleen
     * @param width kuvan leveys uuteen skaalaukseen
     * @param height kuvan korkeus uuteen skaalaukseen
     * @param imageNum kuvan id
     * @param image kuvanäkymä, johon kuva lisätään
     */
    public void setResizeImage(int width, int height, int imageNum, ImageView image){

        int convertHeight  = convertDpToPx(height);
        int convertWidth  = convertDpToPx(width);
        Picasso
                .with(getApplicationContext())
                .load(imageNum)
                .resize(convertWidth, convertHeight) // resizes the image to these dimensions (in pixel). does not respect aspect ratio
                .into(image);
    }



    // Taulukko, joka kertoo onko osion näkymä auki vai suljettu (auki==true)
    private boolean[] openArray = new boolean[4];

    //Taulukossa on kaikkien osioiden painikkeet
    private ImageButton[] buttonArray = new ImageButton[4];

    // taulukko, jossa on kaikkien osioiden näkymäien layout sisällöt
    private RelativeLayout[] contentLayoutArray = new RelativeLayout[4];

    //taulukko, johon lisäytyy jokaisen osion näkymän painetut kerrat
    private int[] counterArray = new int[4];


    /**
     * Muuntaa DP:n pikseliksi
     * @param dp muunnettava yksikkö
     * @return dp:n pikseleinä
     */
    private int convertDpToPx(int dp){
        return Math.round(dp*(getResources().getDisplayMetrics().xdpi/ DisplayMetrics.DENSITY_DEFAULT));

    }




    /**
     * lisää kuvan ja skaalaa kuvan koon uudelleen
     * @param imageNum kuvan id
     * @param imageview kuvanäkymä, johon kuva lisätään
     */
    public void setResizedView(int imageNum, ImageView imageview){

        Picasso
                .with(getApplicationContext())
                .load(imageNum)
                .fit()
                .into(imageview);
    }

    /**
     * sulkee osion näkymän, jos jokin näkymä on auki
     * @param id kuvan id joka aukaistaan
     */
    public void closeIfSomeAnotherOpen(int id){
        int idContent = 0;
        for(boolean openContent: openArray){
            setResizedView(openDownBadDrawableInt, imageOpenArray[idContent]);
            if(id!=idContent){
                if(openContent==true){
                    openArray[idContent] = false;
                    RelativeLayout contentLayout = contentLayoutArray[idContent];
                    counterArray[idContent]++;
                    closeView(contentLayout);
                }
            }
            idContent++;
        }
    }
}
