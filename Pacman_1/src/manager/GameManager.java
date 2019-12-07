package manager;

import com.huantt.pacmangame.gui.GUI;
import com.huantt.pacmangame.gui.MyContainer;
import com.huantt.pacmangame.interfaces.OnChangeListener;
import com.huantt.pacmangame.model.*;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Huan on 7/22/2016
 */
public class GameManager {
    private int pacmanNextOrient;
    private int countDownFirePacman;
    private int score;
    private int numberOfNormalBean;
    private Pacman pacman;
    private ArrayList<Item> items;
    private ArrayList<Bullet> bullets;
    private ArrayList<Ghost> ghosts;
    private Swirl swirl;
    private OnChangeListener onChangeListener;
    private PlayerManager playerManager;
    private int numberOfBullet;
    public static final int NUM_OF_ROWS_MAP = 22;
    public static final int NUM_OF_COLUMNS_MAP = 21;
    private static final int MAX_COUNTDOWN_PACMAN = 250;
    private PlayerManagerWAV playerManagerWAV = PlayerManagerWAV.getInstance();
    private int ghostFlyingStep;
    private Map<Integer, Integer> level2step = new HashMap<>();

    {
        level2step.put(1, 10);
        level2step.put(2, 6);
        level2step.put(3, 5);
        level2step.put(4, 3);
    }

    public GameManager(String map, int level) {
        initializeGhost();
        this.ghostFlyingStep = level2step.get(level);
        swirl = new Swirl((NUM_OF_COLUMNS_MAP / 2) * Item.SIZE, (NUM_OF_ROWS_MAP / 2 - 1) * Item.SIZE);
        pacmanNextOrient = Pacman.LEFT;
        pacman = new Pacman((NUM_OF_COLUMNS_MAP / 2) * Item.SIZE, (NUM_OF_ROWS_MAP - 2) * Item.SIZE, Pacman.UP, 1);
        items = new ArrayList<>();
        bullets = new ArrayList<>();
        loadMap(map);
        playerManager = new PlayerManager();
    }

    public int getPacmanNextOrient() {
        return pacmanNextOrient;
    }

    public void setPacmanNextOrient(int pacmanNextOrient) {
        this.pacmanNextOrient = pacmanNextOrient;
    }

