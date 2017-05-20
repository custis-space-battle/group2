import java.util.ArrayList;
import java.util.Random;

/**
 * Created by bubal on 20.05.2017.
 */
public class WarField {

    public static final int FIELD_X = 10;
    public static final int FIELD_Y = 10;

    public static final String HIT = "HIT";
    public static final String MISS = "MISS";
    public static final String DESTROY = "DESTROY";

    Point lastMove;

    Point lastHitMove;

    ArrayList<Point> availableMoves = new ArrayList<>();

    ArrayList<Point> field = new ArrayList<>();

    public Point randomFire(){
        Random random = new Random();
        boolean available = false;
        Point firePoint = new Point();
        while (!available){
            int x = random.nextInt(FIELD_X );
            int y = random.nextInt(FIELD_Y);
            firePoint.setX(x);
            firePoint.setY(y);
            available = checkPoint(firePoint);
        }
        return firePoint;
    }

    public Point makeAvailbleTurn(String lastTurnResult){
        Point firePoint = null;
        switch (lastTurnResult){
            case HIT:
                lastHitMove = lastMove;
                break;

            case MISS:
                break;

            case DESTROY:
                break;

            default:
                firePoint = randomFire();
        }
        return firePoint;
    }

    private Point hitMove(){
        Point firePoint = null;

        if(availableMoves.size() != 0){

        }
        else {
            updateAvailableMoves();
        }
        return firePoint;
    }

    private void clearMovesList(){

    }

    public boolean checkPoint(Point point){
        if(field.indexOf(point) != -1){
            return true;
        }
        return false;
    }

    private void updateAvailableMoves(){
        addToMoveList(lastMove.getX() - 1, lastMove.getY());
        addToMoveList(lastMove.getX() - 1, lastMove.getY());
        addToMoveList(lastMove.getX(), lastMove.getY() - 1);
        addToMoveList(lastMove.getX(), lastMove.getY() - 1);
    }

    private void addToMoveList(int x, int y){
        if(!(x < 0 || x > FIELD_X || y < 0 || y < FIELD_Y)){
            availableMoves.add(new Point(x, y));
        }
    }

    public Point getLastMove() {
        return lastMove;
    }

    public void setLastMove(Point lastMove) {
        this.lastMove = lastMove;
    }

    public ArrayList<Point> getAvailableMoves() {
        return availableMoves;
    }

    public void setAvailableMoves(ArrayList<Point> availableMoves) {
        this.availableMoves = availableMoves;
    }

    public ArrayList<Point> getField() {
        return field;
    }

    public void setField(ArrayList<Point> field) {
        this.field = field;
    }
}
