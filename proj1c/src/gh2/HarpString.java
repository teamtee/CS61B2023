package gh2;

public class Harp extends GuitarString{
    public Harp(double frequency) {
        super(frequency*2);
    }

    @Override
    public void tic() {
        double first = buffer.removeFirst();
        double newItem = (buffer.get(0) + first)/2 * 0.996;
        buffer.addLast(-newItem);
    }
}
