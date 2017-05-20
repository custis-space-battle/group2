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
    public static final String KILL = "KILL";

    private boolean firstTurn = true;

    WarField(){
        super();
        initField();
    }

    Point lastMove;

    Point lastHitMove;

    ArrayList<Point> availableMoves = new ArrayList<>();

    ArrayList<Point> field = new ArrayList<>();

    private void initField(){
        for (int i = 1; i <= 10; i++){
            for (int j = 1; j <= 10; j++){
                field.add(new Point(i, j));
            }
        }
    }

    public Point randomFire(){
        Random random = new Random();
        boolean available = false;
        Point firePoint = new Point();
        while (!available){
            int x = random.nextInt(FIELD_X) + 1;
            int y = random.nextInt(FIELD_Y) + 1;
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
                firePoint = hitMove();
                clearField(firePoint);
                break;

            case MISS:
                firePoint = missMove();
                clearField(firePoint);
                break;

            case KILL:

                break;

            default:
                firePoint = randomFire();
        }
        if(firePoint == null){
            firePoint = randomFire();
        }
        lastMove = firePoint;
        return firePoint;
    }

    private void clearField(Point hitted){
        ArrayList<Point> list = (ArrayList<Point>) field.clone();
        for(Point point: field){
            if(point.getY() == hitted.getY() && point.getX() == hitted.getX()){
                list.remove(point);
            }
        }
        field = list;
    }

    private Point hitMove(){
        Point firePoint = null;
        if(availableMoves.size() != 0){
            updateAvailableMoves();
        }
        else {
            fillAvailableMoves();
        }
        lastHitMove = lastMove;
        firePoint = availableMoves.get(0);
        availableMoves.remove(0);

        return firePoint;
    }

    private Point missMove(){
        Point firePoint = null;
        if(availableMoves.size() != 0){
            firePoint = availableMoves.get(0);
            availableMoves.remove(0);
        }
        else {
            firePoint = randomFire();
        }
        return firePoint;
    }

    private void clearMovesList(){
        for (Point point: availableMoves) {
            if(point.getX() != lastMove.getX() || point.getY() != lastMove.getY()){
                availableMoves.remove(point);
            }
        }
    }

    public boolean checkPoint(Point point){
        for (Point fieldPoint: field){
            if(fieldPoint.getY() == point.getY() && fieldPoint.getX() == point.getX()){
                return true;
            }
        }
        return false;
    }

    private void updateAvailableMoves(){
        clearMovesList();

        int x;
        int y;

        if(lastMove.getY() == lastHitMove.getY()){
            y = lastMove.getY();
            if(lastMove.getX() < lastHitMove.getX()){
                x = lastMove.getX() + 1;
            }
            else {
                x = lastMove.getX() - 1;
            }
            checkPoint(new Point(x, y));
            addToMoveList(x, y);
        }

        if(lastMove.getX() == lastHitMove.getX()){
            x = lastMove.getX();
            if(lastHitMove.getY() < lastMove.getY()){
                y = lastMove.getY()+1;
            }
            else {
                y = lastMove.getY()-1;
            }
            checkPoint(new Point(x, y));
            addToMoveList(x, y);
        }

    }

    private void fillAvailableMoves(){
        addToMoveList(lastMove.getX() - 1, lastMove.getY());
        addToMoveList(lastMove.getX() + 1, lastMove.getY());
        addToMoveList(lastMove.getX(), lastMove.getY() - 1);
        addToMoveList(lastMove.getX(), lastMove.getY() + 1);
    }

    private void addToMoveList(int x, int y){
        if(!(x < 1 || x > FIELD_X || y < 1 || y > FIELD_Y)){
            if(checkPoint(new Point(x, y))){
                availableMoves.add(new Point(x, y));
            }
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
