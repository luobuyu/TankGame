package model;

import java.awt.*;

public class EnemyTank extends Tank {
    private long lastChangeDirTime;

    public EnemyTank(int tankType, MyPanel father, int x, int y) {
        super(tankType, father, x, y);
        // 这个id用来遍历的时候与自己分别开
        this.setDir(Const.RIGHT);
        this.setSpeed(Const.TANK_NOR_SPEED);
        this.setBulletType(Const.BULLET_NOR);
        this.setLastChangeDirTime(System.currentTimeMillis());
        this.setImg(new String[]{Const.EnemyTank_IMG_UP, Const.EnemyTank_IMG_DOWN, Const.EnemyTank_IMG_RIGHT, Const.EnemyTank_IMG_LEFT});
    }

    /***
     * 敌人坦克开火函数，判断间隔，摇随机数，开火
     *
     * 目前设置每隔1秒，摇一次随机数，判断奇偶 1/2
     */
    @Override
    public boolean canFire() {
        long t = System.currentTimeMillis();
        if(t - this.getLastFireTime() > Const.EnemyFireGap + this.getRandom().nextInt(1000)) {
            this.setLastFireTime(t);
            return true;
        }else {
            return false;
        }
    }

    public int getDisOfAim() {
        int aimX = this.getFather().getTanks().get(0).getX();
        int aimY = this.getFather().getTanks().get(0).getY();
        return (int) Math.sqrt(Math.pow(aimX - this.getX(), 2) + Math.pow(aimY - this.getY(), 2));
    }

    public int changeDir() {
        int dir = this.getDir(); // 这是原来的方向
        int aimX = this.getFather().getTanks().get(0).getX();
        int aimY = this.getFather().getTanks().get(0).getY();
        int dis = getDisOfAim();
        long t = System.currentTimeMillis();

        if (t - this.getLastChangeDirTime() >= Const.CHANGE_DIR_GAP + this.getRandom().nextInt(1000)) {
            if (dis < Const.Short_Dis) {
                if (this.getHp() < 25) {
                    // 逃跑
                    dir = escape(aimX, aimY);
                } else {
                    // 距离近， 血量厚
                    dir = pursuit(aimX, aimY);
                }
            } else if (dis > Const.Long_Dis){
                // 距离远
                if (this.getHp() < 25) {
                    dir = randomDir(aimX, aimY);
                } else {
                    // 距离远，血量厚
                    dir = pursuit(aimX, aimY);
                }
            }else {
                dir = pursuit(aimX, aimY);
            }
            this.setLastChangeDirTime(t);
        }
        return dir;
    }


    /**
     * 逃跑
     * @param aimX 目标横坐标
     * @param aimY 目标纵坐标，传入玩家坦克或者home
     * @return 方向
     */
    public int escape(int aimX, int aimY) {
        int x = this.getX();
        int y = this.getY();
        int dir;
        if(this.getRandom().nextInt(2) == 0) {
            // 0.5概率改变 X
            if(aimX > x){
                if(this.getRandom().nextInt(100) < 20) {
                    // 2/5 概率逃跑失败
                    dir = Const.RIGHT;
                }else {
                    // 4/5 逃跑成功
                    dir = Const.LEFT;
                }
            }else {
                if(this.getRandom().nextInt(100) < 20) {
                    dir = Const.LEFT;
                }else {
                    dir = Const.RIGHT;
                }
            }
        }else {
            // 0.5 概率改变 y
            if(aimY > y){
                if(this.getRandom().nextInt(100) < 20) {
                    // 2/5 概率逃跑失败
                    dir = Const.DOWN;
                }else {
                    // 4/5 逃跑成功
                    dir = Const.UP;
                }
            }else {
                if(this.getRandom().nextInt(100) < 20) {
                    dir = Const.UP;
                }else {
                    dir = Const.DOWN;
                }
            }
        }
        return dir;
    }

