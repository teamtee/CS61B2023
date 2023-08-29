package gh2;

import java.util.Random;

public class DrumString extends GuitarString{
    public DrumString(double frequency) {
        super(frequency);
        DECAY = 1;
    }

    @Override
    public void tic() {
        double first = buffer.removeFirst();
        double newItem = (buffer.get(0) + first)/2 * DECAY;
        Random random = new Random();
        buffer.addLast(newItem*(random.nextInt(2)*2-1));
    }
}
