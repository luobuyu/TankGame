import java.awt.*;

public class Tank {
    private int x;
    private int y;
    private int speed;
    private int dir;

    public Tank(int speed){
        this.x = 200;
        this.y = 200;
        this.dir = Dir.UP;
        this.speed = speed;
    }

    public void move(){
        switch (this.dir){
            case Dir.UP:
                this.setY(this.getY()-speed);
                break;
            case Dir.down:
                this.setY(this.getY()+speed);
                break;
            case Dir.left:
                this.setX(this.getX()-speed);
                break;
            case Dir.right:
                this.setX(this.getX()+speed);
                break;
        }
    }

    public void draw(Graphics g){
        g.setColor(Color.red);
        g.drawLine(200, 200, 100, 100);
        g.fillRect(this.getX(), this.getY(), 200, 200);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }
}
