package bomb;

import common.IntList;
public class BombMain {
    public static void main(String[] args) {
        int phase = 2;
        if (args.length > 0) {
            phase = Integer.parseInt(args[0]);
        }
        // TODO: Find the correct passwords to each phase using debugging techniques
        Bomb b = new Bomb();
        if (phase >= 0) {
            b.phase0("39291226");
        }
        if (phase >= 1) {

            IntList phase1Password = IntList.of(0,9,3,0,8);
            b.phase1(phase1Password); // Figure this out too
        }
        if (phase >= 2) {
            String phase2Password = "-81201430 ".repeat(1338);
            b.phase2(phase2Password);
        }
    }
}