    public void loadMap(String fileName) {
        items.clear(); // Xoa data trong list cu khi goi ham nay 2 lan
        File file = new File(getClass().getResource("/res/maps/" + fileName).getPath());
        int row = 0;
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(file, "r");
            String line;
            while ((line = raf.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    int type = line.charAt(i) - '0';
                    switch (type) {
                        case Item.TYPE_STONE:
                            items.add(new Item(Item.SIZE * i, Item.SIZE * row, Item.TYPE_STONE));
                            break;
                        case Item.TYPE_NORMAL_BEAN:
                            numberOfNormalBean++;
                            items.add(new Item(Item.SIZE * i, Item.SIZE * row, Item.TYPE_NORMAL_BEAN));
                            break;
                        case Item.TYPE_BIG_BEAN:
                            items.add(new Item(Item.SIZE * i, Item.SIZE * row, Item.TYPE_BIG_BEAN));
                            break;
                        case Item.TYPE_DOOR:
                            items.add(new Item(Item.SIZE * i, Item.SIZE * row, Item.TYPE_DOOR));
                            break;
                    }
                }
                row++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                raf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public void drawIteam(Graphics2D graphics2D) {
        assert items != null;
        for (int i = 0; i < items.size(); i++) {
            items.get(i).draw(graphics2D);
        }
    }

    public boolean isWin() {
        if (numberOfNormalBean == 0) {
            Rectangle recPacman = new Rectangle(pacman.getX(), pacman.getY(), Pacman.SIZE, Pacman.SIZE);
            if (recPacman.intersects(swirl.getrSwirl())) {
                return true;
            }
        }
        return false;
    }

    public void addChangeScoreListenr(OnChangeListener onChangeScoreListener) {
        this.onChangeListener = onChangeScoreListener;
    }

    //Handle Pacman
    public void handleMovePacMan(int count) {
        if (isWin()) {
            playerManagerWAV.getsHu().stop();
            playerManagerWAV.getsWin().play();
            String name = JOptionPane.showInputDialog(null, "Name");
            this.reCalculateScore();
            playerManager.addPlayer(new Player(name, score));
            MyContainer myContainer = MyContainer.getInstance();
            myContainer.backMenu();
        }
        // Handle va cham voi ghost
        boolean breackFor = false;
        Rectangle rePacman = new Rectangle(pacman.getX(), pacman.getY(), pacman.SIZE - 5, Pacman.SIZE - 5);
        for (int i = 0; i < ghosts.size(); i++) {
            if (rePacman.intersects(ghosts.get(i).getReGhost()) && ghosts.get(i).isDie() == false) {
                onChangeListener.onPacmanDie();
                playerManagerWAV.getsDie().play();
                try {
                    Thread.sleep(1800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                playerManagerWAV.getsDie().stop();
                if (pacman.die() == 0) {
                    String name = JOptionPane.showInputDialog(null, "Name");
                    playerManager.addPlayer(new Player(name, score));
                    MyContainer myContainer = MyContainer.getInstance();
                    myContainer.backMenu();
                    return;
                } else {
                    pacman.setLocation((NUM_OF_COLUMNS_MAP / 2) * Item.SIZE, (NUM_OF_ROWS_MAP - 2) * Item.SIZE);
                    initializeGhost();

                }
            }
        }
        //Handle va cham voi item
        for (int i = 0; i < items.size(); i++) {
            if (pacman.collision(items.get(i))) {
                switch (items.get(i).getType()) {
                    case Item.TYPE_STONE:
                        return;
                    case Item.TYPE_NORMAL_BEAN:
                        playerManagerWAV.getSEatItem().play();
                        items.remove(i);
                        score++;
                        onChangeListener.onChangeScore(score);
                        numberOfNormalBean--;
                        breackFor = true;
                        break;
                    case Item.TYPE_BIG_BEAN:
                        playerManagerWAV.getsEatBullet().play();
                        items.remove(i);
                        numberOfBullet++;
                        onChangeListener.onAddBullet(numberOfBullet);
                        break;
                }
                if (breackFor) break;
            }
        }

        movePacman(count);
    }

    private void reCalculateScore() {
        bullets.forEach(bullet -> score += 10);
        long deadGhosts = ghosts.stream().filter(ghost -> ghost.isDie()).count();
        score += deadGhosts * 20;
        score += pacman.getTurn() * 30;
    }

    public void movePacman(int count) {
        pacman.move(count);
    }

    public void chanePacmanOrient(int orient) {
        pacman.setOrient(orient);

    }

    public void drawPacMan(Graphics2D graphics2D) {
        pacman.draw(graphics2D);
    }

    public boolean canChangeOrientPacman() {
        int xRec = pacman.getX();
        int yRec = pacman.getY();

        switch (pacmanNextOrient) {
            case Pacman.UP:
                yRec--;
                break;
            case Pacman.DOWN:
                yRec++;
                break;
            case Pacman.LEFT:
                xRec--;
                break;
            case Pacman.RIGHT:
                xRec++;
                break;
            default:
                xRec += 2;
                yRec += 2;
        }
        Rectangle reNextOrient = new Rectangle(xRec, yRec, Pacman.SIZE, Pacman.SIZE);
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getType() == Item.TYPE_STONE && reNextOrient.intersects(items.get(i).getRItem())) {
                return false;
            }

        }
        return true;
    }

    public boolean isPacmanChangeOrient() {
        return pacmanNextOrient != pacman.getOrient();

    }

    //Handle Ghost
    public void handleGhostMove(int count) {
        boolean[] isMove = new boolean[4];
        for (int j = 0; j < ghosts.size(); j++) {
            for (Ghost ghost : ghosts) {
                if (ghosts.get(j) != ghost && ghosts.get(j).getReGhost().intersects(ghost.getReGhost()) && !ghost.isDie()) {
                    ghosts.get(j).setDie(false);
                    this.moveGhostToNearWithPacman(ghosts.get(j));
                }
            }

            //Check va vào đá => set isMove = false để đổi chiều
            for (int i = 0; i < items.size(); i++) {
                if (ghosts.get(j).isCollisionStone(items.get(i))) {
                    isMove[j] = false;
                    break;
                } else {
                    isMove[j] = true;
                }
            }

            // Check nếu đang không di chuyển được thì đổi chiều
            if (isMove[j] == true) {
                ghosts.get(j).move(count);
            } else {
                ghosts.get(j).changeOrient(items);
            }
        }
    }

    private void moveGhostToNearWithPacman(Ghost ghost) {
        switch (pacman.getOrient()) {
            case GameObject.UP:
                if (pacman.getY() - ghostFlyingStep * GameObject.SIZE_GAME_OBJECT > GameObject.SIZE_GAME_OBJECT && !isCollisionStone(new Ghost(pacman.getX(), pacman.getY() - ghostFlyingStep * GameObject.SIZE_GAME_OBJECT))) {
                    ghost.setX(pacman.getX());
                    ghost.setY(pacman.getY() - ghostFlyingStep * GameObject.SIZE_GAME_OBJECT);
                    ghost.setOrient(GameObject.DOWN);
                }
                break;
            case GameObject.DOWN:
                if (pacman.getY() + ghostFlyingStep * GameObject.SIZE_GAME_OBJECT < NUM_OF_ROWS_MAP * GameObject.SIZE_GAME_OBJECT && !isCollisionStone(new Ghost(pacman.getX(), pacman.getY() + ghostFlyingStep * GameObject.SIZE_GAME_OBJECT))) {
                    ghost.setX(pacman.getX());
                    ghost.setY(pacman.getY() + ghostFlyingStep * GameObject.SIZE_GAME_OBJECT);
                    ghost.setOrient(GameObject.UP);
                }
                break;
            case GameObject.LEFT:
                if (pacman.getX() - ghostFlyingStep * GameObject.SIZE_GAME_OBJECT > GameObject.SIZE_GAME_OBJECT && !isCollisionStone(new Ghost(pacman.getX() - ghostFlyingStep * GameObject.SIZE_GAME_OBJECT, pacman.getY()))) {
                    ghost.setX(pacman.getX() - ghostFlyingStep * GameObject.SIZE_GAME_OBJECT);
                    ghost.setY(pacman.getY());
                    ghost.setOrient(GameObject.RIGHT);
                }
                break;
            case GameObject.RIGHT:
                if (pacman.getX() + ghostFlyingStep * GameObject.SIZE_GAME_OBJECT < NUM_OF_COLUMNS_MAP * GameObject.SIZE_GAME_OBJECT && !isCollisionStone(new Ghost(pacman.getX() + ghostFlyingStep * GameObject.SIZE_GAME_OBJECT, pacman.getY()))) {
                    ghost.setX(pacman.getX() + ghostFlyingStep * GameObject.SIZE_GAME_OBJECT);
                    ghost.setY(pacman.getY());
                    ghost.setOrient(GameObject.LEFT);
                }
                break;
        }
    }

    boolean isCollisionStone(Ghost ghost) {
        for (int i = 0; i < items.size(); i++) {
            if (ghost.isCollisionStone(items.get(i))) return true;
        }
        return false;
    }

    public void drawGhost(Graphics2D graphics2D) {
        for (int i = 0; i < ghosts.size(); i++) {
            ghosts.get(i).draw(graphics2D);
        }
    }

    private void initializeGhost() {
        ghosts = new ArrayList<>();
        ghosts.add(new Ghost((NUM_OF_COLUMNS_MAP / 2) * Item.SIZE, (NUM_OF_ROWS_MAP / 2 + 5) * Item.SIZE, Ghost.TYPE_BLINKY, 1, Ghost.FOUR_ORIENTS));
        ghosts.add(new Ghost((NUM_OF_COLUMNS_MAP / 2 - 3) * Item.SIZE, (NUM_OF_ROWS_MAP / 2 - 7) * Item.SIZE, Ghost.TYPE_PINKY, 1, Ghost.FOUR_ORIENTS));
        ghosts.add(new Ghost((NUM_OF_COLUMNS_MAP / 2 + 3) * Item.SIZE, (NUM_OF_ROWS_MAP / 2 - 7) * Item.SIZE, Ghost.TYPE_CLYDE, 1, Ghost.FOUR_ORIENTS));
        ghosts.add(new Ghost((NUM_OF_COLUMNS_MAP / 2) * Item.SIZE, (NUM_OF_ROWS_MAP / 2 - 3) * Item.SIZE, Ghost.TYPE_INKY, 1, Ghost.LEFT_RIGHT));
    }

    //Handle Bullet
    public void handleBulletMove() {
        boolean isBreak = false;
        int maxI = bullets.size();
        for (int i = 0; i < bullets.size(); i++) {
            isBreak = false;
            bullets.get(i).move();
            Rectangle reBullet = new Rectangle(bullets.get(i).getX(), bullets.get(i).getY(), Bullet.SIZE, Bullet.SIZE);
            for (int j = 0; j < ghosts.size(); j++) {
                if (reBullet.intersects(ghosts.get(j).getReGhost())) {
                    score += 10;
                    onChangeListener.onChangeScore(score);
                    ghosts.get(j).setDie(true);
                    bullets.remove(i);
                    isBreak = true;
                    break;
                }
            }
            if (isBreak == true) {
                break;
            }
            if (bullets.get(i).getX() < 0 || bullets.get(i).getX() > GUI.WIDTH_FRAME
                    || bullets.get(i).getY() < 0 || bullets.get(i).getY() > GUI.HEIGHT_FRAME) {
                bullets.remove(i);
                maxI = bullets.size();
            }

            for (int j = 0; j < items.size(); j++) {

                if (i < bullets.size() && !bullets.isEmpty() && bullets.get(i).collision(items.get(j))) { // Phải check lại i vì size bullet đã thay đổi có thể nhỏ hơn i
                    bullets.remove(i);
                    isBreak = true;
                    break;
                }
            }
            if (isBreak == true) {
                break;
            }

        }

    }

    public void drawBullet(Graphics2D graphic2D) {
        for (Bullet bullet : bullets) {
            bullet.draw(graphic2D);
        }
    }

    public void fireByPacman() {
        if (numberOfBullet > 0) {
            if (countDownFirePacman == 0) {
                playerManagerWAV.getsBullet().play();
                numberOfBullet--;
                onChangeListener.onAddBullet(numberOfBullet);
                System.out.println("numberOfBullet = " + numberOfBullet);
                Bullet bullet = new Bullet(pacman.getOrient(), pacman.getX(), pacman.getY());
                bullets.add(bullet);
                countDownFirePacman = MAX_COUNTDOWN_PACMAN;
            } else {
                countDownFirePacman -= 10;
            }

        }
    }

    public void drawSwirl(Graphics2D graphics2D) {
        if (numberOfNormalBean == 0) {
            playerManagerWAV.getsHu().loop(Clip.LOOP_CONTINUOUSLY);
            swirl.draw(graphics2D);
        }
    }

}
