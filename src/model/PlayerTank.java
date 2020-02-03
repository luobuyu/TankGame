package model;


public class PlayerTank extends Tank{
    private boolean isMoving;
    private boolean isFiring;
    private boolean isProtected;

    public PlayerTank(int tankType, MyPanel father, int x, int y) {
        super(tankType, father, x, y);
        this.setDir(Const.UP);
        this.setSpeed(Const.TANK_NOR_SPEED);
        this.setMoving(false);
        this.setFiring(false);
        this.setBulletType(Const.BULLET_NOR);
        this.setImg(new String[]{Const.PlayerTank_IMG_UP, Const.PlayerTank_IMG_DOWN, Const.PlayerTank_IMG_RIGHT, Const.PlayerTank_IMG_LEFT});
        this.setProtected(false);
    }

    @Override
    public boolean canFire() {
        long t = System.currentTimeMillis();
        if(t - this.getLastFireTime() > Const.PlayerFireGap && this.isFiring()) {
            this.setLastFireTime(t);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean canMove(){
        this.updatePropState();
        if(!this.isMoving()){
            return false;
        }
        int newX = this.getX();
        int newY = this.getY();
        switch (this.getDir()){
            case Const.UP:
                newY = this.getY()-this.getSpeed();
                break;
            case Const.DOWN:
                newY = this.getY()+this.getSpeed();
                break;
            case Const.LEFT:
                newX = this.getX()-this.getSpeed();
                break;
            case Const.RIGHT:
                newX = this.getX()+this.getSpeed();
                break;
            default:
                break;
        }

        // 如果超出边界
        if(CollDete.isBeyondBorder(newX, newY, Const.TankWidth)){
            return false;
        }

        // 如果和障碍物碰撞
        for(Barrier barrier: this.getFather().getBarriers()){
            if(barrier.getType()>Const.canMove){
                if(CollDete.isCollide(newX, newY, Const.TankWidth, barrier.getX(), barrier.getY(), Const.width)){
                    return false;
                }
            }
        }

        // 如果和出生点碰撞
        for(Birth birth: this.getFather().getBirths()) {
            if(CollDete.isCollide(newX, newY, Const.TankWidth, birth.getX(), birth.getY(), Const.TankWidth)) {
                return false;
            }
        }

        // 如果和坦克碰撞
        for(int i=1; i<this.getFather().getTanks().size(); i++){
            if(CollDete.isCollide(newX, newY, Const.TankWidth, this.getFather().getTanks().get(i).getX(), this.getFather().getTanks().get(i).getY(), Const.TankWidth)){
                return false;
            }
        }

        // 和buff相撞
        for(int i = 0; i < this.getFather().getProps().size(); i++) {
            if(CollDete.isCollide(newX, newY, Const.TankWidth, this.getFather().getProps().get(i).getX(), this.getFather().getProps().get(i).getY(), Const.propWidth)) {
                this.eatProp(this.getFather().getProps().get(i).getType());
                this.getFather().getProps().get(i).setAlive(false);
            }
        }
        return true;
    }

    public void eatProp(int type) {
        switch (type) {
            case Const.changeBullet:
                this.setBulletType(Const.BULLET_ICE);
                break;
            case Const.increase:
                this.setSpeed(Const.Tank_INC_SPEED);
                break;
            case Const.protect:
                this.setProtected(true);
                break;
            case Const.recovery:
                this.setHp(Const.MAX_HP);
                break;
            default:
                break;
        }
        if(type < Const.recovery) {
            this.setPropTime(System.currentTimeMillis(), type);
        }
    }

    public void updatePropState() {
        long t = System.currentTimeMillis();
        for(int i = 0; i < this.getPropTime().length; i++) {
            if(this.getPropTime()[i] != 0 && t - this.getPropTime()[i] > Const.last_time) {
                this.setPropTime(0, i);
                switch(i){
                    case Const.changeBullet:
                        this.setBulletType(Const.BULLET_NOR);
                        break;
                    case Const.increase:
                        this.setSpeed(Const.TANK_NOR_SPEED);
                        break;
                    case Const.protect:
                        this.setProtected(false);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public boolean isFiring() {
        return isFiring;
    }

    public void setFiring(boolean firing) {
        isFiring = firing;
    }

    public boolean isProtected() {
        return isProtected;
    }

    public void setProtected(boolean aProtected) {
        isProtected = aProtected;
    }

    @Override
    public void beAttacked(int damage) {
        if(!this.isProtected()) {
            this.setHp(this.getHp() - damage);
        }
    }
}
