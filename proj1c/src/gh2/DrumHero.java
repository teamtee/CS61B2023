package gh2;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

/**
 * A client that uses the synthesizer package to replicate a plucked guitar string sound
 */
public class DrumHero {
    private static final double CONCERT_A = 440.0;
    private static final double CONCERT_C = CONCERT_A * Math.pow(2, 3.0 / 12.0);
    public static double key2Concert(int ch){
        return 440 * Math.pow(2,(ch-24)/12);
    }
    public static void main(String[] args) {
        /* Create guitar strings */
        String keyboard="q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        GuitarString[] guitarList = new GuitarString[keyboard.length()];
        for(int index = 0 ; index < keyboard.length();index++){
            guitarList[index] = new GuitarString(key2Concert(index));
        }
        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int guitarIndex = keyboard.indexOf(key);
                if(0 <= guitarIndex && guitarIndex < guitarList.length){
                    guitarList[guitarIndex].pluck();
                }
            }
            double sample = 0.0;
            for (GuitarString item : guitarList){
                 sample = sample + item.sample();
                 item.tic();
            }
            /* compute the superposition of samples */
            /* play the sample on standard audio */
            StdAudio.play(sample);
            /* advance the simulation of each guitar string by one step */
        }
    }

}