    public int pursuit(int aimX, int aimY) {
        int x = this.getX();
        int y = this.getY();
        int dir = this.getDir();
        if(this.getRandom().nextInt(2) == 0) {
            // 0.5概率改变 X
            if(aimX > x){
                if(this.getRandom().nextInt(100) < 20) {
                    // 2/5 概率追击失败
                    dir = Const.LEFT;
                }else {
                    // 4/5 追击成功
                    dir = Const.RIGHT;
                }
            }else {
                if(this.getRandom().nextInt(100) < 20) {
                    dir = Const.RIGHT;
                }else {
                    dir = Const.LEFT;
                }
            }
        }else {
            // 0.5 概率改变 y
            if(aimY > y){
                if(this.getRandom().nextInt(100) < 20) {
                    // 2/5 概率逃跑失败
                    dir = Const.UP;
                }else {
                    // 4/5 逃跑成功
                    dir = Const.DOWN;
                }
            }else {
                if(this.getRandom().nextInt(100) < 20) {
                    dir = Const.DOWN;
                }else {
                    dir = Const.UP;
                }
            }
        }
        return dir;
    }

    public int randomDir(int aimX, int aimY) {
        return this.getRandom().nextInt(4) + 1; // 1 - 4
    }


    @Override
    public void move() {
        int dir;
        int[] next;
        // 用来改变前进方向
        dir = this.changeDir();
        next = getMoveAim(dir);     // 得到下一个坐标数组
        if (canMove(next[0], next[1])) {
            this.setDir(dir);
            this.setX(next[0]);
            this.setY(next[1]);
        }
    }

    public int[] getMoveAim(int dir) {
        int newX = this.getX();
        int newY = this.getY();
        switch (dir) {
            case Const.UP:
                newY -= this.getSpeed();
                break;
            case Const.DOWN:
                newY += this.getSpeed();
                break;
            case Const.LEFT:
                newX -= this.getSpeed();
                break;
            case Const.RIGHT:
                newX += this.getSpeed();
                break;
            default:
                break;
        }
        return new int[]{newX, newY};
    }


    public boolean canMove(int newX, int newY) {

        if (CollDete.isBeyondBorder(newX, newY, Const.TankWidth)) {
            return false;
        }

        // 判断和其他的坦克碰撞
        // 2 号老是穿过 1 号
        for (int i = 0; i < this.getFather().getTanks().size(); i++) {
            Tank tank = this.getFather().getTanks().get(i);
            if(this.getFather().getTanks().get(i) != this) {
                if(CollDete.isCollide(newX, newY, Const.TankWidth, tank.getX(), tank.getY(), Const.TankWidth)) {
                    //System.out.println("我的ID："+this.getId()+", "+"碰到了ID："+i);
                    return false;
                }
            }
        }

        // 如果和障碍物碰撞
        for (Barrier barrier : this.getFather().getBarriers()) {
            if (barrier.getType() > Const.canMove) {
                if (CollDete.isCollide(newX, newY, Const.TankWidth, barrier.getX(), barrier.getY(), Const.width)) {
                    return false;
                }
            }
        }


        return true;
    }

    public static int numOfBirthPlace(MyPanel father) {
        int a = 0;
        int b = 0;
        for (Tank tank : father.getTanks()) {
            if (CollDete.isCollide(0, 0, Const.TankWidth, tank.getX(), tank.getY(), Const.TankWidth)) {
                a = 1;
                continue;
            }
            if (CollDete.isCollide(47 * Const.TankWidth, 0, Const.TankWidth, tank.getX(), tank.getY(), Const.TankWidth)) {
                b = 1;
            }
        }
        if (a == 0 && b == 0) {
            return 2;
        } else if (a != 0 && b == 0) {
            return 1;
        } else if (a == 0) {
            return -1;
        } else {
            return 0;
        }
    }

    public long getLastChangeDirTime() {
        return lastChangeDirTime;
    }

    public void setLastChangeDirTime(long lastChangeDirTime) {
        this.lastChangeDirTime = lastChangeDirTime;
    }

}