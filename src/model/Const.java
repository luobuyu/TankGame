package model;

public class Const {
    public final static int WIN_WIDTH = 1000;
    public final static int WIN_HEIGHT = 800;
    public final static int BULLET_NOR = 1;
    public final static int BULLET_ICE = 2;
    public final static int UP = 1;
    public final static int DOWN = 2;
    public final static int RIGHT = 3;
    public final static int LEFT = 4;
    public final static int PLAYER = 1;
    public final static int ENEMY = 0;
    public final static int TankWidth = 60;
    public final static int width = 20;

    public final static String PlayerTank_IMG_UP = "pictures/tank/MyTank_up.png";
    public final static String PlayerTank_IMG_DOWN = "pictures/tank/MyTank_down.png";
    public final static String PlayerTank_IMG_RIGHT = "pictures/tank/MyTank_right.png";
    public final static String PlayerTank_IMG_LEFT = "pictures/tank/MyTank_left.png";
    public final static String EnemyTank_IMG_UP = "pictures/tank/EnemyTank_up.png";
    public final static String EnemyTank_IMG_DOWN = "pictures/tank/EnemyTank_down.png";
    public final static String EnemyTank_IMG_RIGHT = "pictures/tank/EnemyTank_right.png";
    public final static String EnemyTank_IMG_LEFT = "pictures/tank/EnemyTank_left.png";
    public final static String Tank_Explosion = "pictures/explosion/tank_explosion";
    public final static String Bullet_Explosion = "pictures/explosion/bullet_explosion";

    // 坦克速度
    public final static int TANK_NOR_SPEED = 6;
    public final static int Tank_INC_SPEED = 10;
    public final static int Tank_DEC_SPEED = 3;

    public final static int MAX_HP = 100;

    // 子弹伤害
    public final static int NOR_DAMAGE = 20;
    public final static int INC_DAMAGE = 30;
    public final static int DAMAGE_RAND = 10;
    public final static int BulletNorSpeed = 10;
    public final static int BulletIncSpeed = 13;
    public final static int PlayerFireGap = 200;
    public final static int EnemyFireGap = 1000;

    // 追击
    public final static int Short_Dis = 200;
    public final static int Long_Dis = 500;
    public final static int CHANGE_DIR_GAP = 1500;  // 2秒
    public final static int Img_Gap = 200;

    // 障碍物
    public final static int none = 0;
    public final static int grass = 2;
    public final static int canMove = 2;
    public final static int brick = 3;
    public final static int steel = 4;
    public final static int home = 5;

    // buff
    public final static int propWidth = 30;
    public final static int prop_gap = 10000;
    public final static int changeBullet = 0;
    public final static int increase = 1;
    public final static int protect = 2;
    public final static int recovery = 3;
    public final static int P_prop = 5;
    public final static int last_time = 20000;

    // 出生地
    public final static int Enemy_x1 = 0;
    public final static int Enemy_x2 = 47;
    public final static int Enemy_y = 0;
    public final static int Max_Cur_Enemy = 4;
    public final static int Max_Enemy = 20;
    public final static int Player_x = 17;
    public final static int Player_y = 37;
    public final static int Add_Gap = 2000;
}
